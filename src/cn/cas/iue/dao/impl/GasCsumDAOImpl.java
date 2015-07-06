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
import cn.cas.iue.bean.Certificate;
import cn.cas.iue.bean.GasCsum;
import cn.cas.iue.dao.GasCsumDAO;
import cn.cas.iue.dao.InstruDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * Gascsum entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see cn.cas.iue.bean.GasCsum
 * @author MyEclipse Persistence Tools
 */
@Component("gasCsumDAO")
public class GasCsumDAOImpl extends BaseDAO implements GasCsumDAO  {
	private static final Logger log = LoggerFactory.getLogger(GasCsumDAOImpl.class);
	// property constants
	public static final String FILENAME = "filename";
	public static final String PATH = "path";
	private HibernateTemplate hibernateTemplate;

	public void save(GasCsum transientInstance) {
		log.debug("saving Gascsum instance");
		try {
			hibernateTemplate.save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(GasCsum persistentInstance) {
		log.debug("deleting Gascsum instance");
		try {
			hibernateTemplate.delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public GasCsum findById(java.lang.Integer id) {
		log.debug("getting Gascsum instance with id: " + id);
		try {
			GasCsum instance = (GasCsum) hibernateTemplate.get(
					"cn.cas.iue.bean.Gascsum", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<GasCsum> findByProperty(String key, Object value) {
		log.debug("finding GasCsum instance with property: " + key
				+ ", value: " + value);
		try {
			String queryString = "from GasCsum as model where model." + key + "= '" + value + "'";
			List<GasCsum> gasCsums = hibernateTemplate.find(queryString);
			return gasCsums;
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<GasCsum> findByFilename(Object filename) {
		return findByProperty(FILENAME, filename);
	}

	public List<GasCsum> findByPath(Object path) {
		return findByProperty(PATH, path);
	}

	public List<GasCsum> findAll(final int start, final int limit) {
		log.debug("finding all GasCsum instances");
		try {
			final String queryString = "from GasCsum";
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

	public Long getCount(){
		try {
			List<Long> count = hibernateTemplate.find("select COUNT(*) from GasCsum" );
			return count.get(0);
		} catch (RuntimeException re) {
			throw re;
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