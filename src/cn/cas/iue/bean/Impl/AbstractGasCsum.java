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
 * AbstractGascsum entity provides the base persistence definition of the
 * Gascsum entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractGasCsum implements java.io.Serializable {

	// Fields

	private Integer gasCsumId;
	private User user;
	private String filename;
	private Date date;
	private String path;
	private String name;

	// Constructors

	/** default constructor */
	public AbstractGasCsum() {
	}

	/** minimal constructor */
	public AbstractGasCsum(User user, Date date, String path) {
		this.user = user;
		this.date = date;
		this.path = path;
	}

	/** full constructor */
	public AbstractGasCsum(User user, String filename, Date date, String path) {
		this.user = user;
		this.filename = filename;
		this.date = date;
		this.path = path;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "gasCsumId", unique = true, nullable = false)
	public Integer getGasCsumId() {
		return this.gasCsumId;
	}

	public void setGasCsumId(Integer gasCsumId) {
		this.gasCsumId = gasCsumId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "filename", length = 50)
	public String getFilename() {
		return this.filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
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
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}