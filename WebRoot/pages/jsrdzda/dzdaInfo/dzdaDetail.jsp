<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<title>驾驶证档案卷宗查询</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/jquery-1.8.2.js"></script>
<LINK media=screen href="<%=request.getContextPath()%>/js/gjgggl/jQuery.files/jquery-ui-1.7.1.custom.css" type=text/css rel=stylesheet>
<SCRIPT type=text/javascript>
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
<script type="text/javascript">
	function query(){
		var sfzmhm=$.trim(document.getElementById("sfzmhm").value);
		var dabh=$.trim(document.getElementById("dabh").value);
		if(sfzmhm=="" &&  dabh=="" ){
			alert("请填写驾驶证号或档案编号!");
			return false;
		}else{
			document.getElementById("querySubmit").submit();	
		}		
	}	
	
</script>
</head>
	<body>
	  <div style="width:100%;text-align: center;" >		  
	    <table id="showData" class="datalist" width="100%" border="0" cellpadding="0" cellspacing="0">
	    	  <tr>
	    	  	<td colspan="6" class="tr1" style="font-weight: bold;" >驾驶证档案卷宗查询</td>
	    	  </tr>
	    	  <tr>
			    <td class="tds1" style="text-align: right;width:120px;">驾驶证号：</td>
			    <td class="tds2" style="text-align: left;" colspan="5">
			    	<form id="querySubmit" action="<%=request.getContextPath()%>/jsrdzda/jsrdzda_initQueryInfo.action">
		    			<input type="text" name="sfzmhm" id="sfzmhm" value="${request.sfzmhm}" style="margin-right: 90px"/>
		    				<font style="font-weight: bold;">档案编号：</font>
		    			<input type="text" name="dabh" id="dabh" value="${request.dabh}" />
		    			<input type="hidden" name="temp" id="temp" value="isShow" />
		    			<input type="button" class="bnt" style="width: 80px;" onclick="query();" value="查询" />
		    			<s:if test='#request.queryResult=="noData"'>
				    		<label style="color: red;">查无数据</label>
				    	</s:if>
			    	</form>				    	
			    </td>
			  </tr>		    	
			  <tr>
			    <td class="tds1" style="text-align: right;width:120px;">姓名：</td>
			    <td class="tds2" style="text-align: left;width:80px;">
			    	<!-- 广告喷涂业务流水号 -->
			    	<input type="hidden" id="lsh" value="${request.esDrv.xm}" />
			    	${request.esDrv.xm}
			    </td>
			    <td class="tds1" style="text-align: right;width:120px;">身份证明号码：</td>
			    <td class="tds2" style="text-align: left;width:240px;">
			    	${request.esDrv.sfzmhm}
			    </td>
			     <td class="tds1" style="text-align: right;width:120px;">身份证明名称：</td>
			    <td class="tds2" style="text-align: left;width:240px;">
			    	${request.esDrv.sfzmmc}
			    </td>
			  </tr>
			  <tr>

			    <td class="tds1" style="text-align: right;width:120px;">档案编号：</td>
			    <td class="tds2" style="text-align: left;width:240px;">
			    	${request.esDrv.dabh}
			    </td>
			    <td class="tds1" style="text-align: right;width:120px;">性别：</td>
			     <td class="tds2" style="text-align: left;width:80px;">
			     	${request.esDrv.xb}
			     </td>
			   	 <td class="tds1" style="text-align: right;width:120px;">出生日期：</td>
			    <td class="tds2" style="text-align: left;width:240px;">
			    	${request.esDrv.csrq}
			    </td>
			  </tr>
			  <tr>
			  	 <td class="tds1" style="text-align: right;width:120px;">状态：</td>
			     <td class="tds2" style="text-align: left;width:80px;">
			     	${request.esDrv.zt}
			     </td>
			   	 <td class="tds1" style="text-align: right;width:120px;">累计积分：</td>
			    <td class="tds2" style="text-align: left;width:240px;">
			    	${request.esDrv.ljjf}
			    </td>
			    <td class="tds1" style="text-align: right;width:120px;">手机号码：</td>
			    <td class="tds2" style="text-align: left;width:240px;">
			    	${request.esDrv.lxdh}
			    </td>
			  </tr>
			  <tr>
			    <td class="tds1" style="text-align: right;width:120px;">准驾车型：</td>
			    <td class="tds2" colspan="5" style="text-align: left;width:240px;">
			  	  ${request.esDrv.zjcx}
			    </td>
			  </tr>				  
			  <tr>
			    <td class="tds1" style="text-align: right;width:120px;">登记住所地址：</td>
			    <td class="tds2" colspan="5" style="text-align: left;width:240px;" >
			    	${request.esDrv.djzsxxdz}
			    </td>
			  </tr>
		</table>
		<br/>
		<p style="font-size: 14px;font-weight: bold;float: left;margin-bottom: 2px;">历次电子档案采集列表:</p>		
		<table class="datalist" width="100%" border="0"
			cellpadding="0" cellspacing="0">
			<tr class="tr1">
				<th>序号</th>
				<th>驾驶证号</th>
				<th>姓名</th>	
				<th>档案编号</th>
				<th>采集业务</th>
				<th>采集时间</th>
				<th>归档人</th>
				<th>归档部门</th>
				<th>采集资料内容</th>
				<th>操作</th>
			</tr>
			<s:if test="#request.dzdaList.size > 0">
				<s:iterator id="cjVo" value="#request.dzdaList" status="st">
					<tr class="<s:if test="#st.odd == false">altrow</s:if>">
						<td>${st.count}</td>
						<td>${cjVo.sfzmhm }</td>
						<td>${cjVo.xm }</td>
						<td>${cjVo.dabh}</td>
						<td>${cjVo.ywlx }</td>
						<td><s:date name="#cjVo.cjsj" format="yyyy-MM-dd hh:mm:ss"/> </td>
						<td>${cjVo.lockxm }</td>
						<td>${cjVo.lockbm}</td>
						<td style="width: 250px;text-align: left">
                           <s:if test="#cjVo.photoLx!=null && #cjVo.photoLx.size>0" >
                              <s:iterator id="pot" value="#cjVo.photoLx" status="st0">
                                ${st0.count},${pot.zflx }<br>                                
                              </s:iterator>
                           </s:if>
                        </td>
					  <td style="text-align: left">									
					    <a  href="<%=request.getContextPath()%>/jsrdzda/jsrdzda_showJsrShPage.action?editType=show&cjxh=${cjVo.cjxh}">查看</a>
					  </td>
					</tr>
				</s:iterator>
			</s:if>
			<s:else>
				<tr>
					<td colspan="11">
						<span style="color: red">暂时没有相关数据</span>
					</td>
				</tr>
			</s:else>
		</table>
	</div>		
</body>
</html>