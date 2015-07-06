package cn.cas.iue.bean.Impl;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * AbstractInreview entity provides the base persistence definition of the
 * Inreview entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractInReview implements java.io.Serializable {

	// Fields

	private Integer reviewId;
	private Date date;
	private String path;

	// Constructors

	/** default constructor */
	public AbstractInReview() {
	}

	/** full constructor */
	public AbstractInReview(Date date, String path) {
		this.date = date;
		this.path = path;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "reviewId", unique = true, nullable = false)
	public Integer getReviewId() {
		return this.reviewId;
	}

	public void setReviewId(Integer reviewId) {
		this.reviewId = reviewId;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date", nullable = false, length = 10)
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name = "path", nullable = false, length = 100)
	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}