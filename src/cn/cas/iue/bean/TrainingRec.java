package cn.cas.iue.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.cas.iue.bean.Impl.AbstractTrainingRec;

/**
 * Trainingrec entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "trainingrec", catalog = "cm")
public class TrainingRec extends AbstractTrainingRec implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public TrainingRec() {
	}

	/** minimal constructor */
	public TrainingRec(Date startDate, Date endDate, String unit,
			String location, String content) {
		super(startDate, endDate, unit, location, content);
	}

	/** full constructor */
	public TrainingRec(User user, Date startDate, Date endDate,
			String unit, String location, String content, String path) {
		super(user, startDate, endDate, unit, location, content, path);
	}

}
