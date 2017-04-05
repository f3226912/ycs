<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<title>广告证详细1</title>

<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/jquery-1.8.2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/gjgggl/jquery.jPrintArea.js"></script>

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
<script type="text/javascript">
	
	function cancel(){
		window.close();
	}
	
</script>
</head>

	<body>
		  <div style="width:100%;" >
		  
		    <table id="showData" class="datalist" width="100%" border="0" cellpadding="0" cellspacing="0">
		    	
	    	  <tr>
	    	  	<td class="tds1" style="text-align: right;width:80px;">流水号：</td>
			    <td class="tds2" style="text-align: left;width:80px;" colspan="3">
			    	<input type="text" id="lsh" readonly="readonly" value="${request.busGgjgclsb.lsh}" />
			    </td>
	    	  </tr>
			  <tr>
			    <td class="tds1" style="text-align: right;width:80px;">号牌号码：</td>
			    <td class="tds2" style="text-align: left;width:80px;">
			    	<input type="text" id="hphm" readonly="readonly" value="${request.busVehicleBase.hphm}" />
			    </td>
			    <td class="tds1" style="text-align: right;width:80px;">号牌种类：</td>
			    <td class="tds2" style="text-align: left;width:240px;">
			    	<s:if test='#request.hpzlMap!=null '>
				    	<s:select id="hpzl"
				    		theme="simple"
							list="#request.hpzlMap"
							listKey="key" 
							listValue="value"
							headerKey="" 
							headerValue="--请选择号牌种类--"
							disabled="true"
							value="#request.busVehicleBase.hpzl"
							>
						</s:select>
			    	</s:if>
			    	<s:else>
			    		<input type="text" disabled="disabled" />
			    	</s:else>
			    </td>
			  </tr>
			  <tr>
			  	 <td class="tds1" style="text-align: right;width:80px;">公交公司：</td>
			    <td class="tds2" style="text-align: left;width:240px;">
	    			<input type="text" name="gjgsmc" id="gjgsmc" value="${request.gjgsmc}" readonly="readonly"/>
			    </td>
			  	 <td class="tds1" style="text-align: right;width:80px;">公交线路：</td>
			     <td class="tds2" style="text-align: left;width:80px;">
			    	<input type="text" name="busLine" id="busLine" value="${request.busVehicleBase.busLine}" readonly="readonly" />
			     </td>
			     
			  </tr>
			  <tr>
			    <td class="tds1" style="text-align: right;width:80px;">车架号：</td>
			    <td class="tds2" style="text-align: left;width:240px;">
	    			<input type="text" name="clsbdh" id="clsbdh" value="${request.busVehicleBase.clsbdh}" readonly="readonly"/>
			    </td>
			    <td class="tds1" style="text-align: right;width:80px;">车辆型号：</td>
			    <td class="tds2" style="text-align: left;width:240px;">
	    			<input type="text" name="clxh" id="clxh" value="${request.busVehicleBase.clxh}" readonly="readonly"/>
			    </td>
			  </tr>
			  
			  <tr>
			   <td class="tds1" style="text-align: right;width:80px;">车辆类型：</td>
			    <td class="tds2" style="text-align: left;width:240px;">
			    	<s:if test='#request.cllxMap!=null '>
				    	<s:select id="cllx"
				    		theme="simple"
				    		cssStyle="width:155px;" 
							list="#request.cllxMap" 
							listKey="key"
							listValue="value"
							disabled="true"
							headerKey="" 
							headerValue="--请选择车辆类型--"
							value="#request.busVehicleBase.cllx"
							>
						</s:select>
			    	</s:if>
			    	<s:else>
			    		<input type="text" disabled="disabled" />
			    	</s:else>
			    </td>
			    <td class="tds1" style="text-align: right;width:80px;">广告审批号：</td>
			    <td class="tds2" style="text-align: left;width:240px;">
	    			<input type="text" name="ggsph" id="ggsph" value="${ request.busGgjgclsb.ggsph}" readonly="readonly"/>
			    </td>
			   
			  </tr>
			  <tr>
			  	<td class="tds1" style="text-align: right;width:80px;">左前45°：</td>
			  	<td style="text-align: left;" >
			  		<s:if test='#request.isFirst!="" && #request.busGgjgclsb!=null && #request.busGgjgclsb.lsh!=null && #request.busGgjgclsb.lsh!="" '>
				    	 <DIV class="zoom_no_lbox"  >
					    	 <img alt="左前45°图片" title="左前45°图片" id="zqImg" style="width: 290px;height: 250px;" />
				    	 </DIV>
				    </s:if>
			  	</td>
			  	<td class="tds1" style="text-align: right;width:80px;">右后45°：</td>
			  	<td style="text-align: left;" >
			  		<s:if test='#request.busGgjgclsb!=null && #request.busGgjgclsb.lsh!=null && #request.busGgjgclsb.lsh!="" '>
				    	<DIV class="zoom_no_lbox"  >
					    	<img alt="右后45°图片" title="右后45°图片"  id="yhImg" style="width: 290px;height: 250px;" />
				    	</DIV>
				    </s:if>
			  	</td>
			  </tr>
			   <tr>
			  	<td class="tds1" style="text-align: right;width:80px;">广告证：</td>
			  	<td style="text-align: left;" colspan="3">
			  		<s:if test='#request.busGgjgclsb!=null && #request.busGgjgclsb.lsh!=null && #request.busGgjgclsb.lsh!="" '>
				  		<img id="ggzImg"  />
			  		</s:if>
			  	</td>
			  </tr>
			  <tr>
			    <td class="tds" style="text-align: center;" colspan="4">
				    <input type="button" class="bnt" onclick="cancel();"  style="width: 80px;" value="关闭" />
			    </td>
			  </tr>
			</table>
		
		   </div>

</body>
<SCRIPT type="text/javascript">
	function initalImg_left(){
		var lsh=$.trim("${request.busGgjgclsb.lsh}");
		var currentTime=new Date().getTime();
		//var url="<%=request.getContextPath()%>/servlet/getimage?lsh="+lsh+"&position=left";
		var url="<%=request.getContextPath()%>/gjgg/ggz_getImage.action?lsh="+lsh+"&position=left";;
		document.getElementById("zqImg").src=url;
	}
	
	function initalImg_right(){
		var lsh=$.trim("${request.busGgjgclsb.lsh}");
		var currentTime=new Date().getTime();
		//var url="<%=request.getContextPath()%>/servlet/getimage?lsh="+lsh+"&position=right";
		var url="<%=request.getContextPath()%>/gjgg/ggz_getImage.action?lsh="+lsh+"&position=right";
		document.getElementById("yhImg").src=url;
	}
	
	function initalImg_ggz(){
		var lsh=$.trim("${request.busGgjgclsb.lsh}");
		var currentTime=new Date().getTime();
		var url="<%=request.getContextPath()%>/gjgg/ggz_getBazPic.action?lsh="+lsh;
		document.getElementById("ggzImg").src=url;
	}
	
	setTimeout(function(){
		initalImg_left();
	},100);
	
	setTimeout(function(){
		initalImg_right();
	},200);
	
	setTimeout(function(){
		initalImg_ggz();
	},300);
	
</SCRIPT>
</html>