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
    <title>夫妻代办审批列表</title>
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
		
		function delVehPoxx(id){
			if(confirm("确定要删除吗?")){
				$.ajax({
					cache:false,
	    			async:false,
	    			url: '<%=request.getContextPath()%>/vehSpxx/vehSpxx_deleteVehPodb.action',
	    			type: 'GET',
	    			data: {"id": id},
	    			dataType: 'html',
	    			success: function(result){
		    			var message = result+"";
						if(message.indexOf('异常信息') == -1){
							if(result == 1){
								alert("删除成功!");
								$("#"+id).parents("tr").remove();
							}else if(result == 2){
								alert("对象不存在，请刷新!");
							}else{
								alert("服务器繁忙，稍候重试!");
							}
						}else{
							exception(message);
						}
		    		}
				});
			}
		}
	</script>
	
  </head>
  
  <body style="background:#c7e5ff;">
    <div class="content1" style="width: 100%;">
		  <div class="roundedBox" id="type1" style="width: 95%;">
				<div class="right" style="width: 98%;">
					<form action="<%=request.getContextPath()%>/veh/vehSpxx_initVehPodbSpList.action" method="post" id="esfrom">
						<table class="table1" width="100%" border="0"
							cellpadding="0" cellspacing="0">
							<tr>
								<th class="th1" height="32" colspan="4">
									查询条件
								</th>
							</tr>
							<tr>
								<td class="tds" style="text-align: right;">
									号牌号码&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<input type="text" name="hphm" value="${hphm}" onkeyup="clearspace(this)" onblur="clearallspace(this)" />
								</td>
								<td class="tds" style="text-align: right;">
									号牌种类&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<select name="hpzl" id="hpzl">
										<option value="">请选号牌种类</option>
										<s:iterator var="hp" value="#request.hpzlList">
											<option value='<s:property value="#hp.dmz"/>' 
												<s:if test="#request.hpzl==#hp.dmz">selected</s:if>>
												<s:property value="#hp.dmms1"/>
											</option>
										</s:iterator>
									</select>
								</td>
							</tr>
							<tr>
								<td class="tds" style="text-align: right;">
									车主身份证明号码&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<input type="text" name="czSfzmhm" value="${czSfzmhm}" onkeyup="clearspace(this)" onblur="clearallspace(this)" />
								</td>
								<td class="tds" style="text-align: right;">
									配偶身份证明号码&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<input type="text" name="poSfzmhm" value="${poSfzmhm}" onkeyup="clearspace(this)" onblur="clearallspace(this)" />
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
						<input class="bnt" type="button" value="添加审批" onclick="javascript:editsp('<%=request.getContextPath()%>/veh/vehSpxx_selVehPoSpxx.action?editType=add');" id="excelid" style="cursor:pointer;" />
					</div>
					<table class="datalist" width="100%" border="0"
						cellpadding="0" cellspacing="0">
						<tr class="tr1">
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
								车主身份证明名称
							</th>
							<th>
								车主身份证明号码
							</th>
							<th>
								车主姓名
							</th>
							<th>
								审批时间
							</th>
							<th>
								操作
							</th>
						</tr>
						<s:if test="#request.vehPodbsps.size > 0">
							<s:iterator id="posp" value="#request.vehPodbsps" status="st">
								<tr class="<s:if test="#st.odd == false">altrow</s:if>">
									<td>
										${st.count + (map.currentpage-1) * map.pagesize}
									</td>
									<td>
										${posp.hphm}
									</td>
									<td>
										${posp.hpzl }
									</td>
									<td>
								 		${posp.czSfzmmc }
									</td>
									<td>
								 		${posp.czSfzmhm }
									</td>
									<td>
										${posp.czXm }
									</td>
									<td>
										<fmt:formatDate value="${posp.sprSj}" pattern="yyyy-MM-dd HH:mm:ss" />
									</td>
									<td id="${posp.id}">
										<a href="javascript: editsp('<%=request.getContextPath()%>/veh/vehSpxx_selVehPoSpxx.action?editType=query&id=${posp.id}');">查看</a>
										<c:if test="${posp.sprBmkj == kjbmid}">
											<a href="javascript: delVehPoxx(${posp.id})" target="mainFrame">删除</a>
											<a href="javascript: editsp('<%=request.getContextPath()%>/veh/vehSpxx_selVehPoSpxx.action?editType=edit&id=${posp.id}');">修改</a>
										</c:if>
									</td>
								</tr>
							</s:iterator>
						</s:if>
						<s:else>
							<tr>
								<td colspan="8">
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
