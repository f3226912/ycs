<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="com.ycszh.common.PageTag" prefix="pt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<base target="_self">
<title>审核情况统计-详细</title>

<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/jquery-1.8.2.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/content.css" />
<style> 
	html{ 
		overflow:scroll;
		scrollbar-base-color:#c7e5ff;
		scrollbar-track-color:#FFFFFF;
	}
	
	.lscx{
		cursor: pointer;
		color: blue;
		text-decoration: underline;
	}
</style>
</head>
<script type="text/javascript">   
	
	$(function(){
		
		$(".lscx").click(function(){
			
			$(this).css("color","red");
			$(".lscx").not($(this)).css("color","blue");
			
			var lsh=$(this).attr("lsh");
			if(lsh!=null && lsh!=""){
				var uri="<%=request.getContextPath()%>/gjgg/tjcx_getLscx.action?lsh="+lsh+"&comefrom=other";
				window.showModalDialog(uri,"","dialogWidth:1000px;dialogHeight:400px;help:no;status:no;scroll:no;location:no;resizable:yes");
			}else{
				alert("流水号为空,无法查看数据");
			}
			
		});
		
		$("#cancleButton").click(function(){
			window.close();
		});
		
	})
	
</script>
	<body>
	  <div style="width:100%;" >
		
		<div id="sbDataDiv" style="width:100%; margin-top: 10px;margin: 0 auto;" >
	   		<table class="datalist" id="tableList" width="100%" border="0" cellpadding="0" cellspacing="0">
	   			<tr class="tr1">
	   				<th colspan="7">${request.yyycbmmc}</th>
	   			</tr>
	   			<tr class="tr1">
				  	<th>流水号</th>
				    <th>号牌号码</th>
				    <th>号牌种类</th>
				    <th>状态</th>
				    <th>车辆类型</th>
				    <th>预约部门名称</th>
				    <th>操作</th>
				  </tr>
			  <s:if test='#request.sbList!=null && #request.sbList.size>0 '>
			  	<s:iterator value="#request.sbList" var="tempBusGgjgclsb">
				  	<tr class="sbDataDetail" yyycbm="${tempBusGgjgclsb.yyycbm}" ycmjcode="${tempBusGgjgclsb.ycmjcode}">
				  		<td>${tempBusGgjgclsb.lsh}</td>
				  		<td>${tempBusGgjgclsb.hphm}</td>
				  		<td>${tempBusGgjgclsb.hpzl}</td>
				  		<td>${tempBusGgjgclsb.zt}</td>
				  		<td>${tempBusGgjgclsb.lrmac}</td>
				  		<td>${tempBusGgjgclsb.yyycbmmc}</td>
				  		<td>
			  				<a class="lscx" lsh="${tempBusGgjgclsb.lsh}" >流水查询</a>
				  		</td>
				  	</tr>
			  	</s:iterator>
			  </s:if>
	   		</table>
	   </div>
		
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			style="padding-top: 5px;">
			<pt:write uri="${map.uri}" pagesize="${map.pagesize}" rscount="${map.rscount}" currentpage="${map.currentpage}"></pt:write>
		</table>
	
	   </div>

	</body>
</html>