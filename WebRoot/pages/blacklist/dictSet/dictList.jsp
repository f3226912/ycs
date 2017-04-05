<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<title>限制代办业务基础字典设置</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/content.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/jquery-1.8.2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/public.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/artDialog4.1.6/jquery.artDialog.source.js?skin=chrome"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/artDialog4.1.6/plugins/iframeTools.source.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/jquery-ui-1.9.1.custom.js"></script>
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
        var type ='${type}';
        if(type!=''){
          if(type =='jsyy'){
             $('#jsDiv').show();
	         $('#dbDiv').hide();
          }else if(type =='sdyy'){
             $('#sdDiv').show();
	         $('#dbDiv').hide();
          }else{
            $('#addBtn').hide();
          }
           $("input[type=radio][value="+type+"]").attr("checked",true);
        }else{
             $('#addBtn').hide();
        }      
      $('#addBtn').click(function(){
           var radioV =$('input[name="dictType"]:checked').val();
           var temp='sdyy';
           var url='<%=request.getContextPath()%>/blacklist/black_initDictAdd.action?type=';
           if(radioV == temp){
               url+=temp;
            }else{
               url+=radioV;
            }
         art.dialog.open(url,{width:450,height:300,lock:true,opacity:0.1,resize:false,title:'字典录入'});  
     });
  });

  function changeM(radio){  
	   if(radio.value=='dbNum'){  
	     $('#dbDiv').show();
	     $('#sdDiv').hide();
	     $('#jsDiv').hide();
	     $('#addBtn').hide();
	   }else if(radio.value=='sdyy'){
	     $('#sdDiv').show();	
	     $('#dbDiv').hide();
	     $('#jsDiv').hide();
	     $('#addBtn').show();
	   }else{
	     $('#jsDiv').show();
	     $('#sdDiv').hide();
	     $('#dbDiv').hide();
	     $('#addBtn').show();
	   }
  } 
 function openOne(xh,dmz,dmms1){
	art.dialog({
		width:'40%',
	    content: $('#divSelPtmoValue').html(),
	    title: '修改',
		lock: true,
	    opacity: 0.1			
	});
   $("#dmms1").text(dmms1);
   $("#dmz").val(dmz);
   $("#xh").val(xh);
 }
 function openSD(xh,dmms1,dmz,lx){
 	art.dialog({
		width:'40%',
	    content: $('#divSdValue').html(),
	    title: '修改信息',
		lock: true,
	    opacity: 0.1			
	});
    $("#sd_dmms1").text(dmms1);
    $("#sd_dmlb").text(lx);
    $("#sd_dmz").text(dmz);
    $("#sd_xh").val(xh);
 }
 function editeInfo(){
    var dmz = document.getElementById("dmz");
	if(checknotnull(dmz,'请填写代码值!') != "true"){
		return false;
	}
	if(isNaN(dmz.value)){
	   alert('代码值必须是数字!');
	   dmz.style.borderColor = '#FF0000';
	   dmz.focus();
	   return false;
	}
	 var radioV =$('input[name="dictType"]:checked').val();
	 $("#type").val(radioV);
	 var url="<%=request.getContextPath()%>/blacklist/black_editDbDict.action";
      $("#form2").attr("action",url);	
     $("#form2").submit();
 }
  //锁定代码内容
 function editSD(){
     var sd_dmms1 = document.getElementById("sd_dmms1");
	 if(checknotnull(sd_dmms1,'请填写代码说明!') != "true"){
	 	return false;
	 }else{
	   if(sd_dmms1.value.length>200){
	       alert("代码说明内容长度不能超过200位!");
	       return false;
	   }
	 }
	 var radioV =$('input[name="dictType"]:checked').val();
	 $("#sd_type").val(radioV);
     var url="<%=request.getContextPath()%>/blacklist/black_editSDDict.action";
     $("#formsd").attr("action",url);	
     $("#formsd").submit();
 }
   //删除
  function delHmd(xh){
       if(confirm("确定要删除吗？") == true){
            var type=$('input[name="dictType"]:checked').val();
          	$.ajax({
			type:'POST',
			url: '<%=request.getContextPath()%>/blacklist/black_delDict.action',
			data:{'xh':xh,'type':type},//发送的参数
			dataType: 'html',
			success:function(data){
				if(data == 1){
				    alert("操作成功!");
				    window.location.href="<%=request.getContextPath()%>/blacklist/black_getDictList.action?type="+type;
				}else if(data == 0){
					alert("操作失败!");//有可能是此条数据不存在。
					window.location.href="<%=request.getContextPath()%>/blacklist/black_getDictList.action?type="+type;
				}else{
					alert("系统繁忙,请稍候再试!");
				}
			}
		});
      }
  }
