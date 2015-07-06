package cn.cas.iue.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.cas.iue.bean.Urrel;
import cn.cas.iue.dao.UrrelDAO;
import cn.cas.iue.service.UrrelService;

@Component("urrelService")
public class UrrelServiceImpl implements UrrelService {
	private UrrelDAO urrelDAO;
	
	public void addUrrel(Urrel urrel) {
		urrelDAO.save(urrel);
	}
	
	public void deleteUrrel(Urrel urrel) {
		urrelDAO.delete(urrel);
	}
	public UrrelDAO getUrrelDAO() {
		return urrelDAO;
	}
	@Resource
	public void setUrrelDAO(UrrelDAO urrelDAO) {
		this.urrelDAO = urrelDAO;
	}

}
