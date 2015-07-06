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

import cn.cas.iue.bean.SSample;
import cn.cas.iue.bean.User;

/**
 * AbstractSsoutinrec entity provides the base persistence definition of the
 * Ssoutinrec entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractSSOutInRec implements java.io.Serializable {

	// Fields

	private Integer recId;
	private SSample sSample;
	private User user;
	private Date date;
	private String action;
	private double quantity;
	private String userName;
	private String name;
	private String standardSN;
	private String sSampleName;
	private String sampleSN;
	private String specification;

	// Constructors

	/** default constructor */
	public AbstractSSOutInRec() {
	}

	/** full constructor */
	public AbstractSSOutInRec(SSample sSample, User user, Date date,
			String action, double quantity) {
		this.sSample = sSample;
		this.user = user;
		this.date = date;
		this.action = action;
		this.quantity = quantity;
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
	@JoinColumn(name = "sSampleId", nullable = false)
	public SSample getSSample() {
		return this.sSample;
	}

	public void setSSample(SSample sSample) {
		this.sSample = sSample;
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

	@Column(name = "action", nullable = false, length = 5)
	public String getAction() {
		return this.action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	@Column(name = "quantity", nullable = false, precision = 22, scale = 0)
	public double getQuantity() {
		return this.quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
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
	@Transient
	public String getStandardSN() {
		return standardSN;
	}

	public void setStandardSN(String standardSN) {
		this.standardSN = standardSN;
	}
	@Transient
	public String getsSampleName() {
		return sSampleName;
	}

	public void setsSampleName(String sSampleName) {
		this.sSampleName = sSampleName;
	}
	@Transient
	public String getSampleSN() {
		return sampleSN;
	}

	public void setSampleSN(String sampleSN) {
		this.sampleSN = sampleSN;
	}
	@Transient
	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

}