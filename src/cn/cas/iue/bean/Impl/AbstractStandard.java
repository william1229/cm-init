package cn.cas.iue.bean.Impl;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * AbstractStandard entity provides the base persistence definition of the
 * Standard entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractStandard implements java.io.Serializable {

	// Fields

	private Integer standardId;
	private String serialNumber;
	private String name;
	private Date date;
	private Date effectiveDate;
	private String state;
	private String path;

	// Constructors

	/** default constructor */
	public AbstractStandard() {
	}

	/** full constructor */
	public AbstractStandard(String serialNumber, String name, Date date,
			Date effectiveDate, String state, String path) {
		this.serialNumber = serialNumber;
		this.name = name;
		this.date = date;
		this.effectiveDate = effectiveDate;
		this.state = state;
		this.path = path;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "standardId", unique = true, nullable = false)
	public Integer getStandardId() {
		return this.standardId;
	}

	public void setStandardId(Integer standardId) {
		this.standardId = standardId;
	}

	@Column(name = "serialNumber", nullable = false, length = 50)
	public String getSerialNumber() {
		return this.serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	@Column(name = "name", nullable = false, length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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

	@Column(name = "state", nullable = false, length = 5)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "path", nullable = false, length = 200)
	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}