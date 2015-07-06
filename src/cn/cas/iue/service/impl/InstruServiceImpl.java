package cn.cas.iue.service.impl;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.cas.iue.bean.Instru;
import cn.cas.iue.bean.User;
import cn.cas.iue.dao.InstruDAO;
import cn.cas.iue.dao.UserDAO;
import cn.cas.iue.service.InstruService;
import cn.cas.iue.service.UserService;

@Component("instruService")
public class InstruServiceImpl implements InstruService {
	private InstruDAO instruDAO;
	
	public List<Instru> getInstrus(int start, int limit) {
		return instruDAO.findAll(start, limit);
	}
	
	public void addInstru(Instru instru) {
		instruDAO.save(instru);
	}
	
	public Long getTotal() {
		return instruDAO.getCount();
	}
	
	public Long getTotal(Integer userId) {
		return instruDAO.getCount(userId);
	}
	
	public Instru getInstruById(Integer instruId) {
		return instruDAO.findById(instruId);
	}
	
	public List<Instru> getInstruByUserId(int start, int limit, Integer userId) {
		return instruDAO.getByUserId(start, limit, userId);
	}
	
	public void updateInstruInfo(Instru instru) throws Exception {
		instruDAO.updateInstru(instru);
	}
	
	public void updateName(Integer userId, Integer instruId) throws Exception{
		instruDAO.updateAdminName(userId, instruId);
	}
	
	public InstruDAO getInstruDAO() {
		return instruDAO;
	}
	
	public void updateConsumableState(Integer instruId, boolean flag) throws Exception{
		instruDAO.updateConsumableIsLow(instruId, flag);
	}
	@Resource
	public void setInstruDAO(InstruDAO instruDAO) {
		this.instruDAO = instruDAO;
	}
}
