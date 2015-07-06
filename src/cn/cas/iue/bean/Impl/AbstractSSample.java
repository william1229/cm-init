package cn.cas.iue.bean.Impl;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * AbstractSSample entity provides the base persistence definition of the
 * SSample entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractSSample implements java.io.Serializable {

	// Fields

	private Integer sSampleId;
	private String standardSN;
	private String sSampleName;
	private String sampleSN;
	private String standardValue;
	private String relativeUncertainty;
	private String specification;
	private Date effectiveDate;
	private Date date;
	private String source;
	private String storageRequirements;
	private double quantity;

	// Constructors

	/** default constructor */
	public AbstractSSample() {
	}

	/** full constructor */
	public AbstractSSample(String standardSN, String sSampleName,
			String sampleSN, String standardValue, String relativeUncertainty,
			String specification, Date effectiveDate, Date date, String source,
			String storageRequirements, double quantity) {
		this.standardSN = standardSN;
		this.sSampleName = sSampleName;
		this.sampleSN = sampleSN;
		this.standardValue = standardValue;
		this.relativeUncertainty = relativeUncertainty;
		this.specification = specification;
		this.effectiveDate = effectiveDate;
		this.date = date;
		this.source = source;
		this.storageRequirements = storageRequirements;
		this.quantity = quantity;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "sSampleId", unique = true, nullable = false)
	public Integer getsSampleId() {
		return this.sSampleId;
	}

	public void setsSampleId(Integer sSampleId) {
		this.sSampleId = sSampleId;
	}

	@Column(name = "standardSN", nullable = false, length = 50)
	public String getStandardSN() {
		return this.standardSN;
	}

	public void setStandardSN(String standardSN) {
		this.standardSN = standardSN;
	}

	@Column(name = "sSampleName", nullable = false, length = 50)
	public String getsSampleName() {
		return this.sSampleName;
	}

	public void setsSampleName(String sSampleName) {
		this.sSampleName = sSampleName;
	}

	@Column(name = "sampleSN", nullable = false, length = 50)
	public String getSampleSN() {
		return this.sampleSN;
	}

	public void setSampleSN(String sampleSN) {
		this.sampleSN = sampleSN;
	}

	@Column(name = "standardValue", nullable = false, length = 20)
	public String getStandardValue() {
		return this.standardValue;
	}

	public void setStandardValue(String standardValue) {
		this.standardValue = standardValue;
	}

	@Column(name = "relativeUncertainty", nullable = false, length = 20)
	public String getRelativeUncertainty() {
		return this.relativeUncertainty;
	}

	public void setRelativeUncertainty(String relativeUncertainty) {
		this.relativeUncertainty = relativeUncertainty;
	}

	@Column(name = "specification", nullable = false, length = 20)
	public String getSpecification() {
		return this.specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "effectiveDate", nullable = false, length = 10)
	public Date getEffectiveDate() {
		return this.effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date", nullable = false, length = 10)
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name = "source", nullable = false, length = 200)
	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@Column(name = "storageRequirements", nullable = false, length = 200)
	public String getStorageRequirements() {
		return this.storageRequirements;
	}

	public void setStorageRequirements(String storageRequirements) {
		this.storageRequirements = storageRequirements;
	}

	@Column(name = "quantity", nullable = false, precision = 22, scale = 0)
	public double getQuantity() {
		return this.quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
}