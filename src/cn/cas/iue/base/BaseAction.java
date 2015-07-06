package cn.cas.iue.base;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public int pageSize=10;
	
	public int currentPageNum=1;
	
	public long totalCount;
	
	public int totalPageNum;
	

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPageNum() {
		return currentPageNum;
	}

	public void setCurrentPageNum(int currentPageNum) {
		this.currentPageNum = currentPageNum;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPageNum() {
		return totalPageNum;
	}

	public void setTotalPageNum(int totalPageNum) {
		this.totalPageNum = totalPageNum;
	}

	public HttpServletRequest getRequest() {
		return (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	}

	public HttpServletResponse getResponse() {
		return (HttpServletResponse) ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
	}
	
	public ServletContext getContext() {
		return (ServletContext)ActionContext.getContext().get(ServletActionContext.SERVLET_CONTEXT);
	}
	public void responseJson(String jsonStr) throws IOException{ //输出字符串
		getResponse().setContentType("text/html;charset=utf-8");
		//getResponse().setCharacterEncoding("UTF-8");
		getResponse().getWriter().write(jsonStr);
		getResponse().getWriter().flush();
		
	}

    public void outJson(Object obj) throws IOException{  //把对象转换成JSON格式
    	responseJson(JSONObject.fromObject(obj).toString());
    }

    public void outJsonArray(Object array) throws IOException{ //把数组转换成JSON格式
	   responseJson(JSONArray.fromObject(array).toString());
    }
    

}
