<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="com.ycszh.common.PageTag" prefix="pt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
  <head>
    <title>抵押登记查询列表</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/content.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/module.css" />
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
	<script type="text/javascript" src="<%=request.getContextPath()%>/artDialog4.1.6/jquery.artDialog.source.js?skin=chrome"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/artDialog4.1.6/plugins/iframeTools.source.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/public.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/jquery-lightbox-0.5/js/jquery.lightbox-0.5.js"></script>
	<script type="text/javascript">
		var chuli;
		$(document).ready(function(){
			setTimeout(function(){
				var splx = '${request.splx}';
				$("select[name='splx'] option").each(function(){
					if($(this).val() == splx){
						$(this).attr("selected", true);
					}
				});
			},500)
		});
		
		function editsp(url){
			window.location.href = url;
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
		function windowopen(lsh){
			var info = window.open('<%=request.getContextPath()%>/dydj/yhsl_initDydjInfo.action?lsh=' + lsh ,'info','width='+(window.screen.availWidth-10)+',height='+(window.screen.availHeight-30)+ ',top=0,left=0,toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=yes');
		}
		
		function pageload(){
			var jbrbm = '${request.jbrbm}';
			$("#jbrbm").val(jbrbm);
		}
	</script>
	
  </head>
  
  <body onload="pageload();" style="background:#c7e5ff;">
    <div class="content1" style="width: 100%;">
		  <div class="roundedBox" id="type1" style="width: 95%;">
				<div class="right" style="width: 98%;">
					<form action="<%=request.getContextPath()%>/dydj/yhsl_initDydjList.action" method="post" id="esfrom">
						<table class="table1" width="100%" border="0"
							cellpadding="0" cellspacing="0">
							<tr>
								<th class="th1" height="32" colspan="4">
									查询条件
								</th>
							</tr>
							<tr>
								<td class="tds" style="text-align: right">
									录入时间&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									起
									<input name="s_date" id="s_date" value="${s_date}" size="10" type="text"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
									止
									<input name="e_date" id="e_date" value="${e_date}" size="10" type="text"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
								</td>
								<td class="tds" style="text-align: right;">
									流水号&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<input type="text" name="lsh" value="${request.lsh}" onkeyup="clearspace(this)" onblur="clearallspace(this)"/>
								</td>
							</tr>
							<tr>
								<td class="tds" style="text-align: right;">
									号牌号码&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<input type="text" name="hphm" value="${hphm}" onkeyup="clearspace(this)" onblur="clearallspace(this)"/>
								</td>
								<td class="tds" style="text-align: right;">
									号牌种类&nbsp;
								</td>
								<td class="tdl" style="text-align: left;" >
									<select name="hpzl" id="hpzl_dmz" style="width: 150px;">
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
								<td class="tds" style="text-align: right;">
									录入账号&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<input type="text" name="lrzh" value="${lrzh}" onkeyup="clearspace(this)" onblur="clearallspace(this)"/>
								</td>
								<td class="tds" style="text-align: right;">
									银行组织机构证号&nbsp;
								</td>
								<td class="tdl" style="text-align: left;" >
									<input type="text" name="yhZzjgzh" value="${yhZzjgzh}" onkeyup="clearspace(this)" onblur="clearallspace(this)"/>
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
					<br>
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
								号牌号码
							</th>
							<th>
								号牌种类
							</th>
							<th>
								业务办理类型
							</th>
							<th>
								受理类型
							</th>
							<th>
								当前状态
							</th>
							<th>
								录入时间
							</th>
							<th>
								操作
							</th>
						</tr>
						<s:if test="#request.dydjsbspList.size > 0">
							<s:iterator id="dydj" value="#request.dydjsbspList" status="st">
								<tr class="<s:if test="#st.odd == false">altrow</s:if>">
									<td>
										${st.count + (map.currentpage-1) * map.pagesize}
									</td>
									<td>
										${dydj[1] }&nbsp;
									</td>
									<td>
										${dydj[5] }&nbsp;
									</td>
									<td>
								 		<s:iterator id="hpzl" value="#request.hpzlList">
											<s:if test="#request.dydj[6]==#hpzl.dmz">
												<s:property value="#hpzl.dmms1"/>
											</s:if>
										</s:iterator>
									</td>
									<td>
										<c:choose>
											<c:when test="${dydj[2] == 'A'}">抵押登记</c:when>
											<c:when test="${dydj[2] == 'B'}">解除抵押登记</c:when>
											<c:otherwise>${dydj[2]}&nbsp;</c:otherwise>
										</c:choose>
									</td>
									<td>
										<c:choose>
											<c:when test="${dydj[3] == '11'}">代办个人申报</c:when>
											<c:when test="${dydj[3] == '12'}">代办单位申报</c:when>
											<c:when test="${dydj[3] == '21'}">车主自行办理</c:when>
											<c:when test="${dydj[3] == '22'}">车主自行申报</c:when>
											<c:when test="${dydj[3] == '31'}">个人对个人抵押</c:when>
											<c:otherwise>${dydj[3]}&nbsp;</c:otherwise>
										</c:choose>
									</td>
									<td>
										<s:if test="#request.dydj[55]==0">待审 </s:if>
									    <s:elseif test="#request.dydj[55]==1">待取件 </s:elseif>
									    <s:elseif test="#request.dydj[55]==2">邮政待签注资料移交车管</s:elseif>
									    <s:elseif test="#request.dydj[55]==3">车管待签注资料复核 </s:elseif>
									    <s:elseif test="#request.dydj[55]==4">车管已签注资料移交邮政 </s:elseif>
									    <s:elseif test="#request.dydj[55]==5">邮政回填EMS单号</s:elseif>
									    <s:elseif test="#request.dydj[55]=='CT'">车管所退办</s:elseif>
									    <s:elseif test="#request.dydj[55]=='YT'">邮政退办 </s:elseif>
									    <s:elseif test="#request.dydj[55]=='CT4'">车管退办资料移交邮政 </s:elseif>
									    <s:elseif test="#request.dydj[55]=='CT5'">资料退办 </s:elseif>
										<s:elseif test="#request.dydjb[55]=='6'">现场受理 </s:elseif>
										<s:elseif test="#request.dydj[55]=='QX'"><font style="color: red;">已取消</font></s:elseif>
										<s:else>${dydj[55] }</s:else>
									</td>
									<td>
								 		${dydj[53]}
									</td>
									<td id="${dydj[0]}">
										<%-- <a href="<%=request.getContextPath()%>/veh/veh_initSlCx.action?id=${dbjgZjxxb.id}" target="_blank">查看</a> --%>
										<a href="javascript:void(0);" onclick="javascript:windowopen('${dydj[1]}');">查看</a>
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
