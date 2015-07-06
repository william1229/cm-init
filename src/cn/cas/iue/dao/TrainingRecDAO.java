package cn.cas.iue.dao;

import java.util.List;

import cn.cas.iue.bean.Node;
import cn.cas.iue.bean.TrainingRec;
import cn.cas.iue.bean.User;

public interface TrainingRecDAO {
	public List<TrainingRec> findAll(int start, int limit);
	public void save(TrainingRec trainingRec);
	public void delete(TrainingRec trainingRec);
	public Long getCount();
}
