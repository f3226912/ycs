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
<title>字典管理 </title>
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

	var dmlb="";
	var dmz="";
	var dmms="";

	$(function(){
		
		$("#resetButton").click(function(){
			$("#dmz").val("");
			$("#dmlb").val("");
			$("#dmms1").val("");
		});
		
		
		$(".cz").click(function(){
			
			var xh=$(this).attr("xh");
			if(xh!=null && xh!=""){
				
				$(this).css("color","red");
				$(".cz").not($(this)).css("color","blue");
				
				var czlx=$(this).attr("czlx"); 
				
				//td对象
				var td_dmlb=$("#dmlb_"+xh);
				var td_dmz=$("#dmz_"+xh);
				var td_dmms=$("#dmms_"+xh);
				var cancel_button=$("#cancel_"+xh);
				var confirm_button=$("#confirm_"+xh);
				var change_button=$("#change_"+xh);

				
				//新值
				var newDmlb=$.trim($("#dmz_new_"+xh).val());
				var newDmz=$.trim($("#dmlb_new_"+xh).val());
				var newDmms=$.trim($("#dmms_new_"+xh).val());
				
				if(czlx=="change"){
					
					//保存旧值
					dmlb=$.trim(td_dmlb.text());
					dmz=$.trim(td_dmz.text());
					dmms=$.trim(td_dmms.text());
					
					var inputDmlb=$("<input id='dmz_new_"+xh+"' type='text' value='"+dmlb+"' />");
					var inputDmz=$("<input id='dmlb_new_"+xh+"' type='text' value='"+dmz+"' />");
					var inputDmms=$("<input id='dmms_new_"+xh+"' type='text' value='"+dmms+"' />");
					
					//清空旧数据
					td_dmlb.html("");
					td_dmz.html("");
					td_dmms.html("");
					
					td_dmlb.append(inputDmlb);
					td_dmz.append(inputDmz);
					td_dmms.append(inputDmms);
					
					change_button.hide();
					cancel_button.show(); 
					confirm_button.show();
					
				}else if(czlx=="cancel"){
					
					td_dmlb.html(dmlb);
					td_dmz.html(dmz);
					td_dmms.html(dmms);
					
					change_button.show();
					cancel_button.hide();
					confirm_button.hide();
					
				}else if(czlx=="confirm"){
					
					var tempDmms=decodeURI(newDmms);
					
					$.ajax({
						url: "<%=request.getContextPath()%>/gjgg/xtgl_updateSzzd.action",
			  			type:'post',
			  			cache: false,
			  			async: true,
			  			dataType:'text',
			  			data: {
				  			'xh':xh,
				  			'dmlb':newDmlb,
				  			'dmz':newDmz,
				  			'dmms':tempDmms
				  		},
			  			error:function(){
				  			alert("请求异常,请重试!");
				  		},
			  			success:function(data){
				  			var result=$.trim(data);
				  			var index=result.indexOf("-");
				  			if(index!=0 ){
				  				alert("操作成功!");
				  				window.location.href="<%=request.getContextPath()%>/gjgg/xtgl_getSzzdInital.action";
					  		}else{
						  		alert(result.substring(1,result.length));
						  	}
			  			}
					});
				}
				
			}else{
				alert("字典序号为空,无法操作");
			}
		});
		
		
		$("#addButton").click(function(){
			
			
			return false;
			var uri='<%=request.getContextPath()%>' +"/pages/gjgggl/xtgl/gjgszhgl_detail.jsp";
			var returnValue=window.showModalDialog(uri,"","dialogWidth:650px;dialogHeight:300px;help:no;status:no;scroll:no;location:no;resizable:yes");
			if(returnValue!=null && returnValue!="0" && returnValue!="" && typeof(returnValue)!="undefined"){
				var index=returnValue.indexOf("-");
				if(index!=0){
					alert("添加成功,初始密码为:"+returnValue);
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
			 <form id="queryForm" name="queryForm" action="<%=request.getContextPath()%>/gjgg/xtgl_getSzzdInital.action" method="post">
			   <table  class="table1"  width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
				  <tr>
				    <td class="tds" style="text-align: right;">代码类别：</td>
				    <td class="tds" style="text-align: left;">
				    	<input type="text" name="dmlb" id="dmlb" value="${request.dmlb}" />
				    </td>
				  </tr>
				  <tr>
				    <td class="tds" style="text-align: right;">代码值：</td>
				    <td class="tds" style="text-align: left;">
				    	<input type="text" name="dmz" id="dmz" value="${request.dmz}" />
				    </td>
				  </tr>
				  <tr>
				  	 <td class="tds" style="text-align: right;">代码描述：</td>
				    <td class="tds" style="text-align: left;">
				    	<input type="text" name="dmms1" id="dmms1" value="${request.dmms1}" />
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
		  
	  
	    <table class="datalist" id="tableList" width="100%" border="0" cellpadding="0" cellspacing="0">
		  <tr class="tr1">
		    <th>代码类别</th>
		    <th>代码值</th>
		    <th>代码描述</th>
		    <th>操作</th>
		  </tr>
		  <s:if test='#request.szzdList!=null && #request.szzdList.size>0 '>
		  	<s:iterator value="#request.szzdList" var="tempSzzd">
		  		<tr>
			  		<td id="dmlb_${tempSzzd[0]}">${tempSzzd[1]}</td>
			  		<td id="dmz_${tempSzzd[0]}">${tempSzzd[2]}</td>
			  		<td id="dmms_${tempSzzd[0]}">${tempSzzd[3]}</td>
			  		<td id="td_${tempSzzd[0]}">
			  			<a class="cz" style="display:none;" czlx="confirm" id="confirm_${tempSzzd[0]}" xh="${tempSzzd[0]}">确定</a>
			  			<a class="cz" czlx="change" id="change_${tempSzzd[0]}" xh="${tempSzzd[0]}">修改</a>
			  			<a class="cz" style="display:none;" czlx="cancel" id="cancel_${tempSzzd[0]}" xh="${tempSzzd[0]}" >取消</a>
			  		</td>
		  		</tr>
		  	</s:iterator>
		  </s:if>
		  <s:else>
		  	<tr>
		  		<td style="color: red;text-align: center;" colspan="4">查无数据</td>
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