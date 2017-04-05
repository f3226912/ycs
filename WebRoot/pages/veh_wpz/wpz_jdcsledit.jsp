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
	src="<%=request.getContextPath()%>/js/veh/jdcsledit_wpz.js"></script>
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


<!-- 照片拍照后赋值路径  -->
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
		if('新增' == edittype){
			$("#tableid1 input").attr("disabled","disabled");
			$("#tableid2 input").attr("disabled","disabled");
			$("#tableid1 select").attr("disabled","disabled");
			$("#tableid2 select").attr("disabled","disabled");
		}
		
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
			
			//分别给颜色赋值
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
	
	function selectReadonly(obj){
		alert(obj);
		obj.onmouseover = function(){
			ptdosfzmmc.setCapture();
		}
		obj.onmouseout = function(){
			ptdosfzmmc.releaseCapture();
		}
		obj.onfocus = function(){
			ptdosfzmmc.blur();
		}
		obj.onbeforeactivate = function(){
			return false;
		}
		
	}
	
	function xuanze(e){
		$("#dsrsfzmmc").hide();
		$("#dbrsfzmmc").hide();
		$("#hpzl").hide();
		$("#ddlOrgCom").hide();
		$("#dbrddlOrgCom").hide();
		e.stopPropagation();
		//设置弹出层位置
	    var offset = $(e.target).offset();
	    //设置弹出层位置在点击的下面
	    $("#dangport").css({top:offset.top + $(e.target).height()+5 + "px", left:103});
	    $("#dangport").show();
	    //加载业务类型和业务原因
	    var ywlxhtml = $("#ywlxdiv").html();
	    if(ywlxhtml == null || ywlxhtml == ''){
	    	var list = getYwlx('', '', 'JDCYWSL', 'VEH_YWLX');
	   		var data = createYwlx(list);
	    	$("#ywlxdiv").html(data.join(''));
	    }
	}
			
	
	//根据号牌号码、号牌种类获取机动车受理信息		
	function getJdcslInfo(){
		//号牌号码
		var hphm = $("#hphm").val();
		var hpzl = $("#hpzl").val();
		if(hphm == ""||hphm == "B"){
			alert("请输入要查询的车牌号码！");
			return false;
		}
		
		//Ajax查询登记信息和技术参数
		$.ajax({
			cache:false,
			async:false,
			url:'<%=request.getContextPath()%>/wpz_ajax/wpzajax_getJdcInfo.action',
			type:'post',
			data:{"hphm":hphm,"hpzl":hpzl},
			dataType:'json',
			success: function(result){
				if(result!=null && result!=""){
				/*$("#tableid1 input").attr("readonly","true");
				$("#tableid2 input").attr("readonly","true");
				$("#tableid1 select").attr("disabled","disabled");
				$("#tableid2 select").attr("disabled","disabled");*/
				
					//设置业务类型
					//《登记信息》
					$("#ptdosfzmmc").val(result.sfzmmc);//身份证明名称-下拉框*
					$("#ptdosfzmhm").val(result.sfzmhm);//身份证明号码*
					$("#ptdozzz").val(result.zzz);		//居住/暂住证号*
					$("#ptdosyr").val(result.czmc);		//姓名/名称*
					$("#ptdolxdh").val(result.sjhm);	//移动电话*
					$("#ptdodzyx").val(result.dzyx);	//电子邮箱
					$("#yzbm7").val(result.zsxzqh);		//住所地址- 区域下拉框
					$("#yzbm6").val(result.zzxzqh);		//邮寄地址- 区域下拉框
					$("#ptdozsxxdz").val(result.zsxxdz);//住所地址-详细地址*
					$("#ptdozzxxdz").val(result.zzxxdz);//邮寄地址-详细地址*
					$("#ptdoyzbm1").val(result.yzbm1);  //邮寄地址
					$("#ptdosyxz").val(result.syxz);	//使用性质《下拉框》
					$("#ptdosyq").val(result.syq);		//所有权《下拉框》
														//获得方式《下拉框》
					$("#ptdozsxzqh").val(result.zsxzqh);//行政区划《下拉框》
					$("#ptdohpzl").val(result.hpzl);	//号牌种类《下拉框》
														//保险生效日期
														//保险终止日期
														//检验日期
														//承检单位《下拉框》
														//常停车场所
					
					//《技术参数》
					$("#ptdoclsbdh").val(result.clsbdh);//车辆识别代号
					$("#ptdoclxh").val(result.xh);		//车辆型号
														//中文品牌
														//英文品牌
					$("#ptdocllx").val(result.cllx);	//车辆类型《下拉框》
					$("#ptdozzcmc").val(result.cj);		//制造厂名称
					$("#ptdofdjh").val(result.fdjh);	//发动机号
					$("#ptdoccrq").val(result.ccdjrq);	//出厂日期
					$("#ptdofhgzrq").val(result.fhgzrq);//发合格证日期
					$("#ptdocsys1").val(result.csys);	//车身颜色《三个下拉框》
					$("#ptdohgzbh").val(result.hgzbh);	//合格证编号
														//国产/进口《下拉框》
					$("#ptdozzg").val(result.zzg);		//制造国《国家下拉框》
					$("#ptdofdjxh").val(result.fdjxh);	//发动机型号
					$("#ptdopl").val(result.pl);		//排量pl
					$("#ptdogl").val(result.gl);		//功率gl
					$("#ptdorlzl").val(result.rlzl);	//燃料种类《燃料类型下拉框》
					$("#ptdocwkc").val(result.cwkc);	//外廓尺寸：长
					$("#ptdocwkk").val(result.cwkk);	//外廓尺寸：宽
					$("#ptdocwkg").val(result.cwkg);	//外廓尺寸：高
					$("#ptdozxxs").val(result.zxxs);	//转向形式《下拉框》
					$("#ptdohxnbcd").val(result.hxnbcd);//货箱内部尺寸：长
					$("#ptdohxnbkd").val(result.hxnbkd);//货箱内部尺寸：宽
					$("#ptdohxnbgd").val(result.hxnbgd);//货箱内部尺寸：高
					$("#ptdohbdbqk").val(result.hbdbqk);//环保达标情况
					$("#ptdogbthps").val(result.gbthps);//钢板弹簧片数
					$("#ptdozs").val(result.cwkg);		//轴数
					$("#ptdozj").val(result.cwkg);		//轴距
					$("#ptdolts").val(result.cwkg);		//轮胎数
					$("#ptdoltgg").val(result.cwkg);	//轮胎规格
					$("#ptdoqlj").val(result.qlj);		//轮距：前
					$("#ptdohlj").val(result.hlj);		//轮距：后
					$("#ptdozzl").val(result.zzl);		//总质量
					$("#ptdozbzl").val(result.zbzl);	//整备质量
					$("#ptdohdzk").val(result.hdzk);	//核定载客
					$("#ptdozqyzl").val(result.zqyzl);	//准牵引总质量
					$("#ptdohdzzl").val(result.hdzzl);	//核定载质量
					$("#ptdoqpzk").val(result.qpzk);	//驾驶室载客：前
					$("#ptdohpzk").val(result.hpzk);	//驾驶室载客：后
					
					//隐藏参数
				    $("#lshval").val(result.lsh);		//流水号
				    $("#hphmval").val(result.hphm);		//号牌号码
				    $("#hpzlval").val(result.hpzl);		//号牌种类
				    $("#syxz").val(result.syxz);		//使用性质
				    $("#cllx").val(result.cllx);		//车辆类型
				    $("#clsbdhval").val(result.clsbdh);	//车辆识别代号
				    //$("#zblx").val(result.zblx);  	//指标类型 	
				    //$("#zbh").val(result.zbh);		//指标号
				    //$("#tyblsh").val(result.tyblsh);
				    //$("#zjxxblsh").val(result.zjxxblsh);
				    //$("#jbzbh").val(result.jbzbh);
				    //$("#sffq").val(result.sffq);		//是否夫妻
				    //$("#hdfs").val(result.hdfs);		//获得方式
				    //$("#vehflowywlx").val(result.vehflowywlx);//流水号中业务类型
				    //$("#vehflowywyy").val(result.vehflowywyy);//流水号中业务原因
				    //$("#sh_jg").val(result.sh_jg);				    
					$("input[name='dydjYwsbspb.ywlx'][value='"+result.ywlx+"']").attr("checked", true);
					$("input[name='dydjYwsbspb.sqlx'][value='"+result.sqlx+"']").attr("checked", true);
				}else{
					alert("未查询到该记录！");
				}				
			}
		});
	}	
