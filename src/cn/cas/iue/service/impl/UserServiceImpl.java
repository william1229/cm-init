package cn.cas.iue.service.impl;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.cas.iue.bean.User;
import cn.cas.iue.dao.UserDAO;
import cn.cas.iue.service.UserService;

@Component("userService")
public class UserServiceImpl implements UserService {
	private UserDAO userDAO;
	
	public UserDAO getUserDao() {
		return userDAO;
	}
	
	@Resource
	public void setUserDao(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public User getUserByUserName(String userName) {
		List<User> users = this.userDAO.findByUserName(userName);
		if(users.size() != 0) {
			User user = this.userDAO.findByUserName(userName).get(0);
			return user;
		} else {
			return null;
		}
	}
	
	public List<User> getUsers(int start, int limit) {
		return userDAO.findAll(start,limit);
	}
	
	public List<User> getAdmins(int start, int limit) {
		return userDAO.findAdmins(start,limit);
	}
	
	public void addUser(User user) throws Exception{
		userDAO.save(user);
	}
	
	public boolean isExists(String userName) throws Exception {
		return userDAO.checkUserExistsWithUserName(userName);
	}
	
	public Long getTotal() {
		return userDAO.getCount();
	}
	public Long getTotalAdmin() {
		return userDAO.getCountAdmin();
	}
	public void resetPassword(Integer userId, String pwd) throws Exception{
		userDAO.changePassword(userId, pwd);
	}

	/* (non-Javadoc)
	 * @see cn.cas.iue.service.UserService#changeUserInfo(cn.cas.iue.bean.User)
	 */
	@Override
	public void updateUserInfo(User user) throws Exception {
		userDAO.updateUser(user);
	}

	/* (non-Javadoc)
	 * @see cn.cas.iue.service.UserService#getUserById(int)
	 */
	@Override
	public User getUserById(Integer userId) {
		// TODO Auto-generated method stub
		return userDAO.findById(userId);
	}
	
	public void deleteUser(Integer userId) throws Exception{
		userDAO.deleteById(userId);
	}
}
