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
			var zj = '${requestScope.ptdo.postZj}';
			if(zj == '1'){
				document.getElementById("ptdo.postZj").value = '1';
				document.getElementById("ptdo.postZj").checked = true;
			}else{
				document.getElementById("ptdo.postZj").value = '0';
			}
			
			var tp = '${requestScope.ptdo.postTp}';
			if(tp == '1'){
				document.getElementById("ptdo.postTp").value = '1';
				document.getElementById("ptdo.postTp").checked = true;
			}else{
				document.getElementById("ptdo.postTp").value = '0';
			}
			var csyss = '${requestScope.ptdo.csys}';
			var csys = csyss.split(",");
			var csysItem = document.getElementsByName("ptdo.csys");
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
			
			
			if("1001" == gw){
				$("#bttcygid").hide();
			}
			if("1" == type){
				$("#czbttrid").hide();
			}else if("2" == type){
				<s:iterator id="xgzd" value="#request.ywlsglVehFieldgllist">
					var zdmc = '${xgzd.zdmc}';
					$("#ptdo_" + zdmc).removeAttr("disabled");
					$("#" + zdmc + "Cf").removeAttr("disabled");
					if(zdmc == "clsbdh"){
						$("#clsbbutid").show();
					}
				</s:iterator>
			}
		}
		
		function cxPTMO(cf){
			
			var clsbdh = "";
			
			// 得到车辆识别代号 fcf 表示非重复，另外一个值为 cf 重复。
			if(cf == "fcf")
				clsbdh = document.getElementById("ptdo.clsbdh");
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
															document.getElementById("ptdo.clsbdh").value = document.getElementById(this.id+"1").value;
															document.getElementById("ptdo.clxh").value = document.getElementById(this.id+"2").value;
															document.getElementById("ptdo.fdjh").value = document.getElementById(this.id+"3").value;
															document.getElementById("ptdo.clpp1").value = document.getElementById(this.id+"4").value;
															document.getElementById("ptdo.clpp2").value = document.getElementById(this.id+"5").value;
															document.getElementById("ptdo.gcjk").value = document.getElementById(this.id+"7").value;
															document.getElementById("ptdo.hgzbh").value = document.getElementById(this.id+"8").value;
															document.getElementById("ptdo.jkpzhm").value = document.getElementById(this.id+"9").value;
															document.getElementById("ptdo.csys").value = document.getElementById(this.id+"10").value;
															document.getElementById("ptdo.netJbrxm").value = document.getElementById(this.id+"11").value;
															document.getElementById("ptdo.outIn").value = "OUT";
															document.getElementById("ptdo.ccrq").value = document.getElementById(this.id+"14").value;
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
														selChange(document.getElementById("ptdo.gcjk"));
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
			var fpdm = document.getElementById("ptdo.fpdm");
			var fphm = document.getElementById("ptdo.fphm");
			var gzsbh = document.getElementById("ptdo.gzszmbh");
			var syr = document.getElementById("ptdo.syr");
			var clsbdh = document.getElementById("ptdo.clsbdh");
			var xh = document.getElementById("ptdo.xh");
			var yzGsInfo = document.getElementById("ptdo.yzGsInfo"); // 0表示国税验证通过，1国税验证未通过
			var clxh = document.getElementById("ptdo.clxh");
			var fdjxh = document.getElementById("ptdo.fdjxh");
			var gcjk = document.getElementById("ptdo.gcjk");
			// 先判断所有信息是否输入完全
			pd = djcsClick();
			if(pd == false){
				ycTable('tabCc1');
				return false;
			}
			/*pd = djcsCfClick();
			if(pd == false){
				ycTable('tabCc2');
				return false;
			}*/
			
			pd = jscsClick();
			if(pd == false){
				ycTable('tabCc3');
				return false;
			}
			/*pd = jscsCfClick();
			if(pd == false){
				ycTable('tabCc4');
				return false;
			}*/
			
			pd = dlrxxCheck();
			if(pd == false){
				ycTable('tabCc5');
				return false;
			}
			/*pd = dlrxxCfCheck();
			if(pd = false){
				ycTable('tabCc6');
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
			var gcjkValue = "";
			
			if(pdGcJk(gcjk.value)){
				gcjkValue = "gc";
			}else{
				gcjkValue = "jk";
			}
			
			$.ajax({
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
			});
			if(pd == false){
				return false;
			}
			
			
			// 如果用户是做修改操作。无需判断是否已经做过预受理。
			if(xh.value != "" && xh.value.trim() != "" && xh.value != null
			&& xh.value != undefined){
				// 判断用户是否有选择验车方式
				var ycfsValue = document.getElementById("ptdo.outIn");
				if(ycfsValue.value == ""){
					ycfs();
					return false;
				}
				var imgurl = '<%=request.getContextPath()%>/artDialog4.1.6/skins/icons/loading2.gif';
				chuli = art.dialog({
				    content: '<div style=\"text-align:center; text-indent:-999em; overflow:hidden; background:url('+imgurl+') no-repeat center center;\"><span>loading..</span></div><br/>数据处理中,请稍候.....',
				    title: '数据处理中',
	    			lock: true,
				    opacity: 0.87
				});
				$(":text").removeAttr("disabled");
				$(".selectcss").removeAttr("disabled");
				$(":checkbox").removeAttr("disabled");
				user_form.submit();
			}
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
					var lsh = $("#ptdo_lsh").val();
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
					var lsh = $("#ptdo_lsh").val();
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
					var lsh = $("#ptdo_lsh").val();
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
					var lsh = $("#ptdo_lsh").val();
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
				action="<%=request.getContextPath()%>/yanche/lsgl_editPtdo.action"
				method="post" id="user_form" name="user_form" target="abc">
				<iframe id="abc" name="abc" width="0" height="0" style="display: none;"></iframe>
				<table class="table">
					<tr>
						<th id="tabCc1Th" onclick="ycTable('tabCc1')" class="thColor">登记参数
							<input id="ptdo_outIn" name="ptdo.outIn" type="hidden" value="${ptdo.outIn}" />
							<input id="ptdo_ywlx" name="ptdo.ywlx" type="hidden" value="A" />
							<input id="ptdo_ywyy" name="ptdo.ywyy" type="hidden" value="A" />
							<input id="ptdo_xh" name="ptdo.xh" type="hidden" value="${ptdo.xh}" />
							<input id="ptdo_lsh" name="ptdo.lsh" type="hidden" value="${ptdo.lsh}" />
							<input id="ptdo_synFlag" name="ptdo.synFlag" type="hidden" value="${ptdo.synFlag}" />
							<input id="ptdo_yzGsInfo" name="ptdo.yzGsInfo" type="hidden" value="${ptdo.yzGsInfo}" />
							<input id="ptdo_chrq" name="ptdo.chrq" type="hidden" value="${ptdo.chrq}" />
							<input id="lsztid" value="${ywlsglVehFlow.xh}" type="hidden" />
							<input id="typeid" value="${type}" name="type" type="hidden" />
							<input id="gwid" value="${gw}" name="gw" type="hidden" />
							<input id="ldid" value="${ld}" name="ld" type="hidden" />
							<input id="ldid" value="${thyj}" name="thyj" type="hidden" />
						</th>
						<!-- <th id="tabCc2Th" onclick="ycTable('tabCc2')" class="">登记参数重复</th> -->
						<th id="tabCc3Th" onclick="ycTable('tabCc3')" class="">技术参数</th>
						<!-- <th id="tabCc4Th" onclick="ycTable('tabCc4')" class="">技术参数重复</th> -->
						<!--<th id="tabCc5Th" onclick="ycTable('tabCc5')" class="">发票信息</th>
						<th id="tabCc6Th" onclick="ycTable('tabCc6')" class="">发票信息重复</th>-->
						<th id="tabCc5Th" onclick="ycTable('tabCc5')" class="">代理人信息</th>
						<!-- <th id="tabCc6Th" onclick="ycTable('tabCc6')" class="">代理信息重复</th>-->
						<th id="tabCc7Th" onclick="ycTable('tabCc7')" class="">邮寄信息</th>
					</tr>
				</table>
				<!-- 登记参数 -->
				<table border="0" cellpadding="0" cellspacing="0" id="tabCc1"
					class="table">
					<tr>
						<td style="text-align:right;"><font style="color:red;">*</font>身份证明名称：
						</td>
						<td class="trs">&nbsp;<s:select cssClass="selectcss"
								list="#request.sfzmList" theme="simple" id="ptdo_sfzmmc"
								headerKey="" headerValue="---请选择---" listKey="dmz"
								listValue="dmz+':'+dmsm1" name="ptdo.sfzmmc"
								value="#request.ptdo.sfzmmc"></s:select></td>
						<td style="text-align:right;"><font style="color:red;">*</font>身份证明号码：
						</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="ptdo_sfzmhm" value="${ptdo.sfzmhm}" name="ptdo.sfzmhm"
							size="17" maxlength="30" /></td>
						<td style="text-align:right;"><font style="color:red;">*</font>居住/暂住证号：
						</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="ptdo_zzz" value="${ptdo.zzz}" name="ptdo.zzz" size="20"
							maxlength="100" /></td>
					</tr>
					<tr>
						<td style="text-align:right;"><font style="color:red;">*</font>姓名/名称：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="ptdo_syr" value="${ptdo.syr}" name="ptdo.syr" size="17"
							maxlength="50" />
						</td>

						<td style="text-align:right;"><font style="color:red;">*</font>移动电话：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="ptdo_lxdh" value="${ptdo.lxdh}" name="ptdo.lxdh" size="17"
							maxlength="50" />
						</td>

						<td style="text-align:right;">电子邮箱：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="ptdo_dzyx" value="${ptdo.dzyx}" name="ptdo.dzyx" size="20"
							maxlength="30" />
						</td>
					</tr>
					<tr>
						<td style="text-align:right;"><font style="color:red;">*</font>发票代码：
						</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="ptdo_fpdm" value="${ptdo.fpdm}" name="ptdo.fpdm" size="17"
							maxlength="20" /></td>
						<td style="text-align:right;"><font style="color:red;">*</font>发票号码：
						</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="ptdo_fphm" value="${ptdo.fphm}" name="ptdo.fphm" size="17"
							maxlength="20" /></td>
						<td style="text-align:right;"><font style="color:red;">*</font>购置税证明编号：
						</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="ptdo_gzszmbh" value="${ptdo.gzszmbh}" name="ptdo.gzszmbh"
							size="17" maxlength="20" /></td>
					</tr>
					<tr>
						<td style="text-align:right;"><font style="color:red;">*</font>住所地址：</td>
						<td class="trs" colspan="5">&nbsp;<s:select
								cssClass="selectcss" list="#request.yzbmList" theme="simple"
								id="yzbm7" listKey="dmz" listValue="dmz+':'+dmsm1" name="yzbm7"></s:select>
							<input type="text" class="disabled" id="ptdo_zsxxdz"
							value="${ptdo.zsxxdz}" name="ptdo.zsxxdz" size="50"
							maxlength="100" /></td>
					</tr>
					<tr>

						<td style="text-align:right;">邮寄地址：</td>
						<td class="trs" colspan="3">&nbsp;<s:select
								cssClass="selectcss" list="#request.yzbmList" theme="simple"
								id="yzbm6" listKey="dmz" listValue="dmz+':'+dmsm1" name="yzbm6"></s:select>
							<input type="text" class="disabled" id="ptdo_zzxxdz"
							value="${ptdo.zzxxdz}" name="ptdo.zzxxdz" size="50"
							maxlength="100" /></td>
						<td style="text-align:right;">邮政编码：</td>
						<td><input type="text" class="disabled" id="ptdo_yzbm1"
							value="${ptdo.yzbm1}" name="ptdo.yzbm1" size="10" maxlength="10" />
						</td>
					</tr>
					<tr>
						<td style="text-align:right;"><font style="color:red;">*</font>使用性质：</td>
						<td>&nbsp;<s:select cssClass="selectcss"
								list="#request.syxzList" theme="simple" id="ptdo_syxz"
								headerKey="" headerValue="---请选择---" listKey="dmz"
								listValue="dmz+':'+dmsm1" name="ptdo.syxz"
								value="#request.ptdo.syxz"></s:select></td>

						<td style="text-align:right;"><font style="color:red;">*</font>所有权：</td>
						<td>&nbsp;<s:select cssClass="selectcss"
								list="#request.syqList" theme="simple" id="ptdo_syq"
								headerKey="" headerValue="---请选择---" listKey="dmz"
								listValue="dmz+':'+dmsm1" name="ptdo.syq"
								value="#request.ptdo.syq"></s:select></td>

						<td style="text-align:right;"><font style="color:red;">*</font>获得方式：</td>
						<td>&nbsp;<s:select cssClass="selectcss"
								list="#request.hdfsList" theme="simple" id="ptdo_hdfs"
								headerKey="" headerValue="---请选择---" listKey="dmz"
								listValue="dmz+':'+dmsm1" name="ptdo.hdfs"
								value="#request.ptdo.hdfs"></s:select></td>
					</tr>
					<tr>
						<td style="text-align:right;"><font style="color:red;">*</font>行政区划：</td>
						<td class="trs">&nbsp;<s:select cssClass="selectcss"
								list="#request.yzbmList" theme="simple" id="ptdo_zsxzqh"
								headerKey="" headerValue="---请选择---" listKey="dmz"
								listValue="dmz+':'+dmsm1" name="ptdo.zsxzqh"
								value="#request.ptdo.zsxzqh"></s:select></td>

						<td style="text-align:right;"><font style="color:red;">*</font>号牌种类：</td>
						<td class="trs">&nbsp;<s:select cssClass="selectcss"
								list="#request.hpzlList" theme="simple" id="ptdo_hpzl"
								headerKey="" headerValue="---请选择---" listKey="dmz"
								listValue="dmz+':'+dmsm1" name="ptdo.hpzl"
								value="#request.ptdo.hpzl"></s:select></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td style="text-align:right;">保险生效日期：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="ptdo_sxrq" value="${fn:substring(ptdo.sxrq,0,10)}"
							name="ptdo.sxrq" size="10" maxlength="20"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:CurentTime()})" />
						</td>

						<td style="text-align:right;">保险终止日期：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="ptdo_zzrq" value="${fn:substring(ptdo.zzrq,0,10)}"
							name="ptdo.zzrq" size="10" maxlength="20"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:CurentTime()})" />
						</td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td style="text-align: right;" style="width: 876px height: 21px;">&nbsp;
							<font style="color:red;">*</font>检验日期：</td>
						<td align="left" style="width: 196px height: 21px;">&nbsp;<input
							name="ptdo.yxqz" id="ptdo_yxqz"
							value="${fn:substring(ptdo.yxqz,0,10)}" size="10" maxlength="20"
							type="text"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:CurentTime()})" />
						</td>
						<td style="text-align: right;" style="width: 680px height: 21px;">&nbsp;
							承检单位：</td>
						<td style="width: 164px height: 21px;" colspan="3" align="left">&nbsp;<s:select
								cssClass="selectcss" list="#request.cjdwList" theme="simple"
								id="ptdo_cjdw" headerKey="" headerValue="---请选择---"
								listKey="dmz" listValue="dmz+':'+dmsm1" name="ptdo.cjdw"
								value="#request.ptdo.cjdw"></s:select></td>
					</tr>
					<tr>
						<td style="text-align: right;" style="width: 876px height: 21px;">&nbsp;
							常停车场所：</td>
						<td colspan="3" align="left" style="width: 196px height: 21px;">&nbsp;<input
							name="ptdo.tempBz2" id="ptdo_tempBz2" value="${ptdo.tempBz2}"
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
								value="#request.ptdo.sfzmmc"></s:select></td>
						<td style="text-align:right;"><font style="color:red;">*</font>身份证明号码：
						</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="sfzmhmCf" value="${ptdo.sfzmhm}" name="sfzmhmCf" size="17"
							maxlength="30" /></td>
						<td style="text-align:right;"><font style="color:red;">*</font>居住/暂住证号：
						</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="zzzCf" value="${ptdo.zzz}" name="zzzCf" size="20"
							maxlength="100" /></td>
					</tr>
					<tr>
						<td style="text-align:right;"><font style="color:red;">*</font>姓名/名称：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="syrCf" value="${ptdo.syr}" name="syrCf" size="17"
							maxlength="50" />
						</td>

						<td style="text-align:right;"><font style="color:red;">*</font>移动电话：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="lxdhCf" value="${ptdo.lxdh}" name="lxdhCf" size="17"
							maxlength="50" />
						</td>

						<td style="text-align:right;">电子邮箱：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="dzyxCf" value="${ptdo.dzyx}" name="dzyxCf" size="20"
							maxlength="30" />
						</td>
					</tr>
					<tr>
						<td style="text-align:right;"><font style="color:red;">*</font>发票代码：
						</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="fpdmCf" value="${ptdo.fpdm}" name="fpdmCf" size="17"
							maxlength="30" /></td>
						<td style="text-align:right;"><font style="color:red;">*</font>发票号码：
						</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="fphmCf" value="${ptdo.fphm}" name="fphmCf" size="17"
							maxlength="10" /></td>
						<td style="text-align:right;"><font style="color:red;">*</font>购置税证明编号：
						</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="gzszmbhCf" value="${ptdo.gzszmbh}" name="gzszmbhCf" size="17"
							maxlength="100" /></td>
					</tr>
					<tr>
						<td style="text-align:right;"><font style="color:red;">*</font>住所地址：</td>
						<td class="trs" colspan="5">&nbsp;<s:select
								cssClass="selectcss" list="#request.yzbmList" theme="simple"
								id="yzbm7Cf" listKey="dmz" listValue="dmz+':'+dmsm1"
								name="yzbm7Cf"></s:select> <input type="text" class="disabled"
							id="zsxxdzCf" value="${ptdo.zsxxdz}" name="zsxxdzCf" size="50"
							maxlength="100" /></td>
					</tr>
					<tr>
						<td style="text-align:right;">邮寄地址：</td>
						<td class="trs" colspan="3">&nbsp;<s:select
								cssClass="selectcss" list="#request.yzbmList" theme="simple"
								id="yzbm6Cf" listKey="dmz" listValue="dmz+':'+dmsm1"
								name="yzbm6Cf"></s:select> <input type="text" class="disabled"
							id="zzxxdzCf" value="${ptdo.zzxxdz}" name="zzxxdzCf" size="50"
							maxlength="100" /></td>
						<td style="text-align:right;">邮政编码：</td>
						<td><input type="text" class="disabled" id="yzbm1Cf"
							value="${ptdo.yzbm1}" name="yzbm1Cf" size="10" maxlength="10" />
						</td>
					</tr>
					<tr>
						<td style="text-align:right;"><font style="color:red;">*</font>使用性质：</td>
						<td>&nbsp;<s:select cssClass="selectcss"
								list="#request.syxzList" theme="simple" id="syxzCf" headerKey=""
								headerValue="---请选择---" listKey="dmz" listValue="dmz+':'+dmsm1"
								name="syxzCf" value="#request.ptdo.syxz"></s:select></td>

						<td style="text-align:right;"><font style="color:red;">*</font>所有权：</td>
						<td>&nbsp;<s:select cssClass="selectcss"
								list="#request.syqList" theme="simple" id="syqCf" headerKey=""
								headerValue="---请选择---" listKey="dmz" listValue="dmz+':'+dmsm1"
								name="syqCf" value="#request.ptdo.syq"></s:select></td>

						<td style="text-align:right;"><font style="color:red;">*</font>获得方式：</td>
						<td>&nbsp;<s:select cssClass="selectcss"
								list="#request.hdfsList" theme="simple" id="hdfsCf" headerKey=""
								headerValue="---请选择---" listKey="dmz" listValue="dmz+':'+dmsm1"
								name="hdfsCf" value="#request.ptdo.hdfs"></s:select></td>
					</tr>
					<tr>
						<td style="text-align:right;"><font style="color:red;">*</font>行政区划：</td>
						<td class="trs">&nbsp;<s:select cssClass="selectcss"
								list="#request.yzbmList" theme="simple" id="xzqhCf" headerKey=""
								headerValue="---请选择---" listKey="dmz" listValue="dmz+':'+dmsm1"
								name="xzqhCf" value="#request.ptdo.zsxzqh"></s:select></td>

						<td style="text-align:right;"><font style="color:red;">*</font>号牌种类：</td>
						<td class="trs">&nbsp;<s:select cssClass="selectcss"
								list="#request.hpzlList" theme="simple" id="hpzlCf" headerKey=""
								headerValue="---请选择---" listKey="dmz" listValue="dmz+':'+dmsm1"
								name="hpzlCf" value="#request.ptdo.hpzl"></s:select></td>

						<td></td>
						<td></td>
					</tr>
					<tr>
						<td style="text-align:right;">保险生效日期：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="sxrqCf" value="${fn:substring(ptdo.sxrq,0,10)}" name="sxrqCf"
							size="10" maxlength="20"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:CurentTime()})" />
						</td>

						<td style="text-align:right;">保险终止日期：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="zzrqCf" value="${fn:substring(ptdo.zzrq,0,10)}" name="zzrqCf"
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
							name="yxqzCf" id="yxqzCf" value="${fn:substring(ptdo.yxqz,0,10)}"
							size="10" type="text"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:CurentTime()})" />
						</td>
						<td style="text-align: right;" style="width: 680px height: 21px;">&nbsp;
							承检单位：</td>
						<td style="width: 164px height: 21px;" colspan="3" align="left">&nbsp;<s:select
								cssClass="selectcss" list="#request.cjdwList" theme="simple"
								id="cjdwCf" headerKey="" headerValue="---请选择---" listKey="dmz"
								listValue="dmz+':'+dmsm1" name="cjdwCf"
								value="#request.ptdo.cjdw"></s:select></td>
					</tr>
					<tr>
						<td style="text-align: right;" style="width: 876px height: 21px;">&nbsp;
							常停车场所：</td>
						<td colspan="3" align="left" style="width: 196px height: 21px;">&nbsp;<input
							name="tempBz2Cf" id="tempBz2Cf" value="${ptdo.tempBz2}" size="60"
							maxlength="100" type="text" /></td>

						<td></td>
						<td></td>
					</tr>
				</table>

				<!-- 技术参数 -->
				<table border="0" cellpadding="0" cellspacing="0" id="tabCc3"
					class="table">
					<tr>
						<td style="text-align:right;"><font style="color:red;">*</font>车辆识别代号：</td>
						<td class="trs" colspan="3">&nbsp;<input type="text"
							class="disabled" id="ptdo_clsbdh" value="${ptdo.clsbdh}"
							name="ptdo.clsbdh" size="30" maxlength="100" />&nbsp;&nbsp;&nbsp;&nbsp;
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
							class="disabled" id="ptdo_clxh" value="${ptdo.clxh}"
							name="ptdo.clxh" size="30" maxlength="32" /></td>
						<td style="text-align:right;">中文品牌：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="ptdo_clpp1" value="${ptdo.clpp1}" name="ptdo.clpp1" size="20"
							maxlength="16" /></td>
					</tr>
					<tr>
						<td style="text-align:right;">英文品牌：</td>
						<td class="trs" colspan="3">&nbsp;<input type="text"
							class="disabled" id="ptdo_clpp2" value="${ptdo.clpp2}"
							name="ptdo.clpp2" size="30" maxlength="16" />
						</td>

						<td style="text-align:right;"><font style="color:red;">*</font>车辆类型：</td>
						<td class="trs">&nbsp;<s:select cssClass="selectcss"
								list="#request.cllxList" theme="simple" id="ptdo_cllx"
								headerKey="" headerValue="---请选择---" listKey="dmz"
								listValue="dmz+':'+dmsm1" name="ptdo.cllx"
								value="#request.ptdo.cllx"></s:select></td>
					</tr>
					<tr>
						<td style="text-align:right;"><font style="color:red;">*</font>制造国：</td>
						<td colspan="3">&nbsp;<s:select cssClass="selectcss"
								list="#request.zzgList" theme="simple" id="ptdo_zzg"
								headerKey="" headerValue="---请选择---" listKey="dmz"
								listValue="dmz+':'+dmsm1" name="ptdo.zzg"
								value="#request.ptdo.zzg"></s:select></td>
						<td style="text-align:right;"><font style="color:red;">*</font>发动机号：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="ptdo_fdjh" value="${ptdo.fdjh}" name="ptdo.fdjh" size="20"
							maxlength="100" /></td>
					</tr>

					<tr>
						<td style="text-align:right;">制造厂名称：</td>
						<td class="trs" colspan="3">&nbsp;<input type="text"
							class="disabled" id="ptdo_zzcmc" value="${ptdo.zzcmc}"
							name="ptdo.zzcmc" size="20" maxlength="64" /> &nbsp;发合格证日期：<input
							name="ptdo.fhgzrq" id="ptdo_fhgzrq"
							value="${fn:substring(ptdo.fhgzrq,0,10)}" size="10" type="text"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:CurentTime()})" />
						</td>

						<td style="text-align:right;">车身颜色：</td>
						<td>&nbsp;<s:select cssClass="selectcss"
								list="#request.csysList" theme="simple" id="ptdo_csys1"
								headerKey="" headerValue="---请选择---" listKey="dmz"
								listValue="dmz+':'+dmsm1" name="ptdo.csys"
								></s:select>
								<s:select cssClass="selectcss"
								list="#request.csysList" theme="simple" id="ptdo_csys2"
								headerKey="" headerValue="---请选择---" listKey="dmz"
								listValue="dmz+':'+dmsm1" name="ptdo.csys"
								></s:select>
								<s:select cssClass="selectcss"
								list="#request.csysList" theme="simple" id="ptdo_csys3"
								headerKey="" headerValue="---请选择---" listKey="dmz"
								listValue="dmz+':'+dmsm1" name="ptdo.csys"
								></s:select>
						</td>
					</tr>
					<tr>
						<td style="text-align:right;">出厂日期：</td>
						<td>&nbsp;<input name="ptdo.ccrq" id="ptdo_ccrq"
							value="${fn:substring(ptdo.ccrq,0,10)}" size="10" type="text"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:CurentTime()})" />
						</td>

						<td style="text-align:right;">国产/进口：</td>
						<td>&nbsp;<s:select cssClass="selectcss"
								onchange="selChange(this);" list="#request.gcjkList"
								theme="simple" id="ptdo_gcjk" headerKey=""
								headerValue="---请选择---" listKey="dmz" listValue="dmz+':'+dmsm1"
								name="ptdo.gcjk" value="#request.ptdo.gcjk"></s:select></td>
						<td id="ptdo_gcjkTdHgzbh" style="text-align:right;"><font
							style="color:red;">*</font>合格证编号：</td>
						<td id="ptdo_gcjkTdHgzbhValue" class="trs">&nbsp;<input
							type="text" class="disabled" id="ptdo_hgzbh"
							value="${ptdo.hgzbh}" name="ptdo.hgzbh" size="30" maxlength="30" />
						</td>

						<td id="ptdo_gcjkTdJgpzh" style="text-align:right;display: none;"><font
							style="color:red;">*</font>进口凭证号：</td>
						<td id="ptdo_gcjkTdJgpzhValue" class="trs" style="display: none;">
							&nbsp;<input type="text" class="disabled" id="ptdo_jkpzhm"
							value="${ptdo.jkpzhm}" name="ptdo.jkpzhm" size="30"
							maxlength="30" /></td>
					</tr>
					<tr>
						<td style="text-align:right;"><font style="color:red;">*</font>发动机型号：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="ptdo_fdjxh" value="${ptdo.fdjxh}" name="ptdo.fdjxh" size="20"
							maxlength="100" /></td>

						<td style="text-align:right;">排量/功率：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="ptdo_pl" value="${ptdo.pl}" name="ptdo.pl" size="1"
							maxlength="6" />ml <input type="text" class="disabled"
							id="ptdo_gl" value="${ptdo.gl}" name="ptdo.gl" size="1"
							maxlength="7" />kw</td>

						<td style="text-align:right;">燃料种类：</td>
						<td class="trs">&nbsp;<s:select cssClass="selectcss"
								list="#request.rlzlList" theme="simple" id="ptdo_rlzl"
								headerKey="" headerValue="---请选择---" listKey="dmz"
								listValue="dmz+':'+dmsm1" name="ptdo.rlzl"
								value="#request.ptdo.rlzl"></s:select></td>
					</tr>
					<tr>
						<td style="text-align:right;">外廓尺寸</td>
						<td class="trs" colspan="3">&nbsp;<input name="ptdo.cwkc"
							type="text" id="ptdo_cwkc" value="${ptdo.cwkc}" size="3"
							maxlength="5" /> mm(长) <input name="ptdo.cwkk" type="text"
							id="ptdo_cwkk" value="${ptdo.cwkk}" size="3" maxlength="4" />
							mm(宽) <input name="ptdo.cwkg" type="text" id="ptdo_cwkg"
							value="${ptdo.cwkg}" size="3" maxlength="4" /> mm(高)</td>

						<td style="text-align:right;">转向形式：</td>
						<td class="trs">&nbsp;<s:select cssClass="selectcss"
								list="#request.zxxsList" theme="simple" id="ptdo_zxxs"
								headerKey="" headerValue="---请选择---" listKey="dmz"
								listValue="dmz+':'+dmsm1" name="ptdo.zxxs"
								value="#request.ptdo.zxxs"></s:select></td>
					</tr>
					<tr>
						<td style="text-align:right;">货箱内部尺寸</td>
						<td class="trs" colspan="3">&nbsp;<input name="ptdo.hxnbcd"
							type="text" id="ptdo_hxnbcd" value="${ptdo.hxnbcd}" size="3"
							maxlength="5" /> mm(长) <input name="ptdo.hxnbkd" type="text"
							id="ptdo_hxnbkd" value="${ptdo.hxnbkd}" size="3" maxlength="4" />
							mm(宽) <input name="ptdo.hxnbgd" type="text" id="ptdo_hxnbgd"
							value="${ptdo.hxnbgd}" size="3" maxlength="4" /> mm(高)</td>

						<td style="text-align:right;">环保达标情况：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="ptdo_hbdbqk" value="${ptdo.hbdbqk}" name="ptdo.hbdbqk"
							size="20" maxlength="60" /></td>
					</tr>
					<tr>
						<td style="text-align:right;">钢板弹簧片数：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="ptdo_gbthps" value="${ptdo.gbthps}" name="ptdo.gbthps"
							size="10" maxlength="3" />片</td>

						<td style="text-align:right;">轴数：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="ptdo_zs" value="${ptdo.zs}" name="ptdo.zs" size="10"
							maxlength="1" />个</td>

						<td style="text-align:right;">轴距：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="ptdo_zj" value="${ptdo.zj}" name="ptdo.zj" size="10"
							maxlength="5" />mm</td>
					</tr>
					<tr>
						<td style="text-align:right;">轮胎数：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="ptdo_lts" value="${ptdo.lts}" name="ptdo.lts" size="5"
							maxlength="2" />个</td>

						<td style="text-align:right;">轮胎规格：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="ptdo_ltgg" value="${ptdo.ltgg}" name="ptdo.ltgg" size="10"
							maxlength="50" /></td>

						<td style="text-align:right;">轮距：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="ptdo_qlj" value="${ptdo.qlj}" name="ptdo.qlj" size="5"
							maxlength="4" />mm(前) &nbsp;&nbsp;<input type="text"
							class="disabled" id="ptdo_hlj" value="${ptdo.hlj}"
							name="ptdo.hlj" size="5" maxlength="4" />mm(后)</td>
					</tr>
					<tr>
						<td style="text-align:right;">总质量：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="ptdo_zzl" value="${ptdo.zzl}" name="ptdo.zzl" size="5"
							maxlength="8" />kg</td>

						<td style="text-align:right;">整备质量：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="ptdo_zbzl" value="${ptdo.zbzl}" name="ptdo.zbzl" size="5"
							maxlength="8" />kg</td>

						<td style="text-align:right;">核定载客：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="ptdo_hdzk" value="${ptdo.hdzk}" name="ptdo.hdzk" size="5"
							maxlength="3" />人</td>
					</tr>
					<tr>
						<td style="text-align:right;">准牵引总质量：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="ptdo_zqyzl" value="${ptdo.zqyzl}" name="ptdo.zqyzl" size="5"
							maxlength="8" />个</td>

						<td style="text-align:right;">核定载质量：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="ptdo_hdzzl" value="${ptdo.hdzzl}" name="ptdo.hdzzl" size="5"
							maxlength="8" />个</td>

						<td style="text-align:right;">驾驶室载客：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="ptdo_qpzk" value="${ptdo.qpzk}" name="ptdo.qpzk" size="5"
							maxlength="1" />人(前) &nbsp;&nbsp;<input type="text"
							class="disabled" id="ptdo_hpzk" value="${ptdo.hpzk}"
							name="ptdo.hpzk" size="5" maxlength="1" />人(后)</td>
					</tr>
				</table>

				<!-- 技术参数重复 -->
				<table border="0" cellpadding="0" cellspacing="0" id="tabCc4"
					class="table">
					<tr>
						<td style="text-align:right;"><font style="color:red;">*</font>车辆识别代号：</td>
						<td class="trs" colspan="3">&nbsp;<input type="text"
							class="disabled" id="clsbdhCf" value="${ptdo.clsbdh}"
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
							class="disabled" id="clxhCf" value="${ptdo.clxh}" name="clxhCf"
							size="30" maxlength="32" /></td>
						<td style="text-align:right;">中文品牌：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="clpp1Cf" value="${ptdo.clpp1}" name="clpp1Cf" size="20"
							maxlength="16" /></td>
					</tr>
					<tr>
						<td style="text-align:right;">英文品牌：</td>
						<td class="trs" colspan="3">&nbsp;<input type="text"
							class="disabled" id="clpp2Cf" value="${ptdo.clpp2}"
							name="clpp2Cf" size="30" maxlength="16" />
						</td>

						<td style="text-align:right;"><font style="color:red;">*</font>车辆类型：</td>
						<td class="trs">&nbsp;<s:select cssClass="selectcss"
								list="#request.cllxList" theme="simple" id="cllxCf" headerKey=""
								headerValue="---请选择---" listKey="dmz" listValue="dmz+':'+dmsm1"
								name="cllxCf" value="#request.ptdo.cllx"></s:select></td>
					</tr>
					<tr>
						<td style="text-align:right;"><font style="color:red;">*</font>制造国：</td>
						<td colspan="3">&nbsp;<s:select cssClass="selectcss"
								list="#request.zzgList" theme="simple" id="zzgCf" headerKey=""
								headerValue="---请选择---" listKey="dmz" listValue="dmz+':'+dmsm1"
								name="zzgCf" value="#request.ptdo.zzg"></s:select></td>
						<td style="text-align:right;"><font style="color:red;">*</font>发动机号：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="fdjhCf" value="${ptdo.fdjh}" name="fdjhCf" size="20"
							maxlength="100" /></td>
					</tr>
					<tr>
						<td style="text-align:right;">制造厂名称：</td>
						<td class="trs" colspan="3">&nbsp;<input type="text"
							class="disabled" id="zzcmcCf" value="${ptdo.zzcmc}"
							name="zzcmcCf" size="20" maxlength="64" /> &nbsp;发合格证日期：<input
							name="fhgzrqCf" id="fhgzrqCf"
							value="${fn:substring(ptdo.fhgzrq,0,10)}" size="10" type="text"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:CurentTime()})" />
						</td>

						<td style="text-align:right;">车身颜色：</td>
						<td>&nbsp;<s:select cssClass="selectcss"
								list="#request.csysList" theme="simple" id="csysCf" headerKey=""
								headerValue="---请选择---" listKey="dmz" listValue="dmz+':'+dmsm1"
								name="csysCf" value="#request.ptdo.csys"></s:select></td>
					</tr>
					<tr>
						<td style="text-align:right;">出厂日期：</td>
						<td>&nbsp;<input name="ccrqCf" id="ccrqCf"
							value="${fn:substring(ptdo.ccrq,0,10)}" size="10" type="text"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:CurentTime()})" />
						</td>

						<td style="text-align:right;">国产/进口：</td>
						<td>&nbsp;<s:select cssClass="selectcss"
								onchange="selChange(this);" list="#request.gcjkList"
								theme="simple" id="gcjkCf" headerKey="" headerValue="---请选择---"
								listKey="dmz" listValue="dmz+':'+dmsm1" name="gcjkCf"
								value="#request.ptdo.gcjk"></s:select></td>

						<td id="gcjkCfTdHgzbh" style="text-align:right;"><font
							style="color:red;">*</font>合格证编号：</td>
						<td id="gcjkCfTdHgzbhValue" class="trs">&nbsp;<input
							type="text" class="disabled" id="hgzbhCf" value="${ptdo.hgzbh}"
							name="hgzbhCf" size="30" maxlength="30" />
						</td>

						<td id="gcjkCfTdJgpzh" style="text-align:right;display: none;"><font
							style="color:red;">*</font>进口凭证号：</td>
						<td id="gcjkCfTdJgpzhValue" class="trs" style="display: none;">
							&nbsp;<input type="text" class="disabled" id="jkpzhmCf"
							value="${ptdo.jkpzhm}" name="jkpzhmCf" size="30" maxlength="30" />
						</td>

					</tr>
					<tr>
						<td style="text-align:right;"><font style="color:red;">*</font>发动机型号：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="fdjxhCf" value="${ptdo.fdjxh}" name="fdjxhCf" size="20"
							maxlength="100" /></td>

						<td style="text-align:right;">排量/功率：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="plCf" value="${ptdo.pl}" name="plCf" size="1" maxlength="6" />ml
							<input type="text" class="disabled" id="glCf" value="${ptdo.gl}"
							name="glCf" size="1" maxlength="7" />kw</td>

						<td style="text-align:right;">燃料种类：</td>
						<td class="trs">&nbsp;<s:select cssClass="selectcss"
								list="#request.rlzlList" theme="simple" id="rlzlCf" headerKey=""
								headerValue="---请选择---" listKey="dmz" listValue="dmz+':'+dmsm1"
								name="rlzlCf" value="#request.ptdo.rlzl"></s:select></td>
					</tr>
					<tr>
						<td style="text-align:right;">外廓尺寸</td>
						<td class="trs" colspan="3">&nbsp;<input name="cwkcCf"
							type="text" id="cwkcCf" value="${ptdo.cwkc}" size="3"
							maxlength="5" /> mm(长) <input name="cwkkCf" type="text"
							id="cwkkCf" value="${ptdo.cwkk}" size="3" maxlength="4" /> mm(宽)
							<input name="cwkgCf" type="text" id="cwkgCf" value="${ptdo.cwkg}"
							size="3" maxlength="4" /> mm(高)</td>

						<td style="text-align:right;">转向形式：</td>
						<td class="trs">&nbsp;<s:select cssClass="selectcss"
								list="#request.zxxsList" theme="simple" id="zxxsCf" headerKey=""
								headerValue="---请选择---" listKey="dmz" listValue="dmz+':'+dmsm1"
								name="zxxsCf" value="#request.ptdo.zxxs"></s:select></td>
					</tr>
					<tr>
						<td style="text-align:right;">货箱内部尺寸</td>
						<td class="trs" colspan="3">&nbsp;<input name="hxnbcdCf"
							type="text" id="hxnbcdCf" value="${ptdo.hxnbcd}" size="3"
							maxlength="5" /> mm(长) <input name="hxnbkdCf" type="text"
							id="hxnbkdCf" value="${ptdo.hxnbkd}" size="3" maxlength="4" />
							mm(宽) <input name="hxnbgdCf" type="text" id="hxnbgdCf"
							value="${ptdo.hxnbgd}" size="3" maxlength="4" /> mm(高)</td>

						<td style="text-align:right;">环保达标情况：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="hbdbqkCf" value="${ptdo.hbdbqk}" name="hbdbqkCf" size="20"
							maxlength="60" /></td>
					</tr>
					<tr>
						<td style="text-align:right;">钢板弹簧片数：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="gbthpsCf" value="${ptdo.gbthps}" name="gbthpsCf" size="10"
							maxlength="3" />片</td>

						<td style="text-align:right;">轴数：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="zsCf" value="${ptdo.zs}" name="zsCf" size="10" maxlength="1" />个
						</td>

						<td style="text-align:right;">轴距：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="zjCf" value="${ptdo.zj}" name="zjCf" size="10" maxlength="5" />mm
						</td>
					</tr>
					<tr>
						<td style="text-align:right;">轮胎数：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="ltsCf" value="${ptdo.lts}" name="ltsCf" size="5"
							maxlength="2" />个</td>

						<td style="text-align:right;">轮胎规格：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="ltggCf" value="${ptdo.ltgg}" name="ltggCf" size="10"
							maxlength="50" /></td>

						<td style="text-align:right;">轮距：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="qljCf" value="${ptdo.qlj}" name="qljCf" size="5"
							maxlength="4" />mm(前) &nbsp;&nbsp;<input type="text"
							class="disabled" id="hljCf" value="${ptdo.hlj}" name="hljCf"
							size="5" maxlength="4" />mm(后)</td>
					</tr>
					<tr>
						<td style="text-align:right;">总质量：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="zzlCf" value="${ptdo.zzl}" name="zzlCf" size="5"
							maxlength="8" />kg</td>

						<td style="text-align:right;">整备质量：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="zbzlCf" value="${ptdo.zbzl}" name="zbzlCf" size="5"
							maxlength="8" />kg</td>

						<td style="text-align:right;">核定载客：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="hdzkCf" value="${ptdo.hdzk}" name="hdzkCf" size="5"
							maxlength="3" />人</td>
					</tr>
					<tr>
						<td style="text-align:right;">准牵引总质量：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="zqyzlCf" value="${ptdo.zqyzl}" name="zqyzlCf" size="5"
							maxlength="8" />个</td>

						<td style="text-align:right;">核定载质量：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="hdzzlCf" value="${ptdo.hdzzl}" name="hdzzlCf" size="5"
							maxlength="8" />个</td>

						<td style="text-align:right;">驾驶室载客：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="qpzkCf" value="${ptdo.qpzk}" name="qpzkCf" size="5"
							maxlength="1" />人(前) &nbsp;&nbsp;<input type="text"
							class="disabled" id="hpzkCf" value="${ptdo.hpzk}" name="hpzkCf"
							size="5" maxlength="1" />人(后)</td>
					</tr>
				</table>

				<!-- 发票信息
				<table border="0" cellpadding="0" cellspacing="0" id="tabCc5" class="table">
					<tr>
						<td style="text-align:right;"><font style="color:red;">*</font>发票交易号：
						</td>
						<td class="trs">&nbsp;<input type="text" class="disabled" id="ptdo_netFph" value="${ptdo.netFph}" name="ptdo.netFph" size="17" maxlength="30"/>
						</td>
						<td style="text-align:right;">
							<font style="color:red;">*</font>纳税证明：
						</td>
						<td class="trs">&nbsp;<input type="text" class="disabled" id="ptdo_nszm" value="${ptdo.nszm}" name="ptdo.nszm" size="17" maxlength="10"/>
						</td>
						<td style="text-align:right;">
							<font style="color:red;">*</font>纳税证明编号：
						</td>
						<td class="trs">&nbsp;<input type="text" class="disabled" id="ptdo_nszmbh" value="${ptdo.nszmbh}" name="ptdo.nszmbh" size="20" maxlength="100"/>
						</td>
					</tr>
					<tr>
						<td style="text-align:right;">销售单位：
						</td>
						<td class="trs" colspan="5">&nbsp;<input type="text" class="disabled" id="ptdo_xsdw" value="${ptdo.xsdw}" name="ptdo.xsdw" size="30" maxlength="64"/>
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
						<td class="trs">&nbsp;<input type="text" class="disabled" id="netFphCf" value="${ptdo.netFph}" name="netFphCf" size="17" maxlength="30"/>
						</td>
						<td style="text-align:right;">
							<font style="color:red;">*</font>纳税证明：
						</td>
						<td class="trs">&nbsp;<input type="text" class="disabled" id="nszmCf" value="${ptdo.nszm}" name="nszmCf" size="17" maxlength="10"/>
						</td>
						<td style="text-align:right;">
							<font style="color:red;">*</font>纳税证明编号：
						</td>
						<td class="trs">&nbsp;<input type="text" class="disabled" id="nszmbhCf" value="${ptdo.nszmbh}" name="nszmbhCf" size="20" maxlength="100"/>
						</td>
					</tr>
					<tr>
						<td style="text-align:right;">销售单位：
						</td>
						<td class="trs" colspan="5">&nbsp;<input type="text" class="disabled" id="xsdwCf" value="${ptdo.xsdw}" name="xsdwCf" size="30" maxlength="64"/>
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
							id="ptdo_netXm" value="${ptdo.netXm}" name="ptdo.netXm" size="17"
							maxlength="30" /></td>
						<td style="text-align:right;"><font style="color:red;">*</font>代理人联系电话：
						</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="ptdo_netLxdh" value="${ptdo.netLxdh}" name="ptdo.netLxdh"
							size="17" maxlength="10" /></td>
						<td style="text-align:right;"><font style="color:red;">*</font>代理人邮政编码：
						</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="ptdo_netYzbm" value="${ptdo.netYzbm}" name="ptdo.netYzbm"
							size="10" maxlength="10" /></td>
					</tr>
					<tr>
						<td style="text-align:right;"><font style="color:red;">*</font>代理人邮寄地址：
						</td>
						<td class="trs" colspan="5">&nbsp;<input type="text"
							class="disabled" id="ptdo_netYjdz" value="${ptdo.netYjdz}"
							name="ptdo.netYjdz" size="60" maxlength="100" /></td>
					</tr>
					<tr>
						<td style="text-align:right;">代理人电子邮箱：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="ptdo_netDzxx" value="${ptdo.netDzxx}" name="ptdo.netDzxx"
							size="17" maxlength="30" /></td>
						<td style="text-align:right;"><font style="color:red;">*</font>经办人姓名：
						</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="ptdo_netJbrxm" value="${ptdo.netJbrxm}" name="ptdo.netJbrxm"
							size="17" maxlength="10" /></td>
						<td style="text-align:right;"><font style="color:red;">*</font>经办人联系电话：
						</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="ptdo_netJbrlxdh" value="${ptdo.netJbrlxdh}"
							name="ptdo.netJbrlxdh" size="20" maxlength="100" /></td>
					</tr>
				</table>

				<!-- 代理人信息重复 -->
				<table border="0" cellpadding="0" cellspacing="0" id="tabCc6"
					class="table">
					<tr>
						<td style="text-align:right;"><font style="color:red;">*</font>代理人姓名：
						</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="netXmCf" value="${ptdo.netXm}" name="netXmCf" size="17"
							maxlength="30" /></td>
						<td style="text-align:right;"><font style="color:red;">*</font>代理人联系电话：
						</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="netLxdhCf" value="${ptdo.netLxdh}" name="netLxdhCf" size="17"
							maxlength="10" /></td>
						<td style="text-align:right;"><font style="color:red;">*</font>代理人邮政编码：
						</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="netYzbmCf" value="${ptdo.netYzbm}" name="netYzbmCf" size="10"
							maxlength="10" /></td>
					</tr>
					<tr>
						<td style="text-align:right;"><font style="color:red;">*</font>代理人邮寄地址：
						</td>
						<td class="trs" colspan="5">&nbsp;<input type="text"
							class="disabled" id="netYjdzCf" value="${ptdo.netYjdz}"
							name="netYjdzCf" size="60" maxlength="100" /></td>
					</tr>
					<tr>
						<td style="text-align:right;">代理人电子邮箱：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="netDzxxCf" value="${ptdo.netDzxx}" name="netDzxxCf" size="17"
							maxlength="30" /></td>
						<td style="text-align:right;"><font style="color:red;">*</font>经办人姓名：
						</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="netJbrxmCf" value="${ptdo.netJbrxm}" name="netJbrxmCf"
							size="17" maxlength="10" /></td>
						<td style="text-align:right;"><font style="color:red;">*</font>经办人联系电话：
						</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="netJbrlxdhCf" value="${ptdo.netJbrlxdh}" name="netJbrlxdhCf"
							size="20" maxlength="100" /></td>
					</tr>
				</table>

				<!-- 邮寄信息 -->
				<table border="0" cellpadding="0" cellspacing="0" id="tabCc7"
					class="table">
					<tr>
						<td style="text-align:right;" colspan="2">驾驶证邮递 <input
							type="checkbox" id="ptdo_postZj" value="0"
							onclick="jsz_jdc_select(this)" name="ptdo.postZj" /></td>

						<td style="text-align:right;"></td>
						<td class="trs">&nbsp;</td>
						<td style="text-align:left;" colspan="2">机动车号牌邮递<input
							type="checkbox" id="ptdo_postTp" onclick="jsz_jdc_select(this)"
							value="0" name="ptdo.postTp" /></td>
					</tr>
					<tr>
						<td style="text-align:right;">收件人地址：</td>
						<td class="trs" colspan="5">&nbsp;<input type="text"
							class="disabled" id="ptdo_postYjdz" value="${ptdo.postYjdz}"
							name="ptdo.postYjdz" size="60" maxlength="100" /></td>
					</tr>
					<tr>
						<td style="text-align:right;">收件人姓名：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="ptdo_postSjr" value="${ptdo.postSjr}" name="ptdo.postSjr"
							size="17" maxlength="30" /></td>
						<td style="text-align:right;">联系电话：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="ptdo_postLxdh" value="${ptdo.postLxdh}" name="ptdo.postLxdh"
							size="17" maxlength="10" /></td>
						<td style="text-align:right;">邮政编码：</td>
						<td class="trs">&nbsp;<input type="text" class="disabled"
							id="ptdo_postYzbm" value="${ptdo.postYzbm}" name="ptdo.postYzbm"
							size="10" maxlength="100" /></td>
					</tr>
				</table>
				<table border="0" cellpadding="0" cellspacing="0" id="czbttrid">
					<tr>
						<td colspan="6" height="50" style="text-align: center;"><input
							type="button" onclick="javascript:yzAll();;" value="修 改"
							style="cursor:pointer;width: 79px;height: 28px;border: none;background: url(<%=request.getContextPath()%>/images/an2.gif) no-repeat;color: #FFFFFF;font-weight: bold;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="button" onclick="javascript:zthf();" value="暂停恢复"
							style="cursor:pointer;width: 79px;height: 28px;border: none;background: url(<%=request.getContextPath()%>/images/an2.gif) no-repeat;color: #FFFFFF;font-weight: bold;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<span id="bttcygid"><input type="button" onclick="javascript:tcyg();" value="退查验岗"
							style="cursor:pointer;width: 79px;height: 28px;border: none;background: url(<%=request.getContextPath()%>/images/an2.gif) no-repeat;color: #FFFFFF;font-weight: bold;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
							<input type="button" onclick="javascript:zxyk();" value="转嫌疑库"
							style="cursor:pointer;width: 79px;height: 28px;border: none;background: url(<%=request.getContextPath()%>/images/an2.gif) no-repeat;color: #FFFFFF;font-weight: bold;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="button" onclick="javascript:tb();" value="退 办"
							style="cursor:pointer;width: 79px;height: 28px;border: none;background: url(<%=request.getContextPath()%>/images/an2.gif) no-repeat;color: #FFFFFF;font-weight: bold;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</td>
					</tr>
				</table>
				<table border="0" cellpadding="0" cellspacing="0" align="left">
					<tr>
						<td colspan="6" style="text-align: left;">
							流水状态：<font color="red">${ywlsglVehFlow.lszt }</font>
						</td>
					</tr>
					<tr>
						<td colspan="6" style="text-align: left;">
							流水状态说明：<font color="red">${ywlsglVehFlow.lsztSm }</font>
						</td>
					</tr>
					<tr>
						<td colspan="6" style="text-align: left;">
							备注：<font color="red">${ywlsglVehFlow.bz }</font>
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