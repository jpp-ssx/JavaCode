package com.example.webfluxservice.repository;

import com.example.webfluxservice.entity.Student;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

//Repository第一个泛型是操作对象的类型
//第二个泛型是操作对象的id类型
public interface StudentRepository extends ReactiveMongoRepository<Student, String> {
    Flux<Student> findByAgeBetween(int below, int top);

    //使用Mongodb原始化操作查询
    @Query("{'age':{'$gte':?0,'$lte':?1}}")
    Flux<Student> queryByAge(int below, int top);
}
