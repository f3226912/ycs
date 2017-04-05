<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<title>资料图片</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/jquery-1.8.2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/jsrdzda/jquery.PrintArea.js"></script>
<style> 
	html{ 
		overflow:scroll;
		scrollbar-base-color:#c7e5ff;
		scrollbar-track-color:#FFFFFF;
	}
</style>
</head>
	<body>
	<div id="divScope" style="height:960px;width:100%;">	
	    <img id="picImage" style="height:960px;width:100%;"/>
	</div>	
	</body>
<script type="text/javascript">
	var picStr=window.dialogArguments;
	function initalImg(){
		if(picStr!=null && picStr!=""){
			var url="http://100.100.21.61/cmp_new/view_pic.asp?efid="+picStr;
			 document.getElementById("picImage").src=url;
			window.print();	
		}else{
			alert("图片为空,无法获取!");
		}
	}	
	setTimeout(function(){
		initalImg();	
	},100);
		
	
</script>
</html>