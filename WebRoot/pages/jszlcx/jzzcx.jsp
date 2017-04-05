<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="com.ycszh.common.PageTag" prefix="pt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<title>居住证信息查询</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/jquery-1.8.2.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/myCss.css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/jquery/js/ztreecss/zTreeStyle/zTreeStyle.css" type="text/css"/>

<style type="text/css">
	.ztree li span.button.addz {margin-left:2px; margin-right: -1px; background-position:-144px 0; vertical-align:top; *vertical-align:middle}
	div.zTreeDemoBackground {width:250px;height:250px;text-align:center;}
	ul.ztree {margin-top: 10px;border: 1px solid #617775;background: #f0f6e4;width:300px;height:400px;overflow-y:auto;overflow-x:auto;}
</style>
<script type="text/javascript">

	/*$(document).ready(function() {
		var xm = $('#ceshi').val();
		if (xm) {
		  $('#a').show();
		} else {
		  $('#a').hide();
		}    
	});*/
	
	function Click() {	
		var idno = $('#idno').val();
		var xm = $('#xm').val();
		if(idno == "" || xm == ""){
			alert("身份证号和姓名不能为空！");
			return false;
		}else{
			document.getElementById("searchfromid").submit();
		}
	};
</script>
</head>
<body>
<form action="<%=request.getContextPath()%>/jzzcx/jzzcx_jzzcxList2.action" method="post" id="searchfromid" target="abc">
	    <iframe id="abc" name="abc" width="0" height="0" style="display: none;"></iframe>
	
	<!--<form action="<%=request.getContextPath()%>/jzzcx/jzzcx_jzzcxList2.action" method="post" id="searchfromid">
	--><div id="page">
       <fieldset>
           <legend class="style3"><strong>居住证信息查询</strong> </legend>
           <br />
           <div>&nbsp;&nbsp;&nbsp;
           		身份证号：<input name="idno" type="text" id="idno" style="width:193px;" />&nbsp;&nbsp;&nbsp; 
           		姓名：<input name="xm" type="text" id="xm" style="width:120px;" />
               <input type="button" onclick="javascript:Click();" name="Button2" value="查 询" id="Button2" class="buttom" />&nbsp;&nbsp;&nbsp;&nbsp;</div>
           <br/>
           
           <table cellspacing="1" class="tabList" style="width: 100%" border="1px solid;">        
               <tr>
                   <td style="width: 121px;"  align="right" id="id"><span style="float:right">姓名：</span></td>
                   <td colspan="5" style="height: 30px;">&nbsp;<span id="ceshi" style="float: left;">&nbsp;&nbsp;${obj[1]}</span></td>
               </tr>
               <tr id="TrYWBZ">
				   <td style="width: 121px" class="listTitle"><span style="float:right">身份证号码：</span></td>
				   <td><span id="lbSFZ" style="float: left;">&nbsp;&nbsp;${obj[2]}</span></td>
			   </tr>
               <tr>
                   <td style="width: 121px; height: 30px;"  class="listTitle" ><span style="float:right">户籍地址：</span></td>
                   <td colspan="5" style=" height: 30px;" >&nbsp;<span id="lbHJ"  style="float: left;">&nbsp;&nbsp;${obj[3]}</span></td>       
               </tr>
               <tr>
                   <td style="width: 121px" class="listTitle"><span style="float:right">现居住地址：</span></td>
                   <td colspan="5" >&nbsp;<span id="lbXZD" style="float: left;">&nbsp;&nbsp;${obj[4]}</span></td>
               </tr>
               <tr>
                   <td style="width: 121px" class="listTitle"><span style="float:right">居住证类型：</span></td>
                   <td colspan="5" >&nbsp;<span id="lbLX" style="float: left;">&nbsp;&nbsp;${obj[5]}</span></td>
               </tr>
               <tr>
                   	<td style="width: 121px; height: 30px;" class="listTitle"><span style="float:right">居住证状态：</span></td>
	              	<td  colspan="5" style=" height: 30px;" >
	              	
		              	<s:if test="#session.obj[6] == 1">
		              		<span id="lbZT" style="float: left;">&nbsp;&nbsp;初录完成</span>
		              	</s:if>
		              	<s:elseif test="#session.obj[6] == 2">
		              		<span id="lbZT" style="float: left;">&nbsp;&nbsp;待审核</span>
		              	</s:elseif>
		              	<s:elseif test="#session.obj[6] == 3">
		              		<span id="lbZT" style="float: left;">&nbsp;&nbsp;审核完成</span>
		              	</s:elseif>
		              	<s:elseif test="#session.obj[6] == 4">
		              		<span id="lbZT" style="float: left;">&nbsp;&nbsp;审核未通过</span>
		              	</s:elseif>
		              	<s:elseif test="#session.obj[6] == 5">
		              		<span id="lbZT" style="float: left;">&nbsp;&nbsp;被移除</span>
		              	</s:elseif>
		              	<s:elseif test="#session.obj[6] == 6">
		              		<span id="lbZT" style="float: left;">&nbsp;&nbsp;发送成功</span>
		              	</s:elseif>
		              	<s:elseif test="#session.obj[6] == 7">
		              		<span id="lbZT" style="float: left;">&nbsp;&nbsp;发送失败</span>
		              	</s:elseif>
		              	<s:elseif test="#session.obj[6] == 8">
		              		<span id="lbZT" style="float: left;">&nbsp;&nbsp;已发证</span>
		              	</s:elseif>
		              	<s:elseif test="#session.obj[6] == 9">
		              		<span id="lbZT" style="float: left;">&nbsp;&nbsp;已申报</span>
		              	</s:elseif>
		              	<s:elseif test="#session.obj[6] == 10">
		              		<span id="lbZT" style="float: left;">&nbsp;&nbsp;接收成功</span>
		              	</s:elseif>
		              	<s:elseif test="#session.obj[6] == 11">
		              		<span id="lbZT" style="float: left;">&nbsp;&nbsp;初录未完成</span>
		              	</s:elseif>
		              	<s:elseif test="#session.obj[6] == 12">
		              		<span id="lbZT" style="float: left;">&nbsp;&nbsp;制证失败</span>
		              	</s:elseif>
		              	<s:elseif test="#session.obj[6] == 13">
		              		<span id="lbZT" style="float: left;">&nbsp;&nbsp;未校验</span>
		              	</s:elseif>
		              	<s:elseif test="#session.obj[6] == 14">
		              		<span id="lbZT" style="float: left;">&nbsp;&nbsp;制证成功</span>
		              	</s:elseif>
		              	<s:elseif test="#session.obj[6] == 15">
		              		<span id="lbZT" style="float: left;">&nbsp;&nbsp;可发证</span>
		              	</s:elseif>
		              	<s:elseif test="#session.obj[6] == 17">
		              		<span id="lbZT" style="float: left;">&nbsp;&nbsp;已注销</span>
		              	</s:elseif>
		              	<s:elseif test="#session.obj[6] == 20">
		              		<span id="lbZT" style="float: left;">&nbsp;&nbsp;制证失败(原)</span>
		              	</s:elseif>
		              	<s:elseif test="#session.obj[6] == 21">
		              		<span id="lbZT" style="float: left;">&nbsp;&nbsp;已退费</span>
		              	</s:elseif>
		              	<s:elseif test="#session.obj[6] == 22">
		              		<span id="lbZT" style="float: left;">&nbsp;&nbsp;终止功能</span>
		              	</s:elseif>
		              	
	              	</td>
               </tr>
               <tr>
                   <td style="width: 121px; height: 30px;"  class="listTitle"><span style="float:right">有效期：</span></td>
                   <td colspan="5">&nbsp;<span id="lbYXQ" style="float: left;">&nbsp;&nbsp;${obj[7]}&nbsp;<a id="a">～</a>&nbsp;${obj[8]}</span></td> 
               </tr>        

           </table>
           </fieldset>
    </div>
</form>

</body>
</html>
