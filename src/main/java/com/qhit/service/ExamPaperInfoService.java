package com.qhit.service;

import com.qhit.pojo.ExamPaperInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ExamPaperInfoService {

    //获取试卷数据总量
    public int getExamPaperTotal();

    //查询所有试卷
    public List<ExamPaperInfo> getExamPaper();

    //通过试卷编号获取试卷信息
    public ExamPaperInfo getExamPaperById(int examPaperId);

    //添加试卷
    public int insertExamPaper(ExamPaperInfo examPaperInfo);

    //修改试卷
    public int updateExamPaper(ExamPaperInfo examPaperInfo);

    //删除试卷
    public int deleteExamPaper(int examPaperId);

    //获取所有的试卷信息 -- 纯净的
    public List<ExamPaperInfo> getExamPapersClear();

    //修改试卷试题总量
    public int isUpdateExamPaperSubjects(Map<String, Object> map);

    //修改试卷总分
    public int isUpdateExamPaperScore(Map<String, Object> map);

    // 修改试卷中的试题数量和分数
    int updateExamPaperSubNumAndScore(int examPaperId, int subjNum, int score);

    //    根据试卷编号查询该试卷中的试题编号
    List<Integer> getSubjectIdByExamPaperId(int examPaperId);
    //       查看试卷试题
    ExamPaperInfo getOneExamPaperInfoById(int examPaperId);

    int updateScore(Map<String, Object> map);

    int upadteSubjects(Map<String, Object> map);

    int insertExamParerLite(ExamPaperInfo examPaperInfo);

    ExamPaperInfo selectExamParerIdByName(String examParerName);
}
