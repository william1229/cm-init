package cn.cas.iue.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.cas.iue.bean.Impl.AbstractMgtReview;

/**
 * Mgtreview entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "mgtreview", catalog = "cm")
public class MgtReview extends AbstractMgtReview implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public MgtReview() {
	}

	/** full constructor */
	public MgtReview(Date date, String path) {
		super(date, path);
	}

}
