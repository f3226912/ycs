<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<title>机动车相片库查询</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/jquery-1.8.2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/development-bundle/ui/jquery.ui.core.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/development-bundle/ui/jquery.ui.widget.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/development-bundle/ui/jquery.ui.position.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/development-bundle/ui/jquery.ui.menu.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/development-bundle/ui/jquery.ui.autocomplete.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/jquery-ui-1.9.1.custom.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery/css/redmond/jquery-ui-1.9.1.custom.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery/css/redmond/jquery.ui.all.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/artDialog4.1.6/jquery.artDialog.source.js?skin=chrome"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/artDialog4.1.6/plugins/iframeTools.source.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/myCss.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/public.js"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/jquery-lightbox-0.5/js/jquery.lightbox-0.5.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery/jquery-lightbox-0.5/css/jquery.lightbox-0.5.css" media="screen" />

<script type="text/javascript">
	function exception(content){
		art.dialog({
			width:'50%',
		    content: content,
		    title: '系统异常',
		    cancelVal: '关闭',
			cancel: true,
			lock: true,
		    opacity: 0.87,
		    icon: 'error'
		});
	}

/*	function sub(){
		var hphm=$("#hphms").val();
		var hpzl=$("#hpzls").val();
		var clsbdh=$("#clsbdhs").val();
		alert(clsbdh);
		if((hphm == "" && hpzl != "") || (hphm != "" && hpzl == "")){
			alert('请输入号牌号码并选择号牌种类！ ');
			return false;
		}else if(hphm == "" && hpzl = "" && clsbdh == ""){
			alert('请输入查询条件！');
			return false;
		}
		var t =document.getElementById("myfrom");
	    t.action="<%=request.getContextPath()%>/jdcjsrxpk/jdcjsrxpk_jdcxpkList.action";
		document.myfrom.submit();
	}*/


	function fd() {
	  $("#xp").lightBox();
	}

</script>
<style >
#ejiaA1 {
	width: 120%;
}
.ejiaA2 {
	width: 100%;
}
.bnt_b {
	width: 100px;
	height: 27px;
	border: none;
	background: url(../images/an3.gif) no-repeat;
	color: #FFFFFF;
	font-weight: bold;
	cursor: pointer;
}
.bnt {
	width: 90px;
	height: 23px;
	line-height: 21px;
	border: none;
	background: url(<%=request.getContextPath()%>/images/bnt_1.jpg )
		no-repeat;
	color: #FFFFFF;
	font-weight: bold;
	cursor: pointer;
}
.table_t {
	background: url(../images/table_abg.gif) repeat-x;
	font-size: 14px;
	letter-spacing: 1.5px;
	font-weight: bold;
}
img{border: none;}
</style>
</head>
<body>
<form action="<%=request.getContextPath()%>/jdcjsrxpk/jdcjsrxpk_jdcxpkList.action" method="post" name="myfrom" id="myfrom"><!--
	 <iframe id="abc" name="abc" width="0" height="0" style="display: none;"></iframe>
	--><table width="80%" align="center">
	<tr >
		<td colspan="4" class="table_t" >查询条件</td>
	</tr>
	<tr>
		<td class="td_r">
			号牌号码&nbsp;
		</td>
		<td class="td_l">
			&nbsp;<input type="text" name="hphms" id="hphms" value="${hphm}" />
		</td>
		<td class="td_r">
			号牌种类&nbsp;
		</td>
		<td class="td_l">
			&nbsp;<s:select cssStyle="width:153px;" headerKey="" headerValue="---请选择---" list="#{'01':'01---大型汽车',
				'02':'02---小型汽车','03':'03---使馆汽车','04':
				'04---领馆汽车','05':'05---境外汽车','06':
				'06---外籍汽车','07':'07---普通摩托车','08':
				'08---轻便摩托车','09':'09---使馆摩托车','10':
				'10---领馆摩托车','11':'11---境外摩托车','12':'12---外籍摩托车'
				,'13':'13---低速车','14':'14---拖拉机'
				,'15':'15---挂车','16':'16---教练汽车'
				,'17':'17---教练摩托车','18':'18---试验汽车'
				,'19':'19---试验摩托车','20':'20---临时入境汽车'
				,'21':'21---临时入境摩托车','22':'22---临时行驶车'
				,'23':'23---警用汽车','24':'24---警用摩托'
				,'25':'25---原农机号牌','26':'26---香港入出境车'
				,'27':'27---澳门入出境车'
				}" theme="simple" listKey="key" listValue="value"
				 name="hpzls" value="#request.hpzl"  id="hpzls"></s:select>
		</td>
	</tr>
	<tr >
		<td class="td_r">
			车辆识别代号&nbsp;
		</td>
		<td class="td_l">
			&nbsp;<input type="text" name="clsbdhs" id="clsbdhs" value="${clsbdh}" />
		</td>
		<td colspan="2"></td>
	</tr>
	<tr>
		<td colspan="4">
			<input class="bnt" type="submit" value="查   询"/><!--
			<input class="bnt_a" type="button" onclick="cx()" value="查询2"/>
		--></td>
	</tr>
	</table>
</form>
<br/>
<table width="80%" align="center">
	<s:if test="#request.jdcList.size > 0">
	<s:iterator id="wfqd" value="#request.jdcList" status="st">
	<tr class="table_t">
		<td colspan="6">机动车信息</td>
	</tr>
	<tr> 
		<td width="25%">号牌号码</td>
		<td><s:property value="#request.wfqd[1]"/></td>
		
			<s:if test="#request.jdcList.size > 0">
				<s:iterator id="wfqd" value="#request.jdcList" status="st">
				<td rowspan="6" width="30%" >
					<a id="xp" href="http://100.100.21.61/cmp_new/view_pic.asp?efid=${wfqd[0]}"  onclick="fd()"><img width="380" height="250" src="http://100.100.21.61/cmp_new/view_pic.asp?efid=${wfqd[0]}"/></a>
				</td>
				</s:iterator>
			</s:if>
		
	</tr>
	<tr>
		<td>号牌种类</td>
		<td><s:property value="#request.wfqd[2]"/></td>
	</tr>
	<tr>
		<td>车辆识别代号</td>
		<td><s:property value="#request.wfqd[3]"/></td>
	</tr>
	<tr>
		<td>录入时间</td>
		<td><s:property value="#request.wfqd[4]"/></td>
	</tr>
</s:iterator>
</s:if>
<s:else>
	<tr>
		<td colspan="10">
			<span id="nodata" style="color: red">暂时没有相关数据</span>
		</td>
	</tr>
</s:else>
</table>

<script language="javascript">
ejiaA1("ejiaA1","#fff","#F5F5F5","#FFFFCC","#C4DEFF");
</script>
</body>
</html>
