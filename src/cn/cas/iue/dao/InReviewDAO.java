package cn.cas.iue.dao;

import java.util.List;

import cn.cas.iue.bean.InReview;

public interface InReviewDAO {
	public void save(InReview transientInstance);
	public void delete(InReview persistentInstance);
	public List<InReview> findAll(final int start, final int limit);
	public Long getCount();
}
