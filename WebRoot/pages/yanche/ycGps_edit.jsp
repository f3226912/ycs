<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<title>${request.editType=="update"?"修改":"添加"}验车位置点</title>

<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/jquery-1.8.2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/gjgggl/jquery.jPrintArea.js"></script>


<style> 
	html{ 
		overflow:scroll;
		scrollbar-base-color:#c7e5ff;
		scrollbar-track-color:#FFFFFF;
	}
		
	.datalist {
		border: 1px solid #0058a3;
		font-family: Arial, Helvetica, sans-serif;
		border-collapse: collapse;
		/*background-color: #eaf5ff;*/
		font-size: 14px;
	}
	
	.datalist tr {
		height: 32px;
	}
	
	.datalist tr.altrow {
		/*background-color: #c7e5ff;*/
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
	
	.tds1 {
		background: #eaf5ff;
		font-weight: bold;
		font-size: 14px;
	}
	
	.tds2 {
		font-weight: bold;
		font-size: 14px;
	}
	
	.bnt {
		width: 76px;
		height: 27px;
		background: url(<%=request.getContextPath()%>/images/an2.gif) no-repeat;
		border: none;
		font-weight: bold;
	}
	
	.isNeed{
		color: red;
		font-size: 11px;
	}
	
	
</style>
<script type="text/javascript">
	$(function(){
		
	})
	
	function editGsp(){
		var editType = $("#editType").val();
		var jgmc = $("#jgmc").val();
		var xxdz = $("#xxdz").val();
		var gpsx = $("#gpsx").val()		
		var gpsy = $("#gpsy").val()	
		var yxjl = $("#yxjl").val()		
		if(jgmc==null || jgmc==""){
			alert("机构名称不能为空!");
			return false;
		}
		if(xxdz==null || xxdz==""){
			alert("详细地址不能为空!");
			return false;
		}
		if(gpsx==null || gpsx==""){
			alert("经纬度X不能为空!");
			return false;
		}
		var r = /^\d+(\.\d{1,6})?$/;
		if(r.test(gpsx)==false){
			alert("经纬度X格式不正确!必须为数字或小数点数字，例如：123或123.123123");
			return false;
		}
		if(gpsy==null || gpsy==""){
			alert("经纬度Y不能为空!");
			return false;
		}
		if(r.test(gpsy)==false){
			alert("经纬度Y格式不正确!必须为小数点数字，例如：123.123123");
			return false;
		}
		if(yxjl==null || yxjl==""){
			alert("有效距离不能为空!");
			return false;
		}
		if(!(/(^[1-9]\d*$)/.test(yxjl))){
			alert("有效距离只能为整数字!");
			return false;
		}
		$.ajax({
			cache:false,
			async:false,
			type:"POST",
			url:"<%=request.getContextPath()%>/yanche/yczd_editYcGps.action",
			data:$('#gps_form').serialize(),
			dataType: 'text',
			success:function(data){
				if(data==0){
					if(editType=="update"){
						alert("修改成功!");
					}else{
						alert("添加成功!")				
					}
					window.close();
					window.returnValue="success";
				}else{
					if(editType=="update"){
						alert("系统繁忙，修改失败,请稍后在试!");
					}else{
						alert("系统繁忙，添加失败,请稍后在试!");			
					}
					return;
				}
			}
		});
		return;
	}
</script>
</head>
	<body>
		  <div style="width:100%;" >
		 <form action="<%=request.getContextPath()%>/yanche/yczd_editGps.action" id="gps_form" method="post">
		    <table id="showData" class="datalist" width="100%" border="0" cellpadding="0" cellspacing="0">
			  <input type="hidden" name="gps.gpsId" value="${request.gps.gpsId}"/>	
			  <input type="hidden" name="editType" id="editType" value="${request.editType}"></input>      	
	    	  <tr>
	    	  	<td class="tds1" style="text-align: right;width:150px;">机构名称：</td>
			    <td class="tds2" style="text-align: left;" >
			    	<input type="text" size="35" name="gps.jgmc" id="jgmc" value="${request.gps.jgmc}">
			    </td>
	    	  </tr>
			  <tr>
			    <td class="tds1" style="text-align: right;width:150px;">详细地址：</td>
			    <td class="tds2" style="text-align: left;">
			    	<input type="text" size="35" name="gps.xxdz" id="xxdz" value="${request.gps.xxdz}">
			    </td>
			  </tr>
			  <tr>
			  	<td class="tds1" style="text-align: right;width:150px;">经纬度X：</td>
			    <td class="tds2" style="text-align: left;">
			    	<input type="text" size="35" name="gps.gpsx" id="gpsx" value="${request.gps.gpsx}" />
			    </td>
			  </tr>
			  <tr>
			  	<td class="tds1" style="text-align: right;width:150px;">经纬度Y：</td>
			     <td class="tds2" style="text-align: left;">
			    	<input type="text" size="35" name="gps.gpsy" id="gpsy" value="${request.gps.gpsy}" />
			     </td>
			  </tr>
			  <tr>
			     <td class="tds1" style="text-align: right;width:150px;">有效距离：</td>
			    <td class="tds2" style="text-align: left;">
			    	<input type="text" size="35" name="gps.yxjl" id="yxjl" value="${request.gps.yxjl}"/>
			    </td>
			  </tr>
			  <tr>
			     <td class="tds1" style="text-align: right;width:150px;">状态：</td>
			    <td class="tds2" style="text-align: left;">
			    	<input type="radio" name="gps.zt" <s:if test='#request.gps.zt=="0"'>checked="checked"</s:if><s:if test='#request.gps.zt==null'>checked="checked"</s:if> value="0"/>无效
			    	<input type="radio" name="gps.zt" <s:if test='#request.gps.zt=="1"'>checked="checked"</s:if> value="1"/>有效
			    </td>
			  </tr>
			  <tr>
			    <td class="tds" style="text-align: center;" colspan="2">
			    	<input type="button" class="bnt" onclick="editGsp()" style="width: 80px;" value="确定" />
				    <input type="button" class="bnt" onclick="window.close()" style="width: 80px;" value="取消" />
			    </td>
			  </tr>
			</table>
		</form>
		   </div>
</body>
</html>