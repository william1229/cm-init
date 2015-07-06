package cn.cas.iue.service;

import java.util.List;

import cn.cas.iue.bean.Node;

public interface NodeService {
	public List<Node> getNodeById(Integer userId, String treeId);
	public List<Node> getChild(Integer nodeId);
	public Node getNodeById(Integer nodeId);
}