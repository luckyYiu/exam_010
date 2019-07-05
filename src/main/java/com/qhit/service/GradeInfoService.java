package com.qhit.service;

import com.qhit.pojo.GradeInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface GradeInfoService {

    //获取所有年级
    public List<GradeInfo> getGrade();

    //根据编号获取年级
    public GradeInfo getGradeById(int gradeId);

    //添加年级
    public int insertGrade(GradeInfo gradeInfo);

    //删除年级
    public int deleteGrade(int gradeId);

    //修改年级
    public int updateGrade(GradeInfo gradeInfo);

}
