<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
  <head>
    <title>${editType }电动车型信息</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/module.css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/jquery-1.8.2.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/artDialog4.1.6/jquery.artDialog.source.js?skin=chrome"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/artDialog4.1.6/plugins/iframeTools.source.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/public.js"></script>

	<script type="text/javascript">
		var cplxval = "";
		var djlxval = "";
		$(document).ready(function() {
			var type = '${editType}';
			cplxval = '${veElectro.clxh}';
			djlxval = '${veElectro.djxh}';
			if(type == 'query'){
				$("input").not("input[type='button']").each(function(){
					$(this).attr("readonly", "readonly");
					$(this).attr("disabled", "disabled");
				});
				$("#zt").attr("disabled", "disabled");
			}
		});
		
		function check(){
			
			var clpp = checknotnull(document.getElementById("clpp"),'请输入车辆品牌');
			if(clpp != "true"){
				return false;
			}
			var clxh = checknotnull(document.getElementById("clxh"),'请输入车辆型号');
			if(clxh != "true"){
				return false;
			}
			var djxh = checknotnull(document.getElementById("djxh"),'请输入电机型号');
			if(djxh != "true"){
				return false;
			}
			
			var gcjk = $("input[name='veElectro.gcjk']:checked").val();
			if(gcjk == null || gcjk == ''){
				alert("请选择国产或进口!");
				return false;
			}
			
			var xh = $("#xh").val();
			if(xh != null && xh != ""){
				var clxh = $("#clxh").val();
				var djxh = $("#djxh").val();
				if(clxh != cplxval || djxh != djlxval){
					$("#yz_flag").val("1");
				}
			}else{
				$("#yz_flag").val("1");
			}
			$.ajax({
				cache:false,
				async:false,
				url:'<%=request.getContextPath()%>/ddc/ddc_editVehtypeElectro.action',
				type:'post',
				data:$("#veElectro_form").serialize(),
				dataType:'json',
				error:function(XmlHttpRequest,textStatus, errorThrown){
			   		alert("编辑角色信息失败!");
					exception(XmlHttpRequest.responseText);
					return false;
				},
				success: function(result){
					if(result == "1"){
						if(xh == null || xh == ""){
							alert("添加成功!");
						}else{
							alert("修改成功!");
						}
						var win = art.dialog.open.origin; 
						win.location.href="<%=request.getContextPath()%>/ddc/ddc_getvehTypeElectroList.action";
						window.close(); 
						art.dialog.close();
					}else if(result == "2"){
						alert("车辆型号和电机型号已存在");
					}else{
						alert("编辑失败!");
					}
				}
			});
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
		
		function artclose(){
			var win = art.dialog.open.origin;//来源页面
			// 如果父页面重载或者关闭其子对话框全部会关闭
			//win.location.reload();
			//return false;  
			window.close();  
			art.dialog.close(); 
		}
		
	</script>
  </head>
  <body>
	<center>
		<div id="admin_main">
			<form action="<%=request.getContextPath()%>/ddc/ddc_editVehtypeElectro.action" method="post" id="veElectro_form" name="role_form">
				<table border="0" cellpadding="0" cellspacing="0" class="edittable">
					<tr>
						<td style="text-align:right; width: 120px;"><font style="color:red;">*</font>车辆品牌：
						</td>
						<td class="trs" style="text-align:left;">&nbsp;<input type="text" id="clpp" name="veElectro.clpp" value="${veElectro.clpp}" maxlength="50" size="25"/>
							<font id="nameDesc" style="color: red;"></font>
						</td>
					</tr>
					<tr>
						<td style="text-align:right;"><font style="color:red;">*</font>车辆型号：</td>
						<td class="trs" style="text-align:left;">
							&nbsp;<input type="text" id="clxh" name="veElectro.clxh"  value="${veElectro.clxh}" maxlength="50" size="25"/>
						</td>
					</tr>
					<tr>
						<td style="text-align:right;"><font style="color:red;">*</font>电机型号：</td>
						<td class="trs" style="text-align:left;">
							&nbsp;<input type="text" id="djxh" name="veElectro.djxh"  value="${veElectro.djxh}" maxlength="50" size="25"/>
						</td>
					</tr>
					<tr>
						<td style="text-align:right;"><font style="color:red;">*</font>国产进口：</td>
						<td class="trs" style="text-align:left;">
							&nbsp;<input type="radio"  name="veElectro.gcjk"  value="0" <s:if test='#request.veElectro.gcjk == 0'>checked</s:if>/>国产&nbsp;&nbsp;
							<input type="radio"  name="veElectro.gcjk"  value="1" <s:if test='#request.veElectro.gcjk == 1'>checked</s:if> />进口
						</td>
					</tr>
					<tr>
						<td style="text-align:right;">是否启用：</td>
						<td style="text-align:left;">
							&nbsp;<select id="zt" style="width: 153px;" name="veElectro.zt">
								<option value="T" <s:if test='#request.veElectro.zt == "T"'>selected="selected"</s:if>>启用</option>
								<option value="F" <s:if test='#request.veElectro.zt == "F"'>selected="selected"</s:if>>停用</option>
							</select>
						</td>
					</tr>
					<tr>
						<td colspan="2" height="50" align="center">
							<s:if test="#request.editType != 'query'">
			    				<input type="button" id="tijiao" onclick="javascript:check();" value="提 交" style="cursor:pointer;width: 79px;height: 28px;border: none;background: url(<%=request.getContextPath()%>/images/an2.gif) no-repeat;color: #FFFFFF;font-weight: bold;">
			    				<input type="button" onclick="javascript:artclose();" value="关闭" style="cursor:pointer;width: 79px;height: 28px;border: none;background: url(<%=request.getContextPath()%>/images/an2.gif) no-repeat;color: #FFFFFF;font-weight: bold;">
			    			</s:if>
			    			<s:else>
			    				<input type="button" onclick="javascript:artclose();" value="关闭" style="cursor:pointer;width: 79px;height: 28px;border: none;background: url(<%=request.getContextPath()%>/images/an2.gif) no-repeat;color: #FFFFFF;font-weight: bold;">
			    			</s:else>
							
						</td>
					</tr>
				</table>
				<div style="padding-top: 10px; color: red; font-size: 12px;">
					车辆型号和电机型号组合不能重复
				</div>
				<input type="hidden" id="xh" name="veElectro.xh" value="${veElectro.xh}">
				<input type="hidden" name="veElectro.lrsj" value="${veElectro.lrsj}">
				<input type="hidden" name="veElectro.synFlag" value="UW">
				<input type="hidden" name="veElectro.tranFlag" value="${veElectro.tranFlag}">
				<input type="hidden" name="veElectro.tranDate" value="${veElectro.tranDate}">
				<input type="hidden" id="yz_flag" name="yz_flag" value="0"/>
			</form>
		</div>
	</center>
	<div></div>
  </body>
</html>