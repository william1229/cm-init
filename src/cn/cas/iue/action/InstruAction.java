/**
 * 
 */
package cn.cas.iue.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import cn.cas.iue.base.BaseAction;
import cn.cas.iue.bean.Instru;
import cn.cas.iue.bean.Metrical;
import cn.cas.iue.bean.RepairRec;
import cn.cas.iue.bean.User;
import cn.cas.iue.common.Global;
import cn.cas.iue.service.InstruService;
import cn.cas.iue.service.MetricalService;
import cn.cas.iue.service.RepairRecService;
import cn.cas.iue.service.UserService;
import cn.cas.iue.util.JSONUtil;
import cn.cas.iue.util.StringUtil;

/**
 * @Author: William
 * @ProjectName: cm
 * @ClassName: InstruAction 
 * @Description: TODO
 * @Date: 2014年4月30日 上午11:23:12
 * @Version V1.0
 */
public class InstruAction extends BaseAction implements SessionAware{
	private Integer instruId;
	private String instruName;
	private String instruModel;
	private String instruNum;
	private String manufacturer;
	private String country;
	private String distributor;
	private Date date;
	private String money;
	private String location;
	private String instruAdmin;
	private Integer userId;
	private String content;
	private String name;
	private Date effectiveDate;
	private String remark;
	private String serialNumber;
	private String unit;
	private double cost;
	private int start;
	private int limit;
	private InstruService instruService;
	private UserService userService;
	private Instru instru;
	private Map<String, Object> session;
	private RepairRecService repairRecService;
	private MetricalService metricalService;
	
