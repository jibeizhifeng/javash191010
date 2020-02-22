package com.atguigu.common.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

//	响应类
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AppResponse<T> {

	//	响应状态码：响应成功还是失败，"00000"代表成功，其他的都代表失败
	private String code;
	//	响应描述字符串
	private String message;
	//	响应数据：用户查询、用户集合的查询、项目详情查询、项目列表查询
	private T data;
	
	//	业务bean：成功的响应，失败的响应
	public static <T> AppResponse<T> ok(T data){
		return new AppResponse<T>("00000", "success", data);
	}
	public static <T> AppResponse<T> fail(String code, String message){
		return new AppResponse<T>(code, message, null);
	}
}
