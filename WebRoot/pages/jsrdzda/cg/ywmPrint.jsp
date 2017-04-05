<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<title>邮政编码</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/jquery-1.8.2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.PrintArea.js"></script>
<style>
	*{ margin:0 auto;}
	.xzyh{ width:98%; padding-bottom:40px; border:1px solid #aaccee;}
	.h1_t{ width:100%; height:23px; line-height:23px; background:url(<%=request.getContextPath()%>/images/table_abg.gif) repeat-x; font-size:14px; border-bottom:1px solid #aaccee;}
	.table_xx{ width:80%; margin-top:40px; background:#f6f6f6;}
	table,table td, table th{border:1px solid #aaccee;border-collapse:collapse;  word-break:break-all;word-wrap:break-word;}
	td{ height:26px; font-size:12px;}
	.table_t{background:url(images/table_abg.gif) repeat-x; font-size:14px; height:23px; letter-spacing:1.5px; font-weight:bold; text-align:center;}
	.td_r{ text-align:right;}
	.td_l {text-align:left;}
	.text_w{ width:400px;}
	.xzq{ width:400px;}
	.wby{ width:400px; height:100px;}
	.bnt_a{ width:90px; height:23px; border:none; background:url(<%=request.getContextPath()%>/images/bnt_1.jpg) no-repeat; color:#FFFFFF; font-weight:bold; cursor:pointer;}
	.djan{ text-align:center;}
	.xzyh span{ color:#FF0000;}
	.btn{width:60px; height:30px; border:none; color:#FFFFFF; font-weight:bold; cursor:pointer;}
</style>
<script type="text/javascript">
	var yzbm=${request.dzxxb.yzbm};
	if(yzbm!=null && yzbm!=""){
	   window.print(); 
	   
	   setTimeout(function(){
		checkPrint();
	   },100);
	}
		
	function checkPrint(){	
	  window.close();
	}
</script>
</head>
	<body style="text-align: center;">
	  	  <br>
  	  	  <br>	
  	  	  <br>
   	   <div id="printScope" >
  		  <s:if test='#request.dzxxb.yzbm!=null && #request.dzxxb.yzbm!="" '>
  		     <p style="font-size: 16px">姓名:${request.dzxxb.xm }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;身份证号:${request.dzxxb.sfzmhm }</p>
  		     <br/>
	  		 <img style="width:294px; height:80px;" src="<%=request.getContextPath()%>/servlet/yzbmServlet?yzbm=${request.dzxxb.yzbm}" />
  	  	  </s:if>

  	 </div>
  	 
    </body>
</html>