package cn.cas.iue.dao;

import java.util.List;

import cn.cas.iue.bean.MgtReview;

public interface MgtReviewDAO {
	public void save(MgtReview transientInstance);
	public void delete(MgtReview persistentInstance);
	public List<MgtReview> findAll(final int start, final int limit);
	public Long getCount();
}
