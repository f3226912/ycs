<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<title>验车结果查询</title>
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

		<script type="text/javascript">
			var chuli;
			$(document).ready(function() {
				
			});
			function clearfrom(){
				
			}
			
			function submitfrom() {
				$("#table0").hide();
				$("#table1").hide();
				$("#divid1").hide();
				$("#tableid0").hide();
				$("#tableid1").hide();
				$("#tableid2").hide();
				$("#tableid3").hide();
				$(".tdc").html("");
				$("#ycjgid").html("");
				var imgurl = '<%=request.getContextPath()%>/artDialog4.1.6/skins/icons/loading2.gif';
				chuli = art.dialog({
				    content: '<div style=\"text-align:center; text-indent:-999em; overflow:hidden; background:url('+imgurl+') no-repeat center center;\"><span>loading..</span></div><br/>数据处理中,请稍候.....',
				    title: '数据处理中',
	    			lock: true,
				    opacity: 0.87
				});
				var lsh = $("#lshid").val();
				var clsbdh = $("#clsbdhid").val();
				$.ajax({
					type:'POST',
					url:'<%=request.getContextPath()%>/yanche/vehpcb_getYcjgAjax.action',
					data:{lsh:lsh,clsbdh:clsbdh},
					dataType: 'json',
					error:function(){
						alert("读取数据异常!");
					},
					success:function(data){
						chuli.close();
						if(null != data && "" != data){
							
							var zttdstr = "";
							if(null != data.chrq && "" != data.chrq){
								if(null != data.ycfprq && "" != data.ycfprq){
									if(null != data.ycmjrq && "" != data.ycmjrq){
										if(null != data.qrmjrq && "" != data.qrmjrq){
											zttdstr = "<font color='red'>预约验车("+data.chrq+")---->分配验车("+data.ycfprq+")---->上门验车("+data.ycmjrq+")---->确认验车("+data.qrmjrq+")</font>";
										}else{
											zttdstr = "<font color='red'>预约验车("+data.chrq+")---->分配验车("+data.ycfprq+")---->上门验车("+data.ycmjrq+")----></font>确认验车";
										}
									}else{
										zttdstr = "<font color='red'>预约验车("+data.chrq+")---->分配验车("+data.ycfprq+")----></font>上门验车---->确认验车";
									}
								}else{
									zttdstr = "<font color='red'>预约验车("+data.chrq+")----></font>分配验车---->上门验车---->确认验车";
								}
							}else{
								zttdstr = "预约验车---->分配验车---->上门验车---->确认验车";
							}
							$("#zttdid").html(zttdstr);
							
							if(null != data.chrq && "" != data.chrq){
								$("#tdid1").html(data.chrq);
								$("#tdid2").html("预约验车");
								$("#tdid3").html(data.chmc);
							}
							if(null != data.ycfprq && "" != data.ycfprq){
								$("#tdid4").html(data.ycfprq);
								$("#tdid5").html("分配验车");
								$("#tdid6").html(data.fpYcmjxm);
							}
							if(null != data.ycmjrq && "" != data.ycmjrq){
								$("#tdid7").html(data.ycmjrq);
								$("#tdid8").html("上门验车");
								$("#tdid9").html(data.ycmjxm);
							}
							if(null != data.qrmjrq && "" != data.qrmjrq){
								$("#tdid10").html(data.qrmjrq);
								$("#tdid11").html("确认验车");
								$("#tdid12").html(data.qrmjxm);
							}
							
							$("#ycjgid").html("&nbsp;验车状态:<font color='red'>" + data.ycjg + "</font>");
							if("合格" == data.ycjg){
								$("#ycbtgid").hide();
							}else{
								$("#ycbtgid").show();
								$("#tdid34").html("&nbsp;" + data.ycjgYy);
							}
							
							$("#tdid13").html("&nbsp;" + data.smycyypch);
							$("#tdid14").html("&nbsp;" + data.smycyypchsl);
							$("#tdid15").html("&nbsp;" + data.clcfdd);
							$("#tdid16").html("&nbsp;" + data.yycgbm);
							$("#tdid17").html("&nbsp;" + data.chid);
							$("#tdid18").html("&nbsp;" + data.chmc);
							$("#tdid19").html("&nbsp;" + data.chrq);
							$("#tdid20").html("&nbsp;" + data.chip);
							$("#tdid21").html("&nbsp;" + data.chmac);
							$("#tdid22").html("&nbsp;" + data.yclx);
							$("#tdid23").html("&nbsp;" + data.ycpch);
							$("#tdid24").html("&nbsp;" + data.ycfpxm);
							$("#tdid25").html("&nbsp;" + data.ycfpbm);
							$("#tdid26").html("&nbsp;" + data.ycfprq);
							$("#tdid27").html("&nbsp;" + data.yjsmsj);
							$("#tdid28").html("&nbsp;" + data.fpYcmjxm);
							$("#tdid29").html("&nbsp;" + data.fpYcmjbm);
							$("#tdid30").html("&nbsp;" + data.ycfpqkMs);
							$("#tdid31").html("&nbsp;" + data.ycmjxm);
							$("#tdid32").html("&nbsp;" + data.ycmjbm);
							$("#tdid33").html("&nbsp;" + data.ycmjrq);
							$("#tdid35").html("&nbsp;" + data.sjqrLsh);
							$("#tdid36").html("&nbsp;" + data.qrjg);
							$("#tdid37").html("&nbsp;" + data.qrjgYy);
							$("#tdid38").html("&nbsp;" + data.qrmjxm);
							$("#tdid39").html("&nbsp;" + data.qrmjbm);
							$("#tdid40").html("&nbsp;" + data.qrmjrq);
							$("#tdid41").html("&nbsp;" + data.pzzt);
							
							$("#table0").show();
							$("#table1").show();
							$("#divid1").show();
							settab(this,0);
						}else{
							alert("没有该验车数据,请核实后再查!");
						}
					}
				});
			}
			
			//清除空格和'$'
			function clearspace(obj){
				obj.value = obj.value.replace(" ","");
				obj.value = obj.value.replace("　","");
				obj.value = obj.value.replace("（","(");
				obj.value = obj.value.replace("）",")");
			}
			
			//小写字母变大写字母,适用于号牌号码.
			function xiaobianda(obj){
				obj.value = obj.value.replace(" ","");
				obj.value = obj.value.replace("　","");
				obj.value = obj.value.toUpperCase();
			}
			
			
			//页面重新加载
			function reonload(){
				window.location.reload();
			}
			
			function closechuli(){
				chuli.close();
			}
			
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
			
			
			function settab(obj,n){
				var tds = $("#test2_li_now_ li");
				for(var i=0;i < tds.length;i++){ 
					tds[i].className=i==n?"now":"";
					$("#tableid" + i).hide();
				}
				$("#tableid" + n).show();
			}
			
			function cleartext(type){
				if('1' == type){
					$("#clsbdhid").val("");
				}else if('2' == type){
					$("#lshid").val("");
				}
			}
		</script>
		
