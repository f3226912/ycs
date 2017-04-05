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
<title>综合应用平台业务受理情况统计</title>
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
		var xglxss = '${xglxs}';
		var qxchs = '${qxch}';
		if(qxchs == '0'){
			document.getElementById("qxch").checked = "checked";
		}
		var xglxs = document.getElementsByName("xglxs");
		if(xglxss.indexOf(",") > 0){
			var zjcxs = xglxss.split(",");
			for(var i = 0; i < zjcxs.length; i ++){
				for(var j = 0; j < xglxs.length; j ++){
					if(zjcxs[i] == xglxs[j].value){
						xglxs[j].checked = "checked";
					}
				}
			}
		}else{
			for(var j = 0; j < xglxs.length; j ++){
				if(xglxss == xglxs[j].value){
					xglxs[j].checked = "checked";
				}
			}
		}
		
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
	
	function qx(obj){
		var xglxsss = document.getElementsByName("xglxs");
		if(obj.checked){
			for(var j = 0; j < xglxsss.length; j ++){
				xglxsss[j].checked = "checked";
			}
		}else{
			for(var j = 0; j < xglxsss.length; j ++){
				xglxsss[j].checked = "";
			}
		}
	}

    function ck(){
    	window.mjclform.action='<%=request.getContextPath()%>/mjcl/mjcl_xgslQuery.action';
    	window.mjclform.submit();
    }

    function exportToExport(){
    	var nodata=$("#nodata");
    	var rscount = '${rscount}';
		if(nodata.length>0){
			alert("无数据导出");
		}else{
			if(rscount > 50000){
				alert("数据量超过5W条,请缩小查询范围后导出!");
			}else{
				var t =document.getElementById("searchfromid");
		    	t.action="<%=request.getContextPath()%>/mjcl/mjcl_xgslQueryExcel.action";
		    	t.submit();
			}
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
	<div class="content1" style="width:98%;">
		<div class="roundedBox" id="type1" style="width:97%;">
			<div class="right" style="width:99%;">
				<form action="<%=request.getContextPath()%>/mjcl/mjcl_xgslQuery.action" method="post" id="searchfromid" name="mjclform">
				<table class="table1" width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<th class="th1" height="32" colspan="4">
							操作
						</th>
					</tr>
					<tr>
						<td class="tds" style="text-align: right;" >
							申请时间&nbsp;
						</td>
						<td class="tdl" style="text-align: left;width: 45%">
							<input type="text" name="s_date" id="s_date" value="${s_date}"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false})" />
							至
							<input type="text" name="e_date" id="e_date" value="${e_date}"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false})" />
						</td>
						<td class="tds" style="text-align: right;">
							部门&nbsp;
						</td>
						<td class="tdl" style="text-align: left;">
							<select name="glbms" id="glbms" >
								<option value="">---请选择---</option>
								<s:iterator var="obj" value="#session.listDept">
									<option value='<s:property value="#obj[0]"/>' 
										<s:if test="#request.glbms==#obj[0]">selected</s:if>>
										<s:property value="#obj[1]"/>
									</option>
								</s:iterator>
							</select>
						</td>
					</tr>
					<tr>
						<td class="tds" style="text-align: right;" >
							业务类型&nbsp;
						</td>
						<td class="tdl" style="text-align: left;width: 45%" colspan="3">
							<select name="ywlxs" id="ywlxs" >
								<option value="">---请选择---</option>
								<s:iterator var="obj" value="#session.listywlx">
									<option value='<s:property value="#obj[0]"/>' 
										<s:if test="#request.ywlxs==#obj[0]">selected</s:if>>
										<s:property value="#obj[1]"/>
									</option>
								</s:iterator>
							</select>
						</td>
					</tr>
					<tr>
						<td class="tds" style="text-align: right;">
							限购类型（<input type="checkbox" id="qxch" name="qxch" value="0" onclick="qx(this);" />全选）&nbsp;
						</td>
						<td class="tdl" style="text-align: left;" colspan="3">
							<s:iterator var="obj" value="#session.listxglx" status="st">
								<s:if test="#st.count == 6"><br/></s:if>
								<input type="checkbox" name="xglxs" value="<s:property value="#obj[0]"/>" /><s:property value="#obj[1]"/>
							</s:iterator>
						</td>
					</tr>
					<tr>
						<td class="tds" colspan="4">
							<div align="center">
								<input class="bnt" type="button" id="frombutid" value="查  询" onclick="ck()" style="cursor:pointer;"/>
								&nbsp;&nbsp;
								<input class="bnt" type="button" id="frombutid" value="导出Excel" onclick="exportToExport()" style="cursor:pointer;"/>
							</div>
						</td>
					</tr>
				</table>
				</form>
				<font color="blue">${tjstr}</font>
				<table class="datalist" width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr class="tr1">
						<th>
							序列
						</th>
						<th>
							流水号
						</th>
						<th>
							序号
						</th>
						<th>
							号牌号码
						</th>
						<th>
							号牌种类
						</th>
						<th>
							所有人
						</th>
						<th>
							车辆型号
						</th>
						<th>
							申请日期
						</th>
						<th>
							办结日期
						</th>
						<th>
							管理部门
						</th>
					</tr>
					<s:if test="#request.list.size > 0">
						<s:iterator id="mjcl" value="#request.list" status="st">
							<tr class="<s:if test="#st.odd == false">altrow</s:if>">
								<td>
									${st.count + (map.currentpage-1) * map.pagesize}
								</td>
								<td>
									<s:property value="#request.mjcl[0]"/>
								</td>
								<td>
									<s:property value="#request.mjcl[1]"/>
								</td>
								<td>
									<s:property value="#request.mjcl[2]"/>
								</td>
								<td>
									<s:if test="#request.mjcl[3] == '01'">
										大型汽车
									</s:if>
									<s:elseif test="#request.mjcl[3] == '02'">
										小型汽车
									</s:elseif>
									<s:elseif test="#request.mjcl[3] == '03'">
										使馆汽车
									</s:elseif>
									<s:elseif test="#request.mjcl[3] == '04'">
										领馆汽车
									</s:elseif>
									<s:elseif test="#request.mjcl[3] == '05'">
										境外汽车
									</s:elseif>
									<s:elseif test="#request.mjcl[3] == '06'">
										外籍汽车
									</s:elseif>
									<s:elseif test="#request.mjcl[3] == '07'">
										普通摩托车
									</s:elseif>
									<s:elseif test="#request.mjcl[3] == '08'">
										轻便摩托车
									</s:elseif>
									<s:elseif test="#request.mjcl[3] == '09'">
										使馆摩托车
									</s:elseif>
									<s:elseif test="#request.mjcl[3] == '10'">
										领馆摩托车
									</s:elseif>
									<s:elseif test="#request.mjcl[3] == '11'">
										境外摩托车
									</s:elseif>
									<s:elseif test="#request.mjcl[3] == '12'">
										外籍摩托车
									</s:elseif>
									<s:elseif test="#request.mjcl[3] == '13'">
										低速车
									</s:elseif>
									<s:elseif test="#request.mjcl[3] == '14'">
										拖拉机
									</s:elseif>
									<s:elseif test="#request.mjcl[3] == '15'">
										挂车
									</s:elseif>
									<s:elseif test="#request.mjcl[3] == '16'">
										教练汽车
									</s:elseif>
									<s:elseif test="#request.mjcl[3] == '17'">
										教练摩托车
									</s:elseif>
									<s:elseif test="#request.mjcl[3] == '18'">
										试验汽车
									</s:elseif>
									<s:elseif test="#request.mjcl[3] == '19'">
										试验摩托车
									</s:elseif>
									<s:elseif test="#request.mjcl[3] == '20'">
										临时入境汽车
									</s:elseif>
									<s:elseif test="#request.mjcl[3] == '21'">
										临时入境摩托车
									</s:elseif>
									<s:elseif test="#request.mjcl[3] == '22'">
										临时行驶车
									</s:elseif>
									<s:elseif test="#request.mjcl[3] == '23'">
										警用汽车
									</s:elseif>
									<s:elseif test="#request.mjcl[3] == '24'">
										警用摩托
									</s:elseif>
									<s:elseif test="#request.mjcl[3] == '25'">
										原农机号牌
									</s:elseif>
									<s:elseif test="#request.mjcl[3] == '26'">
										香港入出境车
									</s:elseif>
									<s:elseif test="#request.mjcl[3] == '27'">
										澳门入出境车
									</s:elseif>
									<s:else>
										未知
									</s:else>
								</td>
								<td>
									<s:property value="#request.mjcl[4]"/>
								</td>
								<td>
									<s:property value="#request.mjcl[5]"/>
								</td>
								<td>
									<s:property value="#request.mjcl[6]"/>
								</td>
								<td>
									<s:property value="#request.mjcl[7]"/>
									<%--<s:date name="#request.mjcl[7]" format="yyyy-MM-dd"/>--%>
								</td>
								<td>
									<s:property value="#request.mjcl[8]"/>
								</td>
							</tr>
						</s:iterator>
					</s:if>
					<s:else>
						<tr>
							<td colspan="9">
								<span id="nodata" style="color: red">暂时没有相关数据</span>
							</td>
						</tr>
					</s:else>
				</table>
				<table width="100%" border="0" cellspacing="0" cellpadding="0" style="padding-top: 5px;">
					<pt:write uri="${map.uri}" pagesize="${map.pagesize}" rscount="${map.rscount}" currentpage="${map.currentpage}"></pt:write>
				</table>
			</div>
			<div class="corner topLeft"></div>
			<div class="corner topRight"></div>
			<div class="corner bottomLeft"></div>
			<div class="corner bottomRight"></div>
		</div>
	</div>
</body>
</html>
