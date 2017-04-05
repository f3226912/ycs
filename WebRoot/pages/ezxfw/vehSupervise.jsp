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
		<title>机动车未网上申报数据预警列表</title>
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
    var ywlx=$("#sltYwlx option:selected").val();
    if(!ywlx){
        alert("请选择业务类型");
        return false;
    }
    if(ywlx=='P:F'){
        alert("业务数据暂没供给，暂无运算");
        return false;
    }
   
	$("#esfrom")
			.attr("action","<%=request.getContextPath()%>/ezxfw/ezxfw_getVehSuperviseData.action");
	$("#esfrom").submit();
}


$(function() {
	loadInit();
})

function loadInit() {
	var sywlx = "${vehSupervise.ywlx}";
	var shpzl="${vehSupervise.hpzl}"
	if (sywlx) {
		$("#sltYwlx").val(sywlx);
		$("#sltHpzl").val(shpzl);
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
									<input name="vehSupervise.startDt" id="startDt"
										value="${vehSupervise.startDt}" size="10" type="text"
										onclick="WdatePicker({minDate:'y%-M%-{%d-90}'})" />
									止
									<input name="vehSupervise.endDt" id="endDt" value="${vehSupervise.endDt}"
										size="10" type="text"
										onclick="WdatePicker({minDate:'y%-M%-{%d-90}'})" />
									<font color="red">温馨提示内容：仅能查90天范围内的业务办理数据,单次查询时间段在7天之内</font>
								</td>
							</tr>
							<tr>
								<td class="tds" style="text-align: right;">
									业务类型&nbsp;
								</td>
								<td class="tdl" style="text-align: left;" colspan="3">
									<select id="sltYwlx" name="vehSupervise.ywlx">
										<option value="">
											------请选择------
										</option>
										<option value="E">
											抵押登记
										</option>
										<option value="Q:A">
											异地委托检验
										</option>
										<option value="K:B">
											补领行驶证
										</option>
										<option value="K:H">
											补领检验合格证
										</option>
										<option value="O">
											临时号牌
										</option>
										<option value="P:F">
											核发检验标志(免检车)
										</option>
									</select>
							</tr>
							<tr>
								<td class="tds" style="text-align: right; width: 150px;">
									号牌号码&nbsp;
								</td>
								<td style="text-align: left; right; width: 300px;">

									<input type="text" name="vehSupervise.hphm" id="txtHphm" maxlength="20"
										value="${vehSupervise.hphm}" style="width: 100px;" />

								</td>
								<td class="tds" style="text-align: right; width: 150px;">
									号牌种类&nbsp;
								</td>
								<td style="text-align: left;">
									<select id="sltHpzl" name="vehSupervise.hpzl">
										<option value="">
											------请选择------
										</option>
										<option value="01">
											大型汽车
										</option>
										<option value="02">
											小型汽车
										</option>
										<option value="03">
											使馆汽车
										</option>
										<option value="04">
											领馆汽车
										</option>
										<option value="05">
											境外汽车
										</option>
										<option value="06">
											外籍汽车
										</option>
										<option value="07">
											普通摩托车
										</option>
										<option value="08">
											轻便摩托车
										</option>
										<option value="09">
											使馆摩托车
										</option>
										<option value="10">
											领馆摩托车
										</option>
										<option value="11">
											境外摩托车
										</option>
										<option value="12">
											外籍摩托车
										</option>
										<option value="13">
											低速车
										</option>
										<option value="14">
											拖拉机
										</option>
										<option value="15">
											挂车
										</option>
										<option value="16">
											教练汽车
										</option>
										<option value="17">
											教练摩托车
										</option>
										<option value="18">
											试验汽车
										</option>
										<option value="19">
											试验摩托车
										</option>
										<option value="20">
											临时入境汽车
										</option>
										<option value="21">
											临时入境摩托车
										</option>
										<option value="22">
											临时行驶车
										</option>
										<option value="23">
											警用汽车
										</option>
										<option value="24">
											警用摩托
										</option>
										<option value="25">
											原农机号牌
										</option>
										<option value="26">
											香港入出境车
										</option>
										<option value="27">
											澳门入出境车
										</option>


									</select>
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
						预警规则：没有经过网上车管所或在线申请 90天内未监管机动车业务相关数据
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
								所有人
							</th>
							<th>
								号牌种类
							</th>
							<th>
								号牌号码
							</th>
							<th>
								车辆品牌
							</th>
							<th>
								车辆型号
							</th>
							<th>
								车辆类型
							</th>
							<th>
								管理部门
							</th>
							<th>
								申请日期
							</th>
							<th>
								办结日期
							</th>
						</tr>
						<s:if test="vehSupervise.vehList.size > 0">
							<s:iterator id="veh" value="vehSupervise.vehList" status="st">
								<tr class="<s:if test="#st.odd == false">altrow</s:if>">
									<td>
										${st.count}
									</td>
									<td>
										${veh[0]}
									</td>
									<td>
										${veh[4]}
									</td>
									<td>
										${veh[5]}
									</td>
									<td>
										${veh[6]}
									</td>
									<td>
										${veh[7]}
									</td>
									<td>
										${veh[8]}
									</td>
									<td>
										${veh[9]}
									</td>
									<td>
										${veh[16]}
									</td>
									<td>
										${veh[11]}
									</td>
									<td>
										${veh[12]}
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
					<s:if test="vehSupervise.vehList.size>0">
						<table width="100%" border="0" cellspacing="0" cellpadding="0"
							style="padding-top: 5px;">
							<pt:write uri="${vehSupervise.pageUrl}"
								pagesize="${vehSupervise.pageSize}"
								rscount="${vehSupervise.pageCount}"
								currentpage="${vehSupervise.currentPage}"></pt:write>
						</table>
					</s:if>
				</div>
				<div class="corner topLeft"></div>
				<div class="corner topRight"></div>
			</div>
		</div>
	</body>
</html>
