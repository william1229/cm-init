package cn.cas.iue.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.cas.iue.bean.Consumable;
import cn.cas.iue.bean.SSOutInRec;
import cn.cas.iue.dao.SSOutInRecDAO;
import cn.cas.iue.service.SSOutInRecService;

@Component("sSOutInRecService")
public class SSOutInRecServiceImpl implements SSOutInRecService {
	private SSOutInRecDAO sSOutInRecDAO;

	public void addSSOutInRec(SSOutInRec sSOutInRec) {
		sSOutInRecDAO.save(sSOutInRec);
	}
	
	public List<SSOutInRec> getSSOutInRecs(int start, int limit) {
		List<SSOutInRec> sSOutInRecs = sSOutInRecDAO.findAll(start, limit);
		return sSOutInRecs;
	}
	
	public Long getTotal() {
		return sSOutInRecDAO.getCount();
	}
	
	public SSOutInRecDAO getSSOutInRecDAO() {
		return sSOutInRecDAO;
	}
	@Resource
	public void setSSOutInRecDAO(SSOutInRecDAO sSOutInRecDAO) {
		this.sSOutInRecDAO = sSOutInRecDAO;
	}
}
