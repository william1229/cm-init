package cn.cas.iue.bean.Impl;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import cn.cas.iue.bean.Node;
import cn.cas.iue.bean.Role;

/**
 * AbstractRnrel entity provides the base persistence definition of the Rnrel
 * entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractRnrel implements java.io.Serializable {

	// Fields

	private Integer rnId;
	private Node node;
	private Role role;

	// Constructors

	/** default constructor */
	public AbstractRnrel() {
	}

	/** full constructor */
	public AbstractRnrel(Node node, Role role) {
		this.node = node;
		this.role = role;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "rnId", unique = true, nullable = false)
	public Integer getRnId() {
		return this.rnId;
	}

	public void setRnId(Integer rnId) {
		this.rnId = rnId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "nodeId", nullable = false)
	public Node getNode() {
		return this.node;
	}

	public void setNode(Node node) {
		this.node = node;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "roleId", nullable = false)
	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}