package com.ycszh.common;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * @author dengsiqi E-mail:dengsiqi@vip.qq.com
 * @version 创建时间：2012-4-8 下午05:02:34 PageTag.java类说明
 */
public class PageTagLoading extends TagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String uri;

	private int currentpage = 1;

	private int rscount = 0;

	private int pagesize = 20;

	@Override
	public int doStartTag() throws JspException {
		HttpServletRequest request = (HttpServletRequest) this.pageContext
				.getRequest();
		String url = this.getParamUrl(request);
		String path = request.getContextPath();
		int prePage = currentpage - 1;
		int nextpage = currentpage + 1;
		int pagecount = this.getPageCount();
		StringBuffer str = new StringBuffer();
		str.append("<form method='post' name='pageform' action=''>");
		str
				.append("<table style='font-size:12px' width='100%' border='0' cellspacing='0' cellpadding='0'>");
		str.append("<tr>");
		str.append("<td style='text-align: left;border: 0px solid #3C7EBA;'>");
		str.append("总记录" + rscount + "条&nbsp;&nbsp;&nbsp;");
		str.append("共" + pagecount + "页&nbsp;&nbsp;&nbsp;");
		str.append("每页" + pagesize + "条&nbsp;&nbsp;&nbsp;");
		str.append("当前" + currentpage + "/" + pagecount + "页");
		str.append("</td><td style='text-align: right;border: 0px solid #3C7EBA;'>");
		if (currentpage > 1) {
			str.append("<a href='javascript: gotoPageLoading(1); '>首页</a>");
			str.append("&nbsp;&nbsp;&nbsp;");
			str.append("<a href='javascript: gotoPageLoading("+prePage+");'>上一页</a>");
			str.append("&nbsp;&nbsp;&nbsp;");
		} else {
			str.append("首页");
			str.append("&nbsp;&nbsp;&nbsp;");
			str.append("上一页");
			str.append("&nbsp;&nbsp;&nbsp;");
		}
		if (currentpage < pagecount) {
			str.append("<a href='javascript: gotoPageLoading("+nextpage+");'>下一页</a>");
			str.append("&nbsp;&nbsp;&nbsp;");
		} else {
			str.append("下一页");
			str.append("&nbsp;&nbsp;&nbsp;");
		}
		if (pagecount > 1 && currentpage != pagecount) {
			str.append("<a href='javascript: gotoPageLoading("+pagecount+");'>尾页</a>");
		} else {
			str.append("尾页");
		}
		str.append("&nbsp;&nbsp;&nbsp;转向第&nbsp;<input type='text' id='gopage' name='gopage' style='height:16px;width:40px;' />&nbsp;页");
		str.append("&nbsp;<input type='button' value='GO' onclick='javascript:gotoPage();' style='width:35px;height:20px;border:1px solid gray;'/>");
		str.append("</td></tr></table>");
		str.append("<script language='javascript'>");
		str.append("function gotoPage(){");
		str.append("var gopage=document.getElementById('gopage').value;");
		str.append("if(/^[1-9]\\d*$/.test(gopage)){");
		str.append("if(gopage>" + pagecount + "){gopage=" + pagecount + ";}}");
		str.append("else{gopage=1;}");
		str.append("var imgurl = '"+path+"/artDialog4.1.6/skins/icons/loading2.gif';");
		str.append("art.dialog({");
		str.append("content: '<div style=\"text-align:center; text-indent:-999em; overflow:hidden; background:url('+imgurl+') no-repeat center center;\"><span>loading..</span></div><br/>数据量较大，正在努力加载中，请稍候.....',");
		str.append("title: '数据加载中',");
		str.append("lock: true,");
		str.append(" opacity: 0.4");
		str.append("});");
		str.append("window.location.href='" + url + "currentpage='+gopage+'';");
		str.append("}");
		str.append("function gotoPageLoading(page){");
		str.append("var imgurl = '"+path+"/artDialog4.1.6/skins/icons/loading2.gif';");
		str.append("art.dialog({");
		str.append("content: '<div style=\"text-align:center; text-indent:-999em; overflow:hidden; background:url('+imgurl+') no-repeat center center;\"><span>loading..</span></div><br/>数据量较大，正在努力加载中，请稍候.....',");
		str.append("title: '数据加载中',");
		str.append("lock: true,");
		str.append(" opacity: 0.4");
		str.append("});");
		str.append("window.location.href='" + url + "currentpage='+page+'';");
		str.append("}");
		str.append("</script>");
		str.append("</form>");
		JspWriter out = this.pageContext.getOut();
		try {
			out.print(str.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}

	public int getPageCount() {
		return ((rscount - 1) / pagesize) + 1;
	}

	@SuppressWarnings("unchecked")
	public String getParamUrl(HttpServletRequest request) {
		String url = uri + "?";
		String totalParams = "";
		Enumeration params = request.getParameterNames();
		while (params.hasMoreElements()) {
			String tempName = params.nextElement().toString();
			String tempValue = request.getParameter(tempName);
			try {
				tempValue = URLEncoder.encode(tempValue, "UTF-8");
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (tempValue != null && !tempValue.equals("")
					&& !tempName.equals("currentpage")) {
				if (totalParams.equals("")) {
					totalParams = totalParams + tempName + "=" + tempValue;
				} else {
					totalParams = totalParams + "&" + tempName + "="
							+ tempValue;
				}
			}
		}
		String totalUrl = "";
		if (totalParams.length() == 0) {
			totalUrl = url;
		} else {
			totalUrl = url + totalParams + "&";
		}
		return totalUrl;
	}

	public int getCurrentpage() {
		return currentpage;
	}

	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}

	public int getRscount() {
		return rscount;
	}

	public void setRscount(int rscount) {
		this.rscount = rscount;
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}
}
