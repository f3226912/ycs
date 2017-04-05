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
<title>证件发放情况统计</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/content.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/jquery-1.8.2.js"></script>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/My97DatePicker/WdatePicker.js"></script>
<style> 
	html{ 
		overflow:scroll;
		scrollbar-base-color:#c7e5ff;
		scrollbar-track-color:#FFFFFF;
	}
	.cz,.lscx {
		cursor: pointer;
		color: blue;
		text-decoration: underline;
	}
</style>
</head>
<script type="text/javascript">   

	var mac="";
	var qx=$.trim("${request.qx}");

	$(function(){
		
		$("#resetButton").click(function(){
			$("#lsh").val("");
			$("#hphm").val("");
			$("#hpzl").val("");
			$("#gjgs").val("");
			$("#gggs").val("");
		});
		
		$(".cz").click(function(){
			
			$(this).css("color","red");
			$(".cz").not($(this)).css("color","blue");
			$(".lscx").css("color","blue");
			
			var bm=$(this).attr("bm");
			var zzmjcode=$(this).attr("zzmjcode");
			var startTime=$(this).attr("startTime");
			var endTime=$(this).attr("endTime");
			
			if(qx!="kj"){
				if(bm!=null && bm!=""){
					
					var uri='<%=request.getContextPath()%>' +"/gjgg/tjcx_getZjffqktj_detail.action?yyycbm="+bm+"&startTime="+startTime+"&endTime="+endTime+"&qx="+qx;
				    window.showModalDialog(uri,"","dialogWidth:1000px;dialogHeight:700px;help:no;status:no;scroll:yes;location:no;resizable:yes");
					
				}else{
					alert("无数据显示");
				}
			}else{
				if(zzmjcode!=null && zzmjcode!=""){
					
					var uri='<%=request.getContextPath()%>' +"/gjgg/tjcx_getZjffqktj_detail.action?yyycbm="+bm+"&zzmjcode="+zzmjcode+"&startTime="+startTime+"&endTime="+endTime+"&qx="+qx;
				    window.showModalDialog(uri,"","dialogWidth:1000px;dialogHeight:700px;help:no;status:no;scroll:yes;location:no;resizable:yes");
					
				}else{
					alert("无数据显示");
				}
			}
			
		});
		
		
		$(".cz_ttt").click(function(){
			
			$(this).css("color","red");
			$(".cz").not($(this)).css("color","blue");
			$(".lscx").css("color","blue");
			
			var bm=$(this).attr("bm");
			var zzmjcode=$(this).attr("zzmjcode");
			
			if(qx!="kj"){
				if(bm!=null && bm!=""){
					showFzbmmc(bm);
					var sbDataDetail=$(".sbDataDetail[bm='"+bm+"']");
					if(sbDataDetail!=null && sbDataDetail.length>0){
						$("#sbDataDiv").show();
						$(".sbDataDetail").not(sbDataDetail).hide();
						sbDataDetail.show();
					}else{
						$("#sbDataDiv").hide();
						alert("无数据显示");
					}
					
				}else{
					alert("无数据显示");
				}
			}else{
				if(zzmjcode!=null && zzmjcode!=""){
					showFzbmmc(bm);
					var sbDataDetail=$(".sbDataDetail[zzmjcode='"+zzmjcode+"']");
					if(sbDataDetail!=null && sbDataDetail.length>0){
						$("#sbDataDiv").show();
						$(".sbDataDetail").not(sbDataDetail).hide();
						sbDataDetail.show();
					}else{
						$("#sbDataDiv").hide();
						alert("无数据显示");
					}
					
				}else{
					alert("无数据显示");
				}
			}
			
		});
		
		function showFzbmmc(fzbm){
			if(fzbm!=null && fzbm!=""){
				var mc=$("#fzbm_"+fzbm).html();
				$("#fzbmToShow").html("发证部门："+mc);
			}
		}
		
		
		
	})
	
</script>
	<body>
		<div style="width:95%;margin: 0 auto;">
			 <form id="queryForm" name="queryForm" action="<%=request.getContextPath()%>/gjgg/tjcx_getZjffqktj.action" method="post">
				   <table  class="table1"  width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
					  <tr>
					    <td class="tds" style="text-align: right;">部门：</td>
					    <td style="text-align: left;">
					    	<s:if test='#request.yyycbmMap!=null '>
						    	<s:select id="yyycbm"
						    		name="yyycbm"
						    		cssStyle="width:155px;"
						    		theme="simple"
									list="#request.yyycbmMap"
									listKey="key" 
									listValue="value"
									headerKey="" 
									headerValue="--请选择部门--"
									value="#request.yyycbm"
									>
								</s:select>
					    	</s:if>
					    	<s:else>
					    		<input type="text" disabled="disabled" />
					    	</s:else>
					    	<input type="hidden" name="qx" value="${request.qx}" />
					    </td>
					  </tr>
					  <tr>
					  	 <td class="tds" style="text-align: right;">时间段：</td>
					    <td style="text-align: left;">
					    	<input id="startTime" name="startTime" type="text"
									value="${request.startTime}"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true,enableKeyboard:false,maxDate:'%y-%M-%d %H:%m:%s'})"
									 /> 到 
							<input id="endTime" name="endTime" type="text"
									value="${request.endTime}"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true,enableKeyboard:false,maxDate:'%y-%M-%d %H:%m:%s'})"
									 />
					    </td>
					  </tr>
					  <tr>
					  	<td colspan="4"> 
					    	<input type="submit" id="queryButton" class="bnt" style="width: 80px;" value="查  询" />
					    	<input type="button" id="resetButton" class="bnt" style="width: 80px;" value="重  置"/>
					    </td>
					  </tr>
				  </table> 
			  </form>
		  </div>
		  
		  <div style="width:95%; margin-top: 10px;margin: 0 auto;" >
		  
		    <table class="datalist" id="tableList" width="100%" border="0" cellpadding="0" cellspacing="0">
			  <tr class="tr1">
			  	<th>发证部门</th>
			  	<s:if test='#request.qx=="kj"'>
			  		<th>发证人</th>
			  	</s:if>
			    <th>发证数量</th>
			    <th>错证重打</th>
			    <th>操作</th>
			  </tr>
			  <s:if test='#request.tjList!=null && #request.tjList.size>0 '>
			  	<s:iterator value="#request.tjList" var="tempTj">
				  	<tr>
				  		<td id="fzbm_${tempTj[0]}">${tempTj[1]}</td>
				  		<s:if test='#request.qx=="kj"'>
					  		<td>${tempTj[5]}</td>
					  	</s:if>
				  		<td>${tempTj[2]}</td>
				  		<td>${tempTj[3]}</td>
				  		<td>
			  				<a class="cz" 
			  					bm="${tempTj[0]}" 
			  					zzmjcode="${tempTj[4]}"
			  					startTime="${request.startTime}"
			  					endTime="${request.endTime}" >发证详细</a>
				  		</td>
				  	</tr>
			  	</s:iterator>
			  </s:if>
			  <s:else>
			  	<tr>
			  		<td style="color: red;text-align: center;" colspan="11">查无数据</td>
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