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
		<title>查验项目管理</title>
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
				
			//【查验项目/拍照规格】目录信息
			function CyxmmlOrPzggml(typeId,dmz,typeName){
				var iWidth = 900;
				var iHeight = 420;
				var iTop = (window.screen.height-30-iHeight)/2;
				var iLeft = (window.screen.width-10-iWidth)/2;
				var uri = "<%=request.getContextPath()%>/yanche/yczd_findCyxmOrPzggML.action?dmz="+dmz+"&type="+typeName;
				window.open(uri,null,'height='+iHeight+',innerHeight='+iHeight+',width='+iWidth+',innerWidth='+iWidth+',top='+iTop+',left='+iLeft+',toolbar=no,menubar=no,scrollbars=auto,resizable=yes,status=no,scroll:yes');
			}
			
			//添加指定类别信息
			function insert(type){
				var uri="<%=request.getContextPath()%>/wjzhglxt/wjclgl_initFolder.action?id="+id+"&editType=1";
				var returnValue=window.showModalDialog(uri,null,"dialogHeight:400px;dialogWidth:550px;status:no;scroll:no;center:yes");
				if(returnValue!=null && returnValue!="0" && returnValue!="" && typeof(returnValue)!="undefined"){
					location.reload();
				}
			}
			
			function myclear(obj){
				obj.value = obj.value.replace(" ","");
				obj.value = obj.value.replace(" ","");
			}
			
			
			//【查验项/拍照项】初始化编辑页面
			function initEdit2(type,dmlb,dmz,yeType){
				if(type==2){//添加
					dmlb = $("#dmlb").val();
				}
				
				if(yeType=="pz"){	//拍照规格
					var uri = "<%=request.getContextPath()%>/yanche/yczd_CyOrPzEditInit.action?type="+type+"&dmlb="+dmlb+"&dmz="+dmz+"&yeType="+yeType;
					if(type==2){
						var returnValue=window.showModalDialog(uri,null,"dialogHeight:430px;dialogWidth:660px;status:no;scroll:yes;center:yes");
					}else{
						var returnValue=window.showModalDialog(uri,null,"dialogHeight:760px;dialogWidth:660px;status:no;scroll:yes;center:yes");
					}
					if(returnValue!=null && returnValue!="0" && returnValue!="" && typeof(returnValue)!="undefined"){
						location.reload();
					}
				}
				if(yeType=="cy"){	//查验项目
					var uri = "<%=request.getContextPath()%>/yanche/yczd_CyOrPzEditInit.action?type="+type+"&dmlb="+dmlb+"&dmz="+dmz+"&yeType="+yeType;
					var returnValue=window.showModalDialog(uri,"","dialogWidth:660px;dialogHeight:415px;help:no;status:no;scroll:yes;location:no");
					if(returnValue!=null && returnValue!="0" && returnValue!="" && typeof(returnValue)!="undefined"){
						location.reload();
					}
				}
			}
			
		</script>
	</head>

	<body style="background:#c7e5ff;">
		<div class="content1" style="width: 100%;">
			<div class="roundedBox" id="type1" style="width: 95%;">
				<div class="right" style="width: 90%;">
					<form action="<%=request.getContextPath()%>/yanche/yczd_initPzzdgl.action" method="post">
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
									类型名：&nbsp;
									<input type="text" name="dmz" id="dmz" value="${request.dmz}"/>
									<input type="hidden" name="lyType" id="lyType" value="${request.lyType}"/>
									<input type="hidden" name="ly" id="ly" value="cyxm"/>
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
					<input type="button" onclick="initEdit2(2,'','','cy')" value="添加" class="bnt" />
					<table class="datalist" width="100%" border="0"
						cellpadding="0" cellspacing="0">
						<tr class="tr1">
							<!--<th>代码值</th>-->
							<th>类型名</th>
							<th>查验依据</th>
							<th>状态</th>
							<th>操作权限</th>
							<th>操作</th>
						</tr>
						<s:if test="#request.ycByTypeList.size > 0">
							<s:iterator id="pvp" value="#request.ycByTypeList" status="st">
								<tr class="<s:if test="#st.odd == false">altrow</s:if>">
									<!--<td>${pvp.dmz}</td>-->
									<td>${pvp.dmsm}</td>
									<td>
										<a href="#" onclick="CyxmmlOrPzggml(1,'${pvp.dmz}','cyxmml')">
										<s:if test="#pvp.dmsm1.length()>30">
											${fn:substring(pvp.dmsm1,0,30)}...
										</s:if>
										<s:else>
											${pvp.dmsm1}
										</s:else>
										</a>
									</td>
									<td>${pvp.zt=="0"?"隐藏":"正常"}</td>
									<td>${pvp.dmsx=="0"?"不许修改":"允许修改"}</td>
									<td>
									<s:if test='#session.userbean.name=="jst"'>
										<a href="#" onclick="initEdit2(1,'${pvp.dmlb}','${pvp.dmz}','cy')">修改</a>
									</s:if>
									<s:else>
										<s:if test='#pvp.dmsx=="1"'>
											<a href="#" onclick="initEdit2(1,'${pvp.dmlb}','${pvp.dmz}','cy')">修改</a>
										</s:if>
									</s:else>
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
