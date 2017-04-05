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
<title>车辆限购违规业务办理统计分析</title>
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
	$(function(){
		$("select[name=sfwg]").val('${wglx}');
	})
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

	function ck(){
    	window.yjgzform.action='<%=request.getContextPath()%>/mjcl/mjcl_yjgzQuery.action';
    	window.yjgzform.submit();
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
				var s =document.getElementById("searchfromid");
		    	s.action="<%=request.getContextPath()%>/mjcl/mjcl_yjgzQueryExcel.action";
		    	s.submit(); 
		    	
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
				<form action="<%=request.getContextPath()%>/mjcl/mjcl_yjgzQuery.action" method="post" id="searchfromid" name="yjgzform">
				<table class="table1" width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<th class="th1" height="32" colspan="4">
							操作
						</th>
					</tr>
					<tr>
						<td class="tds" style="text-align: right;" >
							处理时间&nbsp;
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
							<select name="glbm" id="glbm" >
								<option value="">---请选择---</option>
								<s:iterator var="obj" value="#session.listDept">
									<option value='<s:property value="#obj[0]"/>' 
										<s:if test="#request.glbm==#obj[0]">selected</s:if>>
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
						<td class="tdl" style="text-align: left;">
							<select name="ywlx" id="ywlx" >
								<option value="">---请选择---</option>
								<s:iterator var="obj" value="#session.listywlx">
									<option value='<s:property value="#obj[1]"/>' 
										<s:if test="#request.ywlx==#obj[1]">selected</s:if>>
										<s:property value="#obj[2]"/>
									</option>
								</s:iterator>
							</select>
						</td>
						<td class="tds" style="text-align: right;">
							流水号&nbsp;
						</td>
						<td class="tdl" style="text-align: left;">
							<input type="text" name="lsh" id="lsh"  onblur="clearallspace(this);" value="${lsh}"/>
						</td>
					</tr>
					<tr>
						<td class="tds" style="text-align: right;">
							违规类型&nbsp;
						</td>
						<td class="tdl" style="text-align: left;" colspan="3">
							<%--<s:select theme="simple" list="#{'':'---请选择---','1':'直接在统一版进行受理','4':'车辆类型修改','5':'所有人修改','6':'临牌在一窗式未采集或不一致'}" 
								id="sfwg"
								listKey="key" listValue="value" name="sfwg"  value="#request.jdcWgYjgz.sfwg"/>
							--%>
							<select name="sfwg" id="sfwg" >
								<option value="">---请选择---</option>
								<option value="1-直接在统一版进行受理">1-直接在统一版进行受理</option>
								<option value="4-车辆类型修改">4-车辆类型修改</option>
								<option value="5-所有人修改">5-所有人修改</option>
								<option value="6-临牌在一窗式未采集或不一致">6-临牌在一窗式未采集或不一致</option>
								<option value="7-4S店无指标标注上牌">7-4S店无指标标注上牌</option>
							</select>
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
							身份证明号码
						</th>
						<th>
							姓名
						</th>
						<th>
							车辆类型
						</th>
						<th>
							所有人
						</th>
						<th>
							业务类型
						</th>
						<th>
							处理日期
						</th>
						<th>
							经办人
						</th>
						<th>
							管理部门名称
						</th>
						<th>
							违规种类
						</th>
						<th>
							短信内容
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
									<s:property value="#request.mjcl[3]"/>
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
								</td>
								<td>
									<s:property value="#request.mjcl[8]"/>
								</td>
								<td>
									<s:property value="#request.mjcl[9]"/>
								</td>
								<td>
									<a href="javascript:void(0);" title='<s:property value="#request.mjcl[10]"/>' >查看短信</a>
								</td>
							</tr>
						</s:iterator>
					</s:if>
					<s:else>
						<tr>
							<td colspan="12">
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
