package cn.cas.iue.service.impl;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.cas.iue.bean.Consumable;
import cn.cas.iue.bean.Instru;
import cn.cas.iue.bean.User;
import cn.cas.iue.dao.ConsumableDAO;
import cn.cas.iue.dao.UserDAO;
import cn.cas.iue.service.ConsumableService;
import cn.cas.iue.service.UserService;

@Component("consumableService")
public class ConsumableServiceImpl implements ConsumableService {
	private ConsumableDAO consumableDAO;
	
	public List<Consumable> getConsumables(int start, int limit) {
		return consumableDAO.findAll(start, limit);
	}
	
	public List<Consumable> getIConsumables(int start, int limit, Integer instruId) {
		return consumableDAO.findIConsumables(start, limit, instruId);
	}
	
	public List<Consumable> getPConsumables(int start, int limit) {
		return consumableDAO.findPConsumables(start, limit);
	}
	
	public void addConsumable(Consumable consumable) {
		consumableDAO.save(consumable);
	}
	
	public boolean hasLowConsumable(Integer instruId,int limit) {
		return consumableDAO.hasLow(instruId, limit);
	}
	
	public List<Consumable> getIConsumablesByName(int start, int limit, Integer instruId, String query) {
		return consumableDAO.findByConsumableName(start, limit, instruId, query);
	}
	
	public List<Consumable> getPConsumablesByName(int start, int limit, String query) {
		Integer instruId = null;
		return consumableDAO.findByConsumableName(start, limit, instruId, query);
	}
/*	public List<Consumable> getLow(int limit) {
		return consumableDAO.getLowConsumable(limit);
	}*/
	
	public Long getICTotal(Integer instruId) {
		return consumableDAO.getICCount(instruId);
	}
	
	public Long getPCTotal() {
		return consumableDAO.getPCCount();
	}
	
	public void deleteConsumable(Integer consumableId) {
		consumableDAO.deleteById(consumableId);
	}
	
	public Consumable getConsumableById(Integer consumableId) {
		return consumableDAO.findById(consumableId);
	}
	
	public void updateConsumableInfo(Consumable consumable) throws Exception {
		consumableDAO.updateConsumable(consumable);
	}
	
	public void updateConsumableQuantity(Integer quantity, Integer consumableId) throws Exception {
		consumableDAO.updateQuantity(quantity, consumableId);
	}
	
	public ConsumableDAO getConsumableDAO() {
		return consumableDAO;
	}
	@Resource
	public void setConsumableDAO(ConsumableDAO consumableDAO) {
		this.consumableDAO = consumableDAO;
	}
}