</script>

</head>
<body>
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
	<form action="<%=request.getContextPath()%>/wpz/wpz_getJdcInfo.action" id="addForm" name="addForm" method="post"  target="abc">
	<iframe id="abc" name="abc" width="0" height="0" style="display: none;"></iframe>
			<table class="edittable" border="0" cellpadding="0" cellspacing="0" align="center">
				<tr>
					<td style="text-align: center; background-color: #407FB7; color: #FFFFFF" colspan="2">
						<strong>查询条件</strong>
					</td>
				</tr>				
				<tr>
					<td>&nbsp;&nbsp;号牌号码：<input class="dis" type="text" name="dbZjxxb.hphm" value="B" onkeyup="toUpers(this);" onkeyup="clearspace(this)" onblur="clearallspace(this)" id="hphm"/></td>
					<td>
						&nbsp;&nbsp;号牌种类：
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
					<td colspan="2">
						<div align="center">
							<input type="button" onclick="getJdcslInfo()" value="查询" class="bnt" />
						</div>
					</td>
				</tr>
			</table>
		</form>
		<div class="tab2">
			<form action="" method="post" id="lsh_form">
			<ul id="test2_li_now_">
				<li onclick="settab(this,0)" class="now" style="width: 80px;">证件信息</li>
				<li onclick="settab(this,1)" style="width: 80px;">登记信息</li>
				<li onclick="settab(this,2)" style="width: 80px;">技术参数</li>
				<li onclick="settab(this,3)" id="dydjli" style="width: 80px; display: none;"></li>
			</ul>
			</form>
		</div>
		<div class="div">
			<form action="<%=request.getContextPath()%>/wpz/wpz_editSlgVehXxcj_wpz.action" method="post" id="drv_form" enctype="multipart/form-data" target="abc">
			<iframe id="abc" name="abc" width="0" height="0" style="display: none;"></iframe>
			<input type="hidden" name="dbZjxxb.lsh" id="lshval" value=""/>
			<input type="hidden" name="dbZjxxb.hphm" id="hphmval" value=""/>
			<input type="hidden" name="dbZjxxb.hpzl" id="hpzlval" value=""/>
			<input type="hidden" name="dbZjxxb.clsbdh" id="clsbdhval" value=""/>
			<input type="hidden" name="dbZjxxb.ywlx" id="ywlx" value=""/>
			<input type="hidden" name="dbZjxxb.ywyy" id="ywyy" value=""/>
			<input type="hidden" name="dbZjxxb.syxz" id="syxz" value=""/>
			<input type="hidden" name="dbZjxxb.cllx" id="cllx" value=""/>
			
			
			<input type="hidden" name="ywyysize" id="ywyysize" value=""/>
			<input type="hidden" name="xczpdate" id="xczpdate" />
			<input type="hidden" name="zjzpdate" id="zjzpdate" />
			
			<input type="hidden" name="dbZjxxb.zblx" id="zblx" value=""/>
			<input type="hidden" name="dbZjxxb.zbh" id="zbh" value=""/>
			<input type="hidden" name="dbZjxxb.tyblsh" id="tyblsh" value=""/>
			
			<input type="hidden" name="dbZjxxb.zjxxblsh" id="zjxxblsh" value=""/>
			<input type="hidden" name="dbZjxxb.jhzbh" id="jhzbh" value=""/>
			<input type="hidden" name="dbZjxxb.sffq" id="sffq" value=""/>
			<input type="hidden" name="dbZjxxb.zczxhfhljzyqchzx" id="zczxhfhljzyqchzx" value=""/>
			<input type="hidden" name="zbyzflag" id="zbyzflag" value="0"/>
			<input type="hidden" name="dbZjxxb.hdfs" id="hdfs" value=""/>
			<input type="hidden" name="dbZjxxb.gzh" id="gzh" value=""/>
			<input type="hidden" name="alterinfo" id="alterinfo" value=""/>
			
