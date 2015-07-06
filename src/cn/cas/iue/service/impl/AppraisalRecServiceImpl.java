package cn.cas.iue.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.cas.iue.bean.AppraisalRec;
import cn.cas.iue.dao.AppraisalRecDAO;
import cn.cas.iue.service.AppraisalRecService;

@Component("appraisalRecService")
public class AppraisalRecServiceImpl implements AppraisalRecService {
	private AppraisalRecDAO appraisalRecDAO;

	public void addAppraisalRec(AppraisalRec appraisalRec) {
		appraisalRecDAO.save(appraisalRec);
	}
	
	public List<AppraisalRec> getAppraisalRecs(int start, int limit) {
		List<AppraisalRec> appraisalRecs = appraisalRecDAO.findAll(start, limit);
		return appraisalRecs;
	}
	
	public Long getTotal() {
		return appraisalRecDAO.getCount();
	}
	
	public AppraisalRecDAO getAppraisalRecDAO() {
		return appraisalRecDAO;
	}
	@Resource
	public void setAppraisalRecDAO(AppraisalRecDAO appraisalRecDAO) {
		this.appraisalRecDAO = appraisalRecDAO;
	}
}
