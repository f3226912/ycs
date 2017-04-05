<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<title>驾驶人相片库查询</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/jquery-1.8.2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/development-bundle/ui/jquery.ui.core.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/development-bundle/ui/jquery.ui.widget.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/development-bundle/ui/jquery.ui.position.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/development-bundle/ui/jquery.ui.menu.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/development-bundle/ui/jquery.ui.autocomplete.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/jquery-ui-1.9.1.custom.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery/css/redmond/jquery-ui-1.9.1.custom.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery/css/redmond/jquery.ui.all.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/artDialog4.1.6/jquery.artDialog.source.js?skin=chrome"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/artDialog4.1.6/plugins/iframeTools.source.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/myCss.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/public.js"></script>

<style>
html {
	overflow: scroll;
	scrollbar-base-color: #c7e5ff;
	scrollbar-track-color: #FFFFFF;
}
#menutableid td.hover {
	background: #48C7E8;
}
#menutableid td {
	text-align: center;
	cursor: pointer;
	background: #FFFFff;
}
.disabled {
	background-color: #F1F1F1;
}
</style>
<style>
* {
	margin: 0;
	padding: 0;
}
li {
	list-style: none;
}

.tab1,.tab2 {
	width: 100%;
	height: 34px;
	border: 1px #4fb3d5 solid;
	border-bottom: 0;
	background: url(<%=request.getContextPath()%>/images/bj.gif ) repeat-x;
	/*background: #407fb7;*/
	background: #006cce;
	border: none;
}
.tab1 ul,.tab2 ul {
	margin: 0;
	padding: 0;
}
.tab1 li,.tab2 li {
	float: left;
	padding: 0 30px;
	height: 34px;
	line-height: 34px;
	text-align: center;
	border-right: 1px #ebf7ff solid;
	cursor: pointer;
	font-size: 16px;
	letter-spacing: 2px;
	color: #FFFFFF;
}
.tab1 li.now,.tab2 li.now {
	color: #007ba5;
	background: #fff;
	font-weight: bold;
}
.tablist {
	width: 100%;
	height: 100%;
	font-size: 14px;
	line-height: 24px;
	border: 1px #4fb3d5 solid;
	border-top: 0;
	display: none;
	padding-top: 10px;
	border: none;
}
.block {
	display: block;
	width: 100%;
	height: 100%;
	background: #fff;
	padding-top: 10px;
	margin: 0 auto;
	border: none;
}
.edittable {
	width: 80%;
	background: #FFFFFF;
	margin: 0 auto;
	border: none;
}
.bnt {
	width: 90px;
	height: 23px;
	line-height: 21px;
	border: none;
	background: url(<%=request.getContextPath()%>/images/bnt_1.jpg )
		no-repeat;
	color: #FFFFFF;
	font-weight: bold;
	cursor: pointer;
}
.table_t {
	background: url(<%=request.getContextPath()%>/images/table_abg.gif) repeat-x;
	font-size: 14px;
	letter-spacing: 1.5px;
	font-weight: bold;
}
</style>
<script type="text/javascript">
	function exception(content){
		art.dialog({
			width:'50%',
		    content: content,
		    title: '系统异常',
		    cancelVal: '关闭',
			cancel: true,
			lock: true,
		    opacity: 0.87,
		    icon: 'error'
		});
	}
	
	//切换表格
	function settab(obj,n){
		var tds = $("#test2_li_now_ li");
		for(var i=0;i < tds.length;i++){ 
			tds[i].className=i==n?"now":"";
			$("#tableid" + i).hide();
		}
		$("#tableid" + n).show();
	}
	
