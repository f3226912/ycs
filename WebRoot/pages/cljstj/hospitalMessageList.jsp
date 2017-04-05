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
<title>医院管理列表页面</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/content.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/js/tablesort/css/theme.blue.css"></link>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/jquery-1.8.2.js"></script>
<script src="<%=request.getContextPath()%>/js/tablesort/js/jquery.tablesorter.js"></script>
<script src="<%=request.getContextPath()%>/js/tablesort/js/jquery.tablesorter.widgets.js"></script>
<style> 
	html{ 
		overflow:scroll;
		scrollbar-base-color:#c7e5ff;
		scrollbar-track-color:#FFFFFF;
	}
	.cz{
		cursor: pointer;
		color: blue;
		text-decoration: underline;
	}
	.table_t{background:url(<%=request.getContextPath()%>/images/table_abg.gif) repeat-x; font-size:14px; letter-spacing:1.5px; font-
	weight:bold;height: 20px;}
	.tdStyle{border:1px solid #aaccee;border-collapse:collapse;  word-break:break-all;word-wrap:break-word;}
	td{ height:20px;  text-align:center; font-size:12px; }	
	.td_a{ text-decoration: none; }
</style>
</head>
<script type="text/javascript">
	$(function() {
	$("table").tablesorter({
	theme: 'blue',
	widgets: ["saveSort", "zebra"]
    });
});
	 function qx(){
		 $('[name=yyxh]:checkbox').attr('checked',true);
	 }
	
	 function qbx(){
		 $('[name=yyxh]:checkbox').attr('checked',false);
	 }
	 
	 function checkboxCz(){
		 if($("#buttonCheckbox").is(":checked"))
			  qx();
		 else
			 qbx();
	 }
	 
	 function addHospitalUserInfo(){
		  window.location.href="<%=request.getContextPath()%>/pages/cljstj/addHospitalMessage.jsp";
	 }
	 
	 function updHospitalUserInfo(yyxh){
		  window.location.href="<%=request.getContextPath()%>/yytj/yytj_queryHospitalUserinfo.action?yyxh="+yyxh;
	 }
	 
	 function ysAuditMessage(zzjgdmz){
		  window.location.href="<%=request.getContextPath()%>/yytj/yytj_doctorMessageAuditList.action?zzjgdmz="+zzjgdmz;
	 }
	 
	 function deleteHospitalUserInfo(yyxh){
		  $.ajax({
			cache:false,
			async:false,
			type:'post',
			url: "<%=request.getContextPath()%>/yytj/yytj_deleteHospitalUserInfo.action",
			data: {yyxh:yyxh},
			dataType: 'html',
			success:function(data){
				if (data == 1) {
					alert("删除成功!");
					window.location.href = '<%=request.getContextPath()%>/yytj/yytj_hospitalUserinfoQuery.action';
				}else{
					exception("删除失败!");
					return false;
				}
			}
		});
	 }
	 
	 function deleteHospitalUserInfoAll(){
		  var count = $.trim($('[name=yyxh]:checkbox:checked').length);
		  if(count == 0){
			alert("至少选中一条数据!");
			return false;
		  }
		  var result="";
		  var i=0;
		  $('[name=yyxh]:checkbox:checked').each(function(){
			  i++;
			  result+=$(this).val();
			  if(i<count){
				  result+=","
			  }
		  });
			$.ajax({
			    cache:false,
				async:false,
				type:'post',
				url: "<%=request.getContextPath()%>/yytj/yytj_deleteHospitalUserInfoAll.action",
				data: {yyxhs:$.trim(result)},
				dataType: 'html',
				success:function(data){
					if (data == 1) {
						alert("批量删除成功!");
						window.location.href = '<%=request.getContextPath()%>/yytj/yytj_hospitalUserinfoQuery.action';
					}else{
						exception("批量删除失败!");
						return false;
					}
				}
		   });
	 }
	 
	 function HospitalUserStartOrStop(yyxh,zt){
		 $.ajax({
			cache:false,
			async:false,
			type:'post',
			url: "<%=request.getContextPath()%>/yytj/yytj_HospitalUserStartOrStop.action",
			data: {yyxh:yyxh,zt:zt},
			dataType: 'html',
			success:function(data){
				if (data == 1) {
					alert("操作成功!");
					window.location.href = '<%=request.getContextPath()%>/yytj/yytj_hospitalUserinfoQuery.action';
				}else{
					exception("操作失败!");
					return false;
				}
			}
		});
	 }
</script>
	<body>
		<div style="width:95%;margin: 0 auto;">
			 <form id="queryForm" name="queryForm" action="<%=request.getContextPath()%>/yytj/yytj_hospitalUserinfoQuery.action" method="post">
			   <table class="tablesorter" style="width: 100%; border: 1px solid #aaccee;">
			   <thead>
				  <tr>
				    <td class="sorter-false" style="text-align: center;">
				    	<strong>医院名称：</strong><input id="yymc" name="yymc" value="${yymc}" type="text">
				    </td>
				    <td class="sorter-false" style="text-align: center;">
				    	<strong>组织机构代码证号：</strong><input id="zzjgdmzh" name="zzjgdmzh" value="${zzjgdmzh}" type="text">
				    </td>
				  </tr>
				</thead>
				<tbody>
				<tr>
				  	<td colspan="4">
				    	<input type="submit" class="bnt" style="width: 80px;" id="queryButton" value="查  询" />
				    	<input type="button" class="bnt" style="width: 80px;" id="resetButton" value="重  置"/>
				    </td>
				  </tr>
				</tbody>
			  </table> 
			</form>
		 </div>
	  <div>
		<input class="bnt" type="button" value="医院信息采集" onclick="javascript:addHospitalUserInfo();" style="cursor:pointer;" />
		<input class="bnt" type="button" value="批量删除" onclick="javascript:deleteHospitalUserInfoAll();" style="cursor:pointer;" />
	  </div>
	  <div style="width:95%; margin: 0 auto;" >
	    <table class="tablesorter" style="width: 100%; border: 1px solid #aaccee;">
	    <thead>
		  <tr class="table_t">
		    <th class="sorter-false">
		    <%--<input type="button" onclick="qx();" value="全选"><input type="button" onclick="qbx();" value="全不选">
		    --%>
		    <input type="checkbox" id="buttonCheckbox" name="buttonCheckbox" onclick="checkboxCz();">
		    </th>
		    <th class="sorter-false">组织机构代码证号(医院账户)</th>
		    <th class="sorter-false">医院名称</th>
		    <th class="sorter-false">医院联系电话</th>
		    <th class="sorter-false">医院联系地址</th>
		    <th class="sorter-false">医院联系人姓名</th>
		    <th class="sorter-false">医院联系人身份证明号码</th>
		    <th class="sorter-false">用户等级</th>
		    <th class="sorter-false">用户状态</th>
		    <th class="sorter-false">操作</th>
		  </tr>
		</thead>
		  <s:if test='#request.hospitalUserinfoList!=null && #request.hospitalUserinfoList.size>0'>
	    <tbody>
		  	<s:iterator value="#request.hospitalUserinfoList" id="hospitalUserinfo" status="st">
		  		<tr>
		  		    <td><input type="checkbox" id="yyxh" name="yyxh" value="${hospitalUserinfo.yyxh}"> </td>
		  		    <td>${hospitalUserinfo.zzjgdmz}</td>
			  		<td>${hospitalUserinfo.yymc}</td>
			  		<td>${hospitalUserinfo.lxdh}</td>
			  		<td>${hospitalUserinfo.lxdz}</td>
			  		<td>${hospitalUserinfo.yylxrxm}</td>
			  		<td>${hospitalUserinfo.yylxrsfzmhm}</td>
			  		<td><s:if test="#request.hospitalUserinfo.userlevel==1">普通用户</s:if><s:else>可以做残疾人体检</s:else></td>
			  		<td><s:if test="#request.hospitalUserinfo.zt==0">启用</s:if><s:else>停用</s:else></td>
			  		<td>
			  		    <a onclick="javascript:updHospitalUserInfo(${hospitalUserinfo.yyxh})" href="#">修改</a>
			  		    <s:if test="#request.hospitalUserinfo.zt==0">
			  		    <a onclick="javascript:HospitalUserStartOrStop(${hospitalUserinfo.yyxh},1)" href="#">账户停用</a>
			  		    </s:if>
			  		    <%--<s:else>账户停用</s:else>
			  		    --%><s:if test="#request.hospitalUserinfo.zt==1">
			  		    <a onclick="javascript:HospitalUserStartOrStop(${hospitalUserinfo.yyxh},0)" href="#">账户启用</a>
			  		    </s:if>
			  		    <%--<s:else>账户启用</s:else>
			  			--%><a onclick="javascript:deleteHospitalUserInfo(${hospitalUserinfo.yyxh})" href="#">删除</a>
			  			<a onclick="javascript:ysAuditMessage('${hospitalUserinfo.zzjgdmz}')" href="#">医生审核管理</a>
			  		</td>
		  		</tr>
		  	</s:iterator>
		 </tbody>
		  </s:if>
		  <s:else>
		  	<tr>
		  		<td style="color: red;text-align: center;" colspan="6">查无数据</td>
		  	</tr>
		  </s:else>
		</table>
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			style="padding-top: 5px;">
			<pt:write uri="${map.uri}" pagesize="${map.pagesize}" rscount="${map.rscount}" currentpage="${map.currentpage}"></pt:write>
		</table>
	   </div>
</body>
</html>