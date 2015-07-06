package cn.cas.iue.service;

import java.util.List;

import cn.cas.iue.bean.PurchasingRec;

public interface PurchasingRecService {
	public void addPurchasingRec(PurchasingRec purchasingRec);
	public List<PurchasingRec> getPurchasingRecs(int start, int limit);
	public Long getTotal();
	public Long getTotal(Integer userId);
	public Long getTotal(String state);
	public List<PurchasingRec> getPurchasingRecs(int start, int limit, Integer userId);
	public void updateStateById(Integer recId, String state) throws Exception;
	public List<PurchasingRec> getPurchasingRecs(int start, int limit, String state);
	public PurchasingRec getPurchasingRecById(Integer purchasingRecId);
	public void updatePurchasingRecInfo(PurchasingRec purchasingRec) throws Exception;
}