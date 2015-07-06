package cn.cas.iue.dao;

import cn.cas.iue.bean.Urrel;

/**
 * A data access object (DAO) providing persistence and search support for Urrel
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see cn.cas.iue.bean.Urrel
 * @author MyEclipse Persistence Tools
 */
public interface UrrelDAO {
	public void save(Urrel urrel);
	public void delete(Urrel urrel);
}