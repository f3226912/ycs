<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<title>考官排班列表</title>
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
		<script type="text/javascript"
	src="<%=request.getContextPath()%>/artDialog4.1.6/jquery.artDialog.source.js?skin=chrome"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/artDialog4.1.6/plugins/iframeTools.source.js"></script>
		<script type="text/javascript">
			$(document).ready(function(){
				
			});
			
			var i = 0;
			
			function updatePb(pbId,pbrq,pbr){
				
				if(i == 0){
					i = 1;
					var selContent = "<select name='bgkg' id= 'bgkg'>";
				
					// 取得当日排班信息
					$.ajax({
						cache:false,
						async:false,
						type:'POST',
						url: '<%=request.getContextPath()%>/kgpb/pbxxb_initUpdatePb.action',
						dataType: 'json',
						data:{id:pbId},
						success:function(data){
							for(var i = 0; i < data.length; i++){
								selContent += "<option value='"+data[i][0]+"'>"+data[i][1]+"</option>"
							}
						}
					});
					
					selContent += "</select>"
					
					
					
					art.dialog({
						width:'30%',
					    content: "<table><tr><td>排班日期："+pbrq+"</td></tr>" +
					    			"<tr><td>原排班考官："+pbr+"</td></tr>" +
					    			"<tr><td>新排班考官："+selContent+"</td></tr></table>",
					    title: '修改排班',
					    okVal: '保存',
					    ok: function () {
					    	var xpbmj = document.getElementById("bgkg").value;
							this.close();
							i = 0;
							$.ajax({
								type:'POST',
								url: '<%=request.getContextPath()%>/kgpb/pbxxb_updatePb.action',
								dataType: 'html',
								data:{id:pbId,xpbmj:xpbmj},
								success:function(data){
									if(data == 0){
									    alert("修改成功!");
									    //location.reload();
									    window.location.href = '<%=request.getContextPath()%>/kgpb/pbxxb_getPbInfo.action?';
									}else if(data == 1){
										alert("记录修改成功，短信发送失败!");
									}else{
										alert("修改失败!");
									}
								}
							});
					        return false;
					    },
					    cancelVal: '关闭',
		    			cancel: function(){
					    	i = 0;
					    	this.close();
		    			},
		    			lock: false,
					    opacity: 0.87
					});
				}
				
				
			}
			
			function submitFun(){
				document.form1.target = "";
				document.form1.action = "<%=request.getContextPath()%>/kgpb/pbxxb_getPbInfo.action";
				document.form1.submit();
			}
			
			function exportExcle(){
				document.form1.target = "abc";
				document.form1.action = "<%=request.getContextPath()%>/kgpb/pbxxb_getInfoExport.action";
				document.form1.submit();
			}
			
			function printdiv(printpage){
				if(confirm("确定要打印该页的信息吗？")){
					document.getElementById("divTitle").style.display = "block";
					var headstr = "<html><head><title></title></head><body>";
					var footstr = "</body>";
					//alert(newstr);
					var oldstr = document.body.innerHTML;
					
					$("a").attr("style","text-decoration:none")
					document.getElementById("zmDiv").style.display = "none";
					
					var newstr = document.all.item(printpage).innerHTML;
					
					document.body.innerHTML = headstr+newstr+footstr;
					/*var Wsh = new ActiveXObject("WScript.Shell"); 
					var HKEY_Root,HKRY_Path,HKEY_key;
					HKEY_Root = 'HKEY_CURRENT_USER';
					HKRY_Path = '\\Software\Microsoft\\Internet Explorer\\PageSetup';
					HKEY_key = '\\header';
					Wsh.RegWrite(HKEY_Root+HKRY_Path+HKEY_key,"");
					HKEY_key = '\\footer';
					Wsh.RegWrite(HKEY_Root+HKRY_Path+HKEY_key,"");*/
					
					window.print(); 
					document.body.innerHTML = oldstr;
					
					document.getElementById("divTitle").style.display = "none";
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
		<div class="content1" style="width: 100%;">
			<div class="roundedBox" id="type1" style="width: 95%;">
				<div class="right" style="width: 90%;">
					<form action="<%=request.getContextPath()%>/kgpb/pbxxb_getPbInfo.action" name="form1" id="form1" method="post">
					<iframe id="abc" name="abc" width="0" height="0" style="display: none;"></iframe>
						<table class="table1" width="100%" border="0"
							cellpadding="0" cellspacing="0">
							<tr>
								<th class="th1" height="32" colspan="4">
									查询条件
								</th>
							</tr>
							<tr>
								<td class="tds" style="text-align: right; width: 40%">
									排班日期&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<input type="text" id="pbrq" name="pbrq" value="${pbrq}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
									&nbsp;<font color="red" >(按排班日期，查询未来6天排班)</font>
								</td>
							</tr>
							
							<tr>
								<td class="tds" colspan="3">
									<div align="center">
										<input class="bnt" type="button" onclick="submitFun()" value="查  询" style="cursor:pointer;" />&nbsp;&nbsp;
										<input class="bnt" type="button" onclick="printdiv('dyDiv')" value="打  印" style="cursor:pointer;" />&nbsp;&nbsp;
										<input class="bnt" type="button" onclick="exportExcle()" value="导  出" style="cursor:pointer;" />
									</div>
								</td>
							</tr>
						</table>
					</form>
					
					<div id="dyDiv">
						<div id="divTitle" style="text-align: center; margin-bottom: 10px; display: none;"> <font style="font-size: 16px; font-weight: bold;">考官排班列表</font> </div>
						<table class="datalist" width="100%" border="0"
							cellpadding="0" cellspacing="0">
							<tr class="tr1">
								<th>
									日期/考场
								</th>
								<s:iterator var="kcInfo" value="#request.kcList">
									<th>
										<s:property value="#kcInfo.kcmc" />
									</th>
								</s:iterator>
							</tr>
							<s:if test="#request.pbMap.size > 0">
								<s:iterator var="pbMap_set" value="#request.pbMap.keySet()" status="st" >
									<tr class="<s:if test="#st.odd == false">altrow</s:if>">
										<td><s:property value="#pbMap_set" /></td>
									
										<s:iterator var="kcInfo" value="#request.kcList">
											<td>
												<s:iterator var="kcMap_set" value="#request.pbMap.get(#pbMap_set).keySet()" >
													<s:if test="#kcInfo.kcmc == #kcMap_set">
														<s:iterator var="kgInfo" value="#request.pbMap.get(#pbMap_set).get(#kcMap_set)" >
															<s:if test="#kgInfo[4] == null">
																<a href="javascript: updatePb('<s:property value="#kgInfo[0]" />','<s:property value="#pbMap_set" />','<s:property value="#kgInfo[1]" />')"><s:property value="#kgInfo[1]" /></a>&nbsp;
															</s:if>
															<s:else>
																<s:property value="#kgInfo[1]" />
															</s:else>
														</s:iterator>
													</s:if>
												</s:iterator>
											</td>
										</s:iterator>
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
						<div id="zmDiv" style="margin-top: 10px; "><font color="red">注：点击考官姓名，可人为修改该考官排班信息。</font></div>
					</div>
					
				</div>

				<div class="corner topLeft"></div>
				<div class="corner topRight"></div>
			</div>
		</div>
		
	</body>
</html>
