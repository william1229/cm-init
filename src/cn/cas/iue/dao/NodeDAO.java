package cn.cas.iue.dao;

import java.util.List;

import cn.cas.iue.bean.Node;
import cn.cas.iue.bean.User;

public interface NodeDAO {
	
	public List<Node> getNode(Integer userId, String treeId);
	public List<Node> getChildrenNode(Integer nodeId);
	public Node findById(java.lang.Integer id);
}
