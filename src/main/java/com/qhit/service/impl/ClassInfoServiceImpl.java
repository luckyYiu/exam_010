package com.qhit.service.impl;

import com.qhit.mapper.ClassInfoMapper;
import com.qhit.pojo.ClassInfo;
import com.qhit.service.ClassInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassInfoServiceImpl implements ClassInfoService {


    @Autowired
    public ClassInfoMapper classInfoMapper;

    @Override
    public List<ClassInfo> getClassList() {
        return classInfoMapper.getClassList();
    }

    @Override
    public ClassInfo getClassById(int classId) {
        return classInfoMapper.getClassById(classId);
    }

    @Override
    public int insertClass(ClassInfo classInfo) {
        return classInfoMapper.insertClass(classInfo);
    }

    @Override
    public int deleteClass(int classId) {
        return classInfoMapper.deleteClass(classId);
    }

    @Override
    public int updateClass(ClassInfo classInfo) {
        return classInfoMapper.updateClass(classInfo);
    }

    @Override
    public List<ClassInfo> getClassInfoByGradeId(String gradeId) {
        return classInfoMapper.getClassInfoByGradeId(gradeId);
    }

    @Override
    public int getCountClassByGradeId(int gradeId) {
        return classInfoMapper.getCountClassByGradeId(gradeId);
    }

    @Override
    public ClassInfo getOneClassInfoByCid(int classId) {
        return classInfoMapper.getOneClassInfoByCid(classId);
    }

    @Override
    public ClassInfo getClassByTeacherId(int teacherId) {
        return classInfoMapper.getClassByTeacherId(teacherId);
    }
}
