package cn.cas.iue.service;

import java.util.List;

import cn.cas.iue.bean.Metrical;
import cn.cas.iue.bean.Consumable;

public interface MetricalService {
	public void addMetrical(Metrical metrical);
	public List<Metrical> getMetricals(int start, int limit);
	public Long getTotal();
}