package cn.cas.iue.bean.Impl;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import cn.cas.iue.bean.Instru;
import cn.cas.iue.bean.Urrel;

/**
 * AbstractUser entity provides the base persistence definition of the User
 * entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractUser implements java.io.Serializable {

	// Fields

	private Integer userId;
	private String userName;
	private String password;
	private String name;
	private String sex;
	private String telephone;
	private String email;
	private String school;
	private String major;
	private String degree;
	private String jobContent;
	private Set<Urrel> urrels = new HashSet<Urrel>(0);
	private Set<Instru> instrus = new HashSet<Instru>(0);
	private String[] roleNames;
	// Constructors

	/** default constructor */
	public AbstractUser() {
	}

	/** minimal constructor */
	public AbstractUser(String userName, String password, String name,
			String sex, String telephone) {
		this.userName = userName;
		this.password = password;
		this.name = name;
		this.sex = sex;
		this.telephone = telephone;
	}

	/** full constructor */
	public AbstractUser(String userName, String password, String name,
			String sex, String telephone, String email, String school,
			String major, String degree, String jobContent, Set<Urrel> urrels,
			Set<Instru> instrus) {
		this.userName = userName;
		this.password = password;
		this.name = name;
		this.sex = sex;
		this.telephone = telephone;
		this.email = email;
		this.school = school;
		this.major = major;
		this.degree = degree;
		this.jobContent = jobContent;
		this.urrels = urrels;
		this.instrus = instrus;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "userId", unique = true, nullable = false)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "userName", nullable = false, length = 20)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "password", nullable = false, length = 20)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "name", nullable = false, length = 20)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "sex", nullable = false, length = 20)
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "telephone", nullable = false, length = 50)
	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Column(name = "email", length = 50)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "school", length = 20)
	public String getSchool() {
		return this.school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	@Column(name = "major", length = 20)
	public String getMajor() {
		return this.major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	@Column(name = "degree", length = 10)
	public String getDegree() {
		return this.degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	@Column(name = "jobContent", length = 50)
	public String getJobContent() {
		return this.jobContent;
	}

	public void setJobContent(String jobContent) {
		this.jobContent = jobContent;
	}

//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	@Column(updatable = false)
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Urrel> getUrrels() {
		return this.urrels;
	}

	public void setUrrels(Set<Urrel> urrels) {
		this.urrels = urrels;
	}

	@Column(updatable = false)
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Instru> getInstrus() {
		return this.instrus;
	}

	public void setInstrus(Set<Instru> instrus) {
		this.instrus = instrus;
	}
	@Transient
	public String[] getRoleNames() {
		return roleNames;
	}

	public void setRoleNames(String[] roleNames) {
		this.roleNames = roleNames;
	}
}