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
		<title>预约验车部门调整</title>
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
			var chuli;
			function view(pch){
				var vehpcbview = window.open('yanche/vehpcb_initPdasmycVehPcbViewList2.action?pch=' + pch + '&type=1','vehpcbview','width=900,height=500,top='+(screen.height-500)/2+',left='+(screen.width-900)/2+',toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=yes');
			}
			function update(pch,ybm){
				$("#uyybmid").val("");
		    	$("#yyybmid").html(ybm);
		    	$("#yyypchid").html(pch);
				var baocun = art.dialog({
					width:'50%',
				    content: document.getElementById("dfenpei"),
				    title: '修改预约部门',
				    okVal: '保存',
				    ok: function () {
				    	var uyybmid = $("#uyybmid").val();
				    	if(uyybmid == null || "" == uyybmid){
							alert("请选择预约部门!");
							return false;
						}
				    	this.close();
				    	var imgurl = '<%=request.getContextPath()%>/artDialog4.1.6/skins/icons/loading2.gif';
						chuli = art.dialog({
						    content: '<div style=\"text-align:center; text-indent:-999em; overflow:hidden; background:url('+imgurl+') no-repeat center center;\"><span>loading..</span></div><br/>数据处理中,请稍候.....',
						    title: '数据处理中',
			    			lock: true,
						    opacity: 0.87
						});
						$.ajax({
							type:'POST',
							url: '<%=request.getContextPath()%>/yanche/vehpcb_updateYybm.action?pch=' + pch + '&xbm=' + uyybmid,
							dataType: 'html',
							success:function(data){
								chuli.close();
								if(data == 0){
									alert("修改成功!");
									location.reload();
								}else{
									alert("修改失败!");
									exception(data);
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
			
			function closechuli(){
				chuli.close();
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
					<form action="<%=request.getContextPath()%>/yanche/vehpcb_initPdasmycVehPcbList2.action" method="post">
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
									批次号&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<input type="text" name="pch" value="${pch}" />
								</td>
								<td class="tds" style="text-align: right">
									代办机构&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<input type="text" name="dbjg" value="${dbjg}" />
								</td>
							</tr>
							<tr>
								<td class="tds" style="text-align: right">
									预约日期&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									起
									<input name="s_date" id="s_date" value="${s_date}" size="10" type="text"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
									止
									<input name="e_date" id="e_date" value="${e_date}" size="10" type="text"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
								</td>
								<td class="tds" style="text-align: right;">
									&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									
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
								申请批次号
							</th>
							<th>
								代办机构
							</th>
							<th>
								预约验车部门
							</th>
							<th>
								预约批次数量
							</th>
							<th>
								分配数量
							</th>
							<th>
								状态
							</th>
							<th>
								操作
							</th>
						</tr>
						<s:if test="#request.pdasmycVehPcbList.size > 0">
							<s:iterator id="vehpcb" value="#request.pdasmycVehPcbList" status="st">
								<tr class="<s:if test="#st.odd == false">altrow</s:if>">
									<td>
										${st.count + (map.currentpage-1) * map.pagesize}
									</td>
									<td>
										${vehpcb[0] }
									</td>
									<td>
								 		${vehpcb[2] }
									</td>
									<td>
								 		${vehpcb[3] }
									</td>
									<td>
										${vehpcb[1] }
									</td>
									<td>
										${vehpcb[4] }
									</td>
									<td>
										${vehpcb[5] }
									</td>
									<td>
										<a href="javascript:void(0);" onclick="javascript:view('${vehpcb[0]}');">查看</a>
										<s:if test="#vehpcb[6] == 0">
											<a href="javascript:void(0);" onclick="javascript:update('${vehpcb[0]}','${vehpcb[3]}');">修改预约部门</a>
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
		<div id="dfenpei" style="width:500px;display: none;">
			<table class="datalist" style="width:450px;">
				<tr>
					<td style="text-align: right;">
						原预约部门:
					</td>
					<td id="yyybmid" style="text-align: left;">
						
					</td>
				</tr>
				<tr>
					<td style="text-align: right;">
						申请预约批次号:
					</td>
					<td id="yyypchid" style="text-align: left;">
						
					</td>
				</tr>
				<tr>
					<td style="text-align: right;">
						修改预约部门:
					</td>
					<td style="text-align: left;">
						<s:select headerKey="" cssStyle="width:150px;" headerValue="---请选择---" list="#request.vvoylist" theme="simple" listKey="orgId" listValue="orgName"
						 name="uyybm" id="uyybmid"></s:select>
					</td>
				</tr>
			</table>
		</div>
	</body>
</html>
