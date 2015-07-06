package cn.cas.iue.service;

import java.util.List;

import cn.cas.iue.bean.Certificate;
import cn.cas.iue.bean.Consumable;

public interface CertificateService {
	public void addCertificate(Certificate certificate);
	public List<Certificate> getCertificates(int start, int limit);
	public Long getTotal();
}