package com.qhit.service;

import com.qhit.pojo.SubjectInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SubjectInfoService {

    //获取试题总数
    public int getSubjectTotal();

    //查询所有试题
    public List<SubjectInfo> getSubjectList(Map<String, Object> map);

    //导入Excel
    public int addAll(List<SubjectInfo> arrlist);

    //分页获取试题信息
    public Map getAllWithPage(int[] ints);

    //添加试题
    public int insertSubject(SubjectInfo subjectInfo);

    //通过试题编号获取试题信息
    public SubjectInfo getSubjectById(int subjectId);

    //修改试题
    public int updateSubject(SubjectInfo subjectInfo);

    //删除试题
    public int deleteSubject(int subjectId);

    //    根据试卷ID获取试卷中所有试题信息
    List<SubjectInfo> getSubByExamPaperId(int examPaperId);

    //    导入试题，批量添加
    int insertManySubjectInfo(List<SubjectInfo> list);
    //    多个条件限制查询试题
    List<SubjectInfo> getManySubjectInfo(SubjectInfo subjectInfo);


    List getSubjects(SubjectInfo subjectInfo);

    List<Integer> getById(Integer subjectId);

    SubjectInfo selectSubIdByName(String subjectName);
}
