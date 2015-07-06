package cn.cas.iue.dao;

import java.util.List;

import cn.cas.iue.bean.Metrical;
import cn.cas.iue.bean.Consumable;
import cn.cas.iue.bean.Instru;
import cn.cas.iue.bean.Node;
import cn.cas.iue.bean.User;

public interface MetricalDAO {
	public List<Metrical> findAll(final int start, final int limit);
	public void save(Metrical transientInstance);
	public void delete(Metrical persistentInstance);
	public Long getCount();
}
