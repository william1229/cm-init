package cn.cas.iue.service;

import java.util.List;

import cn.cas.iue.bean.SSOutInRec;
import cn.cas.iue.bean.Consumable;

public interface SSOutInRecService {
	public void addSSOutInRec(SSOutInRec sSOutInRec);
	public List<SSOutInRec> getSSOutInRecs(int start, int limit);
	public Long getTotal();
}