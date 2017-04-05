<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
  <head>
    <title>民警验车任务详细列表</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/jquery-1.8.2.js"></script>
	<script type="text/javascript">
		function printReport() {
			//$("#printBtn").hide();
			window.print();
			//$("#printBtn").show();
			return false;
		}
	</script>
	<style type="text/css">
		.datalist {
			border: 1px solid #0058a3;
			font-family: Arial, Helvetica, sans-serif;
			border-collapse: collapse;
			background-color: #eaf5ff;
			font-size: 14px;
		}
		
		.datalist tr {
			height: 32px;
		}
		
		.datalist tr.altrow {
			background-color: #c7e5ff;
			height: 32px;
		}
		
		.datalist td {
			border: 1px solid #3c7eba;
			text-align: center;
			padding-top: 2px;
			padding-bottom: 2px;
			padding-left: 5px;
			overflow: hidden;
			font-size: 12px;
		}
		
		.datalist th {
			border: 1px solid #3c7eba;
			height: 32px;
		}
	</style>
</head>

<body onload="printReport();">
	<table class="datalist" width="100%" align="center">
		<tr>
			<th style="text-align: center;">
				序号
			</th>
			<th style="text-align: center;">
				条形码
			</th>
			<th style="text-align: center;">
				业务流水号
			</th>
			<th style="text-align: center;">
				车行名称
			</th>
			<th style="text-align: center;">
				车辆识别代号
			</th>
			<th style="text-align: center;">
				申报批次号
			</th>
			<th style="text-align: center;">
				验车状态
			</th>
			<th style="text-align: center;">
				验车结果
			</th>
			<th style="text-align: center;">
				车辆停放地点
			</th>
		</tr>
		<s:if test="#request.pdasmycVehPcbList.size > 0">
			<s:iterator id="pvp" value="#request.pdasmycVehPcbList" status="st">
				<tr class="<s:if test="#st.odd == false">altrow</s:if>">
					<td style="text-align: center;">
						${st.count + (map.currentpage-1) * map.pagesize}
					</td>
					<td style="text-align: center;">
						<img id="Image1" src="<%=request.getContextPath()%>/CreateBarCode?code=${pvp.lsh }&barType=CODE39&checkCharacter=n&checkCharacterInText=n"/>
					</td>
					<td style="text-align: center;">
						${pvp.lsh }
					</td>
					<td style="text-align: center;">
						${pvp.chmc }
					</td>
					<td style="text-align: center;">
						${pvp.chid }
					</td>
					<td style="text-align: center;">
						${pvp.smycyypch }
					</td>
					<td style="text-align: center;">
						<s:if test="#pvp.ycjg != null">
							已验车
						</s:if>
						<s:else>
							未验车
						</s:else>
					</td>
					<td style="text-align: center;">
						<s:if test="#pvp.ycjg == 1">
							合格
						</s:if>
						<s:elseif test="#pvp.ycjg == 2">
							不合格
						</s:elseif>
					</td>
					<td style="text-align: center;">
						${pvp.clcfdd }
					</td>
				</tr>
			</s:iterator>
		</s:if>
		<s:else>
			<tr>
				<td colspan="8">
					<span style="color: red">暂时没有相关数据</span>
				</td>
			</tr>
		</s:else>
	</table>
</body>
</html>
