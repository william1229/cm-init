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
import cn.cas.iue.bean.Report;
import cn.cas.iue.dao.ReportDAO;
import cn.cas.iue.dao.SampleDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * Report entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see cn.cas.iue.bean.Report
 * @author MyEclipse Persistence Tools
 */
@Component("reportDAO")
public class ReportDAOImpl extends BaseDAO implements ReportDAO{
	private static final Logger log = LoggerFactory.getLogger(ReportDAOImpl.class);
	// property constants
	public static final String REPORT = "report";
	private HibernateTemplate hibernateTemplate;

	public void save(Report transientInstance) {
		log.debug("saving Report instance");
		try {
			hibernateTemplate.save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Report persistentInstance) {
		log.debug("deleting Report instance");
		try {
			hibernateTemplate.delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Report findById(java.lang.Integer id) {
		log.debug("getting Report instance with id: " + id);
		try {
			Report instance = (Report) hibernateTemplate.get(
					"cn.cas.iue.bean.Report", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Report> findByProperty(String key, Object value) {
		log.debug("finding AppraisalRec instance with property: " + key
				+ ", value: " + value);
		try {
			String queryString = "from Report as model where model." + key + "= '" + value + "'";
			List<Report> reports = hibernateTemplate.find(queryString);
			return reports;
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Report> findByReport(Object report) {
		return findByProperty(REPORT, report);
	}

	public List<Report> findAll(final int start, final int limit) {
		log.debug("finding all Report instances");
		try {
			final String queryString = "from Report";
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
	
	public void deleteById(Integer reportId) throws Exception{
		log.debug("deleting Report instance with id: " + reportId);
		try {
			hibernateTemplate.bulkUpdate("delete Report where reportId=?", new Object[]{reportId});
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public Long getCount(){
		try {
			List<Long> count = hibernateTemplate.find("select COUNT(*) from Report" );
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