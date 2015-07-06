package cn.cas.iue.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
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
import cn.cas.iue.bean.Consumable;
import cn.cas.iue.bean.Instru;
import cn.cas.iue.bean.User;
import cn.cas.iue.dao.ConsumableDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * Consumable entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see cn.cas.iue.bean.Consumable
 * @author MyEclipse Persistence Tools
 */
@Component("consumableDAO")
public class ConsumableDAOImpl extends BaseDAO implements ConsumableDAO {
	private static final Logger log = LoggerFactory
			.getLogger(ConsumableDAOImpl.class);
	// property constants
	public static final String CONSUMABLE_NAME = "consumableName";
	public static final String QUANTITY = "quantity";
	private HibernateTemplate hibernateTemplate;
	
	
	public void save(Consumable transientInstance) {
		log.debug("saving Consumable instance");
		try {
			hibernateTemplate.save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Consumable persistentInstance) {
		log.debug("deleting Consumable instance");
		try {
			hibernateTemplate.delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Consumable findById(java.lang.Integer id) {
		log.debug("getting Consumable instance with id: " + id);
		try {
			Consumable instance = (Consumable) hibernateTemplate.get("cn.cas.iue.bean.Consumable", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public List<Consumable> findIConsumables(final int start, final int limit, final Integer instruId) {
		try {
			final String queryString = "select c from Consumable c join c.instru i where i.instruId = ? order by quantity asc" ;
			List list = hibernateTemplate.executeFind(new HibernateCallback() {     
				public Object doInHibernate(Session session)throws HibernateException, SQLException {     
					Query query = session.createQuery(queryString);
					query.setParameter(0, instruId);
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
	
	public List<Consumable> findByProperty(String key, Object value) {
		log.debug("finding Consumable instance with property: " + key
				+ ", value: " + value);
		try {
			String queryString = "from Consumable as model where model." + key + "= '" + value + "'";
			List<Consumable> consumables = hibernateTemplate.find(queryString);
			return consumables;
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Consumable> findByConsumableName(final int start, final int limit, Integer instruId, String query) {
		log.debug("finding all Consumable instances");
		try {
			final String queryString;
			if(instruId == null) {
				queryString = "from Consumable where instruId is null and consumableName like '%"+ query + "%'";
			} else {
				queryString = "from Consumable where instruId =" + instruId + "and consumableName like '%"+ query + "%'";
			}
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
	
	public List<Consumable> findByQuantity(Object quantity) {
		return findByProperty(QUANTITY, quantity);
	}

	public List<Consumable> findAll(final int start, final int limit) {
		log.debug("finding all Consumable instances");
		try {
			final String queryString = "from Consumable order by quantity asc";
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
	
	public List<Consumable> findPConsumables(final int start, final int limit) {
		log.debug("finding all Consumable instances");
		try {
			final String queryString = "select c from Consumable c left join c.instru i where i is null order by quantity asc";
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
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	/* (non-Javadoc)
	 * @see cn.cas.iue.dao.ConsumableDAO#getCount()
	 */
	@Override
	public Long getICCount(Integer instruId) {
		// TODO Auto-generated method stub
		try {
			List<Long> count = hibernateTemplate.find("select COUNT(*) from Consumable c join c.instru i where i.instruId=" + instruId);
			return count.get(0);
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	public Long getPCCount(){
		try {
			List<Long> count = hibernateTemplate.find("select COUNT(*) from Consumable c left join c.instru i where i is null" );
			return count.get(0);
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	/* (non-Javadoc)
	 * @see cn.cas.iue.dao.ConsumableDAO#updateConsumable(cn.cas.iue.bean.Consumable)
	 */
	@Override
	public void updateConsumable(Consumable consumable) throws Exception {
		// TODO Auto-generated method stub
		try {
			hibernateTemplate.saveOrUpdate(consumable);
			hibernateTemplate.flush();
		} catch (RuntimeException re) {
			// TODO: handle exception
			throw re;
		} finally {
			hibernateTemplate.clear();
		}
	}
	//出入库更新
	public void updateQuantity(Integer quantity, Integer consumableId) throws Exception {
		// TODO Auto-generated method stub
		try {
			hibernateTemplate.bulkUpdate("update Consumable set quantity=? where consumableId=?", new Object[]{quantity, consumableId});
			hibernateTemplate.flush();
		} catch (RuntimeException re) {
			// TODO: handle exception
			throw re;
		} finally {
			hibernateTemplate.clear();
		}
	}

	public boolean hasLow(Integer instruId, int limit) {
		try {
			List<Long> counts = hibernateTemplate.find("select count(*) from Consumable c join c.instru i where c.quantity<=" + limit + " and i.instruId=" + instruId);
			if(counts.get(0).intValue() > 0) {
				return true;
			} else{
				return false;
			}
		} catch (RuntimeException re) {
			// TODO: handle exception
			System.out.println(re);
			throw re;
		}
	}
	
	public void deleteById(Integer consumableId) {
		log.debug("deleting Consumable instance with id: " + consumableId);
		try {
			hibernateTemplate.bulkUpdate("delete Consumable where consumableId=?", new Object[]{consumableId});
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
/*	public List<Consumable> getLowConsumable(int limit) {
		List<Consumable> consumables = hibernateTemplate.find("from Consumable where quantity<" + limit);
		return consumables;
	}*/



}