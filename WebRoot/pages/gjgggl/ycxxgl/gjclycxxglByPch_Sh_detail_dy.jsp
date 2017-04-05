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
<title>打印页面</title>
<base target="_self">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/content.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/jquery-1.8.2.js"></script>
<style>
	html{ 
		overflow:scroll;
		scrollbar-base-color:#c7e5ff;
		scrollbar-track-color:#FFFFFF;
	}
	a{
		cursor: pointer;
		color: blue;
		text-decoration: underline;
	}
</style>
</head>

<body>
	 
	<div id="myPrintArea" style="width:100%;margin: 0px auto;">
		<table class="datalist" id="tableList" width="100%" border="0" cellpadding="0" cellspacing="0">
		  <tr class="tr1">
		    <th>流水号</th>
		    <th>号牌号码</th>
		    <th>号牌种类</th>
		    <th>批次号</th>
		    <th>广告审批号</th>
		    <th>审核部门</th>
		    <th>状态</th>
		    <th>预约审核时间</th>
		  </tr>
		  <s:if test='#request.busGgjgclsbsSingle!=null && #request.busGgjgclsbsSingle.size>0 '>
		  	<s:iterator value="#request.busGgjgclsbsSingle" var="tempSbSingle">
		  	 <tr>
		  		<td>
		  			<img src="<%=request.getContextPath()%>/CreateBarCode?code=${tempSbSingle.lsh}&barType=CODE39&checkCharacter=n&checkCharacterInText=n"/>
		  		</td>
		  		<td>${tempSbSingle.hphm}</td>
		  		<td>${tempSbSingle.hpzl}</td>
		  		<td>${tempSbSingle.yypch}</td>
		  		<td>${tempSbSingle.ggsph}</td>
		  		<td>${tempSbSingle.yyycbmmc}</td>
		  		<td>${tempSbSingle.ztMc}</td>
		  		<td>
		  			<s:date name="#tempSbSingle.yjsmycsj" format="yyyy-MM-dd HH:mm:ss" />
		  		</td>
		  	 </tr>
		  	</s:iterator>
		  </s:if>
		</table>
		
	</div>
	
</body>
<script type="text/javascript">	

	function printPic(){
		
		var headstr = "<html><head><title></title></head><body>";
		var footstr="</body>";
		//要打印的数据
		var newstr = document.getElementById("myPrintArea").innerHTML;
		//旧的网页数据
		var oldstr=document.body.innerHTML;
		document.body.innerHTML = headstr+newstr+footstr;
		//parent.window.print();
		window.print();
		
		document.body.innerHTML = oldstr;
		
		window.close();
			
	}
	
	setTimeout(function(){
		printPic();
	},200);
	
</script>
</html>