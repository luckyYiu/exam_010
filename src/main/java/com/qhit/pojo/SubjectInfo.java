package com.qhit.pojo;

public class SubjectInfo {
    private Integer subjectId;

    private String subjectName;

    private String optionA;

    private String optionB;

    private String optionC;

    private String optionD;

    private String rightResult;

    private Integer subjectScore;

    private Integer subjectType;

    private Integer courseId;

    private Integer gradeId;

    private Integer subjectEasy;

    private Integer division;

    private CourseInfo course;
    private GradeInfo grade;

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName == null ? null : subjectName.trim();
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA == null ? null : optionA.trim();
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB == null ? null : optionB.trim();
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC == null ? null : optionC.trim();
    }

    public String getOptionD() {
        return optionD;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD == null ? null : optionD.trim();
    }

    public String getRightResult() {
        return rightResult;
    }

    public void setRightResult(String rightResult) {
        this.rightResult = rightResult == null ? null : rightResult.trim();
    }

    public Integer getSubjectScore() {
        return subjectScore;
    }

    public void setSubjectScore(Integer subjectScore) {
        this.subjectScore = subjectScore;
    }

    public Integer getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(Integer subjectType) {
        this.subjectType = subjectType;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public Integer getSubjectEasy() {
        return subjectEasy;
    }

    public void setSubjectEasy(Integer subjectEasy) {
        this.subjectEasy = subjectEasy;
    }

    public Integer getDivision() {
        return division;
    }

    public void setDivision(Integer division) {
        this.division = division;
    }

    public CourseInfo getCourse() {
        return course;
    }

    public void setCourse(CourseInfo course) {
        this.course = course;
    }

    public GradeInfo getGrade() {
        return grade;
    }

    public void setGrade(GradeInfo grade) {
        this.grade = grade;
    }
}