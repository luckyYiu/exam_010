package com.qhit.controller;

import com.qhit.pojo.CourseInfo;
import com.qhit.pojo.GradeInfo;
import com.qhit.service.CourseInfoService;
import com.qhit.service.GradeInfoService;
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
@RequestMapping("course")
public class CourseInfoHandler {

    @Autowired
    public CourseInfoService courseInfoService;
    @Autowired
    public GradeInfoService gradeInfoService;

    @RequestMapping("courseList")
    public ModelAndView courseList(Model model){
        List<CourseInfo> courseList = courseInfoService.getCourseList();
        model.addAttribute("courseList",courseList);
        return new ModelAndView("admin/courses");
    }

    @RequestMapping("getCourseById")
    public ModelAndView getCourseById(int courseId){
        CourseInfo course = courseInfoService.getCourseById(courseId);
        List<GradeInfo> grades = gradeInfoService.getGrade();
        ModelAndView mo = new ModelAndView();
        mo.addObject("course",course);
        mo.addObject("grades",grades);
        mo.setViewName("admin/courseedit1");
        return mo;
    }

    @RequestMapping("insertCourse")
    public @ResponseBody Map insertCourse(Model model, CourseInfo courseInfo){
        HashMap hashMap = new HashMap();
        int num = courseInfoService.insertCourse(courseInfo);
        if(num==0){
            hashMap.put("state",1);
            hashMap.put("msg","插入失败！");
        }else{
            hashMap.put("state",0);
            hashMap.put("msg","插入成功！");
        }
        return hashMap;
    }

    @RequestMapping("/deleteCourse")
    public @ResponseBody Map deleteCourse(Model model,int courseId){
        HashMap hashMap = new HashMap();
        int num = courseInfoService.deleteCourse(courseId);
        if(num==0){
            hashMap.put("state",1);
            hashMap.put("msg","删除失败！");
        }else{
            hashMap.put("state",0);
            hashMap.put("msg","删除成功！");
        }
        return hashMap;
    }

    @RequestMapping("/updateCourse")
    public @ResponseBody Map updateCourse(Model model,CourseInfo courseInfo){
        HashMap hashMap = new HashMap();
        int num = courseInfoService.updateCourse(courseInfo);
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
     * 预添加科目信息
     * @return
     */
    @RequestMapping("/preAddCourse")
    public ModelAndView preAddCourse() {
        ModelAndView model = new ModelAndView();
        model.setViewName("/admin/courseedit");
        /** 获取年级集合 */
        List<GradeInfo> grades = gradeInfoService.getGrade();
        model.addObject("grades", grades);
        return model;
    }

}
