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
		<title>流水管理列表</title>
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
					<form action="<%=request.getContextPath()%>/yanche/lsgl_initYwlsglVehFlowList.action" method="post">
						<table class="table1" width="100%" border="0"
							cellpadding="0" cellspacing="0">
							<tr>
								<th class="th1" height="32" colspan="4">
									查询条件
									<input type="hidden" name="type" value="${type}" />
									<input type="hidden" name="gw" value="${gw}" />
									<input type="hidden" name="ld" value="${ld}" />
								</th>
							</tr>
							<tr>
								<td class="tds" style="text-align: right;">
									流水号&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<input type="text" name="lsh" value="${lsh}" />
								</td>
								<td class="tds" style="text-align: right">
									所有人&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<input type="text" name="syr" value="${syr}" />
								</td>
							</tr>
							<tr>
								<td class="tds" style="text-align: right;">
									号牌号码&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<input type="text" name="hphm" value="${hphm}" />
								</td>
								<td class="tds" style="text-align: right">
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
								流水号
							</th>
							<th>
								所有人
							</th>
							<th>
								号牌号码
							</th>
							<th>
								流水状态
							</th>
							<th>
								操作
							</th>
						</tr>
						<s:if test="#request.ywlsglVehFlowList.size > 0">
							<s:iterator id="flow" value="#request.ywlsglVehFlowList" status="st">
								<tr class="<s:if test="#st.odd == false">altrow</s:if>">
									<td>
										${st.count + (map.currentpage-1) * map.pagesize}
									</td>
									<td>
										${flow.lsh }
									</td>
									<td>
								 		${flow.syr }
									</td>
									<td>
								 		${flow.hphm }
									</td>
									<td>
										${flow.lszt }
									</td>
									<td>
										<s:if test="#request.type == 1">
											<a href="<%=request.getContextPath()%>/yanche/lsgl_updateYwlsglVehFlow.action?ptdo.lsh=${flow.lsh}&type=1&ld=${ld}&gw=${gw}">查看</a>
										</s:if>
										<s:else>
											<a href="<%=request.getContextPath()%>/yanche/lsgl_updateYwlsglVehFlow.action?ptdo.lsh=${flow.lsh}&type=2&ld=${ld}&gw=${gw}">流水管理</a>
										</s:else>
									</td>
								</tr>
							</s:iterator>
						</s:if>
						<s:else>
							<tr>
								<td colspan="7">
									<span style="color: red">没有数据,请输入上面查询条件进行搜索</span>
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
