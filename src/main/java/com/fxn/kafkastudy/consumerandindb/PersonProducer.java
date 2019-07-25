package com.fxn.kafkastudy.consumerandindb;

import com.alibaba.fastjson.JSONObject;
import com.fxn.kafkastudy.bean.PersonBean;
import org.apache.kafka.clients.producer.*;

import java.util.Properties;
import java.util.UUID;

/**
 * kafka push person infos
 */
public class PersonProducer {

    public static void main(String[] args) {

        Properties props = new Properties();
        // Kafka服务端的主机名和端口号
        props.put("bootstrap.servers", "localhost:9092");

        // 等待所有副本节点的应答
        props.put("acks", "all");

        // 消息发送最大尝试次数
        props.put("retries", 0);

        // 一批消息处理大小
        props.put("batch.size", 16384);

        // 请求延时
        props.put("linger.ms", 1);

        // 发送缓存区内存大小
        props.put("buffer.memory", 33554432);

        // key序列化
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        // value序列化
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        //ProducerConfig

        /**
         * 当需要自定义分区时
         */
        props.put("partitioner.class","com.fxn.kafkastudy.threadproducer.RandomPartition");

        Producer<String, String> producer = new KafkaProducer<>(props);

        for (int i = 0; i < 500000000; i++) {
            PersonBean personBean = new PersonBean();
            personBean.setAddress("浙江杭州");
            personBean.setAge(12);
            personBean.setHobby("pingpang");
            personBean.setName("jack");
            personBean.setId(i);
            personBean.setTelePhone("18100171066");
            producer.send(new ProducerRecord<>("kafkastudy10", Integer.toString(i), JSONObject.toJSONString(personBean)));
        }

        producer.close();

    }

}
