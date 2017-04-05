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
		<title>待验车分配信息列表</title>
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
			function view(pch){
				var vehpcbview = window.open('yanche/vehpcb_initPdasmycVehPcbViewList2.action?pch=' + pch + '&type=1','vehpcbview','width=900,height=500,top='+(screen.height-500)/2+',left='+(screen.width-900)/2+',toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=yes');
			}
			function fenpei(pch,yni){
				if("0" != yni){
					alert("系统提示，还有"+yni+"条预受理数据未同步至内网，请稍后再验或联系相关人员同步数据！");
					return false;
				}else{
					var vehpcbview = window.open('yanche/vehpcb_initPdasmycVehPcbViewList.action?pch=' + pch + '&type=2','vehpcbview','width=900,height=500,top='+(screen.height-500)/2+',left='+(screen.width-900)/2+',toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=yes');
				}
			}
			function tuiban(pch){
				var vehpcbview = window.open('yanche/vehpcb_initPdasmycVehPcbViewList.action?pch=' + pch + '&type=3','vehpcbview','width=900,height=500,top='+(screen.height-500)/2+',left='+(screen.width-900)/2+',toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=yes');
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
					<form action="<%=request.getContextPath()%>/yanche/vehpcb_initPdasmycVehPcbList.action" method="post">
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
									状态&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<s:select list="#{'0':'待分配验车','1':'已分配验车' }" theme="simple"
										id="ztid" listKey="key" listValue="value" name="zt"
										value="#request.zt"></s:select>
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
									<td id="tdshzt${vehpcb[0] }">
										${vehpcb[5] }
									</td>
									<td id="tdcz${vehpcb[0] }">
					    				<a href="javascript:void(0);" onclick="javascript:view('${vehpcb[0]}');">查看</a>
				    					<a href="javascript:void(0);" onclick="javascript:fenpei('${vehpcb[0]}','${vehpcb[7]}');">
				    						<c:if test="${vehpcb[5] =='未分配验车' || vehpcb[5] =='部分已分配验车'}">
				    							验车分配
				    						</c:if>
				    						<c:if test="${vehpcb[5] =='已分配验车'}">
				    							重新验车分配
				    						</c:if>
				    					</a>
					    				<a href="javascript:void(0);" onclick="javascript:tuiban('${vehpcb[0]}');">退办</a>
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
