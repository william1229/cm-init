package cn.cas.iue.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.cas.iue.bean.Impl.AbstractInReview;

/**
 * Inreview entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "inreview", catalog = "cm")
public class InReview extends AbstractInReview implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public InReview() {
	}

	/** full constructor */
	public InReview(Date date, String path) {
		super(date, path);
	}

}
