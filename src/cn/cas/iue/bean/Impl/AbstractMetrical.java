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

import cn.cas.iue.bean.Instru;

/**
 * AbstractMetrical entity provides the base persistence definition of the
 * Metrical entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractMetrical implements java.io.Serializable {

	// Fields

	private Integer metricalId;
	private Instru instru;
	private Date date;
	private Date effectiveDate;
	private String unit;
	private String serialNumber;
	private String remark;
	private String instruName;
	private String instruModel;

	// Constructors

	/** default constructor */
	public AbstractMetrical() {
	}

	/** minimal constructor */
	public AbstractMetrical(Date date, Date effectiveDate, String unit,
			String serialNumber, String remark) {
		this.date = date;
		this.effectiveDate = effectiveDate;
		this.unit = unit;
		this.serialNumber = serialNumber;
		this.remark = remark;
	}

	/** full constructor */
	public AbstractMetrical(Instru instru, Date date, Date effectiveDate,
			String unit, String serialNumber, String remark) {
		this.instru = instru;
		this.date = date;
		this.effectiveDate = effectiveDate;
		this.unit = unit;
		this.serialNumber = serialNumber;
		this.remark = remark;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "metricalId", unique = true, nullable = false)
	public Integer getMetricalId() {
		return this.metricalId;
	}

	public void setMetricalId(Integer metricalId) {
		this.metricalId = metricalId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "instruId")
	public Instru getInstru() {
		return this.instru;
	}

	public void setInstru(Instru instru) {
		this.instru = instru;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date", nullable = false, length = 10)
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "effectiveDate", nullable = false, length = 10)
	public Date getEffectiveDate() {
		return this.effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	@Column(name = "unit", nullable = false, length = 100)
	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Column(name = "serialNumber", nullable = false, length = 50)
	public String getSerialNumber() {
		return this.serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	@Column(name = "remark", nullable = false, length = 65535)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Transient
	public String getInstruName() {
		return instruName;
	}
	
	public void setInstruName(String instruName) {
		this.instruName = instruName;
	}
	
	@Transient
	public String getInstruModel() {
		return instruModel;
	}
	
	public void setInstruModel(String instruModel) {
		this.instruModel = instruModel;
	}
}