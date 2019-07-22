package com.fxn.kafkastudy.producer;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;

public class ProducerDemo {

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
        props.put("partitioner.class","com.fxn.kafkastudy.producer.ProducerPartitioner");

        Producer<String, String> producer = new KafkaProducer<>(props);

        for (int i = 0; i < 500; i++) {
            producer.send(new ProducerRecord<>("topictest", Integer.toString(i), "----hello world-" + i));
        }


        //带callback的
        for(int i=0;i<500;i++){
            producer.send(new ProducerRecord<>("topictest", "callback"+String.valueOf(i)), new Callback() {
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    if(e!=null){
                        System.out.println("错误");
                        System.out.println(recordMetadata.offset()+"-----"+recordMetadata.partition());
                    }else{
                        System.out.println(recordMetadata.offset()+"-----"+recordMetadata.partition());

                    }
                }
            });
        }
        producer.close();

    }
}
