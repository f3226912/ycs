<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="com.ycszh.common.PageTag" prefix="pt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<title>采集信息列表</title>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/content.css" />
		<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/jquery-1.8.2.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/development-bundle/ui/jquery.ui.core.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/development-bundle/ui/jquery.ui.widget.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/development-bundle/ui/jquery.ui.position.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/development-bundle/ui/jquery.ui.menu.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/development-bundle/ui/jquery.ui.autocomplete.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/jquery-ui-1.9.1.custom.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/public.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/artDialog4.1.6/jquery.artDialog.source.js?skin=chrome"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/artDialog4.1.6/plugins/iframeTools.source.js"></script>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery/css/redmond/jquery-ui-1.9.1.custom.css" />
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery/css/redmond/jquery.ui.all.css" />
		<style>
			.bg{
				background-color: #E8E8DF;
			}
		</style>
		<script type="text/javascript">
			$(function(){
				var msg = '${msg}';
				if(msg!=""){
					alert(msg);
				}
			});
			function edit(id,type){
				var uri="<%=request.getContextPath()%>/dagl/jdcjsrgl_initEditZdjdcjsr.action?id="+id+"&type="+type;		
				var tempNode=window.showModalDialog(uri,window,"dialogHeight:400px;dialogWidth:600px;status:no;scroll:no;center:yes");
				if(tempNode=="refresh"){
					$("#seach").click();
				}
			}
			
			function del(id){
				if(confirm("确定要删除此记录？")){
					$.ajax({
						cache:false,
						async:false,
						type:'POST',
						url: '<%=request.getContextPath()%>/dagl/jdcjsrgl_delZdjdcjsr.action',
						data:{
							'id':id
						},
						dataType: 'text',
						success:function(data){
							var message = data+"";
							if(message.indexOf('异常信息') == -1){
								alert("删除成功");
								$("#seach").click();
							}else{
								alert(message);
							}
						}
					});
				}
			}
			
			function seach(){
				$("#seach").click();
			}
			
			function drvFocus(){
				if($("#hphm").val()!=""||$("#hpzl").val()!=""){
					$("#jszhm").addClass("bg");
					$("#xm").addClass("bg");
					$("#jszhm").attr("disabled","disabled");
					$("#xm").attr("disabled","disabled");
				}else{
					$("#jszhm").removeAttr("disabled","disabled");
					$("#xm").removeAttr("disabled","disabled");
					$("#jszhm").removeClass("bg");
					$("#xm").removeClass("bg");
				}
				
			}
			
			function vehFocus(){
				if($("#jszhm").val()!=""||$("#xm").val()!=""){
					$("#hphm").attr("disabled","disabled");
					$("#hpzl").attr("disabled","disabled");
					$("#hphm").addClass("bg");
					
				}else{
					$("#hphm").removeAttr("disabled","disabled");
					$("#hpzl").removeAttr("disabled","disabled");
					$("#hphm").removeClass("bg");
				}
			}
			
			function vehChange(){
				if($("#hphm").val()!=""||$("#hpzl").val()!=""){
					$("#jszhm").addClass("bg");
					$("#xm").addClass("bg");
					$("#jszhm").attr("disabled","disabled");
					$("#xm").attr("disabled","disabled");
				}else{
					$("#jszhm").removeAttr("disabled","disabled");
					$("#xm").removeAttr("disabled","disabled");
					$("#jszhm").removeClass("bg");
					$("#xm").removeClass("bg");
				}
			}
			
			function drvChange(){
				if($("#jszhm").val()!=""||$("#xm").val()!=""){
					$("#hphm").attr("disabled","disabled");
					$("#hpzl").attr("disabled","disabled");
					$("#hphm").addClass("bg");
					
				}else{
					$("#hphm").removeAttr("disabled","disabled");
					$("#hpzl").removeAttr("disabled","disabled");
					$("#hphm").removeClass("bg");
				}
			}
			
			function putExcel(v){
				if($("#excleFile").val()==""){
					alert("请选择要导入的excel文件");
					return;
				}else{
					$(v).attr("disabled","disabled");
					$("#putExcelForm").submit();
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
					<form action="<%=request.getContextPath()%>/dagl/jdcjsrgl_initZdjdcjsrList.action" id="esfrom" method="post">
						<table class="table1" width="100%" border="0"
							cellpadding="0" cellspacing="0">
							<tr>
								<th class="th1" height="32" colspan="4">
									查询条件
								</th>
							</tr>
								<td class="tds" style="text-align: right">
									号牌号码&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<input type="text" name="hphm" value="${hphm}" onblur="clearallspace(this);toUpers(this);" id="hphm" onfocus="vehFocus();" onchange="vehChange();"/>
								</td>
								<td class="tds" style="text-align: right">
									号牌种类&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<select id="hpzl" name="hpzl" onfocus="vehFocus();" onchange="vehChange();">
										<option value="">---请选择---</option>
										<s:iterator value="#session.hpzlList" id="temp">
											<option <s:if test="#request.hpzl==#request.temp[0]">selected="selected"</s:if> value="${temp[0]}">${temp[0]}-${temp[1]}</option>
										</s:iterator>
									</select>
								</td>
							</tr>
							<tr>
								<td class="tds" style="text-align: right">
									驾驶证号码&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<input type="text" name="jszhm" value="${jszhm}" onblur="clearallspace(this);" id="jszhm" onfocus="drvFocus();" onchange="drvChange();"/>
								</td>
								<td class="tds" style="text-align: right">
									姓名&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<input type="text" name="xm" value="${xm}" onblur="clearallspace(this);" id="xm" onfocus="drvFocus();" onchange="drvChange();"/>
								</td>
							</tr>
							<tr>
								<td class="tds" colspan="4">
									<div align="center">
										<input class="bnt" type="submit" value="查  询" style="cursor:pointer;" id="seach"/>
										<input class="bnt" type="reset" value="重  置" style="cursor:pointer;" />
									</div>
								</td>
							</tr>
						</table>
					</form>
					<div style="width: 98%;height: 35px;">
						<input class="bnt" type="button" value="新增" onclick="edit('','insert');"  style="cursor:pointer; float: left;"/>
						<form id="putExcelForm" action="<%=request.getContextPath()%>/dagl/jdcjsrgl_putExcel.action" method="post" enctype="multipart/form-data" style="float: right;">
							<input type="file" name="excelFile" id="excleFile"/><input type="button" value="导入数据" class="bnt" onclick="putExcel(this);"/>
						</form>
					</div>
					<table class="datalist" width="100%" border="0"
						cellpadding="0" cellspacing="0">
						<tr class="tr1">
							<th>
								序号
							</th>
							<th>
								类型
							</th>
							<th>
								号牌号码
							</th>
							<th>
								号牌种类
							</th>
							<th>
								驾驶证号码
							</th>
							<th>
								姓名
							</th>
							<th >
								预警内容
							</th>
							<th>
								状态
							</th>
							<th>
								录入时间
							</th>
							<th>
								操作
							</th>
						</tr>
						<s:if test="#request.zdjdcjsrList.size > 0">
							<s:iterator id="zdjsrjdc" value="#request.zdjdcjsrList" status="st">
								<tr class="<s:if test="#st.odd == false">altrow</s:if>">
									<td>
										${st.count + (map.currentpage-1) * map.pagesize}
									</td>
									<td>
										<s:if test="#zdjsrjdc.vehDrv == 'VEH'">机动车</s:if>
										<s:else>驾驶人</s:else>
									</td>
									<td>
								 		${zdjsrjdc.hphm }
									</td>
									<td>
										${zdjsrjdc.hpzl }
									</td>
									<td>
								 		${zdjsrjdc.jszhm }
									</td>
									<td>
								 		${zdjsrjdc.xm }
									</td>
									<td  style="width:120px;" title="${zdjsrjdc.yjnr }">
										${zdjsrjdc.yjnr }
									</td>
									<td>
								 		<s:if test="#zdjsrjdc.zt == 0"><font color="red">预警监控</font></s:if>
										<s:elseif test="#zdjsrjdc.zt == 1"><font color="green">停止预警</font></s:elseif>
									</td>
									<td>
										<s:date name="#request.zdjsrjdc.czrq" format="yyyy-MM-dd HH:mm:ss"/>
										
									</td>
									<td>
										<a href="javascript:void(0);" onclick="edit(${id},'update');">修改</a>
										<a href="javascript:void(0);" onclick="del(${id});">删除</a>
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
					<div id="vehPic">
						<p style="color:red; font-size: 12px; margin-bottom: 0px; padding-bottom: 0px;">注：机动车excel文件内容格式</p>
						<img src="../images/vehExcel.jpg" alt="" />
					</div>
				</div>

				<div class="corner topLeft"></div>
				<div class="corner topRight"></div>
			</div>
		</div>
	</body>
</html>
