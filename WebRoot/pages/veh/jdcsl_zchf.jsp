<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path" value="<%=request.getContextPath()%>"></c:set>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<title>机动车受理${editTypeXx }信息</title>
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
<script type="text/javascript"
	src="<%=request.getContextPath()%>/pages/drv/jquery_divmove.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/veh/cookie.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/veh/jdcsledit.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/veh/jdcsl_zchf.js"></script>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/jquery/jquery-lightbox-0.5/css/jquery.lightbox-0.5.css"
	media="screen" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery/jquery-easyui-1.3.6/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery/jquery-easyui-1.3.6/themes/icon.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery/jquery-easyui-1.3.6/demo/demo.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/jquery-easyui-1.3.6/jquery.easyui.min.js"></script>
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
	}
	
	.tab1,.tab2 {
		width: 100%;
		height: 34px;
		border: 1px #4fb3d5 solid;
		border-bottom: 0;
		background: #fff;
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
		border-right: 1px #4fb3d5 solid;
		border-bottom: 1px #4fb3d5 solid;
		cursor: pointer;
		font-size: 16px;
		letter-spacing: 2px;
		background: #D0D0D0;
	}
	
	.tab1 li.now,.tab2 li.now {
		color: #fff;
		font-weight: bold;
		background: #407fb7;
	}
	
	.tab1 li.last,.tab2 li.last {
		padding-top:4px;
		font-size: 14px;
		background: #fff;
		border-right: 0px;
		border-bottom: 0px;
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
	
	.tabywlx{
		border-left: 1px #4fb3d5 solid;
		border-top: 1px #4fb3d5 solid;
	}
	.tabywlx .tdleft{
		border-right: 1px #4fb3d5 solid;
		border-bottom: 1px #4fb3d5 solid;
	}
	.tabywlx .tdright{
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
		background: url(<%=request.getContextPath()%>/images/an.gif ) no-repeat;
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
</style>

<script type="text/javascript">
	var path = '${path}';
	var userdept = '${userbean.bmid}';
	var chuli;
	$(document).ready(function(){
		//图片放大
		$(function(){
			$("#img01a").lightBox();
			$("#img02a").lightBox();
		});
	});
	
	function contentLoad(){
		var edittype = '${editType}';
		var lsh = '${lsh}';
		
		$("#tableid1 input").each(function(){
    		$(this).attr("disabled", "disabled");
    	});
		$("#tableid1 select").each(function(){
			$(this).attr("disabled", "disabled");
		});
		$("#tableid2 input").each(function(){
			$(this).attr("disabled", "disabled");
		});
		$("#tableid2 select").each(function(){
			$(this).attr("disabled", "disabled");
		});
		$("#tableid3 input").each(function(){
			$(this).attr("disabled", "disabled");
		});
		$("#tableid3 select").each(function(){
			$(this).attr("disabled", "disabled");
		});
		
		if ('查看' == edittype) {
			var dydjlsh = '${dbZjxxb.lsh}';
		}else{
			setTimeout(
				function(){
					var obj2 = window.document.getElementById("ScanCtrl");
					obj2.StartPreview();
				},1000
			);
			$("#hpzl").val("02");
		}
		var hphm = $("#hphm").val();
		if(hphm == null || hphm == ""){
			$("#hphm").val("B");
		}
		
	}
	/*<!-- file赋值 -->*/
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
		var lshval = $("#lshval").val();
		var dyrsfz = $("#dyrSfzCardno").val();
		var first = lshval.substring(0,1);
		if(first == "D"){
			if(dyrsfz != null && dyrsfz != ""){
				alert("证件信息已读取，请参见抵押登记选项卡抵押人信息，或者此业务无需读取此证件信息!");
				return false;
			}
		}
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
		$("#dsrSfzCardname1").val(pName);
		$("#dsrSfzCardsex1").val(pSex);
		$("#dsrSfzCardno1").val(pCardNo);
		$("#dsrSfzCardaddress1").val(pAddress);
		$("#dsrLxdz").val(pAddress);
		$("#dsrsfzmhm").val(pCardNo);
		$("#dsrxm").val(pName);
		$("#dsrsfzmmc").val("A");
		
		$("#dsrsfzmhm").attr("readonly", "readonly");
		$("#dsrxm").attr("readonly", "readonly");
		var sfz_name = pName.replace(/(^s*)|(\s*$)/g, "");
		var chuli_type = "1"
		if (chuli_type == "1") {
			ClearIDCard();
		} else {
	
		}
		//判断id长度是否大于4,如果大于则不调用
		if (pCardNo.length > 4) {
			setTimeout(function(){
				var ssd=inputtext("C:\\ycszh_sfz1\\zp.bmp",drv_form.file1);
			},100);
			document.getElementById("sfzxpimgid").src="C:\\ycszh_sfz1\\zp.bmp";
			document.getElementById("ReadResult").innerHTML = "读取成功...";
			$("#chfile1").val("1");
			
		} else {
			if (flag == "0") {
				alert("请拿起身份证再试！");
			}
		}
	}
	
	//代办人证件采集
	function ReadIDCard1(flag) {
		var lshval = $("#lshval").val();
		var dyrsfz = $("#dyrSfzCardno").val();
		var first = lshval.substring(0,1);
		if(first == "D"){
			if(dyrsfz != null && dyrsfz != ""){
				alert("证件信息已读取，请参见抵押登记选项卡抵押人信息，或者此业务无需读取此证件信息!");
				return false;
			}
		}
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
	
		$("#dbrSfzCardname1").val(pName);
		$("#dbrSfzCardsex1").val(pSex);
		$("#dbrSfzCardno1").val(pCardNo);
		$("#dbrSfzCardaddress1").val(pAddress);
		
		$("#dbrsfzmhm").val(pCardNo);
		$("#dbrxm").val(pName);
		
		$("#dbrsfzmmc").val("A");
		$("#dbrsfzmhm").attr("readonly", "readonly");
		$("#dbrxm").attr("readonly", "readonly");
		var sfz_name = pName.replace(/(^s*)|(\s*$)/g, "");
		var chuli_type = "1"
		if (chuli_type == "1") {
			ClearIDCard();
		} else {
	
		}
		//判断id长度是否大于4,如果大于则不调用
		if (pCardNo.length > 4) {
			setTimeout(function(){
				var ssd=inputtext("C:\\ycszh_sfz2\\zp.bmp",drv_form.file2);
			},100);
			document.getElementById("sfzxpimgid2").src="C:\\ycszh_sfz2\\zp.bmp";
			document.getElementById("ReadResult1").innerHTML = "读取成功...";
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
	
	function changevideo(){
		var obj = window.document.getElementById("camera");
		obj.changevideo();
	}
</script>

<!-- 组织机构代码证读取JS开始 -->
<script type="text/javascript">
	function btnGetOrgInfo_onclick() {
		var lshval = $("#lshval").val();
		var dyrsfz = $("#dyrSfzCardno").val();
		var first = lshval.substring(0,1);
		if (document.getElementById("ddlOrgCom").value.length <= 0) {
			alert("请选择设备接口！");
			return false;
		}
		if(first == "D"){
			if(dyrsfz != null && dyrsfz != ""){
				alert("证件信息已读取，请参见抵押登记选项卡抵押人信息，或者此业务无需读取此证件信息!");
				return false;
			}
		}
		$("#sfz_b2").attr("disabled", "disabled");
		$("#zzjgz2").attr("disabled", "disabled");
		$("#zp").attr("disabled", "disabled");
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
					document.getElementById("dsrZzjgZh1").value = a[2];
					document.getElementById("dsrzzjgdm").value = a[2];
				}
				if (a[1] == 'dwmc') {
					document.getElementById("dsrZzjgDwmc1").value = a[2];
				}
				if (a[1] == 'dwzz') {
					document.getElementById("dsrZzjgDz1").value = a[2];
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
		$("#dsrzzjgdm").attr("readonly", "readonly");
		$("#dsrdwmc").attr("readonly", "readonly");
		$("#sfz_b2").removeAttr("disabled");
		$("#zzjgz2").removeAttr("disabled");
		$("#zp").removeAttr("disabled");
	}
	
	function btnGetOrgInfo_onclick2(){
		if (document.getElementById("dbrddlOrgCom").value.length <= 0) {
			alert("请选择设备接口！");
			return false;
		}
		var lshval = $("#lshval").val();
		var dyrsfz = $("#dyrSfzCardno").val();
		var first = lshval.substring(0,1);
		if(first == "D"){
			if(dyrsfz != null && dyrsfz != ""){
				alert("证件信息已读取，请参见抵押登记选项卡抵押人信息，或者此业务无需读取此证件信息!");
				return false;
			}
		}
		$("#zp").attr("disabled", "disabled");
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
		$("#zp").removeAttr("disabled");
		$("#dbrzzjgdm").attr("readonly", "readonly");
		$("#dbrdwmc").attr("readonly", "readonly");
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

<!-- 查看页面的操作 -->
<script type="text/javascript">
	$(document).ready(function() {
		var edittype = '${editType}';
		if('查看' == edittype){
			$(".disabled").attr("disabled","disabled");
			$("select").attr("disabled","disabled");
			$(".disabled1").attr("disabled","disabled");
			$(".dis").attr("disabled","disabled");
			
			var bllx = '${dbZjxxb.bllx}';
			if (bllx==2){
				$(':radio').val(["2"]);
				$("#dbrsfzmtd").show();
				$("#tddbrzjgsfzm").show();
			}
			
		}
	});
</script>

</head>
<body onload="contentLoad();">
	<DIV id=admin_title>
		<DIV class=logo>
			<IMG src="<%=request.getContextPath()%>/images/frame_19.gif">
		</DIV>
		<DIV class=title>
			${editType }${title} ${editTypeXx }信息&nbsp;<A
				href="<%=request.getContextPath()%>/drv/download.action">&nbsp;<FONT
				color=red>点击下载控件驱动程序!</FONT>
			</A>
		</DIV>
	</DIV>

	<!-- http://100.100.21.61/cmp_new/view_pic.asp?efid=1332A1500090C4967 -->
	<div id=admin_main style="WIDTH:95%; margin:0 auto;margin: 25px;margin-top:15px;font-size: 12px;color: #000; z-index: -1000;">

		<div class="tab2">
			<form action="" method="post" id="lsh_form">
			<ul id="test2_li_now_">
				<li onclick="settab(this,0)" class="now" style="width: 80px;">证件信息</li>
				<li onclick="settab(this,1)" style="width: 80px;" <s:if test="#request.pageforward == 'tbyw'">style='display: none;'' </s:if>>流水信息</li>
				<li onclick="settab(this,2)" style="width: 100px;">机动车信息</li>
				<li onclick="settab(this,3)" style="width: 115px;" <s:if test="#request.pageforward == 'zchf'">style='display: none;'' </s:if> >证件采集信息</li>
				
				<li style="text-align: right; width: 320px;" class="last" id="lshli">
					流水号：<input class="dis" type="text" name="lsh" id="lsh" onkeyup="clearspace(this)" onblur="clearallspace(this)"  value="${request.lshval}" size="20"/>&nbsp;&nbsp;&nbsp;&nbsp;
					<s:if test="'查看' != #request.editType">
						<input type="button" id="sfz_b" style="cursor:pointer;" onclick="getEsvehflowandvehicle()" value="查询" class="bnt">
					</s:if>
					<s:else>
					</s:else>
				</li>
			</ul>
			</form>
		</div>
		
		<div class="div">
			<form action="<%=request.getContextPath()%>/veh/veh_editSlgVehXxcj.action" method="post" id="drv_form" enctype="multipart/form-data" target="abc">
			<iframe id="abc" name="abc" width="0" height="0" style="display: none;"></iframe>
			<input type="hidden" name="dbZjxxb.ywlx" id="ywlx" value=""/>
			<input type="hidden" name="dbZjxxb.ywyy" id="ywyy" value=""/>
			<input type="hidden" name="bz" id="bz" value="${ptdo.bz}"/>
			<input type="hidden" name="dbZjxxb.lsh" id="lshval" value=""/>
			<input type="hidden" name="ywyysize" id="ywyysize" value=""/>
			<input type="hidden" id="chfile1" value="0" />
			<input type="hidden" id="chfile2" value="0" />
			<input type="hidden" id="chfile01" value="0" />
			<input type="hidden" id="chfile02" value="0" />
			<input type="hidden" name="xczpdate" id="xczpdate" />
			<input type="hidden" name="zjzpdate" id="zjzpdate" />
			
			<input type="hidden" name="dbZjxxb.zblx" id="zblx" value=""/>
			<input type="hidden" name="dbZjxxb.zbh" id="zbh" value=""/>
			<input type="hidden" name="dbZjxxb.tyblsh" id="tyblsh" value=""/>
			<input type="hidden" name="dbZjxxb.cllx" id="cllx" value=""/>
			<input type="hidden" name="dbZjxxb.syxz" id="syxz" value=""/>
			<input type="hidden" name="dbZjxxb.zjxxblsh" id="zjxxblsh" value=""/>
			<input type="hidden" name="dbZjxxb.jhzbh" id="jhzbh" value=""/>
			<input type="hidden" name="dbZjxxb.sffq" id="sffq" value=""/>
			<input type="hidden" name="dbZjxxb.zczxhfhljzyqchzx" id="zczxhfhljzyqchzx" value=""/>
			<input type="hidden" name="zbyzflag" id="zbyzflag" value="0"/>
			<input type="hidden" name="dbZjxxb.hdfs" id="hdfs" value="0"/>
			<input type="hidden" name="pageforward" id="pageforward" value="${pageforward}"/>
			<input type="hidden" name="dbZjxxb.gzh" id="gzh" value=""/>
			<input type="hidden" name="dbZjxxb.clsbdh" id="clsbdhval" value=""/>
			<input type="hidden" name="dbZjxxb.vehflowywlx" id="vehflowywlx" value=""/>
			<input type="hidden" name="dbZjxxb.vehflowywyy" id="vehflowywyy" value=""/>
			<input type="hidden" name="alterinfo" id="alterinfo" value=""/>
			<div id="tableid0" class="tablist block">
				<table class="edittable" border="0" cellpadding="0" cellspacing="0" align="center">
					<tr>
						<td colspan="7" id="slywlx" style="padding-left: 15px;">
							<s:if test="#request.editType != '查看'">
								业务类型：<input type="text" id="ywlxtext" name="ywlxtext" readonly="readonly" size="20" />
							</s:if>
							<s:else>
								业务类型: ${dbZjxxb.ywlx}
							</s:else>
						</td>
					</tr>
					<tr>
						<td colspan="7" style="padding-left: 15px;">
							<s:if test="#request.lsh == 1">
								号牌号码：<input class="dis" type="text" name="dbZjxxb.hphm" onkeyup="toUpers(this);" onkeyup="clearspace(this)" onblur="clearallspace(this)"  value="${ptdo.hphm}" id="hphm"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</s:if>
							<s:else>
								号牌号码：<input class="dis" type="text" name="dbZjxxb.hphm" onkeyup="toUpers(this);" onblur="clearallspace(this)"  value="${dbZjxxb.hphm}" id="hphm"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</s:else>
							号牌种类：
							<select class="dis" name="dbZjxxb.hpzl" id="hpzl">
								<s:iterator var="hp" value="#request.hpzlList">
									<option value='<s:property value="#hp.dmz"/>' 
										<s:if test="#request.dbZjxxb.hpzl==#hp.dmz">selected</s:if>>
										<s:property value="#hp.dmms1"/>
									</option>
								</s:iterator>
							</select>&nbsp;
						</td>
					</tr>
					<tr>
						<td colspan="7">&nbsp;&nbsp;
							<input class="dis" type="radio" name="dbZjxxb.bllx" value="1" checked="checked"/>&nbsp;本人办理&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input class="dis" type="radio" name="dbZjxxb.bllx" value="2"/>&nbsp;他人代办
						</td>
					</tr>
					<tr>
						<td colspan="7">
							<table class="idcardclass" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td style="width: 130px; border-left: 0px; border-top: 0px; border-bottom: 0px; border-right: #d2e9ff 1px solid; text-align: right; padding-right: 5px;">&nbsp;&nbsp;&nbsp;身份证明名称：</td>
									<td style="text-align: left; width:38%; border-left: 0px; border-top: 0px; border-bottom: 0px; border-right: 0px;">
										&nbsp;
										<s:select list="#{'A':'二代居民身份证','B':'组织机构代码证书',
										'C':'军官证','D':'士兵证','E':'军官离退休证','F':'境外人员身份证明',
										'G':'外交人员身份证明','H':'居民户口薄','J':'单位注销证明',
										'K':'居住暂住证明','L':'驻华机构证明','M':'临时居民身份证','N':'统一社会信用代码','P':'个体工商户营业执照注册号'}" theme="simple"
											id="dsrsfzmmc"
											listKey="key" listValue="value" name="dbZjxxb.dsrsfzmmc" value="#request.dbZjxxb.dsrsfzmmc" onchange="changedsrsfzmmc();"></s:select>&nbsp;<!-- <font style="color: red;">*</font> -->
										<span style="color: black;"><input type="checkbox" name="dsrchkreset" id="dsrchkreset" value="1"/>下宗业务是同一当事人</span>
									</td>
									<td style="text-align: right;width: 145px; border-left: 0px; border-top: 0px; border-bottom: 0px; border-right: #d2e9ff 1px solid ; display: <s:property value='#request.dbZjxxb.bllx eq "2"?"block":"none"'/>" >身份证明名称：</td>
									<td style="text-align: left; border-left: 0px; border-top: 0px; border-bottom: 0px; border-right: 0px; display: <s:property value='#request.dbZjxxb.bllx eq "2"?"block":"none"'/>">
										&nbsp;
										<s:select list="#{'A':'二代居民身份证','B':'组织机构代码证书',
										'C':'军官证','D':'士兵证','E':'军官离退休证','F':'境外人员身份证明',
										'G':'外交人员身份证明','H':'居民户口薄','J':'单位注销证明',
										'K':'居住暂住证明','L':'驻华机构证明','M':'临时居民身份证','N':'统一社会信用代码','P':'个体工商户营业执照注册号'}" theme="simple"
											id="dbrsfzmmc"
											listKey="key" listValue="value" name="dbZjxxb.dbrsfzmmc" value="#request.dbZjxxb.dbrsfzmmc" onchange="changedbrsfzmmc();"></s:select>&nbsp;<!-- <font style="color: red;">*</font> -->
										<span style="color: black;"><input type="checkbox" name="dbrchkreset" id="dsrchkreset" value="1"/>下宗业务是同一代办人</span>
									</td>
								</tr>
							</table>
						</td>
					</tr>		
					<tr>
						<td colspan="7">
							<div style="width: 48%; float: left;">
								<table id="dsrrtablesfz" class="edittable" border="0" cellpadding="0" cellspacing="0" align="center">
									<tr>
										<td colspan="3" style="border-top: 0px;">&nbsp;
											<strong>当事人</strong> 
											<s:if test="'查看' != #request.editType">
												<input type="button" id="sfz_b" style="cursor:pointer;"
													value="读 卡" onclick="return ReadIDCard('0');" class="bnt">
												&nbsp;&nbsp;<span id="ReadResult" style="color: red;">等待读取...</span>
											</s:if>
											<s:else>
												
											</s:else>
										</td>
									</tr>
									<tr>
										<td style="text-align: right;">姓名：</td>
										<td>
											&nbsp;<input type="text" class="disabled" readonly="readonly"
											size="30" id="dsrSfzCardname1" name="dbZjxxb.dsrSfzCardname1"
											value="${dbZjxxb.dsrSfzCardname1}" />
										</td>
										<td rowspan="4" style="text-align: center;">
											<s:if test="#request.editType != '查看'">
												<img id="sfzxpimgid" src="<%=request.getContextPath()%>/images/cp.gif" width="108" height="120" border="0">
											</s:if>
											<s:else>
												<s:if test="#request.dbZjxxb.dsrSfzCardphoto1 == null || #request.dbZjxxb.dsrSfzCardphoto1 == ''">
													<img id="sfzxpimgid" src="<%=request.getContextPath()%>/images/cp.gif" width="108" height="120" border="0">
												</s:if>
												<s:else>
													<img src="http://100.100.21.61/cmp_new/view_pic.asp?efid=${dbZjxxb.dsrSfzCardphoto1}" id="sfzxpimgid" width="108" height="120" border="0">
												</s:else>
											</s:else>
										</td>
									</tr>
									<tr>
										<td style="text-align: right;">性别：</td>
										<td>
											&nbsp;<input type="text" class="disabled" readonly="readonly"
											size="30" id="dsrSfzCardsex1" name="dbZjxxb.dsrSfzCardsex1"
											value="${dbZjxxb.dsrSfzCardsex1}" />
										</td>
									</tr>
									<tr>
										<td style="text-align: right;">身份证号：</td>
										<td>
											&nbsp;<input type="text" class="disabled" readonly="readonly"
											size="30" id="dsrSfzCardno1" name="dbZjxxb.dsrSfzCardno1"
											value="${dbZjxxb.dsrSfzCardno1}" />
										</td>
									</tr>
									<tr>
										<td style="text-align: right;">地址：</td>
										<td>
											&nbsp;<input type="text" class="disabled" readonly="readonly"
												size="30" id="dsrSfzCardaddress1" name="dbZjxxb.dsrSfzCardaddress1"
												value="${dbZjxxb.dsrSfzCardaddress1}" />
											<OBJECT classid="clsid:10946843-7507-44FE-ACE8-2B3483D179B7"
												codebase="CVR100.cab#version=3,0,3,3" id="CVR_IDCard"
												name="CVR_IDCard" width="108" height="110" align="middle"
												hspace="0" vspace="0" style="display: none;"></OBJECT>
										</td>
									</tr>
									<s:if test="#request.editType != '查看'">
										<tr>
											<td style="text-align: right;">&nbsp;照片路径：</td>
											<td colspan="2">
												&nbsp;<input type="file" name="file1" class="disabled" id="file1" title="请选择驾驶人身份证相片路径:C:\\ycszh_sfz1\\zp.bmp" style="width:320px;"/>
											</td>
										</tr>
									</s:if>
									<s:else>
									</s:else>
									<tr>
										<td colspan="3">&nbsp;
											当事人身份证明号码：
											<input type="text" class="disabled1" id="dsrsfzmhm"
												name="dbZjxxb.dsrsfzmhm" value="${dbZjxxb.dsrsfzmhm}" size="18"
												maxlength="20" onkeyup="clearspace(this)" onblur="clearallspace(this)" />&nbsp;<font style="color: red;">*</font>&nbsp;
											当事人姓名：
											<input type="text" class="disabled1" id="dsrxm"
												name="dbZjxxb.dsrxm" value="${dbZjxxb.dsrxm}" size="10"
												maxlength="100" onkeyup="clearspace(this)" onblur="clearallspace(this)" />&nbsp;<font style="color: red;">*</font>
										</td>
									</tr>
								</table>
								
								<!-- 组织机构证 -->
								<table id="dsrtablezzjgz" class="edittable" border="0" cellpadding="0" cellspacing="0" style="display: none;" align="center">
									<tr>
										<td colspan="2" style="border-top: 0px;">&nbsp;
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
											<input type="button" id="zzjgz" style="cursor:pointer;"
													value="获取信息" onclick="return btnGetOrgInfo_onclick()" class="bnt">
											&nbsp;&nbsp;<span id="ReadResultj" style="color: red;">等待读取...</span>
										</td>
									</tr>
									<tr>
										<td style="text-align: right;">组织机构证代码：</td>
										<td>
											&nbsp;<input type="text" readonly="readonly" class="disabled"
												id="dsrZzjgZh1" name="dbZjxxb.dsrZzjgZh1"
												value="${dbZjxxb.dsrZzjgZh1}" size="35" />
										</td>
									</tr>
									<tr>
										<td style="text-align: right;">单位法人：</td>
										<td>
											&nbsp;<input type="text" readonly="readonly" class="disabled"
												id="dsrZzjgFrdb1" name="dbZjxxb.dsrZzjgFrdb1"
												value="${dbZjxxb.dsrZzjgFrdb1}" size="35" />
										</td>
									</tr>
									<tr>
										<td style="text-align: right;">营业执照：</td>
										<td>
											&nbsp;<input type="text" readonly="readonly" class="disabled"
												id="dsrZzjgYyzz1" name="dbZjxxb.dsrZzjgYyzz1"
												value="${dbZjxxb.dsrZzjgYyzz1}" size="35" />
										</td>
									</tr>
									<tr>
										<td style="text-align: right;">单位名称：</td>
										<td>
											&nbsp;<input type="text" readonly="readonly" class="disabled"
													id="dsrZzjgDwmc1" name="dbZjxxb.dsrZzjgDwmc1"
													value="${dbZjxxb.dsrZzjgDwmc1}" size="35" />
											<object id="Videoui1" height="0" width="0"
												classid="clsid:1EED6A72-4FA5-4F8A-B561-D892C6AFE027"></object>
										</td>
									</tr>
									<tr>
										<td style="text-align: right;">&nbsp;年检日期：</td>
										<td>
											&nbsp;<input type="text" readonly="readonly" class="disabled"
													id="dsrZzjgNjrq1" name="dbZjxxb.dsrZzjgNjrq1"
													value="${dbZjxxb.dsrZzjgNjrq1}" size="35" />
										</td>
									</tr>
									<tr>
										<td style="text-align: right;">&nbsp;年检有效期：</td>
										<td>
											&nbsp;<input type="text" readonly="readonly" class="disabled"
													id="dsrZzjgNjyxq1" name="dbZjxxb.dsrZzjgNjyxq1"
													value="${dbZjxxb.dsrZzjgNjyxq1}" size="35" />
										</td>
									</tr>
									<tr>
										<td style="text-align: right;">&nbsp;地址：</td>
										<td>
											&nbsp;<input type="text" readonly="readonly" class="disabled"
												id="dsrZzjgDz1" name="dbZjxxb.dsrZzjgDz1"
												value="${dbZjxxb.dsrZzjgDz1}" size="40" />
										</td>
									</tr>
									<tr>
										<td colspan="2">&nbsp;
											当事人组织机构代码：
											<input type="text" class="disabled1" id="dsrzzjgdm"
												name="dbZjxxb.dsrsfzmhm" value="${dbZjxxb.dsrsfzmhm}" size="18"
												maxlength="20" onkeyup="clearspace(this)" onblur="clearallspace(this)" disabled="disabled" />&nbsp;<font style="color: red;">*</font>&nbsp;
											单位法人：
											<input type="text" class="disabled1" id="dsrdwmc"
												name="dbZjxxb.dsrxm" value="${dbZjxxb.dsrxm}" size="10"
												maxlength="20" onkeyup="clearspace(this)" onblur="clearallspace(this)" disabled="disabled" />&nbsp;<font style="color: red;">*</font>
										</td>
									</tr>
								</table>
							</div>
							
							<div style="width: 48%; float: right;">
								<table id="dbrtablesfz" class="edittable" border="0" cellpadding="0" cellspacing="0" align="center">
									<tr>
										<td colspan="3" style="border-top: 0px;">&nbsp;
											<strong>代办人</strong> 
											<s:if test="'查看' != #request.editType">
												<input type="button" id="sfz_b2" style="cursor:pointer;" disabled="disabled"
													value="读 卡" onclick="return ReadIDCard1('1');" class="bnt">
												&nbsp;&nbsp;<span id="ReadResult1" style="color: red;">等待读取...</span>
											</s:if>
											<s:else>
											</s:else>
										</td>
									</tr>
									<tr>
										<td style="text-align: right;">姓名：</td>
										<td>
											&nbsp;<input type="text" class="disabled" readonly="readonly"
											size="30" id="dbrSfzCardname1" name="dbZjxxb.dbrSfzCardname1"
											value="${dbZjxxb.dbrSfzCardname1}" />
										</td>
										<td rowspan="4" style="text-align: center;">
											<s:if test="#request.editType != '查看'">
												<img id="sfzxpimgid2" src="<%=request.getContextPath()%>/images/cp.gif" width="108" height="120" border="0">
											</s:if>
											<s:else>
												<s:if test="#request.dbZjxxb.dbrSfzCardphoto1 == null || #request.dbZjxxb.dbrSfzCardphoto1 == ''">
													<img id="sfzxpimgid2" src="<%=request.getContextPath()%>/images/cp.gif" width="108" height="120" border="0">
												</s:if>
												<s:else>
													<img src="http://100.100.21.61/cmp_new/view_pic.asp?efid=${dbZjxxb.dbrSfzCardphoto1}" id="sfzxpimgid2" width="108" height="120" border="0">
												</s:else>
											</s:else>
										</td>
									</tr>
									<tr>
										<td style="text-align: right;">性别：</td>
										<td>
											&nbsp;<input type="text" class="disabled" readonly="readonly"
											size="30" id="dbrSfzCardsex1" name="dbZjxxb.dbrSfzCardsex1"
											value="${dbZjxxb.dbrSfzCardsex1}" />
										</td>
									</tr>
									<tr>
										<td style="text-align: right;">身份证号：</td>
										<td>
											&nbsp;<input type="text" class="disabled" readonly="readonly"
											size="30" id="dbrSfzCardno1" name="dbZjxxb.dbrSfzCardno1"
											value="${dbZjxxb.dbrSfzCardno1}" />
										</td>
									</tr>
									<tr>
										<td style="text-align: right;">地址：</td>
										<td>
											&nbsp;<input type="text" class="disabled" readonly="readonly"
												size="30" id="dbrSfzCardaddress1" name="dbZjxxb.dbrSfzCardaddress1"
												value="${dbZjxxb.dbrSfzCardaddress1}" />
										</td>
									</tr>
									<s:if test="#request.editType != '查看'">
										<tr>
											<td style="text-align: right;">&nbsp;照片路径：</td>
											<td colspan="2">
												&nbsp;<input type="file" name="file2" class="disabled" id="file2" title="请选择驾驶人身份证相片路径:C:\\ycszh_sfz1\\zp.bmp" style="width:320px;"/>
											</td>
										</tr>
									</s:if>
									<s:else>
									</s:else>
									<tr>
										<td id="dbrsfzmtd" colspan="3" style="display: none;">&nbsp;
											代办人身份证明号码：
											<input type="text" class="disabled1" id="dbrsfzmhm"
												name="dbZjxxb.dbrsfzmhm" value="${dbZjxxb.dbrsfzmhm}" size="18"
												maxlength="20" onkeyup="clearspace(this)" onblur="clearallspace(this)" />&nbsp;<font style="color: red;">*</font>&nbsp;
											代办人姓名：
											<input type="text" class="disabled1" id="dbrxm"
												name="dbZjxxb.dbrxm" value="${dbZjxxb.dbrxm}" size="10"
												maxlength="20" onkeyup="clearspace(this)" onblur="clearallspace(this)" />&nbsp;<font style="color: red;">*</font>
										</td>
									</tr>
								</table>
								
								<!-- 组织机构证 -->
								<table id="dbrtablezzjgz" class="edittable" border="0" cellpadding="0" cellspacing="0" style="display: none;" align="center">
									<tr>
										<td colspan="2" style="border-top: 0px;">&nbsp;
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
											<input type="button" id="zzjgz2" style="cursor:pointer;" disabled="disabled"
													value="获取信息" onclick="return btnGetOrgInfo_onclick2()" class="bnt">
											&nbsp;&nbsp;<span id="ReadResultj2" style="color: red;">等待读取...</span>
										</td>
									</tr>
									<tr>
										<td style="text-align: right;">组织机构证代码：</td>
										<td>
											&nbsp;<input type="text" readonly="readonly" class="disabled"
												id="dbrZzjgZh1" name="dbZjxxb.dbrZzjgZh1"
												value="${dbZjxxb.dbrZzjgZh1}" size="35" />
										</td>
									</tr>
									<tr>
										<td style="text-align: right;">单位法人：</td>
										<td>
											&nbsp;<input type="text" readonly="readonly" class="disabled"
												id="dbrZzjgFrdb1" name="dbZjxxb.dbrZzjgFrdb1"
												value="${dbZjxxb.dbrZzjgFrdb1}" size="35" />
										</td>
									</tr>
									<tr>
										<td style="text-align: right;">营业执照：</td>
										<td>
											&nbsp;<input type="text" readonly="readonly" class="disabled"
												id="dbrZzjgYyzz1" name="dbZjxxb.dbrZzjgYyzz1"
												value="${dbZjxxb.dbrZzjgYyzz1}" size="35" />
										</td>
									</tr>
									<tr>
										<td style="text-align: right;">单位名称：</td>
										<td>
											&nbsp;<input type="text" readonly="readonly" class="disabled"
													id="dbrZzjgDwmc1" name="dbZjxxb.dbrZzjgDwmc1"
													value="${dbZjxxb.dbrZzjgDwmc1}" size="35" />
										</td>
									</tr>
									<tr>
										<td style="text-align: right;">&nbsp;年检日期：</td>
										<td>
											&nbsp;<input type="text" readonly="readonly" class="disabled"
													id="dbrZzjgNjrq1" name="dbZjxxb.dbrZzjgNjrq1"
													value="${dbZjxxb.dbrZzjgNjrq1}" size="35" />
										</td>
									</tr>
									<tr>
										<td style="text-align: right;">&nbsp;年检有效期：</td>
										<td>
											&nbsp;<input type="text" readonly="readonly" class="disabled"
													id="dbrZzjgNjyxq1" name="dbZjxxb.dbrZzjgNjyxq1"
													value="${dbZjxxb.dbrZzjgNjyxq1}" size="35" />
										</td>
									</tr>
									<tr>
										<td style="text-align: right;">&nbsp;地址：</td>
										<td>
											&nbsp;<input type="text" readonly="readonly" class="disabled"
												id="dbrZzjgDz1" name="dbZjxxb.dbrZzjgDz1"
												value="${dbZjxxb.dbrZzjgDz1}" size="40" />
										</td>
									</tr>
									<tr>
										<td id="tddbrzjgsfzm" colspan="2" style="display: none;">&nbsp;
											代办人组织机构代码：
											<input type="text" class="disabled1" id="dbrzzjgdm"
												name="dbZjxxb.dbrsfzmhm" value="${dbZjxxb.dbrsfzmhm}" size="18"
												maxlength="20" disabled="disabled" onkeyup="clearspace(this)" onblur="clearallspace(this)" />&nbsp;<font style="color: red;">*</font>&nbsp;
											单位法人：
											<input type="text" class="disabled1" id="dbrdwmc"
												name="dbZjxxb.dbrxm" value="${dbZjxxb.dbrxm}" size="10"
												maxlength="20" disabled="disabled" onkeyup="clearspace(this)" onblur="clearallspace(this)" />&nbsp;<font style="color: red;">*</font>
										</td>
									</tr>
								</table>
							</div>
						</td>
					</tr>
					
					<tr>
						<td colspan="7">
							<table class="idcardclass" width="100%" border="0" cellpadding="0" cellspacing="0">
								
								<tr>
									<td style="text-align: left; width: 50%; border-left: 0px; border-top: 0px; border-bottom: 0px; border-right: #d2e9ff 0px solid">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当事人手机号码：
										<input type="text" class="disabled1" id="dsrLxdh"
											name="dbZjxxb.dsrLxdh" value="${dbZjxxb.dsrLxdh}" size="18"
											maxlength="20" onkeyup="clearspace(this)" onblur="clearallspace(this);"/>&nbsp;<font style="color: red;">*</font>&nbsp;
										
										&nbsp;&nbsp;&nbsp;&nbsp;联系地址：
										<input type="text" class="disabled1" id="dsrLxdz"
											name="dbZjxxb.dsrLxdz" value="${dbZjxxb.dsrLxdz}" size="60"
											onkeyup="clearspace(this)" onblur="clearallspace(this)"/>&nbsp;<font style="color: red;">*</font>
								
									</td>
									
								</tr>
								<s:if test="#request.pageforward == 'tbyw'"> 
									<tr>
										<td style="text-align: left; width: 50%; border-left: 0px; border-top: 0px; border-bottom: 0px; border-right: #d2e9ff 0px solid">
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;退办原因：
											<input type="text" class="disabled1" id="tbyy"
												name="dbZjxxb.tbyy" value="${dbZjxxb.tbyy}" size="110"
												maxlength="300" />&nbsp;<font style="color: red;">*</font>&nbsp;
											
											<input type="hidden" class="disabled1" id="tbr"
												name="dbZjxxb.tbr" value="${userbean.name}" size="110"
												maxlength="300" />&nbsp;
											
										</td>
										
									</tr>
								</s:if>
								
							</table>
						</td>
					</tr>
					
				</table>
				<br/>
				<table class="edittable" border="0" cellpadding="0" cellspacing="0" align="center">
				  <tr>
					<td height="33" colspan="4"><strong>摄像头及高拍仪(当事人现场照片及身份证明扫描照片)</strong>
					<s:if test="#request.editType != '查看'">
						&nbsp;<input type="button" style="cursor:pointer;" name="zp" id="zp" value="抓拍图片" class="bnt" onclick="vide();">
						 &nbsp;&nbsp;<span id="ReadResult2" style="color: red;">等待抓拍...</span>
						 <input type="hidden" name="flags" id="flags" value="0">
						   <strong>备注：</strong><span>当图像没有加载请点击“切换视频”按钮切换。</span>
					</s:if>
					<s:else>
					</s:else>
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
							<object id="camera" classid="clsid:9C60C50B-835C-4F7C-A4AB-02C0139E807A"></object>
						</td>
						<td style="width: 25%" height="169" align="center">
							<object id="ScanCtrl" classid="clsid:090457CB-DF21-41EB-84BB-39AAFC9E271A"  CODEBASE="*.cab#version=1,0,0,1" width="220" height="180"></object>
						</td>
						<td style="width: 25%" align="center">
							<a id="img01a" href="#" title="摄像头图片"><img src="<%=request.getContextPath()%>/images/cp.gif" id="img01" width="210" height="175" border="0"></a>
						</td>
						<td style="width: 25%" align="center">
							<a id="img02a" href="" title="高拍仪图片"><img src="<%=request.getContextPath()%>/images/cp.gif" id="img02" width="210" height="175" border="0"></a>
						</td>
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
						<td style="width: 25%" align="center">
							<s:if test="#request.dbZjxxb.dsrSfzSxtZp == null || #request.dbZjxxb.dsrSfzSxtZp == ''">
								<a id="img01a" href="#" title="摄像头图片"><img id="sfzxpimgid2" src="<%=request.getContextPath()%>/images/cp.gif" width="210" height="175" border="0"></a>
							</s:if>
							<s:else>
								<a id="img01a" href="http://100.100.21.61/cmp_new/view_pic.asp?efid=${dbZjxxb.dsrSfzSxtZp}" title="摄像头图片"><img src="http://100.100.21.61/cmp_new/view_pic.asp?efid=${dbZjxxb.dsrSfzSxtZp}" id="img01" width="210" height="175" border="0"></a>
							</s:else>
						</td>
						<td style="width: 25%" align="center">
							<s:if test="#request.dbZjxxb.dsrSfzGpyZp == null || #request.dbZjxxb.dsrSfzGpyZp == ''">
								<a id="img02a" href="#" title="高拍仪图片"><img id="sfzxpimgid2" src="<%=request.getContextPath()%>/images/cp.gif" width="210" height="175" border="0"></a>
							</s:if>
							<s:else>
								<a id="img02a" href="http://100.100.21.61/cmp_new/view_pic.asp?efid=${dbZjxxb.dsrSfzGpyZp}" title="高拍仪图片"><img src="http://100.100.21.61/cmp_new/view_pic.asp?efid=${dbZjxxb.dsrSfzGpyZp}" id="img02" width="210" height="175" border="0"></a>
							</s:else>
						</td>
						<td style="center;border:3px solid #D2E9FF;width: 50%;" colspan="2" valign="top">
							<table style="width:100%;">
								<tr>
									<td style="text-align: right;width: 25%;">
										经办人：
									</td>
									<td style="text-align: left;width: 75%;">
										&nbsp;
										${dbZjxxb.lrrxm}
									</td>
								</tr>
								<tr>
									<td style="text-align: right;width: 25%;">
										经办人部门：
									</td>
									<td style="text-align: left;width: 75%;">
										&nbsp;
										${dbZjxxb.lrbmmc}
									</td>
								</tr>
								<tr>
									<td style="text-align: right;width: 25%;">
										经办时间：
									</td>
									<td style="text-align: left;width: 75%;">
										&nbsp;
										<s:date name="#request.dbZjxxb.lrsj" format="yyyy-MM-dd HH:mm:ss"/>
									</td>
								</tr>
							</table>
						</td>
						</tr>
					</s:else>
				</table>
			</div>
			
			<div id="tableid1" class="tablist" style="text-align: center;height: 300px">
				<!-- 流水信息 -->
				<table border="1" cellpadding="0" cellspacing="0" id="tabCc1"  class="edittable">
					<tr>
						<td style="text-align:right;" width="95"><font style="color:red;">*</font>所有人：
						</td>
						<td class="trs" style="text-align: left;">&nbsp;<input type="text" class="disabled" id="syr" value="" name="syr" size="17" maxlength="30"/>
						</td>
						<td style="text-align:right;">
							<font style="color:red;">*</font>车辆品牌：
						</td>
						<td class="trs" style="text-align: left;">&nbsp;<input type="text" class="disabled" id="clpp1" value="" name="clpp1" size="17" maxlength="30"/>
						</td>
						<td style="text-align:right;">
							<font style="color:red;">*</font>车辆型号： 
						</td>
						<td class="trs" style="text-align: left;">&nbsp;<input type="text" class="disabled" id="clxh" value="" name="clxh" size="20" maxlength="100"/>
						</td>
					</tr>
					<tr>
						<td style="text-align:right;"><font style="color:red;">*</font>车辆识别代号：</td>
						<td class="trs" style="text-align: left;" colspan="5">
						&nbsp;<input type="text" class="disabled" id="clsbdh" value="" name="clsbdh" size="17" maxlength="50"/></td>
					</tr>
				</table>
			</div>
			
			<div id="tableid2" class="tablist" style="text-align: center;height: 420px">
				<!-- 机动车信息 -->
				<table border="1" cellpadding="0" cellspacing="0" id="tabCc3" class="edittable">
					<tr>
						<td style="text-align:right;"><font style="color:red;">*</font>车辆识别代号：</td>
						<td class="trs" colspan="3" style="text-align: left;">&nbsp;<input type="text"  class="disabled" id="vehi_clsbdh" value="" name="vehi_clsbdh" size="30" maxlength="100"/>&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;
						</td>
						<td>
						</td>
						<td >
						</td>
					</tr>
					<tr>
						<td style="text-align:right;"><font style="color:red;">*</font>身份证明号码：
						</td>
						<td class="trs" colspan="3" style="text-align: left;">&nbsp;<input type="text" class="disabled" id="vehi_sfzmhm" value="" name="vehi_sfzmhm" size="30" maxlength="32"/>&nbsp;&nbsp;&nbsp;&nbsp;
							 &nbsp;
						</td>
						<td style="text-align:right;">
							车主名称：
						</td>
						<td class="trs" style="text-align: left;">&nbsp;<input type="text" class="disabled" id="vehi_czmc" value="" name="vehi_czmc" size="20" maxlength="16"/>
						</td>
					</tr>
					<tr>
						<td style="text-align:right;"><font style="color:red;">*</font>车辆类型：
						</td>
						<td class="trs" colspan="3" style="text-align: left;">&nbsp;<input type="text" class="disabled" id="vehi_cllx" value="" name="vehi_cllx" size="30" maxlength="32"/>&nbsp;&nbsp;&nbsp;&nbsp;
							 &nbsp;
						</td>
						<td style="text-align:right;">
							厂牌：
						</td>
						<td class="trs" style="text-align: left;">&nbsp;<input type="text" class="disabled" id="vehi_cp" value="" name="vehi_cp" size="20" maxlength="16"/>
						</td>
					</tr>
				</table>
			</div>
			
			<div id="tableid3" class="tablist" style="text-align: center;height: 420px">
				<!-- 证件采集信息 -->
				<table border="1" cellpadding="0" cellspacing="0" id="tabCc3" class="edittable">
					<tr>
						<td style="text-align:right;">流水号：</td>
						<td class="trs" colspan="3" style="text-align: left;">&nbsp;<input type="text"  class="disabled" id="zjxxb_lsh" value="" name="zjxxb_lsh" size="30" maxlength="100"/>&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;
						</td>
						<td>
						</td>
						<td >
						</td>
					</tr>
					<tr>
						<td style="text-align:right;">当事人身份证明号码：
						</td>
						<td class="trs" colspan="3" style="text-align: left;">&nbsp;<input type="text" class="disabled" id="zjxxb_dsrsfzmhm" value="" name="zjxxb_dsrsfzmhm" size="30" maxlength="32"/>&nbsp;&nbsp;&nbsp;&nbsp;
							 &nbsp;
						</td>
						<td style="text-align:right;">
							当事人姓名：
						</td>
						<td class="trs" style="text-align: left;">&nbsp;<input type="text" class="disabled" id="zjxxb_dsrxm" value="" name="zjxxb_dsrxm" size="20" maxlength="16"/>
						</td>
					</tr>
					<tr>
						<td style="text-align:right;">当事人联系电话：
						</td>
						<td class="trs" colspan="3" style="text-align: left;">&nbsp;<input type="text" class="disabled" id="zjxxb_dsrlxdh" value="" name="zjxxb_dsrlxdh" size="30" maxlength="32"/>&nbsp;&nbsp;&nbsp;&nbsp;
							 &nbsp;
						</td>
						<td style="text-align:right;">
							当事人联系地址：
						</td>
						<td class="trs" style="text-align: left;">&nbsp;<input type="text" class="disabled" id="zjxxb_dsrlxdz" value="" name="zjxxb_dsrlxdz" size="20" maxlength="16"/>
						</td>
					</tr>
					<tr>
						<td style="text-align:right;">指标类型：
						</td>
						<td class="trs" colspan="3" style="text-align: left;">&nbsp;<select id="zjxxb_zblx" name="zjxxb_zblx" style="width: 150px;">
												<option value="">===请选择===</option>
												<s:if test="#request.zblxList != null">
													<s:iterator var="cllx" value="#request.zblxList">
														<option value="<s:property value="#cllx[0]" />" ><s:property value="#cllx[1]" /></option>
													</s:iterator>
												</s:if>
											</select>
						</td>
						<td style="text-align:right;">
							指标号：
						</td>
						<td class="trs" style="text-align: left;">&nbsp;<input type="text" class="disabled" id="zjxxb_zbh" value="" name="zjxxb_zbh" size="20" maxlength="16"/>
						</td>
					</tr>
					<tr>
						<td style="text-align:right;">统一版流水号：
						</td>
						<td class="trs" colspan="3" style="text-align: left;">&nbsp;<input type="text" class="disabled" id="zjxxb_tyblsh" value="" name="zjxxb_tyblsh" size="30" maxlength="32"/>&nbsp;&nbsp;&nbsp;&nbsp;
							 &nbsp;
						</td>
						<td style="text-align:right;">
							车辆识别代号：
						</td>
						<td class="trs" style="text-align: left;">&nbsp;<input type="text" class="disabled" id="zjxxb_clsbdh" value="" name="zjxxb_clsbdh" size="20" maxlength="16"/>
						</td>
					</tr>
				</table>
			</div>
			
			</form>
			<br/>
			<div id="tablediv" style="text-align: center;">
				<table class="edittable" border="0" cellpadding="0" cellspacing="0" align="center" style="border: 1px solid #4FB3D5">
					<tr>
						<td colspan="4" height="50" align="center"
							style="text-align: center">
							<s:if test="#request.editType != '查看'">
								<input type="button" style="cursor:pointer;" onclick="javascript:subzchfforms();" value="保 存" class="bnt" />
								<input type="button" style="cursor:pointer;" onclick="javascript:window.history.go(-1);" value="返 回" class="bnt" />
							</s:if>
							<s:else>
								<input type="button" style="cursor:pointer;" onclick="javascript:window.close();" value="关 闭" class="bnt" />
							</s:else>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
				
	<div class="moveTxt" id="dangport" style="background-color: #EDEDED">
		<div style="text-align: center;">
			<font color="blue">温馨提示：以下蓝色字体的业务类型需通过调取预受理流水号方式来受理，其他业务可输入车牌号码和号牌种类来受理</font>
		</div>
		<div id="ywlxdiv" style="width: 715px; text-align:center; padding-left: 5px; font-size: 12px;">
			
		</div>
		<div style="padding-top: 15px; padding-bottom: 15px; text-align: center; width: 715px;">
			<input type="button" class="btn2" style="width: 60px;" onclick="closeDiv('dangport')" value="关&nbsp;&nbsp;闭"/>
			<input type="button" class="btn2" style="width: 60px;" onclick="clearywlxandyy()" value="重&nbsp;&nbsp;置"/>
		</div>
	</div>
</body>
</html>