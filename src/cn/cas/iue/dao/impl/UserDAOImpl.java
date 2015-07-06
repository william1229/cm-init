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
import cn.cas.iue.bean.User;
import cn.cas.iue.dao.UserDAO;

/**
 * A data access object (DAO) providing persistence and search support for User
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see cn.cas.iue.bean.User
 * @author MyEclipse Persistence Tools
 */
@Component("userDAO")
public class UserDAOImpl extends BaseDAO implements UserDAO {
	private static final Logger log = LoggerFactory.getLogger(UserDAOImpl.class);
	// property constants
	public static final String USER_NAME = "userName";
	public static final String PASSWORD = "password";
	public static final String NAME = "name";
	public static final String SEX = "sex";
	public static final String TELEPHONE = "telephone";
	public static final String EMAIL = "email";
	public static final String SCHOOL = "school";
	public static final String MAJOR = "major";
	public static final String DEGREE = "degree";
	public static final String JOB_CONTENT = "jobContent";
	private HibernateTemplate hibernateTemplate;
	
	
	//通过用户名查找用户
	public List<User> findByUserName(String userName) {
		return findByProperty(USER_NAME, userName);
	}
	//通过姓名查找用户
	public List<User> findByName(String name) {
		return findByProperty(NAME, name);
	}
	//分页查找所有用户
	public List<User> findAll(final int start, final int limit) {
		log.debug("finding all User instances");
		try {
			final String queryString = "from User";
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
	
	//分页查找所有仪器管理员
	public List<User> findAdmins(final int start, final int limit) {
		log.debug("finding all User instances");
		try {
			//9在role表中代表仪器管理员
			final String queryString = "select u from User u join u.urrels ur join ur.role r where r.roleName='仪器管理员'";
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
			System.out.println(re);
			throw re;
		}
	}
	/*public List<User> findAll() {
		log.debug("finding all User instances");
		try {
			String queryString = "from User";
			List<User> users = hibernateTemplate.find(queryString);
			return users;    
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}*/
	
	public void save(User transientInstance) {
		log.debug("saving User instance");
		try {
			hibernateTemplate.save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(User persistentInstance) {
		log.debug("deleting User instance");
		try {
			hibernateTemplate.delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	//修改用户信息
	public void updateUser(User user) throws Exception{
		try {
			hibernateTemplate.saveOrUpdate(user);
			hibernateTemplate.flush();
		} catch (RuntimeException re) {
			// TODO: handle exception
			throw re;
		} finally {
			hibernateTemplate.clear();
		}
	}
	//修改用户密码
	public void changePassword(Integer userId, String pwd) throws Exception{
		try {
			hibernateTemplate.bulkUpdate("update User set password=? where userId=?", new Object[]{pwd, userId});
		} catch (RuntimeException re) {
			// TODO: handle exception
			throw re;
		} finally {
			hibernateTemplate.clear();
		}
	}
	//判断是否用户已存在
	public boolean checkUserExistsWithUserName(String userName) throws Exception{
		try {
			List<User> users = hibernateTemplate.find("from User u where u.userName = '" + userName + "'");
			if(users != null && users.size() > 0) {
				return true;
			}
			return false;
		} catch (RuntimeException re) {
			// TODO: handle exception
			throw re;
		}
	}
	//获取用户总数
	public Long getCount() {
		try {
			List<Long> count = hibernateTemplate.find("select COUNT(*) from User");
			return count.get(0);
		} catch (RuntimeException re) {
			throw re;
		}
	}
	//获取仪器管理员总数
	public Long getCountAdmin() {
		try {
			List<Long> count = hibernateTemplate.find("select COUNT(u) from User u join u.urrels ur join ur.role r where r.roleName='仪器管理员'");
			return count.get(0);
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	public List<User> findByProperty(String key, Object value) {
		log.debug("finding User instance with property: " + key
				+ ", value: " + value);
		try {
			String queryString = "from User as model where model." + key + "= '" + value + "'";
			List<User> users = hibernateTemplate.find(queryString);
			return users;
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public User findById(Integer id) {
		log.debug("getting User instance with id: " + id);
		try {
			User instance = (User) hibernateTemplate.get("cn.cas.iue.bean.User", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	public void deleteById(Integer userId) throws Exception{
		log.debug("deleting User instance with id: " + userId);
		try {
			hibernateTemplate.bulkUpdate("delete User where userId=?", new Object[]{userId});
		} catch (RuntimeException re) {
			log.error("get failed", re);
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