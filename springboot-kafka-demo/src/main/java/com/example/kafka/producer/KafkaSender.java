package com.example.kafka.producer;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * @author zhuwh
 * @date 2018/11/26 15:02
 * @desc
 */
@Component
public class KafkaSender<T> {

    private Logger logger = LoggerFactory.getLogger(getClass());


    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;

    public void send(T obj){
        String json = JSON.toJSONString(obj);
        logger.info("------------ message = {}", json);

        //发送消息
        ListenableFuture<SendResult<String,Object>> future = kafkaTemplate.send("test-kafka",json);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable throwable) {
                logger.info("Produce: The message failed to be sent:" + throwable.getMessage());
            }

            @Override
            public void onSuccess(@Nullable SendResult<String, Object> stringObjectSendResult) {
                //TODO 业务处理
                logger.info("Produce: The message was sent successfully:");
                logger.info("Produce: _+_+_+_+_+_+_+ result: " + stringObjectSendResult.toString());
            }
        });

    }
}
