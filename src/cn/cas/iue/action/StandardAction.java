/**
 * 
 */
package cn.cas.iue.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import cn.cas.iue.base.BaseAction;
import cn.cas.iue.bean.Standard;
import cn.cas.iue.bean.User;
import cn.cas.iue.common.Global;
import cn.cas.iue.dao.StandardDAO;
import cn.cas.iue.service.StandardService;
import cn.cas.iue.util.FileUtil;
import cn.cas.iue.util.JSONUtil;

/**
 * @Author: William
 * @ProjectName: cm
 * @ClassName: StandardAction 
 * @Description: TODO
 * @Date: 2014年5月15日 下午2:53:14
 * @Version V1.0
 */
public class StandardAction extends BaseAction implements ServletResponseAware{
	private Integer standardId;
	private String serialNumber;
	private String name;
	private Date date;
	private Date effectiveDate;
	private String state;
	private String path;
	private String file;
	private String fileFileName;
	private int start;
	private int limit;
	private String downloadPath;
	private StandardService standardService;
	private HttpServletResponse response;
	
	public void getStandards() {
		List<Standard> standards = standardService.getStandards(start, limit);
		Long total = standardService.getTotal();
		JsonConfig jsonConfig = JSONUtil.getJsonConfig();
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new JSONUtil());
		JSONArray jArray = JSONArray.fromObject(standards, jsonConfig);
		JSONObject jObject = new JSONObject();
		jObject.put("standards", jArray);
		jObject.put("total", total);
		try {
			responseJson(jObject.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addStandard() {
		//重命名文件名
		String filename = FileUtil.rename(fileFileName);
		String rootPath = Global.ROOT_PATH;
		String path = rootPath + Global.STANDARD_UPLOAD_PATH + filename;
		//创建路径文件夹
		FileUtil.createDir(rootPath + Global.STANDARD_UPLOAD_PATH);
		//上传文件
		FileUtil.upload(file, path);
		
		Standard standard = new Standard();
		standard.setDate(date);
		standard.setEffectiveDate(effectiveDate);
		standard.setName(name);
		standard.setSerialNumber(serialNumber);
		standard.setState(Global.IN_USE);
		standard.setPath(Global.STANDARD_UPLOAD_PATH + filename);
		
		try {
			standardService.addStandard(standard);
			responseJson("{success:true, msg:'保存成功'}");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public void fileDownload() {
		FileUtil.download(response, path);
	}
	
	public void updateState() {
		try {
			standardService.updateStandardState(state ,standardId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				responseJson("success:false, errorMessage:'操作失败，请重新尝试！'");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
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
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
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

	public String getDownloadPath() {
		return downloadPath;
	}

	public void setDownloadPath(String downloadPath) {
		this.downloadPath = downloadPath;
	}

	public StandardService getStandardService() {
		return standardService;
	}

	public void setStandardService(StandardService standardService) {
		this.standardService = standardService;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public Integer getStandardId() {
		return standardId;
	}

	public void setStandardId(Integer standardId) {
		this.standardId = standardId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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
