package cn.cas.iue.action;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.cache.infinispan.util.FlagAdapter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;

import cn.cas.iue.base.BaseAction;
import cn.cas.iue.bean.Role;
import cn.cas.iue.bean.Urrel;
import cn.cas.iue.bean.User;
import cn.cas.iue.common.Global;
import cn.cas.iue.service.UserService;
import cn.cas.iue.util.JSONUtil;

/**
 * @Author: William
 * @ProjectName: cm
 * @ClassName: LoginAction 
 * @Description: 登陆验证
 * @Date: 2014年3月7日 上午10:46:22
 * @Version V1.0
 */
/*@Component("login")
@Scope("prototype")*/
public class LoginAction extends BaseAction implements SessionAware{
	private static final long serialVersionUID = -573894239206539029L;
	
	private String userName;
	private String password;
	private User user;
	private UserService userService;
	private Map<String, Object> session;

	public void login() throws Exception {
		user = userService.getUserByUserName(userName);
		if(user == null) {
			responseJson("{success:false, errorMessage:'用户名错误或不存在'}");
		} else {
			if(password.equals(user.getPassword())) {
				Set<Urrel> urrels = user.getUrrels();
				Iterator<Urrel> iterator = urrels.iterator();
				String[] roleNames = new String[urrels.size()];
				int i = 0;
				while(iterator.hasNext()) {
					Urrel urrel = iterator.next();
					roleNames[i++] = urrel.getRole().getRoleName();
				}
				user.setRoleNames(roleNames);
				session.put("user", user);
				JSONObject jObject = JSONObject.fromObject(user, JSONUtil.getJsonConfig());
				responseJson("{success:true, user:" + jObject.toString() + "}");
			} else {
				responseJson("{success:false, errorMessage:'密码错误'}");
			}
		}
	}
	
	public String toLogin() throws Exception {
		return "login";
	}
	
	public void logout() throws Exception {
		session.put("user", null);
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public UserService getUserService() {
		return userService;
	}
	
	//@Resource
	public void setUserService(UserService userService) {
		this.userService = userService;
	}



	/* (non-Javadoc)
	 * @see org.apache.struts2.interceptor.SessionAware#setSession(java.util.Map)
	 */
	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session = session;
	}
}
