package com.example.primary.mapper;

import com.example.primary.entity.ScoreVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IScoreMapper {
    void insertScore(ScoreVO scoreVO);
}
