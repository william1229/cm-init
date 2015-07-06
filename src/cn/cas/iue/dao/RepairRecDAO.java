package cn.cas.iue.dao;

import java.util.List;

import cn.cas.iue.bean.RepairRec;
import cn.cas.iue.bean.Consumable;
import cn.cas.iue.bean.Instru;
import cn.cas.iue.bean.Node;
import cn.cas.iue.bean.User;

public interface RepairRecDAO {
	public List<RepairRec> findAll(final int start, final int limit);
	public void save(RepairRec transientInstance);
	public void delete(RepairRec persistentInstance);
	public Long getCount();
}
