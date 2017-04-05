<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<title>${editType }车行机构帐户信息</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
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

		<script type="text/javascript">
			var edittype = '${editType}';
			var chuli;
			$(document).ready(function() {
				if ('查看' == edittype) {
					var flag = '${pdasmycChbase.flag}';
					var ywfw = '${pdasmycChbase.ywfw}';
					var ywfwA = '${pdasmycChbase.ywfwA}';
					var smycbm = '${pdasmycChbase.smycbm}';
					var chjglx = '${pdasmycChbase.chjglx}';
					var flagname = document.getElementsByName("pdasmycChbase.flag");
					var ywfwname = document.getElementsByName("pdasmycChbase.ywfw");
					var ywfwAname = document.getElementsByName("pdasmycChbase.ywfwA");
					var smycbmname = document.getElementsByName("pdasmycChbase.smycbm");
					var chjglxname = document.getElementsByName("pdasmycChbase.chjglx");
					for (var i = 0; i < flagname.length; i ++){
						if(flag == flagname[i].value){
							flagname[i].checked = "checked";
							break;
						}
					}
					for (var i = 0; i < chjglxname.length; i ++){
						if(chjglx == chjglxname[i].value){
							chjglxname[i].checked = "checked";
							break;
						}
					}
					if(ywfw.indexOf(",") > 0){
						var ywfws = ywfw.split(",");
						for(var i = 0; i < ywfws.length; i ++){
							for(var j = 0; j < ywfwname.length; j ++){
								if(ywfws[i] == ywfwname[j].value){
									ywfwname[j].checked = "checked";
								}
							}
						}
					}else{
						for(var j = 0; j < ywfwname.length; j ++){
							if(ywfw == ywfwname[j].value){
								ywfwname[j].checked = "checked";
							}
						}
					}
					if(ywfwA.indexOf(",") > 0){
						var ywfwAs = ywfw.split(",");
						for(var i = 0; i < ywfwAs.length; i ++){
							for(var j = 0; j < ywfwAname.length; j ++){
								if(ywfwAs[i] == ywfwAname[j].value){
									ywfwAname[j].checked = "checked";
								}
							}
						}
					}else{
						for(var j = 0; j < ywfwAname.length; j ++){
							if(ywfwA == ywfwAname[j].value){
								ywfwAname[j].checked = "checked";
							}
						}
					}
					if(smycbm.indexOf(",") > 0){
						var smycbms = smycbm.split(",");
						for(var i = 0; i < smycbms.length; i ++){
							for(var j = 0; j < smycbmname.length; j ++){
								if(smycbms[i] == smycbmname[j].value){
									smycbmname[j].checked = "checked";
								}
							}
						}
					}else{
						for(var j = 0; j < smycbmname.length; j ++){
							if(smycbm == smycbmname[j].value){
								smycbmname[j].checked = "checked";
							}
						}
					}
				}else if('修改' == edittype){
					var flag = '${pdasmycChbase.flag}';
					var ywfw = '${pdasmycChbase.ywfw}';
					var ywfwA = '${pdasmycChbase.ywfwA}';
					var smycbm = '${pdasmycChbase.smycbm}';
					var chjglx = '${pdasmycChbase.chjglx}';
					var flagname = document.getElementsByName("pdasmycChbase.flag");
					var ywfwname = document.getElementsByName("pdasmycChbase.ywfw");
					var ywfwAname = document.getElementsByName("pdasmycChbase.ywfwA");
					var smycbmname = document.getElementsByName("pdasmycChbase.smycbm");
					var chjglxname = document.getElementsByName("pdasmycChbase.chjglx");
					for (var i = 0; i < flagname.length; i ++){
						if(flag == flagname[i].value){
							flagname[i].checked = "checked";
							break;
						}
					}
					for (var i = 0; i < chjglxname.length; i ++){
						if(chjglx == chjglxname[i].value){
							chjglxname[i].checked = "checked";
							break;
						}
					}
					if(ywfw.indexOf(",") > 0){
						var ywfws = ywfw.split(",");
						for(var i = 0; i < ywfws.length; i ++){
							for(var j = 0; j < ywfwname.length; j ++){
								if(ywfws[i] == ywfwname[j].value){
									ywfwname[j].checked = "checked";
								}
							}
						}
					}else{
						for(var j = 0; j < ywfwname.length; j ++){
							if(ywfw == ywfwname[j].value){
								ywfwname[j].checked = "checked";
							}
						}
					}
					if(ywfwA.indexOf(",") > 0){
						var ywfwAs = ywfw.split(",");
						for(var i = 0; i < ywfwAs.length; i ++){
							for(var j = 0; j < ywfwAname.length; j ++){
								if(ywfwAs[i] == ywfwAname[j].value){
									ywfwAname[j].checked = "checked";
								}
							}
						}
					}else{
						for(var j = 0; j < ywfwAname.length; j ++){
							if(ywfwA == ywfwAname[j].value){
								ywfwAname[j].checked = "checked";
							}
						}
					}
					if(smycbm.indexOf(",") > 0){
						var smycbms = smycbm.split(",");
						for(var i = 0; i < smycbms.length; i ++){
							for(var j = 0; j < smycbmname.length; j ++){
								if(smycbms[i] == smycbmname[j].value){
									smycbmname[j].checked = "checked";
								}
							}
						}
					}else{
						for(var j = 0; j < smycbmname.length; j ++){
							if(smycbm == smycbmname[j].value){
								smycbmname[j].checked = "checked";
							}
						}
					}
				}
			});
			
			function clearfrom(){
				
			}
			
			function submitfrom() {
				if('新增' == edittype){
					//验证机构名称
					var pdasmycChbasechmc = checknotnull(document.getElementById("pdasmycChbasechmc"),'请输入机构名称');
					if (pdasmycChbasechmc != "true") {
						return false;
					}
					//验证登录用户名
					var pdasmycChbasechid = checknotnull(document.getElementById("pdasmycChbasechid"),'请输入登录用户名');
					if (pdasmycChbasechid != "true") {
						return false;
					}
					//验证登录密码
					var pdasmycChbasechmm = checknotnull(document.getElementById("pdasmycChbasechmm"),'请输入登录密码');
					if (pdasmycChbasechmm != "true") {
						return false;
					}
					//验证机构类型
					//var pdasmycChbasechjglx = checknotnull(document.getElementById("pdasmycChbasechjglx"),'请输入机构类型');
					//if (pdasmycChbasechjglx != "true") {
					//	return false;
					//}
					var chjglxi,chjglxname;
					chjglxname = document.getElementsByName("pdasmycChbase.chjglx");
					for (chjglxi = 0; chjglxi < chjglxname.length; chjglxi ++){
						if(chjglxname[chjglxi].checked)break;
					}
					if(chjglxi >= chjglxname.length){
						alert("请选择机构类型!");
						return false;
					}
					
					
					//验证组织机构代码证
					var pdasmycChbasezzjgdm = checknotnull(document.getElementById("pdasmycChbasezzjgdm"),'请输入组织机构代码证');
					if (pdasmycChbasezzjgdm != "true") {
						return false;
					}
					//验证代码证有效期
					var pdasmycChbasecszrq = checknotnull(document.getElementById("pdasmycChbasecszrq"),'请输入代码证有效期');
					if (pdasmycChbasecszrq != "true") {
						return false;
					}else{
						var datestr = $("#pdasmycChbasecszrq").val();
						var returndatestr = checkdate(datestr);
						if(returndatestr != "true"){
							return false;
						}
					}
					//验证机构地址
					var pdasmycChbasejgdz = checknotnull(document.getElementById("pdasmycChbasejgdz"),'请输入机构地址');
					if (pdasmycChbasejgdz != "true") {
						return false;
					}
					//验证银行帐号
					var pdasmycChbaseyhkzh = checknotnull(document.getElementById("pdasmycChbaseyhkzh"),'请输入银行帐号');
					if (pdasmycChbaseyhkzh != "true") {
						return false;
					}
					//验证银行开户名称
					var pdasmycChbaseyhkmc = checknotnull(document.getElementById("pdasmycChbaseyhkmc"),'请输入银行开户名称');
					if (pdasmycChbaseyhkmc != "true") {
						return false;
					}
					//验证法人名称
					var pdasmycChbasefr = checknotnull(document.getElementById("pdasmycChbasefr"),'请输入法人名称');
					if (pdasmycChbasefr != "true") {
						return false;
					}
					//验证法人电话
					var pdasmycChbasefrdh = checknotnull(document.getElementById("pdasmycChbasefrdh"),'请输入法人电话');
					if (pdasmycChbasefrdh != "true") {
						return false;
					}
					//验证主要责任人姓名
					var pdasmycChbasezrr = checknotnull(document.getElementById("pdasmycChbasezrr"),'请输入主要责任人姓名');
					if (pdasmycChbasezrr != "true") {
						return false;
					}
					//验证主要责任人电话
					var pdasmycChbasezrrdh = checknotnull(document.getElementById("pdasmycChbasezrrdh"),'请输入主要责任人电话');
					if (pdasmycChbasezrrdh != "true") {
						return false;
					}
					//验证牌证责任人姓名
					var pdasmycChbasepzzrr = checknotnull(document.getElementById("pdasmycChbasepzzrr"),'请输入牌证责任人姓名');
					if (pdasmycChbasepzzrr != "true") {
						return false;
					}
					//验证牌证责任人电话
					var pdasmycChbasepzzrrdh = checknotnull(document.getElementById("pdasmycChbasepzzrrdh"),'请输入牌证责任人电话');
					if (pdasmycChbasepzzrrdh != "true") {
						return false;
					}
					//验证状态
					var zti,ztname;
					ztname = document.getElementsByName("pdasmycChbase.flag");
					for (zti = 0; zti < ztname.length; zti ++){
						if(ztname[zti].checked)break;
					}
					if(zti >= ztname.length){
						alert("请选择状态!");
						return false;
					}
					//验证业务类型
					var ywfwi,ywfw;
					ywfw = document.getElementsByName("pdasmycChbase.ywfw");
					for (ywfwi = 0; ywfwi < ywfw.length; ywfwi ++){
						if(ywfw[ywfwi].checked)break;
					}
					if(ywfwi >= ywfw.length){
						alert("请选择业务类型!");
						return false;
					}
					
					//验证业务范围
					var ywfwAi,ywfwA;
					ywfwA = document.getElementsByName("pdasmycChbase.ywfwA");
					for (ywfwAi = 0; ywfwAi < ywfwA.length; ywfwAi ++){
						if(ywfwA[ywfwAi].checked)break;
					}
					if(ywfwAi >= ywfwA.length){
						alert("请选择业务范围!");
						return false;
					}
					
					//验证上门验车部门
					var smycbmi,smycbm;
					smycbm = document.getElementsByName("pdasmycChbase.smycbm");
					for (smycbmi = 0; smycbmi < smycbm.length; smycbmi ++){
						if(smycbm[smycbmi].checked)break;
					}
					if(smycbmi >= smycbm.length){
						alert("请选择上门验车部门!");
						return false;
					}
					//验证主机MAC地址1
					var pdasmycChbasemac1 = checknotnull(document.getElementById("pdasmycChbasemac1"),'请输入主机MAC地址1');
					if (pdasmycChbasemac1 != "true") {
						return false;
					}
					
				}
				var imgurl = '<%=request.getContextPath()%>/artDialog4.1.6/skins/icons/loading2.gif';
				chuli = art.dialog({
				    content: '<div style=\"text-align:center; text-indent:-999em; overflow:hidden; background:url('+imgurl+') no-repeat center center;\"><span>loading..</span></div><br/>数据处理中,请稍候.....',
				    title: '数据处理中',
	    			lock: true,
				    opacity: 0.87
				});
				$("#txtMac").val(unescape(MACAddr));
				yanche_form.submit();
			}
			
			//清除空格和'$'
			function clearspace(obj){
				obj.value = obj.value.replace(" ","");
				obj.value = obj.value.replace("　","");
				obj.value = obj.value.replace("（","(");
				obj.value = obj.value.replace("）",")");
			}
			
			//小写字母变大写字母,适用于号牌号码.
			function xiaobianda(obj){
				obj.value = obj.value.replace(" ","");
				obj.value = obj.value.replace("　","");
				obj.value = obj.value.toUpperCase();
			}
			
			function cleanmyform(){
				
			}
			
			
			
			//页面重新加载
			function reonload(){
				window.location.reload();
			}
			
			function closechuli(){
				chuli.close();
			}
			
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
		</script>
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
		</script>
