<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="Expires" CONTENT ="0">
<meta http-equiv="Cache-Control" CONTENT="no-cache">
<meta http-equiv="Pragma" CONTENT="no-cache"> 
<title>异地委托详情</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/content.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/jquery-1.8.2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/jquery-ui-1.9.1.custom.js"></script>
	<style> 
		html{ 
			overflow:scroll;
			scrollbar-base-color:#c7e5ff;
			scrollbar-track-color:#FFFFFF;
		} 
		.th1 {
			background: url(../images/cxtjbj.gif) repeat-x;
			font-size: 14px;
			font-weight: bold;
		}
		.tdl {
			text-align: left;
		}
		.tds {
			background: #eaf5ff;
			font-weight: normal;
			font-size: 14px;
	}
	</style>
</head>
<body>
      <table class="table1" align="center" width="80%" height="400x" border="0" cellpadding="0" cellspacing="0">
			   <tr>
			     <th class="th1"  colspan="4" align="left" height="25px">&nbsp;&nbsp;&nbsp;&nbsp;基本信息</th>
			   </tr>
			   <tr>
			    <td class="tds"	style="text-align: right;">车辆所有权 :</td>
			    <td class="tdl" style="text-align: left;" colspan="3">
			    <s:if test='pb.clsyq=="0"'>
			     <input type="radio" name="radioItem" id="radioItem" value="0" checked="checked"/>
			    </s:if>
			    <s:else>
			      <input type="radio" name="radioItem" id="radioItem" value="0" disabled="disabled"/>
			    </s:else>个人所有 
			     <s:if test='pb.clsyq=="1"'>
			     <input type="radio" name="radioItem" id="radioItem" value="1" checked="checked"/>
			    </s:if><s:else>
			      <input type="radio" name="radioItem" id="radioItem" value="1" disabled="disabled"/>
			    </s:else>单位所有</td>
			   </tr>
			   <tr>
			    <td class="tds"  style="text-align: right;">号牌号码:</td>
			    <td class="tdl" style="text-align: left;">${pb.hphm}</td>
			    <td class="tds"	style="text-align: right;">号牌种类:</td>
			    <td class="tdl" style="text-align: left;">${pb.hpzl}</td>
			   </tr>
			   <tr>
			    <td class="tds"	style="text-align: right;">车驾后4:</td>
			    <td class="tdl" style="text-align: left;">${pb.clsbdh}</td>
			    <td class="tds"	style="text-align: right;">车辆年检地车管所:</td>
			    <td style="text-align: left;">${pb.stjg}</td>
			   </tr>
			   <tr>
			    <td class="tds"	style="text-align: right;">所有人:</td>
			    <td class="tdl" style="text-align: left;">${pb.syr}</td>
			    <td class="tds"	style="text-align: right;">身份证号码:</td>
			    <td class="tdl" style="text-align: left;">${pb.sfzmhm}</td>
			   </tr>
			   <tr>
			    <th  class="th1"  colspan="4" align="left" height="25px">&nbsp;&nbsp;&nbsp;&nbsp;邮寄信息</th>
			   </tr>
			   <tr>
			    <td class="tds"	style="text-align: right;">收件人姓名:</td>
			    <td class="tdl" style="text-align: left;">${pb.yjSjrxm}</td>
			    <td class="tds"	style="text-align: right;">联系电话:</td>
			    <td class="tdl" style="text-align: left;">${pb.yjLxdh}</td>
			   </tr>
			   <tr>
			    <td class="tds"	style="text-align: right;">投递地址:</td>
			    <td class="tdl" style="text-align: left;">${pb.yjTddz}</td>
			    <td class="tds"	style="text-align: right;">邮政编码:</td>
			    <td class="tdl" style="text-align: left;">${pb.yjYzbm}</td>
			   </tr>
			   <tr>
			    <td class="tds"	style="text-align: right;">快递编号:</td>
			    <td class="tdl" style="text-align: left;" colspan="3">${pb.sbztKdbh}</td>
			   </tr>
			   <s:if test="#request.pbLogs.size>0" >
			   <tr>
			      <th class="th1"  colspan="4" align="left" height="25px">&nbsp;&nbsp;&nbsp;&nbsp;详细信息</th>
			   </tr>
	          <s:iterator id="order" value="#request.pbLogs" status="st">
		            <tr>
				      <td class="tds" style="text-align:right;">处理时间：</td>
				      <td class="tdl" style="text-align: left;">${order.cgYhsj} </td>
				      <td class="tds" style="text-align: right;">处理信息：</td> 
				      <td class="tdl" style="text-align: left;">
			            <s:if test="#order.sbzt==0">待初审;操作人：${order.czrxm };备注：${order.sbztTbly} </s:if>
					    <s:if test="#order.sbzt==0">申报 ;操作人：${order.czrxm };备注：${order.sbztTbly} </s:if>
					    <s:if test="#order.sbzt==1">已初审 ;操作人：${order.czrxm };备注：${order.sbztTbly} </s:if>
					    <s:if test="#order.sbzt==2">已复核;操作人：${order.czrxm };备注：${order.sbztTbly} </s:if>
					    <s:if test="#order.sbzt==3">通知书已接收;操作人：${order.czrxm };备注：${order.sbztTbly} </s:if>
					    <s:if test="#order.sbzt==4">快递已寄出;操作人：${order.czrxm } ;备注：${order.sbztTbly} </s:if>
					    <s:if test="#order.sbzt=='CT'">车管所退办;退办原因：${order.sbztTbly};操作人：${order.czrxm }</s:if>
				   	    <s:if test="#order.sbzt=='YT'">邮政退办;退办原因：${order.sbztTbly};操作人：${order.czrxm } </s:if></td>
				  </tr>
			   </s:iterator>
		    </s:if>
		    <tr >
		      <td  class="tds" colspan="4" align="center"><input type="button" class="bnt"  onclick="javascript:history.go(-1);" value="返回"/></td>
		    </tr>
		</table>
</body>
</html>
