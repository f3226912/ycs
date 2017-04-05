<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<title>${typeName}考官</title>
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
		
		var ssbm = document.getElementById("ssbm");
		if(ssbm.selectedIndex == 0){
			document.getElementById("ssbmmc").value = "";
		}
		else{
			document.getElementById("ssbmmc").value = ssbm.options[ssbm.selectedIndex].innerHTML;
		}
		
		
	});

	function selChange(obj){
		if(obj.selectedIndex == 0){
			document.getElementById("ssbmmc").value = "";
		}
		else{
			document.getElementById("ssbmmc").value = obj.options[obj.selectedIndex].innerHTML;
		}
	}
	
   	function addFun(){
	   var jh = document.getElementById("jh");
	   var xm = document.getElementById("xm");
	   var ssbm = document.getElementById("ssbm");
	   var sjhm = document.getElementById("sjhm");
	   
	   
	   if(checknotnull(jh,"请填写警员编号") == "false"){
		   return false;
	   }
	   
	   if(checknotnull(xm,"请填写警员姓名") == "false"){
		   return false;
	   }
	   
	   if(checknotnull(ssbm,"请选择所属部门") == "false"){
		   return false;
	   }
	   
	   if(checknotnull(sjhm,"请填写手机号码") == "false"){
		   return false;
	   }
	   
	   if(isNaN(sjhm.value)){
		   prompt(sjhm,"手机号码只能为数字")
		   return false;
	   }
	   removePrompt(sjhm)
	   
	   addForm.submit();
   }
</script>
	<body>
	<br/>
	 <form action="<%=request.getContextPath()%>/kgpb/kgxxb_addOrUpdateKgInfo.action" id="addForm" name="addForm" method="post"  target="abc">
		<div style="width:50%;margin: 0 auto;">
			<iframe id="abc" name="abc" width="0" height="0" style="display: none;"></iframe>
		   <table  class="table1"  width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
			  <tr>
			    <td class="tds" height="25" colspan="2">${typeName}考官
			    	<input type="hidden" id="id" name="kgxxb.id" value="${kgxxb.id}" />
			    </td>
			    
			  </tr>
			  <tr>
			  	<td class="tds" style="text-align: right;"><font color="red">*</font>警号：
			  	</td>
			    <td class="tds" style="text-align: left;">
			    	<input type="text" name="kgxxb.jh" maxlength="20" id="jh" value="${kgxxb.jh}"/>
			    </td>
			    
			  </tr>
			  <tr>
			  	<td class="tds" style="text-align: right;"><font color="red">*</font>姓名：</td>
			    <td class="tds" style="text-align: left;">
			    	<input type="text" name="kgxxb.xm" maxlength="20" id="xm" value="${kgxxb.xm}"/>
			    </td>
			  </tr>
			  <tr>
			    <td class="tds" style="text-align: right;"><font color="red">*</font>所属部门：</td>
			    <td class="tds" style="text-align: left;">
			    	<select id="ssbm" name="kgxxb.ssbm" onchange="selChange(this)" >
						<option value="">===请选择===</option>
						<s:iterator var="kjInfo" value="#request.kjList">
							<option value="<s:property value="#kjInfo[0]" />" <s:if test='#kjInfo[0] == #request.kgxxb.ssbm'>selected="selected"</s:if> ><s:property value="#kjInfo[1]" /></option>
						</s:iterator>
					</select>
			    	
			    	<input name="kgxxb.ssbmmc" id="ssbmmc" type="hidden" />
			    </td>
			  </tr>
			  <tr>
			    <td class="tds" style="text-align: right;"><font color="red">*</font>状态：</td>
			    <td class="tds" style="text-align: left;">
			    	<select id="zlzt" name="kgxxb.zt" >
			    		<option value="0" <s:if test='#request.kgxxb.zt == "0"'>selected="selected"</s:if> >正常</option>
			    		<option value="1" <s:if test='#request.kgxxb.zt == "1"'>selected="selected"</s:if> >休假</option>
			    		<option value="2" <s:if test='#request.kgxxb.zt == "2"'>selected="selected"</s:if> >固定排班</option>
			    	</select>
			    </td>
			  </tr>
			  <tr>
			    <td class="tds" style="text-align: right;"><font color="red">*</font>手机号码：</td>
			    <td class="tds" style="text-align: left;">
			    	<input type="text" name="kgxxb.sjhm" maxlength="20" id="sjhm" value="${kgxxb.sjhm}"/>
			    </td>
			  </tr>
			  <tr>
			  	<td class="tds" style="text-align: right;">备注：</td>
			    <td class="tds" style="text-align: left;">
			    	<input type="text" name="kgxxb.bz" maxlength="20" id="bz" value="${kgxxb.bz}"/>
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