package com.qhit.service.impl;

import com.qhit.mapper.ExamPaperInfoMapper;
import com.qhit.mapper.ExamPlanInfoMapper;
import com.qhit.pojo.ExamPlanInfo;
import com.qhit.service.ExamPlanInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ExamPlanInfoServiceImpl implements ExamPlanInfoService {

    @Autowired
    public ExamPlanInfoMapper examPlanInfoMapper;

    @Override
    public List<ExamPlanInfo> getExamPlanList() {
        return examPlanInfoMapper.getExamPlanList();
    }

    @Override
    public int insertExamPlan(ExamPlanInfo examPlanInfo) {
        return examPlanInfoMapper.insertExamPlan(examPlanInfo);
    }

    @Override
    public int updateExamPlan(ExamPlanInfo examPlanInfo) {
        return examPlanInfoMapper.updateExamPlan(examPlanInfo);
    }

    @Override
    public ExamPlanInfo getExamPlanById(int examPlanId) {
        return examPlanInfoMapper.getExamPlanById(examPlanId);
    }

    @Override
    public List<ExamPlanInfo> getStudentWillExam(Map<String, Object> map) {
        return examPlanInfoMapper.getStudentWillExam(map);
    }

    @Override
    public int deleteExamPlan(int examPlanId) {
        return examPlanInfoMapper.deleteExamPlan(examPlanId);
    }

    @Override
    public List<ExamPlanInfo> getExamPlanByClassId(int classId) {
        return examPlanInfoMapper.getExamPlanByClassId(classId);
    }
}
