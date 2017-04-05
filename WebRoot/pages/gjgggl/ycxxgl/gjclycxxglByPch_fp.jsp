<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<title>调配审核部门-详细</title>

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
	
	//操作类型
	var czlx = window.dialogArguments;
	//要审核的批次号集合
	var pcList= $.trim("${request.yypch}");
	
	var mac="";
	
	$(function(){
		
		function czlxHelp(){
			if(czlx!=null && czlx!="" && czlx=="show"){
				$("#saveButton").attr("disabled","disabled");
				$("#saveButton").hide();
			}
		}
		
		czlxHelp();
		
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
				
				$.ajax({
					url: "<%=request.getContextPath()%>/gjgg/ycgg_updateBusGgjgclsbByPch_fp.action",
		  			type:'post',
		  			cache: false,
		  			async: true,
		  			dataType:'text',
		  			data: {
			  			'pchList':pcList,
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
				alert(obj.length);
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
	  
	    <div id="showSingle" >
	    	
			<table class="datalist" id="tableList" width="100%" border="0" cellpadding="0" cellspacing="0">
			  <tr>
		    	  <td colspan="9" class="tr1" style="font-weight: bold;" >
		    	  	申报数据调配-${request.ycbmmc}
		    	  </td>
		      </tr>
			  <tr class="tr1">
			    <th>流水号</th>
			    <th>号牌号码</th>
			    <th>号牌种类</th>
			    <th>批次号</th>
			    <th>广告审批号</th>
			    <th>预约验车部门</th>
			    <th>状态</th>
			    <th>预约上门验车时间</th>
			  </tr>
			  <s:if test='#request.busGgjgclsbsSingle!=null && #request.busGgjgclsbsSingle.size>0 '>
			  	<s:iterator value="#request.busGgjgclsbsSingle" var="tempSbSingle">
			  	 <tr pch_single="${tempSbSingle.yypch}">
			  		<td>
			  			<s:if test='#request.tempSbSingle.zt!="E" && #request.tempSbSingle.zt!="Q" '>
				  			<input type="hidden" class="plsh_single" value="${tempSbSingle.lsh}"/>
			  			</s:if>
			  			${tempSbSingle.lsh}
			  		</td>
			  		<td>${tempSbSingle.hphm}</td>
			  		<td>${tempSbSingle.hpzl}</td>
			  		<td>${tempSbSingle.yypch}</td>
			  		<td>${tempSbSingle.ggsph}</td>
			  		<td>${tempSbSingle.yyycbmmc}</td>
			  		<td>${tempSbSingle.ztMc}</td>
			  		<td>
			  			<s:date name="#tempSbSingle.yjsmycsj" format="yyyy-MM-dd HH:mm:ss" />
			  		</td>
			  		
			  	 </tr>
			  	</s:iterator>
			  </s:if>
			</table>
		</div>
		
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