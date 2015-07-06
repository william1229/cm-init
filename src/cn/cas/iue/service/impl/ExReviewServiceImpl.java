package cn.cas.iue.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.cas.iue.bean.ExReview;
import cn.cas.iue.dao.ExReviewDAO;
import cn.cas.iue.service.ExReviewService;

@Component("exReviewService")
public class ExReviewServiceImpl implements ExReviewService {
	private ExReviewDAO exReviewDAO;

	public void addExReview(ExReview exReview) {
		exReviewDAO.save(exReview);
	}
	
	public List<ExReview> getExReviews(int start, int limit) {
		List<ExReview> exReviews = exReviewDAO.findAll(start, limit);
		return exReviews;
	}
	
	public Long getTotal() {
		return exReviewDAO.getCount();
	}
	
	public ExReviewDAO getExReviewDAO() {
		return exReviewDAO;
	}
	@Resource
	public void setExReviewDAO(ExReviewDAO exReviewDAO) {
		this.exReviewDAO = exReviewDAO;
	}
}
