package cn.cas.iue.bean;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.cas.iue.bean.Impl.AbstractNode;

/**
 * Node entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "node", catalog = "cm")
public class Node extends AbstractNode implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Node() {
	}

	/** minimal constructor */
	public Node(String id, String text, boolean leaf) {
		super(id, text, leaf);
	}

	/** full constructor */
	public Node(String id, String text, boolean leaf, Integer pid,
			String ctrlName, String treeId, Set<Rnrel> rnrels) {
		super(id, text, leaf, pid, ctrlName, treeId, rnrels);
	}

}
