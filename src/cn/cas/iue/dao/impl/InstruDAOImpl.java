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
import cn.cas.iue.bean.Consumable;
import cn.cas.iue.bean.Instru;
import cn.cas.iue.bean.User;
import cn.cas.iue.dao.InstruDAO;
import cn.cas.iue.dao.NodeDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * Instru entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see cn.cas.iue.bean.Instru
 * @author MyEclipse Persistence Tools
 */
@Component("instruDAO")
public class InstruDAOImpl extends BaseDAO implements InstruDAO {
	private static final Logger log = LoggerFactory.getLogger(InstruDAOImpl.class);
	// property constants
	public static final String INSTRU_NAME = "instruName";
	public static final String INSTRU_MODEL = "instruModel";
	public static final String INSTRU_NUM = "instruNum";
	public static final String MANUFACTURER = "manufacturer";
	public static final String COUNTRY = "country";
	public static final String DISTRIBUTOR = "distributor";
	public static final String MONEY = "money";
	public static final String LOCATION = "location";
	private HibernateTemplate hibernateTemplate;
	
	public void save(Instru transientInstance) {
		log.debug("saving Instru instance");
		try {
			hibernateTemplate.save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Instru persistentInstance) {
		log.debug("deleting Instru instance");
		try {
			hibernateTemplate.delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Instru findById(java.lang.Integer id) {
		log.debug("getting Instru instance with id: " + id);
		try {
			Instru instance = (Instru) hibernateTemplate.get("cn.cas.iue.bean.Instru", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Instru> findByProperty(String key, Object value) {
		log.debug("finding Instru instance with property: " + key
				+ ", value: " + value);
		try {
			String queryString = "from Instru as model where model." + key + "= '" + value + "'";
			List<Instru> instrus = hibernateTemplate.find(queryString);
			return instrus;
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Instru> findByInstruName(Object instruName) {
		return findByProperty(INSTRU_NAME, instruName);
	}

	public List<Instru> findByInstruModel(Object instruModel) {
		return findByProperty(INSTRU_MODEL, instruModel);
	}

	public List<Instru> findByInstruNum(Object instruNum) {
		return findByProperty(INSTRU_NUM, instruNum);
	}

	public List<Instru> findByManufacturer(Object manufacturer) {
		return findByProperty(MANUFACTURER, manufacturer);
	}

	public List<Instru> findByCountry(Object country) {
		return findByProperty(COUNTRY, country);
	}

	public List<Instru> findByDistributor(Object distributor) {
		return findByProperty(DISTRIBUTOR, distributor);
	}

	public List<Instru> findByMoney(Object money) {
		return findByProperty(MONEY, money);
	}

	public List<Instru> findByLocation(Object location) {
		return findByProperty(LOCATION, location);
	}

	public List<Instru> findAll(final int start, final int limit) {
		log.debug("finding all Instru instances");
		try {
			final String queryString = "from Instru order by consumableIsLow desc";
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
	
	public List<Instru> getByUserId(final int start, final int limit, final Integer userId) {
		try {
			final String queryString = "select i from Instru i join i.user u where u.userId = ? order by consumableIsLow desc" ;
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
	
	//获取仪器总数
	public Long getCount() {
		try {
			List<Long> count = hibernateTemplate.find("select COUNT(*) from Instru");
			return count.get(0);
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	//根据userId获取仪器总数
	public Long getCount(Integer userId) {
		try {
			List<Long> count = hibernateTemplate.find("select COUNT(i) from Instru i join i.user u where u.userId=" + userId);
			return count.get(0);
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	//修改仪器信息
	public void updateInstru(Instru instru) throws Exception{
		try {
			hibernateTemplate.saveOrUpdate(instru);
			hibernateTemplate.flush();
		} catch (RuntimeException re) {
			// TODO: handle exception
			throw re;
		} finally {
			hibernateTemplate.clear();
		}
	}
	
	public void updateConsumableIsLow(Integer instruId, boolean flag) throws Exception{
		try {
			hibernateTemplate.bulkUpdate("update Instru set consumableIsLow=? where instruId=?", new Object[]{flag, instruId});
			hibernateTemplate.flush();
		} catch (RuntimeException re) {
			// TODO: handle exception
			throw re;
		} finally {
			hibernateTemplate.clear();
		}
	}
	
	public void updateAdminName(Integer userId, Integer instruId) throws Exception{
		try {
			hibernateTemplate.bulkUpdate("update Instru set userId=? where instruId=?", new Object[]{userId, instruId});
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