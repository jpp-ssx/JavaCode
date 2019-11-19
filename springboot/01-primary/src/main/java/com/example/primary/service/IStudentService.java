package com.example.primary.service;

import com.example.primary.entity.StudentVO;

public interface IStudentService {
    //插入
    void insertStudent(StudentVO studentVO);
    //删除
    void deleteStudent(String name);
    //查询
    StudentVO findStudentById(int id);
    //查询学生总数 不要求精确
    String queryTotalCount();
}
