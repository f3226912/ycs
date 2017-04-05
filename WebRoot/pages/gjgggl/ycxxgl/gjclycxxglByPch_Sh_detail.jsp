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
<title>业务审核-详细</title>
<base target="_self">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/content.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/jquery-1.8.2.js"></script>
<style>
	html{
		overflow:scroll;
		scrollbar-base-color:#c7e5ff;
		scrollbar-track-color:#FFFFFF;
	}
	a{
		cursor: pointer;
		color: blue;
		text-decoration: underline;
	}
</style>
</head>
<script type="text/javascript">	


	var czlx = window.dialogArguments;

	var mac="";
	//权限
	var qx=$.trim('${request.qx}');
	
	//是否允许审核
	var isAllowSh="true";

	$(function(){
		
		$(".copyContent").dblclick(function(){
			var contentStr=$.trim($(this).html());
			var sepArea=$(this).attr("sepArea");
			var WshShell=new ActiveXObject("WScript.Shell");
			var data="";
			if(sepArea=="true"){
				data=$.trim($(this).attr("data"));
			}else{
				data=$.trim($(this).html());
			}
			window.clipboardData.setData('text', data);
			
		});
		
		$("#resetButton").click(function(){
			$("#zzjgdm").val("");
			$("#ggjgmc").val("");
		});
		
		//按批次批量审核
		$("#plshByPc").click(function(){
			shHelp();
		});
		
		function shHelp(){
			var pc=$(".plsh_total:checked");
			if(pc!=null && pc.length>0){
				var pcList=sperator(pc);
				var uri='<%=request.getContextPath()%>' +"/gjgg/ycgg_InitalUpdateDataByPch.action";
				var returnValue=window.showModalDialog(uri,pcList,"dialogWidth:360px;dialogHeight:250px;help:no;status:no;scroll:no;location:no;resizable:yes");
				if(returnValue!=null && returnValue!="0" && returnValue!="" && typeof(returnValue)!="undefined"){
					if(returnValue=="success"){
						window.close();
		  				window.returnValue="success";
						//window.location.href="<%=request.getContextPath()%>/gjgg/ycgg_getYcxxInital.action?qx="+qx;
					}
				}
			}else{
				alert("请选择要审核的批次");
			}
		}
		
		//按流水号批量审核
		$("#plshByLsh").click(function(){

			var lsh=$(".plsh_single:checked");
			if(lsh!=null && lsh.length>0){
				var lshList=sperator(lsh);
				var uri='<%=request.getContextPath()%>' +"/gjgg/ycgg_InitalUpdateDataByLsh.action";
				var returnValue=window.showModalDialog(uri,lshList,"dialogWidth:400px;dialogHeight:300px;help:no;status:no;scroll:no;location:no;resizable:yes");
				if(returnValue!=null && returnValue!="0" && returnValue!="" && typeof(returnValue)!="undefined"){
					if(returnValue=="success"){
						window.close();
						window.returnValue="success";
						//window.location.href="<%=request.getContextPath()%>/gjgg/ycgg_getYcxxInital.action?qx="+qx;
					}
				}
			}else{
				alert("请选择要审核的流水数据");
			}
		});
		
		
		
		
		$("#selectAll").click(function(){
			var isSelectAll=$(this).attr("checked");
			if(isSelectAll=="checked" || isSelectAll=="true" ){
				$(".plsh_single").attr("checked","checked");
			}else{
				$(".plsh_single").removeAttr("checked");
			}
		});
		
		
		
		$(".single_sh").click(function(){
			
			$(this).css("color","red");
			$(".single_sh,.show").not($(this)).css("color","blue");
			
			var lsh=$(this).attr("lsh");
			if(lsh!=null && lsh!="" ){
				var uri='<%=request.getContextPath()%>' +"/gjgg/ycgg_getBusGgjgclsbByLsh.action?lsh="+lsh;
				var returnValue=window.showModalDialog(uri,lsh,"dialogWidth:800px;dialogHeight:720px;help:no;status:no;scroll:no;location:no;resizable:yes");
				if(returnValue!=null && returnValue!="0" && returnValue!="" && typeof(returnValue)!="undefined"){
					if(returnValue=="success"){
						window.close();
		  				window.returnValue="success";
						//window.location.href="<%=request.getContextPath()%>/gjgg/ycgg_getYcxxInital.action?qx="+qx;
					}
				}
			}else{
				alert("无法获取要审核的流水号");
			}
		});
		
		$(".show").click(function(){
			
			$(this).css("color","red");
			$(".single_sh,.show").not($(this)).css("color","blue");
			
			var lsh=$(this).attr("lsh");
			if(lsh!=null && lsh!="" ){
				var uri='<%=request.getContextPath()%>' +"/gjgg/ycgg_getBusGgjgclsbByLsh.action?lsh="+lsh+"&isShow=true";
				window.showModalDialog(uri,lsh,"dialogWidth:800px;dialogHeight:720px;help:no;status:no;scroll:no;location:no;resizable:yes");
			}else{
				alert("无法获取要审核的流水号");
			}
		});
		
		function sperator(obj){
			var result = new Array();
			if(typeof(obj)!="undefined" && obj!=null && obj.length>0){
				for(var i=0;i<obj.size();i++){
					result[i]=$.trim($(obj.get(i)).val());
				}
			}
			return result;
		}
		
		
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
	 	
	 	
	 	function czlxHelp(){
	 		if(czlx=="show"){
	 			$("input").not($("#dy")).attr("disabled","disabled");
	 			$(".single_sh").hide();
	 		}
	 	}
	 	
	 	czlxHelp();
		
		
	})
	
	function dy(yypchParam,yyycbmParam){
		if(yypchParam!=null && yypchParam!=""){
			var uri='<%=request.getContextPath()%>' +"/gjgg/ycgg_getYcxxInitalByPch_dy.action?yypch="+yypchParam+"&yyycbm="+yyycbmParam;
			var returnValue=window.showModalDialog(uri,czlx,"dialogWidth:900px;dialogHeight:600px;help:no;status:no;scroll:no;location:no;resizable:yes");
		}else{
			alert("批次号为空,无法获取打印数据");
		}
	}
	
