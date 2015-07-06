package cn.cas.iue.service;

import java.util.List;

import cn.cas.iue.bean.TrainingRec;

public interface TrainingRecService {
	public void addTrainingRec(TrainingRec trainingRec);
	public List<TrainingRec> getTrainingRecs(int start, int limit);
	public Long getTotal();
}