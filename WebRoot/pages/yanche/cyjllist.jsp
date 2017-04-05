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
	<title>查验记录信息</title>
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
	<script type="text/javascript" src="<%=request.getContextPath()%>/artDialog4.1.6/jquery.artDialog.source.js?skin=chrome"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/artDialog4.1.6/plugins/iframeTools.source.js"></script>
	<script type="text/javascript">
		function exception(exception){
			alert(exception);
		}
					
		function getjyd(lsh){
			var uri = "<%=request.getContextPath()%>/yanche/yczd_showCjbd.action?lsh="+lsh;
			window.open(uri,'info','width='+(window.screen.availWidth-10)+',height='+(window.screen.availHeight-30)+ ',top=0,left=0,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no');
		}
		
		//打印
		function showPrint(lsh){
			var uri = "<%=request.getContextPath()%>/yanche/yczd_printCybd.action?lsh="+lsh;
			//var returnValue=window.open(uri,null,"Height:1000px;Width:600px;status:no;scroll:yes;location=no;center:yes");
			window.open(uri,'info','width='+(window.screen.availWidth-300)+',height='+(window.screen.availHeight-20)+ ',top=0,left=150,scrollbars=yes');
		}
			
	</script>
	</head>

	<body style="background:#c7e5ff;">
		<div class="content1" style="width: 98%;">
			<div class="roundedBox" id="type1" style="width: 98%;">
				<div class="right" style="width: 95%;">
					<form action="<%=request.getContextPath()%>/yanche/yczd_showCjyjl.action" method="post">
						<input type="hidden" id="dmlb" value="${request.lyType}"/>
						<table class="table1" width="100%" border="0"
							cellpadding="0" cellspacing="0">
							<tr>
								<th class="th1" height="32" colspan="4">
									查询条件
								</th>
							</tr>
							<tr>
								<td class="tds" style="text-align: center;">
									流水号：&nbsp;
									<input type="text" name="lsh" id="lsh" value="${request.lsh}"/>
								</td>
								<td class="tds" style="text-align: center;">
									身份证名号：&nbsp;
									<input type="text" name="sfzmhm" id="sfzmhm" value="${request.sfzmhm}"/>
								</td>
							</tr>
							<tr>
								<td class="tds" colspan="4">
									<div align="center">
										<input type="submit" value="查询" class="bnt" />
									</div>
								</td>
							</tr>
						</table>
					</form>					
	<!-- 查验项目--Div2==========================================================-->
				<div id="div2">
					<table class="datalist" width="100%" border="0"
						cellpadding="0" cellspacing="0">
						<tr class="tr1">
							<th>流水号</th>
							<th>车辆类型</th>
							<th>使用性质</th>
							<th>查验人</th>
							<th>身份证号码</th>
							<th>业务类型</th>
							<th>查验时间</th>
							<th>查验结论</th>
							<th>操作</th>
						</tr>
						<s:if test="#request.cyjlList.size > 0">
							<s:iterator id="pvp" value="#request.cyjlList" status="st">
								<tr class="<s:if test="#st.odd == false">altrow</s:if>">
									<td>${pvp[0]}</td>
									<td>${pvp[1]}</td>
									<td>${pvp[2]}</td>
									<td>${pvp[3]}</td>
									<td>${pvp[4]}</td>
									<td>
									<s:if test='#pvp[5]=="B"'>
										<s:if test='#pvp[6]=="B"'>
										<s:property value="#request.ywlxMap.get('BB')" />
										</s:if>
										<s:if test='#pvp[6]=="C"'>
										<s:property value="#request.ywlxMap.get('BC')" />
										</s:if>
									</s:if>
									<s:else>
										<s:property value="#request.ywlxMap.get(#pvp[5])" />
									</s:else>
									</td>
									<td>${pvp[7]}</td>
									<td>${pvp[8]=="0"?"未通过":"通过"}</td>
									<td>
										<!--<a href="#" onclick="getjyd('${pvp[0]}')">预览检验单</a>
										--><a href="#" onclick="showPrint('${pvp[0]}')">查看与打印</a>
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
				</div>	
				<table width="100%" border="0" cellspacing="0" cellpadding="0"
						style="padding-top: 5px;">
						<pt:write uri="${map.uri}" pagesize="${map.pagesize}" rscount="${map.rscount}" currentpage="${map.currentpage}"></pt:write>
				</table>
	
		</div>
	</body>
</html>
