package cn.cas.iue.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.cas.iue.bean.Metrical;
import cn.cas.iue.bean.User;
import cn.cas.iue.dao.MetricalDAO;
import cn.cas.iue.service.MetricalService;

@Component("metricalService")
public class MetricalServiceImpl implements MetricalService {
	private MetricalDAO metricalDAO;

	public void addMetrical(Metrical metrical) {
		metricalDAO.save(metrical);
	}
	
	public List<Metrical> getMetricals(int start, int limit) {
		List<Metrical> metricals = metricalDAO.findAll(start, limit);
		return metricals;
	}
	
	public Long getTotal() {
		return metricalDAO.getCount();
	}
	
	public MetricalDAO getMetricalDAO() {
		return metricalDAO;
	}
	@Resource
	public void setMetricalDAO(MetricalDAO metricalDAO) {
		this.metricalDAO = metricalDAO;
	}
}
