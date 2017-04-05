<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="com.ycszh.common.PageTag" prefix="pt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<title>纯电动车辆型号与电机型号管理</title>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/content.css" />
		<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/jquery-1.8.2.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/public.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/artDialog4.1.6/jquery.artDialog.source.js?skin=chrome"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/artDialog4.1.6/plugins/iframeTools.source.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript">
			var currentpage = '${map.currentpage}';
			function check(){
				$("#esfrom").attr("action", "<%=request.getContextPath()%>/ddc/ddc_getvehTypeElectroList.action");
				$("#esfrom").submit();
			}
			
			function openpage(){
				art.dialog.open('<%=request.getContextPath()%>/pages/ddc/editVehtypeElectro.jsp', {width:600,height:380,title:'添加电动车型信息', opacity: 0.4});
			}
			
			function openUpdatePage(xh){
				art.dialog.open('<%=request.getContextPath()%>/ddc/ddc_initVehtypeElectro.action?editType=update&xh='+xh, {width:600,height:380,title:'修改本地用户信息', opacity: 0.4});
			}
			
			function openDetailPage(xh){
				art.dialog.open('<%=request.getContextPath()%>/ddc/ddc_initVehtypeElectro.action?editType=query&xh='+xh, {width:600,height:380,title:'查看本地用户信息', opacity: 0.4});
			}
			
			function delVehtypeElectro(xh){
				if(confirm("确定要删除吗?")){
					$.ajax({
						cache:false,
						async:false,
						url:'<%=request.getContextPath()%>/ddc/ddc_delVehtypeElectro.action',
						type:'post',
						data:{"xh":xh},
						dataType:'json',
						error:function(XmlHttpRequest,textStatus, errorThrown){
					   		alert("编辑角色信息失败!");
							exception(XmlHttpRequest.responseText);
							return false;
						},
						success: function(result){
							if(result == "1"){
								alert("删除成功!");
								$("#esfrom").attr("action", "<%=request.getContextPath()%>/ddc/ddc_getvehTypeElectroList.action?currentpage="+currentpage);
								$("#esfrom").submit();
							}else{
								alert("删除失败!");
							}
						}
					});
				}
			}
			
			function exception(content){
				art.dialog({
					width:'50%',
				    content: content,
				    title: '系统异常',
				    cancelVal: '关闭',
	    			cancel: true,
	    			lock: true,
				    opacity: 0.87,
				    icon: 'error'
				});
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
				<div class="right" style="width: 99%;">
					<form action="<%=request.getContextPath()%>/ddc/ddc_getvehTypeElectroList.action" method="post" id="esfrom">
						<table class="table1" width="100%" border="0"
							cellpadding="0" cellspacing="0">
							<tr>
								<th class="th1" height="32" colspan="4">
									查询条件
								</th>
							</tr>
							<tr>
								<td class="tds" style="text-align: right; width: 15%;">
									车辆品牌：&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<input type="text" id="clpp" name="clpp" onkeyup="clearspace(this)" onblur="clearallspace(this)"  value="${clpp}" style="width: 100px;"/>
								</td>
								<td class="tds" style="text-align: right; width: 15%;">
									车辆型号：&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<input type="text" id="clxh" name="clxh" onkeyup="clearspace(this)" onblur="clearallspace(this)"  value="${clxh}" style="width: 100px;"/>
								</td>
							</tr>
							<tr>
								<td class="tds" style="text-align: right; width: 15%;">
									电机型号：&nbsp;
								</td>
								<td class="tdl" colspan="3" style="text-align: left;">
									<input type="text" id="djxh" name="djxh" onkeyup="clearspace(this)" onblur="clearallspace(this)"  value="${djxh}" style="width: 100px;"/>
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
					<div style="width: 98%;height: 35px;">
						<input class="bnt" type="button" value="添加信息" onclick="javascript:openpage();" id="excelid" style="cursor:pointer;" />
					</div>
					<table class="datalist" width="100%" border="0"
						cellpadding="0" cellspacing="0">
						<tr class="tr1">
							<th>
								序号
							</th>
							<th>
								车辆品牌
							</th>
							<th>
								车辆型号
							</th>
							<th>
								电机型号
							</th>
							<th>
								状态
							</th>
							<th>
								录入人
							</th>
							<th>
								录入人部门
							</th>
							<th>
								录入时间
							</th>
							<th>
								操作
							</th>
						</tr>
						<s:if test="#request.eleCtroList.size > 0">
							<s:iterator id="ele"  value="#request.eleCtroList" status="st">
								<tr class="<s:if test="#st.odd == false">altrow</s:if>">
									<td>
										${st.count + (map.currentpage-1) * map.pagesize}
									</td>
									<td>
										${ele.clpp }&nbsp;
									</td>
									<td>
										${ele.clxh}&nbsp;
									</td>
									<td>
										${ele.djxh}&nbsp;
								 		
									</td>
									<td>
										<s:if test='#request.ele.zt == "T"'>
											启用
										</s:if>
										<s:else>
											停用
										</s:else>&nbsp;
									</td>
									<td>
								 		${ele.lrrxm}&nbsp;
									</td>
									<td>
								 		${ele.lrrbm}&nbsp;
									</td>
									<td>
								 		<s:date name="#request.ele.lrsj" format="yyyy-MM-dd HH:mm:ss"/>
									</td>
									<td>
										<s:if test="#request.ele.lrrkjbm == #request.kjbmid">
											<a href="javascript: javascript:openUpdatePage(${ele.xh});">修改</a>
										</s:if>
								 		<a href="javascript: javascript:openDetailPage(${ele.xh});">查看</a>
									</td>
								</tr>
							</s:iterator>
						</s:if>
						<s:else>
							<tr>
								<td colspan="9">
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
