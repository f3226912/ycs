package com.ycszh.ssh.service.jszlcx;

import javax.servlet.http.HttpServletRequest;

public interface JzzcxService {

	// 调用居住证查询的存储过程，根据"身份证号码"和"姓名"进行查询
	public Object[] getPro(HttpServletRequest request) throws Exception;

}
