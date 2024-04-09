package com.example.demo.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.ConsumerAwareListenerErrorHandler;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
public class KafkaConsumer {
    @KafkaListener(groupId = "mygroup", topics = {"mytopic"})
    public void onMessage1(ConsumerRecord<String, Object> record) {
        System.out.println(record.toString());
    }

    //指定topic、partition、offset消费
    @KafkaListener(id = "consumer1", groupId = "mygroup", topicPartitions = {
            @TopicPartition(topic = "topic1", partitions = {"0", "1"}),
            @TopicPartition(topic = "topic2", partitions = "0",
                    partitionOffsets = @PartitionOffset(partition = "1", initialOffset = "8"))
    })
    public void onMessage2(ConsumerRecord<String, Object> record) {
        System.out.println(record.toString());
    }

    // 批量消费
    @KafkaListener(id = "consumer2", groupId = "mygroup", topics = {"mytopic"})
    public void onMessage3(List<ConsumerRecord<?, ?>> records) {
        System.out.println("消费数量" + records.size());
        records.forEach(System.out::println);
    }

    // 新建一个异常处理器，用@Bean注入
    @Bean
    public ConsumerAwareListenerErrorHandler consumerAwareErrorHandler() {
        return (message, exception, consumer) -> {
            System.out.println("消费异常："+message.getPayload());
            return null;
        };
    }

    // 将这个异常处理器的BeanName放到@KafkaListener注解的errorHandler属性里面
    @KafkaListener(topics = {"topic1"},errorHandler = "consumerAwareErrorHandler")
    public void onMessage4(ConsumerRecord<?, ?> record) throws Exception {
        throw new Exception("简单消费-模拟异常");
    }

    // 批量消费也一样，异常处理器的message.getPayload()也可以拿到各条消息的信息
    @KafkaListener(topics = "topic1",errorHandler="consumerAwareErrorHandler")
    public void onMessage5(List<ConsumerRecord<?, ?>> records) throws Exception {
        System.out.println("批量消费一次...");
        throw new Exception("批量消费-模拟异常");
    }

    @Autowired
    private ConsumerFactory consumerFactory;

    // 消息过滤器
    @Bean
    public ConcurrentKafkaListenerContainerFactory filterContainerFactory() {
        ConcurrentKafkaListenerContainerFactory factory = new ConcurrentKafkaListenerContainerFactory();
        factory.setConsumerFactory(consumerFactory);
        // 被过滤的消息将被丢弃
        factory.setAckDiscarded(true);
        // 消息过滤策略
        factory.setRecordFilterStrategy(consumerRecord -> {
            if (Integer.parseInt(consumerRecord.value().toString()) % 2 == 0) {
                return false;
            }
            //返回true消息则被过滤
            return true;
        });
        return factory;
    }

    // 消息过滤监听
    @KafkaListener(topics = {"topic1"},containerFactory = "filterContainerFactory")
    public void onMessage6(ConsumerRecord<?, ?> record) {
        System.out.println(record.value());
    }

    // 消息转发，从topic1接收到的消息经过处理后转发到topic2
    @KafkaListener(topics = {"topic1"})
    @SendTo("topic2")
    public String onMessage7(ConsumerRecord<?, ?> record) {
        return record.value()+"-forward message";
    }
}