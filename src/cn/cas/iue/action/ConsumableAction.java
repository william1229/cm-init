/**
 * 
 */
package cn.cas.iue.action;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.Thread.State;
import java.net.URLEncoder;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import cn.cas.iue.base.BaseAction;
import cn.cas.iue.bean.Certificate;
import cn.cas.iue.bean.Consumable;
import cn.cas.iue.bean.GasCsum;
import cn.cas.iue.bean.Instru;
import cn.cas.iue.bean.OutInRec;
import cn.cas.iue.bean.PurchasingRec;
import cn.cas.iue.bean.Standard;
import cn.cas.iue.bean.User;
import cn.cas.iue.common.Global;
import cn.cas.iue.dao.ConsumableDAO;
import cn.cas.iue.dao.PurchasingRecDAO;
import cn.cas.iue.service.ConsumableService;
import cn.cas.iue.service.GasCsumService;
import cn.cas.iue.service.InstruService;
import cn.cas.iue.service.OutInRecService;
import cn.cas.iue.service.PurchasingRecService;
import cn.cas.iue.service.UserService;
import cn.cas.iue.service.impl.OutInRecServiceImpl;
import cn.cas.iue.util.FileUtil;
import cn.cas.iue.util.JSONUtil;
import cn.cas.iue.util.StringUtil;

/**
 * @Author: William
 * @ProjectName: cm
 * @ClassName: ConsumableAction 
 * @Description: TODO
 * @Date: 2014年5月4日 上午10:50:52
 * @Version V1.0
 */
public class ConsumableAction extends BaseAction implements SessionAware, ServletResponseAware{
	private Integer instruId;
	private String instruName;
	private Integer consumableId;
	private String consumableName;
	private String consumableType;
	private String name;
	private Integer userId;
	private String remark;
	private String state;
	private Integer recId;
	private boolean isIn;
	private Date date;
	private Integer quantity;
	private int start;
	private int limit;
	private String query;
	private Consumable consumable;
	private Instru instru;
	private int currentQuantity;
	private boolean flag;
	private String path;
	private String file;
	private String filename;
	private String fileFileName;
	private PurchasingRecService purchasingRecService;
	private OutInRecService outInRecService;
	private ConsumableService consumableService;
	private InstruService instruService;
	private UserService userService;
	private GasCsumService gasCsumService;
	private Map<String, Object> session;
	private HttpServletResponse response;
	
