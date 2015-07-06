package cn.cas.iue.service.impl;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.cas.iue.bean.Node;
import cn.cas.iue.dao.NodeDAO;
import cn.cas.iue.service.NodeService;

@Component("nodeService")
public class NodeServiceImpl implements NodeService {
	private NodeDAO nodeDAO;
	
	public List<Node> getNodeById(Integer userId, String treeId) {
		return nodeDAO.getNode(userId, treeId);
	}
	
	public List<Node> getChild(Integer nodeId) {
		return nodeDAO.getChildrenNode(nodeId);
	}
	
	public Node getNodeById(Integer nodeId) {
		return (Node) nodeDAO.findById(nodeId);
	}
	public NodeDAO getNodeDAO() {
		return nodeDAO;
	}
	@Resource
	public void setNodeDAO(NodeDAO nodeDAO) {
		this.nodeDAO = nodeDAO;
	}
}
