package com.qhit.service.impl;

import com.qhit.mapper.ExamPaperInfoMapper;
import com.qhit.pojo.ExamPaperInfo;
import com.qhit.service.ExamPaperInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ExamPaperInfoServiceImpl implements ExamPaperInfoService {

    @Autowired
    public ExamPaperInfoMapper examPaperInfoMapper;

    @Override
    public int getExamPaperTotal() {
        return examPaperInfoMapper.getExamPaperTotal();
    }

    @Override
    public List<ExamPaperInfo> getExamPaper() {
        return examPaperInfoMapper.getExamPaper();
    }

    @Override
    public ExamPaperInfo getExamPaperById(int examPaperId) {
        return examPaperInfoMapper.getExamPaperById(examPaperId);
    }

    @Override
    public int insertExamPaper(ExamPaperInfo examPaperInfo) {
        return examPaperInfoMapper.insertExamPaper(examPaperInfo);
    }

    @Override
    public int updateExamPaper(ExamPaperInfo examPaperInfo) {
        return examPaperInfoMapper.updateExamPaper(examPaperInfo);
    }

    @Override
    public int deleteExamPaper(int examPaperId) {
        return examPaperInfoMapper.deleteExamPaper(examPaperId);
    }

    @Override
    public List<ExamPaperInfo> getExamPapersClear() {
        return examPaperInfoMapper.getExamPapersClear();
    }

    @Override
    public int isUpdateExamPaperSubjects(Map<String, Object> map) {
        return examPaperInfoMapper.isUpdateExamPaperSubjects(map);
    }

    @Override
    public int isUpdateExamPaperScore(Map<String, Object> map) {
        return examPaperInfoMapper.isUpdateExamPaperScore(map);
    }

    @Override
    public int updateExamPaperSubNumAndScore(int examPaperId, int subjNum, int score) {
        return examPaperInfoMapper.updateExamPaperSubNumAndScore(examPaperId,subjNum,score);
    }

    @Override
    public List<Integer> getSubjectIdByExamPaperId(int examPaperId) {
        return examPaperInfoMapper.getSubjectIdByExamPaperId(examPaperId);
    }

    @Override
    public ExamPaperInfo getOneExamPaperInfoById(int examPaperId) {
        return examPaperInfoMapper.getOneExamPaperInfoById(examPaperId);
    }

    @Override
    public int updateScore(Map<String, Object> map) {
        return examPaperInfoMapper.updateScore(map);
    }

    @Override
    public int upadteSubjects(Map<String, Object> map) {
        return examPaperInfoMapper.upadteSubjects(map);
    }

    @Override
    public int insertExamParerLite(ExamPaperInfo examPaperInfo) {
        return examPaperInfoMapper.insertExamParerLite(examPaperInfo);
    }

    @Override
    public ExamPaperInfo selectExamParerIdByName(String examParerName) {
        return examPaperInfoMapper.selectExamParerIdByName(examParerName);
    }
}
