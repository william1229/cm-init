package cn.cas.iue.service.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.cas.iue.bean.Role;
import cn.cas.iue.dao.RoleDAO;
import cn.cas.iue.service.RoleService;

@Component("roleService")
public class RoleServiceImpl implements RoleService {
	private RoleDAO roleDAO;
	
	public Role getRole(Integer RoleId) {
		return roleDAO.findById(RoleId);
	}

	public RoleDAO getRoleDAO() {
		return roleDAO;
	}	
	@Resource
	public void setRoleDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}

}
