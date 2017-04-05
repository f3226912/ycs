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
<title>公交客运公司账户管理 </title>
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

	$(function(){
		
		$("#resetButton").click(function(){
			$("#gjgsid").val("");
			$("#gjgsmc").val("");
			$("#zzjgdm").val("");
		});
		
		
		$(".cz").click(function(){
			var gjgsid=$(this).attr("gjgsid");
			if(gjgsid!=null && gjgsid!=""){
				
				if(confirm("确定继续操作?")){
					$.ajax({
						url: "<%=request.getContextPath()%>/gjgg/xtgl_updateBusBasePwd.action",
			  			type:'post',
			  			cache: false,
			  			async: true,
			  			dataType:'text',
			  			data: {
				  			'gjgsid':gjgsid
				  		},
			  			error:function(){
				  			alert("请求异常,请重试!");
				  		},
			  			success:function(data){
				  			var result=$.trim(data);
				  			var index=result.indexOf("-");
				  			if(index!=0 ){
				  				//alert("操作成功,新密码为:"+result);
				  				alert("操作成功");
				  				window.location.href="<%=request.getContextPath()%>/gjgg/xtgl_getBusBaseInital.action";
					  		}else{
						  		alert(result.substring(1,result.length));
						  	}
			  			}
					});
				}
					
			}else{
				alert("获取不到当前操作公交公司id,无法操作数据");	
			}
			
		});
		
		$("#addButton").click(function(){
			var uri='<%=request.getContextPath()%>' +"/pages/gjgggl/xtgl/gjgszhgl_detail.jsp";
			var returnValue=window.showModalDialog(uri,"","dialogWidth:650px;dialogHeight:300px;help:no;status:no;scroll:no;location:no;resizable:yes");
			if(returnValue!=null && returnValue!="0" && returnValue!="" && typeof(returnValue)!="undefined"){
				var index=returnValue.indexOf("-");
				if(index!=0){
					//alert("添加成功,初始密码为:"+returnValue);
					alert("添加成功");
					window.location.href="<%=request.getContextPath()%>/gjgg/xtgl_getBusBaseInital.action";
				}else{
			  		alert(returnValue.substring(1,returnValue.length));
			  	}
			}
		});
		
		
	})
	
</script>
	<body>
	
		<div style="width:95%;margin: 0 auto;">
			 <form id="queryForm" name="queryForm" action="<%=request.getContextPath()%>/gjgg/xtgl_getBusBaseInital.action" method="post">
			   <table  class="table1"  width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
				  <tr>
				    <td class="tds" style="text-align: right;">公共公司id：</td>
				    <td class="tds" style="text-align: left;">
				    	<input type="text" name="gjgsid" id="gjgsid" value="${request.gjgsid}" />
				    </td>
				  </tr>
				  <tr>
				    <td class="tds" style="text-align: right;">公共公司名称：</td>
				    <td class="tds" style="text-align: left;">
				    	<input type="text" name="gjgsmc" id="gjgsmc" value="${request.gjgsmc}" />
				    </td>
				  </tr>
				  <tr>
				  	 <td class="tds" style="text-align: right;">组织机构代码：</td>
				    <td class="tds" style="text-align: left;">
				    	<input type="text" name="zzjgdm" id="zzjgdm" value="${request.zzjgdm}" />
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
		  <div style="width:100%;">
			<input type="button" class="bnt" style="width: 80px;" id="addButton" title="添加公交公司"  value="添加公司"/>
		</div> 
	  
	    <table class="datalist" id="tableList" width="100%" border="0" cellpadding="0" cellspacing="0">
		  <tr class="tr1">
		    <th>公交公司id</th>
		    <th>公交公司名称</th>
		    <th>组织机构代码</th>
		    <th>公交公司地址</th>
		    <th>责任人</th>
		    <th>联系电话</th>
		    <th>操作</th>
		  </tr>
		  <s:if test='#request.busBases!=null && #request.busBases.size>0 '>
		  	<s:iterator value="#request.busBases" var="tempBusBase">
		  		<tr>
			  		<td>${tempBusBase.gjgsid}</td>
			  		<td>${tempBusBase.gjgsmc}</td>
			  		<td>${tempBusBase.zzjgdm}</td>
			  		<td>${tempBusBase.gjgsdz}</td>
			  		<td>${tempBusBase.zzr}</td>
			  		<td>${tempBusBase.lxdh}</td>
			  		<td>
			  			<a class="cz"  gjgsid="${tempBusBase.gjgsid}">初始化密码</a>
			  		</td>
		  		</tr>
		  	</s:iterator>
		  </s:if>
		  <s:else>
		  	<tr>
		  		<td style="color: red;text-align: center;" colspan="7">查无数据</td>
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