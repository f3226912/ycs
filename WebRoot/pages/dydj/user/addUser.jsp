<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<title>邮政、车管用户开户</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/content.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/jquery-1.8.2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/jquery-ui-1.9.1.custom.js"></script>
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
           //添加用户
        $('#addBtn').click(function(){
            var yzYhdm= $.trim($("#yzYhdm").val());
            var yhxm = $.trim($("#yhxm").val());
            var ymmm =$.trim($("#ymmm").val());
            var yzCgJjm =$.trim($("#yzCgJjm").val());
            var departId =$("#departId").val();
            if(yzYhdm==''){
              alert("请输入用户代码!");
              return false;
            }
            if(yhxm==''){
               alert("请输入用户姓名!");
               return false;
            }
            if(ymmm==''){
               alert("请输入用户密码!");
               return false;
            }
            if(yzCgJjm==''){
               alert("请输入交接码!");
               return false;
            }else{
               if(yzCgJjm.length>10){
                 alert('交接码的长度不能超过10!');
                 return false;
               }
            }
            if(departId =='----'){
              alert("请选择用户部门!");
              return false;
            }
            var url="<%=request.getContextPath()%>/dydj/dydj_addUser.action";
            $("#addForm").attr("action",url);	
	        $("#addForm").submit();
	    });
	});
</script>
	<body>
	 <form action="" id="addForm" name="addForm" method="post" target="abc">
	 <iframe id="abc" name="abc" width="0" height="0" style="display: none;"></iframe>
		<div style="width:90%;margin: 0 auto;">
		   <table  class="table1"  width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
			  <tr>
			    <td class="tds" height="25" colspan="4">新增用户信息</td>
			  </tr>
			  <tr>
			    <td class="tds" style="text-align: right;"><font color="red">*</font>用户代码：</td>
			    <td class="tds" style="text-align: left;"><input type="text" name="dydjUser.yzYhdm" id="yzYhdm"/></td>
			    <td class="tds" style="text-align: right;"><font color="red">*</font>用户姓名：</td>
			    <td class="tds" style="text-align: left;"><input type="text" name="dydjUser.yhxm" id="yhxm"/></td>
			  </tr>
			  <tr>
			    <td class="tds" style="text-align: right;"><font color="red">*</font>用户密码:</td>
			    <td class="tds" style="text-align: left;"><input type="password" name="dydjUser.ymmm" id="ymmm"/></td>
			    <td class="tds" style="text-align: right;">账户类型：</td>
			    <td class="tds" style="text-align: left;">&nbsp;&nbsp;<input type="radio" name="dydjUser.zhlx" value="0" id="zhlx" checked="checked"/>车管&nbsp;&nbsp;&nbsp;<input type="radio" name="dydjUser.zhlx" value="1" id="zhlx"/>邮政</td>
			  </tr>
			  <tr>
			      <td class="tds" style="text-align: right;"><font color="red">*</font>用户部门：</td>
			    <td class="tds" style="text-align: left;">
			        <input type="hidden" name="dydjUser.yzQx10" id="yzQx10" value="C34702A8FED97CBFE040007F0100339B"/>
			         &nbsp;&nbsp;&nbsp;&nbsp;  西丽总所
			      <!-- <select name="dydjUser.yzQx10" id="departId" style="width:150px">
			       <option value="----">--请选择--</option>
			        <s:if test="#request.list.size>0" >
			             <s:iterator id="objs" value="#request.list">
			                <option value="<s:property value="#objs[0]" />"><s:property value="#objs[1]" /></option>
			            </s:iterator>
			        </s:if> 
			    </select> --></td>
			     <td class="tds" style="text-align: right;"><font color="red">*</font>邮政交接码：</td>
			    <td class="tds" style="text-align: left;"><input type="text" name="dydjUser.yzCgJjm" id="yzCgJjm"/></td>
			  </tr>		  
			  <tr>
			   <td class="tds" style="text-align: center;" colspan="4">
			      <input type="button" name="addBtn" id="addBtn" value="保存" class="bnt" style="cursor:pointer;"/>
			      <input type="button" class="bnt" value="返回" style="cursor:pointer;"  onclick="javascript:window.history.go(-1);"/>
			   </td>
			  </tr>
		  </table> 
		</div>
	</form>
</body>
</html>