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
<title>流水查询</title>
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
			$("#lszt").val("");
		});
		
		
		$(".cz").click(function(){
			var lsh=$(this).attr("lsh");
			var info=$(this).attr("info");
			if(lsh!=null && lsh!=""){
				if(info!="detailInfo"){
					var uri='<%=request.getContextPath()%>' +"/gjgg/tjcx_getBusGgjgclsbByLsh.action?lsh="+lsh;
					window.showModalDialog(uri,"","dialogWidth:800px;dialogHeight:650px;help:no;status:no;scroll:yes;location:no;resizable:yes");
				}else{
					var uri='<%=request.getContextPath()%>' +"/gjgg/tjcx_getLscx_shwoDetail.action?lsh="+lsh;
					var returnValue=window.showModalDialog(uri,"","dialogWidth:800px;dialogHeight:650px;help:no;status:no;scroll:yes;location:no;resizable:yes");
					if(returnValue!=null && returnValue!="0" && returnValue!="" && typeof(returnValue)!="undefined"){
						if(returnValue=="success"){
							window.location.href="<%=request.getContextPath()%>/gjgg/ggz_getZxOrHfData.action";
						}
					}
				}
			}else{
				alert("获取不到当前操作的流水号,无法进一步操作数据");	
			}
			
		});
		
		
	})
	
