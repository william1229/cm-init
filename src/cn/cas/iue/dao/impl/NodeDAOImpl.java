package cn.cas.iue.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import cn.cas.iue.base.BaseDAO;
import cn.cas.iue.bean.Node;
import cn.cas.iue.dao.NodeDAO;

/**
 * A data access object (DAO) providing persistence and search support for Node
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see cn.cas.iue.bean.Node
 * @author MyEclipse Persistence Tools
 */
@Component("nodeDAO")
public class NodeDAOImpl extends BaseDAO implements NodeDAO{
	private static final Logger log = LoggerFactory.getLogger(NodeDAOImpl.class);
	// property constants
	public static final String ID = "id";
	public static final String TEXT = "text";
	public static final String LEAF = "leaf";
	public static final String PID = "pid";
	private HibernateTemplate hibernateTemplate;
	
	public List<Node> getNode(Integer userId, String treeId) {
		try {
			List<Node> Nodes = hibernateTemplate.find("select n from Node n join n.rnrels rn join rn.role r join r.urrels ur join ur.user u where u.userId=" + userId + " and n.treeId='" + treeId +"'");
			hibernateTemplate.clear();
			return Nodes;
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	public List<Node> getChildrenNode(Integer nodeId) {
		try {
			List<Node> Nodes = hibernateTemplate.find("from Node n where n.pid=" + nodeId);
			hibernateTemplate.clear();
			return Nodes;
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	public Node findById(Integer id) {
		log.debug("getting Node instance with id: " + id);
		try {
			Node instance = (Node) hibernateTemplate.get("cn.cas.iue.bean.Node", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	/*	public void save(Node transientInstance) {
		log.debug("saving Node instance");
		try {
			hibernateTemplate.save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Node persistentInstance) {
		log.debug("deleting Node instance");
		try {
			hibernateTemplate.delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public List<Node> findByExample(Node instance) {
		log.debug("finding Node instance by example");
		try {
			List<Node> results = (List<Node>) hibernateTemplate
					.createCriteria("cn.cas.iue.bean.Node")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Node instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Node as model where model."
					+ propertyName + "= ?";
			Query queryObject = hibernateTemplate.createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Node> findById(Object id) {
		return findByProperty(ID, id);
	}

	public List<Node> findByText(Object text) {
		return findByProperty(TEXT, text);
	}

	public List<Node> findByLeaf(Object leaf) {
		return findByProperty(LEAF, leaf);
	}

	public List<Node> findByPid(Object pid) {
		return findByProperty(PID, pid);
	}

	public List findAll() {
		log.debug("finding all Node instances");
		try {
			String queryString = "from Node";
			Query queryObject = hibernateTemplate.createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Node merge(Node detachedInstance) {
		log.debug("merging Node instance");
		try {
			Node result = (Node) hibernateTemplate.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Node instance) {
		log.debug("attaching dirty Node instance");
		try {
			hibernateTemplate.saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Node instance) {
		log.debug("attaching clean Node instance");
		try {
			hibernateTemplate.lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}*/
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
}