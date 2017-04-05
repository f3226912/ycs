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
    <title>采集审批列表</title>
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
			setTimeout(function(){
				var splx = '${request.splx}';
				var sprBm = '${request.jbrbm}';
				$("select[name='splx'] option").each(function(){
					if($(this).val() == splx){
						$(this).attr("selected", true);
					}
				});
				$("select[name='jbrbm'] option").each(function(){
					if($(this).val() == sprBm){
						$(this).attr("selected", true);
					}
				});
			},500)
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
		
		function delSlgxx(id){
			if(confirm("确定要删除吗?")){
				$.ajax({
					cache:false,
	    			async:false,
	    			url: '<%=request.getContextPath()%>/vehSpxx/vehSpxx_delSlgSpxx.action',
	    			type: 'post',
	    			data: {"id": id},
	    			dataType: 'json',
	    			error: function(){
		    			alert("读取数据异常!");
		    		},
	    			success: function(result){
						if(result == "1"){
							alert("删除成功!");
							$("#"+id).parents("tr").remove();
						}else if(result == "2"){
							alert("对象不存在，请刷新!");
						}else{
							alert("服务器繁忙，稍候重试!");
						}
						//window.location.reload(true);
		    		}
				});
			}
		}
		function closechuli(){
			chuli.close();
		}
	</script>
	
  </head>
  
  <body style="background:#c7e5ff;">
    <div class="content1" style="width: 100%;">
		  <div class="roundedBox" id="type1" style="width: 95%;">
				<div class="right" style="width: 98%;">
					<form action="<%=request.getContextPath()%>/veh/vehSpxx_initSlgSpxxList.action" method="post" id="esfrom">
						<table class="table1" width="100%" border="0"
							cellpadding="0" cellspacing="0">
							<tr>
								<th class="th1" height="32" colspan="4">
									查询条件
								</th>
							</tr>
							<tr>
								<td class="tds" style="text-align: right;">
									审批类型&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<select name="splx" id="splx">
										<option value="">---请选择---</option>
										<option value="0">当事人</option>
										<option value="1">代理人</option>
									</select>
								</td>
								<td class="tds" style="text-align: right;">
									身份证明号码&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<input type="text" name="sfzmhm" value="${sfzmhm}" />
								</td>
							</tr>
							<tr>
								<td class="tds" style="text-align: right">
									部门&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<select name="jbrbm" id="jbrbm" style="width:155px;">
										<option value="">===请选择===</option>
										<s:iterator id="dept" value="#request.deptList" >
											<option value="${dept[0]}">${dept[1]}</option>
										</s:iterator>
									</select>
								</td>
								<td class="tds" style="text-align: right;">
									姓名&nbsp;
								</td>
								<td class="tdl" style="text-align: left;" colspan="3">
									<input type="text" name="xm" value="${xm}" />
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
					</form>
					<div style="width: 98%;height: 35px;padding-top: 10px;">
						<input class="bnt" type="button" value="添加" onclick="javascript:editsp('<%=request.getContextPath()%>/veh/vehSpxx_selSlgSpxx.action?editType=add');" id="excelid" style="cursor:pointer;" />
					</div>
					<table class="datalist" width="100%" border="0"
						cellpadding="0" cellspacing="0">
						<tr class="tr1">
							<th>
								序号
							</th>
							<th>
								审批类型
							</th>
							<th>
								身份证明号码
							</th>
							<th>
								姓名
							</th>
							<th>
								审批人姓名
							</th>
							<th>
								审批人部门
							</th>
							<th>
								审批时间
							</th>
							<th>
								操作
							</th>
						</tr>
						<s:if test="#request.slgSpxxList.size > 0">
							<s:iterator id="slgsp" value="#request.slgSpxxList" status="st">
								<tr class="<s:if test="#st.odd == false">altrow</s:if>">
									<td>
										${st.count + (map.currentpage-1) * map.pagesize}
									</td>
									<td>
										<s:property value="#slgsp.splx2==0?'当事人':'代理人'"/>
									</td>
									<td>
										${slgsp.sfzmhm }
									</td>
									<td>
								 		${slgsp.xm }
									</td>
									<td>
								 		${slgsp.sprName }
									</td>
									<td>
										${slgsp.sprBmmc }
									</td>
									<td>
										<fmt:formatDate value="${slgsp.sprSj}" pattern="yyyy-MM-dd HH:mm:ss" />
									</td>
									<td id="${slgsp.id}">
										<a href="javascript: editsp('<%=request.getContextPath()%>/veh/vehSpxx_selSlgSpxx.action?editType=query&id=${slgsp.id}');">查看</a>
										<c:if test="${slgsp.sprBmkj == kjbmid}">
											<a href="javascript: delSlgxx(${slgsp.id})" target="mainFrame">删除</a>
											<a href="javascript: editsp('<%=request.getContextPath()%>/veh/vehSpxx_selSlgSpxx.action?editType=edit&id=${slgsp.id}');">修改</a>
										</c:if>
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
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						style="padding-top: 5px;">
						<pt:write uri="${map.uri}" pagesize="${map.pagesize}" rscount="${map.rscount}" currentpage="${map.currentpage}"></pt:write>
					</table>
				</div>

				<div class="corner topLeft"></div>
				<div class="corner topRight"></div>
			</div>
		</div>
  </body>
</html>
