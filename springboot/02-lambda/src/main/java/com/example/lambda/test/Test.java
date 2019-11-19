package com.example.lambda.test;

import com.example.lambda.functioninterface.WorkerInterface;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Test {
    public static void execute(WorkerInterface workerInterface) {
        workerInterface.doSomeWork();

    }

    public static void main(String[] args) {
        //两种方式
        //1古老的方式匿名内部类
//        execute(new WorkerInterface() {
//            @Override
//            public void doSomeWork() {
//                System.out.println("匿名内部类方式调用接口方法");
//            }
//        });
//        //lambda表达式调用
//        execute(() -> {
//            System.out.println("lambda表达式方式调用函数式接口方法");
//        });
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
//        list.forEach(n->{System.out.println(n);});
        int a=list.parallelStream().mapToInt(x->x).sum();
        System.out.println(a);
    }


}
