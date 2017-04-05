<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<title>添加银行代办人</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/content.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/jquery-1.8.2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/artDialog4.1.6/jquery.artDialog.source.js?skin=chrome"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/artDialog4.1.6/plugins/iframeTools.source.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/jquery-ui-1.9.1.custom.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/public.js"></script>
<style> 
	html{ 
		overflow:auto;
		scrollbar-base-color:#c7e5ff;
		scrollbar-track-color:#FFFFFF;
	} 
</style>
</head>
<script type="text/javascript">   
   $(document).ready(function(){
           //添加用户
        $('#addBtn').click(function(){
            var dbrSfzmhm= $.trim($("#dbrSfzmhm").val());
            var dbrXm = $.trim($("#dbrXm").val());
            var yxqz = $.trim($("#yxqz").val());
            var dbrSjhm = $("#dbrSjhm").val();
            var dbrLxdz = $("#dbrLxdz").val();
            if(dbrSfzmhm == null || dbrSfzmhm==''){
              alert("请输入身份证明号码!");
              return false;
            }
            if(dbrXm == null || dbrXm==''){
               alert("请输入姓名!");
               return false;
            }
            var sex = $("input[name='yhDbr.dbrSex']:checked").val();
            if(sex == null || sex == ''){
            	alert("请选择性别!");
               return false;
            }
            if(dbrSjhm == null || dbrSjhm==''){
               alert("请输入手机号码!");
               return false;
            }
            if(yxqz == null || yxqz==''){
               alert("请输入有效期!");
               return false;
            }
            if(dbrLxdz == null || dbrLxdz==''){
               alert("请输入联系地址!");
               return false;
            }
	        $.ajax({
				cache:false,
				async:false,
				url:'<%=request.getContextPath()%>/dydj/dydj_editDydjYhdbrInfo.action',
				type:'post',
				data:$("#addForm").serialize(),
				dataType:'json',
				error:function(XmlHttpRequest,textStatus, errorThrown){
					exception(XmlHttpRequest.responseText);
					return false;
				},
				success: function(result){
					var yhdbrid = $("#yhdbrid").val();
					var mess = "添加成功!";
					if(yhdbrid != null && yhdbrid != ''){
						mess = "修改成功!";
					}
					if(result == "1"){
						alert(mess);
						var win = art.dialog.open.origin; 
						win.location.reload(); 
						window.close(); 
						art.dialog.close();
					}else if(result == "2"){
						alert("该代办人已经备案，无需多次备案!");
					}else{
						alert("编辑失败!");
					}
				}
			});
	    });
	});
   
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
	<body>
	<br/>
	 <form action="<%=request.getContextPath()%>/dydj/dydj_editDydjYhdbrInfo.action" id="addForm" name="addForm" method="post">
		<div style="width:90%;margin: 0 auto;">
		   <table  class="table1"  width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
			  <tr>
			    <td class="tds" height="25" colspan="4">${cztype }银行代办人信息
			    	<input type="hidden" name="yhDbr.id" id="yhdbrid" value="${yhDbr.id}"/>
			    	<input type="hidden" name="yhDbr.synFlag" id="synFlag" value="UW"/>
			    	<input type="hidden" name="yhDbr.tranFlag" id="tranFlag" value=""/>
			    	<input type="hidden" name="yhDbr.tranDate" id="tranDate" value=""/>
			    </td>
			  </tr>
			  <tr>
			    <td class="tds" style="text-align: right;"><font color="red">*</font>身份证明号码：</td>
			    <td class="tds" style="text-align: left;"><input type="text" name="yhDbr.dbrSfzmhm" id="dbrSfzmhm" onkeyup="clearspace(this)" onblur="clearallspace(this)" value="${yhDbr.dbrSfzmhm}"/>
			    	<input type="hidden" name="yhDbr.yhUserId" id="yhUserId" value="${yhUserId}"/>
			    </td>
			    <td class="tds" style="text-align: right;"><font color="red">*</font>姓名：</td>
			    <td class="tds" style="text-align: left;"><input type="text" name="yhDbr.dbrXm" id="dbrXm" onkeyup="clearspace(this)" onblur="clearallspace(this)" value="${yhDbr.dbrXm}"/></td>
			  </tr>
			  
			  <tr>
			    <td class="tds" style="text-align: right;"><font color="red">*</font>性别：</td>
			    <td class="tds" style="text-align: left;"><input type="radio" name="yhDbr.dbrSex" id="dbrSex1" value="男"  <s:if test='#request.yhDbr.dbrSex == "男"'>checked="checked"</s:if>/>男
			    	<input type="radio" name="yhDbr.dbrSex" id="dbrSex0" value="女" <s:if test='#request.yhDbr.dbrSex == "女"'>checked="checked"</s:if>/>女
			    </td>
			    <td class="tds" style="text-align: right;"><font color="red">*</font>手机号码：</td>
			    <td class="tds" style="text-align: left;"><input type="text" name="yhDbr.dbrSjhm" id="dbrSjhm" value="${yhDbr.dbrSjhm}"/></td>
			  </tr>
			  
			  <tr>
			    <td class="tds" style="text-align: right;"><font color="red">*</font>是否有效:</td>
			    <td class="tds" style="text-align: left;"><select id="flag" style="width: 153px;" name="yhDbr.flag">
						<option value="0" <s:if test="#request.user.isValible == 0">selected="selected"</s:if>>有效</option>
						<option value="1" <s:if test="#request.user.isValible == 1">selected="selected"</s:if>>无效</option>
					</select>
				</td>
				 <td class="tds" style="text-align: right;"><font color="red">*</font>有效期止:</td>
			    <td class="tds" style="text-align: left;"><input type="text" name="yhDbr.yxqz" id="yxqz" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="${yhDbr.yxqz}"/>
				</td>
			    
			  </tr>
			  
			  <tr>
			    <td class="tds" style="text-align: right;"><font color="red">*</font>联系地址:</td>
			    <td class="tds" style="text-align: left;" colspan="3"><input type="text" name="yhDbr.dbrLxdz" id="dbrLxdz" value="${yhDbr.dbrLxdz}" style="width: 250px;"/>
				</td>
			  </tr>
			  <tr>
			   <td class="tds" style="text-align: center;" colspan="4">
			      <input type="button" name="addBtn" id="addBtn" value="保存" class="bnt" style="cursor:pointer;"/>
			   </td>
			  </tr>
		  </table> 
		</div>
	</form>
</body>
</html>