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
<title>广告证档案查询 </title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/content.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/jquery-1.8.2.js"></script>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/My97DatePicker/WdatePicker.js"></script>
<style> 
	html{ 
		overflow:scroll;
		scrollbar-base-color:#c7e5ff;
		scrollbar-track-color:#FFFFFF;
	}
	.cz{
		cursor: pointer;
		color: blue;
		text-decoration: underline;
	}
</style>
</head>
<script type="text/javascript">   

	var mac="";

	$(function(){
		
		$("#resetButton").click(function(){
			$("#lsh").val("");
			$("#hphm").val("");
			$("#hpzl").val("");
			$("#gjgs").val("");
			$("#gggs").val("");
		});
		
		
		$(".cz").click(function(){
			var lsh=$(this).attr("lsh");
			if(lsh!=null && lsh!=""){
				var uri='<%=request.getContextPath()%>' +"/pages/gjgggl/tjcx/ggzdacx_detail.jsp";
				var returnValue=window.showModalDialog(uri,lsh,"dialogWidth:720px;dialogHeight:670px;help:no;status:no;scroll:yes;location:no;resizable:yes");
				if(returnValue!=null && returnValue!="0" && returnValue!="" && typeof(returnValue)!="undefined"){
					if(returnValue=="success"){
						window.location.href="<%=request.getContextPath()%>/gjgg/ggz_getZxOrHfData.action";
					}
				}
			}else{
				alert("获取不到当前操作的流水号,无法进一步操作数据");	
			}
			
		});
		
		
	})
	
</script>
	<body>
		<div style="width:95%;margin: 0 auto;">
			 <form id="queryForm" name="queryForm" action="<%=request.getContextPath()%>/gjgg/tjcx_getGgzdacx.action" method="post">
				   <table  class="table1"  width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
					  <tr>
					    <td class="tds" style="text-align: right;">广告证号(流水号)：</td>
					    <td class="tds" style="text-align: left;" colspan="3">
					    	<input type="text" name="lsh" id="lsh" value="${request.lsh}" />
					    </td>
					  </tr>
					  <tr>
					    <td class="tds" style="text-align: right;">号牌号码：</td>
					    <td class="tds" style="text-align: left;">
					    	<input type="text" name="hphm" id="hphm" value="${request.hphm}" />
					    </td>
					    <td class="tds" style="text-align: right;">号牌种类：</td>
					    <td class="tds" style="text-align: left;">
					    	<s:if test='#request.hpzlMap!=null '>
						    	<s:select id="hpzl"
						    		name="hpzl"
						    		theme="simple"
									list="#request.hpzlMap"
									listKey="value" 
									listValue="value"
									headerKey="" 
									headerValue="--请选择号牌种类--"
									value="#request.hpzl"
									>
								</s:select>
					    	</s:if>
					    	<s:else>
					    		<input type="text" disabled="disabled" />
					    	</s:else>
					    </td>
					  </tr>
					  <tr>
					    <td class="tds" style="text-align: right;">公交公司：</td>
					    <td class="tds" style="text-align: left;">
					    	<s:if test='#request.hpzlMap!=null '>
						    	<s:select id="gjgs"
						    		name="gjgs"
						    		theme="simple"
									list="#request.gjgsMap"
									listKey="key" 
									listValue="value"
									headerKey="" 
									headerValue="--请选择公交公司--"
									value="#request.gjgs"
									>
								</s:select>
					    	</s:if>
					    	<s:else>
					    		<select>
					    			<option>--请选择公交公司--</option>
					    		</select>
					    	</s:else>
					    </td>
					    <td class="tds" style="text-align: right;">广告公司：</td>
					    <td class="tds" style="text-align: left;">
					    	<s:if test='#request.hpzlMap!=null '>
						    	<s:select id="gggs"
						    		name="gggs"
						    		theme="simple"
									list="#request.gggsMap"
									listKey="key" 
									listValue="value"
									headerKey="" 
									headerValue="--请选择广告机构--"
									value="#request.gggs"
									>
								</s:select>
					    	</s:if>
					    	<s:else>
					    		<select>
					    			<option>--请选择广告机构--</option>
					    		</select>
					    	</s:else>
					    </td>
					  </tr>
					  <tr>
					    <td class="tds" style="text-align: right;">打印时间：</td>
					    <td class="tds" style="text-align: left;" colspan="3">
					    	<input id="startTime" name="startTime" type="text"
									value="${request.startTime}"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true,enableKeyboard:false,maxDate:'%y-%M-%d %H:%m:%s'})"
									 /> 到 
							<input id="endTime" name="endTime" type="text"
									value="${request.endTime}"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true,enableKeyboard:false,maxDate:'%y-%M-%d %H:%m:%s'})"
									 />
					    </td>
					  </tr>
					  <tr>
					  	<td colspan="4"> 
					    	<input type="submit" id="queryButton" class="bnt" style="width: 80px;" value="查  询" />
					    	<input type="button" id="resetButton" class="bnt" style="width: 80px;" value="重  置"/>
					    </td>
					  </tr>
				  </table> 
			  </form>
		  </div>
		  
		  <div style="width:95%; margin-top: 10px;margin: 0 auto;" >
		  
		    <table class="datalist" id="tableList" width="100%" border="0" cellpadding="0" cellspacing="0">
			  <tr class="tr1">
			  	<th>广告证号(流水号)</th>
			    <th>号牌号码</th>
			    <th>号牌种类</th>
			    <th>车辆识别代号</th>
			    <th>状态</th>
			    <th>打印部门</th>
			    <th>操作</th>
			  </tr>
			  <s:if test='#request.busCertifyInfos!=null && #request.busCertifyInfos.size>0 '>
			  	<s:iterator value="#request.busCertifyInfos" var="tempCertifyInfo">
			  	<tr>
			  		<td>${tempCertifyInfo.lsh}</td>
			  		<td>${tempCertifyInfo.hphm}</td>
			  		<td>${tempCertifyInfo.hpzl}</td>
			  		<td>${tempCertifyInfo.clsbdh}</td>
			  		<td>
			  			<s:if test='#tempCertifyInfo.zt=="A" '>正常
			  				<s:if test='#tempCertifyInfo.dzcs>1 '>
			  					(错证重打)
			  				</s:if>
			  			</s:if>
			  			<s:if test='#tempCertifyInfo.zt=="R" '>注销</s:if>
			  			<s:if test='#tempCertifyInfo.zt=="F" '>超期注销</s:if>
			  		</td>
			  		<td>${tempCertifyInfo.zzmjbmKj}</td>
			  		<td>
		  				<a class="cz" lsh="${tempCertifyInfo.lsh}">查看广告证</a>
			  		</td>
			  		</tr>
			  	</s:iterator>
			  </s:if>
			  <s:else>
			  	<tr>
			  		<td style="color: red;text-align: center;" colspan="11">查无数据</td>
			  	</tr>
			  </s:else>
			</table>
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				style="padding-top: 5px;">
				<pt:write uri="${map.uri}" pagesize="${map.pagesize}" rscount="${map.rscount}" currentpage="${map.currentpage}"></pt:write>
			</table>
		   </div>
		 
</body>
</html>