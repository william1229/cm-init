package cn.cas.iue.dao;

import java.util.List;

import cn.cas.iue.bean.ExReview;

public interface ExReviewDAO {
	public void save(ExReview transientInstance);
	public void delete(ExReview persistentInstance);
	public List<ExReview> findAll(final int start, final int limit);
	public Long getCount();
}
