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
<title>银行开户管理</title>
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
           window.location.href="<%=request.getContextPath()%>/dydj/dydj_initYHUser.action";
        });
        $('#queryBtn').click(function(){
             var  url="<%=request.getContextPath()%>/dydj/dydj_getYHUsersList.action";
            $("#queryForm").attr("action",url);	
	        $("#queryForm").submit();
        });
  });
  // 冻结/活动用户
  function freezeUser(id){
     		$.ajax({
			type:'POST',
			url: '<%=request.getContextPath()%>/dydj/dydj_freezeUser.action?temp=bank',
			data:{'id':id},//发送的参数
			dataType: 'html',
			success:function(data){
				if(data == 1){
				    alert("操作成功!");
				    var btnText =$('#status'+id).val();
				    if(btnText=='冻结'){
		               $('#status'+id).val('活动');
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
   //重置密码
  function checkPwd(id){
	 $.ajax({
		async : false,
		cache : false,
		type:'POST',
		url: '<%=request.getContextPath()%>/dydj/dydj_reSetPwd.action?temp=bank',
		data:{'id':id}, //发送的参数
		dataType: 'html',
		success:function(data){
			if (data == 1) {
				alert("重置密码成功!");
			} else {
				alert("重置密码失败!");
			}
		}
	});
  }
  
  //清空mac地址
  function cleanMac(id){
    	 $.ajax({
		async : false,
		cache : false,
		type:'POST',
		url: '<%=request.getContextPath()%>/dydj/dydj_clearnMac.action?temp=bank',
		data:{'id':id}, //发送的参数
		dataType: 'html',
		success:function(data){
			if (data == 1) {
				alert("清空mac成功!");
			} else {
				alert("清空mac失败!");
			}
		}
	});
  }
  
</script>
	<body>
		 <form action="" id="queryForm" name="queryForm" method="post">
		<div style="width:98%;margin: 0 auto;">
		   <table  class="table1"  width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
			  <tr>
			    <td class="th1" height="25" colspan="4">银行信息查询</td>
			  </tr>
			  <tr>
			    <td class="tds" style="text-align: right;">银行代码：</td>
			    <td class="tds" style="text-align: left;"><input type="text" name="yzYhdm" id="yzYhdm" value="${yzYhdm}"/></td>
			    <td class="tds" style="text-align: right;">银行状态 ：</td>
			    <td class="tds" style="text-align: left;">
			    <s:select theme="simple" cssStyle="width:120px" name="status" id="status" list="#{0:'冻结',1:'活动'}" value="#request.status" headerKey="--" headerValue="--请选择--"></s:select></td>
			  </tr>
			  <tr>
			    <td colspan="4"  class="tds"><input type="button" name="queryBtn" id="queryBtn" value="检索" class="bnt" style="cursor:pointer;"/></td>
			  </tr>
		  </table> 
		  </div>
		  <div style="width:98%;margin: 0 auto;">
		    <input type="button" name="addBtn" id="addBtn" value="添加" class="bnt" style="cursor:pointer;"/>
		  </div>
		  <div style="width:98%; margin-top: 10px;margin: 0 auto;" >
		    <table class="datalist" id="tableList" width="100%" border="0" cellpadding="0" cellspacing="0">
			  <tr class="tr1">
			    <th>序号</th>
			    <th>银行代码</th>
			    <th>银行名称</th>
			    <th>组织机构代码证</th>
			    <th>法人名称</th>
			    <th>联系电话</th>
			    <th>操作</th>
			  </tr>
			 <s:if test="#request.yhUserList.size>0" >
		         <s:iterator id="user" value="#request.yhUserList" status="st">
				  <tr id="${user.id}" class="<s:if test="#st.odd == false">altrow</s:if>">
				    <td>${st.count + (map.currentpage-1) * map.pagesize}</td>
				    <td>${user.yzYhdm}</td>
				    <td>${user.yhxm}</td>
				    <td>${user.zjjgdmz}</td>
				    <td>${user.frmc}</td>
				    <td>${user.lxdh}</td>
				    <td>
				    <s:if test="#user.status==0">
				     <input type="button" name="status${user.id}" id="status${user.id}" value="活动" onclick="freezeUser('${user.id}')"/>
				    </s:if>
				    <s:else>
				      <input type="button" name="status${user.id}" id="status${user.id}" value="冻结" onclick="freezeUser('${user.id}')"/>
				    </s:else>
				      <input type="button" name="resetBut" value="重置密码" onclick="checkPwd('${user.id}')"/>
				    <a href="#" onclick="cleanMac('${user.id}')">清空mac</a>&nbsp;&nbsp;
				    <a href="<%=request.getContextPath()%>/dydj/dydj_getYhUserById.action?id=${user.id}">查看</a>
				    <a href="<%=request.getContextPath()%>/dydj/dydj_getInitEditById.action?id=${user.id}">修改</a>
				    <a href="<%=request.getContextPath()%>/dydj/dydj_getDydjdbrList.action?yhUserId=${user.id}">代办人管理</a>
			       </td>
				  </tr>  
				 </s:iterator>
			  </s:if>   
			  <s:else>
			    <tr>
				  <td colspan="7" align="center">
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