package cn.cas.iue.service;

import java.util.List;

import cn.cas.iue.bean.AppraisalRec;
import cn.cas.iue.bean.Sample;


public interface SampleService {
	public void addSample(Sample sample);
	public List<Sample> getSamples(int start, int limit);
	public Long getTotal();
}