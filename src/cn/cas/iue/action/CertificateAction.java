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
import cn.cas.iue.bean.Certificate;
import cn.cas.iue.bean.OutInRec;
import cn.cas.iue.bean.User;
import cn.cas.iue.service.CertificateService;
import cn.cas.iue.service.UserService;
import cn.cas.iue.util.JSONUtil;

/**
 * @Author: William
 * @ProjectName: cm
 * @ClassName: CertificateAction 
 * @Description: TODO
 * @Date: 2014年6月3日 上午10:17:50
 * @Version V1.0
 */
public class CertificateAction  extends BaseAction {
	private String certificateName;
	private String type;
	private Date date;
	private String authority;
	private Integer userId;
	private int start;
	private int limit;
	private UserService userService;
	private CertificateService certificateService;
	
	public void addCertificate() {
		User user = userService.getUserById(userId);	
		Certificate certificate = new Certificate();
		certificate.setAuthority(authority);
		certificate.setCertificateName(certificateName);
		certificate.setDate(date);
		certificate.setType(type);
		certificate.setUser(user);
		certificateService.addCertificate(certificate);
		try {
			responseJson("{success:true, msg:'保存成功'}");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void getCertificates() {
		List<Certificate> certificates = certificateService.getCertificates(start, limit);
		for(Certificate certificate : certificates) {
			User user = certificate.getUser();
			certificate.setUserName(user.getUserName());
			certificate.setName(user.getName());;
		}
		Long total = certificateService.getTotal();
		JsonConfig jsonConfig = JSONUtil.getJsonConfig();
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new JSONUtil());
		JSONArray jArray = JSONArray.fromObject(certificates, jsonConfig);
		JSONObject jObject = new JSONObject();
		jObject.put("certificates", jArray);
		jObject.put("total", total);
		try {
			responseJson(jObject.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getCertificateName() {
		return certificateName;
	}

	public void setCertificateName(String certificateName) {
		this.certificateName = certificateName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
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

	public CertificateService getCertificateService() {
		return certificateService;
	}

	public void setCertificateService(CertificateService certificateService) {
		this.certificateService = certificateService;
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
}
