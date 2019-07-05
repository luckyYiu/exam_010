package com.qhit.pojo;

public class ExamPaperInfo {
    private Integer examPaperId;

    private String examPaperName;

    private Integer subjectNum;

    private Integer examPaperTime;

    private Integer examPaperScore;

    private Integer gradeId;

    private Integer division;

    private Integer examPaperEasy;

    private GradeInfo grade;

    public Integer getExamPaperId() {
        return examPaperId;
    }

    public void setExamPaperId(Integer examPaperId) {
        this.examPaperId = examPaperId;
    }

    public String getExamPaperName() {
        return examPaperName;
    }

    public void setExamPaperName(String examPaperName) {
        this.examPaperName = examPaperName == null ? null : examPaperName.trim();
    }

    public Integer getSubjectNum() {
        return subjectNum;
    }

    public void setSubjectNum(Integer subjectNum) {
        this.subjectNum = subjectNum;
    }

    public Integer getExamPaperTime() {
        return examPaperTime;
    }

    public void setExamPaperTime(Integer examPaperTime) {
        this.examPaperTime = examPaperTime;
    }

    public Integer getExamPaperScore() {
        return examPaperScore;
    }

    public void setExamPaperScore(Integer examPaperScore) {
        this.examPaperScore = examPaperScore;
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public Integer getDivision() {
        return division;
    }

    public void setDivision(Integer division) {
        this.division = division;
    }

    public Integer getExamPaperEasy() {
        return examPaperEasy;
    }

    public void setExamPaperEasy(Integer examPaperEasy) {
        this.examPaperEasy = examPaperEasy;
    }

    public GradeInfo getGrade() {
        return grade;
    }

    public void setGrade(GradeInfo grade) {
        this.grade = grade;
    }
}