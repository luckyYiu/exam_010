package com.qhit.service.impl;

import cn.hutool.core.util.PageUtil;
import com.qhit.mapper.ExamSubjectMiddleInfoMapper;
import com.qhit.pojo.ExamSubjectMiddleInfo;
import com.qhit.pojo.SubjectInfo;
import com.qhit.service.ExamSubjectMiddleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExamSubjectMiddleServiceImpl implements ExamSubjectMiddleService {
    @Autowired
    private ExamSubjectMiddleInfoMapper examSubjectMiddleInfoMapper;


    @Override
    public int removeSubjectWithExamPaper(Map<String, Object> map) {
        return examSubjectMiddleInfoMapper.removeSubjectWithExamPaper(map);
    }

    @Override
    public int isAddESM(Map<String, Object> map) {
        return examSubjectMiddleInfoMapper.isAddESM(map);
    }

    @Override
    public int insertManySubToExamPaper(Map map) {
        return examSubjectMiddleInfoMapper.insertManySubToExamPaper(map);
    }

    @Override
    public Integer getEsmByExamIdWithSubjectId(Integer examPaperId,Integer subjectId) {
        return examSubjectMiddleInfoMapper.getEsmByExamIdWithSubjectId(examPaperId,subjectId);
    }

    @Override
    public List selectSubjectIdByExamParerId(Integer examPaperId) {
        return examSubjectMiddleInfoMapper.selectSubjectIdByExamParerId(examPaperId);
    }

    @Override
    public Integer getSumScore(Integer examPaperId) {
        return examSubjectMiddleInfoMapper.getSumScore(examPaperId);
    }

    @Override
    public int insertSubIdExamId(List<ExamSubjectMiddleInfo> arrlist) {
        return examSubjectMiddleInfoMapper.insertSubIdExamId(arrlist);
    }

    @Override
    public List<ExamSubjectMiddleInfo> getSubExamPar(int examParerId) {
        return examSubjectMiddleInfoMapper.getSubExamPar(examParerId);
    }


}
