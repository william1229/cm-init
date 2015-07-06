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
import cn.cas.iue.bean.OutInRec;
import cn.cas.iue.bean.User;
import cn.cas.iue.dao.OutInRecDAO;
import cn.cas.iue.dao.TrainingRecDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * Outinrec entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see cn.cas.iue.bean.OutInRec
 * @author MyEclipse Persistence Tools
 */
@Component("outInRecDAO")
public class OutInRecDAOImpl extends BaseDAO implements OutInRecDAO{
	private static final Logger log = LoggerFactory
			.getLogger(OutInRecDAOImpl.class);
	// property constants
	public static final String INSTRU_NAME = "instruName";
	public static final String CONSUMABLE_NAME = "consumableName";
	public static final String CONSUMABLE_TYPE = "consumableType";
	public static final String QUANTITY = "quantity";
	private HibernateTemplate hibernateTemplate;

	public void save(OutInRec transientInstance) {
		log.debug("saving Outinrec instance");
		try {
			hibernateTemplate.save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(OutInRec persistentInstance) {
		log.debug("deleting Outinrec instance");
		try {
			hibernateTemplate.delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public OutInRec findById(java.lang.Integer id) {
		log.debug("getting Outinrec instance with id: " + id);
		try {
			OutInRec instance = (OutInRec) hibernateTemplate.get(
					"cn.cas.iue.bean.Outinrec", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<OutInRec> findByProperty(String key, Object value) {
		log.debug("finding OutInRec instance with property: " + key
				+ ", value: " + value);
		try {
			String queryString = "from OutInRec as model where model." + key + "= '" + value + "'";
			List<OutInRec> outInRecs = hibernateTemplate.find(queryString);
			return outInRecs;
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<OutInRec> findByInstruName(Object instruName) {
		return findByProperty(INSTRU_NAME, instruName);
	}

	public List<OutInRec> findByConsumableName(Object consumableName) {
		return findByProperty(CONSUMABLE_NAME, consumableName);
	}

	public List<OutInRec> findByConsumableType(Object consumableType) {
		return findByProperty(CONSUMABLE_TYPE, consumableType);
	}

	public List<OutInRec> findByQuantity(Object quantity) {
		return findByProperty(QUANTITY, quantity);
	}

	public List<OutInRec> findAll(final int start, final int limit) {
		log.debug("finding all OutInRec instances");
		try {
			final String queryString = "from OutInRec order by date desc";
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
	
	//获取记录总数
	public Long getCount() {
		try {
			List<Long> count = hibernateTemplate.find("select COUNT(*) from OutInRec");
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