	public void getInstrus() {
		try {
			User user = (User)session.get("user");
			userId = user.getUserId();
			String[] roleNames = user.getRoleNames();
			Long total = 0L;
			List<Instru> instrus = new ArrayList<Instru>();
			if(StringUtil.isHave(roleNames, Global.SYSTEM_ADMINISTRATOR)) {
				instrus = instruService.getInstrus(start, limit);
				for(Instru instru : instrus) {
					User u = instru.getUser();
					if(u != null) {
						instru.setName(u.getName());
						instru.setUserId(u.getUserId());
					}
				}
				total = instruService.getTotal();
			} else {
				instrus = instruService.getInstruByUserId(start, limit, userId);
				total = instruService.getTotal(userId);
			}
			JsonConfig jsonConfig = JSONUtil.getJsonConfig();
			jsonConfig.registerJsonValueProcessor(java.util.Date.class, new JSONUtil());
			JSONArray jArray = JSONArray.fromObject(instrus, jsonConfig);
			JSONObject jObject = new JSONObject();
			jObject.put("instrus", jArray);
			jObject.put("total", total);
			responseJson(jObject.toString());
		} catch(RuntimeException re) {
			System.out.println(re);
		}
		
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addInstru() {
		instru = new Instru();
		instru.setInstruName(instruName);
		instru.setInstruModel(instruModel);
		instru.setInstruNum(instruNum);
		instru.setManufacturer(manufacturer);
		instru.setCountry(country);
		instru.setDistributor(distributor);
		instru.setDate(date);
		instru.setMoney(money);
		instru.setLocation(location);
		instruService.addInstru(instru);
		try {
			responseJson("{success:true, msg:'添加成功,请联系管理员授权'}");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateInfo() {
		try {
			instru = instruService.getInstruById(instruId);
			instru.setInstruName(instruName);
			instru.setInstruModel(instruModel);
			instru.setInstruNum(instruNum);
			instru.setManufacturer(manufacturer);
			instru.setCountry(country);
			instru.setDistributor(distributor);
			instru.setDate(date);
			instru.setMoney(money);
			instru.setLocation(location);
			instruService.updateInstruInfo(instru);
			responseJson("{success:true, msg:'更新成功'}");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void accreditOperation() {
		try {
			instruService.updateName(userId, instruId);
			if(userId == null) {
				responseJson("{success:true, msg:'撤销成功'}");
			} else {
				responseJson("{success:true, msg:'授权成功'}");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			try {
				responseJson("{success:false, errorMessage:'操作失败，请重新尝试'}");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	public void addRepairRec() {
		Instru instru = instruService.getInstruById(instruId);
		RepairRec repairRec = new RepairRec();
		repairRec.setContent(content);
		repairRec.setCost(cost);
		repairRec.setDate(date);
		repairRec.setInstru(instru);
		repairRec.setName(name);
		repairRecService.addRepairRec(repairRec);
		try {
			responseJson("{success:true, msg:'保存成功'}");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void getRepairRecs() {
		List<RepairRec> repairRecs = repairRecService.getRepairRecs(start, limit);
		for(RepairRec repairRec : repairRecs) {
			Instru instru = repairRec.getInstru();
			repairRec.setInstruName(instru.getInstruName());
			repairRec.setInstruModel(instru.getInstruModel());
			
		}
		Long total = repairRecService.getTotal();
		JsonConfig jsonConfig = JSONUtil.getJsonConfig();
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new JSONUtil());
		JSONArray jArray = JSONArray.fromObject(repairRecs, jsonConfig);
		JSONObject jObject = new JSONObject();
		jObject.put("repairRecs", jArray);
		jObject.put("total", total);
		try {
			responseJson(jObject.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addMetrical() {
		Instru instru = instruService.getInstruById(instruId);
		Metrical metrical = new Metrical();
		metrical.setDate(date);
		metrical.setEffectiveDate(effectiveDate);
		metrical.setInstru(instru);
		metrical.setRemark(remark);
		metrical.setSerialNumber(serialNumber);
		metrical.setUnit(unit);
		metricalService.addMetrical(metrical);
		try {
			responseJson("{success:true, msg:'保存成功'}");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void getMetricals() {
		List<Metrical> metricals = metricalService.getMetricals(start, limit);
		for(Metrical metrical : metricals) {
			Instru instru = metrical.getInstru();
			metrical.setInstruName(instru.getInstruName());
			metrical.setInstruModel(instru.getInstruModel());
			
		}
		Long total = metricalService.getTotal();
		JsonConfig jsonConfig = JSONUtil.getJsonConfig();
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new JSONUtil());
		JSONArray jArray = JSONArray.fromObject(metricals, jsonConfig);
		JSONObject jObject = new JSONObject();
		jObject.put("metricals", jArray);
		jObject.put("total", total);
		try {
			responseJson(jObject.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getInstruName() {
		return instruName;
	}
	public void setInstruName(String instruName) {
		this.instruName = instruName;
	}
	public String getInstruModel() {
		return instruModel;
	}
	public void setInstruModel(String instruModel) {
		this.instruModel = instruModel;
	}
	public String getInstruNum() {
		return instruNum;
	}
	public void setInstruNum(String instruNum) {
		this.instruNum = instruNum;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getInstruAdmin() {
		return instruAdmin;
	}
	public void setInstruAdmin(String instruAdmin) {
		this.instruAdmin = instruAdmin;
	}

	public InstruService getInstruService() {
		return instruService;
	}
	public void setInstruService(InstruService instruService) {
		this.instruService = instruService;
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

	public UserService getUserService() {
		return userService;
	}
	
	public RepairRecService getRepairRecService() {
		return repairRecService;
	}
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getDistributor() {
		return distributor;
	}

	public void setDistributor(String distributor) {
		this.distributor = distributor;
	}

	public Instru getInstru() {
		return instru;
	}

	public void setInstru(Instru instru) {
		this.instru = instru;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	/* (non-Javadoc)
	 * @see org.apache.struts2.interceptor.SessionAware#setSession(java.util.Map)
	 */
	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session = session;
	}

	public Integer getInstruId() {
		return instruId;
	}

	public void setInstruId(Integer instruId) {
		this.instruId = instruId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public void setRepairRecService(RepairRecService repairRecService) {
		this.repairRecService = repairRecService;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public MetricalService getMetricalService() {
		return metricalService;
	}

	public void setMetricalService(MetricalService metricalService) {
		this.metricalService = metricalService;
	}
}
