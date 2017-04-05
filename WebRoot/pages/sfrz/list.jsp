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
    <title>身份认证信息列表</title>
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
			window.open('<%=request.getContextPath()%>/sfrz/sfrz_initView.action?sfrzCjxxb.cid=' + id ,'info','width='+(window.screen.availWidth-10)+',height='+(window.screen.availHeight-30)+ ',top=0,left=0,toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=yes');
		}
		
		function closechuli(){
			chuli.close();
		}
	</script>
	
  </head>
  
  <body style="background:#c7e5ff;">
    <div class="content1" style="width: 100%;">
		  <div class="roundedBox" id="type1" style="width: 95%;">
				<div class="right" style="width: 98%;">
					<form action="<%=request.getContextPath()%>/sfrz/sfrz_initSfrzCjxxbList.action" method="post" id="sfrzform">
						<table class="table1" width="100%" border="0"
							cellpadding="0" cellspacing="0">
							<tr>
								<th class="th1" height="32" colspan="4">
									查询条件
								</th>
							</tr>
							<tr>
								<td class="tds" style="text-align: right">
									认证来源&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<s:select list="#request.rzlySjzdList" theme="simple"
											id="rzly" headerKey="" headerValue="==请选择==" 
											listKey="dmz" listValue="dmsm" name="rzly" 
											value="#request.rzly" ></s:select>&nbsp;
								</td>
								<td class="tds" style="text-align: right;">
									认证角色&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<s:select list="#request.rzjsSjzdList" theme="simple"
											id="rzjs" headerKey="" headerValue="==请选择==" 
											listKey="dmz" listValue="dmsm" name="rzjs" 
											value="#request.rzjs" ></s:select>&nbsp;
								</td>
								
							</tr>
							<tr>
								<td class="tds" style="text-align: right">
									采集流水号&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<input type="text" name="lsh" onkeyup="clearspace(this);" onblur="clearallspace(this);" value="${lsh}" />
								</td>
								<td class="tds" style="text-align: right;">
									身份证名号码&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<input type="text" name="sfzmhm" onkeyup="clearspace(this);" onblur="clearallspace(this);" value="${sfzmhm}" />
								</td>
								
							</tr>
							<tr>
								<td class="tds" style="text-align: right">
									姓名&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<input type="text" name="xm" onkeyup="clearspace(this);" onblur="clearallspace(this);" value="${xm}" />
								</td>
								<td class="tds" style="text-align: right">
									采集日期&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									起
									<input name="s_date" id="s_date" value="${s_date}" size="10" type="text"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
									止
									<input name="e_date" id="e_date" value="${e_date}" size="10" type="text"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
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
								采集流水号
							</th>
							<th>
								姓名
							</th>
							<th>
								身份证明号码
							</th>
							<th>
								认证来源
							</th>
							<th>
								认证角色
							</th>
							<th>
								采集时间
							</th>
							<th>
								操作
							</th>
						</tr>
						<s:if test="#request.sfrzCjxxbList.size > 0">
							<s:iterator id="sc" value="#request.sfrzCjxxbList" status="st">
								<tr class="<s:if test="#st.odd == false">altrow</s:if>">
									<td>
										${st.count + (map.currentpage-1) * map.pagesize}
									</td>
									<td>
										${sc.cid }
									</td>
									<td>
								 		${sc.xm }
									</td>
									<td>
								 		${sc.sfzmhm }
									</td>
									<td>
										${sc.rzly }
									</td>
									<td>
										${sc.rzjs }
									</td>
									<td>
										<fmt:formatDate value="${sc.shsj}" pattern="yyyy-MM-dd HH:mm:ss" />
									</td>
									<!--  
									<td>
								 		<s:property value="#slgsp.bllx==1?'当事人':'代理人'"/>
									</td>
									-->
									<td>
										<a href="javascript:void(0);" onclick="javascript:windowopen('${sc.cid }');">查看</a>
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
