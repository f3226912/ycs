<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<title>代办人员备案管理-启用/停用</title>

<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/jquery-1.8.2.js"></script>
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
		width:70px;
		color: red;
		font-size: 12px;
		float: left;
	}
</style>
</head>
<script type="text/javascript">   
	//代办人员xh
	var xhAndZhzt = window.dialogArguments;
	var xh="";
	var zhzt="";
	
	$(function(){
		
		function inital(){
			if(xhAndZhzt==null || xhAndZhzt==""){
				alert("无法获取要操作的代办人员序号");
			}else{
				var tempStr=xhAndZhzt.split(",");
				if(tempStr!=null && tempStr.length==2){
					xh=$.trim(tempStr[0]);
					zhzt=$.trim(tempStr[1]);
					if(zhzt=="T"){
						$("#qyzh").attr("checked","checked");
					}else if(zhzt=="F"){
						$("#tyzh").attr("checked","checked");
					}
				}else{
					alert("无法获取要操作的代办人员序号");
				}
			}
		}
		
		inital();
		
		$("#saveButton").click(function(){
			var zhztRadio=$(":radio:checked");
			var bz=$.trim($("#bz").val());
			var tempBz=decodeURI(bz);
			if(xh!=null && xh!=""){
				if(zhztRadio.length==1){
					var zhzt=zhztRadio.val();
					$.ajax({
						url: "<%=request.getContextPath()%>/gjgg/gjgg_updateGgjgDbryByCzlx.action",
			  			type:'post',
			  			cache: false,
			  			async: true,
			  			dataType:'text',
			  			data: {
				  			'xh':xh,
				  			'zhzt':zhzt,
				  			'bz':tempBz,
				  			'czlx':'qy'
				  		},
			  			error:function(){
				  			alert("请求异常,请重试!");
				  		},
			  			success:function(data){
				  			var result=$.trim(data);
				  			var index=result.indexOf("-");
				  			if(index!=0 ){
				  				alert("操作成功!");
				  				window.close();
								window.returnValue="success";
					  		}else{
						  		alert(result.substring(1,result.length));
						  	}
			  			}
					});
				}else{
					alert("请选择账号状态");
				}
			}else{
				alert("无法获取要操作的代办人员序号");
			}
		});
		
		$("#cancleButton").click(function(){
			window.close();
		});
		
		
	})
	
</script>
	<body>
	  <div style="width:100%;" >
	  
	    <table class="datalist" width="100%" border="0" cellpadding="0" cellspacing="0">
			  <tr>
			    <td class="tds1" style="text-align: right;width:80px;">账号状态：</td>
			    <td class="tds2" style="text-align: left;width:160px;">
			    	<input id="qyzh" type="radio" name="zhzt" value="T" />启用
			    	<input id="tyzh" type="radio" name="zhzt" value="F" />停用
			    </td>
			  </tr>
			  <tr>
			    <td class="tds1" style="text-align: right;width:80px;">备注：</td>
			    <td class="tds2" style="text-align: left;width:80px;">
			    	<textarea id="bz" rows="5" cols="30"></textarea>
			    </td>
			  </tr>
			  <tr>
			    <td style="text-align:center;" colspan="2">
			    	 <input type="button" id="saveButton" class="bnt" style="width: 80px;" value="保存" />
			    	 <input type="button" id="cancleButton" class="bnt" style="width: 80px;"  value="返回" />
			    </td>
			  </tr>

		</table>
	
	   </div>

	</body>
</html>