</script>
	<body>
	 
		<div id="showSingle" style="width:95%;margin: 0px auto;">
			<div class="tr1"  style="width: 100%;text-align: center;font-weight: bold;">
		   		审核部门：${request.yyycbmmc}
		   	</div>
			<div>
		   		<input type="button" id="plshByLsh" class="bnt" style="width: 80px;" value="批量审核" />
		   		<input type="button" id="dy" onclick="dy('${request.yypch}','${request.yyycbmmc}');"  class="bnt" style="width: 80px;" value="打印" />
		   	</div>
			<table class="datalist" id="tableList" width="100%" border="0" cellpadding="0" cellspacing="0">
			  <tr class="tr1">
			    <th><label style="font-size: 11px;">全选<input id="selectAll" type="checkbox" value="all" /></label> 流水号</th>
			    <th>号牌号码</th>
			    <th>号牌种类</th>
			    <th>申请批次号</th>
			    <th>广告审批号</th>
			    <th>预约审核部门</th>
			    <th>公交公司</th>
			    <th>状态</th>
			    <th>操作</th>
			  </tr>
			  <s:if test='#request.busGgjgclsbsSingle!=null && #request.busGgjgclsbsSingle.size>0 '>
			  	<s:iterator value="#request.busGgjgclsbsSingle" var="tempSbSingle">
			  	 <tr pch_single="${tempSbSingle.yypch}">
			  		<td class="copyContent" title="双击可复制该区域" sepArea="true" data="${tempSbSingle.lsh}">
			  			<s:if test='#request.tempSbSingle.zt!="E" && #request.tempSbSingle.zt!="CQ" && #request.tempSbSingle.zt!="Q" '>
				  			<input type="checkbox" class="plsh_single" value="${tempSbSingle.lsh}"/>
			  			</s:if>
			  			${tempSbSingle.lsh}
			  		</td>
			  		<td class="copyContent" title="双击可复制该区域" sepArea="false">${tempSbSingle.hphm}</td>
			  		<td class="copyContent" title="双击可复制该区域" sepArea="false">${tempSbSingle.hpzl}</td>
			  		<td class="copyContent" title="双击可复制该区域" sepArea="false">${tempSbSingle.yypch}</td>
			  		<td class="copyContent" title="双击可复制该区域" sepArea="false">${tempSbSingle.ggsph}</td>
			  		<td class="copyContent" title="双击可复制该区域" sepArea="false">${tempSbSingle.yyycbmmc}</td>
			  		<td class="copyContent" title="双击可复制该区域" sepArea="false">${tempSbSingle.gjgsid}</td>
			  		<td class="copyContent" title="双击可复制该区域" sepArea="false">${tempSbSingle.ztMc}</td>
			  		<td>
			  			<div class="singleHandle">
			  				<a class="show" lsh="${tempSbSingle.lsh}">查看</a> 
				  			<s:if test='#tempSbSingle.zt!="E" && #tempSbSingle.zt!="CQ" && #tempSbSingle.zt!="Q" '>
				  				<a class="single_sh" lsh="${tempSbSingle.lsh}">审核</a>
				  			</s:if>
			  			</div>
			  		</td>
			  	 </tr>
			  	</s:iterator>
			  </s:if>
			</table>
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				style="padding-top: 5px;">
				<pt:write uri="${map.uri}" pagesize="${map.pageSize}" rscount="${map.rscount}" currentpage="${map.currentpage}"></pt:write>
			</table>
		</div>
		
</body>
</html>