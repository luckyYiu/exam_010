package com.qhit.controller;

import cn.hutool.core.util.PageUtil;
import com.google.gson.Gson;
import com.qhit.pojo.*;

import com.qhit.service.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("examSubjectMiddle")
public class ExamSubjectMiddleInfoHandler {
	@Autowired
	private ExamSubjectMiddleService examSubjectMiddleService ;
	@Autowired
	private ExamPaperInfoService examPaperInfoService;
	@Autowired
	private SubjectInfoService subjectInfoService;
	@Autowired
	private CourseInfoService courseInfoService;
	@Autowired
	private GradeInfoService gradeInfoService;
	@Autowired
	private ExamSubjectMiddleInfo esm;
	@Autowired
	private ExamPaperInfo examPaper;
	@Autowired
	private SubjectInfo subject;
	@Autowired
	private CourseInfo course;
	@Autowired
	private GradeInfo grade;
	@Autowired
	private Gson gson;

	private Logger logger = Logger.getLogger(ExamSubjectMiddleInfoHandler.class);

	//查看试题
	@RequestMapping("/getSubExamPar")
	public void getSubExamPar(HttpServletResponse response,int examPaperId) throws IOException {
		List<ExamSubjectMiddleInfo> subExamPar = examSubjectMiddleService.getSubExamPar(examPaperId);
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(gson.toJson(subExamPar));
	}

	//从试卷中移除试题
	@RequestMapping("removeSubjectFromPaper")
	public void removeSubjectWithExamPaper(
			@RequestParam("subjectId") Integer subjectId,
			@RequestParam("examPaperId") Integer examPaperId,
			@RequestParam("score") Integer score,
			HttpServletResponse response) throws IOException {
		logger.info("从试卷 "+examPaperId+" 中移除试题 "+subjectId+"，试题分值："+score);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("subjectNum", (-1));
		map.put("score", (-score));
		map.put("examPaperId", examPaperId);
		map.put("subjectId", subjectId);
		//修改试卷总分
		examPaperInfoService.isUpdateExamPaperScore(map);
		//修改试卷题目数量
		examPaperInfoService.isUpdateExamPaperSubjects(map);
		//从试卷中移除试题
		examSubjectMiddleService.removeSubjectWithExamPaper(map);

		response.getWriter().print("t");
	}

	//添加试题-查询所有试题
	@RequestMapping("/selectSubjectAll")
	public ModelAndView selectSubjectAll(Integer page, Integer examPaperId) {
		ModelAndView modelAndView = new ModelAndView();
		int currPage = 1;
		if (page != null) {
			currPage = page;
		}
		int[] ints = PageUtil.transToStartEnd(currPage, 12);
		Map map = subjectInfoService.getAllWithPage(ints);
		map.put("pageNow",currPage);
		modelAndView.addObject("handAdd", 1);
		modelAndView.addObject("choosed",0);
		modelAndView.addObject("examPaperId", examPaperId);
		modelAndView.setViewName("admin/subjects");
		modelAndView.addObject("subjectList", map);
		return modelAndView;
	}

	@RequestMapping("/handAdd")
	public void handAdd(Integer examPaperId,HttpSession session,
						HttpServletResponse response) throws Exception {
		int subjectNum=0;
		int scoreNum=0;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("examPaperId",examPaperId);
		ArrayList<ExamSubjectMiddleInfo> subjectIds = new ArrayList<ExamSubjectMiddleInfo>();
		//试题信息
		List<String> ids = (List<String>) session.getAttribute("ids");
		if (ids != null) {
			for (int i=0;i<ids.size();i++) {
				ExamSubjectMiddleInfo esm = new ExamSubjectMiddleInfo();
				//分割试题编号和分数
				String[] idAndScore = ids.get(i).split(",");
				esm.setExamPaperId(examPaperId);
				esm.setSubjectId(Integer.parseInt(idAndScore[0]));
				//累加试题分数
				scoreNum += Integer.parseInt(idAndScore[1]);
				//累加试题数量
				subjectNum += 1;
				subjectIds.add(esm);
			}
			map.put("subjectIds",subjectIds);
		} else {
			response.getWriter().print("需要添加的试题为空，操作失败！");
			return;
		}
		//总分和题目数量信息
		HashMap<String, Object> map1 = new HashMap<>();
		map1.put("subjectNum", subjectNum);
		map1.put("score", scoreNum);
		map1.put("examPaperId",examPaperId);

		//修改试卷总分
		examPaperInfoService.isUpdateExamPaperScore(map1);
		//修改试卷试题数量
		examPaperInfoService.isUpdateExamPaperSubjects(map1);
		//将试题添加到试卷
		examSubjectMiddleService.isAddESM(map);
		session.removeAttribute("ids");
		response.setCharacterEncoding("utf-8");
		response.getWriter().print("试题已成功添加到试卷中！");

	}

