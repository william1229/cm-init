/**
 * 
 */
package cn.cas.iue.util;

import java.io.*;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import cn.cas.iue.common.Global;

/**
 * @Author: William
 * @ProjectName: cm
 * @ClassName: CreateFileUtil
 * @Description: TODO
 * @Date: 2014年4月11日 下午5:31:22
 * @Version V1.0
 */
public class FileUtil{
	
	public static boolean createFile(String destFileName) {
		File file = new File(destFileName);
		if (file.exists()) {
			System.out.println("创建单个文件" + destFileName + "失败，目标文件已存在！");
			return false;
		}
		if (destFileName.endsWith(File.separator)) {
			System.out.println("创建单个文件" + destFileName + "失败，目标文件不能为目录！");
			return false;
		}
		// 判断目标文件所在的目录是否存在
		if (!file.getParentFile().exists()) {
			// 如果目标文件所在的目录不存在，则创建父目录
			System.out.println("目标文件所在目录不存在，准备创建它！");
			if (!file.getParentFile().mkdirs()) {
				System.out.println("创建目标文件所在目录失败！");
				return false;
			}
		}
		// 创建目标文件
		try {
			if (file.createNewFile()) {
				System.out.println("创建单个文件" + destFileName + "成功！");
				return true;
			} else {
				System.out.println("创建单个文件" + destFileName + "失败！");
				return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out
					.println("创建单个文件" + destFileName + "失败！" + e.getMessage());
			return false;
		}
	}

	public static boolean createDir(String destDirName) {
		File dir = new File(destDirName);
		if (dir.exists()) {
			System.out.println("创建目录" + destDirName + "失败，目标目录已经存在");
			return false;
		}
		if (!destDirName.endsWith(File.separator)) {
			destDirName = destDirName + File.separator;
		}
		// 创建目录
		if (dir.mkdirs()) {
			System.out.println("创建目录" + destDirName + "成功！");
			return true;
		} else {
			System.out.println("创建目录" + destDirName + "失败！");
			return false;
		}
	}

	public static String createTempFile(String prefix, String suffix,
			String dirName) {
		File tempFile = null;
		if (dirName == null) {
			try {
				// 在默认文件夹下创建临时文件
				tempFile = File.createTempFile(prefix, suffix);
				// 返回临时文件的路径
				return tempFile.getCanonicalPath();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("创建临时文件失败！" + e.getMessage());
				return null;
			}
		} else {
			File dir = new File(dirName);
			// 如果临时文件所在目录不存在，首先创建
			if (!dir.exists()) {
				if (!FileUtil.createDir(dirName)) {
					System.out.println("创建临时文件失败，不能创建临时文件所在的目录！");
					return null;
				}
			}
			try {
				// 在指定目录下创建临时文件
				tempFile = File.createTempFile(prefix, suffix, dir);
				return tempFile.getCanonicalPath();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("创建临时文件失败！" + e.getMessage());
				return null;
			}
		}
	}
	
	public static String rename(String fileName) {
		// 格式化时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String datetime = sdf.format(new Date(System.currentTimeMillis()));
		// 重命名上传文件
		StringBuilder sb = new StringBuilder(fileName);
		sb.insert(sb.lastIndexOf("."), "_" + datetime);
		return sb.toString();
	}
	
	public static void download(HttpServletResponse response, String path){
		BufferedInputStream br = null; 
        OutputStream ut = null;  
		try {
			// 读取文件并且设置相关参数  
			String exName = path.substring(path.lastIndexOf("."));
			String fileName = path.substring(path.lastIndexOf("/") + 1, path.lastIndexOf("_"));
			String fullName = fileName + exName;
			String rootPath = Global.ROOT_PATH;
			File file = new File(rootPath + path);  
			byte[] buf = new byte[1024];
			int len = 0;  
	        response.reset();//必须加，不然保存不了临时文件  
	        response.setContentType("application/x-msdownload");
	        response.setHeader("Content-Disposition","attachment; filename=\"" + new String(fullName.getBytes(), "ISO8859-1") + "\"");  
	        br = new BufferedInputStream(new FileInputStream(file));  
	        ut = response.getOutputStream();
	        while((len=br.read(buf)) != -1){
	        	ut.write(buf, 0, len);  
	        } 
		} catch (Exception e) {  
	       		e.printStackTrace();  
	    } finally {
	    	 try {
				ut.flush();
				ut.close();
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	}
	
	public static void upload(String file, String path) {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		FileChannel in = null;
		FileChannel out = null;
		try {
			fis = new FileInputStream(file);
			fos = new FileOutputStream(path);
			in = fis.getChannel(); // 得到对应的文件通道
			out = fos.getChannel(); // 得到对应的文件通道
			in.transferTo(0, in.size(), out); // 连接两个通道，并且从in通道读取，然后写入out通道
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				fos.flush();
				fis.close();
				in.close();
				fos.close();
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