</script>
<style >
#ejiaA1 {
	width: 120%;
}
.ejiaA2 {
	width: 100%;
}
.bnt_b {
	width: 100px;
	height: 27px;
	border: none;
	background: url(../images/an3.gif) no-repeat;
	color: #FFFFFF;
	font-weight: bold;
	cursor: pointer;
}
.h{height: 45px;}
</style>
</head>
<body>
<div>
<form action="<%=request.getContextPath()%>/jdcjsrxpk/jdcjsrxpk_jsrxpkList.action" method="post" name="myfrom" id="myfrom">
	<br/>
	<table width="80%" align="center">
		<tr>
			<td colspan="4" class="table_t" >查询条件</td>
		</tr>
		<tr>
			<td class="td_r">
				驾驶证号码&nbsp;
			</td>
			<td class="td_l">
				&nbsp;<input type="text" name="jszhms" id="jszhms" value="${sfz}"/>
			</td>
		</tr>
		<tr>
			<td colspan="4">
				<input class="bnt" type="submit" value="查   询"/>
			</td>
		</tr>
		<s:if test="#request.jsrList.size > 0">
		<tr><td colspan="6"></td>
		</tr>
		<tr id="xs">
			<td colspan="4">
			<div class="tab2">
				<ul id="test2_li_now_">
					<li onclick="settab(this,0)" class="now" onclick="jsr()">驾驶人相片库信息</li>
					<li onclick="settab(this,1)" onclick="xjyh()">星级用户相片库信息</li>
				</ul>
			</div>
			</td>
		</tr>
		</s:if>
		<s:else></s:else>
	</table>
	<div id="tableid0" class="tablist block" margin:0 auto;>
<table class="edittable" border="0" cellpadding="0" cellspacing="0" align="center" width="80%">
	<s:if test="#request.jsrList.size > 0">
	<s:iterator id="wfqd" value="#request.jsrList" status="st">
	<tr class="table_t">
		<td colspan="5">驾驶人相片库信息</td>
	</tr>
	<tr>
		<td width="20%"><span style="float:right">姓名&nbsp;&nbsp;&nbsp;&nbsp;</span></td>
		<td><span style="float:left">&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="#request.wfqd[2]"/></span></td>
		<td rowspan="6" width="30%">
			<s:if test="#request.jsrList.size > 0">
				<s:iterator id="wfqd" value="#request.jsrList" status="st">
					<input type="hidden" name="sfzmhm" id="sfzmhm" value="<s:property value="#request.wfqd[0]"/>"/>
					<input type="hidden" name="xphzbh" id="xphzbh" value="<s:property value="#request.wfqd[1]"/>"/>
					<img width="215" height="250" src="<%=request.getContextPath()%>/servlet/jsrxpkServlet?xphzbh=${wfqd[1]}"/>
				</s:iterator>
			</s:if>
		</td>
	</tr>
	<tr>
		<td><span style="float:right">身份证号码&nbsp;&nbsp;&nbsp;&nbsp;</span></td>
		<td><span style="float:left">&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="#request.wfqd[0]"/></span></td>
	</tr>
	<tr>
		<td><span style="float:right">驾驶证号码&nbsp;&nbsp;&nbsp;&nbsp;</span></td>
		<td><span style="float:left">&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="#request.wfqd[3]"/></span></td>
	</tr>
	<tr>
		<td><span style="float:right">联系地址&nbsp;&nbsp;&nbsp;&nbsp;</span></td>
		<td><span style="float:left">&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="#request.wfqd[4]"/></span></td>
	</tr>
	<tr>
		<td><span style="float:right">录入时间&nbsp;&nbsp;&nbsp;&nbsp;</span></td>
		<td><span style="float:left">&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="#request.wfqd[6]"/></span></td>
	</tr>
	</s:iterator>
