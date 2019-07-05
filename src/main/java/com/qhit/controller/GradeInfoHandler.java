package com.qhit.controller;

import com.qhit.pojo.GradeInfo;
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
@RequestMapping("grade")
public class GradeInfoHandler {

    @Autowired
    public GradeInfoService gradeInfoService;

    @RequestMapping("gradeList")
    public ModelAndView gradeList(Model model){
        List<GradeInfo> grades = gradeInfoService.getGrade();
        model.addAttribute("grades",grades);
        return new ModelAndView("admin/grades");
    }

    @RequestMapping("getGradeById")
    public ModelAndView getGradeById(int gradeId){
        GradeInfo grade = gradeInfoService.getGradeById(gradeId);
        ModelAndView mo = new ModelAndView();
        mo.addObject("grade",grade);
        mo.setViewName("admin/gradeedit1");
        return mo;
    }

    @RequestMapping("insertGrade")
    public @ResponseBody Map insertGrade(Model model,String gradeName){
        HashMap hashMap = new HashMap();
        GradeInfo gradeInfo = new GradeInfo();
        gradeInfo.setGradeName(gradeName);
        int num = gradeInfoService.insertGrade(gradeInfo);
        if(num==0){
            hashMap.put("state",1);
            hashMap.put("msg","插入失败！");
        }else{
            hashMap.put("state",0);
            hashMap.put("msg","插入成功！");
        }
        return hashMap;
    }

    @RequestMapping("/deleteGrade")
    public @ResponseBody Map deleteGrade(Model model,int gradeId){
        HashMap hashMap = new HashMap();
        int num = gradeInfoService.deleteGrade(gradeId);
        if(num==0){
            hashMap.put("state",1);
            hashMap.put("msg","删除失败！");
        }else{
            hashMap.put("state",0);
            hashMap.put("msg","删除成功！");
        }
        return hashMap;
    }

    @RequestMapping("/updateGrade")
    public @ResponseBody Map updateGrade(Model model,GradeInfo gradeInfo){
        HashMap hashMap = new HashMap();
        int num = gradeInfoService.updateGrade(gradeInfo);
        if(num==0){
            hashMap.put("state",1);
            hashMap.put("msg","修改失败！");
        }else {
            hashMap.put("state",0);
            hashMap.put("msg","修改成功！");
        }
        return hashMap;
    }
}
