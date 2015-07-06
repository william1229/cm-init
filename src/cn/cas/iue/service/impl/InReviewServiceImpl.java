package cn.cas.iue.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.cas.iue.bean.InReview;
import cn.cas.iue.dao.InReviewDAO;
import cn.cas.iue.service.InReviewService;

@Component("inReviewService")
public class InReviewServiceImpl implements InReviewService {
	private InReviewDAO inReviewDAO;

	public void addInReview(InReview inReview) {
		inReviewDAO.save(inReview);
	}
	
	public List<InReview> getInReviews(int start, int limit) {
		List<InReview> inReviews = inReviewDAO.findAll(start, limit);
		return inReviews;
	}
	
	public Long getTotal() {
		return inReviewDAO.getCount();
	}
	
	public InReviewDAO getInReviewDAO() {
		return inReviewDAO;
	}
	@Resource
	public void setInReviewDAO(InReviewDAO inReviewDAO) {
		this.inReviewDAO = inReviewDAO;
	}
}
