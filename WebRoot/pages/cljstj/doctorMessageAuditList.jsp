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
<title>医生信息审核页面</title>
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
	.edittable {
			width: 100%;
			background: #FFFFFF;
			margin: 0 auto;
		}
	.bnt1 {
			font-size:18px;
			font-weight: bold;
			width: 90px;
			height: 38px;
			line-height: 38px;
			border: none;
			background: url(<%=request.getContextPath()%>/images/dd.jpg)
				no-repeat;
			color: #FFFFFF;
			vertical-align: middle;
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
	 //全选
	 function qx(){
		 $('[name=yyxh]:checkbox').attr('checked',true);
	 }
	 //全不选
	 function qbx(){
		 $('[name=yyxh]:checkbox').attr('checked',false);
	 }

	 function auditDoctorMessage(xh,zt,zzjgdmz){
		 $.ajax({
			cache:false,
			async:false,
			type:'post',
			url: "<%=request.getContextPath()%>/yytj/yytj_doctormessageAudit.action",
			data: {xh:xh,zt:zt},
			dataType: 'html',
			success:function(data){
				if (data == 1) {
					alert("审核成功!激活可用!");
					window.location.href = '<%=request.getContextPath()%>/yytj/yytj_doctorMessageAuditList.action?zzjgdmz='+zzjgdmz;
				}else if(data == 2){
					alert("停用、退办成功!");
					window.location.href = '<%=request.getContextPath()%>/yytj/yytj_doctorMessageAuditList.action?zzjgdmz='+zzjgdmz;
				}else if(data == 0){
					alert("操作失败!");
					return false;
				}
			}
		});
	 }
	 
	 function gopageByFh(){
		 window.location.href="<%=request.getContextPath()%>/yytj/yytj_hospitalUserinfoQuery.action";
	 }
</script>
	<body>
		<div style="width:95%;margin: 0 auto;">
			 <form id="queryForm" name="queryForm" action="<%=request.getContextPath()%>/yytj/yytj_doctorMessageAuditList.action" method="post">
			   <table class="tablesorter" style="width: 100%; border: 1px solid #aaccee;">
			   <thead>
				  <tr>
				    <td class="sorter-false" style="text-align: center;">
				    	<strong>医生身份证明号码：</strong><input id="yssfzmhm" name="yssfzmhm" value="${yssfzmhm}" type="text">
				    </td>
				    <td class="sorter-false" style="text-align: center;">
				    	<strong>所属医院：</strong><input id="yymc" name="yymc" value="${yymc}" type="text">
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
	  <div style="width:95%; margin: 0 auto;" >
	    <table class="tablesorter" style="width: 100%; border: 1px solid #aaccee;">
	    <thead>
		  <tr class="table_t">
		    <th class="sorter-false">医生身份证明号码</th>
		    <th class="sorter-false">医生姓名</th>
		    <th class="sorter-false">医生联系电话</th>
		    <th class="sorter-false">医生联系地址</th>
		    <th class="sorter-false">所属医院</th>
		    <th class="sorter-false">审核人账户</th>
		    <th class="sorter-false">审核人部门</th>
		    <th class="sorter-false">审核人ip</th>
		    <th class="sorter-false">用户状态</th>
		    <th class="sorter-false">操作</th>
		  </tr>
		</thead>
	   <s:if test='#request.doctorUserInfoAuditList!=null && #request.doctorUserInfoAuditList.size>0'>
	    <tbody>
		  	<s:iterator value="#request.doctorUserInfoAuditList" id="doctorUserInfoAudit" status="st">
		  		<tr>
		  		    <td>${doctorUserInfoAudit[1]}</td>
			  		<td>${doctorUserInfoAudit[4]}</td>
			  		<td>${doctorUserInfoAudit[5]}</td>
			  		<td>${doctorUserInfoAudit[6]}</td>
			  		<td>${doctorUserInfoAudit[25]}</td>
			  		<td>${doctorUserInfoAudit[16]}</td>
			  		<td>${doctorUserInfoAudit[18]}</td>
			  		<td>${doctorUserInfoAudit[20]}</td>
			  		<td><s:if test="#request.doctorUserInfoAudit[7]==0">待审</s:if>
			  		<s:elseif test="#request.doctorUserInfoAudit[7]==1">已审核，激活可用</s:elseif>
			  		<s:elseif test="#request.doctorUserInfoAudit[7]==-1">停用</s:elseif>
			  		<s:elseif test="#request.doctorUserInfoAudit[7]=='TB'">退办</s:elseif>
			  		<s:else>无效</s:else>
			  		</td>
			  		<td>
			  		<s:if test="#request.doctorUserInfoAudit[7]==0">
			  		   <a onclick="javascript:auditDoctorMessage(${doctorUserInfoAudit[0]},'1','${doctorUserInfoAudit[9]}')" href="#">审核</a>
			  		</s:if>
			  		<s:else>审核</s:else>
			  		<s:if test="#request.doctorUserInfoAudit[7]==1">
			  		   <a onclick="javascript:auditDoctorMessage(${doctorUserInfoAudit[0]},'-1','${doctorUserInfoAudit[9]}')" href="#">停用</a>
			  		   <a onclick="javascript:auditDoctorMessage(${doctorUserInfoAudit[0]},'TB','${doctorUserInfoAudit[9]}')" href="#">退办</a>
			  		</s:if>
			  		<s:else>停用 &nbsp;退办</s:else>
			  		</td>
		  		</tr>
		  	</s:iterator>
		 </tbody>
		  </s:if>
		  <s:else>
		  	<tr>
		  		<td style="color: red;text-align: center;" colspan="10">查无数据</td>
		  	</tr>
		  </s:else>
		</table>
		<table class="edittable" border="0" cellpadding="0" cellspacing="0" align="center">
			<tr>
				<td colspan="4" height="50" align="center"
							style="text-align: center">
					<input type="button" style="cursor:pointer;" onclick="javascript:gopageByFh();" value="返回" class="bnt1" />
				</td>
			</tr>
		</table>
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			style="padding-top: 5px;">
			<pt:write uri="${map.uri}" pagesize="${map.pagesize}" rscount="${map.rscount}" currentpage="${map.currentpage}"></pt:write>
		</table>
	   </div>
</body>
</html>