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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import cn.cas.iue.base.BaseAction;
import cn.cas.iue.bean.AppraisalRec;
import cn.cas.iue.bean.ExReview;
import cn.cas.iue.bean.InReview;
import cn.cas.iue.bean.MgtReview;
import cn.cas.iue.bean.User;
import cn.cas.iue.common.Global;
import cn.cas.iue.service.ExReviewService;
import cn.cas.iue.service.InReviewService;
import cn.cas.iue.service.MgtReviewService;
import cn.cas.iue.util.FileUtil;
import cn.cas.iue.util.JSONUtil;

/**
 * @Author: William
 * @ProjectName: cm
 * @ClassName: ReviewAction 
 * @Description: TODO
 * @Date: 2014年6月10日 下午4:49:44
 * @Version V1.0
 */
public class ReviewAction extends BaseAction implements ServletResponseAware{
	private Date date;
	private String path;
	private String names;
	private int start;
	private int limit;
	private String type;
	private String file;
	private String fileFileName;
	private ExReviewService exReviewService;
	private InReviewService inReviewService;
	private MgtReviewService  mgtReviewService;
	private HttpServletResponse response;
	
	public void addReview() {
		//重命名文件名
		String filename = FileUtil.rename(fileFileName);
		String rootPath = Global.ROOT_PATH;
		if(type.equals("in")) {
			path = rootPath + Global.INREVIEW_UPLOAD_PATH + filename;
			//创建路径文件夹
			FileUtil.createDir(rootPath + Global.INREVIEW_UPLOAD_PATH);
			InReview inReview = new InReview();
			inReview.setDate(date);
			inReview.setPath(Global.INREVIEW_UPLOAD_PATH + filename);
			inReviewService.addInReview(inReview);
		} else if(type.equals("ex")) {
			path = rootPath + Global.EXREVIEW_UPLOAD_PATH + filename;
			//创建路径文件夹
			FileUtil.createDir(rootPath + Global.EXREVIEW_UPLOAD_PATH);
			ExReview exReview = new ExReview();
			exReview.setDate(date);
			exReview.setPath(Global.EXREVIEW_UPLOAD_PATH + filename);
			exReview.setNames(names);
			exReviewService.addExReview(exReview);
		} else if(type.equals("mgt")) {
			path = rootPath + Global.MGTREVIEW_UPLOAD_PATH + filename;
			//创建路径文件夹
			FileUtil.createDir(rootPath + Global.MGTREVIEW_UPLOAD_PATH);
			MgtReview mgtReview = new MgtReview();
			mgtReview.setDate(date);
			mgtReview.setPath(Global.MGTREVIEW_UPLOAD_PATH + filename);
			mgtReviewService.addMgtReview(mgtReview);
		}
		//上传文件
		FileUtil.upload(file, path);
		try {
			responseJson("{success:true, msg:'保存成功'}");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void getReviews() {
		Long total = 0L;
		JsonConfig jsonConfig = JSONUtil.getJsonConfig();
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new JSONUtil());
		JSONArray jArray = new JSONArray();
		if(type.equals("in")) {
			List<InReview> reviews = inReviewService.getInReviews(start, limit);
			total = inReviewService.getTotal();
			jArray = JSONArray.fromObject(reviews, jsonConfig);
		} else if(type.equals("ex")) {
			List<ExReview> reviews = exReviewService.getExReviews(start, limit);
			total = exReviewService.getTotal();
			jArray = JSONArray.fromObject(reviews, jsonConfig);
		} else if(type.equals("mgt")) {
			List<MgtReview> reviews = mgtReviewService.getMgtReviews(start, limit);
			total = mgtReviewService.getTotal();
			jArray = JSONArray.fromObject(reviews, jsonConfig);
		}
		JSONObject jObject = new JSONObject();
		jObject.put("reviews", jArray);
		jObject.put("total", total);
		try {
			responseJson(jObject.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void download() {
		FileUtil.download(response, path);
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getNames() {
		return names;
	}
	public void setNames(String names) {
		this.names = names;
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
	public ExReviewService getExReviewService() {
		return exReviewService;
	}
	public void setExReviewService(ExReviewService exReviewService) {
		this.exReviewService = exReviewService;
	}
	public InReviewService getInReviewService() {
		return inReviewService;
	}
	public void setInReviewService(InReviewService inReviewService) {
		this.inReviewService = inReviewService;
	}
	public MgtReviewService getMgtReviewService() {
		return mgtReviewService;
	}
	public void setMgtReviewService(MgtReviewService mgtReviewService) {
		this.mgtReviewService = mgtReviewService;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	/* (non-Javadoc)
	 * @see org.apache.struts2.interceptor.ServletResponseAware#setServletResponse(javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response;
	}
}
