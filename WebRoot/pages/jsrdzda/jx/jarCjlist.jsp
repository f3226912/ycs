<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="com.ycszh.common.PageTag" prefix="pt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<title>驾校初审列表</title>
		<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/jquery-1.8.2.js"></script>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/content.css" />
		<script type="text/javascript" src="<%=request.getContextPath()%>/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/artDialog4.1.6/jquery.artDialog.source.js?skin=chrome"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/artDialog4.1.6/plugins/iframeTools.source.js"></script>
        <style type="text/css">
			.bnt2 {
				width: 76px;
				height: 27px;
				background: url('<%=request.getContextPath()%>/images/an3.gif') no-repeat;
				border: none;  
				font-weight: bold;
			}
		</style>
  <script type="text/javascript">
   $(document).ready(function(){		
	   var showFlag='${showFlag}';
	   if(showFlag=='show'){
			art.dialog({
			    id: 'msg',
			    title: '温馨提示:',
			    content: document.getElementById("yixuan"),
			    width: 320,
			    height: 240,
			    left: '100%',
			    top: '100%',
			    fixed: true,
			    drag: true,
			    resize: false
			});
	   }

	var btnType = $("#btnType").val();
    if(btnType!=null && btnType!=''){
        var cjListsize = $("#cjListsize").val();
        if(cjListsize!=undefined && cjListsize=='1'){
            var loginName =$("#loginName").val();//当前登录用户名
            var zt ='${xxb.cjmac}';
            var islock ='${xxb.remark}';
            var lockr ='${xxb.lockr}';
            var lockTime='<s:date name="#request.xxb.lockIptime" format="yyyy-MM dd HH:mm:ss"/>';
            var gdrq = '<s:date name="#request.xxb.tranDate" format="yyyy-MM dd HH:mm:ss"/>'; //归档日期
            var lockName='${xxb.lockxm}';
            if(zt=='0' && islock=='0'){
               if(confirm("该宗身份证明号码的电子档案信息处在待审核状态,且未被锁定,是否需要锁定该宗记录?")){
                   var sfz=$("#sfzmhm").val();
                   window.location.href="<%=request.getContextPath()%>/jsrdzda/jsrdzda_lockRecoredBysfz.action?sfzmhm="+sfz;
               }
           }else if(zt=='0' && islock=='1' && lockr!=loginName){
                alert("该身份证号码的电子档案信息处在待审核状态，于"+lockTime+"时间被"+lockName+"锁定。");
           }else if(zt=='2'){
                alert("该身份证号码的电子档案信息已于"+gdrq+"时间归档。");
           }else if(zt=='JT'){
        	   alert("该身份证号码的电子档案信息已被"+lockName+"初审退办");
           }else if(zt=='CT'){
        	   alert("该身份证号码的电子档案信息已被"+lockName+"复核退办");
           }else if(zt=='B' && lockr!=loginName){
               alert("该身份证号码的电子档案待"+loginName+"进行补审");
           }
  	   }
    }
    $('#boxAll').click(function(){
        var flag = $("#boxAll").attr("checked");     
       $('[id$=Item]').each(function(){    //查找每一个Id以Item结尾 的checkbox 
         if(flag){
            if($(this).attr("disabled") ==undefined ){
                $(this).attr("checked", flag);  //选中
            }
         }else{
            $(this).removeAttr("checked"); //取消选中 
         }
       });
      //如果全部选中勾上全选框，全部选中状态时取消了其中一个则取消全选框的选中状态 
  	   $('[id$=Item]').each(function(){ 
	    $(this).click(function (){ 
			if($('[id$=Item]:checked').length == $('[id$=Item]').length) { 
			   $("#boxAll").attr('checked','checked'); 
			} else{
			   $('#boxAll').removeAttr('checked');
			}
	     });
	   });
	   });  	   
	   $('#checkTimeBtn').click(function(){
            var url="<%=request.getContextPath()%>/jsrdzda/jsrdzda_addCurrentLockList.action?btnType=dateConType";
            $("#searchfromid").attr("action",url);	
	        $("#searchfromid").submit();
	   });
	   //身份证查询
	   $('#checkSFBtn').click(function(){
            var url="<%=request.getContextPath()%>/jsrdzda/jsrdzda_addCurrentLockList.action?btnType=sfzConType";
            $("#searchfromid").attr("action",url);	
	        $("#searchfromid").submit();
	   });

	   //获取待审数据
	   $("#addLockBtn").click(function(){
		   
		   var url="<%=request.getContextPath()%>/jsrdzda/jsrdzda_addCurrentLockList.action";
           $("#searchfromid").attr("action",url);	
	       $("#searchfromid").submit();
	   });
    });
   /** var sjhm = checkmobile(loginSjhm.value);手机验证
       if(sjhm!="true") {
          loginSjhm.style.borderColor = '#FF0000';
          loginSjhm.focus();
         return false;
      }   ***/
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
 </script>
		<style> 
			html{ 
				overflow-y:scroll;overflow-x:none;
				scrollbar-base-color:#c7e5ff;
				scrollbar-track-color:#FFFFFF;
			} 
		</style>
	</head>
	<body style="background:#c7e5ff;">
	        <input type="hidden" id="btnType" name="btnType" value="${btnType}"/>
	        <input type="hidden" id="loginName" value="${loginName}"/>
			<div class="roundedBox" id="type1" style="width:100%;overflow-y:scroll;overflow-x:none;">
				<div class="right" style="width:95%;overflow-y:scroll;overflow-x:none;">
					<form action="<%=request.getContextPath()%>/jsrdzda/jsrdzda_initJsrCjList.action" method="post" id="searchfromid">
						
						<table class="table1" width="100%" border="0" cellpadding="0" cellspacing="0">
			 				<tr>
								<th class="th1" height="32" colspan="3">查询条件</th>
							</tr>
							<tr>
								<td class="tds" style="text-align: right">采集时间：&nbsp;</td>
								<td class="tdl" style="text-align: left;">
									<input name="s_date" id="s_date" value="${s_date}" size="10" type="text"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
									至
									<input name="e_date" id="e_date" value="${e_date}" size="10" type="text"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
								</td>
								<td><input class="bnt" type="button" id="checkTimeBtn" value="查  询" style="cursor:pointer;" /></td>
							</tr>
							<tr>
								<td class="tds" style="text-align: right;">身份证明号码：&nbsp;</td>
								<td class="tdl" style="text-align: left;">
									<input type="text" name="sfzmhm" id="sfzmhm" value="${sfzmhm}" />&nbsp;&nbsp;<font color="red">检索所有数据</font>
								</td>
								<td><input class="bnt" type="button" id="checkSFBtn" value="查  询" style="cursor:pointer;" /></td>
							</tr>
						</table>
					</form>
					<div> 
					    <input type="button" class="bnt" value="获待审数据" id="addLockBtn" name="addLockBtn" style="margin-bottom: 5px"/>
					</div>					
					<table class="datalist" width="100%" border="0"
						cellpadding="0" cellspacing="0">
						<tr class="tr1">
							<th>序号</th>
							<th>姓名</th>
							<th>性别</th>
							<th>身份证明号码</th>
							<th>档案编号</th>
							<th>准驾车型</th>
							<th>联系电话</th>
							<th>是否锁定</th>
							<th>锁定人</th>
							<th>锁定时间</th>
							<th>审核状态</th>
							<th>操作</th>
						</tr>
						<s:if test="#request.jsrCjList.size > 0">
						    <input type="hidden"  id="cjListsize" value="1"/><!-- 有数据的标志 -->
							<s:iterator id="cjVo" value="#request.jsrCjList" status="st">
								<tr class="<s:if test="#st.odd == false">altrow</s:if>">
									<td><!-- <input type="checkbox" id="boxItem" name="boxItem" value="${cjVo.cjxh}"/> -->${st.count + (map.currentpage-1) * map.pagesize}</td>
									<td>${cjVo.xm }</td>
									<td>
									    <s:if test="#cjVo.xb == 0">男</s:if>
						    			<s:elseif test="#cjVo.xb == 1">女</s:elseif>
						    		</td>
									<td>${cjVo.sfzmhm}</td>
									<td>${cjVo.dabh }</td>
									<td>${cjVo.zjcx }</td>
									<td>${cjVo.lxdh }</td>
									<td  <s:if test='#cjVo.isLock =="否"'>style="color: red"</s:if> >
									    ${cjVo.isLock}
									</td>
									<td>${cjVo.lockxm}</td>
									<td><s:date name="#cjVo.lockIptime" format="yyyy-MM dd HH:mm:ss"/> </td>
									<td>${cjVo.cjzt }</td>
									<td>
									<s:if test='#cjVo.isLock=="是" && #cjVo.lockr==#session.userbean.name'>
										 <s:if test="#cjVo.cjmac== 0 ">
										    <a href="<%=request.getContextPath()%>/jsrdzda/jsrdzda_showJsrShPage.action?editType=check&cjxh=${cjVo.cjxh}">审核</a>
										 </s:if>
										 <s:elseif test="#cjVo.cjmac=='CT' || #cjVo.cjmac=='JT'" >
										    <a href="<%=request.getContextPath()%>/jsrdzda/jsrdzda_showJsrShPage.action?editType=buCheck&cjxh=${cjVo.cjxh}">补审</a>
										 </s:elseif>
									</s:if>
								      <a href="<%=request.getContextPath()%>/jsrdzda/jsrdzda_showJsrShPage.action?editType=show&temp=shShow&cjxh=${cjVo.cjxh}">查看</a>
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
				<div class="corner topLeft"></div>
				<div class="corner topRight"></div>
				<div class="corner bottomLeft"></div>
				<div class="corner bottomRight"></div>
			</div>		
		<div id="yixuan" style="overflow-y:scroll;overflow-x:none;width:340px;height: 220px;display: none;">			
			<s:property value="titles[0]"/><p style="font-size: 13px">&nbsp;&nbsp;目前系统共有待审总数:${titles[0]} 宗,
			                              已锁定总数:${titles[1]}宗,
			                              待锁定总数:${titles[2]}宗,
			                              待补审总数:${titles[3]}宗,其中</p>
			<table class="datalist" style="width:340px;" id="yixuantableid">
				<tr>
					<td>姓名	</td> 
					<td>已锁定数量</td>
					<td>待补审</td>
				</tr>	
				<s:if test="#request.jsrCjList.size > 0">
					<s:iterator id="tempVo" value="#request.list" status="st">
					    <tr>
						  <td>${tempVo[1]}</td> 
						  <td>${tempVo[2]}</td>
						  <td>${tempVo[3]}</td>
						</tr>
					</s:iterator>
			   </s:if>
			   <s:else>
			      <tr>
			        <td colspan="3">
					   <span style="color: red">暂时没有相关数据</span>
					</td>
				 </tr>
			   </s:else>
			</table>
		</div>			
	</body>
</html>
