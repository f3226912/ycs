<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="com.ycszh.common.PageTagLoading" prefix="pt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<title>机动车预警信息列表</title>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/content.css" />
		<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/jquery-1.8.2.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/public.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/artDialog4.1.6/jquery.artDialog.source.js?skin=chrome"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/artDialog4.1.6/plugins/iframeTools.source.js"></script>
		<script type="text/javascript">
			var chuli;
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
				var imgurl = '<%=request.getContextPath()%>/artDialog4.1.6/skins/icons/loading2.gif';
				chuli = art.dialog({
				    content: '<div style=\"text-align:center; text-indent:-999em; overflow:hidden; background:url('+imgurl+') no-repeat center center;\"><span>loading..</span></div><br/>数据加载中,请稍候.....',
				    title: '数据加载中',
					lock: true,
				    opacity: 0.4
				});
				$("#esfrom").attr("action", "<%=request.getContextPath()%>/yujing/yujing_initVehYujingList.action");
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
				var imgurl = '<%=request.getContextPath()%>/artDialog4.1.6/skins/icons/loading2.gif';
				chuli = art.dialog({
				    content: '<div style=\"text-align:center; text-indent:-999em; overflow:hidden; background:url('+imgurl+') no-repeat center center;\"><span>loading..</span></div><br/>数据加载中,请稍候.....',
				    title: '数据加载中',
					lock: true,
				    opacity: 0.4
				});
				$("#esfrom").attr("action", "<%=request.getContextPath()%>/yujing/yujing_yujingVehListExport.action");
				$("#esfrom").submit();
			}
			
			function closechuli(){
				chuli.close();
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
					<form action="<%=request.getContextPath()%>/yujing/yujing_initVehYujingList.action" method="post" id="esfrom">
						<table class="table1" width="100%" border="0"
							cellpadding="0" cellspacing="0">
							<tr>
								<th class="th1" height="32" colspan="4">
									查询条件
								</th>
							</tr>
							<tr>
								<td class="tds" style="text-align: right; width: 15%;">
									流水号&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<input name="lsh" id="lsh" value="${lsh}" size="18" type="text" onkeyup="clearspace(this)" onblur="clearallspace(this)"/>
								</td>
								<td class="tds" style="text-align: right; width: 15%;">
									业务类型&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<select name="ywlx" id="ywlx_dmz" style="width: 130px;">
										<option value="">请选择业务类型</option>
										<option value="A:A" <s:if test="#request.ywlx=='A:A'">selected</s:if>>注册登记</option>
										<option value="B:B" <s:if test="#request.ywlx=='B:B'">selected</s:if>>转移登记(市内过户)</option>
										<option value="B:C" <s:if test="#request.ywlx=='B:C'">selected</s:if>>转移登记(市外过户)</option>
										<s:iterator var="lx" value="#request.ywlxList">
											<option value='<s:property value="#lx.dmz"/>' 
												<s:if test="#request.ywlx==#lx.dmz">selected</s:if>>
												<s:property value="#lx.dmms1"/>
											</option>
										</s:iterator>
									</select>
								</td>
							</tr>
							<tr>
								<td class="tds" style="text-align: right; width: 15%;">
									号牌号码&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<input name="hphm" id="hphm" value="${hphm}" size="18" type="text" onkeyup="clearspace(this)" onblur="clearallspace(this)"/>
								</td>
								<td class="tds" style="text-align: right; width: 15%;">
									号牌种类&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<select name="hpzl" id="hpzl_dmz" style="width: 130px;">
										<option value="">请选号牌种类</option>
										<s:iterator var="hp" value="#request.hpzlList">
											<option value='<s:property value="#hp.dmz"/>' 
												<s:if test="#request.hpzl==#hp.dmz">selected</s:if>>
												<s:property value="#hp.dmms1"/>
											</option>
										</s:iterator>
									</select>
								</td>
							</tr>
							<tr>
								<td class="tds" style="text-align: right; width: 15%;">
									开始日期&nbsp;
								</td>
								<td class="tdl" style="text-align: left;" colspan="3">
									起
									<input name="s_date" id="s_date" value="${s_date}" size="18" type="text"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
									止
									<input name="e_date" id="e_date" value="${e_date}" size="18" type="text"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
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
								车牌号码
							</th>
							<th>
								车牌种类
							</th>
							<th>
								所有人
							</th>
							<th>
								申请日期
							</th>
							<th>
								业务类型
							</th>
							<th>
								管理部门
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
										${esdrvflow.hphm }
									</td>
									<td>
										${esdrvflow.hpzl }
									</td>
									<td>
								 		${esdrvflow.syr }
									</td>
									<td>
										${esdrvflow.sqrq }
									</td>
									<td>
										${esdrvflow.ywlx }
									</td>
									<td>
										${esdrvflow.ywblbm }
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
