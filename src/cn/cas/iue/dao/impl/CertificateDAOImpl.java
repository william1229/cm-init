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
import cn.cas.iue.bean.Consumable;
import cn.cas.iue.dao.CertificateDAO;
import cn.cas.iue.dao.ConsumableDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * Certificate entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.cas.iue.bean.Certificate
 * @author MyEclipse Persistence Tools
 */
@Component("certificateDAO")
public class CertificateDAOImpl extends BaseDAO implements CertificateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(CertificateDAOImpl.class);
	// property constants
	public static final String CERTIFICATE_NAME = "certificateName";
	public static final String TYPE = "type";
	public static final String AUTHORITY = "authority";
	private HibernateTemplate hibernateTemplate;

	public void save(Certificate transientInstance) {
		log.debug("saving Certificate instance");
		try {
			hibernateTemplate.save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Certificate persistentInstance) {
		log.debug("deleting Certificate instance");
		try {
			hibernateTemplate.delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Certificate findById(java.lang.Integer id) {
		log.debug("getting Certificate instance with id: " + id);
		try {
			Certificate instance = (Certificate) hibernateTemplate.get(
					"cn.cas.iue.bean.Certificate", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Certificate> findByProperty(String key, Object value) {
		log.debug("finding Certificate instance with property: " + key
				+ ", value: " + value);
		try {
			String queryString = "from Certificate as model where model." + key + "= '" + value + "'";
			List<Certificate> certificates = hibernateTemplate.find(queryString);
			return certificates;
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Certificate> findByCertificateName(Object certificateName) {
		return findByProperty(CERTIFICATE_NAME, certificateName);
	}

	public List<Certificate> findByType(Object type) {
		return findByProperty(TYPE, type);
	}

	public List<Certificate> findByAuthority(Object authority) {
		return findByProperty(AUTHORITY, authority);
	}

	public List<Certificate> findAll(final int start, final int limit) {
		log.debug("finding all Certificate instances");
		try {
			final String queryString = "from Certificate";
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
			List<Long> count = hibernateTemplate.find("select COUNT(*) from Certificate" );
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