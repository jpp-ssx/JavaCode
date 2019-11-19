package com.example.webflux.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
public class SomeController {
    //耗时操作
    private String doSome(String msg) {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }

    @GetMapping("/common")
    public String commomHandle() {
        log.info("common start");
        String msg = this.doSome("common response");
        log.info("common stop");
        return msg;
    }

    @GetMapping("/mono")
    public Mono<String> monoHandle() {
        log.info("webflux mono start");
        //mono 标示包含0或1个元素的异步序列
        Mono<String> mono = Mono.fromSupplier(() -> doSome("webflux momo"));
        log.info("webflux mono stop");
        return mono;
    }

    @GetMapping("/fluxs")
    public Flux<String> fluxHandle() {
        //Flux表示包含0或n个元素的异步序列
        return Flux.just("bj", "sh", "gz");
    }

    @RequestMapping("/array")
    public Flux<String> fluxHandle2(@RequestParam String[] cities) {

        //将数组转为flux
        return Flux.fromArray(cities);
    }

    @RequestMapping("/list")
    public Flux<String> fluxHandle3(@RequestParam List<String> cities) {

        //将list转为stream 再将stream转为flux
        return Flux.fromStream(cities.stream());
    }

    @RequestMapping("/time")
    public Flux<String> timeHandle3(@RequestParam List<String> cities) {
        log.info("flux start");
        Flux<String> flux = Flux.fromStream(cities.stream().map(i -> doSome("element-" + i)));
        log.info("flux end");
        //将flux的每一个元素映射为一个dosome耗时操作
        return flux;
    }
}
