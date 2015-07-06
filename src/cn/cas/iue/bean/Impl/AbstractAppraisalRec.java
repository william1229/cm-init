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
 * AbstractAppraisalrec entity provides the base persistence definition of the
 * Appraisalrec entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractAppraisalRec implements java.io.Serializable {

	// Fields

	private Integer recId;
	private User user;
	private Date date;
	private String path;
	private String userName;
	private String name;

	// Constructors

	/** default constructor */
	public AbstractAppraisalRec() {
	}

	/** full constructor */
	public AbstractAppraisalRec(User user, Date date, String path) {
		this.user = user;
		this.date = date;
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
	@JoinColumn(name = "userId", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date", nullable = false, length = 10)
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name = "path", nullable = false, length = 200)
	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	@Transient
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Transient
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}