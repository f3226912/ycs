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
    <title>受理查询列表</title>
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
			var exportData = '${request.exportData}';
			if(exportData != null && exportData != ""){
				alert(exportData);
			}
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
		function windowopen(id){
			var info = window.open('<%=request.getContextPath()%>/veh/veh_initSlCx.action?id=' + id + '&cznr=ck' ,'info','width='+(window.screen.availWidth-10)+',height='+(window.screen.availHeight-30)+ ',top=0,left=0,toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=yes');
		}
		
		function windowopensh(id){
			var info = window.open('<%=request.getContextPath()%>/veh/veh_initSlCx.action?id=' + id + '&cznr=sh' ,'info','width='+(window.screen.availWidth-10)+',height='+(window.screen.availHeight-30)+ ',top=0,left=0,toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=yes');
		}
		
		function pageload(){
			var jbrbm = '${request.jbrbm}';
			$("#jbrbm").val(jbrbm);
		}
		
		function check(){
			$("#esfrom").attr("action", "<%=request.getContextPath()%>/veh/veh_initSlCxList.action");
			$("#esfrom").submit();
		}
		
		function exports(){
			$("#esfrom").attr("action", "<%=request.getContextPath()%>/veh/veh_getSlgVehExport.action");
			$("#esfrom").submit();
		}
		
		function updatezt(cjid,zt){
				if("1" == zt){
					$("#"+cjid+"zt").html("<font color='green'>审核通过</font>");
				}else if("2" == zt){
					$("#"+cjid+"zt").html("<font color='red'>审核未通过</font>");
				}
				$("#"+cjid).find("a").each(function(){
					if($(this).html()=="审核"){
						$(this).remove();
					}
				});
			}
	</script>
	
  </head>
  
  <body onload="pageload();" style="background:#c7e5ff;">
    <div class="content1" style="width: 100%;">
		  <div class="roundedBox" id="type1" style="width: 95%;">
				<div class="right" style="width: 98%;">
					<form action="<%=request.getContextPath()%>/veh/veh_initSlCxList.action" method="post" id="esfrom">
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
									<input name="s_date" id="s_date" value="${s_date}" size="10" type="text"  onfocus="WdatePicker({startDate:'2013-09-13',alwaysUseStartDate:true})"/>
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
									审核状态&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<select name="shzt" id="shzt" style="width:120px;">
										<option value="">===请选择===</option>
										<option value="0" <s:if test="#request.shzt == 0">selected</s:if>>未审核</option>
										<option value="1" <s:if test="#request.shzt == 1">selected</s:if>>审核通过</option>
										<option value="2" <s:if test="#request.shzt == 2">selected</s:if>>审核未通过</option>
									</select>
								</td>
								<td class="tds" style="text-align: right;">
									业务类型&nbsp;
								</td>
								<td class="tdl" style="text-align: left;" >
									<select name="ywlx" id="ywlx_dmz">
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
									<select name="hpzl" id="hpzl_dmz">
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
								<td class="tds" style="text-align: right">
									经办人部门&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<select name="jbrbm" id="jbrbm" style="width:155px;">
										<option value="">===请选择===</option>
										<s:iterator id="dept" value="#request.deptList" >
											<option value="${dept[0]}">${dept[1]}</option>
										</s:iterator>
									</select>
								</td>
								<td class="tds" style="text-align: right">
									经办人&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<input name="jbr" id="jbr" value="${jbr}" size="10" type="text" />
								</td>
							</tr>
							<tr>
								<td class="tds" style="text-align: right">
									审核人&nbsp;
								</td>
								<td class="tdl" style="text-align: left;" colspan="3">
									<input name="shr" id="shr" value="${shr}" size="10" type="text" />
								</td>
							</tr>
							<tr>
								<td class="tds" colspan="4">
									<div align="center">
										<input class="bnt" type="button" value="查  询" onclick="check();" style="cursor:pointer;" />
										<input class="bnt" type="button" value="导  出" onclick="exports();" style="cursor:pointer;" />
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
								业务类型
							</th>
							<th>
								录入时间
							</th>
							<th>
								审核状态
							</th>
							<th>
								操作
							</th>
						</tr>
						<s:if test="#request.dbjgZjxxbList.size > 0">
							<s:iterator id="dbjgZjxxb" value="#request.dbjgZjxxbList" status="st">
								<tr class="<s:if test="#st.odd == false">altrow</s:if>">
									<td>
										${st.count + (map.currentpage-1) * map.pagesize}
									</td>
									<td>
										${dbjgZjxxb.lsh }
									</td>
									<td>
										${dbjgZjxxb.hphm }
									</td>
									<td>
								 		<s:iterator id="hpzl" value="#request.hpzlList">
											<s:if test="#request.dbjgZjxxb.hpzl==#hpzl.dmz">
												<s:property value="#hpzl.dmms1"/>
											</s:if>
										</s:iterator>
									</td>
									<td>
										${dbjgZjxxb.ywlx}
									</td>
									<td>
								 		<fmt:formatDate value="${dbjgZjxxb.lrsj}" pattern="yyyy-MM-dd HH:mm:ss" />
									</td>
									<td id="${dbjgZjxxb.id}zt">
								 		<s:if test="#dbjgZjxxb.shjg == 0"><font color="blue">未审核</font></s:if>
										<s:elseif test="#dbjgZjxxb.shjg == 1"><font color="green">审核通过</font></s:elseif>
										<s:elseif test="#dbjgZjxxb.shjg == 2"><font color="red">审核未通过</font></s:elseif>
									</td>
									<td id="${dbjgZjxxb.id}">
										<c:if test="${dbjgZjxxb.lrbmdm == userbean.bmid}">
											<a href="javascript:void(0);" id="${dbjgZjxxb.id}" onclick="javascript:windowopensh(${dbjgZjxxb.id});">审核</a>
										</c:if>
										<%-- <a href="<%=request.getContextPath()%>/veh/veh_initSlCx.action?id=${dbjgZjxxb.id}" target="_blank">查看</a> --%>
										<a href="javascript:void(0);" onclick="javascript:windowopen(${dbjgZjxxb.id});">查看</a>
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
