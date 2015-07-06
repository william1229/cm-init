package cn.cas.iue.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.cas.iue.bean.OutInRec;
import cn.cas.iue.bean.User;
import cn.cas.iue.dao.OutInRecDAO;
import cn.cas.iue.service.OutInRecService;

@Component("outInRecService")
public class OutInRecServiceImpl implements OutInRecService {
	private OutInRecDAO outInRecDAO;

	public void addOutInRec(OutInRec outInRec) {
		outInRecDAO.save(outInRec);
	}
	
	public List<OutInRec> getOutInRecs(int start, int limit) {
		List<OutInRec> outInRecs = outInRecDAO.findAll(start, limit);
		return outInRecs;
	}
	
	public Long getTotal() {
		return outInRecDAO.getCount();
	}
	
	public OutInRecDAO getOutInRecDAO() {
		return outInRecDAO;
	}
	@Resource
	public void setOutInRecDAO(OutInRecDAO outInRecDAO) {
		this.outInRecDAO = outInRecDAO;
	}
}
