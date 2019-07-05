package com.qhit.mapper;

import java.util.List;

import com.qhit.pojo.StudentExamInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentExamInfoMapper {

	//后台教师根据查看某一班级下所有学生考试数量
	public List<StudentExamInfo> getStudentExamCountByClassId(int classId);
	
	//后台教师查看某一学生的考试信息
	public List<StudentExamInfo> getStudentExamInfo(int studentId);
	
	public List<StudentExamInfo> getAllStudentAvgScoreCount(int classId);
}
