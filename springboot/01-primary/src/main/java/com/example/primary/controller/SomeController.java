package com.example.primary.controller;

import com.example.primary.entity.StudentVO;
import com.example.primary.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/some")
public class SomeController {
    @Autowired
    IStudentService iStudentService;

    @PostMapping("/addStudent")
    public void addStudent(@RequestBody StudentVO studentVO) {
        iStudentService.insertStudent(studentVO);
    }

    @GetMapping("/deleteStudent/{name}")
    public void deleteStudent(@PathVariable("name") String name) {
        iStudentService.deleteStudent(name);
    }

    @GetMapping("/queryStudent/{id}")
    public StudentVO queryStudent(@PathVariable("id") int id) {
        return iStudentService.findStudentById(id);
    }
    @GetMapping("/queryTotalCount")
    public String queryTotalCount(){
        return iStudentService.queryTotalCount();
    }

}
