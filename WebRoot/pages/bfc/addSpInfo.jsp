<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<title>添加报废车特殊审批</title>
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
   function addFun(){
	   var sfzmmc = document.getElementById("sfzmmc");
	   var sfzmhm = document.getElementById("sfzmhm");
	   var ysts = document.getElementById("ysts");
	   
	   if(checknotnull(sfzmhm,"请填写身份证明号码") == "false"){
		   return false;
	   }
	   
	   if(sfzmmc.value == "A"){
		   if(checksfzhm(sfzmhm) == "false"){
			   return false;
		   }
	   }
	   
	   if(checknotnull(ysts,"请填写有效天数") == "false"){
		   return false;
	   }
	   
	   if(isNaN(ysts.value)){
		   prompt(ysts,"有效天数只能为数字")
		   return false;
	   }
	   removePrompt(ysts)
	   
	   if(ysts.value < 1){
		   prompt(ysts,"有效天数必须大于0");
		   return false;
	   }
	   removePrompt(ysts)
	   
	   if(parseInt(ysts.value) != ysts.value){
		   prompt(ysts,"有效天数必须为整数");
		   return false;
	   }
	   
	   addForm.submit();
   }
</script>
	<body>
	<br/>
	 <form action="<%=request.getContextPath()%>/bfc/bfcsp_addBfcTsspb.action" id="addForm" name="addForm" method="post"  target="abc">
		<div style="width:50%;margin: 0 auto;">
			<iframe id="abc" name="abc" width="0" height="0" style="display: none;"></iframe>
		   <table  class="table1"  width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
			  <tr>
			    <td class="tds" height="25" colspan="2">新增报废车特殊审批信息</td>
			  </tr>
			  <tr>
			  	<td class="tds" style="text-align: right;"><font color="red">*</font>身份证明名称：
			  	</td>
			    <td class="tds" style="text-align: left;">
			    	<select id="sfzmmc" name="bfcTssp.sfzmmc">
			    		<s:iterator value="#request.sfzmmcList" var="obj" >
			    			<option value="<s:property value="#obj[0]" />"><s:property value="#obj[1]" /></option>
			    		</s:iterator>
					</select>
			    </td>
			    
			  </tr>
			  <tr>
			  	<td class="tds" style="text-align: right;"><font color="red">*</font>身份证明号码：</td>
			    <td class="tds" style="text-align: left;"><input type="text" name="bfcTssp.sfzmhm" maxlength="20" id="sfzmhm" onkeyup="clearspace(this)" onblur="clearallspace(this)" value="${bfcTssp.sfzmhm}"/></td>
			  </tr>
			  <tr>
			    <td class="tds" style="text-align: right;"><font color="red">*</font>有效天数：</td>
			    <td class="tds" style="text-align: left;">
			    	<input type="text" name="bfcTssp.ysts" maxlength="3" size="5" id="ysts" onkeyup="clearspace(this)"   onblur="clearallspace(this)" value="${bfcTssp.ysts}"/>
			    	<input type="hidden" id="spjb" name="bfcTssp.spjb" value="1" />
			    </td>
			  </tr>
			  <!-- <tr>
			  	<td class="tds" style="text-align: right;"><font color="red">*</font>审批级别：</td>
			    <td class="tds" style="text-align: left;">
			    	<select id="spjb" name="bfcTssp.spjb">
			    			<option value="1">科级审批</option>
					</select>
			    </td>
			  </tr> -->
			  <tr>
			   <td class="tds" style="text-align: center;" colspan="2">
			      <input type="button" name="addBtn" id="addBtn" value="保存" class="bnt" onclick="addFun()" style="cursor:pointer;"/>
			      <input type="button" name="fhBtn" id="fhBtn" value="返回" class="bnt" onclick="javascript: window.history.go(-1)" style="cursor:pointer;"/>
			   </td>
			  </tr>
		  </table> 
		</div>
	</form>
</body>
</html>