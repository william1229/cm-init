package cn.cas.iue.bean.Impl;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import cn.cas.iue.bean.Consumable;
import cn.cas.iue.bean.Instru;
import cn.cas.iue.bean.User;

/**
 * AbstractPurchasingrec entity provides the base persistence definition of the
 * Purchasingrec entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractPurchasingRec implements java.io.Serializable {

	// Fields

	private Integer recId;
	private Instru instru;
	private Consumable consumable;
	private User user;
	private Date date;
	private Integer quantity;
	private String remark;
	private String state;
	private String name;
	private Integer userId;
	private Integer instruId;
	private Integer consumableId;
	private String instruName;
	private String instruModel;
	private String consumableName;
	private String consumableType;
	private String userName;
	private int currentQuantity;

	// Constructors

	/** default constructor */
	public AbstractPurchasingRec() {
	}

	/** minimal constructor */
	public AbstractPurchasingRec(Consumable consumable, User user, Date date,
			Integer quantity, String state) {
		this.consumable = consumable;
		this.user = user;
		this.date = date;
		this.quantity = quantity;
		this.state = state;
	}

	/** full constructor */
	public AbstractPurchasingRec(Instru instru, Consumable consumable,
			User user, Date date, Integer quantity, String remark, String state) {
		this.instru = instru;
		this.consumable = consumable;
		this.user = user;
		this.date = date;
		this.quantity = quantity;
		this.remark = remark;
		this.state = state;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "recId", unique = true, nullable = false)
	public Integer getRecId() {
		return this.recId;
	}

	public void setRecId(Integer recId) {
		this.recId = recId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "instruId")
	public Instru getInstru() {
		return this.instru;
	}

	public void setInstru(Instru instru) {
		this.instru = instru;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "consumableId", nullable = false)
	public Consumable getConsumable() {
		return this.consumable;
	}

	public void setConsumable(Consumable consumable) {
		this.consumable = consumable;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date", nullable = false, length = 10)
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name = "quantity", nullable = false)
	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Column(name = "remark", length = 500)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "state", nullable = false, length = 5)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}
	@Transient
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Transient
	public String getInstruName() {
		return instruName;
	}

	public void setInstruName(String instruName) {
		this.instruName = instruName;
	}
	@Transient
	public String getConsumableName() {
		return consumableName;
	}

	public void setConsumableName(String consumableName) {
		this.consumableName = consumableName;
	}
	@Transient
	public String getConsumableType() {
		return consumableType;
	}

	public void setConsumableType(String consumableType) {
		this.consumableType = consumableType;
	}
	@Transient
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Transient
	public String getInstruModel() {
		return instruModel;
	}

	public void setInstruModel(String instruModel) {
		this.instruModel = instruModel;
	}
	@Transient
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	@Transient
	public Integer getInstruId() {
		return instruId;
	}

	public void setInstruId(Integer instruId) {
		this.instruId = instruId;
	}
	@Transient
	public Integer getConsumableId() {
		return consumableId;
	}

	public void setConsumableId(Integer consumableId) {
		this.consumableId = consumableId;
	}
	@Transient
	public int getCurrentQuantity() {
		return currentQuantity;
	}

	public void setCurrentQuantity(int currentQuantity) {
		this.currentQuantity = currentQuantity;
	}
}