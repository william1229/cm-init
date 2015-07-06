/**
 * 
 */
package cn.cas.iue.util;

/**
 * @Author: William
 * @ProjectName: cm
 * @ClassName: StringUtil 
 * @Description: TODO
 * @Date: 2014年6月8日 下午2:30:29
 * @Version V1.0
 */
public class StringUtil {
	public static boolean isHave(String[] source, String goal) {
		for (String s : source) {
			if(s.equals(goal)) {
				return true;
			}
		}
		return false;
	}
}
