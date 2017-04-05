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
    <title>国税联网数据查询</title>
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
		}
		.div {
			width: 100%;
			height: 600px;
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
			margin: 0 0;
		}
		
		td {
			background: #FFFFFF;
		}
		.datalist th{
			background:url(<%=request.getContextPath()%>/images/cxtjbj.gif) repeat-x);
			text-align: center;
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
		.bnt {
			width: 76px;
			height: 27px;
			background: url(<%=request.getContextPath()%>/images/an2.gif) no-repeat;
			border: none;
			font-weight: bold;
		}
	</style>
	<script type="text/javascript">
		var chuli;
		$(document).ready(function(){
			var tabnum = '${request.tabnum}';
			if(tabnum != null && tabnum != ""){
				settab(this,tabnum);
			}
			var fplx = '${request.fplx}';
			if(fplx != null && fplx != ""){
				$("input[name='fplx']").each(function(){
					if($(this).val() == fplx){
						$(this).attr("checked", "checked");
					}
				});
			}
		});
		function changgui(){
			settab(this,0)
			var cgfphm = $("#cgfphm").val();
			var cgfpdm = $("#cfpdm").val();
			var mfczxm = $("#mfczxm").val();
			var mfsfzmhm = $("#mfsfzmhm").val();
			var cjh = $("#cjh").val();
			var fdjh = $("#fdjhm").val();
			if((cgfphm == null || cgfphm == "") && (cgfpdm == null || cgfpdm == "")
				&& (mfczxm == null || mfczxm == "") && (mfsfzmhm == null || mfsfzmhm == "")
				&& (cjh == null || cjh == "") && (fdjh == null || fdjh == "")){
				alert("请至少输入一个查询条件");
				return false;
			}
			$.ajax({
				cache:false,
    			async:false,
    			url: '<%=request.getContextPath()%>/guoshuiajax/guoshui_initGuoshuiCount.action',
    			type: 'post',
    			data: {"cgfphm": cgfphm, "cgfpdm":cgfpdm, "mfczxm": mfczxm, "mfsfzmhm":mfsfzmhm, "cjh":cjh, "fdjhm":fdjh },
    			dataType: 'json',
    			error: function(){
    				alert("读取数据异常");
	    			return false;
	    		},
    			success: function(result){
					if(result == 'false'){
						alert("根据该条件查询结果太多，请输入更多条件再次查询!");
						return false;
					}else{
						$("#guoshuiform").attr("action", "<%=request.getContextPath()%>/guoshui/guoshui_initGuoshuiList.action?cxlx=changgui").submit();
					}
	    		}
			});
			
		}
		
		function wanshui(){
			settab(this,1)
			var wszmbh = checknotnull2(document.getElementById("wszmbh"),'请输入完税证明编号!',0);
			if (wszmbh != "true") {
				return false;
			}
			$("#guoshuiform").attr("action", "<%=request.getContextPath()%>/guoshui/guoshui_initGuoshuiList.action?cxlx=wanshui").submit();
		}
		
		function shishi(){
			var ssfpdm = checknotnull2(document.getElementById("ssfpdm"),'请输入国税接口查询发票代码!',0);
			if (ssfpdm != "true") {
				return false;
			}
			var ssfphm = checknotnull2(document.getElementById("ssfphm"),'请输入国税接口查询发票号码!',0);
			if (ssfphm != "true") {
				return false;
			}
			var type = $("input[name='fplx'][checked]").val();
			if(type=='XCFPStr.CGS.SJYY'){  //新车
				settab(this,0);
			}else if(type=='CgsFpStr.CGS.SJYY'){ //新车购置税
				settab(this,1);
			}else if(type == 'ESCFPStr.CGS.SJYY'){  //二手车
				settab(this,2);
			}
			var imgurl = '<%=request.getContextPath()%>/artDialog4.1.6/skins/icons/loading2.gif';
			chuli = art.dialog({
			    content: '<div style=\"text-align:center; text-indent:-999em; overflow:hidden; background:url('+imgurl+') no-repeat center center;\"><span>loading..</span></div><br/>数据查询中,请稍候.....',
			    title: '数据查询中',
    			lock: true,
			    opacity: 0.87
			});
			$("#guoshuiform").attr("action", "<%=request.getContextPath()%>/guoshui/guoshui_initGuoshuiList.action?cxlx=fplx").submit();
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
		//验证非空
		function checknotnull2(obj, alerttype,type) {
			if (obj.value != "" && obj.value != " " && obj.value != null
					&& obj.value != undefined) {
				obj.style.borderColor = '';
				return "true";
			} else {
				alert(alerttype);
				obj.style.borderColor = '#FF0000';
				settab(this,type);
				obj.focus();
				return "false";
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
				<form action="" name="guoshuiform" id="guoshuiform" method="post">
					<table class="edittable" border="0" cellpadding="0" cellspacing="0" align="center">
						<tr>
							<td colspan="6" style="text-align: center; font-size: 14px;"><strong>国税联网数据查询</strong></td>
						</tr>
						<tr>
							<td rowspan="4" valign="middle" style="text-align: center;">常规查询</td>
							<td style="text-align: right;">发票代码：</td>
							<td>&nbsp;<input type="text" name="cgfpdm" id="cfpdm" onkeyup="clearspace(this)" onblur="clearallspace(this)" value="${request.cgfpdm}"/></td>
							<td style="text-align: right;">发票号码：</td>
							<td>&nbsp;<input type="text" name="cgfphm" id="cgfphm" onkeyup="clearspace(this)" onblur="clearallspace(this)" value="${request.cgfphm}"/></td>
							<td rowspan="3" valign="middle" style="text-align: center;">
								 <input type="button" value="查询" class="bnt" style="cursor: pointer;" onclick="changgui();"/>
							</td>
						</tr>
						<tr>
							<td style="text-align: right;">买方车主姓名：</td>
							<td>&nbsp;<input type="text" name="mfczxm" id="mfczxm" onkeyup="clearspace(this)" onblur="clearallspace(this)" value="${request.mfczxm}"/></td>
							<td style="text-align: right;">买方身份证明号码：</td>
							<td>&nbsp;<input type="text" name="mfsfzmhm" id="mfsfzmhm" onkeyup="clearspace(this)" onblur="clearallspace(this)" value="${request.mfsfzmhm}"/></td>
						</tr>
						<tr>
							<td style="text-align: right;">车架号：</td>
							<td>&nbsp;<input type="text" name="cjh" id="cjh" onkeyup="clearspace(this)" onblur="clearallspace(this)" value="${request.cjh}"/></td>
							<td style="text-align: right;">发动机号：</td>
							<td>&nbsp;<input type="text" name="fdjhm" id="fdjhm" onkeyup="clearspace(this)" onblur="clearallspace(this)" value="${request.fdjhm}"/></td>
						</tr>
						<tr>
							<td style="text-align: right;">完税证明编号：</td>
							<td colspan="3">&nbsp;<input type="text" name="wszmbh" id="wszmbh" onkeyup="clearspace(this)" onblur="clearallspace(this)" value="${request.wszmbh}"/></td>
							<td valign="middle" style="text-align: center;">
								 <input type="button" value="查询" class="bnt" style="cursor: pointer;" onclick="wanshui();"/>
							</td>
						</tr>
						<tr>
							<td rowspan="3" style="text-align: center;" valign="middle">实时查询国税接口</td>
							<td colspan="4">
								<input type="radio" name="fplx" value="XCFPStr.CGS.SJYY" checked="checked"/>新车发票
								<input type="radio" name="fplx" value="CgsFpStr.CGS.SJYY"/>新车购置税
								<input type="radio" name="fplx" value="ESCFPStr.CGS.SJYY"/>二手车发票
							</td>
							<td rowspan="3" style="text-align: center;">
								<input type="button" value="查询" class="bnt" style="cursor: pointer;" onclick="shishi();"/>
							</td>
						</tr>
						<tr>
							<td colspan="2">&nbsp;发票代码：<input type="text" name="ssfpdm" id="ssfpdm" onkeyup="clearspace(this)" onblur="clearallspace(this)" value="${request.ssfpdm}"/></td>
							<td colspan="2">&nbsp;发票号码：<input type="text" name="ssfphm" id="ssfphm" onkeyup="clearspace(this)" onblur="clearallspace(this)" value="${request.ssfphm}"/></td>
						</tr>
						<tr>
							<td colspan="4"><div style="color: blue;">&nbsp;注：由于该项查询是对国税接口的实时数据进行查询，响应时间会较长，请耐心等待</div></td>
						</tr>
					</table>
				</form>				
				<div class="tab2" style="display: block;" id="divid1">
					<ul id="test2_li_now_">
						<li onclick="settab(this,0)" class="now">新车发票</li>
						<li onclick="settab(this,1)">新车购置税发票</li>
						<li onclick="settab(this,2)">二手车发票</li>
					</ul>
				</div>
				<div id="tableid0" style="width:100%; height:300px; display: block; overflow: auto; text-align: center;">
					<div style="width: 2030px; height: 320px; overflow: auto; text-align: center;">
						<table class="datalist" width="2030" border="0" cellpadding="0" cellspacing="0" align="center">
							<tr style="text-align: center;">
								<th width="80">序号</th>
								<th width="100">发票代码</th>
								<th width="100">发票号码</th>
								<th width="150">开票日期</th>
								<th width="150">买方车主姓名</th>
								<th width="150">买方车主身份证号码</th>
								<th width="150">厂牌号码</th>
								<th width="150">车架号</th>
								<th width="150">发动机号</th>
								<th width="150">合格证号</th>
								<th width="150">进口证明号</th>
								<th width="150">价税合计</th>
								<th width="250">销货单位</th>
								<th width="150">纳税人识别号</th>
							</tr>
							<s:if test="#request.fpxcList.size > 0">
								<s:iterator id="xc" value="#request.fpxcList" status="st">
									<tr class="<s:if test="#st.odd == false">altrow</s:if>">
										<td>
											${st.count}
										</td>
										<td>
											${xc.fpdm}
										</td>
										<td>
									 		${xc.fphm}
										</td>
										<td>
											<s:date name="#xc.kprq" format="yyyy-MM-dd HH:mm:ss"/>
										</td>
										<td>
									 		${xc.mfxm }
										</td>
										<td>
											${xc.mfid}
										</td>
										<td>
											${xc.cpxh}
										</td>
										<td>
											${xc.cjh}
										</td>
										<td>
											${xc.fdjh}
										</td>
										<td>
											${xc.hgzh}
										</td>
										<td>
											${xc.jkzmsh}
										</td>
										<td>
											${xc.jshj}
										</td>
										<td>
											${xc.xhdw}
										</td>
										<td>
											${xc.nsrsbh}
										</td>
									</tr>
								</s:iterator>
							</s:if>
							<s:else>
								<tr>
									<td colspan="14">
										<span style="color: red">暂时没有相关数据</span>
									</td>
								</tr>
							</s:else>
						</table>
					</div>
				</div>
				
				<div id="tableid1" style="width:100%; height:300px; display: none; overflow: auto; text-align: center;">
					<div style="width: 1430px; height: 320px; overflow: auto; text-align: center;">
						<table class="datalist" width="1430" border="0" cellpadding="0" cellspacing="0" align="center">
							<tr style="text-align: center;">
								<th width="80">序号</th>
								<th width="250">车辆购置税完税证明编号</th>
								<th width="150">纳税人名称</th>
								<th width="150">厂牌型号</th>
								<th width="150">车架号码</th>
								<th width="150">发动机号码</th>
								<th width="150">免税标注</th>
								<th width="150">对应的发票代码</th>
								<th width="150">对应的发票号码</th>
							</tr>
							<s:if test="#request.fpxcgzsList.size > 0">
								<s:iterator id="xcgzs" value="#request.fpxcgzsList" status="st">
									<tr class="<s:if test="#st.odd == false">altrow</s:if>">
										<td>
											${st.count}
										</td>
										<td>
											${xcgzs.gzszmbh}
										</td>
										<td>
											${xcgzs.nsrmc}
										</td>
										<td>
									 		${xcgzs.cpxh}
										</td>
										<td>
									 		${xcgzs.cjh }
										</td>
										<td>
											${xcgzs.fdjh}
										</td>
										<td>
											${xcgzs.msbz}
										</td>
										<td>
											${xcgzs.fpdm}
										</td>
										<td>
											${xcgzs.fphm}
										</td>
										
									</tr>
								</s:iterator>
							</s:if>
							<s:else>
								<tr>
									<td colspan="9">
										<span style="color: red">暂时没有相关数据</span>
									</td>
								</tr>
							</s:else>
						</table>
					</div>
				</div>
				
				<div id="tableid2" style="width:100%; height:300px; display: none; overflow: auto; text-align: center;">
					<div style="width: 2480px; height: 320px; overflow: auto; text-align: center;">
						<table class="datalist" width="2480" border="0" cellpadding="0" cellspacing="0" align="center">
							<tr style="text-align: center;">
								<th width="80">序号</th>
								<th width="150">发票代码</th>
								<th width="150">发票号码</th>
								<th width="150">开票日期</th>
								<th width="150">买方车主姓名</th>
								<th width="150">买方车主身份证号码</th>
								<th width="150">买方联系电话</th>
								<th width="150">卖方车主姓名</th>
								<th width="150">卖方车主身份证号码</th>
								<th width="150">卖方联系电话</th>
								<th width="150">车牌号</th>
								<th width="150">等级证书号码</th>
								<th width="150">车架号</th>
								<th width="150">车架合计金额</th>
								<th width="150">经营/拍卖单位</th>
								<th width="150">二手车市场</th>
								<th width="150">纳税人识别号</th>
							</tr>
							<s:if test="#request.fpescList.size > 0">
								<s:iterator id="esc" value="#request.fpescList" status="st">
									<tr class="<s:if test="#st.odd == false">altrow</s:if>">
										<td>
											${st.count}
										</td>
										<td>
											${esc.fpdm}
										</td>
										<td>
									 		${esc.fphm}
										</td>
										<td>
											<s:date name="#esc.kprq" format="yyyy-MM-dd HH:mm:ss"/>
										</td>
										<td>
									 		${esc.mfxm }
										</td>
										<td>
											${esc.mfid}
										</td>
										<td>
											${esc.mflx}
										</td>
										<td>
											${esc.mfxm1}
										</td>
										<td>
											${esc.mfid1}
										</td>
										<td>
											${esc.mfl1}
										</td>
										<td>
											${esc.cph}
										</td>
										<td>
											${esc.djzsbh}
										</td>
										<td>
											${esc.cjh}
										</td>
										<td>
											${esc.hjje}
										</td>
										<td>
											${esc.jydw}
										</td>
										<td>
											${esc.escsc}
										</td>
										<td>
											${esc.nsrsbh}
										</td>
									</tr>
								</s:iterator>
							</s:if>
							<s:else>
								<tr>
									<td colspan="17">
										<span style="color: red">暂时没有相关数据</span>
									</td>
								</tr>
							</s:else>
						</table>
					</div>
				</div>
				
			</div>
		</div>
	</div>
  </body>
</html>
