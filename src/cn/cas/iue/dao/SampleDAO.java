package cn.cas.iue.dao;

import java.util.List;

import cn.cas.iue.bean.Project;
import cn.cas.iue.bean.Sample;

public interface SampleDAO {
	public void save(Sample transientInstance);
	public void delete(Sample persistentInstance);
	public List<Sample> findAll(final int start, final int limit);
	public Long getCount();
}
