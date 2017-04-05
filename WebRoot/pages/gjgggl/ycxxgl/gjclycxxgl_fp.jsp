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
<title>业务审核分配</title>
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

	var mac="";
	//权限
	var qx=$.trim('${request.qx}');

	$(function(){
		
		$("#resetButton").click(function(){
			$("#yyycbm").val("");
			$("#ggjgid").val("");
			$("#yypch").val("");
			$("#hphm").val("");
		});
		
		//按批次批量分配
		$("#plshByPc").click(function(){
			var pc=$(".pch_ :checkbox:checked");
			var qx=$.trim(${request.qx});
			if(pc!=null && pc.length>0){
				var pcList=sperator(pc);
				var uri='<%=request.getContextPath()%>' +"/gjgg/ycgg_getInitalUpdateDataByPch_fp.action";
				var returnValue=window.showModalDialog(uri,pcList,"dialogWidth:360px;dialogHeight:200px;help:no;tatus:no;scroll:no;location:no;resizable:yes");
				if(returnValue!=null && returnValue!="0" && returnValue!="" && typeof(returnValue)!="undefined"){
					if(returnValue=="success"){
						window.location.href="<%=request.getContextPath()%>/gjgg/ycgg_getycxxInital_tp.action?qx="+qx;
					}
				}
			}else{
				alert("请选择要分配的批次");
			}
		});
		
		function shHelp(){
			var pc=$(".plsh_total:checked");
			var qx=$.trim(${request.qx});
			if(pc!=null && pc.length>0){
				var pcList=sperator(pc);
				var uri='<%=request.getContextPath()%>' +"/gjgg/ycgg_getInitalUpdateDataByPch_fp.action";
				var returnValue=window.showModalDialog(uri,pcList,"dialogWidth:360px;dialogHeight:200px;help:no;status:no;scroll:no;location:no;resizable:yes");
				if(returnValue!=null && returnValue!="0" && returnValue!="" && typeof(returnValue)!="undefined"){
					if(returnValue=="success"){
						window.location.href="<%=request.getContextPath()%>/gjgg/ycgg_getycxxInital_tp.action?qx="+qx;
					}
				}
			}else{
				alert("请选择要分配的批次");
			}
		}
		
		
		$(".cz").click(function(){
			
			$(this).css("color","red");
			$(".cz").not($(this)).css("color","blue");
			
			var yypch=$(this).attr("yypch");
			var czlx=$(this).attr("czlx");
			if(yypch!=null && yypch!=""){
				if(czlx=="bmtp" || czlx=="show" ){
					
					$(".plsh_total").removeAttr("checked");
					$("#pch_"+yypch).attr("checked","checked");
					
					var pc=$(".plsh_total:checked");
					var ycbm=$("#pch_"+yypch).attr("ycbm");
					var qx=$.trim(${request.qx});
					if(pc!=null && pc.length>0){
						var pcList=sperator(pc);
						var uri='<%=request.getContextPath()%>' +"/gjgg/ycgg_getycxxInital_tp_pch.action?yypch="+pcList+"&ycbm="+ycbm;
						var returnValue=window.showModalDialog(uri,czlx,"dialogWidth:800px;dialogHeight:600px;help:no;status:no;scroll:yes;location:no;resizable:yes");
						if(returnValue!=null && returnValue!="0" && returnValue!="" && typeof(returnValue)!="undefined"){
							if(returnValue=="success"){
								window.location.href="<%=request.getContextPath()%>/gjgg/ycgg_getycxxInital_tp.action?qx="+qx;
							}
						}
					}else{
						alert("请选择要分配的批次");
					}
				}
			}else{
				alert("获取不到当前预约批次号,无法操作数据");	
			}
			
		});
		
		
		$("#selectAll").click(function(){
			var isSelectAll=$(this).attr("checked");
			if(isSelectAll=="checked" || isSelectAll=="true" ){
				$(".plsh_total").attr("checked","checked");
			}else{
				$(".plsh_total").removeAttr("checked");
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
		
		
	})
	
</script>
	<body>
		<div style="width:95%;margin: 0 auto;">
			 <form id="queryForm" name="queryForm" action="<%=request.getContextPath()%>/gjgg/ycgg_getycxxInital_tp.action" method="post">
				   <table  class="table1"  width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
					  <tr>
					    <td class="tds" style="text-align: right;">预约车管部门：</td>
					    <td class="tds" style="text-align: left;">
					    	<s:select id="yyycbm" name="yyycbm"
					    		theme="simple" 
								list="#request.ycglbmMap" 
								listKey="key" 
								listValue="value"
								headerKey="" 
								headerValue="--请选择预约车管部门--"
								value="#request.yyycbm"
								>
							</s:select>
							<input type="hidden" name="qx" value="${request.qx}" />
					    </td>
					  </tr>
					  <tr>
					  	 <td class="tds" style="text-align: right;">广告机构：</td>
					    <td class="tds" style="text-align: left;">
					    	<s:select id="ggjgid" name="ggjgid"
					    		theme="simple"
								list="#request.ggjgMap" 
								listKey="key" 
								listValue="value"
								headerKey="" 
								headerValue="--请选择广告机构--"
								value="#request.ggjgid"
								>
							</s:select>
					    </td>
					  </tr>
					  <tr>
					  	 <td class="tds" style="text-align: right;">申请批次查询：</td>
					    <td class="tds" style="text-align: left;">
					    	<input type="text" name="yypch" id="yypch" value="${request.yypch}" />
					    </td>
					  </tr>
					  <tr>
					  	 <td class="tds" style="text-align: right;">号牌号码：</td>
					    <td class="tds" style="text-align: left;">
					    	<input type="text" name="hphm" id="hphm" value="${request.hphm}" />
					    </td>
					  </tr>
					  
					  <tr>
					  	<td colspan="2"> 
					    	<input type="submit" class="bnt" style="width: 80px;" id="queryButton" value="查  询" />
					    	<input type="button" class="bnt" style="width: 80px;" id="resetButton" value="重  置"/>
					    </td>
					  </tr>
				  </table>
			   </form>
		  </div>
		  
		  <div style="width:95%; margin-top: 10px;margin: 0 auto;" >
		  
		   	<div>
		   		<input type="button" id="plshByPc" class="bnt" style="width: 80px;" value="部门调配" />
		   	</div>
		    <table class="datalist" id="tableList" width="100%" border="0" cellpadding="0" cellspacing="0">
			  <tr class="tr1">
			    <th><label style="font-size: 11px;">全选<input id="selectAll" type="checkbox" value="all" /></label> 申请批次号</th>
			    <th>代办机构</th>
			    <th>预约车管部门</th>
			    <th>已审核数量/ 批次总数量</th>
			    <th>状态</th>
			    <th>操作</th>
			  </tr>
			  <s:if test='#request.busGgjgclsbTotal!=null && #request.busGgjgclsbTotal.size>0 '>
			  	<s:iterator value="#request.busGgjgclsbTotal" var="tempSbTotal">
			  	<tr class="pch_">
			  		<td>
			  			<input type="checkbox" id="pch_${tempSbTotal.yypch}" ycbm="${tempSbTotal.yyycbm}"  class="plsh_total" value="${tempSbTotal.yypch}"/>${tempSbTotal.yypch}
			  		</td>
			  		<td>${tempSbTotal.ggjgmc}</td>
			  		<td>${tempSbTotal.yyycbmmc}</td>
			  		<td>
			  			${tempSbTotal.yyshsl}/${tempSbTotal.yypcsl}
			  		</td>
			  		<td>
			  			<s:if test='#tempSbTotal.yypcsl == #tempSbTotal.yyshsl '>已审核</s:if>
			  			<s:if test='#tempSbTotal.yypcsl > #tempSbTotal.yyshsl '>未审核</s:if>
			  		</td>
			  		<td>
	  					<a class="cz" czlx="show" yypch="${tempSbTotal.yypch}">查看</a>
		  				<a class="cz" czlx="bmtp" yypch="${tempSbTotal.yypch}">部门调配</a>
			  		</td>
			  	 </tr>
			  	</s:iterator>
			  </s:if>
			  <s:else>
			  	<tr>
			  		<td style="color: red;text-align: center;" colspan="6">查无数据</td>
			  	</tr>
			  </s:else>
			</table>
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				style="padding-top: 5px;">
				<pt:write uri="${map.uri}" pagesize="${map.pageSize}" rscount="${map.rscount}" currentpage="${map.currentPage}"></pt:write>
			</table>
			
			
		   </div>
		
</body>
</html>