package cn.cas.iue.bean.Impl;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * AbstractExreview entity provides the base persistence definition of the
 * Exreview entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractExReview implements java.io.Serializable {

	// Fields

	private Integer reviewId;
	private Date date;
	private String names;
	private String path;

	// Constructors

	/** default constructor */
	public AbstractExReview() {
	}

	/** full constructor */
	public AbstractExReview(Date date, String names, String path) {
		this.date = date;
		this.names = names;
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

	@Column(name = "names", nullable = false, length = 200)
	public String getNames() {
		return this.names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	@Column(name = "path", nullable = false, length = 100)
	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}