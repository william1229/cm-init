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
import cn.cas.iue.bean.SSample;
import cn.cas.iue.dao.SSampleDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * SSample entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see cn.cas.iue.bean.SSample
 * @author MyEclipse Persistence Tools
 */
@Component("SSampleDAO")
public class SSampleDAOImpl extends BaseDAO implements SSampleDAO {
	private static final Logger log = LoggerFactory.getLogger(SSampleDAOImpl.class);
	// property constants
	public static final String STANDARD_SN = "standardSN";
	public static final String SSAMPLE_NAME = "sSampleName";
	public static final String SAMPLE_SN = "sampleSN";
	public static final String STANDARD_VALUE = "standardValue";
	public static final String RELATIVE_UNCERTAINTY = "relativeUncertainty";
	public static final String SPECIFICATION = "specification";
	public static final String SOURCE = "source";
	public static final String STORAGE_REQUIREMENTS = "storageRequirements";
	public static final String QUANTITY = "quantity";
	private HibernateTemplate hibernateTemplate;

	public void save(SSample transientInstance) {
		log.debug("saving SSample instance");
		try {
			hibernateTemplate.save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(SSample persistentInstance) {
		log.debug("deleting SSample instance");
		try {
			hibernateTemplate.delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public SSample findById(java.lang.Integer id) {
		log.debug("getting SSample instance with id: " + id);
		try {
			SSample instance = (SSample) hibernateTemplate.get(
					"cn.cas.iue.bean.SSample", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			System.out.println(re);
			throw re;
		}
	}

	public List<SSample> findByProperty(String key, Object value) {
		log.debug("finding SSample instance with property: " + key
				+ ", value: " + value);
		try {
			String queryString = "from SSample as model where model." + key + "= '" + value + "'";
			List<SSample> sSamples = hibernateTemplate.find(queryString);
			return sSamples;
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<SSample> findByStandardSN(Object standardSN) {
		return findByProperty(STANDARD_SN, standardSN);
	}

	public List<SSample> findBySSampleName(Object sSampleName) {
		return findByProperty(SSAMPLE_NAME, sSampleName);
	}

	public List<SSample> findBySampleSN(Object sampleSN) {
		return findByProperty(SAMPLE_SN, sampleSN);
	}

	public List<SSample> findByStandardValue(Object standardValue) {
		return findByProperty(STANDARD_VALUE, standardValue);
	}

	public List<SSample> findByRelativeUncertainty(Object relativeUncertainty) {
		return findByProperty(RELATIVE_UNCERTAINTY, relativeUncertainty);
	}

	public List<SSample> findBySpecification(Object specification) {
		return findByProperty(SPECIFICATION, specification);
	}

	public List<SSample> findBySource(Object source) {
		return findByProperty(SOURCE, source);
	}

	public List<SSample> findByStorageRequirements(Object storageRequirements) {
		return findByProperty(STORAGE_REQUIREMENTS, storageRequirements);
	}

	public List<SSample> findByQuantity(Object quantity) {
		return findByProperty(QUANTITY, quantity);
	}

	public List<SSample> findAll(final int start, final int limit) {
		log.debug("finding all SSample instances");
		try {
			final String queryString = "from SSample";
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
			List<Long> count = hibernateTemplate.find("select COUNT(*) from SSample" );
			return count.get(0);
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	//出入库更新
	public void updateQuantity(double quantity, Integer sSampleId) throws Exception {
		// TODO Auto-generated method stub
		try {
			hibernateTemplate.bulkUpdate("update SSample set quantity=? where sSampleId=?", new Object[]{quantity, sSampleId});
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