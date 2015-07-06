package cn.cas.iue.service;

import java.util.List;

import cn.cas.iue.bean.Report;


public interface ReportService {
	public void addReport(Report report);
	public List<Report> getReports(int start, int limit);
	public Long getTotal();
	public void deleteReport(Integer reportId) throws Exception;
}