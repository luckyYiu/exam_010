package com.qhit.mapper;

import java.util.List;
import java.util.Map;

import com.qhit.pojo.ExamChooseInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface ExamChooseInfoMapper {

	public ExamChooseInfo getChooseWithIds(Map<String, Object> map);

	public int updateChooseWithIds(ExamChooseInfo examChoose);

	public int addChoose(Map<String, Object> map);

	public List<ExamChooseInfo> getChooseInfoWithSumScore(Map<String, Object> map);

	public List<ExamChooseInfo> getChooseInfoWithExamSubject(Map<String, Object> map);

}
