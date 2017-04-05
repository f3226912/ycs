<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>车辆电子档案查询</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta content="Microsoft FrontPage 4.0" name="GENERATOR">
	<meta content="FrontPage.Editor.Document" name="ProgId">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/module.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/content.css" />
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
	<STYLE type="text/css">
		.imgdiv{
			width: 100%; height: 400px; border: 1px solid #3c7eba;
		}
		.imgdiv .divleft{width: 200px; height: 100%; border-right: 1px solid #3c7eba; float: left;}
		.imgdiv .divright{height: 100%;}
		ul{margin:0; padding:0;}
		ul li{
			list-style-type: none;
		    margin: 20px auto 20px 18px;
		    padding: 0;
		    text-align: center;
		    cursor: pointer;
		}
		.btn2{
			border:1px solid #9fafbc;
			height:22px;
			line-height:18px;
			background:#d5eaff;
			}
		a{
			text-decoration: none;
			color: 
		}
		a:hover{
			text-decoration: none;
		}
	</STYLE>
	
	<SCRIPT type="text/javascript">
		$(document).ready(function(){
			//var hpzl = '${request.hpzl}';
			//$(".divleft ul li").click(function(){
			//	$("#bigimg").attr("src", "<%=request.getContextPath()%>/dzda/dzda_showImage.action?img=2");
			//});
		});
		
		function showobj(){
			if($(".divleft ul li").length > 0){
				$("#Lead1").show();
				$("#btndiv").show();
			}else{
				$("#Lead1").hide();
			}
		}
	</SCRIPT>
  </head>
  
  <body onload="showobj();" style="background:#c7e5ff;">
  	<DIV id=admin_title>
		<DIV class=logo>
			<IMG src="<%=request.getContextPath()%>/images/frame_19.gif">
		</DIV>
		<DIV class=title>
			${editType }驾驶证业务受理采集信息&nbsp;<A
				href="<%=request.getContextPath()%>/drv/download2.action">&nbsp;<FONT
				color=red>点击下载控件驱动程序!</FONT>
			</A>
		</DIV>
	</DIV>
    <div class="content1" style="width: 100%;">
	  	<div class="roundedBox" id="type1" style="width: 99%; height: 99%">
			<div class="right" style="width: 95%; height: 95%;">
				<form name="dzdaform" id="dzdaform" action="<%=request.getContextPath()%>/dzda/dzda_initImageList.action" method="post">
					<table class="table1" width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<th class="th1" height="32" colspan="4">
								查询条件
							</th>
						</tr>
						<tr>
							<td class="tds" style="text-align: right;">
								号牌号码&nbsp;
							</td>
							<td class="tdl" style="text-align: left;">
								<input type="text" name="hphm" value="${request.hphm }" />
							</td>
							<td class="tds" style="text-align: right">
								号牌种类&nbsp;
							</td>
							<td class="tdl" style="text-align: left;">
								<s:select headerKey="" headerValue="---请选择---" list="#{'01':'01---大型汽车','02':
								'02---小型汽车','03':'03---使馆汽车','04':
								'04---领馆汽车','05':'05---境外汽车','06':
								'06---外籍汽车','07':'07---普通摩托车','08':
								'08---轻便摩托车','09':'09---使馆摩托车','10':
								'10---领馆摩托车','11':'11---境外摩托车','12':'12---外籍摩托车'
								,'13':'13---低速车','14':'14---拖拉机'
								,'15':'15---挂车','16':'16---教练汽车'
								,'17':'17---教练摩托车','18':'18---试验汽车'
								,'19':'19---试验摩托车','20':'20---临时入境汽车'
								,'21':'21---临时入境摩托车','22':'22---临时行驶车'
								,'23':'23---警用汽车','24':'24---警用摩托'
								,'25':'25---原农机号牌','26':'26---香港入出境车'
								,'27':'27---澳门入出境车'
								}" theme="simple" listKey="key" listValue="value"
								 name="hpzl" id="fpYcmjcodeid" value="#request.hpzl"></s:select>
							</td>
						</tr>
						<tr>
							<td class="tds" colspan="4">
								<div align="center">
									<input class="bnt" type="submit" value="查  询" style="cursor:pointer;" />
								</div>
							</td>
						</tr>
					</table>
					<div id="imgdiv" class="imgdiv" style="height: 520px;">
						<div class="divleft" style="overflow: auto; height: 520px; text-align: center;">
							<ul>
								<s:iterator value="#request.imgList" var="img" status="st">
									<li>
										<!-- <img src="<%=request.getContextPath()%>/dzda/dzda_showImage.action?img=1" onclick="javascript:Lead1.BitmapDataPath='<%=request.getContextPath()%>/dzda/dzda_showImage.action?img=2'; Lead1.PaintSizeMode = 3" title="${img[1] }"/> -->
										<a href="javascript: void(0);" onclick="javascript:Lead1.BitmapDataPath='<%=request.getContextPath()%>/dzda/dzda_showImage.action?img=${img[0]}'; Lead1.PaintSizeMode = 3">第 ${st.count } 页</a>
									</li>
								</s:iterator>
							</ul>
						</div>
						<div class="divright" style="text-align: center;  height: 500px; padding-top: 20px;" >
							<div style="text-align: center; width: 700px; height:450px; overflow: auto;" >
								<object classid="clsid:5220cb21-c88d-11cf-b347-00aa00a28331" align="middle" border="0" width="1" height="1" id="Microsoft_Licensed_Class_Manager_1_0">
									<param name="LPKPath" value="<%=request.getContextPath()%>/pages/dzda/sun.lpk">
								</object>
							
								<object id="Lead1" name="Lead1" classid="CLSID:00120000-B1BA-11CE-ABC6-F5B2E79D9E3F"  codebase="LTOCX12N.cab" align="middle" border="0" width="650" height="450" style="display: none; text-align: center;">
									<param name="_Version" value="65539">
										<param name="_ExtentX" value="10583">
										<param name="_ExtentY" value="7408">
										<param name="_StockProps" value="229">
										<param name="BackColor" value="11450426">
										<param name="BorderStyle" value="0">
										<param name="Enabled" value="-1">
										<param name="Appearance" value="1">
										<param name="AutoRepaint" value="-1">
										<param name="AutoScroll" value="-1">
										<param name="AutoSetRects" value="-1">
										<param name="BackErase" value="0">
										<param name="BitonalScaling" value="0">
										<param name="EnableMethodErrors" value="-1">
										<param name="EnableProgressEvent" value="0">
										<param name="EnableTwainEvent" value="0">
										<param name="ForePalette" value="0">
										<param name="MousePointer" value="0">
										<param name="PaintDither" value="0">
										<param name="PaintEffect" value="1000">
										<param name="PaintPalette" value="0">
										<param name="PaintWhileLoad" value="0">
										<param name="ScaleHeight" value="276">
										<param name="ScaleLeft" value="0">
										<param name="ScaleMode" value="3">
										<param name="ScaleTop" value="0">
										<param name="ScaleWidth" value="396">
										<param name="DataField" value>
										<param name="DataLoadBits" value="24">
										<param name="DataSaveBits" value="24">
										<param name="DataSaveFormat" value="5">
										<param name="DataSaveQuality" value="-1">
										<param name="BitmapDataPath" value>
										<param name="AnnDataPath" value>
										<param name="PaintSizeMode" value="0">
										<param name="PaintZoomFactor" value="100">
										<param name="AutoAnimate" value="0">
										<param name="AutoAnimationLoop" value="0">
										<param name="AutoSize" value="0">
										<param name="OLEDropAllowed" value="-1">
										<param name="PaintSizeUseDPI" value="0">
										<param name="DrawPersistence" value="0">
										<param name="DrawMode" value="13">
										<param name="DrawPenStyle" value="0">
										<param name="DrawFillStyle" value="1">
										<param name="DrawPenWidth" value="1">
										<param name="DrawPenColor" value="0">
										<param name="DrawFillColor" value="0">
										<param name="DrawFontColor" value="0">
										<param name="PaintContrast" value="0">
										<param name="PaintGamma" value="100">
										<param name="PaintIntensity" value="0">
										<param name="LoadUseViewPerspective" value="0">
										<param name="AutoAnnRotate" value="-1">
										<param name="TransitionStyle" value="0">
										<param name="TransitionEffect" value="1000">
										<param name="GradientStyle" value="0">
										<param name="GradientStartColor" value="16711680">
										<param name="GradientEndColor" value="0">
										<param name="GradientSteps" value="16">
										<param name="PatternStyle" value="0">
										<param name="PatternForeColor" value="0">
										<param name="PatternBackColor" value="0">
										<param name="PaintPass" value="1">
										<param name="PaintMaxPasses" value="1">
										<param name="EffectGrain" value="5">
										<param name="EffectDelay" value="0">
										<param name="WandSize" value="0">
										<param name="WandColor" value="0">
										<param name="PaintRgnOnly" value="0">
										<param name="ShapeLeft" value="0">
										<param name="ShapeTop" value="0">
										<param name="ShapeWidth" value="0">
										<param name="ShapeHeight" value="0">
										<param name="ShapeBorderStyle" value="0">
										<param name="ShapeBorderThickness" value="0">
										<param name="ShapeBorder" value="0">
										<param name="ShapeInnerBandStyle" value="0">
										<param name="ShapeInnerBandThickness" value="0">
										<param name="ShapeInnerBandHiliteColor" value="16777215">
										<param name="ShapeInnerBandShadowColor" value="8421504">
										<param name="ShapeOuterBandStyle" value="0">
										<param name="ShapeOuterBandThickness" value="0">
										<param name="ShapeOuterBandHiliteColor" value="16777215">
										<param name="ShapeOuterBandShadowColor" value="8421504">
										<param name="ShapeBackgroundStyle" value="0">
										<param name="BackgroundImageLeft" value="0">
										<param name="BackgroundImageTop" value="0">
										<param name="BackgroundImageWidth" value="0">
										<param name="BackgroundImageHeight" value="0">
										<param name="ShadowXDepth" value="0">
										<param name="ShadowYDepth" value="0">
										<param name="ShadowColor" value="0">
										<param name="TextHiliteColor" value="16777215">
										<param name="TextLeft" value="0">
										<param name="TextTop" value="0">
										<param name="TextWidth" value="0">
										<param name="TextHeight" value="0">
										<param name="TextAngle" value="0">
										<param name="TextStyle" value="0">
										<param name="TextAlign" value="0">
										<param name="TextWordWrap" value="0">
										<param name="FrameColor" value="0">
										<param name="FrameThickness" value="0">
										<param name="FrameInnerStyle" value="0">
										<param name="FrameInnerThickness" value="0">
										<param name="FrameInnerHiliteColor" value="16777215">
										<param name="FrameInnerShadowColor" value="8421504">
										<param name="FrameOuterStyle" value="0">
										<param name="FrameOuterThickness" value="0">
										<param name="FrameOuterHiliteColor" value="16777215">
										<param name="FrameOuterShadowColor" value="8421504">
										<param name="PaintNotificationDelay" value="0">
										<param name="Transparent" value="0">
										<param name="AnnShowLockedIcon" value="-1">
										<param name="AnnHyperlinkMenuEnable" value="0">
										<param name="EnableZoomInRect" value="-1">
										<param name="PanWinX" value="0">
										<param name="PanWinY" value="0">
										<param name="PanWinWidth" value="150">
										<param name="PanWinHeight" value="150">
										<param name="PanWinRectColor" value="255">
										<param name="PanWinTitle" value="PanWindow">
										<param name="PanWinSysMenu" value="-1">
										<param name="PanWinPaintDither" value="0">
										<param name="PanWinPaintPalette" value="0">
										<param name="PanWinBitonalScaling" value="0">
										<param name="PanWinPointer" value="0">
										<param name="CLeadCtrl" value="0">
										<param name="SavePage" value="1">
										<param name="OLEDropAutoLoad" value="-1">
										<param name="WMFResolution" value="0">
										<param name="RefBitmap" value="0">
										<param name="WindowLevelBitmap" value="-1">
										<param name="SaveResolutionCount" value="0">
										<param name="AutoPan" value="0">
										<param name="LoadCompressed" value="-1">
										<param name="PaintScaling" value="0">
										<param name="PanWinPaintScaling" value="0">
										<param name="EnablePaint" value="-1">
										<param name="RefBitmapList" value="0">
										<param name="RgnFrameColor" value="255">
										<p> If you see this text then your browser is not handling the ActiveX properly. 
										Try downloading Microsoft Explorer. </p>
										</object>
							</div>
							<div id="btndiv" style="padding: 10px; height:50px; display: none;">
								<input type="button" name="Command2" class="btn2" value="缩 小"
									language="JavaScript"
									onclick="Lead1.width=Lead1.width/1.40
									 Lead1.height=Lead1.height/1.40
									 Lead1.PaintSizeMode = 3" class="txt12pt">
								&nbsp;&nbsp;
								<input type="button" class="btn2" name="Command3" value="正 常"
									language="JavaScript" onClick="Lead1.width=650
									 Lead1.height=450
									 Lead1.PaintSizeMode = 3" class="txt12pt">
								&nbsp;&nbsp;
								<input type="button" class="btn2" name="Command1" value="放 大"  language="JavaScript"  
								onclick=" Lead1.width=Lead1.width*1.40
								 Lead1.height=Lead1.height*1.40
								 Lead1.PaintSizeMode = 3" class="txt12pt">
								&nbsp;&nbsp;
								
							</div>
						</div>
					</div>
				</form>
			</div>
			<div class="corner topLeft"></div>
			<div class="corner topRight"></div>
		</div>
	</div>
  </body>
</html>
