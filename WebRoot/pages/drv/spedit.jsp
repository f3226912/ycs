<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
  <head>
    
    <title>My JSP 'spedit.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/module.css" />
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
<script type="text/javascript" src="<%=request.getContextPath()%>/artDialog4.1.6/jquery.artDialog.source.js?skin=chrome"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/artDialog4.1.6/plugins/iframeTools.source.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/public.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/jquery-lightbox-0.5/js/jquery.lightbox-0.5.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery/jquery-lightbox-0.5/css/jquery.lightbox-0.5.css" edia="screen" />
	
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
			var edittype = '${editType}';
			var splx = '${request.slgSpxx.splx2}';
			var flag = '${request.slgSpxx.flag}';
			var chuli;
			var MACAddr;
			$(document).ready(function(){
				setTimeout(function(){
					/*$("select[name='slgSpxx.splx2'] option").each(function(){
						if($(this).val() == splx){
							$(this).attr("selected", true);
						}
					});*/
					$("input[name='slgSpxx.splx2']").each(function(){
						if($(this).val() == splx){
							$(this).attr("checked", "checked");
						}
					});
					$("input[name='slgSpxx.flag']").each(function(){
						if($(this).val() == flag){
							$(this).attr("checked", "checked");
						}
					});
				},500)
				if(edittype == 'query'){
					$("input").each(function(){
						$(this).attr("disabled", "disabled");
					});
					$("#splx").attr("disabled", "disabled");
					$("#returnbtn").attr("disabled", false);
					if(splx == 0){
						$("#tryxsj").hide();
						//$("#trisflag").show();
					}else{
						$("#tryxsj").show();
						$("#trisflag").hide();
					}
					
				}else if(edittype == 'add'){
					$("#trisflag").hide();
				}else if(edittype == 'edit'){
					$("input[name='slgSpxx.splx2']").each(function(){
						$(this).attr("readonly", "readonly");
					});
					if(splx == 0){
						$("#tryxsj").hide();
						//$("#trisflag").show();
					}else{
						$("#tryxsj").show();
						$("#trisflag").hide();
					}
				}
				$("input[name='slgSpxx.splx2']").change(function(){
					if($(this).val() == 0){
						if(edittype == 'add'){
							$("#trisflag").hide();
						}
						//else{
							//$("#trisflag").show();
						//}
						$("#yxsj").val("");
						$("#tryxsj").hide();
						
					}else{
						var date = new Date();
						var datestr = getnowdate(date, 'yyyy-MM-dd', 'no', '-');
						$("#yxsj").val(datestr);
						$("#tryxsj").show();
						$("#trisflag").hide();
						
					}
				});
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
				var type = '${request.editType}';
				var splx = $("input[name='slgSpxx.splx2'][checked]").val();
				document.getElementById('txtMac').value = unescape(MACAddr);
				var sfzmhm = checknotnull(document.getElementById("sfzmhm"),'请输入身份证明号码');
				if(sfzmhm != "true"){
					return false;
				}
				var xm = checknotnull(document.getElementById("xm"),'请输入姓名');
				if(xm != "true"){
					return false;
				}
				if(splx == 1){
					var yxsj = checknotnull(document.getElementById("yxsj"),'请输入有效时间');
					if(yxsj != "true"){
						return false;
					}
				}
				var splxval = $("#splx").val();
				var sfzmhmval = $("#sfzmhm").val();
				var xmval = $("#xm").val();
				//if(type == "add"){
				//	var isexist = spyanzheng(sfzmhmval, xmval, '', splxval);
				//	if(isexist == 1){
				//		alert("该证件信息已存在，请返回列表查询后进行修改!");
				//		return false;
				//	}
				//}
				var imgurl = '<%=request.getContextPath()%>/artDialog4.1.6/skins/icons/loading2.gif';
				chuli = art.dialog({
				    content: '<div style=\"text-align:center; text-indent:-999em; overflow:hidden; background:url('+imgurl+') no-repeat center center;\"><span>loading..</span></div><br/>数据处理中,请稍候.....',
				    title: '数据处理中',
	    			lock: true,
				    opacity: 0.87
				});
				//$("#txtMac").val("");
				slgsp_form.submit();
			}
			
			function spyanzheng(sfzmhm, xm, splx, splx2){
				var istrue;
				$.ajax({
					cache:false,
	    			async:false,
	    			url: '<%=request.getContextPath()%>/slgSpxx/slgSpxx_selSlgSpxxByCondition.action',
	    			type: 'post',
	    			data: {"sfzmhm": sfzmhm, "xm":xm, "splx": splx, "splx2":splx2, "yxsj":0 },
	    			dataType: 'json',
	    			error: function(){
		    			alert("读取数据异常!");
		    			return 0;
		    		},
	    			success: function(result){
						istrue = result;
		    		}
				});
				return istrue;
			}
			
			function closechuli(){
				chuli.close();
			}
			
		</SCRIPT>
		
		<script event="OnObjectReady(objObject,objAsyncContext)" for="foo">
			if (objObject.IPEnabled != null && objObject.IPEnabled != "undefined"
					&& objObject.IPEnabled == true) {
				if (objObject.MACAddress != null && objObject.MACAddress != "undefined"
						&& objObject.DNSServerSearchOrder != null) {
					MACAddr = objObject.MACAddress;
				}
				if (objObject.IPEnabled && objObject.IPAddress(0) != null
						&& objObject.IPAddress(0) != "undefined"
						&& objObject.DNSServerSearchOrder != null) {
					IPAddr = objObject.IPAddress(0);
				}
				if (objObject.DNSHostName != null && objObject.DNSHostName != "undefined") {
					sDNSName = objObject.DNSHostName;
				}
			}
		</script>
		<script type="text/javascript">
			var MACAddr;
			var IPAddr;
			var DomainAddr;
			var sDNSName;
			function init() {
				var service = locator.ConnectServer();
				service.Security_.ImpersonationLevel = 3;
				service.InstancesOfAsync(foo, 'Win32_NetworkAdapterConfiguration');
			}
			function getMac() {
				document.getElementById('txtMac').value = unescape(MACAddr);
			}
			
			function winclose(){
				window.close();
			}
		</script>
  </head>
  
  <body>
  	<div style="text-align: center;">
  		<DIV id=admin_title>
		<DIV class=logo>
			<IMG src="<%=request.getContextPath()%>/images/frame_19.gif">
		</DIV>
		<DIV class=title>
			驾驶证受理业务审批信息&nbsp;
		</DIV>
	</DIV>
	<div id=admin_main style="WIDTH:80%; height:180px; margin:0 auto;margin: 25px;margin-top:25px;font-size: 12px;color: #000; ">
		<div class="div" style="height:180px;">
			<form action="<%=request.getContextPath()%>/yujing/slgSpxx_editSlgSpxx.action" method="post" id="slgsp_form" target="abc">
			<iframe id="abc" name="abc" width="0" height="0" style="display: none;"></iframe>
			<input type="hidden" name="slgSpxx.id" value="${slgSpxx.id }" />
			<input type="hidden" id="txtMac" name="userMac" />
			<input type="hidden" id="slgSpxx.splx" name="slgSpxx.splx" value="drv"/>
			<div id="tableid1" class="tablist block" style=" height: 180px;">
				<table class="edittable" border="0" cellpadding="0" cellspacing="0" align="center">
					<tr>
						<td colspan="4"><strong>审批信息</strong></td>
					</tr>
					<tr>
						<td style="text-align: right;width: 13%">审批类型：</td>
						<td style="text-align: left;width: 20%" colspan="3">
							&nbsp;
									<input type="radio" value="0" name="slgSpxx.splx2" checked="checked"/>当事人
									<input type="radio" value="1" name="slgSpxx.splx2"/>代办人
									&nbsp;
						</td>
					</tr>
					<tr>
						<td style="text-align: right;width: 13%">身份证明号码：</td>
						<td style="text-align: left;width: 20%">
							&nbsp;<input type="text" class="disabled1" id="sfzmhm"
								name="slgSpxx.sfzmhm" value="${slgSpxx.sfzmhm}" size="20"
								maxlength="20" onkeyup="clearspace(this)" onblur="clearallspace(this)" />&nbsp;<font style="color: red;">*</font>
						</td>
						<td style="text-align: right;width: 13%">姓名：</td>
						<td style="text-align: left;width: 20%">
							&nbsp;<input type="text" class="disabled1" id="xm"
								name="slgSpxx.xm" value="${slgSpxx.xm}" size="20"
								maxlength="20" onkeyup="clearspace(this)" onblur="clearallspace(this)" />&nbsp;<font style="color: red;">*</font>
						</td>
					</tr>
					<tr id="trisflag" style="display: none;">
						<td style="text-align: right;width: 13%;">有效设置：</td>
						<td style="text-align: left;width: 21%; padding-left: 2px;" colspan="3">
							 &nbsp;<input type="radio" name="slgSpxx.flag" id="flag" value="0" checked="checked"/>是
							<input type="radio" name="slgSpxx.flag" id="flag" value="1"/>否
							&nbsp;
						</td>
					</tr>
					<tr id="tryxsj" style="display: none;">
						<td style="text-align: right;width: 13%;">有效时间：</td>
						<td style="text-align: left;width: 21%; padding-left: 2px;" colspan="3">
							<!-- &nbsp;<input type="radio" name="slgSpxx.flag" id="flag" value="0" checked="checked"/>是
							<input type="radio" name="slgSpxx.flag" id="flag" value="1"/>否 -->
							 <input type="text" name="slgSpxx.yxsj" id="yxsj" value="<fmt:formatDate value="${slgSpxx.yxsj}" pattern="yyyy-MM-dd" />" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
							&nbsp;<font style="color: red;">*</font>
						</td>
					</tr>
					<tr>
						<td style="text-align: right;width: 13%;">备注：</td>
						<td style="text-align: left;width: 21%; padding-left: 2px;" colspan="3">
							<input type="text" name="slgSpxx.bzsm" id="bzsm" value="${slgSpxx.bzsm}" size="58"/>
						</td>
					</tr>
				</table>
				<table class="edittable" border="0" cellpadding="0" cellspacing="0" align="center">
					<tr>
						<td colspan="4" height="50" align="center"
							style="text-align: center">
							<s:if test="#request.editType != 'query'">
								<input type="button" style="cursor:pointer;" onclick="javascript:submitfrom();" value="保 存" class="bnt1" />
							</s:if>
							<input type="button" id="returnbtn" style="cursor:pointer;" onclick="javascript:window.history.go(-1);" value="返 回" class="bnt1" />
						</td>
					</tr>
				</table>
			</div>
			</form>
		</div>
	</div>
  	</div>
    
  </body>
</html>
