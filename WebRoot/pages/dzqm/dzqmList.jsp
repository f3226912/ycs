<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="com.ycszh.common.PageTag" prefix="pt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<title>警员信息查询</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/jquery-1.8.2.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/myCss.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/myJs.js"></script>

<link rel="stylesheet" href="<%=request.getContextPath()%>/jquery/js/ztreecss/zTreeStyle/zTreeStyle.css" type="text/css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/ztreejs/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/ztreejs/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/ztreejs/jquery.ztree.exedit-3.5.js"></script>
<script type="text/javascript">
	//zTree参数配置
	var setting = {
		view: {
			addHoverDom: addHoverDom,//鼠标悬浮在节点时的事件
			removeHoverDom: removeHoverDom,//鼠标离开节点时的事件
			selectedMulti: false//是否允许选择多个节点
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			beforeClick: beforeClick,
			onClick: onClick
		}
	};

	var zNodes =${node};
	var className = "dark";
	function beforeClick(treeId, treeNode, clickFlag) {
		className = (className === "dark" ? "":"dark");
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		zTree.selectNode(treeNode);
		var isParent = treeNode.isParent;
		if(isParent){
			return false;
		}
		window.location.href="<%=request.getContextPath()%>/yanche/yczd_findQmUser.action?orgid="+treeNode.id;
		return (treeNode.click != false);
	}
	function onClick(event, treeId, treeNode, clickFlag) {

	}
   $(document).ready(function(){
		//初始化zTree的数据
		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		var sNode ='${nodeid}';//初始化选中上次点击节点
		if(sNode!=null&&sNode!=''){
			var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
			treeObj.selectNode(treeObj.getNodeByParam("id", sNode));
		}
	});
	// 显示 
	function addHoverDom(treeId, treeNode) {
		var sObj = $("#" + treeNode.tId + "_span");
		if (treeNode.editNameFlag || $("#addChildBtn_"+treeNode.tId).length>0
				|| $("#addParentBtn_"+treeNode.tId).length>0 ){
			return;
		};
	};

	//鼠标离开时,移除按钮和事件
	function removeHoverDom(treeId, treeNode) {
		$("#addChildBtn_"+treeNode.tId).unbind().remove();
		$("#addParentBtn_"+treeNode.tId).unbind().remove();
	};
	
	//查看签名
	function showDzqm(loginid){
			var uri="<%=request.getContextPath()%>/yanche/yczd_showDzqmInfo.action?loginid="+loginid;
			window.showModalDialog(uri,"","dialogWidth:650px;dialogHeight:333px;help:no;status:no;scroll:no;");
	}
	/*function zwcjClick(id) {
		var uri="<%=request.getContextPath()%>/jyzwcj/jyzwcj_tianjiaFinger.action?user.yhdh="+id+"&cz=cj";
		var tempNode=window.showModalDialog(uri,null,"dialogHeight:800px;dialogWidth:700px;status:no;scroll:no;center:yes");
	};*/

</script>
<style type="text/css">
	.ztree li span.button.addz {margin-left:2px; margin-right: -1px; background-position:-144px 0; vertical-align:top; *vertical-align:middle}
	div.zTreeDemoBackground {width:250px;height:250px;text-align:center;}
	
	ul.ztree {margin-top: 10px;border: 1px solid #617775;background: #f0f6e4;width:300px;height:400px;overflow-y:auto;overflow-x:auto;}
</style>
</head>
<body style="text-align: center;">
<form action="<%=request.getContextPath()%>/yanche/yczd_findQmUser.action" method="post" id="searchfromid">
	<table width="95%">
		<tr>
			<td colspan="4" class="table_t" >查询条件</td>
		</tr>
		<tr>
			<td class="td_r" >
				用户账号&nbsp;
			</td>
			<td class="td_l">
				&nbsp;<input type="text" name="loginid" id="loginid" value="${loginid}" />
				<!-- 保存上次节点id -->
				<input type="hidden" name="nodeid" id="nodeid" value="${nodeid}" />
			</td>
			<td class="td_r">
				用户名称&nbsp;
			</td>
			<td class="td_l">
				&nbsp;<input type="text" name="username" id="username" value="${username}" />
			</td>
		</tr>
		<tr>
			<td colspan="4">
				<input class="bnt_a" type="submit" name="Submit" value="查 询" />
				<input type="hidden" name="cxzt" value="1" />
			</td>
		</tr>
	</table>
</form>
<table width="95%" >
	
	<tr>
		<td width="28%">
			<ul id="treeDemo" class="ztree" style="width:90%;height:345px;text-align:center;margin: 5px;"></ul>
			<div style="height: 5px;"></div>
		</td>
		<td width="72%" valign="top">
			<table id="ejiaA1" style="width:98%;height:98%;text-align:center; margin: 5px;">
				<tr class="table_t">
					<td>账号</td>
					<td>用户名称</td>
					<td>部门/职位</td>
					<td>操作人</td>
					<td>更新时间</td>
					<td>操作IP</td>
					<td>是否签名</td>
					<td>操作</td>
				</tr>
				<s:if test="#request.qmuserlist.size > 0">
					<s:iterator id="qm" value="#request.qmuserlist" status="st">
						<tr>
							<td><s:property value="#request.qm[0]"/></td>
							<td><s:property value="#request.qm[1]"/></td>
							<td><s:property value="#request.qm[2]"/></td>
							<td><s:property value="#request.qm[3]"/></td>
							<td>${request.qm[4]}</td>
							<td><s:property value="#request.qm[5]"/></td>
							<td>${request.qm[6]==null?"未签名":"已签名"}</td>
							<td>				
								<a href="<%=request.getContextPath()%>/yanche/yczd_dzqmgl.action?loginid=${request.qm[0]}&action=${map.uri}&currentpage=${map.currentpage}&orgid=${request.nodeid}">${request.qm[6]==null?"采集签名":"更新签名"}</a>
								<a href="#" onclick="showDzqm('${qm[0]}')">查看签名</a>
							</td>
						</tr>
					</s:iterator>
				</s:if>
				<s:else>
					<tr>
						<td colspan="8">
							<span style="color: red">暂时没有相关数据</span>
						</td>
					</tr>
				</s:else>
			</table>
		</td>
	</tr>
</table>
<div style="width:90%;">
<pt:write uri="${map.uri}" pagesize="${map.pagesize}" rscount="${map.rscount}" currentpage="${map.currentpage}"></pt:write>
</div>

</body>
</html>