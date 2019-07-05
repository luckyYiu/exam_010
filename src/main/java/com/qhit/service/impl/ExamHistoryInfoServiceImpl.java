package com.qhit.service.impl;

import com.qhit.mapper.ExamHistoryInfoMapper;
import com.qhit.pojo.ExamHistoryInfo;
import com.qhit.pojo.ExamHistoryPaper;
import com.qhit.service.ExamHistoryInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ExamHistoryInfoServiceImpl implements ExamHistoryInfoService {

    @Autowired
    public ExamHistoryInfoMapper examHistoryInfoMapper;

    @Override
    public List<ExamHistoryPaper> getExamHistoryToStudent(int studentId) {
        return examHistoryInfoMapper.getExamHistoryToStudent(studentId);
    }

    @Override
    public int isAddExamHistory(Map<String, Object> map) {
        return examHistoryInfoMapper.isAddExamHistory(map);
    }


    @Override
    public int getHistoryInfoWithIds(Map<String, Object> map) {
        return examHistoryInfoMapper.getHistoryInfoWithIds(map);
    }

    @Override
    public List<ExamHistoryInfo> getExamHistoryToTeacher() {
        return examHistoryInfoMapper.getExamHistoryToTeacher();
    }

    @Override
    public int getHitorysNumByExamIdAndStuId(int examPaperId, int studentId) {
        return examHistoryInfoMapper.getHitorysNumByExamIdAndStuId(examPaperId,studentId);
    }
}
