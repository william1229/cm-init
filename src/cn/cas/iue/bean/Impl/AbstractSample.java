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
import javax.persistence.Transient;

import cn.cas.iue.bean.Project;

/**
 * AbstractSample entity provides the base persistence definition of the Sample
 * entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractSample implements java.io.Serializable {

	// Fields

	private Integer sampleId;
	private Project project;
	private String sampleSN;
	private String state;
	private Integer quantity;
	private String unit;
	private Date date;
	private String name;
	private String telephone;
	private String projectSN;

	// Constructors

	/** default constructor */
	public AbstractSample() {
	}

	/** full constructor */
	public AbstractSample(Project project, String sampleSN, String state,
			Integer quantity, String unit, Date date, String name,
			String telephone) {
		this.project = project;
		this.sampleSN = sampleSN;
		this.state = state;
		this.quantity = quantity;
		this.unit = unit;
		this.date = date;
		this.name = name;
		this.telephone = telephone;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "sampleId", unique = true, nullable = false)
	public Integer getSampleId() {
		return this.sampleId;
	}

	public void setSampleId(Integer sampleId) {
		this.sampleId = sampleId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "projectId", nullable = false)
	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@Column(name = "sampleSN", nullable = false, length = 50)
	public String getSampleSN() {
		return this.sampleSN;
	}

	public void setSampleSN(String sampleSN) {
		this.sampleSN = sampleSN;
	}

	@Column(name = "state", nullable = false, length = 20)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "quantity", nullable = false)
	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Column(name = "unit", nullable = false, length = 100)
	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date", nullable = false, length = 10)
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name = "name", nullable = false, length = 10)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "telephone", nullable = false, length = 20)
	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	@Transient
	public String getProjectSN() {
		return projectSN;
	}

	public void setProjectSN(String projectSN) {
		this.projectSN = projectSN;
	}

}