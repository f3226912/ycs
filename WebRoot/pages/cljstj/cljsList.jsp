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
<title>超龄驾驶体检查询</title>
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

    function ck(xh){
    	window.location.href='<%=request.getContextPath()%>/cljstj/cljstj_cljstjShYm.action?xh='+xh+'&ck='+2;
    }

    function bd(){
    	var lsh=document.getElementById("wwlshs").value;
    	
    	if(lsh.length<13){
    		alert('统一版流水号不能少于13位！');
    		return false;
        }
        
    	document.getElementById("searchfromid").submit();
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
				<form action="<%=request.getContextPath()%>/cljstj/cljstj_cljstjQuery.action" method="post" id="searchfromid">
				<table class="table1" width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<th class="th1" height="32" colspan="4">
							操作
						</th>
					</tr>
					<tr>
						<td class="tds" style="text-align: right;">
							统一版流水号&nbsp;
						</td>
						<td class="tdl" style="text-align: left;">
							<input id="wwlshs" type="text" name="wwlshs" value="${wwlshs}" />
						</td>
						<td class="tds" style="text-align: right;">
							身份证明号码&nbsp;
						</td>
						<td class="tdl" style="text-align: left;">
							<input id="sfzmhms" type="text" name="sfzmhms" value="${sfzmhms}" />
						</td>
					</tr>
					<tr>
						<td class="tds" style="text-align: right;" >
							体检时间&nbsp;
						</td>
						<td class="tdl" style="text-align: left;" colspan="3">
							<input type="text" name="startDay" id="startDay" value="${request.startDay}"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false})" />
							至
							<input type="text" name="endDay" id="endDay" value="${request.endDay}"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false})" />
						</td>
					</tr>
					<tr>
						<td class="tds" colspan="4">
							<div align="center">
								<input class="bnt" type="button" id="frombutid" value="查  询" onclick="bd();" style="cursor:pointer;"/>
								&nbsp;&nbsp;
							</div>
						</td>
					</tr>
				</table>
				</form>
				<table class="datalist" width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr class="tr1">
						<th>
							顺序号
						</th>
						<th>
							姓名
						</th>
						<th>
							身份证明号码
						</th>
						<th>
							档案编号
						</th>
						<th>
							体检医院名称
						</th>
						<th>
							体检时间
						</th>
						<th>
							审核状态
						</th>
						<th>
							操作
						</th>
					</tr>
					<s:if test="tjList.size > 0">
						<s:iterator id="cljstj" value="tjList" status="st">
							<tr class="<s:if test="#st.odd == false">altrow</s:if>">
								<td>
									${cljstj.sxh }
								</td>
								<td>
									${cljstj.xm }
								</td>
								<td>
									${cljstj.sfzmhm }
								</td>
								<td>
									${cljstj.dabh }
								</td>
								<td>
									${cljstj.tjyymc }
								</td>
								<td>
									<s:date name="#cljstj.tjrq" format="yyyy-MM-dd HH:mm:ss"/>
								</td>
								<td>
									${cljstj.zhclsm }
								</td>
								<td>
									<a href="#" onclick="ck('<s:property value="#cljstj.sxh"/>')">【查看】</a>
								</td>
							</tr>
						</s:iterator>
					</s:if>
					<s:else>
						<tr>
							<td colspan="8">
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
