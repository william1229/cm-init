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
import java.nio.channels.FileChannel;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import cn.cas.iue.base.BaseAction;
import cn.cas.iue.bean.AppraisalRec;
import cn.cas.iue.bean.Certificate;
import cn.cas.iue.bean.User;
import cn.cas.iue.common.Global;
import cn.cas.iue.service.AppraisalRecService;
import cn.cas.iue.service.UserService;
import cn.cas.iue.util.FileUtil;
import cn.cas.iue.util.JSONUtil;

/**
 * @Author: William
 * @ProjectName: cm
 * @ClassName: AppraisalRecAction 
 * @Description: TODO
 * @Date: 2014年5月15日 上午9:12:29
 * @Version V1.0
 */
public class AppraisalRecAction extends BaseAction implements ServletResponseAware{
	private Integer userId;
	private String name;
	private Date date;
	private String file;
	private String fileFileName;
	private String path;
	private int start;
	private int limit;
	private UserService userService;
	private AppraisalRecService appraisalRecService;
	private HttpServletResponse response;
	
	public void getAppraisalRecs() {
		List<AppraisalRec> appraisalRecs = appraisalRecService.getAppraisalRecs(start, limit);
		for(AppraisalRec appraisalRec : appraisalRecs) {
			User user = appraisalRec.getUser();
			appraisalRec.setUserName(user.getUserName());
			appraisalRec.setName(user.getName());;
		}
		Long total = appraisalRecService.getTotal();
		JsonConfig jsonConfig = JSONUtil.getJsonConfig();
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new JSONUtil());
		JSONArray jArray = JSONArray.fromObject(appraisalRecs, jsonConfig);
		JSONObject jObject = new JSONObject();
		jObject.put("appraisalRecs", jArray);
		jObject.put("total", total);
		try {
			responseJson(jObject.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addAppraisalRec() {
		//重命名文件名
		String filename = FileUtil.rename(fileFileName);
		//上传文件路径
		String path = Global.ROOT_PATH + Global.APPRAISAL_UPLOAD_PATH + filename;
		//创建路径文件夹
		FileUtil.createDir(Global.ROOT_PATH + Global.APPRAISAL_UPLOAD_PATH);
		//上传文件
		FileUtil.upload(file, path);
		try {
			User user = userService.getUserById(userId);
			AppraisalRec appraisalRec = new AppraisalRec();
			appraisalRec.setDate(date);
			appraisalRec.setPath(Global.APPRAISAL_UPLOAD_PATH + filename);
			appraisalRec.setUser(user);
			appraisalRecService.addAppraisalRec(appraisalRec);
			responseJson("{success:true, msg:'保存成功'}");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public void tableDownload() {
		FileUtil.download(response, path);
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
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public AppraisalRecService getAppraisalRecService() {
		return appraisalRecService;
	}

	public void setAppraisalRecService(AppraisalRecService appraisalRecService) {
		this.appraisalRecService = appraisalRecService;
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
