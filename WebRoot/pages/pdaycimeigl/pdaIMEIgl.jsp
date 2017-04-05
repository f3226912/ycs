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
<title>PDA验车IMEI码登记管理</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/content.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/jquery-1.8.2.js"></script>

<style> 
	html{ 
		overflow:scroll;
		scrollbar-base-color:#c7e5ff;
		scrollbar-track-color:#FFFFFF;
	}
	.gl{
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
			$("#ssbm").val("");
			$("#IMEI").val("");
		});
		
		$(".gl").click(function(){
			$(this).css("color","red");
			$(".gl").not($(this)).css("color","blue");
			var czlx=$(this).attr("czlx");
			var pdaId=$(this).attr("pdaId");
			if(pdaId!=null && pdaId!=""){
				if(czlx=="change"){
					var tempSsbm=$.trim($("#ssbm").val());
					var tempIMEI=$.trim($("#IMEI").val());
					var uri='<%=request.getContextPath()%>' +"/pdaimei/imei_getTPdaYcimeiToChange.action?pdaId="+pdaId;
					var returnValue=window.showModalDialog(uri,"","dialogWidth:520px;dialogHeight:455px;help:no;status:no;scroll:no;location:no");
					if(returnValue!=null && returnValue!="0" && returnValue!="" && typeof(returnValue)!="undefined"){
						window.location.href="<%=request.getContextPath()%>/pdaimei/imei_getTPdaYcimei.action?ssbm="+tempSsbm+"&IMEI="+tempIMEI;
					}
				}else if(czlx=="delete"){
					if(confirm("删除后不可恢复,确认继续删除?")){
						$.ajax({
							url: "<%=request.getContextPath()%>/pdaimei/imei_deleteTPdaYcimei.action",
				  			type:'post',
				  			cache: false,
				  			async: true,
				  			dataType:'text',
				  			data: {
					  			'id':pdaId,
					  			'mac':mac
					  		},
				  			error:function(){
					  			alert("请求异常,请重试!");
					  		},
				  			success:function(data){
					  			var result=$.trim(data);
					  			var index=result.indexOf("-");
					  			if(index!=0 ){
					  				alert("操作成功");
					  				window.location.href="<%=request.getContextPath()%>/pdaimei/imei_getTPdaYcimei.action";
						  		}else{
							  		alert(result.substring(1,result.length));
							  	}
				  			}
						});
					}
				}
			}else{
				alert("该记录id为空,无法操作");
				return false;
			}
		});
		
		$("#addButton").click(function(){
			var tempSsbm=$.trim($("#ssbm").val());
			var tempIMEI=$.trim($("#IMEI").val());
			var uri='<%=request.getContextPath()%>' +"/pdaimei/imei_getTPdaYcimeiToAdd.action?ssbm="+tempSsbm+"&IMEI="+tempIMEI;
			var returnValue=window.showModalDialog(uri,"","dialogWidth:520px;dialogHeight:460px;help:no;status:no;scroll:no;location:no");
			if(returnValue!=null && returnValue!="0" && returnValue!="" && typeof(returnValue)!="undefined"){
				window.location.href="<%=request.getContextPath()%>/pdaimei/imei_getTPdaYcimei.action";
			}
		});
		
		$("#excelButton").click(function(){
			$("#exportExcel").submit();
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
		<div style="width:98%;margin: 0 auto;">
			<form id="exportExcel" action="<%=request.getContextPath()%>/pdaimei/imei_getExcel.action">
				<input type="hidden" id="querySsbm" value="${request.ssbm}"/>
				<input type="hidden" id="queryIMEI" value="${request.IMEI}"/>
			</form>
			 <form id="queryForm" name="queryForm" action="<%=request.getContextPath()%>/pdaimei/imei_getTPdaYcimei.action" method="post">
				   <table  class="table1"  width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
					  <tr>
					    <td class="tds" style="text-align: right;">所属管理部门：</td>
					    <td class="tds" style="text-align: left;">
					    	<s:select
					    		id="ssbm"
					    		name="ssbm"
					    		theme="simple"
					    		list="#request.bmMap"
					    		listKey="key"
					    		listValue="value"
					    		headerKey=""
					    		headerValue="--请选择所属部门--"
					    		value="#request.ssbm">
					    	</s:select>
					    	
					    </td>
					  </tr>
					  <tr>
					    <td class="tds" style="text-align: right;">IMEI号：</td>
					    <td class="tds" style="text-align: left;">
					    	<input type="text" name="IMEI" id="IMEI" value="${request.IMEI}" />
					    </td>
					  </tr>
					  <tr>
					  	<td colspan="2">
					    	<input type="submit" id="queryButton" class="bnt"  style="width: 80px;" value="查  询" />
					    	<input type="button" id="excelButton" class="bnt"  style="width: 80px;" value="导出Excel" />
					    	<input type="button" id="resetButton" class="bnt"  style="width: 80px;" value="重  置"/>
					    </td>
					  </tr>
				  </table> 
			  </form>
		  </div>
		  
		  <div style="width:98%; margin-top: 10px;margin: 0 auto;" >
		   <div>
		   		<input type="submit" id="addButton" class="bnt" style="width: 80px;" value="添加" />
		   </div>
		    <table class="datalist" id="tableList" width="100%" border="0" cellpadding="0" cellspacing="0">
			  <tr class="tr1">
			  	<th>序列</th>
			    <th>所属部门</th>
			    <th>所属用户</th>
			    <th>IMEI号</th>
			    <th>验车地址</th>
			    <th>备注</th>
			    <th>录入时间</th>
			    <th style="font-size: 12px;">是否定<br>位标记</th>
			    <th style="font-size: 12px;">时间验<br>证标记</th>
			    <th style="font-size: 12px;">是否对接<br>中央平台</th>
			    <th>状态</th>
			    <th>管理</th>
			  </tr>
			  <s:if test='#request.tPdaYcimeis!=null && #request.tPdaYcimeis.size>0 '>
			  	<s:iterator value="#request.tPdaYcimeis" var="tempTPdaYcimei">
			  	<tr>
			  		<td width="30px;">${tempTPdaYcimei.xh}</td>
			  		<td width="75px;">${tempTPdaYcimei.bdbmKj}</td>
			  		<td>${tempTPdaYcimei.bdyh}</td>
			  		<td width="110px;">${tempTPdaYcimei.imei}</td>
			  		<td width="120px;"><s:property value='#request.xxdzMap.get(#tempTPdaYcimei.id)' /></td>
			  		<td width="120px;">${tempTPdaYcimei.bz}</td>
			  		<td width="125px;">
			  			<s:date name="#tempTPdaYcimei.lrsj" format="yyyy-MM-dd HH:mm:ss"/>
			  		</td>
			  		<td width="40px;">${tempTPdaYcimei.dwbj=="0"?"不定位":"定位"}</td>
			  		<td width="50px;">${tempTPdaYcimei.sjyzbj=="0"?"关闭验证":"打开验证"}</td>
			  		<td width="50px;">${tempTPdaYcimei.zyptbj=="0"?"否":"是"}</td>
			  		<td width="30px;">
			  			<s:if test=' #tempTPdaYcimei.zt=="1" '>正常</s:if>
			  			<s:else>停用</s:else>
			  		</td>
			  		<td>
		  				<a czlx="change" pdaId="${tempTPdaYcimei.id}" class="gl">修改</a>&nbsp;
		  				<a czlx="delete" pdaId="${tempTPdaYcimei.id}" class="gl">删除</a>
			  		</td>
			  		</tr>
			  	</s:iterator>
			  </s:if>
			  <s:else>
			  	<tr>
			  		<td style="color: red;text-align: center;" colspan="8">查无数据</td>
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