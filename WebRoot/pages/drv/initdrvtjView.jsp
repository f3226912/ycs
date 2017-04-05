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
		<title>驾驶证受理业务审核查询统计详情</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
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
function openview(shxm,sdate,edate){
	var name=encodeURI(shxm);
	location.href='<%=request.getContextPath()%>/drv/initSlgDrvXxcjbList2.action?isnew=1&s_date='+sdate+'&e_date='+edate+'&shxm='+name;
}


/**
 * @fpzh 分配账号
 * @lx   类型
 * @sdate 开始日期
 * @edate 结束日期
 */

function closechuli() {
	chuli.close();
	encodeURI(URI)
}

</script>

	</head>

	<body style="background: #c7e5ff;">
		<div class="content1" style="width: 100%;">
			<div class="roundedBox" id="type1" style="width: 95%;">
				<div class="right" style="width: 98%;">
					<form
						action="<%=request.getContextPath()%>/drv/initDrvtjView.action?kjbm="+${shbmkj}
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
									审核部门:&nbsp;
								</td>
								<td class="tdl" colspan="2" style="text-align: left;">
									<s:select list="#request.deptList" theme="simple"
											id="shbmkj"
											listKey="key" listValue="value" headerKey="" headerValue="---请选择---" name="shbmkj" value="#request.shbmkj"></s:select>&nbsp;
								</td>
								<td class="tds" style="text-align: right">
									审核人&nbsp;
								</td>
								<td class="tdl" colspan="3" style="text-align: left;">
									<input name="shxm" id="shxm" value="${shxm }" type="text"/>
									<input name="sdate" id="sdate" value="${sdate }" type="hidden"/>
									<input name="edate" id="edate" value="${edate }" type="hidden"/>
									<input name="ywlx" id="ywlx" value="${ywlx }" type="hidden"/>
								</td>
							</tr>
							<tr>
								<td class="tds" colspan="11">
									<div align="center">
										<input class="bnt" type="submit" value="查  询"
											style="cursor: pointer;" />
									</div>
								</td>
							</tr>
						</table>
					</form>
					<table class="datalist" width="100%" border="0" cellpadding="0"
						cellspacing="0">
						<tr class="tr1">
							<th>
								序号
							</th>
							<th>
								审核人
							</th>
							<th>
								审核数
							</th>
							<th>
								审核通过数
							</th>
							<th>
								审核未通过数
							</th>
							<th>
								操作
							</th>
						</tr>
						<s:if test="#request.drvtjxqList.size > 0">
							<s:iterator id="dc" value="#request.drvtjxqList"
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
										${dc[2] }
									</td>
									<td>
										${dc[3] }
									</td>
									<td>
										<a href="#" onclick="openview('${dc[0]}','${sdate}','${edate}');">
										点击查看详细</a>
									</td>

								</tr>
							</s:iterator>
						</s:if>
						<s:else>
							<tr>
								<td colspan="8">
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

