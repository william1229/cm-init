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
 * AbstractRepairrec entity provides the base persistence definition of the
 * Repairrec entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractRepairRec implements java.io.Serializable {

	// Fields

	private Integer recId;
	private Instru instru;
	private String name;
	private double cost;
	private String content;
	private Date date;
	private String instruName;
	private String instruModel;

	// Constructors

	/** default constructor */
	public AbstractRepairRec() {
	}

	/** minimal constructor */
	public AbstractRepairRec(String name, double cost, String content, Date date) {
		this.name = name;
		this.cost = cost;
		this.content = content;
		this.date = date;
	}

	/** full constructor */
	public AbstractRepairRec(Instru instru, String name, double cost,
			String content, Date date) {
		this.instru = instru;
		this.name = name;
		this.cost = cost;
		this.content = content;
		this.date = date;
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
	@JoinColumn(name = "instruId")
	public Instru getInstru() {
		return this.instru;
	}

	public void setInstru(Instru instru) {
		this.instru = instru;
	}

	@Column(name = "name", nullable = false, length = 10)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "cost", nullable = false, precision = 22, scale = 0)
	public double getCost() {
		return this.cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	@Column(name = "content", nullable = false, length = 65535)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date", nullable = false, length = 10)
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
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