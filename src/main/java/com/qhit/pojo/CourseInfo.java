package com.qhit.pojo;

public class CourseInfo {
    private Integer courseId;

    private String courseName;

    private Integer division;

    private Integer gradeId;

    private GradeInfo grade;

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName == null ? null : courseName.trim();
    }

    public Integer getDivision() {
        return division;
    }

    public void setDivision(Integer division) {
        this.division = division;
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public GradeInfo getGrade() {
        return grade;
    }

    public void setGrade(GradeInfo grade) {
        this.grade = grade;
    }
}