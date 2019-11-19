package com.example.reactivestream.pubsub;

import java.util.Random;
import java.util.concurrent.SubmissionPublisher;

public class MyTest {
    public static void main(String[] args) {
        SubmissionPublisher<Integer> publisher = null;
        try {
            //发布者
            publisher = new SubmissionPublisher<>();
            //处理器
            MessageProcessor messageProcessor = new MessageProcessor();
            //订阅者
            MySubscriber mySubscriber = new MySubscriber();
            publisher.subscribe(messageProcessor);
            messageProcessor.subscribe(mySubscriber);
//            publisher.subscribe(mySubscriber);
            for (int i = 0; i < 10; i++) {
                publisher.submit(new Random().nextInt(100));
            }
        } finally {
            publisher.close();
        }
        try {
            System.out.println("主线程开始等待");
            Thread.currentThread().join(15000);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
