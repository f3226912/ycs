<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<title>流水查询-详细</title>

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
	
	.tr1 {
		background: url(<%=request.getContextPath()%>/images/cxtjbj.gif) repeat;
		height: 15px;
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
		
		$("#cancelButton").click(function(){
			window.close();
		});
		
	})
	
</script>
	<body>
	  <div style="width:100%;" >
	  
	    <table class="datalist" width="100%" border="0" cellpadding="0" cellspacing="0">
		   <s:if test='#request.busGgjgclsb!=null  '>
	   		  <tr>
	    	  	<td colspan="4" class="tr1" style="font-weight: bold;" >申报信息</td>
	    	  </tr>
			  <tr>
			    <td class="tds1" style="text-align: right;width:80px;">号牌号码：</td>
			    <td class="tds2" style="text-align: left;width:80px;">
			    	<input type="text" name="hphm" id="hphm" value="${request.busGgjgclsb.hphm}" readonly="readonly" />
			    </td>
			    <td class="tds1" style="text-align: right;width:80px;">号牌种类：</td>
			    <td class="tds2" style="text-align: left;width:240px;">
			    	<input type="text" name="hpzl" id="hpzl" value="${request.busGgjgclsb.hpzl}" readonly="readonly" />
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
			    	<input type="text" name="busLine" id="busLine" value="${request.busGgjgclsb.ggsph}"  readonly="readonly"/>
			     </td>
			     <td class="tds1" style="text-align: right;width:80px;">车辆类型：</td>
			    <td class="tds2" style="text-align: left;width:240px;">
			    	<input type="text" name="cllx" id="cllx" value="${request.currentBus.cllx}"  readonly="readonly"/>
			    </td>
			  </tr>
			  <tr>
			    <td class="tds1" style="text-align: right;width:80px;">车架号：</td>
			    <td class="tds2" style="text-align: left;width:240px;">
	    			<input type="text" name="clsbdh" id="clsbdh" value="${request.currentBus.clsbdh}" readonly="readonly" />
			    </td>
			    <td class="tds1" style="text-align: right;width:80px;">代办员：</td>
			    <td class="tds2" style="text-align: left;width:240px;" colspan="3">
			    	<input type="text" name="dby" id="dby" value="${request.busGgjgclsb.dlrxh}" readonly="readonly" />
			    </td>
			  </tr>
			   <tr>
	    	  	<td colspan="4" class="tr1" style="font-weight: bold;" >审核信息</td>
	    	  </tr>
			  <tr>
			  	
			  	 <td class="tds1" style="text-align: right;width:80px;">审核人：</td>
			     <td class="tds2" style="text-align: left;width:80px;">
			    	<input type="text" name="busLine" id="busLine" value="${request.busGgjgclsb.ycmjxm}" readonly="readonly" />
			     </td>
			     <td class="tds1" style="text-align: right;width:80px;">审核时间：</td>
			    <td class="tds2" style="text-align: left;width:240px;">
			    	<s:if test="#request.busGgjgclsb!=null && #request.busGgjgclsb.ycmjrq!=null">
				    	<input type="text" name="shrq" id="shrq" value="<s:date name="#request.busGgjgclsb.ycmjrq" format="yyyy-MM-dd HH:mm:ss"/>" readonly="readonly" />
			    	</s:if>
			    </td>
			  </tr>
			  <tr>
			  	<td class="tds1" style="text-align: right;width:80px;">审核状态：</td>
			     <td class="tds2" style="text-align: left;width:80px;" colspan="3">
			    	<input type="text" name="busLine" id="busLine" value="${request.busGgjgclsb.ztMc}" readonly="readonly" />
			     </td>
			  </tr>
			   <tr>
	    	  	<td colspan="4" class="tr1" style="font-weight: bold;" >打印信息</td>
	    	  </tr>
			  <tr>
			  	 <td class="tds1" style="text-align: right;width:80px;">打印人：</td>
			     <td class="tds2" style="text-align: left;width:80px;">
			    	<input type="text" name="busLine" id="busLine" value="${request.busCertifyInfo.zzmjxm}" readonly="readonly" />
			     </td>
			     <td class="tds1" style="text-align: right;width:80px;">打印时间：</td>
			    <td class="tds2" style="text-align: left;width:240px;">
			    	<s:if test="#request.busCertifyInfo!=null && #request.busCertifyInfo.zzrq!=null">
				    	<input type="text" name="busLine" id="busLine" value="<s:date name="#request.busCertifyInfo.zzrq" format="yyyy-MM-dd HH:mm:ss"/>" readonly="readonly" />
			    	</s:if>
			    	<s:else>
			    		<input type="text" name="busLine" id="busLine" readonly="readonly" />
			    	</s:else>
			    </td>
			  </tr>
			  <tr>
	    	  	<td colspan="4" class="tr1" style="font-weight: bold;" >拍照信息</td>
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
				  		<img alt="右后45°图片" title="右后45°图片"  id="yhImg" style="width: 290px;height: 250px;" />
			  		</DIV>
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