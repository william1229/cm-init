package cn.cas.iue.service;

import java.util.List;

import cn.cas.iue.bean.Standard;


public interface StandardService {
	public void addStandard(Standard standard);
	public List<Standard> getStandards(int start, int limit);
	public Long getTotal();
	public void updateStandardState(String state, Integer standardId) throws Exception;
}