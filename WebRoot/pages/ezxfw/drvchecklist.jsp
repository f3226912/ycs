<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="com.ycszh.common.PageTag" prefix="pt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
	<head>
		<title>驾驶证补证/换证初审监管</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath()%>/css/content.css" />
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath()%>/css/module.css" />
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/jquery/js/jquery-1.8.2.js">
</script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/My97DatePicker/WdatePicker.js">
</script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/jquery/development-bundle/ui/jquery.ui.core.js">
</script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/jquery/development-bundle/ui/jquery.ui.widget.js">
</script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/jquery/development-bundle/ui/jquery.ui.position.js">
</script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/jquery/development-bundle/ui/jquery.ui.menu.js">
</script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/jquery/development-bundle/ui/jquery.ui.autocomplete.js">
</script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/jquery/js/jquery-ui-1.9.1.custom.js">
</script>
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath()%>/jquery/css/redmond/jquery-ui-1.9.1.custom.css" />
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath()%>/jquery/css/redmond/jquery.ui.all.css" />
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/artDialog4.1.6/jquery.artDialog.source.js?skin=chrome">
</script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/artDialog4.1.6/plugins/iframeTools.source.js">
</script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/public.js">
</script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/jquery/jquery-lightbox-0.5/js/jquery.lightbox-0.5.js">
</script>
		<script type="text/javascript">
var chuli;
$(document).ready(function() {

});

function editsp(url) {
	window.location.href = url;
}

function exception(content) {
	art.dialog( {
		width : '50%',
		content : content,
		title : '系统异常',
		cancelVal : '关闭',
		cancel : true,
		lock : true,
		opacity : 0.87,
		icon : 'error'
	});
}

/**
 * @fpzh 分配账号
 * @lx   类型
 * @sdate 开始日期
 * @edate 结束日期
 */
function windowopenview(fpzh, lx, sdate, edate,hblx) {
	window
			.open(
					'<%=request.getContextPath()%>/ezxfw/ezxfw_initDrvCheckView.action?fpzh='
							+ fpzh + '&lx=' + lx + '&sdate=' + sdate
							+ '&edate=' + edate+'&hblx='+hblx,
					'info',
					'width='
							+ (window.screen.availWidth - 10)
							+ ',height='
							+ (window.screen.availHeight - 30)
							+ ',top=0,left=0,toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=yes');
}

function closechuli() {
	chuli.close();
}

function exportToExport() {
	var nodata = $("#nodata");
	if (nodata.length > 0) {
		alert("无数据导出");
	} else {
		var uri = "<%=request.getContextPath()%>/ezxfw/ezxfw_ezxfwDrvListExcel.action";
		window.location.href = uri;
	}
}
</script>

	</head>

	<body style="background: #c7e5ff;">
		<div class="content1" style="width: 100%;">
			<div class="roundedBox" id="type1" style="width: 95%;">
				<div class="right" style="width: 98%;">
					<form
						action="<%=request.getContextPath()%>/ezxfw/ezxfw_initDrvCheckList.action"
						method="post" id="ezxfwform">
						<table class="table1" width="100%" border="0" cellpadding="0"
							cellspacing="0">
							<tr>
								<th class="th1" height="32" colspan="11">
									查询条件
								</th>
							</tr>
							<tr>
								<td class="tds" style="text-align: right">
									分配账号&nbsp;
								</td>
								<td class="tdl" colspan="3" style="text-align: left;">
									<input type="text" size="10" value="${q_account}"
										name="q_account" />
								</td>
								<td class="tds" style="text-align: right">
									自动提取日期&nbsp;
								</td>
								<td class="tdl" colspan="3" style="text-align: left;">
									起
									<input name="s_date" id="s_date" value="${s_date}" size="10"
										type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
									止
									<input name="e_date" id="e_date" value="${e_date}" size="10"
										type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
								</td>
								<td class="tds" style="text-align: right">
									业务类型&nbsp;
								</td>
								<td class="tdl" colspan="2" style="text-align: left;">
									<s:select list="#request.ywlxList" theme="simple"
											id="hblx"
											listKey="key" listValue="value" headerKey="" headerValue="---请选择---" name="hblx" value="#request.hblx"></s:select>&nbsp;
								</td>
							</tr>
							<tr>
								<td class="tds" colspan="11">
									<div align="center">
										<input class="bnt" type="submit" value="查  询"
											style="cursor: pointer;" />
										<input class="bnt" type="button" value="导出excel"
											style="cursor: pointer;" onclick="exportToExport()"/>
									</div>
								</td>
							</tr>
						</table>
					</form>
					<div style="font-size: 13px">
						<s:iterator id="sl" value="#request.slList" status="st">
							总待初审(未分配)数量${sl[0] }宗,总待初审(已分配)数量 ${sl[1] }宗，${s_date}至${e_date}已分配情况如下：
						</s:iterator>
					</div>
					<table class="datalist" width="100%" border="0" cellpadding="0"
						cellspacing="0">
						<tr class="tr1">
							<th>
								序号
							</th>
							<th>
								分配账号
							</th>
							<th>
								分配姓名
							</th>
							<th>
								业务类型
							</th>
							<th>
								自动分配数量
							</th>
							<th colspan="2">
								已初审数量<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;初审通过|初审未通过
							</th>
							<th>
								未初审数量
							</th>
							<th>
								最后分配数据时间
							</th>
							<th>
								操作
							</th>
						</tr>
						<s:if test="#request.ezDrvLiceCheckList.size > 0">
							<s:iterator id="dc" value="#request.ezDrvLiceCheckList"
								status="st">
								<tr class="<s:if test="#st.odd == false">altrow</s:if>">
									<td>
										${st.count + (map.currentpage-1) * map.pagesize}
									</td>
									<td>
										${dc[0] }
									</td>
									<td>
										${dc[1] }
									</td>
									<td>
										${dc[2]}
									</td>
									<td>
										${dc[3]}
									</td>
									<td>
										${dc[4] }
									</td>
									<td>
										${dc[8] }
									</td>
									<td>
										${dc[5] }
									</td>
									<td>
										${dc[6] }
									</td>
									<td>
										<a href="javascript:void(0);"
											onclick="javascript:windowopenview('${dc[0] }','${type}','${s_date}','${e_date}','${dc[7]}');">点击查看详细</a>
									</td>

								</tr>
							</s:iterator>
						</s:if>
						<s:else>
							<tr>
								<td colspan="10">
									<span style="color: red">暂时没有相关数据</span>
								</td>
							</tr>
						</s:else>
					</table>
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						style="padding-top: 5px;">
						<pt:write uri="${map.uri}" pagesize="${map.pagesize}"
							rscount="${map.rscount}" currentpage="${map.currentpage}"></pt:write>
					</table>
				</div>

				<div class="corner topLeft"></div>
				<div class="corner topRight"></div>
			</div>
		</div>
	</body>
</html>

