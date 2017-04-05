<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/content.css" />
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
	<style type="text/css">
		html {
			overflow: scroll;
			scrollbar-base-color: #c7e5ff;
			scrollbar-track-color: #FFFFFF;
		}
		* {
			margin: 0;
			padding: 0;
		}
		li {
			list-style: none;
			float: left; width: 200px;
		}
		.div {
			width: 100%;
			height: 600px;
		}
		.tablist {
			width: 100%;
			height: 100%;
			font-size: 12px;
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
			margin: auto;
		}
		
		td {
			background: #FFFFFF; font-size: 12px; border: 1px solid red; height: 20px;
		}
		.datalist th{
			background:url(<%=request.getContextPath()%>/images/cxtjbj.gif) repeat-x);
			text-align: center;
		}
		#cjh{
			width: 100%; 
			height:120px; 
			border: 0px solid red; 
			overflow: auto;
			margin-top: -60px;
			position: absolute;
		}
		#zhang{ width:300px;; height: auto; border:1px solid red;
				line-height: 50px;
				margin-bottom:10px;
				
			}
		#ziti{	position: absolute; 
			  	width: 55px; 
			  	height: 30px; 
			  	border:0px solid red;
			  	margin: 5px 48px;
			  	line-height: 50px;
			  	font-family: "华文行楷";
			  	font-size:18px;
			 }
		ul li{ width: 95%; height: auto; border: 0px solid red; }
		#qm{ width: 340px; height: auto; border: 0px solid red;}
		#qm li{ float: left; width: auto;; height: auto; border: 0px solid red;}
		
	</style>
	<script type="text/javascript">
		function cybPrint(){
			/*var t = document.getElementById("cjh").scrollTop;
			alert(t);*/
			//$('#cjhImg').css('overflow','hidden;');
			//$('#cjhImg').css('margin-top:','-108px;');
			$("#hiBtn").hide();
			window.print();
			window.close();
			return false;
		}
			
		function adapt(){	
			var img = new Image();
			img.src = $('#ycImg').attr("src");
			var imgWidth = img.width;
			var imgHeight = img.height;
			//alert("图片原始宽："+imgWidth+",图片原始高"+imgHeight);
			var sfbl = imgHeight/200;
			var sf_width1 = imgWidth/sfbl;
			var sf_height1 = imgHeight/sfbl;
			document.getElementById("ycImg").width=sf_width1;
			document.getElementById("ycImg").height=sf_height1;
		}
		
		function cjhonload(){
			var img = new Image();
			img.src = $('#cjhImg').attr("src");
			var imgWidth = img.width;
			var imgHeight = img.height;
			alert("图片原始宽："+imgWidth+",图片原始高"+imgHeight);
			/*var sfbl = imgHeight/110;
			var sf_width1 = imgWidth/sfbl;
			var sf_height1 = imgHeight/sfbl;
			document.getElementById("cjhImg").width=sf_width1;
			document.getElementById("cjhImg").height=sf_height1;*/
		}
	
	</script>
  </head> 
  <body>
	<div id=admin_main style="WIDTH:98%; margin:0 auto; color: #000;">
		<div id="hiBtn" align="center" style="width: 100%">
			<input type="button" value="打印" class="bnt" onclick="cybPrint()"/>&nbsp;&nbsp;
			<input type="button" value="取消" class="bnt" onclick="window.close()" />
		</div>
		<div class="div">
			<div style="font-size: 12px;">
				<p style="width: 100%; text-align: center;"><strong>机动车查验记录表</strong></p>
				<p style="width: 100%; float: left">
					号牌号码<span style="font-size: 12px;">（流水号或其他与车辆能对应的号码）</span>:${request.cybdObj[5]}&nbsp&nbsp&nbsp&nbsp&nbsp
					号牌种类:${request.hpzl}&nbsp&nbsp&nbsp&nbsp&nbsp
					使用性质:${request.syxz}
				</p>
				<table class="edittable" border="0" cellpadding="0" cellspacing="0" align="center">
						<tr>
							<td colspan="8" style="text-align: left; font-size: 12px;">
								<strong>业务类型</strong>&nbsp&nbsp
								<input type="checkbox" <s:if test='#request.ywlxOryy=="AA"'>checked="checked"</s:if> value="AA">注册登记 &nbsp;&nbsp;
								<input type="checkbox" <s:if test='#request.ywlxOryy==""'>checked="checked"</s:if> value="">转入&nbsp;&nbsp;
								<input type="checkbox" <s:if test='#request.ywlxOryy=="B"'>checked="checked"</s:if> value="">转移登记 &nbsp;&nbsp;
								<input type="checkbox" <s:if test='#request.ywlxOryy=="DP"'>checked="checked"</s:if> value="DP">变更迁出 &nbsp;&nbsp;
								<input type="checkbox" <s:if test='#request.ywlxOryy=="DD"'>checked="checked"</s:if> value="DD">变更车身颜色 &nbsp;&nbsp;
								<input type="checkbox" <s:if test='#request.ywlxOryy=="DG"'>checked="checked"</s:if> value="DG">更换车身或则车架 &nbsp;&nbsp;&nbsp;&nbsp;
								<input type="checkbox" <s:if test='#request.ywlxOryy=="DH"'>checked="checked"</s:if> value="DH">变更整车 &nbsp;&nbsp;
								<input type="checkbox" <s:if test='#request.ywlxOryy=="DF"'>checked="checked"</s:if> value="DF">更换发动机 &nbsp;&nbsp;
								<input type="checkbox" <s:if test='#request.ywlxOryy=="DI"'>checked="checked"</s:if> value="DI">变更使用性质 &nbsp;&nbsp;
								<input type="checkbox" <s:if test='#request.ywlxOryy==""'>checked="checked"</s:if> value="">重新打印VIN &nbsp;&nbsp;
								<input type="checkbox" <s:if test='#request.ywlxOryy=="DL"'>checked="checked"</s:if> value="DL">重新打刻发动机号 &nbsp;&nbsp;
								<input type="checkbox" <s:if test='#request.ywlxOryy==""'>checked="checked"</s:if> value="">加装/拆除操纵辅助装置 &nbsp;&nbsp;
								<input type="checkbox" <s:if test='#request.ywlxOryy==""'>checked="checked"</s:if> value="">申领登记证书 &nbsp;&nbsp;
								<input type="checkbox" <s:if test='#request.ywlxOryy==""'>checked="checked"</s:if> value="">补领登记证书 &nbsp;&nbsp;
								<input type="checkbox" <s:if test='#request.ywlxOryy==""'>checked="checked"</s:if> value="">监督解体 &nbsp;&nbsp;
								<input type="checkbox" <s:if test='#request.ywlxOryy=="QT"'>checked="checked"</s:if> value="QT">其它 &nbsp;&nbsp;
							</td>
						</tr>
						<tr>
							<td style="text-align: center;" width="60px;">类别</td>
							<td style="text-align: center;" width="40px;">序号</td>
							<td style="text-align: center;">查验项目</td>
							<td style="text-align: center;">判定</td>
							<td style="text-align: center;" width="60px;">类别</td>
							<td style="text-align: center;" width="40px;">序号</td>
							<td style="text-align: center;">查验项目</td>
							<td style="text-align: center;">判定</td>
						</tr>
						<tr>
							<td style="text-align: center;" rowspan="9" >通用项目</td>
							<td style="text-align: center;">1</td>
							<td width="140px;">车辆识别代号</td>
							<td style="text-align: center;" width="80px;"><s:property value="#request.cyxMap.get(1)" /></td>
							<td rowspan="4" style="text-align: center;">大中型客<br>车、危险<br>化学品运<br>输车等</td>
							<td style="text-align: center;" width="50px;">15</td>
							<td width="160px;">灭火器</td>
							<td style="text-align: center;" width="80px;"><s:property value="#request.cyxMap.get(15)" /></td>
						</tr>
						<tr>

							<td style="text-align: center;" >2</td>
							<td width="140px;">发动机型号/号码</td>
							<td style="text-align: center;" width="80px;"><s:property value="#request.cyxMap.get(2)" /></td>
							
							<td style="text-align: center;" >16</td>
							<td width="160px;">行驶记录装置、车<br>内外录像监控装置</td>
							<td style="text-align: center;" width="80px;"><s:property value="#request.cyxMap.get(16)" /></td>
						</tr>
						<tr>

							<td style="text-align: center;" >3</td>
							<td width="140px;">车辆品牌/型号</td>
							<td style="text-align: center;" width="80px;"><s:property value="#request.cyxMap.get(3)" /></td>
							
							<td style="text-align: center;" >17</td>
							<td width="160px;">应急出口/应急<br>锤、乘客门</td>
							<td style="text-align: center;" width="80px;"><s:property value="#request.cyxMap.get(17)" /></td>
						</tr>
						<tr>

							<td style="text-align: center;" >4</td>
							<td width="140px;">车身颜色</td>
							<td style="text-align: center;" width="80px;">
								<s:if test='#request.ywlxOryy=="AA"'><s:property value="#request.csys" /></s:if>
								<s:else><s:property value="#request.cyxMap.get(4)" /></s:else>
							</td>
							
							<td style="text-align: center;" >18</td>
							<td width="160px;">外部标识/文字、<br>喷涂</td>
							<td style="text-align: center;" width="80px;"><s:property value="#request.cyxMap.get(18)" /></td>
						</tr>
						<tr>

							<td style="text-align: center;" >5</td>
							<td width="140px;">核定载人数</td>
							<td style="text-align: center;" width="80px;">
								<s:if test='#request.ywlxOryy=="AA"'><s:property value="#request.hdzrs" /></s:if>
								<s:else><s:property value="#request.cyxMap.get(5)" /></s:else>
							</td>
							<td style="text-align: center;" rowspan="2">其它</td>
							<td style="text-align: center;" >19</td>
							<td width="160px;">标志灯具、警报器</td>
							<td style="text-align: center;" width="80px;"><s:property value="#request.cyxMap.get(19)" /></td>
						</tr>
						<tr>
							<td style="text-align: center;" >6</td>
							<td width="140px;">车辆类型</td>
							<td style="text-align: center;" width="80px;">
								<s:if test='#request.ywlxOryy=="AA"'><s:property value="#request.cllx" /></s:if>
								<s:else><s:property value="#request.cyxMap.get(6)" /></s:else>
							</td>
							<td style="text-align: center;" >20</td>
							<td width="160px;">检验合格证明</td>
							<td style="text-align: center;" width="80px;"><s:property value="#request.cyxMap.get(20)" /></td>
							<td></td>
						</tr>
						<tr>

							<td style="text-align: center;" >7</td>
							<td width="140px;">号牌/车辆外观形状</td>
							<td style="text-align: center;" width="80px;"><s:property value="#request.cyxMap.get(7)" /></td>
							<td colspan="4" rowspan="3">查验结论：${request.cybdObj[5]=="0"?"不合格":"合格"}</td>
							
						</tr>
						<tr>

							<td style="text-align: center;" >8</td>
							<td width="140px;">轮胎完好情况</td>
							<td style="text-align: center;" width="80px;"><s:property value="#request.cyxMap.get(8)" /></td>
							
						</tr>
						<tr>

							<td style="text-align: center;" >9</td>
							<td width="140px;">安全带、三角警告牌</td>
							<td style="text-align: center;" width="80px;"><s:property value="#request.cyxMap.get(9)" /></td>
							
						</tr>
						<tr>
							<td style="text-align: center;" rowspan="5">货车挂车</td>
							<td style="text-align: center;" >10</td>
							<td width="140px;">外廓尺寸、轴数、轴距</td>
							<td style="text-align: center;" width="80px;"><s:property value="#request.cyxMap.get(10)" /></td>
							<td colspan="4" rowspan="3">
								<ul>
									<li>
										<ul id="qm">
											<li>查验员 :</li>
											<li><img src="<%=request.getContextPath()%>/images/zhang/zhang.jpg" width="140" height="81"></li>
											<li>
											<s:if test="#request.dzqm != null">
												<img src=${request.dzqm} width="120" height="60"/>
											</s:if>
											</li>
										</ul>
									</li>
									<li>
										<s:if test='#request.cycs==1'>
											<p style="float: right;">${ycDate[0]}年${ycDate[1]}月${ycDate[2]}日</p>
										</s:if>
										<s:else>
											<p style="float: right;">&nbsp&nbsp&nbsp&nbsp年&nbsp&nbsp&nbsp&nbsp月&nbsp&nbsp&nbsp&nbsp日</p>
										</s:else>
									</li>
								</ul>
							</td>
						</tr>
						<tr>

							<td style="text-align: center;" >11</td>
							<td width="140px;">整备质量</td>
							<td style="text-align: center;" width="80px;"><s:property value="#request.cyxMap.get(11)" /></td>
							
						</tr>
						<tr>

							<td style="text-align: center;" >12</td>
							<td width="140px;">轮胎规格</td>
							<td style="text-align: center;" width="80px;"><s:property value="#request.cyxMap.get(12)" /></td>
							
						</tr>
						<tr>

							<td style="text-align: center;" >13</td>
							<td width="140px;">侧后部防护装置</td>
							<td style="text-align: center;" width="80px;"><s:property value="#request.cyxMap.get(13)" /></td>
							<td rowspan="2" style="text-align: center;">复检合格</td>
							<td  colspan="3" rowspan="2">检验员：<s:if test='#request.cycs>1'>
																	<!-- 如果复检查验仍不合格不用签署 -->
																	<s:if test='#request.cybdObj[5]=="1"'>
																	${request.cyName}
																	</s:if>
																</s:if><br/><br/>
								<s:if test='#request.cycs>1'>
									<s:if test='#request.cybdObj[5]=="1"'>
									<p style="float: right;">${ycDate[0]}年${ycDate[1]}月${ycDate[2]}日</p>
									</s:if>
									<s:else>
									<p style="float: right;">&nbsp&nbsp&nbsp&nbsp年&nbsp&nbsp&nbsp&nbsp月&nbsp&nbsp&nbsp&nbsp日</p>
									</s:else>
								</s:if>
								<s:else>
									<p style="float: right;">&nbsp&nbsp&nbsp&nbsp年&nbsp&nbsp&nbsp&nbsp月&nbsp&nbsp&nbsp&nbsp日</p>
								</s:else>
							</td>
							
						</tr>
						<tr>
							<td style="text-align: center;" >14</td>
							<td width="140px;">车身反光标识和车辆尾<br>部标志板、喷涂</td>
							<td style="text-align: center;" width="80px;"><s:property value="#request.cyxMap.get(14)" /></td>
						</tr>
						<tr>
							<td colspan="5" style="text-align: center; padding-top: 2px;" height="200px;">
							<img src="http://100.100.21.61/cmp_new/view_pic.asp?efid=${request.zpid}" id="ycImg" onload="adapt()" border="0">
							</td>
							<td colspan="3">备&nbsp&nbsp注:<br/>
								<div style="height: 170px; width: 100%;"></div>
								<div style="text-align: right; width: 100%;">&nbsp&nbsp&nbsp&nbsp年&nbsp&nbsp&nbsp&nbsp月&nbsp&nbsp&nbsp&nbsp日</div>
							</td>
						</tr>
						<tr>
							<td colspan="8" style="text-align: center; padding-top: 2px;" valign="middle" height="110px;">
								<s:if test="#request.cjh_zpid == null">
								<div style="height: 110px; line-height:110px; " >暂无图片</div>
								</s:if>
								<s:else>
								<!--<div id="cjh">-->
									<img src="http://100.100.21.61/cmp_new/view_pic.asp?efid=${request.cjh_zpid}"  id="cjhImg" width="400" height="110" border="0">
								<!--</div>-->
								</s:else>
							</td>
						</tr>
					</table>
					<div style="font-size: 12px; margin-top: 100px;">
					说明：1、填表时在对应的业务类型名称上划“√”；2、对按照规定不须查验的项目，在对应的判定栏内划“—”；3、本表所列查验项目判定合格时在对应栏划“√”，
					判定不合格时在对应栏划“×”，本表以外的查验项目不合格时，在备注栏内注明情况，查验结论签注为“不合格”；所有查验项目合格，查验结论签注为“合格”；
					4、复检合格时，查验员签字并签注日期；复检仍不合格的，不签注；5、注册登记查验时，“车身颜色”、“核定载人数”、“车辆类型”判定栏内签注查验确定的
					相应内容；变更车身颜色查验时签注车身颜色。
					</div>
			</div>
		</div>
	</div>
  </body>
</html>
