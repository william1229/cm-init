/**
 * 
 */
package cn.cas.iue.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.cas.iue.base.BaseAction;
import cn.cas.iue.bean.Role;
import cn.cas.iue.bean.Urrel;
import cn.cas.iue.bean.User;
import cn.cas.iue.common.Global;
import cn.cas.iue.service.RoleService;
import cn.cas.iue.service.UrrelService;
import cn.cas.iue.service.UserService;
import cn.cas.iue.util.JSONUtil;

/**
 * @Author: William
 * @ProjectName: cm
 * @ClassName: UserAction 
 * @Description: TODO
 * @Date: 2014年3月12日 上午11:18:39
 * @Version V1.0
 */
/*@Component("u")
@Scope("prototype")*/
public class UserAction extends BaseAction{
	private static final long serialVersionUID = 2138368873748767381L;
	private Integer userId;
	private String userName;
	private String password;
	private String name;
	private String sex;	
	private String telephone;
	private String email;
	private String degree;
	private String jobContent;
	private String major;
	private String school;
	private int[] roleIds;
	private boolean flag;
	private String[] fields;
	private String query;
	private User user;
	private UserService userService;
	private Urrel urrel;
	private UrrelService urrelService;
	private Role role;
	private RoleService roleService;
	private int start;
	private int limit;
	private String type;
	
	public void addUser() {
		try {
			if(userService.isExists(userName)){
				responseJson("{success:false, errorMessage:'该用户名已存在'}");
			} else {
				user = new User();
				user.setUserName(userName);
				user.setPassword(password);
				user.setName(name);
				user.setSex(sex);
				user.setTelephone(telephone);
				user.setEmail(email);
				userService.addUser(user);
				user.setUrrels(UrrelBuilder(user));
				responseJson("{success:true, msg:'保存成功'}");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void getUsers() {
		try {
			if(query == null || query.equals("")) {
				List<User> users = userService.getUsers(start, limit);
				int count = 0;
				int position = -1;
				for (User user : users) {
					String[] roleNames = getRoleNames(user); //获取用户的用户类型
					user.setRoleNames(roleNames);
					if(Arrays.toString(roleNames).indexOf(Global.SYSTEM_ADMINISTRATOR) > -1) { 
						position = count; //系统管理员在users链表中的位置
					}
					count ++;
				}
				if(position != -1) {
					users.remove(position);  //移除系统管理员用户
				}
				JSONArray jArray = JSONArray.fromObject(users, JSONUtil.getJsonConfig());
				Long total = userService.getTotal();
				JSONObject jObj = new JSONObject();
				jObj.put("users", jArray);
				jObj.put("total", total - 1);
				responseJson(jObj.toString());
			} else {
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void delete() {
		try {
			userService.deleteUser(userId);
			responseJson("{success:true, msg:'删除成功'}");
		} catch(RuntimeException re) {
			try {
				responseJson("{success:false, errorMessage:'删除失败，请检查该用户是否有授权仪器'}");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void updateInfo() {
		try {
			user = userService.getUserById(userId);
			user.setName(name);
			user.setSex(sex);
			user.setTelephone(telephone);
			user.setEmail(email);
			user.setDegree(degree);
			user.setJobContent(jobContent);
			user.setMajor(major);
			user.setSchool(school);
			if(flag) {
				UrrelUpdater(user);
			}
			userService.updateUserInfo(user);
			responseJson("{success:true, msg:'保存成功'}");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void resetPwd() {
		try {
			userService.resetPassword(userId, password);
			responseJson("{success:true, msg:'密码修改成功'}");
		} catch(RuntimeException re){
			try {
				responseJson("{success:false, errorMessage:'密码修改失败,请重新尝试'}");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			re.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Set<Urrel> UrrelBuilder(User user) {
		Set<Urrel> urrels = new TreeSet<Urrel>();
		if(roleIds != null) {
			for (int roleId : roleIds) {
				urrel = new Urrel();
				role = roleService.getRole(roleId);
				urrel.setUser(user);
				urrel.setRole(role);
				urrels.add(urrel);
				urrelService.addUrrel(urrel);
			}
			return urrels;
		}
		return null;
	}
	
	public void UrrelUpdater(User user) {
		Set<Urrel> urrels = user.getUrrels();
		for (Urrel urrel : urrels) {
			urrelService.deleteUrrel(urrel);
		}
		if(roleIds != null) {
			for (int roleId : roleIds) {
				urrel = new Urrel();
				role = roleService.getRole(roleId);
				urrel.setUser(user);
				urrel.setRole(role);
				urrelService.addUrrel(urrel);
			}
		}
	}
	
	public String[] getRoleNames(User user) {
		Set<Urrel> urrels = user.getUrrels();
		Iterator<Urrel> iterator = urrels.iterator();
		String[] names = new String[urrels.size() - 1]; //'其他'过滤后不计算在内
		int i = 0;
		while(iterator.hasNext()){
			String name = iterator.next().getRole().getRoleName();
			if(!name.equals(Global.OTHER)) { //过滤角色中名称为其他这项
				names[i++] = name;
			}
		}
		return names;
	}
	
	public void getNames() {
		List<User> users = new ArrayList<User>();
		Long total = 0L;
		int count = 0;
		int position = -1;
		try {
			if(type.equals("user")) {
				//start + 1 user表中第一条记录是系统管理员，不显示
				users = userService.getUsers(start, limit);
				total = userService.getTotal();
			} else if(type.equals("admin")){
				users = userService.getAdmins(start, limit);
				total = userService.getTotalAdmin();
			}
			for (User user : users) {
				String[] roleNames = getRoleNames(user); //获取用户的用户类型
				user.setRoleNames(roleNames);
				if(Arrays.toString(roleNames).indexOf(Global.SYSTEM_ADMINISTRATOR) > -1) { 
					position = count; //系统管理员在users链表中的位置
				}
				count ++;
			}
			if(position != -1) {
				users.remove(position);  //移除系统管理员用户
			}
			JSONArray jArray = JSONArray.fromObject(users, JSONUtil.getJsonConfig());
			JSONObject jObj = new JSONObject();
			jObj.put("names", jArray);
			jObj.put("total", total - 1);
			responseJson(jObj.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserService getUserService() {
		return userService;
	}
	
	//@Resource
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public int[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(int[] roleIds) {
		this.roleIds = roleIds;
	}

	public Urrel getUrrel() {
		return urrel;
	}

	public void setUrrel(Urrel urrel) {
		this.urrel = urrel;
	}

	public UrrelService getUrrelService() {
		return urrelService;
	}

	public void setUrrelService(UrrelService urrelService) {
		this.urrelService = urrelService;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getJobContent() {
		return jobContent;
	}

	public void setJobContent(String jobContent) {
		this.jobContent = jobContent;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String[] getFields() {
		return fields;
	}

	public void setFields(String[] fields) {
		this.fields = fields;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}
}
