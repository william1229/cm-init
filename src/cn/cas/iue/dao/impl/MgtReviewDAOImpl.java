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
import cn.cas.iue.bean.MgtReview;
import cn.cas.iue.dao.AppraisalRecDAO;
import cn.cas.iue.dao.MgtReviewDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * Mgtreview entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see cn.cas.iue.bean.MgtReview
 * @author MyEclipse Persistence Tools
 */
@Component("mgtReviewDAO")
public class MgtReviewDAOImpl extends BaseDAO implements MgtReviewDAO{
	private static final Logger log = LoggerFactory
			.getLogger(MgtReviewDAOImpl.class);
	// property constants
	public static final String PATH = "path";
	private HibernateTemplate hibernateTemplate;

	public void save(MgtReview transientInstance) {
		log.debug("saving Mgtreview instance");
		try {
			hibernateTemplate.save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(MgtReview persistentInstance) {
		log.debug("deleting Mgtreview instance");
		try {
			hibernateTemplate.delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public MgtReview findById(java.lang.Integer id) {
		log.debug("getting Mgtreview instance with id: " + id);
		try {
			MgtReview instance = (MgtReview) hibernateTemplate.get(
					"cn.cas.iue.bean.Mgtreview", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<MgtReview> findByProperty(String key, Object value) {
		log.debug("finding MgtReview instance with property: " + key
				+ ", value: " + value);
		try {
			String queryString = "from MgtReview as model where model." + key + "= '" + value + "'";
			List<MgtReview> mgtReviews = hibernateTemplate.find(queryString);
			return mgtReviews;
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<MgtReview> findByPath(Object path) {
		return findByProperty(PATH, path);
	}

	public List<MgtReview> findAll(final int start, final int limit) {
		log.debug("finding all MgtReview instances");
		try {
			final String queryString = "from MgtReview order by date desc";
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
			List<Long> count = hibernateTemplate.find("select COUNT(*) from MgtReview");
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