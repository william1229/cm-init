/**
 * 
 */
package cn.cas.iue.action;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import cn.cas.iue.base.BaseAction;
import cn.cas.iue.bean.Consumable;
import cn.cas.iue.bean.Instru;
import cn.cas.iue.bean.OutInRec;
import cn.cas.iue.bean.SSOutInRec;
import cn.cas.iue.bean.SSample;
import cn.cas.iue.bean.SSample;
import cn.cas.iue.bean.User;
import cn.cas.iue.service.SSOutInRecService;
import cn.cas.iue.service.SSampleService;
import cn.cas.iue.service.UserService;
import cn.cas.iue.util.JSONUtil;

/**
 * @Author: William
 * @ProjectName: cm
 * @ClassName: SSampleAction 
 * @Description: TODO
 * @Date: 2014年6月3日 下午4:45:59
 * @Version V1.0
 */
public class SSampleAction extends BaseAction{
	private String standardSN;
	private String sSampleName;
	private String sampleSN;
	private String standardValue;
	private String relativeUncertainty;
	private String specification;
	private String source;
	private String storageRequirements;
	private double quantity;
	private Integer userId;
	private Integer sSampleId;
	private Date date;
	private double currentQuantity;
	private boolean flag;
	private int start;
	private int limit;
	private Date effectiveDate;
	private SSampleService sSampleService;
	private UserService userService;
	private SSOutInRecService sSOutInRecService;
	
	public void addSSample() {
		SSample sSample = new SSample();
		sSample.setDate(date);
		sSample.setEffectiveDate(effectiveDate);
		sSample.setQuantity(quantity);
		sSample.setRelativeUncertainty(relativeUncertainty);
		sSample.setSampleSN(sampleSN);
		sSample.setSource(source);
		sSample.setSpecification(specification);
		sSample.setsSampleName(sSampleName);
		sSample.setStandardSN(standardSN);
		sSample.setStandardValue(standardValue);
		sSample.setStorageRequirements(storageRequirements);
		sSampleService.addSSample(sSample);
		try {
			responseJson("{success:true, msg:'保存成功'}");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void getSSamples() {
		List<SSample> sSamples = sSampleService.getSSamples(start, limit);
		Long total = sSampleService.getTotal();
		JsonConfig jsonConfig = JSONUtil.getJsonConfig();
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new JSONUtil());
		JSONArray jArray = JSONArray.fromObject(sSamples, jsonConfig);
		JSONObject jObject = new JSONObject();
		jObject.put("sSamples", jArray);
		jObject.put("total", total);
		try {
			responseJson(jObject.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateQuantity() {
		String jsonStr = null;
		if(flag) {
			currentQuantity += quantity;
			jsonStr = "{success:true, msg:'入库成功,入库数量:" + quantity + "'}";
		} else {
			currentQuantity -= quantity;
			jsonStr = "{success:true, msg:'出库成功,出库数量:" + quantity + "'}";
		}
		try {
			if(currentQuantity < 0) {
				try {
					responseJson("{success:false, errorMessage:'出库失败,库存量不足'}");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				User user = userService.getUserById(userId);
				SSample sSample = sSampleService.getSSampleById(sSampleId);
				String action = null;
				if(flag) {
					action = "入库";
				} else {
					action = "出库";
				}
				SSOutInRec sSOutInRec = new SSOutInRec();
				sSOutInRec.setAction(action);
				sSOutInRec.setDate(date);
				sSOutInRec.setQuantity(quantity);
				sSOutInRec.setSSample(sSample);
				sSOutInRec.setUser(user);
				sSOutInRecService.addSSOutInRec(sSOutInRec);
				sSampleService.updateSSampleQuantity(currentQuantity, sSampleId);
				responseJson(jsonStr);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			try {
				responseJson("{success:false, errorMessage:'操作失败,请重新操作'}");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	public void getOutInRecs() {
		List<SSOutInRec> sSOutInRecs = sSOutInRecService.getSSOutInRecs(start, limit);
		for(SSOutInRec sSOutInRec : sSOutInRecs) {
			SSample s = sSOutInRec.getSSample();
			User u = sSOutInRec.getUser();
			
			sSOutInRec.setName(u.getName());
			sSOutInRec.setUserName(u.getUserName());
			sSOutInRec.setSampleSN(s.getSampleSN());
			sSOutInRec.setSpecification(s.getSpecification());
			sSOutInRec.setsSampleName(s.getsSampleName());
			sSOutInRec.setStandardSN(s.getStandardSN());
		}
		JsonConfig jsonConfig = JSONUtil.getJsonConfig();
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new JSONUtil());
		JSONArray jArray = JSONArray.fromObject(sSOutInRecs, jsonConfig);
		Long total = sSOutInRecService.getTotal();
		JSONObject jObj = new JSONObject();
		jObj.put("sSOutInRecs", jArray);
		jObj.put("total", total);
		try {
			responseJson(jObj.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getStandardSN() {
		return standardSN;
	}
	public void setStandardSN(String standardSN) {
		this.standardSN = standardSN;
	}
	public String getSSampleName() {
		return sSampleName;
	}
	public void setSSampleName(String sSampleName) {
		this.sSampleName = sSampleName;
	}
	public String getSampleSN() {
		return sampleSN;
	}
	public void setSampleSN(String sampleSN) {
		this.sampleSN = sampleSN;
	}
	public String getStandardValue() {
		return standardValue;
	}
	public void setStandardValue(String standardValue) {
		this.standardValue = standardValue;
	}
	public String getRelativeUncertainty() {
		return relativeUncertainty;
	}
	public void setRelativeUncertainty(String relativeUncertainty) {
		this.relativeUncertainty = relativeUncertainty;
	}
	public String getSpecification() {
		return specification;
	}
	public void setSpecification(String specification) {
		this.specification = specification;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getStorageRequirements() {
		return storageRequirements;
	}
	public void setStorageRequirements(String storageRequirements) {
		this.storageRequirements = storageRequirements;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public SSampleService getsSampleService() {
		return sSampleService;
	}

	public void setsSampleService(SSampleService sSampleService) {
		this.sSampleService = sSampleService;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getSSampleId() {
		return sSampleId;
	}

	public void setSSampleId(Integer sSampleId) {
		this.sSampleId = sSampleId;
	}

	public double getCurrentQuantity() {
		return currentQuantity;
	}

	public void setCurrentQuantity(double currentQuantity) {
		this.currentQuantity = currentQuantity;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public SSOutInRecService getsSOutInRecService() {
		return sSOutInRecService;
	}

	public void setsSOutInRecService(SSOutInRecService sSOutInRecService) {
		this.sSOutInRecService = sSOutInRecService;
	}
	
}
