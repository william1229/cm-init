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
import cn.cas.iue.bean.SSOutInRec;
import cn.cas.iue.bean.SSample;
import cn.cas.iue.dao.SSOutInRecDAO;
import cn.cas.iue.dao.SSampleDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * Ssoutinrec entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see cn.cas.iue.bean.SSOutInRec
 * @author MyEclipse Persistence Tools
 */
@Component("sSOutInRecDAO")
public class SSOutInRecDAOImpl extends BaseDAO implements SSOutInRecDAO {
	private static final Logger log = LoggerFactory
			.getLogger(SSOutInRecDAOImpl.class);
	// property constants
	public static final String ACTION = "action";
	public static final String QUANTITY = "quantity";
	private HibernateTemplate hibernateTemplate;

	public void save(SSOutInRec transientInstance) {
		log.debug("saving Ssoutinrec instance");
		try {
			hibernateTemplate.save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(SSOutInRec persistentInstance) {
		log.debug("deleting Ssoutinrec instance");
		try {
			hibernateTemplate.delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public SSOutInRec findById(java.lang.Integer id) {
		log.debug("getting Ssoutinrec instance with id: " + id);
		try {
			SSOutInRec instance = (SSOutInRec) hibernateTemplate.get(
					"cn.cas.iue.bean.Ssoutinrec", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<SSOutInRec> findByProperty(String key, Object value) {
		log.debug("finding SSOutInRec instance with property: " + key
				+ ", value: " + value);
		try {
			String queryString = "from SSOutInRec as model where model." + key + "= '" + value + "'";
			List<SSOutInRec> sSOutInRecs = hibernateTemplate.find(queryString);
			return sSOutInRecs;
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<SSOutInRec> findByAction(Object action) {
		return findByProperty(ACTION, action);
	}

	public List<SSOutInRec> findByQuantity(Object quantity) {
		return findByProperty(QUANTITY, quantity);
	}

	public List<SSOutInRec> findAll(final int start, final int limit) {
		log.debug("finding all SSOutInRec instances");
		try {
			final String queryString = "from SSOutInRec order by date desc";
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
			List<Long> count = hibernateTemplate.find("select COUNT(*) from SSOutInRec" );
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