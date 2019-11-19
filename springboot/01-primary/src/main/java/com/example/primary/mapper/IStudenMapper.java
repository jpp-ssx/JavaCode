package com.example.primary.mapper;

import com.example.primary.entity.StudentVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IStudenMapper {
    //插入
    int insertStudent(StudentVO studentVO);

    //删除
    void deleteStudent(String name);

    //查询
    StudentVO findStudentById(int id);
    //查询学生总数
    String queryTotalCount();
}
