package com.example.primary.service.impl;

import com.example.primary.entity.StudentVO;
import com.example.primary.mapper.IStudenMapper;
import com.example.primary.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@Service
public class StudentServiceImpl implements IStudentService {
    @Autowired
    IStudenMapper iStudenMapper;
    @Autowired
    RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();

    @Transactional(propagation = Propagation.REQUIRED)
//    @CacheEvict(value = "realTimeCache", allEntries = true)//增删改的时候删除缓存
    @Override
    public void insertStudent(StudentVO studentVO) {
         iStudenMapper.insertStudent(studentVO);
    }

    @CacheEvict(value = "realTimeCache", allEntries = true)//增删改的时候删除缓存
    @Override
    public void deleteStudent(String name) {
        iStudenMapper.deleteStudent(name);
    }

    //根据id查询学生信息时可以利用缓存
    @Cacheable(value = "realTimeCache", key = "'student_'+#id")
    @Override
    public StudentVO findStudentById(int id) {
        return iStudenMapper.findStudentById(id);
    }

    @Override
    public String queryTotalCount() {
        //获取redis操作对象
        BoundValueOperations<Object, Object> ops = redisTemplate.boundValueOps("count");
//从缓存中读取数据
        Object count = ops.get();
        if (count == null) {
            synchronized (this) {
                count = ops.get();
                if (count == null) {
                    count = iStudenMapper.queryTotalCount();
                    ops.set(count, 10, TimeUnit.SECONDS);
                }
            }
        }
        return count.toString();
    }
}
