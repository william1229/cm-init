package cn.cas.iue.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.cas.iue.bean.Certificate;
import cn.cas.iue.bean.User;
import cn.cas.iue.dao.CertificateDAO;
import cn.cas.iue.service.CertificateService;

@Component("certificateService")
public class CertificateServiceImpl implements CertificateService {
	private CertificateDAO certificateDAO;

	public void addCertificate(Certificate certificate) {
		certificateDAO.save(certificate);
	}
	
	public List<Certificate> getCertificates(int start, int limit) {
		List<Certificate> certificates = certificateDAO.findAll(start, limit);
		return certificates;
	}
	
	public Long getTotal() {
		return certificateDAO.getCount();
	}
	
	public CertificateDAO getCertificateDAO() {
		return certificateDAO;
	}
	@Resource
	public void setCertificateDAO(CertificateDAO certificateDAO) {
		this.certificateDAO = certificateDAO;
	}
}
