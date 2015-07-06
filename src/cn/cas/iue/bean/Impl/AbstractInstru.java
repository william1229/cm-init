package cn.cas.iue.bean.Impl;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import cn.cas.iue.bean.Consumable;
import cn.cas.iue.bean.User;

/**
 * AbstractInstru entity provides the base persistence definition of the Instru
 * entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractInstru implements java.io.Serializable {

	// Fields

	private Integer instruId;
	private User user;
	private String instruName;
	private String instruModel;
	private String instruNum;
	private String manufacturer;
	private String country;
	private String distributor;
	private Date date;
	private String money;
	private String location;
	private String name;
	private Integer userId;
	private boolean consumableIsLow;
	private Set<Consumable> consumables = new HashSet<Consumable>(0);

	// Constructors

	/** default constructor */
	public AbstractInstru() {
	}

	/** minimal constructor */
	public AbstractInstru(String instruName, String instruModel,
			String instruNum, String manufacturer, String country,
			String distributor, Date date, String money, String location,
			boolean consumableIsLow) {
		this.instruName = instruName;
		this.instruModel = instruModel;
		this.instruNum = instruNum;
		this.manufacturer = manufacturer;
		this.country = country;
		this.distributor = distributor;
		this.date = date;
		this.money = money;
		this.location = location;
		this.consumableIsLow = consumableIsLow;
	}

	/** full constructor */
	public AbstractInstru(User user, String instruName, String instruModel,
			String instruNum, String manufacturer, String country,
			String distributor, Date date, String money, String location, 
			boolean consumableIsLow, Set<Consumable> consumables) {
		this.user = user;
		this.instruName = instruName;
		this.instruModel = instruModel;
		this.instruNum = instruNum;
		this.manufacturer = manufacturer;
		this.country = country;
		this.distributor = distributor;
		this.date = date;
		this.money = money;
		this.location = location;
		this.consumableIsLow = consumableIsLow;
		this.consumables = consumables;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "instruId", unique = true, nullable = false)
	public Integer getInstruId() {
		return this.instruId;
	}

	public void setInstruId(Integer instruId) {
		this.instruId = instruId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "instruName", nullable = false, length = 50)
	public String getInstruName() {
		return this.instruName;
	}

	public void setInstruName(String instruName) {
		this.instruName = instruName;
	}

	@Column(name = "instruModel", nullable = false, length = 50)
	public String getInstruModel() {
		return this.instruModel;
	}

	public void setInstruModel(String instruModel) {
		this.instruModel = instruModel;
	}

	@Column(name = "instruNum", nullable = false, length = 50)
	public String getInstruNum() {
		return this.instruNum;
	}

	public void setInstruNum(String instruNum) {
		this.instruNum = instruNum;
	}

	@Column(name = "manufacturer", nullable = false, length = 50)
	public String getManufacturer() {
		return this.manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	@Column(name = "country", nullable = false, length = 20)
	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Column(name = "distributor", nullable = false, length = 50)
	public String getDistributor() {
		return this.distributor;
	}

	public void setDistributor(String distributor) {
		this.distributor = distributor;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date", nullable = false, length = 10)
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name = "money", nullable = false, length = 20)
	public String getMoney() {
		return this.money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	@Column(name = "location", nullable = false, length = 50)
	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Column(name = "consumableIsLow", nullable = false)
	public boolean getConsumableIsLow() {
		return this.consumableIsLow;
	}

	public void setConsumableIsLow(boolean consumableIsLow) {
		this.consumableIsLow = consumableIsLow;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "instru")
	public Set<Consumable> getConsumables() {
		return this.consumables;
	}

	public void setConsumables(Set<Consumable> consumables) {
		this.consumables = consumables;
	}
	@Transient
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Transient
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}