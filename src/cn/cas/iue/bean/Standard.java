package cn.cas.iue.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.cas.iue.bean.Impl.AbstractStandard;

/**
 * Standard entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "standard", catalog = "cm")
public class Standard extends AbstractStandard implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Standard() {
	}

	/** full constructor */
	public Standard(String serialNumber, String name, Date date,
			Date effectiveDate, String state, String path) {
		super(serialNumber, name, date, effectiveDate, state, path);
	}

}
