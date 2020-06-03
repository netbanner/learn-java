package com.zwh.springboot.redis.config;

import com.zwh.springboot.redis.annotation.LockAction;
import com.zwh.springboot.redis.lock.DistributedLock;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Method;

/**
 * @author zhuwh
 * @date 2018/7/27 15:10
 * @desc
 */
@Aspect
@ConditionalOnClass(DistributedLock.class)
@AutoConfigureAfter(DistributedLockAutoConfiguration.class)
public class DistributedLockAspectConfiguration {
    private final Logger logger = LoggerFactory.getLogger(DistributedLockAspectConfiguration.class);

    @Autowired
    private DistributedLock distributedLock;

    private ExpressionParser parser = new SpelExpressionParser();

    private LocalVariableTableParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();

    public Object around(ProceedingJoinPoint pjp) throws  Throwable{
        Method  method = ((MethodSignature)pjp.getSignature()).getMethod();
        LockAction lockAction = method.getAnnotation(LockAction.class);
        String key = lockAction.value();
        Object []args = pjp.getArgs();
        key = parse(key,method,args);

        int retryTimes = lockAction.action().equals(LockAction.LockFailAction.CONTINUE) ? lockAction.retryTimes() : 0;
        boolean lock = distributedLock.lock(key, lockAction.keepMills(), retryTimes, lockAction.sleepMills());
        if(!lock) {
            logger.debug("get lock failed : " + key);
            return null;
        }

        //得到锁,执行方法，释放锁
        logger.debug("get lock success : " + key);
        try {
            return pjp.proceed();
        }catch (Exception e){
            logger.error("execute locked method occured an exception", e);
            throw e;
        } finally {
            boolean releaseResult = distributedLock.realseLock(key);
            logger.debug("release lock : " + key + (releaseResult ? " success" : " failed"));
        }

    }



    private String parse(String key,Method method,Object []args){
        String []parms = discoverer.getParameterNames(method);
        EvaluationContext context = new StandardEvaluationContext();

        for(int i=0;i<parms.length;i++){
            context.setVariable(parms[i],args[i]);
        }

        return parser.parseExpression(key).getValue(context,String.class);
    }
}
