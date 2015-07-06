package cn.cas.iue.dao;

import java.util.List;

import cn.cas.iue.bean.Report;

public interface ReportDAO {
	public void save(Report transientInstance);
	public void delete(Report persistentInstance);
	public List<Report> findAll(final int start, final int limit);
	public Long getCount();
	public void deleteById(Integer reportId) throws Exception;
}
