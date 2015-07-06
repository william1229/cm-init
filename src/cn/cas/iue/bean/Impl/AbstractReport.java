package cn.cas.iue.bean.Impl;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import cn.cas.iue.bean.Project;

/**
 * AbstractReport entity provides the base persistence definition of the Report
 * entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractReport implements java.io.Serializable {

	// Fields

	private Integer reportId;
	private Project project;
	private Date reportDate;
	private String report;

	// Constructors

	/** default constructor */
	public AbstractReport() {
	}

	/** full constructor */
	public AbstractReport(Project project, Date reportDate, String report) {
		this.project = project;
		this.reportDate = reportDate;
		this.report = report;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "reportId", unique = true, nullable = false)
	public Integer getReportId() {
		return this.reportId;
	}

	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "projectId", nullable = false)
	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "reportDate", nullable = false, length = 10)
	public Date getReportDate() {
		return this.reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	@Column(name = "report", nullable = false, length = 100)
	public String getReport() {
		return this.report;
	}

	public void setReport(String report) {
		this.report = report;
	}

}