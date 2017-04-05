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
<title>超龄驾驶体检审核</title>
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

	function bc(xh){
		var dd = $("input[name='sl']:checked");
		if(dd.val()=='cg'){
			var dhhm = document.getElementById("dhhm").value;
			if(dhhm==''){
				alert("请输入手机号码");
				return false;
			}
			var tylsh = document.getElementById("tylsh").value;
			if(tylsh==''){
				alert("请输入统一流水号");
				return false;
			}
			alert("受理成功");
			window.location.href='<%=request.getContextPath()%>/cljstj/cljstj_cljstjSh.action?tylsh='+tylsh+'&zt='+'1'+'&xh='+xh+'&cz=1&dhhm='+dhhm;
		}else if(dd.val()=='sb'){
			var dhhm = document.getElementById("dhhm").value;
			if(dhhm==''){
				alert("请输入手机号码");
				return false;
			}
			var sbyy = document.getElementById("sbyy").value;
			alert("受理失败");
			window.location.href='<%=request.getContextPath()%>/cljstj/cljstj_cljstjSh.action?sbyy='+sbyy+'&zt='+'2'+'&xh='+xh+'&cz=1&dhhm='+dhhm;
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
							超龄驾驶体检审核
						</th>
					</tr>
					<tr >
						<td class="tds" style="text-align: right;">
							顺序号
						</td>
						<td class="tdl" style="text-align: left;">
							<input type="text" value="${hl.sxh }" disabled="disabled" />
						</td>
					</tr>
					<tr >	
						<td class="tds" style="text-align: right;">
							姓名
						</td>
						<td class="tdl" style="text-align: left;">
							 <input type="text" value="${hl.xm }" disabled="disabled"/>
						</td>
					</tr>
					<tr >	
						<td class="tds" style="text-align: right;">
							身份证明号码
						</td>
						<td class="tdl" style="text-align: left;">
							 <input type="text" value="${hl.sfzmhm }" disabled="disabled"/>
						</td>
					</tr>
					<tr >	
					<td class="tds" style="text-align: right;">
							手机号码
						</td>
						<td class="tdl" style="text-align: left;">
							 <input type="text" value="${hl.lxdh }" id="dhhm"/><font color="red">*</font>
						</td>
					</tr>
					<tr >	
						<td class="tds" style="text-align: right;">
							档案编号
						</td>
						<td class="tdl" style="text-align: left;">
							 <input type="text" value="${hl.dabh }" disabled="disabled"/>
						</td>
					</tr>
					<tr >	
						<td class="tds" style="text-align: right;">
							体检医院名称
						</td>
						<td class="tdl" style="text-align: left;">
							 <input type="text" value="${hl.tjyymc }" size="40" disabled="disabled"/>
						</td>
					</tr>
					<tr >	
						<td class="tds" style="text-align: right;">
							体检时间
						</td>
						<td class="tdl" style="text-align: left;">
							 <input type="text" value="<s:date name="#request.hl.tjrq" format="yyyy-MM-dd HH:mm:ss"/>" disabled="disabled"/>
						</td>
					</tr>
					<tr >	
						<td class="tds" style="text-align: right;">
							身高
						</td>
						<td class="tdl" style="text-align: left;">
							 <input type="text" value="${hl.sg }" disabled="disabled"/>
						</td>
					</tr>
					<tr >	
						<td class="tds" style="text-align: right;">
							左视力
						</td>
						<td class="tdl" style="text-align: left;">
							 <input type="text" value="${hl.zsl }" disabled="disabled"/>
						</td>
					</tr>
					<tr >	
						<td class="tds" style="text-align: right;">
							右视力
						</td>
						<td class="tdl" style="text-align: left;">
							 <input type="text" value="${hl.ysl }" disabled="disabled"/>
						</td>
					</tr>
					<tr >	
						<td class="tds" style="text-align: right;">
							审核状态
						</td>
						<td class="tdl" style="text-align: left;">
							 <input type="text" value="${hl.zhclsm }" disabled="disabled"/>
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
							<input type="text" id="tylsh" size="40"/><font color="red">*</font>
						</td>
					</tr>
					<tr id="by" style="display: none;">
						<td class="tds" style="text-align: right;">
							失败原因
						</td>
						<td class="tdl" style="text-align: left;">
							<input type="text" id="sbyy" size="40"/>
						</td>
					</tr>
					<tr >	
						<td colspan="2" align="center">
							<input type="button" value="保存" class="bnt" onclick="return bc('<s:property value="#request.hl.sxh"/>')"/>
							<input type="button" value="取消" class="bnt" onclick="javascript:history.go(-1);"/>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</body>
</html>
