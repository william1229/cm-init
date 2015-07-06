/**
 * 
 */
package cn.cas.iue.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
import net.sf.json.util.PropertyFilter;

/**
 * @Author: William
 * @ProjectName: cm
 * @ClassName: JSONUtil 
 * @Description: TODO
 * @Date: 2014年4月17日 下午4:04:31
 * @Version V1.0
 */
public class JSONUtil implements JsonValueProcessor{
	private String format = "yyyy-MM-dd";
	
    public JSONUtil() {  
        super();  
    }  
  
    public JSONUtil(String format) {  
        super();  
        this.format = format;  
    } 
    
    @Override  
    public Object processArrayValue(Object value, JsonConfig jsonConfig) {  
        String[] obj = {};  
        if (value instanceof Date[]) {  
            SimpleDateFormat sf = new SimpleDateFormat(format);  
            Date[] dates = (Date[]) value;  
            obj = new String[dates.length];  
            for (int i = 0; i < dates.length; i++) {  
                obj[i] = sf.format(dates[i]);  
            }  
        }  
        return obj;  
    }  
  
    @Override  
    public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {  
    	if(value != null) {
            if (value instanceof java.util.Date) {  
                String str = new SimpleDateFormat(format).format((Date) value);  
                return str;  
            }  
    	} else {
    		return null;
    	}
        return value.toString();  
    }  
  
    public String getFormat() {  
        return format;  
    }  
  
    public void setFormat(String format) {  
        this.format = format;  
    }
    
    public static JsonConfig getJsonConfig() {   //过滤JSONArray里对象的属性配置
		JsonConfig jsonConfig = new JsonConfig(); 
		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
	            public boolean apply(Object arg0, String arg1, Object arg2) {
	                if (arg1.equals("rnrels") || arg1.equals("urrels") 
	                		|| arg1.equals("user") ||arg1.equals("instru")
	                		|| arg1.equals("instrus") ||arg1.equals("trainingRecs")
	                		|| arg1.equals("consumable") || arg1.equals("SSample")
	                		|| arg1.equals("samples") || arg1.equals("reports")
	                		|| arg1.equals("project")) {
	                    return true;
	                } else {
	                    return false;
	                }
	            }
	        });
		return jsonConfig;
	}
}
