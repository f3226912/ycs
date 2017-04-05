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

function closechuli() {
	chuli.close();
}

function exportToExport() {
	var nodata = $("#nodata");
	if (nodata.length > 0) {
		alert("无数据导出");
	} else {
		var uri = "<%=request.getContextPath()%>/ezxfw/ezxfw_ezxfwDrvExcel.action";
		window.location.href = uri;
	}
}
</script>

	</head>

	<body style="background: #c7e5ff;">
		<div class="content1" style="width: 100%;">
			<div class="roundedBox" id="type1" style="width: 95%;">
				<div class="right" style="width: 98%;">
					<div style="width: 98%; height: 35px; padding-top: 10px;">
						<input class="bnt" type="button" value="导出excel"
							onclick="javascript:exportToExport();" style="cursor: pointer;" />
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
								批次号
							</th>
							<th>
								自动分配数量
							</th>
							<th>
								已初审数量
							</th>
							<th>
								未初审数量
							</th>
							<th>
								分配数据时间
							</th>
							
						</tr>
						<s:if test="#session.ezDrvLiceCheckView.size > 0">
							<s:iterator id="dc" value="#session.ezDrvLiceCheckView"
								status="st">
								<tr class="<s:if test="#st.odd == false">altrow</s:if>">
									<td>
										${st.count}
									</td>
									<td>
										${dc[0]}
									</td>
									<td>
										${dc[1]}
									</td>
									<td>
										${dc[2]}
									</td>
									<td>
										${dc[3]}
									</td>
									<td>
										${dc[4]}
									</td>
									<td>
										${dc[5]}
									</td>

									<td>
										${dc[6]}
									</td>
									
								</tr>
							</s:iterator>
						</s:if>
						<s:else>
							<tr>
								<td colspan="8">
									<span id="nodata" style="color: red">暂时没有相关数据</span>
								</td>
							</tr>
						</s:else>
					</table>

				</div>

				<div class="corner topLeft"></div>
				<div class="corner topRight"></div>
			</div>
		</div>
	</body>
</html>

