package com.example.reactivestream.pubsub;

import java.util.concurrent.Flow;
import java.util.concurrent.TimeUnit;

public class MySubscriber implements Flow.Subscriber<String> {
    //声明订阅关系
    private Flow.Subscription subscription;

    //当订阅关系建立时该方法会被发布者自动调用
    //发布者传入订阅令牌
    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        //设置一次请求需要订阅消息的数量
        //背压策略体现
        this.subscription.request(3);
    }

    //订阅者每接受一次订阅消息数据时，该方法会被发布者调用一次，故有多少次数据接受就有多少次方法调用
    @Override
    public void onNext(String item) {
        System.out.println("订阅者正在处理的消息数据为：" + item);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //设置再次请求需要订阅的消息的数量
        this.subscription.request(2);
    }

    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
        this.subscription.cancel();
    }

    @Override
    public void onComplete() {
        System.out.println("发布者已关闭，处理器处理完成");
    }
}
