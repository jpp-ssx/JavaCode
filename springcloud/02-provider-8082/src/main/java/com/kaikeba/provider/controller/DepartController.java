package com.kaikeba.provider.controller;

import com.kaikeba.provider.bean.Depart;
import com.kaikeba.provider.service.DepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/provider/depart")//restfull风格 provider+操作对象
public class DepartController {
    @Autowired
    DepartService departService;
    @Autowired
    private DiscoveryClient discoveryClient;

    @PostMapping("/save")
    public boolean saveHandle(@RequestBody Depart depart) {
        depart.setDbase("jpptest02");
        return departService.saveDepart(depart);
    }

    @GetMapping("/delete/{id}")
    public boolean deleteHandle(@PathVariable("id") Integer id) {
        return departService.deleteDepartById(id);
    }

    @PostMapping("/update")
    public boolean updateHandle(@RequestBody Depart depart) {
        depart.setDbase("jpptest02");
        return departService.modifyDepart(depart);
    }

    @GetMapping("/get/{id}")
    public Depart getHandle(@PathVariable("id") int id) {
        return departService.getDepartById(id);
    }

    @GetMapping("/list")
    public List<Depart> listHandle() {
        return departService.listAllDeparts();
    }

    @GetMapping("/discovery")
    public Object discoveryHandle() {
        //获取服务注册表中所有微服务名称
        List<String> springApplicationNames = discoveryClient.getServices();
        for (String name : springApplicationNames) {
            //获取指定微服务名称服务的所有提供者主机
            List<ServiceInstance> instances = discoveryClient.getInstances(name);
            for (ServiceInstance serviceInstance : instances) {
                String host = serviceInstance.getHost();
                int port = serviceInstance.getPort();
                System.out.println(host + ":" + port);
            }
        }
        return springApplicationNames;
    }

}