	@RequestMapping("/getChooseSubId")
	public void getChooseSubId(Integer subjectId,Integer examPaperId,Integer score,
							   HttpSession session, HttpServletResponse response) throws IOException{
		List<String> ids = null;
		//判断是否在试卷中,如果存在不添加,反之添加
		Integer esmId=examSubjectMiddleService.getEsmByExamIdWithSubjectId(examPaperId,subjectId);
		if (esmId == null) {
			ids = (List<String>) session.getAttribute("ids");
			/** Session 记录非空验证 */
			if (subjectId != null && score != null) {
				//第一次添加
				if (ids == null) {
					ids = new ArrayList<String>();
					ids.add((subjectId+","+score));
				}else {
					if (ids.contains((subjectId+","+score))) {
						ids.remove((subjectId+","+score));
					}else {
						ids.add((subjectId + "," + score));
					}
				}
			} else {
				response.getWriter().print("添加失败，试题编号或试题分数异常！");
				return;
			}
		} else {
			//同时返回添加失败的题号，用于前台方便移除选中
			response.getWriter().print("f-exists-"+subjectId);
			return;
		}
		session.setAttribute("ids", ids);
		response.setCharacterEncoding("utf-8");

	}

	@RequestMapping("/autoAddSubject")
	public @ResponseBody Map autoAddSubject(SubjectInfo subjectInfo,Integer examPaperId,Integer num){
		HashMap map = new HashMap();
		ArrayList list = new ArrayList();
		List getBy= subjectInfoService.getSubjects(subjectInfo);
		List integers = examSubjectMiddleService.selectSubjectIdByExamParerId(examPaperId);
		getBy.removeAll(integers);
		Iterator iterator = getBy.iterator();
		while (iterator.hasNext()){
			list.add(iterator.next());
		}
		Random r = new Random();
		List arrayList = new ArrayList();
		int score = 0;
		int count = 0;
		for (int n = 0; n < num; n++) {
			ExamSubjectMiddleInfo esm = new ExamSubjectMiddleInfo();
			esm.setExamPaperId(examPaperId);
			Integer subjectId =(Integer) list.get(r.nextInt(getBy.size()));
			esm.setSubjectId(subjectId);
			score += subjectInfoService.getSubjectById(subjectId).getSubjectScore();
			count += 1;
			arrayList.add(esm);
		}
		int n=examSubjectMiddleService.isAddESM(map);

		int scoreSum = examSubjectMiddleService.getSumScore(examPaperId);

//        count =examSubjectMiddleService.getSumSubject(exampaperid);
		HashMap map1 = new HashMap();
		map1.put("score", score);
		map1.put("subjectNum", count);
		map1.put("examPaperId",examPaperId);
		//修改试卷总分
		int num1 = examPaperInfoService.isUpdateExamPaperScore(map1);
		//修改试卷试题数量
		int num2 = examPaperInfoService.isUpdateExamPaperSubjects(map1);
		if (n <= 0 ){
			map.put("state","0");
			map.put("msg","添加失败！！！");
		}else{
			map.put("state","1");
			map.put("msg","添加成功！！！");
		}
		return map;
	}

}