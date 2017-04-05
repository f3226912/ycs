<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
    
    <title>夫妻代办审批信息</title>
    
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
			var chuli;
			var MACAddr;
			$(document).ready(function(){
				if(edittype == 'query'){
					$("input").each(function(){
						$(this).attr("disabled", "disabled");
					});
					$("#returnbtn").attr("disabled", false);
					$("#hpzl").attr("disabled", "disabled");
					$("#czsfzmmc").attr("disabled", "disabled");
					$("#posfzmmc").attr("disabled", "disabled");
					
				}else if(edittype == 'add'){
					var date = new Date();
					var datestr = getnowdate(date, 'yyyy-MM-dd', 'no', '-');
					$("#yxrq").val(datestr);
				}
				
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
				//var hphm = checknotnull(document.getElementById("hphm"),'请输入号牌号码');
				//if(hphm != "true"){
				//	return false;
				//}
				//var hpzl = checknotnull(document.getElementById("hpzl"),'请输入选择号牌种类');
				//if(hpzl != "true"){
				//	return false;
				//}
				var czSfzmhm = checknotnull(document.getElementById("czSfzmhm"),'请输入车主身份证明号码');
				if(czSfzmhm != "true"){
					return false;
				}
				var czsfzmmc = $("#czsfzmmc").val();
				if(czsfzmmc == "A"){
					var czsfzmhm = $("#czSfzmhm").val();
					if(czsfzmhm.length < 18){
						alert("请输入正确的车主身份证号码！");
						return false;
					}
					if(czsfzmhm.substring(6,8) != '19'){
						alert("请输入正确的车主身份证号码！");
						return false;
					}
					if(czsfzmhm.substring(10,12) > '12'){
						alert("请输入正确的车主身份证号码！");
						return false
					}
					if(czsfzmhm.substring(12,14) > '31'){
						alert("请输入正确的车主身份证号码！");
						return false;
					}
				}
				var czsfzmmc = checknotnull(document.getElementById("czsfzmmc"),'请选择车主身份证明名称');
				if(czsfzmmc != "true"){
					return false;
				}
				var czXm = checknotnull(document.getElementById("czXm"),'请输入车主姓名');
				if(czXm != "true"){
					return false;
				}
				
				var poSfzmhm = checknotnull(document.getElementById("poSfzmhm"),'请输入配偶身份证明号码');
				if(poSfzmhm != "true"){
					return false;
				}
				var posfzmmc = $("#posfzmmc").val();
				if(posfzmmc == "A"){
					var poSfzmhm = $("#poSfzmhm").val();
					if(poSfzmhm.length < 18){
						alert("请输入正确的配偶身份证号码！");
						return false;
					}
					if(poSfzmhm.substring(6,8) != '19'){
						alert("请输入正确的配偶身份证号码！");
						return false;
					}
					if(poSfzmhm.substring(10,12) > '12'){
						alert("请输入正确的配偶身份证号码！");
						return false
					}
					if(poSfzmhm.substring(12,14) > '31'){
						alert("请输入正确的配偶身份证号码！");
						return false;
					}
				}
				var posfzmmc = checknotnull(document.getElementById("posfzmmc"),'请选择配偶身份证明名称');
				if(posfzmmc != "true"){
					return false;
				}
				var poXm = checknotnull(document.getElementById("poXm"),'请输入配偶姓名');
				if(poXm != "true"){
					return false;
				}
				var yxrq = checknotnull(document.getElementById("yxrq"),'请输入有效日期');
				if(yxrq != "true"){
					return false;
				}
				var xmval = $("#xm").val();
				var imgurl = '<%=request.getContextPath()%>/artDialog4.1.6/skins/icons/loading2.gif';
				chuli = art.dialog({
				    content: '<div style=\"text-align:center; text-indent:-999em; overflow:hidden; background:url('+imgurl+') no-repeat center center;\"><span>loading..</span></div><br/>数据处理中,请稍候.....',
				    title: '数据处理中',
	    			lock: true,
				    opacity: 0.87
				});
				slgsp_form.submit();
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
			夫妻代办业务审批信息&nbsp;
		</DIV>
	</DIV>
	<div id=admin_main style="WIDTH:90%; height:270px; margin:0 auto;margin: 25px;margin-top:25px;font-size: 12px;color: #000; ">
		<div class="div">
			<form action="<%=request.getContextPath()%>/veh/vehSpxx_editVehPodbxx.action" method="post" id="slgsp_form" target="abc">
			<iframe id="abc" name="abc" width="0" height="0" style="display: none;"></iframe>
			<input type="hidden" name="vehPodbsp.id" value="${vehPodbsp.id }" />
			<input type="hidden" id="txtMac" name="userMac" />
			<div id="tableid1" class="tablist block" style=" height: 270px;">
				<table class="edittable" border="0" cellpadding="0" cellspacing="0" align="center" style="padding-bottom: 50px;">
					<tr>
						<td colspan="6"><strong>审批信息</strong></td>
					</tr>
					<tr>
						<td style="text-align: right;width: 15%">号牌号码：</td>
						<td style="text-align: left;width: 23%">
							&nbsp;
							<input type="text" name="vehPodbsp.hphm" id="hphm" value="${vehPodbsp.hphm}" onkeyup="clearspace(this)" onblur="clearallspace(this)" />
							&nbsp;
						</td>
						<td style="text-align: right;width: 15%">号牌种类：</td>
						<td style="text-align: left;" colspan="3">
							&nbsp;
							<select name="vehPodbsp.hpzl" id="hpzl" style="width: 152px;">
										<option value="">请选择号牌种类</option>
										<s:iterator var="hp" value="#request.hpzlList">
											<option value='<s:property value="#hp.dmz"/>' 
												<s:if test="#request.vehPodbsp.hpzl==#hp.dmz">selected</s:if>>
												<s:property value="#hp.dmms1"/>
											</option>
										</s:iterator>
									</select>
							&nbsp;
						</td>
					</tr>
					<tr>
						<td style="text-align: right;">车主身份证明名称：</td>
						<td style="text-align: left;">
							&nbsp;
							<s:select list="#{'A':'二代居民身份证','B':'组织机构代码证书',
							'C':'军官证','D':'士兵证','E':'军官离退休证','F':'境外人员身份证明',
							'G':'外交人员身份证明','H':'居民户口薄','J':'单位注销证明',
							'K':'居住暂住证明','L':'驻华机构证明','M':'临时居民身份证'}" theme="simple"
								id="czsfzmmc"
								listKey="key" listValue="value" name="vehPodbsp.czSfzmmc" value="#request.vehPodbsp.czSfzmmc" cssStyle="width: 152px;"></s:select>
							&nbsp;<font style="color: red;">*</font>
						</td>
						<td style="text-align: right;">车主身份证明号码：</td>
						<td style="text-align: left;">
							&nbsp;
							<input type="text" class="disabled1" id="czSfzmhm"
								name="vehPodbsp.czSfzmhm" value="${vehPodbsp.czSfzmhm}" size="20"
								maxlength="20" onkeyup="clearspace(this)" onblur="clearallspace(this)" />
							&nbsp;<font style="color: red;">*</font>
						</td>
						<td style="text-align: right;">车主姓名：</td>
						<td style="text-align: left;">
							&nbsp;
							<input type="text" name="vehPodbsp.czXm" id="czXm" value="${vehPodbsp.czXm}" onkeyup="clearspace(this)" onblur="clearallspace(this)"/>
							&nbsp;<font style="color: red;">*</font>
						</td>
					</tr>
					<tr>
						<td style="text-align: right;">配偶身份证明名称：</td>
						<td style="text-align: left;">
							&nbsp;
							<s:select list="#{'A':'二代居民身份证','B':'组织机构代码证书',
							'C':'军官证','D':'士兵证','E':'军官离退休证','F':'境外人员身份证明',
							'G':'外交人员身份证明','H':'居民户口薄','J':'单位注销证明',
							'K':'居住暂住证明','L':'驻华机构证明','M':'临时居民身份证'}" theme="simple"
								id="posfzmmc" cssStyle="width: 152px;"
								listKey="key" listValue="value" name="vehPodbsp.poSfzmmc" value="#request.vehPodbsp.poSfzmmc"></s:select>
							&nbsp;<font style="color: red;">*</font>
						</td>
						<td style="text-align: right;">配偶身份证明号码：</td>
						<td style="text-align: left;">
							&nbsp;
							<input type="text" class="disabled1" id="poSfzmhm"
								name="vehPodbsp.poSfzmhm" value="${vehPodbsp.poSfzmhm}" size="20"
								maxlength="20" onkeyup="clearspace(this)" onblur="clearallspace(this)" />
							&nbsp;<font style="color: red;">*</font>
						</td>
						<td style="text-align: right;">配偶姓名：</td>
						<td style="text-align: left;">
							 &nbsp;
							<input type="text" name="vehPodbsp.poXm" id="poXm" value="${vehPodbsp.poXm}" onkeyup="clearspace(this)" onblur="clearallspace(this)"/>
							&nbsp;<font style="color: red;">*</font>
						</td>
					</tr>
					<tr id="tryxsj">
						<td style="text-align: right;">有效日期：</td>
						<td style="text-align: left;">
							&nbsp;							
							 <input type="text" name="vehPodbsp.yxrq" id="yxrq" readonly="readonly" value="<fmt:formatDate value="${vehPodbsp.yxrq}" pattern="yyyy-MM-dd" />"/>
							&nbsp;<font style="color: red;">*</font>
						</td>
						<td style="text-align: right;">备注：</td>
						<td style="text-align: left;" colspan="3">
							&nbsp;
							<input type="text" name="vehPodbsp.bz" id="bz" value="${vehPodbsp.bz}" size="58"/>
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
