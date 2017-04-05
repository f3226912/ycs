<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path" value="<%=request.getContextPath()%>"></c:set>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
  <head>   
    <title>查看用户电子签名</title>    
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/content.css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/jquery-1.8.2.js"></script>
	<link rel="stylesheet" type="text/css"
		href="<%=request.getContextPath()%>/css/module.css" />
	<style type="text/css">
		.usinfo{ list-style-type: none; margin-left: 0px;}
		.usinfo li{height: 30px; width: auto; text-align: left; font-weight: bold;}
	</style>
	
	
</script>
<!-- 手写板读取JS结束 -->
  </head>
 

 <body>

 <div class="right" style="width: 100%;">
  <table class="table1" width="100%" border="0" cellpadding="0" cellspacing="0" align="center">
  	  <tr>
		<td rowspan="2" width="35%" height="226" style="border-left: 0;">
			<ul class="usinfo">
				<li>用&nbsp;&nbsp;户&nbsp;&nbsp;ID: &nbsp;&nbsp;${request.loginid}</li>
				<li>用户姓名: &nbsp;&nbsp;${request.username}</li>
				<li>所属部门: &nbsp;&nbsp;${request.dmbl}</li>
			</ul>
		</td>
		<td colspan="2" width="65%" height="30" align="center" style="border-left: 0;">
			<strong>用户电子签名</strong>
		</td>
	  </tr>
	  <tr>
	  		<td align="center" width="65%" style="border-left: 0">
	  		<s:if test="#request.dzqmurl!= null">
				<img src="<%=request.getContextPath()%>/${request.dzqmurl}?ran=<%=Math.random() %>" id="dzqm" width="450" height="226" border="0">
			</s:if>
			<s:else>
				<img src="/ycszhyw/images/cp.gif" width="300" height="265" border="0">
			</s:else>
	  		</td>
	  </tr>
	  <tr>
	  		<td colspan="2"><input type="button" class="bnt" onclick="window.close()" value="关闭"/></td>
	  </tr>
  </table>

</div>
</div>
  </body>
</html>
