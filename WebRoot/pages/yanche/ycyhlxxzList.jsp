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
		<title>验车结果修改列表</title>
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
		
			
			
			//根据下拉框类别值查询信息
			function SelectType(){
				var zdid = $("#bmid").val();
				var userName = $("#userName").val();
				var loginId = $("loginId").val();
				if(zdid==0){
					zdid="";
				}
				$("#sub_form").submit();
			};

			
			function myclear(obj){
				obj.value = obj.value.replace(" ","");
				obj.value = obj.value.replace(" ","");
			}
			
			//【使用性质,车辆类型】初始化编辑页面
			function initEdit(id){
				var loginId = id;
				var uri = "<%=request.getContextPath()%>/yanche/yczd_initEditYhlxxz.action?loginId="+loginId;
				var returnValue=window.showModalDialog(uri,null,"dialogHeight:800px;dialogWidth:1100px;status:no;scroll:yes;center:yes");
				if(returnValue!=null && returnValue!="0" && returnValue!="" && typeof(returnValue)!="undefined"){
					$("#sub_form").submit();
				}
			}
		</script>
	</head>

	<body style="background:#c7e5ff;">
		<div class="content1" style="width: 100%;">
			<div class="roundedBox" id="type1" style="width: 95%;">
				<div class="right" style="width: 98%;">
					<form action="<%=request.getContextPath()%>/yanche/yczd_showYhlxxz.action" id="sub_form" method="post">
						<input type="hidden" id="dmlb" value="${request.dmlb}"/>
						<table class="table1" width="100%" border="0"
							cellpadding="0" cellspacing="0">
							<tr>
								<th class="th1" height="32" colspan="6">查询条件</th>
							</tr>
							<tr>
								<td class="tds" style="text-align: right;">所属部门：</td>
								<td class="tdl" style="text-align: left;">
									<select id="zdid" name="zdid">
							    	<s:if test="#request.yhbmList.size > 1">
							    		<option value="">--请选择--</option>
							    	</s:if>
						            <s:iterator value="#request.yhbmList" id="bmid" >
								        <s:iterator value="#request.bmid" id="d" status="status">
								            <option value="<s:property value="#request.d.key"/>"<s:if test="#request.d.key==#request.zdid">selected="selected"</s:if>>
								            	<s:property value="value"/>
								            </option>
								        </s:iterator>
					            	</s:iterator>
					            	</select>
								</td>
								<td class="tds" style="text-align: right;">登录账号：</td>
								<td class="tdl" style="text-align: left;">
									<input type="text" name="loginId" id="loginId" value="${request.loginId}"/>
								</td>
								<td class="tds" style="text-align: right;">用户名：</td>
								<td class="tdl" style="text-align: left;">
									<input type="text" name="userName" id="userName" value="${request.userName}"/>
								</td>
							</tr>
							<tr>
								<td class="tds" colspan="6">
									<div align="center">
										<input type="button" onclick="javascript:SelectType();" value="查询" class="bnt" />
									</div>
								</td>
							</tr>
						</table>
					</form>		

	<!-- 使用性质,车辆类型--Div1==========================================================-->
				<div id="div1">
					<table class="datalist" width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr class="tr1">
							<th>账号</th>
							<th>用户名</th>
							<th>职位</th>
							<th>部门名称</th>
							<th>车辆类型权限</th>
							<th>使用性质权限</th>
							<th>业务类型权限</th>
							<th>IMEI码</th>
							<th>状态</th>
							<th>操作</th>
						</tr>
						<s:if test="#request.yhlxxzlist.size > 0">
							<s:iterator id="pvp" value="#request.yhlxxzlist" status="st">
								<tr class="<s:if test="#st.odd == false">altrow</s:if>">
									<td>${pvp[0]}</td>
									<td>${pvp[1]}</td>
									<td>${pvp[2]}</td>
									<td>${pvp[3]}</td>
									<td>${pvp[4]}</a></td>
									<td>${pvp[5]}</td>
									<td>${pvp[6]}</td>
									<td>${pvp[7]}</td>
									<td>${pvp[8]}</td>
									<td><a href="#" onclick="initEdit('${pvp[0]}')">修改</a></td>
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
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						style="padding-top: 5px;">
						<pt:write uri="${map.uri}" pagesize="${map.pagesize}" rscount="${map.rscount}" currentpage="${map.currentpage}"></pt:write>
					</table>
				</div>
		</div>
	</body>
</html>
