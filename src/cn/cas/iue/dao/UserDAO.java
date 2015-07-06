package cn.cas.iue.dao;

import java.util.List;

import cn.cas.iue.bean.User;

public interface UserDAO {
	
	public void save(User user);
	public void delete(User user);
	public List<User> findByUserName(String userName);
	public List<User> findByName(String name);
	public User findById(Integer id);
	public List<User> findAll(int start, int limit);
//	public List<User> findAll();
	public Long getCount();
	public void updateUser(User user) throws Exception;
	public void changePassword(Integer userId, String pwd) throws Exception;
	public boolean checkUserExistsWithUserName(String userName) throws Exception;
	public void deleteById(Integer userId) throws Exception;
	public List<User> findAdmins(final int start, final int limit);
	public Long getCountAdmin();
}
