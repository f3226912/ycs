<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
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
		<title>嫌疑车字典列表</title>
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
		<script type="text/javascript">
		
			//$(document).ready(function() {
				//var dmlb = ${dmlb};
				
			//	var selDmlbs = document.getElementById("dmlb").options;
				
			//	for(var i = 0; i < selDmlbs.lenth; i ++){
			//		if(selDmlbs[i].value == dmlb){
			//			selDmlbs[i].selected = true;
			//		}
			//	}
				
			//});
		
			function insertUser() {
				window.location.href = "<%=request.getContextPath()%>/xyc/xyccode_insertYcsXycCode.action";
			}
			function deleteOne(id) {
				if(window.confirm('是否要删除选择的字典信息?')){
					$.ajax({
						type:'POST',
						url: '<%=request.getContextPath()%>/xyc/xyccode_deleteYcsXycCode.action',
						data:{YcsXycCodeId:id},//发送的参数
						dataType: 'html',
						success:function(data){
							if(data == 0){
							    alert("删除字典信息成功!");
							    $("#"+id).parents("tr").remove();
							    //$("#productform").submit();
							}else if(data == 1){
								alert("删除字典信息失败!");
							}else{
								alert("系统繁忙,请稍候再试!");
							}
						}
					});
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
		<div class="content1">
			<div class="roundedBox" id="type1">
				<div class="right">
					<form action="<%=request.getContextPath()%>/ycsXycCode/ycsXycCode_initYcsXycCodeList.action" method="post">
						<table class="table1" width="100%" border="0"
							cellpadding="0" cellspacing="0">
							<tr>
								<th class="th1" height="32" colspan="2">
									查询条件<br/>
								</th>
							</tr>
							<tr>
								<td class="tds" style="text-align: right">
									创建日期&nbsp;
								</td>
								<td class="tdl" style="text-align: left">
									开始时间
									<input name="s_date" id="s_date" value="${s_date}" size="10" type="text"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
									结束时间
									<input name="e_date" id="e_date" value="${e_date}" size="10" type="text"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
								</td>
							</tr>
							<tr>
								<td class="tds" colspan="2">
									<div align="center">
										<input class="bnt" type="submit" value="查  询" style="cursor:pointer;" />
										<!-- <input class="bnt" type="reset" value="重  置" style="cursor:pointer;" /> -->
									</div>
								</td>
							</tr>
						</table>
					</form>
					<div style="width: 98%;height: 35px;padding-top: 10px;">
						<input class="bnt" type="button" value="添  加" onclick="javascript:insertUser();" style="cursor:pointer;" />
					</div>
					<table class="datalist" width="100%" border="0"
						cellpadding="0" cellspacing="0">
						<tr class="tr1">
							<th>
								嫌疑代码类别
							</th>
							<th>
								嫌疑代码
							</th>
							<th>
								代码说明
							</th>
							<th>
								备注
							</th>
							<th>
								操作
							</th>
						</tr>
						<s:if test="#request.ycsXycCodeList.size > 0">
							<s:iterator id="ycsCode" value="#request.ycsXycCodeList" status="st">
								<tr class="<s:if test="#st.odd == false">altrow</s:if>">
									<td>
										${ycsCode.dmlb }
									</td>
									<td>
										${ycsCode.dmz }
									</td>
									<td>
										${ycsCode.dmms1 }
									</td>
									<td>
										${ycsCode.bz }
									</td>
									<td>
					    				<a href="<%=request.getContextPath()%>/xyc/xyccode_updateYcsXycCode.action?ycsXycCode.id=${ycsCode.id}">修改</a>
					    				<a href="javascript:void(0);" id="${ycsCode.id}" onclick="javascript:deleteOne(${ycsCode.id});">删除</a>
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
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						style="padding-top: 5px;">
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
