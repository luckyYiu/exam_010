package com.qhit.mapper;

import com.qhit.pojo.ClassInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassInfoMapper {

    //获取所有班级
    public List<ClassInfo> getClassList();

    //根据编号获取班级
    public ClassInfo getClassById(int classId);

    //添加班级
    public int insertClass(ClassInfo classInfo);

    //删除班级
    public int deleteClass(int classId);

    //修改班级
    public int updateClass(ClassInfo classInfo);

    //通过年级编号获取班级
    public List<ClassInfo> getClassInfoByGradeId(String gradeId);

    //查询年级下是否有班级
    public int getCountClassByGradeId(int gradeId);

    //   根据班级的id获取该班级的信息
    public ClassInfo getOneClassInfoByCid(int classId);

    //根据当前班级班主任编号获取当前班级信息
    public ClassInfo getClassByTeacherId(int teacherId);
}