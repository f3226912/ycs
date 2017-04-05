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
		<title>报废车审批信息查询与统计</title>
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
				var selOption = document.getElementById("bm");
				var selValue = '<s:property value="#request.bm"/>'
				for(var i =0; i < selOption.options.length; i++){
					if(selValue == selOption.options[i].value){
						selOption.options[i].selected = true;
						break;
					}
				}
			});
			
			function tjFun(){
				showModalDialog('<%=request.getContextPath()%>/bfc/bfcsp_bfcSpCountList.action','example04','dialogWidth:600px;dialogHeight:600px;dialogLeft:300px;dialogTop:250px;resizable:no;status:no;')
			}
			
			function ckInfo(xh){
				showModalDialog('<%=request.getContextPath()%>/bfc/bfcsp_getBfcTsspbByXh.action?xh='+xh,'example04','dialogWidth:600px;dialogHeight:400px;dialogLeft:300px;dialogTop:250px;resizable:no;status:no;scroll:no;')
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
					<form action="<%=request.getContextPath()%>/bfc/bfcsp_bfcSelList.action" method="post">
						<table class="table1" width="100%" border="0"
							cellpadding="0" cellspacing="0">
							<tr>
								<th class="th1" height="32" colspan="4">
									查询条件
									<input type="hidden" id="jb" name="jb" value="${jb}" />
								</th>
							</tr>
							<tr>
								<td class="tds" style="text-align: right">
									身份证明号码&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<input type="text" name="sfzmhm" value="${sfzmhm}" />
								</td>
								<td class="tds" style="text-align: right;">
									入库时间
								</td>
								<td class="tdl" style="text-align: left;">
									<input type="text" id="startRksj" name="startRksj" value="${startRksj}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" /> 至 
									<input type="text" id="endRksj" name="endRksj" value="${endRksj}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
								</td>
							</tr>
							<tr>
								<td class="tds" style="text-align: right;">
									部门
								</td>
								<td class="tdl" style="text-align: left;">
									<select name="bm" id="bm" >
										<option value="">===请选择===</option>
										<s:iterator var="bfcSpBm" value="#request.bfcSpBmList" >
											<option value="<s:property value="#bfcSpBm[0]"/>"><s:property value="#bfcSpBm[1]"/></option>
										</s:iterator>
									</select>
									
								</td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td class="tds" colspan="4">
									<div align="center">
										<input class="bnt" type="submit" value="查  询" style="cursor:pointer;" />
										<input class="bnt" type="button" onclick="tjFun()" value="统  计" style="cursor:pointer;" />
									</div>
								</td>
							</tr>
						</table>
					</form>
					<table class="datalist" width="100%" border="0"
						cellpadding="0" cellspacing="0">
						<tr class="tr1">
							<th>
								序号
							</th>
							<th>
								身份证名称
							</th>
							<th>
								身份证明号码
							</th>
							<th>
								审批状态
							</th>
							<th>
								审批时间
							</th>
							<th>
								民警姓名
							</th>
							<th>
								所在部门
							</th>
							<th>
								操作
							</th>
						</tr>
						<s:if test="#request.bfcJbxxbList.size > 0">
							<s:iterator id="bfcxx" value="#request.bfcJbxxbList" status="st">
								<tr class="<s:if test="#st.odd == false">altrow</s:if>">
									<td>
										${st.count}
									</td>
									<td>
										<c:if test="${bfcxx.sfzmmc == 'A'}">二代居民身份证</c:if>
										<c:if test="${bfcxx.sfzmmc == 'B'}">组织机构代码证书</c:if>
										<c:if test="${bfcxx.sfzmmc == 'C'}">军官证</c:if>
										<c:if test="${bfcxx.sfzmmc == 'D'}">士兵证</c:if>
										<c:if test="${bfcxx.sfzmmc == 'E'}">军官离退休证</c:if>
										<c:if test="${bfcxx.sfzmmc == 'F'}">境外人员身份证明</c:if>
										<c:if test="${bfcxx.sfzmmc == 'G'}">外交人员身份证明</c:if>
										<c:if test="${bfcxx.sfzmmc == 'H'}">居民户口薄</c:if>
										<c:if test="${bfcxx.sfzmmc == 'J'}">单位注销证明</c:if>
										<c:if test="${bfcxx.sfzmmc == 'K'}">居住暂住证明</c:if>
										<c:if test="${bfcxx.sfzmmc == 'L'}">驻华机构证明</c:if>
										<c:if test="${bfcxx.sfzmmc == 'M'}">临时居民身份证</c:if>
									</td>
									<td>
										${bfcxx.sfzmhm }
									</td>
									<td>
								 		<s:if test='#request.bfcxx.spzt == "1"'>
											科级待审批
										</s:if>
										<s:elseif test='#request.bfcxx.spzt == "2"'>
											<font color="red">科级审批不通过</font>
										</s:elseif>
										<s:elseif test='#request.bfcxx.spzt == "3"'>
											处级待审批
										</s:elseif>
										<s:elseif test='#request.bfcxx.spzt == "4"'>
											<font color="red">处级审批不通过</font>
										</s:elseif>
										<s:elseif test='#request.bfcxx.spzt == "5"'>
											<font color="green">科级审批通过</font>
										</s:elseif>
										<s:elseif test='#request.bfcxx.spzt == "6"'>
											<font color="green">处级审批通过</font>
										</s:elseif>
									</td>
									<td>
								 		<s:date name="#bfcxx.MSpsj" format="yyyy-MM-dd HH:mm:ss"/>
									</td>
									<td>
										${bfcxx.MSpxm }
									</td>
									<td>
										${bfcxx.MSpbmmc }
									</td>
									<td>
										<a href="javascript:void(0);" id="${bfcxx.xh}" onclick="ckInfo(${bfcxx.xh})">查看</a>
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
