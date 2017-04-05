<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<title>申请/认证图片</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/jquery-1.8.2.js"></script>

<style> 
	#ggzImage1{display: none;}
</style>
</head>
	<body>
	<div align="center" >
	   	<table width="600" border="0" cellpadding="0" cellspacing="0"  style="margin-top:100px;">
			<tr>
				<td>
					<img id="ggzImage" style="width:595px; height:842px;"  />
				</td>
			</tr>
	   </table>
   </div>
	</body>
<script type="text/javascript">
	var photoType=requet("photoType");
	var sq=requet("sq");
	function initalImg(){
		 if(photoType!=null && photoType!=""){
			 if(sq!=null&&sq!=""){
				var url="<%=request.getContextPath()%>/servlet/sfrzcjImageServlet?pid="+photoType;
				document.getElementById("ggzImage").src=url;
			}else{
				var url="http://100.100.21.61/cmp_new/view_pic.asp?efid="+photoType;
				document.getElementById("ggzImage").src=url;
			}
		}else{
			alert("图片类型为空，没有您要打印的图片");
		}
		window.print();
		return false;
	}
	function requet(paras){
		var url=location.href;
		var paraString=url.substring(url.indexOf("?")+1,url.length).split("&");
		var paraObj={}
		for(i=0;j=paraString[i];i++){
			paraObj[j.substring(0,j.indexOf("=")).toLowerCase()]=j.substring(j.indexOf("=")+1,j.length);
		}
		var returnValue=paraObj[paras.toLowerCase()];
		if(typeof(returnValue)=="undefined"){
			return "";			
		}else{
			return returnValue;
		}
	}
	setTimeout(function(){
		initalImg();
	},100);
		
	
</script>
</html>