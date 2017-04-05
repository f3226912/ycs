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
<title>机动车联系方式变更</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/content.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/jquery-1.8.2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/development-bundle/ui/jquery.ui.core.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/development-bundle/ui/jquery.ui.widget.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/development-bundle/ui/jquery.ui.position.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/development-bundle/ui/jquery.ui.menu.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/development-bundle/ui/jquery.ui.autocomplete.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/jquery-ui-1.9.1.custom.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery/css/redmond/jquery-ui-1.9.1.custom.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery/css/redmond/jquery.ui.all.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/artDialog4.1.6/jquery.artDialog.source.js?skin=chrome"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/artDialog4.1.6/plugins/iframeTools.source.js"></script>
<style type="text/css">
	.ui-autocomplete-loading {
		background: white url('<%=request.getContextPath()%>/jquery/development-bundle/demos/autocomplete/images/ui-anim_basic_16x16.gif') right center no-repeat;
	}
	#susernameid { width: 12em; }
	.bnt2 {
		width: 76px;
		height: 27px;
		background: url('<%=request.getContextPath()%>/images/an3.gif') no-repeat;
		border: none;
		font-weight: bold;
	}
</style>
<script type="text/javascript">
	$(document).ready(function() {
		
	});

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

    function sh(lsh){
    	window.location.href='<%=request.getContextPath()%>/jdcbg/jdcbg_contactBgShym.action?lsh='+lsh;
    }

	function zqsj(){
		window.location.href='<%=request.getContextPath()%>/jdcbg/jdcbg_contactBgHq.action?cz=1';
	}

    function ck(lsh){
    	window.location.href='<%=request.getContextPath()%>/jdcbg/jdcbg_contactBgShym.action?lsh='+lsh+'&ck=1';
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
	<div class="content1" style="width:100%;">
		<div class="roundedBox" id="type1" style="width:95%;">
			<div class="right" style="width:90%;">
				<form action="<%=request.getContextPath()%>/jdcbg/jdcbg_jdcBgQuery.action?cz=2" method="post" id="searchfromid">
				<table class="table1" width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<th class="th1" height="32" colspan="4">
							操作
						</th>
					</tr>
					<tr>
						<td class="tds" style="text-align: right;">
							流水号&nbsp;
						</td>
						<td class="tdl" style="text-align: left;">
							<input id="wwlshs" type="text" name="wwlshs" value="${wwlshs}" />
						</td>
						<td class="tds" style="text-align: right;">
							车主&nbsp;
						</td>
						<td class="tdl" style="text-align: left;">
							<input id="syrs" type="text" name="syrs" value="${syrs}" />
						</td>
					</tr>
					<tr>
						<td class="tds" style="text-align: right;">
							身份证明号码&nbsp;
						</td>
						<td class="tdl" style="text-align: left;">
							<input id="sfzmhms" type="text" name="sfzmhms" value="${sfzmhms}" />
						</td>
						<td class="tds" style="text-align: right;">
							最后处理人&nbsp;
						</td>
						<td class="tdl" style="text-align: left;">
							<select name="zhclrs" id="zhclrs" style="width: 130px;">
								<option value="">---请选择---</option>
								<s:iterator var="obj" value="#request.zhr">
									<option value='<s:property value="#obj"/>' 
										<s:if test="#request.zhclrs==#obj">selected</s:if>>
										<s:property value="#obj"/>
									</option>
								</s:iterator>
							</select>
						</td>
					</tr>
					<tr>
						<td class="tds" style="text-align: right;" >
							录入时间&nbsp;
						</td>
						<td class="tdl" style="text-align: left;" colspan="3">
							<input type="text" name="startDay" id="startDay" value="${request.startDay}"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false})" />
							至
							<input type="text" name="endDay" id="endDay" value="${request.endDay}"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false})" />
						</td>
					</tr>
					<tr>
						<td class="tds" colspan="4" style="text-align: center;">
							<input type="submit" id="frombutid" value="查  询" style="cursor:pointer;"/>
							&nbsp;&nbsp;
							<input type="button" value="获取待审核数据" onclick="zqsj()"/>
							&nbsp;&nbsp;
							<s:if test='#request.cz == "1"'>
								待处理记录数：<s:property value="#request.zon"/>
							</s:if>
						</td>
					</tr>
				</table>
				</form>
				<table class="datalist" width="100%" border="0"
					cellpadding="0" cellspacing="0">
					<tr class="tr1">
						<th>
							流水号
						</th>
						<th>
							车牌号码
						</th>
						<th>
							号牌种类
						</th>
						<th>
							车主身份证明号码
						</th>
						<th>
							车主
						</th>
						<th>
							联系号码
						</th>
						<th>
							录入时间
						</th>
						<th>
							受理人
						</th>
						<th>
							受理状态
						</th>
						<th>
							受理结果
						</th>
						<th>
							操作
						</th>
					</tr>
					<s:if test="bgList.size > 0">
						<s:iterator id="jdcbg" value="bgList" status="st">
							<tr class="<s:if test="#st.odd == false">altrow</s:if>">
								<%--<td>
									${st.count + (map.currentpage-1) * map.pagesize}
								</td>--%>
								<td>
									${jdcbg.wwlsh }
								</td>
								<td>
									${jdcbg.cphm }
								</td>
								<td>
									<s:if test="#request.jdcbg.cllx == '01'">
										大型汽车
									</s:if>
									<s:elseif test="#request.jdcbg.cllx == '02'">
										小型汽车
									</s:elseif>
									<s:elseif test="#request.jdcbg.cllx == '03'">
										使馆汽车
									</s:elseif>
									<s:elseif test="#request.jdcbg.cllx == '04'">
										领馆汽车
									</s:elseif>
									<s:elseif test="#request.jdcbg.cllx == '05'">
										境外汽车
									</s:elseif>
									<s:elseif test="#request.jdcbg.cllx == '06'">
										外籍汽车
									</s:elseif>
									<s:elseif test="#request.jdcbg.cllx == '07'">
										普通摩托车
									</s:elseif>
									<s:elseif test="#request.jdcbg.cllx == '08'">
										轻便摩托车
									</s:elseif>
									<s:elseif test="#request.jdcbg.cllx == '09'">
										使馆摩托车
									</s:elseif>
									<s:elseif test="#request.jdcbg.cllx == '10'">
										领馆摩托车
									</s:elseif>
									<s:elseif test="#request.jdcbg.cllx == '11'">
										境外摩托车
									</s:elseif>
									<s:elseif test="#request.jdcbg.cllx == '12'">
										外籍摩托车
									</s:elseif>
									<s:elseif test="#request.jdcbg.cllx == '13'">
										低速车
									</s:elseif>
									<s:elseif test="#request.jdcbg.cllx == '14'">
										拖拉机
									</s:elseif>
									<s:elseif test="#request.jdcbg.cllx == '15'">
										挂车
									</s:elseif>
									<s:elseif test="#request.jdcbg.cllx == '16'">
										教练汽车
									</s:elseif>
									<s:elseif test="#request.jdcbg.cllx == '17'">
										教练摩托车
									</s:elseif>
									<s:elseif test="#request.jdcbg.cllx == '18'">
										试验汽车
									</s:elseif>
									<s:elseif test="#request.jdcbg.cllx == '19'">
										试验摩托车
									</s:elseif>
									<s:elseif test="#request.jdcbg.cllx == '20'">
										临时入境汽车
									</s:elseif>
									<s:elseif test="#request.jdcbg.cllx == '21'">
										临时入境摩托车
									</s:elseif>
									<s:elseif test="#request.jdcbg.cllx == '22'">
										临时行驶车
									</s:elseif>
									<s:elseif test="#request.jdcbg.cllx == '23'">
										警用汽车
									</s:elseif>
									<s:elseif test="#request.jdcbg.cllx == '24'">
										警用摩托
									</s:elseif>
									<s:elseif test="#request.jdcbg.cllx == '25'">
										原农机号牌
									</s:elseif>
									<s:elseif test="#request.jdcbg.cllx == '26'">
										香港入出境车
									</s:elseif>
									<s:elseif test="#request.jdcbg.cllx == '27'">
										澳门入出境车
									</s:elseif>
									<s:else>
										未知
									</s:else>
								</td>
								<td>
									${jdcbg.sfzmhm }
								</td>
								<td>
									${jdcbg.syr }
								</td>
								<td>
									${jdcbg.yddh }
								</td>
								<td>
									<s:date name="#jdcbg.tjsj" format="yyyy-MM-dd"/>
								</td>
								<td>
									${jdcbg.zhclr }
								</td>
								<td>
									<s:if test='#request.jdcbg.zhclhj == "18"'>
										待受理
									</s:if>
									<s:elseif test='#request.jdcbg.zhclhj == "888"'>
										已受理
									</s:elseif>
									<s:elseif test='#request.jdcbg.zhclhj == "2"'>
										未通过
									</s:elseif>
								</td>
								<td>
									<s:if test='#request.jdcbg.zhclhj == "18"'>
										正在处理
									</s:if>
									<s:elseif test='#request.jdcbg.zhclzt=="1"&&#request.jdcbg.zhclhj == "888"'>
										成功
									</s:elseif>
									<s:elseif test='#request.jdcbg.zhclzt=="0"&&#request.jdcbg.zhclhj == "888"'>
										失败
									</s:elseif>
									<s:elseif test='#request.jdcbg.zhclhj == "2"'>
										资料核实未通过
									</s:elseif>
								</td>
								<td>
									<s:if test='#request.cz == "1"'>
										<a href="#" onclick="sh('<s:property value="#jdcbg.wwlsh"/>')">【审核】</a>
									</s:if>
									<s:elseif test='#request.cz == "2"'>
										<a href="#" onclick="ck('<s:property value="#jdcbg.wwlsh"/>')">【查询】</a>
									</s:elseif>
								</td>
							</tr>
						</s:iterator>
					</s:if>
					<s:else>
						<tr>
							<td colspan="11">
								<span id="nodata" style="color: red">暂时没有相关数据</span>
							</td>
						</tr>
					</s:else>
				</table>
				<table width="100%" border="0" cellspacing="0" cellpadding="0" style="padding-top: 5px;">
					<pt:write uri="${map.uri}" pagesize="${map.pagesize}" rscount="${map.rscount}" currentpage="${map.currentpage}"></pt:write>
				</table>
				<s:if test='#request.slzt ==""'>
					<script type="text/javascript">
						alert('受理失败');
					</script>
				</s:if>
				<s:elseif test='#request.slzt !=""&&#request.zt =="1" '>
					<script type="text/javascript">
						alert('受理成功');
					</script>
				</s:elseif>
				<s:elseif test='#request.slzt !=""&&#request.zt =="2" '>
					<script type="text/javascript">
						alert('受理成功');
					</script>
				</s:elseif>
			</div>
			<div class="corner topLeft"></div>
			<div class="corner topRight"></div>
			<div class="corner bottomLeft"></div>
			<div class="corner bottomRight"></div>
		</div>
	</div>
</body>
</html>
