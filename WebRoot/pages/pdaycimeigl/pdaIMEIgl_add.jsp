<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<title>PDA验车IMEI码登记管理-添加</title>

<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/jquery-1.8.2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/gjgggl/jquery.jPrintArea.js"></script>


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
	
	.isNeed{
		color: red;
		font-size: 11px;
	}
	
	
</style>
<script type="text/javascript">

	var mac="";
	
	$(function(){
		
		$("#addButton").click(function(){
			
			var pdaid=$(this).attr("pdaid");
			var IMEI = $.trim($("#IMEI").val());
			var bdbmkj = $.trim($("#ssbm").val());
			var bdyhValue = $.trim($("#ssyh").val());
			var bdyhText = $.trim($("#ssyh option:selected").text());
			var bz = $.trim($("#bz").val());
			var tempBz=decodeURI(bz);
			var zt = $.trim($('[name=zt]:radio:checked').val());
			var mac = $.trim(mac);
			var zyptbj = $.trim($('[name=zyptbj]:radio:checked').val());
			var gpsidkey = $.trim($("#ycjg").val());
			var gpsidValue = $.trim($("#ycjg option:selected").text());
			var dwbj = $.trim($('[name=dwbj]:radio:checked').val());
			var sjyzbj = $.trim($('[name=sjyzbj]:radio:checked').val());
			if (bdbmkj==null || bdbmkj=="") {
				alert("所属部门为空");
				return false;
			}
			if(bdyhValue==null || bdyhValue==""){
				bdyhText=bdyhValue;
			}
			/*if(bdyhValue==null || bdyhValue==""){
				alert("所属用户为空");
				return false;
			}*/
			if (IMEI==null || IMEI=="") {
				alert("IMEI号为空");
				return false;
			}
			if (zt==null || zt=="") {
				alert("IMEI绑定状态为空");
				return false;
			}
			
			$.ajax({
				url: "<%=request.getContextPath()%>/pdaimei/imei_addTPdaYcimei.action",
	  			type:'post',
	  			cache: false,
	  			async: true,
	  			dataType:'text',
	  			data: {
		  			'pdaYcimei.imei':IMEI,
		  			'pdaYcimei.bdbmKj':bdbmkj,
		  			'pdaYcimei.bdyh':bdyhText,
		  			'pdaYcimei.zt':zt,
		  			'pdaYcimei.bz':tempBz,
		  			'mac':mac,
		  			'pdaYcimei.zyptbj':zyptbj,
		  			'pdaYcimei.gpsid':gpsidkey,
		  			'pdaYcimei.dwbj':dwbj,
		  			'pdaYcimei.sjyzbj':sjyzbj
		  		},
	  			error:function(){
		  			alert("请求异常,请重试!");
		  		},
	  			success:function(data){
		  			var result=$.trim(data);
		  			var index=result.indexOf("-");
		  			if(index!=0 ){
		  				alert("操作成功");
		  				window.close();
		  				window.returnValue="success";
			  		}else{
				  		alert(result.substring(1,result.length));
				  	}
	  			}
			});
		});
		
		$("#ssbm").change(function(){
			var ssbm=$.trim($(this).val());			
			$.ajax({
					cache:false,
					async:false,
					type:'post',
					url:"<%=request.getContextPath()%>/pdaimei/imei_getUserbydept.action",
					data: {ssbm:ssbm},
					dataType: 'html',
					success:function(data){		
						
						var str="";
						    str += "<select style=\"width:170px;"+"\" id=\"realSsyh"+"\" name=\"realSsyh"+"\">";
						    str += "<option value=\"0\">--请选择用户--</option>";
				            var list = data.split(",");
				            if(list.length>0){
					            for(var i=0;i<list.length;i++){
						    	     var listss = list[i].split(":");
						    	     str+="<option value=\""+listss[0]+"\">"+listss[0]+"---"+listss[1]+"</option>";
		     				  	}
				            }
				            $("#UserInfo").empty();
		             		$("#UserInfo").append(str);
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
		
	});
	
</script>
</head>

	<body>
		  <div style="width:100%;" >
		  
		    <table id="showData" class="datalist" width="100%" border="0" cellpadding="0" cellspacing="0">
		    	
	    	  <tr>
	    	  	<td class="tds1" style="text-align: right;width:80px;">所属部门：</td>
			    <td class="tds2" style="text-align: left;" >
			    	<s:if test='#request.bmMap!=null && #request.bmMap.size>0 '>
				    	<s:select id="ssbm"
				    		theme="simple"
							list="#request.bmMap"
							cssStyle="width:170px;"
							listKey="key" 
							listValue="value"
							headerKey="" 
							headerValue="--请选择部门--"
							value="#request.tPdaYcimei.bdbmKj"
							>
						</s:select>
						<label class="isNeed">必选</label>
			    	</s:if>
			    </td>
	    	  </tr>
			  <tr>
			    <td class="tds1" style="text-align: right;width:80px;">所属用户：</td>
			    <td class="tds2" style="text-align: left;">
			    	<span id="UserInfo">
					<select id="ssyh" style="width:170px;">
					<option value=""></option>
					</select>
					</span>
			    </td>
			  </tr>
			  <tr>
			  	<td class="tds1" style="text-align: right;width:80px;">IMEI号：</td>
			    <td class="tds2" style="text-align: left;">
			    	<input type="text" name="IMEI" id="IMEI" style="width:165px;" />
			    	<label class="isNeed">必填</label>
			    </td>
			  </tr>
			  <tr>
			  	<td class="tds1" style="text-align: right;width:120px;">验车机构：</td>
			    <td class="tds2" style="text-align: left;">
			    	<s:if test='#request.ycjgList!=null && #request.ycjgList.size>0 '>
			    		<select id="ycjg" style="width:170px;" >
			    			<option value="">--请选择验车机构--</option>
			    			<s:iterator  value="#request.ycjgList" var="ycjg">
			    				<option value="${ycjg[0]}">
			    					${ycjg[1]}
			    				</option>
			    			</s:iterator>
			    		</select>
			    	</s:if>
			    </td>
			  </tr>
			  <tr>
			  	<td class="tds1" style="text-align: right;width:80px;">备注：</td>
			     <td class="tds2" style="text-align: left;">
			    	<textarea rows="5" cols="30" name="bz" id="bz"></textarea>
			    	<label class="isNeed">可为空</label>
			     </td>
			  </tr>
			  <tr>
			     <td class="tds1" style="text-align: right;width:120px;">时间验证标记：</td>
			    <td class="tds2" style="text-align: left;">
			    	<input type="radio" name="sjyzbj" value="0" />关闭验证
			    	<input type="radio" name="sjyzbj" value="1" checked="checked"/>打开验证
			    	<label class="isNeed">(必选)</label>
			    </td>
			  </tr>
			  <tr>
			     <td class="tds1" style="text-align: right;width:120px;">是否定位标记：</td>
			    <td class="tds2" style="text-align: left;">
			    	<input type="radio" name="dwbj" checked="checked" value="0" />不定位
			    	<input type="radio" name="dwbj" value="1"/>定位
			    	<label class="isNeed">(必选)</label>
			    </td>
			  </tr>
			  <tr>
			     <td class="tds1" style="text-align: right;width:80px;">状态：</td>
			    <td class="tds2" style="text-align: left;">
			    	<input type="radio" name="zt" value="1" checked="checked" />正常
			    	<input type="radio" name="zt" value="2"/>停用
			    	<label class="isNeed">(必选)</label>
			    </td>
			  </tr>
			  <tr>
			     <td class="tds1" style="text-align: right;width:80px;">是否对接中央平台：</td>
			    <td class="tds2" style="text-align: left;">
			    	<input type="radio" name="zyptbj" value="1" />是
			    	<input type="radio" name="zyptbj" value="0" checked="checked" />否
			    	<label class="isNeed">(必选)</label>
			    </td>
			  </tr>
			  <tr>
			    <td class="tds" style="text-align: center;" colspan="2">
			    	<input type="button" class="bnt" id="addButton" style="width: 80px;" value="添加" />
				    <input type="button" class="bnt" id="cancelButton" style="width: 80px;" value="取消" />
			    </td>
			  </tr>
			</table>
		   </div>
</body>
</html>