package cn.cas.iue.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.cas.iue.bean.Impl.AbstractAppraisalRec;

/**
 * Appraisalrec entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "appraisalrec", catalog = "cm")
public class AppraisalRec extends AbstractAppraisalRec implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public AppraisalRec() {
	}

	/** full constructor */
	public AppraisalRec(User user, Date date, String path) {
		super(user, date, path);
	}

}
