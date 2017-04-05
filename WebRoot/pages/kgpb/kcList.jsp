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
		<title>考场信息列表</title>
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
				window.location.href = '<%=request.getContextPath()%>/kgpb/kcxxb_initAddKcInfo.action';
			}
			
			function subFun(){
				//var lrzt = document.getElementsByName("lrzt");
				//alert(lrzt)
				document.form1.submit();
			}
			
			function updateInfo(id){
				window.location.href = '<%=request.getContextPath()%>/kgpb/kcxxb_initKcUpdate.action?id='+id;
			}
			
			function selectInfo(id){
				window.location.href = '<%=request.getContextPath()%>/kgpb/kcxxb_initSelKc.action?id='+id;
			}
			
			function deleteInfo(id){
				if(confirm("确定要删除该信息？")){
					$.ajax({
						type:'POST',
						url: '<%=request.getContextPath()%>/kgpb/kcxxb_delKcInfo.action',
						data: {'id':id},
						dataType: 'html',
						success:function(data){
							if(data == 0){
							    alert("删除成功!");
							    //location.reload();
							    location.reload();
							}else if(data == 1){
								alert("该考场今后还有排班安排!请先停用该考场，等没有排班之后再做删除操作。");
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
					<form action="<%=request.getContextPath()%>/kgpb/kcxxb_getKcInfoList.action" id="form1" name="form1" method="post">
						<table class="table1" width="100%" border="0"
							cellpadding="0" cellspacing="0">
							<tr>
								<th class="th1" height="32" colspan="4">
									查询条件
								</th>
							</tr>
							<tr>
								<td class="tds" style="text-align: right;">
									考场编号&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<input type="text" name="kcbh" value="${kcbh}" maxlength="20" />
								</td>
								<td class="tds" style="text-align: right">
									考场名称&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<input type="text" name="kcmc" value="${kcmc}" maxlength="30" />
								</td>
							</tr>
							<tr>
								<td class="tds" style="text-align: right">
									考场状态&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<select name="zt" >
										<option value="">===请选择===</option>
										<option value="0" <s:if test='#request.zt == "0"'>selected="selected"</s:if> >正常</option>
										<option value="1" <s:if test='#request.zt == "1"'>selected="selected"</s:if>>停用</option>
									</select>
								</td>
								<td class="tds" style="text-align: right;">
									周六、日状态;
								</td>
								<td class="tdl" style="text-align: left;">
									
									<select name="lrzt" >
										<option value="">===请选择===</option>
										<option value="zl0" <s:if test='#request.lrzt == "zl0"'>selected="selected"</s:if> >周六上班</option>
										<option value="zl1" <s:if test='#request.lrzt == "zl1"'>selected="selected"</s:if>>周六休息</option>
										<option value="zr0" <s:if test='#request.lrzt == "zr0"'>selected="selected"</s:if> >周日上班</option>
										<option value="zr1" <s:if test='#request.lrzt == "zr1"'>selected="selected"</s:if>>周日休息</option>
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
								考场编号
							</th>
							<th>
								考场名称
							</th>
							<th>
								考场状态
							</th>
							<th>
								周六状态
							</th>
							<th>
								周日状态
							</th>
							<th>
								考官人数
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
										<s:if test='#kcInfo[5] == "0"'>正常</s:if>
										<s:else>停用</s:else>
									</td>
									<td>
										<s:if test='#kcInfo[3] == "0"'>上班</s:if>
										<s:else>休假</s:else>
									</td>
									<td>
										<s:if test='#kcInfo[4] == "0"'>上班</s:if>
										<s:else>休假</s:else>
									</td>
									<td>
										${kcInfo[6] }
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
		<div id="sfyxdivid"
			style="display:none;width:450px;height: 50px;">
			<table width="90%" border="0" cellpadding="0" cellspacing="0" class="edittable">
				<tr class="tr1">
					<td style="text-align:right;width:40%;">
						是否有效：
					</td>
					<td>
						<input type="radio" name="zt" value="0" checked="checked" />有效&nbsp;&nbsp;<input type="radio" name="zt" value="1" />无效
					</td>
				</tr>
				<tr class="tr1">
					<td style="text-align:right;width:40%;">
						备注：
					</td>
					<td>
						<input type="text" id="bz" size="40" />
					</td>
				</tr>
			</table>
		</div>
	</body>
</html>
