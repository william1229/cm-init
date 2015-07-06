package cn.cas.iue.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.cas.iue.bean.Impl.AbstractRepairRec;

/**
 * Repairrec entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "repairrec", catalog = "cm")
public class RepairRec extends AbstractRepairRec implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public RepairRec() {
	}

	/** minimal constructor */
	public RepairRec(String name, double cost, String content, Date date) {
		super(name, cost, content, date);
	}

	/** full constructor */
	public RepairRec(Instru instru, String name, double cost, String content,
			Date date) {
		super(instru, name, cost, content, date);
	}

}
