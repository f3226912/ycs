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
		<title>审批详细信息</title>
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
								身份证明号码&nbsp;
							</td>
							<td class="tdl" style="text-align: left;">
								<s:property value="#request.bfcTsspb.sfzmhm" />
							</td>
							<td class="tds" style="text-align: right;">
								审批状态
							</td>
							<td class="tdl" style="text-align: left;">
								<s:if test='#request.bfcTsspb.spzt == "1"'>
									科级待审批
								</s:if>
								<s:elseif test='#request.bfcTsspb.spzt == "2"'>
									科级审批不通过
								</s:elseif>
								<s:elseif test='#request.bfcTsspb.spzt == "3"'>
									处级待审批
								</s:elseif>
								<s:elseif test='#request.bfcTsspb.spzt == "4"'>
									处级审批不通过
								</s:elseif>
								<s:elseif test='#request.bfcTsspb.spzt == "5"'>
									科级审批通过
								</s:elseif>
								<s:elseif test='#request.bfcTsspb.spzt == "6"'>
									处级审批通过
								</s:elseif>
							</td>
						</tr>
						<tr>
							<td class="tds" style="text-align: right;">
								有效日期
							</td>
							<td class="tdl" style="text-align: left;">
								<s:date name="#request.bfcTsspb.ysrq" format="yyyy-MM-dd HH:mm:ss"/>
							</td>
							<td class="tds" style="text-align: right;">
								备注
							</td>
							<td class="tdl" style="text-align: left;">
								<s:property value="#request.bfcTsspb.bz" />
							</td>
						</tr>
						
						<tr>
							<td>&nbsp;</td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
						
						<tr>
							<td class="tds" style="text-align: right;">审批民警ID</td>
							<td class="tdl" style="text-align: left;">
								<s:property value="#request.bfcTsspb.MSpid" />
							</td>
							
							<td class="tds" style="text-align: right;">
								审批民警姓名
							</td>
							<td class="tdl" style="text-align: left;">
								<s:property value="#request.bfcTsspb.MSpxm" />
							</td>
						</tr>
						
						<tr>
							<td class="tds" style="text-align: right;">审批民警部门</td>
							<td class="tdl" style="text-align: left;">
								<s:property value="#request.bfcTsspb.MSpbmmc" />
							</td>
							
							<td class="tds" style="text-align: right;">
								民警审批时间
							</td>
							<td class="tdl" style="text-align: left;">
								<s:date name="#request.bfcTsspb.MSpsj" format="yyyy-MM-dd HH:mm:ss"/>
							</td>
							
						</tr>
						
						<tr>
							<td>&nbsp;</td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
						
						<tr>
							<td class="tds" style="text-align: right;">
								科级审批ID
							</td>
							<td class="tdl" style="text-align: left;">
								<s:property value="#request.bfcTsspb.KSpid" />
							</td>
							<td class="tds" style="text-align: right;">科级审批姓名</td>
							<td class="tdl" style="text-align: left;">
								<s:property value="#request.bfcTsspb.KSpxm" />
							</td>
						</tr>
						
						<tr>
							<td class="tds" style="text-align: right;">
								科级审批部门
							</td>
							<td class="tdl" style="text-align: left;">
								<s:property value="#request.bfcTsspb.KSpbmmc" />
							</td>
							<td class="tds" style="text-align: right;">科级审批时间</td>
							<td class="tdl" style="text-align: left;">
								<s:date name="#request.bfcTsspb.KSpsj" format="yyyy-MM-dd HH:mm:ss"/>
							</td>
						</tr>
						
						<tr>
							<td>&nbsp;</td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
						
						<tr>
							<td class="tds" style="text-align: right;">
								处级审批ID
							</td>
							<td class="tdl" style="text-align: left;">
								<s:property value="#request.bfcTsspb.CSpid" />
							</td>
							<td class="tds" style="text-align: right;">处级审批姓名</td>
							<td class="tdl" style="text-align: left;">
								<s:property value="#request.bfcTsspb.CSpxm" />
							</td>
						</tr>
						
						<tr>
							<td class="tds" style="text-align: right;">
								处级审批部门
							</td>
							<td class="tdl" style="text-align: left;">
								<s:property value="#request.bfcTsspb.CSpbmmc" />
							</td>
							<td class="tds" style="text-align: right;">处级审批时间</td>
							<td class="tdl" style="text-align: left;">
								<s:date name="#request.bfcTsspb.CSpsj" format="yyyy-MM-dd HH:mm:ss" />
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
