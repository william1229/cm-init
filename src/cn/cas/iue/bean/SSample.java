package cn.cas.iue.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.cas.iue.bean.Impl.AbstractSSample;

/**
 * Ssample entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ssample", catalog = "cm")
public class SSample extends AbstractSSample implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public SSample() {
	}

	/** full constructor */
	public SSample(String standardSN, String sSampleName, String sampleSN,
			String standardValue, String relativeUncertainty,
			String specification, Date effectiveDate, Date date, String source,
			String storageRequirements, double quantity) {
		super(standardSN, sSampleName, sampleSN, standardValue,
				relativeUncertainty, specification, effectiveDate, date,
				source, storageRequirements, quantity);
	}

}
