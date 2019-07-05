package com.qhit.controller;

import com.google.gson.Gson;
import com.qhit.charts.StudentCount;
import com.qhit.pojo.*;
import com.qhit.service.*;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import org.apache.shiro.subject.Subject;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("student")
public class StudentInfoHandler {

    @Autowired
    private StudentInfoService studentInfoService;
    @Autowired
    private ClassInfoService classInfoService;
    @Autowired
    private ExamSubjectMiddleService examSubjectMiddleService;
    @Autowired
    private ExamHistoryInfoService examHistoryInfoService;
    @Autowired
    private ExamChooseInfoService examChooseInfoService;
    @Autowired
    private ExamSubjectMiddleInfo esm;
    @Autowired
    private ClassInfo classInfo;
    @Autowired
    private ExamPaperInfo examPaper;
    @Autowired
    private GradeInfo grade;
    @Autowired
    private StudentInfo student;

    @Autowired
    private ExamPaperInfoService examPaperInfoService;

    private Logger logger = Logger.getLogger(StudentInfoHandler.class);

    @RequestMapping("login")
    public @ResponseBody Map login(StudentInfo studentInfo, HttpSession session){
        HashMap<Object,Object> map = new HashMap<>();
        Subject subject = SecurityUtils.getSubject();
        if(!subject.isAuthenticated()){
            UsernamePasswordToken token = new UsernamePasswordToken(studentInfo.getStudentAccount(),studentInfo.getStudentPwd());
            try{
                subject.login(token);
                map.put("state","0");
                map.put("msg","登陆成功！");
            }catch (UnknownAccountException unknownAccountException){
                map.put("state","1");
                map.put("msg","用户名不存在！");
            }catch (CredentialsException credentialsException){
                map.put("state","1");
                map.put("msg","密码不正确！");
            }catch (AuthenticationException authenticationException){
                map.put("state","1");
                map.put("msg","登录失败！");
            }
        }
        StudentInfo principal = (StudentInfo)SecurityUtils.getSubject().getPrincipal();
        session.setAttribute("loginStudent",principal);
        return map;
    }

    @RequestMapping("logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:../index.jsp";
    }

    @RequestMapping("studentList")
    public ModelAndView studentList(Model model){
        List<StudentInfo> studentList = studentInfoService.getStudentList();
        model.addAttribute("studentList",studentList);
        return new ModelAndView("admin/students");
    }

    @RequestMapping("getStudentById")
    public ModelAndView getStudentById(int studentId){
        ModelAndView mo = new ModelAndView();
        StudentInfo student = studentInfoService.getStudentById(studentId);
        mo.addObject("student",student);
        /** 获取班级集合 */
        List<ClassInfo> classes = classInfoService.getClassList();
        mo.addObject("classes", classes);
        mo.setViewName("admin/studentedit1");
        return mo;
    }

    @RequestMapping("insertStudent")
    public @ResponseBody Map insertStudent(Model model, StudentInfo studentInfo){
        HashMap hashMap = new HashMap();
        String uuid = UUID.randomUUID().toString();
        SimpleHash simpleHash = new SimpleHash("MD5",studentInfo.getStudentPwd(),uuid,1024);
        studentInfo.setStudentPwd(simpleHash.toString());
        studentInfo.setSalt(uuid);
        int num = studentInfoService.insertStudent(studentInfo);
        if(num==0){
            hashMap.put("state",1);
            hashMap.put("msg","注册失败！");
        }else{
            hashMap.put("state",0);
            hashMap.put("msg","注册成功！");
        }
        return hashMap;
    }

    @RequestMapping("deleteStudent")
    public @ResponseBody Map deleteStudent(Model model,int studentId){
        HashMap hashMap = new HashMap();
        int num = studentInfoService.deleteStudent(studentId);
        if(num==0){
            hashMap.put("state",1);
            hashMap.put("msg","删除失败！");
        }else{
            hashMap.put("state",0);
            hashMap.put("msg","删除成功！");
        }
        return hashMap;
    }

    @RequestMapping("updateStudent")
    public @ResponseBody Map updateStudent(Model model,StudentInfo studentInfo){
        HashMap hashMap = new HashMap();
        String uuid = UUID.randomUUID().toString();
        SimpleHash simpleHash = new SimpleHash("MD5",studentInfo.getStudentPwd(),uuid,1024);
        studentInfo.setStudentPwd(simpleHash.toString());
        studentInfo.setSalt(uuid);
        int num = studentInfoService.updateStudent(studentInfo);
        if(num==0){
            hashMap.put("state",1);
            hashMap.put("msg","修改失败！");
        }else {
            hashMap.put("state",0);
            hashMap.put("msg","修改成功！");
        }
        return hashMap;
    }

    @RequestMapping("getStudentByAccountAndPwd")
    public ModelAndView getStudentByAccountAndPwd(String studentAccount) {
        StudentInfo student = studentInfoService.getStudentByAccountAndPwd(studentAccount);
        ModelAndView mo = new ModelAndView();
        mo.addObject("student", student);
        mo.setViewName("admin/");
        return mo;
    }

    @RequestMapping("isResetPwdWithStudent")
    public @ResponseBody Map isResetPwdWithStudent(Model model,StudentInfo studentInfo){
        HashMap hashMap = new HashMap();
        int num = studentInfoService.isResetPwdWithStudent(studentInfo);
        if(num==0){
            hashMap.put("state",1);
            hashMap.put("msg","重置失败！");
        }else {
            hashMap.put("state",0);
            hashMap.put("msg","重置成功！");
        }
        return hashMap;
    }

