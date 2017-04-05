<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<title>添加公交公司</title>

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
	
	.tr1 {
		background: url(<%=request.getContextPath()%>/images/cxtjbj.gif) repeat;
		height: 20px;
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
	
	var mac="";

	$(function(){
		
		
		$("#addButton").click(function(){
			
			var gjgsmc=$.trim($("#gjgsmc").val());
			var zzjgdm=$.trim($("#zzjgdm").val());
			var gjgsdz=$.trim($("#gjgsdz").val());
			var zrr=$.trim($("#zrr").val());
			var lxdh=$.trim($("#lxdh").val());
			var gsgsjc=$.trim($("#gsgsjc").val());
			
			var gjgsmcStr=decodeURI(gjgsmc);
			var gjgsdzStr=decodeURI(gjgsdz);
			var zrrStr=decodeURI(zrr);
			var gsgsjcStr=decodeURI(gsgsjc);
			
			if(gjgsmc==null || gjgsmc==""){
				alert("请填写公交公司名称!");
				return false;
			}
			if(zzjgdm==null || zzjgdm==""){
				alert("请填写公交公司组织机构代码!");
				return false;
			}
			if(lxdh==null || lxdh==""){
				alert("请填写联系电话");
				return false;
			}
			if(gsgsjc==null || gsgsjc==""){
				alert("请填写简称");
				return false;
			}
			
			$.ajax({
				url: "<%=request.getContextPath()%>/gjgg/xtgl_addBusBase.action",
	  			type:'post',
	  			cache: false,
	  			async: true,
	  			dataType:'text',
	  			data: {
		  			'busBase.gjgsmc':gjgsmcStr,
		  			'busBase.zzjgdm':zzjgdm,
		  			'busBase.gjgsdz':gjgsdzStr,
		  			'busBase.zzr':zrrStr,
		  			'busBase.lxdh':lxdh,
		  			'busBase.gsgsjc':gsgsjcStr,
		  			'mac':mac
		  		},
	  			error:function(){
		  			alert("请求异常,请重试!");
		  		},
	  			success:function(data){
		  			var result=$.trim(data);
		  			var index=result.indexOf("-");
		  			if(index!=0 ){
		  				window.close();
						window.returnValue=result;
			  		}else{
				  		alert(result.substring(1,result.length));
				  	}
	  			}
			});
			
			
		});
		
		$("#cancelButton").click(function(){
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
		  <div style="width:100%;" >
		  
		    <table class="datalist" width="100%" border="0" cellpadding="0" cellspacing="0">
				  <tr>
		    	  	<td colspan="4" class="tr1" >添加公交公司</td>
		    	  </tr>
				  <tr>
				    <td class="tds1" style="text-align: right;width:100px;">公交公司名称：</td>
				    <td class="tds2" style="text-align: left;">
				    	<input type="text" name="gjgsmc" id="gjgsmc" maxlength="20"/>
				    </td>
				    <td class="tds1" style="text-align: right;width:100px">组织机构代码：</td>
				    <td class="tds2" style="text-align: left;">
				    	<input type="text" name="zzjgdm" id="zzjgdm" maxlength="50" />
				    </td>
				  </tr>
				  <tr>
				  	 <td class="tds1" style="text-align: right;width:100px">公交公司地址：</td>
				     <td class="tds2" style="text-align: left;">
				    	<input type="text" name="gjgsdz" id="gjgsdz" maxlength="150" />
				     </td>
				     <td class="tds1" style="text-align: right;width:100px">公司简称：</td>
				    <td class="tds2" style="text-align: left;">
				    	<input type="text" name="gsgsjc" id="gsgsjc" maxlength="50" />
				     </td>
				  </tr>
				  <tr>
				   <td class="tds1" style="text-align: right;width:100px">责任人：</td>
				    <td class="tds2" style="text-align: left;">
				    	<input type="text" name="zrr" id="zrr" maxlength="10" />
				     </td>
				    <td class="tds1" style="text-align: right;width:100px">联系电话：</td>
				     <td class="tds2" style="text-align: left;" colspan="3">
				    	<input type="text" name="lxdh" id="lxdh"  maxlength="30"/>
				     </td>
				  </tr>
				  <tr>
				    <td class="tds" style="text-align: center;" colspan="4">
					     <input id="addButton" type="button" class="bnt" style="width: 80px;" value="添加" />
					     <input id="cancelButton" type="button" class="bnt" style="width: 80px;" value="返回" />
				    </td>
				  </tr>
			   
			</table>
		
		   </div>

</body>
</html>