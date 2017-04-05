<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<title>${editType }重点驾驶人机动车</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/jquery-1.8.2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/public.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/artDialog4.1.6/artDialog.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/myEdit.css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/artDialog4.1.6/_doc/highlight/styles/magula.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/content.css" />
<script type="text/javascript">
	var edittype;
	$(function(){
		edittype = '${editType}';
		if('查看' == edittype){
			$(".disabled").attr("disabled","disabled");
			$("select").attr("disabled","disabled");
			$("textarea").attr("disabled","disabled");
		}else if('修改' == edittype){
			$("input[name='vehDrv']").attr("disabled","disabled");
		}
		
		$("input[name='vehDrv']").change(function(){
			var $hphm = $("#hphm");
			var $hpzl = $("#hpzl");
			var $jszhm = $("#jszhm");
			var $xm= $("#xm");
			if($("input[name='vehDrv']:checked").val()=='VEH'){
				$jszhm.val("");
				$xm.val("");
				$jszhm.parents("tr").hide();
				$xm.parents("tr").hide();
				$hphm.parents("tr").show();
				$hpzl.parents("tr").show();
			}else{
				$hphm.val("");
				$hpzl.val("");
				$hphm.parents("tr").hide();
				$hpzl.parents("tr").hide();
				$jszhm.parents("tr").show();
				$xm.parents("tr").show();
			}
		});
		$("input[name='vehDrv']").change();
	});	
	
	function check(){
		var vehDrv = $("input[name='vehDrv']:checked").val();
		var bool;
		
		if(vehDrv == "VEH"){
			bool = checknotnull(document.getElementById("hphm"),'请输入号牌号码');
			if(bool != "true"){
				return;
			}
			bool = checknotnull(document.getElementById("hpzl"),'请选择号牌种类');
			if(bool != "true"){
				return;
			}
		}else{
			bool = checknotnull(document.getElementById("jszhm"),'请输入驾驶证号码');
			if(bool != "true"){
				return;
			}
			bool = checknotnull(document.getElementById("xm"),'请输入姓名');
			if(bool != "true"){
				return;
			}
		};
		
		bool = checknotnull(document.getElementById("yjnr"),'请输入预警内容');
		if(bool != "true"){
			return;
		}
		
		
		var id=document.getElementById("id").value;
		var hphm=document.getElementById("hphm").value;
		var hpzl=document.getElementById("hpzl").value;
		var jszhm=document.getElementById("jszhm").value;
		var xm=document.getElementById("xm").value;
		var zt=document.getElementById("zt").value;
		var yjnr=document.getElementById("yjnr").value;
		
		var flag = "true";
		
		if(edittype=='添加'){
			$.ajax({
				cache:false,
				async:false,
				type:'POST',
				url: '<%=request.getContextPath()%>/dagl/jdcjsrgl_uniqueCheck.action',
				data:{
					'hphm':hphm,
					'hpzl':hpzl,
					'jszhm':jszhm
				},
				dataType: 'text',
				success:function(data){
					var message = data+"";
					if(message.indexOf('异常信息') == -1){
						if(data!='0'){
							flag = "false";
							if(vehDrv == "VEH"){
								alert("此车牌号码已存在");
							}else{
								alert("此驾驶证号已存在");
							}
						}
					}else{
						flag = "false";
						alert(message);
					}
				}
			});
		}
		
		if(flag == "true"){
			$.ajax({
				cache:false,
				async:false,
				type:'POST',
				url: '<%=request.getContextPath()%>/dagl/jdcjsrgl_editZdjdcjsr.action',
				data:{
					'editType':edittype,
					'zdjdcjsr.id':id,
					'zdjdcjsr.vehDrv':vehDrv,
					'zdjdcjsr.hphm':hphm,
					'zdjdcjsr.hpzl':hpzl,
					'zdjdcjsr.jszhm':jszhm,
					'zdjdcjsr.xm':xm,
					'zdjdcjsr.zt':zt,
					'zdjdcjsr.yjnr':yjnr
				},
				dataType: 'text',
				success:function(data){
					var message = data+"";
					if(message.indexOf('异常信息') == -1){
						if(edittype=='添加'){
							alert("添加成功！");
							window.returnValue = "refresh";
							cleanmyform();
						}else{
							alert("修改成功！");
							window.returnValue = "refresh";
							window.close();
						}
						
					}else{
						alert(message);
					}
				}
			});
		}
	};
	
	function cleanmyform(){
		$(".disabled").val("");
		$("select").val("");
		$("textarea").val("");
	};
	

