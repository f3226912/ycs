<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<title>互联网业务申报、办结、退办数据统计</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/content.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/jquery-1.8.2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/jquery-ui-1.9.1.custom.js"></script>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/My97DatePicker/WdatePicker.js"></script>
</head>
<script type="text/javascript">   
   $(document).ready(function(){
           //多条件查询
        $('#queryBtn').click(function(){
            var  url="<%=request.getContextPath()%>/ydwt/ydwt_declareAndQuitStat.action";
            $("#queryForm").attr("action",url);	
	        $("#queryForm").submit();
	    });
	});
</script>
	<body>
	 <form action="" id="queryForm" name="queryForm" method="post">
      <div style="width:90%;margin: 0 auto;">
	   <table  class="table1"  width="98%" border="0" cellpadding="0" cellspacing="0">
	     <tr >
	       <td style="text-align: right;"> 申报时间:</td>
	       <td style="text-align: left;"><input type="text" name="startTime" id="startTime" class="Wdate" value="${startTime}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />至
	       <input type="text" name="endTime" id="endTIme" class="Wdate" value="${endTime}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
	       </td>
	       <td><input name="queryBtn" id="queryBtn" type="button"  value="查 询" class="bnt" style="cursor:pointer;"/></td>
	     </tr>
	   </table>
	   </div>
	    <div style="width:90%;height: 450px;overflow-y:auto;overflow-x:none; margin-top: 10px;margin: 0 auto;">
        <table width="98%"  class="datalist" >
		  <tr class="tr1">
		    <th>申报日期</th>
		    <th>申报总数</th>
		    <th>初审总数</th>
		    <th>复核总数</th>
		    <th>移交总数</th>
		    <th>车管退办总数</th>
		  </tr>
          <s:if test="#request.ydwtList.size>0" >
		         <s:iterator id="order" value="#request.ydwtList" status="st">
				  <tr class="<s:if test="#st.odd == false">altrow</s:if>">
				    <td>${order.lrsj}</td>
				    <td>${order.declareNum}</td>
				    <td>${order.firstTrialNum}</td>
				    <td>${order.recheckNum}</td>
				    <td>${order.moveNum}</td>
				    <td>${order.cgsQuitNum}</td>
				  </tr>
				 </s:iterator>
			  </s:if>
			  <s:else>
			    <tr>
				  <td colspan="10" align="center">
					<span style="color: red">没有找到相关数据<input type="hidden" value="0" id="itemSize"/> </span>
				  </td>
				</tr>
			  </s:else>	  
        </table>
        </div>
     </form>
</body>
</html>