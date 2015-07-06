package cn.cas.iue.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.LockMode;
import org.hibernate.Query;

import static org.hibernate.criterion.Example.create;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import cn.cas.iue.base.BaseDAO;
import cn.cas.iue.bean.Urrel;
import cn.cas.iue.dao.UrrelDAO;

/**
 * A data access object (DAO) providing persistence and search support for Urrel
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see cn.cas.iue.bean.Urrel
 * @author MyEclipse Persistence Tools
 */
@Component("urrelDAO")
public class UrrelDAOImpl extends BaseDAO implements UrrelDAO{
	private static final Logger log = LoggerFactory.getLogger(UrrelDAOImpl.class);
	private HibernateTemplate hibernateTemplate;
	// property constants

	public void save(Urrel transientInstance) {
		log.debug("saving Urrel instance");
		try {
			hibernateTemplate.save(transientInstance);
			//hibernateTemplate.flush();
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Urrel persistentInstance) {
		log.debug("deleting Urrel instance");
		try {
			hibernateTemplate.delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

/*	public Urrel findById(java.lang.Integer id) {
		log.debug("getting Urrel instance with id: " + id);
		try {
			Urrel instance = (Urrel) hibernateTemplate.get("cn.cas.iue.bean.Urrel",
					id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Urrel instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Urrel as model where model."
					+ propertyName + "= ?";
			Query queryObject = hibernateTemplate.createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all Urrel instances");
		try {
			String queryString = "from Urrel";
			Query queryObject = hibernateTemplate.createQuery(queryString);
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