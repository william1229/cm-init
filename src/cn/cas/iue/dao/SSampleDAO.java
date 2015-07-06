package cn.cas.iue.dao;

import java.util.List;

import cn.cas.iue.bean.Node;
import cn.cas.iue.bean.SSample;
import cn.cas.iue.bean.TrainingRec;
import cn.cas.iue.bean.User;

public interface SSampleDAO {
	public List<SSample> findAll(final int start, final int limit);
	public void save(SSample transientInstance);
	public Long getCount();
	public SSample findById(java.lang.Integer id);
	public void updateQuantity(double quantity, Integer sSampleId) throws Exception;
}
