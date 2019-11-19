package com.example.webfluxservice.controller;

import com.example.webfluxservice.entity.Student;
import com.example.webfluxservice.repository.StudentRepository;
import com.example.webfluxservice.util.ValidateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;

    //以数组形式一次性返回数据
    @GetMapping("/all")
    public Flux<Student> getAll() {
        return studentRepository.findAll();
    }

    //以sse形式实时返回数据
    @GetMapping(value = "/sse/all", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Student> getAllSSE() {
        return studentRepository.findAll();
    }

    //form表单提交保存 当id为空的时候是新增保存 当id有值的时候是修改保存
    @PostMapping("/save")
    public Mono<Student> saveStudent(@Valid Student student) {
        return studentRepository.save(student);
    }

    //json形式提交
    @PostMapping("/save2")
    public Mono<Student> savesStudent(@Valid @RequestBody Student student) {
        ValidateUtil.valideName(student.getName());
        return studentRepository.save(student);
    }

    //无状态删除 删除不存在的数据其响应码仍是200
    @DeleteMapping("/delete/{id}")
    public Mono<Void> deleteStudent(@PathVariable("id") String id) {
        return studentRepository.deleteById(id);
    }

    //有状态删除 删除对象存在 且删除成功返回200 否则返回404
    //ResponseEntity响应实体 包括响应数据和状态码
    //ResponseEntity<Void>代表只返回响应码不返回数据了
    //Mono的flatMap与Map均可做元素映射，flatMap是异步的，如果包含多个操作，使用flatMAP 如果单一操作 使用Map
    //如果一个方法没有返回值，但想要其有返回值则可使用then()
    //defaultIfEmpty()方法执行的是如果Mono没有返回值则执行此方法
    @DeleteMapping("/deleteStatus/{id}")
    public Mono<ResponseEntity<Void>> deleteStatusStudent(@PathVariable("id") String id) {
        return studentRepository.findById(id)
                .flatMap(stu -> studentRepository.delete(stu).then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK))))
                .defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));

    }

    //修改 如果存在则返回对象及响应码 如果不存在返回404
    @PutMapping("/update/{id}")
    public Mono<ResponseEntity<Student>> updateStudent(@PathVariable("id") String id, @Valid @RequestBody Student student) {
        return studentRepository.findById(id).flatMap(stu -> {
            stu.setAge(student.getAge());
            stu.setName(student.getName());
            return studentRepository.save(stu);
        }).map(stu -> new ResponseEntity<Student>(stu, HttpStatus.OK)).defaultIfEmpty(new ResponseEntity<Student>(HttpStatus.NOT_FOUND
        ));

    }

    //根据id查询 如果存在返回数据及响应码，如果不存在返回404
    @GetMapping("/findById/{id}")
    public Mono<ResponseEntity<Student>> findById(@PathVariable("id") String id) {
        return studentRepository.findById(id).map(stu -> new ResponseEntity<Student>(stu, HttpStatus.OK)).defaultIfEmpty(new ResponseEntity<Student>(HttpStatus.NOT_FOUND));

    }

    //根据年龄的上下限查询 一次性查询
    @GetMapping("/findByAge/{below}/{top}")
    public Flux<Student> findByAge(@PathVariable("below") int below, @PathVariable("top") int top) {
        return studentRepository.findByAgeBetween(below, top);
    }

    //根据年龄的上下限查询 实时查询
    @GetMapping(value = "/findSseByAge/{below}/{top}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Student> findSseByAge(@PathVariable("below") int below, @PathVariable("top") int top) {
        return studentRepository.findByAgeBetween(below, top);
    }

    //Mongodb根据年龄的上下限查询 一次性查询
    @GetMapping(value = "/queryByAge/{below}/{top}")
    public Flux<Student> queryByAge(@PathVariable("below") int below, @PathVariable("top") int top) {
        return studentRepository.queryByAge(below, top);
    }

    //Mongodb根据年龄的上下限查询 实时性查询
    @GetMapping(value = "/querySseByAge/{below}/{top}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Student> querySseByAge(@PathVariable("below") int below, @PathVariable("top") int top) {
        return studentRepository.queryByAge(below, top);
    }
}