</head>
<body onload="init();">
	<object id="locator" classid="CLSID:76A64158-CB41-11D1-8B02-00600806D9B6"></object>
	<object id="foo" classid="CLSID:75718C9A-F029-11d1-A1AC-00C04FB6C223"></object>
	<DIV id=admin_title>
		<DIV class=logo>
			<IMG src="<%=request.getContextPath()%>/images/frame_19.gif">
		</DIV>
		<DIV class=title>
			${editType }车行机构帐户信息&nbsp;
		</DIV>
	</DIV>
	<div id=admin_main style="WIDTH:95%; margin:0 auto;margin: 25px;margin-top:15px;font-size: 12px;color: #000;">
		<div class="div">
			<form action="<%=request.getContextPath()%>/yanche/chbase_editPdasmycChbase.action" method="post" id="yanche_form" target="abc">
			<iframe id="abc" name="abc" width="0" height="0" style="display: none;"></iframe>
			<input type="hidden" name="pdasmycChbase.id" value="${pdasmycChbase.id }" />
			<input type="hidden" id="txtMac" name="userMac" />
			<div id="tableid1" class="tablist block">
				<table class="edittable" border="0" cellpadding="0" cellspacing="0" align="center">
					<tr>
						<td colspan="6"><strong>基本信息</strong></td>
					</tr>
					<tr>
						<td style="text-align: right;width: 13%">机构名称：</td>
						<td style="text-align: left;width: 20%">
							&nbsp;<input type="text" class="disabled1" id="pdasmycChbasechmc"
								name="pdasmycChbase.chmc" value="${pdasmycChbase.chmc}" size="20"
								maxlength="20" />&nbsp;<font style="color: red;">*</font>
						</td>
						<td style="text-align: right;width: 13%">登录用户名：</td>
						<td style="text-align: left;width: 20%">
							&nbsp;<input type="text" class="disabled1" id="pdasmycChbasechid"
								name="pdasmycChbase.chid" value="${pdasmycChbase.chid}" size="20"
								maxlength="20" />&nbsp;<font style="color: red;">*</font>
						</td>
						<td style="text-align: right;width: 13%">登录密码：</td>
						<td style="text-align: left;width: 21%">
							&nbsp;<input type="text" class="disabled1" id="pdasmycChbasechmm"
								name="pdasmycChbase.chmm" value="${pdasmycChbase.chmm}" size="20"
								maxlength="20" />&nbsp;<font style="color: red;">*</font>
						</td>
					</tr>
					<tr>
						<td style="text-align: right;width: 13%">机构类型：</td>
						<td style="text-align: left;width: 20%">
							&nbsp;<input type="radio" name="pdasmycChbase.chjglx" value="0" />二手车车行&nbsp;&nbsp;&nbsp;
							<input type="radio" name="pdasmycChbase.chjglx" value="1" />新车车行
							&nbsp;<font style="color: red;">*</font>
						</td>
						<td style="text-align: right;width: 13%">组织机构代码证：</td>
						<td style="text-align: left;width: 20%">
							&nbsp;<input type="text" class="disabled1" id="pdasmycChbasezzjgdm"
								name="pdasmycChbase.zzjgdm" value="${pdasmycChbase.zzjgdm}" size="20"
								maxlength="20" />&nbsp;<font style="color: red;">*</font>
						</td>
						<td style="text-align: right;width: 13%">代码证有效期：</td>
						<td style="text-align: left;width: 21%">
							&nbsp;<input type="text" class="disabled1" id="pdasmycChbasecszrq" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"
								name="pdasmycChbase.cszrq" value="<s:date name="#request.pdasmycChbase.cszrq" format="yyyy-MM-dd"/>" size="20"
								maxlength="20" />&nbsp;<font style="color: red;">*</font>
						</td>
					</tr>
					<tr>
						<td style="text-align: right;width: 13%">机构地址：</td>
						<td style="text-align: left;" colspan="5">
							&nbsp;<input type="text" class="disabled1" id="pdasmycChbasejgdz"
								name="pdasmycChbase.jgdz" value="${pdasmycChbase.jgdz}" size="80"
								maxlength="80" />&nbsp;<font style="color: red;">*</font>
						</td>
					</tr>
					<tr>
						<td style="text-align: right;width: 13%">银行帐号：</td>
						<td style="text-align: left;width: 20%">
							&nbsp;<input type="text" class="disabled1" id="pdasmycChbaseyhkzh"
								name="pdasmycChbase.yhkzh" value="${pdasmycChbase.yhkzh}" size="20"
								maxlength="20" />&nbsp;<font style="color: red;">*</font>
						</td>
						<td style="text-align: right;width: 13%">银行开户名称：</td>
						<td style="text-align: left;width: 20%">
							&nbsp;<input type="text" class="disabled1" id="pdasmycChbaseyhkmc"
								name="pdasmycChbase.yhkmc" value="${pdasmycChbase.yhkmc}" size="20"
								maxlength="20" />&nbsp;<font style="color: red;">*</font>
						</td>
						<td style="text-align: right;width: 13%"></td>
						<td style="text-align: left;width: 21%">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td style="text-align: right;width: 13%">法人名称：</td>
						<td style="text-align: left;width: 20%">
							&nbsp;<input type="text" class="disabled1" id="pdasmycChbasefr"
								name="pdasmycChbase.fr" value="${pdasmycChbase.fr}" size="20"
								maxlength="20" />&nbsp;<font style="color: red;">*</font>
						</td>
						<td style="text-align: right;width: 13%">法人电话：</td>
						<td style="text-align: left;width: 20%">
							&nbsp;<input type="text" class="disabled1" id="pdasmycChbasefrdh"
								name="pdasmycChbase.frdh" value="${pdasmycChbase.frdh}" size="20"
								maxlength="20" />&nbsp;<font style="color: red;">*</font>
						</td>
						<td style="text-align: right;width: 13%"></td>
						<td style="text-align: left;width: 21%">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td colspan="6"><strong>责任人信息</strong></td>
					</tr>
					<tr>
						<td style="text-align: right;width: 13%">主要责任人姓名：</td>
						<td style="text-align: left;width: 20%">
							&nbsp;<input type="text" class="disabled1" id="pdasmycChbasezrr"
								name="pdasmycChbase.zrr" value="${pdasmycChbase.zrr}" size="20"
								maxlength="20" />&nbsp;<font style="color: red;">*</font>
						</td>
						<td style="text-align: right;width: 13%">主要责任人电话：</td>
						<td style="text-align: left;width: 20%">
							&nbsp;<input type="text" class="disabled1" id="pdasmycChbasezrrdh"
								name="pdasmycChbase.zrrdh" value="${pdasmycChbase.zrrdh}" size="20"
								maxlength="20" />&nbsp;<font style="color: red;">*</font>
						</td>
						<td style="text-align: right;width: 13%"></td>
						<td style="text-align: left;width: 21%">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td style="text-align: right;width: 13%">牌证责任人姓名：</td>
						<td style="text-align: left;width: 20%">
							&nbsp;<input type="text" class="disabled1" id="pdasmycChbasepzzrr"
								name="pdasmycChbase.pzzrr" value="${pdasmycChbase.pzzrr}" size="20"
								maxlength="20" />&nbsp;<font style="color: red;">*</font>
						</td>
						<td style="text-align: right;width: 13%">牌证责任人电话：</td>
						<td style="text-align: left;width: 20%">
							&nbsp;<input type="text" class="disabled1" id="pdasmycChbasepzzrrdh"
								name="pdasmycChbase.pzzrrdh" value="${pdasmycChbase.pzzrrdh}" size="20"
								maxlength="20" />&nbsp;<font style="color: red;">*</font>
						</td>
						<td style="text-align: right;width: 13%"></td>
						<td style="text-align: left;width: 21%">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td colspan="6"><strong>系统信息</strong></td>
					</tr>
					<tr>
						<td style="text-align: right;width: 13%">状态：</td>
						<td style="text-align: left;width: 20%">
							&nbsp;
							<input type="radio" class="disabled1" name="pdasmycChbase.flag" checked="checked" value="T" />&nbsp;启用&nbsp;
							<input type="radio" class="disabled1" name="pdasmycChbase.flag" value="F" />&nbsp;停用&nbsp;
							&nbsp;<font style="color: red;">*</font>
						</td>
						<td style="text-align: right;width: 13%">业务类型：</td>
						<td style="text-align: left;width: 20%">
							&nbsp;
							<input type="checkbox" class="disabled1" name="pdasmycChbase.ywfw" value="A" />&nbsp;新车&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="checkbox" class="disabled1" name="pdasmycChbase.ywfw" value="B" />&nbsp;在用车
							&nbsp;<font style="color: red;">*</font>
						</td>
						<td style="text-align: right;width: 13%">业务范围：</td>
						<td style="text-align: left;width: 21%">
							&nbsp;
							<input type="checkbox" class="disabled1" name="pdasmycChbase.ywfwA" value="A" />&nbsp;上门验车&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="checkbox" class="disabled1" name="pdasmycChbase.ywfwA" value="B" />&nbsp;现场验车
							&nbsp;<font style="color: red;">*</font>
						</td>
					</tr>
					<tr>
						<td style="text-align: right;width: 13%">上门验车部门：</td>
						<td style="text-align: left;width: 20%" colspan="5">
							<s:iterator id="smycbmlist" value="#request.ywlsglVehSjzdList" status="st">
								<c:if test="${((st.count-1)%4) == 0 && st.count != 1}"></br></c:if>
								<input type="checkbox" class="disabled1" id="pdasmycChbasesmycbm${st.count}" name="pdasmycChbase.smycbm" value="${smycbmlist.dmz }" />&nbsp;${smycbmlist.dmsm1 }&nbsp;&nbsp;&nbsp;&nbsp;
							</s:iterator>
							&nbsp;<font style="color: red;">*</font>
						</td>
					</tr>
					<tr>
						<td style="text-align: right;width: 13%">主机MAC地址1：</td>
						<td style="text-align: left;width: 20%">
							&nbsp;<input type="text" class="disabled1" id="pdasmycChbasemac1"
								name="pdasmycChbase.mac1" value="${pdasmycChbase.mac1}" size="20"
								maxlength="20" />&nbsp;<font style="color: red;">*</font>
						</td>
						<td style="text-align: right;width: 13%">主机MAC地址2：</td>
						<td style="text-align: left;width: 20%">
							&nbsp;<input type="text" class="disabled1" id="pdasmycChbasemac2"
								name="pdasmycChbase.mac2" value="${pdasmycChbase.mac2}" size="20"
								maxlength="20" />&nbsp;
						</td>
						<td style="text-align: right;width: 13%">主机MAC地址3：</td>
						<td style="text-align: left;width: 21%">
							&nbsp;<input type="text" class="disabled1" id="pdasmycChbasemac3"
								name="pdasmycChbase.mac3" value="${pdasmycChbase.mac3}" size="20"
								maxlength="20" />&nbsp;
						</td>
					</tr>
				</table>
				<table class="edittable" border="0" cellpadding="0" cellspacing="0" align="center">
					<tr>
						<td colspan="4" height="50" align="center"
							style="text-align: center">
							<s:if test="#request.editType != '查看'">
								<input type="button" style="cursor:pointer;" onclick="javascript:submitfrom();" value="保 存" class="bnt1" />
								<input type="button" style="cursor:pointer;" onclick="javascript:window.history.go(-1);" value="返 回" class="bnt1" />
							</s:if>
							<s:else>
								<input type="button" style="cursor:pointer;" onclick="javascript:window.close();" value="关 闭" class="bnt1" />
							</s:else>
						</td>
					</tr>
				</table>
			</div>
			</form>
		</div>
	</div>
</body>
</html>