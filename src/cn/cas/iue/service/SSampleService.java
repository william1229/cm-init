package cn.cas.iue.service;

import java.util.List;

import cn.cas.iue.bean.SSample;
import cn.cas.iue.bean.Consumable;

public interface SSampleService {
	public void addSSample(SSample sSample);
	public List<SSample> getSSamples(int start, int limit);
	public Long getTotal();
	public SSample getSSampleById(Integer sSampleId);
	public void updateSSampleQuantity(double quantity, Integer sSampleId) throws Exception;
}