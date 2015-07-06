package cn.cas.iue.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.cas.iue.bean.Report;
import cn.cas.iue.dao.ReportDAO;
import cn.cas.iue.service.ReportService;

@Component("reportService")
public class ReportServiceImpl implements ReportService {
	private ReportDAO reportDAO;

	public void addReport(Report report) {
		reportDAO.save(report);
	}
	
	public List<Report> getReports(int start, int limit) {
		List<Report> reports = reportDAO.findAll(start, limit);
		return reports;
	}
	
	public Long getTotal() {
		return reportDAO.getCount();
	}
	
	public void deleteReport(Integer reportId) throws Exception{
		reportDAO.deleteById(reportId);
	}
	
	public ReportDAO getReportDAO() {
		return reportDAO;
	}
	@Resource
	public void setReportDAO(ReportDAO reportDAO) {
		this.reportDAO = reportDAO;
	}
}
