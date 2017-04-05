<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
  <head>
    <title>医院信息采集</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/module.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/jquery-1.8.2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/artDialog4.1.6/jquery.artDialog.source.js?skin=chrome"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/artDialog4.1.6/plugins/iframeTools.source.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/public.js"></script>

	<style>
		html {
			overflow: scroll;
			scrollbar-base-color: #c7e5ff;
			scrollbar-track-color: #FFFFFF;
		}
		
		#menutableid td.hover {
			background: #48C7E8;
		}
		
		#menutableid td {
			text-align: center;
			cursor: pointer;
			background: #FFFFff;
		}
		
		.disabled {
			background-color: #F1F1F1;
		}
		</style>
		<style>
		* {
			margin: 0;
			padding: 0;
		}
		
		li {
			list-style: none;
		}
		
		.div {
			width: 100%;
			height: 565px;
		}
		
		.tab1,.tab2 {
			width: 100%;
			height: 34px;
			border: 1px #4fb3d5 solid;
			border-bottom: 0;
			background: url(<%=request.getContextPath()%>/images/bj.gif ) repeat-x;
			background: #407fb7;
		}
		
		.tab1 ul,.tab2 ul {
			margin: 0;
			padding: 0;
		}
		
		.tab1 li,.tab2 li {
			float: left;
			padding: 0 30px;
			height: 34px;
			line-height: 34px;
			text-align: center;
			border-right: 1px #ebf7ff solid;
			cursor: pointer;
			font-size: 16px;
			letter-spacing: 2px;
			color: #FFFFFF;
		}
		
		.tab1 li.now,.tab2 li.now {
			color: #007ba5;
			background: #fff;
			font-weight: bold;
		}
		
		.tablist {
			width: 100%;
			height: 100%;
			font-size: 14px;
			line-height: 24px;
			border: 1px #4fb3d5 solid;
			display: none;
		}
		
		.block {
			display: block;
			width: 100%;
			height: 100%;
			background: #fff;
		}
		
		.edittable {
			width: 100%;
			background: #FFFFFF;
			margin: 0 auto;
		}
		
		td {
			background: #FFFFFF;
		}
		
		.table td {
			width: 50px;
		}
		
		.text {
			border: 1px #CCCCCC solid;
		}
		
		strong {
			letter-spacing: 1px;
			font-size: 13px;
			padding-left: 10px;
		}
		
		.bnt {
			width: 72px;
			height: 21px;
			line-height: 21px;
			border: none;
			background: url(<%=request.getContextPath()%>/images/an.gif )
				no-repeat;
			color: #FFFFFF;
			vertical-align: middle;
		}
		
		.bnt1 {
			font-size:18px;
			font-weight: bold;
			width: 90px;
			height: 38px;
			line-height: 38px;
			border: none;
			background: url(<%=request.getContextPath()%>/images/dd.jpg)
				no-repeat;
			color: #FFFFFF;
			vertical-align: middle;
		}
		
		span {
			color: #CC0000;
			letter-spacing: 2px;
		}
		</style>
		<SCRIPT type="text/javascript">
			var chuli;
			var MACAddr;
			$(document).ready(function(){
			});
			
			function exception(content){
				art.dialog({
					width:'50%',
				    content: content,
				    title: '系统异常',
				    cancelVal: '关闭',
	    			cancel: true,
	    			lock: true,
				    opacity: 0.87,
				    icon: 'error'
				});
			}
			
			function submitfrom(){
				var zzjgdmz = $("#zzjgdmz").val();
				var yhmm = $("#yhmm").val();
				var yymc = $("#yymc").val();
				var qrmm = $("#qrmm").val();
				var lxdh = $("#lxdh").val();
				var lxdz = $("#lxdz").val();
				var yylxrxm = $("#yylxrxm").val();
				var yylxrsfzmhm = $("#yylxrsfzmhm").val();
				var yylxrdh = $("#yylxrdh").val();
				var stopDate = $("#stopDate").val();
				var userlevel = $("#userlevel").val();
				var yyxh = $("#yyxh").val();
			
				if(zzjgdmz ==""){
					 alert("登录账号不能为空!");
					 return false;
				}
				if(yymc ==""){
					 alert("医院名称不能为空!");
					 return false;
				}
				if(yhmm ==""){
					 alert("密码不能为空!");
					 return false;
				}
				if(qrmm ==""){
					 alert("确认密码不能为空!");
					 return false;
				}
				if(yhmm != qrmm){
					 alert("登录密码和确认密码不一致!");
					 return false;
				}
				if(lxdh == ""){
					 alert("医院联系电话不能为空!");
					 return false;
				}
				if(lxdz == ""){
					 alert("医院联系地址不能为空!");
					 return false;
				}
				if(stopDate == ""){
					 alert("医院停止使用时间不能为空!");
					 return false;
				}
				if(Date.parse(stopDate.replace(/\-/g,"\/"))<new Date()){
					 alert("停止使用时间不能小于当前时间!");
					 return false;
				}
				if(userlevel == ""){
					 alert("请选择用户等级!");
					 return false;
				}
				$.ajax({
					cache:false,
					async:false,
					type:'post',
					url: "<%=request.getContextPath()%>/yytj/yytj_saveHospitalUserinfo.action",
					data: {yyxh:yyxh,
						   zzjgdmz:zzjgdmz,
						   yhmm:yhmm,
						   yymc:yymc,
						   lxdh:lxdh,
						   lxdz:lxdz,
						   yylxrxm:yylxrxm,
						   yylxrsfzmhm:yylxrsfzmhm,
						   yylxrdh:yylxrdh,
						   stopDate:stopDate,
						   userlevel:userlevel
						   },
					dataType: 'html',
					success:function(data){
						if (data == 1) {
							alert("医院信息采集成功!");
							window.location.href = '<%=request.getContextPath()%>/yytj/yytj_hospitalUserinfoQuery.action';
						}else if(data == 2){
							alert("医院信息更新成功!");
							window.location.href = '<%=request.getContextPath()%>/yytj/yytj_hospitalUserinfoQuery.action';
						}else{
							exception("采集失败!异常");
							return false;
						}
					}
				});
			}
		
			function closechuli(){
				chuli.close();
			}
		</SCRIPT>
	
  </head>
  <body>
  	<div style="text-align: center;">
  		<DIV id=admin_title>
		<DIV class=logo>
			<IMG src="<%=request.getContextPath()%>/images/frame_19.gif">
		</DIV>
		<DIV class=title>
			医院信息采集&nbsp;
		</DIV>
	</DIV>
	<div id=admin_main style="WIDTH:80%; height:180px; margin:0 auto;margin: 25px;margin-top:25px;font-size: 12px;color: #000; ">
		<div class="div" style="height:180px;">
			<iframe id="abc" name="abc" width="0" height="0" style="display: none;"></iframe>
			<input type="hidden" id="yyxh" name="yyxh" value="${hosHospitalUserinfo.yyxh}">
			<div id="tableid1" class="tablist block" style=" height: 180px;">
				<table class="edittable" border="0" cellpadding="0" cellspacing="0" align="center">
					<tr>
						<td colspan="7"><strong>医院信息采集</strong></td>
					</tr>
					<tr>
						<td style="text-align: right;width: 13%">登录账号(组织机构代码证号)：</td>
						<td style="text-align: left;width: 20%" colspan="6">
							<input type="text" class="disabled1" id="zzjgdmz"
							name="zzjgdmz" value="${hosHospitalUserinfo.zzjgdmz}" size="35"/>
                           <font style="color: red;">*</font>
						</td>
					</tr>
					<tr>
						<td style="text-align: right;width: 13%">登录密码：</td>
						<td style="text-align: left;width: 20%" colspan="6">
							<input type="password" class="disabled1" id="yhmm"
							name="yhmm" value="${hosHospitalUserinfo.yhmm}" size="35"/>
                           <font style="color: red;">*</font>
						</td>
					</tr>
					<tr>
						<td style="text-align: right;width: 13%">确认登录密码：</td>
						<td style="text-align: left;width: 20%" colspan="6">
							<input type="password" class="disabled1" id="qrmm"
							name="qrmm" size="35"/>
                           <font style="color: red;">*</font>
						</td>
					</tr>
					<tr>
						<td style="text-align: right;width: 13%">医院名称：</td>
						<td style="text-align: left;width: 20%" colspan="6">
							<input type="text" class="disabled1" id="yymc"
							name="yymc" value="${hosHospitalUserinfo.yymc}" size="35"/>
                           <font style="color: red;">*</font>
						</td>
					</tr>
					<tr>
						<td style="text-align: right;width: 13%">医院联系电话：</td>
						<td style="text-align: left;width: 20%" colspan="6">
							<input type="text" class="disabled1" id="lxdh"
							name="lxdh" value="${hosHospitalUserinfo.lxdh}" size="35"/>
                           <font style="color: red;">*</font>
						</td>
					</tr>
					<tr>
						<td style="text-align: right;width: 13%">医院联系地址：</td>
						<td style="text-align: left;width: 20%" colspan="6">
							<input type="text" class="disabled1" id="lxdz"
							name="lxdz" value="${hosHospitalUserinfo.lxdz}" size="35"/>
							<font style="color: red;">*</font>
						</td>
					</tr>
					<tr>
						<td style="text-align: right;width: 13%">医院联系人姓名：</td>
						<td style="text-align: left;width: 20%" colspan="6">
							<input type="text" class="disabled1" id="yylxrxm"
							name="yylxrxm" value="${hosHospitalUserinfo.yylxrxm}" size="35"/>
						</td>
					</tr>
					<tr>
						<td style="text-align: right;width: 13%">医院联系人身份证明号码：</td>
						<td style="text-align: left;width: 20%" colspan="6">
							<input type="text" class="disabled1" id="yylxrsfzmhm"
							name="yylxrsfzmhm" value="${hosHospitalUserinfo.yylxrsfzmhm}" size="35"/>
						</td>
					</tr>
					<tr>
						<td style="text-align: right;width: 13%">医院联系人电话：</td>
						<td style="text-align: left;width: 20%" colspan="6">
							<input type="text" class="disabled1" id="yylxrdh"
							name="yylxrdh" value="${hosHospitalUserinfo.yylxrdh}" size="35"/>
						</td>
					</tr>
					<tr>
						<td style="text-align: right;width: 13%;">医院停止使用时间：</td>
						<td style="text-align: left;width: 21%; padding-left: 2px;" colspan="6">
							<input type="text" name="stopDate" id="stopDate" value="${hosHospitalUserinfo.stopDate}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
							<font style="color: red;">*</font>
						</td>
					</tr>
					<tr>
					    <td style="text-align: right;width: 13%">用户等级：</td>
						<td style="text-align: left;width: 21%; padding-left: 2px;" colspan="6">
							<select id="userlevel" name="userlevel">
							   <option value="">--请选择--</option>
							   <option <s:if test="#request.hosHospitalUserinfo.userlevel==1">selected="selected"</s:if> value="1">普通用户</option>
							   <option <s:if test="#request.hosHospitalUserinfo.userlevel==2">selected="selected"</s:if> value="2">可以做残疾人体检</option>
							</select><font style="color: red;">*</font>
						</td>
					</tr>
				   <table width="100%" id="taskTableId">
		              <thead>
					  </thead>
					</table>
				</table>
				<table class="edittable" border="0" cellpadding="0" cellspacing="0" align="center">
					<tr>
						<td colspan="4" height="50" align="center"
							style="text-align: center">
							<input type="button" style="cursor:pointer;" onclick="javascript:submitfrom();" value="保 存" class="bnt1" />
							<input type="button" style="cursor:pointer;" onclick="javascript:window.history.go(-1);" value="返回" class="bnt1" />
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
  	</div>
  </body>
</html>