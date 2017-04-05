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
<title>广告机构备案管理</title>
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
			$("#ggjgmc").val("");
			$("#zzjgdm").val("");
			$("#zt").val("");
			$("#zhzt").val("");
		});
		
		
		$(".cz").click(function(){
			
			var ggjgid=$(this).attr("ggjgid");
			var zhzt=$(this).attr("zhzt");
			var czlx=$(this).attr("czlx");
			if(ggjgid!=null && ggjgid!=""){
				if(czlx=="sh"){
					var uri='<%=request.getContextPath()%>' +"/pages/gjgggl/xxbaspgl/ggjgbagl_sh.jsp";
					var returnValue=window.showModalDialog(uri,ggjgid,"dialogWidth:400px;dialogHeight:250px;help:no;status:no;scroll:no;location:no;resizable:yes");
					if(returnValue!=null && returnValue!="0" && returnValue!="" && typeof(returnValue)!="undefined"){
						if(returnValue=="success"){
							window.location.href="<%=request.getContextPath()%>/gjgg/gjgg_ggjgInital.action?isFirstQuery=true";
						}
					}
				}else{
					if(czlx!="dbr"){
						
						if(czlx=="stop"){
							if(zhzt=="T"){
								zhzt="F";
							}else{
								zhzt="T";
							}
							var tempStr=ggjgid+","+zhzt;
						
							var uri='<%=request.getContextPath()%>' +"/pages/gjgggl/xxbaspgl/ggjgbagl_qyty.jsp";
							var returnValue=window.showModalDialog(uri,tempStr,"dialogWidth:400px;dialogHeight:250px;help:no;status:no;scroll:no;location:no;resizable:yes");
							if(returnValue!=null && returnValue!="0" && returnValue!="" && typeof(returnValue)!="undefined"){
								if(returnValue=="success"){
									window.location.href="<%=request.getContextPath()%>/gjgg/gjgg_ggjgInital.action?isFirstQuery=&shzt=1";
								}
							}
							
						}else if(czlx=="initalPwd"){
							
							if(confirm("确定继续操作?")){
								$.ajax({
									url: "<%=request.getContextPath()%>/gjgg/gjgg_updateBusGgjgBaseByCzlx.action",
						  			type:'post',
						  			cache: false,
						  			async: true,
						  			dataType:'text',
						  			data: {
							  			'ggjgid':ggjgid,
							  			'czlx':czlx
							  		},
						  			error:function(){
							  			alert("请求异常,请重试!");
							  		},
						  			success:function(data){
							  			var result=$.trim(data);
							  			var index=result.indexOf("-");
							  			if(index!=0 ){
							  				if(czlx=="initalPwd"){
							  					//alert("操作成功,密码为:"+result);
							  					alert("操作成功");
							  				}else{
							  					alert("操作成功!");
							  				}
							  				
							  				window.location.href="<%=request.getContextPath()%>/gjgg/gjgg_ggjgInital.action?isFirstQuery=&shzt=1";
								  		}else{
									  		alert(result.substring(1,result.length));
									  	}
						  			}
								});
							}
							
						}
						
						
						
						return false;
						
						
					}else if(czlx=="dbr"){
						window.location.href="<%=request.getContextPath()%>/gjgg/gjgg_ggjgDbrInital.action?ggjgid="+ggjgid;
					}
				}
			}else{
				alert("获取不到当前操作车辆xh,无法操作数据");	
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
	 <form id="queryForm" name="queryForm" action="<%=request.getContextPath()%>/gjgg/gjgg_ggjgInital.action?isFirstQuery=" method="post">
		<div style="width:95%;margin: 0 auto;">
		   <table  class="table1"  width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
			  <tr>
			    <td class="tds" style="text-align: right;">广告机构名称：</td>
			    <td class="tds" style="text-align: left;">
			    	<input type="text" name="ggjgmc" id="ggjgmc" value="${request.ggjgmc}" />
			    </td>
			  </tr>
			  <tr>
			  	 <td class="tds" style="text-align: right;">组织机构代码：</td>
			    <td class="tds" style="text-align: left;">
			    	<input type="text" name="zzjgdm" id="zzjgdm" value="${request.zzjgdm}" />
			    </td>
			  </tr>
			  <tr>
			    <td class="tds" style="text-align: right;">状态：</td>
			    <td class="tds" style="text-align: left;">
			    	<select id="zt" name="shzt">
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
			    <th>机构ID</th>
			    <th>机构名称</th>
			    <th>组织机构代码</th>
			    <th>负责人</th>
			    <th>地址</th>
			    <th>电话</th>
			    <th>所属公交公司</th>
			    <th>审核状态</th>
			    <th>账户状态</th>
			    <th>备注</th>
			    <th>操作</th>
			  </tr>
			  <s:if test='#request.busGgjgBases!=null && #request.busGgjgBases.size>0 '>
			  	<s:iterator value="#request.busGgjgBases" var="tempGgjg">
			  	 <tr>
			  		<td>${tempGgjg.ggjgid}</td>
			  		<td>${tempGgjg.ggjgmc}</td>
			  		<td>${tempGgjg.zzjgdm}</td>
			  		<td>${tempGgjg.zzr}</td>
			  		<td>${tempGgjg.dz}</td>
			  		<td>${tempGgjg.lxdh}</td>
			  		<td>${tempGgjg.gjgsmc}</td>
			  		<td>
			  			<s:if test='#tempGgjg.shzt=="0" '>未审核</s:if>
			  			<s:if test='#tempGgjg.shzt=="1" '>审核通过</s:if>
			  			<s:if test='#tempGgjg.shzt=="2" '>审核不通过</s:if>
			  		</td>
			  		<td>
			  			<s:if test='#tempGgjg.zt=="T" '>启用</s:if>
			  			<s:if test='#tempGgjg.zt=="F" '>停用</s:if>
			  		</td>
			  		<td>
			  			<s:if test='#tempGgjg.bz!=null && #tempGgjg.bz.length()>8 '>
			  				<label title="${tempGgjg.bz}">
			  					<s:property value="#tempGgjg.bz.substring(0,8)+'......' "/>
			  				</label>
			  			</s:if>
			  			<s:else>
			  				${tempGgjg.bz}
			  			</s:else>
			  		</td>
			  		<td>
			  			<s:if test='#tempGgjg.shzt=="0"  '>
			  				<a class="cz" czlx="sh" ggjgid="${tempGgjg.ggjgid}">审核</a>
			  			</s:if>
			  			<s:elseif test='#tempGgjg.shzt=="1"  '>
			  				<s:if test='#tempGgjg.zt=="T" '>
			  					<a class="cz" czlx="initalPwd" ggjgid="${tempGgjg.ggjgid}">初始化密码</a>&nbsp;
			  					<a class="cz" czlx="stop" zhzt="${tempGgjg.zt}" ggjgid="${tempGgjg.ggjgid}">停用</a>&nbsp;
			  					<a class="cz" czlx="dbr" ggjgid="${tempGgjg.ggjgid}">代办人管理</a>
			  				</s:if>
			  				<s:else>
			  					<a class="cz" czlx="stop" zhzt="${tempGgjg.zt}" ggjgid="${tempGgjg.ggjgid}">启用</a>&nbsp;
			  				</s:else>
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
			  		<td style="color: red;text-align: center;" colspan="10">查无数据</td>
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