package cn.cas.iue.service;

import java.util.List;

import cn.cas.iue.bean.GasCsum;

public interface GasCsumService {
	public void addGasCsum(GasCsum gasCsum);
	public List<GasCsum> getGasCsums(int start, int limit);
	public Long getTotal();
}