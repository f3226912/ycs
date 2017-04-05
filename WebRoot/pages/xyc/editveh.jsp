<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
	<head>
		<title>${editType }嫌疑车</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath()%>/css/content.css" />
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/jquery/js/jquery-1.8.2.js">
</script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/My97DatePicker/WdatePicker.js">
</script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/jquery/development-bundle/ui/jquery.ui.core.js">
</script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/jquery/development-bundle/ui/jquery.ui.widget.js">
</script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/jquery/development-bundle/ui/jquery.ui.position.js">
</script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/jquery/development-bundle/ui/jquery.ui.menu.js">
</script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/jquery/development-bundle/ui/jquery.ui.autocomplete.js">
</script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/jquery/js/jquery-ui-1.9.1.custom.js">
</script>
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath()%>/jquery/css/redmond/jquery-ui-1.9.1.custom.css" />
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath()%>/jquery/css/redmond/jquery.ui.all.css" />
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/public.js">
</script>
		<script type="text/javascript">
			$(document).ready(function() {
			var edittype = '${editType}';
			//request.ycsXycCodeList
			
			if(edittype == '查看'){
				$(".disabled").attr("disabled","disabled");
				$("select").attr("disabled","disabled");
				$("textarea").attr("disabled","disabled");
				$("input:checkbox").attr("disabled","disabled");
				$("input:radio").attr("disabled","disabled");
				var yylx = '${ycsXycVeh.yylx}';
				$("#yylx").val(yylx);
				var ywyy = '${ycsXycVeh.ywyy}';
				var sfzmmc = '${ycsXycVeh.sfzmmc}';
				$("#sfzmmc").val(sfzmmc);
				var hpzl = '${ycsXycVeh.hpzl}';
				$("#hpzl").val(hpzl);
				// 嫌疑原因选中
				var xyyyValue = "<s:property value="#request.ycsXycVeh.xyyy"/>";
				
				
				xyyyValue = xyyyValue.split(",");
				var xyyy = document.getElementsByName("ycsXycVeh.xyyy");
				
				for(var i = 0; i < xyyy.length; i ++){
					for(var b = 0; b < xyyyValue.length; b++){
					
						if(xyyyValue[b].replace(" ","") == xyyy[i].value){
							xyyy[i].checked = true;
						}
					}
				}
				var zrfs = '${ycsXycVeh.zrfs}';
 				if(zrfs==0){
					$(".display_db").show();
					$(".display_pl").hide();
				}else{
					$(".display_db").hide();
					$(".display_pl").show();
				}
			}else if(edittype == '修改'){
				var yylx = '${ycsXycVeh.yylx}';
				$("#yylx").val(yylx);
				var ywyy = '${ycsXycVeh.ywyy}';
				$.ajax({
					type:'POST',
					url:'<%=request.getContextPath()%>/xyc/xycveh_getYwyyList.action',
					data:{ywlx:yylx},
					dataType: 'json',
					error:function(){
						alert("读取数据异常!");
					},
					success:function(data){
						$("#ywyy option").remove();
						if(null != data){
					    	if(data.length > 0){
					    		for(var i = 0; i < data.length; i ++){
					    			if(ywyy == data[i][0]){
					    				var ywyyval = "<option value='"+data[i][0]+"' selected='selected'>" + data[i][1] + "</option>";
						    			$("#ywyy").append(ywyyval);
					    			}else{
					    				var ywyyval = "<option value='"+data[i][0]+"'>" + data[i][1] + "</option>";
						    			$("#ywyy").append(ywyyval);
					    			}
						    	}
					    	}else{
					    		var ywyyval = "<option value=''>---请选择---</option>";
					    		$("#ywyy").append(ywyyval);
					    	}
						}else{
							var ywyyval = "<option value=''>---请选择---</option>";
				    		$("#ywyy").append(ywyyval);
						}
					}
				});
				var sfzmmc = '${ycsXycVeh.sfzmmc}';
				$("#sfzmmc").val(sfzmmc);
				var hpzl = '${ycsXycVeh.hpzl}';
				$("#hpzl").val(hpzl);
				// 嫌疑原因选中
				var xyyyValue = "<s:property value="#request.ycsXycVeh.xyyy"/>";
				
				
				xyyyValue = xyyyValue.split(",");
				var xyyy = document.getElementsByName("ycsXycVeh.xyyy");
				
				for(var i = 0; i < xyyy.length; i ++){
					for(var b = 0; b < xyyyValue.length; b++){
					
						if(xyyyValue[b].replace(" ","") == xyyy[i].value){
							xyyy[i].checked = true;
						}
					}
				}
				
				var zrfs = '${ycsXycVeh.zrfs}';
 				if(zrfs==0){
					$(".display_db").show();
					$(".display_pl").hide();
				}else{
					$(".display_db").hide();
					$(".display_pl").show();
				}
			}else if (edittype == '新增'){
				$(":radio[name='ycsXycVeh.zrfs']").eq(0).attr("checked",true);
				$(":radio[name='ycsXycVeh.zt']").eq(0).attr("checked",true);
				$(".display_db").show();
				$(".display_pl").hide();
			}
			
			$(".radiocss").click(function(){
				$(".disabled").val("");
				$("select").val("");
				$("textarea").val("");
				var zrfs = $("input[name='ycsXycVeh.zrfs']:checked").val();
 				if(zrfs==0){
					$(".display_db").show();
					$(".display_pl").hide();
				}else{
					$(".display_db").hide();
					$(".display_pl").show();
				}
			});
			$("#yylx").change(function(){
				var ywlx = $(this).val()
				$.ajax({
					type:'POST',
					url:'<%=request.getContextPath()%>/xyc/xycveh_getYwyyList.action',
					data:{ywlx:ywlx},
					dataType: 'json',
					error:function(){
						alert("读取数据异常!");
					},
					success:function(data){
						$("#ywyy option").remove();
						if(null != data){
					    	if(data.length > 0){
					    		for(var i = 0; i < data.length; i ++){
					    			var ywyyval = "<option value='"+data[i][0]+"'>" + data[i][1] + "</option>";
						    		$("#ywyy").append(ywyyval);
						    	}
					    	}else{
					    		var ywyyval = "<option value=''>---请选择---</option>";
					    		$("#ywyy").append(ywyyval);
					    	}
						}else{
							var ywyyval = "<option value=''>---请选择---</option>";
				    		$("#ywyy").append(ywyyval);
						}
					}
				});
			});
		});
		function check(){
			var zrfs = $("input[name='ycsXycVeh.zrfs']:checked").val();
			//验证业务类型
			var yylx = checknotnull(document.getElementById("yylx"),'请选择业务类型');
			if (yylx != "true") {
				return false;
			}
			//验证业务原因
			var ywyy = checknotnull(document.getElementById("ywyy"),'请选择业务原因');
			if (ywyy != "true") {
				return false;
			}
			if(zrfs == 0){
				//验证车辆识别代号
				var clsbdh = checknotnull(document.getElementById("clsbdh"),'请输入车辆识别代号');
				if (clsbdh != "true") {
					return false;
				}
				/*//验证车辆所有人
				var syr = checknotnull(document.getElementById("syr"),'请输入车辆所有人');
				if (syr != "true") {
					return false;
				}
				//验证身份证明名称
				var sfzmmc = checknotnull(document.getElementById("sfzmmc"),'请选择身份证明名称');
				if (sfzmmc != "true") {
					return false;
				}
				//验证身份证明号码
				var sfzmhm = checknotnull(document.getElementById("sfzmhm"),'请输入身份证明号码');
				if (sfzmhm != "true") {
					return false;
				}
				//验证号牌号码
				var hphm = checknotnull(document.getElementById("hphm"),'请输入号牌号码');
				if (hphm != "true") {
					return false;
				}
				//验证号牌种类
				var hpzl = checknotnull(document.getElementById("hpzl"),'请选择号牌种类');
				if (hpzl != "true") {
					return false;
				}*/
			}else if(zrfs == 1){
				//验证车辆型号
				var clxh = checknotnull(document.getElementById("clxh"),'请输入车辆型号');
				if (clxh != "true") {
					return false;
				}
				//验证中文品牌型号
				var zwppxh = checknotnull(document.getElementById("zwppxh"),'请输入中文品牌型号');
				if (zwppxh != "true") {
					return false;
				}
			}
			// 嫌疑原因
			var xyyy = document.getElementsByName("ycsXycVeh.xyyy");
			var bool = false;
			
			for(var i = 0; i < xyyy.length; i++){
				if(xyyy[i].checked){
					bool = true;
				}
			}
			if(!bool){
				alert("嫌疑原因必须选择！");
				return;
			}
			document.getElementById("user_form").submit();
		}
		
	</script>
	</head>
	<body>
		<center>
			<form
				action="<%=request.getContextPath()%>/xyc/xycveh_editYcsXycVeh.action"
				method="post" id="user_form" name="user_form" target="ss">
				<iframe name="ss" id="ss" style="display: none;"></iframe>
				<div style="width: 50%; margin: 0 auto;">
					<table class="table1" width="100%" align="center" border="0"
						cellpadding="0" cellspacing="0">
						<tr>
							<td class="tds" style="text-align:right;">&nbsp;&nbsp;采集类型：
							</td>
							<td class="tds" style="text-align: left;">
								&nbsp;<s:radio cssClass="radiocss" list="#{0:'按车辆识别代号锁定',1:'按车辆型号、中文品牌锁定'}" id="zrfs" name="ycsXycVeh.zrfs" theme="simple" listKey="key" listValue="value" value="#request.ycsXycVeh.zrfs"></s:radio>
							</td>
						</tr>
						<tr>
							<td class="tds" style="text-align: right;">
								业务类型：
								<input type="hidden" name="ycsXycVeh.xh" id="xh"
									value="${ycsXycVeh.xh}" />
								<input type="hidden" name="ycsXycVeh.lybz" id="lybz"
									value="${ycsXycVeh.lybz}" />
								<input type="hidden" name="ycsXycVeh.lsh" id="lsh"
									value="${ycsXycVeh.lsh}" />
								<input type="hidden" name="ycsXycVeh.lrrdm" id="lrrdm"
									value="${ycsXycVeh.lrrdm}" />
								<input type="hidden" name="ycsXycVeh.lrrmc" id="lrrmc"
									value="${ycsXycVeh.lrrmc}" />
								<input type="hidden" name="ycsXycVeh.lrrbm" id="lrrbm"
									value="${ycsXycVeh.lrrbm}" />
								<input type="hidden" name="ycsXycVeh.lrrbmKj" id="lrrbmKj"
									value="${ycsXycVeh.lrrbmKj}" />
								<input type="hidden" name="ycsXycVeh.lrrsj" id="lrrsj"
									value="${ycsXycVeh.lrrsj}" />
								<input type="hidden" name="ycsXycVeh.lrimac" id="lrimac"
									value="${ycsXycVeh.lrimac}" />
								<input type="hidden" name="ycsXycVeh.lrip" id="lrip"
									value="${ycsXycVeh.lrip}" />
							</td>
							<td class="tds" style="text-align: left;">
								&nbsp;
								<select name="ycsXycVeh.yylx" id="yylx" style="width:215px;">
									<option value="">
										---请选择---
									</option>
									<s:iterator id="yylx" value="#request.ywlxList"
										status="st">
										<option value="${yylx[0]}">
											${yylx[1]}
										</option>
									</s:iterator>
								</select>
								<span style="color: red">*</span>
							</td>
						</tr>
						<tr>
							<td class="tds" style="text-align: right;">
								业务原因：
							</td>
							<td class="tds" style="text-align: left;">
								&nbsp;
								<select name="ycsXycVeh.ywyy" id="ywyy" style="width:215px;">
									<option value="">
										---请选择---
									</option>
								</select>
								<span style="color: red">*</span>
							</td>
						</tr>
						<tr id="clsbdhtr" class="display_db">
							<td class="tds" style="text-align: right;">
								车辆识别代号：
							</td>
							<td class="tds" style="text-align: left;">
								&nbsp;
								<input class="disabled" type="text" id="clsbdh"
									value="${ycsXycVeh.clsbdh}" name="ycsXycVeh.clsbdh" size="30"
									maxlength="45" />
								<span style="color: red">*</span>
							</td>
						</tr>
						<tr id="clsbdhtr" class="display_db">
							<td class="tds" style="text-align: right;">
								车辆所有人：
							</td>
							<td class="tds" style="text-align: left;">
								&nbsp;
								<input class="disabled" type="text" id="syr"
									value="${ycsXycVeh.syr}" name="ycsXycVeh.syr" size="30"
									maxlength="45" />
							</td>
						</tr>
						<tr id="hphmtr" class="display_db">
							<td class="tds" style="text-align: right;">
								身份证明名称：
							</td>
							<td class="tds" style="text-align: left;">
								&nbsp;
								<select name="ycsXycVeh.sfzmmc" id="sfzmmc" style="width:215px;">
									<option value="">
										---请选择---
									</option>
									<s:iterator id="sfzmmc" value="#request.sfzmmcList"
										status="st">
										<option value="${sfzmmc[0]}">
											${sfzmmc[1]}
										</option>
									</s:iterator>
								</select>
							</td>
						</tr>
						<tr id="hpzltr" class="display_db">
							<td class="tds" style="text-align: right;">
								身份证明号码：
							</td>
							<td class="tds" style="text-align: left;">
								&nbsp;
								<input class="disabled" type="text" id="sfzmhm"
									value="${ycsXycVeh.sfzmhm}" name="ycsXycVeh.sfzmhm" size="30"
									maxlength="45" />
							</td>
						</tr>
						<tr id="clsbdhtr" class="display_pl">
							<td class="tds" style="text-align: right;">
								车辆型号：
							</td>
							<td class="tds" style="text-align: left;">
								&nbsp;
								<input class="disabled" type="text" id="clxh"
									value="${ycsXycVeh.clxh}" name="ycsXycVeh.clxh" size="30"
									maxlength="45" />
								<span style="color: red">*</span>
							</td>
						</tr>
						<tr id="clsbdhtr" class="display_pl">
							<td class="tds" style="text-align: right;">
								中文品牌型号：
							</td>
							<td class="tds" style="text-align: left;">
								&nbsp;
								<input class="disabled" type="text" id="zwppxh"
									value="${ycsXycVeh.zwppxh}" name="ycsXycVeh.zwppxh" size="30"
									maxlength="45" />
								<span style="color: red">*</span>
							</td>
						</tr>
						<tr id="hphmtr" class="display_db">
							<td class="tds" style="text-align: right;">
								号牌号码：
							</td>
							<td class="tds" style="text-align: left;">
								&nbsp;
								<input class="disabled" type="text" id="hphm"
									value="${ycsXycVeh.hphm}" name="ycsXycVeh.hphm" size="30"
									maxlength="45" />
							</td>
						</tr>
						<tr id="hpzltr" class="display_db">
							<td class="tds" style="text-align: right;">
								号牌种类：
							</td>
							<td class="tds" style="text-align: left;">
								&nbsp;
								<select name="ycsXycVeh.hpzl" id="hpzl" style="width:215px;">
									<option value="">
										---请选择---
									</option>
									<s:iterator id="hpzl" value="#request.esVehCodeList"
										status="st">
										<option value="${hpzl[0]}">
											${hpzl[1]}
										</option>
									</s:iterator>
								</select>
							</td>
						</tr>
						<tr>
							<td class="tds" style="text-align: right;">
								嫌疑原因：
							</td>
							<td class="tds" style="text-align: left;">
								<s:iterator value="#request.ycsXycCodeList" id="ycsXycCode">
									<input name="ycsXycVeh.xyyy"
										value="<s:property value="#ycsXycCode.dmz" />" type="checkbox" />
									<s:property value="#request.ycsXycCode.dmms1" />
								</s:iterator>
								<span style="color: red">*</span>
							</td>
						</tr>
						<tr>
							<td class="tds" style="text-align: right;">
								嫌疑车状态：
							</td>
							<td class="tds" style="text-align: left;">
								<s:radio list="#{0:'不合法',1:'合法'}" id="zt"
									theme="simple" name="ycsXycVeh.zt"
									value="#request.ycsXycVeh.zt"></s:radio>

							</td>
						</tr>
						<tr>
							<td class="tds" style="text-align: right;">
								备注：
							</td>
							<td class="tds" style="text-align: left;">
								&nbsp;
								<input class="disabled" type="text" name="ycsXycVeh.bz"
									id="bz" value="${ycsXycVeh.bz}" size="45"
									maxlength="45" />
							</td>
						</tr>
						<s:if test="#request.editType !='新增'">
							<tr class="display">
								<td class="tds" style="text-align: right;">
									流水号：
								</td>
								<td class="tds" style="text-align: left;">
									&nbsp;${ycsXycVeh.lsh}
								</td>
							</tr>
							<tr class="display">
								<td class="tds" style="text-align: right;">
									业务来源：
								</td>
								<td class="tds" style="text-align: left;">
									&nbsp;
									<s:if test='#request.ycsXycVeh.lybz == 0'>
										内网
									</s:if>
									<s:elseif test='#request.ycsXycVeh.lybz == 2'>
										PDA查验
									</s:elseif>
									<s:elseif test='#request.ycsXycVeh.lybz == 3'>
										受理
									</s:elseif>
									<s:else>
										未知
									</s:else>
								</td>
							</tr>
							<tr class="display">
								<td class="tds" style="text-align: right;">
									经办人：
								</td>
								<td class="tds" style="text-align: left;">
									&nbsp;${ycsXycVeh.lrrmc}【${ycsXycVeh.lrrdm}】
								</td>
							</tr>
							<tr class="display">
								<td class="tds" style="text-align: right;">
									经办部门：
								</td>
								<td class="tds" style="text-align: left;">

									&nbsp;${ycsXycVeh.lrrbm}
								</td>
							</tr>
							<tr class="display">
								<td class="tds" style="text-align: right;">
									经办时间：
								</td>
								<td class="tds" style="text-align: left;">

									&nbsp;${ycsXycVeh.lrrsj}
								</td>
							</tr>
						</s:if>
						<!--<tr class="display">
						<td style="text-align:right;">经办时间：</td>
						<td>
							&nbsp;<input class="disabled" type="text" name="ycsXycVeh.lybz" id="ycsXycVeh.lybz" value="${ycsXycVeh.lybz}" size="45" maxlength="45" />
						</td>
					</tr>
					-->
						<tr>
							<td class="tds" colspan="2" height="30" align="center">
								<span id="msg" style="color: red; margin-left: 180px;"></span>
							</td>
						</tr>
						<tr>
							<td class="tds" colspan="2" height="50"
								style="text-align: center;">
								<s:if test="#request.editType != '查看'">
									<input type="button" onclick="javascript:check();" value="提 交"
										style="margin-left: 70px;cursor:pointer;width: 79px;height: 28px;border: none;background: url(<%=request.getContextPath()%>/images/an2.gif) no-repeat;color: #FFFFFF;font-weight: bold;">
									<input type="button"
										onclick="javascript:window.history.go(-1);" value="返 回"
										style="cursor:pointer;width: 79px;height: 28px;border: none;background: url(<%=request.getContextPath()%>/images/an2.gif) no-repeat;color: #FFFFFF;font-weight: bold;">
								</s:if>
								<s:else>
									<input type="button"
										onclick="javascript:window.history.go(-1);" value="返 回"
										style="margin-left: 200px;cursor:pointer;width: 79px;height: 28px;border: none;background: url(<%=request.getContextPath()%>/images/an2.gif) no-repeat;color: #FFFFFF;font-weight: bold;">
								</s:else>

							</td>
						</tr>
					</table>
				</div>
			</form>

		</center>
		<div></div>
	</body>
</html>