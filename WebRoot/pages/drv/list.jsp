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
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery/css/redmond/jquery-ui-1.9.1.custom.css" />
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery/css/redmond/jquery.ui.all.css" />
		<script type="text/javascript">
			$(document).ready(function(){
				
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
				var info = window.open('<%=request.getContextPath()%>/drv/initSlgDrvXxcjb.action?slgDrvXxcjb.cjid=' + id + '&ywlx=' + ywlx,'info','width='+(window.screen.availWidth-10)+',height='+(window.screen.availHeight-30)+ ',top=0,left=0,toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=yes');
			}
			function windowopenprint(id){
				var print = window.open('<%=request.getContextPath()%>/drv/print.action?slgDrvXxcjb.cjid=' + id);
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
					<form action="<%=request.getContextPath()%>/drv/initSlgDrvXxcjbList.action" method="post">
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
									业务类型&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<s:select list="#request.slgYwlxList" theme="simple"
										id="sslztid" headerKey="" headerValue="---请选择---"
										listKey="ywms_main" listValue="ywms_main_ch" name="ywmsMain"
										value="#request.ywmsMain"></s:select>
								</td>
								<td class="tds" style="text-align: right">
									档案编号&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<input type="text" name="dabh" value="${dabh}" />
								</td>
							</tr>
							<tr>
								<td class="tds" style="text-align: right;">
									姓名&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<input type="text" name="xm" value="${xm}" />
								</td>
								<td class="tds" style="text-align: right">
									身份证明号码&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<input type="text" name="sfzmhm" value="${sfzmhm}" />
								</td>
							</tr>
							<tr>
								<td class="tds" style="text-align: right;">
									统一版流水号&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<input type="text" name="lsh" value="${lsh}" />
								</td>
								<td class="tds" style="text-align: right">
									采集日期&nbsp;
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
										<input class="bnt" type="reset" value="重  置" style="cursor:pointer;" />
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
								业务类型
							</th>
							<th>
								流水号
							</th>
							<th>
								档案编号
							</th>
							<th>
								姓名
							</th>
							<th>
								采集时间
							</th>
							<!-- <th>
								状态
							</th> -->
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
									<td>
										<s:if test="#xxcjb.ywmsMain == 'MFXX'">满分学习考试业务</s:if>
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
										<s:elseif test="#xxcjb.ywmsMain == 'XXMSHZ'">香港驾驶证免试换领驾驶证业务</s:elseif>
									</td>
									<td>
										${xxcjb.lsh }
									</td>
									<td>
								 		${xxcjb.dabh }
									</td>
									<td>
								 		${xxcjb.xm }
									</td>
									<td>
										<s:date name="#xxcjb.czrq" format="yyyy-MM-dd HH:mm:ss"/>
									</td>
									<!-- <td>
								 		${xxcjb.zt }
									</td> -->
									<td>
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
								<td colspan="7">
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
