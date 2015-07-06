package cn.cas.iue.dao;

import java.util.List;

import cn.cas.iue.bean.Project;

public interface ProjectDAO {
	public void save(Project transientInstance);
	public void delete(Project persistentInstance);
	public List<Project> findAll(final int start, final int limit);
	public Project findById(java.lang.Integer id);
	public Long getCount();
}
