<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<title>广告证图片</title>

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
	
	.error{
		width:70px;
		color: red;
		font-size: 12px;
		float: left;
	}
</style>
</head>

	<body>
	   <div style="width:100%;text-align: center;" >
	  		<img id="ggzImage" style="width:562px; height:785px;"  />
	   </div>
	</body>
<script type="text/javascript">
	

	var lsh=window.dialogArguments;
	
	function initalImg(){
		if(lsh!=null && lsh!=""){
			var currentTime=new Date().getTime();
			//alert(lsh+"--"+currentTime);
			//var url="<%=request.getContextPath()%>/servlet/createBazServlet?lsh="+lsh;
			var url="<%=request.getContextPath()%>/gjgg/ggz_getBazPic.action?lsh="+lsh;
			document.getElementById("ggzImage").src=url;
		}else{
			alert("流水号为空,无法查询广告证");
		}
	}
	
	
	setTimeout(function(){
		initalImg();
	},100);
		
	
</script>
</html>