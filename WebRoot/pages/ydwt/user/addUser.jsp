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
            var yzJjm =$.trim($("#yzJjm").val());
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
            if(yzJjm==''){
               alert("请输入交接码!");
               return false;
            }else{
               if(yzJjm.length>10){
                 alert('交接码的长度不能超过10!');
                 return false;
               }
            }
         var url="<%=request.getContextPath()%>/ydwt/ydwt_addUser.action";           
            $("#addForm").attr("action",url);	
	        $("#addForm").submit();
	    });
	});
</script>
	<body>
	 <form action="" id="addForm" name="addForm" method="post" target="abc">
	 <iframe id="abc" name="abc" width="0" height="0" style="display: none;"></iframe>
	 <input type="hidden" name="editType" id="editType" value="${editType}">
	 <input type="hidden" name="wtydnsUser.id" id="wtydnsUserId" value="${wtydnsUser.id}">
		<div style="width:90%;margin: 0 auto;">
		   <table  class="table1"  width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
			  <tr>
			    <td class="tds" height="25" colspan="4">${editType }用户信息</td>
			  </tr>
			  <tr>
			    <td class="tds" style="text-align: right;"><font color="red">*</font>用户代码：</td>
			    <td class="tds" style="text-align: left;"><input type="text" name="wtydnsUser.yzYhdm" id="yzYhdm" value="${wtydnsUser.yzYhdm}"/></td>
			    <td class="tds" style="text-align: right;"><font color="red">*</font>用户姓名：</td>
			    <td class="tds" style="text-align: left;"><input type="text" name="wtydnsUser.yhxm" id="yhxm" value="${wtydnsUser.yhxm}"/></td>
			  </tr>
			  <tr>
			    <td class="tds" style="text-align: right;"><font color="red">*</font>用户密码:</td>
			    <td class="tds" style="text-align: left;"><input type="password" name="wtydnsUser.ymmm" id="ymmm" value="${wtydnsUser.ymmm}"/></td>
			    <td class="tds" style="text-align: right;">账户类型：</td>
			    <td class="tds" style="text-align: left;">&nbsp;&nbsp;&nbsp;&nbsp;车管<input type="radio" name="wtydnsUser.zhlx" value="0" id="zhlx"  checked="checked"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;邮政<input type="radio" name="user.zhlx" value="1" id="zhlx"/></td>
			  </tr>
			  <tr>
			    <td class="tds" style="text-align: right;"><font color="red">*</font>邮政交接码：</td>
			    <td class="tds" style="text-align: left;"><input type="text" name="wtydnsUser.yzJjm" id="yzJjm" value="${wtydnsUser.yzJjm}"/></td>
			    <td class="tds" style="text-align: center;" colspan="2"><input type="button" name="addBtn" id="addBtn" value="保存" class="bnt" style="cursor:pointer;"/></td>
			  </tr>
		  </table> 
		</div>
	</form>
</body>
</html>