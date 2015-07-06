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
import cn.cas.iue.bean.TrainingRec;
import cn.cas.iue.bean.User;
import cn.cas.iue.dao.TrainingRecDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * TrainingRec entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.cas.iue.bean.TrainingRec
 * @author MyEclipse Persistence Tools
 */
@Component("TrainingRecDAO")
public class TrainingRecDAOImpl extends BaseDAO implements TrainingRecDAO{
	private static final Logger log = LoggerFactory
			.getLogger(TrainingRecDAOImpl.class);
	// property constants
	public static final String NAME = "name";
	public static final String CONTENT = "content";
	public static final String PATH = "path";
	private HibernateTemplate hibernateTemplate;

	public void save(TrainingRec transientInstance) {
		log.debug("saving TrainingRec instance");
		try {
			hibernateTemplate.save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TrainingRec persistentInstance) {
		log.debug("deleting TrainingRec instance");
		try {
			hibernateTemplate.delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TrainingRec findById(java.lang.Integer id) {
		log.debug("getting TrainingRec instance with id: " + id);
		try {
			TrainingRec instance = (TrainingRec) hibernateTemplate.get(
					"cn.cas.iue.bean.TrainingRec", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<TrainingRec> findByProperty(String key, Object value) {
		log.debug("finding TrainingRec instance with property: " + key
				+ ", value: " + value);
		try {
			String queryString = "from TrainingRec as model where model." + key + "= '" + value + "'";
			List<TrainingRec> trainingRecs = hibernateTemplate.find(queryString);
			return trainingRecs;
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<TrainingRec> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List<TrainingRec> findByContent(Object content) {
		return findByProperty(CONTENT, content);
	}

	public List<TrainingRec> findByPath(Object path) {
		return findByProperty(PATH, path);
	}

	public List<TrainingRec> findAll(final int start, final int limit) {
		log.debug("finding all TrainingRec instances");
		try {
			final String queryString = "from TrainingRec order by startDate desc";
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
			System.out.println(re);
			throw re;
		}
	}
	
	//获取记录总数
	public Long getCount() {
		try {
			List<Long> count = hibernateTemplate.find("select COUNT(*) from TrainingRec");
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