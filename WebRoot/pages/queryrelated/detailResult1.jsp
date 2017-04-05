<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<title>详细结果</title>
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath()%>/css/content.css" />
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/jquery/js/jquery-1.8.2.js"></script>
		<style>
html {
	overflow: scroll;
	scrollbar-base-color: #c7e5ff;
	scrollbar-track-color: #FFFFFF;
}
</style>
	</head>
	<script type="text/javascript">
	$(document).ready(function() {
	});
</script>
	<body>
		<div style="width: 95%; margin: 0 auto;">
			<table class="table1" width="100%" align="center" border="0"
				cellpadding="0" cellspacing="0">
				<s:if test="#request.detailInfo!=null">
					<tr>
						<td class="tds" style="text-align: right;">
							外网流水号:
						</td>
						<td class="tds" style="text-align: left;">
							${request.detailInfo[0]}
						</td>
					</tr>
					<tr>
						<td class="tds" style="text-align: right;">
							换补类型
						</td>
						<td class="tds" style="text-align: left;">
							<s:if test='#request.detailInfo[1]=="H" '>
				        		换证
				        	</s:if>
				        	<s:elseif test='#request.detailInfo[1]=="B" '>
				        		补证
				        	</s:elseif>
						</td>
					</tr>
					<tr>
						<td class="tds" style="text-align: right;">
							身份证明号码
						</td>
						<td class="tds" style="text-align: left;">
							${request.detailInfo[2]}
						</td>
					</tr>
					<tr>
						<td class="tds" style="text-align: right;">
							驾驶证号码
						</td>
						<td class="tds" style="text-align: left;">
							${request.detailInfo[3]}
						</td>
					</tr>
					<tr>
						<td class="tds" style="text-align: right;">
							姓名
						</td>
						<td class="tds" style="text-align: left;">
							${request.detailInfo[4]}
						</td>
					</tr>
					<tr>
						<td class="tds" style="text-align: right;">
							登记住所地址
						</td>
						<td class="tds" style="text-align: left;">
							${request.detailInfo[5]}
						</td>
					</tr>
					<tr>
						<td class="tds" style="text-align: right;">
							固定电话
						</td>
						<td class="tds" style="text-align: left;">
							${request.detailInfo[6]}
						</td>
					</tr>
					<tr>
						<td class="tds" style="text-align: right;">
							移动电话
						</td>
						<td class="tds" style="text-align: left;">
							${request.detailInfo[7]}
						</td>
					</tr>
					<tr>
						<td class="tds" style="text-align: right;">
							收件人姓名
						</td>
						<td class="tds" style="text-align: left;">
							${request.detailInfo[8]}
						</td>
					</tr>
					<tr>
						<td class="tds" style="text-align: right;">
							收件人地址
						</td>
						<td class="tds" style="text-align: left;">
							${request.detailInfo[9]}
						</td>
					</tr>
					<tr>
						<td class="tds" style="text-align: right;">
							收件人手机
						</td>
						<td class="tds" style="text-align: left;">
							${request.detailInfo[10]}
						</td>
					</tr>
					<tr>
						<td class="tds" style="text-align: right;">
							网上录入时间
						</td>
						<td class="tds" style="text-align: left;">
						 ${request.detailInfo[11]}
						</td>
					</tr>
					<tr>
						<td class="tds" style="text-align: right;">
							体检医院名称
						</td>
						<td class="tds" style="text-align: left;">
							${request.detailInfo[12]}
						</td>
					</tr>
					<tr>
						<td class="tds" style="text-align: right;">
							体检日期
						</td>
						<td class="tds" style="text-align: left;">
							${request.detailInfo[13]}
						</td>
					</tr>
				</s:if>
				<s:else>
					<tr id="nodata">
						<td colspan="9" align="center">
							<span style="color: red">没有找到相关数据</span>
						</td>
					</tr>
				</s:else>
			</table>
		</div>
	</body>
</html>