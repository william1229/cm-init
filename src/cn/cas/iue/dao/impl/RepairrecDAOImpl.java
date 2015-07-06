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
import cn.cas.iue.bean.RepairRec;
import cn.cas.iue.dao.CertificateDAO;
import cn.cas.iue.dao.RepairRecDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * Repairrec entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see cn.cas.iue.bean.RepairRec
 * @author MyEclipse Persistence Tools
 */
@Component("repairrecDAO")
public class RepairrecDAOImpl extends BaseDAO implements RepairRecDAO {
	private static final Logger log = LoggerFactory
			.getLogger(RepairrecDAOImpl.class);
	// property constants
	public static final String NAME = "name";
	public static final String COST = "cost";
	public static final String CONTENT = "content";
	private HibernateTemplate hibernateTemplate;

	public void save(RepairRec transientInstance) {
		log.debug("saving Repairrec instance");
		try {
			hibernateTemplate.save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(RepairRec persistentInstance) {
		log.debug("deleting Repairrec instance");
		try {
			hibernateTemplate.delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public RepairRec findById(java.lang.Integer id) {
		log.debug("getting Repairrec instance with id: " + id);
		try {
			RepairRec instance = (RepairRec) hibernateTemplate.get(
					"cn.cas.iue.bean.Repairrec", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<RepairRec> findByProperty(String key, Object value) {
		log.debug("finding RepairRec instance with property: " + key
				+ ", value: " + value);
		try {
			String queryString = "from RepairRec as model where model." + key + "= '" + value + "'";
			List<RepairRec> repairRecs = hibernateTemplate.find(queryString);
			return repairRecs;
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<RepairRec> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List<RepairRec> findByCost(Object cost) {
		return findByProperty(COST, cost);
	}

	public List<RepairRec> findByContent(Object content) {
		return findByProperty(CONTENT, content);
	}

	public List<RepairRec> findAll(final int start, final int limit) {
		log.debug("finding all RepairRec instances");
		try {
			final String queryString = "from RepairRec order by date desc";
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
			List<Long> count = hibernateTemplate.find("select COUNT(*) from RepairRec" );
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