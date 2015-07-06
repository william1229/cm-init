package cn.cas.iue.bean;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.cas.iue.bean.Impl.AbstractRnrel;

/**
 * Rnrel entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "rnrel", catalog = "cm")
public class Rnrel extends AbstractRnrel implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Rnrel() {
	}

	/** full constructor */
	public Rnrel(Node node, Role role) {
		super(node, role);
	}

}
