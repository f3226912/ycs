<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="com.ycszh.common.PageTag" prefix="pt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<title>机动车联系方式变更审核</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/content.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/jquery-1.8.2.js"></script>
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
<style type="text/css">
	.ui-autocomplete-loading {
		background: white url('<%=request.getContextPath()%>/jquery/development-bundle/demos/autocomplete/images/ui-anim_basic_16x16.gif') right center no-repeat;
	}
	#susernameid { width: 12em; }
	.bnt2 {
		width: 76px;
		height: 27px;
		background: url('<%=request.getContextPath()%>/images/an3.gif') no-repeat;
		border: none;
		font-weight: bold;
	}
</style>
<script type="text/javascript">
	$(document).ready(function() {
		
	});

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

	function bc(lsh){
		var dd = $("input[name='sl']:checked");
		if(dd.val()=='cg'){
			var tylsh = document.getElementById("tylsh").value;
			if(tylsh==''){
				alert("请输入统一流水号");
				return false;
			}
			window.location.href='<%=request.getContextPath()%>/jdcbg/jdcbg_contactBgSh.action?tylsh='+tylsh+'&zt='+'1'+'&lsh='+lsh+'&cz=1';
		}else if(dd.val()=='sb'){
			var sbyy = document.getElementById("sbyy").value;
			window.location.href='<%=request.getContextPath()%>/jdcbg/jdcbg_contactBgSh.action?sbyy='+sbyy+'&zt='+'2'+'&lsh='+lsh+'&cz=1';
		}
	}

	function ff(){
		var dd = $("input[name='sl']:checked");
		if(dd.val()=='cg'){
			var obj = document.getElementById("ty");
			obj.style.display = ""
			var obj = document.getElementById("by");
			obj.style.display = "none";
		}else if(dd.val()=='sb'){
			var obj = document.getElementById("ty");
			obj.style.display = "none"
			var obj = document.getElementById("by");
			obj.style.display = "";
		}
	}
</script>
<style> 
	html{ 
		overflow:scroll;
		scrollbar-base-color:#c7e5ff;
		scrollbar-track-color:#FFFFFF;
	} 
