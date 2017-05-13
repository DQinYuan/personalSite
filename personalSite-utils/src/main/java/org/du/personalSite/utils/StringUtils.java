package org.du.personalSite.utils;

import com.google.common.base.Strings;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * String工具类
 * 
 * @author sunxy 2016年1月20日 下午4:29:26	
 * @since 1.0
 */
public class StringUtils {
	
	public static String cutIfExceed(String original, int length) {
		if (Strings.isNullOrEmpty(original) || original.length() <= length) {
			return original;
		} else {
			return original.substring(0, length);
		}
	}

	public static boolean isContainChinese(String str) {

		Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
		Matcher m = p.matcher(str);
		if (m.find()) {
			return true;
		}
		return false;
	}

	/**
	 * 该方法主要使用正则表达式来判断字符串中是否包含字母
	 * @author fenggaopan 2015年7月21日 上午9:49:40
	 * @param cardNum 待检验的原始卡号
	 * @return 返回是否包含
	 */
	public static boolean isContainsStr(String cardNum) {
		String regex=".*[a-zA-Z]+.*";
		Matcher m=Pattern.compile(regex).matcher(cardNum);
		return m.matches();
	}

	public static boolean isNotBlank(String target) {
		return (target != null && !"".equals(target));
	}
	
	public static boolean isBlank(String target) {
		return (target == null || "".equals(target));
	}

}