	public void getConsumables() {
		List<Consumable> consumables = new ArrayList<Consumable>();
		Long total = 0L;
		if(query == null || query.equals("")) {
			if(instruId != null) { 
				//仪器耗材
				consumables = consumableService.getIConsumables(start, limit, instruId);
				total = consumableService.getICTotal(instruId);
			} else {
				//公共耗材
				consumables = consumableService.getPConsumables(start, limit);
				total = consumableService.getPCTotal();
			}
		} else  {	
			if(instruId != null) { 
				//仪器耗材
				consumables = consumableService.getIConsumablesByName(start, limit, instruId, query);
				total = 1L;
			} else {
				//公共耗材
				consumables = consumableService.getPConsumablesByName(start, limit, query);
				total = 1L;
			}
		}
		JSONArray jArray = JSONArray.fromObject(consumables, JSONUtil.getJsonConfig());
		JSONObject jObject = new JSONObject();
		jObject.put("consumables", jArray);
		jObject.put("total", total);
		try {
			responseJson(jObject.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addConsumable() {
		consumable = new Consumable();
		consumable.setConsumableName(consumableName);
		consumable.setQuantity(quantity);
		consumable.setConsumableType(consumableType);
		try {
			if(instruId != 0) {
				instru = instruService.getInstruById(instruId);
				consumable.setInstru(instru);
			}
			if(quantity <= 1) {
				boolean isLow = true;
				instruService.updateConsumableState(instruId, isLow);
			}
			consumableService.addConsumable(consumable);
			responseJson("{success:true, msg:'保存成功'}");
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
	
	public void updateInfo() {
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
				Instru instru = instruService.getInstruById(instruId);
				Consumable consumable = consumableService.getConsumableById(consumableId);
				String action = null;
				if(flag) {
					action = "入库";
				} else {
					action = "出库";
				}
				OutInRec outInRec = new OutInRec();
				outInRec.setDate(date);
				outInRec.setQuantity(quantity);
				outInRec.setUser(user);
				outInRec.setInstru(instru);
				outInRec.setConsumable(consumable);
				outInRec.setAction(action);
				outInRecService.addOutInRec(outInRec);
				consumableService.updateConsumableQuantity(currentQuantity, consumableId);
				this.setIsLow();
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
		List<OutInRec> outInRecs = outInRecService.getOutInRecs(start, limit);
		for(OutInRec outInRec : outInRecs) {
			User u = outInRec.getUser();
			Instru i = outInRec.getInstru();
			Consumable c = outInRec.getConsumable();
			
			outInRec.setName(u.getName());
			outInRec.setUserName(u.getUserName());
			
			if(i != null) {
				outInRec.setInstruName(i.getInstruName());
				outInRec.setInstruModel(i.getInstruModel());
			}
			
			outInRec.setConsumableName(c.getConsumableName());
			outInRec.setConsumableType(c.getConsumableType());
		}
		JsonConfig jsonConfig = JSONUtil.getJsonConfig();
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new JSONUtil());
		JSONArray jArray = JSONArray.fromObject(outInRecs, jsonConfig);
		Long total = outInRecService.getTotal();
		JSONObject jObj = new JSONObject();
		jObj.put("outInRecs", jArray);
		jObj.put("total", total);
		try {
			responseJson(jObj.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void delete() {
		consumableService.deleteConsumable(consumableId);
		this.setIsLow();
		try {
			responseJson("{success:true, msg:'删除成功'}");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setIsLow() {
		try{
			boolean hasLow = consumableService.hasLowConsumable(instruId, 1);
			if(hasLow) {
				instruService.updateConsumableState(instruId, hasLow);
			} else {
				instruService.updateConsumableState(instruId, hasLow);
			}
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
	
	public void applyPurchasing() {
		User user = userService.getUserById(userId);
		Instru instru = instruService.getInstruById(instruId);
		Consumable consumable = consumableService.getConsumableById(consumableId);
		PurchasingRec purchasingRec = new PurchasingRec();
		purchasingRec.setConsumable(consumable);
		purchasingRec.setDate(date);
		purchasingRec.setInstru(instru);
		purchasingRec.setQuantity(quantity);
		purchasingRec.setRemark(remark);
		purchasingRec.setState(state);
		purchasingRec.setUser(user);
		purchasingRecService.addPurchasingRec(purchasingRec);
		try {
			responseJson("{success:true, msg:'申请成功,请在采购记录中查看审批结果'}");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void getPurchasingRecs() {
		User user = (User)session.get("user");
		userId = user.getUserId();
		String[] roleNames = user.getRoleNames();
		Long total = 0L;
		List<PurchasingRec> purchasingRecs =  new ArrayList<PurchasingRec>();
		if(StringUtil.isHave(roleNames, Global.SYSTEM_ADMINISTRATOR)) {
			purchasingRecs = purchasingRecService.getPurchasingRecs(start, limit);
			total = purchasingRecService.getTotal();
		} else {
			purchasingRecs = purchasingRecService.getPurchasingRecs(start, limit, userId);
			total = purchasingRecService.getTotal(userId);
			if(StringUtil.isHave(roleNames, Global.CONSUMABLE_ADMINISTRATOR)) {
				String state = Global.PASSED;
				List<PurchasingRec> purchasingRecsPassed = purchasingRecService.getPurchasingRecs(start, limit, state);
				Long totalPassed = purchasingRecService.getTotal(state);
				purchasingRecs.addAll(purchasingRecsPassed);
				total += totalPassed;
			}
		}
		for(PurchasingRec purchasingRec : purchasingRecs) {
			User u = purchasingRec.getUser();
			Instru i = purchasingRec.getInstru();
			Consumable c = purchasingRec.getConsumable();
			
			purchasingRec.setName(u.getName());
			purchasingRec.setUserName(u.getUserName());
			purchasingRec.setUserId(u.getUserId());
			
			if(i != null) {
				purchasingRec.setInstruName(i.getInstruName());
				purchasingRec.setInstruModel(i.getInstruModel());
				purchasingRec.setInstruId(i.getInstruId());
			}
			purchasingRec.setConsumableId(c.getConsumableId());
			purchasingRec.setConsumableName(c.getConsumableName());
			purchasingRec.setConsumableType(c.getConsumableType());
			purchasingRec.setCurrentQuantity(c.getQuantity());
		}
		JsonConfig jsonConfig = JSONUtil.getJsonConfig();
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new JSONUtil());
		JSONArray jArray = JSONArray.fromObject(purchasingRecs, jsonConfig);
		JSONObject jObj = new JSONObject();
		jObj.put("purchasingRecs", jArray);
		jObj.put("total", total);
		try {
			responseJson(jObj.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updatePurchasingState() {
		try {
			purchasingRecService.updateStateById(recId, state);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			try {
				responseJson("{success:false, errorMessage:'申请失败，请重新尝试'}");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	public void updatePurchasingInfo() {
		User user = userService.getUserById(userId);
		Instru instru = instruService.getInstruById(instruId);
		Consumable consumable = consumableService.getConsumableById(consumableId);
		PurchasingRec purchasingRec = purchasingRecService.getPurchasingRecById(recId);
		purchasingRec.setConsumable(consumable);
		purchasingRec.setDate(date);
		purchasingRec.setInstru(instru);
		purchasingRec.setQuantity(quantity);
		purchasingRec.setRemark(remark);
		purchasingRec.setState(state);
		purchasingRec.setUser(user);
		try {
			responseJson("{success:true, msg:'申请成功，请在采购记录中查看审批结果'}");
			purchasingRecService.updatePurchasingRecInfo(purchasingRec);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			try {
				responseJson("{success:false, errorMessage:'申请失败，请重新尝试'}");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	public void addGasCsum() {
		//重命名文件名
		String gasFilename = FileUtil.rename(fileFileName);
		//上传文件路径
		String path = Global.ROOT_PATH + Global.GAS_UPLOAD_PATH + gasFilename;
		//创建路径文件夹
		FileUtil.createDir(Global.ROOT_PATH + Global.GAS_UPLOAD_PATH);
		//上传文件
		FileUtil.upload(file, path);
		try {
			User user = userService.getUserById(userId);
			GasCsum gasCsum = new GasCsum();
			gasCsum.setDate(date);
			gasCsum.setFilename(filename);
			gasCsum.setPath(Global.GAS_UPLOAD_PATH + gasFilename);
			gasCsum.setUser(user);
			gasCsumService.addGasCsum(gasCsum);
			responseJson("{success:true, msg:'保存成功'}");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void getGasCsums() {
		List<GasCsum> gasCsums = gasCsumService.getGasCsums(start, limit);
		for(GasCsum gasCsum : gasCsums) {
			User user = gasCsum.getUser();
			gasCsum.setName(user.getName());
		}
		Long total = gasCsumService.getTotal();
		JsonConfig jsonConfig = JSONUtil.getJsonConfig();
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new JSONUtil());
		JSONArray jArray = JSONArray.fromObject(gasCsums, jsonConfig);
		JSONObject jObject = new JSONObject();
		jObject.put("gasCsums", jArray);
		jObject.put("total", total);
		try {
			responseJson(jObject.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void gasFileDownload() {
		FileUtil.download(response, path); 
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

	public Consumable getConsumable() {
		return consumable;
	}

	public void setConsumable(Consumable consumable) {
		this.consumable = consumable;
	}

	public String getConsumableName() {
		return consumableName;
	}

	public void setConsumableName(String consumableName) {
		this.consumableName = consumableName;
	}

	public ConsumableService getConsumableService() {
		return consumableService;
	}

	public void setConsumableService(ConsumableService consumableService) {
		this.consumableService = consumableService;
	}

	public InstruService getInstruService() {
		return instruService;
	}

	public void setInstruService(InstruService instruService) {
		this.instruService = instruService;
	}

	public Instru getInstru() {
		return instru;
	}

	public void setInstru(Instru instru) {
		this.instru = instru;
	}

	public int getCurrentQuantity() {
		return currentQuantity;
	}

	public void setCurrentQuantity(int currentQuantity) {
		this.currentQuantity = currentQuantity;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getConsumableType() {
		return consumableType;
	}

	public void setConsumableType(String consumableType) {
		this.consumableType = consumableType;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}


	public String getInstruName() {
		return instruName;
	}

	public void setInstruName(String instruName) {
		this.instruName = instruName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public OutInRecService getOutInRecService() {
		return outInRecService;
	}

	public void setOutInRecService(OutInRecService outInRecService) {
		this.outInRecService = outInRecService;
	}

	public boolean isIn() {
		return isIn;
	}

	public void setIn(boolean isIn) {
		this.isIn = isIn;
	}

	public Integer getInstruId() {
		return instruId;
	}

	public void setInstruId(Integer instruId) {
		this.instruId = instruId;
	}

	public Integer getConsumableId() {
		return consumableId;
	}

	public void setConsumableId(Integer consumableId) {
		this.consumableId = consumableId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public PurchasingRecService getPurchasingRecService() {
		return purchasingRecService;
	}

	public void setPurchasingRecService(PurchasingRecService purchasingRecService) {
		this.purchasingRecService = purchasingRecService;
	}
	/* (non-Javadoc)
	 * @see org.apache.struts2.interceptor.SessionAware#setSession(java.util.Map)
	 */
	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session = session;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getRecId() {
		return recId;
	}

	public void setRecId(Integer recId) {
		this.recId = recId;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public GasCsumService getGasCsumService() {
		return gasCsumService;
	}

	public void setGasCsumService(GasCsumService gasCsumService) {
		this.gasCsumService = gasCsumService;
	}

	/* (non-Javadoc)
	 * @see org.apache.struts2.interceptor.ServletResponseAware#setServletResponse(javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}
}
