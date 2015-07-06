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
import cn.cas.iue.bean.Project;
import cn.cas.iue.bean.Sample;
import cn.cas.iue.dao.PurchasingRecDAO;
import cn.cas.iue.dao.SampleDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * Sample entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see cn.cas.iue.bean.Sample
 * @author MyEclipse Persistence Tools
 */
@Component("sampleDAO")
public class SampleDAOImpl extends BaseDAO implements SampleDAO {
	private static final Logger log = LoggerFactory.getLogger(SampleDAOImpl.class);
	// property constants
	public static final String SAMPLE_SN = "sampleSn";
	public static final String STATE = "state";
	public static final String QUANTITY = "quantity";
	public static final String UNIT = "unit";
	public static final String NAME = "name";
	public static final String TELEPHONE = "telephone";
	private HibernateTemplate hibernateTemplate;
	
	public void save(Sample transientInstance) {
		log.debug("saving Sample instance");
		try {
			hibernateTemplate.save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			System.out.println(re);
			throw re;
		}
	}

	public void delete(Sample persistentInstance) {
		log.debug("deleting Sample instance");
		try {
			hibernateTemplate.delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Sample findById(java.lang.Integer id) {
		log.debug("getting Sample instance with id: " + id);
		try {
			Sample instance = (Sample) hibernateTemplate.get(
					"cn.cas.iue.bean.Sample", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Sample> findByProperty(String key, Object value) {
		log.debug("finding Sample instance with property: " + key
				+ ", value: " + value);
		try {
			String queryString = "from Sample as model where model." + key + "= '" + value + "'";
			List<Sample> samples = hibernateTemplate.find(queryString);
			return samples;
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Sample> findBySampleSn(Object sampleSn) {
		return findByProperty(SAMPLE_SN, sampleSn);
	}

	public List<Sample> findByState(Object state) {
		return findByProperty(STATE, state);
	}

	public List<Sample> findByQuantity(Object quantity) {
		return findByProperty(QUANTITY, quantity);
	}

	public List<Sample> findByUnit(Object unit) {
		return findByProperty(UNIT, unit);
	}

	public List<Sample> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List<Sample> findByTelephone(Object telephone) {
		return findByProperty(TELEPHONE, telephone);
	}

	public List<Sample> findAll(final int start, final int limit) {
		log.debug("finding all Sample instances");
		try {
			final String queryString = "from Sample order by sampleSN desc";
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
			List<Long> count = hibernateTemplate.find("select COUNT(*) from Sample" );
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