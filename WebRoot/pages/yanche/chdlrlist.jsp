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
		<title>代理人审核信息列表</title>
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
			$(document).ready(function(){
				
			});
			
			function insertPdasmycChdlr() {
				window.location.href = "<%=request.getContextPath()%>/yanche/chdlr_insertPdasmycChdlr.action";
			}
			function deleteOne(id) {
				if(window.confirm('是否要删除选择的代理人审核信息?')){
					$.ajax({
						type:'GET',
						url: '<%=request.getContextPath()%>/yanche/chdlr_deletePdasmycChdlr.action',
						data:{PdasmycChdlrId:id},//发送的参数
						dataType: 'html',
						success:function(data){
							if(data == 0){
							    alert("删除代理人审核信息成功!");
							    $("#"+id).parents("tr").remove();
							}else if(data == 1){
								alert("删除代理人审核信息失败!");
							}else{
								alert("系统繁忙,请稍候再试!");
							}
						}
					});
				}
			}
			
			function windowopen(id,ywlx){
				var info = window.open('<%=request.getContextPath()%>/yanche/chdlr_initPdasmycChdlr.action?PdasmycChdlr.id=' + id + '&ywlx=' + ywlx,'info','width='+(window.screen.availWidth-10)+',height='+(window.screen.availHeight-30)+ ',top=0,left=0,toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=yes');
			}
			
			function shenhe(id,xm,sfzmhm,dlrzh,chmc){
				var tablestr = "<table class='datalist'>" +
				"<tr><td style='text-align:right;'>姓名:</td><td style='text-align:left;'>&nbsp;"+xm+"</td></tr>" +
				"<tr><td style='text-align:right;'>身份证明号码:</td><td style='text-align:left;'>&nbsp;"+sfzmhm+"</td></tr>" + 
				"<tr><td style='text-align:right;'>代理人证号:</td><td style='text-align:left;'>&nbsp;"+dlrzh+"</td></tr>" + 
				"<tr><td style='text-align:right;'>车行名称:</td><td style='text-align:left;'>&nbsp;"+chmc+"</td></tr>" + 
				"<tr><td style='text-align:right;'>审核状态:</td><td style='text-align:left;'>&nbsp;<input type='radio' class='disabled1' name='dlrsh' checked='checked' value='1' />&nbsp;审核通过&nbsp;&nbsp;&nbsp;<input type='radio' class='disabled1' name='dlrsh' value='2' />&nbsp;审核不通过</td></tr>"+
				"<tr><td style='text-align:right;'>审核备注:</td><td style='text-align:left;'>&nbsp;<input type='text' id='shbz' size='30' /></td></tr>"
				"</table>"
				art.dialog({
					width:'50%',
				    content: tablestr,
				    title: '代理人信息审核',
				    okVal: '保存',
				    ok: function () {
				    	var dlrshi,dlrsh,shvalue;
				    	dlrsh = document.getElementsByName("dlrsh");
						for (dlrshi = 0; dlrshi < dlrsh.length; dlrshi ++){
							if(dlrsh[dlrshi].checked){
								shvalue = dlrsh[dlrshi].value;
								break;
							}
						}
						var shbz = $("#shbz").val();
						shbz = encodeURI(shbz);
						shbz = encodeURI(shbz);
						this.close();
						var imgurl = '<%=request.getContextPath()%>/artDialog4.1.6/skins/icons/loading2.gif';
						var chuli = art.dialog({
						    content: '<div style=\"text-align:center; text-indent:-999em; overflow:hidden; background:url('+imgurl+') no-repeat center center;\"><span>loading..</span></div><br/>数据处理中,请稍候.....',
						    title: '数据处理中',
			    			lock: true,
						    opacity: 0.87
						});
						$.ajax({
							type:'GET',
							url: '<%=request.getContextPath()%>/yanche/chdlr_dlrshzt.action',
							data:{id:id,shzt:shvalue,shbz:shbz},//发送的参数
							dataType: 'html',
							success:function(data){
								chuli.close();
								if(data == 0){
									if(shvalue == '1'){
										$("#tdshzt" + id).html("审核通过");
									}else if(shvalue == '2'){
										$("#tdshzt" + id).html("审核不通过");
									}
									$("#tdcz" + id).html("");
								    alert("操作成功!");
								}else if(data == 1){
									alert("操作失败!");
								}else{
									alert("系统异常!");
								}
							}
						});
				        return false;
				    },
				    cancelVal: '关闭',
	    			cancel: true,
	    			lock: true,
				    opacity: 0.87
				});
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
					<form action="<%=request.getContextPath()%>/yanche/chdlr_initPdasmycChdlrList.action" method="post">
						<table class="table1" width="100%" border="0"
							cellpadding="0" cellspacing="0">
							<tr>
								<th class="th1" height="32" colspan="4">
									查询条件
									<input type="hidden" name="chatype" value="${chatype}" />
								</th>
							</tr>
							<tr>
								<td class="tds" style="text-align: right;">
									姓名&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<input type="text" name="dlrxm" value="${dlrxm}" />
								</td>
								<td class="tds" style="text-align: right">
									身份证明号码&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<input type="text" name="dlrsfzmhm" value="${dlrsfzmhm}" />
								</td>
							</tr>
							<tr>
								<td class="tds" style="text-align: right;">
									车行名称&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<input type="text" name="chmc" value="${chmc}" />
								</td>
								<td class="tds" style="text-align: right">
									录入时间&nbsp;
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
								姓名
							</th>
							<th>
								身份证明号码
							</th>
							<th>
								代理人证号
							</th>
							<th>
								车行名称
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
						<s:if test="#request.pdasmycChdlrList.size > 0">
							<s:iterator id="chdlr" value="#request.pdasmycChdlrList" status="st">
								<tr class="<s:if test="#st.odd == false">altrow</s:if>">
									<td>
										${st.count + (map.currentpage-1) * map.pagesize}
									</td>
									<td>
										${chdlr.dlrxm }
									</td>
									<td>
								 		${chdlr.dlrsfzmhm }
									</td>
									<td>
								 		${chdlr.dlrzh }
									</td>
									<td>
										${chdlr.chmc }
									</td>
									<td>
										<s:date name="#chdlr.lrsj" format="yyyy-MM-dd HH:mm:ss"/>
									</td>
									<td id="tdshzt${chdlr.id }">
										<s:if test="#chdlr.shzt == 0">
						    				未审核
						    			</s:if>
						    			<s:elseif test="#chdlr.shzt == 1">
						    				审核通过
						    			</s:elseif>
						    			<s:elseif test="#chdlr.shzt == 2">
						    				审核未通过
						    			</s:elseif>
									</td>
									<td id="tdcz${chdlr.id }">
										<s:if test="#chdlr.shzt == 0">
						    				<a href="javascript:void(0);" onclick="javascript:shenhe('${chdlr.id}','${chdlr.dlrxm }','${chdlr.dlrsfzmhm }','${chdlr.dlrzh }','${chdlr.chmc }');">审核</a>
						    			</s:if>
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
