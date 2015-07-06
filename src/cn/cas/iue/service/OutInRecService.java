package cn.cas.iue.service;

import java.util.List;

import cn.cas.iue.bean.OutInRec;

public interface OutInRecService {
	public void addOutInRec(OutInRec outInRec);
	public List<OutInRec> getOutInRecs(int start, int limit);
	public Long getTotal();
}