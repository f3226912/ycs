<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="<%=request.getContextPath()%>"></c:set>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<title>抵押登记详情</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/jquery-1.8.2.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/content.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/jquery/jquery-lightbox-0.5/css/jquery.lightbox-0.5.css"
	media="screen" />
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/jquery-ui-1.9.1.custom.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/public.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/artDialog4.1.6/jquery.artDialog.source.js?skin=chrome"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/artDialog4.1.6/plugins/iframeTools.source.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/jquery/jquery-lightbox-0.5/js/jquery.lightbox-0.5.js"></script>
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
	background: url(<%=request.getContextPath()%>/images/an.gif )
		no-repeat;
	color: #FFFFFF;
	vertical-align: middle;
}
.title {
	MARGIN-TOP: 1px; MARGIN-LEFT: 1px; WIDTH: 99.8%; MARGIN-RIGHT: 1px; HEIGHT: 20px; BACKGROUND-COLOR: #353c44;
	font-size: 12px; padding-top: 5px; padding-left: 10px;
}
.moveDiv{
	 width:650px;
	 height:30px;
	 line-height:30px;
	 background:#39C;
	 margin-top:-30px;
	 font-weight:bold;
	 cursor:move;
	 }
.moveTxt{
	 position:absolute;
	 display:none;
	 height:380px;
	 background:#FFFFFF;
	 border:#0C6 1px solid;
	 padding-top:30px
	 z-index:101;  
	 width: 650px; 
	 top: expression((body.clientHeight-380)/2); 
	 left: 160px;  
	 text-align:center;
	 }
</style>

