package com.qhit.service.impl;

import java.util.List;

import com.qhit.mapper.StudentExamInfoMapper;
import com.qhit.pojo.StudentExamInfo;
import com.qhit.service.StudentExamInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentExamInfoServiceImpl implements StudentExamInfoService {

	@Autowired
	private StudentExamInfoMapper studentExamInfoMapper;
	
	public List<StudentExamInfo> getStudentExamCountByClassId(int classId) {
		return studentExamInfoMapper.getStudentExamCountByClassId(classId);
	}

	public List<StudentExamInfo> getStudentExamInfo(int studentId) {
		return studentExamInfoMapper.getStudentExamInfo(studentId);
	}

	public List<StudentExamInfo> getAllStudentAvgScoreCount(int classId) {
		return studentExamInfoMapper.getAllStudentAvgScoreCount(classId);
	}

}
