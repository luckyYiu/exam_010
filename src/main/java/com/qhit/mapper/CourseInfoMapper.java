package com.qhit.mapper;

import com.qhit.pojo.CourseInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseInfoMapper {

    //查询所有科目
    public List<CourseInfo> getCourseList();

    //根据编号获取科目
    public CourseInfo getCourseById(int courseId);

    //添加科目
    public int insertCourse(CourseInfo courseInfo);

    //修改年级
    public int updateCourse(CourseInfo courseInfo);

    //删除年级
    public int deleteCourse(int courseId);

    //    根据分科情况查询科目
    public List<CourseInfo> getManyCourseByDivision(int division);

}