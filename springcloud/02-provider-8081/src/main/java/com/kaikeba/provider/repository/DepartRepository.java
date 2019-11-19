package com.kaikeba.provider.repository;

import com.kaikeba.provider.bean.Depart;
import org.springframework.data.jpa.repository.JpaRepository;

//第一个泛型为当前repository操作对象类型，第二个为当前操作对象的id
public interface DepartRepository extends JpaRepository<Depart, Integer> {
}
