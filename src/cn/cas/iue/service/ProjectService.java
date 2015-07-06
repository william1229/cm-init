package cn.cas.iue.service;

import java.util.List;

import cn.cas.iue.bean.AppraisalRec;
import cn.cas.iue.bean.Project;;

public interface ProjectService {
	public void addProject(Project project);
	public List<Project> getProjects(int start, int limit);
	public Long getTotal();
	public Project getProjectById(Integer projectId);
}