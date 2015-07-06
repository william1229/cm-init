package cn.cas.iue.bean.Impl;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

import cn.cas.iue.bean.Rnrel;
import cn.cas.iue.bean.Urrel;

/**
 * AbstractRole entity provides the base persistence definition of the Role
 * entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractRole implements java.io.Serializable {

	// Fields

	private Integer roleId;
	private String roleName;
	private Set<Rnrel> rnrels = new HashSet<Rnrel>(0);
	private Set<Urrel> urrels = new HashSet<Urrel>(0);

	// Constructors

	/** default constructor */
	public AbstractRole() {
	}

	/** minimal constructor */
	public AbstractRole(String roleName) {
		this.roleName = roleName;
	}

	/** full constructor */
	public AbstractRole(String roleName, Set<Rnrel> rnrels, Set<Urrel> urrels) {
		this.roleName = roleName;
		this.rnrels = rnrels;
		this.urrels = urrels;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "roleId", unique = true, nullable = false)
	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	@Column(name = "roleName", nullable = false, length = 20)
	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "role")
	public Set<Rnrel> getRnrels() {
		return this.rnrels;
	}

	public void setRnrels(Set<Rnrel> rnrels) {
		this.rnrels = rnrels;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "role")
	public Set<Urrel> getUrrels() {
		return this.urrels;
	}

	public void setUrrels(Set<Urrel> urrels) {
		this.urrels = urrels;
	}

}