package cn.cas.iue.dao.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
import cn.cas.iue.bean.Project;
import cn.cas.iue.dao.ProjectDAO;
import cn.cas.iue.dao.PurchasingRecDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * Project entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see cn.cas.iue.bean.Project
 * @author MyEclipse Persistence Tools
 */
@Component("projectDAO")
public class ProjectDAOImpl extends BaseDAO implements ProjectDAO {
	private static final Logger log = LoggerFactory.getLogger(ProjectDAOImpl.class);
	// property constants
	public static final String PROJECT_SN = "projectSn";
	public static final String PROJECT_NAME = "projectName";
	public static final String UNIT = "unit";
	public static final String TELEPHONE = "telephone";
	public static final String MONEY = "money";
	public static final String CONTRACT = "contract";
	public static final String REPORT = "report";
	private HibernateTemplate hibernateTemplate;

	public void save(Project transientInstance) {
		log.debug("saving Project instance");
		try {
			hibernateTemplate.save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Project persistentInstance) {
		log.debug("deleting Project instance");
		try {
			hibernateTemplate.delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Project findById(java.lang.Integer id) {
		log.debug("getting Project instance with id: " + id);
		try {
			Project instance = (Project) hibernateTemplate.get(
					"cn.cas.iue.bean.Project", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Project> findByProperty(String key, Object value) {
		log.debug("finding Project instance with property: " + key
				+ ", value: " + value);
		try {
			String queryString = "from AppraisalRec as model where model." + key + "= '" + value + "'";
			List<Project> projects = hibernateTemplate.find(queryString);
			return projects;
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Project> findByProjectSn(Object projectSn) {
		return findByProperty(PROJECT_SN, projectSn);
	}

	public List<Project> findByProjectName(Object projectName) {
		return findByProperty(PROJECT_NAME, projectName);
	}

	public List<Project> findByUnit(Object unit) {
		return findByProperty(UNIT, unit);
	}

	public List<Project> findByTelephone(Object telephone) {
		return findByProperty(TELEPHONE, telephone);
	}

	public List<Project> findByMoney(Object money) {
		return findByProperty(MONEY, money);
	}

	public List<Project> findByContract(Object contract) {
		return findByProperty(CONTRACT, contract);
	}

	public List<Project> findByReport(Object report) {
		return findByProperty(REPORT, report);
	}

	public List<Project> findAll(final int start, final int limit) {
		log.debug("finding all Project instances");
		try {
			final String queryString = "from Project order by projectSN desc";
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
			List<Long> count = hibernateTemplate.find("select COUNT(*) from Project" );
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