</head>
<body>
	<DIV id=admin_title>
		<DIV class=logo>
			<IMG src="<%=request.getContextPath()%>/images/frame_19.gif">
		</DIV>
		<DIV class=title>
			验车结果查询&nbsp;
		</DIV>
	</DIV>
	<div id=admin_main style="WIDTH:94%; margin:0 auto;margin: 25px;margin-top:15px;font-size: 12px;color: #000;">
		<div class="div">
			<div class="tablist block">
				<table class="edittable" border="0" cellpadding="0" cellspacing="0" align="center">
					<tr>
						<td colspan="6" style="text-align: center;"><strong>验车结果查询</strong></td>
					</tr>
					<tr>
						<td style="text-align: right;width: 13%">流水号：</td>
						<td style="text-align: left;width: 20%">
							&nbsp;<input type="text" class="disabled1" id="lshid" onkeyup="javascript:cleartext('1');"
								name="lsh" size="20"
								maxlength="20" />&nbsp;
						</td>
						<td style="text-align: right;width: 13%">车辆识别代号：</td>
						<td style="text-align: left;width: 20%">
							&nbsp;<input type="text" class="disabled1" id="clsbdhid" onkeyup="javascript:cleartext('2');"
								name="clsbdh" size="20"
								maxlength="20" />&nbsp;
						</td>
						<td style="text-align: left;" colspan="2">
							&nbsp;<input type="button" style="cursor:pointer;" onclick="javascript:submitfrom();" value="查 询" class="bnt1" />
						</td>
					</tr>
				</table>
				<table class="edittable" id="table1" border="0" cellpadding="0" cellspacing="0" align="center" style="display: none;">
					<tr>
						<td style="text-align: center;width: 100%" class="tdc" id="zttdid"></td>
					</tr>
				</table>
				<table class="edittable" id="table0" border="0" cellpadding="0" cellspacing="0" align="center" style="display: none;">
					<tr>
						<td colspan="6" style="text-align: left;"><strong>数据处理跟踪</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong id="ycjgid"></strong></td>
					</tr>
					<tr>
						<td style="text-align: center;width: 15%">时间</td>
						<td style="text-align: center;width: 15%">操作过程</td>
						<td style="text-align: center;width: 15%">操作人</td>
					</tr>
					<tr>
						<td style="text-align: center;width: 15%" class="tdc" id="tdid1"></td>
						<td style="text-align: center;width: 15%" class="tdc" id="tdid2"></td>
						<td style="text-align: center;width: 15%" class="tdc" id="tdid3"></td>
					</tr>
					<tr>
						<td style="text-align: center;width: 15%" class="tdc" id="tdid4"></td>
						<td style="text-align: center;width: 15%" class="tdc" id="tdid5"></td>
						<td style="text-align: center;width: 15%" class="tdc" id="tdid6"></td>
					</tr>
					<tr>
						<td style="text-align: center;width: 15%" class="tdc" id="tdid7"></td>
						<td style="text-align: center;width: 15%" class="tdc" id="tdid8"></td>
						<td style="text-align: center;width: 15%" class="tdc" id="tdid9"></td>
					</tr>
					<tr>
						<td style="text-align: center;width: 15%" class="tdc" id="tdid10"></td>
						<td style="text-align: center;width: 15%" class="tdc" id="tdid11"></td>
						<td style="text-align: center;width: 15%" class="tdc" id="tdid12"></td>
					</tr>
				</table>
				<div class="tab2" style="display: none;" id="divid1">
					<ul id="test2_li_now_">
						<li onclick="settab(this,0)" class="now">预约验车信息</li>
						<li onclick="settab(this,1)">分配验车信息</li>
						<li onclick="settab(this,2)">上门验车信息</li>
						<li onclick="settab(this,3)">确认验车信息</li>
					</ul>
				</div>
				<table class="edittable" id="tableid0" border="0" cellpadding="0" cellspacing="0" align="center" style="display: none;">
					<tr>
						<td style="text-align: right;width: 25%">预约批次号：</td>
						<td style="text-align: left;width: 75%" class="tdc" id="tdid13"></td>
					</tr>
					<tr>
						<td style="text-align: right;width: 25%">预约批次数量：</td>
						<td style="text-align: left;width: 75%" class="tdc" id="tdid14"></td>
					</tr>
					<tr>
						<td style="text-align: right;width: 25%">车辆存放地点：</td>
						<td style="text-align: left;width: 75%" class="tdc" id="tdid15"></td>
					</tr>
					<tr>
						<td style="text-align: right;width: 25%">预约车管部门：</td>
						<td style="text-align: left;width: 75%" class="tdc" id="tdid16"></td>
					</tr>
					<tr>
						<td style="text-align: right;width: 25%">车行id：</td>
						<td style="text-align: left;width: 75%" class="tdc" id="tdid17"></td>
					</tr>
					<tr>
						<td style="text-align: right;width: 25%">车行名称：</td>
						<td style="text-align: left;width: 75%" class="tdc" id="tdid18"></td>
					</tr>
					<tr>
						<td style="text-align: right;width: 25%">车行日期：</td>
						<td style="text-align: left;width: 75%" class="tdc" id="tdid19"></td>
					</tr>
					<tr>
						<td style="text-align: right;width: 25%">车行ip：</td>
						<td style="text-align: left;width: 75%" class="tdc" id="tdid20"></td>
					</tr>
					<tr>
						<td style="text-align: right;width: 25%">车行录入MAC地址：</td>
						<td style="text-align: left;width: 75%" class="tdc" id="tdid21"></td>
					</tr>
					<tr>
						<td style="text-align: right;width: 25%">验车类型：</td>
						<td style="text-align: left;width: 75%" class="tdc" id="tdid22"></td>
					</tr>
				</table>
				<table class="edittable" id="tableid1" border="0" cellpadding="0" cellspacing="0" align="center" style="display: none;">
					<tr>
						<td style="text-align: right;width: 25%">验车分配批次：</td>
						<td style="text-align: left;width: 75%" class="tdc" id="tdid23"></td>
					</tr>
					<tr>
						<td style="text-align: right;width: 25%">验车分配人姓名：</td>
						<td style="text-align: left;width: 75%" class="tdc" id="tdid24"></td>
					</tr>
					<tr>
						<td style="text-align: right;width: 25%">验车分配人部门：</td>
						<td style="text-align: left;width: 75%" class="tdc" id="tdid25"></td>
					</tr>
					<tr>
						<td style="text-align: right;width: 25%">验车分配日期：</td>
						<td style="text-align: left;width: 75%" class="tdc" id="tdid26"></td>
					</tr>
					<tr>
						<td style="text-align: right;width: 25%">预计上门验车时间：</td>
						<td style="text-align: left;width: 75%" class="tdc" id="tdid27"></td>
					</tr>
					<tr>
						<td style="text-align: right;width: 25%">分配验车民警姓名：</td>
						<td style="text-align: left;width: 75%" class="tdc" id="tdid28"></td>
					</tr>
					<tr>
						<td style="text-align: right;width: 25%">分配验车民警部门：</td>
						<td style="text-align: left;width: 75%" class="tdc" id="tdid29"></td>
					</tr>
					<tr>
						<td style="text-align: right;width: 25%">验车分配情况描述：</td>
						<td style="text-align: left;width: 75%" class="tdc" id="tdid30"></td>
					</tr>
				</table>
				<table class="edittable" id="tableid2" border="0" cellpadding="0" cellspacing="0" align="center" style="display: none;">
					<tr>
						<td style="text-align: right;width: 25%">验车民警姓名：</td>
						<td style="text-align: left;width: 75%" class="tdc" id="tdid31"></td>
					</tr>
					<tr>
						<td style="text-align: right;width: 25%">验车民警部门：</td>
						<td style="text-align: left;width: 75%" class="tdc" id="tdid32"></td>
					</tr>
					<tr>
						<td style="text-align: right;width: 25%">验车民警日期：</td>
						<td style="text-align: left;width: 75%" class="tdc" id="tdid33"></td>
					</tr>
					<tr style="display: none;" id="ycbtgid">
						<td style="text-align: right;width: 25%">验车不通过选项：</td>
						<td style="text-align: left;width: 75%" class="tdc" id="tdid34"></td>
					</tr>
				</table>
				<table class="edittable" id="tableid3" border="0" cellpadding="0" cellspacing="0" align="center" style="display: none;">
					<tr>
						<td style="text-align: right;width: 25%">数据确认流水号：</td>
						<td style="text-align: left;width: 75%" class="tdc" id="tdid35"></td>
					</tr>
					<tr>
						<td style="text-align: right;width: 25%">上门验车数据确认结果：</td>
						<td style="text-align: left;width: 75%" class="tdc" id="tdid36"></td>
					</tr>
					<tr>
						<td style="text-align: right;width: 25%">验车确认：</td>
						<td style="text-align: left;width: 75%" class="tdc" id="tdid37"></td>
					</tr>
					<tr>
						<td style="text-align: right;width: 25%">确认民警姓名：</td>
						<td style="text-align: left;width: 75%" class="tdc" id="tdid38"></td>
					</tr>
					<tr>
						<td style="text-align: right;width: 25%">确认民警部门：</td>
						<td style="text-align: left;width: 75%" class="tdc" id="tdid39"></td>
					</tr>
					<tr>
						<td style="text-align: right;width: 25%">确认民警时间：</td>
						<td style="text-align: left;width: 75%" class="tdc" id="tdid40"></td>
					</tr>
					<tr>
						<td style="text-align: right;width: 25%">上门验车是否拍照：</td>
						<td style="text-align: left;width: 75%" class="tdc" id="tdid41"></td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</body>
</html>