</script>
	<body>
		<div style="width:99%;margin: 0 auto;">
			 <form id="queryForm" name="queryForm" action="<%=request.getContextPath()%>/gjgg/tjcx_getLscx.action" method="post">
				   <table  class="table1"  width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
					  <tr>
					  <td class="tds" style="text-align: right;">状态：</td>
					    <td class="tds" style="text-align: left;">
					    	<s:if test='#request.hpzlMap!=null '>
					    	<s:if test='#request.comefrom=="other" '>
						    	<s:select id="lszt"
						    		name="lszt"
						    		theme="simple"
						    		disabled="true"
									list="#request.lsztMap"
									listKey="key" 
									listValue="value"
									headerKey="" 
									headerValue="--请选择状态--"
									value="#request.lszt"
									>
								</s:select>
					    	</s:if>
					    	<s:else>
					    		<s:select id="lszt"
						    		name="lszt"
						    		theme="simple"
									list="#request.lsztMap"
									listKey="key" 
									listValue="value"
									headerKey="" 
									headerValue="--请选择状态--"
									value="#request.lszt"
									>
								</s:select>
					    	</s:else>
					    	</s:if>
					    	<s:else>
					    		<select>
					    			<option>--请选择状态--</option>
					    		</select>
					    	</s:else>
					    </td>
					    <td class="tds" style="text-align: right;">广告证号(流水号)：</td>
					    <td class="tds" style="text-align: left;">
					    	<input type="text" name="lsh" id="lsh" <s:if test='#request.comefrom=="other" '>disabled="disabled"</s:if> value="${request.lsh}" />
					    </td>
					    
					  </tr>
					  
					  <tr>
					    <td class="tds" style="text-align: right;">号牌号码：</td>
					    <td class="tds" style="text-align: left;">
					    	<input type="text" name="hphm" id="hphm" value="${request.hphm}" <s:if test='#request.comefrom=="other" '>disabled="disabled"</s:if> />
					    </td>
					    <td class="tds" style="text-align: right;">号牌种类：</td>
					    <td class="tds" style="text-align: left;">
					    	<s:if test='#request.hpzlMap!=null '>
					    	<s:if test='#request.comefrom=="other" '>
						    	<s:select id="hpzl"
						    		name="hpzl"
						    		theme="simple"
									list="#request.hpzlMap"
									listKey="key" 
									listValue="value"
									disabled="true"
									headerKey="" 
									headerValue="--请选择号牌种类--"
									value="#request.hpzl"
									>
								</s:select>
					    	</s:if>
					    	<s:else>
					    		<s:select id="hpzl"
						    		name="hpzl"
						    		theme="simple"
									list="#request.hpzlMap"
									listKey="key" 
									listValue="value"
									headerKey="" 
									headerValue="--请选择号牌种类--"
									value="#request.hpzl"
									>
								</s:select>
					    	</s:else>
					    	</s:if>
					    	<s:else>
					    		<select>
					    			<option>--请选择号牌种类--</option>
					    		</select>
					    	</s:else>
					    </td>
					  </tr>
					  <tr>
					    <td class="tds" style="text-align: right;">公交公司：</td>
					    <td class="tds" style="text-align: left;">
					    	<s:if test='#request.hpzlMap!=null '>
						    <s:if test='#request.comefrom=="other" '>
						    	<s:select id="gjgs"
						    		name="gjgs"
						    		theme="simple"
									list="#request.gjgsMap"
									listKey="key" 
									listValue="value"
									headerKey="" 
									disabled="true"
									headerValue="--请选择公交公司--"
									value="#request.gjgs"
									>
								</s:select>
						    </s:if>
						    <s:else>
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
						    </s:else>
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
						    <s:if test='#request.comefrom=="other" '>
						    	<s:select id="gggs"
						    		name="gggs"
						    		theme="simple"
									list="#request.gggsMap"
									listKey="key" 
									listValue="value"
									headerKey="" 
									disabled="true"
									headerValue="--请选择广告机构--"
									value="#request.gggs"
									>
								</s:select>
						    </s:if>
						    <s:else>
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
						    </s:else>
					    	</s:if>
					    	<s:else>
					    		<select>
					    			<option>--请选择广告机构--</option>
					    		</select>
					    	</s:else>
					    </td>
					  </tr>
					  <tr>
					    <td class="tds" style="text-align: right;">时间：</td>
					    <td class="tds" style="text-align: left;" colspan="3">
						     <s:if test='#request.comefrom==null || #request.comefrom=="" '>
						    	<input id="startTime" name="startTime" type="text" title="报备时间"
										value="${request.startTime}"
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true,enableKeyboard:false,maxDate:'%y-%M-%d %H:%m:%s'})"
										 /> 到 
								<input id="endTime" name="endTime" type="text" title="报备时间"
										value="${request.endTime}"
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true,enableKeyboard:false,maxDate:'%y-%M-%d %H:%m:%s'})"
										 />
						     </s:if>
						     <s:else>
						     	<input id="startTime" name="startTime" type="text" disabled="disabled"/> 到 
								<input id="endTime" name="endTime" type="text" disabled="disabled"/>
						     </s:else>
					    </td>
					    
					  </tr>
					  
					  <tr>
					  	<td colspan="4">
					    	<input type="submit" id="queryButton" class="bnt" <s:if test='#request.comefrom=="other" '>disabled="disabled"</s:if> style="width: 80px;" value="查  询" />
					    	<input type="button" id="resetButton" class="bnt" <s:if test='#request.comefrom=="other" '>disabled="disabled"</s:if> style="width: 80px;" value="重  置"/>
					    </td>
					  </tr>
				  </table> 
			  </form>
		  </div>
		  
		  <div style="width:99%; margin-top: 10px;margin: 0 auto;" >
		  
		    <table class="datalist" id="tableList" width="100%" border="0" cellpadding="0" cellspacing="0">
			  <tr class="tr1">
			  	<th>流水号</th>
			    <th>号牌号码</th>
			    <th>号牌种类</th>
			    <th>广告机构</th>
			    <th>审核部门</th>
			    <th>审核人</th>
			    <th>审核时间</th>
			    <th>审核结果</th>
			    <th>报备日期</th>
			    <th>状态</th>
			    <th>操作</th>
			  </tr>
			  <s:if test='#request.busGgjgclsbs!=null && #request.busGgjgclsbs.size>0 '>
			  	<s:iterator value="#request.busGgjgclsbs" var="tempBusGgjgclsb">
			  	<tr>
			  		<td>${tempBusGgjgclsb.lsh}</td>
			  		<td>${tempBusGgjgclsb.hphm}</td>
			  		<td>${tempBusGgjgclsb.hpzl}</td>
			  		<td>${tempBusGgjgclsb.ggjgmc}</td>
			  		<td>${tempBusGgjgclsb.ycmjbmKj}</td>
			  		<td>
			  			${tempBusGgjgclsb.ycmjxm}
			  			<!-- 
				  			<s:if test=' (#tempBusGgjgclsb.fpYcmjbm==null || #tempBusGgjgclsb.fpYcmjbm=="") && (#tempBusGgjgclsb.ycmjxm==null || #tempBusGgjgclsb.ycmjxm=="")  '>
				  				----
				  			</s:if>
				  			<s:else>
					  			<s:if test='#tempBusGgjgclsb.fpYcmjbm==null || #tempBusGgjgclsb.fpYcmjbm=="" '>--</s:if>
					  			<s:else>${tempBusGgjgclsb.fpYcmjbm}</s:else>
					  			 / 
					  			 <s:if test='#tempBusGgjgclsb.ycmjxm==null || #tempBusGgjgclsb.ycmjxm=="" '>--</s:if>
					  			 <s:else>${tempBusGgjgclsb.ycmjxm}</s:else>
				  			</s:else>
			  			 -->
			  		</td>
			  		<td>
			  			<s:date name="#tempBusGgjgclsb.ycmjrq" format="yyyy-MM-dd HH:mm:ss"/>
			  		</td>
			  		<td>
			  			<s:if test=' #tempBusGgjgclsb.ycjg=="1" '>合格</s:if>
			  			<s:else>不合格</s:else>
			  		</td>
			  		<td>
			  			<s:date name="#tempBusGgjgclsb.lrsj" format="yyyy-MM-dd HH:mm:ss"/>
			  		</td>
			  		<td>
			  			${tempBusGgjgclsb.ztMc}
			  		</td>
			  		<td>
		  				<s:if test='#tempBusGgjgclsb.zt=="E" '>
		  					<!--  
		  					<a class="cz" lsh="${tempBusGgjgclsb.lsh}">广告证</a>
		  					-->
		  				</s:if>
		  				<a class="cz" info="detailInfo" lsh="${tempBusGgjgclsb.lsh}">详细</a>
			  		</td>
			  	  </tr>
			  	</s:iterator>
			  </s:if>
			  <s:else>
			  	<tr>
			  		<td style="color: red;text-align: center;" colspan="10">查无数据</td>
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