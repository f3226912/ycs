<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<title>报废车详细信息</title>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/content.css" />
		<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/jquery-1.8.2.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/development-bundle/ui/jquery.ui.core.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/development-bundle/ui/jquery.ui.widget.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/development-bundle/ui/jquery.ui.position.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/development-bundle/ui/jquery.ui.menu.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/development-bundle/ui/jquery.ui.autocomplete.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/jquery-ui-1.9.1.custom.js"></script>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery/css/redmond/jquery-ui-1.9.1.custom.css" />
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery/css/redmond/jquery.ui.all.css" />
		<script type="text/javascript">
			$(document).ready(function(){
				
			});
			
		</script>
		<style> 
			html{ 
				overflow:scroll;
				scrollbar-base-color:#c7e5ff;
				scrollbar-track-color:#FFFFFF;
			} 
		</style>
	</head>

	<body style="background:#c7e5ff;">
		<div class="content1" style="width: 100%;">
			<div class="roundedBox" id="type1" style="width: 95%;">
				<div class="right" style="width: 90%;">
					<table class="table1" width="100%" border="0"
						cellpadding="0" cellspacing="0">
						<tr>
							<th class="th1" height="32" colspan="4">
								详细信息
							</th>
						</tr>
						<tr>
							<td class="tds" style="text-align: right">
								号牌号码&nbsp;
							</td>
							<td class="tdl" style="text-align: left;">
								<s:property value="#request.bfcJgskb.hphm" />
							</td>
							<td class="tds" style="text-align: right;">
								号牌种类
							</td>
							<td class="tdl" style="text-align: left;">
								<s:property value="#request.bfcJgskb.hpzl" />
							</td>
						</tr>
						<tr>
							<td class="tds" style="text-align: right;">
								身份证明号码
							</td>
							<td class="tdl" style="text-align: left;">
								<s:property value="#request.bfcJgskb.sfzmhm" />
							</td>
							<td class="tds" style="text-align: right;">
								所有人
							</td>
							<td class="tdl" style="text-align: left;">
								<s:property value="#request.bfcJgskb.syr" />
							</td>
						</tr>
						
						<tr>
							<td class="tds" style="text-align: right;">车辆识别代号</td>
							<td class="tdl" style="text-align: left;">
								<s:property value="#request.bfcJgskb.clsbdh" />
							</td>
							<td class="tds" style="text-align: right;">入库时间</td>
							<td class="tdl" style="text-align: left;">
								<s:date name="#request.bfcJgskb.rksj" format="yyyy-MM-dd HH:mm:ss"/>
							</td>
						</tr>
						
						<tr>
							<td class="tds" style="text-align: right;">车辆状态</td>
							<td class="tdl" style="text-align: left;">
								<s:property value="#request.bfcJgskb.clzt" />
							</td>
							<td class="tds" style="text-align: right;">是否有效</td>
							<td class="tdl" style="text-align: left;">
								<s:property value="#request.bfcJgskb.sfyx" />
							</td>
						</tr>
						
						<tr>
							<td class="tds" style="text-align: right;">初次登记日期</td>
							<td class="tdl" style="text-align: left;">
								<s:date name="#request.bfcJgskb.ccdjrq" format="yyyy-MM-dd HH:mm:ss" />
							</td>
							<td class="tds" style="text-align: right;">强制报废日期</td>
							<td class="tdl" style="text-align: left;">
								<s:date name="#request.bfcJgskb.qzbfqz" format="yyyy-MM-dd HH:mm:ss"/>
							</td>
						</tr>
						
						<tr>
							<td class="tds" colspan="4">
								<div align="center">
									<input class="bnt" type="button" onclick="javascript: window.close()" value="关  闭" style="cursor:pointer;" />
								</div>
							</td>
						</tr>
					</table>
				</div>

				<div class="corner topLeft"></div>
				<div class="corner topRight"></div>
			</div>
		</div>
		
	</body>
</html>
