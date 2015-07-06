package cn.cas.iue.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.cas.iue.bean.Standard;
import cn.cas.iue.dao.StandardDAO;
import cn.cas.iue.service.StandardService;

@Component("standardService")
public class StandardServiceImpl implements StandardService {
	private StandardDAO standardDAO;

	public void addStandard(Standard standard) {
		standardDAO.save(standard);
	}
	
	public List<Standard> getStandards(int start, int limit) {
		List<Standard> standards = standardDAO.findAll(start, limit);
		return standards;
	}
	
	public void updateStandardState(String state, Integer standardId) throws Exception {
		standardDAO.updateState(state, standardId);
	}
	
	public Long getTotal() {
		return standardDAO.getCount();
	}
	
	public StandardDAO getStandardDAO() {
		return standardDAO;
	}
	@Resource
	public void setStandardDAO(StandardDAO standardDAO) {
		this.standardDAO = standardDAO;
	}
}
