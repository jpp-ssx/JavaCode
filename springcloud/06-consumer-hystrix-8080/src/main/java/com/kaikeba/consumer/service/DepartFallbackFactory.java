package com.kaikeba.consumer.service;

import com.kaikeba.consumer.bean.Depart;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

//服务降级处理类
@Component
public class DepartFallbackFactory implements FallbackFactory<DepartService> {
    @Override
    public DepartService create(Throwable throwable) {
        //返回feign接口的匿名内部类
        return new DepartService() {
            @Override
            public boolean saveDepart(Depart depart) {
                System.out.println("执行saveDepart()的服务降级处理方法");
                return false;
            }

            @Override
            public boolean deleteDepartById(Integer id) {
                System.out.println("deleteDepartById()的服务降级处理方法");
                return false;
            }

            @Override
            public boolean modifyDepart(Depart depart) {
                System.out.println("modifyDepart()的服务降级处理方法");
                return false;
            }

            @Override
            public Depart getDepartById(Integer id) {
                System.out.println("getDepartById()的服务降级处理方法");
                Depart depart = new Depart();
                depart.setDbase("no this db");
                depart.setName("no this depart");
                depart.setId(id);
                return depart;
            }

            @Override
            public List<Depart> listAllDeparts() {
                System.out.println("listAllDeparts()的服务降级处理方法");
                return null;
            }
        };
    }
}
