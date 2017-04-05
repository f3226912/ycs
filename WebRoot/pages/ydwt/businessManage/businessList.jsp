<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="com.ycszh.common.PageTag" prefix="pt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<title>委托异地年检业务信息管理</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/content.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/jquery-1.8.2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/jquery-ui-1.9.1.custom.js"></script>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/My97DatePicker/WdatePicker.js"></script>
<style> 
	html{ 
		overflow:scroll;
		scrollbar-base-color:#c7e5ff;
		scrollbar-track-color:#FFFFFF;
	} 
</style>
</head>
<script type="text/javascript">   
   $(document).ready(function(){
           //多条件查询
        $('#queryBtn').click(function(){
            var  url="<%=request.getContextPath()%>/ydwt/ydwt_businessManage.action";
            $("#queryForm").attr("action",url);	
	        $("#queryForm").submit();
	    });
	     $('#queryExcel').click(function(){
            var  url="<%=request.getContextPath()%>/ydwt/ydwt_exportStatis.action";
            $("#queryForm").attr("action",url);	
	        $("#queryForm").submit();
	    });
	});
</script>
	<body>
	 <form action="" id="queryForm" name="queryForm" method="post">
		<div style="width:95%;margin: 0 auto;">
		   <table  class="table1"  width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
			  <tr>
			    <td class="th1" height="25" colspan="4">异地委托年检业务信息查询</td>
			  </tr>
			  <tr>
			    <td class="tds" style="text-align: right;">号牌号码:</td>
			    <td class="tds" style="text-align: left;"><input type="text" name="hphm" id="hphm" value="${pb.hphm}"/></td>
			    <td class="tds" style="text-align: right;">号牌种类:</td>
			    <td class="tds" style="text-align: left;"><s:select theme="simple" name="hpzl" id="hpzl" list="#request.hpzlList" value="#request.pb.hpzl" listKey="dmz" listValue="dmsm1" headerKey="--" headerValue="--请选择--" /></td>
			  </tr>
			  <tr>
			    <td class="tds" style="text-align: right;">批次号:</td>
			    <td class="tds" style="text-align: left;"><input type="text" name="pch" id="pch" value="${pb.pch}"/></td>
		        <td class="tds" style="text-align: right;">业务状态:</td>
			    <td class="tds" style="text-align: left;">
			     <s:select theme="simple" cssStyle="width:120px" name="sbzt" id="sbzt" list="#{'0':'待初审','1':'已初审','2':'已复核','3':'通知书已接收 ','4':'快递已寄出','CT':'车管所退办','YT':'邮政退办','QT':'个人取消申请','QQ':'个人取消业务','CC':'车管业务退办'}" value="#request.pb.sbzt" headerKey="--" headerValue="--请选择--"/>
			    </td>
			  </tr>
			  <tr>
			    <td class="tds" style="text-align: right;">身份证号码:</td>
			    <td class="tds" style="text-align: left;"><input type="text" name="sfzh" id="sfzh" value="${pb.sfzmhm}"/></td>
			    <td class="tds" style="text-align: right;">申报时间:</td>
			    <td class="tds" style="text-align: left;">
			    <input type="text" name="startTime" style="width: 120px" id="startTime" class="Wdate" value="${startTime}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
			          至
			    <input type="text" name="endTime"   style="width: 120px" id="endTime" class="Wdate" value="${endTime}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
			    </td>
			  </tr>
			  <tr>
			    <td colspan="4"  class="tds">
			    <input type="button" name="queryBtn" id="queryBtn" value="检索" class="bnt" style="cursor:pointer;"/> &nbsp;
			    <input type="button" name="queryExcel" id="queryExcel" value="导出" class="bnt" style="cursor:pointer;"/> </td>
			  </tr>
		  </table> 
		  </div>
		  <div style="width:95%; margin-top: 10px;margin: 0 auto;" >
		    <table class="datalist" id="tableList" width="100%" border="0" cellpadding="0" cellspacing="0">
			  <tr class="tr1">
			    <th>序号</th>
			    <th>号牌号码</th>
			    <th>号牌种类</th>
			    <th>车驾后4</th>
			    <th>身份证号码</th>
			    <th>姓名</th>
			    <th>手机号码</th>
			    <th>受托核发检验合格标志机构</th>
			    <th>状态</th>
			    <th>批次</th>
			    <th>操作</th>
			  </tr>
			 <s:if test="#request.spbList.size>0" >
		        <s:iterator id="order" value="#request.spbList" status="st">
				  <tr id="${order.hphm}" class="<s:if test="#st.odd == false">altrow</s:if>">
				    <td>${st.index+1}</td>
				    <td>${order.hphm}</td>
				    <td>${order.hpzl}</td>
				    <td>${order.clsbdh}</td>
				    <td>${order.sfzmhm}</td>
				    <td>${order.syr}</td>
				    <td>${order.yjLxdh}</td>
				    <td>${order.stjg}</td>
				    <td><s:if test="#order.sbzt==0">待初审 </s:if>
				    <s:if test="#order.sbzt==1">已初审 </s:if>
				    <s:if test="#order.sbzt==2">已复核</s:if>
				    <s:if test="#order.sbzt==3">通知书已接收 </s:if>
				    <s:if test="#order.sbzt==4">快递已寄出 </s:if>
				    <s:if test="#order.sbzt=='CT'">车管所退办</s:if>
				    <s:if test="#order.sbzt=='YT'">邮政退办 </s:if>
				    <s:if test="#order.sbzt=='QT'">个人取消申请 </s:if>
				    <s:if test="#order.sbzt=='QQ'">个人取消业务 </s:if>
				    <s:if test="#order.sbzt=='CC'">车管业务退办 </s:if>
				    </td>
				    <td>${order.pch}</td>
				    <td><a href="<%=request.getContextPath()%>/ydwt/ydwt_displayDetail.action?id=${order.id}">详情</a></td>
				  </tr>
				 </s:iterator>
			  </s:if>
			  <s:else>
			    <tr>
				  <td colspan="11" align="center">
					<span style="color: red">没有找到相关数据<input type="hidden" value="0" id="itemSize"/> </span>
				  </td>
				</tr>
			  </s:else>	  
			</table>
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				style="padding-top: 5px;">
				<pt:write uri="${map.uri}" pagesize="${map.pagesize}" rscount="${map.rscount}" currentpage="${map.currentpage}"></pt:write>
			</table>
		     </div>
		 </form>
</body>
</html>