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
<title>代办人员备案管理</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/content.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/jquery-1.8.2.js"></script>
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
			$("#xm").val("");
			$("#sfzmhm").val("");
			$("#shzt").val("");
		});
		
		
		$(".cz").click(function(){
			var ggjgid=$.trim('${request.ggjgid}');
			var xh=$(this).attr("xh");
			var czlx=$(this).attr("czlx");
			if(xh!=null && xh!=""){
				if(czlx=="sh" ){
					var uri='<%=request.getContextPath()%>' +"/pages/gjgggl/xxbaspgl/dbrybagl_sh.jsp";
					var returnValue=window.showModalDialog(uri,xh,"dialogWidth:400px;dialogHeight:250px;help:no;status:no;scroll:no;location:no;resizable:yes");
					if(returnValue!=null && returnValue!="0" && returnValue!="" && typeof(returnValue)!="undefined"){
						if(returnValue=="success"){
							window.location.href="<%=request.getContextPath()%>/gjgg/gjgg_ggjgDbrInital.action?ggjgid="+ggjgid;
						}
					}
				}else if(czlx=="qy" || czlx=="ty"){
					
					var zhzt="";
					if(czlx=="qy"){
						zhzt="T";
					}else{
						zhzt="F";
					}
					var tempStr=xh+","+zhzt;
				
					var uri='<%=request.getContextPath()%>' +"/pages/gjgggl/xxbaspgl/dbrybagl_qyty.jsp";
					var returnValue=window.showModalDialog(uri,tempStr,"dialogWidth:400px;dialogHeight:250px;help:no;status:no;scroll:no;location:no;resizable:yes");
					if(returnValue!=null && returnValue!="0" && returnValue!="" && typeof(returnValue)!="undefined"){
						if(returnValue=="success"){
							//alert(ggjgid);
							window.location.href="<%=request.getContextPath()%>/gjgg/gjgg_ggjgDbrInital.action?ggjgid="+ggjgid;
						}
					}
				}
				
			}else{
				alert("获取不到当前操作代办人员的xh,无法操作数据");	
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
	
	function reHistory(){
		window.location.href="<%=request.getContextPath()%>/gjgg/gjgg_ggjgInital.action?isFirstQuery=true";
	}
	
</script>
	<body>
	 <form id="queryForm" name="queryForm" action="<%=request.getContextPath()%>/gjgg/gjgg_ggjgDbrInital.action" method="post">
		<div style="width:95%;margin: 0 auto;">
		   <table  class="table1"  width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
			  <tr>
			    <td class="tds" style="text-align: right;">代办人员名称：</td>
			    <td class="tds" style="text-align: left;">
			    	<input type="text" name="xm" id="xm" value="${request.xm}" />
			    </td>
			  </tr>
			  <tr>
			  	 <td class="tds" style="text-align: right;">身份证明号码：</td>
			    <td class="tds" style="text-align: left;">
			    	<input type="text" name="sfzmhm" id="sfzmhm" value="${request.sfzmhm}" />
			    </td>
			  </tr>
			  <tr>
			  	 <td class="tds" style="text-align: right;">广告机构：</td>
			    <td class="tds" style="text-align: left;">
			    	<s:select id="ggjgid"
			    			name="ggjgid"
				    		theme="simple"
							list="#request.busGgjgBases"
							listKey="ggjgid" 
							listValue="ggjgmc"
							headerKey="" 
							headerValue="--请选择广告机构--"
							value="#request.ggjgid"
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
			    	<input type="button" onclick="reHistory();" class="bnt" style="width: 80px;" value="返  回"/>
			    </td>
			  </tr>
		  </table> 
		  </div>
		 </form>		   
		 <div style="width:95%; margin-top: 10px;margin: 0 auto;" >		  
		    <table class="datalist" id="tableList" width="100%" border="0" cellpadding="0" cellspacing="0">
			  <tr class="tr1">
			    <th>姓名</th>
			    <th>身份证明号码</th>
			    <th>所属广告机构</th>
			    <th>电话</th>
			    <th>地址</th>
			    <th>审核状态</th>
			    <th>账号状态</th>
			    <th>备注</th>
			    <th>操作</th>
			  </tr>
			  <s:if test='#request.busGgjgdlrs!=null && #request.busGgjgdlrs.size>0 '>
			  	<s:iterator value="#request.busGgjgdlrs" var="tempDbr">
			  	 <tr>
			  		<td>${tempDbr.xm}</td>
			  		<td>${tempDbr.sfzmhm}</td>
			  		<td>${tempDbr.ggjgmc}</td>
			  		<td>${tempDbr.lxdh}</td>
			  		<td>${tempDbr.zzdz}</td>
			  		<td>
			  			<s:if test='#tempDbr.shzt=="0" '>未审核</s:if>
			  			<s:if test='#tempDbr.shzt=="1" '>审核通过</s:if>
			  			<s:if test='#tempDbr.shzt=="2" '>审核不通过</s:if>
			  		</td>
			  		<td>
			  			<s:if test='#tempDbr.zt=="T" '>启用</s:if>
			  			<s:if test='#tempDbr.zt=="F" '>停用</s:if>
			  		</td>
			  		<td>
			  			<s:if test='#tempDbr.bz!=null && #tempDbr.bz.length()>8 '>
			  				<label title="${tempDbr.bz}">
			  					<s:property value="#tempDbr.bz.substring(0,8)+'......' "/>
			  				</label>
			  			</s:if>
			  			<s:else>
			  				${tempDbr.bz}
			  			</s:else>
			  		</td>
			  		<td>
			  			<s:if test='#tempDbr.shzt=="0" '>
			  				<a class="cz" czlx="sh" xh="${tempDbr.xh}">审核</a>
			  			</s:if>
			  			<s:elseif test='#tempDbr.shzt=="1" '>
			  				<s:if test='#tempDbr.zt=="F" '>
			  					<a class="cz" czlx="qy" xh="${tempDbr.xh}">启用</a>
			  				</s:if>
			  				<s:if test='#tempDbr.zt=="T" '>
			  					<a class="cz" czlx="ty" xh="${tempDbr.xh}">停用</a>
			  				</s:if>
			  			</s:elseif>
			  			<s:else>
			  				---
			  			</s:else>
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
		
</body>
</html>