package com.example.demo.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {

    private final String TOPIC = "mytopic";

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

//    @Autowired
//    private AdminClient adminClient;

    //https://blog.csdn.net/yuanlong122716/article/details/105160545/
    @PostMapping("/testKafka")
    public Boolean test() {
        //adminClient.createTopics();
        // 回调
        kafkaTemplate.send(TOPIC, "ok").addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("发送失败");
            }

            @Override
            public void onSuccess(SendResult<String, Object> stringObjectSendResult) {
                System.out.println("发送成功");
                System.out.println(stringObjectSendResult.getRecordMetadata().toString());
            }
        });
        // 事务
        kafkaTemplate.executeInTransaction(new KafkaOperations.OperationsCallback<String, Object, Object>() {
            @Override
            public Object doInOperations(KafkaOperations<String, Object> kafkaOperations) {
                kafkaOperations.send(TOPIC, "step 1");
                kafkaOperations.send(TOPIC, "step 2");
                return null;
            }
        });
        kafkaTemplate.send(TOPIC, "step 3");

        return true;
    }
}