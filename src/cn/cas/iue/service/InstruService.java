package cn.cas.iue.service;

import java.util.List;

import cn.cas.iue.bean.Instru;
import cn.cas.iue.bean.User;

public interface InstruService {
	public void addInstru(Instru instru);
	public List<Instru> getInstrus(int start, int limit);
	public Long getTotal();
	public Instru getInstruById(Integer instruId);
	public void updateInstruInfo(Instru instru) throws Exception;
	public void updateConsumableState(Integer instruId, boolean flag) throws Exception;
	public void updateName(Integer userId, Integer instruId) throws Exception;
	public List<Instru> getInstruByUserId(int start, int limit, Integer userId);
	public Long getTotal(Integer userId);
}