</style>
</head>
<body style="background:#c7e5ff;">
	<div class="content1" style="width:100%;">
		<div class="roundedBox" id="type1" style="width:95%;">
			<div class="right" style="width:90%;">
				<table class="table1" width="100%" border="1" cellpadding="1" cellspacing="1">
					<tr>
						<th class="th1" height="32" colspan="2">
							机动车联系方式变更审核
						</th>
					</tr>
					<tr >
						<td class="tds" style="text-align: right;">
							流水号 
						</td>
						<td class="tdl" style="text-align: left;">
							<input type="text" value="${bgym.wwlsh }" disabled="disabled" />
						</td>
					</tr>
					<tr >
						<td class="tds" style="text-align: right;">
							车牌号码
						</td>
						<td class="tdl" style="text-align: left;">
							<input type="text" value="${bgym.cphm }" disabled="disabled" />
						</td>
					</tr>
					<tr >
						<td class="tds" style="text-align: right;">
							号牌种类
						</td>
						<td class="tdl" style="text-align: left;">
							<s:if test="#request.bgym.cllx == '01'">
								<input type="text" value="大型汽车" disabled="disabled" />
							</s:if>
							<s:elseif test="#request.bgym.cllx == '02'">
								<input type="text" value="小型汽车" disabled="disabled" />
							</s:elseif>
							<s:elseif test="#request.bgym.cllx == '03'">
								<input type="text" value="使馆汽车" disabled="disabled" />
							</s:elseif>
							<s:elseif test="#request.bgym.cllx == '04'">
								<input type="text" value="领馆汽车" disabled="disabled" />
							</s:elseif>
							<s:elseif test="#request.bgym.cllx == '05'">
								<input type="text" value="境外汽车" disabled="disabled" />
							</s:elseif>
							<s:elseif test="#request.bgym.cllx == '06'">
								<input type="text" value="外籍汽车" disabled="disabled" />
							</s:elseif>
							<s:elseif test="#request.bgym.cllx == '07'">
								<input type="text" value="普通摩托车" disabled="disabled" />
							</s:elseif>
							<s:elseif test="#request.bgym.cllx == '08'">
								<input type="text" value="轻便摩托车" disabled="disabled" />
							</s:elseif>
							<s:elseif test="#request.bgym.cllx == '09'">
								<input type="text" value="使馆摩托车" disabled="disabled" />
							</s:elseif>
							<s:elseif test="#request.bgym.cllx == '10'">
								<input type="text" value="领馆摩托车" disabled="disabled" />
							</s:elseif>
							<s:elseif test="#request.bgym.cllx == '11'">
								<input type="text" value="境外摩托车" disabled="disabled" />
							</s:elseif>
							<s:elseif test="#request.bgym.cllx == '12'">
								<input type="text" value="外籍摩托车" disabled="disabled" />
							</s:elseif>
							<s:elseif test="#request.bgym.cllx == '13'">
								<input type="text" value="低速车" disabled="disabled" />
							</s:elseif>
							<s:elseif test="#request.bgym.cllx == '14'">
								<input type="text" value="拖拉机" disabled="disabled" />
							</s:elseif>
							<s:elseif test="#request.bgym.cllx == '15'">
								<input type="text" value="挂车" disabled="disabled" />
							</s:elseif>
							<s:elseif test="#request.bgym.cllx == '16'">
								<input type="text" value="教练汽车" disabled="disabled" />
							</s:elseif>
							<s:elseif test="#request.bgym.cllx == '17'">
								<input type="text" value="教练摩托车" disabled="disabled" />
							</s:elseif>
							<s:elseif test="#request.bgym.cllx == '18'">
								<input type="text" value="试验汽车" disabled="disabled" />
							</s:elseif>
							<s:elseif test="#request.bgym.cllx == '19'">
								<input type="text" value="试验摩托车" disabled="disabled" />
							</s:elseif>
							<s:elseif test="#request.bgym.cllx == '20'">
								<input type="text" value="临时入境汽车" disabled="disabled" />
							</s:elseif>
							<s:elseif test="#request.bgym.cllx == '21'">
								<input type="text" value="临时入境摩托车" disabled="disabled" />
							</s:elseif>
							<s:elseif test="#request.bgym.cllx == '22'">
								<input type="text" value="临时行驶车" disabled="disabled" />
							</s:elseif>
							<s:elseif test="#request.bgym.cllx == '23'">
								<input type="text" value="警用汽车" disabled="disabled" />
							</s:elseif>
							<s:elseif test="#request.bgym.cllx == '24'">
								<input type="text" value="警用摩托" disabled="disabled" />
							</s:elseif>
							<s:elseif test="#request.bgym.cllx == '25'">
								<input type="text" value="原农机号牌" disabled="disabled" />
							</s:elseif>
							<s:elseif test="#request.bgym.cllx == '26'">
								<input type="text" value="香港入出境车" disabled="disabled" />
							</s:elseif>
							<s:elseif test="#request.bgym.cllx == '27'">
								<input type="text" value="澳门入出境车" disabled="disabled" />
							</s:elseif>
							<s:else>
								<input type="text" value="未知" disabled="disabled" />
							</s:else>
						</td>
					</tr>
					<tr >	
						<td class="tds" style="text-align: right;">
							车主
						</td>
						<td class="tdl" style="text-align: left;">
							 <input type="text" value="${bgym.syr }" disabled="disabled"/>
						</td>
					</tr>
					<tr >
						<td class="tds" style="text-align: right;">
							车辆识别代码
						</td>
						<td class="tdl" style="text-align: left;">
							<input type="text" value="${bgym.clsbdm }" disabled="disabled" />
						</td>
					</tr>
					<tr >
						<td class="tds" style="text-align: right;">
							发动机号码
						</td>
						<td class="tdl" style="text-align: left;">
							<input type="text" value="${bgym.fdjhm }" disabled="disabled" />
						</td>
					</tr>
					<tr >	
						<td class="tds" style="text-align: right;">
							车主身份证明号码
						</td>
						<td class="tdl" style="text-align: left;">
							 <input type="text" value="${bgym.sfzmhm }" disabled="disabled"/>
						</td>
					</tr>
					<tr >
						<td class="tds" style="text-align: right;">
							联系电话
						</td>
						<td class="tdl" style="text-align: left;">
							<input type="text" value="${bgym.yddh }" disabled="disabled"/>
						</td>
					</tr>
					<tr >	
						<td class="tds" style="text-align: right;">
							联系地址
						</td>
						<td class="tdl" style="text-align: left;">
							 <input type="text" value="${bgym.lxdz }" size="50" disabled="disabled"/>
						</td>
					</tr>
					<tr >	
						<td class="tds" style="text-align: right;">
							移动电话
						</td>
						<td class="tdl" style="text-align: left;">
							 <input type="text" value="${bgym.yddh }" disabled="disabled"/>
						</td>
					</tr>
					<tr >	
						<td class="tds" style="text-align: right;">
							电子邮件
						</td>
						<td class="tdl" style="text-align: left;">
							 <input type="text" value="${bgym.dzyj }" disabled="disabled"/>
						</td>
					</tr>
					<tr>	
						<td class="tds" style="text-align: right;">
							邮政编码
						</td>
						<td class="tdl" style="text-align: left;">
							 <input type="text" value="${bgym.yzbm }" disabled="disabled"/>
						</td>
					</tr>
					<tr >	
						<td class="tds" style="text-align: right;">
							注册登记日期
						</td>
						<td class="tdl" style="text-align: left;">
							<input type="text" value="<s:date name="#request.bgym.zcdjrq" format="yyyy-MM-dd"/>" disabled="disabled"/>
						</td>
					</tr>
					<tr >	
						<td class="tds" style="text-align: right;">
							受理结果
						</td>
						<td class="tdl" style="text-align: left;">
							<input type ='radio' name='sl' value='cg' checked="checked" onclick="ff()"/>成功
							<input type ='radio' name='sl' value='sb' onclick="ff()"/>不予受理
						</td>
					</tr>
					<tr id="ty" style="display: block;">
						<td class="tds" style="text-align: right;">
							统一版流水号
						</td>
						<td class="tdl" style="text-align: left;">
							<input type="text" id="tylsh" /><font color="red">*</font>
						</td>
					</tr>
					<tr id="by" style="display: none;">
						<td class="tds" style="text-align: right;">
							失败原因
						</td>
						<td class="tdl" style="text-align: left;">
							<input type="text" id="sbyy" />
						</td>
					</tr>
					<tr >	
						<td colspan="2" align="center">
							<input type="button" value="保存" class="bnt" onclick="return bc('<s:property value="#request.bgym.wwlsh"/>')"/>
							<input type="button" value="取消" class="bnt" onclick="javascript:history.go(-1);"/>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</body>
</html>