</table>
</div>
<div id="tableid1" class="tablist" align="center">
	<table width="80%" align="center" id="xjyh" class="edittable">
		<tr class="table_t">
			<td colspan="5">星级用户相片库信息</td>
		</tr>
		<tr height="40">
			<td width="20%"><span style="float:right">姓名&nbsp;&nbsp;&nbsp;&nbsp;</span></td>
			<td><span style="float:left">&nbsp;&nbsp;&nbsp;&nbsp;${xms}</span></td>
		</tr>
		<tr height="40">
			<td><span style="float:right">身份证明号码&nbsp;&nbsp;&nbsp;&nbsp;</span></td>
			<td><span style="float:left">&nbsp;&nbsp;&nbsp;&nbsp;${sfz}</span></td>
		</tr>
		<tr height="40">
			<td><span style="float:right">用户来源&nbsp;&nbsp;&nbsp;&nbsp;</span></td>
			<td><span style="float:left">&nbsp;&nbsp;&nbsp;&nbsp;${yhly}</span></td>
		</tr>
		<tr height="40">
			<td><span style="float:right">入库时间&nbsp;&nbsp;&nbsp;&nbsp;</span></td>
			<td><span style="float:left">&nbsp;&nbsp;&nbsp;&nbsp;${lrsj}</span></td>
		</tr>
	</table>
	<table width="80%" align="center" id="xjxp"  class="edittable">
		<s:if test="#request.jsrxjxxList.size > 0">
			<tr class="table_t">
				<td colspan="5">星级用户身份相关图片信息</td>
			</tr>
			<tr>
				<td width="750">
					<img width="350" height="350" src="http://100.100.21.61/cmp_new/view_pic.asp?efid=${tpList[0]}"/><br/>
					${smList[0]}
				</td>&nbsp;&nbsp;&nbsp;&nbsp;
				<td width="750">
					<img width="350" height="350" src="http://100.100.21.61/cmp_new/view_pic.asp?efid=${tpList[1]}"/><br/>
					${smList[1]}
				</td>
			</tr>
			<tr width="100%">
				<td>
					<img width="350" height="350" src="http://100.100.21.61/cmp_new/view_pic.asp?efid=${tpList[2]}"/><br/>
					${smList[2]}
				</td>
				<s:if test="#request.jsrxjxxList.size > 3">
				<td>
					<img width="350" height="350" src="http://100.100.21.61/cmp_new/view_pic.asp?efid=${tpList[3]}"/><br/>
					${smList[3]}
				</td>
			</tr>
			<s:if test="#request.jsrxjxxList.size > 4">
			<tr width="100%">
				<td>
					<img width="350" height="350" src="http://100.100.21.61/cmp_new/view_pic.asp?efid=${tpList[4]}"/><br/>
					${smList[4]}
				</td>
				<s:if test="#request.jsrxjxxList.size > 5">
				<td>
					<img width="350" height="350" src="http://100.100.21.61/cmp_new/view_pic.asp?efid=${tpList[5]}"/><br/>
					${smList[5]}
				</td>
			</tr>
			<s:if test="#request.jsrxjxxList.size > 6">
			<tr width="100%">
				<td>
					<img width="350" height="350" src="http://100.100.21.61/cmp_new/view_pic.asp?efid=${tpList[6]}"/><br/>
					${smList[6]}
				</td>
				<s:if test="#request.jsrxjxxList.size > 7">
				<td>
					<img width="350" height="350" src="http://100.100.21.61/cmp_new/view_pic.asp?efid=${tpList[7]}"/><br/>
					${smList[7]}
				</td>
			</tr>
			</s:if><s:else></s:else>
			</s:if><s:else></s:else>
			</s:if><s:else></s:else>
			</s:if><s:else></s:else>
			</s:if><s:else></s:else>
		</s:if><s:else></s:else>
	</table>
</div>
</form>
</div>
<br/>
</s:if>
<s:else>
<table  align="center">
	<tr>
		<td width="1095px;">
			<span id="nodata" style="color: red" >暂时没有相关数据</span>
		</td>
	</tr>
</table>
</s:else>
<script language="javascript">
ejiaA1("ejiaA1","#fff","#F5F5F5","#FFFFCC","#C4DEFF");
</script>
</body> 
</html>
