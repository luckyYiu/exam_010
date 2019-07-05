package com.qhit.service.impl;

import com.qhit.mapper.CourseInfoMapper;
import com.qhit.pojo.CourseInfo;
import com.qhit.service.CourseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseInfoServiceImpl implements CourseInfoService {

    @Autowired
    public CourseInfoMapper courseInfoMapper;

    @Override
    public List<CourseInfo> getCourseList() {
        return courseInfoMapper.getCourseList();
    }

    @Override
    public CourseInfo getCourseById(int courseId) {
        return courseInfoMapper.getCourseById(courseId);
    }

    @Override
    public int insertCourse(CourseInfo courseInfo) {
        return courseInfoMapper.insertCourse(courseInfo);
    }

    @Override
    public int updateCourse(CourseInfo courseInfo) {
        return courseInfoMapper.updateCourse(courseInfo);
    }

    @Override
    public int deleteCourse(int courseId) {
        return courseInfoMapper.deleteCourse(courseId);
    }

    @Override
    public List<CourseInfo> getManyCourseByDivision(int division) {
        return courseInfoMapper.getManyCourseByDivision(division);
    }
}
