<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path" value="<%=request.getContextPath()%>"></c:set>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<title>${editType }代办员信息</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/module.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/jquery/js/jquery-1.8.2.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/jquery/development-bundle/ui/jquery.ui.core.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/jquery/development-bundle/ui/jquery.ui.widget.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/jquery/development-bundle/ui/jquery.ui.position.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/jquery/development-bundle/ui/jquery.ui.menu.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/jquery/development-bundle/ui/jquery.ui.autocomplete.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/jquery/js/jquery-ui-1.9.1.custom.js"></script>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/jquery/css/redmond/jquery-ui-1.9.1.custom.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/jquery/css/redmond/jquery.ui.all.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/artDialog4.1.6/jquery.artDialog.source.js?skin=chrome"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/artDialog4.1.6/plugins/iframeTools.source.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/public.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/sfrz/sfrzcj.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/sfrz/cookie.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/jquery/jquery-lightbox-0.5/js/jquery.lightbox-0.5.js"></script>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/jquery/jquery-lightbox-0.5/css/jquery.lightbox-0.5.css"
	media="screen" />
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
		border: 1px #839fa6 solid;
		border-top: 0;
		display: none;
		padding-top: 10px;
	}
	
	.tabywlx{
		border-left: 1px #4fb3d5 solid;
		border-top: 1px #4fb3d5 solid;
	}
	.tabywlx .tdleft{
		border-right: 1px #4fb3d5 solid;
		border-bottom: 1px #4fb3d5 solid;
	}
	.tabywlx .tdright{
		width:181px;
		border-right: 1px #4fb3d5 solid;
		border-bottom: 1px #4fb3d5 solid;
	}
	
	.block {
		display: block;
		width: 100%;
		height: 100%;
		background: #fff;
		padding-top: 10px;
	}
	
	.edittable {
		width: 100%;
		background: #FFFFFF;
		margin: 0 auto;
		border-left: 0;
		border-right: 0;
		border-top: 0;
		border-bottom: 0;
	}
	
	td {
		background: #FFFFFF;
	}
	
	.table {
		font-size: 12px;
		width: 800px;
		border-collapse: collapse;
		background-color: #EEF2FB;
	}
	
	.td {
		text-align: center;
	}
	
	.table tr {
		height: 30px;
		background-color: #EEF2FB;
	}
	
	.table th {
		width: 150px;
		text-align: center;
		cursor: hand;
	}
	
	.table td {
		text-align: left;
		background-color: #EEF2FB;
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
		background: url(<%=request.getContextPath()%>/images/an3.gif ) no-repeat;
		color: #FFFFFF;
		vertical-align: middle;
	}
	
	.bnt3 {
		width: 72px;
		height: 21px;
		line-height: 21px;
		border: none;
		background: url(<%=request.getContextPath()%>/images/an3.gif ) no-repeat;
		color: #FFFFFF;
		vertical-align: middle;
	}
	
	.btn2{
		border:1px solid #4fb3d5;
		height:22px;
		line-height:18px;
		background:#d5eaff;
		cursor: pointer;
		}
	
	span {
		color: #CC0000;
		letter-spacing: 2px;
	}
	
	.idcardclass tr, idcardclass td{
		BORDER-RIGHT: #d2e9ff 0px solid;
		BORDER-TOP: #d2e9ff 0px solid;
		BORDER-LEFT: #d2e9ff 0px solid;
		BORDER-BOTTOM: #d2e9ff 0px solid;
	}
	.moveDiv{
		 width:800px;
		 height:30px;
		 line-height:30px;
		 background:#39C;
		 margin-top:-30px;
		 font-weight:bold;
		 cursor:move;
		 }
	.moveTxt{
		 position:absolute;
		 width:715px;
		 display:none;
		 top:40px;
		 left:8px;
		 background:#FFFFFF;
		 border:#4fb3d5 1px solid;
		 padding-top:15px;
		 font-size: 12px;
		 }

	
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

