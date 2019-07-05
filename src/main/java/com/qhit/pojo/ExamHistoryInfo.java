package com.qhit.pojo;

public class ExamHistoryInfo {
    private Integer historyId;

    private Integer studentId;

    private Integer examPaperId;

    private Integer examScore;

    private ExamPaperInfo examPaperInfo;
    private StudentInfo studentInfo;
    private ExamPlanInfo examPlanInfo;

    public Integer getHistoryId() {
        return historyId;
    }

    public void setHistoryId(Integer historyId) {
        this.historyId = historyId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getExamPaperId() {
        return examPaperId;
    }

    public void setExamPaperId(Integer examPaperId) {
        this.examPaperId = examPaperId;
    }

    public Integer getExamScore() {
        return examScore;
    }

    public void setExamScore(Integer examScore) {
        this.examScore = examScore;
    }

    public ExamPaperInfo getExamPaperInfo() {
        return examPaperInfo;
    }

    public void setExamPaperInfo(ExamPaperInfo examPaperInfo) {
        this.examPaperInfo = examPaperInfo;
    }

    public StudentInfo getStudentInfo() {
        return studentInfo;
    }

    public void setStudentInfo(StudentInfo studentInfo) {
        this.studentInfo = studentInfo;
    }

    public ExamPlanInfo getExamPlanInfo() {
        return examPlanInfo;
    }

    public void setExamPlanInfo(ExamPlanInfo examPlanInfo) {
        this.examPlanInfo = examPlanInfo;
    }
}