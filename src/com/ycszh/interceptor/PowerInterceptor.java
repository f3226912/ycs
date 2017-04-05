package com.ycszh.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.ycszh.global.SysConst;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * @author dengsiqi E-mail:dengsiqi@vip.qq.com
 * @version 创建时间：2012-4-8 下午05:04:56 PowerInterceptor.java类说明
 */
public class PowerInterceptor extends AbstractInterceptor {
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		HttpSession session=request.getSession();
		String uri=request.getRequestURI();
		String path=request.getContextPath();
		String action=uri.substring(uri.lastIndexOf("/")+1);
		if(action.equals("user_login.action")){
			return invocation.invoke();
		}else{
			if(session.getAttribute(SysConst.USERBEAN)==null){
				response.getWriter().println("<script>window.top.location.replace('"+path+"/login.jsp')</script>");
				return null;
			}
			return invocation.invoke();
		}
	}
}