</script>
	<body>
	  <br>
	  <br>
	  <div style="width:80%;margin: 0 auto;">
		   <table class="table1"  width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
			  <tr>
			    <td class="tds" height="25" colspan="4" >限制代办业务基础字典设置</td>
			  </tr>
			  <tr>
			    <td class="tds" style="text-align: right;">代码类型:</td>
			    <td class="tds" style="text-align: left;" colspan="3">
			    <input type="radio" name="dictType" value="dbNum" checked="checked"  onclick="changeM(this)"/>未备案代办员可代办数   &nbsp;&nbsp;
	            <input type="radio" name="dictType" value="sdyy"  onclick="changeM(this)"/> 锁定原因  &nbsp;&nbsp;
	            <input type="radio" name="dictType" value="jsyy"  onclick="changeM(this)"/> 解锁原因</td>
			  </tr>
		  </table> 
		  <input type="button" name="addBtn"  id="addBtn" value="录入字典" class="bnt"/>
	   </div>
		  <br>
		  <div id="dbDiv">
		     <table class="datalist" id="tableList" style="margin: 0 auto;width:80%;margin-left: 100px" border="0" cellpadding="0" cellspacing="0">
			  <tr class="tr1">
			    <th>序号</th>
			    <th>代码类别</th>	
			    <th>可代办宗数</th>
			    <th>操作</th>
			  </tr>
			 <s:if test="#request.dbcsList.size>0" >
		         <s:iterator id="dict" value="#request.dbcsList" status="st">
				  <tr id="${dict.xh}" class="<s:if test="#st.odd == false">altrow</s:if>">
				    <td>${st.index+1}</td>
				    <td>${dict.dmms1}</td>
				    <td>${dict.dmz}</td>
			        <td>
			           <a href="javascript:void(0);" onclick="openOne('${dict.xh}','${dict.dmz}','${dict.dmms1}')">修改</a>
			        </td>
				  </tr>  
				 </s:iterator>	
			  </s:if>   
			  <s:else>
			    <tr>
				  <td colspan="9" align="center">
					<span style="color: red">没有找到相关数据<input type="hidden" value="0" id="itemSize"/> </span>
				  </td>
				</tr>
			  </s:else>	  
			</table>
		  </div>
          <div style="display: none;" id="sdDiv">
		     <table class="datalist" id="tableList" style="margin: 0 auto;width:80%;margin-left: 100px" border="0" cellpadding="0" cellspacing="0">
			  <tr class="tr1">
			    <th>序号</th>
			    <th>代码类别</th>
			    <th>代码说明</th>			    
			    <th>操作</th>
			  </tr>
			 <s:if test="#request.sjzdSdList.size>0" >
		         <s:iterator id="dict" value="#request.sjzdSdList" status="st">
				  <tr id="${dict.xh}" class="<s:if test="#st.odd == false">altrow</s:if>">
				     <td>${st.index+1}</td>
				     <td>${dict.dmlb}</td>
				    <td>${dict.dmms1}</td>				    
			       <td>
			           <a href="javascript:void(0);" onclick="openSD('${dict.xh}','${dict.dmms1}','${dict.dmz}','${dict.dmlb}')">修改</a>&nbsp;&nbsp;
			           <a href="javascript:void(0);" onclick="delHmd('${dict.xh}')">删除</a>
			       </td>
				  </tr>  
				 </s:iterator>
			  </s:if>   
			  <s:else>
			    <tr>
				  <td colspan="9" align="center">
					<span style="color: red">没有找到相关数据<input type="hidden" value="0" id="itemSize"/> </span>
				  </td>
				</tr>
			  </s:else>	  
			</table>
		  </div>
		  <div style="display: none;" id="jsDiv">
		     <table class="datalist" id="tableList" style="margin: 0 auto;width:80%;margin-left: 100px" border="0" cellpadding="0" cellspacing="0">
			  <tr class="tr1">
			    <th>序号</th>
			    <th>代码类别</th>
			    <th>代码说明</th>
			    <th>操作</th>
			  </tr>
			 <s:if test="#request.sjzdJsList.size>0" >
		         <s:iterator id="dict" value="#request.sjzdJsList" status="st">
				  <tr id="${dict.xh}" class="<s:if test="#st.odd == false">altrow</s:if>">
				    <td>${st.index+1}</td>
				    <td>${dict.dmlb}</td>
				    <td>${dict.dmms1}</td>
			       <td>
			           <a href="javascript:void(0);" onclick="openSD('${dict.xh}','${dict.dmms1}','${dict.dmz}','${dict.dmlb}')">修改</a>&nbsp;&nbsp;
			           <a href="javascript:void(0);" onclick="delHmd('${dict.xh}')">删除</a>
			       </td>
				  </tr>  
				 </s:iterator>
			  </s:if>   
			  <s:else>
			    <tr>
				  <td colspan="9" align="center">
					<span style="color: red">没有找到相关数据</span>
				  </td>
				</tr>
			  </s:else>	  
			</table>
      </div>
     <div id="divSelPtmoValue" style="display:none;overflow-y:scroll;overflow-x:none;width:1000px;">
		<div style="width:100%;height: 100px;">
		  <form action="" method="post" id="form2" name="form2" target="bcd">
		   <iframe id="bcd" name="bcd" width="0" height="0" style="display: none;"></iframe>
			<input type="hidden" name="dict.xh" id="xh" />
			<input type="hidden" name="type" id="type" value=""/>
			<table  width="100%" height="100px" border="0" cellpadding="0" cellspacing="0" class="datalist" >
				<tr class="tr1" id="selTr">
					<td style="text-align: right;width: 100px">代码类别:</td>
					<td style="text-align: left;"><label id="dmms1"></label></td>
				</tr>
				<tr>
					<td style="text-align: right;width: 100px">代码值:</td>
					<td style="text-align: left;"><input type="text" name="dict.dmz" id="dmz" value="" maxlength="20"/></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="button" value="保存" onclick="editeInfo()" class="bnt" style="cursor:pointer;"/>
					</td>
				</tr>
			</table>
		  </form>
		</div>
	</div>
	<div id="divSdValue" style="display:none;overflow-y:scroll;overflow-x:none;width:1000px;">
		<div style="width:100%;height: 200px;">
		  <form action="" method="post" id="formsd" name="formsd" target="abc">
		    <iframe id="abc" name="abc" width="0" height="0" style="display: none;"></iframe>
			<input type="hidden" name="sd_xh" id="sd_xh" />
			<input type="hidden" name="type"  id="sd_type" value=""/>
			<table  width="100%" height="200px" border="0" cellpadding="0" cellspacing="0" class="table1" >
				<tr>
					<td class="tds" style="text-align: right;width: 100px">代码类型:</td>
					<td class="tds" style="text-align: left;"><label id="sd_dmlb"></label></td>
				</tr>
				<tr>
					<td class="tds" style="text-align: right;width: 100px">代码值:</td>
					<td class="tds" style="text-align: left;">&nbsp;&nbsp;<label id="sd_dmz"></label></td>
				</tr>
				<tr>
					<td class="tds" style="text-align: right;width: 100px">代码说明:</td>
					<td class="tds" style="text-align: left;"><textarea cols="30" rows="4" name="sd_dmms1" id="sd_dmms1"></textarea></td>
				</tr>
				<tr>
					<td class="tds" colspan="2" align="center">
						<input type="button" value="保存" onclick="editSD()" class="bnt" style="cursor:pointer;"/>
					</td>
				</tr>
			</table>
		  </form>
		</div>
	</div>
</body>
</html>