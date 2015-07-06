package cn.cas.iue.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.cas.iue.bean.TrainingRec;
import cn.cas.iue.bean.User;
import cn.cas.iue.dao.TrainingRecDAO;
import cn.cas.iue.service.TrainingRecService;

@Component("trainingRecService")
public class TrainingRecServiceImpl implements TrainingRecService {
	private TrainingRecDAO trainingRecDAO;

	public void addTrainingRec(TrainingRec trainingRec) {
		trainingRecDAO.save(trainingRec);
	}
	
	public List<TrainingRec> getTrainingRecs(int start, int limit) {
		List<TrainingRec> trainingRecs = trainingRecDAO.findAll(start, limit);
		return trainingRecs;
	}
	
	public Long getTotal() {
		return trainingRecDAO.getCount();
	}
	
	public TrainingRecDAO getTrainingRecDAO() {
		return trainingRecDAO;
	}
	@Resource
	public void setTrainingRecDAO(TrainingRecDAO trainingRecDAO) {
		this.trainingRecDAO = trainingRecDAO;
	}
}
