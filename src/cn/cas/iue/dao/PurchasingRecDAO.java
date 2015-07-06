package cn.cas.iue.dao;

import java.util.List;

import cn.cas.iue.bean.OutInRec;
import cn.cas.iue.bean.PurchasingRec;
import cn.cas.iue.bean.User;

public interface PurchasingRecDAO {
	public List<PurchasingRec> findAll(final int start, final int limit);
	public void save(PurchasingRec transientInstance);
	public void delete(PurchasingRec persistentInstance);
	public Long getCount();
	public Long getCount(Integer userId);
	public List<PurchasingRec> findAll(final int start, final int limit, final Integer userId);
	public void updateState(Integer recId, String state) throws Exception;
	public List<PurchasingRec> findAll(final int start, final int limit,final String state);
	public Long getCount(String state);
	public PurchasingRec findById(java.lang.Integer id);
	public void updatePurchasingRec(PurchasingRec purchasingRec) throws Exception;
}