<!-- 证件信息页面 ===================================================-->			
			<div id="tableid0" class="tablist block">
				<table class="edittable" border="0" cellpadding="0" cellspacing="0" align="center">
					<tr>
						<td colspan="2">
							业务类型：<input type="text" id="ywlxhtml" readonly="readonly" size="115" style="margin:5px 0 0 0;" /> &nbsp;&nbsp;<input type="button" id="btnsplx" class="bnt" style="cursor: pointer; margin:0 0 5px 0;" value="选&nbsp;择"/>
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
							<table width="100%"  class="idcardclass" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td width="50%">
										&nbsp;&nbsp;身份证明名称：
										&nbsp;
										<s:select list="#{'A':'二代居民身份证','B':'组织机构代码证书',
										'C':'军官证','D':'士兵证','E':'军官离退休证','F':'境外人员身份证明',
										'G':'外交人员身份证明','H':'居民户口薄','J':'单位注销证明',
										'K':'居住暂住证明','L':'驻华机构证明','M':'临时居民身份证','N':'统一社会信用代码','P':'个体工商户营业执照注册号'}" theme="simple"
											id="dsrsfzmmc"
											listKey="key" listValue="value" name="dbZjxxb.dsrSfzmmc" value="#request.dbZjxxb.dsrSfzmmc" onchange="changedsrsfzmmc();"></s:select>&nbsp;<!-- <font style="color: red;">*</font> -->
										<span style="color: black;"><input type="checkbox" name="dsrchkreset" id="dsrchkreset" value="1"/>下宗业务是同一当事人</span>
									</td>
									<td>
										<td>
										&nbsp;&nbsp;身份证明名称：
										&nbsp;
										<s:select list="#{'A':'二代居民身份证','B':'组织机构代码证书',
										'C':'军官证','D':'士兵证','E':'军官离退休证','F':'境外人员身份证明',
										'G':'外交人员身份证明','H':'居民户口薄','J':'单位注销证明',
										'K':'居住暂住证明','L':'驻华机构证明','M':'临时居民身份证','N':'统一社会信用代码','P':'个体工商户营业执照注册号'}" theme="simple"
											id="dbrsfzmmc"
											listKey="key" listValue="value" name="dbZjxxb.dbrSfzmmc" value="#request.dbZjxxb.dbrSfzmmc" onchange="changedbrsfzmmc();"></s:select>&nbsp;<!-- <font style="color: red;">*</font> -->
										<!--<span style="color: black;"><input type="checkbox" name="dbrchkreset" id="dbrchkreset" value="1"/>下宗业务是同一代办人</span>
									--></td>
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
												name="dbZjxxb.dsrSfzmhm" value="${dbZjxxb.dsrSfzmhm}"
												onkeyup="clearspace(this)" onblur="clearallspace(this)" />&nbsp;<font style="color: red;">*</font>&nbsp;
											当事人姓名：
											<input type="text" class="disabled1" id="dsrxm"
												name="dbZjxxb.dsrXm" value="${dbZjxxb.dsrXm}" size="10"
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
												name="dbZjxxb.dsrSfzmhm" value="${dbZjxxb.dsrSfzmhm}"
												onkeyup="clearspace(this)" onblur="clearallspace(this)" disabled="disabled" />&nbsp;<font style="color: red;">*</font>&nbsp;
											单位法人：
											<input type="text" class="disabled1" id="dsrdwmc"
												name="dbZjxxb.dsrXm" value="${dbZjxxb.dsrXm}" size="10"
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
												name="dbZjxxb.dbrSfzmhm" value="${dbZjxxb.dbrSfzmhm}" size="18"
												maxlength="20" onkeyup="clearspace(this)" onblur="clearallspace(this)" />&nbsp;<font style="color: red;">*</font>&nbsp;
											代办人姓名：
											<input type="text" class="disabled1" id="dbrxm"
												name="dbZjxxb.dbrXm" value="${dbZjxxb.dbrXm}" size="10"
												maxlength="20" onkeyup="clearspace(this)" onblur="clearallspace(this)" />&nbsp;<font style="color: red;">*</font>
										</td>
									</tr>
								</table>
								
								<!-- 代办人组织机构证 -->
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
												name="dbZjxxb.dbrSfzmhm" value="${dbZjxxb.dbrSfzmhm}" size="18"
												maxlength="20" disabled="disabled" onkeyup="clearspace(this)" onblur="clearallspace(this)" />&nbsp;<font style="color: red;">*</font>&nbsp;
											单位法人：
											<input type="text" class="disabled1" id="dbrdwmc"
												name="dbZjxxb.dbrXm" value="${dbZjxxb.dbrXm}" size="10"
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
											name="dbZjxxb.dsrLxdh" value="" size="18"
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
<!-- 证件信息页面结束===================================================-->
				

			</div>

