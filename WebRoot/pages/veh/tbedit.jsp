<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path" value="<%=request.getContextPath()%>"></c:set>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
  <head>
    
    <title>退办</title>
    
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
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/veh/cookie.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/veh/jdcsledit.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/veh/jdcsl_zchf.js"></script>
	
	<style>
		html {
			scrollbar-base-color: #c7e5ff;
			scrollbar-track-color: #FFFFFF;
		}
		</style>
		<style>
		* {
			margin: 0;
			padding: 0;
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
		
		</style>
		<SCRIPT type="text/javascript">
			var edittype = '${editType}';
			var userdept = '${userbean.bmid}';
			var path = '${path}';
			var chuli;
			var imgurl = path+'/artDialog4.1.6/skins/icons/loading2.gif';
			function exception(content){
				art.dialog({
					width:'50%',
				    content: content,
				    title: '系统异常',
				    cancelVal: '关闭',
	    			cancel: true,
	    			lock: true,
				    opacity: 0.4,
				    icon: 'error'
				});
			}
			
			function submitfrom(){
				var id = $("#id").val();
				var tbyy = $("#tbyy").val();
				var lsh = $("#lsh").val();
				if(tbyy == null || tbyy == ''){
					alert("请填写退办原因");
					return false;
				}
				$.ajax({
						cache:false,
						async:false,
						url:path+"/veh_ajax/vehAjax_getTbyanz.action",
						type:'post',
						data:{"lsh":lsh, "userdept":userdept},
						dataType:'html',
						success: function(result){
							var message = result+"";
							if(message.indexOf('异常信息') == -1){
								var objs = result.split("+");
								if($.trim(objs[0]) == '1' ){
									$.ajax({
											cache:false,
											async:false,
											url:path+"/veh_ajax/vehAjax_tbZbinfo.action",
											type:'post',
											data:{"id":id, "lsh":lsh, "tbyy":tbyy},
											dataType:'json',
											error:function(XmlHttpRequest,textStatus, errorThrown){
												exception(XmlHttpRequest.responseText);
												obj = "error";
											},
											success: function(result){
												var message = result+"";
												if(message.indexOf('异常信息') == -1){
													if($.trim(result) == '1'){
														alert("退办成功!");
														art.dialog.close();
													}else if($.trim(result) == '-1'){
														alert("退办失败，请重新退办");
													}else{
														alert("退办失败!");
													}
												}else{
													obj = 'error';
													exception(message);
												}
											}
									 });
								}else{
									alert($.trim(objs[1]));
								}
							}else{
								exception(message);
							}
						}
				 });
				
			}
			
			function closechuli(){
				art.dialog.close();
			}
			
		</SCRIPT>
		
  </head>
  
  <body>
	<div style="WIDTH:90%; height:150px;  font-size: 12px;color: #000;">
		<div style="padding-top: 25px; padding-left: 25px;">
			<form action="<%=request.getContextPath()%>/veh/vehSpxx_tbZbinfo.action" method="post" id="slgsp_form" target="abc">
				<iframe id="abc" name="abc" width="0" height="0" style="display: none;"></iframe>
				<div id="tableid1" class="tablist block" style=" height: 150px;">
					<table class="edittable" border="0" cellpadding="0" cellspacing="0" align="center" >
						<tr>
							<td colspan="6"><strong>退办信息</strong></td>
						</tr>
						<tr>
							<td style="text-align: right;width: 15%">退办原因：</td>
							<td style="text-align: left;width: 23%">
								&nbsp;
								<input type="hidden" name="id" id="id" value="<%=request.getParameter("id") %>"/>
								<input type="hidden" name="lsh" id="lsh" value="<%=request.getParameter("lsh") %>"/>
								<input type="text" name="tbyy" id="tbyy" value="${tbyy}" onkeyup="clearspace(this)" onblur="clearallspace(this)" />
								&nbsp;
							</td>
						</tr>
						<tr>
							<td colspan="4" align="center"
								style="text-align: center">
								<input type="button" style="cursor:pointer;" onclick="javascript:submitfrom();" value="保 存" class="bnt" />
								<input type="button" style="cursor:pointer;" onclick="javascript:closechuli();" value="关 闭 " class="bnt" />
							</td>
						</tr>
					</table>
				</div>
			</form>
		</div>
	</div>
  </body>
</html>
