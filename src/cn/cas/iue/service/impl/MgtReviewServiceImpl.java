package cn.cas.iue.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.cas.iue.bean.MgtReview;
import cn.cas.iue.dao.MgtReviewDAO;
import cn.cas.iue.service.MgtReviewService;

@Component("mgtReviewService")
public class MgtReviewServiceImpl implements MgtReviewService {
	private MgtReviewDAO mgtReviewDAO;

	public void addMgtReview(MgtReview mgtReview) {
		mgtReviewDAO.save(mgtReview);
	}
	
	public List<MgtReview> getMgtReviews(int start, int limit) {
		List<MgtReview> mgtReviews = mgtReviewDAO.findAll(start, limit);
		return mgtReviews;
	}
	
	public Long getTotal() {
		return mgtReviewDAO.getCount();
	}
	
	public MgtReviewDAO getMgtReviewDAO() {
		return mgtReviewDAO;
	}
	@Resource
	public void setMgtReviewDAO(MgtReviewDAO mgtReviewDAO) {
		this.mgtReviewDAO = mgtReviewDAO;
	}
}
