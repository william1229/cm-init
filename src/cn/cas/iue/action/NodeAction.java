/**
 * 
 */
package cn.cas.iue.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;
import cn.cas.iue.base.BaseAction;
import cn.cas.iue.bean.Node;
import cn.cas.iue.bean.User;
import cn.cas.iue.service.NodeService;
import cn.cas.iue.service.UserService;
import cn.cas.iue.util.JSONUtil;

/**
 * @Author: William
 * @ProjectName: cm
 * @ClassName: NodeAction 
 * @Description: TODO
 * @Date: 2014年3月19日 上午11:36:23
 * @Version V1.0
 */
public class NodeAction extends BaseAction implements SessionAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2035589517081133252L;
	private String id;
	private String text;
	private boolean leaf;
	private String treeId;
	private Integer userId;
	private NodeService nodeService;
	private Map<String, Object> session;
	
	public void getNodes() {
		try {
			User user = (User)session.get("user");
			userId = user.getUserId();
			List<Node> nodes = new ArrayList<Node>();
			nodes = nodeService.getNodeById(userId, treeId);
			String TreeJsonStr = TreeBuilder(nodes);
			responseJson(TreeJsonStr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String TreeBuilder(List<Node> nodes) { //构建树的json字符串
		Map<Integer, Node> pNodes = new HashMap<Integer, Node>();
		int i = 0;
		for(Node node : nodes) {
			Integer pId = node.getPid();
			Node pNode;
			Node rNode = new Node();
			if(pId != null) {
				if(!pNodes.containsKey(pId)) {
					pNode = nodeService.getNodeById(pId);
					pNodes.put(pId, pNode);
				} else {
					pNode = pNodes.get(pId);
				}
				pNode.getChildren().add(node);
			} else{
				pNodes.put(i--, node);
			} 
		}
		JSONArray jArray = JSONArray.fromObject(pNodes.values(), JSONUtil.getJsonConfig());
		return jArray.toString();
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isLeaf() {
		return leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	public NodeService getNodeService() {
		return nodeService;
	}

	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}

	/* (non-Javadoc)
	 * @see org.apache.struts2.interceptor.SessionAware#setSession(java.util.Map)
	 */
	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session = session;
	}
	
	public String getTreeId() {
		return treeId;
	}

	public void setTreeId(String treeId) {
		this.treeId = treeId;
	}
}
