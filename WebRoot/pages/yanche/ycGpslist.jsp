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
	<title>查验记录信息</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/content.css" />
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
	<script type="text/javascript">
		function exception(exception){
			alert(exception);
		}
		//编辑验车位子GPS信息			
		function editGps(id){
			var uri="<%=request.getContextPath()%>/yanche/yczd_intieditYcGps.action?gpsid="+id;
			var returnValue=window.showModalDialog(uri,null,"dialogHeight:290px;dialogWidth:450px;status:no;scroll:no;center:yes");
			if(returnValue!=null && returnValue!="0" && returnValue!="" && typeof(returnValue)!="undefined"){
				location.reload();
			}
		}
		
		function deleteGps(id){
			if(confirm("删除后不可恢复,确认继续删除?")){
				$.ajax({
					cache:false,
					async:false,
					type:"POST",
					url:"<%=request.getContextPath()%>/yanche/yczd_deleteYcGps.action",
					data:{gpsId:id},
					dataType: 'text',
					success:function(data){
						if(data==0){
							alert("删除成功!");
							window.location.href = '<%=request.getContextPath()%>/yanche/yczd_ycGpsgl.action';
						}else{
							alert("系统繁忙，删除失败,请稍后在试!");
							return;
						}
					}
				});
			}
		}

			
	</script>
	</head>

	<body style="background:#c7e5ff;">
		<div class="content1" style="width: 98%;">
			<div class="roundedBox" id="type1" style="width: 98%;">
				<div class="right" style="width: 95%;">
					<form action="<%=request.getContextPath()%>/yanche/yczd_ycGpsgl.action" method="post">
						<table class="table1" width="100%" border="0"
							cellpadding="0" cellspacing="0">
							<tr>
								<th class="th1" height="32" colspan="4">
									查询条件
								</th>
							</tr>
							<tr>
								<td class="tds" style="text-align: center;">
									机构名称：&nbsp;
									<input type="text" name="jgmc" id="jgmc" value="${request.jgmc}"/>
								</td>
								<td class="tds" style="text-align: center;">
									详细地址：&nbsp;
									<input type="text" name="xxdz" id="xxdz" value="${request.xxdz}"/>
								</td>
							</tr>
							<tr>
								<td class="tds" colspan="4">
									<div align="center">
										<input type="submit" value="查询" class="bnt" />
									</div>
								</td>
							</tr>
						</table>
					</form>					
				<div id="div2">
				<div>
		   		<input type="submit" onclick="editGps('')" class="bnt" style="width: 80px;" value="添加" />
		   		</div>
					<table class="datalist" width="100%" border="0"
						cellpadding="0" cellspacing="0">
						<tr class="tr1">
							<th>编号</th>
							<th>机构名称</th>
							<th>详细地址</th>
							<th>经纬度X</th>
							<th>经纬度Y</th>
							<th>有效距离</th>
							<th>状态</th>
							<th>操作</th>
						</tr>
						<s:if test="#request.ycGpsList.size > 0">
							<s:iterator id="pvp" value="#request.ycGpsList" status="st">
								<tr class="<s:if test="#st.odd == false">altrow</s:if>">
									<td>${pvp.gpsId}</td>
									<td>${pvp.jgmc}</td>
									<td>${pvp.xxdz}</td>
									<td>${pvp.gpsx}</td>
									<td>${pvp.gpsy}</td>
									<td>${pvp.yxjl}</td>
									<td>${pvp.zt=="0"?"无效":"有效"}</td>
									<td>
										<a href="#" onclick="editGps(${pvp.gpsId})">修改</a>
										<a href="#" onclick="deleteGps(${pvp.gpsId})">删除</a>
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
				</div>	
				<table width="100%" border="0" cellspacing="0" cellpadding="0"
						style="padding-top: 5px;">
						<pt:write uri="${map.uri}" pagesize="${map.pagesize}" rscount="${map.rscount}" currentpage="${map.currentpage}"></pt:write>
				</table>
	
		</div>
	</body>
</html>
