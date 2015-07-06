package cn.cas.iue.dao;

import java.util.List;

import cn.cas.iue.bean.AppraisalRec;
import cn.cas.iue.bean.Consumable;
import cn.cas.iue.bean.Instru;
import cn.cas.iue.bean.Node;
import cn.cas.iue.bean.Standard;
import cn.cas.iue.bean.User;

public interface StandardDAO {
	public Long getCount();
	public List<Standard> findAll(final int start, final int limit);
	public void save(Standard transientInstance);
	public void delete(Standard persistentInstance);
	public void updateState(String state, Integer standardId) throws Exception;
}
