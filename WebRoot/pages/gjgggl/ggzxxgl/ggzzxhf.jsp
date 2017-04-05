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
<title>广告证注销/恢复 </title>
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
			$("#lsh").val("");
		});
		
		
		$(".cz").click(function(){
			var lsh=$(this).attr("lsh");
			var czlx=$(this).attr("czlx");
			if(lsh!=null && lsh!=""){
				//查看广告证
				if(czlx=="show"){
					var uri='<%=request.getContextPath()%>' +"/gjgg/ggz_getBusGgjgclsbByLsh.action?lsh="+lsh;
					var returnValue=window.showModalDialog(uri,"","dialogWidth:800px;dialogHeight:650px;help:no;status:no;scroll:no;location:no;resizable:yes");
					if(returnValue!=null && returnValue!="0" && returnValue!="" && typeof(returnValue)!="undefined"){
						if(returnValue=="success"){
							window.location.href="<%=request.getContextPath()%>/gjgg/ggz_getZxOrHfData.action";
						}
					}
				//注销或恢复广告证
				}else if(czlx=="zx" || czlx=="hf"){
					var showMessage="";
					if(czlx=="zx"){
						showMessage="确定注销该广告证?";
					}else{
						showMessage="确定恢复该广告证?";
					}
					if(confirm(showMessage)){
						$.ajax({
							url: "<%=request.getContextPath()%>/gjgg/ggz_zxOrfhBusCertifyInfo.action",
				  			type:'post',
				  			cache: false,
				  			async: true,
				  			dataType:'text',
				  			data: {
					  			'lsh':lsh,
					  			'czlx':czlx,
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
					  				window.location.href="<%=request.getContextPath()%>/gjgg/ggz_getZxOrHfData.action";
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
	 <form id="queryForm" name="queryForm" action="<%=request.getContextPath()%>/gjgg/ggz_getZxOrHfData.action" method="post">
		<div style="width:95%;margin: 0 auto;">
		   <table  class="table1"  width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
			  <tr>
			    <td class="tds" style="text-align: right;">广告证号(流水号)：</td>
			    <td class="tds" style="text-align: left;">
			    	<input type="text" name="lsh" id="lsh" value="${request.lsh}" />
			    	<input type="hidden" name="qx" id="qx" value="${request.qx}" />
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
			  	<th>流水号</th>
			    <th>号牌号码</th>
			    <th>号牌种类</th>
			    <th>车辆类型</th>
			    <th>车辆型号</th>
			    <th>制证时间</th>
			    <th>公交公司</th>
			    <th>状态</th>
			    <th>操作</th>
			  </tr>
			  <s:if test='#request.busCertifyInfos!=null && #request.busCertifyInfos.size>0 '>
			  	<s:iterator value="#request.busCertifyInfos" var="tempBusCertifyInfo">
				  	<tr>
				  		<td>${tempBusCertifyInfo.lsh}</td>
				  		<td>${tempBusCertifyInfo.hphm}</td>
				  		<td>${tempBusCertifyInfo.hpzl}</td>
				  		<td>${tempBusCertifyInfo.cllx}</td>
				  		<td>${tempBusCertifyInfo.clxh}</td>
				  		<td>
				  			<s:date name="#tempBusCertifyInfo.zzrq" format="yyyy-MM-dd HH:mm:ss"/>
				  		</td>
				  		<td>${tempBusCertifyInfo.gjgsid}</td>
				  		<td>
				  			<s:if test='#tempBusCertifyInfo.zt=="A" '>正常</s:if>
				  			<s:if test='#tempBusCertifyInfo.zt=="R" '>注销</s:if>
				  			<s:if test='#tempBusCertifyInfo.zt=="F" '>超期注销</s:if>
				  		</td>
				  		<td>
				  			<a class="cz" czlx="show" lsh="${tempBusCertifyInfo.lsh}">查看</a>&nbsp;
				  			<s:if test='#tempBusCertifyInfo.zzmjbmKj==null || #tempBusCertifyInfo.zzmjbmKj!="true" '>
					  			<s:if test='#tempBusCertifyInfo.zt=="A" '>
					  				<a class="cz" czlx="zx" lsh="${tempBusCertifyInfo.lsh}">注销</a>
					  			</s:if>
					  			<s:if test='#tempBusCertifyInfo.zt=="R"  '>
					  				<a class="cz" czlx="hf" lsh="${tempBusCertifyInfo.lsh}">恢复</a>
					  			</s:if>
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