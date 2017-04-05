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
    <title>抵押登记代办人信息列表</title>
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
		function openpage(userid, type, dbrid){
			if('update' == type){
				art.dialog.open('<%=request.getContextPath()%>/dydj/dydj_initEditYhdbr.action?yhUserId='+userid+'&type='+type+'&yhdbrid='+dbrid, {width:600,height:255,title:'修改银行代办人信息', opacity: 0.4});
			}else{
				art.dialog.open('<%=request.getContextPath()%>/dydj/dydj_initEditYhdbr.action?yhUserId='+userid+'&type='+type, {width:600,height:255,title:'添加银行代办人信息', opacity: 0.4});
			}
			
		}
		
		function delYhdbrInfo(id){
			if(confirm("确定删除吗?")){
				$.ajax({
					cache:false,
					async:false,
					url:'<%=request.getContextPath()%>/dydj/dydj_delYhdbrInfo.action',
					type:'post',
					data:{"id":id},
					dataType:'json',
					error:function(XmlHttpRequest,textStatus, errorThrown){
						exception(XmlHttpRequest.responseText);
						return false;
					},
					success: function(result){
						if(result == "1"){
							alert("删除成功!");
							window.location.reload(); 
						}else{
							alert("删除失败!");
						}
					}
				});
			}
		}
		
		function updateYhdbrInfo(id, flag){
			$.ajax({
				cache:false,
				async:false,
				url:'<%=request.getContextPath()%>/dydj/dydj_updateDydjDbrInfo.action',
				type:'post',
				data:{"id":id, "flag": flag},
				dataType:'json',
				error:function(XmlHttpRequest,textStatus, errorThrown){
					exception(XmlHttpRequest.responseText);
					return false;
				},
				success: function(result){
					if(result == "1"){
						if(flag == '0'){
							alert("启用成功!");
						}else{
							alert("停用成功!");
						}
						
						window.location.reload(); 
					}else{
						alert("操作失败!");
					}
				}
			});
		}
	</script>
	
  </head>
  
  <body style="background:#c7e5ff;">
    <div class="content1" style="width: 100%;">
		  <div class="roundedBox" id="type1" style="width: 95%;">
		  		<div style="width:90%;margin: 0 auto; text-align: right;">
				    <input type="button" name="addBtn" id="addBtn" value="添加"  onclick="javascript:openpage(${yhUserId}, 'add', '');" class="bnt" style="cursor:pointer;"/>
				    <input type="button" name="addBtn" id="addBtn" value="返回" class="bnt" onclick="javascript:history.go(-1);" style="cursor:pointer;"/>
				</div>
				<br/>
				<div class="right" style="width: 90%;">
					<form action="" method="post" id="esfrom">
						<input type="hidden" name="yhUserid" id="yhUserid" value="${yhUserId}"/>
					</form>
					<table class="datalist" width="100%" border="0"
						cellpadding="0" cellspacing="0">
						<tr class="tr1">
							<th>
								序号
							</th>
							<th>
								身份证明号码
							</th>
							<th>
								姓名
							</th>
							<th>
								性别
							</th>
							<th>
								手机号码
							</th>
							<th>
								联系地址
							</th>
							<th>
								录入人
							</th>
							<th>
								录入时间
							</th>
							<th>
								有效期止
							</th>
							<th>
								是否停用
							</th>
							<th>
								操作
							</th>
						</tr>
						<s:if test="#request.dydjYhdbrList.size > 0">
							<s:iterator id="dbr" value="#request.dydjYhdbrList" status="st">
								<tr class="<s:if test="#st.odd == false">altrow</s:if>">
									<td>
										${st.count }
									</td>
									<td>
										${dbr.dbrSfzmhm }
									</td>
									<td>
										${dbr.dbrXm }
									</td>
									<td>
										${dbr.dbrSex }
									</td>
									<td>
										${dbr.dbrSjhm }
									</td>
									<td>
										${dbr.dbrLxdz }
									</td>
									<td>
										${dbr.lrr }
									</td>
									<td>
								 		<fmt:formatDate value="${dbr.lrsj}" pattern="yyyy-MM-dd HH:mm:ss" />
									</td>
									<td>
								 		<fmt:formatDate value="${dbr.yxqz}" pattern="yyyy-MM-dd" />
									</td>
									<td>
								 		<s:if test="#request.dbr.flag == 0">否</s:if>
								 		<s:else><font style="color: red;">是</font></s:else>
									</td>
									<td id="${dbr.id}">
										<s:if test="#request.dbr.flag == 0"><a href="javascript:updateYhdbrInfo(${dbr.id}, 1);">停用</a></s:if>
								 		<s:else><a href="javascript:updateYhdbrInfo(${dbr.id}, 0);">启用</a></s:else>
								 		<a href="javascript:openpage(${yhUserId}, 'update', ${dbr.id});">修改</a>
								 		<a href="javascript:delYhdbrInfo(${dbr.id});">删除</a>
									</td>
								</tr>
							</s:iterator>
						</s:if>
						<s:else>
							<tr>
								<td colspan="10">
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
