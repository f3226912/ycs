<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<title>广告证打印</title>

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
	
	.tr1 {
		background: url(<%=request.getContextPath()%>/images/cxtjbj.gif) repeat;
		height: 20px;
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

	<body>
		  <div style="width:95%;" >
		  
		    <table id="showData" class="datalist" width="100%" border="0" cellpadding="0" cellspacing="0">
		    	  <tr>
		    	  	<td colspan="4" class="tr1" style="font-weight: bold;" >广告证打印</td>
		    	  </tr>
		    	  <tr>
				    <td class="tds1" style="text-align: right;width:80px;">流水号：</td>
				    <td class="tds2" style="text-align: left;" colspan="3">
				    	<form id="querySubmit" action="<%=request.getContextPath()%>/gjgg/ggz_getBusGgjgclsb.action">
			    			<input type="text" name="lshStr" id="lshStr" value="${request.busGgjgclsb.lsh}" />
			    			<input type="hidden" name="qx" id="qx" value="${request.qx}" />
			    			<input type="hidden" name="isFirst" id="isFirst" value="false" />
			    			<input type="button" class="bnt" style="width: 80px;" onclick="query();" value="查询" />
			    			<s:if test='#request.isFirst=="false" '>
					    		<label style="color: red;">查无数据</label>
					    	</s:if>
					    	<s:if test='#request.queryResult!=null || #request.queryResult!="" '>
					    		<label style="color: red;">${request.queryResult}</label>
					    	</s:if>
				    	</form>
				    	
				    </td>
				  </tr>
		    	
				  <tr>
				    <td class="tds1" style="text-align: right;width:80px;">号牌号码：</td>
				    <td class="tds2" style="text-align: left;width:80px;">
				    	<!-- 广告喷涂业务流水号 -->
				    	<input type="hidden" id="lsh" value="${request.busGgjgclsb.lsh}" />
				    	<!-- 广告喷涂业务对应的公交公司id -->
				    	<input type="hidden" id="gjgsid" value="${request.busGgjgclsb.gjgsid}" />
				    	<!-- 公交车序号 -->
				    	<input type="hidden" id="xh" value="${request.busVehicleBase.xh}" />
				    	${request.busVehicleBase.hphm}
				    </td>
				    <td class="tds1" style="text-align: right;width:80px;">号牌种类：</td>
				    <td class="tds2" style="text-align: left;width:240px;">
				    	${request.hpzlStr}
				    </td>
				  </tr>
				  <tr>
				  	 <td class="tds1" style="text-align: right;width:80px;">公交公司：</td>
				    <td class="tds2" style="text-align: left;width:240px;">
				    	${request.gjgsmc}
				    </td>
				  	 <td class="tds1" style="text-align: right;width:80px;">公交线路：</td>
				     <td class="tds2" style="text-align: left;width:80px;">
				     	${request.busVehicleBase.busLine}
				     </td>
				  </tr>
				  <tr>
				    <td class="tds1" style="text-align: right;width:80px;">车架号：</td>
				    <td class="tds2" style="text-align: left;width:240px;">
				  	  ${request.busVehicleBase.clsbdh}
				    </td>
				    <td class="tds1" style="text-align: right;width:80px;">车辆型号：</td>
				    <td class="tds2" style="text-align: left;width:240px;">
				    	${request.busVehicleBase.clxh}
				    </td>
				  </tr>
				  
				  <tr>
				    <td class="tds1" style="text-align: right;width:80px;">车辆类型：</td>
				    <td class="tds2" style="text-align: left;width:240px;" >
				    	${request.cllxStr}
				    </td>
				    <td class="tds1" style="text-align: right;width:80px;">广告审批号：</td>
				    <td class="tds2" style="text-align: left;width:240px;">
				    	${request.busGgjgclsb.ggsph}
				    </td>
				  </tr>
				  <tr>
				  	<td class="tds1" style="text-align: right;width:80px;">左前45°：</td>
				  	<td style="text-align: left;" >
				  		<s:if test='#request.isFirst!="" && #request.busGgjgclsb!=null && #request.busGgjgclsb.lsh!=null && #request.busGgjgclsb.lsh!="" '>
					    	 <DIV class="zoom_no_lbox"  >
						    	 <img alt="左前45°图片" title="左前45°图片" id="zqImg" style="width: 290px;height: 250px;"  />
					    	 </DIV>
					    </s:if>
				  	</td>
				  	<td class="tds1" style="text-align: right;width:80px;">右后45°：</td>
				  	<td style="text-align: left;" >
				  		<s:if test='#request.isFirst!="" && #request.busGgjgclsb!=null && #request.busGgjgclsb.lsh!=null && #request.busGgjgclsb.lsh!="" '>
					    	 <DIV class="zoom_no_lbox"  >
						    	<img alt="右后45°图片" title="右后45°图片"  id="yhImg" style="width: 290px;height: 250px;"  />
					    	 </DIV>
					    </s:if>
				  	</td>
				  </tr>
				  <tr>
				    <td class="tds" style="text-align: center;" colspan="4">
				    	<input id="showPicArea" type="button" class="bnt"  style="width: 80px;display: none;" onclick="showPrintAreaToShow();" isShow="false"  value="广告证预览" <s:if test='#request.isFirst=="false" || #request.isFirst=="" '>disabled="disabled"</s:if> />
					    <input  id="printButton" type="button" class="bnt"  style="width: 80px;display: none;" onclick="printPic();" value="打印" <s:if test='#request.isFirst=="false" || #request.isFirst=="" || (#request.queryResult!=null && #request.queryResult!="" ) '>disabled="disabled"</s:if> />
				    </td>
				  </tr>
			</table>
			<div id="myPrintArea" style="display: none;">
			  	  <img id="ggzImg" style="width:662px; height:885px;" />
	  		</div>
		
		   </div>

</body>
<script type="text/javascript">

	var lsh=$.trim("${request.busGgjgclsb.lsh}");
	var mac="";
	
	function query(){
		var lshStr=$.trim(document.getElementById("lshStr").value);
		if(typeof(lshStr)!="undefined" && lshStr!=null && lshStr!=""){
			document.getElementById("querySubmit").submit();
		}
	}
	
	function showPrintAreaToShow(){
		if(lsh!=null && lsh!=""){
			var uri='<%=request.getContextPath()%>' +"/pages/gjgggl/ggzxxgl/showGgz.jsp";
			window.showModalDialog(uri,lsh,"dialogWidth:600px;dialogHeight:800px;help:no;status:no;scroll:no;location:no;resizable:yes");
		}
	}
	
	function addBusCertifyInfo(){
		var lsh=$.trim($("#lsh").val());
		if(typeof(lsh)!="undefined" &&　lsh!=null && lsh!=""){
			$.ajax({
				url: "<%=request.getContextPath()%>/gjgg/ggz_addBusCertifyInfo.action",
	  			type:'post',
	  			cache: false,
	  			async: true,
	  			dataType:'text',
	  			data: {
		  			'lsh':lsh,
		  			'mac':mac
		  		},
	  			error:function(){
		  			alert("请求异常,请重试!");
		  		},
	  			success:function(data){
		  			var result=$.trim(data);
		  			var index=result.indexOf("-");
		  			if(index==0 ){
		  				alert(result.substring(1,result.length));
			  		}else{
			  			//alert("操作成功!");
			  			window.location.reload();
			  		}
	  			}
			});
			
		}else{
			alert("操作失败:流水号为空");
		}
	}
	
	
	function printPic(){
		
		if(confirm("确定打印广告证?")){
			document.getElementById("myPrintArea").style.display="block";
			
			var headstr = "<html><head><title></title></head><body>";
			var footstr="</body>";
			//要打印的数据
			var newstr = document.getElementById("myPrintArea").innerHTML;
			//旧的网页数据
			var oldstr=document.body.innerHTML;
			document.body.innerHTML = headstr+newstr+footstr;
			//parent.window.print();
			window.print();
			
			document.body.innerHTML = oldstr;
			document.getElementById("myPrintArea").style.display="none";
			
			//window.close();
			
			setTimeout(function(){
				addBusCertifyInfo();
			},2500);
			
		}
	}
	
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
	
	function initalImg_ggz(){
		var lsh=$.trim("${request.busGgjgclsb.lsh}");
		if(lsh!=null && lsh!=""){
			var currentTime=new Date().getTime();
			var url="<%=request.getContextPath()%>/gjgg/ggz_getBazPic.action?lsh="+lsh;
			document.getElementById("ggzImg").src=url;
			setTimeout(function(){
				$("#showPicArea").show();
				$("#printButton").show();
			},100);
			
		}
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
		
	
</script>
</html>