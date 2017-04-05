<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<base target="_self">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<title>${request.cyyjId==null?'拍照规格':'查验依据'}</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/jquery-1.8.2.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/myCss.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/content.css" />
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
		var dmz = treeNode.id;
		var cyyjId = $("#cyyjId").val();
		var pzggId = $("#pzggId").val();
		var mldmz = $("#mldmz").val();
		var dmlb = $("#dmlb").val();
		var type = $("#type").val();
		window.location.target="_self";
		if(type=="cyxmml"||type=="pzggml"){//【查验项目/拍照规格】目录信息
			window.location.href="<%=request.getContextPath()%>/yanche/yczd_findCyxmOrPzggML.action?dmz="+dmz+"&type="+type;
		}else{//【车辆类型/使用类型】查验依据和拍照规格信息
			window.location.href="<%=request.getContextPath()%>/yanche/yczd_showCyyjOrPzgg.action?dmz="+dmz+"&cyyjId="+cyyjId+"&pzggId="+pzggId+"&mldmz="+mldmz+"&dmlb="+dmlb;
		}
		return (treeNode.click != false);
	}
	
	
	
	function onClick(event, treeId, treeNode, clickFlag) {

	}
   $(document).ready(function(){
		//初始化zTree的数据
		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		var sNode ='${glbms }';//初始化选中上次点击节点
		if(sNode!=null&&sNode!=''){
		   alert(sNode);
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
	

	function zwcjClick(id) {
		/*var uri="<%=request.getContextPath()%>/jyzwcj/jyzwcj_tianjiaFinger.action?user.yhdh="+id+"&cz=cj";
		var tempNode=window.showModalDialog(uri,null,"dialogHeight:800px;dialogWidth:700px;status:no;scroll:no;center:yes");*/
	};

</script>
<style type="text/css">
	.ztree li span.button.addz {margin-left:2px; margin-right: -1px; background-position:-144px 0; vertical-align:top; vertical-align:middle}
	div.zTreeDemoBackground {width:250px;height:250px;text-align:center;}
	ul.ztree {margin-top: 10px;border: 1px solid #617775;background: #f0f6e4;width:300px;height:400px;overflow-y:auto;overflow-x:auto;}
	#dmms{ border:0px solid #B6CEFB; margin: 8px 0px; background-color:#E5F0F9;  
		width: 400px; height: 340px; font-size: 14px;  text-align: center;}
	#dmms ul{margin: 0 auto;}
	#dmms li{ list-style-type: none; border: 0px solid red; margin: 0 auto;}
	.dom{ padding: 0px; margin: 5px;}
	#imgDom{ border: 0px solid #B6CEFB; width: 500px; height: 420px; margin: 20px auto; text-align: center; padding: auto;}
	#imgDom li{ list-style: none;}
	.dmsm{ font-size: 14px; font-weight: bold; color: blue; text-align: center;}
	.dmsm1{ text-indent: 2em;}
	.img{ text-align: center;}
	.div{ height: 20px;}
</style>
</head>
<body>

<table width="99%" class="dom">
	<tr>
		<td width="28%" >
			<ul id="treeDemo" class="ztree" style="width:90%;height:350px;text-align:center;margin: 5px;"></ul>
		</td>
		<td width="72%" valign="top" style="background-color:#E5F0F9;">
			<div id="dmms" style="width: 95%; height: auto;">
				<ul>
					<li class="dmsm">${request.dmsm}</li>
					<li class="dmsm1">${request.cyyjOrpzgg}</li>
				</ul>
				<div class="div"></div>
				<s:if test="#request.shilitu!=null">
					<ul>
					<li class="dmsm">------示例图------</li>
					<li class="img"><img width="360" height="320" src="<%=request.getContextPath()%>/${request.shilitu}"/></li>
					</ul>
				</s:if>
			</div>
		</td>
	</tr>
</table>

<input type="hidden" id="cyyjId" name="cyyjId" value="${cyyjId}"/>
<input type="hidden" id="pzggId" name="pzggId" value="${pzggId}"/>
<input type="hidden" id="dmlb" name="dmlb" value="${dmlb}"/>
<input type="hidden" id="mldmz" name="mldmz" value="${mldmz}"/>
<input type="hidden" id="type" name="type" value="${type}"/>
</body>
</html>