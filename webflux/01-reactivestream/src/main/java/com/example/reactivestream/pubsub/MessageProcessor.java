package com.example.reactivestream.pubsub;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;

public class MessageProcessor extends SubmissionPublisher<String> implements Flow.Processor<Integer, String> {

    //声明订阅关系
    private Flow.Subscription subscription;

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        //设置一次请求需要订阅消息的数量
        //背压策略体现
        this.subscription.request(3);
    }

    @Override
    public void onNext(Integer item) {
        System.out.println("订阅者正在处理的消息数据为：" + item);
        if (item < 50) {
            System.out.println("我是处理器过滤并处理过的消息：" + item);
        }
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //设置再次请求需要订阅的消息的数量
        this.subscription.request(3);
    }

    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
        this.subscription.cancel();
    }

    @Override
    public void onComplete() {
        System.out.println("发布者已关闭，处理器处理完成");
        //关闭处理器
        this.close();
    }
}
