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
		<title>报废车信息列表</title>
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
			//修改是否有效
			function sfyx(xh){
				art.dialog({
					width:'50%',
				    content: document.getElementById("sfyxdivid"),
				    title: '修改是否有效',
				    okVal: '保存',
				    ok: function () {
				    	var zt = "";
				    	$("input[name=zt][checked]").each(function(){
			    			zt = $(this).val();
						});
						var bz = $("#bz").val();
						bz = encodeURI(bz);
						bz = encodeURI(bz);
						this.close();
						var imgurl = '<%=request.getContextPath()%>/artDialog4.1.6/skins/icons/loading2.gif';
						var chuli = art.dialog({
						    content: '<div style=\"text-align:center; text-indent:-999em; overflow:hidden; background:url('+imgurl+') no-repeat center center;\"><span>loading..</span></div><br/>数据处理中,请稍候.....',
						    title: '数据处理中',
			    			lock: true,
						    opacity: 0.87
						});
						$.ajax({
							type:'POST',
							url: '<%=request.getContextPath()%>/bfc/bfc_bfcztupdate.action?xh='+xh+'&zt=' + zt + '&bz=' + bz,
							dataType: 'html',
							success:function(data){
								chuli.close();
								if(data == 0){
								    alert("修改成功!");
								    //location.reload();
								    window.location.href = '<%=request.getContextPath()%>/bfc/bfc_initBfcJbxxbList.action';
								}else if(data == 1){
									alert("修改失败!");
								}else{
									alert("修改失败!");
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
					<form action="<%=request.getContextPath()%>/bfc/bfc_initBfcJbxxbList.action" method="post">
						<table class="table1" width="100%" border="0"
							cellpadding="0" cellspacing="0">
							<tr>
								<th class="th1" height="32" colspan="4">
									查询条件
								</th>
							</tr>
							<tr>
								<td class="tds" style="text-align: right;">
									号牌号码&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<input type="text" name="hphm" value="${hphm}" />
								</td>
								<td class="tds" style="text-align: right">
									号牌种类&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<s:select list="#request.slgSjzdList" theme="simple"
										id="sslztid" headerKey="" headerValue="---请选择---"
										listKey="dmz" listValue="dmms1" name="hpzl"
										value="#request.hpzl"></s:select>
								</td>
							</tr>
							<tr>
								<td class="tds" style="text-align: right">
									身份证明号码&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<input type="text" name="sfzmhm" value="${sfzmhm}" />
								</td>
								<td class="tds" style="text-align: right;">
									&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									&nbsp;
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
								号牌号码
							</th>
							<th>
								号牌种类
							</th>
							<th>
								身份证明名称
							</th>
							<th>
								身份证明号码
							</th>
							<th>
								所有人
							</th>
							<th>
								车辆识别代号
							</th>
							<th>
								强制报废日期
							</th>
							<th>
								状态
							</th>
							<th>
								是否有效
							</th>
							<th>
								操作
							</th>
						</tr>
						<s:if test="#request.bfcJbxxbList.size > 0">
							<s:iterator id="bfcxx" value="#request.bfcJbxxbList" status="st">
								<tr class="<s:if test="#st.odd == false">altrow</s:if>">
									<td>
										${bfcxx.xh}
									</td>
									<td>
										${bfcxx.hphm }
									</td>
									<td>
										${bfcxx.hpzl }
									</td>
									<td>
										<c:if test="${bfcxx.sfzmmc == 'A'}">二代居民身份证</c:if>
										<c:if test="${bfcxx.sfzmmc == 'B'}">组织机构代码证书</c:if>
										<c:if test="${bfcxx.sfzmmc == 'C'}">军官证</c:if>
										<c:if test="${bfcxx.sfzmmc == 'D'}">士兵证</c:if>
										<c:if test="${bfcxx.sfzmmc == 'E'}">军官离退休证</c:if>
										<c:if test="${bfcxx.sfzmmc == 'F'}">境外人员身份证明</c:if>
										<c:if test="${bfcxx.sfzmmc == 'G'}">外交人员身份证明</c:if>
										<c:if test="${bfcxx.sfzmmc == 'H'}">居民户口薄</c:if>
										<c:if test="${bfcxx.sfzmmc == 'J'}">单位注销证明</c:if>
										<c:if test="${bfcxx.sfzmmc == 'K'}">居住暂住证明</c:if>
										<c:if test="${bfcxx.sfzmmc == 'L'}">驻华机构证明</c:if>
										<c:if test="${bfcxx.sfzmmc == 'M'}">临时居民身份证</c:if>
									</td>
									<td>
										${bfcxx.sfzmhm }
									</td>
									<td>
								 		${bfcxx.syr }
									</td>
									<td>
								 		${bfcxx.clsbdh }
									</td>
									<td>
										<s:date name="#bfcxx.qzbfqz" format="yyyy-MM-dd"/>
									</td>
									<td>
								 		${bfcxx.zt }
									</td>
									<td>
								 		<c:if test="${bfcxx.sfyx == '0'}">有效</c:if>
										<c:if test="${bfcxx.sfyx == '1'}">无效</c:if>
									</td>
									<td>
										<a href="javascript:void(0);" id="${bfcxx.xh}" onclick="javascript:sfyx(${bfcxx.xh});">修改</a>
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
		<div id="sfyxdivid"
			style="display:none;width:450px;height: 50px;">
			<table width="90%" border="0" cellpadding="0" cellspacing="0" class="edittable">
				<tr class="tr1">
					<td style="text-align:right;width:40%;">
						是否有效：
					</td>
					<td>
						<input type="radio" name="zt" value="0" checked="checked" />有效&nbsp;&nbsp;<input type="radio" name="zt" value="1" />无效
					</td>
				</tr>
				<tr class="tr1">
					<td style="text-align:right;width:40%;">
						备注：
					</td>
					<td>
						<input type="text" id="bz" size="40" />
					</td>
				</tr>
			</table>
		</div>
	</body>
</html>
