package com.kaikeba.consumer.service;

import com.kaikeba.consumer.bean.Depart;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@Service
//指定当前service绑定的提供者微服务名称
@FeignClient("kkbmsc-provider-depart")
@RequestMapping("/provider/depart")//restfull风格 provider+操作对象
public interface DepartService {
    @PostMapping("/save")
    boolean saveDepart(Depart depart);
    @GetMapping("/delete/{id}")
    boolean deleteDepartById(@PathVariable("id") Integer id);
    @PostMapping("/update")
    boolean modifyDepart(Depart depart);
    @GetMapping("/get/{id}")
    Depart getDepartById(@PathVariable("id") Integer id);
    @GetMapping("/list")
    List<Depart> listAllDeparts();
}
