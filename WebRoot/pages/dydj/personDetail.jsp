<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>个人自主办理抵押登记取消抵押登记业务</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/jquery-1.8.2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/jquery-ui-1.9.1.custom.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/public.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/artDialog4.1.6/jquery.artDialog.source.js?skin=chrome"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/artDialog4.1.6/plugins/iframeTools.source.js"></script>
<style>
.table,.table td,.table th {
	border: 1px solid #b3b4b2;
	border-collapse: collapse;
	word-break: break-all;
	word-wrap: break-word;
	height: 30px;
	font-size: 12px;
	vertical-align: middle;
}
.span {
	position: absolute;
	top: 10px;
	left: 64px;
	font-weight: bold;
	color: #164796;
}
.sqlr {
	width: 794px;
	height: auto;
	margin-top:10px;
}
.td_1 {
	text-align: right;
}
.td_2 {
	text-align: left;
}
.td_3 {
	text-align: center;
	font-weight: bold;
	letter-spacing: 1px;
}
.text_1 {
	width: 200px;
}
.text_2 {
	width: 100px;
}
.tablelr{ width:auto; height:auto; border: 1 solid red; }
.edittable{ float:left; width:450px;}

.bnt {
		width: 72px;
		height: 21px;
		line-height: 21px;
		border: none;
		background: url(<%=request.getContextPath()%>/images/an.gif ) no-repeat;
		color: #FFFFFF;
		vertical-align: middle;
	}
	
