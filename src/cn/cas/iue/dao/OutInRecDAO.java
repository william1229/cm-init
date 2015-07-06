package cn.cas.iue.dao;

import java.util.List;

import cn.cas.iue.bean.Node;
import cn.cas.iue.bean.OutInRec;
import cn.cas.iue.bean.TrainingRec;
import cn.cas.iue.bean.User;

public interface OutInRecDAO {
	public List<OutInRec> findAll(int start, int limit);
	public void save(OutInRec trainingRec);
	public void delete(OutInRec trainingRec);
	public Long getCount();
}
