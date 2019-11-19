package com.kaikeba.provider.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Data
@Entity//和表绑定 默认创建depart表
//忽略延迟加载及延迟加载的属性
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "fieldHandler"})
public class Depart {
    @Id//标示为主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//主键自增
    private int id;
    private String name;
    private String dbase;
}
