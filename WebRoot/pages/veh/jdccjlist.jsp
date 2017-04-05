<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="com.ycszh.common.PageTag" prefix="pt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
  <head>
    <title>受理查询列表</title>
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
	<script type="text/javascript">
		var chuli;
		$(document).ready(function(){
			var exportData = '${request.exportData}';
			if(exportData != null && exportData != ""){
				alert(exportData);
			}
		});
		
		function editsp(url){
			window.location.href = url;
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
		function windowopen(id){
			var info = window.open('<%=request.getContextPath()%>/veh/veh_initSlCx.action?id=' + id ,'info','width='+(window.screen.availWidth-10)+',height='+(window.screen.availHeight-30)+ ',top=0,left=0,toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=yes');
		}
		
		function pageload(){
			var jbrbm = '${request.jbrbm}';
			$("#jbrbm").val(jbrbm);
		}
		
		function check(){
			var lsh = $("#lsh").val();
			if(lsh == null || lsh == ''){
				alert("请根据流水号查询!");
				return false;
			}
			$("#esfrom").attr("action", "<%=request.getContextPath()%>/veh/veh_getZjxxbList.action");
			$("#esfrom").submit();
		}
		
		function openpage(id, lsh){
			art.dialog.open('<%=request.getContextPath()%>/pages/veh/tbedit.jsp?id='+id+'&lsh='+lsh, {width:600,height:255,title:'退办', opacity: 0.4});
		}
		
	</script>
	
  </head>
  
  <body onload="pageload();" style="background:#c7e5ff;">
    <div class="content1" style="width: 100%;">
		  <div class="roundedBox" id="type1" style="width: 95%;">
				<div class="right" style="width: 98%;">
					<form action="<%=request.getContextPath()%>/veh/veh_getZjxxbList.action" method="post" id="esfrom">
						<table class="table1" width="100%" border="0"
							cellpadding="0" cellspacing="0">
							<tr>
								<th class="th1" height="32" colspan="2">
									查询条件
								</th>
							</tr>
							<tr>
								<td class="tds" style="text-align: right;">
									流水号&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<input type="text" name="lsh" id="lsh" value="${lsh}" onkeyup="clearspace(this)" onblur="clearallspace(this)"/>
								</td>
							</tr>
							<tr>
								<td class="tds" colspan="2">
									<div align="center">
										<input class="bnt" type="button" value="查  询" onclick="check();" style="cursor:pointer;" />
									</div>
								</td>
							</tr>
						</table>
					</form>
					<br>
					<table class="datalist" width="100%" border="0"
						cellpadding="0" cellspacing="0">
						<tr class="tr1">
							<th>
								序号
							</th>
							<th>
								流水号
							</th>
							<th>
								号牌号码
							</th>
							<th>
								号牌种类
							</th>
							<th>
								业务类型
							</th>
							<th>
								指标类型
							</th>
							<th>
								指标号
							</th>
							<th>
								公证号
							</th>
							<th>
								录入时间
							</th>
							<th>
								退办人
							</th>
							<th>
								退办原因
							</th>
							<th>
								退办时间
							</th>
							<th>
								操作
							</th>
						</tr>
						<s:if test="#request.zjxxList.size > 0">
							<s:iterator id="dbjgZjxxb" value="#request.zjxxList" status="st">
								<tr class="<s:if test="#st.odd == false">altrow</s:if>">
									<td>
										${st.count + (map.currentpage-1) * map.pagesize}
									</td>
									<td>
										${dbjgZjxxb.lsh }
									</td>
									<td>
										${dbjgZjxxb.hphm }
									</td>
									<td>
								 		<s:iterator id="hpzl" value="#request.hpzlList">
											<s:if test="#request.dbjgZjxxb.hpzl==#hpzl.dmz">
												<s:property value="#hpzl.dmms1"/>
											</s:if>
										</s:iterator>
									</td>
									<td>
										${dbjgZjxxb.ywlx}
									</td>
									<td>
										${dbjgZjxxb.zblx}
									</td>
									<td>
										${dbjgZjxxb.zbh}
									</td>
									<td>
										${dbjgZjxxb.gzh}
									</td>
									<td>
								 		<fmt:formatDate value="${dbjgZjxxb.lrsj}" pattern="yyyy-MM-dd HH:mm:ss" />
									</td>
									<td>
								 		${dbjgZjxxb.tbrxm}
									</td>
									<td>
								 		${dbjgZjxxb.tbyy}
									</td>
									<td>
								 		<fmt:formatDate value="${dbjgZjxxb.tbrq}" pattern="yyyy-MM-dd" />
									</td>
									<td id="${dbjgZjxxb.id}">
										<%-- <a href="<%=request.getContextPath()%>/veh/veh_initSlCx.action?id=${dbjgZjxxb.id}" target="_blank">查看</a> --%>
										<a href="javascript:void(0);" onclick="javascript:windowopen(${dbjgZjxxb.id});">查看</a>
										<s:if test="#request.dbjgZjxxb.zbh != null && #request.dbjgZjxxb.zbh != '' && #request.dbjgZjxxb.tbr == null">
											<a href="javascript:openpage(${dbjgZjxxb.id}, '${dbjgZjxxb.lsh }');" >退办</a>
										</s:if>
										<a href="javascript:openpage(${dbjgZjxxb.id}, '${dbjgZjxxb.lsh }');" >退办</a>
									</td>
								</tr>
							</s:iterator>
						</s:if>
						<s:else>
							<tr>
								<td colspan="13">
									<span style="color: red">暂时没有相关数据</span>
								</td>
							</tr>
						</s:else>
					</table>
				</div>
				<div class="corner topLeft"></div>
				<div class="corner topRight"></div>
			</div>
		</div>
  </body>
</html>
