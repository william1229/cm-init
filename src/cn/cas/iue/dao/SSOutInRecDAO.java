package cn.cas.iue.dao;

import java.util.List;

import cn.cas.iue.bean.Consumable;
import cn.cas.iue.bean.Instru;
import cn.cas.iue.bean.Node;
import cn.cas.iue.bean.SSOutInRec;
import cn.cas.iue.bean.User;

public interface SSOutInRecDAO {
	public Long getCount();
	public List<SSOutInRec> findAll(final int start, final int limit);
	public void save(SSOutInRec transientInstance);
}
