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
		<title>特殊日期信息列表</title>
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
		<script type="text/javascript"
	src="<%=request.getContextPath()%>/artDialog4.1.6/jquery.artDialog.source.js?skin=chrome"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/artDialog4.1.6/plugins/iframeTools.source.js"></script>
		<script type="text/javascript">
			$(document).ready(function(){
				
			});
			
			function addFun(){
				window.location.href = '<%=request.getContextPath()%>/kgpb/tsrqb_initAddTsrqb.action';
			}
			
			function subFun(){
				//var lrzt = document.getElementsByName("lrzt");
				//alert(lrzt)
				document.form1.submit();
			}
			
			function updateInfo(id){
				window.location.href = '<%=request.getContextPath()%>/kgpb/tsrqb_initTsrqbUpdate.action?id='+id;
			}
			
			function selectInfo(id){
				window.location.href = '<%=request.getContextPath()%>/kgpb/tsrqb_initSelTsrqb.action?id='+id;
			}
			
			function deleteInfo(id){
				if(confirm("确定要删除该信息？")){
					$.ajax({
						type:'POST',
						url: '<%=request.getContextPath()%>/kgpb/tsrqb_delTsrqb.action',
						data: {'id':id},
						dataType: 'html',
						success:function(data){
							if(data == 0){
							    alert("删除成功!");
							    //location.reload();
							    location.reload();
							}else if(data == 1){
								alert("已过期的日期或者已排班的日期无法删除！");
							}else{
								alert(data);
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
		<div class="content1" style="width: 100%;">
			<div class="roundedBox" id="type1" style="width: 95%;">
				<div class="right" style="width: 90%;">
					<form action="<%=request.getContextPath()%>/kgpb/tsrqb_getTsrqbList.action" id="form1" name="form1" method="post">
						<table class="table1" width="100%" border="0"
							cellpadding="0" cellspacing="0">
							<tr>
								<th class="th1" height="32" colspan="4">
									查询条件
								</th>
							</tr>
							<tr>
								<td class="tds" style="text-align: right;">
									考场&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<s:select list="#request.kcxxbList" theme="simple"
										id="kcid" headerKey="" headerValue="---请选择---"
										listKey="kcbh" listValue="kcmc" name="kcid"
										value="#request.kcid"></s:select>
								</td>
								<td class="tds" style="text-align: right">
									特殊日期&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<input type="text" name="tsrq" value="${tsrq}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" maxlength="30" />
								</td>
							</tr>
							<tr>
								<td class="tds" style="text-align: right">
									类型&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<select name="type" >
										<option value="">===请选择===</option>
										<option value="1" <s:if test='#request.type == "1"'>selected="selected"</s:if> >上班</option>
										<option value="2" <s:if test='#request.type == "2"'>selected="selected"</s:if>>休假</option>
									</select>
								</td>
								<td class="tds" style="text-align: right;">
									&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									&nbsp;
								</td>
							</tr>
							<tr>
								<td class="tds" colspan="4">
									<div align="center">
										<input class="bnt" type="button" onclick="subFun()" value="查  询" style="cursor:pointer;" />
									</div>
								</td>
							</tr>
						</table>
					</form>
					<input class="bnt" type="button" onclick="addFun()" value="新  增" style="cursor:pointer; margin-bottom: 5px;" />
					<table class="datalist" width="100%" border="0"
						cellpadding="0" cellspacing="0">
						<tr class="tr1">
							<th>
								考场名称
							</th>
							<th>
								特殊日期
							</th>
							<th>
								类型
							</th>
							<th>
								备注
							</th>
							<th>
								操作
							</th>
						</tr>
						<s:if test="#request.kcInfoList.size > 0">
							<s:iterator id="kcInfo" value="#request.kcInfoList" status="st">
								<tr class="<s:if test="#st.odd == false">altrow</s:if>">
									<td>
										${kcInfo[1] }
									</td>
									<td>
										${kcInfo[2] }
									</td>
									<td>
										<s:if test='#kcInfo[3] == "1"'>上班</s:if>
										<s:else>休假</s:else>
									</td>
									<td>
										${kcInfo[4] }
									</td>
									<td>
										<a href="javascript:selectInfo(${kcInfo[0]});">查看</a>
										<a href="javascript:updateInfo(${kcInfo[0]});">修改</a>
										<a href="javascript:deleteInfo(${kcInfo[0]});">删除</a>
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
