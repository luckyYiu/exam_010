package com.qhit.pojo;


public class ExamPlanInfo {
    private Integer examPlanId;

    private Integer courseId;

    private Integer classId;

    private Integer examPaperId;

    private String beginTime;

    private CourseInfo course;
    private ClassInfo clazz;
    private ExamPaperInfo examPaper;

    public Integer getExamPlanId() {
        return examPlanId;
    }

    public void setExamPlanId(Integer examPlanId) {
        this.examPlanId = examPlanId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getExamPaperId() {
        return examPaperId;
    }

    public void setExamPaperId(Integer examPaperId) {
        this.examPaperId = examPaperId;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public CourseInfo getCourse() {
        return course;
    }

    public void setCourse(CourseInfo course) {
        this.course = course;
    }

    public ClassInfo getClazz() {
        return clazz;
    }

    public void setClazz(ClassInfo clazz) {
        this.clazz = clazz;
    }

    public ExamPaperInfo getExamPaper() {
        return examPaper;
    }

    public void setExamPaper(ExamPaperInfo examPaper) {
        this.examPaper = examPaper;
    }
}