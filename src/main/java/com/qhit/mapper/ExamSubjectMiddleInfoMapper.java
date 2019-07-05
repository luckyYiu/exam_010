package com.qhit.mapper;

import com.qhit.pojo.ExamSubjectMiddleInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface ExamSubjectMiddleInfoMapper {

    //查询试题
    List<ExamSubjectMiddleInfo> getSubExamPar(int examParerId);

    //移除试卷中的试题
    public int removeSubjectWithExamPaper(Map<String, Object> map);

    //试卷中添加试题
    public int isAddESM(Map<String, Object> map);

    //为试卷批量添加试题
    int insertManySubToExamPaper(Map map);

    //验证添加记录 是否已经存在
    Integer getEsmByExamIdWithSubjectId(@Param("examPaperId")Integer examPaperId, @Param("subjectId")Integer subjectId);

    List selectSubjectIdByExamParerId(Integer examPaperId);

    //查询试卷总分
    Integer getSumScore(Integer examPaperId);

    int insertSubIdExamId(List<ExamSubjectMiddleInfo> arrlist);

}