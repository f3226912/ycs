<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<title>${typeName}考场</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/content.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/jquery-1.8.2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/jquery-ui-1.9.1.custom.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/public.js"></script>
<style> 
	html{ 
		overflow:auto;
		scrollbar-base-color:#c7e5ff;
		scrollbar-track-color:#FFFFFF;
	} 
</style>
</head>
<script type="text/javascript">   

	$(document).ready(function(){
		var edittype = '${typeName}';
		if('查看' == edittype){
			$("select").attr("disabled","disabled");
			$("input[type='text']").attr("disabled","disabled");
		}
		
	});

   	function addFun(){
	   var kcbh = document.getElementById("kcbh");
	   var kcmc = document.getElementById("kcmc");
	   var rs = document.getElementById("rs");
	   
	   if(checknotnull(kcbh,"请填写考场编号") == "false"){
		   return false;
	   }
	   
	   if(checknotnull(kcmc,"请填写考场名称") == "false"){
		   return false;
	   }
	   
	   if(checknotnull(rs,"请填写人数") == "false"){
		   return false;
	   }
	   
	   if(isNaN(rs.value)){
		   prompt(rs,"人数只能为数字")
		   return false;
	   }
	   removePrompt(rs)
	   
	   if(rs.value < 1){
		   prompt(rs,"人数必须大于0");
		   return false;
	   }
	   removePrompt(rs)
	   
	   if(parseInt(rs.value) != rs.value){
		   prompt(rs,"人数必须为整数");
		   return false;
	   }
	   
	   addForm.submit();
   }
</script>
	<body>
	<br/>
	 <form action="<%=request.getContextPath()%>/kgpb/kcxxb_addOrUpdateKcInfo.action" id="addForm" name="addForm" method="post"  target="abc">
		<div style="width:50%;margin: 0 auto;">
			<iframe id="abc" name="abc" width="0" height="0" style="display: none;"></iframe>
		   <table  class="table1"  width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
			  <tr>
			    <td class="tds" height="25" colspan="2">${typeName}考场
			    	<input type="hidden" id="id" name="kcxxb.id" value="${kcxxb.id}" />
			    </td>
			    
			  </tr>
			  <tr>
			  	<td class="tds" style="text-align: right;"><font color="red">*</font>考场编号：
			  	</td>
			    <td class="tds" style="text-align: left;">
			    	<input type="text" name="kcxxb.kcbh" maxlength="20" id="kcbh" value="${kcxxb.kcbh}"/>
			    </td>
			    
			  </tr>
			  <tr>
			  	<td class="tds" style="text-align: right;"><font color="red">*</font>考场名称：</td>
			    <td class="tds" style="text-align: left;">
			    	<input type="text" name="kcxxb.kcmc" maxlength="20" id="kcmc" value="${kcxxb.kcmc}"/>
			    </td>
			  </tr>
			  <tr>
			    <td class="tds" style="text-align: right;"><font color="red">*</font>状态：</td>
			    <td class="tds" style="text-align: left;">
			    	<select id="zt" name="kcxxb.zt" >
			    		<option value="0" <s:if test='#request.kcxxb.zt == "0"'>selected="selected"</s:if>>正常</option>
			    		<option value="1" <s:if test='#request.kcxxb.zt == "1"'>selected="selected"</s:if>>停用</option>
			    	</select>
			    </td>
			  </tr>
			  <tr>
			    <td class="tds" style="text-align: right;"><font color="red">*</font>周六状态：</td>
			    <td class="tds" style="text-align: left;">
			    	<select id="zlzt" name="kcxxb.zlzt" >
			    		<option value="0" <s:if test='#request.kcxxb.zlzt == "0"'>selected="selected"</s:if> >上班</option>
			    		<option value="1" <s:if test='#request.kcxxb.zlzt == "1"'>selected="selected"</s:if> >休假</option>
			    	</select>
			    </td>
			  </tr>
			  <tr>
			    <td class="tds" style="text-align: right;"><font color="red">*</font>周日状态：</td>
			    <td class="tds" style="text-align: left;">
			    	<select id="zrzt" name="kcxxb.zrzt" >
			    		<option value="0" <s:if test='#request.kcxxb.zrzt == "0"'>selected="selected"</s:if> >上班</option>
			    		<option value="1" <s:if test='#request.kcxxb.zrzt == "1"'>selected="selected"</s:if> >休假</option>
			    	</select>
			    </td>
			  </tr>
			  <tr>
			  	<td class="tds" style="text-align: right;"><font color="red">*</font>考官人数：</td>
			    <td class="tds" style="text-align: left;">
			    	<input type="text" name="kcxxb.rs" maxlength="20" id="rs" value="${kcxxb.rs}"/>
			    </td>
			  </tr>
			  <tr>
			  	<td class="tds" style="text-align: right;"><font color="red">*</font>固定考官：</td>
			    <td class="tds" style="text-align: left;">
			    	<select id="zrzt" name="kcxxb.gdkgid" >
			    		<option value="">===请选择===</option>
			    		<s:iterator value="#request.gdkgList" var="gdkg" >
			    			<option value="<s:property value="#gdkg[0]" />" <s:if test='#request.kcxxb.gdkgid == #gdkg[0]'>selected="selected"</s:if> ><s:property value="#gdkg[1]" /></option>
			    		</s:iterator>
			    	</select>
			    </td>
			  </tr>
			  <tr>
			   <td class="tds" style="text-align: center;" colspan="2">
			  	  <s:if test='#request.typeName != "查看"'>
			      	<input type="button" name="addBtn" id="addBtn" value="保存" class="bnt" onclick="addFun()" style="cursor:pointer;"/>
			      </s:if>
			      <input type="button" name="fhBtn" id="fhBtn" value="返回" class="bnt" onclick="javascript: window.history.go(-1)" style="cursor:pointer;"/>
			   </td>
			  </tr>
		  </table> 
		</div>
	</form>
</body>
</html>