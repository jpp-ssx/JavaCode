package com.kaikeba.provider.controller;

import com.kaikeba.provider.bean.Depart;
import com.kaikeba.provider.service.DepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/provider/depart")//restfull风格 provider+操作对象
public class DepartController {
    @Autowired
    DepartService departService;

    @PostMapping("/save")
    public boolean saveHandle(@RequestBody Depart depart) {
        depart.setDbase("jpptest");
        return departService.saveDepart(depart);
    }

    @GetMapping("/delete/{id}")
    public boolean deleteHandle(@PathVariable("id") Integer id) {
        return departService.deleteDepartById(id);
    }

    @PostMapping("/update")
    public boolean updateHandle(@RequestBody Depart depart) {
        depart.setDbase("jpptest");
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
}
