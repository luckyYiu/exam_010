package com.qhit.controller;

import com.qhit.pojo.ExamHistoryInfo;
import com.qhit.pojo.ExamHistoryPaper;
import com.qhit.pojo.SubjectInfo;
import com.qhit.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("examHistory")
public class ExamHistoryInfoHandler {

    @Autowired
    private ExamHistoryInfoService examHistoryInfoService;
    @Autowired
    private ExamSubjectMiddleService examSubjectMiddleService;
    @Autowired
    private ExamPaperInfoService examPaperInfoService;
    @Autowired
    private SubjectInfoService subjectInfoService;
    @Autowired
    private ExamChooseInfoService examChooseInfoService;

    @RequestMapping("ExamHistoryToTeacher")
    public ModelAndView ExamHistoryToTeacher(Model model){
        List<ExamHistoryInfo> examHistoryTeacher = examHistoryInfoService.getExamHistoryToTeacher();
        model.addAttribute("examHistoryTeacher",examHistoryTeacher);
        return new ModelAndView("admin/examHistorys");
    }

    @RequestMapping("ExamHistoryToStudent")
    public ModelAndView ExamHistoryToTeacher(Model model,int studentId){
        List<ExamHistoryPaper> examHistoryStudent = examHistoryInfoService.getExamHistoryToStudent(studentId);
        model.addAttribute("ehps",examHistoryStudent);
        return new ModelAndView("reception/examHistory");
    }

}
