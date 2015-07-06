package cn.cas.iue.dao.impl;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.LockMode;
import org.hibernate.Query;

import static org.hibernate.criterion.Example.create;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import cn.cas.iue.base.BaseDAO;
import cn.cas.iue.bean.Role;
import cn.cas.iue.bean.User;
import cn.cas.iue.dao.RoleDAO;

/**
 * A data access object (DAO) providing persistence and search support for Role
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see cn.cas.iue.bean.Role
 * @author MyEclipse Persistence Tools
 */
@Component("roleDAO")
public class RoleDAOImpl extends BaseDAO implements RoleDAO{
	private static final Logger log = LoggerFactory.getLogger(RoleDAOImpl.class);
	// property constants
	public static final String ROLE_NAME = "roleName";
	private HibernateTemplate hibernateTemplate;
	
	public void save(Role transientInstance) {
		log.debug("saving Role instance");
		try {
			hibernateTemplate.save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Role persistentInstance) {
		log.debug("deleting Role instance");
		try {
			hibernateTemplate.delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Role findById(Integer id) {
		log.debug("getting Role instance with id: " + id);
		try {
			Role instance = (Role) hibernateTemplate.get("cn.cas.iue.bean.Role", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Role> findByProperty(String key, Object value) {
		log.debug("finding Role instance with property: " + key
				+ ", value: " + value);
		try {
			String queryString = "from Role as model where model." + key + "=" + value;
			List<Role> roles = hibernateTemplate.find(queryString);
			return roles;
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Role> findByRoleName(Object roleName) {
		return findByProperty(ROLE_NAME, roleName);
	}

/*	public List findAll() {
		log.debug("finding all Role instances");
		try {
			String queryString = "from Role";
			Query queryObject = hibernateTemplate.find(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}*/

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
}