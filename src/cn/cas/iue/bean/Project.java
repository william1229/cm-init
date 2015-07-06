package cn.cas.iue.bean;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.cas.iue.bean.Impl.AbstractProject;

/**
 * Project entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "project", catalog = "cm")
public class Project extends AbstractProject implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Project() {
	}

	/** minimal constructor */
	public Project(String projectSN, String projectName, Date date,
			String unit, String telephone, double money, String contract) {
		super(projectSN, projectName, date, unit, telephone, money, contract);
	}

	/** full constructor */
	public Project(String projectSN, String projectName, Date date,
			String unit, String telephone, double money, String contract,
			Set<Sample> samples, Set<Report> reports) {
		super(projectSN, projectName, date, unit, telephone, money, contract,
				samples, reports);
	}

}
