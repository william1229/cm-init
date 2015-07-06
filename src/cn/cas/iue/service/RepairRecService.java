package cn.cas.iue.service;

import java.util.List;

import cn.cas.iue.bean.RepairRec;

public interface RepairRecService {
	public void addRepairRec(RepairRec repairRec);
	public List<RepairRec> getRepairRecs(int start, int limit);
	public Long getTotal();
}