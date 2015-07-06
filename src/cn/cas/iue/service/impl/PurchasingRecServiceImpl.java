package cn.cas.iue.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.cas.iue.bean.Consumable;
import cn.cas.iue.bean.Instru;
import cn.cas.iue.bean.PurchasingRec;
import cn.cas.iue.bean.User;
import cn.cas.iue.dao.PurchasingRecDAO;
import cn.cas.iue.service.PurchasingRecService;

@Component("purchasingRecService")
public class PurchasingRecServiceImpl implements PurchasingRecService {
	private PurchasingRecDAO purchasingRecDAO;

	public void addPurchasingRec(PurchasingRec purchasingRec) {
		purchasingRecDAO.save(purchasingRec);
	}
	
	public List<PurchasingRec> getPurchasingRecs(int start, int limit) {
		List<PurchasingRec> purchasingRecs = purchasingRecDAO.findAll(start, limit);
		return purchasingRecs;
	}
	
	public PurchasingRec getPurchasingRecById(Integer purchasingRecId) {
		return purchasingRecDAO.findById(purchasingRecId);
	}
	
	public Long getTotal() {
		return purchasingRecDAO.getCount();
	}
	
	public Long getTotal(Integer userId) {
		return purchasingRecDAO.getCount(userId);
	}
	
	public Long getTotal(String state) {
		return purchasingRecDAO.getCount(state);
	}
	
	public List<PurchasingRec> getPurchasingRecs(int start, int limit, Integer userId) {
		return purchasingRecDAO.findAll(start, limit, userId);
	}
	
	public List<PurchasingRec> getPurchasingRecs(int start, int limit, String state) {
		List<PurchasingRec> purchasingRecs = purchasingRecDAO.findAll(start, limit, state);
		return purchasingRecs;
	}
	
	public void updatePurchasingRecInfo(PurchasingRec purchasingRec) throws Exception {
		purchasingRecDAO.updatePurchasingRec(purchasingRec);
	}
	
	public void updateStateById(Integer recId, String state) throws Exception{
		purchasingRecDAO.updateState(recId, state);
	}
	
	public PurchasingRecDAO getPurchasingRecDAO() {
		return purchasingRecDAO;
	}
	@Resource
	public void setPurchasingRecDAO(PurchasingRecDAO purchasingRecDAO) {
		this.purchasingRecDAO = purchasingRecDAO;
	}
}
