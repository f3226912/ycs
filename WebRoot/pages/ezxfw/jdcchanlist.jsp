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
    <title>驾驶证补换证信息列表</title>
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
			if(${cx!='cx'}){
				if(${num==1}){
					alert("自动提取审核数据完成！");
					$("#csshrzh").show();
				}else if(${num==0}){
					alert("提取失败，没有可提取数据！");
				}else{
					alert("还有未审核数据,请审核完再进行提取分配！");
					$("#csshrzh").show();
				}
			}
		});
		
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
		
		function windowopen(wwlsh,xg){
			window.open('<%=request.getContextPath()%>/ezxfw/ezxfw_initJdcChanInfo.action?wwlsh=' + wwlsh+'&xg='+xg ,'info','width='+(window.screen.availWidth-10)+',height='+(window.screen.availHeight-30)+ ',top=0,left=0,toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=yes');
		}
		function tqsh(){
			var ywlxval=$("#ywlxtq").val();
			if(ywlxval==''){
				alert("请选择业务类型！");
				return false;
			}else{
				if(confirm("是否提取数据？")==true){
					window.ywlxform.action='<%=request.getContextPath()%>/ezxfw/ezxfw_getTqjdcs.action';
	    			window.ywlxform.submit();
	    		}
			}
			
    		
		}
		function windowopenview(wwlsh){
			window.open('<%=request.getContextPath()%>/ezxfw/ezxfw_initJdcChanInfoview.action?wwlsh=' + wwlsh ,'info','width='+(window.screen.availWidth-10)+',height='+(window.screen.availHeight-30)+ ',top=0,left=0,toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=yes');
		}
		
		function closechuli(){
			chuli.close();
		}
		
		function updatezt(wwlsh,zt){
			var czzt="<a href='javascript:void(0);' id='"+wwlsh+"xg' onclick=\"javascript:windowopen('"+wwlsh+"','xg');\">修改</a>";
			$("#"+wwlsh+"sh").remove();
			$("#"+wwlsh+"xg").remove();
			if("1" == zt){
				$("#"+wwlsh+"zt").html("审核通过");
				$("#"+wwlsh+"czzt").append(czzt);
			}else if("TB" == zt){
				$("#"+wwlsh+"czzt").append(czzt);
				$("#"+wwlsh+"zt").html("退办");
			}
		}
		
	</script>
	
  </head>
  
  <body style="background:#c7e5ff;">
    <div class="content1" style="width: 100%;">
		  <div class="roundedBox" id="type1" style="width: 95%;">
				<div class="right" style="width: 98%;">
					<form action="<%=request.getContextPath()%>/ezxfw/ezxfw_initJdcChanList.action" method="post" id="ezxfwform" name="ezxfwform">
						<table class="table1" width="100%" border="0"
							cellpadding="0" cellspacing="0">
							<tr>
								<th class="th1" height="32" colspan="4">
									查询条件
								</th>
							</tr>
							<tr>
								<td class="tds" style="text-align: right">
									业务类型&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<s:select list="#request.ywlxList" theme="simple"
											id="ywlx"
											listKey="key" listValue="value" headerKey="" headerValue="---请选择---" name="ywlx" value="#request.ywlx"></s:select>&nbsp;
								</td>
								<td class="tds" style="text-align: right;">
									处理状态&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<s:select list="#{'0':'待初审（未分配）','1':'初审通过','2':'车管已制证','3':'待初审(已分配)','TB':'退办'}" theme="simple"
											id="zhclzt"
											listKey="key" listValue="value" headerKey="" headerValue="---请选择---" name="zhclzt" value="#request.zhclzt"></s:select>&nbsp;
								</td>
							</tr>
							<tr>
								<td class="tds" style="text-align: right;">
									号牌号码&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<input type="text" name="hphm" onkeyup="clearspace(this);" onblur="clearallspace(this);" value="${hphm}" />
								</td>
								<td class="tds" style="text-align: right">
									号牌种类&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<s:select list="#request.hpzlSjzdMap" theme="simple"
											id="hpzl"
											listKey="key" listValue="value" headerKey="" headerValue="---请选择---" name="hpzl" value="#request.hpzl"></s:select>&nbsp;
								</td>
							</tr>
							<tr>
								<td class="tds" style="text-align: right;">
									身份证名号码&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<input type="text" name="sfzmhm" onkeyup="clearspace(this);" onblur="clearallspace(this);" value="${sfzmhm}" />
								</td>
								<td class="tds" style="text-align: right">
									姓名&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<input type="text" name="jdcsyr" onkeyup="clearspace(this);" onblur="clearallspace(this);" value="${jdcsyr}" />
								</td>
							</tr>
							<tr>
								<td class="tds" style="text-align: right">
									外网录入日期&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									起
									<input name="s_date" id="s_date" value="${s_date}" size="10" type="text"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
									止
									<input name="e_date" id="e_date" value="${e_date}" size="10" type="text"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
								</td>
								<td class="tds" style="text-align: right">
									数据来源&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<s:select list="#request.yhlyList" theme="simple"
											id="yhly"
											listKey="key" listValue="value" headerKey="" headerValue="---请选择---" name="yhly" value="#request.yhly"></s:select>&nbsp;
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
					<div style="width: 98%;height: 35px;padding-top: 10px;font-size: 14px;">
					<form action="<%=request.getContextPath()%>/ezxfw/ezxfw_getTqjdcs.action" method="post" id="ywlxform" name="ywlxform">
						<td class="tds" style="text-align: right">
							业务类型:&nbsp;
						</td>
						<td>
							<s:select list="#request.ywlxList" theme="simple"
									id="ywlxtq"
									listKey="key" listValue="value" headerKey="" headerValue="---请选择---" name="ywlxtq" value="#request.ywlxtq"></s:select>&nbsp;
						</td>
						<td>
							</td><input class="bnt" id="jdctq" type="button" onclick="tqsh()" value="自动提取" style="cursor:pointer;" />
						</td>
						<td><span style="display:none;" id="csshrzh" >当前审核的批次号为：${cspch}</span></td>
					</form>
					</div>
					<table class="datalist" width="100%" border="0"
						cellpadding="0" cellspacing="0">
						<tr class="tr1">
							<th>
								序号
							</th>
							<th>
								互联网流水号
							</th>
							<th>
								业务类型
							</th>
							<th>
								号牌号码
							</th>
							<th>
								号牌种类
							</th>
							<th>
								身份证明号码
							</th>
							<th>
								机动车所有人
							</th>
							<th>
								网上录入时间
							</th>
							<th>
								最后处理状态
							</th>
							<th>
								操作
							</th>
						</tr>
						<s:if test="#request.ezJdcChanAppList.size > 0">
							<s:iterator id="dc" value="#request.ezJdcChanAppList" status="st">
								<tr class="<s:if test="#st.odd == false">altrow</s:if>">
									<td>
										${st.count + (map.currentpage-1) * map.pagesize}
									</td>
									<td>
										${dc.wwlsh }
									</td>
									<td>
										<s:if test="#request.num==1||#request.num==3">
								 			${dc.bhlx }
								 		</s:if>
								 		<s:else>
								 			${dc.ywlx }
								 		</s:else>
									</td>
									<td>
								 		${dc.hphm }
									</td>
									<td>
									<s:if test="#request.num==1||#request.num==3">
								 			${dc.hpzl2 }
								 		</s:if>
								 		<s:else>
								 			${dc.hpzl }
								 		</s:else>
									</td>
									<td>
										${dc.sfzmhm }
									</td>
									<td>
										${dc.jdcsyr }
									</td>
									<td>
										<s:date name="#dc.wwlrsj" format="yyyy-MM-dd HH:mm:ss"/>
									</td>
									<td id="${dc.wwlsh}zt" class="zh">
										<s:if test="#request.num==1||#request.num==3">
								 			${dc.shzt }
								 		</s:if>
										<s:else>
											${dc.zhclzt }
										</s:else>
									</td>
									<!--  
									<td>
								 		<s:property value="#slgsp.bllx==1?'当事人':'代理人'"/>
									</td>
									-->
									<td id="${dc.wwlsh}czzt">
										<s:if test="(#dc.zhclzt == '待初审(已分配)'&&#dc.csshrzh==#request.csshrzhs)||(#dc.zhclzt == 3&&#dc.csshrzh==#request.csshrzhs)">
											<a href="javascript:void(0);" id="${dc.wwlsh}sh" onclick="javascript:windowopen('${dc.wwlsh }','');">审核</a>
										</s:if>
										<s:elseif test="((#dc.zhclzt == '初审通过'||#dc.zhclzt == '退办')&&#dc.csshrzh==#request.csshrzhs)||((#dc.zhclzt == '1'||#dc.zhclzt == 'TB')&&#dc.csshrzh==#request.csshrzhs)">
											<a href="javascript:void(0);" id="${dc.wwlsh}xg" onclick="javascript:windowopen('${dc.wwlsh }','xg');">修改</a>
										</s:elseif>
										<a href="javascript:void(0);" onclick="javascript:windowopenview('${dc.wwlsh }');">查看</a>
									</td>
								</tr>
							</s:iterator>
						</s:if>
						<s:else>
							<tr>
								<td colspan="10">
									<span id="sj" style="color: red">暂时没有相关数据</span>
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
