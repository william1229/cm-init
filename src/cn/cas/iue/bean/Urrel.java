package cn.cas.iue.bean;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.cas.iue.bean.Impl.AbstractUrrel;

/**
 * Urrel entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "urrel", catalog = "cm")
public class Urrel extends AbstractUrrel implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Urrel() {
	}

	/** full constructor */
	public Urrel(User user, Role role) {
		super(user, role);
	}

}
