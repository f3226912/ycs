<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<title>按批次号审核广告喷涂业务</title>

<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/jquery-1.8.2.js"></script>
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
		width:70px;
		color: red;
		font-size: 12px;
		float: left;
	}
</style>
</head>
<script type="text/javascript">


	//要审核的批次号集合
	var pcList = window.dialogArguments;
	
	var mac="";
	
	$(function(){
		
		function inital(){
			if(pcList==null || pcList=="" || pcList.length<=0){
				alert("无法获取要审核的批次号");
			}
		}
		
		inital();
		
		$("#saveButton").click(function(){
			var shztRadio=$(":radio:checked");
			if(typeof(pcList)!="undefined" && pcList!=null && pcList!=""){
				if(shztRadio.length==1){
					var result=initalData(pcList);
					
					var shzt=$(":radio:checked").val();
					//退办原因对应的id
					var tbyy="";
					var tbyyCheckBox=$("#checkboxDiv :checkbox:checked");
					var otherTbyy=$.trim($("#otherTbyy").val());
					var tempOtherTbyyStr=decodeURI(otherTbyy);
					
					if(shzt=="0"){
						
						if(tbyyCheckBox==null || tbyyCheckBox.length==0){
							alert("请选择退办原因");
							return false;
						}else{
							var tempTbyy=$(tbyyCheckBox.get(0));
							if (tempTbyy.val()=="A000") {
								if (otherTbyy=="") {
									alert("请填写其他原因");
									return false;
								}
								tbyy="A000";
							}
						}
						
						if(tbyy!="A000"){
							for(var i=0;i<=tbyyCheckBox.length-1;i++){
								var tempTbyy=tbyyCheckBox.get(i);
								if(i<tbyyCheckBox.length-1){
									tbyy=tbyy+tempTbyy.value+"*";
								}else{
									tbyy=tbyy+tempTbyy.value;
								}
							}
						}
						
					}
					
					$.ajax({
						url: "<%=request.getContextPath()%>/gjgg/ycgg_updateBusGgjgclsbByPch.action",
			  			type:'post',
			  			cache: false,
			  			async: true,
			  			dataType:'text',
			  			data: {
				  			'pchList':result,
				  			'shzt':shzt,
				  			'tbyy':tbyy,
				  			'otherTbyy':otherTbyy,
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
				}else{
					alert("请选择审核意见");
				}
			}else{
				alert("无法获取要审核的批次号");
			}
		});
		
		function initalData(obj){
			var result="";
			if(typeof(obj)!="undefined" && obj!=null ){
				for(var i=0;i<obj.length;i++){
					if(i!=obj.length-1){
						result=result+obj[i]+"-";
					}else{
						result=result+obj[i];
					}
				}
			}
			return result;
		}
		
		$("#cancleButton").click(function(){
			window.close();
		});
		
		$(".shztRadio").click(function(){
			var shzt=$(this).val();
			//0审核不通过  1审核通过
			if(shzt=="CQ"){
				$("#tbyyTr").show();
			}else if(shzt=="2"){
				$("#tbyyTr").hide();
			}
		});
		
		$("#tbyy").change(function(){
			var tbyy=$(this).val();
			$("#otherTbyy").val("");
			//其他原因
			if(tbyy=="A000"){
				$("#otherTbyyTr").show();
			}else{
				$("#otherTbyyTr").hide();
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
	
	
				//获取元素的纵坐标 
	function getTop(e){
		var Top=e.offsetTop; 
		if(e.offsetParent!=null) Top+=getTop(e.offsetParent); 
		return Top; 
	} 
	//获取元素的横坐标 
	function getLeft(e){
		var Left=e.offsetLeft-2; 
		if(e.offsetParent!=null) Left+=getLeft(e.offsetParent); 
		return Left; 
	}
	
	
	function showDiv(){
		var tbyy=document.getElementById("tbyy");
		var topPositon=getTop(tbyy);
		var leftPositon=getLeft(tbyy);
		
		var tbyyDiv=document.getElementById("tbyyDiv")
		tbyyDiv.style.top=topPositon+"px";
		tbyyDiv.style.left=leftPositon+"px";
		tbyyDiv.style.display="block";
		
	}
	
	function hideTbyyDiv(){
		$("#tbyyDiv").hide();
	}
	
	function checkboxclick(obj){
		var isCheck=obj.getAttribute("checked");
		var tbyyValue=obj.value;
		if(isCheck=="true" || isCheck==true){
			if(tbyyValue=="A000"){
				
				$("#checkboxDiv :checkbox").not($(obj)).removeAttr("checked");
				
				var otherTbyyDiv=document.getElementById("otherTbyyDiv");
				var otherTbyy=document.getElementById("otherTbyy");
				otherTbyyDiv.style.display="block";
				otherTbyy.value="";
			}else{
				$("#tbyy_A000").removeAttr("checked");
				var otherTbyyDiv=document.getElementById("otherTbyyDiv");
				var otherTbyy=document.getElementById("otherTbyy");
				otherTbyyDiv.style.display="none";
				otherTbyy.value="";
			}
			
		}else{
			var otherTbyyDiv=document.getElementById("otherTbyyDiv");
			var otherTbyy=document.getElementById("otherTbyy");
			otherTbyyDiv.style.display="none";
			otherTbyy.value="";
		}
	}
	
	
	function chooseButton(){
		//选中的退办原因
		var chooseTbyy=$("#checkboxDiv :checkbox:checked");
		//是否选择其他退办原因
		var isOtherTbyy=$("#tbyy_A000").attr("checked");
		if(chooseTbyy.length>0){
			$("#showChooseTbyyDiv").html("");
			if(isOtherTbyy=="checked" || isOtherTbyy=="true" || isOtherTbyy==true ){
				var otherTbyy=$.trim($("#otherTbyy").val());
				if(otherTbyy==null || otherTbyy==""){
					alert("请填写其他退办原因");
					return false;
				}else{
					$("#showChooseTbyyDiv").html("其他原因:"+otherTbyy);
				}
			}else{
				for(var i=0;i<chooseTbyy.length;i++){
					var tempChooseTbyy=$(chooseTbyy.get(i)).clone();
					var tbyyText=$("<label>"+tempChooseTbyy.attr("tbyyText")+"</label>")
					$("#showChooseTbyyDiv").append(tempChooseTbyy);
					$("#showChooseTbyyDiv").append(tbyyText);
				}
				$("#showChooseTbyyDiv :checkbox").attr("disabled","disabled");
			}
			hideTbyyDiv();
		}else{
			alert("未选择任何退办原因");
			return false;
		}
	}
	
	function clearChoose(){
		$("#checkboxDiv :checkbox").removeAttr("checked");
		var otherTbyyDiv=document.getElementById("otherTbyyDiv");
		var otherTbyy=document.getElementById("otherTbyy");
		otherTbyyDiv.style.display="none";
		otherTbyy.value="";
		$("#otherTbyy").val();
		
		//清空已选退办原因
		$("#showChooseTbyyDiv").html("");
		
	}
	
	
</script>
	<body>
	  <div style="width:100%;" >
	  
	    <table class="datalist" width="100%" border="0" cellpadding="0" cellspacing="0">
			  <tr>
			    <td class="tds1" style="text-align: right;width:80px;">审核意见：</td>
			    <td class="tds2" style="text-align: left;">
			    	<input class="shztRadio" type="radio" name="shzt" value="2" />通过
			    	<input class="shztRadio" type="radio" name="shzt" value="CQ" />退办
			    </td>
			  </tr>
			  <tr id="tbyyTr" style="display: none;">
			    <td class="tds1" style="text-align: right;width:80px;">退办原因：</td>
			    <td class="tds2" style="text-align: left;" >
			    	<div id="checkboxDiv" style="padding:5px; overflow-y:scroll">
						<s:if test='#request.tbyyMap!=null && #request.tbyyMap.size()>0 '>
							<s:iterator value="#request.tbyyMap" var="tempTbyyMap">
								<div>
									<input type="checkbox" onclick="checkboxclick(this);" class="tbyy" id="tbyy_${tempTbyyMap.key}" value="${tempTbyyMap.key}" tbyyText="${tempTbyyMap.value}" />
									<label>${tempTbyyMap.value}</label>
							   	</div>
							</s:iterator>
						</s:if>
					</div>
					<div id="otherTbyyDiv" style="display:none;">
				    	<textarea id="otherTbyy" cols="35" rows="5"></textarea>
				    </div>
			    </td>
			  </tr>
			  <tr>
			    <td style="text-align:center;" colspan="2">
			    	 <input type="button" id="saveButton" class="bnt" style="width: 80px;" value="提交" />
			    	 <input type="button" id="cancleButton" class="bnt" style="width: 80px;"  value="返回" />
			    </td>
			  </tr>

		</table>
	
	   </div>
	   

	</body>
</html>