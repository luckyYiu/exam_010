package com.qhit.service;

import com.qhit.pojo.StudentInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface StudentInfoService {

    //根据学生名称获取学生信息
    public StudentInfo getStudentByName(String username);
    //获取学生总数
    public int getStudentTotal();

    //查询所有学生
    public List<StudentInfo> getStudentList();

    //通过编号查询学生信息
    public StudentInfo getStudentById(int studentId);
    //修改学生信息
    public int updateStudent(StudentInfo studentInfo);
    //删除学生
    public int deleteStudent(int studentId);
    //添加学生
    public int insertStudent(StudentInfo studentInfo);

    //通过用户名和密码查询学生信息
    public StudentInfo getStudentByAccountAndPwd(String studentAccount);
    //学生重置密码
    public int isResetPwdWithStudent(StudentInfo studentInfo);
    //通过班级ID查询学生
    public List<StudentInfo> getStudentsByClassId(int classId);

    Map selectCountAll(int gradeId);
}
