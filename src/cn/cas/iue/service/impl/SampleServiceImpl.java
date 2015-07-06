package cn.cas.iue.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.cas.iue.bean.Sample;
import cn.cas.iue.bean.User;
import cn.cas.iue.dao.SampleDAO;
import cn.cas.iue.service.SampleService;

@Component("sampleService")
public class SampleServiceImpl implements SampleService {
	private SampleDAO sampleDAO;

	public void addSample(Sample sample) {
		sampleDAO.save(sample);
	}
	
	public List<Sample> getSamples(int start, int limit) {
		List<Sample> samples = sampleDAO.findAll(start, limit);
		return samples;
	}
	
	public Long getTotal() {
		return sampleDAO.getCount();
	}
	
	public SampleDAO getSampleDAO() {
		return sampleDAO;
	}
	@Resource
	public void setSampleDAO(SampleDAO sampleDAO) {
		this.sampleDAO = sampleDAO;
	}
}
