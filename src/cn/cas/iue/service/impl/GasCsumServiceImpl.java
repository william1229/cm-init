package cn.cas.iue.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.cas.iue.bean.GasCsum;
import cn.cas.iue.dao.GasCsumDAO;
import cn.cas.iue.service.GasCsumService;

@Component("gasCsumService")
public class GasCsumServiceImpl implements GasCsumService {
	private GasCsumDAO gasCsumDAO;

	public void addGasCsum(GasCsum gasCsum) {
		gasCsumDAO.save(gasCsum);
	}
	
	public List<GasCsum> getGasCsums(int start, int limit) {
		List<GasCsum> gasCsums = gasCsumDAO.findAll(start, limit);
		return gasCsums;
	}
	
	public Long getTotal() {
		return gasCsumDAO.getCount();
	}
	
	public GasCsumDAO getGasCsumDAO() {
		return gasCsumDAO;
	}
	@Resource
	public void setGasCsumDAO(GasCsumDAO gasCsumDAO) {
		this.gasCsumDAO = gasCsumDAO;
	}
}
