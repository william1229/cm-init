package cn.cas.iue.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.cas.iue.bean.Impl.AbstractCertificate;

/**
 * Certificate entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "certificate", catalog = "cm")
public class Certificate extends AbstractCertificate implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Certificate() {
	}

	/** minimal constructor */
	public Certificate(User user, Date date, String authority) {
		super(user, date, authority);
	}

	/** full constructor */
	public Certificate(User user, String certificateName, String type,
			Date date, String authority) {
		super(user, certificateName, type, date, authority);
	}

}
