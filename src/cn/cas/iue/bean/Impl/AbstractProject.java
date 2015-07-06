package cn.cas.iue.bean.Impl;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import cn.cas.iue.bean.Report;
import cn.cas.iue.bean.Sample;

/**
 * AbstractProject entity provides the base persistence definition of the
 * Project entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractProject implements java.io.Serializable {

	// Fields

	private Integer projectId;
	private String projectSN;
	private String projectName;
	private Date date;
	private String unit;
	private String telephone;
	private double money;
	private String contract;
	private Date reportDate;
	private String report;
	private Integer reportId;
	private Set<Sample> samples = new HashSet<Sample>(0);
	private Set<Report> reports = new HashSet<Report>(0);

	// Constructors

	/** default constructor */
	public AbstractProject() {
	}

	/** minimal constructor */
	public AbstractProject(String projectSN, String projectName, Date date,
			String unit, String telephone, double money, String contract) {
		this.projectSN = projectSN;
		this.projectName = projectName;
		this.date = date;
		this.unit = unit;
		this.telephone = telephone;
		this.money = money;
		this.contract = contract;
	}

	/** full constructor */
	public AbstractProject(String projectSN, String projectName, Date date,
			String unit, String telephone, double money, String contract,
			Set<Sample> samples, Set<Report> reports) {
		this.projectSN = projectSN;
		this.projectName = projectName;
		this.date = date;
		this.unit = unit;
		this.telephone = telephone;
		this.money = money;
		this.contract = contract;
		this.samples = samples;
		this.reports = reports;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "projectId", unique = true, nullable = false)
	public Integer getProjectId() {
		return this.projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	@Column(name = "projectSN", nullable = false, length = 50)
	public String getProjectSN() {
		return this.projectSN;
	}

	public void setProjectSN(String projectSN) {
		this.projectSN = projectSN;
	}

	@Column(name = "projectName", nullable = false, length = 50)
	public String getProjectName() {
		return this.projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date", nullable = false, length = 10)
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name = "unit", nullable = false, length = 100)
	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Column(name = "telephone", nullable = false, length = 20)
	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Column(name = "money", nullable = false, precision = 22, scale = 0)
	public double getMoney() {
		return this.money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	@Column(name = "contract", nullable = false, length = 100)
	public String getContract() {
		return this.contract;
	}

	public void setContract(String contract) {
		this.contract = contract;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "project")
	public Set<Sample> getSamples() {
		return this.samples;
	}

	public void setSamples(Set<Sample> samples) {
		this.samples = samples;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "project")
	public Set<Report> getReports() {
		return this.reports;
	}

	public void setReports(Set<Report> reports) {
		this.reports = reports;
	}
	@Transient
	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}
	@Transient
	public String getReport() {
		return report;
	}

	public void setReport(String report) {
		this.report = report;
	}
	@Transient
	public Integer getReportId() {
		return reportId;
	}

	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}

}