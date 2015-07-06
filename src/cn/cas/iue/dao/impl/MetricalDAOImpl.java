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
import cn.cas.iue.bean.Metrical;
import cn.cas.iue.dao.CertificateDAO;
import cn.cas.iue.dao.MetricalDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * Metrical entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see cn.cas.iue.bean.Metrical
 * @author MyEclipse Persistence Tools
 */
@Component("metricalDAO")
public class MetricalDAOImpl extends BaseDAO implements MetricalDAO {
	private static final Logger log = LoggerFactory
			.getLogger(MetricalDAOImpl.class);
	// property constants
	public static final String UNIT = "unit";
	public static final String SERIAL_NUMBER = "serialNumber";
	public static final String REMARK = "remark";
	private HibernateTemplate hibernateTemplate;

	public void save(Metrical transientInstance) {
		log.debug("saving Metrical instance");
		try {
			hibernateTemplate.save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Metrical persistentInstance) {
		log.debug("deleting Metrical instance");
		try {
			hibernateTemplate.delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Metrical findById(java.lang.Integer id) {
		log.debug("getting Metrical instance with id: " + id);
		try {
			Metrical instance = (Metrical) hibernateTemplate.get(
					"cn.cas.iue.bean.Metrical", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Metrical> findByProperty(String key, Object value) {
		log.debug("finding Metrical instance with property: " + key
				+ ", value: " + value);
		try {
			String queryString = "from Metrical as model where model." + key + "= '" + value + "'";
			List<Metrical> metricals = hibernateTemplate.find(queryString);
			return metricals;
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Metrical> findByUnit(Object unit) {
		return findByProperty(UNIT, unit);
	}

	public List<Metrical> findBySerialNumber(Object serialNumber) {
		return findByProperty(SERIAL_NUMBER, serialNumber);
	}

	public List<Metrical> findByRemark(Object remark) {
		return findByProperty(REMARK, remark);
	}

	public List<Metrical> findAll(final int start, final int limit) {
		log.debug("finding all Metrical instances");
		try {
			final String queryString = "from Metrical order by date desc";
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
			List<Long> count = hibernateTemplate.find("select COUNT(*) from Metrical" );
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