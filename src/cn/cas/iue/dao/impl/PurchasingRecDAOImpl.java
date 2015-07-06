package cn.cas.iue.dao.impl;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import cn.cas.iue.base.BaseDAO;
import cn.cas.iue.bean.PurchasingRec;
import cn.cas.iue.dao.PurchasingRecDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * Purchasingrec entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.cas.iue.bean.PurchasingRec
 * @author MyEclipse Persistence Tools
 */
@Component("purchasingRecDAO")
public class PurchasingRecDAOImpl extends BaseDAO implements PurchasingRecDAO {
	private static final Logger log = LoggerFactory
			.getLogger(PurchasingRecDAOImpl.class);
	// property constants
	public static final String QUANTITY = "quantity";
	public static final String REMARK = "remark";
	public static final String STATE = "state";
	private HibernateTemplate hibernateTemplate;

	public void save(PurchasingRec transientInstance) {
		log.debug("saving Purchasingrec instance");
		try {
			hibernateTemplate.save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(PurchasingRec persistentInstance) {
		log.debug("deleting Purchasingrec instance");
		try {
			hibernateTemplate.delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public PurchasingRec findById(java.lang.Integer id) {
		log.debug("getting PurchasingRec instance with id: " + id);
		try {
			PurchasingRec instance = (PurchasingRec) hibernateTemplate.get("cn.cas.iue.bean.PurchasingRec", id);
			return instance;
		} catch (RuntimeException re) {
			System.out.println("=================================" + re);
			log.error("get failed", re);
			throw re;
		}
	}

	public List<PurchasingRec> findByProperty(String key, Object value) {
		log.debug("finding PurchasingRec instance with property: " + key
				+ ", value: " + value);
		try {
			String queryString = "from PurchasingRec as model where model." + key + "= '" + value + "'";
			List<PurchasingRec> purchasingRecs = hibernateTemplate.find(queryString);
			return purchasingRecs;
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<PurchasingRec> findByQuantity(Object quantity) {
		return findByProperty(QUANTITY, quantity);
	}

	public List<PurchasingRec> findByRemark(Object remark) {
		return findByProperty(REMARK, remark);
	}

	public List<PurchasingRec> findByState(Object state) {
		return findByProperty(STATE, state);
	}
	//查找所有记录
	public List<PurchasingRec> findAll(final int start, final int limit) {
		log.debug("finding all PurchasingRec instances");
		try {
			final String queryString = "from PurchasingRec order by date desc";
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
			List<Long> count = hibernateTemplate.find("select COUNT(*) from PurchasingRec");
			return count.get(0);
		} catch (RuntimeException re) {
			throw re;
		}
	}
	//根据userId获取申请采购记录总数
	public Long getCount(Integer userId) {
		try {
			List<Long> count = hibernateTemplate.find("select COUNT(p) from PurchasingRec p join p.user u where u.userId=" + userId);
			return count.get(0);
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	//根据state获取申请采购记录总数
	public Long getCount(String state) {
		try {
			List<Long> count = hibernateTemplate.find("select COUNT(*) from PurchasingRec where state='" + state +"'");
			return count.get(0);
		} catch (RuntimeException re) {
			throw re;
		}
	}
	//根据userId查找记录
	public List<PurchasingRec> findAll(final int start, final int limit, final Integer userId) {
		try {
			final String queryString = "select p from PurchasingRec p join p.user u where u.userId = ? order by date desc" ;
			List list = hibernateTemplate.executeFind(new HibernateCallback() {     
				public Object doInHibernate(Session session)throws HibernateException, SQLException {     
					Query query = session.createQuery(queryString);
					query.setParameter(0, userId);
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
	//根据state查找记录
	public List<PurchasingRec> findAll(final int start, final int limit,final String state) {
		try {
			final String queryString = "from PurchasingRec where state = ? order by date desc" ;
			List list = hibernateTemplate.executeFind(new HibernateCallback() {     
				public Object doInHibernate(Session session)throws HibernateException, SQLException {     
					Query query = session.createQuery(queryString);
					query.setParameter(0, state);
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
	//更新state
	public void updateState(Integer recId, String state) throws Exception{
		try {
			hibernateTemplate.bulkUpdate("update PurchasingRec set state=? where recId=?", new Object[]{state, recId});
			hibernateTemplate.flush();
		} catch (RuntimeException re) {
			// TODO: handle exception
			throw re;
		} finally {
			hibernateTemplate.clear();
		}
	}
	
	public void updatePurchasingRec(PurchasingRec purchasingRec) throws Exception{
		try {
			hibernateTemplate.saveOrUpdate(purchasingRec);
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