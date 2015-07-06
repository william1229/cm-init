package cn.cas.iue.bean;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.cas.iue.bean.Impl.AbstractConsumable;

/**
 * Consumable entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "consumable", catalog = "cm")
public class Consumable extends AbstractConsumable implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Consumable() {
	}

	/** minimal constructor */
	public Consumable(String consumableName, Integer quantity) {
		super(consumableName, quantity);
	}

	/** full constructor */
	public Consumable(Instru instru, String consumableName,
			String consumableType, Integer quantity) {
		super(instru, consumableName, consumableType, quantity);
	}

}
