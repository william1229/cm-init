package cn.cas.iue.bean.Impl;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import cn.cas.iue.bean.Instru;

/**
 * AbstractConsumable entity provides the base persistence definition of the
 * Consumable entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractConsumable implements java.io.Serializable {

	// Fields

	private Integer consumableId;
	private Instru instru;
	private String consumableName;
	private String consumableType;
	private Integer quantity;

	// Constructors

	/** default constructor */
	public AbstractConsumable() {
	}

	/** minimal constructor */
	public AbstractConsumable(String consumableName, Integer quantity) {
		this.consumableName = consumableName;
		this.quantity = quantity;
	}

	/** full constructor */
	public AbstractConsumable(Instru instru, String consumableName,
			String consumableType, Integer quantity) {
		this.instru = instru;
		this.consumableName = consumableName;
		this.consumableType = consumableType;
		this.quantity = quantity;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "consumableId", unique = true, nullable = false)
	public Integer getConsumableId() {
		return this.consumableId;
	}

	public void setConsumableId(Integer consumableId) {
		this.consumableId = consumableId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "instruId")
	public Instru getInstru() {
		return this.instru;
	}

	public void setInstru(Instru instru) {
		this.instru = instru;
	}

	@Column(name = "consumableName", nullable = false, length = 50)
	public String getConsumableName() {
		return this.consumableName;
	}

	public void setConsumableName(String consumableName) {
		this.consumableName = consumableName;
	}

	@Column(name = "consumableType", length = 50)
	public String getConsumableType() {
		return this.consumableType;
	}

	public void setConsumableType(String consumableType) {
		this.consumableType = consumableType;
	}

	@Column(name = "quantity", nullable = false)
	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}