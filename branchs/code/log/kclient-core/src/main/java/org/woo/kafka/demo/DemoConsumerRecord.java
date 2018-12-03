package org.woo.kafka.demo;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Properties;

/**
 * @author Administrator
 * @date 2018 07
 * org.woo.log.DemoConsumerRecord
 */
public class DemoConsumerRecord {
    protected static final Logger log = LoggerFactory.getLogger(DemoConsumerRecord.class);

    public static void main(String[] args){
        //Kafka consumer configuration settings
        String topicName = "demotest";
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.1.106:9092");
        props.put("group.id", "test");
        props.put("enable.auto.commit", "false");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);

        //Kafka Consumer subscribes list of topics here.
        consumer.subscribe(Arrays.asList(topicName));

        //print the topic name
        System.out.println("Subscribed to topic " + topicName);
        int i = 100;
        while (i >= 0) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records)
                // print the offset,key and value for the consumer records.
                log.info("offset = %d, key = %s, value = %s\n",
                        record.offset(), record.key(), record.value());
            i--;
        }
    }
}
