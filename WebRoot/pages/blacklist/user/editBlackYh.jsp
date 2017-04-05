<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<title>${editType}限制代办业务用户</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/content.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/jquery-1.8.2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/jquery-ui-1.9.1.custom.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/public.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/artDialog4.1.6/jquery.artDialog.source.js?skin=chrome"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/artDialog4.1.6/plugins/iframeTools.source.js"></script>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/My97DatePicker/WdatePicker.js"></script>
<style> 
	html{ 
		overflow:scroll;
		scrollbar-base-color:#c7e5ff;
		scrollbar-track-color:#FFFFFF;
	} 
</style>
</head>
<script type="text/javascript">   
   $(document).ready(function(){
        var editType = '${editType}';
		if(editType == "修改"){
			var sdlx ='${hmdyh.sdlx}';
			var dqzt ='${hmdyh.dqzt}';
			if(dqzt == '1'){
			 if(sdlx=='1'){
			   $("input[type=radio]").attr("checked",sdlx);
			   var sdlxR =$('input[name="hmdyh.sdlx"]:checked').val();
			   $('#dialogTable').find('tr:eq(4)').show();
			  }
			}
		}
	     //修过黑名单状态
        $('#editBtn').click(function(){
            var dqzt = '${hmdyh.dqzt}';		
           if(dqzt=='1'){
              var jsyy = document.getElementById("jsyy");
            if(jsyy.value=='--'){
                alert("请选择解锁原因!");
		        sdyy.focus();
                return false;
            }
           }else{
             var sdyy = document.getElementById("sdyy");
             if(sdyy.value=='--'){
                 alert("请选择锁定原因!");
		         sdyy.focus();
                 return false;
             }
           }
            var sdlx =$('input[name="hmdyh.sdlx"]:checked').val();
			if(sdlx=='1'){
			   var sdyxqz = document.getElementById("sdyxqz");
		 	   if(checknotnull(sdyxqz,'请选择锁定有效期止!') != "true"){
			 	    return false;
			   }
			}
		 var url="<%=request.getContextPath()%>/blacklist/black_editHmdYh.action";
            $("#editForm").attr("action",url);	
	        $("#editForm").submit();
	    });		
	});
	
      function changeM(radio){  
		   if(radio.id=='sdlx'){  
		     $('#dialogTable').find('tr:eq(4)').show();
		   }else{
		     $('#dialogTable').find('tr:eq(4)').hide();
		   }
      } 
</script>
	<body>
	<br>
	 <form action="" onsubmit="" id="editForm" name="editForm" method="post" target="abc">
	 <iframe id="abc" name="abc" width="0" height="0" style="display: none;"></iframe>
	      <input type="hidden" value="${editType} " name="editType" id="editType"/>
	      <input type="hidden" value="${hmdyh.xh}" name="hmdyh.xh" id="xh">
		   <table  class="table1" id="dialogTable"  width="420px" height="250px" align="center" border="0" cellpadding="0" cellspacing="0">
			  <tr>
			    <td class="tds" height="25" colspan="3">${editType}限制代办业务用户信息状态</td>
			  </tr>
			  <s:if test="#request.hmdyh.dqzt==2">
			      <tr>
			      <td class="tds" style="text-align: right;"><font color="red">*</font>锁定原因:</td>
			      <td class="tds" style="text-align: left;"  colspan="2">
			       <s:select theme="simple" name="hmdyh.sdyy" id="sdyy" list="#request.sjzdSdList" value="#request.hmdyh.dmms1" listKey="dmz" listValue="dmms1" headerKey="--" headerValue="--请选择--" />
			      </td>
			     </tr>
			  </s:if>
			  <s:else>  
	             <tr>
			      <td class="tds" style="text-align: right;"><font color="red">*</font>解锁原因:</td>
			      <td class="tds" style="text-align: left;"  colspan="2">
			       <s:select theme="simple" name="hmdyh.jsyy" id="jsyy" list="#request.sjzdJsList"  listKey="dmz" listValue="dmms1" headerKey="--" headerValue="--请选择--" />
	              </td>
	             </tr>
			  </s:else>
			  <tr>
			    <td class="tds" style="text-align: right;">备注:</td>
			    <td class="tds" style="text-align: left;"  colspan="2">
			       <textarea cols="30" rows="4" name="<s:if test="#request.hmdyh.dqzt==2">hmdyh.sdbz</s:if><s:else>hmdyh.jsbz</s:else>" id="sdbz"></textarea>
			   </td>
			  </tr>
			   <s:if test="#request.hmdyh.dqzt==2">
			      <tr>
				    <td class="tds" style="text-align: right;"><font color="red">*</font>锁定类型：</td>
				    <td class="tds" style="text-align: left;"  colspan="2">
				      <input type="radio" name="hmdyh.sdlx" value="2" id="sdlxs" checked="checked" onclick ="changeM(this)"/>永久
				      <input type="radio" name="hmdyh.sdlx" value="1" id="sdlx" onclick ="changeM(this)"/>日期有效止&nbsp;&nbsp;&nbsp;
				    </td>
				  </tr>
				  <tr style="display: none;">
				    <td class="tds" style="text-align: right;"><font color="red">*</font>锁定有效期止：</td>
				    <td class="tds" style="text-align: left;"  colspan="2"><input type="text" name="hmdyh.sdyxqz" id="sdyxqz" class="Wdate" value="<s:date name="yxqzStr" format="yyyy-MM-dd"/>"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',startDate:'%y-%M-%d',minDate:'%y-%M-%d'})" /></td>
				  </tr>  
			   </s:if>
			  <tr>
			     <td class="tds" style="text-align: center;" colspan="3">
				  <input type="button" name="editBtn" id="editBtn" value="保存" class="bnt" style="cursor:pointer;"/>    
			   </td>
			  </tr>	
		  </table>             
	</form>
</body>
</html>