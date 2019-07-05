package com.qhit.controller;

import cn.hutool.core.util.PageUtil;
import com.qhit.pojo.*;
import com.qhit.service.*;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("subject")
public class SubjectInfoHandler {

    @Autowired
    private SubjectInfoService subjectInfoService;
    @Autowired
    private CourseInfoService courseInfoService;
    @Autowired
    private GradeInfoService gradeInfoService;
    @Autowired
    private ExamPaperInfoService examPaperInfoService;
    @Autowired
    private ExamSubjectMiddleService examSubjectMiddleService;
    @Autowired
    private SubjectInfo subject;
    @Autowired
    private CourseInfo course;
    @Autowired
    private GradeInfo grade;
    @Autowired
    private ExamPaperInfo examPaper;

    private Logger logger = Logger.getLogger(SubjectInfoHandler.class);



    @RequestMapping("subjectList")
    public ModelAndView subjectList(Model model, Integer page, Map map) {
        int currPage = 1;
        if (page != null) {
            currPage = page;
        }
        int[] ints = PageUtil.transToStartEnd(currPage, 12);
        Map m = subjectInfoService.getAllWithPage(ints);
        m.put("pageNow", currPage);

        List<SubjectInfo> subjectList = subjectInfoService.getSubjectList(map);
        model.addAttribute("subjectList", m);
        return new ModelAndView("admin/subjects");
    }

    /**
     * 预添加试题信息
     *
     * @return
     */
    @RequestMapping("/preAddSubject")
    public ModelAndView preAddSubject() {
        ModelAndView model = new ModelAndView();
        model.setViewName("/admin/subject-test");
        /** 获取年级集合 */
        List<GradeInfo> grades = gradeInfoService.getGrade();
        model.addObject("grades", grades);
        /** 获取科目集合 */
        List<CourseInfo> courses = courseInfoService.getCourseList();
        model.addObject("courses", courses);
        return model;
    }
    @RequestMapping("/insertSubject")
    public @ResponseBody Map addSubject(Model model,SubjectInfo subjectInfo){
        HashMap map = new HashMap();
        int result=subjectInfoService.insertSubject(subjectInfo);
        if(result==0){
            map.put("state",1);
            map.put("msg","插入失败!");
        }else {
            map.put("state",0);
            map.put("msg","插入成功!");
        }
        return map;
    }

    @RequestMapping("/getSubjectById")
    public ModelAndView getSubjectById(Model model,Integer subjectId){
        SubjectInfo subjectInfo=subjectInfoService.getSubjectById(subjectId);
        List<GradeInfo> grades = gradeInfoService.getGrade();
        List<CourseInfo> courses=courseInfoService.getCourseList();
        model.addAttribute("subject",subjectInfo);
        model.addAttribute("courses",courses);
        model.addAttribute("grades", grades);
        return new ModelAndView("admin/subject-test1");
    }

    @RequestMapping("/updateSubject")
    public @ResponseBody Map updateSubject(Model model,SubjectInfo subjectInfo){
        HashMap map = new HashMap();
        int result=subjectInfoService.updateSubject(subjectInfo);
        if (result == 0) {
            map.put("state", 1);
            map.put("msg", "修改失败!");
        } else {
            map.put("state", 0);
            map.put("msg", "修改成功!");
        }
        return map;
    }

