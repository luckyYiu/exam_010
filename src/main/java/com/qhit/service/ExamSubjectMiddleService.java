package com.qhit.service;

import com.qhit.pojo.ExamSubjectMiddleInfo;
import com.qhit.pojo.SubjectInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface ExamSubjectMiddleService {

    //查询试题
    List<ExamSubjectMiddleInfo> getSubExamPar(int examParerId);

    //移除试卷中的试题
    public int removeSubjectWithExamPaper(Map<String, Object> map);

    //试卷中添加试题
    public int isAddESM(Map<String, Object> map);

    //为试卷批量添加试题
    int insertManySubToExamPaper(Map map);

    //验证添加记录 是否已经存在
    Integer getEsmByExamIdWithSubjectId(Integer examPaperId,Integer subjectId);

    List selectSubjectIdByExamParerId(Integer examPaperId);

    //查询试卷总分
    Integer getSumScore(Integer examPaperId);


    int insertSubIdExamId(List<ExamSubjectMiddleInfo> arrlist);
}
