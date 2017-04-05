<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<title>录入字典</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/content.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/jquery-1.8.2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/jquery-ui-1.9.1.custom.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/public.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/artDialog4.1.6/jquery.artDialog.source.js?skin=chrome"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/artDialog4.1.6/plugins/iframeTools.source.js"></script>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/My97DatePicker/WdatePicker.js"></script>
<style> 
	html{ 
		overflow:scroll;
		scrollbar-base-color:#c7e5ff;
		scrollbar-track-color:#FFFFFF;
	} 
</style>
</head>
<script type="text/javascript">   
   $(document).ready(function(){
        var type = '${type}';
		if(type =='sdyy'){
		  
	    }
           //录入黑名单用户
        $('#addBtn').click(function(){
            var dmms1 = document.getElementById("dmms1");
			if(checknotnull(dmms1,'请填写代码说明!') != "true"){
				return false;
			}else{
			   if(dmms1.value.length>200){
			       alert("代码说明内容长度不能超过200位!");
			       return false;
			   }
			}
           var url="<%=request.getContextPath()%>/blacklist/black_addDict.action";
            $("#addForm").attr("action",url);	
	        $("#addForm").submit();
	    });
	});
</script>
	<body>
	<br>
	 <form action="" id="addForm" name="addForm" method="post" target="abc">
	 <iframe id="abc" name="abc" width="0" height="0" style="display: none;"></iframe>
	       <input type="hidden" value="${type}" name="type" id="type"/>
		   <table  class="table1" id="dialogTable"  width="420px" height="250px" align="center" border="0" cellpadding="0" cellspacing="0">
			  <tr>
			    <td class="tds" height="25" colspan="3">录入限制代办业务字典</td>
			  </tr>
			   <tr>
			    <td class="tds" style="text-align: right;">代码类型:</td>
			    <td class="tds" style="text-align: left;" colspan="2"><s:if test="#request.type =='sdyy'">锁定原因</s:if><s:else>解锁原因</s:else> </td>
			  </tr>
			  <tr>
			    <td class="tds" style="text-align: right;">代码值:</td>
			    <td class="tds" style="text-align: left;"  colspan="2"><label>&nbsp;&nbsp;${seqNo}</label>
			    <input type="hidden" name="dict.dmz" id="dmz" value="${seqNo}"/>
			    </td>
			  </tr>
			  <tr>
			    <td class="tds" style="text-align: right;">代码说明:</td>
			    <td class="tds" style="text-align: left;"  colspan="2">
			       <textarea cols="30" rows="4" name="dict.dmms1" id="dmms1" ></textarea>
			   </td>
			  </tr> 
			  <tr>
			     <td class="tds" style="text-align: center;" colspan="3">
				  <input type="button" name="addBtn" id="addBtn" value="保存" class="bnt" style="cursor:pointer;"/>  
			   </td>
			  </tr>	
		  </table>             
	</form>
</body>
</html>