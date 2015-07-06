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
 * AbstractCertificate entity provides the base persistence definition of the
 * Certificate entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractCertificate implements java.io.Serializable {

	// Fields

	private Integer creatificateId;
	private User user;
	private String certificateName;
	private String type;
	private Date date;
	private String authority;
	private String userName;
	private String name;

	// Constructors

	/** default constructor */
	public AbstractCertificate() {
	}

	/** minimal constructor */
	public AbstractCertificate(User user, Date date, String authority) {
		this.user = user;
		this.date = date;
		this.authority = authority;
	}

	/** full constructor */
	public AbstractCertificate(User user, String certificateName, String type,
			Date date, String authority) {
		this.user = user;
		this.certificateName = certificateName;
		this.type = type;
		this.date = date;
		this.authority = authority;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "creatificateId", unique = true, nullable = false)
	public Integer getCreatificateId() {
		return this.creatificateId;
	}

	public void setCreatificateId(Integer creatificateId) {
		this.creatificateId = creatificateId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "certificateName", length = 50)
	public String getCertificateName() {
		return this.certificateName;
	}

	public void setCertificateName(String certificateName) {
		this.certificateName = certificateName;
	}

	@Column(name = "type", length = 20)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date", nullable = false, length = 10)
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name = "authority", nullable = false, length = 100)
	public String getAuthority() {
		return this.authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
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