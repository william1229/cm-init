package cn.cas.iue.dao;

import java.util.List;

import cn.cas.iue.bean.Instru;
import cn.cas.iue.bean.Node;
import cn.cas.iue.bean.User;

public interface InstruDAO {
	public List<Instru> findAll(final int start, final int limit);
	public void save(Instru transientInstance);
	public Long getCount();
	public void updateInstru(Instru instru) throws Exception;
	public Instru findById(java.lang.Integer id);
	public void updateConsumableIsLow(Integer instruId, boolean flag) throws Exception;
	public void updateAdminName(Integer userId, Integer instruId) throws Exception;
	public List<Instru> getByUserId(final int start, final int limit, final Integer userId);
	public Long getCount(Integer userId);
}
