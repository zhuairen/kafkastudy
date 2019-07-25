package com.fxn.kafkastudy.demofromintenet.methodone;

public class ConsumerMain {

    public static void main(String[] args) {
        String brokerList = "localhost:9092";
        String groupId = "testGroup1";
        String topic = "kafkastudy10";
        int consumerNum = 10;

        ConsumerGroup consumerGroup = new ConsumerGroup(consumerNum, groupId, topic, brokerList);
        consumerGroup.execute();
    }
}
