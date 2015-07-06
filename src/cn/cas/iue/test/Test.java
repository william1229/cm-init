/**
 * 
 */
/**
 * @Author: William
 * @ProjectName: cm
 * @ClassName: package-info 
 * @Description: TODO
 * @Date: 2014年7月23日 下午3:29:25
 * @Version V1.0
 */
package cn.cas.iue.test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Test {
	public static void main(String[] args) {
		String path = "F:/2014.txt";
		String s = new String();
		Map<String, Double> map = new HashMap<String, Double>();
		try {
			BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(path),"GBK"));
			while((s = br.readLine()) != null) {
				String info[] = s.split("	");
				String key = info[0];
				double value = Double.parseDouble(info[1]);
				if(!map.keySet().contains(key)) {
					map.put(key, value);
				} else {
					double total = map.get(key);
					total = total + value;
					map.put(key, total);
				}
			}
			Set<String> set = map.keySet();
			Collection<Double> collection = map.values();
			Iterator<String> keys = set.iterator();
			Iterator<Double> values = collection.iterator();
			while (keys.hasNext()) {
				String key = keys.next();
				double value = values.next();
				System.out.println(key + "	" + value);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}