    @RequestMapping("getStudentsByClassId")
    public ModelAndView getStudentsByClassId(int classId){
        List<StudentInfo> students = studentInfoService.getStudentsByClassId(classId);
        ModelAndView mo = new ModelAndView();
        mo.addObject("students",students);
        mo.setViewName("admin/");
        return mo;
    }


    /**
     * 预添加学生信息
     * @return
     */
    @RequestMapping("/preAddStudent")
    public ModelAndView preAddStudent() {
        ModelAndView model = new ModelAndView();
        model.setViewName("/reception/register");
        /** 获取班级集合 */
        List<ClassInfo> classes = classInfoService.getClassList();
        model.addObject("classes", classes);
        return model;
    }
    @RequestMapping("/selectCountAll")
    public void selectCountAll(HttpServletResponse response,int gradeId) throws IOException {
        Map<String,Object> map = studentInfoService.selectCountAll(gradeId);
        String barJson = StudentCount.createBarJson(map);
        response.setCharacterEncoding("utf-8");
        response.getWriter().print(barJson);
    }

    @RequestMapping("studentExamSubmit")
    public String examSubmit(
            @RequestParam("studentId") Integer studentId,
            @RequestParam("examPaperId") Integer examPaperId,
            @RequestParam("classId") Integer classId,
            @RequestParam("gradeId") Integer gradeId) {
        logger.info("学生 "+studentId+" 提交了试卷 "+examPaperId);

        //获取当前学生当前试卷所选择的全部答案
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("studentId", studentId);
        map.put("examPaperId", examPaperId);
        List<ExamChooseInfo> chooses = examChooseInfoService.getChooseInfoWithSumScore(map);
        logger.info("学生 "+studentId+" 共选择了 "+chooses.size()+" 道题");

        //总分记录
        int sumScore = 0;
        for (ExamChooseInfo choose : chooses) {
            SubjectInfo subject = choose.getSubject();
            String chooseResult = choose.getChooseResult();
            String rightResult = subject.getRightResult();

            if (chooseResult.equals(rightResult)) {	//答案正确
                sumScore += subject.getSubjectScore();
                logger.info("学生 "+studentId+" 第 "+subject.getSubjectId()+" 选择正确答案 "+chooseResult+" 当前总分 "+sumScore);
            } else {
                logger.info("学生 "+studentId+" 第 "+subject.getSubjectId()+" 答案选择错误 "+chooseResult+" 正确答案为 "+rightResult+" 当前总分 "+sumScore);
            }
        }
        //首先判断当前记录是否已经添加过
        //防止当前学生点击提交后，系统倒计时再次进行提交
        int count = examHistoryInfoService.getHistoryInfoWithIds(map);

        if (count == 0) {
            //添加到历史记录
            map.put("examScore", sumScore);
            int row = examHistoryInfoService.isAddExamHistory(map);
            logger.info("学生 "+studentId+" 提交的试卷 "+examPaperId+" 已成功处理，并添加到历史记录中");
        }

        return "redirect:../examPlan/willExams.action?gradeId="+gradeId+"&classId="+classId+"&studentId="+studentId;
    }


    //学生回顾试卷  --  后台教师查看也调用此方法
    @RequestMapping("/review")
    public ModelAndView reViewExam(
            @RequestParam("studentId") Integer studentId,
            @RequestParam("examPaperId") Integer examPaperId,
            @RequestParam("score") Integer score,
            @RequestParam("examPaperName") String examPaperName,
            @RequestParam(value="studentName", required=false) String studentName) throws UnsupportedEncodingException {
        ModelAndView model = new ModelAndView();
        if (studentId == null) {
            model.addObject("error", "请先登录后再操作");
            model.setViewName("error");
            return model;
        } else {
            //获取当前试卷的试题集合  -- 前台判断需要
            examPaper.setExamPaperId(examPaperId);
            esm.setExamPaper(examPaper);
            List<ExamSubjectMiddleInfo> esms = examSubjectMiddleService.getSubExamPar(examPaperId);

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("studentId", studentId);
            map.put("examPaperId", examPaperId);

            //获取当前回顾试卷 试题、选择答案 信息
            List<ExamChooseInfo> reviews = examChooseInfoService.getChooseInfoWithExamSubject(map);
            logger.info("学生 " + studentId + " 回顾试卷 " + examPaperId + " 试题数量 " + reviews.size());
            //设置试卷名称、试卷总分
            model.addObject("examPaperName", examPaperName);
            model.addObject("score", score);

            model.setViewName("reception/review");
            model.addObject("views", reviews);

            model.addObject("esms", esms);
            if (studentName != null) model.addObject("studentName", studentName);

            /*model.addObject("ExamedPaper", examPaperInfoService.getExamPaper());*/
            model.addObject("examPaper", examPaperInfoService.getExamPaperById(examPaperId));
            return model;
        }
    }
    /**
     * 学生查看自己信息
     * @param studentId
     * @return
     */
    @RequestMapping("/self")
    public ModelAndView selfInfo(Integer studentId) {
        StudentInfo stu = studentInfoService.getStudentById(studentId);

        ModelAndView model = new ModelAndView();
        model.setViewName("/reception/self");
        model.addObject("self", stu);
        return model;
    }
}
