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
<title>免检车辆部门统计</title>
<!-- <base target="_self"/> -->
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

    function exportToExport(){
    	var nodata=$("#nodata");
		if(nodata.length>0){
			alert("无数据导出");
		}else{
	        var count=${count};
	        var s_date='<s:property value="#request.s_date"/>';
	        var e_date='<s:property value="#request.e_date"/>';
		    var uri="<%=request.getContextPath()%>/mjcl/mjcl_mjclTjExcel.action?s_date="+s_date+"&e_date="+e_date+"&count="+count;
		    window.location.href=uri;
		    /*var t =document.getElementById("tj");
	    	t.action=uri;
	    	t.submit();*/
	    	//window.coles;
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
		<div class="roundedBox" id="type1" style="width:95%;">
			<div class="right" style="width:90%;">
				<table class="table1" width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<th class="th1" height="32">
							操作
						</th>
					</tr>
					<tr>
						<td class="tds" >
							<div align="center">
								&nbsp;&nbsp;<input class="bnt" type="button" id="frombutid" value="导出Excel" onclick="exportToExport()" style="cursor:pointer;"/>
								&nbsp;&nbsp;<input class="bnt" type="button" id="fh" value="返回" onclick="history.go(-1);" style="cursor:pointer;"/>
							</div>
						</td>
					</tr>
				</table>
				&nbsp;&nbsp;<font color="red">统计时间：${s_date} 至 ${e_date} 如下：</font>
				<table class="datalist" width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr class="tr1">
						<th>
							序号
						</th>
						<th>
							管理部门
						</th>
						<th>
							总数
						</th>
					</tr>
					<s:if test="#request.deptTj.size > 0">
						<s:iterator id="mjcl" value="#request.deptTj" status="st">
							<tr class="<s:if test="#st.odd == false">altrow</s:if>">
								<td>
									 ${st.count + (map.currentpage-1) * map.pagesize}
								</td>
								<td>
									<s:property value="#request.mjcl[1]"/>
								</td>
								<td>
									<s:property value="#request.mjcl[2]"/>
								</td>
							</tr>
						</s:iterator>
					</s:if>
					<s:else>
						<tr>
							<td colspan="3">
								<span id="nodata" style="color: red">暂时没有相关数据</span>
							</td>
						</tr>
					</s:else>
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
