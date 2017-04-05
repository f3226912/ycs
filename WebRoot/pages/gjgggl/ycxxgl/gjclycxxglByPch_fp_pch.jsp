<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<title>调配审核部门</title>

<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/jquery-1.8.2.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/content.css" />
<style> 
	html{ 
		overflow:scroll;
		scrollbar-base-color:#c7e5ff;
		scrollbar-track-color:#FFFFFF;
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
	
	//要审核的批次号集合
	var pcList= window.dialogArguments;
	
	var mac="";
	
	$(function(){
		
		function inital(){
			if(pcList==null || pcList=="" || pcList.length<=0){
				alert("无法获取要分配的批次号");
			}
		}
		
		inital();
		
		$("#saveButton").click(function(){
			
			if(typeof(pcList)!="undefined" && pcList!=null && pcList!=""){
				var ycbm=$("#ycbm").val();
				if(ycbm==null || ycbm==""){
					alert("请选择验车部门!");
					return false;
				}
				var pch=initalData(pcList);
				$.ajax({
					url: "<%=request.getContextPath()%>/gjgg/ycgg_updateBusGgjgclsbByPch_fp.action",
		  			type:'post',
		  			cache: false,
		  			async: true,
		  			dataType:'text',
		  			data: {
			  			'pchList':pch,
			  			'ycbm':ycbm,
			  			'mac':mac
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
				alert("无法获取要分配的批次号");
			}
		});
		
		function initalData(obj){
			var result="";
			if(typeof(obj)!="undefined" && obj!=null ){
				for(var i=0;i<obj.length;i++){
					if(i!=obj.length-1){
						result=result+obj[i]+"-";
					}else{
						result=result+obj[i];
					}
				}
			}
			return result;
		}
		
		$("#cancleButton").click(function(){
			window.close();
		});
		
		
		//获取mac地址
	    function getMac() {
		     mac="";
	 		 try{
	 		    var locator =new ActiveXObject ("WbemScripting.SWbemLocator");
	 		    var service = locator.ConnectServer(".");
	 		    var properties = service.ExecQuery("Select * from Win32_NetworkAdapterConfiguration Where IPEnabled =True");       
	 		    var e =new Enumerator (properties);       
	 				{
	 					var p = e.item();  
	 					mac=p.MACAddress;
	 				}
	 		}catch(e){
	 			mac='0.0.0.0.0';
	 		}
	 		mac=$.trim(mac);
	 	}
	
	 	//初始化mac地址
	    getMac();
		
	})
	
</script>
	<body>
	  <div style="width:100%;margin-top: 2px;" >
		
		<table style="margin-top: 5px;" class="datalist" id="tableList" width="100%" border="0" cellpadding="0" cellspacing="0">
			  <tr>
			  	<td style="text-align: left;">
			  		验车部门：
			  		<s:select id="ycbm" name="ycbm"
			    		theme="simple" 
						list="#request.ycbmMap" 
						listKey="key" 
						listValue="value"
						headerKey="" 
						headerValue="--请选择验车部门--"
						value="#request.ycbm"
						>
					</s:select>
			  	</td>
			  </tr>
			  <tr>
			  	<td>
			  		<input type="button" id="saveButton" class="bnt" style="width: 80px;" value="部门调配" />
			  		<input type="button" id="cancleButton" class="bnt" style="width: 80px;" value="返回" />
			  	</td>
			  </tr>
		</table>
			
	
	   </div>

	</body>
</html>