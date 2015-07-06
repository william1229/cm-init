package cn.cas.iue.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.cas.iue.bean.Impl.AbstractGasCsum;

/**
 * Gascsum entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "gascsum", catalog = "cm")
public class GasCsum extends AbstractGasCsum implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public GasCsum() {
	}

	/** minimal constructor */
	public GasCsum(User user, Date date, String path) {
		super(user, date, path);
	}

	/** full constructor */
	public GasCsum(User user, String filename, Date date, String path) {
		super(user, filename, date, path);
	}

}
