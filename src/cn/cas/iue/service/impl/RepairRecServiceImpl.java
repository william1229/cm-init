package cn.cas.iue.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.cas.iue.bean.RepairRec;
import cn.cas.iue.bean.User;
import cn.cas.iue.dao.RepairRecDAO;
import cn.cas.iue.service.RepairRecService;

@Component("repairRecService")
public class RepairRecServiceImpl implements RepairRecService {
	private RepairRecDAO repairRecDAO;

	public void addRepairRec(RepairRec repairRec) {
		repairRecDAO.save(repairRec);
	}
	
	public List<RepairRec> getRepairRecs(int start, int limit) {
		List<RepairRec> repairRecs = repairRecDAO.findAll(start, limit);
		return repairRecs;
	}
	
	public Long getTotal() {
		return repairRecDAO.getCount();
	}
	
	public RepairRecDAO getRepairRecDAO() {
		return repairRecDAO;
	}
	@Resource
	public void setRepairRecDAO(RepairRecDAO repairRecDAO) {
		this.repairRecDAO = repairRecDAO;
	}
}
