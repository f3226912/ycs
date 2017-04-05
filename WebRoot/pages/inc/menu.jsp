<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
  <head>
    <title>menu</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery/development-bundle/themes/base/jquery.ui.all.css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/jquery-1.8.2.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/development-bundle/ui/jquery.ui.core.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/development-bundle/ui/jquery.ui.widget.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/development-bundle/ui/jquery.ui.accordion.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/development-bundle/ui/jquery.ui.button.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/jquery-ui-1.9.1.custom.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery/css/redmond/jquery-ui-1.9.1.custom.css" />
	<style type="text/css">
		.ui-widget-content{
			border:0;
		}
		html,body{
			height:100%;
			margin:0;
			padding:0;
		}
		a:link {
			text-decoration: none;
			color:black;
		}
		a:visited {
			text-decoration: none;
			color:black;
		}
		a:hover {
			text-decoration: none;
			color:blue;
		}
		a:active {
			text-decoration: none;
			color:blue;
		}
		#accordion{
			margin:0;
			padding:0;
			width:182px;
			font-size:12px;
		}
		#accordion ul{
			margin:0;
			padding:0;
		}
		#accordion ul li{
			list-style-type: none;
		    margin: 20px auto 20px 18px;
		    padding: 0;
		    text-align: left;
		}
	</style>
	<script type="text/javascript">
	    $(function(){
	    	var icons = {
				header: "ui-icon-circle-arrow-e",
				headerSelected: "ui-icon-circle-arrow-s"
			};
			$("#accordion").accordion({
				autoHeight: false,
				navigation: true,
				fillSpace: false,
				collapsible: false,
				icons: icons
			});
		});
		$(document).ready(function() {
			$(".aclass").click(function(){
				$(".aclass").attr("style", "");
				$(this).attr("style","color:red");
			});
		});
		function HideOrShow(){
			var showed = document.all.item("showed");
	     	if(showed.value!=null){
	     		if(showed.value=="true"){
                    window.parent.FrameLeft.cols="8,*,8";
	     			showed.value="false";
	     			$("#tddivid").show();
	     		}else{
                    window.parent.FrameLeft.cols="200,*,8"
	        		showed.value="true";
	        		$("#tddivid").hide();
	     		}
	     	}else{
	     		alert("Error");
	     	}
		}
    </script>
  </head>
  <body>
	<table width="190" height="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td width="8" style="background:url(<%=request.getContextPath()%>/images/frame_12.gif) 0 0 repeat-y">
				&nbsp;<!-- <div id="tddivid" style="display: none;"><img title="点击显示导航菜单" style="cursor: pointer;" onclick="HideOrShow()" height="50" src="<%=request.getContextPath()%>/images/show_left.gif" width="8" name="pic"></div> -->
			</td>
			<td width="182" valign="top">
			<div id="accordion" style="overflow: auto;">
				<h3><a href="#">机动车业务->(无拍照)机动车受理业务</a></h3>
				<ul>
					<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/wpz/wpz_initJdcslPage_wpz.action" class="aclass" target="mainFrame">机动车业务受理(无拍照)</a></li>
					<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/wpz/wpz_initSlCxList_wpz.action" class="aclass" target="mainFrame">机动车业务受理查询(无拍照)</a></li>
				</ul>
				<h3><a href="#">驾驶证业务</a></h3>
				<ul>
					<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/drv/initInertSlgDrvXxcjb.action" class="aclass" target="mainFrame">驾驶证业务受理</a></li>
					<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/drv/initSlgDrvXxcjbList.action" class="aclass" target="mainFrame">驾驶证受理信息查询(旧版)</a></li>
					<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/drv/initSlgDrvXxcjbList2.action?isnew=1" class="aclass" target="mainFrame">驾驶证受理信息查询(新版)</a></li>
					<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/yujing/slgSpxx_initSlgSpxxList.action" class="aclass" target="mainFrame">驾驶证受理业务审批</a></li>
					<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/yujing/yujing_initYujingList.action" class="aclass" target="mainFrame">驾驶证受理业务数据预警</a></li>
					<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/drv/insertSlgDrvXxcjb.action?ywlx=MFXX" class="aclass" target="mainFrame">满分学习考试业务</a></li>
					<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/drv/insertSlgDrvXxcjb.action?ywlx=CCSL" class="aclass" target="mainFrame">初次申领业务</a></li>
					<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/drv/insertSlgDrvXxcjb.action?ywlx=ZJSL" class="aclass" target="mainFrame">增加准驾车型申领业务</a></li>
					<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/drv/insertSlgDrvXxcjb.action?ywlx=JJSL" class="aclass" target="mainFrame">持军队、武装警察部队驾驶证申领业务</a></li>
					<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/drv/insertSlgDrvXxcjb.action?ywlx=LSXK" class="aclass" target="mainFrame">临时机动车驾驶许可申领业务</a></li>
					<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/drv/insertSlgDrvXxcjb.action?ywlx=BZHZ" class="aclass" target="mainFrame">驾驶证补证换证业务</a></li>
					<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/drv/insertSlgDrvXxcjb.action?ywlx=JSZZX" class="aclass" target="mainFrame">驾驶证注销业务</a></li>
					<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/drv/insertSlgDrvXxcjb.action?ywlx=TJTJXX" class="aclass" target="mainFrame">驾驶人提交身体条件证明业务</a></li>
					<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/drv/insertSlgDrvXxcjb.action?ywlx=YQHZ" class="aclass" target="mainFrame">办理延期驾驶证期满换证业务</a></li>
					<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/drv/insertSlgDrvXxcjb.action?ywlx=YQTJ" class="aclass" target="mainFrame">办理延期提交《身体条件证明》业务</a></li>
					<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/drv/insertSlgDrvXxcjb.action?ywlx=HFJSZG" class="aclass" target="mainFrame">恢复驾驶资格业务</a></li>
					<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/drv/insertSlgDrvXxcjb.action?ywlx=JWSL" class="aclass" target="mainFrame">持境外驾驶证申领驾驶证业务</a></li>
					<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/drv/insertSlgDrvXxcjb.action?ywlx=XXMSHZ" class="aclass" target="mainFrame">香港驾驶证免试换领驾驶证业务</a></li> 
					<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/drv/cxtjSlgDrvXxcjb.action" class="aclass" target="mainFrame">驾驶证受理业务审核查询统计</a></li> 
				</ul>
				<h3><a href="#">考官排班</a></h3>
				<ul>
					<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/kgpb/pbxxb_getPbInfo.action" class="aclass" target="mainFrame">排班列表</a></li>
					<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/kgpb/kcxxb_getKcInfoList.action" class="aclass" target="mainFrame">考场列表</a></li>
					<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/kgpb/kgxxb_getKgInfoList.action" class="aclass" target="mainFrame">考官列表</a></li>
					<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/kgpb/tsrqb_getTsrqbList.action" class="aclass" target="mainFrame">特殊日期列表</a></li>
				</ul>
				<h3><a href="#">手机库业务</a></h3>
				<ul>
					<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/pages/sjk/dxList.jsp" class="aclass" target="mainFrame">短信发送</a></li>
				</ul>
				<h3><a href="#">综合业务查询->业务查询->机动车类业务查询</a></h3>
				<ul>
					<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/pages/guoshui/guoshuilist.jsp" class="aclass" target="mainFrame">国税联网数据查询</a></li>
					<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/mjcl/mjcl_mjclQuery.action" class="aclass" target="mainFrame">免检车辆工作量统计</a></li>
					<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/mjcl/mjcl_xgslQuery.action" class="aclass" target="mainFrame">综合应用平台业务受理情况统计</a></li>
					<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/pages/mjcl/zbInfo.jsp" class="aclass" target="mainFrame">指标信息查询</a></li>
					<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/mjcl/mjcl_yjgzQuery.action" class="aclass" target="mainFrame">车辆限购违规业务办理统计分析</a></li>
				</ul>
				<h3><a href="#">档案查询->车辆电子档案查询</a></h3>
				<ul>
					<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/pages/dzda/dzda.jsp" class="aclass" target="mainFrame">车辆电子档案查询</a></li>
				</ul>
				<h3><a href="#">机动车业务->PDA上门验车管理->机动车验车管理</a></h3>
				<ul>
					<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/yanche/chbase_initPdasmycChbaseList.action" class="aclass" target="mainFrame">车行机构账户管理</a></li>
					<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/yanche/chdlr_initPdasmycChdlrList.action" class="aclass" target="mainFrame">代理人信息审核</a></li>
					<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/yanche/vehpcb_initPdasmycVehPcbList.action" class="aclass" target="mainFrame">待验车清单分配</a></li>
					<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/yanche/vehpcb_initYcrwList.action" class="aclass" target="mainFrame">本部门验车任务管理</a></li>
					<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/yanche/vehpcb_initYcrwList4.action" class="aclass" target="mainFrame">民警验车任务管理</a></li>
					<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/pages/yanche/yccx.jsp" class="aclass" target="mainFrame">验车结果查询</a></li>
					<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/yanche/vehpcb_initVDataCheckList.action" class="aclass" target="mainFrame">验车结果修改</a></li>
					<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/yanche/vehpcb_initPdasmycVehPcbList2.action" class="aclass" target="mainFrame">预约验车部门调整</a></li>
					<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/yanche/vehpcb_initPdasmycVehPcbList3.action" class="aclass" target="mainFrame">验车未拍照超时审批(科)</a></li>
					<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/yanche/vehpcb_initPdasmycVehPcbList4.action" class="aclass" target="mainFrame">验车未拍照超时审批(处)</a></li>
				</ul>
				<h3><a href="#">机动车业务->PDA上门验车管理->机动车验车流水管理</a></h3>
				<ul>
					<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/yanche/lsgl_initYwlsglVehFlowList.action?type=1&gw=1001&ld=0" class="aclass" target="mainFrame">查验岗流水查询</a></li>
					<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/yanche/lsgl_initYwlsglVehFlowList.action?type=2&gw=1001&ld=0" class="aclass" target="mainFrame">查验岗流水管理</a></li>
					<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/yanche/lsgl_initYwlsglVehFlowList.action?type=2&gw=1001&ld=1" class="aclass" target="mainFrame">查验岗流水管理(领导岗)</a></li>
					<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/yanche/lsgl_initFlowInfo_thyj.action?type=2&gw=1001&ld=1" class="aclass" target="mainFrame">查验岗流水退回预警列表</a></li>
					<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/yanche/lsgl_initYwlsglVehFlowList.action?type=1&gw=2001&ld=0" class="aclass" target="mainFrame">受理岗流水查询</a></li>
					<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/yanche/lsgl_initYwlsglVehFlowList.action?type=2&gw=2001&ld=0" class="aclass" target="mainFrame">受理岗流水管理</a></li>
					<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/yanche/lsgl_initYwlsglVehFlowList.action?type=2&gw=2001&ld=1" class="aclass" target="mainFrame">受理岗流水管理(领导岗)</a></li>
					<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/yanche/lsgl_initYwlsglVehFlowListt.action?gw=2001" class="aclass" target="mainFrame">流水数据特殊字符修改</a></li>
				</ul>
				<h3><a href="#">机动车业务->报废车信息管理</a></h3>
				<ul>
					<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/bfc/bfc_initBfcJbxxbList.action" class="aclass" target="mainFrame">报废车信息管理(旧)</a></li>
					<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/bfc/bfcsp_getBfcTsspbList.action?jb=3" class="aclass" target="mainFrame">报废车审批申请管理</a></li>
					<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/bfc/bfcsp_getBfcTsspbList.action?jb=2" class="aclass" target="mainFrame">报废车科级审批管理</a></li>
					<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/bfc/bfcsp_getBfcTsspbList.action?jb=1" class="aclass" target="mainFrame">报废车处级审批管理</a></li>
					<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/bfc/bfjg_getBfcJgskbList.action" class="aclass" target="mainFrame">报废车信息查询(新)</a></li>
					<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/bfc/bfcsp_bfcSelList.action" class="aclass" target="mainFrame">报废车审批信息查询与统计</a></li>
					
				</ul>
				<h3><a href="#">机动车业务->机动车受理业务</a></h3>
				<ul>
					<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/veh/veh_initEditPage.action" class="aclass" target="mainFrame">机动车受理</a></li>
					<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/veh/veh_initSlCxList.action" class="aclass" target="mainFrame">机动车受理查询</a></li>
					<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/veh/vehSpxx_initSlgSpxxList.action" class="aclass" target="mainFrame">机动车受理业务审批</a></li>
					<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/pages/yujing/loading.jsp" class="aclass" target="mainFrame">机动车受理预警查询</a></li>
					<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/veh/vehSpxx_initVehPodbSpList.action" class="aclass" target="mainFrame">夫妻代办业务审批</a></li>
					<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/pages/veh/shenban.jsp" class="aclass" target="mainFrame">申办</a></li>
					<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/veh/veh_initJdcslPage.action?page=zchf" class="aclass" target="mainFrame">转出恢复业务</a></li>
					<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/pages/veh/jdccjlist.jsp" class="aclass" target="mainFrame">退办业务</a></li>
				</ul>
				<h3><a href="#">网上车管所业务管理->机动车预受理业务->纯电动车辆型号与电机型号管理</a></h3>
				<ul>
					<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/ddc/ddc_getvehTypeElectroList.action" class="aclass" target="mainFrame">纯电动车辆型号与电机型号管理</a></li>
				</ul>
				<h3><a href="#">机动车业务->其他业务->异地委托业务</a></h3>
				<ul>
				   <li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/ydwt/ydwt_businessManage.action" class="aclass" target="mainFrame">委托异地年检业务信息管理</a></li>
				   <li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/ydwt/ydwt_declareAndQuitStat.action" class="aclass" target="mainFrame">互联网业务申报、办结、退办数据统计</a></li>
				   <li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/ydwt/ydwt_ydwtPosQuitStat.action" class="aclass" target="mainFrame">材料移交邮政信息查询统计</a></li>
				   <li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/ydwt/ydwt_getUsersList.action" class="aclass" target="mainFrame">邮政、车管用户开户</a></li>
				   <li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/ydwt/ydwt_initWarnYdwt.action" class="aclass" target="mainFrame">未经申报办理业务预警</a></li>
				</ul>
				<h3><a href="#">机动车业务->其他业务->抵押登记业务</a></h3>
				<ul>
				   <li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/dydj/dydj_getYHUsersList.action" class="aclass" target="mainFrame">银行代办机构开户</a></li>
				   <li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/dydj/dydj_getUsersList.action" class="aclass" target="mainFrame">邮政、车管用户开户</a></li>
				   <li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/dydj/yhsl_initDydjList.action" class="aclass" target="mainFrame">互联网业务申报信息查询</a></li>
				   <li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/dydj/yhsl_initWarnDydj.action" class="aclass" target="mainFrame">未经申报办理业务预警</a></li>
				</ul>
				<h3><a href="#">业务管理->机动车业务管理->限制代办业务登记管理</a></h3>
				<ul>
				   <li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/blacklist/black_getBlackList.action" class="aclass" target="mainFrame">限制代办业务用户管理</a></li>
				   <li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/blacklist/black_getDictList.action" class="aclass" target="mainFrame">限制代办业务基础字典设置</a></li>
				</ul>
				<h3><a href="#">网上车管所业务管理->网上车管所制证中心->机动车联系方式变更</a></h3>
				<ul>
					<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/jdcbg/jdcbg_contactBgQuery.action?cs=2" class="aclass" target="mainFrame">机动车联系方式变更</a></li>
				</ul>
				<h3><a href="#">网上车管所业务管理->网上车管所制证中心->超龄驾驶人体检审核</a></h3>
				<ul>
					<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/cljstj/cljstj_cljstjShList.action?qc=2" class="aclass" target="mainFrame">超龄驾驶体检审核</a></li>
				</ul>
				<h3><a href="#">网上车管所业务管理->网上车管所制证中心->网上车管所业务查询统计</a></h3>
				<ul>
				   <li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/qrelate/qrelate_qrelate1.action" class="aclass" target="mainFrame">在线驾驶证补证换证</a></li>
				   <li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/qrelate/qrelate_qrelate2.action" class="aclass" target="mainFrame">驾驶人联系方式变更</a></li>
				   <li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/qrelate/qrelate_qrelate3.action" class="aclass" target="mainFrame">机动车联系方式变更</a></li>
				   <li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/qrelate/qrelate_qrelate4.action" class="aclass" target="mainFrame">补换机动车行驶证</a></li>
				   <li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/qrelate/qrelate_qrelate5.action" class="aclass" target="mainFrame">补换检验合格标志</a></li>
				   <li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/qrelate/qrelate_qrelate6.action" class="aclass" target="mainFrame">机动车业务外网预约</a></li>
				</ul>
				<h3><a href="#">业务管理->一窗式综合->嫌疑车管理</a></h3>
				<ul>
				   <li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/xyc/xyccode_initYcsXycCodeList.action" class="aclass" target="mainFrame">嫌疑车字典管理</a></li>
				   <li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/xyc/xycveh_initYcsXycVehList.action" class="aclass" target="mainFrame">嫌疑车管理</a></li>
				</ul>
				<h3><a href="#">机动车业务->公交车身广告喷涂变更登记备案->公交客运公司审批管理</a></h3>
				<ul>
				   <li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/gjgg/gjgg_clbaglInital.action?isFirstQuery=true" class="aclass" target="mainFrame">车辆备案管理</a></li>
				   <li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/gjgg/gjgg_ggjgInital.action?isFirstQuery=true" class="aclass" target="mainFrame">广告机构备案管理</a></li>
				   <li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/gjgg/gjgg_ggjgDbrInital.action" class="aclass" target="mainFrame">代办人员备案管理</a></li>
				</ul>
				<h3><a href="#">机动车业务->公交车身广告喷涂变更登记备案->广告喷涂变更登记审批管理</a></h3>
				<ul>
				   <li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/gjgg/ycgg_getYcxxInital.action?qx=cj" class="aclass" target="mainFrame">业务审核(处级)</a></li>
				   <li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/gjgg/ycgg_getYcxxInital.action?qx=kj" class="aclass" target="mainFrame">业务审核(科级)</a></li>
				   <li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/gjgg/ycgg_getycxxInital_tp.action" class="aclass" target="mainFrame">审核部门调配</a></li>
				</ul>
				<h3><a href="#">机动车业务->公交车身广告喷涂变更登记备案->广告证信息管理</a></h3>
				<ul>
				   <li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/gjgg/ggz_getBusGgjgclsb.action" class="aclass" target="mainFrame">广告证打印</a></li>
				   <li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/gjgg/ggz_getBusGgjgclsb_cd.action" class="aclass" target="mainFrame">广告证错证重打</a></li>
				   <li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/gjgg/ggz_getZxOrHfData.action?qx=cj" class="aclass" target="mainFrame">广告证注销/恢复(处级)</a></li>
				   <li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/gjgg/ggz_getZxOrHfData.action?qx=kj" class="aclass" target="mainFrame">广告证注销/恢复(科级)</a></li>
				</ul>
				<h3><a href="#">机动车业务->公交车身广告喷涂变更登记备案->系统管理</a></h3>
				<ul>
				   <li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/gjgg/xtgl_getBusBaseInital.action" class="aclass" target="mainFrame">公交客运公司账户管理 </a></li>
				   <li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/gjgg/xtgl_getSzzdInital.action" class="aclass" target="mainFrame">字典管理</a></li>
				</ul>
				<h3><a href="#">机动车业务->公交车身广告喷涂变更登记备案->统计查询</a></h3>
				<ul>
				   <li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/gjgg/tjcx_getLscx.action" class="aclass" target="mainFrame">流水查询</a></li>
				   <li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/gjgg/tjcx_getGgzdacx.action" class="aclass" target="mainFrame">广告证档案查询 </a></li>
				   <li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/gjgg/tjcx_getShqktj.action?qx=cj" class="aclass" target="mainFrame">审核情况统计(处)</a></li>
				   <li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/gjgg/tjcx_getShqktj.action?qx=kj" class="aclass" target="mainFrame">审核情况统计(科)</a></li>
				   <li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/gjgg/tjcx_getZjffqktj.action?qx=cj" class="aclass" target="mainFrame">证件发放情况统计(处)</a></li>
				   <li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/gjgg/tjcx_getZjffqktj.action?qx=kj" class="aclass" target="mainFrame">证件发放情况统计(科)</a></li>
				</ul>
				<h3><a href="#">业务管理->机动车业务管理->PDA验车IMEI码登记管理</a></h3>
				<ul>
				   <li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/pdaimei/imei_getTPdaYcimei.action" class="aclass" target="mainFrame">PDA验车IMEI码登记管理</a></li>
				</ul>
			   	<h3><a href="#">业务管理->机动车驾驶人电子影像档案采集</a></h3>
				<ul>
				   <li><img src="<%=request.getContextPath()%>/images/xgmm.jpg"  width="12" height="12" border="0" /><a href="<%=request.getContextPath()%>/jsrdzda/jsrdzda_initJsrCjList.action" class="aclass" target="mainFrame">初审</a></li>
				   <li><img src="<%=request.getContextPath()%>/images/xgmm.jpg"  width="12" height="12" border="0" /><a href="<%=request.getContextPath()%>/jsrdzda/jsrdzda_initCgCheckJsrList.action" class="aclass" target="mainFrame">复核</a></li>
				   <li><img src="<%=request.getContextPath()%>/images/xgmm.jpg"  width="12" height="12" border="0" /><a href="<%=request.getContextPath()%>/jsrdzda/jsrdzda_initQueryInfo.action?temp=" class="aclass" target="mainFrame">档案卷宗查询</a></li>
				</ul>
				<h3><a href="#">业务管理->电子身份认证信息库业务管理</a></h3>
				<ul>
					<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/sfrz/sfrz_initEditPage.action" class="aclass" target="mainFrame">身份认证信息采集</a></li>
				    <li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/sfrz/sfrz_initSfrzCjxxbList.action" class="aclass" target="mainFrame">身份认证信息管理</a></li>
					<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/sfrz/sfrz_getDbyList.action" class="aclass" target="mainFrame">代办员信息管理</a></li>
				</ul>
				<h3><a href="#">网上车管所业务管理->车管在手综合业务管理</a></h3>
				<ul>
					<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/ezxfw/ezxfw_initDrvChanList.action" class="aclass" target="mainFrame">驾驶证初审管理</a></li>
				    <li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/ezxfw/ezxfw_initJdcChanList.action" class="aclass" target="mainFrame">机动车初审管理</a></li>
				    <li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/ezxfw/ezxfw_initChanList.action?type=drv" class="aclass" target="mainFrame">驾驶证业务复核制证管理</a></li>
				    <li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/ezxfw/ezxfw_initChanList.action?type=jdc" class="aclass" target="mainFrame">机动车业务复核制证管理</a></li>
				    <li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/ezxfw/ezxfw_initDrvCheckList.action?type=drv" class="aclass" target="mainFrame">驾驶证初审监管</a></li>
				    <li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/ezxfw/ezxfw_initJdcCheckList.action?type=jdc" class="aclass" target="mainFrame">机动车初审监管</a></li>
				    <li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/ezxfw/ezxfw_initDrvGzlList.action?type=drv" class="aclass" target="mainFrame">驾驶证初审工作量统计</a></li>
				    <li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/ezxfw/ezxfw_initJdcGzlList.action?type=jdc" class="aclass" target="mainFrame">机动车初审工作量统计</a></li>
					<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/ezxfw/ezxfw_initDrvLscxList.action" class="aclass" target="mainFrame">驾驶证流水查询</a></li>
				    <li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/ezxfw/ezxfw_initJdcLscxList.action" class="aclass" target="mainFrame">机动车流水查询</a></li>

					<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/jdcjsrxpk/jdcjsrxpk_jdcxpkList.action" class="aclass" target="mainFrame">机动车相片库</a></li>
					<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/jdcjsrxpk/jdcjsrxpk_jsrxpkList.action" class="aclass" target="mainFrame">驾驶人相片库</a></li>

					<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/ezxfw/ezxfw_initezXxdPrintList.action" class="aclass" target="mainFrame">驾驶证信息单打印申请</a></li>
					<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/ezxfw/ezxfw_getVehSuperviseData.action" class="aclass" target="mainFrame">机动车违规预警分析</a></li>
					<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/ezxfw/" class="aclass" target="mainFrame">驾驶证违规预警分析</a></li>
				</ul>
				<h3><a href="#">档案管理->车管重点机动车驾驶人管理</a></h3>
				<ul>
					<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/dagl/jdcjsrgl_initZdjdcjsrList.action" class="aclass" target="mainFrame">车管重点机动车驾驶人管理</a></li>
			    </ul>
			    <h3><a href="#">业务管理->机动车业务管理->业务用户指纹信息管理</a></h3>
			    <ul>
			    	<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/jyzwcj/jyzwcj_bmjyList.action" class="aclass" target="mainFrame">用户指纹采集管理</a></li>
			    </ul>
			    <h3><a href="#">验车管理->验车字典管理</a></h3>
			    <ul>
			    	<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/pdaimei/imei_getTPdaYcimei.action" class="aclass" target="mainFrame">PDA验车IMEI码登记管理</a></li>
			    	<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/yanche/yczd_initYcgl.action" class="aclass" target="mainFrame">验车字典管理</a></li>
			    	<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/yanche/yczd_initYcgl.action?dmlb=40&fjsz=true" class="aclass" target="mainFrame">复检条件参数设置</a></li>
			    	<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/yanche/yczd_initPzzdgl.action?lyType=103&ly=cyxm" class="aclass" target="mainFrame">查验项管理</a></li>
			    	<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/yanche/yczd_initPzzdgl.action?lyType=33&ly=pzgg" class="aclass" target="mainFrame">拍照项管理</a></li>
			    	<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/yanche/yczd_initPzzdgl.action?lyType=4&ly=syxzOrcllx" class="aclass" target="mainFrame">车型关联查验拍照项</a></li>
			    	<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/yanche/yczd_initPzzdgl.action?lyType=3&ly=syxzOrcllx" class="aclass" target="mainFrame">使用性质关联查验拍照项</a></li>			    	
			    	<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/yanche/yczd_showYhlxxz.action" class="aclass" target="mainFrame">账号关联车辆类型及使用性质</a></li>
			    	<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/yanche/yczd_ycGpsgl.action" class="aclass" target="mainFrame">验车点位置管理</a></li>
			    	<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/yanche/yczd_showCjyjl.action" class="aclass" target="mainFrame">查验记录</a></li>
			    	<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/yanche/yczd_findQmUser.action" class="aclass" target="mainFrame">电子签名管理</a></li>
			    	<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/yanche/yczd_findUserDzqm.action" class="aclass" target="mainFrame">查看用户签名列表</a></li>
			    </ul>
			    
			    <h3><a href="#">综合业务查询->业务查询->驾驶证类业务查询</a></h3>
				<ul>
					<li><img src="<%=request.getContextPath()%>/images/xgmm.jpg" height="12" border="0" /><a href="<%=request.getContextPath()%>/jzzcx/jzzcx_jzzcxList.action" class="aclass" target="mainFrame">居住证查询</a></li>
				</ul>
			</div>
			</td>
		</tr>
	</table>
	<input name="showed" type="hidden" value="true" id="showed3" width="0">
	<input name="showtop" type="hidden" value="true" id="showtop" width="0">
  </body>
</html>
