package com.example.rabbit;

import com.example.rabbit.consumer.ConsumerConfig;
import com.example.rabbit.producer.ProducerConfiguration;

import java.util.concurrent.TimeUnit;

public class Runner {
    public static void main(String[] args) throws InterruptedException, Exception {
        ProducerConfiguration producer = new ProducerConfiguration("q1", "q1");
        ConsumerConfig consumer = new ConsumerConfig("q1", "q1", 5);
        int cout = 0;
        while (true) {

            producer.send("Str: " + cout);
            TimeUnit.SECONDS.sleep(2);
            cout++;

        }
    }
}
