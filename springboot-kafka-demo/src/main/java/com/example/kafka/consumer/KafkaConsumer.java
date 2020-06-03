package com.example.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

/**
 * @author zhuwh
 * @date 2018/11/26 15:18
 * @desc
 */
@Component
public class KafkaConsumer {

    private Logger logger = LoggerFactory.getLogger(getClass());


    @KafkaListener(id = "tut",topics = "test-kafka")
    public void listern(ConsumerRecord<?,?> record, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic){
        if(record.value()!=null){
            //获取消息
            Object message = record.value();

            logger.info("Receive： +++++++++++++++ Topic:" + topic);
            logger.info("Receive： +++++++++++++++ Record:" + record);
            logger.info("Receive： +++++++++++++++ Message:" + message);
        }
    }
}
