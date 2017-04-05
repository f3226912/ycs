<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path" value="<%=request.getContextPath()%>"></c:set>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
  <head>   
    <title>电子签名录入更新</title>    
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/content.css" />
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
	
	<style type="text/css">
		.usinfo{ list-style-type: none;}
		.usinfo li{height: 30px; width: auto; text-align: left; font-weight: bold;}
	</style>
	
<script language="vbscript">
	Function inputtext(vv,obj)
	obj.focus()
	Set WshShell = CreateObject("WScript.Shell")
	WshShell.SendKeys(vv)
	Set WshShell = Nothing
	End Function
</script>	
<!-- 手写板JS开始 -->		
 <script type="text/javascript">
 	function contentLoad(){
 		sxinit();
 	}
 
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
		openbnt("dybnt");
	}
	
	//清空签名	
	function Button3_onclick() {
	    sxobj.HWClearPenSign();
	    document.getElementById("img06").src='/ycszhyw/images/cp.gif';
		document.getElementById("img06a").src='/ycszhyw/images/cp.gif';
		$("#file06").val("");
		document.getElementById("file06").outerHTML = document.getElementById("file06").outerHTML;
		closebnt("dybnt");
	}
	function signComplete() {
	    Button14_onclick();
	}
	//流浪图片并加载显示
	function changeImg(){
		var imgUrl = $("#file06").val();
		document.all["img06"].src=imgUrl;
	}
	
	//提交更新
	function qmclick(){
		var qmUrl = $("#file06").val();
		if(qmUrl!=""&&qmUrl!=null){
			$("#sub_form").submit();
			return;
		}else{
			alert("如需更新请签名保存！");
		}
	}
	
	function Return(){
		var url = $("#url").val();
		var uri = "<%=request.getContextPath()%>+'"+url+"'";
		window.location.href=url;
	}
	
</script>
<!-- 手写板读取JS结束 -->
  </head>
 

 <body onload="contentLoad();">
 <div class="content1" style="width: 98%;">
			<div class="roundedBox" id="type1" style="width: 98%;">
 <div class="right" style="width: 95%;">
 <form action="<%=request.getContextPath()%>/yanche/yczd_qmlrSubmit.action" id="sub_form" enctype="multipart/form-data"  target="abc" method="post">
  <iframe id="abc" name="abc" width="0" height="0" style="display: none;"></iframe>
  <input type="hidden" name="message" id="message" value="${request.message}">
  <input type="hidden" name="loginid" id="loginid" value="${request.loginid}">
  <!-- 返回签名用户列表参数 -->
  <input type="hidden" name="url" id="url" value="${request.url}">
  <input type="hidden" name="action" id="action" value="${request.action}">
  <input type="hidden" name="currentpage" id="currentpage" value="${request.currentpage}">
  <input type="hidden" name="orgid" id="orgid" value="${request.orgid}">
  
  <table  class="edittable" width="100%" border="0" cellpadding="0" cellspacing="0" align="center">
  	  <tr>
  	  	<td colspan="3"  align="center"><strong>用户电子签名信息管理</strong></td>
  	  </tr>
  	  <tr>
		<td rowspan="2" height="36" style="border-left: 0;">
			<ul class="usinfo">
				<li>用&nbsp;&nbsp;户&nbsp;&nbsp;ID: &nbsp;&nbsp;${loginid}</li>
				<li>用户姓名: &nbsp;&nbsp;${username}</li>
				<li>部门职位: &nbsp;&nbsp;${orgname}</li>
			</ul>
		</td>
		<td colspan="2" height="30" align="center" style="border-left: 0;">
			<strong>用户电子签名</strong>
		</td>
	  </tr>
	  <tr>
	  		<td align="center" style="border-left: 0">
	  		<s:if test="#request.qmurl != null">
				<img src="<%=request.getContextPath()%>${request.qmurl}?ran=<%=Math.random() %>" id="dzqm" width="450" height="260" border="0">
			</s:if>
			<s:else>
				<img src="/ycszhyw/images/cp.gif" width="300" height="265" border="0">
			</s:else>
	  		</td>
	  </tr>
  	  <tr>
		<td width="35%"; height="36" align="center" style="border-left: 0;">
			手写板签名拍摄区域
		</td>
		<td width="65%"; colspan="2" height="36" align="center" style="border-left: 0;">
			<strong>
			       签字区域：            
            </strong>
            <input type="button" style="cursor:pointer;" onclick="javascript:Button14_onclick();" value="保存图像" class="bnt" />
            <input type="button" style="cursor:pointer;" onclick="javascript:Button3_onclick();" value="重新签名" class="bnt" /><br>
		</td>
	  </tr>
	  <tr>
		<td width="35%" align="center" style="border-left: 0; padding-bottom: 3px;">
			<a id="img06a" href="" title="手写板签名拍摄区域">
				<img src="/ycszhyw/images/cp.gif" id="img06" width="300" height="265" border="0">
			</a>
			<input type="file" name="image" id="file06" value="" class="disabled" onchange="changeImg()" title="请选择手写板签名图片路径:C:\\printzj4.jpg" style="width:200px;" />
			<a href="javascript:Button3_onclick();">清空</a>
		</td>
		<td  width="65%" height="169" colspan="2" align="center" style="border-left: 0; padding-bottom: 3px;">
            <object id="HWPenSign"
                   name="HWPenSign"
                   classid="clsid:E8F5278C-0C72-4561-8F7E-CCBC3E48C2E3"
                   width="520"
                   height="260">
             </object>
		</td>
	  </tr>
	  <tr>
  	  	<td colspan="3" align="center">
  	  		<input type="button" onclick="Return()" class="bnt" value="返回">
  	  		<input type="button" onclick="qmclick()" class="bnt" value="更新签名">
  	  	</td>
  	  </tr>
  </table>
  </form>
</div>
</div>
</div>
  </body>
</html>