<script type="text/javascript">
	var path = '${path}';
	var chuli;
	$(document).ready(function(){
		var gj = '${sfrzCjxxb.gj}';
		if(gj == '' || gj == null){
			$("#gj").val("156");
		}
		$(".aa").hide();
		$(".ss").hide();
		$("#dmz").hide();
		$("#dmzmc").hide();
		//图片放大
		$(function(){
			$("#img01a").lightBox();
			$("#img02a").lightBox();
			$("#img03a").lightBox();
			$("#img04a").lightBox();
			$("#img05a").lightBox();
			$("#img06a").lightBox();
			$("#img07a").lightBox();
			$("#img08a").lightBox();
			$("#img09a").lightBox();
		});
		
		$("#btnlsh").click(function(){
			initform();
			//采集流水号
			var cidval = $("#cidid").val();
			var cidid = checknotnull(document.getElementById("cidid"),'请输入采集流水号');
			if (cidid != "true") {
				return false;
			}
			
			$.ajax({
				type:'POST',
				url:'<%=request.getContextPath()%>/sfrz_ajax/getSfrzCjxxbInfo.action',
				data:{cid:cidval},
				dataType: 'json',
				cache:false,
				async:false,
				error:function(){
					alert("读取数据异常!");
				},
				success:function(data){
					if(data == null || data.length == 0){
						alert("没有查询到流水信息");
					}else{
						if('0002' == data.tranFlag){
							if(confirm(data.synFlag)){
								$("#sfzmmc").val(data.sfzmmc);
								$("#sfzmhm").val(data.sfzmhm);
								$("#xm").val(data.xm);
								$("#gj").val(data.gj);
								$("#mz").val(data.mz);
								$("#jg").val(data.jg);
								$("#yddh").val(data.yddh);
								$("#txdz").val(data.txdz);
								$("#dzyx").val(data.dzyx);
								$("#gddh").val(data.gddh);
								$("#wx").val(data.wx);
								$("#wb").val(data.wb);
								$("#qq").val(data.qq);
								
								$("#djzsdz").val(data.djzsdz);
								$("#xphz").val(data.xphz);
								$("#jzzdz").val(data.jzzdz);
								$("#sfzmhmyxq").val(data.sfzmhmyxq);
								$("#jzzyxq").val(data.jzzyxq);
								$("#jzzhm").val(data.jzzhm);
								
								$("input[type=radio][name='sfrzCjxxb.rzjs'][value='"+data.rzjs+"']").attr("checked",true);
								$("input[type=radio][name='sfrzCjxxb.xb'][value='"+data.xb+"']").attr("checked",true);
								$("input[type=radio][name='sfrzCjxxb.sfsh'][value='"+data.sfsh+"']").attr("checked",true);
							}
						}else if('0003' == data.tranFlag){
							alert(data.synFlag);
							$("#sfzmmc").val(data.sfzmmc);
							$("#sfzmhm").val(data.sfzmhm);
							$("#xm").val(data.xm);
							$("#gj").val(data.gj);
							$("#mz").val(data.mz);
							$("#jg").val(data.jg);
							$("#yddh").val(data.yddh);
							$("#txdz").val(data.txdz);
							$("#dzyx").val(data.dzyx);
							$("#gddh").val(data.gddh);
							$("#wx").val(data.wx);
							$("#wb").val(data.wb);
							$("#qq").val(data.qq);
							
							$("#djzsdz").val(data.djzsdz);
							$("#xphz").val(data.xphz);
							$("#jzzdz").val(data.jzzdz);
							$("#sfzmhmyxq").val(data.sfzmhmyxq);
							$("#jzzyxq").val(data.jzzyxq);
							$("#jzzhm").val(data.jzzhm);
							
							$("input[type=radio][name='sfrzCjxxb.rzjs'][value='"+data.rzjs+"']").attr("checked",true);
							$("input[type=radio][name='sfrzCjxxb.xb'][value='"+data.xb+"']").attr("checked",true);
							$("input[type=radio][name='sfrzCjxxb.sfsh'][value='"+data.sfsh+"']").attr("checked",true);
						}else{
							alert(data.synFlag);
						}
					}
				}
			});
			
		});
		
		//diabled所有可选框
		$("input").each(function(){
			$(this).attr("disabled", "disabled");
		});
		$("radio").each(function(){
			$(this).attr("disabled", "disabled");
		});
		$("select").each(function(){
			$(this).attr("disabled", "disabled");
		});
		$("#dsrsfzmhm").attr("disabled", "disabled");
		$("#dbrsfzmmc").attr("disabled", "disabled");
		$("#guanbi").removeAttr("disabled");
		$("#shenhe1").removeAttr("disabled");
		$("#shenhe2").removeAttr("disabled");
		$("#shenhe3").removeAttr("disabled");
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
	function dbyshenhe(wwlsh,zt){
		if("0" == zt){
			var sm = checknotnull(document.getElementById("shenhe3"),'请输入审核不通过说明');
			if(sm != "true"){
				return false;
			}
		}
		if(confirm("是否审核？")==true){
			var tbyy=$("#shenhe3").val();
			$.ajax({
				type:'POST',
				url:'<%=request.getContextPath()%>/sfrz/sfrz_updateDby.action',
				data:{cid:wwlsh,dbyzt:zt,tbyy:tbyy},
				dataType: 'html',
				cache:false,
				async:false,
				error:function(){
					alert("读取数据异常!");
				},
				success:function(data){
					var message = data+"";
					if(message.indexOf('异常信息') == -1){
						if(data == 0){
						    alert("审核成功!");
						   // opener.updatezt(wwlsh,zt);
						    window.close();
						}else if(data == 1){
							alert("审核失败!");
						}else{
							alert("系统繁忙,请稍候再试!");
						}
					}else{
						exception(message);
					}
				}
			});
		}
	}
		
	
	function SfrzA(sfzmhmval){
		//alert(1);
		//var sfzmhmval = $("#sfzmhm").val();
		var num1=$("input[type=radio][name='sfrzCjxxb.rzjs'][checked]").val();
		//var sfzmhm = checknotnull(document.getElementById("sfzmhm"),'请输入身份证明号码');
		//if (sfzmhm != "true") {
		//	return false;
		//}
		$.ajax({
				type:'POST',
				url:'<%=request.getContextPath()%>/sfrz_ajax/getSfrzA.action',
				data:{sfzmhm:sfzmhmval},
				dataType: 'json',
				cache:false,
				async:false,
				error:function(){
					alert("读取数据异常1!");
				},
				success:function(data){
					if(null != data){
						if(num1 =='5'){
							$.ajax({
								type:'POST',
								url:'<%=request.getContextPath()%>/sfrz_ajax/getRscDby.action',
								data:{sfzmhms:sfzmhmval},
								dataType: 'json',
								cache:false,
								async:false,
								error:function(){
									alert("读取数据异常2!");
								},
								success:function(data2){
									if(null != data2){
										$.ajax({
											type:'POST',
											url:'<%=request.getContextPath()%>/sfrz_ajax/getRscDbyZjxxb.action',
											data:{sfzmhm:sfzmhmval},
											dataType: 'json',
											cache:false,
											async:false,
											error:function(){
												alert("读取数据异常3!");
											},
											success:function(data3){
												if(null != data3){
													alert("该用户已经认证过星级用户，并已采集指纹信息！");
													return false;
												}else{
													$("#xm").val(data.loginTruename);
													$("#gj").val(data.gj);
													$("#mz").val(data.mz);
													$("#jg").val(data.jg);
													$("#yddh").val(data.sjhm);
													$("#txdz").val(data.txdz);
													$("#dzyx").val(data.dzyx);
													$("#gddh").val(data.gddh);
													$("#wx").val(data.wx);
													$("#wb").val(data.wb);
													$("#qq").val(data.qq);
													$("#ccid").val(data.cid);
													
													$("#djzsdz").val(data.djzzdz);
													$("#xphz").val(data.xphz);
													$("#jzzdz").val(data.jzzdz);
													$("#sfzmhmyxq").val(data.sfzmhmyxq);
													$("#jzzyxq").val(data.jzzyxq);
													$("#jzzhm").val(data.jzzhm);
													
													//$("input[type=radio][name='sfrzCjxxb.rzjs'][value='"+data.yhjs+"']").attr("checked",true);
													$("input[type=radio][name='sfrzCjxxb.xb'][value='"+data.xb+"']").attr("checked",true);
													$("input[type=radio][name='sfrzCjxxb.sfsh'][value='"+data.sfsh+"']").attr("checked",true);
													
													//代办人信息
													//$("#dbrSfzCardname1").val(data.loginTruename);
													//if('1'==data.xb){
													//	$("#dbrSfzCardsex1").val("男");
													//}else if('2'==data.xb){
													//	$("#dbrSfzCardsex1").val("女");
													//}
													//$("#dbrSfzCardno1").val(data.loginUser);
													//$("#dbrSfzCardaddress1").val(data.jg);
													alert("请填写信息，抓拍当事人现场照片并采集指纹");
												}
											}
										});
									}else{
										$("#xm").val(data.loginTruename);
										$("#gj").val(data.gj);
										$("#mz").val(data.mz);
										$("#jg").val(data.jg);
										$("#yddh").val(data.sjhm);
										$("#txdz").val(data.txdz);
										$("#dzyx").val(data.dzyx);
										$("#gddh").val(data.gddh);
										$("#wx").val(data.wx);
										$("#wb").val(data.wb);
										$("#qq").val(data.qq);
										$("#ccid").val(data.cid);
										
										$("#djzsdz").val(data.djzzdz);
										$("#xphz").val(data.xphz);
										$("#jzzdz").val(data.jzzdz);
										$("#sfzmhmyxq").val(data.sfzmhmyxq);
										$("#jzzyxq").val(data.jzzyxq);
										$("#jzzhm").val(data.jzzhm);
										
										//$("input[type=radio][name='sfrzCjxxb.rzjs'][value='"+data.yhjs+"']").attr("checked",true);
										$("input[type=radio][name='sfrzCjxxb.xb'][value='"+data.xb+"']").attr("checked",true);
										$("input[type=radio][name='sfrzCjxxb.sfsh'][value='"+data.sfsh+"']").attr("checked",true);
										
										//代办人信息
										//$("#dbrSfzCardname1").val(data.loginTruename);
										//if('1'==data.xb){
										//	$("#dbrSfzCardsex1").val("男");
										//}else if('2'==data.xb){
										//	$("#dbrSfzCardsex1").val("女");
										//}
										//$("#dbrSfzCardno1").val(data.loginUser);
										//$("#dbrSfzCardaddress1").val(data.jg);
										alert("当事人已认证过星级用户，请补全代办人照片信息及指纹信息！");

									}
								}
							});
						}else{
							alert("该用户已经认证过星级用户");
							return false;
						}
					}else{
						//alert(123123);
					}
				}
		});
	}
	function getda(){
		var num=$("input[type=radio][name='sfrzCjxxb.sfsh'][checked]").val();
		if('2'==num){
			if(document.getElementById("sfzmmc").value!="A"){
				alert("请选择把身份证名称选为二代居民身份证，并填写身份证号码后点击查询档案按钮！");
				return false;
			}
			//身份证明号码
			var sfzmhmval = $("#sfzmhm").val();
			var sfzmhm = checknotnull(document.getElementById("sfzmhm"),'请输入身份证明号码');
			if (sfzmhm != "true") {
				return false;
			}
			var xmval = $("#xm").val();
			var xm = checknotnull(document.getElementById("xm"),'请输入姓名');
			if (xm != "true") {
				return false;
			}
			//判断如果是非深户
			//拿到身份证明号码和姓名
			$.ajax({
				type:'POST',
				url:'<%=request.getContextPath()%>/sfrz_ajax/getsfshList.action',
				data:{sfzhm:sfzmhmval,mz:xmval},
				dataType:'html',
				cache:false,
				async:false,
				error:function(){
					alert("读取数据异常!");
				},
				success:function(data){
					var result=$.trim(data);
					alert(result);
					if(0 == result){
						alert("没有查到相关居住证信息，非深户必须要有居住证信息!");
						return false;
					}else{
						alert('ss');
						var dataval = result.split(';');
						alert(dataval);
						$("#jzzhm").val(dataval[2]);
		    			$("#jzzyxq").val(dataval[8]);
		    			//$("#txdz").val(dataval[2]);
		    			$("#jzzdz").val(dataval[4]);
		    			$("#djzsdz").val(dataval[3]);
					}
				}
			});
		}else{
			//是深户就往下走
			var sfzmhmval = $("#sfzmhm").val();
			var sfzmhm = checknotnull(document.getElementById("sfzmhm"),'请输入身份证明号码');
			if (sfzmhm != "true") {
				return false;
			}
			$("#datableid").html("");
			$("#datableid2").html("");
			$.ajax({
				type:'POST',
				url:'<%=request.getContextPath()%>/sfrz_ajax/getVehList.action',
				data:{sfzmhm:sfzmhmval},
				dataType: 'json',
				cache:false,
				async:false,
				error:function(){
					alert("读取数据异常!");
				},
				success:function(data){
					var addstr;
					addstr = "<tr class=\"tr1\"><td colspan=\"7\"><strong>机动车档案信息</strong></td></tr>";
					if(null != data){
						addstr += "<tr class=\"tr1\"><td>号牌号码</td><td width='15%'>号牌种类</td><td>身份证明名称</td><td>身份证明号码</td><td>初次登记日期</td><td>车架号</td><td>发动机号</td></tr>";
				    	if(data.length > 0){
				    		for(var i = 0; i < data.length; i ++){
					    		var hphm = data[i][0];
					    		var hpzl = data[i][1];
					    		var sfzmmc = data[i][2];
					    		var sfzmhm = data[i][3];
					    		var ccdjrq = data[i][4];
					    		var clsbdh = data[i][5];
					    		var fdjh = data[i][6];
					    		var strtemp = "<tr><td>"+hphm+"</td><td>"+hpzl+"</td><td>"+sfzmmc+"</td><td>"+sfzmhm+"</td><td>"+ccdjrq+"</td><td>"+clsbdh+"</td><td>"+fdjh+"</td></tr>";
								addstr += strtemp;
					    	}
				    	}else{
				    		addstr += "<tr><td colspan=\"7\" style=\"text-align: center\"><font color='red'>无机动车档案信息</font>&nbsp;&nbsp;</td></tr>";
				    	}
					}else{
						addstr += "<tr><td colspan=\"7\" style=\"text-align: center\"><font color='red'>无机动车档案信息</font>&nbsp;&nbsp;</td></tr>";
					}
					$("#datableid").append(addstr);
				}
			});
			
			$.ajax({
				type:'POST',
				url:'<%=request.getContextPath()%>/sfrz_ajax/getDrvList.action',
				data:{sfzmhm:sfzmhmval},
				dataType: 'json',
				cache:false,
				async:false,
				error:function(){
					alert("读取数据异常!");
				},
				success:function(data){
					var addstr;
					addstr = "<tr class=\"tr1\"><td colspan=\"7\"><strong>驾驶证档案信息</strong></td></tr>";
					if(null != data){
						addstr += "<tr class=\"tr1\"><td>驾驶证号码</td><td width='15%'>姓名</td><td>初次领证日期</td><td>状态</td><td>有效期至</td><td>准驾车型</td><td>累计积分</td></tr>";
				    	if(data.length > 0){
				    		for(var i = 0; i < data.length; i ++){
					    		var sfzmhm = data[i][0];
					    		var xm = data[i][1];
					    		var cclzrq = data[i][2];
					    		var zt = data[i][3];
					    		var yxqz = data[i][4];
					    		var zjcx = data[i][5];
					    		var ljjf = data[i][6];
					    		var strtemp = "<tr><td>"+sfzmhm+"</td><td>"+xm+"</td><td>"+cclzrq+"</td><td>"+zt+"</td><td>"+yxqz+"</td><td>"+zjcx+"</td><td>"+ljjf+"</td></tr>";
								addstr += strtemp;
					    	}
				    	}else{
				    		addstr += "<tr><td colspan=\"7\" style=\"text-align: center\"><font color='red'>无驾驶证档案信息</font>&nbsp;&nbsp;</td></tr>";
				    	}
					}else{
						addstr += "<tr><td colspan=\"7\" style=\"text-align: center\"><font color='red'>无驾驶证档案信息</font>&nbsp;&nbsp;</td></tr>";
					}
					$("#datableid").append(addstr);
				}
			});
			$("#datableid").append("<tr><td colspan=\"7\" style=\"text-align: center\"><input type=\"button\" class=\"bnt\" style=\"cursor: pointer; margin:0 0 5px 0;\" onclick=\"settab(this,0);\" value=\"继续采集\"/></td></tr>");
			//settab(this,1);
			$.ajax({
				type:'POST',
				url:'<%=request.getContextPath()%>/sfrz_ajax/getXphzList.action',
				data:{sfzmhm:sfzmhmval},
				dataType: 'json',
				cache:false,
				async:false,
				error:function(){
					alert("读取数据异常!");
				},
				success:function(data){
					var addstr;
					addstr = "<tr class=\"tr1\"><td colspan=\"7\"><strong>相片回执信息</strong></td></tr>";
					if(null != data){
						addstr += "<tr class=\"tr1\"><td>选择</td><td>身份证明号码</td><td width='15%'>回执编号</td><td>相片日期</td><td>相片</td></tr>";
				    	if(data.length > 0){
				    		for(var i = 0; i < data.length; i ++){
					    		var sfzmhm = data[i][0];
					    		var photo_no = data[i][1];
					    		var czrq = data[i][2];
					    		var url = '<%=request.getContextPath()%>/servlet/zzsxphzImageServlet?pid=' + photo_no;
					    		var checkstr = '';
					    		if(i == 0){
					    			checkstr = "checked='checked'";
					    		}
					    		var strtemp = "<tr><td><input type='radio' name='xphzxz' value='"+photo_no+"' "+checkstr+" /></td><td>"+sfzmhm+"</td><td>"+photo_no+"</td><td>"+czrq+"</td><td><img src = '"+url+"'  width='100px;' height='100px;' /></td></tr>";
								addstr += strtemp;
					    	}
				    	}else{
				    		addstr += "<tr><td colspan=\"5\" style=\"text-align: center\"><font color='red'>无相片回执信息</font>&nbsp;&nbsp;</td></tr>";
				    	}
					}else{
						addstr += "<tr><td colspan=\"5\" style=\"text-align: center\"><font color='red'>无相片回执信息</font>&nbsp;&nbsp;</td></tr>";
					}
					$("#datableid2").append(addstr);
				}
			});
			$("#datableid2").append("<tr><td colspan=\"7\" style=\"text-align: center\"><input type=\"button\" class=\"bnt\" style=\"cursor: pointer; margin:0 0 5px 0;\" onclick=\"getxphz();\" value=\"确定\"/></td></tr>");
			settab(this,2);
		}
	}
	function getLongin(){
		var num=$("input[type=radio][name='sfrzCjxxb.rzjs'][checked]").val();
		//var num=document.getElementById("sfrzCjxxb.rzjs").value;
		var loginUserval = $("#loginUser").val();
		var loginUser = checknotnull(document.getElementById("loginUser"),'请输入组织机构代码');
		if (loginUser != "true") {
			return false;
		}
		$("#datableid3").html("");
		$.ajax({
			type:'POST',
			url:'<%=request.getContextPath()%>/sfrz_ajax/getUserInfo.action?rejsval='+num,
			data:{loginUser:loginUserval},
			dataType: 'json',
			cache:false,
			async:false,
			error:function(){
				alert("读取数据异常!");
			},
			success:function(data){
				var addstr;
				addstr = "<tr class=\"tr1\"><td colspan=\"7\"><strong>公司信息</strong></td></tr>";
				if(null != data){
					addstr += "<tr class=\"tr1\"><td>名称</td><td>所属单位</td><td>用户来源</td><td>电子邮箱</td><td>手机号码</td><td>登记住所地址</td><td>状态</td></tr>";
			    	if(data.length > 0){
			    		$("#synFlag").val("");
			    		for(var i = 0; i < data.length; i ++){
			    			var loginUser=$("#loginUser").val();
				    		var loginTruename = data[i].loginTruename;
				    		var ssdw = data[i].ssdw;
				    		if(ssdw==undefined)
				    			ssdw="";
				    		var yhly = data[i].yhly;
				    		if(yhly==undefined)
				    			yhly="";
				    		var dzyx = data[i].dzyx;
				    		if(dzyx==undefined)
				    			dzyx="";
				    		var sjhm = data[i].sjhm;
				    		if(sjhm==undefined)
				    			sjhm="";
				    		var djzzdz = data[i].djzzdz;
				    		if(djzzdz==undefined)
				    			djzzdz="";
				    		var zt = data[i].zt;
				    		if(zt==undefined)
				    			zt="";
				    		if(yhly=="面签"||yhly=="金融+面签"||yhly=="微信+面签"){
					    		var strtemp = "<tr><td>"+loginTruename+"</td><td>"+ssdw+"</td><td>"+yhly+"</td><td>"+dzyx+"</td><td>"+sjhm+"</td><td>"+djzzdz+"</td><td>"+zt+"</td></tr>";
								addstr += strtemp;
								var synFlag = $("#synFlag").val();
									if(null != synFlag || "" != synFlag){
										$("#synFlag").val("");
										$("#synFlag").val(loginUser);
									}
							}else{
								addstr += "<tr><td colspan=\"7\" style=\"text-align: center\"><font color='red'>无窗口面签记录！</font>&nbsp;&nbsp;</td></tr>";
							}
				    	}
			    		$("#datableid3").append(addstr);
			    		$("#datableid3").append("<tr><td colspan=\"7\" style=\"text-align: center\"><input type=\"button\" class=\"bnt\" style=\"cursor: pointer; margin:0 0 5px 0;\" onclick=getzzjgdm('"+loginUser+"'); value=\"确定\"/></td></tr>");
			    		settab(this,3);
			    	}else{
			    		alert("无窗口面签记录，请先采集代办单位！");
			    	}
				}else{
					alert("无窗口面签记录，请先采集代办单位！");
				}
				
			}
		});
		$("#loginUser").val("");
		
		
	}
	function getxphz(){
		var xphzval = $("input:radio[name='xphzxz'][checked]").val();
		$("#xphz").val(xphzval);
		//$("#loginUser").val(loginUser);
		//document.getElementById("sfz_b2").disabled=false;
		$("#chkpic").val("1");
		settab(this,0);
	}
	
	function getzzjgdm(loginUser){
		$("#loginUser").val(loginUser);
		document.getElementById("sfz_b2").disabled=false;
		settab(this,0);
	}
	
	function getpic(){
		$("#xphztdid").html("");
		var xphz = checknotnull(document.getElementById("xphz"),"请输入相片回执号码!");
		if (xphz != "true") {
			return false;
		}else{
			var pid = $("#xphz").val();
			var url = '<%=request.getContextPath()%>/servlet/zzsxphzImageServlet?pid=' + pid;
			var imgstr = "<img src = '"+url+"'  width='100px;' height='100px;' />";
			$("#xphztdid").html(imgstr);
			$("#chkpic").val("1");
			/*var xphzimghtml = $("#xphztdid").html();
			alert($("#xphztdid").html());
			if("" != xphzimghtml){
				$("#chkpic").val("1");
				alert("1");
			}else{
				alert("2");
			}*/
		}
	}
	
	function contentLoad(){
		var edittype = '${editType}';
		var rzjs = '${sfrzCjxxb.rzjs}';
		var xb = '${sfrzCjxxb.xb}';
		var sfsh = '${sfrzCjxxb.sfsh}';
		if ('查看' == edittype) {
			$("input[type=radio][name='sfrzCjxxb.rzjs'][value='"+rzjs+"']").attr("checked",true);
			$("input[type=radio][name='sfrzCjxxb.xb'][value='"+xb+"']").attr("checked",true);
			$("input[type=radio][name='sfrzCjxxb.sfsh'][value='"+sfsh+"']").attr("checked",true);
		}else{
			sxinit();
			setTimeout(
				function(){
					var obj2 = window.document.getElementById("ScanCtrl");
					obj2.StartPreview();
				},1000
			);
		}
	}
	//赋值
	function CreateFolder(src){
		var strFolder = src;
		var objFSO = new ActiveXObject("Scripting.FileSystemObject");
		// 检查文件夹是否存在
		if (!objFSO.FolderExists(strFolder)){
		   // 创建文件夹
		   var strFolderName = objFSO.CreateFolder(strFolder);
		}
	}
	
	function daiban1(){
		document.getElementById("sfrzCjxxb.sfzmmc").value="A";
		$("#dwtd").hide();
		$("#dbtd").hide();
		$("#synFlag").val("");
		$("#dmz").hide();
		$("#sfz").show();
		$("#dmzmc").hide();
		$("#sfzxm").show();
	}
	function daiban(){
		document.getElementById("sfrzCjxxb.sfzmmc").value="A";
		$("#dwtd").hide();
		$("#dbtd").show();
		$("#synFlag").val("");
		$("#dmz").hide();
		$("#sfz").show();
		$("#dmzmc").hide();
		$("#sfzxm").show();
	}
	function zydaiban(){
		document.getElementById("sfrzCjxxb.sfzmmc").value="A";
		$("#dwtd").hide();
		$("#dbtd").show();
		$("#dmz").hide();
		$("#sfz").show();
		$("#dmzmc").hide();
		$("#sfzxm").show();
		$("#synFlag").val("");
		document.getElementById("sfz_b2").disabled=true;
		alert("请根据组织机构代码查询是否面签记录。");
	}
	function danwei(){
		document.getElementById("sfrzCjxxb.sfzmmc").value="B";
		$("#dwtd").show();
		$("#dbtd").hide();
		$("#synFlag").val("");
		$("#djzsdz").val("");
		$("#txdz").val("");
		$("#jzzhm").val("");
		$("#jzzyxq").val("");
		$("#jzzdz").val("");
		$("#dmz").show();
		$("#sfz").hide();
		$("#dmzmc").show();
		$("#sfzxm").hide();
}
	
	function YzdbrSfff(sfzmhmval){
		var ss;
		$.ajax({
			type:'POST',
			url:'<%=request.getContextPath()%>/sfrz/sfrz_getYzdbrSfff.action',
			data:{sfzmhm:sfzmhmval},
			dataType: 'json',
			cache:false,
			async:false,
			error:function(){
				alert("读取数据异常!");
			},
			success:function(data){
				if(data!='0'){
					alert("黑名单代办人，不能进行采集");
					ss= 'false';
				}
			}
		});
		return ss;
		
	}
	
	function tjdbr(){
		var num=$("input[type=radio][name='sfrzCjxxb.rzjs'][checked]").val();
		$("#xztableid").html("");
		var inittr = "<tr class='tr1'><th style='width: 10%;' align='center'>序号</th><th style='width: 40%;' align='center'>身份证号码</th><th style='width: 20%;' align='center'>姓名</th><th style='width: 15%;' align='center'>操作</th></tr>";
		$("#xztableid").append(inittr);
		var synFlag = $("#synFlag").val();
		if(null != synFlag && "" != synFlag){
			if(synFlag.indexOf(",") >= 0){
				var v_synFlag = synFlag.split(',');
				if(v_synFlag != null && v_synFlag.length > 0){
					for(var k = 0; k < v_synFlag.length; k++){
						var s_synFlag = v_synFlag[k];
						$.ajax({
							type:'POST',
							url:'<%=request.getContextPath()%>/sfrz_ajax/getdbrList.action?rzjs='+num,
							data:{sfzmhm:s_synFlag},
							dataType: 'json',
							cache:false,
							async:false,
							error:function(data){ 
								alert("读取数据异常!");
							},
							success:function(data){
								if(null != data || '' != data){
									if('4'==num){
										var addstr = '';
										if(null != data){
									    	if(data.length > 0){
									    		for(var i = 0; i < data.length; i ++){
										    		var login_user = data[i][0];
										    		var login_truename = data[i][1];
										    		var id=data[i][2];
										    		var ii = i + 1;
										    		var tzval = '';
										    		if(id != null && id != ''){
										    			tzval = "<a href='javascript:void(0);' onclick=\"javascript:getdbr('"+id+"');\">查看</a>";
										    		}
										    		var strtemp = "<tr id='"+login_user+"'><td>"+ii+"</td><td>"+login_user+"</td><td>"+login_truename+"</td><td>"+tzval+"&nbsp;&nbsp;&nbsp;<a href='javascript:void(0);' onclick=\"javascript:deletetr('"+login_user+"');\">删除该行</a></td></tr>";
													addstr += strtemp;
										    	}
									    	}
											$("#xztableid").append(addstr);
										}else{
											alert('没有该代办员相关信息!');
										}
									}else{
										$.ajax({
											type:'POST',
											url:'<%=request.getContextPath()%>/sfrz_ajax/getRscDby.action',
											data:{sfzmhms:data.ssdw},
											dataType: 'json',
											cache:false,
											async:false,
											error:function(data1){ 
												alert("读取数据异常!");
											},
											success:function(data1){
												if(null != data1 || '' != data1){
													$.ajax({
														type:'POST',
														url:'<%=request.getContextPath()%>/sfrz_ajax/getRscDbyZjxxb.action',
														data:{sfzmhm:data1.xh},
														dataType: 'json',
														cache:false,
														async:false,
														error:function(data1){ 
															alert("读取数据异常!");
														},
														success:function(data2){
															if(null != data2 || '' != data2){
																var addstr = '';
														    	if(data.length > 0){
														    		for(var i = 0; i < data.length; i ++){
															    		var login_user = data[i][0];
															    		var login_truename = data[i][1];
															    		var id=data[i][2];
															    		var ii = i + 1;
															    		var tzval = '';
															    		if(id != null && id != ''){
															    			tzval = "<a href='javascript:void(0);' onclick=\"javascript:getdbr('"+id+"');\">查看</a>";
															    		}
															    		var strtemp = "<tr id='"+login_user+"'><td>"+ii+"</td><td>"+login_user+"</td><td>"+login_truename+"</td><td>"+tzval+"&nbsp;&nbsp;&nbsp;<a href='javascript:void(0);' onclick=\"javascript:deletetr('"+login_user+"');\">删除该行</a></td></tr>";
																		addstr += strtemp;
															    	}
														    	}
																$("#xztableid").append(addstr);
															}else{
																alert('没有该代办员相关信息!');
															}
														}
													});
													
												}else{
													alert('没有该代办员相关信息!');
												}
											}
										});
									}
								}else{
									alert('没有该代办员相关信息!');
								}
							}
						});
					}
				}
			}else{
				$.ajax({
					type:'POST',
					url:'<%=request.getContextPath()%>/sfrz_ajax/getdbrList.action?rzjs='+num,
					data:{sfzmhm:synFlag},
					dataType: 'json',
					cache:false,
					async:false,
					error:function(data){ 
						alert("读取数据异常!");
					},
					success:function(data){
						if(null == data || '' == data){
							alert('没有相关信息!');
						}else{
							var addstr = '';
							if(null != data){
						    	if(data.length > 0){
						    		for(var i = 0; i < data.length; i ++){
							    		var login_user = data[i][0];
							    		var login_truename = data[i][1];
							    		var id=data[i][2];
							    		var ii = i + 1;
							    		var tzval = '';
							    		if(id != null && id != ''){
							    			tzval = "<a href='javascript:void(0);' onclick=\"javascript:getdbr('"+id+"');\">查看</a>";
							    		}
							    		var strtemp = "<tr id='"+login_user+"'><td>"+ii+"</td><td>"+login_user+"</td><td>"+login_truename+"</td><td>"+tzval+"&nbsp;&nbsp;&nbsp;<a href='javascript:void(0);' onclick=\"javascript:deletetr('"+login_user+"');\">删除该行</a></td></tr>";
										addstr += strtemp;
							    	}
						    	}
								$("#xztableid").append(addstr);
							}
						}
					}
				});
			}
		}
		art.dialog({
		 	fixed: true,
	        resize: false,
		    content: $('#divSelPtmoValue').html(),
		    title: '添加代办人',
			lock: true,
		    opacity: 0.1,
		    padding: 5,
			cancelVal: '关闭',
			cancel: true
	     });
	}
	
	function adddbr(){
		var num=$("input[type=radio][name='sfrzCjxxb.rzjs'][checked]").val();
		//判断身份证号码
		var sfzhmval=$("#sfzhm").val();
		var ycval = $("#synFlag").val();
		var sfzhm = checknotnull(document.getElementById("sfzhm"),'请输入身份证明号码');
		if (sfzhm != "true") {
			return false;
		}
		if(ycval.indexOf(sfzhmval) >= 0){
			alert('不能添加同一个代办人！');
		}else{
			$.ajax({
				type:'POST',
				url:'<%=request.getContextPath()%>/sfrz_ajax/getdbrList.action?rzjs='+num,
				data:{sfzmhm:sfzhmval},
				dataType: 'json',
				cache:false,
				async:false,
				error:function(data){ 
					alert("读取数据异常!");
				},
				success:function(data){
					if(null == data || '' == data){
						alert('没有相关信息!');
					}else{
						var addstr = '';
						if(null != data){
					    	if(data.length > 0){
					    		for(var i = 0; i < data.length; i ++){
						    		var login_user = data[i][0];
						    		var login_truename = data[i][1];
						    		var id=data[i][2];
						    		var ii = i + 1;
						    		var tzval = '';
						    		if(id != null && id != ''){
						    			tzval = "<a href='javascript:void(0);' onclick=\"javascript:getdbr('"+id+"');\">查看</a>";
						    		}
						    		var strtemp = "<tr id='"+login_user+"'><td>"+ii+"</td><td>"+login_user+"</td><td>"+login_truename+"</td><td>"+tzval+"&nbsp;&nbsp;&nbsp;<a href='javascript:void(0);' onclick=\"javascript:deletetr('"+login_user+"');\">删除该行</a></td></tr>";
									addstr += strtemp;
									var synFlag = $("#synFlag").val();
									if(null == synFlag || "" == synFlag){
										$("#synFlag").val(login_user);
									}else{
										$("#synFlag").val(synFlag + "," + login_user);
									}
						    	}
					    	}
						$("#xztableid").append(addstr);
					}
				}
			}
		});
	}
		$("#sfzhm").val("");
}
	function deletetr(loginUser){
			$("#"+loginUser).remove();
			var synFlag = $("#synFlag").val();
			if(null != synFlag && '' != synFlag){
				if(synFlag.indexOf(",") >= 0){
					var v_synFlag = synFlag.split(',');
					if(v_synFlag != null && v_synFlag.length > 0){
						for(var k = 0; k < v_synFlag.length; k++){
							var s_synFlag = v_synFlag[k];
							if(s_synFlag.indexOf(loginUser) >= 0){
								var indexval = synFlag.indexOf(s_synFlag);
								var qval = synFlag.substr(0,indexval-1);
								var hval = synFlag.substr(indexval+s_synFlag.length+1,synFlag.length);
								$("#synFlag").val(qval + hval);
							}
						}
					}
				}else{
					if(synFlag.indexOf(loginUser) >= 0){
						$("#synFlag").val("");
					}
				}
			}
		}
	function getdbr(id){
		window.open('<%=request.getContextPath()%>/sfrz/sfrz_initView.action?sfrzCjxxb.cid=' + id ,'info','width='+(window.screen.availWidth-10)+',height='+(window.screen.availHeight-30)+ ',top=0,left=0,toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=yes');
		
	}

</script>
<!-- 手写板读取JS开始 -->
<script>
	var sxobj;
	function sxinit(){
		//初始化手写板
		sxobj = document.getElementById("HWPenSign");
        sxobj.HWSetBkColor(0xE0F8E0);
		sxobj.HWSetCtlFrame(2, 0x000000);
		sxobj.HWInitialize();
	}
	function Button14_onclick() {
	    sxobj.HWSetFilePath("C:\\printzj4.jpg"); 
	    sxobj.HWSaveFile();
	    document.all["img06"].src="C:\\printzj4.jpg";
		document.all["img06a"].href="C:\\printzj4.jpg";
		var file02val = $("#file06").val();
		if(file02val != "C:\\printzj4.jpg"){
			$("#file06").val("C:\\printzj4.jpg");
			var obj = document.getElementById("file06");
			setTimeout(function(){
				var sd=inputtext("C:\\printzj4.jpg",obj);
			},100);
		}
		//document.getElementById("ReadResult2").innerHTML="抓拍图片成功....";
		var fso = new ActiveXObject("Scripting.FileSystemObject");
		var f2 = fso.GetFile("C:\\printzj4.jpg");
		var  f2d;
		f2d = f2.DateLastModified;
		var d2 = new Date(f2d);
		$("#zjzpdate4").val(d2.getTime());
		var num2=$("input[type=radio][name='sfrzCjxxb.rzjs'][checked]").val();
		if(num2=='5'){
			setTimeout(
				function(){
					//指纹
					var finger1=$('#fingerOne').val();
					var finger2=$('#fingerThree').val();
					var finger3=$('#fingerTwo').val();
					var finger4=$('#fingerFour').val();
					if(finger1==""||finger2==""||finger3==""||finger4==""){
						showZhiWen();
					}
					//指纹超连接
					document.getElementById("zwTd").style.display="inline";
				
			},1000)
		}
		openbnt("dybnt");
	}
		//打开指纹窗口
	function showZhiWen(){
		window.open('<%=request.getContextPath()%>/pages/sfrz/zhiwen.jsp','zhiwen','width=500,height=300,top='+(screen.height-300)/2+',left='+(screen.width-500)/2+',toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no');
	}
		
	function Button3_onclick() {
	    sxobj.HWClearPenSign();
	    document.getElementById("img06").src=path+'/images/cp.gif';
		document.getElementById("img06a").src=path+'/images/cp.gif';
		$("#file06").val("");
		document.getElementById("file06").outerHTML = document.getElementById("file06").outerHTML;
		$("#zjzpdate4").val("");
		closebnt("dybnt");
	}
	function signComplete() {
	    Button14_onclick();
	}
	function signRestart() {
	     ;         
	}
</script>
<!-- 手写板读取JS结束 -->
<!-- 二代身份证读取JS开始 -->
<script>
	/*function nocontent() {
		//阻止消息上传
		event.cancelBubble = true;
		event.returnValue = false;
	}*/
	function ClearIDCard() {
		CVR_IDCard = document.getElementById("CVR_IDCard");
		CVR_IDCard.Name = "";
		CVR_IDCard.NameL = "";
		CVR_IDCard.Sex = "";
		CVR_IDCard.Nation = "";
		CVR_IDCard.Born = "";//出生日期
		CVR_IDCard.Address = "";
		CVR_IDCard.CardNo = "";
		CVR_IDCard.Police = "";//发放单位
		CVR_IDCard.Activity = "";//有效期
		CVR_IDCard.NewAddr = "";
		CVR_IDCard.Pnationl = "";
		return true;
	}
	
	//清空表单数据
	function ClearForm() {
		$("#dsrSfzCardname1").val("");
		$("#dsrSfzCardsex1").val("");
		$("#dsrSfzCardno1").val("");
		$("#dsrSfzCardaddress1").val("");
		document.getElementById("sfzxpimgid").src='<%=request.getContextPath()%>/images/cp.gif';
		document.getElementById("file1").outerHTML = document.getElementById("file1").outerHTML;
		return true;
	}
	
	function ClearForm2() {
		$("#dbrSfzCardname1").val("");
		$("#dbrSfzCardsex1").val("");
		$("#dbrSfzCardno1").val("");
		$("#dbrSfzCardaddress1").val("");
		document.getElementById("sfzxpimgid2").src='<%=request.getContextPath()%>/images/cp.gif';
		document.getElementById("file2").outerHTML = document.getElementById("file2").outerHTML;
		return true;
	}
	
	//禁止鼠标右键
	//document.oncontextmenu = nocontent;
	
	//当事人证件采集
	function ReadIDCard(flag) {
		CVR_IDCard = document.getElementById("CVR_IDCard");
		//图片路径
		CreateFolder("C:\\jjywslg_sfz1");
		CVR_IDCard.PhotoPath = 'C:\\jjywslg_sfz1';
		CVR_IDCard.TimeOut = 3;
		ClearIDCard();
		ClearForm();
		$("#chfile1").val("0");
		document.getElementById("ReadResult").innerHTML = "请放卡...";
		var strReadResult = CVR_IDCard.ReadCard;
		var pName = CVR_IDCard.NameL
		var pSex = CVR_IDCard.SexL
		var pNationL = CVR_IDCard.NationL;
		var pBorn = CVR_IDCard.BornL
		var pAddress = CVR_IDCard.Address;
		var pCardNo = CVR_IDCard.CardNo;
		var pPolice = CVR_IDCard.Police;
		var pActivity = CVR_IDCard.Activity;
		var pNewAddr = CVR_IDCard.NewAddr;
		var pActivityLFrom = CVR_IDCard.ActivityLFrom;
		var pActivityLTo = CVR_IDCard.ActivityLTo;
		var pPhotoBuffer = CVR_IDCard.GetPhotoBuffer;
		
		var num=$("input[type=radio][name='sfrzCjxxb.rzjs'][checked]").val();
		if(num=='5'&&pCardNo!=''){
			if(YzdbrSfff(pCardNo)=='false'){
				return false;
			}
		}
		$("#dsrSfzCardname1").val(pName);
		$("#dsrSfzCardsex1").val(pSex);
		$("#dsrSfzCardno1").val(pCardNo);
		$("#dsrSfzCardaddress1").val(pAddress);
		$("#xm").val(pName);
		if('男'==pSex){
			$("input[type=radio][name='sfrzCjxxb.xb'][value='1']").attr("checked",true);
		}else{
			$("input[type=radio][name='sfrzCjxxb.xb'][value='2']").attr("checked",true);
		}
		$("#sfzmhm").val(pCardNo);
		$("#djzsdz").val(pAddress);
		$("#txdz").val(pAddress);
		
		//$("#dsrsfzmhm").val(pCardNo);
		//$("#dsrxm").val(pName);
		$("#dsrsfzmmc").val("A");
		
		
		var sfz_name = pName.replace(/(^s*)|(\s*$)/g, "");
		var chuli_type = "1"
		if (chuli_type == "1") {
			ClearIDCard();
		} else {
	
		}
		SfrzA(pCardNo);
		//判断id长度是否大于4,如果大于则不调用
		if (pCardNo.length > 4) {
			setTimeout(function(){
				var ssd=inputtext("C:\\jjywslg_sfz1\\zp.bmp",sfrz_form.file1);
			},100);
			document.getElementById("sfzxpimgid").src="C:\\jjywslg_sfz1\\zp.bmp";
			document.getElementById("ReadResult").innerHTML = "读取成功...";
			openbnt("zp");
			$("#chfile1").val("1");
			
		} else {
			if (flag == "0") {
				alert("请拿起身份证再试！");
			}
		}
	}
	
	//代办人证件采集
	function ReadIDCard1(flag) {
		CVR_IDCard = document.getElementById("CVR_IDCard");
		//图片路径
		CreateFolder("C:\\jjywslg_sfz2");
		CVR_IDCard.PhotoPath = 'C:\\jjywslg_sfz2';
		CVR_IDCard.TimeOut = 3;
		ClearIDCard();
		ClearForm2();
		$("#chfile2").val("0");
		document.getElementById("ReadResult1").innerHTML = "请放卡...";
		var strReadResult = CVR_IDCard.ReadCard;
		var pName = CVR_IDCard.NameL
		var pSex = CVR_IDCard.SexL
		var pNationL = CVR_IDCard.NationL;
		var pBorn = CVR_IDCard.BornL
		var pAddress = CVR_IDCard.Address;
		var pCardNo = CVR_IDCard.CardNo;
		var pPolice = CVR_IDCard.Police;
		var pActivity = CVR_IDCard.Activity;
		var pNewAddr = CVR_IDCard.NewAddr;
		var pActivityLFrom = CVR_IDCard.ActivityLFrom;
		var pActivityLTo = CVR_IDCard.ActivityLTo;
		var pPhotoBuffer = CVR_IDCard.GetPhotoBuffer;
		
		var num=$("input[type=radio][name='sfrzCjxxb.rzjs'][checked]").val();
		if(num=='5'&&pCardNo!=''){
			if(YzdbrSfff(pCardNo)=='false'){
				return false;
			}
		}
		$("#dbrSfzCardname1").val(pName);
		$("#dbrSfzCardsex1").val(pSex);
		$("#dbrSfzCardno1").val(pCardNo);
		$("#dbrSfzCardaddress1").val(pAddress);
		//$("#xm").val(pName);
		//if('男'==pSex){
		//	$("input[type=radio][name='sfrzCjxxb.xb'][value='1']").attr("checked",true);
		//}else{
		//	$("input[type=radio][name='sfrzCjxxb.xb'][value='2']").attr("checked",true);
		//}
		//$("#sfzmhm").val(pCardNo);
		//$("#djzsdz").val(pAddress);
		//$("#txdz").val(pAddress);
		
		//$("#dbrsfzmmc").val("A");
		var sfz_name = pName.replace(/(^s*)|(\s*$)/g, "");
		var chuli_type = "1"
		if (chuli_type == "1") {
			ClearIDCard();
		} else {
	
		}
		
		//判断id长度是否大于4,如果大于则不调用
		if (pCardNo.length > 4) {
			setTimeout(function(){
				var ssd=inputtext("C:\\jjywslg_sfz2\\zp.bmp",sfrz_form.file2);
			},100);
			document.getElementById("sfzxpimgid2").src="C:\\jjywslg_sfz2\\zp.bmp";
			document.getElementById("ReadResult1").innerHTML = "读取成功...";
			openbnt("zp");
			$("#chfile2").val("1");
		} else {
			if (flag == "0") {
				alert("请拿起身份证再试！");
			}
		}
	}
</script>
<!-- 二代身份证读取JS结束 -->

<!-- 高拍仪和摄像头读取JS开始 -->
<script language="javascript">
	function vide(){
		$("#chfile01").val("0");
		$("#chfile02").val("0");
		document.getElementById("ReadResult2").innerHTML="开始抓拍图片...";
		var obj = window.document.getElementById("camera");
		var obj2 = window.document.getElementById("ScanCtrl");
		var sc = "print";
		document.getElementById("ReadResult2").innerHTML="正在生成图片....";
		obj.ImageSave("print");
		var path= "C:\\printzj.jpg";
		obj2.Scan(path);
		document.getElementById("flags").value="1";
			
		setTimeout(
	      	function(){
	      		document.all["img01"].src="C:\\printtx.jpg";
	      		document.all["img01a"].href="C:\\printtx.jpg";
	      		var file01val = $("#file01").val();
	      		//var file01val = $("#file01").val();
				if(file01val != "C:\\"+sc+"tx.jpg"){
					var ssd=inputtext("C:\\"+sc+"tx.jpg",sfrz_form.file01);
				}
				$("#chfile01").val("1");
		        setTimeout(
					function(){
						document.all["img02"].src="C:\\printzj.jpg";
						document.all["img02a"].href="C:\\printzj.jpg";
						var file02val = $("#file02").val();
						if(file02val != "C:\\"+sc+"zj.jpg"){
							$("#file02").val("C:\\"+sc+"zj.jpg");
							var sd=inputtext("C:\\"+sc+"zj.jpg",sfrz_form.file02);
						}
						$("#chfile02").val("1");
						document.getElementById("ReadResult2").innerHTML="抓拍图片成功....";
						var fso = new ActiveXObject("Scripting.FileSystemObject");
						var f1 = fso.GetFile("C:\\printtx.jpg");
						var f2 = fso.GetFile("C:\\printzj.jpg");
						var f1d, f2d;
						f1d = f1.DateLastModified;
						f2d = f2.DateLastModified;
						var d1 = new Date(f1d);
						var d2 = new Date(f2d);
						$("#xczpdate").val(d1.getTime());
						$("#zjzpdate").val(d2.getTime());
						openbnt("zp3");
						openbnt("zp4");
						openbnt("zp6");
						openbnt("zp7");
						openbnt("zp8");
				    },1000
				);
			},1000
	    );
	}
	
	function vide2(num){
		/*var num = 0;
		var iszp = $("#chfile01").val();
		if(iszp == "0"){
			alert("请先点击'抓拍图片'按钮拍摄当事人照片和证件照片!");
			return false;
		}
		for(var i = 3; i < 6; i++){
			if($("#file0"+i).val() == ""){
				num = i;
				break;
			}
		}
		if(num == 0){
			alert("最多抓拍4张图片!");
			return false;
		}*/
		var pic = 1;
		pic = num - 2;
		//$("#zj").show();
		//$("#zja").show();
		document.getElementById("ReadResult2").innerHTML="开始抓拍图片...";
		document.getElementById("ReadResult2").innerHTML="正在生成图片....";
		var obj2 = window.document.getElementById("ScanCtrl");
		var sc = "print";
		var path= "C:\\printzj"+pic+".jpg";
		obj2.Scan(path);
			
        setTimeout(
			function(){
				document.all["img0"+num].src="C:\\printzj"+pic+".jpg";
				document.all["img0"+num+"a"].href="C:\\printzj"+pic+".jpg";
				var file02val = $("#file0"+num).val();
				if(file02val != "C:\\"+sc+"zj"+pic+".jpg"){
					$("#file0"+num).val("C:\\"+sc+"zj"+pic+".jpg");
					var obj = document.getElementById("file0"+num);
					var sd=inputtext("C:\\"+sc+"zj"+pic+".jpg",obj);
				}
				document.getElementById("ReadResult2").innerHTML="抓拍图片成功....";
				var fso = new ActiveXObject("Scripting.FileSystemObject");
				var f2 = fso.GetFile("C:\\printzj"+pic+".jpg");
				var  f2d;
				f2d = f2.DateLastModified;
				var d2 = new Date(f2d);
				$("#zjzpdate"+pic).val(d2.getTime());
				if(3 == pic){
					openbnt("bcbnt");
				}
		    },2000
		);
	}
	
	function changevideo(){
		var obj = window.document.getElementById("camera");
		obj.changevideo();
	}
	
	function delzjtp(num){
		document.getElementById("img0"+num).src=path+'/images/cp.gif';
		document.getElementById("img0"+num+"a").src=path+'/images/cp.gif';
		document.getElementById("ReadResult2").innerHTML="等待抓拍...";
		$("#file0"+num).val("");
		document.getElementById("file0"+num).outerHTML = document.getElementById("file0"+num).outerHTML;
		var dateid = num - 2;
		$("#zjzpdate"+dateid).val("");
		var iscontain = 0;
		for(var i = 3; i < 7; i++){
			if($("#file0"+i).val() != ""){
				iscontain = 1;
				break;
			}
		}
		/*if(iscontain == "0"){
			$("#zj").hide();
			$("#zja").hide();
		}*/
	}
		
	
</script>

<!-- 组织机构代码证读取JS开始 -->
<script type="text/javascript">
	function btnGetOrgInfo_onclick() {
		if (document.getElementById("ddlOrgCom").value.length <= 0) {
			alert("请选择设备接口！");
			return false;
		}
		document.all['ReadResultj'].innerHTML = "读取中...";
		var objectvide = document.getElementById("Videoui1");
		var inread = objectvide
				.ReadCardString(document.getElementById("ddlOrgCom").value);
	
		if (inread == "") {
			document.all['ReadResultj'].innerHTML = "等待读取...";
			return false;
		}
		k = inread.split(";")
		for (i = 0; i < k.length - 1; i++) {
			try {
				a = k[i].split("@")
				if (a[1] == 'dwdm') {
					//alert(a[2]);
					document.getElementById("dsrZzjgZh1").value = a[2];
					//document.getElementById("dsrzzjgdm").value = a[2];
					document.getElementById("sfzmhm").value =a[2];
					//alert(document.getElementById("dsrzzjgdm").value);
				}
				if (a[1] == 'dwmc') {
					document.getElementById("dsrZzjgDwmc1").value = a[2];
					document.getElementById("xm").value = a[2];
				}
				if (a[1] == 'dwzz') {
					document.getElementById("dsrZzjgDz1").value = a[2];
					document.getElementById("djzsdz").value = a[2];
					document.getElementById("txdz").value = a[2];
				}
				if (a[1] == 'njrq') {
					document.getElementById("dsrZzjgNjrq1").value = a[2];
				}
				if (a[1] == 'njqx') {
					document.getElementById("dsrZzjgNjyxq1").value = a[2];
				}
				if (a[1] == 'frdb') {
					document.getElementById("dsrZzjgFrdb1").value = a[2];
					document.getElementById("dsrdwmc").value = a[2];
				}
				if (a[1] == 'dwxz') {
					document.getElementById("dsrZzjgYyzz1").value = a[2];
				}
				
			} catch (e) {
	
			}
		}
		document.all['ReadResultj'].innerHTML = "读取成功...";
		openbnt("zp");
	}
	
	function btnGetOrgInfo_onclick2(){
		if (document.getElementById("dbrddlOrgCom").value.length <= 0) {
			alert("请选择设备接口！");
			return false;
		}
		document.all['ReadResultj2'].innerHTML = "读取中...";
		var objectvide = document.getElementById("Videoui1");
		var inread = objectvide
				.ReadCardString(document.getElementById("dbrddlOrgCom").value);
	
		if (inread == "") {
			document.all['ReadResultj2'].innerHTML = "等待读取...";
			return false;
		}
		k = inread.split(";")
		for (i = 0; i < k.length - 1; i++) {
			try {
				a = k[i].split("@")
				if (a[1] == 'dwdm') {
					document.getElementById("dbrZzjgZh1").value = a[2];
					document.getElementById("dbrzzjgdm").value = a[2];
				}
				if (a[1] == 'dwmc') {
					document.getElementById("dbrZzjgDwmc1").value = a[2];
				}
				if (a[1] == 'dwzz') {
					document.getElementById("dbrZzjgDz1").value = a[2];
				}
				if (a[1] == 'njrq') {
					document.getElementById("dbrZzjgNjrq1").value = a[2];
				}
				if (a[1] == 'njqx') {
					document.getElementById("dbrZzjgNjyxq1").value = a[2];
				}
				if (a[1] == 'frdb') {
					document.getElementById("dbrZzjgFrdb1").value = a[2];
					document.getElementById("dbrdwmc").value = a[2];
				}
				if (a[1] == 'dwxz') {
					document.getElementById("dbrZzjgYyzz1").value = a[2];
				}
			} catch (e) {
	
			}
		}
		document.all['ReadResultj2'].innerHTML = "读取成功...";
		openbnt("zp");
	}
</script>
<!-- 组织机构代码证读取JS结束 -->

<script language="vbscript">
	Function inputtext(vv,obj)
	obj.focus()
	Set WshShell = CreateObject("WScript.Shell")
	WshShell.SendKeys(vv)
	Set WshShell = Nothing
	End Function
</script>

</head>
<body onload="contentLoad();">
	<DIV id=admin_title>
		<DIV class=logo>
			<IMG src="<%=request.getContextPath()%>/images/frame_19.gif">
		</DIV>
		<DIV class=title>
			${editType }代办员信息&nbsp;<A
				href="<%=request.getContextPath()%>/sfrz/download.action">&nbsp;<FONT
				color=red>点击下载控件驱动程序!</FONT>
				&nbsp;&nbsp;&nbsp;<A
				href="<%=request.getContextPath()%>/sfrz/czscload.action">&nbsp;<FONT
				color=red>点击下载操作手册!</FONT>
			</A>
		</DIV>
	</DIV>

	<!-- http://100.100.21.61/cmp_new/view_pic.asp?efid=1332A1500090C4967 -->
	<div id=admin_main style="WIDTH:95%; margin:0 auto;margin: 25px;margin-top:15px;font-size: 12px;color: #000; z-index: -1000;">
		<div class="div">
			<form action="<%=request.getContextPath()%>/sfrz/sfrz_editSfrzCjxxb.action" method="post" id="sfrz_form" enctype="multipart/form-data" target="abc">
			<iframe id="abc" name="abc" width="0" height="0" style="display: none;"></iframe>
			<input type="hidden" id="chfile1" value="0" />
			<input type="hidden" id="chfile2" value="0" />
			<input type="hidden" id="chfile01" value="0" />
			<input type="hidden" id="chfile02" value="0" />
			<input type="hidden" name="xczpdate" id="xczpdate" />
			<input type="hidden" name="zjzpdate" id="zjzpdate" />
			<input type="hidden" name="zjzpdate1" id="zjzpdate1" />
			<input type="hidden" name="zjzpdate2" id="zjzpdate2" />
			<input type="hidden" name="zjzpdate3" id="zjzpdate3" />
			<input type="hidden" name="zjzpdate4" id="zjzpdate4" />
			<input type="hidden" name="zjzpdate5" id="zjzpdate5" />
			<input type="hidden" name="zjzpdate6" id="zjzpdate6" />
			<input type="hidden" name="zjzpdate7" id="zjzpdate7" />
			<input type="hidden" name="sfrzCjxxb.rzly" id="rzly" value="B" />
			<input type="hidden" id="chkdy" value="0" />
			<input type="hidden" id="chkpic" value="0" />
			<div id="tableid0" class="tablist block">
				<table class="edittable" border="0" cellpadding="0" cellspacing="0" align="center">
					<%--<tr>
						<td colspan="4" id="slywlx" style= padding-left: 15px; border-left: 0; border-right: 0;">
							<s:if test="#request.editType != '查看'">采集流水号：<input type="text" name="sfrzCjxxb.cid" id="cidid" onkeyup="clearspace(this); toUpers(this);" onblur="clearallspace(this);" size="20"/> &nbsp;&nbsp;<input type="button" id="btnlsh" class="bnt" style="cursor: pointer; margin:0 0 5px 0;" value="查&nbsp;询"/></s:if>
							<s:else>
								采集流水号: ${sfrzCjxxb.cid}
							</s:else>
						</td>
					</tr>
					--%><tr>
						<td colspan="2" style="border-left: 0; border-right: 0;">&nbsp;&nbsp;
							认证角色：
							<s:if test="#request.users.name=='121013'||#request.users.name=='121016'||#request.users.name=='054417'">
								<input type="radio" name="sfrzCjxxb.rzjs" value="3" onclick="danwei()"/>&nbsp;代办单位
								<input type="radio" name="sfrzCjxxb.rzjs" value="5" onclick="zydaiban()"/>&nbsp;专业代办人&nbsp;<font style="color: red;">*</font>
								<%--<input type="Hi" name="sfrzCjxxb.rzjs" value="2" onclick="danwei()"/>&nbsp;普通单位
								<input type="radio" name="sfrzCjxxb.rzjs" value="4" onclick="daiban()"/>&nbsp;普通代办人 --%>
							</s:if>
							<s:else>
								<input type="radio" name="sfrzCjxxb.rzjs" value="1" checked="checked" onclick="daiban1()"/>&nbsp;个人&nbsp;&nbsp;
								<input type="radio" name="sfrzCjxxb.rzjs" value="3" onclick="danwei()"/>&nbsp;代办单位
								<input type="radio" name="sfrzCjxxb.rzjs" value="5" onclick="zydaiban()"/>&nbsp;专业代办人&nbsp;<font style="color: red;">*</font>
							</s:else>
						</td>
						<td style="display:none;" id="dbtd">
							&nbsp;&nbsp;组织机构代码：<input type="text" id="loginUser" value=""/>
							<input type="button" id="t2" class="bnt" onclick="getLongin();" style="cursor: pointer; margin:0 0 5px 0;" value="查&nbsp;询"/>
						</td>
						<td style="display:none;" id="dwtd">
							<input type="button" id="t3" class="bnt" onclick="tjdbr();" style="cursor: pointer; margin:0 0 5px 0;" value="添加代办人"/>
							<input type="hidden" id="synFlag" name="sfrzCjxxb.tranFlag" />
						</td>
					</tr>
					<tr>
						<td colspan="4" style="border-left: 0; border-right: 0;">&nbsp;&nbsp;
							身份证明名称：<s:select list="#{'A':'二代居民身份证','B':'组织机构代码证书',
										'C':'军官证','D':'士兵证','E':'军官离退休证','F':'境外人员身份证明',
										'G':'外交人员身份证明','H':'居民户口薄','J':'单位注销证明',
										'K':'居住暂住证明','L':'驻华机构证明','M':'临时居民身份证'}" theme="simple"
											id="sfzmmc"
											listKey="key" listValue="value" name="sfrzCjxxb.sfzmmc" value="#request.sfrzCjxxb.sfzmmc"></s:select>&nbsp;<font style="color: red;">*</font>&nbsp;&nbsp;&nbsp;&nbsp;
							<font style="font-size: 12px;" id="sfz">身份证明号码</font><font style="font-size: 12px;" id="dmz">组织机构代码证</font>：<input type="text" name="sfrzCjxxb.sfzmhm" id="sfzmhm" value="${request.sfrzCjxxb.sfzmhm}" />&nbsp;<font style="color: red;">*</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</td>
					</tr>
				<table  class="edittable" border="0" cellpadding="0" cellspacing="0" align="center">
					<tr>
						<td>
							<font style="font-size: 12px;" id="sfzxm">&nbsp;&nbsp;姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</font>
							<font style="font-size: 12px;" id="dmzmc">&nbsp;单位名称：</font><input type="text" name="sfrzCjxxb.xm" id="xm" value="${request.sfrzCjxxb.xm}" style="width: 100px;"/>&nbsp;<font style="color: red;">*</font>&nbsp;&nbsp;&nbsp;&nbsp;
						</td>
						<td>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别&nbsp;：<input type="radio" name="sfrzCjxxb.xb" value="1" checked="checked"/>&nbsp;男&nbsp;&nbsp;
							<input type="radio" name="sfrzCjxxb.xb" value="2"/>&nbsp;女&nbsp;<font style="color: red;">*</font>&nbsp;&nbsp;&nbsp;&nbsp;
						</td>
						<td>		
							&nbsp;身份证号码有效期：<input type="text" name="sfrzCjxxb.sfzmhmyxq" id="sfzmhmyxq" value="${request.sfrzCjxxb.sfzmhmyxq}" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="hidden" id="ccid" name="ccid">
						</td>
						<td>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;国&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;籍：<s:select list="#request.gjlist" theme="simple" id="gj" listKey="key" listValue="key+'---'+value" name="sfrzCjxxb.gj" value="#request.sfrzCjxxb.gj"></s:select>&nbsp;<font style="color: red;">*</font>&nbsp;&nbsp;&nbsp;&nbsp;
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;&nbsp;民&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;族：<input type="text" name="sfrzCjxxb.mz" style="width: 100px;" id="mz" value="${request.sfrzCjxxb.mz}" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</td>
						<td>
							&nbsp;&nbsp;固&nbsp;定&nbsp;电&nbsp;话：<input type="text" name="sfrzCjxxb.gddh" id="gddh" value="${request.sfrzCjxxb.gddh}"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</td>
						<td>			
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;移&nbsp;&nbsp;动&nbsp;&nbsp;电&nbsp;&nbsp;话：<input type="text" name="sfrzCjxxb.yddh" id="yddh" value="${request.sfrzCjxxb.yddh}" />&nbsp;<font style="color: red;">*</font>&nbsp;&nbsp;&nbsp;&nbsp;
						</td>
						<td>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;电子邮箱：<input type="text" name="sfrzCjxxb.dzyx" style="width: 212px;" id="dzyx" value="${request.sfrzCjxxb.dzyx}" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</td>
						
					</tr>
					<tr>
						<td>
							&nbsp;&nbsp;籍&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;贯：<input type="text" name="sfrzCjxxb.jg" style="width: 100px;" id="jg" value="${request.sfrzCjxxb.jg}" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</td>
						<td>						
							&nbsp;&nbsp;是&nbsp;否&nbsp;深&nbsp;户：<input type="radio" name="sfrzCjxxb.sfsh" value="1" checked="checked"/>&nbsp;深户&nbsp;&nbsp;
							<input type="radio" name="sfrzCjxxb.sfsh" value="2"/>&nbsp;非深户&nbsp;<font style="color: red;">*</font>&nbsp;&nbsp;&nbsp;&nbsp;
						</td>
						<td>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;通&nbsp;&nbsp;信&nbsp;&nbsp;地&nbsp;&nbsp;址：<input type="text" name="sfrzCjxxb.txdz" id="txdz" value="${request.sfrzCjxxb.txdz}"/>&nbsp;<font style="color: red;">*</font>&nbsp;&nbsp;&nbsp;&nbsp;
						</td>
						<td>
							&nbsp;登记住所地址：<input type="text" name="sfrzCjxxb.djzsdz" id="djzsdz" style="width: 212px;" value="${request.sfrzCjxxb.djzsdz}" />&nbsp;<font style="color: red;">*</font>&nbsp;&nbsp;&nbsp;&nbsp;
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;相片回执：<input type="text" name="sfrzCjxxb.xphz" id="xphz" value="${request.sfrzCjxxb.xphz}"  style="width: 100px;"/>&nbsp;&nbsp;&nbsp;&nbsp;
						</td>
						<td>
							&nbsp;居住证号码：<input type="text" name="sfrzCjxxb.jzzhm" id="jzzhm" value="${request.sfrzCjxxb.jzzhm}" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</td>
						<td>
							&nbsp;&nbsp;&nbsp;&nbsp;居&nbsp;住&nbsp;证&nbsp;有&nbsp;效&nbsp;期：<input type="text" name="sfrzCjxxb.jzzyxq" id="jzzyxq" value="${request.sfrzCjxxb.jzzyxq}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</td>
						<td>
							&nbsp;居&nbsp;住&nbsp;证&nbsp;地&nbsp;址：<input type="text" name="sfrzCjxxb.jzzdz" id="jzzdz" value="${request.sfrzCjxxb.jzzdz}"  style="width: 212px;"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</td>
					</tr>
					<tr>
						<td>			
							&nbsp;Q&nbsp;&nbsp;&nbsp;Q&nbsp;&nbsp;&nbsp;号：<input type="text" name="sfrzCjxxb.qq" id="qq" value="${request.sfrzCjxxb.qq}" style="width: 100px;"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</td>
						<td>
							&nbsp;微&nbsp;&nbsp;&nbsp;博&nbsp;&nbsp;&nbsp;&nbsp;号&nbsp;：<input type="text" name="sfrzCjxxb.wb" id="wb" value="${request.sfrzCjxxb.wb}" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</td>
						<td>	
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;微&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;信&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号&nbsp;：<input type="text" name="sfrzCjxxb.wx" id="wx" value="${request.sfrzCjxxb.wx}" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</td>
						<td></td>
						</tr>
					</table>
					<tr>
						<td colspan="5">
							<table class="idcardclass" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td style="width:15%; border-left: 1px solid; border-top: 1px solid; border-bottom: 1px solid; border-right: 1px solid; text-align: right; padding-right: 5px;">&nbsp;&nbsp;&nbsp;身份证明名称：</td>
									<td style="text-align: left; width:35%; border-left: 0px solid; border-top: 1px solid; border-bottom: 1px solid; border-right: 1px solid;">
										&nbsp;
										<s:select list="#{'A':'二代居民身份证','B':'组织机构代码证书',
										'C':'军官证','D':'士兵证','E':'军官离退休证','F':'境外人员身份证明',
										'G':'外交人员身份证明','H':'居民户口薄','J':'单位注销证明',
										'K':'居住暂住证明','L':'驻华机构证明','M':'临时居民身份证'}" theme="simple"
											id="dsrsfzmmc"
											listKey="key" listValue="value" name="sfrzCjxxb.dsrSfzmmc" value="#request.sfrzCjxxb.dsrSfzmmc" onchange="changedsrsfzmmc();"></s:select>&nbsp;<!-- <font style="color: red;">*</font> -->
									</td>
									<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
									<td style="text-align: right;width:15%; border-left: 1px solid; border-top: 1px solid; border-bottom: 1px solid; border-right: 1px solid ; padding-left: 20px;" >身份证明名称：</td>
									<td style="text-align: left; width:35%;border-left: 0px solid; border-top: 1px solid; border-bottom: 1px solid; border-right: 1px solid ;">
										&nbsp;
										<s:select list="#{'A':'二代居民身份证','B':'组织机构代码证书',
										'C':'军官证','D':'士兵证','E':'军官离退休证','F':'境外人员身份证明',
										'G':'外交人员身份证明','H':'居民户口薄','J':'单位注销证明',
										'K':'居住暂住证明','L':'驻华机构证明','M':'临时居民身份证'}" theme="simple"
											id="dbrsfzmmc"
											listKey="key" listValue="value" value="#request.sfrzCjxxb.dbrSfzmmc" onchange="changedbrsfzmmc();"></s:select>&nbsp;<!-- <font style="color: red;">*</font> -->
									</td>
								</tr>
							</table>
						</td>
					</tr>		
					<tr>
						<td colspan="4" style="border-left: 0; border-right: 0;">
							<div style="width: 48%; float: left;">
								<table id="dsrrtablesfz" class="edittable" border="0" cellpadding="0" cellspacing="0" align="center">
									<tr>
										<td style="text-align: right; border-left: 0; border-right: 0;">姓名：</td>
										<td>
											&nbsp;<input type="text" class="disabled" readonly="readonly"
											size="30" id="dsrSfzCardname1" name="sfrzCjxxb.dsrSfzCardname1"
											value="${sfrzCjxxb.dsrSfzCardname1}" />
										</td>
										<td rowspan="4" style="text-align: center;">
											<s:if test="#request.photolist.a1 == null || #request.photolist.a1 == ''">
												<img id="sfzxpimgid" src="<%=request.getContextPath()%>/images/cp.gif" width="108" height="120" border="0">
											</s:if>
											<s:else>
												<img src="http://100.100.21.61/cmp_new/view_pic.asp?efid=${photolist.a1}" id="sfzxpimgid" width="108" height="120" border="0">
											</s:else>
										</td>
									</tr>
									<tr>
										<td style="text-align: right; border-left: 0; border-right: 0;">性别：</td>
										<td>
											&nbsp;<input type="text" class="disabled" readonly="readonly"
											size="30" id="dsrSfzCardsex1" name="sfrzCjxxb.dsrSfzCardsex1"
											value="${sfrzCjxxb.dsrSfzCardsex1}" />
										</td>
									</tr>
									<tr>
										<td style="text-align: right; border-left: 0; border-right: 0;">身份证号：</td>
										<td>
											&nbsp;<input type="text" class="disabled" readonly="readonly"
											size="30" id="dsrSfzCardno1" name="sfrzCjxxb.dsrSfzCardno1"
											value="${sfrzCjxxb.dsrSfzCardno1}" />
										</td>
									</tr>
									<tr>
										<td style="text-align: right; border-left: 0; border-right: 0;">地址：</td>
										<td>
											&nbsp;<input type="text" class="disabled" readonly="readonly"
												size="30" id="dsrSfzCardaddress1" name="sfrzCjxxb.dsrSfzCardaddress1"
												value="${sfrzCjxxb.dsrSfzCardaddress1}" />
											<OBJECT classid="clsid:10946843-7507-44FE-ACE8-2B3483D179B7"
												codebase="CVR100.cab#version=3,0,3,3" id="CVR_IDCard"
												name="CVR_IDCard" width="108" height="110" align="middle"
												hspace="0" vspace="0" style="display: none;"></OBJECT>
										</td>
									</tr>
								</table>
								
								<!-- 组织机构证 -->
								<table id="dsrtablezzjgz" class="edittable" border="0" cellpadding="0" cellspacing="0" style="display: none;" align="center">
									<tr>
										<td colspan="2" style="border-top: 0px; border-left: 0; ">&nbsp;
											<strong>当事人</strong> 
											<select id="ddlOrgCom" name="ddlOrgCom">
												<option value="">
													请选择设备接口
												</option>
												<option value="1001">
													USB1
												</option>
												<option value="1002">
													USB2
												</option>
												<option value="1003">
													USB3
												</option>
												<option value="1004">
													USB4
												</option>
												<option value="1005">
													USB5
												</option>
												<option value="1006">
													USB6
												</option>
												<option value="1007">
													USB7
												</option>
												<option value="1008">
													USB8
												</option>
												<option value="1009">
													USB9
												</option>
												<option value="1">
													COM1
												</option>
												<option value="2">
													COM2
												</option>
												<option value="3">
													COM3
												</option>
												<option value="4">
													COM4
												</option>
												<option value="5">
													COM5
												</option>
												<option value="6">
													COM6
												</option>
												<option value="7">
													COM7
												</option>
												<option value="8">
													COM8
												</option>
												<option value="9">
													COM9
												</option>
											</select>
										</td>
									</tr>
									<tr>
										<td style="text-align: right; border-left: 0; border-right: 0;">组织机构证代码：</td>
										<td>
											&nbsp;<input type="text" 
												id="dsrZzjgZh1" name="sfrzCjxxb.dsrZzjgZh1"
												value="${sfrzCjxxb.dsrZzjgZh1}" size="35" />
										</td>
									</tr>
									<tr>
										<td style="text-align: right; border-left: 0; border-right: 0;">单位法人：</td>
										<td>
											&nbsp;<input type="text"
												id="dsrZzjgFrdb1" name="sfrzCjxxb.dsrZzjgFrdb1"
												value="${sfrzCjxxb.dsrZzjgFrdb1}" size="35" />
										</td>
									</tr>
									<tr>
										<td style="text-align: right; border-left: 0; border-right: 0;">营业执照：</td>
										<td>
											&nbsp;<input type="text" 
												id="dsrZzjgYyzz1" name="sfrzCjxxb.dsrZzjgYyzz1"
												value="${sfrzCjxxb.dsrZzjgYyzz1}" size="35" />
										</td>
									</tr>
									<tr>
										<td style="text-align: right; border-left: 0; border-right: 0;">单位名称：</td>
										<td>
											&nbsp;<input type="text" 
													id="dsrZzjgDwmc1" name="sfrzCjxxb.dsrZzjgDwmc1"
													value="${sfrzCjxxb.dsrZzjgDwmc1}" size="35" />
											<object id="Videoui1" height="0" width="0"
												classid="clsid:1EED6A72-4FA5-4F8A-B561-D892C6AFE027"></object>
										</td>
									</tr>
									<tr>
										<td style="text-align: right; border-left: 0; border-right: 0;">&nbsp;年检日期：</td>
										<td>
											&nbsp;<input type="text"
													id="dsrZzjgNjrq1" name="sfrzCjxxb.dsrZzjgNjrq1"
													value="${sfrzCjxxb.dsrZzjgNjrq1}" size="35" />
										</td>
									</tr>
									<tr>
										<td style="text-align: right; border-left: 0; border-right: 0;">&nbsp;年检有效期：</td>
										<td>
											&nbsp;<input type="text"
													id="dsrZzjgNjyxq1" name="sfrzCjxxb.dsrZzjgNjyxq1"
													value="${sfrzCjxxb.dsrZzjgNjyxq1}" size="35" />
										</td>
									</tr>
									<tr>
										<td style="text-align: right; border-left: 0; border-right: 0;">&nbsp;地址：</td>
										<td>
											&nbsp;<input type="text"
												id="dsrZzjgDz1" name="sfrzCjxxb.dsrZzjgDz1"
												value="${sfrzCjxxb.dsrZzjgDz1}" size="40" />
										</td>
									</tr>
								</table>
							</div>
							
							<div style="width: 48%; float: right;">
								<table id="dbrtablesfz" class="edittable" border="0" cellpadding="0" cellspacing="0" align="center">
									<tr>
										<td colspan="3" style="border-top: 0px; border-right: 0;">&nbsp;
											<strong>代办人</strong> 
										</td>
									</tr>
									<tr>
										<td style="text-align: right;">姓名：</td>
										<td>
											&nbsp;<input type="text" class="disabled" readonly="readonly"
											size="30" id="dbrSfzCardname1" name="sfrzCjxxb.dbrSfzCardname1"
											value="${sfrzCjxxb.dbrSfzCardname1}" />
										</td>
										<td rowspan="4" style="text-align: center; border-right: 0;">
											<s:if test="#request.photolist.a3 == null || #request.photolist.a3 == ''">
												<img id="sfzxpimgid2" src="<%=request.getContextPath()%>/images/cp.gif" width="108" height="120" border="0">
											</s:if>
											<s:else>
												<img src="http://100.100.21.61/cmp_new/view_pic.asp?efid=${photolist.a3}" id="sfzxpimgid2" width="108" height="120" border="0">
											</s:else>
										</td>
									</tr>
									<tr>
										<td style="text-align: right;">性别：</td>
										<td>
											&nbsp;<input type="text" class="disabled" readonly="readonly"
											size="30" id="dbrSfzCardsex1" name="sfrzCjxxb.dbrSfzCardsex1"
											value="${sfrzCjxxb.dbrSfzCardsex1}" />
										</td>
									</tr>
									<tr>
										<td style="text-align: right;">身份证号：</td>
										<td>
											&nbsp;<input type="text" class="disabled" readonly="readonly"
											size="30" id="dbrSfzCardno1" name="sfrzCjxxb.dbrSfzCardno1"
											value="${sfrzCjxxb.dbrSfzCardno1}" />
										</td>
									</tr>
									<tr>
										<td style="text-align: right;">地址：</td>
										<td>
											&nbsp;<input type="text" class="disabled" readonly="readonly"
												size="30" id="dbrSfzCardaddress1" name="sfrzCjxxb.dbrSfzCardaddress1"
												value="${sfrzCjxxb.dbrSfzCardaddress1}" />
										</td>
									</tr>
								</table>
								
								<!-- 组织机构证 -->
								<table id="dbrtablezzjgz" class="edittable" border="0" cellpadding="0" cellspacing="0" style="display: none;" align="center">
									<tr>
										<td colspan="2" style="border-top: 0px; border-right: 0;">&nbsp;
											<strong>代办人</strong> 
											<select id="dbrddlOrgCom" name="ddlOrgCom">
												<option value="">
													请选择设备接口
												</option>
												<option value="1001">
													USB1
												</option>
												<option value="1002">
													USB2
												</option>
												<option value="1003">
													USB3
												</option>
												<option value="1004">
													USB4
												</option>
												<option value="1005">
													USB5
												</option>
												<option value="1006">
													USB6
												</option>
												<option value="1007">
													USB7
												</option>
												<option value="1008">
													USB8
												</option>
												<option value="1009">
													USB9
												</option>
												<option value="1">
													COM1
												</option>
												<option value="2">
													COM2
												</option>
												<option value="3">
													COM3
												</option>
												<option value="4">
													COM4
												</option>
												<option value="5">
													COM5
												</option>
												<option value="6">
													COM6
												</option>
												<option value="7">
													COM7
												</option>
												<option value="8">
													COM8
												</option>
												<option value="9">
													COM9
												</option>
											</select>
										</td>
									</tr>
									<tr>
										<td style="text-align: right;">组织机构证代码：</td>
										<td style="border-right: 0;">
											&nbsp;<input type="text" readonly="readonly" class="disabled"
												id="dbrZzjgZh1" name="sfrzCjxxb.dbrZzjgZh1"
												value="${sfrzCjxxb.dbrZzjgZh1}" size="35" />
										</td>
									</tr>
									<tr>
										<td style="text-align: right;">单位法人：</td>
										<td style="border-right: 0;">
											&nbsp;<input type="text" readonly="readonly" class="disabled"
												id="dbrZzjgFrdb1" name="sfrzCjxxb.dbrZzjgFrdb1"
												value="${sfrzCjxxb.dbrZzjgFrdb1}" size="35" />
										</td>
									</tr>
									<tr>
										<td style="text-align: right;">营业执照：</td>
										<td style="border-right: 0;">
											&nbsp;<input type="text" readonly="readonly" class="disabled"
												id="dbrZzjgYyzz1" name="sfrzCjxxb.dbrZzjgYyzz1"
												value="${sfrzCjxxb.dbrZzjgYyzz1}" size="35" />
										</td>
									</tr>
									<tr>
										<td style="text-align: right;">单位名称：</td>
										<td style="border-right: 0;">
											&nbsp;<input type="text" readonly="readonly" class="disabled"
													id="dbrZzjgDwmc1" name="sfrzCjxxb.dbrZzjgDwmc1"
													value="${sfrzCjxxb.dbrZzjgDwmc1}" size="35" />
										</td>
									</tr>
									<tr>
										<td style="text-align: right;">&nbsp;年检日期：</td>
										<td style="border-right: 0;">
											&nbsp;<input type="text" readonly="readonly" class="disabled"
													id="dbrZzjgNjrq1" name="sfrzCjxxb.dbrZzjgNjrq1"
													value="${sfrzCjxxb.dbrZzjgNjrq1}" size="35" />
										</td>
									</tr>
									<tr>
										<td style="text-align: right;">&nbsp;年检有效期：</td>
										<td style="border-right: 0;">
											&nbsp;<input type="text" readonly="readonly" class="disabled"
													id="dbrZzjgNjyxq1" name="sfrzCjxxb.dbrZzjgNjyxq1"
													value="${sfrzCjxxb.dbrZzjgNjyxq1}" size="35" />
										</td>
									</tr>
									<tr>
										<td style="text-align: right;">&nbsp;地址：</td>
										<td style="border-right: 0;">
											&nbsp;<input type="text" readonly="readonly" class="disabled"
												id="dbrZzjgDz1" name="sfrzCjxxb.dbrZzjgDz1"
												value="${sfrzCjxxb.dbrZzjgDz1}" size="40" />
										</td>
									</tr>
								</table>
							</div>
						</td>
					</tr>
				</table>
				<br/>
				<table class="edittable" border="0" cellpadding="0" cellspacing="0" align="center">
				  <tr>
					<td height="33" colspan="4" style="border-left: 0; border-right: 0;"><strong>摄像头及高拍仪(当事人现场照片及身份证明扫描照片)</strong>
						<s:if test="'查看' != #request.editType">
							&nbsp;<input type="button" style="cursor:pointer;" name="zp" id="zp" value="抓拍图片" class="bnt" onclick="vide();">
							 &nbsp;&nbsp;<span id="ReadResult2" style="color: red;">等待抓拍...</span>
							 <input type="hidden" name="flags" id="flags" value="0">
							   <strong>备注：</strong><span>当图像没有加载请点击“切换视频”按钮切换。</span>
						</s:if>
					</td>
				  </tr>
					  <tr>
						<td style="width: 25%" height="36" align="center">当事人现场照片</td>
						<td style="width: 25%" align="center">证件正面图片（身份证、居住证、驾驶证、行驶证）</td>
						<td style="width: 25%" height="36" align="center">面签证据照片</td>
						<td style="width: 25%" height="36" align="center">手写签名照片</td>
						<%--<td style="width: 25%" height="36" align="center">
							身份证件反面照片
						</td >
						<td style="width: 25%" height="36" align="center">
							驾驶证照片
						</td>
					  --%>
					  
					 </tr>
					  <tr>
						<td style="width: 25%" align="center">
							<s:if test="#request.photolist.a2 == null || #request.photolist.a2 == ''">
								<a id="img01a" href="" title="当事人现场照片"><img src="<%=request.getContextPath()%>/images/cp.gif" id="sfzxpimgid1" width="210" height="175" border="0"></a>
							</s:if>
							<s:else>
								<a id="img01a" href="http://100.100.21.61/cmp_new/view_pic.asp?efid=${photolist.a2}" title="当事人现场照片"><img src="http://100.100.21.61/cmp_new/view_pic.asp?efid=${photolist.a2}" id="img01" width="210" height="175" border="0"></a>
							</s:else>
						</td>
						<td style="width: 25%" align="center">
							<s:if test="#request.photolist.a19 == null || #request.photolist.a19 == ''">
								<a id="img02a" href="" title="证件正面图片（身份证、居住证、驾驶证、行驶证）"><img src="<%=request.getContextPath()%>/images/cp.gif" id="sfzxpimgid2" width="210" height="175" border="0"></a>
							</s:if>
							<s:else>
								<a id="img02a" href="http://100.100.21.61/cmp_new/view_pic.asp?efid=${photolist.a19}" title="证件正面图片（身份证、居住证、驾驶证、行驶证）"><img src="http://100.100.21.61/cmp_new/view_pic.asp?efid=${photolist.a19}" id="img02" width="210" height="175" border="0"></a>
							</s:else>
						</td>
						<td style="width: 25%" align="center">
								<s:if test="#request.photolist.a5 == null || #request.photolist.a5 == ''">
									<a id="img08a" href="" title="面签证据照片"><img src="<%=request.getContextPath()%>/images/cp.gif" id="sfzxpimgid8" width="210" height="175" border="0"></a>
								</s:if>
								<s:else>
									<a id="img08a" href="http://100.100.21.61/cmp_new/view_pic.asp?efid=${photolist.a5}" title="面签证据照片"><img src="http://100.100.21.61/cmp_new/view_pic.asp?efid=${photolist.a5}" id="img08" width="210" height="175" border="0"></a>
								</s:else>
						</td>
						<td style="width: 25%" align="center">
								<s:if test="#request.photolist.a15 == null || #request.photolist.a15 == ''">
									<a id="img09a" href="" title="手写签名照片"><img src="<%=request.getContextPath()%>/images/cp.gif" id="sfzxpimgid9" width="210" height="175" border="0"></a>
								</s:if>
								<s:else>
									<a id="img09a" href="http://100.100.21.61/cmp_new/view_pic.asp?efid=${photolist.a15}" title="手写签名照片"><img src="http://100.100.21.61/cmp_new/view_pic.asp?efid=${photolist.a15}" id="img09" width="210" height="175" border="0"></a>
								</s:else>
						</td>
					<tr>
							
							<td style="width: 25%" colspan="4" height="36" align="center">
								采集信息
							</td>
						</tr>
						<tr>
							<td style="center;border:1px solid #D2E9FF;width: 50%;" colspan="4" valign="top">
								<table style="width:100%;">
									<tr>
										<td style="text-align: right;width: 25%;">
											经办人：
										</td>
										<td style="text-align: left;width: 75%;">
											&nbsp;
											${sfrzCjxxb.shrxm}
										</td>
									</tr>
									<tr>
										<td style="text-align: right;width: 25%;">
											经办人部门：
										</td>
										<td style="text-align: left;width: 75%;">
											&nbsp;
											${sfrzCjxxb.shbm}
										</td>
									</tr>
									<tr>
										<td style="text-align: right;width: 25%;">
											经办时间：
										</td>
										<td style="text-align: left;width: 75%;">
											&nbsp;
											<s:date name="#request.sfrzCjxxb.shsj" format="yyyy-MM-dd HH:mm:ss"/>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td colspan="4" height="50" align="center"
								style="text-align: center; border: 0px;">
								<s:if test="#request.editType != '查看'">
				    				<input class="bnt" id="shenhe1" type="button" value="审核通过" onclick="dbyshenhe('${xh }','1');" style="cursor:pointer;" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input class="bnt" id="shenhe2" type="button" value="审核不通过" onclick="dbyshenhe('${xh }','0');" style="cursor:pointer;" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									审核不通过原因：<input type="text" name="tbyy" id="shenhe3" />
				    			</s:if>
				    			<s:else>
				    				<input type="button" onclick="javascript:window.close();" value="关闭" style="margin-left: 200px;cursor:pointer;width: 79px;height: 28px;border: none;background: url(<%=request.getContextPath()%>/images/an2.gif) no-repeat;color: #FFFFFF;font-weight: bold;">
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