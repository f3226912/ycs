<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<title>${request.type==1?"修改":"添加"}验车管理项</title>

<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/jquery-1.8.2.js"></script>
<LINK media=screen href="<%=request.getContextPath()%>/js/gjgggl/jQuery.files/jquery-ui-1.7.1.custom.css" type=text/css rel=stylesheet>
<LINK media=screen href="<%=request.getContextPath()%>/js/gjgggl/jQuery.files/jquery.gzoom.css" type=text/css rel=stylesheet>
<SCRIPT src="<%=request.getContextPath()%>/js/gjgggl/jQuery.files/ui.core.min.js" type=text/javascript></SCRIPT>
<SCRIPT src="<%=request.getContextPath()%>/js/gjgggl/jQuery.files/ui.slider.min.js" type=text/javascript></SCRIPT>
<SCRIPT src="<%=request.getContextPath()%>/js/gjgggl/jQuery.files/jquery.mousewheel.js" type=text/javascript></SCRIPT>
<SCRIPT src="<%=request.getContextPath()%>/js/gjgggl/jQuery.files/jquery.gzoom.js" type=text/javascript></SCRIPT>
<SCRIPT type=text/javascript>
     $(function() {
        $(".zoom_no_lbox").gzoom({
            sW: 470,
            sH: 250,
            lW: 1410,
            lH: 750,
            lightbox: false
        });
      });
