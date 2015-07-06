package cn.cas.iue.dao.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;

import static org.hibernate.criterion.Example.create;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import cn.cas.iue.base.BaseDAO;
import cn.cas.iue.bean.Standard;
import cn.cas.iue.dao.StandardDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * Standard entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see cn.cas.iue.bean.Standard
 * @author MyEclipse Persistence Tools
 */
@Component("standardDAO")
public class StandardDAOImpl extends BaseDAO implements StandardDAO{
	private static final Logger log = LoggerFactory
			.getLogger(StandardDAOImpl.class);
	// property constants
	public static final String SERIAL_NUMBER = "serialNumber";
	public static final String NAME = "name";
	public static final String STATE = "state";
	public static final String PATH = "path";
	private HibernateTemplate hibernateTemplate;

	public void save(Standard transientInstance) {
		log.debug("saving Standard instance");
		try {
			hibernateTemplate.save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Standard persistentInstance) {
		log.debug("deleting Standard instance");
		try {
			hibernateTemplate.delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Standard findById(java.lang.Integer id) {
		log.debug("getting Standard instance with id: " + id);
		try {
			Standard instance = (Standard) hibernateTemplate.get(
					"cn.cas.iue.bean.Standard", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Standard> findByProperty(String key, Object value) {
		log.debug("finding Standard instance with property: " + key
				+ ", value: " + value);
		try {
			String queryString = "from Standard as model where model." + key + "= '" + value + "'";
			List<Standard> standards = hibernateTemplate.find(queryString);
			return standards;
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	//获取记录总数
	public Long getCount() {
		try {
			List<Long> count = hibernateTemplate.find("select COUNT(*) from Standard");
			return count.get(0);
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	public List<Standard> findBySerialNumber(Object serialNumber) {
		return findByProperty(SERIAL_NUMBER, serialNumber);
	}

	public List<Standard> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List<Standard> findByState(Object state) {
		return findByProperty(STATE, state);
	}

	public List<Standard> findByPath(Object path) {
		return findByProperty(PATH, path);
	}

	public List<Standard> findAll(final int start, final int limit) {
		log.debug("finding all Standard instances");
		try {
			final String queryString = "from Standard order by state desc";
			List list = hibernateTemplate.executeFind(new HibernateCallback() {     
				public Object doInHibernate(Session session)throws HibernateException, SQLException {     
					Query query = session.createQuery(queryString);     
					query.setFirstResult(start);
					query.setMaxResults(limit);
					return query.list();
			    }     
			});     
			return list; 
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public void updateState(String state, Integer standardId) throws Exception {
		// TODO Auto-generated method stub
		try {
			hibernateTemplate.bulkUpdate("update Standard set state=? where standardId=?", new Object[]{state, standardId});
			hibernateTemplate.flush();
		} catch (RuntimeException re) {
			// TODO: handle exception
			throw re;
		} finally {
			hibernateTemplate.clear();
		}
	}
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
}