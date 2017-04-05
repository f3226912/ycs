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
<html>
  <head>
    <title>异地委托未网上申报数据预警列表</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/content.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/module.css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/jquery-1.8.2.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/jquery-ui-1.9.1.custom.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/artDialog4.1.6/jquery.artDialog.source.js?skin=chrome"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/artDialog4.1.6/plugins/iframeTools.source.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/public.js"></script>	
	<script type="text/javascript">
	
		function exports(){
			var s_date = $("#s_date").val();
			var e_date = $("#e_date").val();
			var hphm =$('#hphm').val();
			var hpzl=$('#hpzl_dmz').val();
			if(hphm=='' && hpzl==''){
			  if(null == s_date || "" == s_date){
				alert("请选择申报开始日期!");
				return false;
			  }
			  if(null == e_date || "" == e_date){
				alert("请选择申报结束日期!");
				return false;
			  }	  
			}else{			    
			    if(hpzl==''){
			      alert('请选择号牌种类!');
			      $('#hpzl_dmz').focus();
			      return false;
			    }
			    if(null != s_date && "" != s_date){
			       if(null == e_date || "" == e_date){
				     alert("请选择申报结束日期!");
				    return false;
			        }	  
			    }
		        if(null!= e_date &&  ""!=e_date){
		            if(null == s_date || "" == s_date){
						alert("请选择申报开始日期!");
						return false;
					}		         
		        }			    
			 }		   
			  var dayNum =DateDiff(s_date,e_date);
              if(dayNum>7){
			     alert("申报开始时间与结束时间之间不能超过7天!");
			     return false;
			  }
		
		    var imgurl = '<%=request.getContextPath()%>/artDialog4.1.6/skins/icons/loading.gif';
			      art.dialog({
			        content: '<div style=\"text-align:center; text-indent:-999em; overflow:hidden; background:url('+imgurl+') no-repeat center center;\"><span>loading..</span></div><br/>数据加载中,请稍候.....',
			        title: '温馨提示',
				    lock: true,
			        opacity: 0.4
			     });
			 $("#esfrom").attr("action", "<%=request.getContextPath()%>/ydwt/ydwt_initWarnYdwt.action");
			 $("#esfrom").submit();
		}

     //计算天数差的函数，通用s
    function DateDiff(sDate1, sDate2){  //sDate1和sDate2是2004-10-18格式
        var aDate, oDate1, oDate2, iDays;
        aDate = sDate1.split("-");
        oDate1 = new Date(aDate[1]+"-"+aDate[2]+"-"+aDate[0]);  //转换为10-18-2004格式
        aDate = sDate2.split("-");
        oDate2 = new Date(aDate[1]+"-"+aDate[2]+"-"+aDate[0]);
        iDays = parseInt(Math.abs(oDate1-oDate2)/1000/60/60/24);  //把相差的毫秒数转换为天数
      return iDays;
  } 
	</script>
  </head>  
  <body style="background:#c7e5ff;">
    <div class="content1" style="width: 100%;">
		  <div class="roundedBox" id="type1" style="width: 95%;">
				<div class="right" style="width: 98%;">    
					<form action="" method="post" id="esfrom">
						<input type="hidden" name="stauts" id="stauts" value="1"/>
						<table class="table1" width="100%" border="0"
							cellpadding="0" cellspacing="0">
							<tr>
								<th class="th1" height="32" colspan="4">
									查询条件
								</th>
							</tr>
							<tr>
								<td class="tds" style="text-align: right">
									申报时间&nbsp;
								</td>								
								<td class="tdl" style="text-align: left;" colspan="3">
								<input name="s_date" id="s_date" value="${s_date}" size="10" type="text"  onfocus="WdatePicker({onpicked:function(){e_date.focus();}})"/>
									止
									<input name="e_date" id="e_date" value="${e_date}" size="10" type="text"  onfocus="WdatePicker({minDate:'#F{$dp.$D(\'s_date\')}'})"/>
									<font color="red">温馨提示内容：仅能查7天范围内的业务办理时间数据.</font>
	                         </td>						
							</tr>
							<tr>
								<td class="tds" style="text-align: right;">
									号牌号码&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<input type="text" name="hphm" id="hphm" value="${hphm}" onkeyup="clearspace(this)" onblur="clearallspace(this)"/>
								</td>
								<td class="tds" style="text-align: right;">
									号牌种类&nbsp;
								</td>
								<td class="tdl" style="text-align: left;" >
									<select name="hpzl" id="hpzl_dmz" style="width: 150px;">
										<option value="">请选号牌种类</option>
										<s:iterator var="hp" value="#request.hpzlList">
											<option value='<s:property value="#hp.dmz"/>' 
												<s:if test="#request.hpzl==#hp.dmz">selected</s:if>>
												<s:property value="#hp.dmms1"/>
											</option>
										</s:iterator>
									</select>
								</td>
							</tr>
							<tr>
								<td class="tds" colspan="4">
									<div align="center">
										<input class="bnt" type="button" value="查  询" style="cursor:pointer;" onclick="exports()" />
									</div>
								</td>
							</tr>
						</table>
					</form>
					<br>
					<p style="color: red;font-size: 12px;">预警规则：在综合应用平台业务申报时间前30天之内未在互联网进行申报的数据。</p>
					<table class="datalist" width="100%" border="0"
						cellpadding="0" cellspacing="0">
						<tr class="tr1">
							<th>序号</th>
							<th>流水号</th>
							<th>所有人</th>
							<th>号牌号码</th>
							<th>号牌种类</th>
							<th>车辆品牌</th>							
							<th>车辆型号</th>
							<th>车辆识别代号</th>
							<th>申报时间</th>
							<th>办结时间</th>
							<th>部门名称</th>
						</tr>
						<s:if test="#request.warnSbList.size > 0">
							<s:iterator id="warn" value="#request.warnSbList" status="st">
								<tr class="<s:if test="#st.odd == false">altrow</s:if>">
									<td>${st.count + (map.currentpage-1) * map.pagesize}</td>
									<td>${warn.lsh }</td>
									<td>${warn.syr}</td>
									<td>${warn.hphm}</td>
									<td>${warn.hpzl}</td>
									<td>${warn.clpp1}</td>
									<td>${warn.clxh}</td>
									<td>${warn.clsbdh}</td>
									<td><fmt:formatDate value="${warn.sqrq}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
									<td><fmt:formatDate value="${warn.bjrq}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
									<td>${warn.glbm}</td>									
									<%-- <a href="<%=request.getContextPath()%>/veh/veh_initSlCx.action?id=${dbjgZjxxb.id}" target="_blank">查看</a> --%>
								</tr>
							</s:iterator>
						</s:if>
						<s:else>
							<tr>
								<td colspan="15">
									<span style="color: red">暂时没有相关数据</span>
								</td>
							</tr>
						</s:else>
					</table>
				  <s:if test="#request.warnSbList.size > 0">
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						style="padding-top: 5px;">
						<pt:write uri="${map.uri}" pagesize="${map.pagesize}" rscount="${map.rscount}" currentpage="${map.currentpage}"></pt:write>
					</table>
				  </s:if>
					</div>
				<div class="corner topLeft"></div>
				<div class="corner topRight"></div>
			</div>
		</div>
  </body>
</html>
