package cn.cas.iue.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.cas.iue.bean.Impl.AbstractMetrical;

/**
 * Metrical entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "metrical", catalog = "cm")
public class Metrical extends AbstractMetrical implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Metrical() {
	}

	/** minimal constructor */
	public Metrical(Date date, Date effectiveDate, String unit,
			String serialNumber, String remark) {
		super(date, effectiveDate, unit, serialNumber, remark);
	}

	/** full constructor */
	public Metrical(Instru instru, Date date, Date effectiveDate, String unit,
			String serialNumber, String remark) {
		super(instru, date, effectiveDate, unit, serialNumber, remark);
	}

}
