package cn.cas.iue.service;

import java.util.List;

import cn.cas.iue.bean.User;

public interface UserService {
	public User getUserByUserName(String userName);
	public User getUserById(Integer userId);
	public void addUser(User user) throws Exception;
	public List<User> getUsers(int start, int limit);
	public Long getTotal();
	public void updateUserInfo(User user) throws Exception;
	public boolean isExists(String userName) throws Exception;
	public void resetPassword(Integer userId, String pwd) throws Exception;
	public void deleteUser(Integer userId) throws Exception;
	public List<User> getAdmins(int start, int limit);
	public Long getTotalAdmin();
}