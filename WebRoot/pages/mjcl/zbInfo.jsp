<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<title>指标信息查询</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/content.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/jquery-1.8.2.js"></script>

<style type="text/css">
	.ui-autocomplete-loading {
		background: white url('<%=request.getContextPath()%>/jquery/development-bundle/demos/autocomplete/images/ui-anim_basic_16x16.gif') right center no-repeat;
	}
	#susernameid { width: 12em; }
	.bnt2 {
		width: 76px;
		height: 27px;
		background: url('<%=request.getContextPath()%>/images/an3.gif') no-repeat;
		border: none;
		font-weight: bold;
	}
</style>

<script type="text/javascript">

	$(document).ready(function() {
		
	});
	
	function getZbInfoData(){
		var sfzmhm=$("#sfzmhm").val();
		if(sfzmhm==null || sfzmhm==""){
			alert("请填写正确身份证明号码");
			return false;
		}else{
			$("#myForm").submit();
		}
	}
	
	function toUp(){
		var sfzmhm=$("#sfzmhm").val();
		sfzmhm=$.trim(sfzmhm.toUpperCase());
		$("#sfzmhm").val(sfzmhm);
	}
	
	function resetdata(){
		$("#sfzmhm").val("");
	}
	

</script>
<style> 
	html{ 
		overflow:scroll;
		scrollbar-base-color:#c7e5ff;
		scrollbar-track-color:#FFFFFF;
	} 
</style>
</head>
<body style="background:#c7e5ff;">
	<div class="content1" style="width:98%;">
		<div class="roundedBox" id="type1" style="width:97%;">
			<div class="right" style="width:99%;">
				<form action="<%=request.getContextPath()%>/mjcl/zb_getZbInfoData.action" method="post" id="myForm">
					<table class="table1" width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr class="th1"  >
				
							<th  height="32"  colspan="4"  >
								指标信息查询
							</th>
					
						</tr>
						<tr >
				
							<td class="tds" style="text-align: left;" > 
								身份证明名称:<s:select theme="simple" list="#{'A':'二代居民身份证','B':'组织机构代码证书','C':'军官证','D':'士兵证','E':'军官离退休证','F':'境外人员身份证明','G':'外交人员身份证明','H':'居民户口薄','J':'单位注销证明','K':'居住暂住证明','L':'驻华机构证明','M':'临时居民身份证'}"  value="#request.sfzmmc"  name="sfzmmc" id="sfzmmc" />
														
							</td>
							<td class="tds" style="text-align: left;" >
								身份证明号码：
								<input type="text" name="sfzmhm" id="sfzmhm" value="${request.sfzmhm}" maxlength="50" onblur="toUp()" />
							</td>
							
						</tr>
						<tr>
							
							<td class="tds" colspan="2" >
								<div align="center">
									<input class="bnt" type="button" id="frombutid" value="查  询" onclick="getZbInfoData()" style="cursor:pointer;"/>
									<input class="bnt" type="button" value="重  置" onclick="resetdata()" style="cursor:pointer;"/>
								</div>
							</td>
						</tr>
					</table>
				</form>
				<font color="blue">${tjstr}</font>
				<font color="blue">(一)、交委指标信息</font>
				<table class="datalist" width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr class="tr1">
						<th>
							指标号
						</th>
						<th>
							身份证明号码
						</th>
						<th>
							身份证明类型
						</th>
						<th>
							姓名
						</th>
						<th>
							指标过期日期
						</th>
						<th>
							指标分类
						</th>
						<th>
							指标状态
						</th>
					</tr>
					<s:if test='#request.result=="0" || #request.result=="" '>
						<s:if test='#request.r_!=null && #request.r_.size>0 '>
							<s:iterator value="#request.r_" var="tempData" >
								<tr>
									<td>
										${tempData[3]}
									</td>
									<td>
										${tempData[7]}
									</td>
									<td>
										${tempData[6]}
									</td>
									<td>
										${tempData[5]}
									</td>
									<td>
										${tempData[4]}
									</td>
									<td>
										${tempData[2]}
									</td>
									<td>
										${tempData[8]}
									</td>
								</tr>
							</s:iterator>
						</s:if>
						<s:else>
							<tr>
								<td colspan="7">
									<span id="nodata" style="color: red">暂时没有相关数据</span>
								</td>
							</tr>
						</s:else>
						
					</s:if>
					<s:else>
						<tr>
							<td colspan="8">
								<span id="nodata" style="color: red">${request.result}</span>
							</td>
						</tr>
					</s:else>
					
				</table>
				<br/>
				<font color="blue">（二）、交警业务主要验证环节验证信息</font>
				<table class="datalist" width="100%" border="0" cellpadding="0" cellspacing="0" >
					<tr class="tr1">
						<th>
							流水号
						</th>
						<th width="6%">
							验证岗位
						</th>
						<th width="6%">
							指标类型
						</th>
						<th  width="6%">
							指标号
						</th>
						<th  width="10%">
							公证号
						</th>
						<th  width="10%">
							身份证明号码
						</th>
						<th>
							姓名
						</th>
                          <th>
							车辆识别代号
						</th>
						<th>
							验证日期
						</th>
						<th  width="7%">
							验证结果
						</th>
						<th width="15%">
							验证结果说明
						</th>
					</tr>
					<s:if test='#request.result=="0" || #request.result=="" '>
						<s:if test='#request.r1_!=null && #request.r1_.size>0 '>
							<s:iterator  value="#request.r1_" var="yzjg"  status="st" >
								<tr class="<s:if test="#st.odd == false">altrow</s:if>">
									<td>
									 ${yzjg[0]}
									</td>
					                <td>
									 ${yzjg[1]}
									</td>
									<td>
									 ${yzjg[2]}
									</td>
					                <td>
									 ${yzjg[3]}
									</td>
											<td>
									 ${yzjg[4]}
									</td>
					                <td>
									 ${yzjg[5]}
									</td>
									<td>
									 ${yzjg[6]}
									</td>
					             	<td>
									 ${yzjg[7]}
									</td>
									<td>
									 ${yzjg[8]}
									</td>
									<td>
									 ${yzjg[9]}
									</td>
									<td>
									 ${yzjg[10]}
									</td>
									
								</tr>
							</s:iterator>
						</s:if>
						<s:else>
							<tr>
								<td colspan="11">
									<span id="nodata" style="color: red">暂时没有相关数据</span>
								</td>
							</tr>
						</s:else>
						
					</s:if>
					<s:else>
						<tr>
							<td colspan="11">
								<span id="nodata" style="color: red">${request.result}</span>
							</td>
						</tr>
					</s:else>
					
				</table>				
			</div>
			<div class="corner topLeft"></div>
			<div class="corner topRight"></div>
			<div class="corner bottomLeft"></div>
			<div class="corner bottomRight"></div>
		</div>
	</div>
</body>
</html>
