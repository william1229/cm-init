package cn.cas.iue.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.cas.iue.bean.Impl.AbstractSSOutInRec;

/**
 * Ssoutinrec entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ssoutinrec", catalog = "cm")
public class SSOutInRec extends AbstractSSOutInRec implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public SSOutInRec() {
	}

	/** full constructor */
	public SSOutInRec(SSample sSample, User user, Date date, String action,
			double quantity) {
		super(sSample, user, date, action, quantity);
	}

}
