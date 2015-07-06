package cn.cas.iue.bean.Impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import cn.cas.iue.bean.Node;
import cn.cas.iue.bean.Rnrel;

/**
 * AbstractNode entity provides the base persistence definition of the Node
 * entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractNode implements java.io.Serializable {

	// Fields

	private Integer nodeId;
	private String id;
	private String text;
	private boolean leaf;
	private Integer pid;
	private String ctrlName;
	private String treeId;
	private List<Node> children = new ArrayList<Node>();
	private Set<Rnrel> rnrels = new HashSet<Rnrel>(0);

	// Constructors

	/** default constructor */
	public AbstractNode() {
	}

	/** minimal constructor */
	public AbstractNode(String id, String text, boolean leaf) {
		this.id = id;
		this.text = text;
		this.leaf = leaf;
	}

	/** full constructor */
	public AbstractNode(String id, String text, boolean leaf, Integer pid,
			String ctrlName, String treeId, Set<Rnrel> rnrels) {
		this.id = id;
		this.text = text;
		this.leaf = leaf;
		this.pid = pid;
		this.ctrlName = ctrlName;
		this.treeId = treeId;
		this.rnrels = rnrels;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "nodeId", unique = true, nullable = false)
	public Integer getNodeId() {
		return this.nodeId;
	}

	public void setNodeId(Integer nodeId) {
		this.nodeId = nodeId;
	}

	@Column(name = "id", nullable = false, length = 30)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "text", nullable = false, length = 50)
	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Column(name = "leaf", nullable = false)
	public boolean getLeaf() {
		return this.leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	@Column(name = "pid")
	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	@Column(name = "ctrlName", length = 30)
	public String getCtrlName() {
		return this.ctrlName;
	}

	public void setCtrlName(String ctrlName) {
		this.ctrlName = ctrlName;
	}

	@Column(name = "treeId", length = 30)
	public String getTreeId() {
		return this.treeId;
	}

	public void setTreeId(String treeId) {
		this.treeId = treeId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "node")
	public Set<Rnrel> getRnrels() {
		return this.rnrels;
	}

	public void setRnrels(Set<Rnrel> rnrels) {
		this.rnrels = rnrels;
	}
	@Transient
	public List<Node> getChildren() {
		return children;
	}

	public void setChildren(List<Node> children) {
		this.children = children;
	}

}