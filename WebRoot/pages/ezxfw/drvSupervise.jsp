<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="com.ycszh.common.PageTag" prefix="pt"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
	<head>
		<title>驾驶证未网上申报数据预警列表</title>
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
			src="<%=request.getContextPath()%>/jquery/js/jquery-ui-1.9.1.custom.js">
</script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/artDialog4.1.6/jquery.artDialog.source.js?skin=chrome">
</script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/artDialog4.1.6/plugins/iframeTools.source.js">
</script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/public.js">
</script>
		<script type="text/javascript">

function exports() {
    var date1=$("#startDt").val();
    var date2=$("#endDt").val();
    if(!date1){
        alert("请选择开始时间");
        $("#startDt").focus();
        return false;
    }
    if(!date2){
        alert("请选择结束时间");
        $("#endDt").focus();
        return false;
    }
    date1=Date.parse(date1);
    date2=Date.parse(date2);
    if(((date2-date1)/86400000)>7){
        alert("开始时间与结束时间之间间隔已经超过七天");
        return false;
    }
   
	$("#esfrom")
			.attr("action",
					"<%=request.getContextPath()%>/ezxfw/ezxfw_getDrvSuperviseData.action");
	$("#esfrom").submit();
}


$(function() {
	loadInit();
})

function loadInit() {
	var sywlx = "${drvSupervise.ywlx}";
	if (sywlx) {
		$("#sltYwlx").val(sywlx);
	}
}
</script>
	</head>
	<body style="background: #c7e5ff;">
		<div class="content1" style="width: 100%;">
			<div class="roundedBox" id="type1" style="width: 95%;">
				<div class="right" style="width: 98%;">
					<form action="" method="post" id="esfrom">
						<input type="hidden" name="stauts" id="stauts" value="1" />
						<table class="table1" width="100%" border="0" cellpadding="0"
							cellspacing="0">
							<tr>
								<th class="th1" height="32" colspan="4">
									查询条件
								</th>
							</tr>
							<tr>
								<td class="tds" style="text-align: right">
									六合一受理时间&nbsp;
								</td>
								<td class="tdl" style="text-align: left;" colspan="3">
									<input name="drvSupervise.startDt" id="startDt"
										value="${drvSupervise.startDt}" size="10" type="text"
										onclick="WdatePicker({minDate:'y%-M%-{%d-90}'})" />
									止
									<input name="drvSupervise.endDt" id="endDt" value="${drvSupervise.endDt}"
										size="10" type="text"
										onclick="WdatePicker({minDate:'y%-M%-{%d-90}'})" />
									<font color="red">温馨提示内容：仅能查90天范围内的业务办理数据,单次查询时间段在7天之内</font>
								</td>
							</tr>
							<tr>
								<td class="tds" style="text-align: right;">
									业务类型&nbsp;
								</td>
								<td class="tdl" style="text-align: left;" clospan="3">
									<select id="sltYwlx" name="drvSupervise.ywlx" >
									   <!--  <option value="">------请选择------</option> -->
										<option value="C">
											补证换证
										</option>
										
									</select>
								</td>
								
								<td class="tds" style="text-align: right; width: 150px;">
									驾驶证号码&nbsp;
								</td>
								<td style="text-align: left; right; width: 300px;">

									<input type="text" name="drvSupervise.jszhm" id="txtJszhm" maxlength="35"
										value="${drvSupervise.jszhm}" style="width: 150px;" />

								</td>

							</tr>
							<tr>
								<td class="tds" colspan="4">
									<div align="center">
										<input class="bnt" type="button" value="查  询"
											style="cursor: pointer;" onclick="return exports()" />
									</div>
								</td>
							</tr>
						</table>
					</form>
					<br>
					<p style="color: red; font-size: 12px;">
		                                     预警规则：没有经过网上车管所或在线申请 90天内未监管驾驶证业务相关数据
					</p>
					<table class="datalist" width="100%" border="0" cellpadding="0"
						cellspacing="0">
						<tr class="tr1">
							<th>
								序号
							</th>
							<th>
								流水号
							</th>
							<th>
								驾驶证号码
							</th>
							<th>
								档案编号
							</th>
							<th>
								姓名
							</th>
							
							<th>
								开始时间
							</th>
							<th>
								结束时间
							</th>
							<th>
								管理部门
							</th>

						</tr>
						<s:if test="drvSupervise.drvList.size > 0">
							<s:iterator id="veh" value="drvSupervise.drvList" status="st">
								<tr class="<s:if test="#st.odd == false">altrow</s:if>">
									<td>
										${st.count}
									</td>
									<td>
										${veh[0]}
									</td>

									<td>
										${veh[1]}
									</td>
									<td>
										${veh[2]}
									</td>
									<td>
										${veh[3]}
									</td>
									
									<td>
										${veh[6]}
									</td>
									<td>
										${veh[7]}
									</td>

									<td>
										${veh[11]}
									</td>


									<%-- <a href="<%=request.getContextPath()%>/veh/veh_initSlCx.action?id=${dbjgZjxxb.id}" target="_blank">查看</a> --%>
								</tr>
							</s:iterator>
						</s:if>
						<s:else>
							<tr>
								<td colspan="15">
									<span style="color: red">暂时没有相关数据</span>
								</td>
							</tr>
						</s:else>
					</table>
					<s:if test="drvSupervise.drvList.size>0">
						<table width="100%" border="0" cellspacing="0" cellpadding="0"
							style="padding-top: 5px;">
							<pt:write uri="${drvSupervise.pageUrl}"
								pagesize="${drvSupervise.pageSize}"
								rscount="${drvSupervise.pageCount}"
								currentpage="${drvSupervise.currentPage}"></pt:write>
						</table>
					</s:if>
				</div>
				<div class="corner topLeft"></div>
				<div class="corner topRight"></div>
			</div>
		</div>
	</body>
</html>
