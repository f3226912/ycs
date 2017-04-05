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
		<title>固定字典项目查询</title>
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
		
			$(document).ready(function(){
				var type = $("#yanche").val();
				$("#div0").hide();
				$("#div1").hide();
				$("#div2").hide();
				$("#div3").hide();
				$("#div4").hide();
				if(type==0){
					$("#div0").show();
				}else if(type=='3'||type==4){
					$("#div1").show();
				}else if(type=='103'){
					$("#div2").show();
				}else if(type=='33'){
					$("#div3").show();
				}else{
					$("#div4").show();
				}
			});
			
			
			//根据下拉框类别值查询信息
			function SelectType(){
				var type = $("#yanche").val();	
				var url = '<%=request.getContextPath()%>/yanche/yczd_initYcgl.action?dmlb='+type+'';
				window.location.href=url;
			};
			
			
			//【其它业务项】初始化编辑页面
			function initEdit3(type,dmlb,dmz){
				if(type==2){//添加
					dmlb = $("#dmlb").val();
				}
				var uri = "<%=request.getContextPath()%>/yanche/yczd_qtEditInit.action?type="+type+"&dmlb="+dmlb+"&dmz="+dmz;
				var returnValue=window.showModalDialog(uri,null,"dialogHeight:140px;dialogWidth:600px;status:no;scroll:yes;center:yes");
				if(returnValue!=null && returnValue!="0" && returnValue!="" && typeof(returnValue)!="undefined"){
						location.reload();
				}
			}
		</script>
	</head>

	<body style="background:#c7e5ff;">
		<div class="content1" style="width: 100%;">
			<div class="roundedBox" id="type1" style="width: 95%;">
				<div class="right" style="width: 90%;">
					<form action="<%=request.getContextPath()%>/yanche/yczd_initYcgl.action" method="post">
						<input type="hidden" id="dmlb" value="${request.dmlb}"/>
						<table class="table1" width="100%" border="0"
							cellpadding="0" cellspacing="0">
							<tr>
								<th class="th1" height="32" colspan="4">
									查询条件
								</th>
							</tr>				
							<tr>
								<td class="tds" style="text-align:center;" colspan="4">
									验车业务类别：&nbsp;
									<select id="yanche" name="yanche">
							    	<s:if test="#request.ycTypeList.size > 1">
							    		<option value="0">--请选择--</option>
							    	</s:if>
						            <s:iterator value="#request.ycTypeList" id="yanche" >
								        <s:iterator value="#request.yanche" id="d" status="status">
								            <option value="<s:property value="#request.d.key"/>"<s:if test="#request.d.key==#request.dmlb">selected="selected"</s:if>>
								            	<s:property value="value"/>
								            </option>
								        </s:iterator>
					            	</s:iterator>
					            </select>
								</td>
							</tr>
							<tr>
								<td class="tds" colspan="4">
									<div align="center">
										<input type="button" onclick="SelectType()" value="查询" class="bnt" />
									</div>
								</td>
							</tr>
						</table>
					</form>						
		<div id="div0">	
			<table class="datalist" width="100%" border="0"cellpadding="0" cellspacing="0">
				<tr>
					<td style="text-align: center; font-weight: bold; font-size: 13px;">请选择验车业务类别</td>
				</tr>
				<tr style="text-align: center; ">
					<td><span style=" color: red; font-size: 11px;">暂时没有相关数据</span></td>
				</tr>
			</table>
		</div>	
				
	<!-- 其它--Div4==========================================================-->
				<div id="div4">
					<!--<input type="button" onclick="initEdit3(2,'','')" value="添加" class="bnt" />-->
					<table class="datalist" width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr class="tr1">
							<th>代码值</th>
							<th>类型名</th>
							<th>状态</th>
							<s:if test="#request.dmlb!=40">
							<th>操作权限</th>
							</s:if>
							<!--<th>操作</th>-->
						</tr>
						<s:if test="#request.ycByTypeList.size > 0">
							<s:iterator id="pvp" value="#request.ycByTypeList" status="st">
								<tr class="<s:if test="#st.odd == false">altrow</s:if>">
									<td>${pvp.dmz}</td>
									<td>${pvp.dmsm}</td>
									<s:if test="#request.dmlb==40">
										<td>${pvp.dmsm1=="1"?"√":""}</td>
									</s:if>
									<s:else>
										<td>${pvp.zt=="0"?"隐藏":"正常"}</td>
										<td>${pvp.dmsx=="0"?"不许修改":"允许修改"}</td>
									</s:else>
									
									<!--<td>
									<s:if test='#session.userbean.name=="jst"'>
										<a href="#" onclick="initEdit3(1,'${pvp.dmlb}','${pvp.dmz}')">修改</a>
									</s:if>
									<s:else>
										<s:if test='#pvp.dmsx=="1"'>
											<a href="#" onclick="initEdit3(1,'${pvp.dmlb}','${pvp.dmz}')">修改</a>
										</s:if>
									</s:else>
									</td>-->
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
				<div class="corner topLeft"></div>
				<div class="corner topRight"></div>
			</div>
		</div>
	</body>
</html>
