package cn.cas.iue.service;

import java.util.List;

import cn.cas.iue.bean.ExReview;


public interface ExReviewService {
	public void addExReview(ExReview exReview);
	public List<ExReview> getExReviews(int start, int limit);
	public Long getTotal();
}