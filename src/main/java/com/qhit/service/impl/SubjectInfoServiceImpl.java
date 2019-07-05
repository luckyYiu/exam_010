package com.qhit.service.impl;

import cn.hutool.core.util.PageUtil;
import com.qhit.mapper.SubjectInfoMapper;
import com.qhit.pojo.SubjectInfo;
import com.qhit.service.SubjectInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SubjectInfoServiceImpl implements SubjectInfoService {

    @Autowired
    public SubjectInfoMapper subjectInfoMapper;

    @Override
    public int getSubjectTotal() {
        return subjectInfoMapper.getSubjectTotal();
    }

    @Override
    public List<SubjectInfo> getSubjectList(Map<String, Object> map) {
        return subjectInfoMapper.getSubjectList(map);
    }

    @Override
    public int addAll(List<SubjectInfo> arrlist) {
        return subjectInfoMapper.addAll(arrlist);
    }

    @Override
    public Map getAllWithPage(int[] ints) {
        int subjectCount = subjectInfoMapper.getCount();
        HashMap hashMap = new HashMap();
        hashMap.put("start",ints[0]);
        hashMap.put("end",12);
        hashMap.put("result",subjectInfoMapper.getSubjectList(hashMap));
        hashMap.put("subjectCount",subjectCount);
        hashMap.put("pageCount", PageUtil.totalPage(subjectCount,12));
        return hashMap;
    }

    @Override
    public int insertSubject(SubjectInfo subjectInfo) {
        return subjectInfoMapper.insertSubject(subjectInfo);
    }

    @Override
    public SubjectInfo getSubjectById(int subjectId) {
        return subjectInfoMapper.getSubjectById(subjectId);
    }

    @Override
    public int updateSubject(SubjectInfo subjectInfo) {
        return subjectInfoMapper.updateSubject(subjectInfo);
    }

    @Override
    public int deleteSubject(int subjectId) {
        return subjectInfoMapper.deleteSubject(subjectId);
    }

    @Override
    public List<SubjectInfo> getSubByExamPaperId(int examPaperId) {
        return subjectInfoMapper.getSubByExamPaperId(examPaperId);
    }

    @Override
    public int insertManySubjectInfo(List<SubjectInfo> list) {
        return subjectInfoMapper.insertManySubjectInfo(list);
    }

    @Override
    public List<SubjectInfo> getManySubjectInfo(SubjectInfo subjectInfo) {
        return subjectInfoMapper.getManySubjectInfo(subjectInfo);
    }

    @Override
    public List getSubjects(SubjectInfo subjectInfo) {
        return subjectInfoMapper.getSubjects(subjectInfo);
    }

    @Override
    public List<Integer> getById(Integer subjectId) {
        return subjectInfoMapper.getById(subjectId);
    }

    @Override
    public SubjectInfo selectSubIdByName(String subjectName) {
        return subjectInfoMapper.selectSubIdByName(subjectName);
    }


}
