package com.qhit.controller;

import com.qhit.pojo.ClassInfo;
import com.qhit.pojo.GradeInfo;
import com.qhit.pojo.TeacherInfo;
import com.qhit.service.ClassInfoService;
import com.qhit.service.GradeInfoService;
import com.qhit.service.TeacherInfoService;
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
@RequestMapping("class")
public class ClassInfoHandler {

    @Autowired
    public ClassInfoService classInfoService;
    @Autowired
    public GradeInfoService gradeInfoService;
    @Autowired
    public TeacherInfoService teacherInfoService;

    @RequestMapping("classList")
    public ModelAndView classList(Model model){
        List<ClassInfo> classList = classInfoService.getClassList();
        model.addAttribute("classList",classList);
        return new ModelAndView("admin/classes");
    }

    @RequestMapping("clazz")
    public ModelAndView getClassById(Model model,int classId){
        ClassInfo classList = classInfoService.getClassById(classId);
        model.addAttribute("classList",classList);
        return new ModelAndView("admin/classes");
    }

    @RequestMapping("getClassInfoByGradeId")
    public @ResponseBody Map getClassInfoByGradeId(String gradeId){
        HashMap hashMap = new HashMap();
        hashMap.put("classes",classInfoService.getClassInfoByGradeId(gradeId));
        return hashMap;
    }

    @RequestMapping("insertClass")
    public @ResponseBody Map insertClass(Model model,ClassInfo classInfo){
        HashMap hashMap = new HashMap();
        int num = classInfoService.insertClass(classInfo);
        if(num==0){
            hashMap.put("state",1);
            hashMap.put("msg","插入失败！");
        }else{
            hashMap.put("state",0);
            hashMap.put("msg","插入成功！");
        }
        return hashMap;
    }

    @RequestMapping("deleteClass")
    public @ResponseBody Map deleteClass(Model model,int classId){
        HashMap hashMap = new HashMap();
        int num = classInfoService.deleteClass(classId);
        if(num==0){
            hashMap.put("state",1);
            hashMap.put("msg","删除失败！");
        }else{
            hashMap.put("state",0);
            hashMap.put("msg","删除成功！");
        }
        return hashMap;
    }

    @RequestMapping("getClassById")
    public ModelAndView getClassById(int classId){
        ModelAndView mo = new ModelAndView();
        ClassInfo clazz = classInfoService.getClassById(classId);
        mo.addObject("clazz",clazz);
        mo.setViewName("admin/classedit1");
        /** 获取年级集合 */
        List<GradeInfo> grades = gradeInfoService.getGrade();
        mo.addObject("grades", grades);
        /** 获取教师集合 */
        List<Map> teachers = teacherInfoService.getTeacherList();
        mo.addObject("teachers",teachers);
        return mo;
    }

    @RequestMapping("/updateClass")
    public @ResponseBody Map updateClass(Model model,ClassInfo classInfo){
        HashMap hashMap = new HashMap();
        int num = classInfoService.updateClass(classInfo);
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
     * 预添加班级信息
     * @return
     */
    @RequestMapping("/preAddClass")
    public ModelAndView preAddClass() {
        ModelAndView model = new ModelAndView();
        model.setViewName("/admin/classedit");
        /** 获取年级集合 */
        List<GradeInfo> grades = gradeInfoService.getGrade();
        model.addObject("grades", grades);
        /** 获取教师集合 */
        List<Map> teachers = teacherInfoService.getTeacherList();
        model.addObject("teachers",teachers);
        return model;
    }

    //学生人数图表信息
    @RequestMapping("/preStudentCount")
    public ModelAndView preStudentCount() {
        ModelAndView model = new ModelAndView();
        //获取年级信息
        List<GradeInfo> grades = gradeInfoService.getGrade();
        model.setViewName("admin/charts/studentCount");
        model.addObject("grades", grades);
        return model;
    }
}
