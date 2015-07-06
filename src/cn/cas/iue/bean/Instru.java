package cn.cas.iue.bean;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.cas.iue.bean.Impl.AbstractInstru;

/**
 * Instru entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "instru", catalog = "cm")
public class Instru extends AbstractInstru implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Instru() {
	}

	/** minimal constructor */
	public Instru(String instruName, String instruModel, String instruNum,
			String manufacturer, String country, String distributor, Date date,
			String money, String location, boolean consumableIsLow) {
		super(instruName, instruModel, instruNum, manufacturer, country,
				distributor, date, money, location, consumableIsLow);
	}

	/** full constructor */
	public Instru(User user, String instruName, String instruModel,
			String instruNum, String manufacturer, String country,
			String distributor, Date date, String money, String location,
			boolean consumableIsLow, Set<Consumable> consumables) {
		super(user, instruName, instruModel, instruNum, manufacturer, country,
				distributor, date, money, location, consumableIsLow, consumables);
	}

}
