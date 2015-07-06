package cn.cas.iue.dao;

import java.util.List;

import cn.cas.iue.bean.GasCsum;


public interface GasCsumDAO {
	public void save(GasCsum transientInstance);
	public void delete(GasCsum persistentInstance);
	public List<GasCsum> findAll(final int start, final int limit);
	public Long getCount();
}