<!-- 登记信息 ===========================================================-->			
			<div id="tableid1" class="tablist" style="text-align: center;height: 300px">
				<!-- 登记参数 -->
				<table border="1" cellpadding="0" cellspacing="0" id="tabCc1"  class="edittable">
					<tr>
						<td style="text-align:right;" width="95"><font style="color:red;">*</font>身份证明名称：
						</td>
						<td class="trs" style="text-align: left;">
						&nbsp;<select id="ptdosfzmmc" name="ptdo.sfzmmc">
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
						<td class="trs" style="text-align: left;">&nbsp;<input type="text" class="disabled" id="ptdosfzmhm" name="ptdo.sfzmhm" size="17" />
						</td>
						<td style="text-align:right;">
							<font style="color:red;">*</font>居住/暂住证号： 
						</td>
						<td class="trs" style="text-align: left;">&nbsp;<input type="text" class="disabled" id="ptdozzz" " name="ptdo.zzz" size="20"/>
						</td>
					</tr>
					<tr>
						<td style="text-align:right;"><font style="color:red;">*</font>姓名/名称：</td>
						<td class="trs" style="text-align: left;">
						&nbsp;<input type="text" class="disabled" id="ptdosyr"  name="ptdo.syr" size="17" /></td>
					
						<td style="text-align:right;"><font style="color:red;">*</font>移动电话：</td>
						<td class="trs" style="text-align: left;">&nbsp;<input type="text" class="disabled" id="ptdolxdh" name="ptdo.lxdh" size="17"/></td>
					
						<td style="text-align:right;">电子邮箱：</td>
						<td class="trs" style="text-align: left;">&nbsp;<input type="text" class="disabled" id="ptdodzyx"  name="ptdo.dzyx" size="20" /></td>
					</tr>
					
					<tr>
						<td style="text-align:right;"><font style="color:red;">*</font>住所地址：</td>
						<td class="trs" colspan="5" style="text-align: left;">&nbsp;<select onchange="return false" class="select" id="yzbm7" name="yzbm7" >
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
								value = "<s:property value="#ptdo.zsxxdz"/>"
							</s:else> 
							name="ptdo.zsxxdz" size="50"/>
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
							 name="ptdo.zzxxdz" size="50"  />
						</td>
						<td style="text-align:right;">
							邮政编码：
						</td>
						<td style="text-align: left;">
							<input type="text" class="disabled" id="ptdoyzbm1" name="ptdo.yzbm1" size="10" />
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
						<td class="trs" style="text-align: left;">&nbsp;<input type="text" class="disabled" id="ptdosxrq" value="${fn:substring(ptdo.sxrq,0,10)}" name="ptdo.sxrq" size="10" />
						</td>
						
						<td style="text-align:right;">保险终止日期：</td>
						<td class="trs" style="text-align: left;">&nbsp;<input type="text" class="disabled" id="ptdozzrq" value="${fn:substring(ptdo.zzrq,0,10)}" name="ptdo.zzrq" size="10"/>
						</td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td style="text-align: right;" style="width: 876px height: 21px;">&nbsp;
							<font style="color:red;">*</font>检验日期：
						</td>
						<td align="left" style="width: 196px height: 21px;text-align: left;">&nbsp;<input name="ptdo.yxqz" id="ptdoyxqz" class="disabled" value="${fn:substring(ptdo.yxqz,0,10)}" size="10" type="text"/>
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
						<td colspan="3" align="left" style="width: 196px height: 21px;text-align: left;">&nbsp;<input name="ptdo.tempBz2" id="ptdotempBz2"  class="disabled" size="60" type="text" />
						</td>
						<td>
						</td>
						<td></td>
					</tr>
				</table>
			</div>
