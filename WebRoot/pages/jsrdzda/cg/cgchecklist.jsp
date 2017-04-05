<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="com.ycszh.common.PageTag" prefix="pt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<title>驾校初审</title>
		<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/jquery-1.8.2.js"></script>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/content.css" />
		<script type="text/javascript" src="<%=request.getContextPath()%>/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/css/redmond/jquery-ui.js"></script>
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
	 $('#boxAll').click(function(){
       var flag = $("#boxAll").attr("checked");     
       $('[id$=Item]').each(function(){    //查找每一个Id以Item结尾的checkbox 
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
            var url="<%=request.getContextPath()%>/jsrdzda/jsrdzda_addCurrentLockByRecheck.action?btnType=dateConType";
            $("#searchfromid").attr("action",url);	
	        $("#searchfromid").submit();
	   });
	   $('#checkSFBtn').click(function(){
            var url="<%=request.getContextPath()%>/jsrdzda/jsrdzda_addCurrentLockByRecheck.action?btnType=sfzConType";
            $("#searchfromid").attr("action",url);	
	        $("#searchfromid").submit();
	   });
	   //获取待审数据
	   $("#getBlockBtn").click(function(){
		   var url="<%=request.getContextPath()%>/jsrdzda/jsrdzda_addCurrentLockByRecheck.action";
           $("#searchfromid").attr("action",url);	
	       $("#searchfromid").submit();
	   });
    });
   /**  var sjhm = checkmobile(loginSjhm.value);手机验证
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
				overflow:scroll;
				scrollbar-base-color:#c7e5ff;
				scrollbar-track-color:#FFFFFF;
			} 
		</style>
	</head>

	<body style="background:#c7e5ff;">
		<div class="content1" style="width:100%;">
			<div class="roundedBox" id="type1" style="width:100%;">
				<div class="right" style="width:90%;">
					<form action="<%=request.getContextPath()%>/jsrdzda/jsrdzda_initCgCheckJsrList.action" method="post" id="searchfromid">
						<table class="table1" width="100%" border="0"
							cellpadding="0" cellspacing="0">
							<tr>
								<th class="th1" height="32" colspan="5">查询条件</th>
							</tr>
							<tr>
								<td class="tds" style="text-align: right">采集时间：&nbsp;</td>
								<td class="tdl" style="text-align: left;" >
									<input name="s_date" id="s_date" value="${s_date}" size="10" type="text"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
									至
									<input name="e_date" id="e_date" value="${e_date}" size="10" type="text"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
								</td>
								<td colspan="3"><input class="bnt" type="button" id="checkTimeBtn" value="查  询" style="cursor:pointer;" /></td>
							</tr>
							<tr>
								<td class="tds" style="text-align: right;">身份证明号码：&nbsp;</td>
								<td class="tdl" style="text-align: left;width: 250px">
									<input type="text" name="sfzmhm" id="sfzmhm" value="${sfzmhm}" />									
								</td>
								<td class="tds" style="text-align: right;">状态：&nbsp;</td>
								<td class="tdl" style="text-align: left;">
									<s:select theme="simple"  name="cjzt" id="cjzt" value="#request.cjzt" list="#{'1':'待车管复核','B':'车管补审','CT':'车管退办','2':'归档'}" headerKey="--" headerValue="==请选择=="></s:select>
								</td>
								<td><input class="bnt" type="button" id="checkSFBtn" value="查  询" style="cursor:pointer;" /></td>
							</tr>
						</table>
					</form>
					<div>
					    <input class="bnt" type="button" id="getBlockBtn" name="getBlockBtn" value="获待审数据" style="cursor:pointer;" />
					</div>
					<table class="datalist" width="100%" border="0"	cellpadding="0" cellspacing="0">
							<tr class="tr1">
								<th><!-- <input type="checkbox" id="boxAll" name="boxAll"/> -->序号</th>
								<th>姓名</th>
								<th>性别</th>
								<th>身份证明号码</th>
								<th>档案编号</th>
								<th>准驾车型</th>
								<th>联系电话</th>
								<th>审核状态</th>
								<th>操作</th>
							</tr>
						<s:if test="#request.jsrCjList.size > 0">
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
									<td>${cjVo.cjzt }</td>
									<td>
									 <s:if test='#cjVo.cjmac=="1" || #cjVo.cjmac=="B"' >
									     <a href="<%=request.getContextPath()%>/jsrdzda/jsrdzda_showJsrShPage.action?editType=cgCk&cjxh=${cjVo.cjxh}">复审</a>
									 </s:if>
								  <a href="<%=request.getContextPath()%>/jsrdzda/jsrdzda_showJsrShPage.action?editType=show&cjxh=${cjVo.cjxh}">查看</a>
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
		</div>
	</body>
</html>
