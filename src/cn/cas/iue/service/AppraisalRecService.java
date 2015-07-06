package cn.cas.iue.service;

import java.util.List;

import cn.cas.iue.bean.AppraisalRec;


public interface AppraisalRecService {
	public void addAppraisalRec(AppraisalRec appraisalRec);
	public List<AppraisalRec> getAppraisalRecs(int start, int limit);
	public Long getTotal();
}