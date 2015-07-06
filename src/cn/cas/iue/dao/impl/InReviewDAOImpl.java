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
import cn.cas.iue.bean.ExReview;
import cn.cas.iue.bean.InReview;
import cn.cas.iue.dao.AppraisalRecDAO;
import cn.cas.iue.dao.InReviewDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * Inreview entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see cn.cas.iue.bean.InReview
 * @author MyEclipse Persistence Tools
 */
@Component("inReviewDAO")
public class InReviewDAOImpl extends BaseDAO implements InReviewDAO {
	private static final Logger log = LoggerFactory
			.getLogger(InReviewDAOImpl.class);
	// property constants
	public static final String PATH = "path";
	private HibernateTemplate hibernateTemplate;

	public void save(InReview transientInstance) {
		log.debug("saving Inreview instance");
		try {
			hibernateTemplate.save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(InReview persistentInstance) {
		log.debug("deleting Inreview instance");
		try {
			hibernateTemplate.delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public InReview findById(java.lang.Integer id) {
		log.debug("getting Inreview instance with id: " + id);
		try {
			InReview instance = (InReview) hibernateTemplate.get(
					"cn.cas.iue.bean.Inreview", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<InReview> findByProperty(String key, Object value) {
		log.debug("finding InReview instance with property: " + key
				+ ", value: " + value);
		try {
			String queryString = "from InReview as model where model." + key + "= '" + value + "'";
			List<InReview> inReviews = hibernateTemplate.find(queryString);
			return inReviews;
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<InReview> findByPath(Object path) {
		return findByProperty(PATH, path);
	}

	public List<InReview> findAll(final int start, final int limit) {
		log.debug("finding all InReview instances");
		try {
			final String queryString = "from InReview order by date desc";
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
			List<Long> count = hibernateTemplate.find("select COUNT(*) from InReview");
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