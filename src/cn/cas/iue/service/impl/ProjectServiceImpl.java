package cn.cas.iue.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.cas.iue.bean.Instru;
import cn.cas.iue.bean.Project;
import cn.cas.iue.dao.ProjectDAO;
import cn.cas.iue.service.ProjectService;

@Component("projectService")
public class ProjectServiceImpl implements ProjectService {
	private ProjectDAO projectDAO;

	public void addProject(Project project) {
		projectDAO.save(project);
	}
	
	public List<Project> getProjects(int start, int limit) {
		List<Project> projects = projectDAO.findAll(start, limit);
		return projects;
	}
	
	public Project getProjectById(Integer projectId) {
		return projectDAO.findById(projectId);
	}
	
	public Long getTotal() {
		return projectDAO.getCount();
	}
	
	public ProjectDAO getProjectDAO() {
		return projectDAO;
	}
	@Resource
	public void setProjectDAO(ProjectDAO projectDAO) {
		this.projectDAO = projectDAO;
	}
}
