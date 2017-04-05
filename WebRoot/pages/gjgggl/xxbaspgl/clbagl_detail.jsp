<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<title>车辆备案管理-详细</title>

<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/jquery-1.8.2.js"></script>
<LINK media=screen href="<%=request.getContextPath()%>/js/gjgggl/jQuery.files/jquery-ui-1.7.1.custom.css" type=text/css rel=stylesheet>
<LINK media=screen href="<%=request.getContextPath()%>/js/gjgggl/jQuery.files/jquery.gzoom.css" type=text/css rel=stylesheet>
<SCRIPT src="<%=request.getContextPath()%>/js/gjgggl/jQuery.files/ui.core.min.js" type=text/javascript></SCRIPT>
<SCRIPT src="<%=request.getContextPath()%>/js/gjgggl/jQuery.files/ui.slider.min.js" type=text/javascript></SCRIPT>
<SCRIPT src="<%=request.getContextPath()%>/js/gjgggl/jQuery.files/jquery.mousewheel.js" type=text/javascript></SCRIPT>
<SCRIPT src="<%=request.getContextPath()%>/js/gjgggl/jQuery.files/jquery.gzoom.js" type=text/javascript></SCRIPT>
<SCRIPT type=text/javascript>
      $(function() {

        $(".zoom_no_lbox").gzoom({
            sW: 470,
            sH: 250,
            lW: 1410,
            lH: 750,
            lightbox: false
        });
		
      });
</SCRIPT>
<style> 
	html{ 
		overflow:scroll;
		scrollbar-base-color:#c7e5ff;
		scrollbar-track-color:#FFFFFF;
	}
	
	
	.datalist {
		border: 1px solid #0058a3;
		font-family: Arial, Helvetica, sans-serif;
		border-collapse: collapse;
		/*background-color: #eaf5ff;*/
		font-size: 14px;
	}
	
	.datalist tr {
		height: 32px;
	}
	
	.datalist tr.altrow {
		/*background-color: #c7e5ff;*/
		height: 32px;
	}
	
	.datalist td {
		border: 1px solid #3c7eba;
		text-align: center;
		padding-top: 2px;
		padding-bottom: 2px;
		padding-left: 5px;
		overflow: hidden;
		font-size: 12px;
	}
	
	.datalist th {
		border: 1px solid #3c7eba;
		height: 32px;
	}
	
	.tds1 {
		background: #eaf5ff;
		font-weight: bold;
		font-size: 14px;
	}
	
	.tds2 {
		font-weight: bold;
		font-size: 14px;
	}
	
	.bnt {
		width: 76px;
		height: 27px;
		background: url(<%=request.getContextPath()%>/images/an2.gif) no-repeat;
		border: none;
		font-weight: bold;
	}
	
	.error{
		width:160px;
		color: #941305;
		font-size: 12px;
	}
