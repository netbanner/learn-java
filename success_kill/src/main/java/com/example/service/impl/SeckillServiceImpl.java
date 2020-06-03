package com.example.service.impl;

import com.example.dao.SeckillDao;
import com.example.dao.SuccessKilledDao;
import com.example.dto.Exposer;
import com.example.dto.SeckillExecution;
import com.example.dto.SeckillStatEnum;
import com.example.entity.Seckill;
import com.example.entity.SuccessKilled;
import com.example.exception.RepeatKillException;
import com.example.exception.SeckillCloseException;
import com.example.exception.SeckillException;
import com.example.service.SeckillService;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SeckillServiceImpl implements SeckillService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SeckillServiceImpl.class);

    //加入一个混淆字符串(秒杀接口)的salt，为了我避免用户猜出我们的md5值，值任意给，越复杂越好
    private final String salt = "sadjgioqwelrhaljflutoiu293480523*&%*&*#";

    @Autowired
    private SeckillDao seckillDao;

    @Autowired
    private SuccessKilledDao successKilledDao;


    public SeckillServiceImpl() {
        super();
    }

    @Override
    public List<Seckill> getSeckillList() {
        return seckillDao.queryAll(0,4);
    }

    @Override
    public Seckill getById(long seckill) {
        return seckillDao.queryById(seckill);
    }

    @Override
    public Exposer exportSeckillUrl(long seckillId) {
        Seckill seckill = seckillDao.queryById(seckillId);
        Date startTime = seckill.getStartTime();
        Date endTime = seckill.getEndTime();
        Date nowTime = new Date();

        if(nowTime.getTime()<startTime.getTime()||nowTime.getTime()>endTime.getTime()){
            return new Exposer(false,seckillId,nowTime.getTime(),startTime.getTime(),endTime.getTime());
        }
        String md5 = getMD5(seckillId);

        return new Exposer(true,md5,seckillId);
    }

    @Override
    @Transactional
    public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5) throws SeckillException, RepeatKillException, SeckillCloseException {
    if(md5==null&&!md5.equals(getMD5(seckillId))){
        throw new SeckillException("seckill data rewrite");
    }
    //执行秒杀逻辑:减库存+记录购买行为
        Date now = new Date();
    try {
        int insertSuccessKilled = successKilledDao.insertSuccessKilled(seckillId, userPhone);
        if (insertSuccessKilled <= 0) {
            throw new RepeatKillException("seckill repeat");
        } else {
            int updateCount = seckillDao.reduceNumber(seckillId, now);
            if (updateCount <= 0) {
                throw new SeckillCloseException("seckill close");
            } else {
                SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
                return new SeckillExecution(seckillId, SeckillStatEnum.SUCCESS, successKilled);
            }
        }

    } catch (Exception e) {
        LOGGER.error(e.getMessage(), e);
        //所有编译器异常，转化成运行期异常
        throw new SeckillException("seckill inner error:" + e.getMessage());
    }


    }

    @Override
    public SeckillExecution executeSeckillProcedure(long seckillId, long userPhone, String md5) throws SeckillException, RepeatKillException, SeckillCloseException {
        if (md5 == null || !md5.equals(getMD5(seckillId))) {
            return new SeckillExecution(seckillId, SeckillStatEnum.DATE_REWRITE);
        }
        Date time = new Date();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("seckillId", seckillId);
        map.put("phone", userPhone);
        map.put("killTime", time);
        map.put("result", null);
        try {
            seckillDao.killByProcedure(map);
            int result = MapUtils.getInteger(map, "result", -2);
            if (result == 1) {
                SuccessKilled successKill = successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
                return new SeckillExecution(seckillId, SeckillStatEnum.SUCCESS, successKill);
            } else {
                return new SeckillExecution(seckillId, SeckillStatEnum.stateOf(result));
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return new SeckillExecution(seckillId, SeckillStatEnum.INNER_ERROR);
        }
    }

    private String getMD5(long seckillId) {
        String base = seckillId + "/" + salt;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }


    }
