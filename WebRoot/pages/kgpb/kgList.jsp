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
		<title>考官信息列表</title>
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
				window.location.href = '<%=request.getContextPath()%>/kgpb/kgxxb_initAddKgInfo.action';
			}
			
			function subFun(){
				//var lrzt = document.getElementsByName("lrzt");
				//alert(lrzt)
				document.form1.submit();
			}
			
			function updateInfo(id){
				window.location.href = '<%=request.getContextPath()%>/kgpb/kgxxb_initKgUpdate.action?id='+id;
			}
			
			function selectInfo(id){
				window.location.href = '<%=request.getContextPath()%>/kgpb/kgxxb_initSelKg.action?id='+id;
			}
			
			function deleteInfo(id){
				if(confirm("确定要删除该信息？")){
					$.ajax({
						type:'POST',
						url: '<%=request.getContextPath()%>/kgpb/kgxxb_delKgInfo.action',
						data: {'id':id},
						dataType: 'html',
						success:function(data){
							if(data == 0){
							    alert("删除成功!");
							    //location.reload();
							    location.reload();
							}else if(data == 1){
								alert("该考官今后还有排班安排!请先修改该考官状态，等没有排班之后再做删除操作。\n 或者在排班列表中，手动替换该考官今后排班");
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
					<form action="<%=request.getContextPath()%>/kgpb/kgxxb_getKgInfoList.action" id="form1" name="form1" method="post">
						<table class="table1" width="100%" border="0"
							cellpadding="0" cellspacing="0">
							<tr>
								<th class="th1" height="32" colspan="4">
									查询条件
								</th>
							</tr>
							<tr>
								<td class="tds" style="text-align: right;">
									考官警号&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<input type="text" name="kgjh" value="${kgjh}" maxlength="20" />
								</td>
								<td class="tds" style="text-align: right">
									考官姓名&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<input type="text" name="kgxm" value="${kgxm}" maxlength="30" />
								</td>
							</tr>
							<tr>
								<td class="tds" style="text-align: right">
									状态&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<select name="zt" >
										<option value="">===请选择===</option>
										<option value="0" <s:if test='#request.zt == "0"'>selected="selected"</s:if> >正常</option>
										<option value="1" <s:if test='#request.zt == "1"'>selected="selected"</s:if>>休假</option>
										<option value="2" <s:if test='#request.zt == "2"'>selected="selected"</s:if>>固定排班</option>
									</select>
								</td>
								<td class="tds" style="text-align: right;">
									所属部门&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<select name="ssbm" >
										<option value="">===请选择===</option>
										<s:iterator var="kjInfo" value="#request.kjList">
											<option value="<s:property value="#kjInfo[0]" />" <s:if test='#kjInfo[0] == #request.ssbm'>selected="selected"</s:if> ><s:property value="#kjInfo[1]" /></option>
										</s:iterator>
									</select>
									
									<!-- <input type="checkbox" <s:if test='#request.lrzt == "zl0"'>checked="checked"</s:if> name="lrzt" value="zl0"/> 周六上课
									<input type="checkbox" <s:if test='#request.lrzt == "zl1"'>checked="checked"</s:if> name="lrzt" value="zl1"/> 周六休假
									<input type="checkbox" <s:if test='#request.lrzt == "zr0"'>checked="checked"</s:if> name="lrzt" value="zr0"/> 周日上班
									<input type="checkbox" <s:if test='#request.lrzt == "zr1"'>checked="checked"</s:if> name="lrzt" value="zr1"/> 周日休息 -->
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
								警号
							</th>
							<th>
								姓名
							</th>
							<th>
								所属部门
							</th>
							<th>
								状态
							</th>
							<th>
								手机号码
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
										${kcInfo[4] }
									</td>
									<td>
										<s:if test='#kcInfo[5] == "0"'>正常</s:if>
										<s:elseif test='#kcInfo[5] == "1"'>休假</s:elseif>
										<s:else>固定排班</s:else>
									</td>
									<td>
										${kcInfo[6] }
									</td>
									<td>
										${kcInfo[7] }
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
