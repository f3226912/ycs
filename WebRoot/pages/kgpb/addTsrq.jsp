<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<title>${typeName}特殊日期</title>
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
	   var kcid = document.getElementById("kcid");
	   var tsrq = document.getElementById("tsrq");
	   var rs = document.getElementById("rs");
	   
	   if(checknotnull(kcid,"请选择考场编号") == "false"){
		   return false;
	   }
	   
	   if(checknotnull(tsrq,"请选择特殊日期") == "false"){
		   return false;
	   }
	   
	   addForm.submit();
   }
</script>
	<body>
	<br/>
	 <form action="<%=request.getContextPath()%>/kgpb/tsrqb_addOrUpdateTsrqb.action" id="addForm" name="addForm" method="post"  target="abc">
		<div style="width:50%;margin: 0 auto;">
			<iframe id="abc" name="abc" width="0" height="0" style="display: none;"></iframe>
		   <table  class="table1"  width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
			  <tr>
			    <td class="tds" height="25" colspan="2">${typeName}特殊日期
			    	<input type="hidden" id="id" name="tsrqb.id" value="${tsrqb.id}" />
			    </td>
			    
			  </tr>
			  <tr>
			  	<td class="tds" style="text-align: right;"><font color="red">*</font>考场：
			  	</td>
			    <td class="tds" style="text-align: left;">
			    	<s:select list="#request.kcxxbList" theme="simple"
										id="kcid" headerKey="" headerValue="---请选择---"
										listKey="kcbh" listValue="kcmc" name="tsrqb.kcid"
										value="#request.tsrqb.kcid"></s:select>
			    </td>
			    
			  </tr>
			  <tr>
			  	<td class="tds" style="text-align: right;"><font color="red">*</font>特殊日期：</td>
			    <td class="tds" style="text-align: left;">
			    	<input type="text" id="tsrq" name="tsrqb.tsrq" value="<s:date name="#request.tsrqb.tsrq" format="yyyy-MM-dd"/>" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" maxlength="30" />
			    </td>
			  </tr>
			  <tr>
			    <td class="tds" style="text-align: right;"><font color="red">*</font>类型：</td>
			    <td class="tds" style="text-align: left;">
			    	<select id="zt" name="tsrqb.type" >
			    		<option value="1" <s:if test='#request.tsrqb.type == "1"'>selected="selected"</s:if>>上班</option>
			    		<option value="2" <s:if test='#request.tsrqb.type == "2"'>selected="selected"</s:if>>休假</option>
			    	</select>
			    </td>
			  </tr>
			  <tr>
			  	<td class="tds" style="text-align: right;"><font color="red">*</font>备注：</td>
			    <td class="tds" style="text-align: left;">
			    	<input type="text" name="tsrqb.bz" maxlength="20" id="bz" value="${tsrqb.bz}"/>
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