<script type="text/javascript">
	$(document).ready(function(){
		//图片放大
		$(function(){
			$("#img01a").lightBox();
			$("#gpytp1").lightBox();
		});
		var editType = '${editType}';
		if(editType == "查看"){
			$("input").not(".bnt").each(function(){
				$(this).attr("disabled", "disabled");
			});
			
			$("#hpzl").attr("disabled", "disabled");
			$("#ycsDeptId").attr("disabled", "disabled");
			$("#yhorgcomtr").hide();
			$("#dyrorgcomtr").hide();
			$("#filetr").hide();
			$("#filetr1").hide();
			$("#sfz_b1").hide();
			$("#sfz_b2").hide();
			$("#ReadResult").hide();
			$("#ReadResult1").hide();
			var ywlx = '${dydjYwsbspb.ywlx}';
			var sqlx = '${dydjYwsbspb.sqlx}';
			var ywbld = '${dydjYwsbspb.ycsDeptId}';
			var sbzt = '${dydjYwsbspb.sbzt}'
			//$("input[type=radio][name='dydjYwsbspb.ywlx'][value='1']").attr("checked",true);
			$(":radio[name='dydjYwsbspb.ywlx']").val([ywlx]);
			$(":radio[name='dydjYwsbspb.sqlx']").val([sqlx]);
			if(sqlx == '11'){
				$("#zjxxdiv").attr("width","1160");
				$("#czxx").hide();
				$("#gszzjgzxx").hide();
				$("#qjrxx").show();
				$("#yjrxx").show();
			}else if(sqlx == '12'){
				$("#zjxxdiv").attr("width","1500");
				$("#czxx").hide();
				$("#gszzjgzxx").show();
				$("#qjrxx").show();
				$("#yjrxx").show();
			}else{
				$("#zjxxdiv").attr("width","795");
				$("#czxx").show();
				$("#gszzjgzxx").hide();
				$("#qjrxx").hide();
				$("#yjrxx").hide();
				$("#trbld").hide();
			}
			$("#ycsDeptId").val(ywbld);
			if(sbzt == "6"){
				$("input[name='bllx'][value='0']").attr("checked", true);
				$("#qjrxx").hide();
			    $("#yjrxx").hide();
			}else{
				$("input[name='bllx'][value='1']").attr("checked", true);
				$("#qjrxx").show();
			    $("#yjrxx").show();
			}
			
		}
	});
	 function opendYzyj(){
	 $("#userName").attr("disabled", false);
	 $("#yzJjm").attr("disabled", false);
            art.dialog({
			  width:'38%',
			  resize: false,
		      content: $('#yzMoveCheck').html(),
		      title: '邮政移交审核',
			  lock: true,
			  padding:5,
			  fixed: true,
		      opacity: 0.1			
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
	<div style="width: 100%; height: 100%; padding-left: 15px; text-align: left;">
		<form action="<%=request.getContextPath()%>/bank/bank_editYhShenban.action" name="drv_form" id="drv_form" method="post" enctype="multipart/form-data" target="abc">
			<iframe id="abc" name="abc" width="0" height="0" style="display: none;"></iframe>
				<input type="hidden" id="chfile02" value="0" />
				<input type="hidden" name="zjzpdate" id="zjzpdate" />
				<div class="sqlr">
					<table width="795" class="table">
						<tr bgcolor="#abd3ff">
							<td colspan="4" class="td_3">基本信息</td>
						</tr>
						<tr>
							<td width="124" class="td_1"><span style="color:red;" >*</span>业务办理类型：</td>
							<td colspan="3" class="td_2">
							<input type="radio" name="dydjYwsbspb.ywlx" value="A" checked="checked" /> 抵押登记 &nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="dydjYwsbspb.ywlx" value="B" /> 解除抵押登记</td>
						</tr>
						<tr>
							<td width="124" class="td_1"><span style="color:red;" >*</span>银行受理类型：</td>
							<td colspan="3" class="td_2">
							<input type="radio"	name="dydjYwsbspb.sqlx" value="11" checked="checked"/>代办个人车辆申报&nbsp;&nbsp;
							<input type="radio" name="dydjYwsbspb.sqlx" value="12" />代办单位车辆申报&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="dydjYwsbspb.sqlx" value="21" />车主自行办理车辆申报</td>
						</tr>
						<tr>
						  <td width="124" class="td_1"><span style="color:red;" >*</span>主合同号：</td>
							<td width="270" class="td_2">
								&nbsp;
							  <input onkeyup="xiaobianda(this);clearspace(this);" onblur="clearallspace(this);"
								id="zhth" type="text" name="dydjYwsbspb.zhth"  value="${dydjYwsbspb.zhth}"/> 
						  </td>
							<td width="108" class="td_1"><span style="color:red;" >*</span>抵押合同号：</td>
							<td width="273" class="td_2">
						 		&nbsp;
						 	 <input type="text" id="dyhth" name="dydjYwsbspb.dyhth" onkeyup="xiaobianda(this); clearspace(this)" onblur="clearallspace(this);" value="${dydjYwsbspb.dyhth}"/></td>
						  
						</tr>
						<tr>
							<td width="124" class="td_1"><span style="color:red;" >*</span>号牌号码：</td>
							<td width="270" class="td_2">
								&nbsp;
							  <input onkeyup="xiaobianda(this);clearspace(this);" onblur="clearallspace(this)"
								id="hphm" type="text" name="dydjYwsbspb.hphm" value="${dydjYwsbspb.hphm}"/> 
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
							</td>
						</tr>	
						<tr>
							<td class="td_1"><span style="color:red;" >*</span>车辆识别代号：</td>
							<td class="td_2">
								&nbsp;
								<input type="text" id="clsbdh" name="dydjYwsbspb.clsbdh" onkeyup="xiaobianda(this); clearspace(this)" onblur="clearallspace(this)" value="${dydjYwsbspb.clsbdh}"/>
							</td>
							<td class="td_1"><span style="color:red;" >*</span>登记证书编号：</td>
							<td class="td_2">
								&nbsp;
								<input type="text" id="djzsbh" name="dydjYwsbspb.djzsbh" onkeyup="xiaobianda(this); clearspace(this)" onblur="clearallspace(this)" value="${dydjYwsbspb.djzsbh}"/>
							</td>
						</tr>
						<tr id="czxx" style="display: none;">
							<td class="td_1">车主身份证号码：</td>
							<td class="td_2">
								&nbsp;
								<input type="text" id="czsfzmhm" name="czsfzmhm" onkeyup="xiaobianda(this); clearspace(this)" onblur="clearallspace(this)" value="${dydjYwsbspb.dyrSfzCardno}" />
							</td>
							<td height="35" class="td_1">车主姓名：</td>
							<td class="td_2">
								&nbsp;
								<input type="text" id="czxm" name="czxm" onkeyup="clearspace(this)" onblur="clearallspace(this)" value="${dydjYwsbspb.dyrSfzCardname}" />
								<input type="hidden" id="ycsDeptId" name="dydjYwsbspb.ycsDeptId" value="C34702A8FED97CBFE040007F0100339B"/>
								<input type="hidden" id="ycsDeptName" name="dydjYwsbspb.ycsDeptName" value="西丽总所"/>
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
							<td height="52" colspan="4" class="td_3" bgcolor="#abd3ff">取件人信息</td>
						</tr>
						<tr>
							<td width="120"  height="52" class="td_1"><span style="color:red;" >*</span>取件人姓名：</td>
							<td class="td_2" style="width: 278px;">
								&nbsp;
								<input class="text_2" type="text" id="qjQjrxm" name="dydjYwsbspb.qjQjrxm" onkeyup="textchange('qjQjrxm', 'yjSjrxm'); clearspace(this);" onblur="clearallspace(this);" onchange="textchange('qjQjrxm', 'yjSjrxm');" value="${dydjYwsbspb.qjQjrxm}"/>&nbsp;</td>
							<td align="right"><span class="td_1"><span style="color:red;" >*</span>取件人电话：</span></td>
							<td>
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
							<td height="52" colspan="4" class="td_3" bgcolor="#abd3ff">邮寄收件人信息</td>
						</tr>
						<tr>
							<td width="120"  height="52" class="td_1"><span style="color:red;" >*</span>收件人姓名：</td>
							<td  class="td_2" style="width: 278px;">
								&nbsp;
								<input class="text_2" type="text" id="yjSjrxm" name="dydjYwsbspb.yjSjrxm" onkeyup="clearspace(this)" onblur="clearallspace(this)" value="${dydjYwsbspb.yjSjrxm}" maxlength="20"/>&nbsp;</td>
							<td align="right"><span class="td_1"><span style="color:red;" >*</span>收件人电话：</span></td>
							<td>
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
								<td height="52" colspan="7" class="td_3" bgcolor="#abd3ff">录入信息</td>
							</tr>
							<tr>
								<td width="120"  height="52" class="td_1">录入账号：</td>
								<td  class="td_2" style="width: 278px;"> &nbsp;&nbsp; ${dydjYwsbspb.lrzh} </td>
								<td class="td_1"  style="width: 124px;"><span class="td_1">录入姓名/名称：</span></td>
								<td class="td_2" colspan="4">&nbsp;<span class="td_2" style="width:300px ">${dydjYwsbspb.lrxm}</span></td>
							</tr>
							<tr>
								<td width="120"  height="52" class="td_1">录入时间：</td>
								<td  class="td_2"> &nbsp;&nbsp;<s:date name="#request.dydjYwsbspb.lrsj" format="yyyy-MM-dd HH:mm:ss"/></td>
								<td class="td_1"  style="width: 124px;"><span class="td_1">录入IP：</span></td>
								<td class="td_2" colspan="4">&nbsp;<span class="td_2" style="width:300px ">${dydjYwsbspb.lrip}</span></td>
							</tr>
					  </table>
					 </s:if>
					
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
					 <s:if test="#request.editType=='查看' && #request.dydjYwsbspb.dyrSfzBz != null && #request.dydjYwsbspb.dyrSfzBz != ''">
					 	<table width="796" id="yjrxx" class="table">
					 		<tr>
								<td height="52" colspan="2" class="td_3" bgcolor="#abd3ff">抵押人未读卡原因</td>
							</tr>
							<tr>
								<td class="td_1" height="52">抵押人身份证未读卡原因：</td>
							  	<td style="width: 620px"  class="td_2">
							  		&nbsp;${dydjYwsbspb.dyrSfzBz}
							  	</td>
							</tr>
							<tr>
								<td  class="td_2" style="text-align: center;">证件图片</td>
								<td  class="td_2" style="text-align: left;">
									&nbsp;<a id="gpytp1" href="<%=request.getContextPath()%>/dydj/yhsl_showImage.action?id=${dydjYwsbspb.dyrSfzCardphotoId}" title="证件图片"><img src="<%=request.getContextPath()%>/bank/bank_showImage.action?id=${dydjYwsbspb.dyrSfzCardphotoId}" id="gpytp_a" width="210" height="175" border="0"></a>
								</td>
							 </tr>
					 	</table>
					 </s:if>
					<div class="tablelr">
					<table width="1160" class="table" id="zjxxdiv">
					 <tr>
					 	<td colspan="2" style="text-align: center; background: #6ba0da; font-size: 16px; width: 794px;"><strong>抵押权人</strong></td>
					 	<td id="tddyr" colspan="2" style="text-align: center;background: #6b8ada; font-size: 16px;"><strong>抵押人</strong></td>
					 </tr>
					 <tr>
	                   <td style="vertical-align: top; width: 398px;">
			                 <!-- 组织机构证 -->
						  	<table width="100%" class="table">
							  <tr>
							    <td height="52"  colspan="2" class="td_3" bgcolor="#6ba0da">银行组织机构代码证</td>
							  </tr>
							    <tr id="yhorgcomtr"> 
							    <td width="30%" align="right">
							    	<select id="ddlOrgCom" name="ddlOrgCom">
										<option value="">
											请选择设备接口
										</option>
										<option value="1001">
											USB1
										</option>
										<option value="1002">
											USB2
										</option>
										<option value="1003">
											USB3
										</option>
										<option value="1004">
											USB4
										</option>
										<option value="1005">
											USB5
										</option>
										<option value="1006">
											USB6
										</option>
										<option value="1007">
											USB7
										</option>
										<option value="1008">
											USB8
										</option>
										<option value="1009">
											USB9
										</option>
										<option value="1">
											COM1
										</option>
										<option value="2">
											COM2
										</option>
										<option value="3">
											COM3
										</option>
										<option value="4">
											COM4
										</option>
										<option value="5">
											COM5
										</option>
										<option value="6">
											COM6
										</option>
										<option value="7">
											COM7
										</option>
										<option value="8">
											COM8
										</option>
										<option value="9">
											COM9
										</option>
								  </select></td>	
							    <td width="70%">
							    	&nbsp;
							    	<input type="button" id="zzjgz"	value="获取信息" onclick="return btnGetOrgInfo_onclick()" class="bnt">
							    	&nbsp;&nbsp;<span id="ReadResultj" style="color: red;">等待读取...</span>
							    </td>
								</tr>
								<tr>
									<td style="text-align: right;">组织机构证代码：</td>
									<td>
										&nbsp;
										<input type="text" readonly="readonly" class="disabled"	id="yhZzjgZh" name="dydjYwsbspb.yhZzjgZh" value="${dydjYwsbspb.yhZzjgZh}" size="30" />
									</td>
								</tr>
								<tr>
									<td style="text-align: right;">单位法人：</td>
									<td>
										&nbsp;
										<input type="text" readonly="readonly" class="disabled"	id="yhZzjgFrdb" name="dydjYwsbspb.yhZzjgFrdb" value="${dydjYwsbspb.yhZzjgFrdb}" size="30" />
									</td>
								</tr>
								<tr>
									<td style="text-align: right;">营业执照：</td>
									<td>
										&nbsp;
										<input type="text" readonly="readonly" class="disabled" id="yhZzjgYyzz" name="dydjYwsbspb.yhZzjgYyzz" value="${dydjYwsbspb.yhZzjgYyzz}" size="30" />
									</td>
								</tr>
								<tr>
									<td style="text-align: right;">单位名称：</td>
									<td>
										&nbsp;
										<input type="text" readonly="readonly" class="disabled"	id="yhZzjgDwmc" name="dydjYwsbspb.yhZzjgDwmc"  value="${dydjYwsbspb.yhZzjgDwmc}" size="30" />
										<object id="Videoui1" height="0" width="0" classid="clsid:1EED6A72-4FA5-4F8A-B561-D892C6AFE027"></object>
									</td>
								</tr>
								<tr>
									<td style="text-align: right;">&nbsp;年检日期：</td>
									<td>
										&nbsp;
										<input type="text" readonly="readonly" class="disabled"	id="yhZzjgNjrq" name="dydjYwsbspb.yhZzjgNjrq" value="${dydjYwsbspb.yhZzjgNjrq}" size="30" />
									</td>
								</tr>
								<tr>
									<td style="text-align: right;">&nbsp;年检有效期：</td>
									<td>
										&nbsp;
										<input type="text" readonly="readonly" class="disabled"	id="yhZzjgNjyxq" name="dydjYwsbspb.yhZzjgNjyxq" value="${dydjYwsbspb.yhZzjgNjyxq}" size="30" /></td>
								</tr>
								<tr>
									<td style="text-align: right;">&nbsp;地址：</td>
									<td>
										&nbsp;
										<input type="text" readonly="readonly" class="disabled"
											id="yhZzjgDz" name="dydjYwsbspb.yhZzjgDz" value="${dydjYwsbspb.yhZzjgDz}" size="30" />
									</td>
								</tr>
							</table>
					   </td>
				 
				 <!-- 银行经办人-->
				 <td style="vertical-align: top; padding-top: 2px; width: 385px;">
					<table id="yhtablesfz" width="100%" class="table" border="0" cellpadding="0" cellspacing="0" align="center">
								<tr>
								    <td colspan="3" bgcolor="#6ba0da" style="border-top: 0px; height: 32px; text-align: center;">&nbsp;<strong>经办人信息</strong>&nbsp;
									</td>
								 </tr>
								 <tr>
								    <td colspan="3" style="border-top: 0px; height: 32px;">&nbsp;&nbsp;<input type="button" id="sfz_b1" style="cursor:pointer;" value="读 卡" onclick="return ReadIDCard1('1');" class="bnt">
									&nbsp;&nbsp;<span id="ReadResult1" style="color: red;">等待读取...</span>
									</td>
								 </tr>
								<tr>
									<td width="104" style="text-align: right; height: 32px;">姓名：</td>
									<td>
										&nbsp;
										<input type="text" class="disabled" readonly="readonly"
										size="18" id="yhSfzCardname" name="dydjYwsbspb.yhSfzCardname" value="${dydjYwsbspb.yhSfzCardname}"/>
								   </td>
									<td width="110" rowspan="4" style="text-align: center;">
										<s:if test="#request.editType != '查看'">
											<img id="sfzxpimgid1" src="<%=request.getContextPath()%>/images/cp.gif" width="108" height="120" border="0">
										</s:if>
										<s:else>
											<s:if test="#request.dydjYwsbspb.yhSfzCardphotoId == null || #request.dydjYwsbspb.yhSfzCardphotoId == ''">
												<img id="sfzxpimgid1" src="<%=request.getContextPath()%>/images/cp.gif" width="108" height="120" border="0">
											</s:if>	
											<s:else>
												<img id="sfzxpimgid1" src="<%=request.getContextPath()%>/dydj/yhsl_showImage.action?id=${dydjYwsbspb.yhSfzCardphotoId}" width="108" height="120" border="0">
											</s:else>
										</s:else>
											
									</td>
								</tr>
								<tr>
									<td style="text-align: right; height: 32px;">性别：</td>
									<td>
										&nbsp;
										<input type="text" class="disabled" readonly="readonly"
										size="18" id="yhSfzCardsex" name="dydjYwsbspb.yhSfzCardsex" value="${dydjYwsbspb.yhSfzCardsex}"/>
									</td>
								</tr>
								<tr>
									<td style="text-align: right; height: 32px;">身份证号：</td>
									<td>
										&nbsp;
										<input type="text" class="disabled" readonly="readonly"
										size="18" id="yhSfzCardno" name="dydjYwsbspb.yhSfzCardno" value="${dydjYwsbspb.yhSfzCardno}"/>
									</td>
								</tr>
								<tr>
									<td style="text-align: right; height: 32px;">地址：</td>
									<td>
										&nbsp;
										<input type="text" class="disabled" readonly="readonly"
											size="18" id="yhSfzCardaddress" name="dydjYwsbspb.yhSfzCardaddress" value="${dydjYwsbspb.yhSfzCardaddress}"/>
											<OBJECT classid="clsid:10946843-7507-44FE-ACE8-2B3483D179B7"
														codebase="CVR100.cab#version=3,0,3,3" id="CVR_IDCard"
														name="CVR_IDCard" width="108" height="110" align="middle"
														hspace="0" vspace="0" style="display: none;"></OBJECT>
									</td>
								</tr>
							    <tr id="filetr">
									<td style="text-align: right; height: 32px;">照片路径：</td>
									<td colspan="2">
									&nbsp;
									<input type="file" name="file1" class="disabled" id="file1" title="请选择银行经办人身份证相片路径:C:\\dydj1\\zp.bmp" style="width:215px;"/></td>
						  		</tr>
					  </table>
					 </td>
				 <!-- 抵押人（经办人信息） -->
				 <td style="vertical-align: top; padding-top: 2px;" id="dyrsfzjxx">
					<table id="dbrtablesfz" class="table" border="0" cellpadding="0" cellspacing="0" align="center">
								<tr>
								    <td colspan="3" bgcolor="#6b8ada" style="border-top: 0px; height: 32px; text-align: center;">&nbsp;<strong>经办人信息</strong>&nbsp;
									</td>
								 </tr>
								 <tr>
								    <td colspan="3" style="border-top: 0px; height: 32px;">&nbsp;&nbsp;<input type="button" id="sfz_b2" style="cursor:pointer;" value="读 卡" onclick="return ReadIDCard('1');" class="bnt">
									&nbsp;&nbsp;<span id="ReadResult" style="color: red;">等待读取...</span>
									</td>
								 </tr>
								<tr>
									<td width="104" style="text-align: right; height: 32px;">姓名：</td>
									<td>
										&nbsp;
										<input type="text" class="disabled" readonly="readonly"
										size="18" id="dyrSfzCardname" name="dydjYwsbspb.dyrSfzCardname" value="${dydjYwsbspb.dyrSfzCardname}"/>
								  </td>
									<td width="110" rowspan="4" style="text-align: center;">
										<s:if test="#request.editType != '查看'">
											<img id="sfzxpimgid" src="<%=request.getContextPath()%>/images/cp.gif" width="108" height="120" border="0">
										</s:if>
										<s:else>
											<s:if test="#request.dydjYwsbspb.dyrSfzCardphotoId == null || #request.dydjYwsbspb.dyrSfzCardphotoId == ''">
												<img id="sfzxpimgid" src="<%=request.getContextPath()%>/images/cp.gif" width="108" height="120" border="0">
											</s:if>	
											<s:else>
												<img id="sfzxpimgid" src="<%=request.getContextPath()%>/dydj/yhsl_showImage.action?id=${dydjYwsbspb.dyrSfzCardphotoId}" width="108" height="120" border="0">
											</s:else>
										</s:else>
											
									</td>
								</tr>
								<tr>
									<td style="text-align: right; height: 32px;">性别：</td>
									<td>
										&nbsp;
										<input type="text" class="disabled" readonly="readonly"
										size="18" id="dyrSfzCardsex" name="dydjYwsbspb.dyrSfzCardsex" value="${dydjYwsbspb.dyrSfzCardsex}"/>
									</td>
								</tr>
								<tr>
									<td style="text-align: right; height: 32px;">身份证号:</td>
									<td>
										&nbsp;
										<input type="text" class="disabled" readonly="readonly"
										size="18" id="dyrSfzCardno" name="dydjYwsbspb.dyrSfzCardno" value="${dydjYwsbspb.dyrSfzCardno}"/>
									</td>
								</tr>
								<tr>
									<td style="text-align: right; height: 32px;">地址：</td>
									<td>
										&nbsp;
										<input type="text" class="disabled" readonly="readonly"
											size="18" id="dyrSfzCardaddress" name="dydjYwsbspb.dyrSfzCardaddress" value="${dydjYwsbspb.dyrSfzCardaddress}"/>
									</td>
								</tr>
							    <tr id="filetr1">
									<td style="text-align: right; height: 32px;">照片路径：</td>
									<td colspan="2">
									&nbsp;
									<input type="file" name="file" class="disabled" id="file" title="请选择经办人身份证相片路径:C:\\dydj\\zp.bmp" style="width:215px;"/></td>
						  </tr>
					  </table>
					 </td>
				 
				 <td style="display: none; vertical-align: top;" id="gszzjgzxx">
				 	<!-- 组织机构证 -->
				 	<table width="100%" class="table">
					  <tr>
					    <td height="52"  colspan="2" class="td_3" bgcolor="#6b8ada">公司组织机构代码证</td>
					  </tr>
					    <tr id="dyrorgcomtr">
					    <td align="right">
					    	<select id="dbrddlOrgCom" name="dbrddlOrgCom">
								<option value="">
									请选择设备接口
								</option>
								<option value="1001">
									USB1
								</option>
								<option value="1002">
									USB2
								</option>
								<option value="1003">
									USB3
								</option>
								<option value="1004">
									USB4
								</option>
								<option value="1005">
									USB5
								</option>
								<option value="1006">
									USB6
								</option>
								<option value="1007">
									USB7
								</option>
								<option value="1008">
									USB8
								</option>
								<option value="1009">
									USB9
								</option>
								<option value="1">
									COM1
								</option>
								<option value="2">
									COM2
								</option>
								<option value="3">
									COM3
								</option>
								<option value="4">
									COM4
								</option>
								<option value="5">
									COM5
								</option>
								<option value="6">
									COM6
								</option>
								<option value="7">
									COM7
								</option>
								<option value="8">
									COM8
								</option>
								<option value="9">
									COM9
								</option>
							</select></td>	
					    <td>
					    	&nbsp;
					    	<input type="button" id="zzjgz"	value="获取信息" onclick="return btnGetOrgInfo_onclick2()" class="bnt">
					    	&nbsp;&nbsp;<span id="ReadResultj2" style="color: red;">等待读取...</span>
					    </td>
						</tr>
						<tr>
							<td style="text-align: right;">组织机构证代码：</td>
							<td>
								&nbsp;
								<input type="text" readonly="readonly" class="disabled"
									id="dyrZzjgZh" name="dydjYwsbspb.dyrZzjgZh" value="${dydjYwsbspb.dyrZzjgZh}" size="30" />
							</td>
						</tr>
						<tr>
							<td style="text-align: right;">单位法人：</td>
							<td>
								&nbsp;
								<input type="text" readonly="readonly" class="disabled"
									id="dyrZzjgFrdb" name="dydjYwsbspb.dyrZzjgFrdb" value="${dydjYwsbspb.dyrZzjgFrdb}" size="30" />
							</td>
						</tr>
						<tr>
							<td style="text-align: right;">营业执照：</td>
							<td>
								&nbsp;
								<input type="text" readonly="readonly" class="disabled"
									id="dyrZzjgYyzz" name="dydjYwsbspb.dyrZzjgYyzz" value="${dydjYwsbspb.dyrZzjgYyzz}" size="30" />
							</td>
						</tr>
						<tr>
							<td style="text-align: right;">单位名称：</td>
							<td>
								&nbsp;
								<input type="text" readonly="readonly" class="disabled"
										id="dyrZzjgDwmc" name="dydjYwsbspb.dyrZzjgDwmc" value="${dydjYwsbspb.dyrZzjgDwmc}" size="30" />
							</td>
						</tr>
						<tr>
							<td style="text-align: right;">&nbsp;年检日期：</td>
							<td>
								&nbsp;
								<input type="text" readonly="readonly" class="disabled"
										id="dyrZzjgNjrq" name="dydjYwsbspb.dyrZzjgNjrq" value="${dydjYwsbspb.dyrZzjgNjrq}" size="30" />
							</td>
						</tr>
						<tr>
							<td style="text-align: right;">&nbsp;年检有效期：</td>
							<td>
								&nbsp;
								<input type="text" readonly="readonly" class="disabled"
										id="dyrZzjgNjyxq" name="dydjYwsbspb.dyrZzjgNjyxq" value="${dydjYwsbspb.dyrZzjgNjyxq}" size="30" />
							</td>
						</tr>
						<tr>
							<td style="text-align: right;">&nbsp;地址：</td>
							<td>
								&nbsp;
								<input type="text" readonly="readonly" class="disabled"
									id="dyrZzjgDz" name="dydjYwsbspb.dyrZzjgDz" value="${dydjYwsbspb.dyrZzjgDz}" size="30" />
							</td>
						</tr>
					</table>
				 </td>
				  </tr>
				</table>
		  </div>
			
		   <div style="clear:both; width:796px; height:30px; text-align:center; margin-top:20px;">
		 	 <input type="button" style="cursor:pointer;" onclick="javascript:window.close();" value="关 闭" class="bnt" />
		  </div>
		  </div>		  
		    <div class="moveTxt" id="pzdiv">
				<div class="moveDiv" id="moveEndPort">
				&nbsp;
					<div style="float:left; width:150px; padding-left: 220px;">摄像区</div>
					<div style="float:right; width:180px;text-align: right; padding-right: 20px;">
						<a href="javascript: void(0)" onclick="closeDiv('pzdiv');" style="width: 30px; font-size: 16px;">X</a>
					</div>
				</div>
				<div style="height: 5px;"></div>
					<div id="tab" style="text-align: center; padding-left: 20px; padding-right: 20px;">
						<table class="tptable" border="0" cellpadding="0" cellspacing="0">
							  <tr>
							  	<td style="width: 30%; text-align: right; vertical-align: middle;" >抵押人身份证未读卡原因：</td>
							  	<td style="width: 70%; text-align: left;">
							  		&nbsp;<input type="text" id="dyrSfzBz" name="dydjYwsbspb.dyrSfzBz" style="width: 230px;"/>
							  	</td>
							  </tr>
							  <tr>
								<td height="33" colspan="2">&nbsp;&nbsp;<strong>照片信息</strong>
									&nbsp;<input type="button" style="cursor:pointer;" name="zp" id="zp" value="抓拍图片" class="bnt" onclick="vide();">
									 &nbsp;&nbsp;<span id="ReadResult2" style="color: red;">等待抓拍...</span>
								</td>
							  </tr>
							  <tr>
								<td style="width: 30%" align="center">证件拍摄区</td>
								<td style="width: 70%" align="center">证件图片</td>
							  </tr>
							  <tr>
								<td style="width: 30%" height="169" align="center">
									<object id="ScanCtrl" classid="clsid:090457CB-DF21-41EB-84BB-39AAFC9E271A"  CODEBASE="*.cab#version=1,0,0,1" width="220" height="180"></object>
								</td>
								<td style="width: 70%" align="center">
									<a id="gpytp" href="#" title="高拍仪图片"><img src="<%=request.getContextPath()%>/images/cp.gif" id="gpytp_a" width="210" height="175" border="0"></a>
								</td>
							   </tr>
							  <tr>
								<td style="width: 50%" width="116" align="center">
									&nbsp;
								</td>
								<td style="width: 50%" width="102" align="center">
									<input type="file" name="file02" id="file02" class="disabled" title="请选择高拍仪图片路径:C:\\printzj.jpg" style="width:210px;" />
								</td>
							  </tr>
							</table>
					</div>
					<div style="padding-top: 10px; text-align: center"">
					</div>
			</div>
			
		</form>
	</div>
	<div style="position: absolute; z-index: 100; border: 0px solid #b3b4b2; width: 200px; height: 220px; left: 850px; top:40px;" >
		<a id="img01a" href="<%=request.getContextPath()%>/dydj/yhsl_showUserImage.action?yzyhdm=${dydjYwsbspb.lrzh}" title="银行印章"><img  src="<%=request.getContextPath()%>/dydj/yhsl_showUserImage.action?yzyhdm=${dydjYwsbspb.lrzh}" border="0" title="银行印章" width="180" height="200"/></a>
	</div>
</body>
</html>