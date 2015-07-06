package cn.cas.iue.dao;

import java.util.List;

import cn.cas.iue.bean.Consumable;
import cn.cas.iue.bean.Instru;
import cn.cas.iue.bean.Node;
import cn.cas.iue.bean.User;

public interface ConsumableDAO {
	public List<Consumable> findAll(final int start, final int limit);
	public void save(Consumable transientInstance);
	public Long getICCount(Integer instruId);
	public Long getPCCount();
	public void updateConsumable(Consumable consumable) throws Exception;
	public Consumable findById(Integer id);
	public List<Consumable> findIConsumables(int start, int limit, Integer instruId);
	public List<Consumable> findPConsumables(final int start, final int limit);
	public void updateQuantity(Integer quantity, Integer consumableId) throws Exception;
	public boolean hasLow(Integer instruId, int limit);
	public void deleteById(Integer consumableId);
	public List<Consumable> findByConsumableName(int start, int limit, Integer instruId, String query);
	//public List<Consumable> getLowConsumable(int limit);
}
