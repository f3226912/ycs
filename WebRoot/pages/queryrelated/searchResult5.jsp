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
<title>补换检验合格标志</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/content.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/jquery-1.8.2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/jquery-ui-1.9.1.custom.js"></script>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/My97DatePicker/WdatePicker.js"></script>
<style> 
	html{ 
		overflow:scroll;
		scrollbar-base-color:#c7e5ff;
		scrollbar-track-color:#FFFFFF;
	} 
</style>
</head>
<script type="text/javascript">   
   $(document).ready(function(){
	});

   function query(){
	    var startTime=$("#startTime").val();
		var endTime=$("#endTime").val();
		var url="<%=request.getContextPath()%>/qrelate/qrelate_qrelate5.action";
		var queryForm =document.getElementById("queryForm");
		queryForm.action=url;
		queryForm.submit();
  }
   
   function exportToExcel(){
	    var startTime=$("#startTime").val();
		var endTime=$("#endTime").val();
		if(startTime=="" || endTime==""){
			alert("请选择查询时间范围!");
			return false;
		}else{
			var url="<%=request.getContextPath()%>/qrelate/qrelate_qrelate_exportToExcel.action";
			var queryForm =document.getElementById("queryForm");
			queryForm.action=url;
			queryForm.submit();
		}
   }

   function printInfo(printPage) {
		var headstr = "<html><head><title></title></head><body>";
		var footstr="</body>";
		var newstr = document.getElementById(printPage).innerHTML;
		var oldstr=document.body.innerHTML;
		document.body.innerHTML = headstr+newstr+footstr;
		parent.window.print();
		document.body.innerHTML = oldstr;
		window.close();
	}
	

	function print(){
		var nodata=$("#nodata");
		if(nodata.length>0){
			alert("无数据导出,请选择其他日期");
		}else{
			printInfo("printArea");
		}
		
	}
	
</script>
	<body>
	 <form id="queryForm" name="queryForm" method="post">
		<div style="width:95%;margin: 0 auto;">
		   <table  class="table1"  width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
			  <tr>
			    <td class="tds" style="text-align: right;">时间:</td>
			    <td class="tds" style="text-align: left;">
			    	<input type="text" name="startTime" id="startTime" class="Wdate" value="${request.startTime}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true})" />
			    	到
			    	<input type="text" name="endTime" id="endTime" class="Wdate" value="${request.endTime}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true})" />
			    	<font color="red">提示：开始或结束时间为空，则查询所有数据</font>
			    </td>
			    
			  </tr>
			  <tr>
			    <td class="tds" style="text-align: right;">流水号:</td>
			    <td class="tds" style="text-align: left;">
			    	<input type="text" name="lsh" id="lsh" value="${request.lsh}" />
			    </td>
			  </tr>
			  <tr>
			  	<td colspan="2"> 
			    	<input type="button" value="查  询" onclick="query();"/>
			    	<input type="reset" value="重  置"/>
			    </td>
			  </tr>
		  </table> 
		  </div>
		  
		  <div style="width:95%; margin-top: 10px;margin: 0 auto;" id="printArea">
		  <div style="font-weight: bolder;color: red;">补换检验合格标志业务数：${map.rscount}</div><br/>
		    <table class="datalist" id="tableList" width="100%" border="0" cellpadding="0" cellspacing="0">
			  <tr class="tr1">
			    <th>流水号</th>
			    <th>业务类型</th>
			    <th>机动车所有人</th>
			    <th>身份证明号码</th>
			    <th>号牌号码</th>
			    <th>号牌种类</th>
			    <th>住所详细地址</th>
			    <th>移动电话</th>
			    <th>收件人姓名</th>
			    <th>收件人地址</th>
			    <th>收件人手机</th>
			    <th>录入时间</th>
			  </tr>
			  <s:if test="#request.resultList.size>0" >
			  <s:iterator var="rl" value="#request.resultList">
			  	<tr>
		        	<td>${rl[0]}</td>
		        	<s:if test='#rl[1]=="1" '>
		        		<td>行驶证业务</td>
		        	</s:if>
		        	<s:elseif test='#rl[1]=="2" '>
		        		<td>合格标志业务</td>
		        	</s:elseif>
		        	<td>${rl[2]}</td>
		        	<td>${rl[3]}</td>
		        	<td>${rl[4]}</td>
		        	<td>${rl[5]}</td>
		        	<td>${rl[6]}</td>
		        	<td>${rl[7]}</td>
		        	<td>${rl[9]}</td>
		        	<td>${rl[10]}</td>
		        	<td>${rl[11]}</td>
		        	<td>${rl[8]}</td>
		        </tr>
			  </s:iterator>
			  </s:if>
			  <s:else>
			    <tr id="nodata">
				  <td colspan="12" align="center">
					<span style="color: red">没有找到相关数据</span>
				  </td>
				</tr>
			  </s:else>	  
			</table>
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				style="padding-top: 5px;">
				<pt:write uri="${map.uri}" pagesize="${map.pageSize}" rscount="${map.rscount}" currentpage="${map.currentPage}"></pt:write>
			</table>
		   </div>
		 </form>
</body>
</html>