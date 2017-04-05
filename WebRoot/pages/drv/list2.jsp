<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
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
		<script type="text/javascript">
			var chuli;
			$(document).ready(function(){
				var exportData = '${request.exportData}';
				var dept = '${request.jbrbm}';
				$("#jbrbm").val(dept);
				var shzt = '${request.shzt}';
				$("#shzt").val(shzt);
				var slzt = '${request.slzt}';
				$("#slzt").val(slzt);
				if(exportData != null && exportData != ""){
					alert(exportData);
				}
			});
			
			function insertSlgDrvXxcjb() {
				window.location.href = "<%=request.getContextPath()%>/drv/insertSlgDrvXxcjb.action";
			}
			function deleteOne(id) {
				if(window.confirm('是否要删除选择的采集信息?')){
					$.ajax({
						type:'GET',
						url: '<%=request.getContextPath()%>/drv/deleteSlgDrvXxcjb.action',
						data:{cjid:id},//发送的参数
						dataType: 'html',
						success:function(data){
							if(data == 0){
							    alert("删除采集信息成功!");
							    $("#"+id).parents("tr").remove();
							}else if(data == 1){
								alert("删除采集信息失败!");
							}else{
								alert("系统繁忙,请稍候再试!");
							}
						}
					});
				}
			}
			
			function windowopen(id,ywlx){
				var lookinfo = window.open('<%=request.getContextPath()%>/drv/initSlgDrvXxcjb2.action?slgDrvXxcjb.cjid=' + id + '&ywlx=' + ywlx,'lookinfo','width='+(window.screen.availWidth-10)+',height='+(window.screen.availHeight-30)+ ',top=0,left=0,toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=yes');
			}
			function windowopenprint(id){
				var print = window.open('<%=request.getContextPath()%>/drv/print.action?slgDrvXxcjb.cjid=' + id);
			}
			function windowopensh(id,ywlx){
				var info = window.open('<%=request.getContextPath()%>/drv/initSHSlgDrvXxcjb.action?slgDrvXxcjb.cjid=' + id + '&ywlx=' + ywlx,'info','width='+(window.screen.availWidth-10)+',height='+(window.screen.availHeight-30)+ ',top=0,left=0,toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=yes');
			}
			
			function exports(){
				var count = '${map.rscount}';
				if(count > 10000){
					alert("数据量太多无法一次导出，请筛选！");
					return false;
				}else if(count > 1000){
					if(confirm("数据量较多，需要较长时间，请耐心等候!")){
						var imgurl = '<%=request.getContextPath()%>/artDialog4.1.6/skins/icons/loading2.gif';
						chuli = art.dialog({
						    content: '<div style=\"text-align:center; text-indent:-999em; overflow:hidden; background:url('+imgurl+') no-repeat center center;\"><span>loading..</span></div><br/>数据下载中,请稍候.....',
						    title: '数据下载中',
			    			lock: true,
						    opacity: 0.87
						});
						dingshiqi();
						$("#esfrom").attr("action", "<%=request.getContextPath()%>/drv/initSlgDrvXxcjbExport.action");
						$("#esfrom").submit();
					}
				}else{
					var imgurl = '<%=request.getContextPath()%>/artDialog4.1.6/skins/icons/loading2.gif';
					chuli = art.dialog({
					    content: '<div style=\"text-align:center; text-indent:-999em; overflow:hidden; background:url('+imgurl+') no-repeat center center;\"><span>loading..</span></div><br/>数据下载中,请稍候.....',
					    title: '数据下载中',
		    			lock: true,
					    opacity: 0.87
					});
					dingshiqi();
					$("#esfrom").attr("action", "<%=request.getContextPath()%>/drv/initSlgDrvXxcjbExport.action");
					$("#esfrom").submit();
				}
			}
			
			function goURL(){
				$("#esfrom").attr("action", "<%=request.getContextPath()%>/drv/initSlgDrvXxcjbList2.action");
				$("#esfrom").submit();
			}
			
			function dingshiqi(){
				setTimeout(function(){chuli.close();},20000);
			}
			function load(){
				var len = $("#jbrbm option").length;
				if(len == 1){
					$("#esfrom").submit();
				}
			}
			
			function updatezt(cjid,zt){
				if("1" == zt){
					$("#"+cjid+"zt").html("<font color='green'>审核通过</font>");
				}else if("2" == zt){
					$("#"+cjid+"zt").html("<font color='red'>审核未通过</font>");
				}else if("3" == zt){
					$("#"+cjid+"zt").html("<font color='red'>不予许可登记</font>");
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

	<body onload="load();" style="background:#c7e5ff;">
		<div class="content1" style="width: 100%;">
			<div class="roundedBox" id="type1" style="width: 95%;">
				<div class="right" style="width: 90%;">
					<form action="<%=request.getContextPath()%>/drv/initSlgDrvXxcjbList2.action" id="esfrom" method="post">
						<table class="table1" width="100%" border="0"
							cellpadding="0" cellspacing="0">
							<tr>
								<th class="th1" height="32" colspan="4">
									查询条件
									<input type="hidden" name="chatype" value="${chatype}" />
									<input type="hidden" name="isnew" value="1" />
								</th>
							</tr>
							<!--<tr>
								<td class="tds" style="text-align: right;">
									业务类型&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<s:select list="#request.slgSjzdList" theme="simple"
										id="sslztid" headerKey="" headerValue="---请选择---"
										listKey="dmz" listValue="dmms1" name="ywmsMain"
										value="#request.ywmsMain"></s:select>
								</td>
								<td class="tds" style="text-align: right">
									档案编号&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<input type="text" name="dabh" value="${dabh}" />
								</td>
							</tr>-->
							<tr>
								<!--<td class="tds" style="text-align: right;">
									姓名&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<input type="text" name="xm" value="${xm}" />
								</td>-->
								<td class="tds" style="text-align: right">
									身份证明号码&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<input type="text" name="sfzmhm" value="${sfzmhm}" onkeyup="clearspace(this)" onblur="clearallspace(this)"/>
								</td>
								<td class="tds" style="text-align: right">
									姓名&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<input type="text" name="xm" value="${xm}" onkeyup="clearspace(this)" onblur="clearallspace(this)"/>
								</td>
							</tr>
							<tr>
								<td class="tds" style="text-align: right">
									经办人&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<input type="text" name="jbrxm" value="${jbrxm}" onkeyup="clearspace(this)" onblur="clearallspace(this)"/>
								</td>
								<td class="tds" style="text-align: right">
									经办人部门&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<select name="jbrbm" id="jbrbm" style="width:120px;">
										<option value="">===请选择===</option>
										<s:iterator id="dept" value="#request.deptList" >
											<option value="${dept[0]}">${dept[1]}</option>
										</s:iterator>
									</select>
								</td>
							</tr>
							<tr>
								<td class="tds" style="text-align: right">
									采集日期&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									起
									<input name="s_date" id="s_date" value="${s_date}" size="10" type="text"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
									止
									<input name="e_date" id="e_date" value="${e_date}" size="10" type="text"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
								</td>
								<td class="tds" style="text-align: right">
									审核状态&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<select name="shzt" id="shzt" style="width:120px;">
										<option value="">===请选择===</option>
										<option value="0">未审核</option>
										<option value="1">审核通过</option>
										<option value="2">审核未通过</option>
										<option value="3">不予许可登记</option>
									</select>
								</td>
							</tr>
							<!--<tr>
								<td class="tds" style="text-align: right">
									采集日期&nbsp;
								</td>
								<td class="tdl" style="text-align: left;" colspan="3">
									起
									<input name="s_date" id="s_date" value="${s_date}" size="10" type="text"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
									止
									<input name="e_date" id="e_date" value="${e_date}" size="10" type="text"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
								</td>
							</tr>-->
							<tr>
								<td class="tds"  style="text-align: right">
									审核人&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<input type="text" name="shxm" value="${shxm}" onkeyup="clearspace(this)" onblur="clearallspace(this)"/>
								</td>
								<td class="tds"  style="text-align: right">
									受理状态&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<select name="slzt" id="slzt" style="width:120px;">
										<option value="">===请选择===</option>
										<option value="0">正常受理</option>
										<option value="1">不予受理登记</option>
									</select>
								</td>
							</tr>
							<tr>
								<td class="tds" colspan="4">
									<div align="center">
										<input class="bnt" type="button" onclick="goURL();" value="查  询" style="cursor:pointer;" />
										<input class="bnt" type="reset" value="重  置" style="cursor:pointer;" />
									</div>
								</td>
							</tr>
						</table>
					</form>
					<div style="width: 98%;height: 35px;">
						<input class="bnt" type="button" value="导出Excel" onclick="javascript:exports();" id="excelid" style="cursor:pointer;" />
					</div>
					<table class="datalist" width="100%" border="0"
						cellpadding="0" cellspacing="0">
						<tr class="tr1">
							<th>
								序号
							</th>
							<th width="200">
								身份证明号码
							</th>
							<th>
								姓名
							</th>
							<th>
								档案编号
							</th>
							<th>
								业务类型
							</th>
							<th>
								采集时间
							</th>
							<th>
								受理状态
							</th>
							<th>
								审核状态
							</th>
							<th>
								操作
							</th>
						</tr>
						<s:if test="#request.slgDrvXxcjbList.size > 0">
							<s:iterator id="xxcjb" value="#request.slgDrvXxcjbList" status="st">
								<tr class="<s:if test="#st.odd == false">altrow</s:if>">
									<td>
										${st.count + (map.currentpage-1) * map.pagesize}
									</td>
									<td width="200">
										<!--<s:if test="#xxcjb.ywmsMain == 'MFXX'">满分学习考试业务</s:if>
										<s:elseif test="#xxcjb.ywmsMain == 'CCSL'">初次申领业务</s:elseif>
										<s:elseif test="#xxcjb.ywmsMain == 'ZJSL'">增加准驾车型申领业务</s:elseif>
										<s:elseif test="#xxcjb.ywmsMain == 'JJSL'">持军队、武装警察部队驾驶证申领业务</s:elseif>
										<s:elseif test="#xxcjb.ywmsMain == 'LSXK'">临时机动车驾驶许可申领业务</s:elseif>
										<s:elseif test="#xxcjb.ywmsMain == 'BZHZ'">驾驶证补证换证业务</s:elseif>
										<s:elseif test="#xxcjb.ywmsMain == 'JSZZX'">驾驶证注销业务</s:elseif>
										<s:elseif test="#xxcjb.ywmsMain == 'TJTJXX'">驾驶人提交身体条件证明业务</s:elseif>
										<s:elseif test="#xxcjb.ywmsMain == 'YQHZ'">办理延期驾驶证期满换证业务</s:elseif>
										<s:elseif test="#xxcjb.ywmsMain == 'YQTJ'">办理延期提交《身体条件证明》业务</s:elseif>
										<s:elseif test="#xxcjb.ywmsMain == 'HFJSZG'">恢复驾驶资格业务</s:elseif>
										<s:elseif test="#xxcjb.ywmsMain == 'JWSL'">持境外驾驶证申领驾驶证业务</s:elseif>
										<s:elseif test="#xxcjb.ywmsMain == 'XXMSHZ'">香港驾驶证免试换领驾驶证业务</s:elseif>-->
										${xxcjb.sfzmhm}
									</td>
									<td>
								 		${xxcjb.xm }
									</td>
									<td>
								 		${xxcjb.dabh }
									</td>
									<td>
								 		${xxcjb.ywlx }
									</td>
									<td>
										<s:date name="#xxcjb.czrq" format="yyyy-MM-dd HH:mm:ss"/>
									</td>
									<td>
								 		<s:if test="#xxcjb.bz == null || #xxcjb.bz == ''"><font color="blue">正常受理</font></s:if>
										<s:elseif test="#xxcjb.bz == 1"><font color="red">不予受理登记</font></s:elseif>
									</td>
									<td id="${xxcjb.cjid}zt">
								 		<s:if test="#xxcjb.shJg == 0"><font color="blue">未审核</font></s:if>
										<s:elseif test="#xxcjb.shJg == 1"><font color="green">审核通过</font></s:elseif>
										<s:elseif test="#xxcjb.shJg == 2"><font color="red">审核未通过</font></s:elseif>
										<s:elseif test="#xxcjb.shJg == 3"><font color="red">不予许可登记</font></s:elseif>
									</td>
									<td>
										<c:if test="${xxcjb.czbm == userkjbm}">
											<a href="javascript:void(0);" id="${xxcjb.cjid}" onclick="javascript:windowopensh(${xxcjb.cjid},'${xxcjb.ywmsMain }');">审核</a>
										</c:if>
										<a href="javascript:void(0);" onclick="javascript:windowopen(${xxcjb.cjid},'${xxcjb.ywmsMain }');">查看</a>
										<a href="javascript:void(0);" onclick="javascript:windowopenprint(${xxcjb.cjid});">打印</a>
										<c:if test="${xxcjb.czbm == userbean.bmid}">
											<a href="javascript:void(0);" id="${xxcjb.cjid}" onclick="javascript:deleteOne(${xxcjb.cjid});">删除</a>
										</c:if>
									</td>
								</tr>
							</s:iterator>
						</s:if>
						<s:else>
							<tr>
								<td colspan="6">
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