</SCRIPT>
<style> 
	html{ 
		overflow:scroll;
		scrollbar-base-color:#c7e5ff;
		scrollbar-track-color:#FFFFFF;
	}
	
	.datalist {
		border: 1px solid #0058a3;
		font-family: Arial, Helvetica, sans-serif;
		border-collapse: collapse;
		/*background-color: #eaf5ff;*/
		font-size: 14px;
	}
	
	.datalist tr {
		height: 32px;
	}
	
	.datalist tr.altrow {
		/*background-color: #c7e5ff;*/
		height: 32px;
	}
	
	.datalist td {
		border: 1px solid #3c7eba;
		text-align: center;
		padding-top: 2px;
		padding-bottom: 2px;
		padding-left: 5px;
		overflow: hidden;
		font-size: 12px;
	}
	
	.datalist th {
		border: 1px solid #3c7eba;
		height: 32px;
	}
	
	.tds1 {
		background: #eaf5ff;
		font-weight: bold;
		font-size: 14px;
	}
	
	.tds2 {
		font-weight: bold;
		font-size: 14px;
	}
	
	.bnt {
		width: 76px;
		height: 27px;
		background: url(<%=request.getContextPath()%>/images/an2.gif) no-repeat;
		border: none;
		font-weight: bold;
	}
	
	.error{
		width:160px;
		color: #941305;
		font-size: 12px;
	}
	.span{ width: 100px; border: 1px solid #eaf5ff;}
	ul{ margin-left: -20px;}
	ul li{ border:0px solid red; float:left; width:275px; height: 20px;  list-style-type: none; }
</style>


<script type="text/javascript">   

	$(function(){
		$(".dis").attr("disabled","disabled");
		
		
		//是否全检
		$("#dmsm2").change(function(){
			var id = $("#dmsm2").val();
			if(id==1){//全选
				$('[name=ids1]:checkbox').attr('checked',true);
			}else{
				$('[name=ids1]:checkbox').attr('checked',false);
			}
			
			
		});
		//是否全拍
		$("#dmsm4").change(function(){
			var id = $("#dmsm4").val();
			if(id==1){//全选
				$('[name=ids2]:checkbox').attr('checked',true);
			}else{
				$('[name=ids2]:checkbox').attr('checked',false);
			}
		});


		//返回
		$("#cancelButton").click(function(){
			window.close();
		});	
		
		//判断代码值是否存在
		$("#dmz").change(function(){
			//Ajax判断代码值在该类型库中是否存在！
			var dmlb = $("#dmlb1").val();
			var dmz = $("#dmz").val();
			$.ajax({
				cache:false,
				async:false,
				type:"POST",
				url:"<%=request.getContextPath()%>/yanche/yczd_existsBydmz.action",
				data:{dmlb:dmlb,dmz:dmz},
				dataType: 'text',
				success:function(data){
					if(data==1){
						$("#message").html("代码值已经存在!");
					}else{
						$("#message").html("");
					}
				}
			 });
		});
	});
	
	//提交修改
	function cleck(type){
		var dmsm = $("#dmsm").val();
		var codeid = $("#codeid").val();
		var dmz = $("#dmz").val();
		var zt = $("#zt").val();
		var yeType = "xzOrlx";
		var dmsm2 = $("#dmsm2").val();
		var message = $("#message").html();
		var dmsm1Count = $('[name=ids1]:checkbox:checked').length;
		var dmsm1 ="";	
		$('[name=ids1]:checkbox:checked').each(function(i){
			  dmsm1 += $(this).val();
			  if(i+1<dmsm1Count){
			  	dmsm1 +=",";
			  }
		});
		var dmsm4 = $("#dmsm4").val();
		var dmsm3Count = $('[name=ids1]:checkbox:checked').length;
		var dmsm3="";
		$('[name=ids2]:checkbox:checked').each(function(i){
			  dmsm3 += $(this).val();
			  if(i+1<dmsm3Count){
			  	dmsm3 +=",";
			  }
		});
		var dmsx = $("#dmsx").val();
		if(dmz==null||dmz==""){
			alert("类型名不能为空!");
			$("#dmz").focus();
			return;
		}else{
			if(type==2&&message!=""){			
				alert("代码值已经存在!");
				return;
			}
		}
		if(dmsm==""||dmsm==null){
			alert("标识码不能为空!");
			$("#dmsm").focus();
			return;
		}else{
			if(type==1){//修改
				$.ajax({
					cache:false,
					async:false,
					type:"POST",
					url:"<%=request.getContextPath()%>/yanche/yczd_saveOrupdateSubmit.action",
					data:{codeid:codeid,dmz:dmz,zt:zt,dmsm2:dmsm2,dmsm1:dmsm1,dmsm4:dmsm4,
						dmsm3:dmsm3,dmsx:dmsx,dmsm:dmsm,type:type,yeType:yeType},
					dataType: 'text',
					success:function(data){
						if(data==0){
							alert("修改成功!");
							window.close();
							window.returnValue="success";
						}else{
							alert("系统繁忙，修改失败,请稍后在试!");
							return;
						}
					}
				});
			}else if(type==2){//添加
				var dmlb = $("#dmlb1").val();
				$.ajax({
					cache:false,
					async:false,
					type:"POST",
					url:"<%=request.getContextPath()%>/yanche/yczd_saveOrupdateSubmit.action",
					data:{ dmz:dmz,dmlb:dmlb,zt:zt,dmsm2:dmsm2,dmsm1:dmsm1,dmsm4:dmsm4,
						dmsm3:dmsm3,dmsx:dmsx,dmsm:dmsm,type:type,yeType:yeType},
					dataType: 'text',
					success:function(data){
						if(data==0){
							alert("添加成功!");
							window.close();
							window.returnValue="success";
						}else{
							alert("系统繁忙，添加失败,请稍后在试!");
							return;
						}
					}
				});
			}
			
		}
	}	
		
		
		
</script>
</head>
	<body>
		  <div style="width:100%;" >
		  <form action="<%=request.getContextPath()%>/yanche/yczd_updateSubmit.action" id="sub_form" method="post">
		  	<input type="hidden" id="codeid" value="${yanche.codeId}">
	    	<input type="hidden" id="dmlb" value="${yanche.dmlb}">
	    	<input type="hidden" id="lbms" value="${yanche.lbms}">
	    	<input type="hidden" id="dmlb1" value="${request.dmlb}">
	    	<input type="hidden" id="userid" value="${userbean.name}">
	    	
		    <table class="datalist" width="100%" border="0" cellpadding="0" cellspacing="0">
				  <tr>
				    <td class="tds1" style="text-align: right;width:80px;">标识码：</td>
				    <td class="tds2" style="text-align: left;width:300px;">
				    <s:if test="#request.type==1">
				    	<input type="text" id="dmz" name="dmz" value="${yanche.dmz}" disabled="disabled"/>
				    </s:if>
				    <s:else>
				    	<input type="text" id="dmz" name="dmz" value="${yanche.dmz}"/>
				    </s:else>
				    <span id="message" style="color: red; font-size: 12px; " ></span>	
				    </td>
				    <td class="tds1" style="text-align: right;width:80px;">状态：</td>
				    <td class="tds2" style="text-align: left;width:240px;">
				    	<s:if test='#session.userbean.name=="jst"'>
				  		<s:select theme="simple"  id="zt" name="zt" list="#{'1':'正常','0':'隐藏'}" value="#request.yanche.zt"></s:select>
				  		</s:if>
				  		<s:else>
				  		<s:select theme="simple" class="dis" id="zt" name="zt" list="#{'1':'正常','0':'隐藏'}" value="#request.yanche.zt"></s:select>
				  		</s:else>
				    </td>
				  </tr>
				  <tr>
				  	<td class="tds1" style="text-align: right;width:80px;">类型名：</td>
				  	<td class="tds2" style="text-align: left;width:80px;">
				  		<input <s:if test='#request.type=="1"'>readonly="readonly"</s:if> type="text" id="dmsm" value="${yanche.dmsm}">
				  	</td>
				  	<td class="tds1" style="text-align: right;width:80px;">操作权限：</td>
				  	
				  	<td class="tds2" style="text-align: left;width:240px;">
				  	<s:if test='#session.userbean.name=="jst"'>
				  		<s:select theme="simple"  id="dmsx" name="dmsx" list="#{'1':'允许修改','0':'不许修改'}" value="#request.yanche.dmsx"></s:select>
				  	</s:if>
				  	<s:else>
						<s:select theme="simple" class="dis" id="dmsx" name="dmsx" list="#{'1':'允许修改','0':'不许修改'}" value="#request.yanche.dmsx"></s:select>
				    </s:else>
				    </td>
				  </tr>
				  <tr>
				  	<td class="tds1" style="text-align: right;width:80px;">是否全检：</td>
				    <td class="tds2" colspan="3" style="text-align: left;width:240px;">
						<s:select theme="simple" id="dmsm2" name="dmsm2" list="#{'0':'不全检','1':'全检'}" value="#request.yanche.dmsm2"></s:select>
				    </td>
				  </tr>
				  <tr>
				  	<td class="tds1" style="text-align: right;width:80px;">检查项：</td>
				    <td class="tds2" colspan="3" style="text-align: left;">
				    	<ul>
		    			<s:iterator id="obj" value="#request.cyxListAll">
		    				<li>
		    				<input type="checkbox" name="ids1" class="st"
		    						<s:iterator id="cx" value="#request.cyx">
			    						<s:if test="#obj[0]==#cx">
			    							checked="checked" 
			    						</s:if>
		    						</s:iterator>
		    						value="<s:property value="#obj[0]"/>"/><s:property value="#obj[1]"/>
		    				</li>
						</s:iterator>
						</ul>
				    </td>
				  </tr>
				  
				  <tr>
				  	<td class="tds1" style="text-align: right;width:80px;">是否全拍：</td>
				    <td class="tds2" colspan="4" style="text-align: left;width:240px;">
						<s:select theme="simple" id="dmsm4" list="#{'0':'不全拍','1':'全拍'}" value="#request.yanche.dmsm4"></s:select>
				    </td>
				  </tr>
				  <tr>
				  	<td class="tds1" style="text-align: right;width:80px;">拍照项：</td>
				    <td class="tds2" colspan="3" style="text-align: left;">
				    	<ul>
						<s:iterator id="obj" value="#request.pzxListAll">
							<li>
		    				<input type="checkbox" name="ids2"
		    						<s:iterator id="pz" value="#request.pzx">
			    						<s:if test="#obj[0]==#pz">
			    							checked="checked" 
			    						</s:if>
		    						</s:iterator>
		    						value="<s:property value="#obj[0]"/>"/><s:property value="#obj[1]"/>
							</li>
						</s:iterator>
						</ul>
				    </td>
				  </tr>
				  <tr>
				  	<td colspan="4">
				  	<s:if test='#request.yanche.dmsx=="0"'>
				  		<s:if test='#session.userbean.name=="jst"'>
				  		<input type="button" id="click" class="bnt" style="width: 80px;" onclick="javascript:cleck(${request.type})"  value="确定" />
				  		</s:if>
				  	</s:if>
				  	<s:else>
				  		<input type="button" id="click" class="bnt" style="width: 80px;" onclick="javascript:cleck(${request.type})"  value="确定" />
				  	</s:else>
				  	<input type="button" id="cancelButton" class="bnt" style="width: 80px;"  value="返回" /></td>
				  </tr>
			</table>
		</form>
		   </div>

</body>
<script type="text/javascript">  

</script>
</html>