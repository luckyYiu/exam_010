package com.qhit.service.impl;

import java.util.List;
import java.util.Map;

import com.qhit.mapper.ExamChooseInfoMapper;
import com.qhit.pojo.ExamChooseInfo;
import com.qhit.service.ExamChooseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ExamChooseInfoServiceImpl implements ExamChooseInfoService {

	@Autowired
	private ExamChooseInfoMapper examChooseInfoMapper;

	//根据学生编号，试卷编号，试题编号获取选择记录
	public ExamChooseInfo getChooseWithIds(Map<String, Object> map) {
		return examChooseInfoMapper.getChooseWithIds(map);
	}

	//修改选择记录
	public int updateChooseWithIds(ExamChooseInfo examChoose) {
		return examChooseInfoMapper.updateChooseWithIds(examChoose);
	}

	//添加选择记录
	public int addChoose(Map<String, Object> map) {
		return examChooseInfoMapper.addChoose(map);
	}

	public List<ExamChooseInfo> getChooseInfoWithSumScore(
			Map<String, Object> map) {
		return examChooseInfoMapper.getChooseInfoWithSumScore(map);
	}

	public List<ExamChooseInfo> getChooseInfoWithExamSubject(
			Map<String, Object> map) {
		return examChooseInfoMapper.getChooseInfoWithExamSubject(map);
	}


}
