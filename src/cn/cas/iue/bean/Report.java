package cn.cas.iue.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.cas.iue.bean.Impl.AbstractReport;

/**
 * Report entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "report", catalog = "cm")
public class Report extends AbstractReport implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Report() {
	}

	/** full constructor */
	public Report(Project project, Date reportDate, String report) {
		super(project, reportDate, report);
	}

}
