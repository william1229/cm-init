package cn.cas.iue.bean;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.cas.iue.bean.Impl.AbstractRole;

/**
 * Role entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "role", catalog = "cm")
public class Role extends AbstractRole implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Role() {
	}

	/** minimal constructor */
	public Role(String roleName) {
		super(roleName);
	}

	/** full constructor */
	public Role(String roleName, Set<Rnrel> rnrels, Set<Urrel> urrels) {
		super(roleName, rnrels, urrels);
	}

}
