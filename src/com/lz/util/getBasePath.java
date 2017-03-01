package com.lz.util;

import javax.servlet.http.HttpServletRequest;

public class getBasePath {
	
	public static String getBasePath(HttpServletRequest request){
		
		String basePath = "http://" + request.getServerName() // 服务器地址
				+ ":" + request.getServerPort() // 端口号
				+ request.getContextPath();// 项目名称
		
		return basePath;
	}

}
