package cn.cas.iue.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.cas.iue.bean.Impl.AbstractSample;

/**
 * Sample entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sample", catalog = "cm")
public class Sample extends AbstractSample implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Sample() {
	}

	/** full constructor */
	public Sample(Project project, String sampleSN, String state,
			Integer quantity, String unit, Date date, String name,
			String telephone) {
		super(project, sampleSN, state, quantity, unit, date, name, telephone);
	}

}
