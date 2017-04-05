<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<title>流水管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/jquery/js/jquery-1.8.2.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/artDialog4.1.6/jquery.artDialog.source.js?skin=chrome"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/artDialog4.1.6/plugins/iframeTools.source.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/public.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/yslYz.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/rq.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
		var type = '${type}';
		var gw = '${gw}';
		var ld = '${ld}';
		var chuli;
		$(document).ready(function() {
			// 其他模块隐藏
			document.getElementById("tabCc2").style.display = "none";
			document.getElementById("tabCc3").style.display = "none";
			document.getElementById("tabCc4").style.display = "none";
			document.getElementById("tabCc5").style.display = "none";
			document.getElementById("tabCc6").style.display = "none";
			document.getElementById("tabCc7").style.display = "none";
			
			loadinit();
			
			// 判断邮寄信息中， 复选框是否需要选中
			var zj = '${requestScope.ptdoi.postZj}';
			if(zj == '1'){
				document.getElementById("ptdoi.postZj").value = '1';
				document.getElementById("ptdoi.postZj").checked = true;
			}else{
				document.getElementById("ptdoi.postZj").value = '0';
			}
			
			var tp = '${requestScope.ptdoi.postTp}';
			if(tp == '1'){
				document.getElementById("ptdoi.postTp").value = '1';
				document.getElementById("ptdoi.postTp").checked = true;
			}else{
				document.getElementById("ptdoi.postTp").value = '0';
			}
			var csyss = '${requestScope.ptdoi.csys}';
			var csys = csyss.split(",");
			var csysItem = document.getElementsByName("ptdoi.csys");
			if(csys.length > 2){
				csysItem[0].value = csys[0];
				csysItem[1].value = csys[1];
				csysItem[2].value = csys[2];
			}else{
				csysItem[0].value = csyss;
			}
		});
		
		function loadinit(){
			$(":text").attr("disabled",true);
			$(".selectcss").attr("disabled",true);
			$(":checkbox").attr("disabled",true);
			$("#clsbbutid").hide();
			
			var wxts = '';
			<s:iterator id="xgzd" value="#request.ywlsglVehFieldgllist" status="st">
				var zdmc = '${xgzd.zdmc}';
				var zdsm = '${xgzd.zdsm}';
				$("#ptdoi_" + zdmc).removeAttr("disabled");
				$("#" + zdmc + "Cf").removeAttr("disabled");
				if(zdmc == "clsbdh"){
					$("#clsbbutid").show();
				}
				var count = '${st.count}';
				
				if(count == 1){
					wxts += zdsm;
				}else{
					wxts += '、' + zdsm;
				}
			</s:iterator>
			$("#wxtsspan").html("温馨提示：目前仅可以对流水数据的"+wxts+"信息项进行修改。");
			ycTable2('tabCc3');
		}
		
		function cxPTMO(cf){
			
			var clsbdh = "";
			
			// 得到车辆识别代号 fcf 表示非重复，另外一个值为 cf 重复。
			if(cf == "fcf")
				clsbdh = document.getElementById("ptdoi.clsbdh");
			else
				clsbdh = document.getElementById("clsbdhCf");
			
			if(checknotnull(clsbdh,'请先填写车辆识别代号') != "true"){
				return false;
			}
			if(!checkVIN_JYW(clsbdh.value)){
				prompt(clsbdh,"车辆识别代号格式不正确!");
				return false;
			}
			else{
				removePrompt(clsbdh);
			}
			
			$.ajax({
				type:'POST',
				url: '<%=request.getContextPath()%>/ptmo/ptmo_pdCarByClsbdh.action',
				data:{clsbdh:clsbdh.value},//发送的参数
				dataType: 'html',
				success:function(data){
					if(data == 0){
						$.ajax({
							type:'POST',
							url: '<%=request.getContextPath()%>/ptmo/ptmo_selectPTMOByPar2.action',
							data:{outIn:'OUTSMYC',clsbdh:clsbdh.value},//发送的参数
							dataType: 'json',
							success:function(data){
								var juValue = eval(data);
								var tabElement = document.getElementById("selTab");
								var rowNum = 7					// 表格列数。从0开始数。
								
								// 清空表格原有数据
								$("#selTab").children().remove();
								if(juValue!=""){
									for(var i = 0; i < juValue.length; i ++){
										
										// 创建一个tr 
										var tr=document.createElement("tr");
										if((i+1)%2 == 0){
											tr.className = 'altrow'
										}
										
										for(var j = 0; j < juValue[i].length; j ++){
											var td=document.createElement("td"); 
											td.className = "td";
								　　			var inputElent = document.createElement("input");
								　　			
								　　			// 给input 元素赋属性 
								　　			inputElent.id = i+"_"+j;
								　　			inputElent.value= juValue[i][j];
								　　			inputElent.type = "hidden";
								　　			if(j < rowNum){
								　　				// 需要展现的值
									  			td.innerHTML = juValue[i][j];
								　　				td.appendChild(inputElent);
								　　				tr.appendChild(td);
											}
								　　			else{
								　　				// 加上 操作按钮
								  				if(j == rowNum){
								  					var inputButton = document.createElement("input");
								  					inputButton.type = "button";
								  					inputButton.value = "选择";
								  					inputButton.id = i+"_";
								  					// 添加单击事件
								  					inputButton.onclick = function(){
								  						// 给对应的文本框赋值
								  						//if(cf == "fcf"){
															document.getElementById("ptdoi.clsbdh").value = document.getElementById(this.id+"1").value;
															document.getElementById("ptdoi.clxh").value = document.getElementById(this.id+"2").value;
															document.getElementById("ptdoi.fdjh").value = document.getElementById(this.id+"3").value;
															document.getElementById("ptdoi.clpp1").value = document.getElementById(this.id+"4").value;
															document.getElementById("ptdoi.clpp2").value = document.getElementById(this.id+"5").value;
															document.getElementById("ptdoi.gcjk").value = document.getElementById(this.id+"7").value;
															document.getElementById("ptdoi.hgzbh").value = document.getElementById(this.id+"8").value;
															document.getElementById("ptdoi.jkpzhm").value = document.getElementById(this.id+"9").value;
															document.getElementById("ptdoi.csys").value = document.getElementById(this.id+"10").value;
															document.getElementById("ptdoi.netJbrxm").value = document.getElementById(this.id+"11").value;
															document.getElementById("ptdoi.outIn").value = "OUT";
															document.getElementById("ptdoi.ccrq").value = document.getElementById(this.id+"14").value;
														//}
														//else{
															// 添加重复项的值
															document.getElementById("clsbdhCf").value = document.getElementById(this.id+"1").value;
															document.getElementById("clxhCf").value = document.getElementById(this.id+"2").value;
															document.getElementById("fdjhCf").value = document.getElementById(this.id+"3").value;
															document.getElementById("clpp1Cf").value = document.getElementById(this.id+"4").value;
															document.getElementById("clpp2Cf").value = document.getElementById(this.id+"5").value;
															document.getElementById("gcjkCf").value = document.getElementById(this.id+"7").value;
															document.getElementById("hgzbhCf").value = document.getElementById(this.id+"8").value;
															document.getElementById("jkpzhmCf").value = document.getElementById(this.id+"9").value;
															document.getElementById("csysCf").value = document.getElementById(this.id+"10").value;
															document.getElementById("netJbrxmCf").value = document.getElementById(this.id+"11").value;
															document.getElementById("ccrqCf").value = document.getElementById(this.id+"14").value;
														//}
								  						// 调用 国产/进口 下拉框选择事件 
														selChange(document.getElementById("ptdoi.gcjk"));
								  						// 调用 重复国产/进口 下拉框选择事件
								  						selChange(document.getElementById("gcjkCf"));
								  						
								  						// 关闭层
								  						selArt.close();
								  					};
								  					
								  					td.appendChild(inputButton);
								  					tr.appendChild(td);
								  				}
								　　				
								　　				// 部分不需要展现的值，如果用户选择，需要赋值到对应的文本中。
								　　				tabElement.appendChild(inputElent);
											}
								　　			
										}
							　　			tabElement.appendChild(tr);
									}
								}
								else{
									var tr=document.createElement("tr");
									var td=document.createElement("td");
									var span = document.createElement("span"); 
									td.setAttribute("colspan",rowNum+1+"");
									td.setAttribute("style","text-align:center;");
									td.innerHTML = "<span style='color: red;'>暂时没有相关数据</span>";
									tr.appendChild(td);
									tabElement.appendChild(tr);
								}
							}
						});
						
						// 弹出层， 给予用户选择。
						var selArt = art.dialog({ 
							width: '850px',
							height: '200px',
							title: '请选择验车类型',
							content: document.getElementById("divSelPtmoValue"),
							lock: true,
							fixed: true,
							cancel: function () {
								return true;
							},
							ok: false
						});
						return false;
						if(typeof(returnValue)!="undefined" ){
							var returnValue = returnValue.split(",");
						}
						
					}else if(data == 1){
						alert("该车已做过预受理，请不要重复添加。");
						return false;
					}else{
						alert("系统繁忙,请稍候再试!");
						return false;
					}
				}
			});
			
		}
		
		// 邮寄信息中 复选框选择事件
		function jsz_jdc_select(obj){
			// 判断用户是否选择
			if(obj.checked){
				obj.value = 1;
			}
			else{
				obj.value = 0;
			}
		}
		
		function yzAll(){
			var pd;
			var fpdm = document.getElementById("ptdoi.fpdm");
			var fphm = document.getElementById("ptdoi.fphm");
			var gzsbh = document.getElementById("ptdoi.gzszmbh");
			var syr = document.getElementById("ptdoi.syr");
			var clsbdh = document.getElementById("ptdoi.clsbdh");
			var xh = document.getElementById("ptdoi.xh");
			var yzGsInfo = document.getElementById("ptdoi.yzGsInfo"); // 0表示国税验证通过，1国税验证未通过
			var clxh = document.getElementById("ptdoi.clxh");
			var fdjxh = document.getElementById("ptdoi.fdjxh");
			var gcjk = document.getElementById("ptdoi.gcjk");
			// 先判断所有信息是否输入完全
			/*pd = djcsClick();
			if(pd == false){
				ycTable2('tabCc1');
				return false;
			}*/
			/*pd = djcsCfClick();
			if(pd == false){
				ycTable2('tabCc2');
				return false;
			}*/
			
			/*pd = jscsClick();
			if(pd == false){
				ycTable2('tabCc3');
				return false;
			}*/
			/*pd = jscsCfClick();
			if(pd == false){
				ycTable2('tabCc4');
				return false;
			}*/
			
			/*pd = dlrxxCheck();
			if(pd == false){
				ycTable2('tabCc5');
				return false;
			}*/
			/*pd = dlrxxCfCheck();
			if(pd = false){
				ycTable2('tabCc6');
				return false;
			}*/
			
			// 弹出等待层，以免查询时间太久
			var imgurl = '<%=request.getContextPath()%>/artDialog4.1.6/skins/icons/loading2.gif';
			chuli = art.dialog({
			    content: '<div style=\"text-align:center; text-indent:-999em; width:190px; overflow:hidden; background:url('+imgurl+') no-repeat center center;\"><span>loading..</span></div><br/>数据处理中,请稍候.....',
			    title: '数据处理中...',
    			lock: true,
    			opacity: 0.87
			});
			//验证车管车型参数库
			$.ajax({
				type:'POST',
				url: '<%=request.getContextPath()%>/yanche/lsgl_yzcgcx.action',
				data:{clxh:clxh.value},//发送的参数
				async:false,
				dataType: 'html',
				success:function(data){
					chuli.close();
					if(data == 0){
						pd = true;
					}else if(data == 1){
						alert("没有该车辆类型，请去车管部门进行车辆参数维护!");
						pd = false;
					}else{
						alert("系统繁忙,请稍候再试!");
						pd = false;
					}
				}
			});
			if(pd == false){
				return false;
			}
			
			// 验证环保
			/*var gcjkValue = "";
			
			if(pdGcJk(gcjk.value)){
				gcjkValue = "gc";
			}else{
				gcjkValue = "jk";
			}*/
			
			/*$.ajax({
				type:'POST',
				url: '<%=request.getContextPath()%>/yanche/lsgl_yzhb.action',
				data:{clsbdh:clsbdh.value,gcjk:gcjkValue,clxh:clxh.value,fdjxh:fdjxh.value},//发送的参数
				async:false,
				dataType: 'html',
				success:function(data){
					chuli.close();
					if(data == 0){
						pd = true;
					}else if(data == 1){
						alert("该车型没有环保达标记录!");
						pd = false;
					}else{
						alert("系统繁忙,请稍候再试!");
						pd = false;
					}
				}
			});*/
			/*if(pd == false){
				return false;
			}*/
			
			var imgurl = '<%=request.getContextPath()%>/artDialog4.1.6/skins/icons/loading2.gif';
			chuli = art.dialog({
			    content: '<div style=\"text-align:center; text-indent:-999em; overflow:hidden; background:url('+imgurl+') no-repeat center center;\"><span>loading..</span></div><br/>数据处理中,请稍候.....',
			    title: '数据处理中',
    			lock: true,
			    opacity: 0.87
			});
			//$(":text").removeAttr("disabled");
			//$(".selectcss").removeAttr("disabled");
			//$(":checkbox").removeAttr("disabled");
			user_form.submit();
		}
		
		//暂停与恢复
		function zthf(){
			if(!ztpd('5')){
				alert("当前流水状态不能暂停与恢复!");
				return false;
			}
			var lsztstemp = $("#lsztid").val();
			if("5" == lsztstemp){
				$("#zthftrid").hide();
			}else{
				$("#zthftrid").hide();
			}
			$("#zthfsmid").removeAttr("disabled");
			$("input[name=ztyynames]").removeAttr("disabled");
			art.dialog({
				width:'50%',
			    content: document.getElementById("zthfyydivid"),
			    title: '暂停与恢复',
			    okVal: '保存',
			    ok: function () {
			    	var zthfyy = "";
			    	$("input[name=ztyynames][checked]").each(function(){
			    		if(null == zthfyy || "" == zthfyy){
			    			zthfyy += $(this).val();
			    		}else{
			    			zthfyy += "," + $(this).val();
			    		}
					});
			    	if("5" == lsztstemp){
			    		
			    	}else{
			    		if(null == zthfyy || "" == zthfyy){
				    		alert("请选择暂停与恢复原因!");
				    		return false;
				    	}
			    	}
					var zthfsm = $("#zthfsmid").val();
					var lsh = $("#ptdoi_lsh").val();
					zthfsm = encodeURI(zthfsm);
					zthfsm = encodeURI(zthfsm);
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
						url: '<%=request.getContextPath()%>/yanche/lsgl_lszthf.action?lsh='+lsh+'&zthfyy=' + zthfyy + '&zthfsm=' + zthfsm,
						dataType: 'html',
						success:function(data){
							chuli.close();
							if(data == 0){
							    alert("暂停与恢复成功!");
							    //location.reload();
							    window.location.href = '<%=request.getContextPath()%>/yanche/lsgl_initYwlsglVehFlowList.action?type='+type+'&gw='+gw+'&ld=' + ld;
							}else if(data == 1){
								alert("暂停与恢复失败!");
							}else{
								alert("暂停与恢复失败!");
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
		
		//转嫌疑库
		function zxyk(){
			if(!ztpd('2')){
				alert("当前流水状态不能转嫌疑库!");
				return false;
			}
			$("#zxyksmid").removeAttr("disabled");
			$("input[name=zxykyynames]").removeAttr("disabled");
			art.dialog({
				width:'50%',
			    content: document.getElementById("zxykyydivid"),
			    title: '转嫌疑库',
			    okVal: '保存',
			    ok: function () {
			    	var zxykyy = "";
			    	$("input[name=zxykyynames][checked]").each(function(){
			    		if(null == zxykyy || "" == zxykyy){
			    			zxykyy += $(this).val();
			    		}else{
			    			zxykyy += "," + $(this).val();
			    		}
					});
			    	if(null == zxykyy || "" == zxykyy){
			    		alert("请选择转嫌疑库原因!");
			    		return false;
			    	}
					var zxyksm = $("#zxyksmid").val();
					var lsh = $("#ptdoi_lsh").val();
					zxyksm = encodeURI(zxyksm);
					zxyksm = encodeURI(zxyksm);
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
						url: '<%=request.getContextPath()%>/yanche/lsgl_lszxyc.action?lsh='+lsh+'&zxykyy=' + zxykyy + '&zxyksm=' + zxyksm,
						dataType: 'html',
						success:function(data){
							chuli.close();
							if(data == 0){
							    alert("转嫌疑库成功!");
							    //location.reload();
							    window.location.href = '<%=request.getContextPath()%>/yanche/lsgl_initYwlsglVehFlowList.action?type='+type+'&gw='+gw+'&ld=' + ld;
							}else if(data == 1){
								alert("转嫌疑库失败!");
							}else{
								alert("转嫌疑库失败!");
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
		
		//退办
		function tb(){
			if(!ztpd('Q')){
				alert("当前流水状态不能退办!");
				return false;
			}
			$("#tbsmid").removeAttr("disabled");
			$("input[name=tbyynames]").removeAttr("disabled");
			art.dialog({
				width:'50%',
			    content: document.getElementById("tbyydivid"),
			    title: '退办',
			    okVal: '保存',
			    ok: function () {
			    	var tbyy = "";
			    	$("input[name=tbyynames][checked]").each(function(){
			    		if(null == tbyy || "" == tbyy){
			    			tbyy += $(this).val();
			    		}else{
			    			tbyy += "," + $(this).val();
			    		}
					});
			    	if(null == tbyy || "" == tbyy){
			    		alert("请选择退办原因!");
			    		return false;
			    	}
					var tbsm = $("#tbsmid").val();
					var lsh = $("#ptdoi_lsh").val();
					tbsm = encodeURI(tbsm);
					tbsm = encodeURI(tbsm);
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
						url: '<%=request.getContextPath()%>/yanche/lsgl_lstb.action?lsh='+lsh+'&tbyy=' + tbyy + '&tbsm=' + tbsm,
						dataType: 'html',
						success:function(data){
							chuli.close();
							if(data == 0){
							    alert("退办成功!");
							    //location.reload();
							    window.location.href = '<%=request.getContextPath()%>/yanche/lsgl_initYwlsglVehFlowList.action?type='+type+'&gw='+gw+'&ld=' + ld;
							}else if(data == 1){
								alert("退办失败!");
							}else{
								alert("退办失败!");
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
		
		//退查验岗
		function tcyg(){
			if(!ztpd('3')){
				alert("当前流水状态不能退回查验岗!");
				return false;
			}
			$("#tcygsmid").removeAttr("disabled");
			$("input[name=tcygyynames]").removeAttr("disabled");
			art.dialog({
				width:'50%',
			    content: document.getElementById("tcygyydivid"),
			    title: '退查验岗',
			    okVal: '保存',
			    ok: function () {
			    	var tcygyy = "";
			    	$("input[name=tcygyynames][checked]").each(function(){
			    		if(null == tcygyy || "" == tcygyy){
			    			tcygyy += $(this).val();
			    		}else{
			    			tcygyy += "," + $(this).val();
			    		}
					});
			    	if(null == tcygyy || "" == tcygyy){
			    		alert("请选择退查验岗原因!");
			    		return false;
			    	}
					var tcygsm = $("#tcygsmid").val();
					var lsh = $("#ptdoi_lsh").val();
					tcygsm = encodeURI(tcygsm);
					tcygsm = encodeURI(tcygsm);
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
						url: '<%=request.getContextPath()%>/yanche/lsgl_lsthyj.action?lsh='+lsh+'&tcygyy=' + tcygyy + '&tcygsm=' + tcygsm,
						dataType: 'html',
						success:function(data){
							chuli.close();
							if(data == 0){
							    alert("退回查验岗成功!");
							    //location.reload();
							    window.location.href = '<%=request.getContextPath()%>/yanche/lsgl_initYwlsglVehFlowList.action?type='+type+'&gw='+gw+'&ld=' + ld;
							}else if(data == 1){
								alert("退回查验岗失败!");
							}else{
								alert("退回查验岗失败!");
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
		
		function ztpd(cz){
			var lszts = $("#lsztid").val();
			if("1" == lszts){
				if("2" == cz || "3" == cz || "5" == cz || "Q" == cz){
					return true;
				}
			}else if("2" == lszts){
				return false;
			}else if("3" == lszts){
				return false;
			}else if("4" == lszts){
				if("2" == cz || "3" == cz || "4" == cz || "5" == cz || "Q" == cz){
					return true;
				}
			}else if("5" == lszts){
				if("2" == cz || "3" == cz || "4" == cz || "5" == cz || "Q" == cz){
					return true;
				}
			}
			return false;
		}
		
		function closechuli(){
			chuli.close();
		}
		
		function exception(content){
			loadinit();
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
<STYLE type="text/css">
* {
	margin: 0;
	padding: 0;
}
.edittable{
	font-size:12px;
	width:500px;
	border-collapse:collapse;
}
.edittable,.edittable tr,.edittable td,.edittable th{
	BORDER-RIGHT: #d2e9ff 1px solid;
	BORDER-TOP: #d2e9ff 1px solid;
	BORDER-LEFT: #d2e9ff 1px solid;
	BORDER-BOTTOM: #d2e9ff 1px solid;
}
.edittable tr{
	height:30px;
	background-color:#EEF2FB;
}
.edittable th{
	WIDTH: 150px;
	TEXT-ALIGN: right
}
.table {
	font-size: 12px;
	width: 800px;
	border-collapse: collapse;
}

.td {
	text-align: center;
}

.table tr {
	height: 30px;
	background-color: #EEF2FB;
}

.table th {
	width: 150px;
	text-align: center;
	cursor: hand;
}

.table td {
	text-align: left;
}
/*****页面导航栏样式*****/
#admin_title {
	margin-top: 1px;
	margin-right: 1px;
	margin-left: 1px;
	width: 99.8%;
	height: 25px;
	background-color: #353c44;
}

#admin_title .logo {
	width: 14px;
	height: 14px;
	float: left;
	margin-top: 4px;
	margin-left: 10px;
}

#admin_title .title {
	height: 26px;
	float: left;
	font-size: 12px;
	color: white;
	line-height: 26px;
	margin-left: 10px;
}

#admin_main {
	width: 800px;
	margin-top: 30px;
}

.thColor {
	background-color: silver;
}

.altrow {
	background-color: #c7e5ff;
	height: 32px;
}
</STYLE>
</head>
<body onkeydown="javascript:PreventBSK();">
	<center>
		<div id="admin_title">
			<div class="logo">
				<img src="<%=request.getContextPath()%>/images/frame_19.gif">
			</div>
			<div class="title">流水管理</div>
		</div>
		<div id="admin_main">
			<form
				action="<%=request.getContextPath()%>/yanche/lsgl_editPtdoi.action"
				method="post" id="user_form" name="user_form" target="abc">
				<iframe id="abc" name="abc" width="0" height="0" style="display: none;"></iframe>
				<table border="0" cellpadding="0" cellspacing="0" align="left">
					<tr>
						<td colspan="6" style="text-align: left;">
							<font color="blue" id="wxtsspan"></font>
						</td>
					</tr>
				</table>
				<br/>
				<table class="table">
					<tr>
						<th id="tabCc1Th" onclick="ycTable2('tabCc1')" class="thColor">登记参数
							<input id="gwid" value="${gw}" name="gw" type="hidden" />
							<input id="ptdoi_lsh" name="ptdoi.lsh" type="hidden" value="${ptdoi.lsh}" />
						</th>
						<!-- <th id="tabCc2Th" onclick="ycTable2('tabCc2')" class="">登记参数重复</th> -->
						<th id="tabCc3Th" onclick="ycTable2('tabCc3')" class="">技术参数</th>
						<!-- <th id="tabCc4Th" onclick="ycTable2('tabCc4')" class="">技术参数重复</th> -->
						<!--<th id="tabCc5Th" onclick="ycTable2('tabCc5')" class="">发票信息</th>
						<th id="tabCc6Th" onclick="ycTable2('tabCc6')" class="">发票信息重复</th>-->
						<!-- <th id="tabCc5Th" onclick="ycTable2('tabCc5')" class="">代理人信息</th>
						<th id="tabCc6Th" onclick="ycTable2('tabCc6')" class="">代理信息重复</th>
						<th id="tabCc7Th" onclick="ycTable2('tabCc7')" class="">邮寄信息</th>-->
					</tr>
				</table>
				<!-- 登记参数 -->
				<table border="1" cellpadding="0" cellspacing="0" id="tabCc1"
					class="table">
					<tr>
						<td style="text-align:right;"><font style="color:red;">*</font>身份证明名称：
						</td>
						<td class="trs">&nbsp;<s:select cssClass="selectcss"
								list="#request.sfzmList" theme="simple" id="ptdoi_sfzmmc"
								headerKey="" headerValue="---请选择---" listKey="dmz"
								listValue="dmz+':'+dmsm1" name="ptdoi.sfzmmc"
								value="#request.ptdoi.sfzmmc"></s:select></td>
						<td style="text-align:right;"><font style="color:red;">*</font>身份证明号码：
						</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="ptdoi_sfzmhm" value="${ptdoi.sfzmhm}" name="ptdoi.sfzmhm"
							size="17" maxlength="30" /></td>
						<td style="text-align:right;"><font style="color:red;">*</font>居住/暂住证号：
						</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="ptdoi_zzz" value="${ptdoi.zzz}" name="ptdoi.zzz" size="20"
							maxlength="100" /></td>
					</tr>
					<tr>
						<td style="text-align:right;"><font style="color:red;">*</font>姓名/名称：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="ptdoi_syr" value="${ptdoi.syr}" name="ptdoi.syr" size="17"
							maxlength="50" />
						</td>

						<td style="text-align:right;"><font style="color:red;">*</font>移动电话：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="ptdoi_lxdh" value="${ptdoi.lxdh}" name="ptdoi.lxdh" size="17"
							maxlength="50" />
						</td>

						<td style="text-align:right;">电子邮箱：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="ptdoi_dzyx" value="${ptdoi.dzyx}" name="ptdoi.dzyx" size="20"
							maxlength="30" />
						</td>
					</tr>
					<tr>
						<td style="text-align:right;"><font style="color:red;">*</font>发票代码：
						</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="ptdoi_fpdm" value="${ptdoi.fpdm}" name="ptdoi.fpdm" size="17"
							maxlength="20" /></td>
						<td style="text-align:right;"><font style="color:red;">*</font>发票号码：
						</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="ptdoi_fphm" value="${ptdoi.fphm}" name="ptdoi.fphm" size="17"
							maxlength="20" /></td>
						<td style="text-align:right;"><font style="color:red;">*</font>购置税证明编号：
						</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="ptdoi_gzszmbh" value="${ptdoi.gzszmbh}" name="ptdoi.gzszmbh"
							size="17" maxlength="20" /></td>
					</tr>
					<tr>
						<td style="text-align:right;"><font style="color:red;">*</font>住所地址：</td>
						<td class="trs" colspan="5">&nbsp;<s:select
								cssClass="selectcss" list="#request.yzbmList" theme="simple"
								id="yzbm7" listKey="dmz" listValue="dmz+':'+dmsm1" name="yzbm7"></s:select>
							<input type="text" class="disabled" id="ptdoi_zsxxdz"
							value="${ptdoi.zsxxdz}" name="ptdoi.zsxxdz" size="50"
							maxlength="100" /></td>
					</tr>
					<tr>

						<td style="text-align:right;">邮寄地址：</td>
						<td class="trs" colspan="3">&nbsp;<s:select
								cssClass="selectcss" list="#request.yzbmList" theme="simple"
								id="yzbm6" listKey="dmz" listValue="dmz+':'+dmsm1" name="yzbm6"></s:select>
							<input type="text" class="disabled" id="ptdoi_zzxxdz"
							value="${ptdoi.zzxxdz}" name="ptdoi.zzxxdz" size="50"
							maxlength="100" /></td>
						<td style="text-align:right;">邮政编码：</td>
						<td><input type="text" class="disabled" id="ptdoi_yzbm1"
							value="${ptdoi.yzbm1}" name="ptdoi.yzbm1" size="10" maxlength="10" />
						</td>
					</tr>
					<tr>
						<td style="text-align:right;"><font style="color:red;">*</font>使用性质：</td>
						<td>&nbsp;<s:select cssClass="selectcss"
								list="#request.syxzList" theme="simple" id="ptdoi_syxz"
								headerKey="" headerValue="---请选择---" listKey="dmz"
								listValue="dmz+':'+dmsm1" name="ptdoi.syxz"
								value="#request.ptdoi.syxz"></s:select></td>

						<td style="text-align:right;"><font style="color:red;">*</font>所有权：</td>
						<td>&nbsp;<s:select cssClass="selectcss"
								list="#request.syqList" theme="simple" id="ptdoi_syq"
								headerKey="" headerValue="---请选择---" listKey="dmz"
								listValue="dmz+':'+dmsm1" name="ptdoi.syq"
								value="#request.ptdoi.syq"></s:select></td>

						<td style="text-align:right;"><font style="color:red;">*</font>获得方式：</td>
						<td>&nbsp;<s:select cssClass="selectcss"
								list="#request.hdfsList" theme="simple" id="ptdoi_hdfs"
								headerKey="" headerValue="---请选择---" listKey="dmz"
								listValue="dmz+':'+dmsm1" name="ptdoi.hdfs"
								value="#request.ptdoi.hdfs"></s:select></td>
					</tr>
					<tr>
						<td style="text-align:right;"><font style="color:red;">*</font>行政区划：</td>
						<td class="trs">&nbsp;<s:select cssClass="selectcss"
								list="#request.yzbmList" theme="simple" id="ptdoi_zsxzqh"
								headerKey="" headerValue="---请选择---" listKey="dmz"
								listValue="dmz+':'+dmsm1" name="ptdoi.zsxzqh"
								value="#request.ptdoi.zsxzqh"></s:select></td>

						<td style="text-align:right;"><font style="color:red;">*</font>号牌种类：</td>
						<td class="trs">&nbsp;<s:select cssClass="selectcss"
								list="#request.hpzlList" theme="simple" id="ptdoi_hpzl"
								headerKey="" headerValue="---请选择---" listKey="dmz"
								listValue="dmz+':'+dmsm1" name="ptdoi.hpzl"
								value="#request.ptdoi.hpzl"></s:select></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td style="text-align:right;">保险生效日期：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="ptdoi_sxrq" value="${fn:substring(ptdoi.sxrq,0,10)}"
							name="ptdoi.sxrq" size="10" maxlength="20"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:CurentTime()})" />
						</td>

						<td style="text-align:right;">保险终止日期：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="ptdoi_zzrq" value="${fn:substring(ptdoi.zzrq,0,10)}"
							name="ptdoi.zzrq" size="10" maxlength="20"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:CurentTime()})" />
						</td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td style="text-align: right;" style="width: 876px height: 21px;">&nbsp;
							<font style="color:red;">*</font>检验日期：</td>
						<td align="left" style="width: 196px height: 21px;">&nbsp;<input
							name="ptdoi.yxqz" id="ptdoi_yxqz"
							value="${fn:substring(ptdoi.yxqz,0,10)}" size="10" maxlength="20"
							type="text"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:CurentTime()})" />
						</td>
						<td style="text-align: right;" style="width: 680px height: 21px;">&nbsp;
							承检单位：</td>
						<td style="width: 164px height: 21px;" colspan="3" align="left">&nbsp;<s:select
								cssClass="selectcss" list="#request.cjdwList" theme="simple"
								id="ptdoi_cjdw" headerKey="" headerValue="---请选择---"
								listKey="dmz" listValue="dmz+':'+dmsm1" name="ptdoi.cjdw"
								value="#request.ptdoi.cjdw"></s:select></td>
					</tr>
					<tr>
						<td style="text-align: right;" style="width: 876px height: 21px;">&nbsp;
							常停车场所：</td>
						<td colspan="3" align="left" style="width: 196px height: 21px;">&nbsp;<input
							name="ptdoi.tempBz2" id="ptdoi_tempBz2" value="${ptdoi.tempBz2}"
							size="60" maxlength="100" type="text" /></td>

						<td></td>
						<td></td>
					</tr>
				</table>

				<!-- 登记参数重复 -->
				<table border="0" cellpadding="0" cellspacing="0" id="tabCc2"
					class="table">
					<tr>
						<td style="text-align:right;"><font style="color:red;">*</font>身份证明名称：
						</td>
						<td class="trs">&nbsp;<s:select cssClass="selectcss"
								list="#request.sfzmList" theme="simple" id="sfzmmcCf"
								headerKey="" headerValue="---请选择---" listKey="dmz"
								listValue="dmz+':'+dmsm1" name="sfzmmcCf"
								value="#request.ptdoi.sfzmmc"></s:select></td>
						<td style="text-align:right;"><font style="color:red;">*</font>身份证明号码：
						</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="sfzmhmCf" value="${ptdoi.sfzmhm}" name="sfzmhmCf" size="17"
							maxlength="30" /></td>
						<td style="text-align:right;"><font style="color:red;">*</font>居住/暂住证号：
						</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="zzzCf" value="${ptdoi.zzz}" name="zzzCf" size="20"
							maxlength="100" /></td>
					</tr>
					<tr>
						<td style="text-align:right;"><font style="color:red;">*</font>姓名/名称：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="syrCf" value="${ptdoi.syr}" name="syrCf" size="17"
							maxlength="50" />
						</td>

						<td style="text-align:right;"><font style="color:red;">*</font>移动电话：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="lxdhCf" value="${ptdoi.lxdh}" name="lxdhCf" size="17"
							maxlength="50" />
						</td>

						<td style="text-align:right;">电子邮箱：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="dzyxCf" value="${ptdoi.dzyx}" name="dzyxCf" size="20"
							maxlength="30" />
						</td>
					</tr>
					<tr>
						<td style="text-align:right;"><font style="color:red;">*</font>发票代码：
						</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="fpdmCf" value="${ptdoi.fpdm}" name="fpdmCf" size="17"
							maxlength="30" /></td>
						<td style="text-align:right;"><font style="color:red;">*</font>发票号码：
						</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="fphmCf" value="${ptdoi.fphm}" name="fphmCf" size="17"
							maxlength="10" /></td>
						<td style="text-align:right;"><font style="color:red;">*</font>购置税证明编号：
						</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="gzszmbhCf" value="${ptdoi.gzszmbh}" name="gzszmbhCf" size="17"
							maxlength="100" /></td>
					</tr>
					<tr>
						<td style="text-align:right;"><font style="color:red;">*</font>住所地址：</td>
						<td class="trs" colspan="5">&nbsp;<s:select
								cssClass="selectcss" list="#request.yzbmList" theme="simple"
								id="yzbm7Cf" listKey="dmz" listValue="dmz+':'+dmsm1"
								name="yzbm7Cf"></s:select> <input type="text" class="disabled"
							id="zsxxdzCf" value="${ptdoi.zsxxdz}" name="zsxxdzCf" size="50"
							maxlength="100" /></td>
					</tr>
					<tr>
						<td style="text-align:right;">邮寄地址：</td>
						<td class="trs" colspan="3">&nbsp;<s:select
								cssClass="selectcss" list="#request.yzbmList" theme="simple"
								id="yzbm6Cf" listKey="dmz" listValue="dmz+':'+dmsm1"
								name="yzbm6Cf"></s:select> <input type="text" class="disabled"
							id="zzxxdzCf" value="${ptdoi.zzxxdz}" name="zzxxdzCf" size="50"
							maxlength="100" /></td>
						<td style="text-align:right;">邮政编码：</td>
						<td><input type="text" class="disabled" id="yzbm1Cf"
							value="${ptdoi.yzbm1}" name="yzbm1Cf" size="10" maxlength="10" />
						</td>
					</tr>
					<tr>
						<td style="text-align:right;"><font style="color:red;">*</font>使用性质：</td>
						<td>&nbsp;<s:select cssClass="selectcss"
								list="#request.syxzList" theme="simple" id="syxzCf" headerKey=""
								headerValue="---请选择---" listKey="dmz" listValue="dmz+':'+dmsm1"
								name="syxzCf" value="#request.ptdoi.syxz"></s:select></td>

						<td style="text-align:right;"><font style="color:red;">*</font>所有权：</td>
						<td>&nbsp;<s:select cssClass="selectcss"
								list="#request.syqList" theme="simple" id="syqCf" headerKey=""
								headerValue="---请选择---" listKey="dmz" listValue="dmz+':'+dmsm1"
								name="syqCf" value="#request.ptdoi.syq"></s:select></td>

						<td style="text-align:right;"><font style="color:red;">*</font>获得方式：</td>
						<td>&nbsp;<s:select cssClass="selectcss"
								list="#request.hdfsList" theme="simple" id="hdfsCf" headerKey=""
								headerValue="---请选择---" listKey="dmz" listValue="dmz+':'+dmsm1"
								name="hdfsCf" value="#request.ptdoi.hdfs"></s:select></td>
					</tr>
					<tr>
						<td style="text-align:right;"><font style="color:red;">*</font>行政区划：</td>
						<td class="trs">&nbsp;<s:select cssClass="selectcss"
								list="#request.yzbmList" theme="simple" id="xzqhCf" headerKey=""
								headerValue="---请选择---" listKey="dmz" listValue="dmz+':'+dmsm1"
								name="xzqhCf" value="#request.ptdoi.zsxzqh"></s:select></td>

						<td style="text-align:right;"><font style="color:red;">*</font>号牌种类：</td>
						<td class="trs">&nbsp;<s:select cssClass="selectcss"
								list="#request.hpzlList" theme="simple" id="hpzlCf" headerKey=""
								headerValue="---请选择---" listKey="dmz" listValue="dmz+':'+dmsm1"
								name="hpzlCf" value="#request.ptdoi.hpzl"></s:select></td>

						<td></td>
						<td></td>
					</tr>
					<tr>
						<td style="text-align:right;">保险生效日期：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="sxrqCf" value="${fn:substring(ptdoi.sxrq,0,10)}" name="sxrqCf"
							size="10" maxlength="20"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:CurentTime()})" />
						</td>

						<td style="text-align:right;">保险终止日期：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="zzrqCf" value="${fn:substring(ptdoi.zzrq,0,10)}" name="zzrqCf"
							size="10" maxlength="20"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:CurentTime()})" />
						</td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td style="text-align: right;" style="width: 876px height: 21px;">&nbsp;
							<font style="color:red;">*</font>检验日期：</td>
						<td align="left" style="width: 196px height: 21px;">&nbsp;<input
							name="yxqzCf" id="yxqzCf" value="${fn:substring(ptdoi.yxqz,0,10)}"
							size="10" type="text"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:CurentTime()})" />
						</td>
						<td style="text-align: right;" style="width: 680px height: 21px;">&nbsp;
							承检单位：</td>
						<td style="width: 164px height: 21px;" colspan="3" align="left">&nbsp;<s:select
								cssClass="selectcss" list="#request.cjdwList" theme="simple"
								id="cjdwCf" headerKey="" headerValue="---请选择---" listKey="dmz"
								listValue="dmz+':'+dmsm1" name="cjdwCf"
								value="#request.ptdoi.cjdw"></s:select></td>
					</tr>
					<tr>
						<td style="text-align: right;" style="width: 876px height: 21px;">&nbsp;
							常停车场所：</td>
						<td colspan="3" align="left" style="width: 196px height: 21px;">&nbsp;<input
							name="tempBz2Cf" id="tempBz2Cf" value="${ptdoi.tempBz2}" size="60"
							maxlength="100" type="text" /></td>

						<td></td>
						<td></td>
					</tr>
				</table>

				<!-- 技术参数 -->
				<table border="1" cellpadding="0" cellspacing="0" id="tabCc3"
					class="table">
					<tr>
						<td style="text-align:right;"><font style="color:red;">*</font>车辆识别代号：</td>
						<td class="trs" colspan="3">&nbsp;<input type="text"
							class="disabled" id="ptdoi_clsbdh" value="${ptdoi.clsbdh}"
							name="ptdoi.clsbdh" size="30" maxlength="100" />&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="button" id="clsbbutid"
							onclick="javascript:cxPTMO('fcf');" value="查  询"
							style="cursor:pointer;width: 79px;height: 28px;border: none;background: url(<%=request.getContextPath()%>/images/an2.gif) no-repeat;color: #FFFFFF;font-weight: bold;">
						</td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td style="text-align:right;"><font style="color:red;">*</font>车辆型号：
						</td>
						<td class="trs" colspan="3">&nbsp;<input type="text"
							class="disabled" id="ptdoi_clxh" value="${ptdoi.clxh}"
							name="ptdoi.clxh" size="30" maxlength="32" /></td>
						<td style="text-align:right;">中文品牌：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="ptdoi_clpp1" value="${ptdoi.clpp1}" name="ptdoi.clpp1" size="20"
							maxlength="16" /></td>
					</tr>
					<tr>
						<td style="text-align:right;">英文品牌：</td>
						<td class="trs" colspan="3">&nbsp;<input type="text"
							class="disabled" id="ptdoi_clpp2" value="${ptdoi.clpp2}"
							name="ptdoi.clpp2" size="30" maxlength="16" />
						</td>

						<td style="text-align:right;"><font style="color:red;">*</font>车辆类型：</td>
						<td class="trs">&nbsp;<s:select cssClass="selectcss"
								list="#request.cllxList" theme="simple" id="ptdoi_cllx"
								headerKey="" headerValue="---请选择---" listKey="dmz"
								listValue="dmz+':'+dmsm1" name="ptdoi.cllx"
								value="#request.ptdoi.cllx"></s:select></td>
					</tr>
					<tr>
						<td style="text-align:right;"><font style="color:red;">*</font>制造国：</td>
						<td colspan="3">&nbsp;<s:select cssClass="selectcss"
								list="#request.zzgList" theme="simple" id="ptdoi_zzg"
								headerKey="" headerValue="---请选择---" listKey="dmz"
								listValue="dmz+':'+dmsm1" name="ptdoi.zzg"
								value="#request.ptdoi.zzg"></s:select></td>
						<td style="text-align:right;"><font style="color:red;">*</font>发动机号：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="ptdoi_fdjh" value="${ptdoi.fdjh}" name="ptdoi.fdjh" size="20"
							maxlength="100" /></td>
					</tr>

					<tr>
						<td style="text-align:right;">制造厂名称：</td>
						<td class="trs" colspan="3">&nbsp;<input type="text"
							class="disabled" id="ptdoi_zzcmc" value="${ptdoi.zzcmc}"
							name="ptdoi.zzcmc" size="20" maxlength="64" /> &nbsp;发合格证日期：<input
							name="ptdoi.fhgzrq" id="ptdoi_fhgzrq"
							value="${fn:substring(ptdoi.fhgzrq,0,10)}" size="10" type="text"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:CurentTime()})" />
						</td>

						<td style="text-align:right;">车身颜色：</td>
						<td>&nbsp;<s:select cssClass="selectcss"
								list="#request.csysList" theme="simple" id="ptdoi_csys1"
								headerKey="" headerValue="---请选择---" listKey="dmz"
								listValue="dmz+':'+dmsm1" name="ptdoi.csys"
								></s:select>
								<s:select cssClass="selectcss"
								list="#request.csysList" theme="simple" id="ptdoi_csys2"
								headerKey="" headerValue="---请选择---" listKey="dmz"
								listValue="dmz+':'+dmsm1" name="ptdoi.csys"
								></s:select>
								<s:select cssClass="selectcss"
								list="#request.csysList" theme="simple" id="ptdoi_csys3"
								headerKey="" headerValue="---请选择---" listKey="dmz"
								listValue="dmz+':'+dmsm1" name="ptdoi.csys"
								></s:select>
						</td>
					</tr>
					<tr>
						<td style="text-align:right;">出厂日期：</td>
						<td>&nbsp;<input name="ptdoi.ccrq" id="ptdoi_ccrq"
							value="${fn:substring(ptdoi.ccrq,0,10)}" size="10" type="text"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:CurentTime()})" />
						</td>

						<td style="text-align:right;">国产/进口：</td>
						<td>&nbsp;<s:select cssClass="selectcss"
								onchange="selChange(this);" list="#request.gcjkList"
								theme="simple" id="ptdoi_gcjk" headerKey=""
								headerValue="---请选择---" listKey="dmz" listValue="dmz+':'+dmsm1"
								name="ptdoi.gcjk" value="#request.ptdoi.gcjk"></s:select></td>
						<td id="ptdoi_gcjkTdHgzbh" style="text-align:right;"><font
							style="color:red;">*</font>合格证编号：</td>
						<td id="ptdoi_gcjkTdHgzbhValue" class="trs">&nbsp;<input
							type="text" class="disabled" id="ptdoi_hgzbh"
							value="${ptdoi.hgzbh}" name="ptdoi.hgzbh" size="30" maxlength="30" />
						</td>

						<td id="ptdoi_gcjkTdJgpzh" style="text-align:right;display: none;"><font
							style="color:red;">*</font>进口凭证号：</td>
						<td id="ptdoi_gcjkTdJgpzhValue" class="trs" style="display: none;">
							&nbsp;<input type="text" class="disabled" id="ptdoi_jkpzhm"
							value="${ptdoi.jkpzhm}" name="ptdoi.jkpzhm" size="30"
							maxlength="30" /></td>
					</tr>
					<tr>
						<td style="text-align:right;"><font style="color:red;">*</font>发动机型号：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="ptdoi_fdjxh" value="${ptdoi.fdjxh}" name="ptdoi.fdjxh" size="20"
							maxlength="100" /></td>

						<td style="text-align:right;">排量/功率：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="ptdoi_pl" value="${ptdoi.pl}" name="ptdoi.pl" size="1"
							maxlength="6" />ml <input type="text" class="disabled"
							id="ptdoi_gl" value="${ptdoi.gl}" name="ptdoi.gl" size="1"
							maxlength="7" />kw</td>

						<td style="text-align:right;">燃料种类：</td>
						<td class="trs">&nbsp;<s:select cssClass="selectcss"
								list="#request.rlzlList" theme="simple" id="ptdoi_rlzl"
								headerKey="" headerValue="---请选择---" listKey="dmz"
								listValue="dmz+':'+dmsm1" name="ptdoi.rlzl"
								value="#request.ptdoi.rlzl"></s:select></td>
					</tr>
					<tr>
						<td style="text-align:right;">外廓尺寸</td>
						<td class="trs" colspan="3">&nbsp;<input name="ptdoi.cwkc"
							type="text" id="ptdoi_cwkc" value="${ptdoi.cwkc}" size="3"
							maxlength="5" /> mm(长) <input name="ptdoi.cwkk" type="text"
							id="ptdoi_cwkk" value="${ptdoi.cwkk}" size="3" maxlength="4" />
							mm(宽) <input name="ptdoi.cwkg" type="text" id="ptdoi_cwkg"
							value="${ptdoi.cwkg}" size="3" maxlength="4" /> mm(高)</td>

						<td style="text-align:right;">转向形式：</td>
						<td class="trs">&nbsp;<s:select cssClass="selectcss"
								list="#request.zxxsList" theme="simple" id="ptdoi_zxxs"
								headerKey="" headerValue="---请选择---" listKey="dmz"
								listValue="dmz+':'+dmsm1" name="ptdoi.zxxs"
								value="#request.ptdoi.zxxs"></s:select></td>
					</tr>
					<tr>
						<td style="text-align:right;">货箱内部尺寸</td>
						<td class="trs" colspan="3">&nbsp;<input name="ptdoi.hxnbcd"
							type="text" id="ptdoi_hxnbcd" value="${ptdoi.hxnbcd}" size="3"
							maxlength="5" /> mm(长) <input name="ptdoi.hxnbkd" type="text"
							id="ptdoi_hxnbkd" value="${ptdoi.hxnbkd}" size="3" maxlength="4" />
							mm(宽) <input name="ptdoi.hxnbgd" type="text" id="ptdoi_hxnbgd"
							value="${ptdoi.hxnbgd}" size="3" maxlength="4" /> mm(高)</td>

						<td style="text-align:right;">环保达标情况：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="ptdoi_hbdbqk" value="${ptdoi.hbdbqk}" name="ptdoi.hbdbqk"
							size="20" maxlength="60" /></td>
					</tr>
					<tr>
						<td style="text-align:right;">钢板弹簧片数：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="ptdoi_gbthps" value="${ptdoi.gbthps}" name="ptdoi.gbthps"
							size="10" maxlength="3" />片</td>

						<td style="text-align:right;">轴数：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="ptdoi_zs" value="${ptdoi.zs}" name="ptdoi.zs" size="10"
							maxlength="1" />个</td>

						<td style="text-align:right;">轴距：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="ptdoi_zj" value="${ptdoi.zj}" name="ptdoi.zj" size="10"
							maxlength="5" />mm</td>
					</tr>
					<tr>
						<td style="text-align:right;">轮胎数：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="ptdoi_lts" value="${ptdoi.lts}" name="ptdoi.lts" size="5"
							maxlength="2" />个</td>

						<td style="text-align:right;">轮胎规格：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="ptdoi_ltgg" value="${ptdoi.ltgg}" name="ptdoi.ltgg" size="10"
							maxlength="50" /></td>

						<td style="text-align:right;">轮距：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="ptdoi_qlj" value="${ptdoi.qlj}" name="ptdoi.qlj" size="5"
							maxlength="4" />mm(前) &nbsp;&nbsp;<input type="text"
							class="disabled" id="ptdoi_hlj" value="${ptdoi.hlj}"
							name="ptdoi.hlj" size="5" maxlength="4" />mm(后)</td>
					</tr>
					<tr>
						<td style="text-align:right;">总质量：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="ptdoi_zzl" value="${ptdoi.zzl}" name="ptdoi.zzl" size="5"
							maxlength="8" />kg</td>

						<td style="text-align:right;">整备质量：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="ptdoi_zbzl" value="${ptdoi.zbzl}" name="ptdoi.zbzl" size="5"
							maxlength="8" />kg</td>

						<td style="text-align:right;">核定载客：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="ptdoi_hdzk" value="${ptdoi.hdzk}" name="ptdoi.hdzk" size="5"
							maxlength="3" />人</td>
					</tr>
					<tr>
						<td style="text-align:right;">准牵引总质量：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="ptdoi_zqyzl" value="${ptdoi.zqyzl}" name="ptdoi.zqyzl" size="5"
							maxlength="8" />个</td>

						<td style="text-align:right;">核定载质量：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="ptdoi_hdzzl" value="${ptdoi.hdzzl}" name="ptdoi.hdzzl" size="5"
							maxlength="8" />个</td>

						<td style="text-align:right;">驾驶室载客：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="ptdoi_qpzk" value="${ptdoi.qpzk}" name="ptdoi.qpzk" size="5"
							maxlength="1" />人(前) &nbsp;&nbsp;<input type="text"
							class="disabled" id="ptdoi_hpzk" value="${ptdoi.hpzk}"
							name="ptdoi.hpzk" size="5" maxlength="1" />人(后)</td>
					</tr>
				</table>

				<!-- 技术参数重复 -->
				<table border="0" cellpadding="0" cellspacing="0" id="tabCc4"
					class="table">
					<tr>
						<td style="text-align:right;"><font style="color:red;">*</font>车辆识别代号：</td>
						<td class="trs" colspan="3">&nbsp;<input type="text"
							class="disabled" id="clsbdhCf" value="${ptdoi.clsbdh}"
							name="clsbdhCf" size="30" maxlength="100" />&nbsp;&nbsp;&nbsp;&nbsp;
							<!-- <input type="button" onclick="javascript:cxPTMO('cf');" value="查  询" style="cursor:pointer;width: 79px;height: 28px;border: none;background: url(<%=request.getContextPath()%>/images/an2.gif) no-repeat;color: #FFFFFF;font-weight: bold;"> -->

						</td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td style="text-align:right;"><font style="color:red;">*</font>车辆型号：
						</td>
						<td class="trs" colspan="3">&nbsp;<input type="text"
							class="disabled" id="clxhCf" value="${ptdoi.clxh}" name="clxhCf"
							size="30" maxlength="32" /></td>
						<td style="text-align:right;">中文品牌：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="clpp1Cf" value="${ptdoi.clpp1}" name="clpp1Cf" size="20"
							maxlength="16" /></td>
					</tr>
					<tr>
						<td style="text-align:right;">英文品牌：</td>
						<td class="trs" colspan="3">&nbsp;<input type="text"
							class="disabled" id="clpp2Cf" value="${ptdoi.clpp2}"
							name="clpp2Cf" size="30" maxlength="16" />
						</td>

						<td style="text-align:right;"><font style="color:red;">*</font>车辆类型：</td>
						<td class="trs">&nbsp;<s:select cssClass="selectcss"
								list="#request.cllxList" theme="simple" id="cllxCf" headerKey=""
								headerValue="---请选择---" listKey="dmz" listValue="dmz+':'+dmsm1"
								name="cllxCf" value="#request.ptdoi.cllx"></s:select></td>
					</tr>
					<tr>
						<td style="text-align:right;"><font style="color:red;">*</font>制造国：</td>
						<td colspan="3">&nbsp;<s:select cssClass="selectcss"
								list="#request.zzgList" theme="simple" id="zzgCf" headerKey=""
								headerValue="---请选择---" listKey="dmz" listValue="dmz+':'+dmsm1"
								name="zzgCf" value="#request.ptdoi.zzg"></s:select></td>
						<td style="text-align:right;"><font style="color:red;">*</font>发动机号：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="fdjhCf" value="${ptdoi.fdjh}" name="fdjhCf" size="20"
							maxlength="100" /></td>
					</tr>
					<tr>
						<td style="text-align:right;">制造厂名称：</td>
						<td class="trs" colspan="3">&nbsp;<input type="text"
							class="disabled" id="zzcmcCf" value="${ptdoi.zzcmc}"
							name="zzcmcCf" size="20" maxlength="64" /> &nbsp;发合格证日期：<input
							name="fhgzrqCf" id="fhgzrqCf"
							value="${fn:substring(ptdoi.fhgzrq,0,10)}" size="10" type="text"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:CurentTime()})" />
						</td>

						<td style="text-align:right;">车身颜色：</td>
						<td>&nbsp;<s:select cssClass="selectcss"
								list="#request.csysList" theme="simple" id="csysCf" headerKey=""
								headerValue="---请选择---" listKey="dmz" listValue="dmz+':'+dmsm1"
								name="csysCf" value="#request.ptdoi.csys"></s:select></td>
					</tr>
					<tr>
						<td style="text-align:right;">出厂日期：</td>
						<td>&nbsp;<input name="ccrqCf" id="ccrqCf"
							value="${fn:substring(ptdoi.ccrq,0,10)}" size="10" type="text"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:CurentTime()})" />
						</td>

						<td style="text-align:right;">国产/进口：</td>
						<td>&nbsp;<s:select cssClass="selectcss"
								onchange="selChange(this);" list="#request.gcjkList"
								theme="simple" id="gcjkCf" headerKey="" headerValue="---请选择---"
								listKey="dmz" listValue="dmz+':'+dmsm1" name="gcjkCf"
								value="#request.ptdoi.gcjk"></s:select></td>

						<td id="gcjkCfTdHgzbh" style="text-align:right;"><font
							style="color:red;">*</font>合格证编号：</td>
						<td id="gcjkCfTdHgzbhValue" class="trs">&nbsp;<input
							type="text" class="disabled" id="hgzbhCf" value="${ptdoi.hgzbh}"
							name="hgzbhCf" size="30" maxlength="30" />
						</td>

						<td id="gcjkCfTdJgpzh" style="text-align:right;display: none;"><font
							style="color:red;">*</font>进口凭证号：</td>
						<td id="gcjkCfTdJgpzhValue" class="trs" style="display: none;">
							&nbsp;<input type="text" class="disabled" id="jkpzhmCf"
							value="${ptdoi.jkpzhm}" name="jkpzhmCf" size="30" maxlength="30" />
						</td>

					</tr>
					<tr>
						<td style="text-align:right;"><font style="color:red;">*</font>发动机型号：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="fdjxhCf" value="${ptdoi.fdjxh}" name="fdjxhCf" size="20"
							maxlength="100" /></td>

						<td style="text-align:right;">排量/功率：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="plCf" value="${ptdoi.pl}" name="plCf" size="1" maxlength="6" />ml
							<input type="text" class="disabled" id="glCf" value="${ptdoi.gl}"
							name="glCf" size="1" maxlength="7" />kw</td>

						<td style="text-align:right;">燃料种类：</td>
						<td class="trs">&nbsp;<s:select cssClass="selectcss"
								list="#request.rlzlList" theme="simple" id="rlzlCf" headerKey=""
								headerValue="---请选择---" listKey="dmz" listValue="dmz+':'+dmsm1"
								name="rlzlCf" value="#request.ptdoi.rlzl"></s:select></td>
					</tr>
					<tr>
						<td style="text-align:right;">外廓尺寸</td>
						<td class="trs" colspan="3">&nbsp;<input name="cwkcCf"
							type="text" id="cwkcCf" value="${ptdoi.cwkc}" size="3"
							maxlength="5" /> mm(长) <input name="cwkkCf" type="text"
							id="cwkkCf" value="${ptdoi.cwkk}" size="3" maxlength="4" /> mm(宽)
							<input name="cwkgCf" type="text" id="cwkgCf" value="${ptdoi.cwkg}"
							size="3" maxlength="4" /> mm(高)</td>

						<td style="text-align:right;">转向形式：</td>
						<td class="trs">&nbsp;<s:select cssClass="selectcss"
								list="#request.zxxsList" theme="simple" id="zxxsCf" headerKey=""
								headerValue="---请选择---" listKey="dmz" listValue="dmz+':'+dmsm1"
								name="zxxsCf" value="#request.ptdoi.zxxs"></s:select></td>
					</tr>
					<tr>
						<td style="text-align:right;">货箱内部尺寸</td>
						<td class="trs" colspan="3">&nbsp;<input name="hxnbcdCf"
							type="text" id="hxnbcdCf" value="${ptdoi.hxnbcd}" size="3"
							maxlength="5" /> mm(长) <input name="hxnbkdCf" type="text"
							id="hxnbkdCf" value="${ptdoi.hxnbkd}" size="3" maxlength="4" />
							mm(宽) <input name="hxnbgdCf" type="text" id="hxnbgdCf"
							value="${ptdoi.hxnbgd}" size="3" maxlength="4" /> mm(高)</td>

						<td style="text-align:right;">环保达标情况：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="hbdbqkCf" value="${ptdoi.hbdbqk}" name="hbdbqkCf" size="20"
							maxlength="60" /></td>
					</tr>
					<tr>
						<td style="text-align:right;">钢板弹簧片数：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="gbthpsCf" value="${ptdoi.gbthps}" name="gbthpsCf" size="10"
							maxlength="3" />片</td>

						<td style="text-align:right;">轴数：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="zsCf" value="${ptdoi.zs}" name="zsCf" size="10" maxlength="1" />个
						</td>

						<td style="text-align:right;">轴距：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="zjCf" value="${ptdoi.zj}" name="zjCf" size="10" maxlength="5" />mm
						</td>
					</tr>
					<tr>
						<td style="text-align:right;">轮胎数：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="ltsCf" value="${ptdoi.lts}" name="ltsCf" size="5"
							maxlength="2" />个</td>

						<td style="text-align:right;">轮胎规格：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="ltggCf" value="${ptdoi.ltgg}" name="ltggCf" size="10"
							maxlength="50" /></td>

						<td style="text-align:right;">轮距：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="qljCf" value="${ptdoi.qlj}" name="qljCf" size="5"
							maxlength="4" />mm(前) &nbsp;&nbsp;<input type="text"
							class="disabled" id="hljCf" value="${ptdoi.hlj}" name="hljCf"
							size="5" maxlength="4" />mm(后)</td>
					</tr>
					<tr>
						<td style="text-align:right;">总质量：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="zzlCf" value="${ptdoi.zzl}" name="zzlCf" size="5"
							maxlength="8" />kg</td>

						<td style="text-align:right;">整备质量：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="zbzlCf" value="${ptdoi.zbzl}" name="zbzlCf" size="5"
							maxlength="8" />kg</td>

						<td style="text-align:right;">核定载客：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="hdzkCf" value="${ptdoi.hdzk}" name="hdzkCf" size="5"
							maxlength="3" />人</td>
					</tr>
					<tr>
						<td style="text-align:right;">准牵引总质量：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="zqyzlCf" value="${ptdoi.zqyzl}" name="zqyzlCf" size="5"
							maxlength="8" />个</td>

						<td style="text-align:right;">核定载质量：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="hdzzlCf" value="${ptdoi.hdzzl}" name="hdzzlCf" size="5"
							maxlength="8" />个</td>

						<td style="text-align:right;">驾驶室载客：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="qpzkCf" value="${ptdoi.qpzk}" name="qpzkCf" size="5"
							maxlength="1" />人(前) &nbsp;&nbsp;<input type="text"
							class="disabled" id="hpzkCf" value="${ptdoi.hpzk}" name="hpzkCf"
							size="5" maxlength="1" />人(后)</td>
					</tr>
				</table>

				<!-- 发票信息
				<table border="0" cellpadding="0" cellspacing="0" id="tabCc5" class="table">
					<tr>
						<td style="text-align:right;"><font style="color:red;">*</font>发票交易号：
						</td>
						<td class="trs">&nbsp;<input type="text" class="disabled" id="ptdoi_netFph" value="${ptdoi.netFph}" name="ptdoi.netFph" size="17" maxlength="30"/>
						</td>
						<td style="text-align:right;">
							<font style="color:red;">*</font>纳税证明：
						</td>
						<td class="trs">&nbsp;<input type="text" class="disabled" id="ptdoi_nszm" value="${ptdoi.nszm}" name="ptdoi.nszm" size="17" maxlength="10"/>
						</td>
						<td style="text-align:right;">
							<font style="color:red;">*</font>纳税证明编号：
						</td>
						<td class="trs">&nbsp;<input type="text" class="disabled" id="ptdoi_nszmbh" value="${ptdoi.nszmbh}" name="ptdoi.nszmbh" size="20" maxlength="100"/>
						</td>
					</tr>
					<tr>
						<td style="text-align:right;">销售单位：
						</td>
						<td class="trs" colspan="5">&nbsp;<input type="text" class="disabled" id="ptdoi_xsdw" value="${ptdoi.xsdw}" name="ptdoi.xsdw" size="30" maxlength="64"/>
						</td>
					</tr>
					<tr>
						<td colspan="6" height="50" style="text-align: center;">
			    			<input type="button" onclick="javascript:fpxxClick()" value="下一步" style="cursor:pointer;width: 79px;height: 28px;border: none;background: url(<%=request.getContextPath()%>/images/an2.gif) no-repeat;color: #FFFFFF;font-weight: bold;">
			    			<input type="button" onclick="javascript:fhClick('4');" value="返 回" style="cursor:pointer;width: 79px;height: 28px;border: none;background: url(<%=request.getContextPath()%>/images/an2.gif) no-repeat;color: #FFFFFF;font-weight: bold;">
						</td>
					</tr>
				</table> -->

				<!-- 发票信息重复
				<table border="0" cellpadding="0" cellspacing="0" id="tabCc6" class="table">
					<tr>
						<td style="text-align:right;"><font style="color:red;">*</font>发票交易号：
						</td>
						<td class="trs">&nbsp;<input type="text" class="disabled" id="netFphCf" value="${ptdoi.netFph}" name="netFphCf" size="17" maxlength="30"/>
						</td>
						<td style="text-align:right;">
							<font style="color:red;">*</font>纳税证明：
						</td>
						<td class="trs">&nbsp;<input type="text" class="disabled" id="nszmCf" value="${ptdoi.nszm}" name="nszmCf" size="17" maxlength="10"/>
						</td>
						<td style="text-align:right;">
							<font style="color:red;">*</font>纳税证明编号：
						</td>
						<td class="trs">&nbsp;<input type="text" class="disabled" id="nszmbhCf" value="${ptdoi.nszmbh}" name="nszmbhCf" size="20" maxlength="100"/>
						</td>
					</tr>
					<tr>
						<td style="text-align:right;">销售单位：
						</td>
						<td class="trs" colspan="5">&nbsp;<input type="text" class="disabled" id="xsdwCf" value="${ptdoi.xsdw}" name="xsdwCf" size="30" maxlength="64"/>
						</td>
					</tr>
					<tr>
						<td colspan="6" height="50" style="text-align: center;">
			    			<input type="button" onclick="javascript:fpxxCfClick()" value="下一步" style="cursor:pointer;width: 79px;height: 28px;border: none;background: url(<%=request.getContextPath()%>/images/an2.gif) no-repeat;color: #FFFFFF;font-weight: bold;">
			    			<input type="button" onclick="javascript:fhClick('5');" value="返 回" style="cursor:pointer;width: 79px;height: 28px;border: none;background: url(<%=request.getContextPath()%>/images/an2.gif) no-repeat;color: #FFFFFF;font-weight: bold;">
						</td>
					</tr>
				</table> -->

				<!-- 代理人信息 -->
				<table border="0" cellpadding="0" cellspacing="0" id="tabCc5"
					class="table">
					<tr>
						<td style="text-align:right;"><font style="color:red;">*</font>代理人姓名：
						</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="ptdoi_netXm" value="${ptdoi.netXm}" name="ptdoi.netXm" size="17"
							maxlength="30" /></td>
						<td style="text-align:right;"><font style="color:red;">*</font>代理人联系电话：
						</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="ptdoi_netLxdh" value="${ptdoi.netLxdh}" name="ptdoi.netLxdh"
							size="17" maxlength="10" /></td>
						<td style="text-align:right;"><font style="color:red;">*</font>代理人邮政编码：
						</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="ptdoi_netYzbm" value="${ptdoi.netYzbm}" name="ptdoi.netYzbm"
							size="10" maxlength="10" /></td>
					</tr>
					<tr>
						<td style="text-align:right;"><font style="color:red;">*</font>代理人邮寄地址：
						</td>
						<td class="trs" colspan="5">&nbsp;<input type="text"
							class="disabled" id="ptdoi_netYjdz" value="${ptdoi.netYjdz}"
							name="ptdoi.netYjdz" size="60" maxlength="100" /></td>
					</tr>
					<tr>
						<td style="text-align:right;">代理人电子邮箱：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="ptdoi_netDzxx" value="${ptdoi.netDzxx}" name="ptdoi.netDzxx"
							size="17" maxlength="30" /></td>
						<td style="text-align:right;"><font style="color:red;">*</font>经办人姓名：
						</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="ptdoi_netJbrxm" value="${ptdoi.netJbrxm}" name="ptdoi.netJbrxm"
							size="17" maxlength="10" /></td>
						<td style="text-align:right;"><font style="color:red;">*</font>经办人联系电话：
						</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="ptdoi_netJbrlxdh" value="${ptdoi.netJbrlxdh}"
							name="ptdoi.netJbrlxdh" size="20" maxlength="100" /></td>
					</tr>
				</table>

				<!-- 代理人信息重复 -->
				<table border="0" cellpadding="0" cellspacing="0" id="tabCc6"
					class="table">
					<tr>
						<td style="text-align:right;"><font style="color:red;">*</font>代理人姓名：
						</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="netXmCf" value="${ptdoi.netXm}" name="netXmCf" size="17"
							maxlength="30" /></td>
						<td style="text-align:right;"><font style="color:red;">*</font>代理人联系电话：
						</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="netLxdhCf" value="${ptdoi.netLxdh}" name="netLxdhCf" size="17"
							maxlength="10" /></td>
						<td style="text-align:right;"><font style="color:red;">*</font>代理人邮政编码：
						</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="netYzbmCf" value="${ptdoi.netYzbm}" name="netYzbmCf" size="10"
							maxlength="10" /></td>
					</tr>
					<tr>
						<td style="text-align:right;"><font style="color:red;">*</font>代理人邮寄地址：
						</td>
						<td class="trs" colspan="5">&nbsp;<input type="text"
							class="disabled" id="netYjdzCf" value="${ptdoi.netYjdz}"
							name="netYjdzCf" size="60" maxlength="100" /></td>
					</tr>
					<tr>
						<td style="text-align:right;">代理人电子邮箱：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="netDzxxCf" value="${ptdoi.netDzxx}" name="netDzxxCf" size="17"
							maxlength="30" /></td>
						<td style="text-align:right;"><font style="color:red;">*</font>经办人姓名：
						</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="netJbrxmCf" value="${ptdoi.netJbrxm}" name="netJbrxmCf"
							size="17" maxlength="10" /></td>
						<td style="text-align:right;"><font style="color:red;">*</font>经办人联系电话：
						</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="netJbrlxdhCf" value="${ptdoi.netJbrlxdh}" name="netJbrlxdhCf"
							size="20" maxlength="100" /></td>
					</tr>
				</table>

				<!-- 邮寄信息 -->
				<table border="0" cellpadding="0" cellspacing="0" id="tabCc7"
					class="table">
					<tr>
						<td style="text-align:right;" colspan="2">驾驶证邮递 <input
							type="checkbox" id="ptdoi_postZj" value="0"
							onclick="jsz_jdc_select(this)" name="ptdoi.postZj" /></td>

						<td style="text-align:right;"></td>
						<td class="trs">&nbsp;</td>
						<td style="text-align:left;" colspan="2">机动车号牌邮递<input
							type="checkbox" id="ptdoi_postTp" onclick="jsz_jdc_select(this)"
							value="0" name="ptdoi.postTp" /></td>
					</tr>
					<tr>
						<td style="text-align:right;">收件人地址：</td>
						<td class="trs" colspan="5">&nbsp;<input type="text"
							class="disabled" id="ptdoi_postYjdz" value="${ptdoi.postYjdz}"
							name="ptdoi.postYjdz" size="60" maxlength="100" /></td>
					</tr>
					<tr>
						<td style="text-align:right;">收件人姓名：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="ptdoi_postSjr" value="${ptdoi.postSjr}" name="ptdoi.postSjr"
							size="17" maxlength="30" /></td>
						<td style="text-align:right;">联系电话：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="ptdoi_postLxdh" value="${ptdoi.postLxdh}" name="ptdoi.postLxdh"
							size="17" maxlength="10" /></td>
						<td style="text-align:right;">邮政编码：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="ptdoi_postYzbm" value="${ptdoi.postYzbm}" name="ptdoi.postYzbm"
							size="10" maxlength="100" /></td>
					</tr>
				</table>
				<table border="0" cellpadding="0" cellspacing="0" id="czbttrid">
					<tr>
						<td colspan="6" height="50" style="text-align: center;"><input
							type="button" onclick="javascript:yzAll();" value="修 改"
							style="cursor:pointer;width: 79px;height: 28px;border: none;background: url(<%=request.getContextPath()%>/images/an2.gif) no-repeat;color: #FFFFFF;font-weight: bold;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							
							<input type="button" style="cursor:pointer;width: 79px;height: 28px;border: none;background: url(<%=request.getContextPath()%>/images/an2.gif) no-repeat;color: #FFFFFF;font-weight: bold;" onclick="javascript:window.location.href = '<%=request.getContextPath()%>/yanche/lsgl_initYwlsglVehFlowListt.action?gw=2001';" value="返 回" />
						</td>
					</tr>
				</table>
			</form>
		</div>
	</center>
	<div></div>
	<div id="divSelPtmoValue"
		style="display:none;overflow-y:scroll;overflow-x:none;width:850px;height: 200px;">
		<table width="75%" border="0" cellpadding="0" cellspacing="0"
			class="table">
			<tr class="tr1" id="selTr">
				<th>验车流水</th>
				<th>车辆识别代码</th>
				<th>车辆型号</th>
				<th>发动机号</th>
				<th>中文品牌</th>
				<th>英文品牌</th>
				<th>验车结果</th>
				<th>操作</th>
			</tr>
			<tbody id="selTab">

			</tbody>
		</table>
	</div>
	<div id="zthfyydivid"
		style="display:none;overflow-y:scroll;overflow-x:none;width:550px;height: 250px;">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="edittable">
			<tr class="tr1" id="zthftrid">
				<td style="text-align:right;width:20%;">
					暂停与恢复原因：
				</td>
				<td>
					<s:iterator id="ztyy" value="#request.ztyylist" status="st">
						<c:if test="${((st.count+1)%2) == 0}"></br></c:if>
						<input type="checkbox" id="ztyyid${st.count}" name="ztyynames" value="${ztyy[0] }" />&nbsp;${ztyy[1]}&nbsp;&nbsp;&nbsp;&nbsp;
					</s:iterator>
				</td>
			</tr>
			<tr class="tr1">
				<td style="text-align:right;width:20%;">
					备注：
				</td>
				<td>
					<input type="text" id="zthfsmid" size="50" />
				</td>
			</tr>
		</table>
	</div>
	<div id="zxykyydivid"
		style="display:none;overflow-y:scroll;overflow-x:none;width:550px;height: 100px;">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="edittable">
			<tr class="tr1">
				<td style="text-align:right;width:20%;">
					转嫌疑库原因：
				</td>
				<td>
					<s:iterator id="zxykyy" value="#request.zxykyylist" status="st">
						<c:if test="${((st.count+1)%2) == 0}"></br></c:if>
						<input type="checkbox" id="ztyyid${st.count}" name="zxykyynames" value="${zxykyy[0] }" />&nbsp;${zxykyy[1]}&nbsp;&nbsp;&nbsp;&nbsp;
					</s:iterator>
				</td>
			</tr>
			<tr class="tr1">
				<td style="text-align:right;width:20%;">
					备注：
				</td>
				<td>
					<input type="text" id="zxyksmid" size="50" />
				</td>
			</tr>
		</table>
	</div>
	<div id="tbyydivid"
		style="display:none;overflow-y:scroll;overflow-x:none;width:550px;height: 100px;">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="edittable">
			<tr class="tr1">
				<td style="text-align:right;width:20%;">
					退办原因：
				</td>
				<td>
					<s:iterator id="tbyy" value="#request.tbyylist" status="st">
						<c:if test="${((st.count+1)%2) == 0}"></br></c:if>
						<input type="checkbox" id="tbyyid${st.count}" name="tbyynames" value="${tbyy[0] }" />&nbsp;${tbyy[1]}&nbsp;&nbsp;&nbsp;&nbsp;
					</s:iterator>
				</td>
			</tr>
			<tr class="tr1">
				<td style="text-align:right;width:20%;">
					备注：
				</td>
				<td>
					<input type="text" id="tbsmid" size="50" />
				</td>
			</tr>
		</table>
	</div>
	<div id="tcygyydivid"
		style="display:none;overflow-y:scroll;overflow-x:none;width:550px;height: 100px;">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="edittable">
			<tr class="tr1">
				<td style="text-align:right;width:20%;">
					退查验岗原因：
				</td>
				<td>
					<s:iterator id="tcygyy" value="#request.tcygyylist" status="st">
						<c:if test="${((st.count+1)%2) == 0}"></br></c:if>
						<input type="checkbox" id="tcygyyid${st.count}" name="tcygyynames" value="${tcygyy[0] }" />&nbsp;${tcygyy[1]}&nbsp;&nbsp;&nbsp;&nbsp;
					</s:iterator>
				</td>
			</tr>
			<tr class="tr1">
				<td style="text-align:right;width:20%;">
					备注：
				</td>
				<td>
					<input type="text" id="tcygsmid" size="50" />
				</td>
			</tr>
		</table>
	</div>
</body>
</html>