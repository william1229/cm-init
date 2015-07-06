package cn.cas.iue.service;

import java.util.List;

import cn.cas.iue.bean.Consumable;

public interface ConsumableService {
	public void addConsumable(Consumable consumable);
	public List<Consumable> getConsumables(int start, int limit);
	public Long getICTotal(Integer instruId);
	public Long getPCTotal();
	public Consumable getConsumableById(Integer consumableId);
	public void updateConsumableInfo(Consumable consumable) throws Exception;
	public List<Consumable> getIConsumables(int start, int limit, Integer instruId);
	public List<Consumable> getPConsumables(int start, int limit);
	public void updateConsumableQuantity(Integer quantity, Integer consumableId) throws Exception;
	public boolean hasLowConsumable(Integer instruId,int limit);
	public void deleteConsumable(Integer consumableId);
	public List<Consumable> getIConsumablesByName(int start, int limit, Integer instruId, String query);
	public List<Consumable> getPConsumablesByName(int start, int limit, String query);
	//public List<Consumable> getLow(int limit);
}