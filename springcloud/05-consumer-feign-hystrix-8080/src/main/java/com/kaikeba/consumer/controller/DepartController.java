package com.kaikeba.consumer.controller;

import com.kaikeba.consumer.bean.Depart;
import com.kaikeba.consumer.service.DepartService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/consumer/depart")
public class DepartController {
    @Autowired
    private DepartService departService;

    @PostMapping("/save")
    public boolean saveHandle(Depart depart) {
        return departService.saveDepart(depart);
    }

    @GetMapping("/delete/{id}")
    public boolean deleteHandle(@PathVariable("id") Integer id) {
        return departService.deleteDepartById(id);
    }

    @PostMapping("/update")
    public boolean updateHandle(@RequestBody Depart depart) {

        return departService.modifyDepart(depart);
    }

    @HystrixCommand(fallbackMethod = "getHystrixHandle")
    @GetMapping("/get/{id}")
    public Depart getHandle(@PathVariable("id") int id) {
        Depart depart = departService.getDepartById(id);
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
        return departService.listAllDeparts();
    }
}
