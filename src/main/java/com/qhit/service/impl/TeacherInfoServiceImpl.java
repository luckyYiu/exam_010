package com.qhit.service.impl;

import cn.hutool.core.util.PageUtil;
import com.qhit.mapper.TeacherInfoMapper;
import com.qhit.pojo.TeacherInfo;
import com.qhit.service.TeacherInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TeacherInfoServiceImpl implements TeacherInfoService {

    @Autowired
    public TeacherInfoMapper teacherInfoMapper;

    @Override
    public TeacherInfo getTeacherByName(String username) {
        return teacherInfoMapper.getTeacherByName(username);
    }

    @Override
    public int getTeacherTotal() {
        return teacherInfoMapper.getTeacherTotal();
    }

    @Override
    public List<Map> getTeacherList() {
        return teacherInfoMapper.getTeacherList(new HashMap());
    }

    @Override
    public TeacherInfo getTeacherById(int teacherId) {
        return teacherInfoMapper.getTeacherById(teacherId);
    }

    @Override
    public int updateTeacher(TeacherInfo teacherinfo) {
        return teacherInfoMapper.updateTeacher(teacherinfo);
    }

    @Override
    public int insertTeacher(TeacherInfo teacherinfo) {
        return teacherInfoMapper.insertTeacher(teacherinfo);
    }

    @Override
    public int deleteTeacher(int teacherId) {
        return teacherInfoMapper.deleteTeacher(teacherId);
    }

    @Override
    public Map getAllWithPage(int[] ints) {
        int teacherCount = teacherInfoMapper.getCount();
        HashMap hashMap = new HashMap();
        hashMap.put("start",ints[0]);
        hashMap.put("end",12);
        hashMap.put("result",teacherInfoMapper.getTeacherList(hashMap));
        hashMap.put("teacherCount",teacherCount);
        hashMap.put("pageCount", PageUtil.totalPage(teacherCount,12));
        return hashMap;
    }
}
