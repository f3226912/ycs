<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="com.ycszh.common.PageTag" prefix="pt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
  <head>
    <title>${editType }嫌疑字典</title>
    	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/content.css" />
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
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/public.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$(".disabled").attr("style","display:none;");
		});
		
		function tiShi(alertValue,obj ){
			alert(alertValue);
			obj.style.borderColor = '#FF0000';
			obj.focus();
		}
		
		function check(){
			
			var dmz = document.getElementById("ycsXycCode.dmz");
			var dmms1 = document.getElementById("ycsXycCode.dmms1");
			var dmlb = document.getElementById("ycsXycCode.dmlb");
			var bz = document.getElementById("ycsXycCode.bz");
			var lrrdm = document.getElementById("ycsXycCode.lrrdm");
			var lrrxm = document.getElementById("ycsXycCode.lrrxm");
			var lrrbm = document.getElementById("ycsXycCode.lrrbm");
			var lrsj = document.getElementById("ycsXycCode.lrsj");
			
			if("false" == checknotnull(dmz,"代码值必须填写！")){
				return false;
			}
			// DMMS1 为必填项
			if("false" == checknotnull(dmms1,"代码说明必须填写！")){
				return false;
			}
			document.getElementById("addForm").submit();
		}
	</script>
  </head>
  <body>
	<center>
		<form action="<%=request.getContextPath()%>/xyc/xyccode_editYcsXycCode.action" id="addForm" name="addForm" method="post"  target="abc">
		<div style="width:50%;margin: 0 auto;">
			<iframe id="abc" name="abc" width="0" height="0" style="display: none;"></iframe>
		   <table  class="table1"  width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
			  <tr>
			    <td class="tds" height="25" colspan="2">${editType }嫌疑字典</td>
			  </tr>
			  <tr>
			  	<td class="tds" style="text-align: right;"><font color="red">*</font>代码值：
			  	</td>
			    <td class="tds" style="text-align: left;">
			    	<input type="text" id="ycsXycCode.dmz" name="ycsXycCode.dmz" value="${ycsXycCode.dmz}" />
					<input type="hidden" id="dmlb" value="XYYY" name="ycsXycCode.dmlb" value="${ycsXycCode.dmz}"/>
					<input type="hidden" value="${ycsXycCode.id}" name="ycsXycCode.id"/>
					<input type="hidden" value="${ycsXycCode.lrrdm}" name="ycsXycCode.lrrdm"/>
					<input type="hidden" value="${ycsXycCode.lrrxm}" name="ycsXycCode.lrrxm"/>
					<input type="hidden" value="${ycsXycCode.lrrbm}" name="ycsXycCode.lrrbm"/>
					<input type="hidden" value="${ycsXycCode.lrsj}" name="ycsXycCode.lrsj"/>
			    </td>
			  </tr>
			  <tr>
			  	<td class="tds" style="text-align: right;"><font color="red">*</font>代码说明：</td>
			    <td class="tds" style="text-align: left;"><input type="text" id="ycsXycCode.dmms1" value="${ycsXycCode.dmms1}" name="ycsXycCode.dmms1" size="45" maxlength="45"/></td>
			  </tr>
			  <tr>
			    <td class="tds" style="text-align: right;">备注：</td>
			    <td class="tds" style="text-align: left;">
			    	<input type="text" id="ycsXycCode.bz" value="${ycsXycCode.bz}" name="ycsXycCode.bz" size="45" maxlength="45"/>
			    </td>
			  </tr>
			  <tr>
			   <td class="tds" style="text-align: center;" colspan="2">
			      <input type="button" name="addBtn" id="addBtn" value="保存" class="bnt" onclick="check()" style="cursor:pointer;"/>
			   </td>
			  </tr>
		  </table> 
		</div>
	</form>
		
	</center>
	<div></div>
  </body>
</html>