package com.qhit.mapper;

import com.qhit.pojo.ExamPlanInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ExamPlanInfoMapper {

    //查询所有待考信息
    public List<ExamPlanInfo> getExamPlanList();

    //添加待考信息
    public int insertExamPlan(ExamPlanInfo examPlanInfo);

    //修改待考信息
    public int updateExamPlan(ExamPlanInfo examPlanInfo);
    //通过ID获取待考信息
    public ExamPlanInfo getExamPlanById(int examPlanId);

    //查询学生待考信息
    public List<ExamPlanInfo> getStudentWillExam(Map<String, Object> map);

    //删除待考信息
    public int deleteExamPlan(int examPlanId);

    /*根据班级编号查询，某个班级是否有考试安排*/
    List<ExamPlanInfo> getExamPlanByClassId(int classId);
}