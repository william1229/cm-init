package cn.cas.iue.dao;

import java.util.List;

import cn.cas.iue.bean.Certificate;
import cn.cas.iue.bean.Consumable;
import cn.cas.iue.bean.Instru;
import cn.cas.iue.bean.Node;
import cn.cas.iue.bean.User;

public interface CertificateDAO {
	public List<Certificate> findAll(final int start, final int limit);
	public void save(Certificate transientInstance);
	public void delete(Certificate persistentInstance);
	public Long getCount();
}
