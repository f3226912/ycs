<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<title>按流水号查看公交客运车辆车身颜色变更业务-详细</title>

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
            sW: 290,
            sH: 250,
            lW: 580,
            lH: 500,
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
		width:70px;
		color: red;
		font-size: 12px;
		float: left;
	}
</style>
</head>
<script type="text/javascript">  


	//流水号
	var lsh = window.dialogArguments;
	//查询车档镜像库是否有数据
	var nodata=$.trim(${resultMap.nodata});
	
	var mac="";
	
	$(function(){
		
		
		$("#tbyy").focus(function(){
			showDiv();
		});
		
		$("#cancelButton").click(function(){
			window.close();
		});
		
	})
	
	
	//获取元素的纵坐标 
	function getTop(e){
		var Top=e.offsetTop; 
		if(e.offsetParent!=null) Top+=getTop(e.offsetParent); 
		return Top; 
	} 
	//获取元素的横坐标 
	function getLeft(e){ 
		var Left=e.offsetLeft; 
		if(e.offsetParent!=null) Left+=getLeft(e.offsetParent); 
		return Left; 
	} 
	
	
	function showDiv(){
		var tbyy=document.getElementById("tbyy");
		var topPositon=getTop(tbyy)-215;
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
		   <s:if test='#request.busGgjgclsb!=null  '>
			  <tr>
			    <td class="tds1" style="text-align: right;width:80px;">号牌号码：</td>
			    <td class="tds2" style="text-align: left;width:80px;">
			    	<!-- 广告喷涂业务流水号 -->
			    	<input type="hidden" id="lsh" value="${request.busGgjgclsb.lsh}" />
			    	<!-- 广告喷涂业务对应的公交公司id -->
			    	<input type="hidden" id="gjgsid" value="${request.busGgjgclsb.gjgsid}" />
			    	<!-- 公交车序号 -->
			    	<input type="hidden" id="xh" value="${request.currentBus.xh}" />
			    	<input type="text" id="hphm" value="${request.currentBus.hphm}" readonly="readonly"/>
			    	
			    </td>
			    <td class="tds1" style="text-align: right;width:80px;">号牌种类：</td>
			    <td class="tds2" style="text-align: left;width:240px;">
			    <input type="text" id="hpzl" value="${request.currentBus.hpzl}" readonly="readonly"/>
			    	
			    </td>
			  </tr>
			  <tr>
			  	 <td class="tds1" style="text-align: right;width:80px;">公交公司：</td>
			     <td class="tds2" style="text-align: left;width:80px;">
			    	<input type="text" name="busLine" id="busLine" value="${request.busBase.gjgsmc}" readonly="readonly" />
			     </td>
			     <td class="tds1" style="text-align: right;width:80px;">广告机构：</td>
			    <td class="tds2" style="text-align: left;width:240px;">
			    	<input type="text" name="busLine" id="busLine" value="${request.busGgjgclsb.ggjgmc}" readonly="readonly" />
			    </td>
			  </tr>
			  <tr>
			  	 <td class="tds1" style="text-align: right;width:80px;">广告审批号：</td>
			     <td class="tds2" style="text-align: left;width:80px;">
			    	<input type="text" name="busLine" id="busLine" value="${request.busGgjgclsb.ggsph}" readonly="readonly" />
			     </td>
			     <td class="tds1" style="text-align: right;width:80px;">车辆类型：</td>
			    <td class="tds2" style="text-align: left;width:240px;">
			    	<s:select id="cllx"
			    		theme="simple"
			    		cssStyle="width:155px;" 
						list="#request.cllxMap" 
						listKey="key"
						listValue="value"
						disabled="true"
						headerKey="" 
						headerValue="--请选择车辆类型--"
						value="#request.currentBus.cllx"
						>
					</s:select>
			    </td>
			  </tr>
			  <tr>
			    <td class="tds1" style="text-align: right;width:80px;">车架号：</td>
			    <td class="tds2" style="text-align: left;width:240px;">
	    			<input type="text" name="clsbdh" id="clsbdh" value="${request.currentBus.clsbdh}" readonly="readonly"/>
			    </td>
			   <td class="tds1" style="text-align: right;width:80px;">代办员：</td>
			    <td class="tds2" style="text-align: left;width:240px;" colspan="3">
	    			<s:if test='#request.busGgjgdlrs!=null '>
		    			<s:select id="dby"
				    		theme="simple"
				    		cssStyle="width:155px;" 
							list="#request.busGgjgdlrs"
							listKey="xh"
							listValue="xm"
							headerKey="" 
							headerValue="--请选择代办员--"
							disabled="true"
							value="#request.busGgjgclsb.dlrxh"
							>
						</s:select>
	    			</s:if>
			    </td>
			  </tr>
			  
			  <tr>
			  	<td class="tds1" style="text-align: right;width:80px;">左前45°：</td>
			  	<td style="text-align: left;" >
			  		<DIV class="zoom_no_lbox"  >
				  		<img alt="左前45°图片" title="左前45°图片" id="zqImg" style="width: 290px;height: 250px;"  />
			  		</DIV>
			  	</td>
			  	<td class="tds1" style="text-align: right;width:80px;">右后45°：</td>
			  	<td style="text-align: left;" >
			  		<DIV class="zoom_no_lbox"  >
				  		<img alt="右后45°图片" title="右后45°图片"  id="yhImg" style="width: 290px;height: 250px;"  />
			  		</DIV>
			  	</td>
			  </tr>
			  <tr>
			    <td class="tds1" style="text-align: right;width:80px;">审核结果：</td>
			    <td class="tds2" style="text-align: left;" colspan="3">
			    
			    	<s:if test='#request.busGgjgclsb.zt=="0" '>待公交公司审核</s:if>
			    	<s:if test='#request.busGgjgclsb.zt=="1" '>待车管审核</s:if>
			    	<s:if test='#request.busGgjgclsb.zt=="2" '>车管审核成功</s:if>
			    	<s:if test='#request.busGgjgclsb.zt=="E" '>办结</s:if>
			    	<s:if test='#request.busGgjgclsb.zt=="CQ" '>车管退办</s:if>
			    	<s:if test='#request.busGgjgclsb.zt=="Q" '>公交公司退办</s:if>

			    </td>
			  </tr>
			  <tr id="tbyyTr" <s:if test='#request.busGgjgclsb.ycfpms==null || #request.busGgjgclsb.ycfpms=="" '>style="display: none;"</s:if>>
			    <td class="tds1" style="text-align: right;width:80px;">退办原因：</td>
			    <td class="tds2" style="text-align: left;" colspan="3">
					<div id="tbyyDiv" style="width:100%; height:20%;">
						${#request.busGgjgclsb.ycfpms}
					</div>
			    </td>
			  </tr>
			  <tr>
			    <td class="tds" style="text-align: center;" colspan="4">
				    <input type="button" id="cancelButton" class="bnt" style="width: 80px;"  value="关闭" />
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
<script type="text/javascript">

	function initalImg_left(){
		var lsh=$.trim("${request.busGgjgclsb.lsh}");
		if(lsh!=null && lsh!=""){
			var currentTime=new Date().getTime();
			//var url="<%=request.getContextPath()%>/servlet/getimage?lsh="+lsh+"&position=left";
			var url="<%=request.getContextPath()%>/gjgg/ggz_getImage.action?lsh="+lsh+"&position=left";;
			document.getElementById("zqImg").src=url;
		}
	}
	
	function initalImg_right(){
		var lsh=$.trim("${request.busGgjgclsb.lsh}");
		if(lsh!=null && lsh!=""){
			var currentTime=new Date().getTime();
			//var url="<%=request.getContextPath()%>/servlet/getimage?lsh="+lsh+"&position=right";
			var url="<%=request.getContextPath()%>/gjgg/ggz_getImage.action?lsh="+lsh+"&position=right";
			document.getElementById("yhImg").src=url;
		}
	}
	
	
	setTimeout(function(){
		initalImg_left();
	},100);
	
	setTimeout(function(){
		initalImg_right();
	},200);
	

</script>
</html>