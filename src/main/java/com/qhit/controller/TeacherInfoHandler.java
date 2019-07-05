package com.qhit.controller;

import cn.hutool.core.util.PageUtil;
import com.qhit.pojo.TeacherInfo;
import com.qhit.service.TeacherInfoService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("teacher")
public class TeacherInfoHandler {

    @Autowired
    public TeacherInfoService teacherInfoService;

    @RequestMapping("goLogin")
    public ModelAndView goLogin(){
        return new ModelAndView("admin/login");
    }

    @RequestMapping("login")
    public @ResponseBody Map login(String teacherAccount, String teacherPwd, HttpSession session){
        HashMap<Object,Object> map = new HashMap<>();
        Subject subject = SecurityUtils.getSubject();
        if(!subject.isAuthenticated()){
            UsernamePasswordToken token = new UsernamePasswordToken(teacherAccount,teacherPwd);
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
        TeacherInfo principal = (TeacherInfo)SecurityUtils.getSubject().getPrincipal();
        session.setAttribute("loginTeacher",principal);
        return map;
    }

    @RequestMapping("logout")
    public ModelAndView logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return new ModelAndView("admin/login");
    }

    /**
     * 教师查看自己的信息
     * @param teacherId
     * @return
     */
    @RequestMapping("/self")
    public ModelAndView loginTeacherSelf(Integer teacherId) {
        ModelAndView model = new ModelAndView();
        List<TeacherInfo> teachers = new ArrayList<TeacherInfo>();
        TeacherInfo teacher = teacherInfoService.getTeacherById(teacherId);
        teachers.add(teacher);
        model.addObject("teacher", teacher);
        model.setViewName("/admin/self");

        return model;
    }

    @RequestMapping("teacherList")
    public ModelAndView teacherList(Model model,Integer page){
        int currPage = 1;
        if(page != null){
            currPage = page;
        }
        int[] ints = PageUtil.transToStartEnd(currPage,12);
        Map m = teacherInfoService.getAllWithPage(ints);
        m.put("pageNow",currPage);

        List<Map> teacherList = teacherInfoService.getTeacherList();
        model.addAttribute("teacherList",m);
        return new ModelAndView("admin/teachers");

    }

    @RequestMapping("getTeacherById")
    public ModelAndView getTeacherById(int teacherId){
        TeacherInfo teacher = teacherInfoService.getTeacherById(teacherId);
        ModelAndView mo = new ModelAndView();
        mo.addObject("teacher",teacher);
        mo.setViewName("admin/teacheredit1");
        return mo;
    }

    @RequestMapping("insertTeacher")
    public @ResponseBody Map insertTeacher(Model model,TeacherInfo teacherInfo){
        HashMap hashMap = new HashMap();
        String uuid = UUID.randomUUID().toString();
        SimpleHash simpleHash = new SimpleHash("MD5",teacherInfo.getTeacherPwd(),uuid,1024);
        teacherInfo.setTeacherPwd(simpleHash.toString());
        teacherInfo.setSalt(uuid);
        int num = teacherInfoService.insertTeacher(teacherInfo);
        if(num==0){
            hashMap.put("state",1);
            hashMap.put("msg","插入失败！");
        }else{
            hashMap.put("state",0);
            hashMap.put("msg","插入成功！");
        }
        return hashMap;
    }

    @RequestMapping("updateTeacher")
    public @ResponseBody Map updateTeacher(Model model,TeacherInfo teacherInfo){
        HashMap hashMap = new HashMap();
        String uuid = UUID.randomUUID().toString();
        SimpleHash simpleHash = new SimpleHash("MD5",teacherInfo.getTeacherPwd(),uuid,1024);
        teacherInfo.setTeacherPwd(simpleHash.toString());
        teacherInfo.setSalt(uuid);
        int num = teacherInfoService.updateTeacher(teacherInfo);
        if(num==0){
            hashMap.put("state",1);
            hashMap.put("msg","修改失败！");
        }else {
            hashMap.put("state",0);
            hashMap.put("msg","修改成功！");
        }
        return hashMap;
    }

    @RequestMapping("deleteTeacher")
    public @ResponseBody Map deleteTeacher(Model model,int teacherId){
        HashMap hashMap = new HashMap();
        int num = teacherInfoService.deleteTeacher(teacherId);
        if(num==0){
            hashMap.put("state",1);
            hashMap.put("msg","删除失败！");
        }else{
            hashMap.put("state",0);
            hashMap.put("msg","删除成功！");
        }
        return hashMap;
    }
}
