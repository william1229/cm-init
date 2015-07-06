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

import cn.cas.iue.bean.User;

/**
 * AbstractTrainingrec entity provides the base persistence definition of the
 * Trainingrec entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractTrainingRec implements java.io.Serializable {

	// Fields

	private Integer recId;
	private User user;
	private String name;
	private Date startDate;
	private Date endDate;
	private String unit;
	private String location;
	private String content;
	private String path;
	private String userName;

	// Constructors

	/** default constructor */
	public AbstractTrainingRec() {
	}

	/** minimal constructor */
	public AbstractTrainingRec(Date startDate, Date endDate,
			String unit, String location, String content) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.unit = unit;
		this.location = location;
		this.content = content;
	}

	/** full constructor */
	public AbstractTrainingRec(User user, Date startDate,
			Date endDate, String unit, String location, String content,
			String path) {
		this.user = user;
		this.startDate = startDate;
		this.endDate = endDate;
		this.unit = unit;
		this.location = location;
		this.content = content;
		this.path = path;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "recId", unique = true, nullable = false)
	public Integer getRecId() {
		return this.recId;
	}

	public void setRecId(Integer recId) {
		this.recId = recId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "startDate", nullable = false, length = 10)
	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "endDate", nullable = false, length = 10)
	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Column(name = "unit", nullable = false, length = 50)
	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Column(name = "location", nullable = false, length = 50)
	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Column(name = "content", nullable = false, length = 200)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "path", length = 200)
	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	@Transient
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Transient
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}