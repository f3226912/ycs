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
		<title>报废车信息查询与统计</title>
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
			
			function ckInfo(xh){
				showModalDialog('<%=request.getContextPath()%>/bfc/bfjg_getBfcJgskbInfo.action?xh='+xh,'example04','dialogWidth:600px;dialogHeight:400px;dialogLeft:300px;dialogTop:250px;resizable:no;status:no;scroll:no;')
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
					<form action="<%=request.getContextPath()%>/bfc/bfjg_getBfcJgskbList.action" method="post">
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
									<input type="text" id="sfzmhm" name="sfzmhm" value="${sfzmhm}" />
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
								<td class="tds" style="text-align: right">
									是否有效&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<select id="sfyx" name="sfyx" >
										<option value="" <s:if test='#request.sfyx == ""'>selected="selected"</s:if>>===请选择===</option>
										<option value="0" <s:if test='#request.sfyx == "0"'>selected="selected"</s:if>>有效</option>
										<option value="1" <s:if test='#request.sfyx == "1"'>selected="selected"</s:if>>无效</option>
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
										<input class="bnt" type="submit" value="查  询" style="cursor:pointer;" />
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
								号牌号码
							</th>
							<th>
								号牌种类
							</th>
							<th>
								身份证明号码
							</th>
							<th>
								所有人
							</th>
							<th>
								车辆识别代号
							</th>
							<th>
								入库时间
							</th>
							<th>
								是否有效
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
										${bfcxx[1] }
									</td>
									<td>
										${bfcxx[2] }
									</td>
									<td>
										${bfcxx[3] }
									</td>
									<td>
								 		${bfcxx[4] }
									</td>
									<td>
								 		${bfcxx[5] }
									</td>
									<td>
										${bfcxx[7] }
									</td>
									<td>
										${bfcxx[8] }
									</td>
									<td>
										<a href="javascript:void(0);" id="${bfcxx[0] }" onclick="ckInfo(${bfcxx[0] })">查看</a>
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
