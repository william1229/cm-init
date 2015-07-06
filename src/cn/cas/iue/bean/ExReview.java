package cn.cas.iue.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.cas.iue.bean.Impl.AbstractExReview;

/**
 * Exreview entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "exreview", catalog = "cm")
public class ExReview extends AbstractExReview implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public ExReview() {
	}

	/** full constructor */
	public ExReview(Date date, String names, String path) {
		super(date, names, path);
	}

}
