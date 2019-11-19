package com.kaikeba.consumer.controller;

import com.kaikeba.consumer.bean.Depart;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/consumer/depart")
public class DepartController {
    @Autowired
    RestTemplate restTemplate;

    @PostMapping("/save")
    public boolean saveHandle(Depart depart) {
        String url = "http://localhost:8081/provider/depart/save";
        return restTemplate.postForObject(url, depart, Boolean.class);
    }

    @GetMapping("/delete/{id}")
    public void deleteHandle(@PathVariable("id") Integer id) {
        String url = "http://localhost:8081/provider/depart/delete" + id;
        restTemplate.delete(url);
    }

    @PostMapping("/update")
    public void updateHandle(@RequestBody Depart depart) {
        String url = "http://localhost:8081/provider/depart/update";
        restTemplate.put(url, depart);
    }

    //服务降级，当发生异常时则执行fallbackMethod指定的方法getHystrixHandle
    @HystrixCommand(fallbackMethod = "getHystrixHandle")
    @GetMapping("/get/{id}")
    public Depart getHandle(@PathVariable("id") int id) {
        String url = "http://localhost:8081/provider/depart/get" + id;
        Depart depart = restTemplate.getForObject(url, Depart.class);
        if (depart == null) {
            throw new RuntimeException("no this depart" + id);
        }
        return depart;
    }

    public Depart getHystrixHandle(@PathVariable("id") int id) {
        Depart depart = new Depart();
        depart.setDbase("no this db");
        depart.setName("no this depart");
        depart.setId(id);
        return depart;
    }

    @GetMapping("/list")
    public List<Depart> listHandle() {
        String url = "http://localhost:8081/provider/depart/list";
        return restTemplate.getForObject(url, List.class);
    }
}
