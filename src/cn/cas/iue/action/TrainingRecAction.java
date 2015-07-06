/**
 * 
 */
package cn.cas.iue.action;

import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.channels.FileChannel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javassist.expr.NewArray;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.JSONUtils;

import org.apache.commons.io.input.ReaderInputStream;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;

import com.opensymphony.xwork2.ActionContext;

import cn.cas.iue.base.BaseAction;
import cn.cas.iue.bean.TrainingRec;
import cn.cas.iue.bean.User;
import cn.cas.iue.common.Global;
import cn.cas.iue.service.TrainingRecService;
import cn.cas.iue.service.UserService;
import cn.cas.iue.util.FileUtil;
import cn.cas.iue.util.JSONUtil;

/**
 * @Author: William
 * @ProjectName: cm
 * @ClassName: TrainingRecAcion
 * @Description: TODO
 * @Date: 2014年4月9日 下午3:22:36
 * @Version V1.0
 */
public class TrainingRecAction extends BaseAction implements ServletResponseAware {
	private static final long serialVersionUID = 7889144596697557616L;
	private Integer userId;
	private String name;
	private Date startDate;
	private Date endDate;
	private String unit;
	private String location;
	private String content;
	private String file;
	private String path;
	private String fileFileName;
	private TrainingRecService trainingRecService;
	private TrainingRec trainingRec;
	private UserService userService;
	private int start;
	private int limit;
	private HttpServletResponse response;

	public void getTrainingRecs() {
		try {
			List<TrainingRec> trainingRecs = trainingRecService.getTrainingRecs(start,limit);
			for(TrainingRec trainingRec : trainingRecs) {
				User user = trainingRec.getUser();
				trainingRec.setName(user.getName());
				trainingRec.setUserName(user.getUserName());
			}
			JsonConfig jsonConfig = JSONUtil.getJsonConfig();
			jsonConfig.registerJsonValueProcessor(java.util.Date.class, new JSONUtil());
			JSONArray jArray = JSONArray.fromObject(trainingRecs, jsonConfig);
			Long total = trainingRecService.getTotal();
			JSONObject jObj = new JSONObject();
			jObj.put("trainingRecs", jArray);
			jObj.put("total", total);
			responseJson(jObj.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addTrainingRec() {
		//重命名文件名
		String filename = FileUtil.rename(fileFileName);
		//上传文件路径
		String rootPath = Global.ROOT_PATH;
		String path = rootPath + Global.SUMMARY_UPLOAD_PATH + filename;
		//创建路径文件夹
		FileUtil.createDir(rootPath + Global.SUMMARY_UPLOAD_PATH);
		//上传文件
		FileUtil.upload(file, path);
		//根据userId查找user
		User user = userService.getUserById(userId);
		//数据库插入数据
		trainingRec = new TrainingRec();
		trainingRec.setStartDate(startDate);
		trainingRec.setEndDate(endDate);
		trainingRec.setUnit(unit);
		trainingRec.setLocation(location);
		trainingRec.setContent(content);
		trainingRec.setPath(Global.SUMMARY_UPLOAD_PATH + filename);
		trainingRec.setUser(user);
		
		try {
			trainingRecService.addTrainingRec(trainingRec);
			responseJson("{success:true, msg:'保存成功'}");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				responseJson("{success:false, errorMessage:'保存失败，请重新尝试'}");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	public void summaryDownload() {
		FileUtil.download(response, path);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public TrainingRecService getTrainingRecService() {
		return trainingRecService;
	}

	public void setTrainingRecService(TrainingRecService trainingRecService) {
		this.trainingRecService = trainingRecService;
	}

	public TrainingRec getTrainingRec() {
		return trainingRec;
	}

	public void setTrainingRec(TrainingRec trainingRec) {
		this.trainingRec = trainingRec;
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

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	/* (non-Javadoc)
	 * @see org.apache.struts2.interceptor.ServletResponseAware#setServletResponse(javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response;
	}
}
