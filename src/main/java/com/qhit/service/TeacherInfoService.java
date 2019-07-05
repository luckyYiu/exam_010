package com.qhit.service;

import com.qhit.pojo.TeacherInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TeacherInfoService {

    //根据教师名称获取教师信息
    public TeacherInfo getTeacherByName(String username);
    //获取教师数据总量
    public int getTeacherTotal();

    //查询所有教师
    public List<Map> getTeacherList();

    //根据教师编号获取教师信息
    public TeacherInfo getTeacherById(int teacherId);

    //修改教师信息
    public int updateTeacher(TeacherInfo teacherinfo);

    //添加教师信息
    public int insertTeacher(TeacherInfo teacherinfo);

    //删除教师信息
    public int deleteTeacher(int teacherId);

    //获取教师信息
    public Map getAllWithPage(int[] ints);
}
