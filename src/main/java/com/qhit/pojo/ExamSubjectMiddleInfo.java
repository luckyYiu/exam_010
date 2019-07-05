package com.qhit.pojo;

public class ExamSubjectMiddleInfo {

	private Integer esmId;

	private Integer examPaperId;

	private Integer subjectId;

	private ExamPaperInfo examPaper;

	private SubjectInfo subject;

	public Integer getEsmId() {
		return esmId;
	}

	public void setEsmId(Integer esmId) {
		this.esmId = esmId;
	}

	public Integer getExamPaperId() {
		return examPaperId;
	}

	public void setExamPaperId(Integer examPaperId) {
		this.examPaperId = examPaperId;
	}

	public Integer getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

	public ExamPaperInfo getExamPaper() {
		return examPaper;
	}

	public void setExamPaper(ExamPaperInfo examPaper) {
		this.examPaper = examPaper;
	}

	public SubjectInfo getSubject() {
		return subject;
	}

	public void setSubject(SubjectInfo subject) {
		this.subject = subject;
	}
}