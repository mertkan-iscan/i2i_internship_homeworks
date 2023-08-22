package org.example;

import org.apache.kafka.clients.producer.*;
import java.util.Properties;
import java.util.Random;

public class KafkaPublisher {
    private static final String TOPIC_NAME = "SUBSCRIBER";

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
        Random rand = new Random();

        try {
            while (true) {
                int subsc_id = rand.nextInt(1000);
                String subsc_name = "Name" + subsc_id;
                String subsc_surname = "Surname" + subsc_id;
                String msisdn = "MSISDN" + subsc_id;

                String value = subsc_id + "," + subsc_name + "," + subsc_surname + "," + msisdn;
                ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC_NAME, value);

                producer.send(record, (metadata, e) -> {
                    if (e != null) {
                        e.printStackTrace();
                    } else {
                        System.out.println("Sent message: " + value);
                    }
                });

                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.close();
        }
    }
}
