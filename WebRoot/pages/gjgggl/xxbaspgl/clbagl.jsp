<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="com.ycszh.common.PageTag" prefix="pt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<title>车辆备案管理</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/content.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/jquery-1.8.2.js"></script>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/My97DatePicker/WdatePicker.js"></script>
<style> 
	html{ 
		overflow:scroll;
		scrollbar-base-color:#c7e5ff;
		scrollbar-track-color:#FFFFFF;
	}
	.cz{
		cursor: pointer;
		color: blue;
		text-decoration: underline;
	}
</style>
</head>
<script type="text/javascript">   

	var mac="";

	$(function(){
		
		$("#resetButton").click(function(){
			$("#hphm").val("");
			$("#hpzl").val("");
			$("#shzt").val("");
		});
		
		
		$(".cz").click(function(){
			var xh=$(this).attr("xh");
			var czlx=$(this).attr("czlx");
			if(xh!=null && xh!=""){
				if(czlx!="" && czlx!="tb"){
					var uri='<%=request.getContextPath()%>' +"/gjgg/gjgg_getBusVehicleBase.action?xh="+xh+"&czlx="+czlx;
					var returnValue=null;
					if(czlx=="show"){
						returnValue=window.showModalDialog(uri,czlx,"dialogWidth:700px;dialogHeight:670px;help:no;status:no;scroll:no;location:no;resizable:yes");
					}else{
						returnValue=window.showModalDialog(uri,czlx,"dialogWidth:700px;dialogHeight:730px;help:no;status:no;scroll:no;location:no;resizable:yes");
					}
					if(returnValue!=null && returnValue!="0" && returnValue!="" && typeof(returnValue)!="undefined"){
						if(returnValue=="success"){
							window.location.href="<%=request.getContextPath()%>/gjgg/gjgg_clbaglInital.action?isFirstQuery=true";
						}
					}
				}else if(czlx=="tb"){
					if(confirm("确定要退办该车辆数据?")){
						$.ajax({
							url: "<%=request.getContextPath()%>/gjgg/gjgg_cancelBusVehicleBase.action",
				  			type:'post',
				  			cache: false,
				  			async: true,
				  			dataType:'text',
				  			data: {
					  			'xh':xh,
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
					  				window.location.href="<%=request.getContextPath()%>/gjgg/gjgg_clbaglInital.action?isFirstQuery=&shzt=1";
						  		}else{
							  		alert(result.substring(1,result.length));
							  	}
				  			}
						});
					}
				}
			}else{
				alert("获取不到当前操作车辆xh,无法进一步操作数据");	
			}
			
			
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
	 <form id="queryForm" name="queryForm" action="<%=request.getContextPath()%>/gjgg/gjgg_clbaglInital.action?isFirstQuery=" method="post">
		<div style="width:95%;margin: 0 auto;">
		   <table  class="table1"  width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
			  <tr>
			    <td class="tds" style="text-align: right;">号牌号码：</td>
			    <td class="tds" style="text-align: left;">
			    	<input type="text" name="hphm" id="hphm" value="${request.hphm}" />
			    </td>
			  </tr>
			  <tr>
			  	 <td class="tds" style="text-align: right;">号牌种类：</td>
			    <td class="tds" style="text-align: left;">
			    	<s:select id="hpzl" name="hpzl"
			    		theme="simple" 
						list="#request.hpzlMap" 
						listKey="key" 
						listValue="value"
						headerKey="" 
						headerValue="--请选择号牌种类--"
						value="#request.hpzl"
						>
					</s:select>
			    </td>
			  </tr>
			  <tr>
			    <td class="tds" style="text-align: right;">审核状态：</td>
			    <td class="tds" style="text-align: left;">
			    	<select id="shzt" name="shzt">
			    		
			    		<option value="" >--请选择审核状态--</option>
			    		<option value="0" <s:if test='#request.shzt=="0" '>selected="selected"</s:if>>未审核</option>
			    		<option value="1" <s:if test='#request.shzt=="1" '>selected="selected"</s:if>>审核通过</option>
			    		<option value="2" <s:if test='#request.shzt=="2" '>selected="selected"</s:if>>审核不通过</option>
			    	</select>
			    </td>
			  </tr>
			  <tr>
			  	<td colspan="2"> 
			    	<input type="submit" id="queryButton" class="bnt" style="width: 80px;" value="查  询" />
			    	<input type="button" id="resetButton" class="bnt" style="width: 80px;" value="重  置"/>
			    </td>
			  </tr>
		  </table> 
		  </div>
		  
		  <div style="width:95%; margin-top: 10px;margin: 0 auto;" >
		  
		    <table class="datalist" id="tableList" width="100%" border="0" cellpadding="0" cellspacing="0">
			  <tr class="tr1">
			    <th>号牌号码</th>
			    <th>号牌种类</th>
			    <th>车辆类型</th>
			    <th>机动车所有人</th>
			    <th>车架号码</th>
			    <th>审核状态</th>
			    <th>审核不通过原因</th>
			    <th>申报时间</th>
			    <th>操作</th>
			  </tr>
			  <s:if test='#request.busVehicleBases!=null && #request.busVehicleBases.size>0 '>
			  	<s:iterator value="#request.busVehicleBases" var="tempBus">
			  	<tr>
			  		<td>${tempBus.hphm}</td>
			  		<td>${tempBus.hpzl}</td>
			  		<td>${tempBus.cllx}</td>
			  		<td>${tempBus.jdcsyr}</td>
			  		<td>${tempBus.clsbdh}</td>
			  		<td>
			  			<s:if test='#tempBus.shzt=="0" '>未审核</s:if>
			  			<s:if test='#tempBus.shzt=="1" '>审核通过</s:if>
			  			<s:if test='#tempBus.shzt=="2" '>审核不通过</s:if>
			  		</td>
			  		<td>
			  			<s:if test='#tempBus.tbyy!=null && #tempBus.tbyy.length()>15 '>
			  				<label title="${tempBus.tbyy}">
			  					<s:property value="#tempBus.tbyy.substring(0,10)+'......' "/>
			  				</label>
			  			</s:if>
			  			<s:else>
			  				${tempBus.tbyy}
			  			</s:else>
			  		</td>
			  		<td>
			  			<s:date name="#tempBus.lrrq" format="yyyy-MM-dd HH:mm:ss"/>
			  		</td>
			  		<td>
			  			<a class="cz" czlx="show" xh="${tempBus.xh}">查看</a>&nbsp;
			  			<s:if test='#tempBus.shzt=="0"'>
			  				<a class="cz" czlx="sh" xh="${tempBus.xh}">审核</a>&nbsp;
			  			</s:if>
			  			<s:if test='#tempBus.shzt=="1" '>
			  				<a class="cz" czlx="tb" xh="${tempBus.xh}">退办</a>
			  			</s:if>
			  		</td>
			  		</tr>
			  	</s:iterator>
			  </s:if>
			  <s:else>
			  	<tr>
			  		<td style="color: red;text-align: center;" colspan="9">查无数据</td>
			  	</tr>
			  </s:else>
			</table>
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				style="padding-top: 5px;">
				<pt:write uri="${map.uri}" pagesize="${map.pagesize}" rscount="${map.rscount}" currentpage="${map.currentpage}"></pt:write>
			</table>
		   </div>
		 </form>
</body>
</html>