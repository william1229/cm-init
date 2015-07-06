package cn.cas.iue.bean;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.cas.iue.bean.Impl.AbstractUser;

/**
 * User entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user", catalog = "cm")
public class User extends AbstractUser implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(String userName, String password, String name, String sex,
			String telephone) {
		super(userName, password, name, sex, telephone);
	}

	/** full constructor */
	public User(String userName, String password, String name, String sex,
			String telephone, String email, String school, String major,
			String degree, String jobContent, Set<Urrel> urrels,
			Set<Instru> instrus) {
		super(userName, password, name, sex, telephone, email, school, major,
				degree, jobContent, urrels, instrus);
	}

}
