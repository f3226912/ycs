<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="com.ycszh.common.PageTag" prefix="pt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>机动车牌证申请表</title>
<style type="text/css">
*{padding:0;margin:0;}
.content{width:800px;margin:0 auto;}
.content .table{width:620px;border-collapse:collapse;font-size:14px;}
.table caption{font-size:20px;font-weight:620px;margin:10px 0;}

.table tr td .table1{border-collapse:collapse;width:620px;text-align:center;border-bottom:hidden;}
.table tr td .table1 tr{height:40px;}

.table tr td .table2{border-collapse:collapse;width:620px;text-align:center;border-top:hidden;border-bottom:hidden;}
.table tr td .table2 tr{height:40px;}

.table tr td .table3{border-collapse:collapse;width:620px;text-align:center;border-top:hidden;}
.table tr td .table3 tr{height:30px;}
</style>
</head>
<script type="text/javascript">
	setTimeout(function(){
		window.print();
		window.close();
		return false;
	},100);
	
</script>
<body>
<div class="content">
      <table cellpadding="0"  cellspacing="0" border="1" bordercolor="#000" class="table">
              <caption>机动车牌证申请表</caption>
              <tr>
                   <td>
                        <table cellpadding="0"  cellspacing="0" border="1" class="table1">
                                <tr><th colspan="5" style="font-size:18px">申请人信息栏</th></tr>
                                <tr><td rowspan="3" style="width:100px;font-weight:800;">机动车所有人</td><td style="font-weight:800;">姓名/名称</td><td>${ezJdcChanApp.jdcsyr}</td><td style="font-weight:800;">邮政编码</td><td></td></tr>
                                <tr><td style="font-weight:800;">邮寄地址</td><td colspan="3">${ezJdcChanApp.sjrdz}</td></tr>
                                <tr><td style="font-weight:800;">手机号码</td><td>${ezJdcChanApp.yddh}</td><td style="font-weight:800;">固定电话</td><td>${ezJdcChanApp.gddh}</td></tr>
                                <tr><td style="font-weight:800;">代理人</td><td style="font-weight:800;">姓名/名称</td><td></td><td style="font-weight:800;">手机号码</td><td></td></tr>
                        </table>
                   </td>
              </tr>
              
              <tr>
                   <td>
                        <table cellpadding="0"  cellspacing="0" border="1" class="table2">
                                <tr><th colspan="5" style="font-size:18px">申请业务事项</th></tr>
                                <tr><td style="width:100px;font-weight:800;">号牌种类</td><td style="width:240px;">${ezJdcChanApp.hpzl}</td><td style="width:283px;font-weight:800;">号牌号码</td><td>${ezJdcChanApp.hphm}</td></tr>
                                <tr><td style="font-weight:800;">申请事项</td><td colspan="4">申请原因及明细</td></tr>
                                <s:if test="#request.ezJdcChanApp.ywlx=='补领机动车号牌'">
	                                <tr>
	                                	<td rowspan="2"  style="width:100px;font-weight:800;">号牌</td>
	                                	<s:if test="#request.ywyy=='补证'">
	                                		<td><input type="checkbox" checked="checked"/>补领</td>
	                                	</s:if>
	                                	<s:else>
	                                		<td><input type="checkbox"/>补领</td>
	                                	</s:else>
	                                	<td colspan="3">
	                                		<input type="checkbox" />丟失 
	                                		<input type="checkbox" /> 灭失
	                                		<input type="checkbox" /> 前号牌 
	                                		<input type="checkbox" /> 后号牌
	                                	</td>
	                                </tr>
	                                <tr>
	                               		<s:if test="#request.ywyy=='换证'">
	                                		<td><input type="checkbox" checked="checked"/>换领</td>
	                                	</s:if>
	                                	<s:else>
	                                		<td><input type="checkbox" /> 换领</td>
	                                	</s:else>
	                                	<td  colspan="3"><input type="checkbox" /> 前号牌  
	                                		<input type="checkbox" /> 后号牌
	                                	</td>
	                               </tr>
                                </s:if>
                                <s:else>
                                	<tr>
	                                	<td rowspan="2"  style="width:100px;font-weight:800;">号牌</td>
	                                	<td><input type="checkbox"/>补领</td>
	                                	<td colspan="3">
	                                		<input type="checkbox" />丟失 
	                                		<input type="checkbox" /> 灭失
	                                		<input type="checkbox" /> 前号牌 
	                                		<input type="checkbox" /> 后号牌
	                                	</td>
	                                </tr>
	                                <tr>
	                                	<td><input type="checkbox" /> 换领</td>
	                                	<td  colspan="3"><input type="checkbox" /> 前号牌  
	                                		<input type="checkbox" /> 后号牌
	                                	</td>
	                               </tr>
                                </s:else>
                                
                                <s:if test="#request.ezJdcChanApp.ywlx=='补换领行驶证'">
	                                <tr>
	                                	<td rowspan="2"  style="width:100px;font-weight:800;">行驶证</td>
	                                	<s:if test="#request.ywyy=='补证'">
	                                		<td><input type="checkbox" checked="checked"/>补领</td>
	                                	</s:if>
	                                	<s:else>
	                                		<td><input type="checkbox"/>补领</td>
	                                	</s:else>
	                                	<td colspan="3">
	                                		<input type="checkbox" /> 丟失 
	                                		<input type="checkbox" /> 灭失
	                                	</td>
	                                </tr>
	                                <tr>
	                                	<s:if test="#request.ywyy=='换证'">
	                                		<td><input type="checkbox" checked="checked"/>换领</td>
	                                	</s:if>
	                                	<s:else>
	                                		<td><input type="checkbox" /> 换领</td>
	                                	</s:else>
	                                </tr>
                               </s:if>
                               <s:else>
                               		<tr>
	                                	<td rowspan="2"  style="width:100px;font-weight:800;">行驶证</td>
	                                	<td><input type="checkbox" /> 补领</td>
	                                	<td colspan="3">
	                                		<input type="checkbox" /> 丟失 
	                                		<input type="checkbox" /> 灭失
	                                	</td>
	                                </tr>
	                                <tr><td><input type="checkbox" /> 换领</td><td colspan="3"></td></tr>
                               </s:else> 
                                  <tr>
                                	<td rowspan="3"  style="width:100px;font-weight:800;">登记证书</td>
                                	<td><input type="checkbox" /> 申领</td><td colspan="3"></td></tr>
                                <tr>
                                	<td><input type="checkbox" /> 补领</td>
                                	<td colspan="3"><input type="checkbox" /> 丟失 
                                		<input type="checkbox" /> 灭失 
                                		<input type="checkbox" /> 未获得
                                	</td>
                                </tr>
                                <tr><td><input type="checkbox" /> 换领</td><td colspan="3"></td></tr>
                                
                               <s:if test="#request.ezJdcChanApp.ywlx=='补换领检验合格标志'"> 
	                                <tr>
	                                	<td rowspan="3"  style="width:100px;font-weight:800;">检验合格标志</td>
	                                	<td><input type="checkbox" /> 申请</td>
	                                	<td colspan="3">
	                                		<input type="checkbox" />  在登记地车辆管理所申请
	                                		<input type="checkbox" /> 在登记地以外车辆管理所申请
	                                	</td>
	                                </tr>
                                	<s:if test="#request.ywyy=='补证'">
	                                	<tr>
	                                		<td><input type="checkbox" checked="checked"/>补领</td>
	                                		<td  colspan="3">
		                                		<input type="checkbox" /> 丟失 
		                                		<input type="checkbox" /> 灭失
		                                    </td>
	                                	</tr>
	                                	<tr>
	                                		<td><input type="checkbox" /> 换领</td>
	                                	</tr>
                                	</s:if>
                                	<s:else>
	                                	<tr>
	                                		<td><input type="checkbox"/>补领</td>
	                                		<td  colspan="3">
		                                		<input type="checkbox" /> 丟失 
		                                		<input type="checkbox" /> 灭失
		                                    </td>
	                                	</tr>
	                                	<tr><td><input type="checkbox" checked="checked"/>换领</td></tr>
                                	</s:else>
                               </s:if>
                               <s:else>
                               		<tr>
	                                	<td rowspan="3"  style="width:100px;font-weight:800;">检验合格标志</td>
	                                	<td><input type="checkbox" /> 申请</td>
	                                	<td colspan="3">
	                                		<input type="checkbox" />  在登记地车辆管理所申请
	                                		<input type="checkbox" /> 在登记地以外车辆管理所申请
	                                	</td>
	                                </tr>
	                                <tr>
	                                	<td><input type="checkbox" /> 补领</td>
	                                	<td  colspan="3">
	                                		<input type="checkbox" /> 丟失 
	                                		<input type="checkbox" /> 灭失
	                                    </td>
	                                </tr>
	                                <tr><td><input type="checkbox" /> 换领</td><td colspan="3"></td></tr>
                               </s:else>
                        </table>
                   </td>
              </tr>
              <tr>
                   <td>
                        <table  cellpadding="0"  cellspacing="0" border="1" class="table3">
                                <tr><td rowspan="3" colspan="3" style="font-weight:bold;width:140px;font-size:18px">机动车所有人及代理人对申请材料的真实有效性负责。</td><td style="text-align:left;text-indent:10px;">机动车所有人（代理人）签字：</td></tr>
                                <tr><td style="border-top:hidden;border-bottom:hidden;"><font style="font-family:楷体_GB2312;font-size: 16px; ">${ezJdcChanApp.jdcsyr}</font></td></tr>
                                <tr><td style="text-align:right;padding-right:20px;"><s:date name="#request.date" format="yyyy年MM月dd日"/></td></tr>
                        </table>
                   </td>
              </tr>
      </table>
</div>
</body>
</html>
