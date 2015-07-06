package cn.cas.iue.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.cas.iue.bean.Impl.AbstractOutInRec;

/**
 * Outinrec entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "outinrec", catalog = "cm")
public class OutInRec extends AbstractOutInRec implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public OutInRec() {
	}

	/** minimal constructor */
	public OutInRec(Consumable consumable, User user, Date date,
			Integer quantity, String action) {
		super(consumable, user, date, quantity, action);
	}

	/** full constructor */
	public OutInRec(Instru instru, Consumable consumable, User user, Date date,
			Integer quantity, String action) {
		super(instru, consumable, user, date, quantity, action);
	}

}
