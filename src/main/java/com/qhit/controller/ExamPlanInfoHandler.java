package com.qhit.controller;

import com.qhit.pojo.*;
import com.qhit.service.ClassInfoService;
import com.qhit.service.CourseInfoService;
import com.qhit.service.ExamPaperInfoService;
import com.qhit.service.ExamPlanInfoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("examPlan")
public class ExamPlanInfoHandler {

    @Autowired
    public ExamPlanInfoService examPlanInfoService;
    @Autowired
    private ClassInfoService classInfoService;
    @Autowired
    private CourseInfoService courseInfoService;
    @Autowired
    private ExamPaperInfoService examPaperInfoService;


    private Logger logger = Logger.getLogger(ExamPlanInfoHandler.class);

    //获取所有待考记录
    @RequestMapping("examPlanList")
    public ModelAndView examPlanList(Model model){
        List<ExamPlanInfo> examPlanList = examPlanInfoService.getExamPlanList();
        model.addAttribute("examPlanList",examPlanList);
        return new ModelAndView("admin/examPlans");
    }

    //预添加
    @RequestMapping("/preAddExamPlan")
    public ModelAndView preAddExamPlan() {
        ModelAndView model = new ModelAndView();
        model.setViewName("admin/examPlanedit");
        //获取所有班级信息
        List<ClassInfo> classes = classInfoService.getClassList();
        model.addObject("classes", classes);
        //获取所有科目信息
        List<CourseInfo> courses = courseInfoService.getCourseList();
        model.addObject("courses", courses);
        //获取所有的试卷信息 -- 纯净的
        List<ExamPaperInfo> examPapers = examPaperInfoService.getExamPapersClear();
        model.addObject("examPapers", examPapers);
        return model;
    }

    //添加待考信息
    @RequestMapping("insertExamPlan")
    public @ResponseBody Map insertExamPlan(ExamPlanInfo examPlanInfo) {
        HashMap hashMap = new HashMap();
        logger.info("添加待考记录："+examPlanInfo);
        int num = examPlanInfoService.insertExamPlan(examPlanInfo);
        if(num==0){
            hashMap.put("state",1);
            hashMap.put("msg","插入失败！");
        }else{
            hashMap.put("state",0);
            hashMap.put("msg","插入成功！");
        }
        return hashMap;
    }

    //预修改
    @RequestMapping("preUpdateExamPlan")
    public ModelAndView preUpdateExamPlan(int examPlanId) {
        ModelAndView model = new ModelAndView();
        model.setViewName("/admin/examPlanedit1");
        //获取所有班级信息
        List<ClassInfo> classes = classInfoService.getClassList();
        model.addObject("classes", classes);
        //获取所有科目信息
        List<CourseInfo> courses = courseInfoService.getCourseList();
        model.addObject("courses", courses);
        //获取所有的试卷信息 -- 纯净的(简单的)
        List<ExamPaperInfo> examPapers = examPaperInfoService.getExamPapersClear();
        model.addObject("examPapers", examPapers);
        //获取当前修改对象
        ExamPlanInfo examPlanWithUpdate = examPlanInfoService.getExamPlanById(examPlanId);
        logger.info("获取要修改的待考记录："+examPlanWithUpdate);
        model.addObject("examPlan", examPlanWithUpdate);

        return model;
    }

    //修改待考信息
    @RequestMapping("updateExamPlan")
    public @ResponseBody Map updateExamPlan(ExamPlanInfo examPlanInfo) {
        HashMap hashMap = new HashMap();
        logger.info("修改待考记录："+examPlanInfo);
        int num = examPlanInfoService.updateExamPlan(examPlanInfo);
        if(num==0){
            hashMap.put("state",1);
            hashMap.put("msg","修改失败！");
        }else {
            hashMap.put("state",0);
            hashMap.put("msg","修改成功！");
        }
        return hashMap;
    }

    //查询学生待考信息
    @RequestMapping("/willExams")
    public ModelAndView getStudentWillExam(
            @RequestParam("classId") Integer classId,
            @RequestParam("gradeId") Integer gradeId,
            @RequestParam(value="studentId", required=false) Integer studentId) {
        logger.info("查询学生 "+studentId+"(NULL-未指定)待考信息 班级："+classId+", 年级："+gradeId);
        ModelAndView model = new ModelAndView();
        model.setViewName("/reception/examCenter");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("classId", classId);
        map.put("gradeId", gradeId);

        List<ExamPlanInfo> examPlans = examPlanInfoService.getStudentWillExam(map);
        model.addObject("examPlans", examPlans);
        model.addObject("gradeId", gradeId);

        return model;
    }

    //教师移除考试安排记录
    @RequestMapping("deleteExamPlan")
    public @ResponseBody Map deleteExamPlan(int examPlanId) {
        HashMap hashMap = new HashMap();
        logger.info("教师 移除考试安排 "+examPlanId);
        int num = examPlanInfoService.deleteExamPlan(examPlanId);
        if(num==0){
            hashMap.put("state",1);
            hashMap.put("msg","删除失败！");
        }else{
            hashMap.put("state",0);
            hashMap.put("msg","删除成功！");
        }
        return hashMap;
    }

    /**
     * 验证试卷是否过期
     * @param beginTime 考试开始时间
     * @param examTime 考试时间
     * @return
     */
    private boolean validateExamPaerBeOverdue(String beginTime, int examTime) {
        boolean flag = false;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date beginTimeDate = sdf.parse(beginTime);
            Long beginTimeTime = beginTimeDate.getTime();

            /** 转换考试时间为毫秒单位 */
            int examTimeSecond = examTime * 60 * 1000;

            Date nowDate = new Date();
            Long nowDateTime = nowDate.getTime();

            /** 当前时间超过了 考试结束时间，即为过期记录 */
            if(nowDateTime > (beginTimeTime+examTimeSecond)) {
                flag = true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 定时刷新考试安排记录，将过期考试移除
     * 周一至周五 每隔15分钟刷新一次
     */
    @Scheduled(cron="0 */15 * * * MON-FRI")
    public void refreshExamPlan() {
        List<ExamPlanInfo> examPlans = examPlanInfoService.getExamPlanList();
        logger.info("刷新待考记录, SIZE "+examPlans.size());
        if (examPlans.size() > 0) {
            for (ExamPlanInfo examPlanInfo : examPlans) {

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String beginTime = sdf.format(examPlanInfo.getBeginTime());
                int examPaperTime = examPlanInfo.getExamPaper().getExamPaperTime();
                /** 验证是否可移除 */
                if (validateExamPaerBeOverdue(beginTime, examPaperTime)) {
                    logger.info("待考试卷 "+examPlanInfo.getExamPaper().getExamPaperId()+" 已经过期，即将移除");
                    //移除过期考试安排
                    int row = examPlanInfoService.deleteExamPlan(examPlanInfo.getExamPlanId());
                } else {
                    logger.info("待考试卷 "+examPlanInfo.getExamPaper().getExamPaperId()+" 暂未过期，无法移除");
                    continue;
                }
            }
        }
    }

    @RequestMapping("examCenter")
    public ModelAndView getExamPlan(int classId){
        ModelAndView mo = new ModelAndView();
        List<ExamPlanInfo> examPlans = examPlanInfoService.getExamPlanByClassId(classId);
        mo.addObject("examPlans",examPlans);
        mo.setViewName("reception/examCenter");
        return mo;
    }
}
