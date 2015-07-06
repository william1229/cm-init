package cn.cas.iue.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.cas.iue.bean.Impl.AbstractPurchasingRec;

/**
 * Purchasingrec entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "purchasingrec", catalog = "cm")
public class PurchasingRec extends AbstractPurchasingRec implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public PurchasingRec() {
	}

	/** minimal constructor */
	public PurchasingRec(Consumable consumable, User user, Date date,
			Integer quantity, String state) {
		super(consumable, user, date, quantity, state);
	}

	/** full constructor */
	public PurchasingRec(Instru instru, Consumable consumable, User user,
			Date date, Integer quantity, String remark, String state) {
		super(instru, consumable, user, date, quantity, remark, state);
	}

}
