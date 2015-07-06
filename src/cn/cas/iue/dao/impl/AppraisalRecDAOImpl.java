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
import cn.cas.iue.bean.AppraisalRec;
import cn.cas.iue.bean.Consumable;
import cn.cas.iue.dao.AppraisalRecDAO;
import cn.cas.iue.dao.ConsumableDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * AppraisalRec entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.cas.iue.bean.AppraisalRec
 * @author MyEclipse Persistence Tools
 */
@Component("appraisalDAO")
public class AppraisalRecDAOImpl extends BaseDAO implements AppraisalRecDAO{
	private static final Logger log = LoggerFactory
			.getLogger(AppraisalRecDAOImpl.class);
	// property constants
	public static final String PATH = "path";
	private HibernateTemplate hibernateTemplate;

	public void save(AppraisalRec transientInstance) {
		log.debug("saving AppraisalRec instance");
		try {
			hibernateTemplate.save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(AppraisalRec persistentInstance) {
		log.debug("deleting AppraisalRec instance");
		try {
			hibernateTemplate.delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public AppraisalRec findById(java.lang.Integer id) {
		log.debug("getting AppraisalRec instance with id: " + id);
		try {
			AppraisalRec instance = (AppraisalRec) hibernateTemplate.get(
					"cn.cas.iue.bean.AppraisalRec", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<AppraisalRec> findByProperty(String key, Object value) {
		log.debug("finding AppraisalRec instance with property: " + key
				+ ", value: " + value);
		try {
			String queryString = "from AppraisalRec as model where model." + key + "= '" + value + "'";
			List<AppraisalRec> appraisalRecs = hibernateTemplate.find(queryString);
			return appraisalRecs;
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<AppraisalRec> findByPath(Object path) {
		return findByProperty(PATH, path);
	}

	public List<AppraisalRec> findAll(final int start, final int limit) {
		log.debug("finding all AppraisalRec instances");
		try {
			final String queryString = "from AppraisalRec order by date desc";
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
			List<Long> count = hibernateTemplate.find("select COUNT(*) from AppraisalRec" );
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