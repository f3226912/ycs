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
		<title>验车结果修改列表</title>
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
			var chuli;
			function update(lsh,ycLx,checkFalg){
				$(".tdstr").html("");
				$(":radio").removeAttr("checked");
				//$("#shjg0").attr("checked","checked");
				$("#yuanyin").val("");
				$("#beizhu").val("");
				if("1" == checkFalg)$("#tdstrid").html("<font color='red'>合格</font>");
		    	else if("2" == checkFalg)$("#tdstrid").html("<font color='red'>不合格</font>");
				var baocun = art.dialog({
					width:'50%',
				    content: document.getElementById("xiugaiid"),
				    title: '验车结果修改',
				    okVal: '保存',
				    ok: function () {
				    	/*var yuanyin = $("#yuanyin").val();
				    	if(null == yuanyin || "" == yuanyin){
				    		alert("请输入结果修改原因!");
				    		return false;
				    	}*/
				    	var yuanyin = "";
				    	var bhgyyname = document.getElementsByName("bhgyynames");
						for (var i = 0; i < bhgyyname.length; i ++){
							if(bhgyyname[i].checked){
								if(yuanyin == ""){
									yuanyin = bhgyyname[i].value;
								}else{
									yuanyin = yuanyin + "|" + bhgyyname[i].value;
								}
							}
						}
				    	
				    	var beizhu = $("#beizhu").val();
				    	//yuanyin = encodeURI(yuanyin);
				    	beizhu = encodeURI(beizhu);
				    	var check = "";
						var shjg = document.getElementsByName("shjg");
						for (var i = 0; i < shjg.length; i ++){
							if(shjg[i].checked){
								check = shjg[i].value;
								break;
							}
						}
						if(check == "2"){
							if(yuanyin == ""){
								alert("请选择不合格原因!");
					    		return false;
							}
						}
				    	this.close();
				    	var imgurl = '<%=request.getContextPath()%>/artDialog4.1.6/skins/icons/loading2.gif';
						chuli = art.dialog({
						    content: '<div style=\"text-align:center; text-indent:-999em; overflow:hidden; background:url('+imgurl+') no-repeat center center;\"><span>loading..</span></div><br/>数据处理中,请稍候.....',
						    title: '数据处理中',
			    			lock: true,
						    opacity: 0.87
						});
						$.ajax({
							type:'POST',
							url: '<%=request.getContextPath()%>/yanche/vehpcb_updateVDataCheck.action?lsh=' + lsh + '&ycLx=' + ycLx + '&check=' + check + '&yuanyin=' + yuanyin + '&beizhu=' + beizhu,
							dataType: 'html',
							success:function(data){
								chuli.close();
								if(data == 0){
									alert("修改成功!");
									location.reload();
								}else{
									alert("修改失败!");
									exception(data);
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
			
			function closechuli(){
				chuli.close();
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
			
			function myclear(obj){
				obj.value = obj.value.replace(" ","");
				obj.value = obj.value.replace("　","");
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
					<form action="<%=request.getContextPath()%>/yanche/vehpcb_initVDataCheckList.action" method="post">
						<table class="table1" width="100%" border="0"
							cellpadding="0" cellspacing="0">
							<tr>
								<th class="th1" height="32" colspan="4">
									查询条件<input type="button" ok/>
									<input type="hidden" name="chatype" value="${chatype}" />
								</th>
							</tr>
							<tr>
								<td class="tds" style="text-align: right;">
									流水号&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<input type="text" name="slsh" value="${slsh}" />
								</td>
								<td class="tds" style="text-align: right">
									车辆识别代号&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<input type="text" name="sclsbdh" value="${sclsbdh}" />
								</td>
							</tr>
							<tr>
								<td class="tds" style="text-align: right;">
									号牌号码&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<input type="text" name="shphm" value="${shphm}" />
								</td>
								<td class="tds" style="text-align: right">
									号牌种类&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<s:select headerKey="" headerValue="---请选择---" list="#{'01':'01---大型汽车','02':
									'02---小型汽车','03':'03---使馆汽车','04':
									'04---领馆汽车','05':'05---境外汽车','06':
									'06---外籍汽车','07':'07---普通摩托车','08':
									'08---轻便摩托车','09':'09---使馆摩托车','10':
									'10---领馆摩托车','11':'11---境外摩托车','12':'12---外籍摩托车'
									,'13':'13---低速车','14':'14---拖拉机'
									,'15':'15---挂车','16':'16---教练汽车'
									,'17':'17---教练摩托车','18':'18---试验汽车'
									,'19':'19---试验摩托车','20':'20---临时入境汽车'
									,'21':'21---临时入境摩托车','22':'22---临时行驶车'
									,'23':'23---警用汽车','24':'24---警用摩托'
									,'25':'25---原农机号牌','26':'26---香港入出境车'
									,'27':'27---澳门入出境车'
									}" theme="simple" listKey="key" listValue="value"
									 name="shpzl" id="hpzlid" value="#request.shpzl"></s:select>
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
								业务流水
							</th>
							<th>
								车辆识别代号
							</th>
							<th>
								号牌号码
							</th>
							<th>
								号牌种类
							</th>
							<th>
								验车结果
							</th>
							<th>
								验车时间
							</th>
							<th>
								操作
							</th>
						</tr>
						<s:if test="#request.vDataCheckList.size > 0">
							<s:iterator id="vdc" value="#request.vDataCheckList" status="st">
								<tr class="<s:if test="#st.odd == false">altrow</s:if>">
									<td>
										${st.count + (map.currentpage-1) * map.pagesize}
									</td>
									<td>
										${vdc.lsh}
									</td>
									<td>
										${vdc.clsbdh}
									</td>
									<td>
										${vdc.hphm}
									</td>
									<td>
										<s:if test="#vdc.hpzl == 01">
						    				大型汽车
						    			</s:if>
						    			<s:elseif test="#vdc.hpzl == 02">
						    				小型汽车
						    			</s:elseif>
						    			<s:elseif test="#vdc.hpzl == 03">
						    				使馆汽车
						    			</s:elseif>
						    			<s:elseif test="#vdc.hpzl == 04">
						    				领馆汽车
						    			</s:elseif>
						    			<s:elseif test="#vdc.hpzl == 05">
						    				境外汽车
						    			</s:elseif>
						    			<s:elseif test="#vdc.hpzl == 06">
						    				外籍汽车
						    			</s:elseif>
						    			<s:elseif test="#vdc.hpzl == 07">
						    				普通摩托车
						    			</s:elseif>
						    			<s:elseif test="#vdc.hpzl == 08">
						    				轻便摩托车
						    			</s:elseif>
						    			<s:elseif test="#vdc.hpzl == 09">
						    				使馆摩托车车
						    			</s:elseif>
						    			<s:elseif test="#vdc.hpzl == 10">
						    				领馆摩托车
						    			</s:elseif>
						    			<s:elseif test="#vdc.hpzl == 11">
						    				境外摩托车
						    			</s:elseif>
						    			<s:elseif test="#vdc.hpzl == 12">
						    				外籍摩托车
						    			</s:elseif>
						    			<s:elseif test="#vdc.hpzl == 13">
						    				低速车
						    			</s:elseif>
						    			<s:elseif test="#vdc.hpzl == 14">
						    				拖拉机
						    			</s:elseif>
						    			<s:elseif test="#vdc.hpzl == 15">
						    				挂车
						    			</s:elseif>
						    			<s:elseif test="#vdc.hpzl == 16">
						    				教练汽车
						    			</s:elseif>
						    			<s:elseif test="#vdc.hpzl == 17">
						    				教练摩托车
						    			</s:elseif>
						    			<s:elseif test="#vdc.hpzl == 18">
						    				试验汽车
						    			</s:elseif>
						    			<s:elseif test="#vdc.hpzl == 19">
						    				试验摩托车
						    			</s:elseif>
						    			<s:elseif test="#vdc.hpzl == 20">
						    				临时入境汽车
						    			</s:elseif>
						    			<s:elseif test="#vdc.hpzl == 21">
						    				临时入境摩托车
						    			</s:elseif>
						    			<s:elseif test="#vdc.hpzl == 22">
						    				临时行驶车
						    			</s:elseif>
						    			<s:elseif test="#vdc.hpzl == 23">
						    				警用汽车
						    			</s:elseif>
						    			<s:elseif test="#vdc.hpzl == 24">
						    				警用摩托
						    			</s:elseif>
						    			<s:elseif test="#vdc.hpzl == 25">
						    				原农机号牌
						    			</s:elseif>
						    			<s:elseif test="#vdc.hpzl == 26">
						    				香港入出境车
						    			</s:elseif>
						    			<s:elseif test="#vdc.hpzl == 26">
						    				澳门入出境车
						    			</s:elseif>
									</td>
									<td>
										<s:if test="#vdc.checkFalg == 2">
						    				不合格
						    			</s:if>
						    			<s:elseif test="#vdc.checkFalg == 1">
						    				合格
						    			</s:elseif>
									</td>
									<td>
										<s:date name="#vdc.checkDate" format="yyyy-MM-dd HH:mm:ss"/>
									</td>
									<td>
					    				<a href="javascript:void(0);" onclick="javascript:update('${vdc.lsh}','${vdc.ycLx}','${vdc.checkFalg}');">修改</a>
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
		<div id="xiugaiid" style="overflow-y:scroll;overflow-x:none;display: none;">
			<table class="datalist" width="400px;">
				<tr>
					<td style="text-align: right;">
						原始状态:
					</td>
					<td style="text-align: left;" class="tdstr" id="tdstrid">
						
					</td>
				</tr>
				<tr>
					<td style="text-align: right;">
						审核结果:
					</td>
					<td style="text-align: left;">
						<input type="radio" class="disabled1" id="shjg0" checked="checked"
								name="shjg" value="1" />合格
						<input type="radio" class="disabled1" id="shjg1"
							name="shjg" value="2" />不合格
					</td>
				</tr>
				<tr>
					<td style="text-align: right;">
						原因:
					</td>
					<td style="text-align: left;">
						<s:iterator id="bhgyy" value="#request.bhgyylist" status="st">
							<c:if test="${((st.count+1)%2) == 0}"></br></c:if>
							<input type="checkbox" id="zbhgyyid${st.count}" name="bhgyynames" value="${bhgyy[0] }" />&nbsp;${bhgyy[1]}&nbsp;&nbsp;
						</s:iterator>
					</td>
				</tr>
				<tr>
					<td style="text-align: right;">
						备注:
					</td>
					<td style="text-align: left;">
						<textarea rows="5" cols="30" name="beizhu" id="beizhu"></textarea>
					</td>
				</tr>
			</table>
		</div>
	</body>
</html>
