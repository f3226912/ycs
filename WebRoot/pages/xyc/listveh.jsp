<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="com.ycszh.common.PageTag" prefix="pt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<title>嫌疑车列表</title>
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
				var hpzl = '${hpzl}';
				$("#hpzl").val(hpzl);
			});
			
			function insertUser() {
				window.location.href = "<%=request.getContextPath()%>/xyc/xycveh_insertYcsXycVeh.action";
			}
			
			function deleteOne(id) {
				if(window.confirm('是否要删除选择的嫌疑车信息?')){
					$.ajax({
						type:'POST',
						url: '<%=request.getContextPath()%>/xyc/xycveh_deleteYcsXycVeh.action',
						data:{ycsXycVehId:id},//发送的参数
						dataType: 'html',
						success:function(data){
							if(data == 0){
							    alert("删除嫌疑车信息成功!");
							    $("#"+id).parents("tr").remove();
							    //$("#productform").submit();
							}else if(data == 1){
								alert("删除嫌疑车信息失败!");
							}else{
								alert("系统繁忙,请稍候再试!");
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
		<div class="content1">
			<div class="roundedBox" id="type1">
				<div class="right">
					<form action="<%=request.getContextPath()%>/xyc/xycveh_initYcsXycVehList.action" method="post">
						<table class="table1" width="100%" border="0"
							cellpadding="0" cellspacing="0">
							<tr>
								<th class="th1" height="32" colspan="4">
									查询条件
								</th>
							</tr>
							<tr>
								<td class="tds" style="text-align: right">
									号牌号码	&nbsp;
								</td>
								<td class="tdl" style="text-align: left">
									<input name="hphm" id="hphm" value="${hphm}" type="text" />
								</td>
								<td class="tds" style="text-align: right">
									号牌种类&nbsp;
								</td>
								<td class="tdl" style="text-align: left">
									<select name="hpzl" id="hpzl">
										<option value="">---请选择---</option>
										<s:iterator id="hpzlss" value="#request.esVehCodeList" status="st">
											<option value="${hpzlss[0]}">${hpzlss[1]}</option>
										</s:iterator>
									</select>
								</td>
							</tr>
							<tr>
								<td class="tds" style="text-align: right">
									车辆识别代号&nbsp;
								</td>
								<td class="tdl" style="text-align: left">
									<input type="text" name="clsbdh" id="clsbdh" value="${clsbdh}" />
								</td>
								<td class="tds" style="text-align: right">
									身份证明号码&nbsp;
								</td>
								<td class="tdl" style="text-align: left">
									<input type="text" name="sfzmhm" id="sfzmhm" value="${sfzmhm}" />
								</td>
							</tr>
							<tr>
								<td class="tds" style="text-align: right">
									车辆型号	&nbsp;
								</td>
								<td class="tdl" style="text-align: left">
									<input name="clxhs" id="clxhs" value="${clxhs}" type="text" />
								</td>
								<td class="tds" style="text-align: right">
									中文品牌型号	&nbsp;
								</td>
								<td class="tdl" style="text-align: left">
									<input name="zwppxhs" id="zwppxhs" value="${zwppxhs}" type="text" />
								</td>
							</tr>
							<tr>
								<td class="tds" style="text-align: right">
									流水号	&nbsp;
								</td>
								<td class="tdl" style="text-align: left">
									<input name="lsh" id="lsh" value="${lsh}" type="text" />
								</td>
								<td class="tds" style="text-align: right">
									创建时间&nbsp;
								</td>
								<td class="tdl" style="text-align: left">
									开始时间
									<input name="s_date" id="s_date" value="${s_date}" size="10" type="text"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
									结束时间
									<input name="e_date" id="e_date" value="${e_date}" size="10" type="text"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
								</td>
							</tr>
							<tr>
								<td class="tds" colspan="4">
									<div align="center">
										<input class="bnt" type="submit" value="查  询" style="cursor:pointer;" />
										<!-- <input class="bnt" type="reset" value="重  置" style="cursor:pointer;" /> -->
									</div>
								</td>
							</tr>
						</table>
					</form>
					<div style="width: 98%;height: 35px;padding-top: 10px;">
						<input class="bnt" type="button" value="添  加" onclick="javascript:insertUser();" style="cursor:pointer;" />
					</div>
					<table class="datalist" width="100%" border="0"
						cellpadding="0" cellspacing="0">
						<tr class="tr1">
							<th>
								序号
							</th>
							<th>
								采集类型
							</th>
							<th>
								车辆识别代号
							</th>
							<th>
								号牌号码
							</th>
							<th>
								号牌种类
							</th>
							<th>
								车辆型号
							</th>
							<th>
								中文品牌型号
							</th>
							<th>
								嫌疑车状态
							</th>
							<th>
								录入时间
							</th>
							<th>
								操作
							</th>
						</tr>
						<s:if test="#request.ycsXycVehList.size > 0">
							<s:iterator id="ycsXycVeh" value="#request.ycsXycVehList" status="st">
								<tr class="<s:if test="#st.odd == false">altrow</s:if>">
									<td>
										${st.count + (map.currentpage-1) * map.pagesize}
									</td>
									<td>
										<s:if test="#ycsXycVeh.zrfs == 0">按车辆识别代号锁定</s:if>
									    <s:elseif test="#ycsXycVeh.zrfs == 1">按车辆型号、中文品牌锁定</s:elseif>
									</td>
									<td>
										<s:property value="#ycsXycVeh.clsbdh"/>
									</td>
									<td>
										<s:property value="#ycsXycVeh.hphm"/>
									</td>
									<td>
										<s:if test="#ycsXycVeh.hpzl == '01'">大型汽车</s:if>
									    <s:elseif test="#ycsXycVeh.hpzl == '02'">小型汽车</s:elseif>
									    <s:elseif test="#ycsXycVeh.hpzl == '03'">使馆汽车</s:elseif>
									    <s:elseif test="#ycsXycVeh.hpzl == '04'">领馆汽车</s:elseif>
									    <s:elseif test="#ycsXycVeh.hpzl == '05'">境外汽车</s:elseif>
									    <s:elseif test="#ycsXycVeh.hpzl == '06'">外籍汽车</s:elseif>
									    <s:elseif test="#ycsXycVeh.hpzl == '07'">普通摩托车</s:elseif>
									    <s:elseif test="#ycsXycVeh.hpzl == '08'">轻便摩托车</s:elseif>
									    <s:elseif test="#ycsXycVeh.hpzl == '09'">使馆摩托车</s:elseif>
									    <s:elseif test="#ycsXycVeh.hpzl == '10'">领馆摩托车</s:elseif>
									    <s:elseif test="#ycsXycVeh.hpzl == '11'">境外摩托车</s:elseif>
									    <s:elseif test="#ycsXycVeh.hpzl == '12'">外籍摩托车</s:elseif>
									    <s:elseif test="#ycsXycVeh.hpzl == '13'">低速车</s:elseif>
									    <s:elseif test="#ycsXycVeh.hpzl == '14'">拖拉机</s:elseif>
									    <s:elseif test="#ycsXycVeh.hpzl == '15'">挂车</s:elseif>
									    <s:elseif test="#ycsXycVeh.hpzl == '16'">教练汽车</s:elseif>
									    <s:elseif test="#ycsXycVeh.hpzl == '17'">教练摩托车</s:elseif>
									    <s:elseif test="#ycsXycVeh.hpzl == '20'">临时入境汽车</s:elseif>
									    <s:elseif test="#ycsXycVeh.hpzl == '21'">临时入境摩托车</s:elseif>
									    <s:elseif test="#ycsXycVeh.hpzl == '22'">临时行驶车</s:elseif>
									    <s:elseif test="#ycsXycVeh.hpzl == '23'">警用汽车</s:elseif>
									    <s:elseif test="#ycsXycVeh.hpzl == '24'">警用摩托</s:elseif>
									    <s:else>
											
										</s:else>
									</td>
									<td>
										<s:property value="#ycsXycVeh.clxh"/>
									</td>
									<td>
										<s:property value="#ycsXycVeh.zwppxh"/>
									</td>
									<td>
										<s:if test="#ycsXycVeh.zt == 0">
											不合法
										</s:if>
										<s:elseif test="#ycsXycVeh.zt == 1">
											合法
										</s:elseif>
									</td>
									<td>
										<s:date name="#ycsXycVeh.lrrsj" format="yyyy-MM-dd hh:mm:ss" />
									</td>
									<td>
										<a href="<%=request.getContextPath()%>/xyc/xycveh_initYcsXycVeh.action?ycsXycVeh.xh=<s:property value="#ycsXycVeh.xh"/>">查看</a>
					    				<a href="<%=request.getContextPath()%>/xyc/xycveh_updateYcsXycVeh.action?ycsXycVeh.xh=<s:property value="#ycsXycVeh.xh"/>">修改</a>
					    				<a href="javascript:void(0);" id="<s:property value="#ycsXycVeh.xh"/>" onclick="javascript:deleteOne(<s:property value="#ycsXycVeh.xh"/>);">删除</a>
									</td>
								</tr>
							</s:iterator>
						</s:if>
						<s:else>
							<tr>
								<td colspan="10">
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
				<div class="corner bottomLeft"></div>
				<div class="corner bottomRight"></div>
			</div>
		</div>

	</body>
</html>
