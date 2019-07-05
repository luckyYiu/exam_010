package com.qhit.service.impl;

import com.qhit.mapper.StudentInfoMapper;
import com.qhit.pojo.StudentInfo;
import com.qhit.service.StudentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StudentInfoServiceImpl implements StudentInfoService {

    @Autowired
    public StudentInfoMapper studentInfoMapper;

    @Override
    public StudentInfo getStudentByName(String username) {
        return studentInfoMapper.getStudentByName(username);
    }

    @Override
    public int getStudentTotal() {
        return studentInfoMapper.getStudentTotal();
    }

    @Override
    public List<StudentInfo> getStudentList() {
        return studentInfoMapper.getStudentList();
    }

    @Override
    public StudentInfo getStudentById(int studentId) {
        return studentInfoMapper.getStudentById(studentId);
    }

    @Override
    public int updateStudent(StudentInfo studentInfo) {
        return studentInfoMapper.updateStudent(studentInfo);
    }

    @Override
    public int deleteStudent(int studentId) {
        return studentInfoMapper.deleteStudent(studentId);
    }

    @Override
    public int insertStudent(StudentInfo studentInfo) {
        return studentInfoMapper.insertStudent(studentInfo);
    }

    @Override
    public StudentInfo getStudentByAccountAndPwd(String studentAccount) {
        return studentInfoMapper.getStudentByAccountAndPwd(studentAccount);
    }

    @Override
    public int isResetPwdWithStudent(StudentInfo studentInfo) {
        return studentInfoMapper.isResetPwdWithStudent(studentInfo);
    }

    @Override
    public List<StudentInfo> getStudentsByClassId(int classId) {
        return studentInfoMapper.getStudentsByClassId(classId);
    }

    @Override
    public Map selectCountAll(int gradeId) {
        return studentInfoMapper.selectCountAll(gradeId);
    }
}