    @RequestMapping("/deleteSubject")
    public @ResponseBody
    Map deleteSubject(Model model, int subjectId){
        HashMap hashMap = new HashMap();
        int num = subjectInfoService.deleteSubject(subjectId);
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
     * 将数据传递到上传页面
     *
     * @param mo
     * @return
     */
    @RequestMapping("preUploadSubject")
    public ModelAndView preUploadSubject(ModelAndView mo) {
//        获取所有试卷
        mo.addObject("examPapers", examPaperInfoService.getExamPaper());
//        获取所有年级
        mo.addObject("grades", gradeInfoService.getGrade());
//        获取所有科目
        mo.addObject("courses", courseInfoService.getCourseList());
        mo.setViewName("admin/importSubject");
        return mo;
    }


    @RequestMapping("/upload")
    public Map upload(String fileName,
                      int importOption,
                      int examPaperId,
                      int examPaperEasy,
                      String examPaperName,
                      Integer examPaperTime,
                      int division,
                      int gradeId,
                      String realPath) {
        int filg=0;
        int i=0;
        Map hashMap = new HashMap();
        try {
            FileInputStream fileInputStream = new FileInputStream(realPath+fileName);
            XSSFWorkbook sheets = new XSSFWorkbook(fileInputStream);
            XSSFSheet sheetAt = sheets.getSheetAt(0);
            for (Row row:sheetAt
                    ) {
                if(row.getRowNum()==0){
                    continue;
                }
                String subjectName=(row.getCell(0)==null)?"":row.getCell(0).getStringCellValue();
                String optionA=(row.getCell(1)==null)?"":row.getCell(1).getStringCellValue();
                String optionB=(row.getCell(2)==null)?"":row.getCell(2).getStringCellValue();
                String optionC=(row.getCell(3)==null)?"":row.getCell(3).getStringCellValue();
                String optionD=(row.getCell(4)==null)?"":row.getCell(4).getStringCellValue();
                String rightResult=(row.getCell(5)==null)?"":row.getCell(5).getStringCellValue();
                double subjectScore= (row.getCell(6)==null)?0:row.getCell(6).getNumericCellValue();
                String subjectType=(row.getCell(7)==null)?"":row.getCell(7).getStringCellValue();
                String subjectEasy=(row.getCell(8)==null)?"":row.getCell(8).getStringCellValue();
                int subjectTypes=0;
                int subjectEasys=0;
                if(subjectType.equals("单选")){
                    subjectTypes=0;
                }
                if(subjectType.equals("多选")){
                    subjectTypes=1;
                }
                if(subjectType.equals("简答")){
                    subjectTypes=2;
                }
                if (subjectEasy.equals("简单")){
                    subjectEasys=0;
                }
                if (subjectEasy.equals("普通")){
                    subjectEasys=1;
                }
                if (subjectEasy.equals("困难")){
                    subjectEasys=2;
                }
                ArrayList<SubjectInfo> subjectInfos = new ArrayList<>();
                SubjectInfo subjectInfo = new SubjectInfo();
                subjectInfo.setSubjectName(subjectName);
                subjectInfo.setOptionA(optionA);
                subjectInfo.setOptionB(optionB);
                subjectInfo.setOptionC(optionC);
                subjectInfo.setOptionD(optionD);
                subjectInfo.setRightResult(rightResult);
                subjectInfo.setSubjectScore((int)subjectScore);
                subjectInfo.setSubjectType(subjectTypes);
                subjectInfo.setSubjectEasy(subjectEasys);
                subjectInfos.add(subjectInfo);
                if(importOption==0){
                    SubjectInfo subIdByName = subjectInfoService.selectSubIdByName(subjectName);
                    if(subIdByName!=null){
                        hashMap.put("error","试题已存在，请重新选择文件");
                        return hashMap;
                    }else{
                        filg=subjectInfoService.addAll(subjectInfos);
                    }
                    hashMap.put("filg",filg);
                }else if(importOption==1){
                    SubjectInfo subIdByName = subjectInfoService.selectSubIdByName(subjectName);
                    if(subIdByName!=null){
                        hashMap.put("error","试题已存在，请重新选择文件");
                        return hashMap;
                    }else{
                        subjectInfoService.addAll(subjectInfos);
                    }
                    SubjectInfo selectSubIdByName = subjectInfoService.selectSubIdByName(subjectName);
                    ExamSubjectMiddleInfo examSubjectMiddleInfo = new ExamSubjectMiddleInfo();
                    examSubjectMiddleInfo.setExamPaperId(examPaperId);
                    examSubjectMiddleInfo.setSubjectId(selectSubIdByName.getSubjectId());
                    List<ExamSubjectMiddleInfo> arrayList = new ArrayList();
                    arrayList.add(examSubjectMiddleInfo);
                    filg = examSubjectMiddleService.insertSubIdExamId(arrayList);
                    hashMap.put("subjectnum",1);
                    hashMap.put("exampaperid",examPaperId);
                    hashMap.put("score",selectSubIdByName.getSubjectScore());
                    examPaperInfoService.upadteSubjects(hashMap);
                    examPaperInfoService.updateScore(hashMap);
                    hashMap.put("filg",filg);
                }else if(importOption==2){
                    ExamPaperInfo examPaperInfo = new ExamPaperInfo();
                    examPaperInfo.setExamPaperEasy(examPaperEasy);
                    examPaperInfo.setExamPaperName(examPaperName);
                    examPaperInfo.setExamPaperTime(examPaperTime);
                    examPaperInfo.setDivision(division);
                    examPaperInfo.setGradeId(gradeId);
                    if(i==0) {
                        ExamPaperInfo examParerIdByName= examPaperInfoService.selectExamParerIdByName(examPaperName);
                        if(examParerIdByName==null){
                            i = examPaperInfoService.insertExamParerLite(examPaperInfo);
                        }else{
                            hashMap.put("error","试卷已存在，请重新输入");
                            return hashMap;
                        }
                    }
                    SubjectInfo selectSubIdByName = subjectInfoService.selectSubIdByName(subjectName);
                    if(selectSubIdByName!=null){
                        hashMap.put("error","试题已存在，请重新选择文件");
                        return hashMap;
                    }else{
                        subjectInfoService.addAll(subjectInfos);
                    }
                    SubjectInfo subIdByName = subjectInfoService.selectSubIdByName(subjectName);
                    ExamPaperInfo examParerIdByName = examPaperInfoService.selectExamParerIdByName(examPaperName);
                    ExamSubjectMiddleInfo examSubjectMiddleInfo = new ExamSubjectMiddleInfo();
                    examSubjectMiddleInfo.setExamPaperId(examParerIdByName.getExamPaperId());
                    examSubjectMiddleInfo.setSubjectId(subIdByName.getSubjectId());
                    List<ExamSubjectMiddleInfo> arrayList = new ArrayList();
                    arrayList.add(examSubjectMiddleInfo);
                    filg = examSubjectMiddleService.insertSubIdExamId(arrayList);
                    hashMap.put("subjectnum",1);
                    hashMap.put("exampaperid",examParerIdByName.getExamPaperId());
                    hashMap.put("score",subIdByName.getSubjectScore());
                    examPaperInfoService.upadteSubjects(hashMap);
                    examPaperInfoService.updateScore(hashMap);
                    hashMap.put("filg",filg);
                }


            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalStateException e){
            e.printStackTrace();
            hashMap.put("error","数据类型错误");
        }
        return hashMap;
    }

    @RequestMapping(value="/doUpload", method= RequestMethod.POST)
    public ModelAndView Upload(@RequestParam("file") MultipartFile file,
                               int importOption,
                               @RequestParam("examPaperId")int examPaperId,
                               int examPaperEasy,
                               String examPaperName,
                               Integer examPaperTime,
                               int division,
                               int gradeId,
                               HttpServletRequest request
    ) {
        String realPath = request.getServletContext().getRealPath("\\WEB-INF\\upload/");
        long currentTimeMillis = System.currentTimeMillis();
        ModelAndView modelAndView = new ModelAndView("success");
        long size = file.getSize();
        if (size>0) {
            if (size > 5242880) {
                modelAndView.addObject("error","文件超过指定大小（5M）");
                return modelAndView;
            } else {
                String originalFilename = file.getOriginalFilename();
                String[] split = originalFilename.split("\\.");
                String s = split[split.length - 1];
                if (s.equals("txt") || s.equals("xlsx")) {
                    File files = new File(realPath);
                    // 获得该文件夹内的所有文件
                    File[] array = (files.listFiles()==null)?null:files.listFiles();
                    if(array!=null) {
                        for (int i = 0; i < array.length; i++) {
                            if (array[i].isFile())//如果是文件
                            {
                                String obj = currentTimeMillis + file.getOriginalFilename();
                                if (obj.equals(array[i].getName())) {
                                    modelAndView.addObject("error", "文件已存在，请重新选择");
                                    return modelAndView;
                                }
                            }
                        }
                    }
                    if (!file.isEmpty()) {
                        try {
                            //这里将上传得到的文件保存至 WEB-INF/upload/ 目录
                            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(realPath,
                                    currentTimeMillis+file.getOriginalFilename()));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    modelAndView.addObject("error","不支持的上传文件类型，请选择支持的上传文件类型（.txt||.xlsx）");
                    return modelAndView;
                }
            }
        }else{
            modelAndView.addObject("error","未选择上传文件");
            return modelAndView;
        }
        Map upload =upload(currentTimeMillis+file.getOriginalFilename(), importOption, examPaperId,
                examPaperEasy, examPaperName, examPaperTime,division,gradeId,realPath);
        if(upload.get("filg")!=null){
            modelAndView.addObject("error","文件上传成功");
        }else{
            modelAndView.addObject("error",upload.get("error"));
        }
        return modelAndView;
    }
}