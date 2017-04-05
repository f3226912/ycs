<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<title>银行开户${editType}</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/content.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery/jquery-lightbox-0.5/css/jquery.lightbox-0.5.css"	media="screen" />
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/jquery-1.8.2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/public.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/jquery-ui-1.9.1.custom.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/artDialog4.1.6/jquery.artDialog.source.js?skin=chrome"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/artDialog4.1.6/plugins/iframeTools.source.js"></script>
<script type="text/javascript"	src="<%=request.getContextPath()%>/jquery/jquery-lightbox-0.5/js/jquery.lightbox-0.5.js"></script>
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
         //图片放大
         $(function(){
			$("#photoToImg").lightBox();
		 });
	  	 if(editType == "查看"){
	      	var status = '${yhUser.status}';  //状态
            if(status !=null && status!=''){
	           if(status=='1'){//设置默认选中
	             $("input[type=radio][value=1]").attr("checked","checked");
	           }else{
	             $("input[type=radio][value=0]").attr("checked","checked");
	          }
            }
			$("input").not('.bnt').each(function(){
				$(this).attr("disabled", "disabled");
			});
         }
         if(editType=='修改'){
              $("#yzYhdm").attr("disabled",true);
              var status = '${yhUser.status}';  //状态
              if(status !=null && status!=''){
	            if(status=='1'){//设置默认选中
	             $("input[type=radio][value=1]").attr("checked","checked");
	            }else{
	             $("input[type=radio][value=0]").attr("checked","checked");
	           }
             }
         }     
          //添加银行信息
        $('#addBtn').click(function(){
            var yzYhdm = document.getElementById("yzYhdm");
			if(checknotnull(yzYhdm,'请填写银行代码!') != "true"){
				return false;
			}
			var yhxm = document.getElementById("yhxm");
			if(checknotnull(yhxm,'请填写银行名称!') != "true"){
				return false;
			}
			var ymmm = document.getElementById("ymmm");
			if(checknotnull(ymmm,'请填写银行密码!') != "true"){
				return false;
			}
			var zjjgdmz = document.getElementById("zjjgdmz");
			if(checknotnull(zjjgdmz,'请填写组织机构证代码!') != "true"){
				return false;
			}
			var frmc = document.getElementById("frmc");
			if(checknotnull(frmc,'请填写法人名称!') != "true"){
				return false;
			}
			
			var lxrmc = document.getElementById("lxrmc");
			if(checknotnull(lxrmc,'请填写银行联系人名称!') != "true"){
				return false;
			}
			
			var lxdh = document.getElementById("lxdh");
			if(checknotnull(lxdh,'请填写银行联系电话!') != "true"){
				return false;
			}else{
			   var strLxdh = checkmobile(lxdh.value);
		       if(strLxdh!="true") {
		          lxdh.style.borderColor = '#FF0000';
		          lxdh.focus();
		         return false;
		       }
			}
			var lxdz = document.getElementById("lxdz");
			if(checknotnull(lxdz,'请填写银行联系地址!') != "true"){
				return false;
			}
			  var sdyxqz = document.getElementById("yxqz");
		 	  if(checknotnull(sdyxqz,'请选择有效期止!') != "true"){
			 	return false;
			  }
			var fileName= document.getElementById("picPath").value;
			if(fileName!=''){
			    checkPic();
			}
            var url="<%=request.getContextPath()%>/dydj/dydj_addYHUser.action";
            $("#addForm").attr("action",url);	
	        $("#addForm").submit();
	    });
	});
	 function checkPic() {
          var picPath = document.getElementById("picPath").value;;
          var type = picPath.substring(picPath.lastIndexOf(".") + 1, picPath.length).toLowerCase();
          if (type != "jpg" && type != "bmp" && type != "gif" && type != "png") {
              alert("请上传正确的图片格式");          
               var file =$("#picPath")
                   file.after(file.clone().val(""));
                   file.remove();    
              return false;
          }
          return true;
      }
        //图片预览
     function PreviewImage(upload) {
          if (checkPic()) {
              try {
                  var imgPath;      //图片路径
                  var Browser_Agent = navigator.userAgent;
                  //判断浏览器的类型
                  if (Browser_Agent.indexOf("Firefox") != -1) {
                      //火狐浏览器
                     //getAsDataURL在Firefox7.0 无法预览本地图片，这里需要修改
                      imgPath = upload.files[0].getAsDataURL();
                      document.getElementById('photoImg').src = imgPath;
                  } else {
                      //IE浏览器 ie9 必须在兼容模式下才能显示预览图片
                     document.getElementById('photoImg').src=upload.value;
                     imgPath=upload.value;
                  }
                 $("#photoToImg").attr("href",imgPath);
              } catch (e) {
                  alert("请上传正确的图片格式");
                   var file =$("#picPath")
                   file.after(file.clone().val(""));
                   file.remove();
                   return false;
              }
          }
      }
    function checkUpLower(obj){
	  	obj.value = obj.value.replace(" ","");
		obj.value = obj.value.replace("　","");
		obj.value = obj.value.toUpperCase();
	}  
     function clearform(flag){
		$("input").not(".bnt").each(function(){
			$(this).val("");
		});
	 }
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
	<br>
	<br/>
	 <form action="" id="addForm" name="addForm" method="post"  enctype="multipart/form-data" target="abc">
	   <input type="hidden" name="editType" id="editType" value="${editType}"/>
	   <input type="hidden" name="yhUser.id" value="${yhUser.id}">
	   <iframe id="abc" name="abc" width="0" height="0" style="display: none;"></iframe>
		<div style="width:80%;margin: 0 auto;">
		   <table  class="table1" width="90%" align="center" border="0" cellpadding="0" cellspacing="0">
			  <tr>
			    <td class="tds" height="30" colspan="4">${editType}用户信息</td>
			  </tr>
			  <tr>
			    <td class="tds" style="text-align: right;"><font color="red">*</font>银行代码：</td>
			    <td class="tds" style="text-align: left;"><input type="text" id="yzYhdm" name="yhUser.yzYhdm" value="${yhUser.yzYhdm}"  onkeyup="checkUpLower(this)"/></td>
			
			    <td class="tds" style="text-align: right;" rowspan="10">电子图鉴：</td>
			    <td class="tds" rowspan="10" align="left">
				    <ul style="list-style: none;padding: 0px; text-align: left; margin-left: 0px;margin-bottom 0px;height: 240px" >
				      <s:if test="#request.editType == '查看'">
				      	   <li>
                            <a id="photoToImg" href="<%=request.getContextPath()%>/dydj/dydj_showImage.action?id=${yhUser.id}"><img id="photoImg" src="<%=request.getContextPath()%>/dydj/dydj_showImage.action?id=${yhUser.id}" border="0" style="width:200px;height: 230px"></a>
                          </li>
					 </s:if>						
					<s:else>
					    <s:if test="#request.editType =='修改'">
					         <li>
                               <a id="photoToImg" href="<%=request.getContextPath()%>/dydj/dydj_showImage.action?id=${yhUser.id}"><img id="photoImg" src="<%=request.getContextPath()%>/dydj/dydj_showImage.action?id=${yhUser.id}" border="0" style="width:200px;height: 230px"></a>
                             </li>
					    </s:if>
					    <s:else>
						    <li style="width: 200px;padding:0px;margin-bottom: 0px;">
								 <a id="photoToImg" href="<%=request.getContextPath()%>/images/cp.gif"><img id="photoImg" src="<%=request.getContextPath()%>/images/cp.gif" border="0" style="width:200px;height: 230px"></a>
						     </li>				      
					    </s:else>
					      <li style="text-align: left; padding:0px;margin-bottom: 0px;">
					          <input type="file" name="file" id="picPath" name="picPath" onchange="PreviewImage(this);" id="file" title="请选择车行电子图鉴地址" style="width:250px;"/>
					      </li>
					</s:else>
				  </ul>
			    </td>
			  </tr>
			  <tr>
			    <td class="tds" style="text-align: right;"><font color="red">*</font>银行名称：</td>
			    <td class="tds" style="text-align: left;"><input type="text" id="yhxm" name="yhUser.yhxm" value="${yhUser.yhxm}"  onkeyup="checkUpLower(this)"/></td> 
			  </tr>
			  <tr>
			    <td class="tds" style="text-align: right;"><font color="red">*</font>银行密码：</td>
			    <td class="tds" style="text-align: left;"><input type="password" id="ymmm" name="yhUser.ymmm" value="${yhUser.ymmm}" size="22"  onkeyup="checkUpLower(this)" maxlength="22"/></td>
			 </tr>
			 <tr>
			    <td class="tds" style="text-align: right;">用户状态：</td>
			  	<td class="tds" style="text-align: left;">
			  	  <input type="radio" name="yhUser.status" value="1" id="status" checked="checked"/>活动&nbsp;&nbsp;&nbsp;
			  	  <input type="radio" name="yhUser.status" value="0" id="status"/>冻结</td>
			  </tr>
			 <tr>
			    <td class="tds" style="text-align: right;"><font color="red">*</font>组织机构证代码：</td>
			    <td class="tds" style="text-align: left;"><input type="text" id="zjjgdmz" name="yhUser.zjjgdmz" value="${yhUser.zjjgdmz}" onkeyup="checkUpLower(this)"/></td>
			 </tr>
			 <tr>
			   <td class="tds" style="text-align: right;"><font color="red">*</font>法人名称：</td>
			   <td class="tds" style="text-align: left;"><input type="text" id="frmc" name="yhUser.frmc" value="${yhUser.frmc}"  onkeyup="checkUpLower(this)"/></td>
			 </tr>
			  <tr>
			     <td class="tds" style="text-align: right;"><font color="red">*</font>银行联系人名称：</td>
			     <td class="tds" style="text-align: left;"><input type="text" id="lxrmc" name="yhUser.lxrmc" value="${yhUser.lxrmc}"  onkeyup="checkUpLower(this)"/></td>
			  </tr>
			  <tr>
			    <td class="tds" style="text-align: right;"><font color="red">*</font>银行联系电话：</td>
			    <td class="tds" style="text-align: left;" ><input type="text"  id="lxdh" name="yhUser.lxdh" value="${yhUser.lxdh}"/></td>
			  </tr>
			  <tr>
			    <td class="tds" style="text-align: right;"><font color="red">*</font>银行地址：</td>
			    <td class="tds" style="text-align: left;"><input type="text" id="lxdz" name="yhUser.lxdz" value="${yhUser.lxdz}" onkeyup="checkUpLower(this)"/></td>
			  </tr>
			  <tr>
			    <td class="tds" style="text-align: right;"><font color="red">*</font>有效期止：</td>
			    <td class="tds" style="text-align: left;"><input type="text" name="yhUser.yxqz" id="yxqz" class="Wdate" value="<s:date name="yhUser.yxqz" format="yyyy-MM-dd"/>" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',startDate:'%y-%M-%d',minDate:'%y-%M-%d'})" /></td>
			  </tr>
			  <tr>
			   <td class="tds" colspan="4">
			   <s:if test="#request.editType!='查看'">
			      <input type="button" name="addBtn" id="addBtn" value="保存" class="bnt" style="cursor:pointer;"/>
			   </s:if>
			   <s:else>
			      <input type="button" name="goback" id="goback" value="返回" class="bnt" onclick="javascript:history.go(-1);" style="cursor:pointer;"/>
			   </s:else>
			   </td>
			  </tr>
			  <tr>
			   <td class="tds" colspan="4" height="28">&nbsp;&nbsp;</td>
			  </tr>
		  </table> 
		</div>
	</form>
</body>
</html>