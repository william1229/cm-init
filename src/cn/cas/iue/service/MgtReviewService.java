package cn.cas.iue.service;

import java.util.List;

import cn.cas.iue.bean.MgtReview;


public interface MgtReviewService {
	public void addMgtReview(MgtReview mgtReview);
	public List<MgtReview> getMgtReviews(int start, int limit);
	public Long getTotal();
}