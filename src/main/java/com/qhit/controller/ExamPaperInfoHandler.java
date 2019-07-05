package com.qhit.controller;

import com.qhit.pojo.ClassInfo;
import com.qhit.pojo.CourseInfo;
import com.qhit.pojo.ExamPaperInfo;
import com.qhit.pojo.GradeInfo;
import com.qhit.service.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("examPaper")
public class ExamPaperInfoHandler {

    @Autowired
    public ExamPaperInfoService examPaperInfoService;
    @Autowired
    public ExamHistoryInfoService examHistoryInfoService;
    @Autowired
    public ClassInfoService classInfoService;
    @Autowired
    public SubjectInfoService subjectInfoService;
    @Autowired
    public GradeInfoService gradeInfoService;

    @RequestMapping("examPaperList")
    public ModelAndView examPaperList(Model model){
        List<ExamPaperInfo> examPapers = examPaperInfoService.getExamPaper();
        model.addAttribute("examPapers",examPapers);
        return new ModelAndView("admin/examPapers");
    }

    @RequestMapping("getExamPaperById")
    public ModelAndView getExamPaperById(int examPaperId){
        ExamPaperInfo examPaper = examPaperInfoService.getExamPaperById(examPaperId);
        List<GradeInfo> grades = gradeInfoService.getGrade();
        ModelAndView mo = new ModelAndView();
        mo.addObject("examPaper",examPaper);
        mo.addObject("grades",grades);
        mo.setViewName("admin/examPaperedit1");
        return mo;
    }

    @RequestMapping("insertExamPaper")
    public @ResponseBody Map insertExamPaper(Model model, ExamPaperInfo examPaperInfo){
        HashMap hashMap = new HashMap();
        int num = examPaperInfoService.insertExamPaper(examPaperInfo);
        if(num==0){
            hashMap.put("state",1);
            hashMap.put("msg","插入失败！");
        }else{
            hashMap.put("state",0);
            hashMap.put("msg","插入成功！");
        }
        return hashMap;
    }

    @RequestMapping("deleteExamPaper")
    public @ResponseBody Map deleteExamPaper(Model model,int examPaperId){
        HashMap hashMap = new HashMap();
        int num = examPaperInfoService.deleteExamPaper(examPaperId);
        if(num==0){
            hashMap.put("state",1);
            hashMap.put("msg","删除失败！");
        }else{
            hashMap.put("state",0);
            hashMap.put("msg","删除成功！");
        }
        return hashMap;
    }

    @RequestMapping("updateExamPaper")
    public @ResponseBody Map updateExamPaper(Model model, ExamPaperInfo examPaperInfo){
        HashMap hashMap = new HashMap();
        int num = examPaperInfoService.updateExamPaper(examPaperInfo);
        if(num==0){
            hashMap.put("state",1);
            hashMap.put("msg","修改失败！");
        }else {
            hashMap.put("state",0);
            hashMap.put("msg","修改成功！");
        }
        return hashMap;
    }

    /**
     * 预添加试卷信息
     * @return
     */
    @RequestMapping("/preAddExamPaper")
    public ModelAndView preAddCourse() {
        ModelAndView model = new ModelAndView();
        model.setViewName("/admin/examPaperedit");
        /** 获取年级集合 */
        List<GradeInfo> grades = gradeInfoService.getGrade();
        model.addObject("grades", grades);
        return model;
    }

    @RequestMapping("joinToExaming")
    public ModelAndView joinToExaming(int examPaperId, int studentId, int classId, int examTime, String beginTime){
        ModelAndView mo = new ModelAndView();
        /*判断该学生是否已经参加过该次考试*/
        int count = examHistoryInfoService.getHitorysNumByExamIdAndStuId(examPaperId, studentId);
        if(count>=1){
            mo.setViewName("error");
            mo.addObject("error","你已经参加过该次考试");
        }else{
            mo.setViewName("reception/exam");
            mo.addObject("beginTime",beginTime);
            mo.addObject("examTime",examTime);
            mo.addObject("classId",classId);
            mo.addObject("examPaperId",examPaperId);
            ClassInfo oneClassInfoByCid = classInfoService.getOneClassInfoByCid(classId);
            mo.addObject("gradeId",oneClassInfoByCid.getGradeId());
            mo.addObject("esms",subjectInfoService.getSubByExamPaperId(examPaperId));
        }
        return mo;
    }
}
