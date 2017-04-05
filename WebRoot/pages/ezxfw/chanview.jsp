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
    <title>${typeval }补换证信息制证管理</title>
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
			$("#lshcx").click(function(){
				var tyblsh = $("#tyblsh").val();
				var type = '${type}';
				$.ajax({
					type:'POST',
					url:'<%=request.getContextPath()%>/ezxfw/ezxfw_getChanList.action',
					data:{tyblsh:tyblsh,type:type},
					dataType: 'json',
					cache:false,
					async:false,
					error:function(){
						alert("读取数据异常!");
					},
					success:function(data){
						var message = data+"";
						if(message.indexOf('异常信息') == -1){
							if(null == data || '' == data){
								alert('没有相关流水信息!');
							}else{
								var addstr = '';
						    	if(data.length > 0){
						    		if(data.length > 1){
						    			$("#xztableid").html("");
						    			var vtable = '';
						    			var typeval = '';
						    			if("drv" == type){
						    				typeval = "申报信息(身份证明号码,姓名,移动电话,联系地址)";
						    			}else if("jdc" == type){
						    				typeval = "申报信息(号牌号码,号牌种类,身份证明号码,机动车所有人,移动电话,联系地址)";
						    			}
						    			vtable = "<tr class='tr1'><th style='width: 10%;' align='center'></th><th style='width: 20%;' align='center'>统一版流水号</th><th style='width: 20%;' align='center'>app数据源编号</th><th style='width: 50%;' align='center'>申报信息</th></tr>";
						    			for(var i = 0; i < data.length; i ++){
								    		var v_tylsh = data[i][0];
								    		var v_applsh = data[i][1];
								    		var v_info = data[i][2];
								    		var strtemp = "";
								    		if(i == 0){
								    			strtemp = "<tr id='v"+v_applsh+"'><td><input type='radio' checked='checked' name='applshnames' value='"+i+"'/></td><td>"+v_tylsh+"</td><td>"+v_applsh+"</td><td>"+v_info+"</td></tr>";
								    		}else{
								    			strtemp = "<tr id='v"+v_applsh+"'><td><input type='radio' name='applshnames' value='"+i+"'/></td><td>"+v_tylsh+"</td><td>"+v_applsh+"</td><td>"+v_info+"</td></tr>";
								    		}
											vtable += strtemp;
								    	}
						    			$("#xztableid").append(vtable);
						    			art.dialog({
							 		 	    fixed: true,
							                resize: false,
										    content: $('#divSelPtmoValue').html(),
										    title: '请选择一个对应的app数据源',
											lock: true,
										    opacity: 0.1,
										    padding: 5,
										    okVal: '确定',
											ok: function(){
												var radioval = $(":radio[name=applshnames][checked]").val();
												var v_tylsh = data[radioval][0];
									    		var v_applsh = data[radioval][1];
									    		var v_info = data[radioval][2];
									    		var strtemp = "<tr id='"+v_applsh+"' class='trcla'><td>"+v_tylsh+"</td><td>"+v_applsh+"</td><td>"+v_info+"</td><td><a href='javascript:void(0);' onclick=\"javascript:deletetr('"+v_applsh+"');\">删除该行</a></td></tr>";
												addstr += strtemp;
												$("#datableid").append(addstr);
												var appsval = $("#appsval").val();
												if(null == appsval || "" == appsval){
													$("#appsval").val(v_applsh + "," + v_tylsh);
												}else{
													$("#appsval").val(appsval + "&" + v_applsh + "," + v_tylsh);
												}
												$("#tyblsh").val("");
											},
											cancelVal: '关闭',
											cancel: true
									     });
						    		}else{
						    			for(var i = 0; i < data.length; i ++){
								    		var v_tylsh = data[i][0];
								    		var v_applsh = data[i][1];
								    		var v_info = data[i][2];
								    		var strtemp = "<tr id='"+v_applsh+"' class='trcla'><td>"+v_tylsh+"</td><td>"+v_applsh+"</td><td>"+v_info+"</td><td><a href='javascript:void(0);' onclick=\"javascript:deletetr('"+v_applsh+"');\">删除该行</a></td></tr>";
											addstr += strtemp;
											var appsval = $("#appsval").val();
											if(null == appsval || "" == appsval){
												$("#appsval").val(v_applsh + "," + v_tylsh);
											}else{
												$("#appsval").val(appsval + "&" + v_applsh + "," + v_tylsh);
											}
								    	}
								    	$("#tyblsh").val("");
								    	$("#datableid").append(addstr);
						    		}
						    	}
							}
						}else{
							exception(message);
						}
					}
				});
			});
			
			$("#frombut").click(function (){
				var appsval = $("#appsval").val();
				if(null == appsval || '' == appsval){
					alert("请先根据统一版流水号查询出数据后点击保存按钮!");
					return false;
				}else{
					ezxfw_form.submit();
				}
			});
		});
		
		function deletetr(applsh){
			$("#"+applsh).remove();
			var appsval = $("#appsval").val();
			if(null != appsval && '' != appsval){
				if(appsval.indexOf("&") >= 0){
					var v_appsval = appsval.split('&');
					if(v_appsval != null && v_appsval.length > 0){
						for(var k = 0; k < v_appsval.length; k++){
							var s_appsval = v_appsval[k];
							if(s_appsval.indexOf(applsh) >= 0){
								var indexval = appsval.indexOf(s_appsval);
								var qval = appsval.substr(0,indexval);
								var hval = appsval.substr(indexval+s_appsval.length+1,appsval.length);
								$("#appsval").val(qval + hval);
							}
						}
					}
				}else{
					if(appsval.indexOf(applsh) >= 0){
						$("#appsval").val("");
					}
				}
			}
		}
		
		function lshon(){
			var tyblsh = $("#tyblsh").val();
			var type = '${type}';
			$.ajax({
				type:'POST',
				url:'<%=request.getContextPath()%>/ezxfw/ezxfw_getChanList.action',
				data:{tyblsh:tyblsh,type:type},
				dataType: 'json',
				cache:false,
				async:false,
				error:function(){
					alert("读取数据异常!");
				},
				success:function(data){
					var message = data+"";
					if(message.indexOf('异常信息') == -1){
						if(null == data || '' == data){
							alert('没有相关流水信息!');
						}else{
							var addstr = '';
					    	if(data.length > 0){
					    		if(data.length > 1){
					    			$("#xztableid").html("");
					    			var vtable = '';
					    			var typeval = '';
					    			if("drv" == type){
					    				typeval = "申报信息(身份证明号码,姓名,移动电话,联系地址)";
					    			}else if("jdc" == type){
					    				typeval = "申报信息(号牌号码,号牌种类,身份证明号码,机动车所有人,移动电话,联系地址)";
					    			}
					    			vtable = "<tr class='tr1'><th style='width: 10%;' align='center'></th><th style='width: 20%;' align='center'>统一版流水号</th><th style='width: 20%;' align='center'>app数据源编号</th><th style='width: 50%;' align='center'>申报信息</th></tr>";
					    			for(var i = 0; i < data.length; i ++){
							    		var v_tylsh = data[i][0];
							    		var v_applsh = data[i][1];
							    		var v_info = data[i][2];
							    		var strtemp = "";
							    		if(i == 0){
							    			strtemp = "<tr id='v"+v_applsh+"'><td><input type='radio' checked='checked' name='applshnames' value='"+i+"'/></td><td>"+v_tylsh+"</td><td>"+v_applsh+"</td><td>"+v_info+"</td></tr>";
							    		}else{
							    			strtemp = "<tr id='v"+v_applsh+"'><td><input type='radio' name='applshnames' value='"+i+"'/></td><td>"+v_tylsh+"</td><td>"+v_applsh+"</td><td>"+v_info+"</td></tr>";
							    		}
										vtable += strtemp;
							    	}
					    			$("#xztableid").append(vtable);
					    			art.dialog({
						 		 	    fixed: true,
						                resize: false,
									    content: $('#divSelPtmoValue').html(),
									    title: '请选择一个对应的app数据源',
										lock: true,
									    opacity: 0.1,
									    padding: 5,
									    okVal: '确定',
										ok: function(){
											var radioval = $(":radio[name=applshnames][checked]").val();
											var v_tylsh = data[radioval][0];
								    		var v_applsh = data[radioval][1];
								    		var v_info = data[radioval][2];
								    		var strtemp = "<tr id='"+v_applsh+"' class='trcla'><td>"+v_tylsh+"</td><td>"+v_applsh+"</td><td>"+v_info+"</td><td><a href='javascript:void(0);' onclick=\"javascript:deletetr('"+v_applsh+"');\">删除该行</a></td></tr>";
											addstr += strtemp;
											$("#datableid").append(addstr);
											var appsval = $("#appsval").val();
											if(null == appsval || "" == appsval){
												$("#appsval").val(v_applsh + "," + v_tylsh);
											}else{
												$("#appsval").val(appsval + "&" + v_applsh + "," + v_tylsh);
											}
											$("#tyblsh").val("");
										},
										cancelVal: '关闭',
										cancel: true
								     });
					    		}else{
					    			for(var i = 0; i < data.length; i ++){
							    		var v_tylsh = data[i][0];
							    		var v_applsh = data[i][1];
							    		var v_info = data[i][2];
							    		var strtemp = "<tr id='"+v_applsh+"' class='trcla'><td>"+v_tylsh+"</td><td>"+v_applsh+"</td><td>"+v_info+"</td><td><a href='javascript:void(0);' onclick=\"javascript:deletetr('"+v_applsh+"');\">删除该行</a></td></tr>";
										addstr += strtemp;
										var appsval = $("#appsval").val();
										if(null == appsval || "" == appsval){
											$("#appsval").val(v_applsh + "," + v_tylsh);
										}else{
											$("#appsval").val(appsval + "&" + v_applsh + "," + v_tylsh);
										}
							    	}
							    	$("#tyblsh").val("");
							    	$("#datableid").append(addstr);
					    		}
					    	}
						}
					}else{
						exception(message);
					}
				}
			});
		}
		function updateChanDg(tyblsh,applsh,type,pch){
			$.ajax({
				cache:false,
				async:false,
				url:'<%=request.getContextPath()%>/ezxfw/ezxfw_updateChanDg.action',
				type:'post',
				data:{"tyblsh":tyblsh,"applsh":applsh,"type":type},
				dataType:'json',
				error: function(){
	    			alert("读取数据异常!");
	    			return false;
		    	},
				success: function(result){
					var message = result+"";
					if(message.indexOf('异常信息') == -1){
						if("0" == result){
							alert("审核成功!");
							$("#"+applsh+"zt").html("");
							opener.sxlb(pch);
						}else if("1" == result){
							alert("审核失败!");
							return false;
						}
					}else{
						exception(message);
					}
				}
			});
		}
		function initform(){
			$("#xztableid").html("");
			$(".trcla").remove();
			$("#appsval").val("");
			$("#tyblsh").val("");
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
		
		
		function exportToExport(){
	    	var nodata=$("#nodata");
			if(nodata.length>0){
				alert("无数据导出");
			}else{
			    var uri="<%=request.getContextPath()%>/ezxfw/ezxfw_ezxfwchanExcel.action";
			    window.location.href=uri;
			}
	    }
	</script>
	
  </head>
  
  <body style="background:#c7e5ff;">
    <div class="content1" style="width: 100%;">
		  <div class="roundedBox" id="type1" style="width: 95%;">
				<div class="right" style="width: 98%;">
					<s:if test="#request.type == 'drvv' || #request.type == 'jdcv'">
						<div style="width: 98%;height: 35px;padding-top: 10px;">
							<input class="bnt" type="button" value="导出excel" onclick="javascript:exportToExport();" style="cursor:pointer;" />
						</div>
						<table class="datalist" width="100%" border="0"
							cellpadding="0" cellspacing="0" id="datableid">
							<tr class="tr1">
								<th style="width: 10%;">
									序号
								</th>
								<th style="width: 10%;">
									批次号
								</th>
								<th style="width: 16%;">
									统一版流水号
								</th>
								<th style="width: 16%;">
									app数据源申请编号
								</th>
								<th style="width: 40%;">
									<s:if test="#request.type == 'drvv'">
										申报信息(身份证明号码,姓名,移动电话,联系地址)
									</s:if>
									<s:else>
										申报信息(号牌号码,号牌种类,身份证明号码,机动车所有人,移动电话,联系地址)
									</s:else>
								</th>
								<th style="width: 8%;">
									操作
								</th>
							</tr>
							<s:if test="#request.datalist.size > 0">
								<s:iterator id="dc" value="#request.datalist" status="st">
									<tr class="<s:if test="#st.odd == false">altrow</s:if>">
										<td>
											${st.count + (map.currentpage-1) * map.pagesize}
										</td>
										<td>
											${dc[3] }
										</td>
										<td>
											${dc[0] }
										</td>
										<td>
									 		${dc[1] }
										</td>
										<td>
											${dc[2] }
										</td>
										<td id="${dc[1] }zt">
											<s:if test="#request.dc[4]!=2">
												<a href="javascript:void(0);" onclick="javascript:updateChanDg('${dc[0] }','${dc[1] }','${type}','${dc[3] }')">复核</a>
											</s:if>
										</td>
									</tr>
								</s:iterator>
							</s:if>
							<s:else>
								<tr>
									<td colspan="8">
										<span id="nodata" style="color: red">暂时没有相关数据</span>
									</td>
								</tr>
							</s:else>
						</table>
					</s:if>
					<s:else>
						<table class="table1" width="100%" border="0"
							cellpadding="0" cellspacing="0">
							<tr>
								<th class="th1" height="32" colspan="4">
									${typeval }补换证发证
								</th>
							</tr>
							<tr>
								<td class="tds" style="text-align: right;width: 17%;">
									统一版流水号：&nbsp;
								</td>
								<td colspan="3" style="text-align: left;">
									&nbsp;<input type="text" class="disabled" id="tyblsh" name="tyblsh" size="30"/>&nbsp;&nbsp;
									<input class="bnt" type="button" value="查询" id="lshcx" style="cursor:pointer;" />
								</td>
							</tr>
						</table>
						<table class="datalist" width="100%" border="0"
							cellpadding="0" cellspacing="0" id="datableid">
							<tr class="tr1">
								<th style="width: 20%;">
									统一版流水号
								</th>
								<th style="width: 20%;">
									app数据源申请编号
								</th>
								<th style="width: 50%;">
									<s:if test="#request.type == 'drv'">
										申报信息(身份证明号码,姓名,移动电话,联系地址)
									</s:if>
									<s:else>
										申报信息(号牌号码,号牌种类,身份证明号码,机动车所有人,移动电话,联系地址)
									</s:else>
								</th>
								<th style="width: 10%;">
									操作
								</th>
							</tr>
						</table>
						<form action="<%=request.getContextPath()%>/ezxfw/ezxfw_editChan.action?type=${type}" method="post" id="ezxfw_form" enctype="multipart/form-data" target="abc">
							<iframe id="abc" name="abc" width="0" height="0" style="display: none;"></iframe>
							<input type="hidden" id="appsval" name="appsval" />
							<table width="100%" border="0" cellspacing="0" cellpadding="0"
								style="padding-top: 20px;">
								<tr>
									<td class="tds" colspan="4">
										<div align="center">
											<input class="bnt" type="button" value="保存" id="frombut" style="cursor:pointer;" />
										</div>
									</td>
								</tr>
							</table>
						</form>
					</s:else>
				</div>
				<div class="corner topLeft"></div>
				<div class="corner topRight"></div>
			</div>
		</div>
		
		
		
		<div  id="divSelPtmoValue" style="display:none;overflow-y:scroll;overflow-x:scroll;width:700px;height: 200px;">
	     <div style="overflow:auto;width:700px;height: 200px;">
		  <table  class="datalist" width="100%" border="0" cellpadding="0" cellspacing="0" style="verflow:scroll;" id="xztableid">
	   </table>
	   </div>
	    </div>
  </body>
</html>
