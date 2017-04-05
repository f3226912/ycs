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
				
				$(".h1").hide();
				$("#h11").hide();
			});
			$(".red").click(function(){
				var num=$("input[type=radio][name='zt'][checked]").val();
				if("TB" == num){
					$(".h1").show();
					$("#h11").show();
					$(".h2").hide();
					$("#h22").hide();
				}else{
					$(".h2").show();
					$(".h1").hide();
					$("#h22").show();
					$("#h11").hide();
				}
			});
			
		});
		
		
		//显示打印图片
		function showPrintAreaToShow(photoType,photoType2,sq){
			var ezXxdPrint = window.open('<%=request.getContextPath()%>/pages/ezxfw/showPic.jsp?photoType='+ photoType+'&photoType2='+photoType2+'&sq='+sq);
			
		}
		//显示A4打印图片
		function showPrintAreaToShow3(photoType,sq){
			var ezXxdPrint = window.open('<%=request.getContextPath()%>/pages/ezxfw/showPic3.jsp?photoType='+ photoType+'&sq='+sq);
			
		}
		
		//显示审核图片
		function gettp(tpid){
			var ezXxdPrint = window.open('<%=request.getContextPath()%>/pages/ezxfw/shenheTp.jsp?tpid='+tpid);
			
		}
		//显示打印页面
		function drvshowPrint(printXh){
			window.open('<%=request.getContextPath()%>/ezxfw/ezxfw_drvshowPrint.action?printXh=' + printXh );
		}
		
		function baocun(printXh,shzt){
			var tbyy = $("#tbyy").val();
			var sqlx=$("#sqlx").val();
			if("TB" == shzt){
				var tbyy = checknotnull(document.getElementById("tbyy"),'请输入审核不通过说明');
				if(tbyy != "true"){
					return false;
				}
			}else{
				var fileName1 = checknotnull(document.getElementById("picPath"),'请选择图片！');
				if(fileName1 != "true"){
					return false;
				}
				var fileName= document.getElementById("picPath").value;
				if(fileName!=''){
				    checkPic();
				}
			}
	        $("#ezxfwform").submit();
			
		}
		
		 function checkPic() {
	          var picPath = document.getElementById("picPath").value;;
	          var type = picPath.substring(picPath.lastIndexOf(".") + 1, picPath.length).toLowerCase();
	          if (type != "jpg" && type != "bmp" && type != "gif" && type != "png") {
	              alert("请上传正确的图片格式");          
	               var file =$("#picPath");
	                   file.after(file.clone().val(""));
	                   file.remove();    
	              return false;
	          }
	          return true;
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
					<form action="<%=request.getContextPath()%>/ezxfw/ezxfw_updatePrintChanInfo.action" method="post" id="ezxfwform" enctype="multipart/form-data" >
						<table class="table1" width="100%" border="0"
							cellpadding="0" cellspacing="0">
							<tr>
								<th class="th1" height="32" colspan="6">
									驾驶证信息单信息${editType}
									<input type="button"  class="bnt"  onclick="drvshowPrint('${ezXxdPrint.printXh}'); " value="打印" />
								</th>
							</tr>
							<tr>
								<td class="tds" style="text-align: right;width: 10%">
									互联网ID：&nbsp;
								</td>
								<td class="tdl" style="text-align: left;width: 24%">
									&nbsp;${ezXxdPrint.printXh}
								</td>
								<td class="tds" style="text-align: right;width: 15%">
									业务类型：&nbsp;
								</td>
								<td class="tdl" style="text-align: left;width: 19%">
									&nbsp;${ezXxdPrint.sqlx}
								</td>
								<td class="tds" style="text-align: right;width: 10%">
									申请来源：&nbsp;
								</td>
								<td class="tdl" style="text-align: left;width: 24%">
									&nbsp;${ezXxdPrint.sqly}
								</td>
							</tr>
							<tr>
								<td class="tds" style="text-align: right;">
									申请人姓名：&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									&nbsp;${ezXxdPrint.sqrxm}
								</td>
								<td class="tds" style="text-align: right;">
									申请人身份证明号码：&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									&nbsp;${ezXxdPrint.sqrsfzmhm}
								</td>
								<td class="tds" style="text-align: right;">
									联系电话：&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									&nbsp;${ezXxdPrint.lxdh}
								</td>
							</tr>
							<tr>
								<td class="tds" style="text-align: right;">
									号牌号码：&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									&nbsp;${ezXxdPrint.hphm}
								</td>
								<td class="tds" style="text-align: right;">
									号牌种类：&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									&nbsp;${ezXxdPrint.hpzl}
								</td>
								<td class="tds" style="text-align: right;">
									申请时间：&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									&nbsp;<s:date name="#request.ezXxdPrint.sqsj" format="yyyy-MM-dd"/>
								</td>
							</tr>
							<tr>
								<td class="tds" style="text-align: right;">
									审核状态：&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									&nbsp;${ezXxdPrint.shzt}
								</td>
								<td class="tds" style="text-align: right;">
									退办原因：&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									&nbsp;${ezXxdPrint.tbyy}
								</td>
								<td class="tds" style="text-align: right;">
									申请来源：&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									&nbsp;${ezXxdPrint.sqly}
								</td>
							</tr>
							<tr>
								<td class="tds" style="text-align: right;">
									批次号：&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									&nbsp;${ezXxdPrint.pch}
								</td>
								<td class="tds" style="text-align: right;">
									批次生成时间：&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									&nbsp;<s:date name="#request.ezXxdPrint.pcsj" format="yyyy-MM-dd"/>
								</td>
								<td class="tds" style="text-align: right;">
									批次生成账户：&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									&nbsp;${ezXxdPrint.pczh}
								</td>
							</tr>
							<tr>
								<td class="tds" style="text-align: right;">
									批次生成姓名：&nbsp;
								</td>
								<td class="tdl" style="text-align: left;color: red;">
									&nbsp;${ezXxdPrint.pcxm}
								</td>
								<td class="tds" style="text-align: right;">
									批次部门名称：&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									&nbsp;${ezXxdPrint.pcbmmc}
								</td>
								<td class="tds" style="text-align: right;">
									审核人：&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									&nbsp;${ezXxdPrint.shrzh}
								</td>
							</tr>
							<tr>
								<td class="tds" style="text-align: right;">
									审核人姓名：&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									&nbsp;${ezXxdPrint.shrxm}
								</td>
								<td class="tds" style="text-align: right;">
									审核人部门名称：&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									&nbsp;${ezXxdPrint.shrbmmc}
								</td>
								<td class="tds" style="text-align: right;">
									审核时间：&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									&nbsp;${ezXxdPrint.shsj}
								</td>
							</tr>
							<s:if test="#request.editType == '查看'&&#request.ezXxdPrint.shzt=='审核通过'">
								<tr>
									<td colspan="6" align="center">
										<a href="javascript:void(0);" onclick="javascript:gettp('${ezXxdPrint.tpid }');">点击查看审核上传图片</a>
									</td >
								</tr>
							</s:if>
						<table  class="table1" width="100%" border="0" cellpadding="0" cellspacing="0">
							
							
						</table>
							<table class="table1" width="100%" border="0" cellpadding="0" cellspacing="0">
								<tr>
								<th class="th1" height="32" colspan="4">
									身份证认证图片信息
								</th>
							</tr>
							<tr>
								<td style="width: 25%" height="36" align="center">
									面签证据图片(面签告知书、身份证反面、居住证反面)(认证信息)
								</td>
								<td style="width: 25%" height="36" align="center">
									手写电子签名(认证信息)
								</td>
								<td style="width: 25%" align="center">本人身份证件正面照片(认证信息)
								</td>
								<td style="width: 25%" height="36" align="center">
									本人身份证件反面照片(认证信息)
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
								</td>
								<td style="width: 25%" align="center" >当事人头像(认证信息)
								</td>
								<td style="width: 25%" height="36" align="center">
									面签证件图片(身份证、居住证、驾驶证、行驶证)(认证信息)
								</td >
								
								<td style="width: 25%" height="36" align="center">
									驾驶证照片(认证信息)
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
									</td>
									<td style="width: 25%" height="36" align="center">本人手持身份证图片(认证信息)
										</td>
										<td style="width: 25%" height="36" align="center">
										代办人身份证头像 (认证信息)
									</td>
									<td style="width: 25%" height="36" align="center">
										代办人头像(认证信息)
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
										</td>
										<td style="width: 25%" height="36" align="center">
											车主身份证反面照片(认证信息)
										</td>
										<td style="width: 25%" height="36" align="center">
											车主手持身份证(认证信息)
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
							<tr>
								<td class="tds" colspan="6">
									<div align="center" style="font-size: 14px;">
										<s:if test="#request.editType == '审核'">
											<table class="table1" width="100%" border="0" cellpadding="0" cellspacing="0">
												<tr>
													<td class="tds" style="text-align: right; width: 40%">审核结果：</td>
													<td class="tdl" style="text-align: left; width: 60%"">
														<input type="radio" name="zt" class="red"  value="2" checked="checked"/>通过&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														<input type="radio" name="zt" class="red" value="TB"/>不通过&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													</td>
												</tr>
												<tr class="h1" style="display:none;" >
													<td class="tds" style="text-align: right;">
														审核不通过原因：
													</td>
													<td class="tdl" style="text-align: left;">
														<input type="text" name="tbyy" id="tbyy" />
													</td>
												</tr>
												<tr class="h2">
													<td class="tds" style="text-align: right;">
														审核通过上传图片：
													</td>
													<td class="tdl" style="text-align: left;">
														<input type="file" name="picPath" id="picPath" title="请选择图片" style="width:320px;"/>
													</td>
												</tr>
												<tr>
													<td colspan="2">
														<input id="h22" class="bnt" type="button" value="初审通过" onclick="javascript:baocun('${ezXxdPrint.printXh }','2');" style="cursor:pointer;" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														<input id="h11" class="bnt" type="button" value="审核不通过" onclick="javascript:baocun('${ezXxdPrint.printXh }','TB');" style="cursor:pointer;" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													</td>
												</tr>
												<input type="hidden" name="printXh" id="printXh" value="${ezXxdPrint.printXh }"/>
												<input type="hidden" name="sqlx" id="sqlx" value="${ywlx}"/>
												<input type="hidden" name="ywlx" id="ywlx" value="${ezXxdPrint.sqlx}"/>
											</table>
										</s:if>
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
