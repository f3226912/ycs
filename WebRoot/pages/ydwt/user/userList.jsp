<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="com.ycszh.common.PageTag" prefix="pt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<title>邮政、车管开户管理</title>
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
        $('#addBtn').click(function(){
           window.location.href="<%=request.getContextPath()%>/ydwt/ydwt_initUser.action";
        });
        $('#queryBtn').click(function(){
             var  url="<%=request.getContextPath()%>/ydwt/ydwt_getUsersList.action";
            $("#queryForm").attr("action",url);	
	        $("#queryForm").submit();
        });
  });
 // 冻结/解冻用户
  function freezeUser(id){
     		$.ajax({
			type:'POST',
			url: '<%=request.getContextPath()%>/ydwt/ydwt_freezeUser.action',
			data:{id:id},//发送的参数
			dataType: 'html',
			success:function(data){
				if(data == 1){
				    alert("操作成功!");
				    var btnText =$('#status'+id).val();
				    if(btnText=='冻结'){
		               $('#status'+id).val('解冻');
				    }else{
				       $('#status'+id).val('冻结');
				    }
				}else if(data == 0){
					alert("操作失败!");
				}else{
					alert("系统繁忙,请稍候再试!");
				}
			}
		});
   }
  function checkPwd(id){
	 $.ajax({
		async : false,
		cache : false,
		type:'POST',
		url: '<%=request.getContextPath()%>/ydwt/ydwt_reSetPwd.action',
		data:{id:id}, //发送的参数
		dataType: 'html',
		success:function(data){
			if (data == 1) {
				alert("重置密码成功!");
			} else {
				alert("重置失败!");
			}
		}
	});
  }
  
</script>
	<body>
		 <form action="" id="queryForm" name="queryForm" method="post">
		<div style="width:90%;margin: 0 auto;">
		   <table  class="table1"  width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
			  <tr>
			    <td class="th1" height="25" colspan="4">邮政、车管用户信息查询</td>
			  </tr>
			  <tr>
			    <td class="tds" style="text-align: right;">用户代码：</td>
			    <td class="tds" style="text-align: left;"><input type="text" name="yzYhdm" id="yzYhdm" value="${yzYhdm}"/></td>
			    <td class="tds" style="text-align: right;">账户类型 ：</td>
			    <td class="tds" style="text-align: left;">
			    <s:select theme="simple" cssStyle="width:120px" name="zhlx" id="zhlx" list="#{0:'车管用户',1:'邮政用户'}" value="#request.zhlx" headerKey="--" headerValue="--请选择--"></s:select> </td>
			  </tr>
			  <tr>
			    <td colspan="4"  class="tds"><input type="button" name="queryBtn" id="queryBtn" value="检索" class="bnt" style="cursor:pointer;"/> </td>
			  </tr>
		  </table> 
		  </div>
		  <div style="width:90%;margin: 0 auto;">
		    <input type="button" name="addBtn" id="addBtn" value="添加" class="bnt" style="cursor:pointer;"/>
		  </div>
		  <div style="width:90%; margin-top: 10px;margin: 0 auto;" >
		    <table class="datalist" id="tableList" width="100%" border="0" cellpadding="0" cellspacing="0">
			  <tr class="tr1">
			    <th>序号</th>
			    <th>用户代码</th>
			    <th>用户姓名</th>
			    <th>账户类型</th>
			    <th>邮政交接码</th>
			    <th>操作</th>
			  </tr>
			 <s:if test="#request.userList.size>0" >
		         <s:iterator id="user" value="#request.userList" status="st">
				  <tr id="${user.id}" class="<s:if test="#st.odd == false">altrow</s:if>">
				    <td>${st.index+1}</td>
				    <td>${user.yzYhdm}</td>
				    <td>${user.yhxm}</td>
				    <td>
				    <s:if test="#user.zhlx==0">车管用户</s:if>
				    <s:else>邮政用户</s:else></td>
				    <td>${user.yzJjm}</td>
				    <td>
				    <s:if test="#user.status==0">
				     <input type="button" name="status${user.id}" id="status${user.id}" value="解冻" onclick="freezeUser('${user.id}')"/>
				    </s:if>
				    <s:else>
				      <input type="button" name="status${user.id}" id="status${user.id}" value="冻结" onclick="freezeUser('${user.id}')"/>
				    </s:else>
				    &nbsp;&nbsp;
				    <a href="#" onclick="checkPwd('${user.id}')">重置密码</a>
				       &nbsp;&nbsp;
				    <a href="<%=request.getContextPath()%>/ydwt/ydwt_editUserInit.action?id=${user.id}">编辑</a>
			       </td>
				  </tr>
				 </s:iterator>
			  </s:if>   
			  <s:else>
			    <tr>
				  <td colspan="6" align="center">
					<span style="color: red">没有找到相关数据<input type="hidden" value="0" id="itemSize"/> </span>
				  </td>
				</tr>
			  </s:else>	  
			</table>
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				style="padding-top: 5px;">
				<pt:write uri="${map.uri}" pagesize="${map.pagesize}" rscount="${map.rscount}" currentpage="${map.currentpage}"></pt:write>
			</table>
		     </div>
		</form>
</body>
</html>