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
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import cn.cas.iue.base.BaseAction;
import cn.cas.iue.bean.Sample;
import cn.cas.iue.bean.Project;
import cn.cas.iue.bean.Project;
import cn.cas.iue.bean.Report;
import cn.cas.iue.bean.Sample;
import cn.cas.iue.bean.User;
import cn.cas.iue.common.Global;
import cn.cas.iue.dao.ProjectDAO;
import cn.cas.iue.service.ProjectService;
import cn.cas.iue.service.ReportService;
import cn.cas.iue.service.SampleService;
import cn.cas.iue.util.FileUtil;
import cn.cas.iue.util.JSONUtil;

/**
 * @Author: William
 * @ProjectName: cm
 * @ClassName: ProjectAction 
 * @Description: TODO
 * @Date: 2014年6月9日 下午3:00:25
 * @Version V1.0
 */
public class ProjectAction extends BaseAction implements ServletResponseAware{
	private Integer projectId;
	private Integer reportId;
	private String projectSN;
	private String projectName;
	private Date date;
	private String unit;
	private String telephone;
	private double money;
	private Date reportDate;
	private String contract;
	private String report;
	private String sampleSN;
	private String state;
	private int quantity;
	private String path;
	private String name;
	private String contractFileName;
	private String reportFileName;
	private int start;
	private int limit;
	private ProjectService projectService;
	private SampleService sampleService;
	private ReportService reportService;
	private HttpServletResponse response;
	
	public void getProjects() {
		List<Project> projects = projectService.getProjects(start, limit);
		for (Project project : projects) {
			Set<Report> reports = project.getReports();
			if(reports.size() != 0) {
				Iterator<Report> iterator = reports.iterator();
				while(iterator.hasNext()) {
					Report report = iterator.next();
					project.setReportDate(report.getReportDate());
					project.setReport(report.getReport());
					project.setReportId(report.getReportId());
				}
			}
		}
		Long total = projectService.getTotal();
		JsonConfig jsonConfig = JSONUtil.getJsonConfig();
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new JSONUtil());
		JSONArray jArray = JSONArray.fromObject(projects, jsonConfig);
		JSONObject jObject = new JSONObject();
		jObject.put("projects", jArray);
		jObject.put("total", total);
		try {
			responseJson(jObject.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}
	
	public void addProject() {
		//重命名文件名
		String filename = FileUtil.rename(contractFileName);
		//上传文件路径
		String path = Global.ROOT_PATH + Global.CONTRACT_UPLOAD_PATH + filename;
		//创建路径文件夹
		FileUtil.createDir(Global.ROOT_PATH + Global.CONTRACT_UPLOAD_PATH);
		//上传文件
		FileUtil.upload(contract, path);
		try {
			Project project = new Project();
			project.setContract(Global.CONTRACT_UPLOAD_PATH + filename);
			project.setDate(date);
			project.setMoney(money);
			project.setProjectName(projectName);
			project.setProjectSN(projectSN);
			project.setTelephone(telephone);
			project.setUnit(unit);
			projectService.addProject(project);
			responseJson("{success:true, msg:'保存成功'}");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void download() {
		FileUtil.download(response, path);
	}
	
	public void addReport() {
		//重命名文件名
		String filename = FileUtil.rename(reportFileName);
		//上传文件路径
		String path = Global.ROOT_PATH + Global.REPORT_UPLOAD_PATH + filename;
		//创建路径文件夹
		FileUtil.createDir(Global.ROOT_PATH + Global.REPORT_UPLOAD_PATH);
		//上传文件
		FileUtil.upload(report, path);
		
		Project p = projectService.getProjectById(projectId);
		Report r = new Report();
		r.setReport(Global.REPORT_UPLOAD_PATH + filename);
		r.setReportDate(reportDate);
		r.setProject(p);
		
		try {
			reportService.addReport(r);
			responseJson("{success:true, msg:'保存成功'}");
		}  catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addSample() {
		Project project = projectService.getProjectById(projectId);
		Sample sample = new Sample();
		sample.setDate(date);
		sample.setName(name);
		sample.setProject(project);
		sample.setQuantity(quantity);
		sample.setSampleSN(sampleSN);
		sample.setState(state);
		sample.setTelephone(telephone);
		sample.setUnit(unit);
		try {
			sampleService.addSample(sample);
			responseJson("{success:true, msg:'保存成功'}");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void getSamples() {
		List<Sample> samples = sampleService.getSamples(start, limit);
		for(Sample sample : samples) {
			Project project = sample.getProject();
			sample.setProjectSN(project.getProjectSN());
		}
		Long total = sampleService.getTotal();
		JsonConfig jsonConfig = JSONUtil.getJsonConfig();
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new JSONUtil());
		JSONArray jArray = JSONArray.fromObject(samples, jsonConfig);
		JSONObject jObject = new JSONObject();
		jObject.put("samples", jArray);
		jObject.put("total", total);
		try {
			responseJson(jObject.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteReport() {
		try {
			reportService.deleteReport(reportId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			try {
				responseJson("{success:false, errorMessage:'删除失败，请重新尝试'}");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	public String getProjectSN() {
		return projectSN;
	}
	public void setProjectSN(String projectSN) {
		this.projectSN = projectSN;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public Date getReportDate() {
		return reportDate;
	}
	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}
	public String getContract() {
		return contract;
	}
	public void setContract(String contract) {
		this.contract = contract;
	}
	public String getReport() {
		return report;
	}
	public void setReport(String report) {
		this.report = report;
	}
	public String getSampleSN() {
		return sampleSN;
	}
	public void setSampleSN(String sampleSN) {
		this.sampleSN = sampleSN;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public ProjectService getProjectService() {
		return projectService;
	}
	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}
	public SampleService getSampleService() {
		return sampleService;
	}
	public void setSampleService(SampleService sampleService) {
		this.sampleService = sampleService;
	}
	
	public String getContractFileName() {
		return contractFileName;
	}

	public void setContractFileName(String contractFileName) {
		this.contractFileName = contractFileName;
	}

	public String getReportFileName() {
		return reportFileName;
	}

	public void setReportFileName(String reportFileName) {
		this.reportFileName = reportFileName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	/* (non-Javadoc)
	 * @see org.apache.struts2.interceptor.ServletResponseAware#setServletResponse(javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public ReportService getReportService() {
		return reportService;
	}

	public void setReportService(ReportService reportService) {
		this.reportService = reportService;
	}

	public Integer getReportId() {
		return reportId;
	}

	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}
}
