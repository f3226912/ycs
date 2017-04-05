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
		<title>车行机构帐户信息列表</title>
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
			$(document).ready(function(){
				
			});
			
			function insertPdasmycChbase() {
				window.location.href = "<%=request.getContextPath()%>/yanche/chbase_insertPdasmycChbase.action";
			}
			function deleteOne(id) {
				if(window.confirm('是否要删除选择的车行机构帐户信息?')){
					$.ajax({
						type:'GET',
						url: '<%=request.getContextPath()%>/yanche/chbase_deletePdasmycChbase.action',
						data:{pdasmycChbaseId:id},//发送的参数
						dataType: 'html',
						success:function(data){
							if(data == 0){
							    alert("删除车行机构帐户信息成功!");
							    $("#"+id).parents("tr").remove();
							}else if(data == 1){
								alert("删除车行机构帐户信息失败!");
							}else{
								alert("系统繁忙,请稍候再试!");
							}
						}
					});
				}
			}
			
			function windowopen(id,ywlx){
				var info = window.open('<%=request.getContextPath()%>/yanche/chbase_initPdasmycChbase.action?pdasmycChbase.id=' + id + '&ywlx=' + ywlx,'info','width='+(window.screen.availWidth-10)+',height='+(window.screen.availHeight-30)+ ',top=0,left=0,toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=yes');
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
		<div class="content1" style="width: 100%;">
			<div class="roundedBox" id="type1" style="width: 95%;">
				<div class="right" style="width: 90%;">
					<form action="<%=request.getContextPath()%>/yanche/chbase_initPdasmycChbaseList.action" method="post">
						<table class="table1" width="100%" border="0"
							cellpadding="0" cellspacing="0">
							<tr>
								<th class="th1" height="32" colspan="4">
									查询条件
									<input type="hidden" name="chatype" value="${chatype}" />
								</th>
							</tr>
							<tr>
								<td class="tds" style="text-align: right;">
									机构帐户&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<input type="text" name="chid" value="${chid}" />
								</td>
								<td class="tds" style="text-align: right">
									机构名称&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<input type="text" name="chmc" value="${chmc}" />
								</td>
							</tr>
							<tr>
								<td class="tds" style="text-align: right;">
									机构代码&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<input type="text" name="zzjgdm" value="${zzjgdm}" />
								</td>
								<td class="tds" style="text-align: right">
									机构类型&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<input type="text" name="chjglx" value="${chjglx}" />
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
						<input class="bnt" type="button" value="添  加" onclick="javascript:insertPdasmycChbase();" style="cursor:pointer;" />
					</div>
					<table class="datalist" width="100%" border="0"
						cellpadding="0" cellspacing="0">
						<tr class="tr1">
							<th>
								序号
							</th>
							<th>
								机构帐户
							</th>
							<th>
								机构名称
							</th>
							<th>
								机构类型
							</th>
							<th>
								机构代码
							</th>
							<th>
								状态
							</th>
							<th>
								操作
							</th>
						</tr>
						<s:if test="#request.pdasmycChbaseList.size > 0">
							<s:iterator id="chbase" value="#request.pdasmycChbaseList" status="st">
								<tr class="<s:if test="#st.odd == false">altrow</s:if>">
									<td>
										${st.count + (map.currentpage-1) * map.pagesize}
									</td>
									<td>
										${chbase.chid }
									</td>
									<td>
								 		${chbase.chmc }
									</td>
									<td>
								 		<c:if test="${chbase.chjglx == '0' }">二手车车行</c:if>
										<c:if test="${chbase.chjglx == '1' }">新车车行</c:if>
									</td>
									<td>
										${chbase.zzjgdm }
									</td>
									<td>
										<c:if test="${chbase.flag == 'T' }">正常</c:if>
										<c:if test="${chbase.flag == 'F' }">停用</c:if>
									</td>
									<td id="${chbase.id }">
										<a href="javascript:void(0);" onclick="javascript:windowopen(${chbase.id});">查看</a>
										<a href="<%=request.getContextPath()%>/yanche/chbase_updatePdasmycChbase.action?pdasmycChbase.id=${chbase.id}">修改</a>
										<a href="javascript:void(0);" id="${chbase.id}" onclick="javascript:deleteOne(${chbase.id});">删除</a>
									</td>
								</tr>
							</s:iterator>
						</s:if>
						<s:else>
							<tr>
								<td colspan="7">
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
