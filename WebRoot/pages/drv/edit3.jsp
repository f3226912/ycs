<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<title>${editType }采集信息</title>
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
	border: 1px #4fb3d5 solid;
	border-top: 0;
	display: none;
	padding-top: 10px;
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
</style>

		<script type="text/javascript">
			var edittype = '${editType}';
			var chuli;
			$(document).ready(function() {
				//图片放大
				$(function(){
					$("#img01a").lightBox();
					$("#img02a").lightBox();
				});
			
				if ('查看' == edittype) {
					$("#ReadResult").html("");
					$("#ReadResult1").html("");
					$("#ReadResult2").html("");
					$(".bnt").hide();
					$(".disabled1").toggleClass("disabled");
					
					var xb = '${slgDrvXxcjb.xb}';
					var ywyy = '${slgDrvXxcjb.ywyy}';
					var xbname = document.getElementsByName("slgDrvXxcjb.xb");
					var ywyyname = document.getElementsByName("slgDrvXxcjb.ywyy");
					for (var i = 0; i < xbname.length; i ++){
						if(xb == xbname[i].value){
							xbname[i].checked = "checked";
							break;
						}
					}
					if(ywyy.indexOf(",") > 0){
						var ywyys = ywyy.split(",");
						for(var i = 0; i < ywyys.length; i ++){
							for(var j = 0; j < ywyyname.length; j ++){
								if(ywyys[i] == ywyyname[j].value){
									ywyyname[j].checked = "checked";
								}
							}
						}
					}else{
						for(var j = 0; j < ywyyname.length; j ++){
							if(ywyy == ywyyname[j].value){
								ywyyname[j].checked = "checked";
							}
						}
					}
					
					var lxzsxzqh = '${slgDrvXxcjb.lxzsxzqh}';
					$("#slgDrvXxcjblxzsxzqh").val(lxzsxzqh);
					var djzsxzqh = '${slgDrvXxcjb.djzsxzqh}';
					$("#slgDrvXxcjbdjzsxzqh").val(djzsxzqh);
				}else if('修改' == edittype){
					
				}
				
				//根据身份证明号码查询档案信息
				$("#sfzmhmbid").click(function(){
					var sfzmhmvalue = $("#slgDrvXxcjbsfzmhm").val();
					$.ajax({
						type:'POST',
						url:'<%=request.getContextPath()%>/drv_ajax/getDaxx.action',
						data:{hm:sfzmhmvalue,type:'1'},
						dataType: 'json',
						error:function(){
							alert("读取数据异常!");
						},
						success:function(data){
							if(data == null || data.length == 0){
								//clearfrom();
								//alert("没有查询到档案信息");
							}else{
								$("#chaxunid").val("1");
								var dabh = data[0];
								var sfzmhm = data[1];
								var zjcx = data[2];
								var cclzrq = data[3];
								var ljjf = data[4];
								var zt = data[5];
								var xm = data[6];
								var gj = data[7];
								var syrq = data[8];
								var yxqz = data[9];
								var lxzsxzqh = data[10];
								var lxzsxxdz = data[12];
								var djzsxxdz = data[13];
								var lxdh = data[14];
								var csrq = data[15];
								var xb = data[16];
								var sfzmmc = data[17];
								var ztr = data[18];
								
								if(xb == '1'){
									$("#slgDrvXxcjbxb0").attr("checked","checked");
									$("#slgDrvXxcjbxb1").attr("checked","");
								}else if(xb == '2'){
									$("#slgDrvXxcjbxb1").attr("checked","checked");
									$("#slgDrvXxcjbxb0").attr("checked","");
								}
								$("#slgDrvXxcjbsfzmmc").val(sfzmmc);
								
								//$("#slgDrvXxcjbxm").val(xm);
								$("#slgDrvXxcjbdabh").val(dabh);
								//$("#slgDrvXxcjbsfzmhm").val(sfzmhm);
								//$("#slgDrvXxcjblxdh").val(lxfs);
								$("#slgDrvXxcjbjszhm").val(sfzmhm);
								$("#slgDrvXxcjbzt").val(ztr);
								$("#slgDrvXxcjbzjcx").val(zjcx);
								$("#slgDrvXxcjbljjf").val(ljjf);
								$("#slgDrvXxcjbgj").val(gj);
								$("#slgDrvXxcjbsyrq").val(syrq);
								$("#slgDrvXxcjbcclzrq").val(cclzrq);
								$("#slgDrvXxcjbyxqz").val(yxqz);
								$("#slgDrvXxcjblxdh2").val(lxdh);
								$("#slgDrvXxcjblxzsxzqh").val(lxzsxzqh);
								$("#slgDrvXxcjblxzsxxdz").val(lxzsxxdz);
								$("#slgDrvXxcjbdjzsxxdz").val(djzsxxdz);
								$("#slgDrvXxcjbztr").val(zt);
								$("#slgDrvXxcjblxdh").val(lxdh);
								$("#slgDrvXxcjbsjhm").val(lxdh);
								$("#slgDrvXxcjbcsrq").val(csrq);
								
								showBirthday(sfzmhm);
							}
						}
					});
				});
				//根据档案编号查询档案信息
				$("#dzbhbid").click(function(){
					var dabhvalue = $("#slgDrvXxcjbdabh").val();
					$.ajax({
						type:'POST',
						url:'<%=request.getContextPath()%>/drv_ajax/getDaxx.action',
						data:{hm:dabhvalue,type:'2'},
						dataType: 'json',
						error:function(){
							alert("读取数据异常!");
						},
						success:function(data){
							if(data == null || data.length == 0){
								//clearfrom();
								//alert("没有查询到档案信息");
							}else{
								$("#chaxunid").val("1");
								var dabh = data[0];
								var sfzmhm = data[1];
								var zjcx = data[2];
								var cclzrq = data[3];
								var ljjf = data[4];
								var zt = data[5];
								var xm = data[6];
								var gj = data[7];
								var syrq = data[8];
								var yxqz = data[9];
								var lxzsxzqh = data[10];
								var lxzsxxdz = data[12];
								var djzsxxdz = data[13];
								var lxdh = data[14];
								var csrq = data[15];
								var xb = data[16];
								var sfzmmc = data[17];
								var ztr = data[18];
								
								if(xb == '1'){
									$("#slgDrvXxcjbxb0").attr("checked","checked");
									$("#slgDrvXxcjbxb1").attr("checked","");
								}else if(xb == '2'){
									$("#slgDrvXxcjbxb1").attr("checked","checked");
									$("#slgDrvXxcjbxb0").attr("checked","");
								}
								$("#slgDrvXxcjbsfzmmc").val(sfzmmc);
								
								//$("#slgDrvXxcjbxm").val(xm);
								//$("#slgDrvXxcjbdabh").val(dabh);
								//$("#slgDrvXxcjbsfzmhm").val(sfzmhm);
								//$("#slgDrvXxcjblxdh").val(lxfs);
								$("#slgDrvXxcjbjszhm").val(sfzmhm);
								$("#slgDrvXxcjbzt").val(ztr);
								$("#slgDrvXxcjbzjcx").val(zjcx);
								$("#slgDrvXxcjbljjf").val(ljjf);
								$("#slgDrvXxcjbgj").val(gj);
								$("#slgDrvXxcjbsyrq").val(syrq);
								$("#slgDrvXxcjbcclzrq").val(cclzrq);
								$("#slgDrvXxcjbyxqz").val(yxqz);
								$("#slgDrvXxcjblxdh2").val(lxdh);
								$("#slgDrvXxcjblxzsxzqh").val(lxzsxzqh);
								$("#slgDrvXxcjblxzsxxdz").val(lxzsxxdz);
								$("#slgDrvXxcjbdjzsxxdz").val(djzsxxdz);
								$("#slgDrvXxcjbztr").val(zt);
								$("#slgDrvXxcjblxdh").val(lxdh);
								$("#slgDrvXxcjbsjhm").val(lxdh);
								$("#slgDrvXxcjbcsrq").val(csrq);
								
								showBirthday(sfzmhm);
							}
						}
					});
				});
				
				
				$("#slgDrvXxcjbsfzmhm").keyup(function(){
					$("#chaxunid").val("0");
				});
				
				$("#slgDrvXxcjbdabh").keyup(function(){
					$("#chaxunid").val("0");
				});
				
				
				$("#slgDrvXxcjbsfzmmc").change(function(){
					var ismyself = $("input:radio[name='slgDrvXxcjb.isjsr'][checked]").val();
					cleanmyform(0);
					$("input:radio[name='slgDrvXxcjb.isjsr'][value="+ismyself+"]").attr("checked", true);
					var mcvalue = $(this).val();
					if(mcvalue != "A"){
						document.getElementById("file1").outerHTML = document.getElementById("file1").outerHTML;
						document.getElementById("file2").outerHTML = document.getElementById("file2").outerHTML;
					}
				});
				
				$("input[name='slgDrvXxcjb.isjsr']").change(function(){
					if($(this).val() == 0){
						$("#sfzmhmdbrtd").show();
					}else{
						$("#sfzmhmdbrtd").hide();
					}
				});
			});
			
			//验证非空
			function checknotnull2(obj, alerttype,type) {
				if (obj.value != "" && obj.value != " " && obj.value != null
						&& obj.value != undefined) {
					obj.style.borderColor = '';
					return "true";
				} else {
					alert(alerttype);
					obj.style.borderColor = '#FF0000';
					settab(this,type);
					obj.focus();
					return "false";
				}
			}
			
			function clearfrom(){
				$("#slgDrvXxcjbxm").val("");
				$("#slgDrvXxcjbdabh").val("");
				$("#slgDrvXxcjbsfzmhm").val("");
				$("#slgDrvXxcjbjszhm").val("");
				$("#slgDrvXxcjbzt").val("");
				$("#slgDrvXxcjbzjcx").val("");
				$("#slgDrvXxcjbljjf").val("");
				$("#slgDrvXxcjbgj").val("");
				$("#slgDrvXxcjbsyrq").val("");
				$("#slgDrvXxcjbcclzrq").val("");
				$("#slgDrvXxcjbyxqz").val("");
				$("#slgDrvXxcjblxdh2").val("");
				$("#slgDrvXxcjblxzsxzqh").val("");
				$("#slgDrvXxcjblxzsxxdz").val("");
				$("#slgDrvXxcjbdjzsxxdz").val("");
				$("#slgDrvXxcjbztr").val("");
				$("#slgDrvXxcjblxdh").val("");
				$("#slgDrvXxcjbsjhm").val("");
				$("#slgDrvXxcjbcsrq").val("");
				$("#slgDrvXxcjbsfzmmc").val("");
				$("#slgDrvXxcjbxb0").attr("checked","");
				$("#slgDrvXxcjbxb1").attr("checked","");
			}
			
			//清除空格和'$'
			function clearspace(obj){
				obj.value = obj.value.replace(" ","");
				obj.value = obj.value.replace("　","");
				obj.value = obj.value.replace("（","(");
				obj.value = obj.value.replace("）",")");
			}
			
			//清除所有的空格
			function clearallspace(obj){
				var val = obj.value;
				while(val.indexOf(" ") != -1){
					val = val.replace(" ", "");
				}
				obj.value = val;
				return obj.value;
			}
			
			var chuli;
			function alertdiv(divhtml){
				var reason = art.dialog({
					width:'50%',
					title: '未读卡原因',
					content: divhtml,
					okVal: '保存',
					ok: function(){
						//验证未填驾驶人不是二代身份证的原因
						var jsrbsedsfzmyyval = '';
						var jsrobj = document.getElementsByName('jsryycheckbox');
						var jsrbsedsfzmyy = document.getElementById("jsrbsedsfzmyyid");
						if(jsrobj != null){
							for(var i = 0; i < jsrobj.length; i++){
								if(jsrobj[i].checked){
									jsrbsedsfzmyyval = jsrbsedsfzmyyval + jsrobj[i].value + ",";
								}
							}
						}
						if(jsrbsedsfzmyyval != null && jsrbsedsfzmyyval != '' && jsrbsedsfzmyyval != ' '){
							if (jsrbsedsfzmyy != null) {
								if(jsrbsedsfzmyy.value != null && jsrbsedsfzmyy.value != "" && jsrbsedsfzmyy.value != " "){
									$("#jsrbsedsfzmyy").val(jsrbsedsfzmyyval+"卍"+$("#jsrbsedsfzmyyid").val());
								}else{
									$("#jsrbsedsfzmyy").val(jsrbsedsfzmyyval+"卍,");							
								}
							}else{
								$("#jsrbsedsfzmyy").val(jsrbsedsfzmyyval+"卍,");
							}
						}else{
							if (jsrbsedsfzmyy != null) {
								var jsrval = clearallspace(jsrbsedsfzmyy);
								if(jsrval == "" || jsrval == " " || jsrval == null){
									alert("请填写驾驶人二代身份证未填卡原因");
									return false;
								}else{
									$("#jsrbsedsfzmyy").val(",卍"+$("#jsrbsedsfzmyyid").val());
								}
							}
						}
						
						//验证未填代办人不是二代身份证的原因
						var dbrbsedsfzmyyval = '';
						var dbrobj = document.getElementsByName('dbryycheckbox');
						var dbrbsedsfzmyy = document.getElementById("dbrbsedsfzmyystrid");
						if(dbrobj != null){
							for(var i = 0; i < dbrobj.length; i++){
								if(dbrobj[i].checked){
									dbrbsedsfzmyyval = dbrbsedsfzmyyval + dbrobj[i].value + ",";
								}
							}
						}
						if(dbrbsedsfzmyyval != null && dbrbsedsfzmyyval != '' && dbrbsedsfzmyyval != ' '){
							if (dbrbsedsfzmyy != null) {
								if(dbrbsedsfzmyy.value != null && dbrbsedsfzmyy.value != "" && dbrbsedsfzmyy.value != " "){
									$("#dbrbsedsfzmyy").val(dbrbsedsfzmyyval + "卍" + $("#dbrbsedsfzmyystrid").val());
								}else{
									$("#dbrbsedsfzmyy").val(dbrbsedsfzmyyval+"卍,");							
								}
							}else{
								$("#dbrbsedsfzmyy").val(dbrbsedsfzmyyval+"卍,");
							}
						}else{
							if (dbrbsedsfzmyy != null ) {
								var dbrval = clearallspace(dbrbsedsfzmyy);
								if(dbrval == "" || dbrval == " " || dbrval == null){
									alert("请填写代办人二代身份证未填卡原因");
									return false;
								}else{
									$("#dbrbsedsfzmyy").val(",卍"+$("#dbrbsedsfzmyystrid").val());
								}
							}
						}
						var imgurl = '<%=request.getContextPath()%>/artDialog4.1.6/skins/icons/loading2.gif';
						chuli = art.dialog({
						    content: '<div style=\"text-align:center; text-indent:-999em; overflow:hidden; background:url('+imgurl+') no-repeat center center;\"><span>loading..</span></div><br/>数据处理中,请稍候.....',
						    title: '数据处理中',
			    			lock: true,
						    opacity: 0.87
						});
						
						drv_form.submit();
					},
					cancelVal: '关闭',
	    			cancel: true,
	    			lock: true,
				    opacity: 0.87
				});
			}
			
			function pjwdkyy(type){
				var checkboxhtml = '';
				$.ajax({
					cache:false,
    				async:false,
					type:'POST',
					url:'<%=request.getContextPath()%>/drv_ajax/pjwdkyy.action',
					data:'',
					dataType: 'json',
					error:function(){
						alert("读取数据异常!");
					},
					success:function(data){
						if(data != null && data.length > 0){
							for(var i = 0; i < data.length; i++){
								var slgSjzd = data[i];
								checkboxhtml = checkboxhtml + '&nbsp;&nbsp;<input type=\"checkbox\" name=\"'+type+'yycheckbox\" value=\"'+slgSjzd.dmz+'\"/>&nbsp;' + slgSjzd.dmms1;
								if((i+1)%3 == 0){
									checkboxhtml = checkboxhtml + "<br/>&nbsp;&nbsp;";
								}
							}
						}
					}
				});
				return checkboxhtml;
			}
			
			function submitfrom() {
				if('新增' == edittype){
					//验证驾驶人身份证读取
					var sfzmmc = $("#slgDrvXxcjbsfzmmc").val();
					var idno = $("#slgDrvXxcjbidNo").val();
					var chfile1 = $("#chfile1").val();
					
					if(sfzmmc == 'A' && idno != null && idno != ''){
						var file1 = $("#file1").val();
						if(file1 != "C:\\ycszh_sfz1\\zp.bmp"){
							alert("驾驶人身份证图片路径读取失败!请读取后点击浏览选择C:\\ycszh_sfz1\\zp.bmp");
							settab(this,0);
							return false;
						}
					}
					var ismyself = $("input:radio[name='slgDrvXxcjb.isjsr'][checked]").val();
					var isedsfzm = $("#dbrslgDrvXxcjbsfzmmc").val();
					var idno2 = '';
					if(ismyself == '0'){
						//验证代办人身份证读取
						idno2 = $("#slgDrvXxcjbidNo2").val();
						var chfile2 = $("#chfile2").val();
						if(isedsfzm == 'A' && idno2 != null && idno2 != ''){
							var file2 = $("#file2").val();
							if(file2 != "C:\\ycszh_sfz2\\zp.bmp"){
								alert("代办人身份证图片路径读取失败!请读取后点击浏览选择C:\\ycszh_sfz2\\zp.bmp");
								settab(this,0);
								return false;
							}
						}
					}
					
					//验证身份证明号码
					var slgDrvXxcjbsfzmhm = checknotnull2(document.getElementById("slgDrvXxcjbsfzmhm"),'请输入驾驶员驾驶证号码',0);
					if (slgDrvXxcjbsfzmhm != "true") {
						return false;
					}
					//验证姓名
					var slgDrvXxcjbxm = checknotnull2(document.getElementById("slgDrvXxcjbxm"),'请输入驾驶人姓名',0);
					if (slgDrvXxcjbxm != "true") {
						return false;
					}
					
					if(ismyself == '0'){
						//验证身份证明号码
						var slgDrvXxcjbsfzmhmbr = checknotnull2(document.getElementById("slgDrvXxcjbsfzmhmdbr"),'请输入代办人驾驶证号码',0);
						if (slgDrvXxcjbsfzmhmbr != "true") {
							return false;
						}
						//验证姓名
						var slgDrvXxcjbxmdbr = checknotnull2(document.getElementById("slgDrvXxcjbxmdbr"),'请输入代办人姓名',0);
						if (slgDrvXxcjbxmdbr != "true") {
							return false;
						}
					}
					
					//验证高拍仪和摄像头拍摄
					var file01 = $("#file01").val();
					var file02 = $("#file02").val();
					var chfile01 = $("#chfile01").val();
					var chfile02 = $("#chfile02").val();
					if(chfile01 == "1" && chfile01 == "1"){
						if(file01 != "C:\\printtx.jpg" || file02 != "C:\\printzj.jpg"){
							alert("高拍仪和摄像头拍摄图片路径读取失败!请抓拍图片后点击浏览摄像头图片选择C:\\printtx.jpg文件,高拍仪图片选择C:\\printzj.jpg文件!");
							settab(this,0);
							return false;
						}
					}else{
						alert("请点击'抓拍图片'按钮拍摄当事人照片和证件照片!");
						settab(this,0);
						return false;
					}
					
					//验证出生日期
					var datestr = $("#slgDrvXxcjbcsrq").val();
					if(null != datestr && "" != datestr){
						var returndatestr = checkdate(datestr);
						if(returndatestr != "true"){
							return false;
						}
					}
					
					//验证业务原因是否选择
					/*var ywyyi,ywyy,jsrxbi,jsrxb;
					ywyy = document.getElementsByName("slgDrvXxcjb.ywyy");
					jsrxb = document.getElementsByName("slgDrvXxcjb.xb");
					for (ywyyi = 0; ywyyi < ywyy.length; ywyyi ++){
						if(ywyy[ywyyi].checked)break;
					}
					if(ywyyi >= ywyy.length){
						alert("请选择业务原因!");
						settab(this,1);
						return false;
					}
					
					//验证身份证明名称
					var slgDrvXxcjbsfzmmc = checknotnull2(document.getElementById("slgDrvXxcjbsfzmmc"),'请选择身份证明名称',1);
					if (slgDrvXxcjbsfzmmc != "true") {
						return false;
					}
					
					//验证驾驶人性别
					for (jsrxbi = 0; jsrxbi < jsrxb.length; jsrxbi ++){
						if(jsrxb[jsrxbi].checked)break;
					}
					if(jsrxbi >= jsrxb.length){
						alert("请选择驾驶人性别!");
						settab(this,1);
						return false;
					}
					//验证出生日期
					var slgDrvXxcjbcsrq = checknotnull2(document.getElementById("slgDrvXxcjbcsrq"),'请输入出生日期',1);
					if (slgDrvXxcjbcsrq != "true") {
						return false;
					}else{
						var datestr = $("#slgDrvXxcjbcsrq").val();
						var returndatestr = checkdate(datestr);
						if(returndatestr != "true"){
							return false;
						}
					}
					//验证手机号码
					var slgDrvXxcjbsjhm = checknotnull2(document.getElementById("slgDrvXxcjbsjhm"),'请输入手机号码',1);
					if (slgDrvXxcjbsjhm != "true") {
						return false;
					}else{
						var sjhm = $("#slgDrvXxcjbsjhm").val();
						var resjhm = checkmobile(sjhm);
						if (resjhm != "true") {
							return false;
						}
					}
					
					var ywlx = '${ywlx}';
					if(ywlx != 'CCSL' && ywlx != 'JJSL' && ywlx != 'JWSL' && ywlx != 'XXMSHZ'){
						var chaxun = $("#chaxunid").val();
						if("0" == chaxun){
							if(ywlx == 'BZHZ'){
								var szywyy = '';
								for (var ywyyi2 = 0; ywyyi2 < ywyy.length; ywyyi2 ++){
									if(ywyy[ywyyi2].checked){
										szywyy += ywyy[ywyyi2].value;
									}
								}
								if('B' != szywyy){
									alert("请点击查询按钮查询档案信息!");
									return false;
								}
							}else{
								alert("请点击查询按钮查询档案信息!");
								return false;
							}
						}
					}
					
					//验证统一版流水号
					var slgDrvXxcjblsh = checknotnull2(document.getElementById("slgDrvXxcjblsh"),'请输入统一版流水号',1);
					if (slgDrvXxcjblsh != "true") {
						return false;
					}*/
					
					//验证联系电话
					/*var lxdh = $("#slgDrvXxcjblxdh").val();
					if(null != lxdh && "" != lxdh){
						var relxdh = checkphone(lxdh);
						if (relxdh != "true") {
							settab(this,1);
							return false;
						}
					}
					//验证电子邮箱
					var dzyx = $("#slgDrvXxcjbdzyx").val();
					if(null != dzyx && "" != dzyx){
						var redzyx = checkemail(dzyx);
						if (redzyx != "true") {
							settab(this,1);
							return false;
						}
					}*/
					
					//弹出填写未读卡的原因层
					var divhtml = "";
					var checkboxhtml = '';
					var dbrcheckboxhtml = '';
					var sfzmhm = $("#slgDrvXxcjbsfzmhm").val();
					var xm = $("#slgDrvXxcjbxm").val();
					if(ismyself == "0"){
						var sfzmhmdbr = $("#slgDrvXxcjbsfzmhmdbr").val();
						var xmdbr = $("#slgDrvXxcjbxmdbr").val();
						if(sfzmmc == "A" && (idno == null ||idno == '') ){
							var istrue = spyanzheng(sfzmhm, xm, '', 0);
							if(istrue == 0){
								alert("驾驶人请先进行驾驶证审批");
								return false;
							}
							/*if(checkboxhtml == null || checkboxhtml == ''){
								checkboxhtml = pjwdkyy('jsr');
							}
							divhtml = divhtml + '<div><table class=\"edittable\" style=\"width: 400px;\"><tr><td style=\"text-align: center;\" colspan=\"2\"><strong>驾驶人信息未读卡原因\：</strong></td></tr>'
							if(checkboxhtml != null && checkboxhtml != ''){
								divhtml = divhtml + '<tr><td style=\"text-align: right;\">&nbsp;&nbsp;请选择:&nbsp;</td><td>&nbsp;&nbsp;'+checkboxhtml+ '</td></tr>';
							}
							divhtml = divhtml + '<tr><td style=\"text-align: right;\">&nbsp;&nbsp;备注:&nbsp;</td><td>&nbsp;&nbsp;&nbsp;<input type=\"text\" name=\"jsrbsedsfzmyystr\" id=\"jsrbsedsfzmyyid\" size=\"40\"/></td></tr>';
							*/
						}
						if(isedsfzm == "A" && (idno2 == null ||idno2 == '')){
							var istrue = spyanzheng(sfzmhmdbr, xmdbr, '', 1);
							if(istrue == 0){
								alert("代办人请先进行驾驶证审批");
								return false;
							}
							/*if(divhtml == null || divhtml == ''){
								divhtml = divhtml + '<div><table class=\"edittable\" style=\"width: 400px;\">';
							}
							if(dbrcheckboxhtml == null || dbrcheckboxhtml == ''){
								dbrcheckboxhtml = pjwdkyy('dbr');
							}
							divhtml = divhtml + '<tr><td style=\"text-align: center;\" colspan=\"2\"><strong>代办人信息未读卡原因\:</strong></td></tr>';
							if(dbrcheckboxhtml != null && dbrcheckboxhtml != ''){
								divhtml = divhtml + '<tr><td style=\"text-align: right;\">&nbsp;&nbsp;请选择:&nbsp;</td><td>&nbsp;&nbsp;'+dbrcheckboxhtml+ '</td></tr>';
							}
							divhtml = divhtml + '<tr><td style=\"text-align: right;\">&nbsp;&nbsp;备注:&nbsp;</td><td>&nbsp;&nbsp;&nbsp;<input type=\"text\" name=\"dbrbsedsfzmyystr\" id=\"dbrbsedsfzmyystrid\" size=\"40\"/></td></tr>';
							divhtml = divhtml + '</table></div>';*/
						}
						/*else{
							if(divhtml != null && divhtml != ''){
								divhtml = divhtml + '</table></div>';
							}
						}*/
					}else{
						if(sfzmmc == "A" && (idno == null ||idno == '')){
							var istrue = spyanzheng(sfzmhm, xm, '', 0);
							if(istrue == 0){
								alert("驾驶人请先进行驾驶证审批");
								return false;
							}
							/*if(checkboxhtml == null || checkboxhtml == ''){
								checkboxhtml = pjwdkyy('jsr');
							}
							divhtml = divhtml + '<div><table class=\"edittable\" style=\"width: 400px;\"><tr><td style=\"text-align: center; \" colspan=\"2\"><strong>驾驶人信息未读卡原因\：</strong></td></tr>'
							if(checkboxhtml != null && checkboxhtml != ''){
								divhtml = divhtml + '<tr><td style=\"text-align: right;\">&nbsp;&nbsp;请选择:&nbsp;</td><td>&nbsp;&nbsp;'+checkboxhtml+ '</td></tr>';
							}
							divhtml = divhtml + '<tr><td style=\"text-align: right;\">&nbsp;&nbsp;备注:&nbsp;</td><td>&nbsp;&nbsp;&nbsp;<input type=\"text\" name=\"jsrbsedsfzmyystr\" id=\"jsrbsedsfzmyyid\" size=\"40\"/></td></tr>';
							divhtml = divhtml + '</table></div>';*/
						}
						
					}
					/*if(divhtml != null && divhtml != ""){
						alertdiv(divhtml);
						return false;
					}*/
					return true;
				}
				return false;
			}
			
			function spyanzheng(sfzmhm, xm, splx, splx2){
				var istrue;
				$.ajax({
					cache:false,
	    			async:false,
	    			url: '<%=request.getContextPath()%>/slgSpxx/slgSpxx_selSlgSpxxByCondition.action',
	    			type: 'post',
	    			data: {"sfzmhm": sfzmhm, "xm":xm, "splx": splx, "splx2":splx2},
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
			
			function realsubmit(){
				if(submitfrom()){
					var fso = new ActiveXObject("Scripting.FileSystemObject");
					var f1 = fso.GetFile("C:\\printtx.jpg");
					var f2 = fso.GetFile("C:\\printzj.jpg");
					var f1d, f2d;
					f1d = f1.DateLastModified;
					f2d = f2.DateLastModified;
					var d1 = new Date(f1d);
					var d2 = new Date(f2d);
					var d01 = $("#xczpdate").val();
					var d02 = $("#zjzpdate").val();
					if(d1.getTime() != d01){
						alert('现场照片存在异常!');
					}else if(d2.getTime() != d02){
						alert('高拍仪照片存在异常!');
					}else{
						var imgurl = '<%=request.getContextPath()%>/artDialog4.1.6/skins/icons/loading2.gif';
						chuli = art.dialog({
						    content: '<div style=\"text-align:center; text-indent:-999em; overflow:hidden; background:url('+imgurl+') no-repeat center center;\"><span>loading..</span></div><br/>数据处理中,请稍候.....',
						    title: '数据处理中',
			    			lock: true,
						    opacity: 0.87
						});
						drv_form.submit();
					}
					
				}
			}
			
			//小写字母变大写字母,适用于号牌号码.
			function xiaobianda(obj){
				obj.value = obj.value.replace(" ","");
				obj.value = obj.value.replace("　","");
				obj.value = obj.value.toUpperCase();
			}
			
			function cleanmyform(val){
				if(val == "1"){
					$("#slgDrvXxcjbsfzmmc").val('A');
					$("#dbrslgDrvXxcjbsfzmmc").val('A');
					$("input[type=radio][name='slgDrvXxcjb.isjsr'][value='1']").attr("checked",true);
					$("#slgDrvXxcjbsfzmhmdbr").val("");
					$("#slgDrvXxcjbxmdbr").val("");
					$("#sfzmhmdbrtd").hide();
				}
				document.getElementById("sfzxpimgid").src='<%=request.getContextPath()%>/images/cp.gif';
				document.getElementById("sfzxpimgid2").src='<%=request.getContextPath()%>/images/cp.gif';
				//document.getElementById("file1").outerHTML = document.getElementById("file1").outerHTML;
				//document.getElementById("file2").outerHTML = document.getElementById("file2").outerHTML;
				$("#slgDrvXxcjbidName").val("");
				$("#slgDrvXxcjbidSex").val("");
				$("#slgDrvXxcjbidNo").val("");
				$("#slgDrvXxcjbidAddress").val("");
				$("#slgDrvXxcjbidName2").val("");
				$("#slgDrvXxcjbidSex2").val("");
				$("#slgDrvXxcjbidNo2").val("");
				$("#slgDrvXxcjbidAddress2").val("");
				//$(":radio").removeAttr("checked");
				$(":checkbox").removeAttr("checked");
				
				$("#slgDrvXxcjbxm").val("");
				$("#slgDrvXxcjbdabh").val("");
				$("#slgDrvXxcjbsfzmhm").val("");
				$("#slgDrvXxcjbjszhm").val("");
				$("#slgDrvXxcjbzt").val("");
				$("#slgDrvXxcjbzjcx").val("");
				$("#slgDrvXxcjbljjf").val("");
				$("#slgDrvXxcjbgj").val("");
				$("#slgDrvXxcjbsyrq").val("");
				$("#slgDrvXxcjbcclzrq").val("");
				$("#slgDrvXxcjbyxqz").val("");
				$("#slgDrvXxcjblxdh2").val("");
				$("#slgDrvXxcjblxzsxzqh").val("");
				$("#slgDrvXxcjblxzsxxdz").val("");
				$("#slgDrvXxcjbdjzsxxdz").val("");
				$("#slgDrvXxcjbztr").val("");
				$("#slgDrvXxcjblxdh").val("");
				$("#slgDrvXxcjbsjhm").val("");
				$("#slgDrvXxcjbdzyx").val("");
				$("#slgDrvXxcjblsh").val("");
				$("#slgDrvXxcjbcsrq").val("");
				
				$("#chfile1").val("0");
				$("#chfile2").val("0");
				$("#chfile01").val("0");
				$("#chfile02").val("0");
				
				document.getElementById("img01").src='<%=request.getContextPath()%>/images/cp.gif';
				document.getElementById("img02").src='<%=request.getContextPath()%>/images/cp.gif';
				//document.getElementById("file01").outerHTML = document.getElementById("file01").outerHTML;
				//document.getElementById("file02").outerHTML = document.getElementById("file02").outerHTML;
				document.getElementById("ReadResult").innerHTML="等待读取...";
				document.getElementById("ReadResult1").innerHTML="等待读取...";
				document.getElementById("ReadResult2").innerHTML="等待抓拍...";
				
				settab(this,0);
			}
			
			function getdaxx(){
				var sfzmhmvalue = $("#slgDrvXxcjbsfzmhm").val();
				$.ajax({
					type:'POST',
					url:'<%=request.getContextPath()%>/drv_ajax/getDaxx.action',
					data:{hm:sfzmhmvalue,type:'1'},
					dataType: 'json',
					error:function(){
						alert("读取数据异常!");
					},
					success:function(data){
						if(data == null || data.length == 0){
							//clearfrom();
							//alert("没有查询到档案信息");
						}else{
							$("#chaxunid").val("1");
							var dabh = data[0];
							var sfzmhm = data[1];
							var zjcx = data[2];
							var cclzrq = data[3];
							var ljjf = data[4];
							var zt = data[5];
							var xm = data[6];
							var gj = data[7];
							var syrq = data[8];
							var yxqz = data[9];
							var lxzsxzqh = data[10];
							var lxzsxxdz = data[12];
							var djzsxxdz = data[13];
							var lxdh = data[14];
							var csrq = data[15];
							var xb = data[16];
							var sfzmmc = data[17];
							var ztr = data[18];
							
							if(xb == '1'){
								$("#slgDrvXxcjbxb0").attr("checked","checked");
								$("#slgDrvXxcjbxb1").attr("checked","");
							}else if(xb == '2'){
								$("#slgDrvXxcjbxb1").attr("checked","checked");
								$("#slgDrvXxcjbxb0").attr("checked","");
							}
							$("#slgDrvXxcjbsfzmmc").val(sfzmmc);
							
							//$("#slgDrvXxcjbxm").val(xm);
							$("#slgDrvXxcjbdabh").val(dabh);
							//$("#slgDrvXxcjbsfzmhm").val(sfzmhm);
							//$("#slgDrvXxcjblxdh").val(lxfs);
							$("#slgDrvXxcjbjszhm").val(sfzmhm);
							$("#slgDrvXxcjbzt").val(ztr);
							$("#slgDrvXxcjbzjcx").val(zjcx);
							$("#slgDrvXxcjbljjf").val(ljjf);
							$("#slgDrvXxcjbgj").val(gj);
							$("#slgDrvXxcjbsyrq").val(syrq);
							$("#slgDrvXxcjbcclzrq").val(cclzrq);
							$("#slgDrvXxcjbyxqz").val(yxqz);
							$("#slgDrvXxcjblxdh2").val(lxdh);
							$("#slgDrvXxcjblxzsxzqh").val(lxzsxzqh);
							$("#slgDrvXxcjblxzsxxdz").val(lxzsxxdz);
							$("#slgDrvXxcjbdjzsxxdz").val(djzsxxdz);
							$("#slgDrvXxcjbztr").val(zt);
							$("#slgDrvXxcjblxdh").val(lxdh);
							$("#slgDrvXxcjbsjhm").val(lxdh);
							$("#slgDrvXxcjbcsrq").val(csrq);
							
							showBirthday(sfzmhm);
						}
					}
				});
			}
			
			//切换表格
			function settab(obj,n){
				var tds = $("#test2_li_now_ li");
				for(var i=0;i < tds.length;i++){ 
					tds[i].className=i==n?"now":"";
					$("#tableid" + i).hide();
				}
				$("#tableid" + n).show();
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
			
			function windowopenprint(id){
				var print = window.open('<%=request.getContextPath()%>/drv/print.action?slgDrvXxcjb.cjid=' + id);
			}
			
			
			function showBirthday(val) {
			    var birthdayValue,sex;
			    if (15 == val.length) { //15位身份证号码
			        birthdayValue = val.charAt(6) + val.charAt(7);
			        if (parseInt(birthdayValue) < 10) {
			            birthdayValue = '20' + birthdayValue;
			        }
			        else {
			            birthdayValue = '19' + birthdayValue;
			        }
			        birthdayValue = birthdayValue + '-' + val.charAt(8) + val.charAt(9) + '-' + val.charAt(10) + val.charAt(11);
			        if (parseInt(val.charAt(14) / 2) * 2 != val.charAt(14))
			            sex = '男';
			        else
			            sex = '女';
			        //$("#slgDrvXxcjbcsrq").val(birthdayValue);
					var xb = document.getElementsByName("slgDrvXxcjb.xb");
					for (var i = 0; i < xb.length; i ++){
						if(sex == xb[i].value){
							xb[i].checked = "checked";
							break;
						}
					}
			    }
			    if (18 == val.length) { //18位身份证号码
			        birthdayValue = val.charAt(6) + val.charAt(7) + val.charAt(8) + val.charAt(9) + '-' + val.charAt(10) + val.charAt(11)
			   + '-' + val.charAt(12) + val.charAt(13);
			        if (parseInt(val.charAt(16) / 2) * 2 != val.charAt(16))
			            sex = '男';
			        else
			            sex = '女';
			        //$("#slgDrvXxcjbcsrq").val(birthdayValue);
					var xb = document.getElementsByName("slgDrvXxcjb.xb");
					for (var i = 0; i < xb.length; i ++){
						if(sex == xb[i].value){
							xb[i].checked = "checked";
							break;
						}
					}
			    }
			}
		</script>

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
				$("#slgDrvXxcjbidName").val("");
				$("#slgDrvXxcjbidSex").val("");
				$("#slgDrvXxcjbidNo").val("");
				$("#slgDrvXxcjbidAddress").val("");
				document.getElementById("sfzxpimgid").src='<%=request.getContextPath()%>/images/cp.gif';
				//document.getElementById("file1").outerHTML = document.getElementById("file1").outerHTML;
				return true;
			}
			
			function ClearForm2() {
				$("#slgDrvXxcjbidName2").val("");
				$("#slgDrvXxcjbidSex2").val("");
				$("#slgDrvXxcjbidNo2").val("");
				$("#slgDrvXxcjbidAddress2").val("");
				document.getElementById("sfzxpimgid2").src='<%=request.getContextPath()%>/images/cp.gif';
				//document.getElementById("file2").outerHTML = document.getElementById("file2").outerHTML;
				return true;
			}
			
			//禁止鼠标右键
			//document.oncontextmenu = nocontent;
			
			//驾驶人证件采集
			function ReadIDCard(flag) {
				CVR_IDCard = document.getElementById("CVR_IDCard");
				//图片路径
				CreateFolder("C:\\ycszh_sfz1");
				CVR_IDCard.PhotoPath = 'C:\\ycszh_sfz1';
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
				
				$("#slgDrvXxcjbidName").val(pName);
				$("#slgDrvXxcjbidSex").val(pSex);
				$("#slgDrvXxcjbidNo").val(pCardNo);
				$("#slgDrvXxcjbidAddress").val(pAddress);
				
				//$("#slgDrvXxcjbxm").val(pName);
				//$("#slgDrvXxcjbsfzmhm").val(pCardNo);
				$("#slgDrvXxcjbdjzsxxdz").val(pAddress);
				$("#slgDrvXxcjbcsrq").val(pBorn);
				$("#slgDrvXxcjbsfzmmc").val("A");
				
				var xb = document.getElementsByName("slgDrvXxcjb.xb");
				for (var i = 0; i < xb.length; i ++){
					if(pSex == xb[i].value){
						xb[i].checked = "checked";
						break;
					}
				}
				
				var sfz_name = pName.replace(/(^s*)|(\s*$)/g, "");
				var chuli_type = "1"
				if (chuli_type == "1") {
					ClearIDCard();
				} else {
			
				}
				//判断id长度是否大于4,如果大于则不调用
				if (pCardNo.length > 4) {
					var file1val = $("#file1").val();
					if(file1val != "C:\\ycszh_sfz1\\zp.bmp"){
						var ssd=inputtext("C:\\ycszh_sfz1\\zp.bmp",drv_form.file1);
					}
					document.getElementById("sfzxpimgid").src="C:\\ycszh_sfz1\\zp.bmp";
					document.getElementById("ReadResult").innerHTML = "读取成功...";
					$("#chfile1").val("1");
					getdaxx();
				} else {
					if (flag == "0") {
						alert("请拿起身份证再试！");
						//ReadIDCard("0");
					}
				}
			}
			
			//代办人证件采集
			function ReadIDCard1(flag) {
				CVR_IDCard = document.getElementById("CVR_IDCard");
				//图片路径
				CreateFolder("C:\\ycszh_sfz2");
				CVR_IDCard.PhotoPath = 'C:\\ycszh_sfz2';
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
			
				$("#slgDrvXxcjbidName2").val(pName);
				$("#slgDrvXxcjbidSex2").val(pSex);
				$("#slgDrvXxcjbidNo2").val(pCardNo);
				$("#slgDrvXxcjbidAddress2").val(pAddress);
				
				var sfz_name = pName.replace(/(^s*)|(\s*$)/g, "");
				var chuli_type = "1"
				if (chuli_type == "1") {
					ClearIDCard();
				} else {
			
				}
				//判断id长度是否大于4,如果大于则不调用
				if (pCardNo.length > 4) {
					var file2val = $("#file2").val();
					if(file2val != "C:\\ycszh_sfz2\\zp.bmp"){
						var ssd=inputtext("C:\\ycszh_sfz2\\zp.bmp",drv_form.file2);
					}
					document.getElementById("sfzxpimgid2").src="C:\\ycszh_sfz2\\zp.bmp";
					document.getElementById("ReadResult1").innerHTML = "读取成功...";
					$("#chfile2").val("1");
				} else {
					if (flag == "0") {
						alert("请拿起身份证再试！");
						//ReadIDCard("0");
					}
				}
			}
		</script>
		<!-- 二代身份证读取JS结束 -->

		<!-- 高拍仪和摄像头读取JS开始 -->
		<script language="javascript">
			function setCaret(id, pos) {
　　				var textbox = id;
				//textRange
			　　var r = textbox.createTextRange();
				//collapse 将插入点移动到当前范围的开始或结尾
			　　r.collapse(true);
			    //moveStart 更改范围的开始位置
			　　r.moveStart('character',pos);
				//select 将当前选择区 置为当前对象
			　　r.select();
			}
			
			var ss="" ;
			
		    function getrr(value){
			    ss=value;
		    }
			
			function vide(){
				$("#chfile01").val("0");
				$("#chfile02").val("0");
				var chuli_type="1"
				if(chuli_type != 1){
					if(ss.length<5){
						alert("采集图片失败,请重新采集信息！");
						return;
					}
				}
				document.getElementById("ReadResult2").innerHTML="开始抓拍图片...";
				//document.getElementById("file01").outerHTML = document.getElementById("file01").outerHTML;
				//document.getElementById("file02").outerHTML = document.getElementById("file02").outerHTML;
				var obj = window.document.getElementById("test");
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
			      		var file01val = $("#file01").val();
						if(file01val != "C:\\"+sc+"tx.jpg"){
							var ssd=inputtext("C:\\"+sc+"tx.jpg",drv_form.file01);
						}
						$("#chfile01").val("1");
				        setTimeout(
							function(){
								document.all["img02"].src="C:\\printzj.jpg";
								document.all["img02a"].href="C:\\printzj.jpg";
								var file02val = $("#file02").val();
								if(file02val != "C:\\"+sc+"zj.jpg"){
									$("#file02").val("C:\\"+sc+"zj.jpg");
									var sd=inputtext("C:\\"+sc+"zj.jpg",drv_form.file02);
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
						    },1000
						);
					},1000
			    );
			}
			
			function svide(){
			  	document.getElementById("v_div").style.display="block";
			  	document.getElementById("v_vide").style.display="none";
			}
			
			function contentLoad(){
				var edittype = '${editType}';
				if ('查看' == edittype) {
					var isjsr = '${request.slgDrvXxcjb.isjsr}';
					$("#slgDrvXxcjbsfzmmc").attr("disabled", "true");
					$("input:radio[name='slgDrvXxcjb.isjsr'][value="+isjsr+"]").attr("checked", true);
					var ismyself = $("input:radio[name='slgDrvXxcjb.isjsr'][checked]").val();
					if(ismyself == 0){
						$("#sfzmhmdbrtd").show();
					}
				}else{
					setTimeout(
						function(){
							var obj2 = window.document.getElementById("ScanCtrl");
							obj2.StartPreview();
						},1000
					);
				}
				
				//
			}
			function changevideo(){
				var obj = window.document.getElementById("test");
				obj.changevideo();
			}
		</script>
		<!-- 高拍仪和摄像头读取JS结束 -->
		
		<!-- file赋值 -->
		<script type="text/javascript">
			function CreateFolder(src){
				var strFolder = src;
				var objFSO = new ActiveXObject("Scripting.FileSystemObject");
				// 检查文件夹是否存在
				if (!objFSO.FolderExists(strFolder)){
				   // 创建文件夹
				   var strFolderName = objFSO.CreateFolder(strFolder);
				}
			}
		</script>
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
			${editType }${ywms }采集信息&nbsp;<A
				href="<%=request.getContextPath()%>/drv/download.action">&nbsp;<FONT
				color=red>点击下载控件驱动程序!</FONT>
			</A>
		</DIV>
	</DIV>

	<!-- http://100.100.21.61/cmp_new/view_pic.asp?efid=1332A1500090C4967 -->
	<div id=admin_main style="WIDTH:95%; margin:0 auto;margin: 25px;margin-top:15px;font-size: 12px;color: #000;">

		<div class="tab2">
			<ul id="test2_li_now_">
				<li onclick="settab(this,0)" class="now">证件信息</li>
				<li onclick="settab(this,1)" style="display: none">基本信息</li>
			</ul>
		</div>

		<div class="div">
			<form action="<%=request.getContextPath()%>/drv/editSlgDrvXxcjb.action" method="post" id="drv_form" enctype="multipart/form-data" target="abc">
			<iframe id="abc" name="abc" width="0" height="0" style="display: none;"></iframe>
			<input type="hidden" name="slgDrvXxcjb.ywlx" value="${ywlxstr }" />
			<input type="hidden" name="slgDrvXxcjb.ywmsMain" value="${ywmsen }" />
			<input type="hidden" name="slgDrvXxcjb.gj" id="slgDrvXxcjbgj" />
			<input type="hidden" name="slgDrvXxcjb.zt" id="slgDrvXxcjbztr" />
			<input type="hidden" id="chaxunid" value="0" />
			
			<input type="hidden" name="xczpdate" id="xczpdate" />
			<input type="hidden" name="zjzpdate" id="zjzpdate" />
			
			<input type="hidden" id="chfile1" value="0" />
			<input type="hidden" id="chfile2" value="0" />
			<input type="hidden" id="chfile01" value="0" />
			<input type="hidden" id="chfile02" value="0" />
			<input type="hidden" name="slgDrvXxcjb.jsrbsedsfzmyy" id="jsrbsedsfzmyy"/>
			<input type="hidden" name="slgDrvXxcjb.dbrbsedsfzmyy" id="dbrbsedsfzmyy"/>
			<div id="tableid0" class="tablist block">
				<table class="edittable" border="0" cellpadding="0" cellspacing="0" align="center">
					<tr>
						<td colspan="7" id="slywlx">
							<s:iterator id="sjzd" value="#request.slgSjzdList" status="st">
								<span style="color: black;">
									<input type="checkbox" name="slgDrvXxcjb.ywmsMain" value="${sjzd.dmz}"/>${sjzd.dmms1}
								</span>
								<s:if test="#st.count%6 == 0">
									<br/>
								</s:if>
							</s:iterator>
						</td>
					</tr>
					<tr>
						<td colspan="7">&nbsp;&nbsp;
							<input type="radio" name="slgDrvXxcjb.isjsr" value="1" checked="checked"/>&nbsp;本人办理&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="slgDrvXxcjb.isjsr" value="0"/>&nbsp;他人代办
						</td>
					</tr>
					<tr>
						<td colspan="7">
							<table class="idcardclass" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td style="width: 130px; border-left: 0px; border-top: 0px; border-bottom: 0px; border-right: #d2e9ff 1px solid;">&nbsp;&nbsp;&nbsp;驾驶人身份证明名称：</td>
									<td style="text-align: left; width:47%; border-left: 0px; border-top: 0px; border-bottom: 0px; border-right: 0px;">
										&nbsp;
										<s:select list="#{'A':'二代居民身份证','B':'组织机构代码证书',
										'C':'军官证','D':'士兵证','E':'军官离退休证','F':'境外人员身份证明',
										'G':'外交人员身份证明','H':'居民户口薄','J':'单位注销证明',
										'K':'居住暂住证明','L':'驻华机构证明','M':'临时居民身份证'}" theme="simple"
											id="slgDrvXxcjbsfzmmc"
											listKey="key" listValue="value" name="slgDrvXxcjb.sfzmmc" value="#request.slgDrvXxcjb.sfzmmc" ></s:select>&nbsp;<!-- <font style="color: red;">*</font> -->
									</td>
									<td style="text-align: right;width: 145px; border-left: 0px; border-top: 0px; border-bottom: 0px; border-right: #d2e9ff 1px solid ; display: <s:property value='#request.editType eq "查看"?"none":"block"'/>" >代办人身份证明名称：</td>
									<td style="text-align: left; border-left: 0px; border-top: 0px; border-bottom: 0px; border-right: 0px; display: <s:property value='#request.editType eq "查看"?"none":"block"'/>">
										&nbsp;
										<s:select list="#{'A':'二代居民身份证','B':'组织机构代码证书',
										'C':'军官证','D':'士兵证','E':'军官离退休证','F':'境外人员身份证明',
										'G':'外交人员身份证明','H':'居民户口薄','J':'单位注销证明',
										'K':'居住暂住证明','L':'驻华机构证明','M':'临时居民身份证'}" theme="simple"
											id="dbrslgDrvXxcjbsfzmmc"
											listKey="key" listValue="value" name="dbrsfzmmc" ></s:select>&nbsp;<!-- <font style="color: red;">*</font> -->
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td colspan="3" class="tdtab"><strong>驾驶人信息</strong> 
						<input type="button" id="sfz_b" style="cursor:pointer;"
									value="读 卡" onclick="return ReadIDCard('0');" class="bnt">
							&nbsp;&nbsp;<span id="ReadResult" style="color: red;">等待读取...</span></td>
						<td width="26" rowspan="6"></td>
						<td colspan="3" class="tdtab"><strong>代办人信息</strong> 
							<input type="button" id="sfz_b2" style="cursor:pointer;"
									value="读 卡" onclick="return ReadIDCard1('0');" class="bnt">
							&nbsp;&nbsp;<span id="ReadResult1" style="color: red;">等待读取...</span></td>
					</tr>
					<tr>
						<td style="text-align: right;width: 10%">姓名：</td>
						<td style="text-align: left;width: 23%">
							&nbsp;<input type="text" class="disabled" readonly="readonly"
									size="30" id="slgDrvXxcjbidName" name="slgZjxxb.cardname"
									value="${slgZjxxb.cardname}" />
						</td>
						<td rowspan="4" style="text-align: center;width: 15%">
							<s:if test="#request.editType != '查看'">
								<img id="sfzxpimgid" src="<%=request.getContextPath()%>/images/cp.gif" width="108" height="120" border="0">
							</s:if>
							<s:else>
								<s:if test="#request.slgZjxxb.cardphoto == null || #request.slgZjxxb.cardphoto == ''">
									<img id="sfzxpimgid" src="<%=request.getContextPath()%>/images/cp.gif" width="108" height="120" border="0">
								</s:if>
								<s:else>
									<img src="http://100.100.21.61/cmp_new/view_pic.asp?efid=${slgZjxxb.cardphoto}" id="sfzxpimgid" width="108" height="120" border="0">
								</s:else>
							</s:else>
						</td>
						<td style="text-align: right;width: 10%">姓名：</td>
						<td style="text-align: left;width: 23%">
							&nbsp;<input type="text" class="disabled" readonly="readonly"
									size="30" id="slgDrvXxcjbidName2" name="slgZjxxb.cardnameDbr"
									value="${slgZjxxb.cardnameDbr}" />
						</td>
						<td style="text-align: center;width: 15%" rowspan="4">
							<s:if test="#request.editType != '查看'">
								<img id="sfzxpimgid2" src="<%=request.getContextPath()%>/images/cp.gif" width="108" height="120" border="0">
							</s:if>
							<s:else>
								<s:if test="#request.slgZjxxb.cardphotoDbr == null || #request.slgZjxxb.cardphotoDbr == ''">
									<img id="sfzxpimgid2" src="<%=request.getContextPath()%>/images/cp.gif" width="108" height="120" border="0">
								</s:if>
								<s:else>
									<img src="http://100.100.21.61/cmp_new/view_pic.asp?efid=${slgZjxxb.cardphotoDbr}" id="sfzxpimgid2" width="108" height="120" border="0">
								</s:else>
							</s:else>
						</td>
					</tr>
					<tr>
						<td style="text-align: right;width: 10%">性别：</td>
						<td style="text-align: left;width: 23%">
							&nbsp;<input type="text" class="disabled" readonly="readonly"
									size="30" id="slgDrvXxcjbidSex" name="slgZjxxb.cardsex"
									value="${slgZjxxb.cardsex}" />
						</td>
						<td style="text-align: right;width: 10%">性别：</td>
						<td style="text-align: left;width: 23%">
							&nbsp;<input type="text" class="disabled" readonly="readonly"
									size="30" id="slgDrvXxcjbidSex2" name="slgZjxxb.cardsexDbr"
									value="${slgZjxxb.cardsexDbr}" />
						</td>
					</tr>
					<tr>
						<td style="text-align: right;width: 10%">身份证号：</td>
						<td style="text-align: left;width: 23%">
							&nbsp;<input type="text" class="disabled" readonly="readonly"
									size="30" id="slgDrvXxcjbidNo" name="slgZjxxb.cardno"
									value="${slgZjxxb.cardno}" />
						</td>
						<td style="text-align: right;width: 10%">身份证号：</td>
						<td style="text-align: left;width: 23%">
							&nbsp;<input type="text" class="disabled" readonly="readonly"
									size="30" id="slgDrvXxcjbidNo2" name="slgZjxxb.cardnoDbr"
									value="${slgZjxxb.cardnoDbr}" />
						</td>
					</tr>
					<tr>
						<td style="text-align: right;width: 10%">地址：</td>
						<td style="text-align: left;width: 23%">
							&nbsp;<input type="text" class="disabled" readonly="readonly"
									size="30" id="slgDrvXxcjbidAddress" name="slgZjxxb.cardaddress"
									value="${slgZjxxb.cardaddress}" />
								<OBJECT classid="clsid:10946843-7507-44FE-ACE8-2B3483D179B7"
									codebase="CVR100.cab#version=3,0,3,3" id="CVR_IDCard"
									name="CVR_IDCard" width="108" height="110" align="middle"
									hspace="0" vspace="0" style="display: none;"></OBJECT>
						</td>
						<td style="text-align: right;width: 10%">地址：</td>
						<td style="text-align: left;width: 23%">
							&nbsp;<input type="text" class="disabled" readonly="readonly"
									size="30" id="slgDrvXxcjbidAddress2" name="slgZjxxb.cardaddressDbr"
									value="${slgZjxxb.cardaddressDbr}" />
						</td>
					</tr>
					<tr>
						<td style="text-align: right;width: 10%">&nbsp;照片路径：</td>
						<td style="text-align: left;width: 23%" colspan="2">
							&nbsp;<input type="file" name="file1" class="disabled" id="file1" title="请选择驾驶人身份证相片路径:C:\\ycszh_sfz1\\zp.bmp" style="width:320px;"/>
						</td>
						<td style="text-align: right;width: 10%">&nbsp;照片路径：</td>
						<td style="text-align: left;width: 23%" colspan="2">
							&nbsp;<input type="file" name="file2" class="disabled" id="file2" title="请选择代办人身份证相片路径:C:\\ycszh_sfz2\\zp.bmp" style="width:320px;"/>
						</td>
					</tr>
					<tr>
						<td colspan="7">
							<table class="idcardclass" width="100%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td style="text-align: left; width: 50%; border-left: 0px; border-top: 0px; border-bottom: 0px; border-right: #d2e9ff 0px solid">
										&nbsp;&nbsp;驾驶人驾驶证号码：
										<input type="text" class="disabled1" id="slgDrvXxcjbsfzmhm"
											name="slgDrvXxcjb.sfzmhm" value="${slgDrvXxcjb.sfzmhm}" size="20"
											maxlength="20" onkeyup="clearspace(this)" onblur="clearallspace(this)" />&nbsp;<font style="color: red;">*</font>&nbsp;
										<!--<s:if test="#request.ywlx != 'CCSL' && #request.ywlx != 'JJSL' && #request.ywlx != 'JWSL' && #request.ywlx != 'XXMSHZ'">
											<input type="button" style="cursor:pointer;" value="查 询" id="sfzmhmbid" class="bnt" />
										</s:if>-->
									
										驾驶人姓名：
										<input type="text" class="disabled1" id="slgDrvXxcjbxm"
											name="slgDrvXxcjb.xm" value="${slgDrvXxcjb.xm}" size="10"
											maxlength="20" onkeyup="clearspace(this)" onblur="clearallspace(this)" />&nbsp;<font style="color: red;">*</font>
								
									</td>
									<td id="sfzmhmdbrtd" style="text-align: left; border-left: #d2e9ff 1px solid; border-top: 0px; border-bottom: 0px; border-right: #d2e9ff 0px solid; display: none;">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;代办人驾驶证号码：
										<input type="text" class="disabled1" id="slgDrvXxcjbsfzmhmdbr"
											name="slgDrvXxcjb.sfzmhmdbr" value="${slgDrvXxcjb.sfzmhmdbr}" size="20"
											maxlength="20" onkeyup="clearspace(this)" onblur="clearallspace(this)" />&nbsp;<font style="color: red;">*</font>&nbsp;
										<!--<s:if test="#request.ywlx != 'CCSL' && #request.ywlx != 'JJSL' && #request.ywlx != 'JWSL' && #request.ywlx != 'XXMSHZ'">
											<input type="button" style="cursor:pointer;" value="查 询" id="sfzmhmbid" class="bnt" />
										</s:if>-->
										代办人姓名：
										<input type="text" class="disabled1" id="slgDrvXxcjbxmdbr"
											name="slgDrvXxcjb.xmdbr" value="${slgDrvXxcjb.xmdbr}" size="10"
											maxlength="20" onkeyup="clearspace(this)" onblur="clearallspace(this)" />&nbsp;<font style="color: red;">*</font>
									</td>
									
								</tr>
							</table>
						</td>
					</tr>
					<!---<s:if test="#request.editType eq '查看'">
						<tr>
							<td colspan="7">
								<table class="idcardclass" border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td style="width: 145px; border-left: 0px; border-top: 0px; border-bottom: 0px; border-right: #d2e9ff 1px solid;">&nbsp;&nbsp;&nbsp;驾驶人信息未读卡原因：</td>
										<td style="text-align: left; width:440px; border-left: 0px; border-top: 0px; border-bottom: 0px; border-right: 0px;">
											&nbsp;
											${slgDrvXxcjb.jsrbsedsfzmyy}
										</td>
										<td style="text-align: right;width: 20%; border-left: 0px; border-top: 0px; border-bottom: 0px; border-right: #d2e9ff 1px solid ;" >代办人信息未读卡原因：</td>
										<td style="text-align: left; border-left: 0px; border-top: 0px; border-bottom: 0px; border-right: 0px;">
											&nbsp;
											${slgDrvXxcjb.dbrbsedsfzmyy}
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</s:if>-->
					
				</table>
				<br />	  
				<table class="edittable" border="0" cellpadding="0" cellspacing="0" align="center">
				  <tr>
					<td height="33" colspan="4"><strong>摄像头及高拍仪(当事人现场照片及身份证明扫描照片)</strong>
						&nbsp;<input type="button" style="cursor:pointer;" name="zp" id="zp" value="抓拍图片" class="bnt" onclick="vide();">
						 &nbsp;&nbsp;<span id="ReadResult2" style="color: red;">等待抓拍...</span>
						 <input type="hidden" name="flags" id="flags" value="0">
						   <strong>备注：</strong><span>当图像没有加载请点击“切换视频”按钮切换。</span>
					</td>
				  </tr>
				  <s:if test="#request.editType != '查看'">
					  <tr>
						<td style="width: 25%" height="36" align="center">当事人现场照片区域</td>
						<td style="width: 25%" align="center">身份证件拍摄区域</td>
						<td style="width: 25%" align="center">当事人现场照片区域</td>
						<td style="width: 25%" align="center">身份证件拍摄区域</td>
					  </tr>
					  <tr>
						<td style="width: 25%" height="169" align="center">
							<object id="test" classid="clsid:9C60C50B-835C-4F7C-A4AB-02C0139E807A"></object>
						</td>
						<td style="width: 25%" height="169" align="center">
							<object id="ScanCtrl" classid="clsid:090457CB-DF21-41EB-84BB-39AAFC9E271A"  CODEBASE="*.cab#version=1,0,0,1" width="220" height="180"></object>
						</td>
						<s:if test="#request.editType != '查看'">
							<td style="width: 25%" align="center">
								<a id="img01a" href="#" title="摄像头图片"><img src="<%=request.getContextPath()%>/images/cp.gif" id="img01" width="210" height="175" border="0"></a>
							</td>
							<td style="width: 25%" align="center">
								<a id="img02a" href="" title="高拍仪图片"><img src="<%=request.getContextPath()%>/images/cp.gif" id="img02" width="210" height="175" border="0"></a>
							</td>
						</s:if>
						<s:else>
							<td style="width: 25%" align="center">
								<a id="img01a" href="http://100.100.21.61/cmp_new/view_pic.asp?efid=${slgZjxxb.sxtZp}" title="摄像头图片"><img src="http://100.100.21.61/cmp_new/view_pic.asp?efid=${slgZjxxb.sxtZp}" id="img01" width="210" height="175" border="0"></a>
							</td>
							<td style="width: 25%" align="center">
								<a id="img02a" href="http://100.100.21.61/cmp_new/view_pic.asp?efid=${slgZjxxb.gpyZp}" title="高拍仪图片"><img src="http://100.100.21.61/cmp_new/view_pic.asp?efid=${slgZjxxb.gpyZp}" id="img02" width="210" height="175" border="0"></a>
							</td>
						</s:else>
						</tr>
					  <tr>
						<td style="width: 50%" colspan="2" align="center">
							<input type="button" style="cursor:pointer;" onclick="javascript:changevideo();" value="切换视频" class="bnt" />
						  	<input type="button" style="cursor:pointer;" onclick="javascript:window.location.reload();" value="刷  新" class="bnt" />
						</td>
						<td style="width: 25%" width="116" align="center">
							<input type="file" name="file01" id="file01" class="disabled" title="请选择摄像头图片路径:C:\\printtx.jpg" style="width:210px;" />
						</td>
						<td style="width: 25%" width="102" align="center">
							<input type="file" name="file02" id="file02" class="disabled" title="请选择高拍仪图片路径:C:\\printzj.jpg" style="width:210px;" />
						</td>
					  </tr>
					</s:if>
					<s:else>
					  <tr>
						<td style="width: 25%" height="36" align="center">当事人现场照片</td>
						<td style="width: 25%" align="center">身份证件照片</td>
						<td style="width: 50%" align="center">采集信息</td>
					  </tr>
					  <tr>
						<s:if test="#request.editType != '查看'">
							<td style="width: 25%" align="center">
								<a id="img01a" href="<%=request.getContextPath()%>/jsr/jsr_viewImage.action?id=${slgDrvXxcjb.cjid}&type=2" title="摄像头图片"><img src="<%=request.getContextPath()%>/images/cp.gif" id="img01" width="210" height="175" border="0"></a>
							</td>
							<td style="width: 25%" align="center">
								<a id="img02a" href="<%=request.getContextPath()%>/jsr/jsr_viewImage.action?id=${slgDrvXxcjb.cjid}&type=3" title="高拍仪图片"><img src="<%=request.getContextPath()%>/images/cp.gif" id="img02" width="210" height="175" border="0"></a>
							</td>
						</s:if>
						<s:else>
							<td style="width: 25%" align="center">
								<a id="img01a" href="http://100.100.21.61/cmp_new/view_pic.asp?efid=${slgZjxxb.sxtZp}" title="摄像头图片"><img src="http://100.100.21.61/cmp_new/view_pic.asp?efid=${slgZjxxb.sxtZp}" id="img01" width="210" height="175" border="0"></a>
							</td>
							<td style="width: 25%" align="center">
								<a id="img02a" href="http://100.100.21.61/cmp_new/view_pic.asp?efid=${slgZjxxb.gpyZp}" title="高拍仪图片"><img src="http://100.100.21.61/cmp_new/view_pic.asp?efid=${slgZjxxb.gpyZp}" id="img02" width="210" height="175" border="0"></a>
							</td>
						</s:else>
						<td style="center;border:3px solid #D2E9FF;width: 50%;" colspan="2" valign="top">
							<table style="width:100%;">
								<tr>
									<td style="text-align: right;width: 25%;">
										经办人：
									</td>
									<td style="text-align: left;width: 75%;">
										&nbsp;
										${slgDrvXxcjb.czrxm}
									</td>
								</tr>
								<tr>
									<td style="text-align: right;width: 25%;">
										经办人部门：
									</td>
									<td style="text-align: left;width: 75%;">
										&nbsp;
										${slgDrvXxcjb.czbm}
									</td>
								</tr>
								<tr>
									<td style="text-align: right;width: 25%;">
										经办时间：
									</td>
									<td style="text-align: left;width: 75%;">
										&nbsp;
										<s:date name="#request.slgDrvXxcjb.czrq" format="yyyy-MM-dd HH:mm:ss"/>
									</td>
								</tr>
							</table>
						</td>
						</tr>
					</s:else>
				</table>
				<table class="edittable" border="0" cellpadding="0" cellspacing="0" align="center" class="edittable">
					<tr>
						<td colspan="4" height="50" align="center"
							style="text-align: center">
							<s:if test="#request.editType != '查看'">
								<input type="button" style="cursor:pointer;" onclick="javascript:realsubmit();" value="保 存" class="bnt" />
								<input type="button" style="cursor:pointer;" onclick="javascript:window.history.go(-1);" value="返 回" class="bnt" />
							</s:if>
							<s:else>
								<input type="button" style="cursor:pointer;" onclick="javascript:window.close();" value="关 闭" class="bnt" />
							</s:else>
						</td>
					</tr>
				</table>
			</div>

			<div id="tableid1" class="tablist">
				<table class="edittable" border="0" cellpadding="0" cellspacing="0" align="center">
					<tr>
						<td colspan="6"><strong>基本信息&nbsp;<font color="#003399">(以下信息带"*"的输入项为必填项,其他为选填项.)</font></strong>
						</td>
					</tr>
					<tr>
						<td style="text-align: right;width: 15%">业务类型：</td>
						<td style="text-align: left;width: 85%" colspan="5">&nbsp;${ywms }</td>
					</tr>
					<tr>
						<td style="text-align: right;width: 10%">业务原因：</td>
						<td style="text-align: left;width: 90%" colspan="5">
							<s:if test="#request.ywlx == 'BZHZ'">
								<s:iterator id="ywlxlist" value="#request.slgYwlxList" status="st">
									<s:if test="#request.slgYwlxList.size < 2">
										<input type="checkbox" class="disabled1" id="slgDrvXxcjbywyyid${st.count}" name="slgDrvXxcjb.ywyy" value="${ywlxlist.ywyy }" checked="checked" />&nbsp;${ywlxlist.ywms_sub }&nbsp;
									</s:if>
									<s:else>
										<input type="checkbox" class="disabled1" id="slgDrvXxcjbywyyid${st.count}" name="slgDrvXxcjb.ywyy" value="${ywlxlist.ywyy }" />&nbsp;${ywlxlist.ywms_sub }&nbsp;&nbsp;&nbsp;&nbsp;
									</s:else>
								</s:iterator>
							</s:if>
							<s:else>
								<s:iterator id="ywlxlist" value="#request.slgYwlxList" status="st">
									<s:if test="#request.slgYwlxList.size < 2">
										<input type="radio" class="disabled1" id="slgDrvXxcjbywyyid${st.count}" name="slgDrvXxcjb.ywyy" value="${ywlxlist.ywyy }" checked="checked" />&nbsp;${ywlxlist.ywms_sub }&nbsp;
									</s:if>
									<s:else>
										<input type="radio" class="disabled1" id="slgDrvXxcjbywyyid${st.count}" name="slgDrvXxcjb.ywyy" value="${ywlxlist.ywyy }" />&nbsp;${ywlxlist.ywms_sub }&nbsp;
									</s:else>
								</s:iterator>&nbsp;<!-- <font style="color: red;">*</font> -->
							</s:else>
						</td>
					</tr>
					<tr>
						<td align="right">性别：</td>
						<td>
							&nbsp;
							<input type="radio" class="disabled1" id="slgDrvXxcjbxb0"
								name="slgDrvXxcjb.xb" value="男" />男
							<input type="radio" class="disabled1" id="slgDrvXxcjbxb1"
								name="slgDrvXxcjb.xb" value="女" />女
							&nbsp;<!-- <font style="color: red;">*</font> -->
						</td>
						<td align="right">出生日期：</td>
						<td>
							&nbsp;
							<input type="text" class="disabled1" id="slgDrvXxcjbcsrq"
								name="slgDrvXxcjb.csrq" value="<s:date name="#request.slgDrvXxcjb.csrq" format="yyyy-MM-dd"/>" size="20"
								maxlength="20" />&nbsp;<!-- <font style="color: red;">*</font> -->
						</td>
						<td align="right">联系电话：</td>
						<td>
							&nbsp;
							<input type="text" class="disabled1" id="slgDrvXxcjblxdh"
								name="slgDrvXxcjb.lxdh" value="${slgDrvXxcjb.lxdh}" size="20"
								maxlength="20" />
						</td>
					</tr>
					<tr>
						<td align="right">手机号码：</td>
						<td>
							&nbsp;
							<input type="text" class="disabled1" id="slgDrvXxcjbsjhm"
								name="slgDrvXxcjb.sjhm" value="${slgDrvXxcjb.sjhm}" size="20"
								maxlength="20" />&nbsp;<!-- <font style="color: red;">*</font> -->
						</td>
						<td align="right">电子邮箱：</td>
						<td colspan="3">
							&nbsp;
							<input type="text" class="disabled1" id="slgDrvXxcjbdzyx"
								name="slgDrvXxcjb.dzyx" value="${slgDrvXxcjb.dzyx}" size="20"
								maxlength="40" />
						</td>
					</tr>
					<tr>
						<!-- <td align="right">登记住所行政区划：</td>
						<td>
							&nbsp;
							<select name="slgDrvXxcjb.djzsxzqh" id="slgDrvXxcjbdjzsxzqh" class="disabled1">
								<option value="">---请选择---</option>
								<s:iterator id="xzqh" value="#request.xzqhlist">
									<option value="${xzqh[0]}">${xzqh[1]}</option>
								</s:iterator>
							</select>
						</td> -->
						<td align="right">登记住所地址：</td>
						<td colspan="5">
							&nbsp;
							<input type="text" class="disabled1" id="slgDrvXxcjbdjzsxxdz"
								name="slgDrvXxcjb.djzsxxdz" value="${slgDrvXxcjb.djzsxxdz}" size="60"
								maxlength="20" />
						</td>
					</tr>
					<s:if test="#request.ywlx != 'CCSL' && #request.ywlx != 'JJSL' && #request.ywlx != 'JWSL' && #request.ywlx != 'XXMSHZ'">
					<tr>
						<td colspan="6"><strong>档案信息</strong>
						</td>
					</tr>
					<tr>
						<td align="right">驾驶证号码：</td>
						<td>
							&nbsp;
							<input type="text" class="disabled" readonly="readonly" id="slgDrvXxcjbjszhm"
								value="${slgDrvXxcjb.sfzmhm}" size="20"
								maxlength="20" />
						</td>
						<td align="right">档案编号：</td>
						<td>
							&nbsp;
							<input type="text" class="disabled1" id="slgDrvXxcjbdabh"
								name="slgDrvXxcjb.dabh" value="${slgDrvXxcjb.dabh}" size="20"
								maxlength="20" />&nbsp;<!-- <font style="color: red;">*</font>&nbsp;
							<input type="button" style="cursor:pointer;" value="查 询" id="dzbhbid" class="bnt" /> -->
						</td>
						<td align="right">状态：</td>
						<td>
							&nbsp;
							<input type="text" class="disabled" readonly="readonly" id="slgDrvXxcjbzt"
								 value="${slgDrvXxcjb.zt}" size="20"
								maxlength="20" />
						</td>
					</tr>
					<tr>
						<td align="right">准驾车型：</td>
						<td>
							&nbsp;
							<input type="text" class="disabled" readonly="readonly" id="slgDrvXxcjbzjcx"
								name="slgDrvXxcjb.zjcx" value="${slgDrvXxcjb.zjcx}" size="20"
								maxlength="20" />
						</td>
						<td align="right">累计积分：</td>
						<td>
							&nbsp;
							<input type="text" class="disabled" readonly="readonly" id="slgDrvXxcjbljjf"
								name="slgDrvXxcjb.ljjf" value="${slgDrvXxcjb.ljjf}" size="20"
								maxlength="20" />
						</td>
						<td align="right">审验日期：</td>
						<td>
							&nbsp;
							<input type="text" class="disabled" readonly="readonly" id="slgDrvXxcjbsyrq"
								name="slgDrvXxcjb.syrq" value="<s:date name="#request.slgDrvXxcjb.syrq" format="yyyy-MM-dd"/>" size="20"
								maxlength="20" />
						</td>
					</tr>

					<tr>
						<td align="right">初次领证日期：</td>
						<td>
							&nbsp;
							<input type="text" class="disabled" readonly="readonly" id="slgDrvXxcjbcclzrq"
								name="slgDrvXxcjb.cclzrq" value="<s:date name="#request.slgDrvXxcjb.cclzrq" format="yyyy-MM-dd"/>" size="20"
								maxlength="20" />
						</td>
						<td align="right">有效期止：</td>
						<td>
							&nbsp;
							<input type="text" class="disabled" readonly="readonly" id="slgDrvXxcjbyxqz"
								name="slgDrvXxcjb.yxqz" value="<s:date name="#request.slgDrvXxcjb.yxqz" format="yyyy-MM-dd"/>" size="20"
								maxlength="20" />
						</td>
						<td align="right">联系电话：</td>
						<td>
							&nbsp;
							<input type="text" class="disabled" readonly="readonly" id="slgDrvXxcjblxdh2"
								 value="${slgDrvXxcjb.lxdh}" size="20"
								maxlength="20" />
						</td>
					</tr>
					<tr>
						<td align="right">联系住所行政区划：</td>
						<td>
							&nbsp;
							<select name="slgDrvXxcjb.lxzsxzqh" id="slgDrvXxcjblxzsxzqh" class="disabled">
								<option value="">---请选择---</option>
								<s:iterator id="xzqh" value="#request.xzqhlist">
									<option value="${xzqh[0]}">${xzqh[1]}</option>
								</s:iterator>
							</select>
						</td>
						<td align="right">联系住所地址：</td>
						<td colspan="3">
							&nbsp;
							<input type="text" class="disabled" readonly="readonly" id="slgDrvXxcjblxzsxxdz"
								name="slgDrvXxcjb.lxzsxxdz" value="${slgDrvXxcjb.lxzsxxdz}" size="60"
								maxlength="20" />
						</td>
					</tr>
					</s:if>
					<tr>
						<td><strong>统一版流水号：</strong></td>
						<td colspan="5">
							&nbsp;
							<input type="text" class="disabled1" id="slgDrvXxcjblsh"
								name="slgDrvXxcjb.lsh" value="${slgDrvXxcjb.lsh}" size="40"
								maxlength="30" />&nbsp;<!-- <font style="color: red;">*</font> -->
						</td>
					</tr>
					
				</table>
				<table class="edittable" border="0" cellpadding="0" cellspacing="0" align="center">
					<tr>
						<td colspan="4" height="50" align="center"
							style="text-align: center">
							<s:if test="#request.editType != '查看'">
								<input type="button" style="cursor:pointer;" onclick="javascript:submitfrom();" value="保 存" class="bnt" />
								<input type="button" style="cursor:pointer;" onclick="javascript:window.history.go(-1);" value="返 回" class="bnt" />
							</s:if>
							<s:else>
								<input type="button" style="cursor:pointer;" onclick="javascript:window.close();" value="关 闭" class="bnt" />
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