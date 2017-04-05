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
		<title>驾驶人流水信息列表</title>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/content.css" />
		<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/jquery-1.8.2.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/public.js"></script>
		<script type="text/javascript">
			$(document).ready(function(){
				var exportData = '${request.exportData}';
				if(exportData != null && exportData != ""){
					alert(exportData);
				}
			});
			function check(){
				var s_date = $("#s_date").val();
				var e_date = $("#e_date").val();
				if(null == s_date || "" == s_date){
					alert("请选择开始日期");
					return false;
				}
				if(null == e_date || "" == e_date){
					alert("请选择结束日期");
					return false;
				}
				$("#esfrom").attr("action", "<%=request.getContextPath()%>/yujing/yujing_initYujingList.action");
				$("#esfrom").submit();
			}
			
			function exports(){
				var s_date = $("#s_date").val();
				var e_date = $("#e_date").val();
				if(null == s_date || "" == s_date){
					alert("请选择开始日期");
					return false;
				}
				if(null == e_date || "" == e_date){
					alert("请选择结束日期");
					return false;
				}
				$("#esfrom").attr("action", "<%=request.getContextPath()%>/yujing/yujing_yujingListExport.action");
				$("#esfrom").submit();
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
					<form action="<%=request.getContextPath()%>/yujing/yujing_initYujingList.action" method="post" id="esfrom">
						<table class="table1" width="100%" border="0"
							cellpadding="0" cellspacing="0">
							<tr>
								<th class="th1" height="32" colspan="4">
									查询条件
								</th>
							</tr>
							<tr>
								<td class="tds" style="text-align: right; width: 15%;">
									身份证明号码&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<input name="sfzmhm" id="sfzmhm" value="${sfzmhm}" size="18" type="text" onkeyup="clearspace(this)" onblur="clearallspace(this)"/>
								</td>
								<td class="tds" style="text-align: right; width: 15%;">
									开始日期&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									起
									<input name="s_date" id="s_date" value="${s_date}" size="18" type="text"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
									止
									<input name="e_date" id="e_date" value="${e_date}" size="18" type="text"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
								</td>
							</tr>
							<tr>
								<td class="tds" colspan="4">
									<div align="center">
										<input class="bnt" type="button" value="查  询" onclick="javascript:check();" style="cursor:pointer;" />
									</div>
								</td>
							</tr>
						</table>
					</form>
					<div style="width: 98%;height: 35px;padding-top: 10px;">
						<input class="bnt" type="button" value="导出Excel" onclick="javascript:exports();" id="excelid" style="cursor:pointer;" />
					</div>
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
								身份证明号码
							</th>
							<th>
								档案编号
							</th>
							<th>
								姓名
							</th>
							<th>
								开始时间
							</th>
							<th>
								业务类型
							</th>
							<th>
								业务办理部门
							</th>
						</tr>
						<s:if test="#request.esDrvFlowList.size > 0">
							<s:iterator id="esdrvflow" value="#request.esDrvFlowList" status="st">
								<tr class="<s:if test="#st.odd == false">altrow</s:if>">
									<td>
										${st.count + (map.currentpage-1) * map.pagesize}
									</td>
									<td>
										${esdrvflow.lsh }
									</td>
									<td>
										${esdrvflow.sfzmhm }
									</td>
									<td>
								 		${esdrvflow.dabh }
									</td>
									<td>
								 		${esdrvflow.xm }
									</td>
									<td>
										${esdrvflow.kssj }
									</td>
									<td>
										${esdrvflow.ywlx }
									</td>
									<td>
										${esdrvflow.glbm }
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
