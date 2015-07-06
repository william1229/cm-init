package cn.cas.iue.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.cas.iue.bean.Consumable;
import cn.cas.iue.bean.SSample;
import cn.cas.iue.dao.SSampleDAO;
import cn.cas.iue.service.SSampleService;

@Component("sSampleService")
public class SSampleServiceImpl implements SSampleService {
	private SSampleDAO sSampleDAO;

	public void addSSample(SSample sSample) {
		sSampleDAO.save(sSample);
	}
	
	public List<SSample> getSSamples(int start, int limit) {
		List<SSample> sSamples = sSampleDAO.findAll(start, limit);
		return sSamples;
	}
	
	public SSample getSSampleById(Integer sSampleId) {
		return sSampleDAO.findById(sSampleId);
	}
	
	public void updateSSampleQuantity(double quantity, Integer sSampleId) throws Exception {
		sSampleDAO.updateQuantity(quantity, sSampleId);
	}
	
	public Long getTotal() {
		return sSampleDAO.getCount();
	}
	
	public SSampleDAO getSSampleDAO() {
		return sSampleDAO;
	}
	@Resource
	public void setSSampleDAO(SSampleDAO sSampleDAO) {
		this.sSampleDAO = sSampleDAO;
	}
}