</style>
</head>
<script type="text/javascript">   
	//操作类型
	var czlx = window.dialogArguments;
	//查询车档镜像库是否有数据
	var nodata=$.trim(${resultMap.nodata});
	
	var mac="";
	
	$(function(){
		
		if(nodata=="true"){
			alert("无该车车档数据");
		}
		
		function inital(){
			czlx=$.trim(czlx);
			if(czlx=="show"){
				$("input").attr("readonly","readonly");
				$("input[type='radio']").not($("#cancelButton")).attr("disabled","disabled");
				$("input[type='button']").not($("#cancelButton")).hide();
				$("select").attr("disabled","disabled");
				$("textarea").attr("readonly","readonly");
			}else if(czlx=="tb"){
				
			}else if(czlx=="sh"){
				
			}
		}
		inital();
		
		$("#zlxz").click(function(){
			var realCllx=$.trim($("#realCllx").val());
			var realClsbdh=$.trim($("#realClsbdh").val());
			
			if(realCllx!=null && realCllx!=""){
				$("#cllx").val(realCllx);
			}
			if(realClsbdh!=null && realClsbdh!=""){
				$("#clsbdh").val(realClsbdh);
			}
			
			$(":radio[value='A']").attr("checked","checked");
			
			//清空错误数据
			$("#tbyy").html("");
			$(".error").hide();
			$(".error").html("");
			
		});
		
		$(".tj").click(function(){
			
			var xh=$.trim($("#xh").val());
			var cllx=$.trim($("#cllx").val());
			var clsbdh=$.trim($("#clsbdh").val());
			var shzt=$(":radio:checked").val();
			var tbyy=$.trim($("#tbyy").val());
			var tempTbyy=decodeURI(tbyy);
			
			if(shzt==null || shzt==""){
				alert("请选择审核结果");
				return false;
			}
			
			if(shzt=="2"){
				if(tbyy==null || tbyy==""){
					alert("请填写不通过原因");
					return false;
				}
			}
			
			var tbyy=$.trim($("#tbyy").val());
			if(cllx==null || cllx==""){
				alert("请选择车辆类型!");
				return false;
			}
			if(clsbdh==null || clsbdh==""){
				alert("请填写车架号");
				return false;
			}
			if(shzt==null || shzt==""){
				alert("请选择审核结果!");
				return false;
			}
			
			$.ajax({
				url: "<%=request.getContextPath()%>/gjgg/gjgg_updateBusVehicleBase.action",
	  			type:'post',
	  			cache: false,
	  			async: true,
	  			dataType:'text',
	  			data: {
		  			'bus.xh':xh,
		  			'bus.shzt':shzt,
		  			'bus.cllx':cllx,
		  			'bus.clsbdh':clsbdh,
		  			'bus.tbyy':tempTbyy,
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
		  				window.close();
						window.returnValue="success";
			  		}else{
				  		alert(result.substring(1,result.length));
				  	}
	  			}
			});
			
		});
		
		$("#cancelButton").click(function(){
			window.close();
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
	 	
	 	setTimeout(function(){
	 		var xh=$.trim("${request.busVehicleBase.xh}");
	 		if(xh!=null && xh!=""){
		 		var src="<%=request.getContextPath()%>/gjgg/gjgg_getXszPic.action?xh="+xh;
		 		$("#xszImg").attr("src",src);
	 		}
	 	},100);
		
	})
	
</script>
	<body>
		  <div style="width:100%;" >
		  
		    <table class="datalist" width="100%" border="0" cellpadding="0" cellspacing="0">
			   <s:if test='#request.busVehicleBase!=null '>
				  <tr>
				    <td class="tds1" style="text-align: right;width:80px;">号牌号码：</td>
				    <td class="tds2" style="text-align: left;width:80px;">
				    	<input type="hidden" id="xh" value="${request.busVehicleBase.xh}" />
				    	<input type="text" name="hphm" id="hphm" value="${request.busVehicleBase.hphm}"  />
				    </td>
				    <td class="tds1" style="text-align: right;width:80px;">号牌种类：</td>
				    <td class="tds2" style="text-align: left;width:240px;">
				    	<s:select id="hpzl"
				    		theme="simple"
							list="#request.hpzlMap"
							listKey="key" 
							listValue="value"
							disabled="true"
							headerKey="" 
							headerValue="--请选择号牌种类--"
							value="#request.busVehicleBase.hpzl"
							>
						</s:select>
				    </td>
				  </tr>
				  
				  <tr>
				  	
				  	<td class="tds1" style="text-align: right;width:80px;">车辆类型：</td>
				    <td class="tds2" style="text-align: left;width:240px;">
				    	<div>
				    		<div>
						    	<s:select id="cllx"
						    		theme="simple"
						    		cssStyle="width:155px;" 
									list="#request.cllxMap" 
									listKey="key"
									listValue="value"
									headerKey="" 
									headerValue="--请选择车辆类型--"
									value="#request.busVehicleBase.cllx"
									>
								</s:select>
								<input type="hidden" id="realCllx" value="${resultMap_.cllx}" />
				    		</div>
				    		<s:if test='#request.resultMap.cllx!=null && #request.resultMap.cllx!="" '>
					    		<div class="error" id="cllxError">${resultMap.cllx}</div>
				    		</s:if>
				    	</div>
						
				    </td>
				  	
				    <td class="tds1" style="text-align: right;width:80px;">车架号：</td>
				    <td class="tds2" style="text-align: left;width:240px;">
				    	<div style="float: left;">
				    		<div style="float: left;">
				    			<input type="text" name="clsbdh" id="clsbdh" value="${request.busVehicleBase.clsbdh}" />
				    			<input type="hidden" id="realClsbdh" value="${resultMap_.clsbdh}" />
				    		</div>
				    		<s:if test='#request.resultMap.clsbdh!=null && #request.resultMap.clsbdh!="" '>
				    			<div class="error" id="clsbdhError">${resultMap.clsbdh}</div>
				    		</s:if>
				    	</div>
				    </td>
				    
				  </tr>

				  
				  <tr>
				  	<td class="tds1" style="text-align: right;width:80px;">行驶证图片：</td>
				  	<td colspan="3" style="text-align: left;" >
					  	<DIV class="zoom_no_lbox"  >
					  		<img id="xszImg" title="行驶证图片" style="width: 470px;height: 250px;"  />
						</DIV>
				  	</td>
				  </tr>
				  <tr>
				    <td class="tds1" style="text-align: right;width:80px;">审核结果：</td>
				    <td class="tds2" style="text-align: left;" colspan="3">
				    	<input type="radio" name="shzt" id="shzt" value="1" <s:if test='#request.busVehicleBase.shzt=="0" || #request.busVehicleBase.shzt=="1" '>checked="checked"</s:if> />审核通过
				    	<input type="radio" name="shzt" id="shzt" value="2" <s:if test='#request.busVehicleBase.shzt=="2" '>checked="checked"</s:if> />审核不通过
				    </td>
				  </tr>
				  <tr>
				    <td class="tds1" style="text-align: right;width:80px;">退办原因：</td>
				    <td class="tds2" style="text-align: left;" colspan="3">
				    	<s:if test='#request.czlx=="show" '>
				    		<textarea id="tbyy" name="tbyy" rows="5" cols="70">${request.busVehicleBase.tbyy}</textarea>
				    	</s:if>
				    	<s:else>
						    <textarea id="tbyy" name="tbyy" rows="5" cols="70">${resultMap.cllx} ${resultMap.clsbdh} </textarea>
				    	</s:else>
				    </td>
				  </tr>
				  <tr>
				    <td class="tds" style="text-align: center;" colspan="4">
				    	<!-- 
				    		 <input type="button" class="tj bnt" style="width: 80px;" zt="1" value="通过" />
					     	<input type="button" class="tj bnt" style="width: 80px;" zt="2" value="不通过" />
				    	 -->
					     <input type="button" class="tj bnt" style="width: 80px;" value="确定" />
					    <s:if test=' #request.resultMap.nodata!="true" && #request.resultMap.hasDifference=="true" '>
					    	<input type="button" id="zlxz" class="bnt" style="width: 80px;"  value="资料修正" />
					    </s:if>
					    <!-- 查车档镜像库无数据  -->
					    <s:elseif test='#request.resultMap.nodata=="true"'>
					    	<input type="button" title="车档无此车辆数据" class="bnt" style="width: 80px;"  value="资料修正" disabled="disabled" />
					    </s:elseif>
					    <input type="button" id="cancelButton" class="bnt" style="width: 80px;"  value="返回" />
				    </td>
				  </tr>
			   </s:if>
			   <s:else>
			   	 <tr>
			   	 	<td style="color: red;" colspan="4">查无数据</td>
			   	 </tr>
			   </s:else>
			</table>
		
		   </div>

</body>
</html>