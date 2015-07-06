package cn.cas.iue.service;

import java.util.List;

import cn.cas.iue.bean.InReview;


public interface InReviewService {
	public void addInReview(InReview inReview);
	public List<InReview> getInReviews(int start, int limit);
	public Long getTotal();
}