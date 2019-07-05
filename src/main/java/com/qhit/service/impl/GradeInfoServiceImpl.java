package com.qhit.service.impl;

import com.qhit.mapper.GradeInfoMapper;
import com.qhit.pojo.GradeInfo;
import com.qhit.service.GradeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GradeInfoServiceImpl implements GradeInfoService {

    @Autowired
    public GradeInfoMapper gradeInfoMapper;


    @Override
    public List<GradeInfo> getGrade() {
        return gradeInfoMapper.getGrade();
    }

    @Override
    public GradeInfo getGradeById(int gradeId) {
        return gradeInfoMapper.getGradeById(gradeId);
    }

    @Override
    public int insertGrade(GradeInfo gradeInfo) {
        return gradeInfoMapper.insertGrade(gradeInfo);
    }

    @Override
    public int deleteGrade(int gradeId) {
        return gradeInfoMapper.deleteGrade(gradeId);
    }

    @Override
    public int updateGrade(GradeInfo gradeInfo) {
        return gradeInfoMapper.updateGrade(gradeInfo);
    }
}
