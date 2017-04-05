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
</style>


<script type="text/javascript">   
	$(function(){

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
		var yeType = $("#yeType").val();
		var dmsm = $("#dmsm").val();
		var dmlb = $("#dmlb").val();
		var codeid = $("#codeid").val();
		var dmz = $("#dmz").val();
		var dmsx = $("#dmsx").val();
		var zt = $("#zt").val();
		var message = $("#message").html();
		if(dmz==""||dmz==null){
			alert("代码值不能为空!");
			$("#dmz").foucs();
			return;
		}else{
			//判断代码值是否存在!
			if(type==2&&message!=""){				
				alert("代码值已经存在!");
				return;
			}
		}
		if(dmsm==""||dmsm==null){
			alert("类型名不能为空!");
			$("#dmsm").foucs();
			return;
		}else{
			if(type==1){//修改
				$.ajax({
					cache:false,
					async:false,
					type:"POST",
					url:"<%=request.getContextPath()%>/yanche/yczd_saveOrupdateSubmit.action",
					data:{codeid:codeid,dmz:dmz,zt:zt,dmsx:dmsx,dmsm:dmsm,type:type},
					dataType: 'text',
					success:function(data){
						if(data==0){
							alert("修改成功!");
							window.close();
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
					data:{dmz:dmz,zt:zt,dmlb:dmlb,dmsx:dmsx,dmsm:dmsm,type:type},
					dataType: 'text',
					success:function(data){
						if(data==0){
							alert("添加成功!");
							window.close();
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
		  	<input type="hidden" id="yeType" value="${request.yeType}">
		  	<input type="hidden" id="codeid" value="${yanche.codeId}">
	    	<input type="hidden" id="dmlb" value="${yanche.dmlb}">
	    	<input type="hidden" id="lbms" value="${yanche.lbms}">
	    	<input type="hidden" id="dmlb1" value="${request.dmlb}">
	    	<input type="hidden" id="type" value="${request.type}">
		    <table class="datalist" width="100%" border="0" cellpadding="0" cellspacing="0">
				  <tr>
				    <td class="tds1" style="text-align: right;width:80px;">代码值：</td>
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
				    	<s:select theme="simple" id="zt" name="zt" list="#{'0':'隐藏','1':'正常'}" value="#request.yanche.zt"></s:select>
				    </td>
				  </tr>
				  
				  <tr>
				  	<td class="tds1" style="text-align: right;width:80px;">类型名：</td>
				  	<td class="tds2" style="text-align: left;width:80px;">
				  		<input type="text" id="dmsm" value="${yanche.dmsm}">
				  	</td>
				  	<td class="tds1" style="text-align: right;width:80px;">操作权限：</td>
				    <td class="tds2" style="text-align: left;width:240px;">
						<s:select theme="simple" id="dmsx" name="dmsx" list="#{'0':'不许修改','1':'允许修改'}" value="#request.yanche.dmsx"></s:select>
				    </td>
				  </tr>			  
				  <tr>
				  	<td colspan="4">
				  	<input type="button" id="click" class="bnt" style="width: 80px; " onclick="cleck(${request.type})"  value="确定" />
				  	<input type="button" id="cancelButton" class="bnt" style="width: 80px;"  value="返回" /></td>
				  </tr>
			</table>
		</form>
		   </div>

</body>
</html>