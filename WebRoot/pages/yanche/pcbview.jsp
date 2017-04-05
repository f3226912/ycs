<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<title>验车批次详细信息</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/module.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/jquery-1.8.2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/My97DatePicker/WdatePicker.js"></script>
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
<script type="text/javascript" src="<%=request.getContextPath()%>/js/public.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/jquery-lightbox-0.5/js/jquery.lightbox-0.5.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery/jquery-lightbox-0.5/css/jquery.lightbox-0.5.css" edia="screen" />
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

.div {
	width: 100%;
	height: 565px;
}

.tab1,.tab2 {
	width: 100%;
	height: 34px;
	border: 1px #4fb3d5 solid;
	border-bottom: 0;
	background: url(<%=request.getContextPath()%>/images/bj.gif ) repeat-x;
	background: #407fb7;
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
	display: none;
}

.block {
	display: block;
	width: 100%;
	height: 100%;
	background: #fff;
}

.edittable {
	width: 100%;
	background: #FFFFFF;
	margin: 0 auto;
}

td {
	background: #FFFFFF;
}

.table td {
	width: 50px;
}

.text {
	border: 1px #CCCCCC solid;
}

strong {
	letter-spacing: 1px;
	font-size: 13px;
	padding-left: 10px;
}

.bnt {
	width: 72px;
	height: 21px;
	line-height: 21px;
	border: none;
	background: url(<%=request.getContextPath()%>/images/an.gif )
		no-repeat;
	color: #FFFFFF;
	vertical-align: middle;
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

span {
	color: #CC0000;
	letter-spacing: 2px;
}
</style>
</head>
<body>
	<DIV id=admin_title>
		<DIV class=logo>
			<IMG src="<%=request.getContextPath()%>/images/frame_19.gif">
		</DIV>
		<DIV class=title>
			查看验车批次详细信息&nbsp;
		</DIV>
	</DIV>
	<div id=admin_main style="WIDTH:95%; margin:0 auto;margin: 25px;margin-top:15px;font-size: 12px;color: #000;">
		<div class="div">
			<div id="tableid1" class="tablist block">
				<table class="edittable" border="0" cellpadding="0" cellspacing="0" align="center">
					<tr>
						<td colspan="6"><strong>预约信息</strong></td>
					</tr>
					<tr>
						<td style="text-align: right;width: 13%">流水号：</td>
						<td style="text-align: left;width: 20%">
							&nbsp;${pdasmycVehPcb.lsh}
						</td>
						<td style="text-align: right;width: 13%">预约批次号：</td>
						<td style="text-align: left;width: 20%">
							&nbsp;${pdasmycVehPcb.smycyypch}
						</td>
						<td style="text-align: right;width: 13%">预约批次数量：</td>
						<td style="text-align: left;width: 21%">
							&nbsp;${pdasmycVehPcb.smycyypchsl}
						</td>
					</tr>
					<tr>
						<td style="text-align: right;width: 13%">车辆存放地点：</td>
						<td style="text-align: left;width: 20%">
							&nbsp;${pdasmycVehPcb.clcfdd}
						</td>
						<td style="text-align: right;width: 13%">预约车管部门：</td>
						<td style="text-align: left;width: 20%">
							&nbsp;${pdasmycVehPcb.yycgbm}
						</td>
						<td style="text-align: right;width: 13%">车行id：</td>
						<td style="text-align: left;width: 21%">
							&nbsp;${pdasmycVehPcb.chid}
						</td>
					</tr>
					<tr>
						<td style="text-align: right;width: 13%">车行名称：</td>
						<td style="text-align: left;width: 20%">
							&nbsp;${pdasmycVehPcb.chmc}
						</td>
						<td style="text-align: right;width: 13%">车行日期：</td>
						<td style="text-align: left;width: 20%">
							&nbsp;<!--<s:date name="#request.pdasmycVehPcb.chrq" format="yyyy-MM-dd HH:mm:ss"/>-->
							<s:date name="#request.pdasmycVehPcb.chrq" format="yyyy-MM-dd"/>
						</td>
						<td style="text-align: right;width: 13%">车行ip：</td>
						<td style="text-align: left;width: 21%">
							&nbsp;${pdasmycVehPcb.chip}
						</td>
					</tr>
					<tr>
						<td style="text-align: right;width: 13%">车行录入MAC地址：</td>
						<td style="text-align: left;width: 20%">
							&nbsp;${pdasmycVehPcb.chmac}
						</td>
						<td style="text-align: right;width: 13%">验车类型：</td>
						<td style="text-align: left;width: 20%">
							&nbsp;${pdasmycVehPcb.yclx}
						</td>
						<td style="text-align: right;width: 13%">实际验车类型：</td>
						<td style="text-align: left;width: 21%">
							&nbsp;${pdasmycVehPcb.yclxSj}
						</td>
					</tr>
					<tr>
						<td colspan="6"><strong>验车分配信息</strong></td>
					</tr>
					<tr>
						<td style="text-align: right;width: 13%">验车分配状态：</td>
						<td style="text-align: left;width: 20%">
							&nbsp;${pdasmycVehPcb.ycfpqk}
						</td>
						<td style="text-align: right;width: 13%">验车分配情况描述：</td>
						<td style="text-align: left;width: 20%">
							&nbsp;${pdasmycVehPcb.ycfpqkMs}
						</td>
						<td style="text-align: right;width: 13%">验车分配批次：</td>
						<td style="text-align: left;width: 21%">
							&nbsp;${pdasmycVehPcb.ycpch}
						</td>
					</tr>
					<tr>
						<td style="text-align: right;width: 13%">验车分配人代码：</td>
						<td style="text-align: left;width: 20%">
							&nbsp;${pdasmycVehPcb.ycfpcode}
						</td>
						<td style="text-align: right;width: 13%">验车分配人姓名：</td>
						<td style="text-align: left;width: 20%">
							&nbsp;${pdasmycVehPcb.ycfpxm}
						</td>
						<td style="text-align: right;width: 13%">验车分配人部门：</td>
						<td style="text-align: left;width: 21%">
							&nbsp;${pdasmycVehPcb.ycfpbm}
						</td>
					</tr>
					<tr>
						<td style="text-align: right;width: 13%">验车分配人部门(科级)：</td>
						<td style="text-align: left;width: 20%">
							&nbsp;${pdasmycVehPcb.ycfpbmKj}
						</td>
						<td style="text-align: right;width: 13%">验车分配日期：</td>
						<td style="text-align: left;width: 20%">
							&nbsp;<s:date name="#request.pdasmycVehPcb.ycfprq" format="yyyy-MM-dd HH:mm:ss"/>
						</td>
						<td style="text-align: right;width: 13%">预计上门验车时间：</td>
						<td style="text-align: left;width: 21%">
							&nbsp;<s:date name="#request.pdasmycVehPcb.yjsmsj" format="yyyy-MM-dd"/>
						</td>
					</tr>
					<tr>
						<td style="text-align: right;width: 13%">分配验车民警代码：</td>
						<td style="text-align: left;width: 20%">
							&nbsp;${pdasmycVehPcb.fpYcmjcode}
						</td>
						<td style="text-align: right;width: 13%">分配验车民警姓名：</td>
						<td style="text-align: left;width: 20%">
							&nbsp;${pdasmycVehPcb.fpYcmjxm}
						</td>
						<td style="text-align: right;width: 13%">分配验车民警部门：</td>
						<td style="text-align: left;width: 21%">
							&nbsp;${pdasmycVehPcb.fpYcmjbm}
						</td>
					</tr>
					<tr>
						<td colspan="6"><strong>验车信息</strong></td>
					</tr>
					<tr>
						<td style="text-align: right;width: 13%">验车结果：</td>
						<td style="text-align: left;width: 20%">
							&nbsp;${pdasmycVehPcb.ycjg}
						</td>
						<td style="text-align: right;width: 13%">验车不通过选项：</td>
						<td style="text-align: left;width: 20%">
							&nbsp;${pdasmycVehPcb.ycjgYy}
						</td>
						<td style="text-align: right;width: 13%">验车民警代码：</td>
						<td style="text-align: left;width: 21%">
							&nbsp;${pdasmycVehPcb.ycmjcode}
						</td>
					</tr>
					<tr>
						<td style="text-align: right;width: 13%">验车民警姓名：</td>
						<td style="text-align: left;width: 20%">
							&nbsp;${pdasmycVehPcb.ycmjxm}
						</td>
						<td style="text-align: right;width: 13%">确认民警部门：</td>
						<td style="text-align: left;width: 20%">
							&nbsp;${pdasmycVehPcb.ycmjbm}
						</td>
						<td style="text-align: right;width: 13%">确认民警时间：</td>
						<td style="text-align: left;width: 21%">
							&nbsp;<s:date name="#request.pdasmycVehPcb.ycmjrq" format="yyyy-MM-dd HH:mm:ss"/>
						</td>
					</tr>
					<tr>
						<td colspan="6"><strong>验车确认信息</strong></td>
					</tr>
					<tr>
						<td style="text-align: right;width: 13%">数据确认流水号：</td>
						<td style="text-align: left;width: 20%">
							&nbsp;${pdasmycVehPcb.sjqrLsh}
						</td>
						<td style="text-align: right;width: 13%">数据确认结果：</td>
						<td style="text-align: left;width: 20%">
							&nbsp;${pdasmycVehPcb.qrjg}
						</td>
						<td style="text-align: right;width: 13%">数据确认选项：</td>
						<td style="text-align: left;width: 21%">
							&nbsp;${pdasmycVehPcb.qrjgYy}
						</td>
					</tr>
					<tr>
						<td style="text-align: right;width: 13%">确认民警代码：</td>
						<td style="text-align: left;width: 20%">
							&nbsp;${pdasmycVehPcb.qrmjcode}
						</td>
						<td style="text-align: right;width: 13%">确认民警姓名：</td>
						<td style="text-align: left;width: 20%">
							&nbsp;${pdasmycVehPcb.qrmjxm}
						</td>
						<td style="text-align: right;width: 13%">确认民警部门：</td>
						<td style="text-align: left;width: 21%">
							&nbsp;${pdasmycVehPcb.qrmjbm}
						</td>
					</tr>
					<tr>
						<td style="text-align: right;width: 13%">确认民警时间：</td>
						<td style="text-align: left;width: 20%">
							&nbsp;<s:date name="#request.pdasmycVehPcb.qrmjrq" format="yyyy-MM-dd HH:mm:ss"/>
						</td>
						<td style="text-align: right;width: 13%">退办原因描述：</td>
						<td style="text-align: left;width: 20%">
							&nbsp;${pdasmycVehPcb.tbyyms}
						</td>
						<td style="text-align: right;width: 13%">上门验车是否拍照：</td>
						<td style="text-align: left;width: 21%">
							&nbsp;${pdasmycVehPcb.pzzt}
						</td>
					</tr>
				</table>
				<table class="edittable" border="0" cellpadding="0" cellspacing="0" align="center">
					<tr>
						<td colspan="4" height="50" align="center"
							style="text-align: center">
							<input type="button" style="cursor:pointer;" onclick="javascript:window.close();" value="关 闭" class="bnt1" />
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</body>
</html>