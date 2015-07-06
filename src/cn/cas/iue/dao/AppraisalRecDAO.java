package cn.cas.iue.dao;

import java.util.List;

import cn.cas.iue.bean.AppraisalRec;
import cn.cas.iue.bean.Consumable;
import cn.cas.iue.bean.Instru;
import cn.cas.iue.bean.Node;
import cn.cas.iue.bean.User;

public interface AppraisalRecDAO {
	public void save(AppraisalRec transientInstance);
	public void delete(AppraisalRec persistentInstance);
	public List<AppraisalRec> findAll(final int start, final int limit);
	public Long getCount();
}
