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
		<title>待验车分配信息</title>
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
				//全选
				$("#chkids").click(function(){
					if($("#chkids").attr("checked")){
						$(".chk").each(function(i){
							$(this).attr("checked", true);
						});
					}else{
						$(".chk").each(function(i){
							$(this).attr("checked", false);
						});
					}
				});
			});
			
			function checkAll(type){
				var chks = $('.chk');
				var ids = '', chk;
				for(var i =0, len = chks.length; i < len; i++){
					chk = $(chks[i]);
					if(chk.attr('checked')){
						ids += 'id='+chk.attr('rawValue')+'&';
					}
				}
				if(ids.length > 0){
					if("2" == type){
						$("#suserid option").remove();
						$("#suserid").append('<option value=>---请选择---</option>');
						<s:iterator id="user" value="#request.vVehUserYcsList">
							var userstr = '${user.loginId}' +'---' + '${user.userName}';
							$("#suserid").append("<option value="+'${user.loginId}'+">"+userstr+"</option>");
						</s:iterator>
						var baocun = art.dialog({
							width:'50%',
						    content: document.getElementById("dfenpei"),
						    title: '分配民警验车',
						    okVal: '保存',
						    ok: function () {
						    	var yjsj = $("#yjycsj").val();
						    	var szmj = $("#suserid").val();
						    	var fpms = $("#fpms").val();
						    	fpms = encodeURI(fpms);
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
									url: '<%=request.getContextPath()%>/yanche/vehpcb_fenpeiycmj.action?' + ids + 'yjsj=' + yjsj + '&szmj=' + szmj + '&fpms=' + fpms,
									dataType: 'html',
									success:function(data){
										chuli.close();
										if(data == 0){
											alert("分配成功!");
											location.reload();
										}else if(data == 2){
											alert("该批车辆中,车辆型号或使用性质不在该民警的验车范围内不能分配验车!");
										}else if(data == 3){
											alert("该民警未设置验车类型和试用性质,不能进行分配验车!");
										}else{
											alert("分配失败!");
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
					}else if("3" == type){
						var baocun = art.dialog({
							width:'50%',
						    content: document.getElementById("dtuiban"),
						    title: '预约验车退办',
						    okVal: '保存',
						    ok: function () {
						    	var tbyyms = $("#tbyyms").val();
						    	tbyyms = encodeURI(tbyyms);
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
									url: '<%=request.getContextPath()%>/yanche/vehpcb_fenpeitb.action?' + ids + 'tbyyms=' + tbyyms,
									dataType: 'html',
									success:function(data){
										chuli.close();
										if(data == 0){
											alert("退办成功!");
											location.reload();
										}else{
											alert("退办失败!");
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
				}else{
					alert('请勾选要操作的数据!');
				}
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
					<s:if test="#request.type == 2">
						<div style="width: 98%;height: 35px;padding-top: 10px;">
							<input class="bnt" type="button" value="分配民警" onclick="javascript:checkAll('2');" style="cursor:pointer;" />
						</div>
					</s:if>
					<s:elseif test="#request.type == 3">
						<div style="width: 98%;height: 35px;padding-top: 10px;">
							<input class="bnt" type="button" value="退 办" onclick="javascript:checkAll('3');" style="cursor:pointer;" />&nbsp;
						</div>
					</s:elseif>
					<table class="datalist" width="100%" border="0"
						cellpadding="0" cellspacing="0">
						<tr class="tr1">
							<th>
								<s:if test="#request.type == 2 || #request.type == 3">
									<input name="input" type="checkbox" id="chkids" />&nbsp;全选
								</s:if>
							</th>
							<th>
								序号
							</th>
							<th>
								业务流水
							</th>
							<th>
								业务类型
							</th>
							<th>
								车辆识别代号
							</th>
							<th>
								车辆型号
							</th>
							<th>
								状态
							</th>
							<th>
								分配验车民警
							</th>
							<th>
								分配人
							</th>
						</tr>
						<s:if test="#request.pdasmycVehPcbList.size > 0">
							<s:iterator id="vehpcb" value="#request.pdasmycVehPcbList" status="st">
								<tr class="<s:if test="#st.odd == false">altrow</s:if>">
									<td>
										<s:if test="#request.type == 2 && (#vehpcb.ycpch == '未分配验车' || #vehpcb.ycpch == '已分配验车')">
											<input type="checkbox" class="chk" rawValue="${vehpcb.lsh}" />
										</s:if>
										<s:elseif test="#request.type == 3 && #vehpcb.ycpch != '已退办'">
											<input type="checkbox" class="chk" rawValue="${vehpcb.lsh}" />
										</s:elseif>
									</td>
									<td>
										${st.count + (map.currentpage-1) * map.pagesize}
									</td>
									<td>
										${vehpcb.lsh}
									</td>
									<td>
								 		<s:if test="#vehpcb.yclx == 'AA'">
						    				未售出新车
						    			</s:if>
						    			<s:elseif test="#vehpcb.yclx == 'AB'">
						    				已售出新车
						    			</s:elseif>
						    			<s:elseif test="#vehpcb.yclx == 'BB'">
						    				未售出在用车
						    			</s:elseif>
						    			<s:elseif test="#vehpcb.yclx == 'BC'">
						    				已售出在用车
						    			</s:elseif>
									</td>
									<td>
								 		${vehpcb.ycfpqkMs}
									</td>
									<td>
										${vehpcb.ycfpcode}
									</td>
									<td>
										${vehpcb.ycpch}
									</td>
									<td>
										${vehpcb.fpYcmjxm}
									</td>
									<td>
										${vehpcb.ycfpxm}
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
				</div>

				<div class="corner topLeft"></div>
				<div class="corner topRight"></div>
			</div>
		</div>
		
		<div id="dfenpei" style="display: none;">
			<table class="datalist">
				<tr>
					<td style="text-align: right;">
						选择民警:
					</td>
					<td style="text-align: left;">
						<select name="szmj" id="suserid">
							
						</select>
					</td>
				</tr>
				<tr>
					<td style="text-align: right;">
						预计上门验车时间:
					</td>
					<td style="text-align: left;">
						<input name="yjycsj" id="yjycsj" size="20" type="text"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
					</td>
				</tr>
				<tr>
					<td style="text-align: right;">
						分配验车描述:
					</td>
					<td style="text-align: left;">
						<input type="text" name="fpms" size="30" id="fpms" />
					</td>
				</tr>
			</table>
		</div>
		<div id="dtuiban" style="display: none;">
			<table class="datalist">
				<tr>
					<td style="text-align: right;">
						退办原因:
					</td>
					<td style="text-align: left;">
						<textarea rows="5" cols="30" name="tbyyms" id="tbyyms"></textarea>
					</td>
				</tr>
			</table>
		</div>
	</body>
</html>
