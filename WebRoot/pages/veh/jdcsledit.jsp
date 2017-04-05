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
	
	.box{width:900px;}
	.box ul li{list-style:none;float:left;width:190px;height:200px;float:left}

	
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
		var list = getYwlx('', '', 'JDCYWSL', 'VEH_YWLX');
	   	var data = createYwlx(list);
	    $("#ywlxdiv").html(data.join(''));
		
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
		$("#shjg").removeAttr("disabled");
		if(lsh == '1'){
			$("#hphm").attr("readonly", "readonly");
			$("#hpzl").attr("disabled", "disabled");
			$("#hpzl").val('${ptdo.hpzl}');
			//alert('${ptdo.hpzl}');
	    	var ywlx = '${ptdo.ywlx}';
	    	var ywyy = '${ptdo.ywyy}';
	    	var bz = '${ptdo.bz}';
	    	var ishandle = '${ptdo.localIshandle}';
	    	var type = '${type}';
	    	var ptdolsh = '${ptdo.lsh}';
	    	if(type == 'out'){
	    		if(ptdolsh == null || ptdolsh == ""){
	    			alert("该流水号不存在!");
	    		}else{
	    			if(ywlx != 'D'){
		    			alert("该业务类型不能通过此方式受理!");
		    		}else{
		    			if(ywyy != 'P' && ywyy != 'A' && ywyy != 'J'){
		    				alert("该业务类型不能通过此方式受理!");
		    			}
		    		}
	    		}
	    		
	    	}else if(type == 'in'){
	    		if(ishandle == 1){
		    		var localOpertime = '${ptdo.localOpertime}';
		    		var localName = '${ptdo.localName}';
		    		alert("此流水号在"+localOpertime+"时间,被"+localName+"已受理!");
		    		setTimeout(
						function(){
							var obj2 = window.document.getElementById("ScanCtrl");
							obj2.StartPreview();
						},1000
					);
					return false;
		    	}
		    	if(ywlx == "A" && ywyy == "A"){
		    		alert("该流水号是新车登记业务，不能在本模块操作!");
		    		clearform2();
		    	}
		    	if(ywlx == "B" && (ywyy == "B" || ywyy == "C") && (bz == null || bz == "") ){
		    		alert("该流水号是转移登记业务，不能在本模块操作!");
		    		clearform2();
		    	}
	    	}
	    	if(ywlx == "B" && bz != null && bz != ""){
	    		ywyy = "";
	    		var ywlxs = bz.split(':');
	    		ywlx = ywlxs[0];
	    		ywyy = ywlxs[1];
	    	}
	    	setYwlxAndYwyy(ywlx, ywyy);
	    	
		}
		if ('查看' == edittype) {
			var dydjlsh = '${dbZjxxb.lsh}';
			var first = dydjlsh.substring(0,1);
			if(first == "D"){
				$("#dydjli").show();
			}
			var dydjywlx = '${dydjYwsbspb.ywlx}';
			var dydjsqlx = '${dydjYwsbspb.sqlx}';
			var dydjbllx = '${dydjYwsbspb.sbzt}';
			$("input[name='dydjYwsbspb.ywlx'][value='"+dydjywlx+"']").attr("checked", true);
			$("input[name='dydjYwsbspb.sqlx'][value='"+dydjsqlx+"']").attr("checked", true);
			if(dydjbllx == '6'){
				$("input[name='bllx'][value='0']").attr("checked", true);
			}else{
				$("input[name='bllx'][value='1']").attr("checked", true);
			}
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
	
	function getGpyMessage(index,src){
		 $("#gpyimg"+index+"a").lightBox();
		 var obj = window.document.getElementById("ScanCtrl");
		 src = src.substr(2,src.length);
		 var path = "C:\\\\"+src;
		 obj.Scan(path);
		 setTimeout(
					function(){
						document.all["gpyimg"+index].src=path;
						document.all["gpyimg"+index+"a"].href=path;
						var fileval = $("#gpyfile"+index).val();
						if(fileval != path){
							$("#gpyfile"+index+"").val(path);
							var sd=inputtext(path,document.getElementById("gpyfile"+index));
						}
						$("#chfile"+index).val("1");
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
			
			// 分别给颜色赋值
			var csyss = '${ptdo.csys}';
			var csys = csyss.split("");
			
			if(csys.length > 2){
				var csysItem = document.getElementsByName("ptdo.csys");
				csysItem[0].value = csys[0];
				csysItem[1].value = csys[1];
				csysItem[2].value = csys[2];
			}
		}
	});
	
			//审核
			function shsubmit(){
				//验证审核流水号
				var shlshck = checknotnull2(document.getElementById("shlsh"),'请填写审核流水号，即六合一平台业务流水号',0);
				if (shlshck != "true") {
					return false;
				}
				var cjid = '${dbZjxxb.id}';
				var shlsh = $("#shlsh").val();
				var hphm = '${dbZjxxb.hphm}';
				var hpzl = '${dbZjxxb.hpzl}';
				var shjg = $("#shjg").val();
				$.ajax({
					cache:false,
					async:false,
					url:'<%=request.getContextPath()%>/veh/veh_vehShenheCheck.action',
					type:'post',
					data:{"shlsh":shlsh,"hphm":hphm,"hpzl":hpzl},
					dataType:'json',
					error : function() {
						alert("调用六合一机动车流水查询接口异常!");
					},
					success: function(result){
						var message = result+"";
						if(message.indexOf('异常信息') == -1){
							if("0" == result){
								alert("审核失败,调用六合一机动车流水查询接口失败!");
								return false;
							}else if("1" == result){
								$.ajax({
									cache:false,
									async:false,
									url:'<%=request.getContextPath()%>/veh/veh_vehShenhe.action',
									type:'post',
									data:{"shlsh":shlsh, "shjg":shjg, "cjid":cjid,"hphm":hphm,"hpzl":hpzl},
									dataType:'json',
									success: function(result){
										var message = result+"";
										if(message.indexOf('异常信息') == -1){
											if("1" == result){
												alert("审核成功!");
												opener.updatezt(cjid,shjg);
												window.close();
											}else if("2" == result){
												alert("审核失败,调用六合一机动车收费写入接口异常!");
												return false;
											}
										}else{
											exception(message);
										}
									}
								});
							}else if("2" == result){
								alert("审核失败,未查到审核流水号信息!");
								return false;
							}else if("3" == result){
								alert("审核失败,审核流水号中的身车牌号码或车牌种类与采集信息不符!");
								return false;
							}
						}else{
							exception(message);
						}
					}
				});
			}
</script>

</head>
<body onload="contentLoad();">
	<DIV id=admin_title>
		<DIV class=logo>
			<IMG src="<%=request.getContextPath()%>/images/frame_19.gif">
		</DIV>
		<DIV class=title>
			${editType }机动车业务受理${editTypeXx }信息&nbsp;<A
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
				<li onclick="settab(this,1)" style="width: 80px;">登记信息</li>
				<li onclick="settab(this,2)" style="width: 80px;">技术参数</li>
				<li onclick="settab(this,3)" id="dydjli" style="width: 80px; display: none;">抵押登记</li>
				<li style="text-align: right; width: 320px;" class="last" id="lshli">
					流水号：<input class="dis" type="text" name="lsh" id="lsh" onkeyup="clearspace(this)" onblur="clearallspace(this)"  value="${request.lshval}" size="20"/>&nbsp;&nbsp;&nbsp;&nbsp;
					<s:if test="'查看' != #request.editType">
						<input type="button" id="sfz_b" style="cursor:pointer;" onclick="getDengjiAndJishu()" value="查询" class="bnt">
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
			<input type="hidden" name="lshval" id="lshval" value="${request.lshval}"/>
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
			<input type="hidden" name="dbZjxxb.hdfs" id="hdfs" value=""/>
			<input type="hidden" name="dbZjxxb.gzh" id="gzh" value=""/>
			<input type="hidden" name="dbZjxxb.clsbdh" id="clsbdhval" value=""/>
			<input type="hidden" name="alterinfo" id="alterinfo" value=""/>
			<input type="hidden" name="filecount" id="filecount" value=""/>
			<div id="tableid0" class="tablist block">
				<table class="edittable" border="0" cellpadding="0" cellspacing="0" align="center">
					<tr>
						<td colspan="7" id="slywlx" style="padding-left: 15px;">
							<s:if test="#request.editType != '查看'">
								业务类型：<input type="text" id="ywlxhtml" readonly="readonly" size="115" style="margin:5px 0 0 0;" /> &nbsp;&nbsp;<input type="button" id="btnsplx" class="bnt" style="cursor: pointer; margin:0 0 5px 0;" value="选&nbsp;择"/>
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
								<%-- <s:iterator value="#request.hpzlList" var="hp">
									<option value="${hp.dmz}">${hp.dmms1}</option>
								</s:iterator> --%>
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
										<span style="color: black;"><%--<input type="checkbox" name="dbrchkreset" id="dsrchkreset" value="1"/>下宗业务是同一代办人--%></span>
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
												name="dbZjxxb.dsrsfzmhm" value="${dbZjxxb.dsrsfzmhm}"
												onkeyup="clearspace(this)" onblur="clearallspace(this)" />&nbsp;<font style="color: red;">*</font>&nbsp;
											当事人姓名：
											<input type="text" class="disabled1" id="dsrxm"
												name="dbZjxxb.dsrxm" value="${dbZjxxb.dsrxm}" size="10"
												onkeyup="clearspace(this)" onblur="clearallspace(this)" />&nbsp;<font style="color: red;">*</font>
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
												name="dbZjxxb.dsrsfzmhm" value="${dbZjxxb.dsrsfzmhm}"
												onkeyup="clearspace(this)" onblur="clearallspace(this)" disabled="disabled" />&nbsp;<font style="color: red;">*</font>&nbsp;
											单位法人：
											<input type="text" class="disabled1" id="dsrdwmc"
												name="dbZjxxb.dsrxm" value="${dbZjxxb.dsrxm}" size="10"
												onkeyup="clearspace(this)" onblur="clearallspace(this)" disabled="disabled" />&nbsp;<font style="color: red;">*</font>
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
												disabled="disabled" onkeyup="clearspace(this)" onblur="clearallspace(this)" />&nbsp;<font style="color: red;">*</font>
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
						<s:if test="#request.editType2 == '审核'">
						<tr>
							<td colspan="3" align="center">
								审核流水号：<input type="text" id="shlsh"/>&nbsp;<font style="color: red;">*</font>&nbsp;&nbsp;&nbsp;&nbsp;
								审核结果：
									<s:select list="#{'1':'审核通过','2':'审核不通过'}" theme="simple"
											id="shjg"
											listKey="key" listValue="value" ></s:select>&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="button" style="cursor:pointer;" onclick="javascript:shsubmit();" value="审核确认" class="bnt3" />
							</td>
						</tr>				
					</s:if>
					</s:else>
				</table>
		    <!-- 新加动态拍摄区域 -->
			<div id="psdiv" style="display:none;">
				<table width="900px" border="0" cellpadding="0" cellspacing="0" >
					<tr width="900px">
						<td id="pztd" width="900px">
						</td>
					</tr>
				</table>
			</div>
			</div>
			
			<div id="tableid1" class="tablist" style="text-align: center;height: 300px">
				<!-- 登记参数 -->
				<table border="1" cellpadding="0" cellspacing="0" id="tabCc1"  class="edittable">
					<tr>
						<td style="text-align:right;" width="95"><font style="color:red;">*</font>身份证明名称：
						</td>
						<td class="trs" style="text-align: left;">&nbsp;<select id="ptdosfzmmc" name="ptdo.sfzmmc">
									<option value="">===请选择===</option>
									<s:if test="#request.sfzmList != null">
										<s:iterator var="sfzm" value="#request.sfzmList">
											<option value="<s:property value="#sfzm.dmz" />" <s:if test="#request.ptdo.sfzmmc == #sfzm.dmz">selected="selected"</s:if>><s:property value="#sfzm.dmsm1" /></option>
										</s:iterator>
									</s:if>
								</select>
						</td>
						<td style="text-align:right;">
							<font style="color:red;">*</font>身份证明号码：
						</td>
						<td class="trs" style="text-align: left;">&nbsp;<input type="text" class="disabled" id="ptdosfzmhm" value="${ptdo.sfzmhm}" name="ptdo.sfzmhm" size="17" maxlength="30"/>
						</td>
						<td style="text-align:right;">
							<font style="color:red;">*</font>居住/暂住证号： 
						</td>
						<td class="trs" style="text-align: left;">&nbsp;<input type="text" class="disabled" id="ptdozzz" value="${ptdo.zzz}" name="ptdo.zzz" size="20" maxlength="100"/>
						</td>
					</tr>
					<tr>
						<td style="text-align:right;"><font style="color:red;">*</font>姓名/名称：</td>
						<td class="trs" style="text-align: left;">
						&nbsp;<input type="text" class="disabled" id="ptdosyr" value="${ptdo.syr}" name="ptdo.syr" size="17" maxlength="100"/></td>
					
						<td style="text-align:right;"><font style="color:red;">*</font>移动电话：</td>
						<td class="trs" style="text-align: left;">&nbsp;<input type="text" class="disabled" id="ptdolxdh" value="${ptdo.lxdh}" name="ptdo.lxdh" size="17" maxlength="50"/></td>
					
						<td style="text-align:right;">电子邮箱：</td>
						<td class="trs" style="text-align: left;">&nbsp;<input type="text" class="disabled" id="ptdodzyx" value="${ptdo.dzyx}" name="ptdo.dzyx" size="20" maxlength="30"/></td>
					</tr>
					
					<tr>
						<td style="text-align:right;"><font style="color:red;">*</font>住所地址：</td>
						<td class="trs" colspan="5" style="text-align: left;">&nbsp;<select id="yzbm7" name="yzbm7">
															<option value="">===请选择===</option>
															<s:iterator var="yzbm" value="#request.yzbmList">
																<option value="<s:property value="#yzbm.dmz" />" <s:if test="#request.ptdo.zsxzqh == #yzbm.dmz">selected="selected"</s:if>><s:property value="#yzbm.dmsm1" /></option>
															</s:iterator>
														</select>
							<input type="text" class="disabled"  id="ptdozsxxdz" 
							<s:if test='#ptdo == null'>
								value = "广东省深圳市"
							</s:if>
							<s:else>
								value = "<s:property value="#ptdo.zsxxdz" />"
							</s:else> 
							name="ptdo.zsxxdz" size="50" maxlength="100"/>
						</td>
					</tr>
					<tr>
						
						<td style="text-align:right;"><font style="color:red;">*</font>邮寄地址：</td>
						<td class="trs" colspan="3" style="text-align: left;">&nbsp;<select id="yzbm6" name="yzbm6">
															<option value="">===请选择===</option>
															<s:iterator var="yzbm" value="#request.yzbmList">
																<option value="<s:property value="#yzbm.dmz" />" <s:if test="#request.ptdo.yzbm1 == #yzbm.dmz">selected="selected"</s:if>><s:property value="#yzbm.dmsm1" /></option>
															</s:iterator>
														</select>
							<input type="text" class="disabled" id="ptdozzxxdz" 
							<s:if test='#ptdo == null'>
								value = "广东省深圳市"
							</s:if>
							<s:else>
								value = "<s:property value="#ptdo.zzxxdz" />"
							</s:else> 
							 name="ptdo.zzxxdz" size="50" maxlength="100"  />
						</td>
						<td style="text-align:right;">
							邮政编码：
						</td>
						<td style="text-align: left;">
							<input type="text" class="disabled" id="ptdoyzbm1" value="${ptdo.yzbm1}" name="ptdo.yzbm1" size="10" maxlength="10"/>
						</td>
					</tr>
					<tr>
						<td style="text-align:right;"><font style="color:red;">*</font>使用性质：</td>
						<td style="text-align: left;">&nbsp;<select id="ptdosyxz" name="ptdo.syxz">
									<option value="">===请选择===</option>
									<s:if test="#request.syxzList != null">
										<s:iterator var="syxz" value="#request.syxzList">
											<option value="<s:property value="#syxz.dmz" />" <s:if test="#request.ptdo.syxz == #syxz.dmz">selected="selected"</s:if>><s:property value="#syxz.dmsm1" /></option>
										</s:iterator>
									</s:if>
								</select>
						</td>
						
						<td style="text-align:right;"><font style="color:red;">*</font>所有权：</td>
						<td style="text-align: left;">&nbsp;<select id="ptdosyq" name="ptdo.syq">
									<option value="">===请选择===</option>
									<s:if test="#request.syqList != null">
										<s:iterator var="syq" value="#request.syqList">
											<option value="<s:property value="#syq.dmz" />" <s:if test="#request.ptdo.syq == #syq.dmz">selected="selected"</s:if>><s:property value="#syq.dmsm1" /></option>
										</s:iterator>
									</s:if>
								</select>
						</td>
						
						<td style="text-align:right;"><font style="color:red;">*</font>获得方式：</td>
						<td style="text-align: left;">&nbsp;<select id="ptdohdfs" name="ptdo.hdfs">
									<option value="">===请选择===</option>
									<s:if test="#request.hdfsList != null">
										<s:iterator var="hdfs" value="#request.hdfsList">
											<option value="<s:property value="#hdfs.dmz" />" <s:if test="#request.ptdo.hdfs == #hdfs.dmz">selected="selected"</s:if>><s:property value="#hdfs.dmsm1" /></option>
										</s:iterator>
									</s:if>
								</select>
						</td>
					</tr>
					<tr>
						<td style="text-align:right;"><font style="color:red;">*</font>行政区划：</td>
						<td class="trs" style="text-align: left;">&nbsp;<SELECT id="ptdozsxzqh" name="ptdo.zsxzqh">
												<option value="">===请选择===</option>
												<s:if test="#request.yzbmList != null">
													<s:iterator var="zsxzqh" value="#request.yzbmList">
														<option value="<s:property value="#zsxzqh.dmz" />" <s:if test="#request.ptdo.zsxzqh == #zsxzqh.dmz">selected="selected"</s:if>><s:property value="#zsxzqh.dmsm1" /></option>
													</s:iterator>
												</s:if>
											</SELECT>
						</td>
						
						<td style="text-align:right;"><font style="color:red;">*</font>号牌种类：</td>
						<td class="trs" style="text-align: left;">&nbsp;<SELECT id="ptdohpzl" name="ptdo.hpzl">
												<option value="">===请选择===</option>
												<s:if test="#request.hpzlLists != null">
													<s:iterator var="hpzl" value="#request.hpzlLists">
														<option value="<s:property value="#hpzl.dmz" />" <s:if test="#request.ptdo.hpzl == #hpzl.dmz">selected="selected"</s:if>><s:property value="#hpzl.dmsm1" /></option>
													</s:iterator>
												</s:if>
											</SELECT>
						</td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td style="text-align:right;">保险生效日期：</td>
						<td class="trs" style="text-align: left;">&nbsp;<input type="text" class="disabled" id="ptdosxrq" value="${fn:substring(ptdo.sxrq,0,10)}" name="ptdo.sxrq" size="10" maxlength="20" />
						</td>
						
						<td style="text-align:right;">保险终止日期：</td>
						<td class="trs" style="text-align: left;">&nbsp;<input type="text" class="disabled" id="ptdozzrq" value="${fn:substring(ptdo.zzrq,0,10)}" name="ptdo.zzrq" size="10" maxlength="20"/>
						</td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td style="text-align: right;" style="width: 876px height: 21px;">&nbsp;
							<font style="color:red;">*</font>检验日期：
						</td>
						<td align="left" style="width: 196px height: 21px;text-align: left;">&nbsp;<input name="ptdo.yxqz" id="ptdoyxqz" class="disabled" value="${fn:substring(ptdo.yxqz,0,10)}" size="10" maxlength="20" type="text"/>
						</td>
						<td style="text-align: right;" style="width: 680px height: 21px;">&nbsp;
							承检单位：
						</td>
						<td style="width: 164px height: 21px;text-align: left;" colspan="3" align="left">&nbsp;<select name="ptdo.cjdw" id="ptdocjdw">
								<option value="">===请选择===</option>
								<s:if test="#request.cjdwList != null">
									<s:iterator var="cjdw" value="#request.cjdwList">
										<option value="<s:property value="#cjdw.dmz" />" <s:if test="#request.ptdo.cjdw == #cjdw.dmz">selected="selected"</s:if>><s:property value="#cjdw.dmsm1" /></option>
									</s:iterator>
								</s:if>
							</select>
						</td>
					</tr>
					<tr>
						<td style="text-align: right;" style="width: 876px height: 21px;">&nbsp;
							常停车场所：
						</td>
						<td colspan="3" align="left" style="width: 196px height: 21px;text-align: left;">&nbsp;<input name="ptdo.tempBz2" id="ptdotempBz2"  class="disabled" value="${ptdo.tempBz2}" size="60" maxlength="100" type="text" />
						</td>
						<td>
						</td>
						<td></td>
					</tr>
				</table>
			</div>
			
			<div id="tableid2" class="tablist" style="text-align: center;height: 420px">
				<!-- 技术参数 -->
				<table border="1" cellpadding="0" cellspacing="0" id="tabCc3" class="edittable">
					<tr>
						<td style="text-align:right;"><font style="color:red;">*</font>车辆识别代号：</td>
						<td class="trs" colspan="3" style="text-align: left;">&nbsp;<input type="text" onchange="javascript: document.getElementById('ptdo.outIn').value = '';document.getElementById('ptdo.smycLsh').value = '';" disabled id="ptdoclsbdh" style="background-color: #F1F1F1" value="${ptdo.clsbdh}" name="ptdo.clsbdh" size="30" maxlength="100"/>&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;
						</td>
						<td>
						</td>
						<td >
						</td>
					</tr>
					<tr>
						<td style="text-align:right;"><font style="color:red;">*</font>车辆型号：
						</td>
						<td class="trs" colspan="3" style="text-align: left;">&nbsp;<input type="text" class="disabled" id="ptdoclxh" value="${ptdo.clxh}" name="ptdo.clxh" size="30" maxlength="32"/>&nbsp;&nbsp;&nbsp;&nbsp;
							 &nbsp;
						</td>
						<td style="text-align:right;">
							中文品牌：
						</td>
						<td class="trs" style="text-align: left;">&nbsp;<input type="text" class="disabled" id="ptdoclpp1" value="${ptdo.clpp1}" name="ptdo.clpp1" size="20" maxlength="16"/>
						</td>
					</tr>
					<tr>
						<td style="text-align:right;">英文品牌：</td>
						<td class="trs"  colspan="3" style="text-align: left;">
						&nbsp;<input type="text" class="disabled" id="ptdoclpp2" value="${ptdo.clpp2}" name="ptdo.clpp2" size="30" maxlength="16"/></td>
					
						<td style="text-align:right;"><font style="color:red;">*</font>车辆类型：</td>
						<td class="trs" style="text-align: left;">&nbsp;<select id="ptdocllx" name="ptdo.cllx">
												<option value="">===请选择===</option>
												<s:if test="#request.cllxList != null">
													<s:iterator var="cllx" value="#request.cllxList">
														<option value="<s:property value="#cllx.dmz" />" <s:if test="#request.ptdo.cllx == #cllx.dmz">selected="selected"</s:if>><s:property value="#cllx.dmsm1" /></option>
													</s:iterator>
												</s:if>
											</select>
						</td>
					</tr>
					<tr>
						<td style="text-align:right;">制造厂名称：</td>
						<td class="trs" colspan="3" style="text-align: left;">&nbsp;<input type="text" class="disabled" id="ptdozzcmc" value="${ptdo.zzcmc}" name="ptdo.zzcmc" size="30" maxlength="64"/>
						</td>
						
						<td style="text-align:right;"><font style="color:red;">*</font>发动机号：</td>
						<td class="trs" style="text-align: left;">&nbsp;<input type="text" class="disabled" id="ptdofdjh" value="${ptdo.fdjh}" name="ptdo.fdjh" size="20" maxlength="100"/>
						</td>
					</tr>
					
					<tr>
						<td style="text-align:right;"><font style="color:red;">*</font>出厂日期：</td>
						<td style="text-align: left;">&nbsp;<input name="ptdo.ccrq" id="ptdoccrq" class="disabled" value="${fn:substring(ptdo.ccrq,0,10)}" size="10" type="text"/>
						</td>
						
						<td style="text-align:right;">发合格证日期：</td>
						<td class="trs" style="text-align: left;"><input name="ptdo.fhgzrq" id="ptdofhgzrq" class="disabled" value="${fn:substring(ptdo.fhgzrq,0,10)}" size="10" type="text"/></td>
						
						<td style="text-align:right;">车身颜色：</td>
						<td style="text-align: left;">&nbsp;<select id="ptdocsys1" name="ptdo.csys">
									<option value="">请选择</option>
									<s:if test="#request.csysList != null">
										<s:iterator var="csys" value="#request.csysList">
											<option value="<s:property value="#csys.dmz" />" <s:if test="#request.ptdo.csys == #csys.dmz">selected="selected"</s:if>><s:property value="#csys.dmsm1" /></option>
										</s:iterator>
									</s:if>
								</select>
								
								<select id="ptdocsys2" name="ptdo.csys">
									<option value="">请选择</option>
									<s:if test="#request.csysList != null">
										<s:iterator var="csys" value="#request.csysList">
											<option value="<s:property value="#csys.dmz" />" <s:if test="#request.ptdo.csys == #csys.dmz">selected="selected"</s:if>><s:property value="#csys.dmsm1" /></option>
										</s:iterator>
									</s:if>
								</select>
								
								<select id="ptdocsys3" name="ptdo.csys">
									<option value="">请选择</option>
									<s:if test="#request.csysList != null">
										<s:iterator var="csys" value="#request.csysList">
											<option value="<s:property value="#csys.dmz" />" <s:if test="#request.ptdo.csys == #csys.dmz">selected="selected"</s:if>><s:property value="#csys.dmsm1" /></option>
										</s:iterator>
									</s:if>
								</select>
						</td>
					</tr>
					<tr >
						<td id="ptdo.gcjkTdHgzbh" style="text-align:right;">
							<font style="color:red;">*</font>合格证编号：
						</td>
						<td id="ptdo.gcjkTdHgzbhValue" class="trs" style="text-align: left;">&nbsp;<input type="text" class="disabled" id="ptdohgzbh" value="${ptdo.hgzbh}" name="ptdo.hgzbh" size="20" maxlength="30"/></td>
						
						<td id="ptdo.gcjkTdJgpzh" style="text-align:right;display: none;"><font style="color:red;">*</font>进口凭证号：</td>
						<td id="ptdo.gcjkTdJgpzhValue" class="trs" style="display: none;" style="text-align: left;">
						&nbsp;<input type="text" class="disabled" id="ptdojkpzhm" value="${ptdo.jkpzhm}" name="ptdo.jkpzhm" size="20" maxlength="30"/>
						</td>
					
						<td style="text-align:right;">国产/进口：</td>
						<td style="text-align: left;">&nbsp;<select id="ptdogcjk" name="ptdo.gcjk">
									<option value="">===请选择===</option>
									<s:if test="#request.gcjkList != null">
										<s:iterator var="gcjk" value="#request.gcjkList">
											<option value="<s:property value="#gcjk.dmz" />" <s:if test="#request.ptdo.gcjk == #gcjk.dmz">selected="selected"</s:if>><s:property value="#gcjk.dmsm1" /></option>
										</s:iterator>
									</s:if>
								</select>
						</td>
						
						<td style="text-align:right;"><font style="color:red;">*</font>制造国：</td>
						<td style="text-align: left;">&nbsp;<select id="ptdozzg" name="ptdo.zzg" >
									<option value="">===请选择===</option>
									<s:if test="#request.zzgList != null">
										<s:if test='#request.par == null'>
											<s:iterator var="zzg" value="#request.zzgList">
												<option value="<s:property value="#zzg.dmz" />" <s:if test='#zzg.dmz == "156"'>selected="selected"</s:if>><s:property value="#zzg.dmsm1" /></option>
											</s:iterator>
										</s:if>
										<s:else>
											<s:iterator var="zzg" value="#request.zzgList">
												<option value="<s:property value="#zzg.dmz" />" <s:if test="#request.ptdo.zzg == #zzg.dmz">selected="selected"</s:if>><s:property value="#zzg.dmsm1" /></option>
											</s:iterator>
										</s:else>
									</s:if>
								</select>
						</td>
						
					</tr>
					<tr>
						<td style="text-align:right;"><font style="color:red;">*</font>发动机型号：</td>
						<td class="trs" style="text-align: left;">
						&nbsp;<input type="text" class="disabled" id="ptdofdjxh" value="${ptdo.fdjxh}" name="ptdo.fdjxh" size="20" maxlength="100"/>
						</td>
						
						<td style="text-align:right;">排量/功率：</td>
						<td class="trs" style="text-align: left;">&nbsp;<input type="text" class="disabled" id="ptdopl" value="${ptdo.pl}" name="ptdo.pl" size="1" maxlength="6"/>ml
							<input type="text" class="disabled" id="ptdogl" value="${ptdo.gl}" name="ptdo.gl" size="1" maxlength="7"/>kw
						</td>
						
						<td style="text-align:right;">燃料种类：</td>
						<td class="trs" style="text-align: left;">&nbsp;<select id="ptdorlzl" name="ptdo.rlzl">
												<option value="">===请选择===</option>
												<s:if test="#request.rlzlList != null">
													<s:iterator var="rlzl" value="#request.rlzlList">
														<option value="<s:property value="#rlzl.dmz" />" <s:if test="#request.ptdo.rlzl == #rlzl.dmz">selected="selected"</s:if>><s:property value="#rlzl.dmsm1" /></option>
													</s:iterator>
												</s:if>
											</select>
						</td>
					</tr>
					<tr>
						<td style="text-align:right;">外廓尺寸：</td>
						<td class="trs" colspan="3" style="text-align: left;">&nbsp;<input name="ptdo.cwkc" type="text" id="ptdocwkc" class="disabled" value="${ptdo.cwkc}" size="3" maxlength="5"/>
							mm(长)
							<input name="ptdo.cwkk" type="text" class="disabled" id="ptdocwkk" value="${ptdo.cwkk}" size="3" maxlength="4"/>
							mm(宽)
							<input name="ptdo.cwkg" type="text" class="disabled" id="ptdocwkg" value="${ptdo.cwkg}"  size="3" maxlength="4" />
							mm(高)
						</td>
						
						<td style="text-align:right;">转向形式：</td>
						<td class="trs" style="text-align: left;">&nbsp;<select id="ptdozxxs" name="ptdo.zxxs">
												<option value="">===请选择===</option>
												<s:if test="#request.zxxsList != null">
													<s:iterator var="zxxs" value="#request.zxxsList">
														<option value="<s:property value="#zxxs.dmz" />" <s:if test="#request.ptdo.zxxs == #zxxs.dmz">selected="selected"</s:if>><s:property value="#zxxs.dmsm1" /></option>
													</s:iterator>
												</s:if>
											</select>
						</td>
					</tr>
					<tr>
						<td style="text-align:right;">货箱内部尺寸：</td>
						<td class="trs" colspan="3" style="text-align: left;">&nbsp;<input name="ptdo.hxnbcd" type="text" class="disabled" id="ptdohxnbcd" value="${ptdo.hxnbcd}" size="3" maxlength="5" />
							mm(长)
							<input name="ptdo.hxnbkd" type="text" class="disabled" id="ptdohxnbkd" value="${ptdo.hxnbkd}" size="3" maxlength="4" />
							mm(宽)
							<input name="ptdo.hxnbgd" type="text" class="disabled" id="ptdohxnbgd" value="${ptdo.hxnbgd}" size="3" maxlength="4" />
							mm(高)
									
						</td>
						
						<td style="text-align:right;">环保达标情况：</td>
						<td class="trs" style="text-align: left;">&nbsp;<input type="text" class="disabled" id="ptdohbdbqk" value="${ptdo.hbdbqk}" name="ptdo.hbdbqk" size="20" maxlength="60"/>
						</td>
					</tr>
					<tr>
						<td style="text-align:right;">钢板弹簧片数：</td>
						<td class="trs" style="text-align: left;">&nbsp;<input type="text" class="disabled" id="ptdogbthps" value="${ptdo.gbthps}" name="ptdo.gbthps" size="10" maxlength="3"/>片
						</td>
						
						<td style="text-align:right;">轴数：</td>
						<td class="trs" style="text-align: left;" >&nbsp;<input type="text" class="disabled" id="ptdozs" value="${ptdo.zs}" name="ptdo.zs" size="10" maxlength="1"/>个
						</td>
						
						<td style="text-align:right;">轴距：</td>
						<td class="trs" style="text-align: left;">&nbsp;<input type="text" class="disabled" id="ptdozj" value="${ptdo.zj}" name="ptdo.zj" size="10" maxlength="5"/>mm
						</td>
					</tr>
					<tr>
						<td style="text-align:right;">轮胎数：</td>
						<td class="trs" style="text-align: left;">&nbsp;<input type="text" class="disabled" id="ptdolts" value="${ptdo.lts}" name="ptdo.lts" size="5" maxlength="2"/>个
						</td>
						
						<td style="text-align:right;">轮胎规格：</td>
						<td class="trs" style="text-align: left;">&nbsp;<input type="text" class="disabled" id="ptdoltgg" value="${ptdo.ltgg}" name="ptdo.ltgg" size="10" maxlength="50"/>
						</td>
						
						<td style="text-align:right;">轮距：</td>
						<td class="trs" style="text-align: left;">&nbsp;<input type="text" class="disabled" id="ptdoqlj" value="${ptdo.qlj}" name="ptdo.qlj" size="5" maxlength="4"/>mm(前)
							&nbsp;&nbsp;<input type="text" class="disabled" id="ptdohlj" value="${ptdo.hlj}" name="ptdo.hlj" size="5" maxlength="4"/>mm(后)
						</td>
					</tr>
					<tr>
						<td style="text-align:right;">总质量：</td>
						<td class="trs" style="text-align: left;">&nbsp;<input type="text" class="disabled" id="ptdozzl" value="${ptdo.zzl}" name="ptdo.zzl" size="5" maxlength="8"/>kg
						</td>
						
						<td style="text-align:right;"><font style="color:red;">*</font>整备质量：</td>
						<td class="trs" style="text-align: left;">&nbsp;<input type="text" class="disabled" id="ptdozbzl" value="${ptdo.zbzl}" name="ptdo.zbzl" size="5" maxlength="8"/>kg
						</td>
						
						<td style="text-align:right;">核定载客：</td>
						<td class="trs" style="text-align: left;">&nbsp;<input type="text" class="disabled" id="ptdohdzk" value="${ptdo.hdzk}" name="ptdo.hdzk" size="5" maxlength="3"/>人
						</td>
					</tr>
					<tr>
						<td style="text-align:right;">准牵引总质量：</td>
						<td class="trs" style="text-align: left;">&nbsp;<input type="text" class="disabled" id="ptdozqyzl" value="${ptdo.zqyzl}" name="ptdo.zqyzl" size="5" maxlength="8"/>个
						</td>
						
						<td style="text-align:right;">核定载质量：</td>
						<td class="trs" style="text-align: left;">&nbsp;<input type="text" class="disabled" id="ptdohdzzl" value="${ptdo.hdzzl}" name="ptdo.hdzzl" size="5" maxlength="8"/>个
						</td>
						
						<td style="text-align:right;">驾驶室载客：</td>
						<td class="trs" style="text-align: left;">&nbsp;<input type="text" class="disabled" id="ptdoqpzk" value="${ptdo.qpzk}" name="ptdo.qpzk" size="5" maxlength="1"/>人(前)
										&nbsp;&nbsp;<input type="text" class="disabled" id="ptdohpzk" value="${ptdo.hpzk}" name="ptdo.hpzk" size="5" maxlength="1"/>人(后)
						</td>
					</tr>
				</table>
			</div>
		
			<div id="tableid3" class="tablist" style="text-align: center;height: 850px">
				<!-- 抵押登记 -->
				<table border="1" cellpadding="0" cellspacing="0" id="tabCc1"  class="edittable">
					<tr>
						<td style="text-align:right; font-weight: bold" width="120" rowspan="5">基本信息：
						</td>
						<td class="trs" style="text-align: right; width: 100px;">&nbsp;
								<font style="color:red;">*</font>业务办理类型:
						</td>
						<td style="text-align:left;">
							<input type="radio" name="dydjYwsbspb.ywlx" value="A" checked="checked" /> 抵押登记 &nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="dydjYwsbspb.ywlx" value="B" /> 解除抵押登记
						</td>
						<td class="trs" style="text-align: right; width: 100px;">&nbsp;
							<font style="color:red;">*</font>银行受理类型：
						</td>
						<td style="text-align:left;">
							<input type="radio"	name="dydjYwsbspb.sqlx" value="11" checked="checked"/>代办个人车辆申报&nbsp;&nbsp;
							<input type="radio" name="dydjYwsbspb.sqlx" value="12" />代办单位车辆申报&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="dydjYwsbspb.sqlx" value="21" />车主自行办理车辆申报 
						</td>
					</tr>
					<tr>
						<td style="text-align:right;"><font style="color:red;">*</font>主合同号：</td>
						<td class="trs" style="text-align: left;">
							&nbsp;
							<input id="zhth" type="text" name="dydjYwsbspb.zhth"  value="${dydjYwsbspb.zhth}"/> 
						</td>
					
						<td style="text-align:right;"><font style="color:red;">*</font>抵押合同号：</td>
						<td class="trs" style="text-align: left;">
							&nbsp;
							<input type="text" id="dyhth" name="dydjYwsbspb.dyhth" value="${dydjYwsbspb.dyhth}"/>
						</td>
					</tr>
					
					<tr>
						<td style="text-align:right;"><font style="color:red;">*</font>号牌号码：</td>
						<td class="trs" style="text-align: left;">&nbsp;
							<input onkeyup="xiaobianda(this);clearspace(this);" onblur="clearallspace(this)"
								id="hphmdydj" type="text" name="dydjYwsbspb.hphm" value="${dydjYwsbspb.hphm}"/> 
						</td>
						<td style="text-align:right;"><font style="color:red;">*</font>号牌种类：</td>
						<td style="text-align: left;">&nbsp;
							<select class="dis" name="dbZjxxb.hpzl" id="hpzldydj">
								<s:iterator var="hp" value="#request.hpzlList">
									<option value='<s:property value="#hp.dmz"/>'>
										<s:property value="#hp.dmms1"/>
									</option>
								</s:iterator>
							</select>
						</td>
						
					</tr>
					<tr>
						<td style="text-align:right;"><font style="color:red;">*</font>车辆识别代号：</td>
						<td class="trs" style="text-align: left;">&nbsp;
							<input type="text" id="clsbdh" name="dydjYwsbspb.clsbdh" value="${dydjYwsbspb.clsbdh}"/>
						</td>
						<td style="text-align:right;">
							<font style="color:red;">*</font>登记证书编号：
						</td>
						<td style="text-align: left;">
							&nbsp;
							<input type="text" id="djzsbh" name="dydjYwsbspb.djzsbh" onkeyup="xiaobianda(this); clearspace(this)" onblur="clearallspace(this)" value="${dydjYwsbspb.djzsbh}"/>
						</td>
					</tr>
					<tr>
						<td style="text-align:right;"><font style="color:red;">*</font>业务办理点：</td>
						<td style="text-align: left;">&nbsp;
							<select name="dydjYwsbspb.ycsDeptId" id="ycsDeptId" style="width: 155px;">
								<s:iterator var="dept" value="#request.deptList">
									<option value='${dept[0]}' 
										<s:if test="#request.dydjYwsbspb.ycsDeptId==#dept[0]">selected</s:if>>
										${dept[1]}
									</option>
								</s:iterator>
							</select>
						</td>
						
						<td style="text-align:right;"><font style="color:red;">*</font>办理类型：</td>
						<td style="text-align: left;">&nbsp;
							<input type="radio" id="bllxxc" name="bllx" value="0" />现场办理
							<input type="radio" id="bllxyz" name="bllx" value="1" />邮政上门
						</td>
					</tr>
					
					<tr>
						<td style="text-align:right; font-weight: bold" width="120" rowspan="2">取件人信息：
						</td>
						<td class="trs" style="text-align: right; width: 100px;">&nbsp;
							<font style="color:red;">*</font>取件人姓名:
						</td>
						<td style="text-align:left;">
							&nbsp;
							<input class="text_2" type="text" id="qjQjrxm" name="dydjYwsbspb.qjQjrxm" onkeyup="textchange('qjQjrxm', 'yjSjrxm'); clearspace(this);" onblur="clearallspace(this);" onchange="textchange('qjQjrxm', 'yjSjrxm');" value="${dydjYwsbspb.qjQjrxm}"/>&nbsp;
						</td>
						<td class="trs" style="text-align: right; width: 100px;">&nbsp;
							<font style="color:red;">*</font>取件人电话：
						</td>
						<td style="text-align:left;">
							&nbsp;
							<input type="text" id="qjLxdh" name="dydjYwsbspb.qjLxdh" onkeyup="textchange('qjLxdh', 'yjLxdh');" onchange="textchange('qjLxdh', 'yjLxdh');" value="${dydjYwsbspb.qjLxdh}"/>
						</td>
					</tr>
					<tr>
						<td style="text-align:right;"><font style="color:red;">*</font>取件地址：</td>
						<td class="trs" style="text-align: left;">
							&nbsp;
							<input class="text_2" type="text" id="qjTddz" name="dydjYwsbspb.qjTddz" onkeyup="textchange('qjTddz', 'yjTddz'); clearspace(this);" onblur="clearallspace(this);" onchange="textchange('qjTddz', 'yjTddz');" value="${dydjYwsbspb.qjTddz}" style="width: 200px"/> 
						</td>
					
						<td style="text-align:right;"><font style="color:red;">*</font>邮政编码：</td>
						<td class="trs" style="text-align: left;">
							&nbsp;
							<input type="text" id="qjYzbm" name="dydjYwsbspb.qjYzbm" onkeyup="textchange('qjYzbm', 'yjYzbm'); clearspace(this);" onblur="clearallspace(this);" onchange="textchange('qjYzbm', 'yjYzbm');" value="${dydjYwsbspb.qjYzbm}"/>
						</td>
					</tr>
					
					<tr>
						<td style="text-align:right; font-weight: bold" width="120" rowspan="2">邮寄收件人信息：
						</td>
						<td class="trs" style="text-align: right; width: 100px;">&nbsp;
								<font style="color:red;">*</font>收件人姓名:
						</td>
						<td style="text-align:left;">
							&nbsp;
							<input class="text_2" type="text" id="yjSjrxm" name="dydjYwsbspb.yjSjrxm" onkeyup="clearspace(this)" onblur="clearallspace(this)" value="${dydjYwsbspb.yjSjrxm}" maxlength="20"/>&nbsp;
						</td>
						<td class="trs" style="text-align: right; width: 100px;">&nbsp;
							<font style="color:red;">*</font>收件人电话：
						</td>
						<td style="text-align:left;">
							&nbsp;
							<input type="text" id="yjLxdh" name="dydjYwsbspb.yjLxdh" onkeyup="clearspace(this)" onblur="clearallspace(this)" value="${dydjYwsbspb.yjLxdh}"/>
						</td>
					</tr>
					<tr>
						<td style="text-align:right;"><font style="color:red;">*</font>收件人地址：</td>
						<td class="trs" style="text-align: left;">
							&nbsp;
							<input class="text_2" type="text" id="yjTddz" name="dydjYwsbspb.yjTddz" onkeyup="clearspace(this)" onblur="clearallspace(this)" value="${dydjYwsbspb.yjTddz}" style="width: 200px"/> 
						</td>
					
						<td style="text-align:right;"><font style="color:red;">*</font>邮政编码：</td>
						<td class="trs" style="text-align: left;">
							&nbsp;
							<input type="text" id="yjYzbm" name="dydjYwsbspb.yjYzbm" onkeyup="clearspace(this)" onblur="clearallspace(this)" value="${dydjYwsbspb.yjYzbm}"/>
						</td>
					</tr>
					<!-- 抵押权人信息 -->
					<tr>
						<td style="text-align:right; font-weight: bold" width="120" rowspan="2">抵押权人：
						</td>
						<td class="trs" colspan="2" style="text-align: center;">&nbsp;
							<b>组织机构证件信息</b>
						</td>
						
						<td class="trs" colspan="2" style="text-align: center; ">&nbsp;
							<b>经办人信息</b>
						</td>
					</tr>
					<tr>
						<td class="trs" colspan="2" style="text-align:left; vertical-align: top; padding-top: 0px;">
							<table id="dbrtablezzjgz" class="edittable" border="0" cellpadding="0" cellspacing="0" align="center">
								<tr>
									<td style="text-align: right;">组织机构证代码：</td>
									<td style="border-right: 0px;">
										&nbsp;<input type="text" readonly="readonly" class="disabled"
											id="yhZzjgZh" name="dydjYwsbspb.yhZzjgZh"
											value="${dydjYwsbspb.yhZzjgZh}" size="35" />
									</td>
								</tr>
								<tr>
									<td style="text-align: right;">单位法人：</td>
									<td>
										&nbsp;<input type="text" readonly="readonly" class="disabled"
											id="yhZzjgFrdb" name="dydjYwsbspb.yhZzjgFrdb"
											value="${dydjYwsbspb.yhZzjgFrdb}" size="30" />
									</td>
								</tr>
								<tr>
									<td style="text-align: right;">营业执照：</td>
									<td>
										&nbsp;<input type="text" readonly="readonly" class="disabled"
											id="yhZzjgYyzz" name="dydjYwsbspb.yhZzjgYyzz"
											value="${dydjYwsbspb.yhZzjgYyzz}" size="30" />
									</td>
								</tr>
								<tr>
									<td style="text-align: right;">单位名称：</td>
									<td>
										&nbsp;<input type="text" readonly="readonly" class="disabled"
												id="yhZzjgDwmc" name="dydjYwsbspb.yhZzjgDwmc"
												value="${dydjYwsbspb.yhZzjgDwmc}" size="30" />
									</td>
								</tr>
								<tr>
									<td style="text-align: right;">&nbsp;年检日期：</td>
									<td>
										&nbsp;<input type="text" readonly="readonly" class="disabled"
												id="yhZzjgNjrq" name="dydjYwsbspb.yhZzjgNjrq"
												value="${dydjYwsbspb.yhZzjgNjrq}" size="30" />
									</td>
								</tr>
								<tr>
									<td style="text-align: right;">&nbsp;年检有效期：</td>
									<td>
										&nbsp;<input type="text" readonly="readonly" class="disabled"	id="yhZzjgNjyxq" name="dydjYwsbspb.yhZzjgNjyxq" value="${dydjYwsbspb.yhZzjgNjyxq}" size="30" />
									</td>
								</tr>
								<tr>
									<td style="text-align: right;">&nbsp;地址：</td>
									<td>
										&nbsp;<input type="text" readonly="readonly" class="disabled"
											id="yhZzjgDz" name="dydjYwsbspb.yhZzjgDz" value="${dydjYwsbspb.yhZzjgDz}" size="30" />
									</td>
								</tr>
							</table>
						</td>
						<td class="trs" colspan="2" style="text-align: left; vertical-align: top;">
							<table id="dbrtablesfz" class="edittable" border="0" cellpadding="0" cellspacing="0" align="left">
								<tr>
									<td style="text-align: right; border-left: 0px;">姓名：</td>
									<td>
										&nbsp;<input type="text" class="disabled" readonly="readonly"
										size="18" id="yhSfzCardname" name="dydjYwsbspb.yhSfzCardname" value="${dydjYwsbspb.yhSfzCardname}"/>
									</td>
									<td rowspan="4" style="text-align: center;">
										<img id="sfzxpimgid1" src="<%=request.getContextPath()%>/images/cp.gif" width="108" height="120" border="0">
									</td>
								</tr>
								<tr>
									<td style="text-align: right;">性别：</td>
									<td>
										&nbsp;<input type="text" class="disabled" readonly="readonly"
										size="18" id="yhSfzCardsex" name="dydjYwsbspb.yhSfzCardsex" value="${dydjYwsbspb.yhSfzCardsex}"/>
									</td>
								</tr>
								<tr>
									<td style="text-align: right;">身份证号：</td>
									<td>
										&nbsp;<input type="text" class="disabled" readonly="readonly"
										size="18" id="yhSfzCardno" name="dydjYwsbspb.yhSfzCardno" value="${dydjYwsbspb.yhSfzCardno}"/>
									</td>
								</tr>
								<tr>
									<td style="text-align: right;">地址：</td>
									<td>
										&nbsp;<input type="text" class="disabled" readonly="readonly"
											size="18" id="yhSfzCardaddress" name="dydjYwsbspb.yhSfzCardaddress" value="${dydjYwsbspb.yhSfzCardaddress}"/>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<!-- 抵押人信息 -->
					<tr>
						<td style="text-align:right; font-weight: bold" width="120" rowspan="2">抵押人：
						</td>
						<td class="trs" colspan="2" style="text-align: center;">&nbsp;
							<b>组织机构证件信息</b>
						</td>
						
						<td class="trs" colspan="2" style="text-align: center; ">&nbsp;
							<b>经办人信息</b>
						</td>
					</tr>
					<tr>
						<td class="trs" colspan="2" style="text-align:left; vertical-align: top; padding-top: 0px;">
							<table id="dbrtablezzjgz" class="edittable" border="0" cellpadding="0" cellspacing="0" align="center">
								<tr>
									<td style="text-align: right;">组织机构证代码：</td>
									<td style="border-right: 0px;">
										&nbsp;<input type="text" readonly="readonly" class="disabled"
									id="dyrZzjgZh" name="dydjYwsbspb.dyrZzjgZh" value="${dydjYwsbspb.dyrZzjgZh}" size="30" />
									</td>
								</tr>
								<tr>
									<td style="text-align: right;">单位法人：</td>
									<td>
										&nbsp;<input type="text" readonly="readonly" class="disabled"
									id="dyrZzjgFrdb" name="dydjYwsbspb.dyrZzjgFrdb" value="${dydjYwsbspb.dyrZzjgFrdb}" size="30" />
									</td>
								</tr>
								<tr>
									<td style="text-align: right;">营业执照：</td>
									<td>
										&nbsp;<input type="text" readonly="readonly" class="disabled"
									id="dyrZzjgYyzz" name="dydjYwsbspb.dyrZzjgYyzz" value="${dydjYwsbspb.dyrZzjgYyzz}" size="30" />
									</td>
								</tr>
								<tr>
									<td style="text-align: right;">单位名称：</td>
									<td>
										&nbsp;<input type="text" readonly="readonly" class="disabled"
										id="dyrZzjgDwmc" name="dydjYwsbspb.dyrZzjgDwmc" value="${dydjYwsbspb.dyrZzjgDwmc}" size="30" />
									</td>
								</tr>
								<tr>
									<td style="text-align: right;">&nbsp;年检日期：</td>
									<td>
										&nbsp;<input type="text" readonly="readonly" class="disabled"
										id="dyrZzjgNjrq" name="dydjYwsbspb.dyrZzjgNjrq" value="${dydjYwsbspb.dyrZzjgNjrq}" size="30" />
									</td>
								</tr>
								<tr>
									<td style="text-align: right;">&nbsp;年检有效期：</td>
									<td>
										&nbsp;<input type="text" readonly="readonly" class="disabled"
										id="dyrZzjgNjyxq" name="dydjYwsbspb.dyrZzjgNjyxq" value="${dydjYwsbspb.dyrZzjgNjyxq}" size="30" />
									</td>
								</tr>
								<tr>
									<td style="text-align: right;">&nbsp;地址：</td>
									<td>
										&nbsp;<input type="text" readonly="readonly" class="disabled"
									id="dyrZzjgDz" name="dydjYwsbspb.dyrZzjgDz" value="${dydjYwsbspb.dyrZzjgDz}" size="30" />
									</td>
								</tr>
							</table>
						</td>
						<td class="trs" colspan="2" style="text-align: left; vertical-align: top;">
							<table id="dbrtablesfz" class="edittable" border="0" cellpadding="0" cellspacing="0" align="left">
								<tr>
									<td style="text-align: right; border-left: 0px;">姓名：</td>
									<td>
										&nbsp;<input type="text" class="disabled" readonly="readonly"
										size="18" id="dyrSfzCardname" name="dydjYwsbspb.dyrSfzCardname" value="${dydjYwsbspb.dyrSfzCardname}"/>
									</td>
									<td rowspan="4" style="text-align: center;">
										<img id="dydjsfzxpimgid" src="<%=request.getContextPath()%>/images/cp.gif" width="108" height="120" border="0">
									</td>
								</tr>
								<tr>
									<td style="text-align: right;">性别：</td>
									<td>
										&nbsp;<input type="text" class="disabled" readonly="readonly"
										size="18" id="dyrSfzCardsex" name="dydjYwsbspb.dyrSfzCardsex" value="${dydjYwsbspb.dyrSfzCardsex}"/>
									</td>
								</tr>
								<tr>
									<td style="text-align: right;">身份证号：</td>
									<td>
										&nbsp;<input type="text" class="disabled" readonly="readonly"
										size="18" id="dyrSfzCardno" name="dydjYwsbspb.dyrSfzCardno" value="${dydjYwsbspb.dyrSfzCardno}"/>
									</td>
								</tr>
								<tr>
									<td style="text-align: right;">地址：</td>
									<td>
										&nbsp;<input type="text" class="disabled" readonly="readonly"
											size="18" id="dyrSfzCardaddress" name="dydjYwsbspb.dyrSfzCardaddress" value="${dydjYwsbspb.dyrSfzCardaddress}"/>
									</td>
								</tr>
								
							</table>
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
								<input type="button" style="cursor:pointer;" onclick="javascript:subforms();" value="保 存" class="bnt" />
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