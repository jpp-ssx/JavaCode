package com.example.primary.service.impl;

import com.example.primary.entity.ScoreVO;
import com.example.primary.entity.StudentVO;
import com.example.primary.mapper.IScoreMapper;
import com.example.primary.service.IScoreService;
import com.example.primary.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ScoreServiceImpl implements IScoreService {
    @Autowired
    IScoreMapper iScoreMapper;
    @Autowired
    IStudentService iStudentService;

//    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void insertScore() {
        StudentVO studentVO = new StudentVO();
        studentVO.setName("张三");
        studentVO.setSex("女");
        studentVO.setAge(18);
        iStudentService.insertStudent(studentVO);
        ScoreVO scoreVO=new ScoreVO();
        scoreVO.setScore(99);
        scoreVO.setStudent_id(studentVO.getId());
        scoreVO.setSubject_name("语文");
        iScoreMapper.insertScore(scoreVO);
        int i=3/0;
    }
}
