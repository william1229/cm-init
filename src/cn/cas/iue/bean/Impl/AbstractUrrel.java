package cn.cas.iue.bean.Impl;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import cn.cas.iue.bean.Role;
import cn.cas.iue.bean.User;

/**
 * AbstractUrrel entity provides the base persistence definition of the Urrel
 * entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractUrrel implements java.io.Serializable, Comparable<AbstractUrrel>{

	// Fields

	private Integer urId;
	private User user;
	private Role role;

	// Constructors

	/** default constructor */
	public AbstractUrrel() {
	}

	/** full constructor */
	public AbstractUrrel(User user, Role role) {
		this.user = user;
		this.role = role;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "urId", unique = true, nullable = false)
	public Integer getUrId() {
		return this.urId;
	}

	public void setUrId(Integer urId) {
		this.urId = urId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "roleId", nullable = false)
	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public int compareTo(AbstractUrrel o) {
		// TODO Auto-generated method stub
		return o.getRole().getRoleId()-this.getRole().getRoleId();
	}
}