</script>
</head>
<body class="xzyh">
<div>
<h1 class="h1_t">${editType}重点驾驶人机动车</h1>
	<iframe id="abc" name="abc" width="0" height="0" style="display: none;"></iframe>
		<table class="table_xx" >
			<tr>
				<td colspan="2" class="table_t">${editType }重点驾驶人机动车
					<input type="hidden" id="id" value="${zdjdcjsr.id}"/>
				</td>
			</tr>
			<tr>
				<td class="td_r">类型：</td>
				<td ><span class="td_l">&nbsp;
				   	  <input type="radio" name="vehDrv" value="VEH" <s:if test="#request.zdjdcjsr.vehDrv=='VEH' || #request.zdjdcjsr.vehDrv ==null">checked="checked"</s:if> />机动车<input type="radio" name="vehDrv" value="DRV" <s:if test="#request.zdjdcjsr.vehDrv=='DRV'">checked="checked"</s:if> />驾驶人
				    </span>
				</td>
			</tr>
			<tr>
				<td class="td_r">号牌号码：</td>
				<td><span class="td_l">&nbsp;
						<input type="text" class="disabled" id="hphm" value="${zdjdcjsr.hphm}"  size="45" maxlength="100" style="width: 245px;" onblur="clearallspace(this);toUpers(this);"/><span style="color:red;">不要加省份简称</span>
				</span>
				</td>
			</tr>
			<tr>
				<td class="td_r">号牌种类：</td>
				<td ><span class="td_l">&nbsp;
					
					<select id="hpzl">
						<option value="">---请选择---</option>
						<s:iterator value="#session.hpzlList" id="temp">
							<option <s:if test="#request.zdjdcjsr.hpzl==#request.temp[0]">selected="selected"</s:if> value="${temp[0]}">${temp[0]}-${temp[1]}</option>
						</s:iterator>
					</select>
				</span>
			</tr>
			<tr>
				<td class="td_r">驾驶证号码：</td>
				<td><span class="td_l">
					&nbsp;
					<input type="text" class="disabled" id="jszhm" value="${zdjdcjsr.jszhm}"  size="45" maxlength="100" style="width: 245px;" onblur="clearallspace(this)"/>
				</span>
				</td>
			</tr>
			<tr>
				<td class="td_r">姓名：</td>
				<td><span class="td_l">&nbsp;
					<input type="text" class="disabled" id="xm" value="${zdjdcjsr.xm}"  size="45" maxlength="100" style="width: 245px;" onblur="clearallspace(this)"/>
				</span>
				</td>
			</tr>
			<tr>
				<td class="td_r">状态：</td>
				<td><span class="td_l">&nbsp;
				<s:select list="#{'0':'预警监控','1':'停止预警'}" theme="simple" id="zt" listKey="key" listValue="value"  value="#request.zdjdcjsr.zt" style="width:100px;"></s:select></span></td>
			</tr>
			<tr>
				<td class="td_r">预警内容：</td>
				<td ><span class="td_l">&nbsp;
				<textarea class="disabled" id="yjnr" cols="35" rows="3" style="width: 245px;">${zdjdcjsr.yjnr}</textarea></span></td>
			</tr>
			<tr>
				<td colspan="2" class="djan">
					<s:if test="#request.editType != '查看'">
		   				<input class="bnt" type="button" name="Submit" onclick="check()" value="提 交" >&nbsp;&nbsp;
		   				<input class="bnt" type="button" name="Submit" onclick="javascript:window.close();" value="关闭" >
		   			</s:if>
		   			<s:else>
		   				<input class="bnt_a" type="button" name="Submit" onclick="javascript:window.close();" value="关闭" >
		   			</s:else>
				</td>
			</tr>
		</table>
</div>
</body>
</html>