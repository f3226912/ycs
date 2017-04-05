<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="com.ycszh.common.PageTag" prefix="pt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
  <head>
    <title>驾驶证业务信息${editType}</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/content.css" />
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
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery/jquery-lightbox-0.5/css/jquery.lightbox-0.5.css" media="screen" />
	<script type="text/javascript">
		var chuli;
		$(document).ready(function(){
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
				$("#img10a").lightBox();
				$("#img11a").lightBox();
				$("#img12a").lightBox();
				$("#img13a").lightBox();
				$("#img14a").lightBox();
				$("#img15a").lightBox();
				$("#img16a").lightBox();
				$("#img17a").lightBox();
				$("#img18a").lightBox();
				$("#img19a").lightBox();
				$("#img20a").lightBox();
				$("#img21a").lightBox();
				$("#img30a").lightBox();
				$("#img31a").lightBox();
			});
		});
		//显示相片回执 图片
		function showPrintAreaToShow2(photoType){
			var print = window.open('<%=request.getContextPath()%>/pages/ezxfw/showPic2.jsp?photoType='+ photoType);

		}
		//显示打印图片
		function showPrintAreaToShow(photoType,photoType2,sq){
			var print = window.open('<%=request.getContextPath()%>/pages/ezxfw/showPic.jsp?photoType='+ photoType+'&photoType2='+photoType2+'&sq='+sq);
			
		}
		//显示A4打印图片
		function showPrintAreaToShow3(photoType,sq){
			var print = window.open('<%=request.getContextPath()%>/pages/ezxfw/showPic3.jsp?photoType='+ photoType+'&sq='+sq);
			
		}
		//显示打印页面
		function drvshowPrint(wwlsh){
			window.open('<%=request.getContextPath()%>/ezxfw/ezxfw_drvshowPrint.action?wwlsh=' + wwlsh );
		}
		function baocun(wwlsh,zt){
			var zhclsm = $("#zhclsm").val();
			if("TB" == zt){
				var sm = checknotnull(document.getElementById("zhclsm"),'请输入审核不通过说明');
				if(sm != "true"){
					return false;
				}
			}
			var jzzno = '${ezDrvLiceChanApp.zzzhm}';
			var xm = '${ezDrvLiceChanApp.xm}';
			var hjszd = '${ezDrvLiceChanApp.hjszd}';
			shenhe(wwlsh,zt,zhclsm);
		}
		
		function xgbaocun(wwlsh,zt){
			var zhclsm = $("#zhclsm").val();
			if("TB" == zt){
				var sm = checknotnull(document.getElementById("zhclsm"),'请输入审核不通过说明');
				if(sm != "true"){
					return false;
				}
			}
			xiugai(wwlsh,zt,zhclsm);
		}
		
		function xiugai(wwlsh,zt,zhclsm){
			$.ajax({
				type:'POST',
				url:'<%=request.getContextPath()%>/ezxfw/ezxfw_updateDrvChanInfo.action',
				data:{wwlsh:wwlsh,zt:zt,sm:zhclsm,ywlx:'${ezDrvLiceChanApp.hblx}'},
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
						    alert("修改成功!");
						    opener.updatezt(wwlsh,zt);
						    window.close();
						}else if(data == 1){
							alert("修改失败!");
						}else{
							alert("系统繁忙,请稍候再试!");
						}
					}else{
						exception(message);
					}
				}
			});
		}
		
		function shenhe(wwlsh,zt,zhclsm){
			$.ajax({
				type:'POST',
				url:'<%=request.getContextPath()%>/ezxfw/ezxfw_updateDrvChanInfo.action',
				data:{wwlsh:wwlsh,zt:zt,sm:zhclsm,ywlx:'${ezDrvLiceChanApp.hblx}'},
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
						    alert("初审成功!");
						    opener.updatezt(wwlsh,zt);
						    window.close();
						}else if(data == 1){
							alert("初审失败!");
						}else{
							alert("系统繁忙,请稍候再试!");
						}
					}else{
						exception(message);
					}
				}
			});
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
	
  </head>
  
  <body style="background:#c7e5ff;">
    <div class="content1" style="width: 100%;">
		  <div class="roundedBox" id="type1" style="width: 95%;">
				<div class="right" style="width: 98%;">
					<form action="<%=request.getContextPath()%>/ezxfw/ezxfw_initDrvChanList.action" method="post" id="ezxfwform">
						<table class="table1" width="100%" border="0"
							cellpadding="0" cellspacing="0">
							<tr>
								<th class="th1" height="32" colspan="6">
									驾驶证业务信息${editType}
									<input type="button"  class="bnt"  onclick="drvshowPrint('${ezDrvLiceChanApp.wwlsh}'); " value="打印" />
								</th>
							</tr>
							<tr>
								<td class="tds" style="text-align: right;width: 10%">
									互联网流水号：&nbsp;
								</td>
								<td class="tdl" style="text-align: left;width: 24%">
									&nbsp;${ezDrvLiceChanApp.wwlsh}
								</td>
								<td class="tds" style="text-align: right;width: 10%">
									业务类型：&nbsp;
								</td>
								<td class="tdl" style="text-align: left;width: 24%">
									&nbsp;${ezDrvLiceChanApp.hblx}
								</td>
								<td class="tds" style="text-align: right;width: 10%">
									&nbsp;
								</td>
								<td class="tdl" style="text-align: left;width: 24%">
									&nbsp;
								</td>
							</tr>
							<tr>
								<td class="tds" style="text-align: right;">
									身份证明名称：&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									&nbsp;${ezDrvLiceChanApp.sfzmmc}
								</td>
								<td class="tds" style="text-align: right;">
									身份证明号码：&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									&nbsp;${ezDrvLiceChanApp.sfzmhm}
								</td>
								<td class="tds" style="text-align: right;">
									驾驶证号码：&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									&nbsp;${ezDrvLiceChanApp.jszhm}
								</td>
							</tr>
							<tr>
								<td class="tds" style="text-align: right;">
									姓名：&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									&nbsp;${ezDrvLiceChanApp.xm}
								</td>
								<td class="tds" style="text-align: right;">
									性别：&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									&nbsp;${ezDrvLiceChanApp.xb}
								</td>
								<td class="tds" style="text-align: right;">
									出生日期：&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									&nbsp;<s:date name="#request.ezDrvLiceChanApp.csrq" format="yyyy-MM-dd"/>
								</td>
							</tr>
							<tr>
								<td class="tds" style="text-align: right;">
									国籍：&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									&nbsp;${ezDrvLiceChanApp.gj}
								</td>
								<td class="tds" style="text-align: right;">
									登记住所地址：&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									&nbsp;${ezDrvLiceChanApp.djzsdz}
								</td>
								<td class="tds" style="text-align: right;">
									联系住所地址：&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									&nbsp;${ezDrvLiceChanApp.lxzsdz}
								</td>
							</tr>
							<tr>
								<td class="tds" style="text-align: right;">
									电子邮箱：&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									&nbsp;${ezDrvLiceChanApp.dzyx}
								</td>
								<td class="tds" style="text-align: right;">
									固定电话：&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									&nbsp;${ezDrvLiceChanApp.gddh}
								</td>
								<td class="tds" style="text-align: right;">
									移动电话：&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									&nbsp;${ezDrvLiceChanApp.yddh}
								</td>
							</tr>
							<tr>
								<td class="tds" style="text-align: right;">
									申请方式：&nbsp;
								</td>
								<td class="tdl" style="text-align: left;color: red;">
									&nbsp;${ezDrvLiceChanApp.sqfs}
								</td>
								<td class="tds" style="text-align: right;">
									相片回执编号：&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									&nbsp;${ezDrvLiceChanApp.xphzbh}
								</td>
								<td class="tds" style="text-align: right;">
									是否体检合格：&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									&nbsp;${ezDrvLiceChanApp.sftjhg}
								</td>
							</tr>
							<tr>
								<td class="tds" style="text-align: right;">
									收件人姓名：&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									&nbsp;${ezDrvLiceChanApp.sjrxm}
								</td>
								<td class="tds" style="text-align: right;">
									收件人地址：&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									&nbsp;${ezDrvLiceChanApp.sjrdz}
								</td>
								<td class="tds" style="text-align: right;">
									收件人手机：&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									&nbsp;${ezDrvLiceChanApp.sjrsj}
								</td>
							</tr>
							<tr>
								<td class="tds" style="text-align: right;">
									身高：&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									&nbsp;${ezDrvLiceChanApp.sg}
								</td>
								<td class="tds" style="text-align: right;">
									左视力：&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									&nbsp;${ezDrvLiceChanApp.zsl}
								</td>
								<td class="tds" style="text-align: right;">
									右视力：&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									&nbsp;${ezDrvLiceChanApp.ysl}
								</td>
							</tr>
							<tr>
								<td class="tds" style="text-align: right;">
									辨色力：&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									&nbsp;${ezDrvLiceChanApp.bsl}
								</td>
								<td class="tds" style="text-align: right;">
									听力：&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									&nbsp;${ezDrvLiceChanApp.tl}
								</td>
								<td class="tds" style="text-align: right;">
									上肢：&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									&nbsp;${ezDrvLiceChanApp.sz}
								</td>
							</tr>
							<tr>
								<td class="tds" style="text-align: right;">
									左下肢：&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									&nbsp;${ezDrvLiceChanApp.zxz}
								</td>
								<td class="tds" style="text-align: right;">
									右下肢：&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									&nbsp;${ezDrvLiceChanApp.yxz}
								</td>
								<td class="tds" style="text-align: right;">
									驱干颈部：&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									&nbsp;${ezDrvLiceChanApp.qgjb}
								</td>
							</tr>
							<tr>
								<td class="tds" style="text-align: right;">
									体检医院名称：&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									&nbsp;${ezDrvLiceChanApp.tjyymc}
								</td>
								<td class="tds" style="text-align: right;">
									体检日期：&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									&nbsp;<s:date name="#request.ezDrvLiceChanApp.tjrq" format="yyyy-MM-dd"/>
								</td>
								<td class="tds" style="text-align: right;">
									&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									&nbsp;
								</td>
							</tr>
							<tr>
								<td class="tds" style="text-align: right;">
									户籍所在地：&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									&nbsp;${ezDrvLiceChanApp.hjszd}
								</td>
								<td class="tds" style="text-align: right;">
									居住证号码：&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									&nbsp;${ezDrvLiceChanApp.zzzhm}
								</td>
								<td class="tds" style="text-align: right;">
									来源标志&nbsp;
								</td>
								<td class="tdl" style="text-align: left;color: red;">
									&nbsp;${ezDrvLiceChanApp.lybz}
								</td>
							</tr>
							<tr>
								<td class="tds" style="text-align: right;">
									统一版流水号：&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									&nbsp;${ezDrvLiceChanApp.tyblsh}
								</td>
								<td class="tds" style="text-align: right;">
									批次号：&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									&nbsp;${ezDrvLiceChanApp.pch}
								</td>
								<td class="tds" style="text-align: right;">
									外网录入时间&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									&nbsp;<s:date name="#request.ezDrvLiceChanApp.wslrsj" format="yyyy-MM-dd"/>
								</td>
							</tr>
							<tr>
								<td class="tds" style="text-align: right;">
									迁入地车管部门：&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									&nbsp;${ezDrvLiceChanApp.qcdcgs}
								</td>
								<td class="tds" style="text-align: right;">
									延期终止日期：&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									&nbsp;<s:date name="#request.ezDrvLiceChanApp.yqzzrq" format="yyyy-MM-dd"/>
								</td>
								<td class="tds" style="text-align: right;">
									延期原因：&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									&nbsp;${ezDrvLiceChanApp.yqyy}
								</td>
							</tr>
							<tr>
								<td class="tds" style="text-align: right;">
									最后处理环节：&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									&nbsp;${ezDrvLiceChanApp.zhclhj}
								</td>
								<td class="tds" style="text-align: right;">
									最后处理状态：&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									&nbsp;${ezDrvLiceChanApp.zhclzt}
								</td>
								<td class="tds" style="text-align: right;">
									最后处理时间&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									&nbsp;<s:date name="#request.ezDrvLiceChanApp.zhclsj" format="yyyy-MM-dd"/>
								</td>
							</tr>
							<tr>
								<td class="tds" style="text-align: right;">
									最后处理人：&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									&nbsp;${ezDrvLiceChanApp.zhclr}
								</td>
								<td class="tds" style="text-align: right;">
									最后处理人姓名：&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									&nbsp;${ezDrvLiceChanApp.zhclrxm}
								</td>
								<td class="tds" style="text-align: right;">
									最后处理人部门名称：&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									&nbsp;${ezDrvLiceChanApp.zhclrbmmc}
								</td>
							</tr>
							<s:if test="#request.ezDrvLiceChanApp.sqfs=='委托他人申请'">
								<tr>
									<td class="tds" style="text-align: right;">
										委托人姓名：&nbsp;
									</td>
									<td class="tdl" style="text-align: left;">
										&nbsp;${ezDzwt.wtXm}
									</td>
									<td class="tds" style="text-align: right;">
										委托人身份证号：&nbsp;
									</td>
									<td class="tdl" style="text-align: left;">
										&nbsp;${ezDzwt.wtSfzmhm}
									</td>
									<td class="tds" style="text-align: right;">
										委托人车牌号码：&nbsp;
									</td>
									<td class="tdl" style="text-align: left;">
										&nbsp;${ezDzwt.wtHphm}
									</td>
								</tr>
								<tr>
									<td class="tds" style="text-align: right;">
										委托号牌种类：&nbsp;
									</td>
									<td class="tdl" style="text-align: left;">
										&nbsp;${ezDzwt.wtHpzl}
									</td>
									<td class="tds" style="text-align: right;">
										委托时间：&nbsp;
									</td>
									<td class="tdl" style="text-align: left;">
										&nbsp;${ezDzwt.wtSj}
									</td>
									<td class="tds" style="text-align: right;">
										业务类型：&nbsp;
									</td>
									<td class="tdl" style="text-align: left;">
										&nbsp;${ezDzwt.wtYwlx}
									</td>
								</tr>
							</s:if>
							<tr>
								<td class="tds" style="text-align: right;">
									最后处理说明：&nbsp;
								</td>
								<td class="tdl" colspan="5" style="text-align: left;">
									&nbsp;${ezDrvLiceChanApp.zhclsm}
								</td>
							</tr>
							<tr>
								<td class="tds" style="text-align: right;">
									处理详细过程：&nbsp;
								</td>
								<td class="tdl" colspan="5" style="text-align: left;">
									&nbsp;${ezDrvLiceChanApp.clxxgc}
								</td>
							</tr>
						<table  class="table1" width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
							<tr>
								<td class="tds" style="text-align: right;" >
									身份证正面图片(申请信息)：&nbsp;
								</td>
								<td style="width: 25%" align="center">
									<s:if test="#request.photolist2.a9 == null || #request.photolist2.a9 == ''">
										<s:if test="#request.photolist2.b9 == null || #request.photolist2.b9 == ''">
											<a id="img09a" href="" title="身份证正面面图片"><img src="<%=request.getContextPath()%>/images/cp.gif" id="sfzxpimgid8" width="210" height="175" border="0"></a>
										</s:if>
										<s:else>
											<a id="img09a" href="<%=request.getContextPath()%>/servlet/sfrzcjImageServlet?pid=${photolist2.b9}" title="身份证正面面图片"><img src="<%=request.getContextPath()%>/servlet/sfrzcjImageServlet?pid=${photolist2.b9}"id="img08" width="210" height="175" border="0"></a>
											<input id="showPicArea" type="button" class="bnt"  style="width: 80px;" onclick="showPrintAreaToShow('${photolist2.b9}','${photolist2.b10}','shzt');" isShow="false"  value="打印" />
										</s:else>
									</s:if>
									<s:else>
										<a id="img09a" href="http://100.100.21.61/cmp_new/view_pic.asp?efid=${photolist2.a9}" title="身份证正面面图片"><img src="http://100.100.21.61/cmp_new/view_pic.asp?efid=${photolist2.a9}"id="img08" width="210" height="175" border="0"></a>
										<input id="showPicArea" type="button" class="bnt"  style="width: 80px;" onclick="showPrintAreaToShow('${photolist2.a9}','${photolist2.a10}','');" isShow="false"  value="打印" />
									</s:else>
								</td>
								<td class="tds" style="text-align: right;" >
									身份证反面面图片(申请信息)：&nbsp;
								</td>
								<td style="width: 25%" align="center" >
									<s:if test="#request.photolist2.a10 == null || #request.photolist2.a10== ''">
										<s:if test="#request.photolist2.b10 == null || #request.photolist2.b10== ''">
											<a id="img10a" href="" title="身份证反面面图片"><img src="<%=request.getContextPath()%>/images/cp.gif" id="sfzxpimgid8" width="210" height="175" border="0"></a>
										</s:if>
										<s:else>
											<a id="img10a" href="<%=request.getContextPath()%>/servlet/sfrzcjImageServlet?pid=${photolist2.b10}" title="身份证反面面图片"><img src="<%=request.getContextPath()%>/servlet/sfrzcjImageServlet?pid=${photolist2.b10}"id="img08" width="210" height="175" border="0"></a>
											<input id="showPicArea" type="button" class="bnt"  style="width: 80px;" onclick="showPrintAreaToShow('${photolist2.b9}','${photolist2.b10}','shzt');" isShow="false"  value="打印" />
										</s:else>
									</s:if>
									<s:else>
										<a id="img10a" href="http://100.100.21.61/cmp_new/view_pic.asp?efid=${photolist2.a10}" title="身份证反面面图片"><img src="http://100.100.21.61/cmp_new/view_pic.asp?efid=${photolist2.a10}"id="img08" width="210" height="175" border="0"></a>
										<input id="showPicArea" type="button" class="bnt"  style="width: 80px;" onclick="showPrintAreaToShow('${photolist2.a9}','${photolist2.a10}','');" isShow="false"  value="打印" />
									</s:else>
								</td>
						   </tr>
						    <%--<s:if test="#request.ezDrvLiceChanApp.hjszd == '非深户'">
							  --%><tr>
						   		<td class="tds" style="text-align: right;" >
									居住证正面图片(申请信息)：&nbsp;
								</td>
								<td style="width: 25%" align="center">
									<s:if test="#request.photolist2.a13 == null || #request.photolist2.a13 == ''">
										<s:if test="#request.photolist2.b13 == null || #request.photolist2.b13 == ''">
											<a id="img13a" href="" title="居住证正面图片"><img src="<%=request.getContextPath()%>/images/cp.gif" id="sfzxpimgid8" width="210" height="175" border="0"></a>
										</s:if>
										<s:else>
											<a id="img13a" href="<%=request.getContextPath()%>/servlet/sfrzcjImageServlet?pid=${photolist2.b13}" title="居住证正面图片"><img src="<%=request.getContextPath()%>/servlet/sfrzcjImageServlet?pid=${photolist2.b13}"id="img08" width="210" height="175" border="0"></a>
											<input id="showPicArea" type="button" class="bnt"  style="width: 80px;" onclick="showPrintAreaToShow('${photolist2.b13}','${photolist2.b14}','shzt');" isShow="false"  value="打印" />
										</s:else>
									</s:if>
									<s:else>
										<a id="img13a" href="http://100.100.21.61/cmp_new/view_pic.asp?efid=${photolist2.a13}" title="居住证正面图片"><img src="http://100.100.21.61/cmp_new/view_pic.asp?efid=${photolist2.a13}"id="img08" width="210" height="175" border="0"></a>
										<input id="showPicArea" type="button" class="bnt"  style="width: 80px;" onclick="showPrintAreaToShow('${photolist2.a13}','${photolist2.a14}','');" isShow="false"  value="打印" />
									</s:else>
								</td>
								<td class="tds" style="text-align: right;" >
									居住证反面面图片(申请信息)：&nbsp;
								</td>
								<td style="width: 25%" align="center" >
									<s:if test="#request.photolist2.a14 == null || #request.photolist2.a14 == ''">
										<s:if test="#request.photolist2.b14 == null || #request.photolist2.b14 == ''">
											<a id="img14a" href="" title="居住证反面面图片"><img src="<%=request.getContextPath()%>/images/cp.gif" id="sfzxpimgid8" width="210" height="175" border="0"></a>
										</s:if> 
										<s:else>
											<a id="img14a" href="<%=request.getContextPath()%>/servlet/sfrzcjImageServlet?pid=${photolist2.b14}" title="居住证反面面图片"><img src="<%=request.getContextPath()%>/servlet/sfrzcjImageServlet?pid=${photolist2.b14}"id="img08" width="210" height="175" border="0"></a>
											<input id="showPicArea" type="button" class="bnt"  style="width: 80px;" onclick="showPrintAreaToShow('${photolist2.b13}','${photolist2.b14}','shzt');" isShow="false"  value="打印" />
										</s:else>
									</s:if> 
									<s:else>
										<a id="img14a" href="http://100.100.21.61/cmp_new/view_pic.asp?efid=${photolist2.a14}" title="居住证反面面图片"><img src="http://100.100.21.61/cmp_new/view_pic.asp?efid=${photolist2.a14}"id="img08" width="210" height="175" border="0"></a>
										<input id="showPicArea" type="button" class="bnt"  style="width: 80px;" onclick="showPrintAreaToShow('${photolist2.a13}','${photolist2.a14}','');" isShow="false"  value="打印" />
									</s:else>
								</td>
						   </tr>
							<tr>
								<td class="tds" style="text-align: right;width: 20%;">
									相片回执照片(申请信息)：&nbsp;
								</td>
								<td style="width: 30%" align="center">
									<s:if test="#request.xphzbh == null || #request.xphzbh == ''">
										<a id="img08a" href="" title="相片回执照片"><img src="<%=request.getContextPath()%>/images/cp.gif" id="sfzxpimgid8" width="210" height="175" border="0"></a>
									</s:if>
									<s:else>
										<a id="img08a" href="<%=request.getContextPath()%>/servlet/zzsxphzImageServlet?pid=${xphzbh}" title="相片回执照片"><img src="<%=request.getContextPath()%>/servlet/zzsxphzImageServlet?pid=${xphzbh}" id="img08" width="210" height="175" border="0"></a>
										<input id="showPicArea" type="button" class="bnt"  style="width: 80px;" onclick="showPrintAreaToShow2('${xphzbh}');" isShow="false"  value="打印" />
									</s:else>
								</td>
								<td class="tds" style="text-align: right;" >
									 新增境外人员临住表图片(申请信息)：&nbsp;
								</td>
								<td style="width: 25%" align="center" >
									<s:if test="#request.photolist2.a31 == null || #request.photolist2.a31 == ''">
										<s:if test="#request.photolist2.b31 == null || #request.photolist2.b31 == ''">
											<a id="img31a" href="" title="进口货物证明书(国产车比传、进口车不传)"><img src="<%=request.getContextPath()%>/images/cp.gif" id="sfzxpimgid8" width="210" height="175" border="0"></a>
										</s:if> 
										<s:else>
											<a id="img31a" href="<%=request.getContextPath()%>/servlet/sfrzcjImageServlet?pid=${photolist2.b31}" title="进口货物证明书(国产车比传、进口车不传)"><img src="<%=request.getContextPath()%>/servlet/sfrzcjImageServlet?pid=${photolist2.b31}"id="img08" width="210" height="175" border="0"></a>
											<input id="showPicArea" type="button" class="bnt"  style="width: 80px;" onclick="showPrintAreaToShow('${photolist2.b31}','','shzt');" isShow="false"  value="打印" />
										</s:else>
									</s:if> 
									<s:else>
										<a id="img31a" href="http://100.100.21.61/cmp_new/view_pic.asp?efid=${photolist2.a31}" title="进口货物证明书(国产车比传、进口车不传)"><img src="http://100.100.21.61/cmp_new/view_pic.asp?efid=${photolist2.a31}"id="img08" width="210" height="175" border="0"></a>
										<input id="showPicArea" type="button" class="bnt"  style="width: 80px;" onclick="showPrintAreaToShow('${photolist2.a31}','','');" isShow="false"  value="打印" />
									</s:else>
								</td>
							</tr>
							<s:if test="#request.ezDrvLiceChanApp.hblx=='驾驶证年审'">
								<tr>
									<td class="tds" style="text-align: right;width: 20%;">
										驾驶证审验教育表：&nbsp;
									</td>
									<td style="width: 30%" align="center">
										<s:if test="#request.photolist2.a30 == null || #request.photolist2.a30 == ''">
											<s:if test="#request.photolist2.b30 == null || #request.photolist2.b30 == ''">
												<a id="img30a" href="" title="驾驶证审验教育表"><img src="<%=request.getContextPath()%>/images/cp.gif" id="sfzxpimgid8" width="210" height="175" border="0"></a>
											</s:if>
											<s:else>
												<a id="img30a" href="<%=request.getContextPath()%>/servlet/sfrzcjImageServlet?pid=${photolist2.b30}" title="驾驶证审验教育表"><img src="<%=request.getContextPath()%>/servlet/sfrzcjImageServlet?pid=${photolist2.b30}" id="img08" width="210" height="175" border="0"></a>
												<input id="showPicArea" type="button" class="bnt"  style="width: 80px;" onclick="showPrintAreaToShow3('${photolist2.b30}','shzt');" isShow="false"  value="打印" />
											</s:else>
										</s:if>
										<s:else>
											<a id="img30a" href="http://100.100.21.61/cmp_new/view_pic.asp?efid=${photolist2.a30}" title="驾驶证审验教育表"><img src="http://100.100.21.61/cmp_new/view_pic.asp?efid=${photolist2.a30}" id="img08" width="210" height="175" border="0"></a>
											<input id="showPicArea" type="button" class="bnt"  style="width: 80px;" onclick="showPrintAreaToShow3('${photolist2.a30}','');" isShow="false"  value="打印" />
										</s:else>
									</td>
									<td class="tds" style="text-align: right;width: 20%;">
									</td>
									<td style="width: 30%" height="36" align="center">
									</td>
								</tr>
							</s:if>
							
						</table>
							<table class="table1" width="100%" border="0" cellpadding="0" cellspacing="0">
								
							</table>
							<div style="height: 220px;overflow: auto;border:1px solid #c7e5ff;">
							<table class="table1" width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<th class="th1" height="32" colspan="4">
									身份证认证图片信息
								</th>
							</tr>
							<tr>
								<td style="width: 25%" height="36" align="center">
									面签证据图片(面签告知书、身份证反面、居住证反面)(认证信息)
									<s:if test="(#request.photolist.a5 != null && #request.photolist.a5 != '')||(#request.photolist3.a5 != null && #request.photolist3.a5 != '')">
											数据来源:${ezDrvLiceChanApp.yhly}
									</s:if>
								</td>
								<td style="width: 25%" height="36" align="center">
									手写电子签名(认证信息)
									<s:if test="(#request.photolist.a15 != null && #request.photolist.a15 != '')||(#request.photolist3.a15 != null && #request.photolist3.a15 != '')">
										数据来源:${ezDrvLiceChanApp.yhly}
									</s:if>
								</td>
								<td style="width: 25%" align="center">本人身份证件正面照片(认证信息)
									<s:if test="(#request.photolist.a9 != null && #request.photolist.a9 != '')||(#request.photolist3.a9 != null && #request.photolist3.a9 != '')">
											数据来源:${ezDrvLiceChanApp.yhly}
									</s:if>
								</td>
								<td style="width: 25%" height="36" align="center">
									本人身份证件反面照片(认证信息)
									<s:if test="(#request.photolist.a10 != null && #request.photolist.a10 != '')||(#request.photolist3.a10 != null && #request.photolist3.a10 != '')">
											数据来源:${ezDrvLiceChanApp.yhly}
									</s:if>
								</td >
								
							</tr>
							 <tr>
								<td style="width: 25%" height="36" align="center" >
									<s:if test="#request.photolist.a5 == null || #request.photolist.a5 == ''">
										<s:if test="#request.photolist3.a5 != null && #request.photolist3.a5 != ''">
											<a id="img05a" href="http://100.100.21.61/cmp_new/view_pic.asp?efid=${photolist3.a5}" title="面签证据图片(面签告知书、身份证反面、居住证反面)"><img src="http://100.100.21.61/cmp_new/view_pic.asp?efid=${photolist3.a5}" id="sfzxpimgid1" width="210" height="175" border="0"></a>
											<input id="showPicArea" type="button" class="bnt"  style="width: 80px;" onclick="showPrintAreaToShow('${photolist3.a5 }','','');" isShow="false"  value="打印" />
										</s:if>
										<s:else>
											<s:if test="#request.photolist.b5 != null && #request.photolist.b5 != ''">
												<a id="img05a" href="<%=request.getContextPath()%>/servlet/sfrzcjImageServlet?pid=${photolist.b5 }" title="面签证据图片(面签告知书、身份证反面、居住证反面)"><img src="<%=request.getContextPath()%>/servlet/sfrzcjImageServlet?pid=${photolist.b5}" id="sfzxpimgid1" width="210" height="175" border="0"></a>
												<input id="showPicArea" type="button" class="bnt"  style="width: 80px;" onclick="showPrintAreaToShow('${photolist.b5 }','','shzt');" isShow="false"  value="打印" />
											</s:if>
											<s:else>
												<a id="img05a" href="" title="面签证据图片(面签告知书、身份证反面、居住证反面)"><img src="<%=request.getContextPath()%>/images/cp.gif" id="sfzxpimgid4" width="210" height="175" border="0"></a>
											</s:else>
										</s:else>
									</s:if>
									<s:else>
										<a id="img05a" href="http://100.100.21.61/cmp_new/view_pic.asp?efid=${photolist.a5}" title="面签证据图片(面签告知书、身份证反面、居住证反面)"><img src="http://100.100.21.61/cmp_new/view_pic.asp?efid=${photolist.a5}" id="img12" width="210" height="175" border="0"></a>
										<input id="showPicArea" type="button" class="bnt"  style="width: 80px;" onclick="showPrintAreaToShow('${photolist.a5 }','','');" isShow="false"  value="打印" />
									</s:else>
								</td>
								<td style="width: 25%" align="center">
									<s:if test="#request.photolist.a15 == null || #request.photolist.a15 == ''">
										<s:if test="#request.photolist3.a15 != null && #request.photolist3.a15 != ''">
											<a id="img15a" href="http://100.100.21.61/cmp_new/view_pic.asp?efid=${photolist3.a15}" title="手写电子签名"><img src="http://100.100.21.61/cmp_new/view_pic.asp?efid=${photolist3.a15}" id="sfzxpimgid1" width="210" height="175" border="0"></a>
											<input id="showPicArea" type="button" class="bnt"  style="width: 80px;" onclick="showPrintAreaToShow('${photolist3.a15}','','');" isShow="false"  value="打印" />
										</s:if>
										<s:else>
											<s:if test="#request.photolist.b15 != null && #request.photolist.b15 != ''">
												<a id="img15a" href="<%=request.getContextPath()%>/servlet/sfrzcjImageServlet?pid=${photolist.b15}" title="手写电子签名"><img src="<%=request.getContextPath()%>/servlet/sfrzcjImageServlet?pid=${photolist.b15}" id="sfzxpimgid1" width="210" height="175" border="0"></a>
												<input id="showPicArea" type="button" class="bnt"  style="width: 80px;" onclick="showPrintAreaToShow('${photolist.b15}','','shzt');" isShow="false"  value="打印" />
											</s:if>
											<s:else>
												<a id="img15a" href="" title="手写电子签名"><img src="<%=request.getContextPath()%>/images/cp.gif" id="sfzxpimgid5" width="210" height="175" border="0"></a>
											</s:else>
										</s:else>
									</s:if>
									<s:else>
										<a id="img15a" href="http://100.100.21.61/cmp_new/view_pic.asp?efid=${photolist.a15}" title="手写电子签名"><img src="http://100.100.21.61/cmp_new/view_pic.asp?efid=${photolist.a15}" id="img13" width="210" height="175" border="0"></a>
										<input id="showPicArea" type="button" class="bnt"  style="width: 80px;" onclick="showPrintAreaToShow('${photolist.a15}','','');" isShow="false"  value="打印" />
									</s:else>
								</td>
								<td style="width: 25%" align="center">
									<s:if test="#request.photolist.a9 == null || #request.photolist.a9 == ''">
										<s:if test="#request.photolist3.a9 != null && #request.photolist3.a9 != ''">
											<a id="img20a" href="http://100.100.21.61/cmp_new/view_pic.asp?efid=${photolist3.a9}" title="本人身份证件正面照片"><img src="http://100.100.21.61/cmp_new/view_pic.asp?efid=${photolist3.a9}" id="sfzxpimgid1" width="210" height="175" border="0"></a>
											<input id="showPicArea" type="button" class="bnt"  style="width: 80px;" onclick="showPrintAreaToShow('${photolist3.a9}','${photolist3.a10}','');" isShow="false"  value="打印" />
										</s:if>
										<s:else>
											<s:if test="#request.photolist.b9 != null && #request.photolist.b9 != ''">
												<a id="img20a" href="<%=request.getContextPath()%>/servlet/sfrzcjImageServlet?pid=${photolist.b9}" title="本人身份证件正面照片"><img src="<%=request.getContextPath()%>/servlet/sfrzcjImageServlet?pid=${photolist.b9}" id="sfzxpimgid1" width="210" height="175" border="0"></a>
												<input id="showPicArea" type="button" class="bnt"  style="width: 80px;" onclick="showPrintAreaToShow('${photolist.b9}','${photolist.b10}','shzt');" isShow="false"  value="打印" />
											</s:if>
											<s:else>
												<a id="img20a" href="" title="本人身份证件正面照片"><img src="<%=request.getContextPath()%>/images/cp.gif" id="sfzxpimgid2" width="210" height="175" border="0"></a>
											</s:else>
										</s:else>
									</s:if>
									<s:else>
										<a id="img20a" href="http://100.100.21.61/cmp_new/view_pic.asp?efid=${photolist.a9}" title="本人身份证件正面照片"><img src="http://100.100.21.61/cmp_new/view_pic.asp?efid=${photolist.a9}" id="img02" width="210" height="175" border="0"></a>
										<input id="showPicArea" type="button" class="bnt"  style="width: 80px;" onclick="showPrintAreaToShow('${photolist.a9}','${photolist.a10}','');" isShow="false"  value="打印" />
									</s:else>
								</td>
								<td style="width: 25%" align="center">
									<s:if test="#request.photolist.a10 == null || #request.photolist.a10 == ''">
										<s:if test="#request.photolist3.a10 != null && #request.photolist3.a10 != ''">
											<a id="img21a" href="http://100.100.21.61/cmp_new/view_pic.asp?efid=${photolist3.a10}" title="本人身份证件反面照片"><img src="http://100.100.21.61/cmp_new/view_pic.asp?efid=${photolist3.a10}" id="sfzxpimgid1" width="210" height="175" border="0"></a>
											<input id="showPicArea" type="button" class="bnt"  style="width: 80px;" onclick="showPrintAreaToShow('${photolist3.a9}','${photolist3.a10}','');" isShow="false"  value="打印" />
										</s:if>
										<s:else>
											<s:if test="#request.photolist.b10 != null && #request.photolist.b10 != ''">
												<a id="img21a" href="<%=request.getContextPath()%>/servlet/sfrzcjImageServlet?pid=${photolist.b10}" title="本人身份证件反面照片"><img src="<%=request.getContextPath()%>/servlet/sfrzcjImageServlet?pid=${photolist.b10}" id="sfzxpimgid1" width="210" height="175" border="0"></a>
												<input id="showPicArea" type="button" class="bnt"  style="width: 80px;" onclick="showPrintAreaToShow('${photolist.b9}','${photolist.b10}','shzt');" isShow="false"  value="打印" />
											</s:if>
											<s:else>
												<a id="img21a" href="" title="本人身份证件反面照片"><img src="<%=request.getContextPath()%>/images/cp.gif" id="sfzxpimgid3" width="210" height="175" border="0"></a>
											</s:else>
										</s:else>
									</s:if>
									<s:else>
										<a id="img21a" href="http://100.100.21.61/cmp_new/view_pic.asp?efid=${photolist.a10}" title="本人身份证件反面照片"><img src="http://100.100.21.61/cmp_new/view_pic.asp?efid=${photolist.a10}" id="img03" width="210" height="175" border="0"></a>
										<input id="showPicArea" type="button" class="bnt"  style="width: 80px;" onclick="showPrintAreaToShow('${photolist.a9}','${photolist.a10}','');" isShow="false"  value="打印" />
									</s:else>
								</td>
							</tr>
							<tr>
								<td style="width: 25%" height="36" align="center">当事人身份证头像(认证信息)
									<s:if test="(#request.photolist.a1 != null && #request.photolist.a1 != '')||(#request.photolist3.a1 != null && #request.photolist3.a1 != '')">
											数据来源:${ezDrvLiceChanApp.yhly}
									</s:if>
								</td>
								<td style="width: 25%" align="center" >当事人头像(认证信息)
									<s:if test="(#request.photolist.a2 != null && #request.photolist.a2 != '')||(#request.photolist3.a2 != null && #request.photolist3.a2 != '')">
											数据来源:${ezDrvLiceChanApp.yhly}
									</s:if>
								</td>
								<td style="width: 25%" height="36" align="center">
									面签证件图片(身份证、居住证、驾驶证、行驶证)(认证信息)
									<s:if test="(#request.photolist.a19 != null && #request.photolist.a19 != '')||(#request.photolist3.a19 != null && #request.photolist3.a19 != '')">
											数据来源:${ezDrvLiceChanApp.yhly}
									</s:if>
								</td >
								
								<td style="width: 25%" height="36" align="center">
									驾驶证照片(认证信息)
									<s:if test="(#request.photolist.a11 != null && #request.photolist.a11 != '')||(#request.photolist3.a11 != null && #request.photolist3.a11 != '')">
											数据来源:${ezDrvLiceChanApp.yhly}
									</s:if>
								</td>
							  </tr>
							  <tr>
							  		<td style="width: 25%" align="center">
										<s:if test="#request.photolist.a1 == null || #request.photolist.a1 == ''">
											<s:if test="#request.photolist3.a1 != null && #request.photolist3.a1 != ''">
												<a id="img01a" href="http://100.100.21.61/cmp_new/view_pic.asp?efid=${photolist3.a1}" title="当事人身份证头像"><img src="http://100.100.21.61/cmp_new/view_pic.asp?efid=${photolist3.a1}" id="sfzxpimgid1" width="210" height="175" border="0"></a>
												<input id="showPicArea" type="button" class="bnt"  style="width: 80px;" onclick="showPrintAreaToShow('${photolist3.a1}','','');" isShow="false"  value="打印" />
											</s:if>
											<s:else>
												<s:if test="#request.photolist.b1 != null && #request.photolist.b1 != ''">
													<a id="img01a" href="<%=request.getContextPath()%>/servlet/sfrzcjImageServlet?pid=${photolist.b1}" title="当事人身份证头像"><img src="<%=request.getContextPath()%>/servlet/sfrzcjImageServlet?pid=${photolist.b1}" id="sfzxpimgid1" width="210" height="175" border="0"></a>
													<input id="showPicArea" type="button" class="bnt"  style="width: 80px;" onclick="showPrintAreaToShow('${photolist.b1}','','shzt');" isShow="false"  value="打印" />
												</s:if>
												<s:else>
													<a id="img01a" href="" title="当事人身份证头像"><img src="<%=request.getContextPath()%>/images/cp.gif" id="sfzxpimgid2" width="210" height="175" border="0"></a>
												</s:else>
											</s:else>
										</s:if>
										<s:else>
											<a id="img01a" href="http://100.100.21.61/cmp_new/view_pic.asp?efid=${photolist.a1}" title="当事人身份证头像"><img src="http://100.100.21.61/cmp_new/view_pic.asp?efid=${photolist.a1}"  id="img09" width="210" height="175" border="0"></a>
											<input id="showPicArea" type="button" class="bnt"  style="width: 80px;" onclick="showPrintAreaToShow('${photolist.a1}','','');" isShow="false"  value="打印" />
										</s:else>
									</td>
									<td style="width: 25%" align="center">
										<s:if test="#request.photolist.a2 == null || #request.photolist.a2 == ''">
											<s:if test="#request.photolist3.a2 != null && #request.photolist3.a2 != ''">
												<a id="img02a" href="http://100.100.21.61/cmp_new/view_pic.asp?efid=${photolist3.a2}" title="当事人头像"><img src="http://100.100.21.61/cmp_new/view_pic.asp?efid=${photolist3.a2}" id="sfzxpimgid1" width="210" height="175" border="0"></a>
												<input id="showPicArea" type="button" class="bnt"  style="width: 80px;" onclick="showPrintAreaToShow('${photolist3.a2}','','');" isShow="false"  value="打印" />
											</s:if>
											<s:else>
												<s:if test="#request.photolist.b2 != null && #request.photolist.b2 != ''">
													<a id="img02a" href="<%=request.getContextPath()%>/servlet/sfrzcjImageServlet?pid=${photolist.b2}" title="当事人头像"><img src="<%=request.getContextPath()%>/servlet/sfrzcjImageServlet?pid=${photolist.b2}" id="sfzxpimgid1" width="210" height="175" border="0"></a>
													<input id="showPicArea" type="button" class="bnt"  style="width: 80px;" onclick="showPrintAreaToShow('${photolist.b2}','','shzt');" isShow="false"  value="打印" />
												</s:if>
												<s:else>
													<a id="img02a" href="" title="当事人头像"><img src="<%=request.getContextPath()%>/images/cp.gif" id="sfzxpimgid2" width="210" height="175" border="0"></a>
												</s:else>
											</s:else>
										</s:if>
										<s:else>
											<a id="img02a" href="http://100.100.21.61/cmp_new/view_pic.asp?efid=${photolist.a2}" title="当事人头像"><img src="http://100.100.21.61/cmp_new/view_pic.asp?efid=${photolist.a2}" id="img10" width="210" height="175" border="0"></a>
											<input id="showPicArea" type="button" class="bnt"  style="width: 80px;" onclick="showPrintAreaToShow('${photolist.a2}','','');" isShow="false"  value="打印" />
										</s:else>
									</td>
									<td style="width: 25%" align="center">
										<s:if test="#request.photolist.a19 == null || #request.photolist.a19 == ''">
											<s:if test="#request.photolist3.a19 != null && #request.photolist3.a19 != ''">
												<a id="img19a" href="http://100.100.21.61/cmp_new/view_pic.asp?efid=${photolist3.a19}" title="面签证件图片（身份证、居住证、驾驶证、行驶证）"><img src="http://100.100.21.61/cmp_new/view_pic.asp?efid=${photolist3.a19}" id="sfzxpimgid1" width="210" height="175" border="0"></a>
												<input id="showPicArea" type="button" class="bnt"  style="width: 80px;" onclick="showPrintAreaToShow('${photolist3.a19}','','');" isShow="false"  value="打印" />
											</s:if>
											<s:else>
												<s:if test="#request.photolist.b19 != null && #request.photolist.b19 != ''">
													<a id="img19a" href="<%=request.getContextPath()%>/servlet/sfrzcjImageServlet?pid=${photolist.b19}" title="面签证件图片（身份证、居住证、驾驶证、行驶证）"><img src="<%=request.getContextPath()%>/servlet/sfrzcjImageServlet?pid=${photolist.b19}" id="sfzxpimgid1" width="210" height="175" border="0"></a>
													<input id="showPicArea" type="button" class="bnt"  style="width: 80px;" onclick="showPrintAreaToShow('${photolist.b19}','','shzt');" isShow="false"  value="打印" />
												</s:if>
												<s:else>
													<a id="img19a" href="" title="面签证件图片（身份证、居住证、驾驶证、行驶证）"><img src="<%=request.getContextPath()%>/images/cp.gif" id="sfzxpimgid3" width="210" height="175" border="0"></a>
												</s:else>
											</s:else>
										</s:if>
										<s:else>
											<a id="img19a" href="http://100.100.21.61/cmp_new/view_pic.asp?efid=${photolist.a19}" title="面签证件图片（身份证、居住证、驾驶证、行驶证）"><img src="http://100.100.21.61/cmp_new/view_pic.asp?efid=${photolist.a19}" id="img11" width="210" height="175" border="0"></a>
											<input id="showPicArea" type="button" class="bnt"  style="width: 80px;" onclick="showPrintAreaToShow('${photolist.a19}','','');" isShow="false"  value="打印" />
										</s:else>
									</td>
									<td style="width: 25%" height="36" align="center">
										<s:if test="#request.photolist.a11 == null || #request.photolist.a11 == ''">
											<s:if test="#request.photolist3.a11 != null && #request.photolist3.a11 != ''">
												<a id="img11a" href="http://100.100.21.61/cmp_new/view_pic.asp?efid=${photolist3.a11}" title="驾驶证照片"><img src="http://100.100.21.61/cmp_new/view_pic.asp?efid=${photolist3.a11}" id="sfzxpimgid1" width="210" height="175" border="0"></a>
												<input id="showPicArea" type="button" class="bnt"  style="width: 80px;" onclick="showPrintAreaToShow('${photolist3.a11}','','');" isShow="false"  value="打印" />
											</s:if>
											<s:else>
												<s:if test="#request.photolist.b11 != null && #request.photolist.b11 != ''">
													<a id="img11a" href="<%=request.getContextPath()%>/servlet/sfrzcjImageServlet?pid=${photolist.b11}" title="驾驶证照片"><img src="<%=request.getContextPath()%>/servlet/sfrzcjImageServlet?pid=${photolist.b11}" id="sfzxpimgid1" width="210" height="175" border="0"></a>
													<input id="showPicArea" type="button" class="bnt"  style="width: 80px;" onclick="showPrintAreaToShow('${photolist.b11}','','');" isShow="false"  value="打印" />
												</s:if>
												<s:else>
													<a id="img11a" href="" title="驾驶证照片"><img src="<%=request.getContextPath()%>/images/cp.gif" id="sfzxpimgid4" width="210" height="175" border="0"></a>
												</s:else>
											</s:else>
										</s:if>
										<s:else>
											<a id="img11a" href="http://100.100.21.61/cmp_new/view_pic.asp?efid=${photolist.a11}" title="驾驶证照片"><img src="http://100.100.21.61/cmp_new/view_pic.asp?efid=${photolist.a11}" id="img04" width="210" height="175" border="0"></a>
											<input id="showPicArea" type="button" class="bnt"  style="width: 80px;" onclick="showPrintAreaToShow('${photolist.a11}','','shzt');" isShow="false"  value="打印" />
										</s:else>
									</td>
								</tr>
								<tr>
									<td style="width: 25%" height="36" align="center">
										行驶证照片(认证信息)
										<s:if test="(#request.photolist.a12 != null && #request.photolist.a12 != '')||(#request.photolist3.a12 != null && #request.photolist3.a12 != '')">
											数据来源:${ezDrvLiceChanApp.yhly}
										</s:if>
									</td>
									<td style="width: 25%" height="36" align="center">本人手持身份证图片(认证信息)
										<s:if test="(#request.photolist.a6 != null && #request.photolist.a6 != '')||(#request.photolist3.a6 != null && #request.photolist3.a6 != '')">
												数据来源:${ezDrvLiceChanApp.yhly}
										</s:if>
										</td>
										<td style="width: 25%" height="36" align="center">
										代办人身份证头像 (认证信息)
										<s:if test="(#request.photolist.a3 != null && #request.photolist.a3 != '')||(#request.photolist3.a3 != null && #request.photolist3.a3 != '')">
											数据来源:${ezDrvLiceChanApp.yhly}
										</s:if>
									</td>
									<td style="width: 25%" height="36" align="center">
										代办人头像(认证信息)
										<s:if test="(#request.photolist.a4 != null && #request.photolist.a4 != '')||(#request.photolist3.a4 != null && #request.photolist3.a4 != '')">
											数据来源:${ezDrvLiceChanApp.yhly}
										</s:if>
									</td>
								</tr>
								<tr>
									<td style="width: 25%" align="center">
										<s:if test="#request.photolist.a12 == null || #request.photolist.a12 == ''">
											<s:if test="#request.photolist3.a12 != null && #request.photolist3.a12 != ''">
												<a id="img12a" href="http://100.100.21.61/cmp_new/view_pic.asp?efid=${photolist3.a12}" title="行驶证照片"><img src="http://100.100.21.61/cmp_new/view_pic.asp?efid=${photolist3.a12}" id="sfzxpimgid1" width="210" height="175" border="0"></a>
												<input id="showPicArea" type="button" class="bnt"  style="width: 80px;" onclick="showPrintAreaToShow('${photolist3.a12}','','');" isShow="false"  value="打印" />
											</s:if>
											<s:else>
												<s:if test="#request.photolist.b12 != null && #request.photolist.b12 != ''">
													<a id="img12a" href="<%=request.getContextPath()%>/servlet/sfrzcjImageServlet?pid=${photolist.b12}" title="行驶证照片"><img src="<%=request.getContextPath()%>/servlet/sfrzcjImageServlet?pid=${photolist.b12}" id="sfzxpimgid1" width="210" height="175" border="0"></a>
													<input id="showPicArea" type="button" class="bnt"  style="width: 80px;" onclick="showPrintAreaToShow('${photolist.b12}','','shzt');" isShow="false"  value="打印" />
												</s:if>
												<s:else>
													<a id="img12a" href="" title="行驶证照片"><img src="<%=request.getContextPath()%>/images/cp.gif" id="sfzxpimgid5" width="210" height="175" border="0"></a>
												</s:else>
											</s:else>
										</s:if>
										<s:else>
											<a id="img12a" href="http://100.100.21.61/cmp_new/view_pic.asp?efid=${photolist.a12}" title="行驶证照片"><img src="http://100.100.21.61/cmp_new/view_pic.asp?efid=${photolist.a12}" id="img05" width="210" height="175" border="0"></a>
											<input id="showPicArea" type="button" class="bnt"  style="width: 80px;" onclick="showPrintAreaToShow('${photolist.a12}','','');" isShow="false"  value="打印" />
										</s:else>
									</td>
									<td style="width: 25%" align="center">
										<s:if test="#request.photolist.a6 == null || #request.photolist.a6 == ''">
											<s:if test="#request.photolist3.a6 != null && #request.photolist3.a6 != ''">
												<a id="img06a" href="http://100.100.21.61/cmp_new/view_pic.asp?efid=${photolist3.a6}" title="本人手持身份证图片"><img src="http://100.100.21.61/cmp_new/view_pic.asp?efid=${photolist3.a6}" id="sfzxpimgid1" width="210" height="175" border="0"></a>
												<input id="showPicArea" type="button" class="bnt"  style="width: 80px;" onclick="showPrintAreaToShow('${photolist3.a6}','','');" isShow="false"  value="打印" />
											</s:if>
											<s:else>
												<s:if test="#request.photolist.b6 != null && #request.photolist.b6 != ''">
													<a id="img06a" href="<%=request.getContextPath()%>/servlet/sfrzcjImageServlet?pid=${photolist.b6}" title="本人手持身份证图片"><img src="<%=request.getContextPath()%>/servlet/sfrzcjImageServlet?pid=${photolist.b6}" id="sfzxpimgid1" width="210" height="175" border="0"></a>
													<input id="showPicArea" type="button" class="bnt"  style="width: 80px;" onclick="showPrintAreaToShow('${photolist.b6}','','');" isShow="false"  value="打印" />
												</s:if>
												<s:else>
													<a id="img06a" href="" title="本人手持身份证图片"><img src="<%=request.getContextPath()%>/images/cp.gif" id="sfzxpimgid1" width="210" height="175" border="0"></a>
												</s:else>
											</s:else>
										</s:if>
										<s:else>
											<a id="img06a" href="http://100.100.21.61/cmp_new/view_pic.asp?efid=${photolist.a6}" title="本人手持身份证图片"><img src="http://100.100.21.61/cmp_new/view_pic.asp?efid=${photolist.a6}" id="img01" width="210" height="175" border="0"></a>
											<input id="showPicArea" type="button" class="bnt"  style="width: 80px;" onclick="showPrintAreaToShow('${photolist.a6}','','shzt');" isShow="false"  value="打印" />
										</s:else>
									</td>
									<td style="width: 25%" align="center">
										<s:if test="#request.photolist.a3 == null || #request.photolist.a3 == ''">
											<s:if test="#request.photolist3.a3 != null && #request.photolist3.a3 != ''">
												<a id="img03a" href="http://100.100.21.61/cmp_new/view_pic.asp?efid=${photolist3.a3}" title="代办人身份证头像"><img src="http://100.100.21.61/cmp_new/view_pic.asp?efid=${photolist3.a3}" id="sfzxpimgid1" width="210" height="175" border="0"></a>
												<input id="showPicArea" type="button" class="bnt"  style="width: 80px;" onclick="showPrintAreaToShow('${photolist3.a3}','','');" isShow="false"  value="打印" />
											</s:if>
											<s:else>
												<s:if test="#request.photolist.b3 != null && #request.photolist.b3 != ''">
													<a id="img03a" href="<%=request.getContextPath()%>/servlet/sfrzcjImageServlet?pid=${photolist.b3}" title="代办人身份证头像"><img src="<%=request.getContextPath()%>/servlet/sfrzcjImageServlet?pid=${photolist.b3}" id="sfzxpimgid1" width="210" height="175" border="0"></a>
													<input id="showPicArea" type="button" class="bnt"  style="width: 80px;" onclick="showPrintAreaToShow('${photolist.b3}','','shzt');" isShow="false"  value="打印" />
												</s:if>
												<s:else>
													<a id="img03a" href="" title="代办人身份证头像"><img src="<%=request.getContextPath()%>/images/cp.gif" id="sfzxpimgid6" width="210" height="175" border="0"></a>
												</s:else>
											</s:else>
										</s:if>
										<s:else>
											<a id="img03a" href="http://100.100.21.61/cmp_new/view_pic.asp?efid=${photolist.a3}" title="代办人身份证头像"><img src="http://100.100.21.61/cmp_new/view_pic.asp?efid=${photolist.a3}" id="img14" width="210" height="175" border="0"></a>
											<input id="showPicArea" type="button" class="bnt"  style="width: 80px;" onclick="showPrintAreaToShow('${photolist.a3}','','');" isShow="false"  value="打印" />
										</s:else>
									</td>
									<td style="width: 25%" align="center">
										<s:if test="#request.photolist.a4 == null || #request.photolist.a4 == ''">
											<s:if test="#request.photolist3.a4 != null && #request.photolist3.a4 != ''">
												<a id="img04a" href="http://100.100.21.61/cmp_new/view_pic.asp?efid=${photolist3.a4}" title="代办人头像"><img src="http://100.100.21.61/cmp_new/view_pic.asp?efid=${photolist3.a4}" id="sfzxpimgid1" width="210" height="175" border="0"></a>
												<input id="showPicArea" type="button" class="bnt"  style="width: 80px;" onclick="showPrintAreaToShow('${photolist3.a4}','','');" isShow="false"  value="打印" />
											</s:if>
											<s:else>
												<s:if test="#request.photolist.b4 != null && #request.photolist.b4 != ''">
													<a id="img04a" href="<%=request.getContextPath()%>/servlet/sfrzcjImageServlet?pid=${photolist.b4}" title="代办人头像"><img src="<%=request.getContextPath()%>/servlet/sfrzcjImageServlet?pid=${photolist.b4}" id="sfzxpimgid1" width="210" height="175" border="0"></a>
													<input id="showPicArea" type="button" class="bnt"  style="width: 80px;" onclick="showPrintAreaToShow('${photolist.b4}','','shzt');" isShow="false"  value="打印" />
												</s:if>
												<s:else>
													<a id="img04a" href="" title="代办人头像"><img src="<%=request.getContextPath()%>/images/cp.gif" id="sfzxpimgid7" width="210" height="175" border="0"></a>
												</s:else>
											</s:else>
										</s:if>
										<s:else>
											<a id="img04a" href="http://100.100.21.61/cmp_new/view_pic.asp?efid=${photolist.a4}" title="代办人头像"><img src="http://100.100.21.61/cmp_new/view_pic.asp?efid=${photolist.a4}" id="img15" width="210" height="175" border="0"></a>
											<input id="showPicArea" type="button" class="bnt"  style="width: 80px;" onclick="showPrintAreaToShow('${photolist.a4}','','');" isShow="false"  value="打印" />
										</s:else>
									</td>
								</tr>
								<s:if test="#request.ezDrvLiceChanApp.sqfs!='本人申请'">
									<tr>
										<td style="width: 25%" height="36" align="center">
											车主身份证正面照片(认证信息)
											<s:if test="(#request.photolist.a16 != null && #request.photolist.a16 != '')||(#request.photolist3.a16 != null && #request.photolist3.a16 != '')">
												数据来源:${ezDrvLiceChanApp.yhly}
											</s:if>
										</td>
										<td style="width: 25%" height="36" align="center">
											车主身份证反面照片(认证信息)
											<s:if test="(#request.photolist.a17 != null && #request.photolist.a17 != '')||(#request.photolist3.a17 != null && #request.photolist3.a17 != '')">
												数据来源:${ezDrvLiceChanApp.yhly}
											</s:if>
										</td>
										<td style="width: 25%" height="36" align="center">
											车主手持身份证(认证信息)
											<s:if test="(#request.photolist.a18 != null && #request.photolist.a18 != '')||(#request.photolist3.a18 != null && #request.photolist3.a18 != '')">
												数据来源:${ezDrvLiceChanApp.yhly}
											</s:if>
										</td>
										<td style="width: 25%" height="36" align="center">
										</td>
									</tr>
									<tr>
										<td style="width: 25%" align="center">
											<s:if test="#request.photolist.a16 == null || #request.photolist.a16 == ''">
												<s:if test="#request.photolist3.a16 != null && #request.photolist3.a16 != ''">
													<a id="img16a" href="http://100.100.21.61/cmp_new/view_pic.asp?efid=${photolist3.a16}" title="车主身份证正面照片"><img src="http://100.100.21.61/cmp_new/view_pic.asp?efid=${photolist3.a16}" id="sfzxpimgid1" width="210" height="175" border="0"></a>
													<input id="showPicArea" type="button" class="bnt"  style="width: 80px;" onclick="showPrintAreaToShow('${photolist3.a16}','${photolist3.a17}','');" isShow="false"  value="打印" />
												</s:if>
												<s:else>
													<s:if test="#request.photolist.b16 != null && #request.photolist.b16 != ''">
														<a id="img16a" href="<%=request.getContextPath()%>/servlet/sfrzcjImageServlet?pid=${photolist.b16}" title="车主身份证正面照片"><img src="<%=request.getContextPath()%>/servlet/sfrzcjImageServlet?pid=${photolist.b16}" id="sfzxpimgid1" width="210" height="175" border="0"></a>
														<input id="showPicArea" type="button" class="bnt"  style="width: 80px;" onclick="showPrintAreaToShow('${photolist.b16}','${photolist.b17}','shzt');" isShow="false"  value="打印" />
													</s:if>
													<s:else>
														<a id="img16a" href="" title="车主身份证正面照片"><img src="<%=request.getContextPath()%>/images/cp.gif" id="sfzxpimgid6" width="210" height="175" border="0"></a>
													</s:else>
												</s:else>
											</s:if>
											<s:else>
												<a id="img16a" href="http://100.100.21.61/cmp_new/view_pic.asp?efid=${photolist.a16}" title="车主身份证正面照片"><img src="http://100.100.21.61/cmp_new/view_pic.asp?efid=${photolist.a16 }" id="img06" width="210" height="175" border="0"></a>
												<input id="showPicArea" type="button" class="bnt"  style="width: 80px;" onclick="showPrintAreaToShow('${photolist.a16}','${photolist.a17}','');" isShow="false"  value="打印" />
											</s:else>
										</td>
										<td style="width: 25%" align="center">
											<s:if test="#request.photolist.a17 == null || #request.photolist.a17 == ''">
												<s:if test="#request.photolist3.a17 != null && #request.photolist3.a17 != ''">
													<a id="img17a" href="http://100.100.21.61/cmp_new/view_pic.asp?efid=${photolist3.a17}" title="车主身份证反面照片"><img src="http://100.100.21.61/cmp_new/view_pic.asp?efid=${photolist3.a17}" id="sfzxpimgid1" width="210" height="175" border="0"></a>
													<input id="showPicArea" type="button" class="bnt"  style="width: 80px;" onclick="showPrintAreaToShow('${photolist3.a16}','${photolist3.a17}','');" isShow="false"  value="打印" />
												</s:if>
												<s:else>
													<s:if test="#request.photolist.b17 != null && #request.photolist.b17 != ''">
														<a id="img17a" href="<%=request.getContextPath()%>/servlet/sfrzcjImageServlet?pid=${photolist.b17}" title="车主身份证反面照片"><img src="<%=request.getContextPath()%>/servlet/sfrzcjImageServlet?pid=${photolist.b17}" id="sfzxpimgid1" width="210" height="175" border="0"></a>
														<input id="showPicArea" type="button" class="bnt"  style="width: 80px;" onclick="showPrintAreaToShow('${photolist.b16}','${photolist.b17}','shzt');" isShow="false"  value="打印" />
													</s:if>
													<s:else>
														<a id="img17a" href="" title="车主身份证反面照片"><img src="<%=request.getContextPath()%>/images/cp.gif" id="sfzxpimgid7" width="210" height="175" border="0"></a>
												</s:else>
												</s:else>
											</s:if>
											<s:else>
												<a id="img17a" href="http://100.100.21.61/cmp_new/view_pic.asp?efid=${photolist.a17}" title="车主身份证反面照片"><img src="http://100.100.21.61/cmp_new/view_pic.asp?efid=${photolist.a17}" id="img07" width="210" height="175" border="0"></a>
												<input id="showPicArea" type="button" class="bnt"  style="width: 80px;" onclick="showPrintAreaToShow('${photolist.a16}','${photolist.a17}','');" isShow="false"  value="打印" />
											</s:else>
										</td>
										<td style="width: 25%" align="center">
											<s:if test="#request.photolist.a18 == null || #request.photolist.a18 == ''">
												<s:if test="#request.photolist3.a18 != null && #request.photolist3.a18 != ''">
													<a id="img18a" href="http://100.100.21.61/cmp_new/view_pic.asp?efid=${photolist3.a18}" title="车主手持身份证"><img src="http://100.100.21.61/cmp_new/view_pic.asp?efid=${photolist3.a18}" id="sfzxpimgid1" width="210" height="175" border="0"></a>
													<input id="showPicArea" type="button" class="bnt"  style="width: 80px;" onclick="showPrintAreaToShow('${photolist3.a18}','','');" isShow="false"  value="打印" />
												</s:if>
												<s:else>
													<s:if test="#request.photolist.b18 != null && #request.photolist.b18 != ''">
														<a id="img18a" href="<%=request.getContextPath()%>/servlet/sfrzcjImageServlet?pid=${photolist.b8}" title="车主手持身份证"><img src="<%=request.getContextPath()%>/servlet/sfrzcjImageServlet?pid=${photolist.b8}" id="sfzxpimgid1" width="210" height="175" border="0"></a>
														<input id="showPicArea" type="button" class="bnt"  style="width: 80px;" onclick="showPrintAreaToShow('${photolist.b18}','','shzt');" isShow="false"  value="打印" />
													</s:if>
													<s:else>
														<a id="img18a" href="" title="车主手持身份证"><img src="<%=request.getContextPath()%>/images/cp.gif" id="sfzxpimgid8" width="210" height="175" border="0"></a>
													</s:else>
												</s:else>
											</s:if>
											<s:else>
												<a id="img18a" href="http://100.100.21.61/cmp_new/view_pic.asp?efid=${photolist.a18}" title="车主手持身份证"><img src="http://100.100.21.61/cmp_new/view_pic.asp?efid=${photolist.a18}" id="img08" width="210" height="175" border="0"></a>
												<input id="showPicArea" type="button" class="bnt"  style="width: 80px;" onclick="showPrintAreaToShow('${photolist.a18}','','');" isShow="false"  value="打印" />
											</s:else>
										</td>
										<td style="width: 25%" height="36" align="center">
										</td>
									</tr>
								</s:if>
							</table>
							</div>
							<tr>
								<td class="tds" colspan="6">
									<div align="center" style="font-size: 14px;">
										<s:if test="#request.editType == '审核'">
						    				<input class="bnt" type="button" value="初审通过" onclick="javascript:baocun('${ezDrvLiceChanApp.wwlsh }','1');" style="cursor:pointer;" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<input class="bnt" type="button" value="初审不通过" onclick="javascript:baocun('${ezDrvLiceChanApp.wwlsh }','TB');" style="cursor:pointer;" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											审核不通过原因：<input type="text" name="zhclsm" id="zhclsm" />
						    			</s:if>
						    			<s:elseif test="#request.editType =='修改'">
						    				<s:if test="#request.ezDrvLiceChanApp.zhclzt=='退办'">
							    				<input class="bnt" type="button" value="初审通过" onclick="javascript:xgbaocun('${ezDrvLiceChanApp.wwlsh }','1');" style="cursor:pointer;" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						    				</s:if>
						    				<s:if test="#request.ezDrvLiceChanApp.zhclzt=='初审通过'">
												<input class="bnt" type="button" value="退办" onclick="javascript:xgbaocun('${ezDrvLiceChanApp.wwlsh }','TB');" style="cursor:pointer;" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						    					退办原因：<input type="text" name="zhclsm" id="zhclsm" />
						    				</s:if>
 						    			</s:elseif>
						    			<s:else>
						    				<input type="button" onclick="javascript:window.close();" value="关闭" style="margin-left: 200px;cursor:pointer;width: 79px;height: 28px;border: none;background: url(<%=request.getContextPath()%>/images/an2.gif) no-repeat;color: #FFFFFF;font-weight: bold;">
						    			</s:else>
									</div>
								</td>
							</tr>
						</table>
					</form>
				</div>
				<div class="corner topLeft"></div>
				<div class="corner topRight"></div>
			</div>
		</div>
  </body>
</html>
