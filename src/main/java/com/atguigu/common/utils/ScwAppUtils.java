package com.atguigu.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;

public class ScwAppUtils {
	
	//	2、将java对象转为json字符串存到redis中的方法
	public static <T> void saveObj2Redis(T t, StringRedisTemplate stringRedisTemplate, String token) {
		//	将对象转为json字符串
		String jsonString = JSON.toJSONString(t);
		//	stringRedisTemplate
		stringRedisTemplate.opsForValue().set(token, jsonString);
	}
	
	//	3、将redis中的json字符串读取java代码中转为java对象的方法
	public static <T> T getJsonStr2Obj(Class<T> type, StringRedisTemplate stringRedisTemplate, String token) {
		//	获取redis中指定键对应的json字符串
		String jsonStr = stringRedisTemplate.opsForValue().get(token);
		if (StringUtils.isEmpty(jsonStr)) {
			return null;
		}
		//	将json转为java对象
		T t = JSON.parseObject(jsonStr, type);
		return t;
	}
	
	//	正则验证手机号码格式的方法
	public static boolean isMobilePhone(String phone) {
	    boolean flag=true;
	    String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
	    if (phone.length() != 11) {
	        flag= false;
	    } else {
	        Pattern p = Pattern.compile(regex);
	        Matcher m = p.matcher(phone);
	        flag = m.matches();
	    }

	    return flag;
	}
}