.title {
	MARGIN-TOP: 1px; MARGIN-LEFT: 1px; WIDTH: 99.8%; MARGIN-RIGHT: 1px; HEIGHT: 20px; BACKGROUND-COLOR: #353c44;
	font-size: 12px; padding-top: 5px; padding-left: 10px;
}
</style>
<script type="text/javascript">
	var chuli;
	$(document).ready(function(){
		var editType = '${editType}';
		if(editType == "查看"){
			$("input").not(".bnt").not("#lrzh").each(function(){
				$(this).attr("disabled", "disabled");
			});
			$("#hpzl").attr("disabled", "disabled");
			$("#selDydj").hide();
			var sbzt = '${dydjYwsbspb.sbzt}'
			if(sbzt == "6"){
				$("input[name='bllx'][value='0']").attr("checked", true);
			}else{
				$("input[name='bllx'][value='1']").attr("checked", true);
			}
		}
		var qjtddz = $("#qjTddz").val();
		var yjtddz = $("#yjTddz").val();
		if(qjtddz == null || qjtddz == ""){
			$("#qjTddz").val("深圳市");
		}
		if(yjtddz == null || yjtddz == ""){
			$("#yjTddz").val("深圳市");
		}
	});	
   function changeM(radio){  
	   if(radio.id=='sbtg'){  
	     $('#dialogTable').find('tr:eq(1)').show();
	     $('#dialogTable').find('tr:eq(2)').show();
	   }else{//点击退办时隐藏行
	     $('#dialogTable').find('tr:eq(1)').hide();
	     $('#dialogTable').find('tr:eq(2)').hide();
	   }
      }
      function openyzqj(){//打开窗体
	      $("#yzqjrXm").attr("disabled", false);
	      $("#yzqjrLxdh").attr("disabled",false);
	      $("#sbzts").attr("disabled",false);
	      var input = $("#divSelPtmoValue").find("input:radio");
	          input.attr("disabled",false);
	      var sbztValue = $("#sbztValue").val();
	      if(sbztValue!='0'){
	         $("input[name='sbzts'][value="+sbztValue+"]").attr("checked",true);
	         if(sbztValue=='YT'){
	           $('#dialogTable').find('tr:eq(1)').hide();
		       $('#dialogTable').find('tr:eq(2)').hide();
	         }
	      }     
       	  art.dialog({
			 width:'45%',
		     content: $('#divSelPtmoValue').html(),
		     title: '审核',
			 lock: true,
		     opacity: 0.2			
	      });
	 }
     function editDialog(){
        var dydjId = $("#dydjId").val();
		var sbzt= $('input[name=sbzts]:checked').val();//是否通过的值
		var tbly = $("#sbztTbly").val();              //备注
		var yzqjrXm =$('#yzqjrXm').val();             //邮政取件人姓名
		var yzqjrLxdh =$('#yzqjrLxdh').val();         //邮政取件人联系电话
		if(sbzt == 'YT'){
			var tblyStu = checknotnull(document.getElementById("sbztTbly"),'请填写不通过备注信息!',0);
			if (tblyStu != "true") {
				return false;
			}else{
			   if(tbly.length>250) {
			     alert("备注长度不能超过250个字符!");
			     return false;
			   }
			}
		}else{
		   var xMStu = checknotnull(document.getElementById("yzqjrXm"),'请填写邮政取件人姓名!',0);
		   if(xMStu !="true") {
		       return false;
		   }
		   var lxdhStu = checknotnull(document.getElementById("yzqjrLxdh"),'请填写邮政取件人联系电话!',0);
		   if(lxdhStu != "true") {
		      return false;
		   }else{
		      lxdhStu = checkmobile(yzqjrLxdh);
		      if(lxdhStu!="true") {
		         return false;
		      }
		   }
		}
	    $.ajax({
			async:true,
   		    cache:false,
   		    type:'POST',
			url: '<%=request.getContextPath()%>/yzBus/yzBus_yzCheckEdit.action',
			data:{'id':dydjId,'yzqjrXm':yzqjrXm,'yzqjrLxdh':yzqjrLxdh,'sbzt':sbzt,'tbly':tbly,'item':'single'},  //发送的参数
			dataType: 'html',
			success:function(result){
			    var message = result+"";
				if(message.indexOf('异常信息') == -1){
					if($.trim(result) == "true"){
					    alert("邮政审核成功!");
						 window.location.href='<%=request.getContextPath()%>/yzBus/yzBus_yzCheckInit.action';
					}else{
						alert(result);
						return false;
				    }
				}else{
					exception(message);
					return false;
				}
			}
		 });
	}

	 function opendYzyj(){//打开窗体
      $("#userName").attr("disabled", false);
      $("#yzJjm").attr("disabled",false);
      $("#sbztTbly").attr("disabled",false);
       	  art.dialog({
			 width:'38%',
		     content: $('#yzMoveCheck').html(),
		     title: '审核',
			 lock: true,
		     opacity: 0.2			
	     });
	 }	
      //邮政单一移交
	  function yzMoveOne(){
		var id = $("#yzMoveID").val();
		var tbly = $("#sbztTbly").val();              //备注
		var yzJjm = $("#yzJjm").val();		          //邮政编码
		var userName =$("#userName").val();                   //用户名
        var userNameStu = checknotnull(document.getElementById("userName"),'请填写用户名!',0);
		if(userNameStu !="true") {
		    return false;
		}
		var yzJjmStu = checknotnull(document.getElementById("yzJjm"),'请填写交接码!',0);
		if(yzJjmStu !="true") {
		    return false;
		}
		 $.ajax({
		   async:true,
   		   cache:false,
   		    type:'POST',
			url: '<%=request.getContextPath()%>/yzBus/yzBus_yzMoveCheckPch.action',
			data:{'id':id,'tbly':tbly,'yzJjm':yzJjm,'userName':userName,'temp':'sigle'},//发送的参数
			dataType: 'html',
			success:function(result){
			    var message = result+"";
				if(message.indexOf('异常信息') == -1){
					 if($.trim(result).indexOf('true')!=-1){
					    alert(message.substring(4,message.length));
					   //  var a = window.opener.$('#work_tr'+id);
					   // a.remove();
					    window.opener.location.href="<%=request.getContextPath()%>/yzBus/yzBus_inintMoveCgList.action";
					    window.close();			
					}else{
						alert(result);
						return false;
					}
				}else{
					exception(message);
					return false;
				}
			},
		    error: function(xhr,msg,e) {
		    		alert('移交异常'+e);		    	  			
	        }
		});
	  }
	  	   function openEMS(){//打开窗体     
        $("#kdbm").attr("disabled",false);
       	  art.dialog({
			 width:'45%',
		     content: $('#divEMS').html(),
		     title: '审核',
			 lock: true,
		     opacity: 0.2			
	     });
	 }
	 
     	  //提交审核MES
	  function updateMES(){
		var id = $("#mesdydjId").val();
		var tbly = $("#kdTbly").val();              //备注
		var kdbm = $("#kdbm").val();		          // 快递编号
	    var kdbmStu = checknotnull(document.getElementById("kdbm"),'请填写快递编号!',0);
		if(kdbmStu !="true") {
		    return false;
		}
		 $.ajax({
		   async:true,
   		   cache:false,
   		    type:'POST',
			url: '<%=request.getContextPath()%>/yzBus/yzBus_isMovePosEidt.action',
			data:{'id':id,'tbly':tbly,'kdbm':kdbm},//发送的参数
			dataType: 'html',
			success:function(result){
			    var message = result+"";
				if(message.indexOf('异常信息') == -1){
					if($.trim(result) == "true"){
					    alert("邮政审核成功!");
						window.location.href='<%=request.getContextPath()%>/yzBus/yzBus_isMovePosInit.action';
					}else{
						alert(result);
						return false;
					}
				}else{
					exception(message);
					return false;
				}
			},
		    error: function(xhr,msg,e) {
		    		alert('邮政审核异常'+e);		    	  			
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
</script>
</head>

<body>
	<form action="" name="drv_form" id="drv_form" method="post">
		<div class="ywsq" style="text-align: center;">
					<input type="hidden" id="chfile1" value="0" />
					<div class="sqlr" style="padding-top: 50px;">
						<table width="795" class="table" id="jibenxinxi">
							<tr bgcolor="#abd3ff">
								<td colspan="4" class="td_3">基本信息</td>
							</tr>
							<tr id="czxx">
								<td height="35" class="td_1">银行受理流水号：</td>
								<td class="td_2" colspan="3" style="height: 35px;">
									&nbsp;
									<input class="text_2" style="width: 200px; " type="text" id="yhsllsh" name="yhsllsh" onblur="clearallspace(this)" value="${dydjYwsbspb.lsh}" />	
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" name="selct" class="bnt" id="selDydj" onclick="selDydjsbsp();" value="查询"/>
								</td>
							</tr>
							<tr id="czxx">
								<td height="35" class="td_1">车主姓名：</td>
								<td class="td_2"><input type="text" id="cxxm" name="cxxm" readonly="readonly"  value="${dydjYwsbspb.dyrSfzCardname}" />&nbsp;</td>
								<td class="td_1" style="width: 125px;">车主身份证号码：</td>
								<td class="td_2" >
									&nbsp;
									<input type="text" id="czsfzmhm" name="czsfzmhm" readonly="readonly" onkeyup="clearspace(this)" onblur="clearallspace(this)" value="${dydjYwsbspb.dyrSfzCardno}" />
									<input type="hidden" name="hideczsfzmhm" value="${dydjYwsbspb.dyrSfzCardno}"/>
								</td>
							</tr>
							<tr>
								<td width="124" class="td_1"><span style="color:red;" >*</span>号牌号码：</td>
								<td width="270" class="td_2">
									&nbsp;
								  <input class="text_2"  onkeyup="xiaobianda(this);clearspace(this);" onblur="clearallspace(this)"
									id="hphm" type="text" name="dydjYwsbspb.hphm" value="${dydjYwsbspb.hphm}"/> 
								  <input type="hidden" name="hidehphm" value="${dydjYwsbspb.hphm}"/>
							 	</td>
								<td width="108" class="td_1"><span style="color:red;" >*</span>号牌种类：</td>
								<td class="td_2">
									&nbsp;
									<select name="dydjYwsbspb.hpzl" id="hpzl" style="width: 155px;">
										<option value="">==号牌种类==</option>
										<s:iterator var="hp" value="#request.wscList">
											<option value='<s:property value="#hp.dmz"/>' 
												<s:if test="#request.dydjYwsbspb.hpzl==#hp.dmz">selected</s:if>>
												<s:property value="#hp.dmms1"/>
											</option>
										</s:iterator>
									</select>
									 <input type="hidden" name="hidehpzl" value="${dydjYwsbspb.hpzl}"/>
								</td>
							</tr>	
							<tr>
								<td class="td_1"><span style="color:red;" >*</span>主合同号：</td>
								<td class="td_2">
									&nbsp;
									<input type="text" name="dydjYwsbspb.zhth" id="zhth" value="${dydjYwsbspb.zhth}" onkeyup="xiaobianda(this); clearspace(this)" onblur="clearallspace(this)"/>
									 <input type="hidden" name="hidezhth" value="${dydjYwsbspb.zhth}"/>
								</td>
								<td class="td_1"><span style="color:red;" >*</span>抵押合同号：</td>
								<td class="td_2">
									&nbsp;
									<input type="text" name="dydjYwsbspb.dyhth" id="dyhth" value="${dydjYwsbspb.dyhth}" onkeyup="xiaobianda(this); clearspace(this)" onblur="clearallspace(this)"/>
									<input type="hidden" name="hidedyhth" value="${dydjYwsbspb.dyhth}"/>
								</td>
							</tr>
							<tr>
								<td class="td_1"><span style="color:red;" >*</span>车辆识别代号：</td>
								<td class="td_2">
									&nbsp;
									<input class="text_1" type="text" id="clsbdh" name="dydjYwsbspb.clsbdh" onkeyup="xiaobianda(this); clearspace(this)" onblur="clearallspace(this)" value="${dydjYwsbspb.clsbdh}"/>
									<input type="hidden" name="hideclsbdh" value="${dydjYwsbspb.clsbdh}"/>
								</td>
								<td class="td_1"><span style="color:red;" >*</span>登记证书编号：</td>
								<td class="td_2">
									&nbsp;
									<input class="text_1" type="text" id="djzsbh" name="dydjYwsbspb.djzsbh" onkeyup="xiaobianda(this); clearspace(this)" onblur="clearallspace(this)" value="${dydjYwsbspb.djzsbh}"/>
									<input type="hidden" name="hidedjzsbh" value="${dydjYwsbspb.djzsbh}"/>
								</td>
							</tr>
							
							<tr>
								<td class="td_1"><span style="color:red;" >*</span>车主联系电话：</td>
								<td class="td_2" colspan="3">
									&nbsp;
									<input type="text" id="czlxfs" name="dydjYwsbspb.czlxfs" onkeyup="clearspace(this)" onblur="clearallspace(this)" value="${dydjYwsbspb.czlxfs}" />
								</td>
							</tr>
						</table>
						<table width="796" id="qjrxx" class="table">
							<tr>
								<td height="52" colspan="7" class="td_3"  bgcolor="#abd3ff">取件人信息</td>
							</tr>
							<tr>
								<td width="120"  height="52" class="td_1"><span style="color:red;" >*</span>取件人姓名：</td>
								<td class="td_2" style="width: 278px;">
									&nbsp;
									<input class="text_2" type="text" id="qjQjrxm" name="dydjYwsbspb.qjQjrxm" onkeyup="textchange('qjQjrxm', 'yjSjrxm'); clearspace(this);" onblur="clearallspace(this);" onchange="textchange('qjQjrxm', 'yjSjrxm');" value="${dydjYwsbspb.qjQjrxm}"/>&nbsp;</td>
								<td align="right"><span class="td_1"><span style="color:red;" >*</span>取件人电话：</span></td>
								<td style="text-align: left;">
									&nbsp;
									<span class="td_2"><input type="text" id="qjLxdh" name="dydjYwsbspb.qjLxdh" onkeyup="textchange('qjLxdh', 'yjLxdh');" onchange="textchange('qjLxdh', 'yjLxdh');" value="${dydjYwsbspb.qjLxdh}"/></span></td>
							</tr>
							<tr>
								<td class="td_1"><span style="color:red;" >*</span>取件地址：</td>
								<td class="td_2">
									&nbsp;
									<input class="text_2" type="text" id="qjTddz" name="dydjYwsbspb.qjTddz" onkeyup="textchange('qjTddz', 'yjTddz'); clearspace(this);" onblur="clearallspace(this);" onchange="textchange('qjTddz', 'yjTddz');" value="${dydjYwsbspb.qjTddz}" style="width: 200px"/></td>
							    <td class="td_1"><span style="color:red;" >*</span>邮政编码：</td>
								<td class="td_2" colspan="4">
									&nbsp;
									<input type="text" id="qjYzbm" name="dydjYwsbspb.qjYzbm" onkeyup="textchange('qjYzbm', 'yjYzbm'); clearspace(this);" onblur="clearallspace(this);" onchange="textchange('qjYzbm', 'yjYzbm');" value="${dydjYwsbspb.qjYzbm}"/></td>
							</tr>
						</table>
						<table width="796" id="yjrxx" class="table">
							<tr>
								<td height="52" colspan="7" class="td_3" bgcolor="#abd3ff">邮寄收件人信息</td>
							</tr>
							<tr>
								<td width="120"  height="52" class="td_1"><span style="color:red;" >*</span>收件人姓名：</td>
								<td  class="td_2" style="width: 278px;">
									&nbsp;
									<input class="text_2" type="text" id="yjSjrxm" name="dydjYwsbspb.yjSjrxm" onkeyup="clearspace(this)" onblur="clearallspace(this)" value="${dydjYwsbspb.yjSjrxm}" maxlength="20"/>&nbsp;</td>
								<td align="right"><span class="td_1"><span style="color:red;" >*</span>收件人电话：</span></td>
								<td style="text-align: left;">
									&nbsp;
									<span class="td_2">
								  	<input type="text" id="yjLxdh" name="dydjYwsbspb.yjLxdh" onkeyup="clearspace(this)" onblur="clearallspace(this)" value="${dydjYwsbspb.yjLxdh}"/>
								</span></td>
							</tr>
							<tr>
								<td class="td_1"><span style="color:red;" >*</span>收件人地址：</td>
								<td class="td_2">
									&nbsp;
									<input class="text_2" type="text" id="yjTddz" name="dydjYwsbspb.yjTddz" onkeyup="clearspace(this)" onblur="clearallspace(this)" value="${dydjYwsbspb.yjTddz}" style="width: 200px"/></td>
							    <td class="td_1"><span style="color:red;" >*</span>邮政编码：</td>
								<td class="td_2" colspan="4">
									&nbsp;
									<input type="text" id="yjYzbm" name="dydjYwsbspb.yjYzbm" onkeyup="clearspace(this)" onblur="clearallspace(this)" value="${dydjYwsbspb.yjYzbm}"/></td>
							</tr>
						</table>
						<s:if test="#request.editType=='查看'">
				        <table width="796" id="yjrxx" class="table">
							<tr>
								<td height="52" colspan="7" class="td_3" bgcolor="#abd3ff">审核信息</td>
							</tr>
							<tr>
								<td width="120"  height="52" class="td_1">审核状态：</td>
								<td  class="td_2" style="width: 278px;"> &nbsp;&nbsp;&nbsp;&nbsp;  <s:if test="#request.dydjYwsbspb.sbzt==0">待审 </s:if>
													    <s:if test="#request.dydjYwsbspb.sbzt==1">待取件 </s:if>
													    <s:if test="#request.dydjYwsbspb.sbzt==2">邮政待签注资料移交车管</s:if>
													    <s:if test="#request.dydjYwsbspb.sbzt==3">车管待签注资料复核 </s:if>
													    <s:if test="#request.dydjYwsbspb.sbzt==4">车管已签注资料移交邮政 </s:if>
													    <s:if test="#request.dydjYwsbspb.sbzt==5">邮政回填EMS单号</s:if>
													    <s:if test="#request.dydjYwsbspb.sbzt=='CT'">车管所退办</s:if>
													    <s:if test="#request.dydjYwsbspb.sbzt=='YT'">邮政退办 </s:if>
													    <s:if test="#request.dydjYwsbspb.sbzt=='CT4'">车管退办资料移交邮政 </s:if>
													    <s:if test="#request.dydjYwsbspb.sbzt=='CT5'">资料退办 </s:if>&nbsp;</td>
								<td class="td_1"  style="width: 124px;"><span class="td_1">备  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</span></td>
								<td class="td_2" colspan="4">&nbsp;<span class="td_2" style="width:300px ">${dydjYwsbspb.sbztTbly}</span></td>
							</tr>
							<tr>
								<td width="120"  height="52" class="td_1">邮政用户代码：</td>
								<td  class="td_2"> &nbsp;&nbsp;&nbsp;&nbsp;${dydjYwsbspb.yzYhdm} &nbsp;</td>
								<td class="td_1"  style="width: 124px;"><span class="td_1">邮政用户姓名:</span></td>
								<td class="td_2" colspan="4">&nbsp;<span class="td_2" style="width:300px ">${dydjYwsbspb.yzYhxm}</span></td>
							</tr>
							<tr>
								<td width="120"  height="52" class="td_1">邮政审核IP：</td>
								<td  class="td_2"> &nbsp;&nbsp;&nbsp;&nbsp;${dydjYwsbspb.yzIp} &nbsp;</td>
								<td class="td_1"  style="width: 124px;"><span class="td_1">邮政审核时间：</span></td>
								<td class="td_2" colspan="4">&nbsp;<span class="td_2" style="width:300px ">${dydjYwsbspb.yzYhsj}</span></td>
							</tr>
							<tr>
								<td width="120"  height="52" class="td_1">车管用户代码：</td>
								<td  class="td_2"> &nbsp;&nbsp;&nbsp;&nbsp;${dydjYwsbspb.cgYhdm} &nbsp;</td>
								<td class="td_1"  style="width: 124px;"><span class="td_1">车管用户姓名：</span></td>
								<td class="td_2" colspan="4">&nbsp;<span class="td_2" style="width:300px ">${dydjYwsbspb.cgYhxm}</span></td>
							</tr>
							<tr>
								<td width="120"  height="52" class="td_1">车管审核IP：</td>
								<td  class="td_2"> &nbsp;&nbsp;&nbsp;&nbsp;${dydjYwsbspb.cgIp} &nbsp;</td>
								<td class="td_1"  style="width: 124px;"><span class="td_1">车管审核时间：</span></td>
								<td class="td_2" colspan="4">&nbsp;<span class="td_2" style="width:300px ">${dydjYwsbspb.cgYhsj}</span></td>
							</tr>
					  </table>
					 </s:if>
			  <div style="clear:both; width:796px; height:30px; text-align:center; margin-top:20px;">
			  	 <input type="button" style="cursor:pointer;" onclick="javascript:window.close();" value="关 闭" class="bnt" />
			  </div>
			  </div>
		</div>
	 
	</form>
</body>
</html>