<!-- 登记信息结束 ===========================================================-->	


<!-- 技术参数 ===========================================================-->			
			<div id="tableid2" class="tablist" style="text-align: center;height: 420px">
				<!-- 技术参数 -->
				<table border="1" cellpadding="0" cellspacing="0" id="tabCc3" class="edittable">
					<tr>
						<td style="text-align:right;"><font style="color:red;">*</font>车辆识别代号：</td>
						<td class="trs" colspan="3" style="text-align: left;">&nbsp;<input type="text" id="ptdoclsbdh" class="disabled" name="ptdo.clsbdh" size="30" />&nbsp;&nbsp;&nbsp;&nbsp;
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
						<td class="trs" colspan="3" style="text-align: left;">
							&nbsp;<input type="text" class="disabled" readonly="readonly" id="ptdoclxh"  name="ptdo.clxh" size="30" />&nbsp;&nbsp;&nbsp;&nbsp;
							 &nbsp;
						</td>
						<td style="text-align:right;">
							中文品牌：
						</td>
						<td class="trs" style="text-align: left;">&nbsp;<input type="text" class="disabled" id="ptdoclpp1" name="ptdo.clpp1" size="20" />
						</td>
					</tr>
					<tr>
						<td style="text-align:right;">英文品牌：</td>
						<td class="trs"  colspan="3" style="text-align: left;">
						&nbsp;<input type="text" class="disabled" id="ptdoclpp2" name="ptdo.clpp2" size="30"/></td>
					
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
						<td class="trs" colspan="3" style="text-align: left;">&nbsp;<input type="text" class="disabled" id="ptdozzcmc" name="ptdo.zzcmc" size="30"/>
						</td>
						
						<td style="text-align:right;"><font style="color:red;">*</font>发动机号：</td>
						<td class="trs" style="text-align: left;">&nbsp;<input type="text" class="disabled" id="ptdofdjh" name="ptdo.fdjh" size="20" />
						</td>
					</tr>
					
					<tr>
						<td style="text-align:right;"><font style="color:red;">*</font>出厂日期：</td>
						<td style="text-align: left;">&nbsp;<input name="ptdo.ccrq" id="ptdoccrq" class="disabled"  size="10" type="text"/>
						</td>
						
						<td style="text-align:right;">发合格证日期：</td>
						<td class="trs" style="text-align: left;"><input name="ptdo.fhgzrq" id="ptdofhgzrq" class="disabled"  size="10" type="text"/></td>
						
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
					
					
					
					<tr>
						<td id="ptdo.gcjkTdHgzbh" style="text-align:right;">
							<font style="color:red;">*</font>合格证编号：
						</td>
						<td id="ptdo.gcjkTdHgzbhValue" class="trs" style="text-align: left;">&nbsp;<input type="text" class="disabled" id="ptdohgzbh" name="ptdo.hgzbh" size="20" /></td>
						
						<td id="ptdo.gcjkTdJgpzh" style="text-align:right;display: none;"><font style="color:red;">*</font>进口凭证号：</td>
						<td id="ptdo.gcjkTdJgpzhValue" class="trs" style="display: none;" style="text-align: left;">
						&nbsp;<input type="text" class="disabled" id="ptdojkpzhm"  name="ptdo.jkpzhm" size="20" />
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
						&nbsp;<input type="text" class="disabled" id="ptdofdjxh" name="ptdo.fdjxh" size="20"/>
						</td>
						
						<td style="text-align:right;">排量/功率：</td>
						<td class="trs" style="text-align: left;">&nbsp;<input type="text" class="disabled" id="ptdopl" name="ptdo.pl" size="1" />ml
							<input type="text" class="disabled" id="ptdogl"  name="ptdo.gl" size="1"/>kw
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
						<td class="trs" colspan="3" style="text-align: left;">&nbsp;<input name="ptdo.cwkc" type="text" id="ptdocwkc" class="disabled"  size="3"/>
							mm(长)
							<input name="ptdo.cwkk" type="text" class="disabled" id="ptdocwkk" size="3" />
							mm(宽)
							<input name="ptdo.cwkg" type="text" class="disabled" id="ptdocwkg"   size="3"  />
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
						<td class="trs" colspan="3" style="text-align: left;">&nbsp;<input name="ptdo.hxnbcd" type="text" class="disabled" id="ptdohxnbcd"  size="3" />
							mm(长)
							<input name="ptdo.hxnbkd" type="text" class="disabled" id="ptdohxnbkd"  size="3" />
							mm(宽)
							<input name="ptdo.hxnbgd" type="text" class="disabled" id="ptdohxnbgd" size="3" />
							mm(高)
									
						</td>
						
						<td style="text-align:right;">环保达标情况：</td>
						<td class="trs" style="text-align: left;">&nbsp;<input type="text" class="disabled" id="ptdohbdbqk"  name="ptdo.hbdbqk" size="20"/>
						</td>
					</tr>
					<tr>
						<td style="text-align:right;">钢板弹簧片数：</td>
						<td class="trs" style="text-align: left;">&nbsp;<input type="text" class="disabled" id="ptdogbthps" name="ptdo.gbthps" size="10" />片
						</td>
						
						<td style="text-align:right;">轴数：</td>
						<td class="trs" style="text-align: left;" >&nbsp;<input type="text" class="disabled" id="ptdozs" name="ptdo.zs" size="10" />个
						</td>
						
						<td style="text-align:right;">轴距：</td>
						<td class="trs" style="text-align: left;">&nbsp;<input type="text" class="disabled" id="ptdozj"  name="ptdo.zj" size="10" />mm
						</td>
					</tr>
					<tr>
						<td style="text-align:right;">轮胎数：</td>
						<td class="trs" style="text-align: left;">&nbsp;<input type="text" class="disabled" id="ptdolts"  name="ptdo.lts" size="5" />个
						</td>
						
						<td style="text-align:right;">轮胎规格：</td>
						<td class="trs" style="text-align: left;">&nbsp;<input type="text" class="disabled" id="ptdoltgg" name="ptdo.ltgg" size="10" />
						</td>
						
						<td style="text-align:right;">轮距：</td>
						<td class="trs" style="text-align: left;">&nbsp;<input type="text" class="disabled" id="ptdoqlj" name="ptdo.qlj" size="5" />mm(前)
							&nbsp;&nbsp;<input type="text" class="disabled" id="ptdohlj" name="ptdo.hlj" size="5" />mm(后)
						</td>
					</tr>
					<tr>
						<td style="text-align:right;">总质量：</td>
						<td class="trs" style="text-align: left;">&nbsp;<input type="text" class="disabled" id="ptdozzl" name="ptdo.zzl" size="5" />kg
						</td>
						
						<td style="text-align:right;"><font style="color:red;">*</font>整备质量：</td>
						<td class="trs" style="text-align: left;">&nbsp;<input type="text" class="disabled" id="ptdozbzl"  name="ptdo.zbzl" size="5" />kg
						</td>
						
						<td style="text-align:right;">核定载客：</td>
						<td class="trs" style="text-align: left;">&nbsp;<input type="text" class="disabled" id="ptdohdzk"  name="ptdo.hdzk" size="5" />人
						</td>
					</tr>
					<tr>
						<td style="text-align:right;">准牵引总质量：</td>
						<td class="trs" style="text-align: left;">&nbsp;<input type="text" class="disabled" id="ptdozqyzl" name="ptdo.zqyzl" size="5" />个
						</td>
						
						<td style="text-align:right;">核定载质量：</td>
						<td class="trs" style="text-align: left;">&nbsp;<input type="text" class="disabled" id="ptdohdzzl"  name="ptdo.hdzzl" size="5" />个
						</td>
						
						<td style="text-align:right;">驾驶室载客：</td>
						<td class="trs" style="text-align: left;">&nbsp;<input type="text" class="disabled" id="ptdoqpzk" name="ptdo.qpzk" size="5" />人(前)
										&nbsp;&nbsp;<input type="text" class="disabled" id="ptdohpzk"  name="ptdo.hpzk" size="5" />人(后)
						</td>
					</tr>
				</table>
			</div>
<!-- 技术参数结束 ===========================================================-->	
				
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