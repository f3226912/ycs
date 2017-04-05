<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
  <head>
    <title>top</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/frame.css">
  </head>
  <body>
	<div id="header_img_1">
		<div id="header_img_2"></div>
		<div id="header_img_3">
			<ul>
				<li><a href="<%=request.getContextPath()%>/user/user_logout.action" target="_top"><img src="<%=request.getContextPath()%>/images/frame_10.gif"></a></li>
				<li><a href="<%=request.getContextPath()%>/user/user_initUpdatePwd.action" target="mainFrame"><img src="<%=request.getContextPath()%>/images/frame_09.gif"></a></li>
				<li><a href="<%=request.getContextPath()%>/user/user_initUser.action?user.id=${userbean.id}" target="mainFrame"><img src="<%=request.getContextPath()%>/images/frame_08.gif"></a></li>
				<li><a href="<%=request.getContextPath()%>/pages/frame.jsp" target="_top"><img src="<%=request.getContextPath()%>/images/frame_07.gif"></a></li>
			</ul>
		</div>
	</div>
	<div id="header_img_4">
		<div id="header_img_5"></div>
		<div id="header_menu">
			<div id="header_menu_logo"><img src="<%=request.getContextPath()%>/images/frame_18.gif" align="top"/></div>
			<span id="header_menu_text">管理菜单</span>
		</div>
		<div id="header_img_6"></div>
		<div id="header_text">当前登录用户：${userbean.name} | 名称：${userbean.ygxm}</div>
		<div id="header_img_7"></div>
	</div>
  </body>
</html>
