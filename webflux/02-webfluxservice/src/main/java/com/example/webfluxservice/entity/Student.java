package com.example.webfluxservice.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Data
@Document(collection = "t_student")//指定在mogodb中生成的表
public class Student {
    @Id//mogodb中id一般为String类型
    private String id;
    @NotBlank(message = "姓名不能为空")
    private String name;
    @Range(min = 23, max = 30, message = "年龄必须在${min}-${max}范围")
    private Integer age;
}
