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
<title>限制代办业务用户管理</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/content.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/jquery-1.8.2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/artDialog4.1.6/jquery.artDialog.source.js?skin=chrome"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/artDialog4.1.6/plugins/iframeTools.source.js"></script>
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
        //新增
        $('#addBtn').click(function(){
         	art.dialog.open('<%=request.getContextPath()%>/blacklist/black_initHmdYh.action',{width:450,height:370,lock:true,opacity:0.1,resize:false,title:'限制代办业务用户录入'});  
        });
  });
  //查询黑名单信息
  function showDetail(xh){
     art.dialog.open('<%=request.getContextPath()%>/blacklist/black_initShowHmdYh.action?xh='+xh,{width:450,height:370,opacity:0.1,lock:true,resize:false,title:'限制代办业务用户信息'});
  }
  //修改当前状态（锁定/解锁）
  function editStatus(xh){
    art.dialog.open('<%=request.getContextPath()%>/blacklist/black_initEditHmdYh.action?xh='+xh,{width:450,height:300,opacity:0.1,lock:true,resize:false,title:'限制代办业务用户信息'});
  }
  //删除
  function delHmd(xh){
       if(confirm("确定要删除吗？") == true){
          	$.ajax({
			type:'POST',
			url: '<%=request.getContextPath()%>/blacklist/black_delHmyh.action',
			data:{'xh':xh},//发送的参数
			dataType: 'html',
			success:function(data){
				if(data == 1){
				    alert("操作成功!");
				    window.location.href="<%=request.getContextPath()%>/blacklist/black_getBlackList.action";
				}else if(data == 0){
					alert("操作失败!");//有可能是此条数据不存在。
					window.location.href="<%=request.getContextPath()%>/blacklist/black_getBlackList.action";
				}else{
					alert("系统繁忙,请稍候再试!");
				}
			}
		});
       }
  }
</script>
	<body>
	    <br>
		 <form action="<%=request.getContextPath()%>/blacklist/black_getBlackList.action" id="queryForm" name="queryForm" method="post">
		<div style="width:90%;margin: 0 auto;">
		   <table  class="table1"  width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
			  <tr>
			    <td class="tds" height="25" colspan="4" >限制代办业务用户信息查询</td>
			  </tr>
			  <tr>
			    <td class="tds" style="text-align: right;">身份证明号码:</td>
			    <td class="tds" style="text-align: left;"><input type="text" name="sfzmhm" id="sfzmhm" value="${sfzmhm}"/></td>
			    <td class="tds" style="text-align: right;">姓名:</td>
			    <td class="tds" style="text-align: left;"><input type="text" name="xm" id="xm" value="${xm}"/></td>
			  </tr>
			  <tr>
			    <td colspan="4"  class="tds"><input type="submit" name="queryBtn" id="queryBtn" value="查询" class="bnt" style="cursor:pointer;"/> </td>
			  </tr>
		  </table> 
		  </div>
		  <div style="width:90%;margin: 0 auto;">
		    <input type="button" name="addBtn" id="addBtn" value="录入" class="bnt" style="cursor:pointer;"/>
		  </div>
		  <div style="width:90%; margin-top: 10px;margin: 0 auto;" >
		    <table class="datalist" id="tableList" width="100%" border="0" cellpadding="0" cellspacing="0">
			  <tr class="tr1">
			    <th>序号</th>
			    <th>身份证明号码</th>
			    <th>姓名</th>
			    <th>入库类型</th>
			    <th>锁定/解锁原因</th>
			    <th>锁定类型</th>
			    <th>锁定期止</th>
			    <th>入库时间</th>
			    <th>当前状态</th>
			    <th>操作</th>
			  </tr>
			 <s:if test="#request.hmdYhList.size>0" >
		         <s:iterator id="user" value="#request.hmdYhList" status="st">
				  <tr id="${user.xh}" class="<s:if test="#st.odd == false">altrow</s:if>">
				    <td>${st.count + (map.currentpage-1) * map.pagesize}</td>
				    <td>${user.sfzmhm}</td>
				    <td>${user.xm}</td>
				    <td>
				        <s:if test="#user.rklx==1">手动添加</s:if>
				        <s:elseif test="#user.rklx==2">系统自动运算</s:elseif>
				        <s:else>其他</s:else>
				    </td>
				    <td> 
				     <s:if test="#user.dqzt==1">${user.sdyy}</s:if>
					 <s:else>${user.jsyy}</s:else>
				    </td>
				    <td>
				       <s:if test="#user.dqzt==1">
				         <s:if test="#user.sdlx==1">日期有效期止</s:if>
				         <s:else>永久锁定</s:else>
				       </s:if>				       
				    </td>
				    <td><s:date name="sdyxqz" format="yyyy-MM-dd"/></td>
				    <td><s:date name="czrq" format="yyyy-MM-dd hh:mm:ss"/></td>
				    <td>
					    <s:if test="#user.dqzt==1"><font color="red">锁定</font></s:if>
					    <s:else><font color="green">解锁</font></s:else>
			       </td>
			       <td>
			            <a href="#" onclick="editStatus('${user.xh}')"><s:if test="#user.dqzt==1">解锁</s:if><s:else>锁定</s:else></a> &nbsp;&nbsp;
					    <a href="javascript:void(0);" onclick="delHmd('${user.xh}')">删除</a>&nbsp;&nbsp;
					    <a href="#" onclick="showDetail('${user.xh}')">查看</a>
			       </td>
				  </tr>  
				 </s:iterator>
			  </s:if>   
			  <s:else>
			    <tr>
				  <td colspan="9" align="center">
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