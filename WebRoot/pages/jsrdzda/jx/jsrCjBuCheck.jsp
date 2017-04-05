<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
  <head>
    <title>驾驶人信息采集补审界面</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/content.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/module.css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/jquery-1.8.2.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/artDialog4.1.6/jquery.artDialog.source.js?skin=chrome"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/artDialog4.1.6/plugins/iframeTools.source.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/public.js"></script>
    <script	src="<%=request.getContextPath()%>/imageZoom/CJL.0.1.min.js"	type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/imageZoom/test.js" type="text/javascript"></script>
   <style type="text/css">
    .izImage,.izViewer {background: #fff;}		
	.izImage {width: 580px;	height: 365px;cursor: pointer;}		
	.izViewer {width: 423px;height: 500px;position: absolute;left:590px;display: none;}			
	.izViewer div {position: absolute;border: 0 dashed #999;top: 0;	left: 0;z-index: 999;width: 100%;height:100%;}		
   </style>
   <!-- 审核不通过的数据 -->
	<script type="text/javascript">
	$(document).ready(function() {		
	});
	function checkBoxItem(obj){	  
	    if(obj.value=='A1005'){
	       if(obj.checked){
	          $("#otherTr").show();
	       }else{	          
	          $("#otherTr").hide();
	       }	     
	    }	     
	}
	var chuli;
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
	 //改变地址显示图片	
	 function checkImgUrl(index,xh,picStr) {		 
		 if(index==null){
			 $.ajax({
		 		 async:true,
		 		 cache:false,
		 		 type:'POST',
		 		 dataType:'json',
		 		 url:"<%=request.getContextPath()%>/jsrdzda/jsrdzda_addViewOnPhoto.action",
		 		 data:{'xh':xh},
		 		 success:function(returnData){
		                var proArray=returnData;  //返回的是流水信息对象
		                if(proArray!="" && proArray!=null){
		                    if(proArray.xh!="" && proArray.xh!=null){
			                    if(proArray.zt=='CT' || proArray.zt=='JT'){
			                    	 $("#idImage"+xh).attr("src",'<%=request.getContextPath()%>/images/cp.gif');   //历史审核图片
                                    alert("暂时还未上传新的图片!");                                 
					            }else{
					            	$("#idImage"+xh).attr("src",'http://100.100.21.61/cmp_new/view_pic.asp?efid='+proArray.picStr);   //待审核图片
					            	alert("已上传新的图片,请刷新页面!");
							    }		                    	 
		                    }else{
		                       exception(proArray.remark); //报错信息，用改对象的remark封装。
		                    }
		                }else{
		                  alert('没有查到相应的数据,请核实后再查询!');     
		                }
		 		 },
		 		 error: function(xhr,msg,e) {
		   			alert(e);		 
		 		 }
		 	});			
		 }else{
			 $("#idImage"+xh).attr("src",'http://100.100.21.61/cmp_new/view_pic.asp?efid='+picStr);   //历史审核图片
	     }
		 //一定要重新更改放大的src
	     var position= $("#idImage"+xh).offset();
	     $("#idViewer"+xh).css("left",position.left);
	     $("#idViewer"+xh).css("top",position.top);	     
	     var izx = new ImageZoom("idImage"+xh,"idViewer"+xh,{scale: 0,mouse:true ,rangeWidth:0,rangeHeight:0});	  
         izx.reset({originPic: $("#idImage"+xh).src, zoomPic: $("#idImage"+xh).src});  
	}
	var openDlog;
    function openDialog(photoXh,temp){
       if(photoXh!=''){
          $("#photoCjxh").val(photoXh);
	      var btnTxt= $("#shBtn"+photoXh).val();     
	      if(btnTxt=='不合格' || temp ){//按钮为不合格 或者有修改链接时，则选择退办原因或者重新修改退办原因
	         $("#isUpdateYY").val("");  
	         if(temp){//表示修改原因
	            $("#isUpdateYY").val("haveYY");
	         }
		      openDlog = art.dialog({
				 width:'45%',
			    content: $('#shPhotoDiv').html(),
			    title: '图片审核结果',
				lock: true,
			    opacity: 0.1			
	          });
	          //必须打开dialog后才能清楚，不然找不到（不合格时才要清checked）
	          $(".aui_content input[name='sbztTbyy']").each(function(){
	             $(this).attr("checked",false);
	          });
	      }else{//按钮为合格或者有取消链接时：点击都要退办原因取消掉;
	          autoAccumulationYY(photoXh,null);
	      }
       }
    }    
    //关闭对话框
   function closeDiaLog(){
       if(openDlog!=undefined){
         openDlog.close();
       }       
    }    
   function checkTbFun(){
     var itemNum= 0;
     var itemsTxt="";
     var itemsVal="";
     var flag=false;
     //这里比较重要，因为在open一个dialog时，会产生一个新div对象，所以我们要通过工具找到那个新的div的id或者class
     $(".aui_content input[name='sbztTbyy']").each(function(){    
       if($(this).attr("checked")=="checked"){         
          var val = $(this).val();
              itemsTxt+=$(this).attr("title")+",";
		      itemsVal+=$(this).val()+",";
		      if($(this).val()=='A1005'){
		           flag =true;
		      }
          itemNum++;       
       }
      });
      var photoXh = $("#photoCjxh").val();
      $("#photoYY"+photoXh).val("");
      $("#photoYYTxt"+photoXh).val("");
      $("#phRemark"+photoXh).val("");
      if(itemNum==0){
         alert("请选择退办原因!");
         return false;
      }else{
          if(flag){//其他
              if($.trim($("#remark").val())==''){
                  alert("请填写备注信息!");
                  $("#remark").val("");
                  $("#remark").focus();
                  return false;
              }else{
                if($.trim($("#remark").val()).length>30){
                     alert("备注请控制在30字以内!");
                     $("#remark").focus();
                     return false;
                }else{
                   var remark=$("#remark").val();
                   $("#phRemark"+photoXh).val(remark);
               }  
            }
          }
          if(photoXh!=''){
            var isUpdate= $("#isUpdateYY").val();
            if(isUpdate==''){
	           //改按钮文字
		       if($("#shBtn"+photoXh).val()=='合格'){
		          $("#shBtn"+photoXh).val("不合格");
		       }else{
		          $("#shBtn"+photoXh).val("合格");
		       }
             }
             $("#ptHang"+photoXh).remove();
	         var tbyy = itemsTxt.substring(0,itemsTxt.length-1);
	         $("#photoYY"+photoXh).val(itemsVal.substring(0,itemsVal.length-1));//每一个的退办原因value
	         $("#photoYYTxt"+photoXh).val(tbyy);//每一个的退办原因text       
	         var phMark =$("#phRemark"+photoXh).val();
	         if(phMark!=''){
	           phMark=":"+phMark;
	         }
	         $("#shBtn"+photoXh).after("<p style='text-align:left;margin-left:10px;color:red' id='ptHang"+photoXh+"'><br>退办原因："+tbyy+phMark+"<a href='javascript:void(0);' style='margin-left:10px' onclick='changeTbyyByxh("+photoXh+")'>修改</a>&nbsp;&nbsp;<a href='javascript:void(0);' onclick='autoAccumulationYY("+photoXh+",null)'>取消</a><br></p>");
		     autoAccumulationYY(photoXh,true);//改变按钮文字
	         closeDiaLog();
         }
	   }     
    }
        
    //自动计算所有的退办原因，并累加在总的退办原因里
    //(a标签中的onclick取消退办原因，onchange修改资料类型的统一计算方法)
    function autoAccumulationYY(photoXh,flag){     
         //取消
         if(flag==null){
           $("#ptHang"+photoXh).remove();
           $("#photoYY"+photoXh).val("");
           $("#photoYYTxt"+photoXh).val("");
           $("#phRemark"+photoXh).val("");
	        //改按钮文字,在改变资料类型时，不变按钮文字
	        if($("#shBtn"+photoXh).val()=='合格'){
	            $("#shBtn"+photoXh).val("不合格");
	            $("#shBtn"+photoXh).after("<p id='ptHang"+photoXh+"' style='text-align:left;margin-left:10px;color:red'>&nbsp;&nbsp;</p>");
	        }else{
	            $("#shBtn"+photoXh).val("合格");
	        }
         }
        
         var allTbyy = '';
         $('[id^=zllx]').each(function(j,m){//查找每一个id以zllx开始的元素 资料类型
		     var zllxArray = new Array();
		     zllxArray = $(this).attr("id").split("zllx");
		     var poxh = zllxArray[1];  //图片序号
		     var itemYY = $("#photoYYTxt"+poxh).val();
		     var remark = $("#phRemark"+poxh).val();
		     if(itemYY!=''){  //有退办原因的,累加显示在总的退办原因中
		       var zllxTxt = $("#zllx"+poxh).find("option:selected").text();
		       allTbyy+=zllxTxt+":"+itemYY+","+remark+'\n';
		     }
	    });    
        $("#allTbyy").val(allTbyy);        
    }
        
     //根据当前的某一个退办原因再次修改
     function changeTbyyByxh(photoXh){
        openDialog(photoXh,true);
     	 var yyKey = $("#photoYY"+photoXh).val();  //获取某一个的退办原因value
         var keyArry = yyKey.split(",");       
	     for(var j =0;j<keyArry.length;j++){
	       $("input[name='sbztTbyy']").each(function(){ 
	         if($(this).val()==keyArry[j]){
	             $(this).attr("checked", "checked");  //选中
	             if(keyArry[j]=='A1005'){
	                $("#remark").val($("#phRemark"+photoXh).val());
	                $("#otherTr").show();
	             }
	         }
	       });
	    }	            
     }
     //验证表单信息
     function checkFormVidate(){         
       var noUploadPics = $("input[name='noUploadPic']");
       if(noUploadPics.length>0){
           alert("图片还未上传待补审图片,不能进行补审操作");
           return false;
       }else{
    	   closeDiaLog();
    	   var ywlx =$("#ywlx").val();
           if(ywlx=='--'){
              alert("请选择业务类型!");
              return false;
           }
           var dabh = document.getElementById("dabh");
	   		if(checknotnull(dabh,'请填写档案编号!') != "true"){
	   			return false;
	   		} 
	           var xm = document.getElementById("xm");
	   		if(checknotnull(xm,'请填写姓名!') != "true"){
	   			return false;
	   		}  
	           var zjcx = document.getElementById("zjcx");
	   		if(checknotnull(zjcx,'请填写准驾车型!') != "true"){
	   			return false;
	   		}
	   		var sfzmhm= document.getElementById("sfzmhm");
	   		if(checknotnull(sfzmhm,'请填写身份证明号码!')!="true"){
	   		   return false;
	   		}
	   		var sfzmmc=document.getElementById("sfzmmc");
	   		if(checknotnull(sfzmmc,'请填写身份证明名称!')!="true"){
	   		  return false;
	   		}
	   		/***var lxdh= document.getElementById("lxdh");
	   	    if(checknotnull(lxdh,'请填写联系电话!')!= "true") {
	   	        return false;
	   	    }else{
	   	       if(checkmobile(lxdh.value)!="true") {
	   	          lxdh.style.borderColor = '#FF0000';
	   	          lxdh.focus();
	   	         return false;
	   	       }
	   	    }
	   	    var lxdz=document.getElementById("lxdz");
	   	    if(checknotnull(lxdz,'请填写地址!')!="true"){
	   	      return false;
	   	    } ***/
	   	    //判断当前的资料类型是否有重复
	   	   var selArry  = new Array();
	          $('[id^=zllx]').each(function(j,m){
	             var obj =$(this);
	             var txt= obj.find("option:selected").text();//获取当前选中值的text
	             selArry.push(txt);                       
	          });
	          var nary = selArry.sort();
	          for(var i=0;i<selArry.length;i++){
	            if(nary[i]==nary[i+1]){
	               alert("资料：'"+nary[i]+"' 重复了");
	              return false;
	            }
	          }	    
		   	  var imgUrl ='<%=request.getContextPath()%>/artDialog4.1.6/skins/icons/loading.gif';
		   	  chuli= art.dialog({
		   	       content:'<img width="16" heigth="16" src="'+imgUrl+'"/>数据正在处理中，请稍后...',
		   	       title:'请等待',
		   	       lock:true,
		   	       opacity:0.37
		   	  });
	   	     $("#jsrdzform").submit(); 
	     }       
     }
     function closeChuli(){
        chuli.close();
     }
	</script>
  </head>
  <body>
	<center>
		<div id="admin_title">
			<div class="logo"><img src="<%=request.getContextPath()%>/images/frame_19.gif"></div>
			<div class="title">驾驶人电子影像档案信息</div>
		</div>
		<div id="admin_main" style="width: 98%;margin-top: 5px">
			<form action="<%=request.getContextPath()%>/jsrdzda/jsrdzda_jxBuCheck.action" method="post" id="jsrdzform" name="jsrdzform" target="abc">
				<iframe id="abc" name="abc" width="0" height="0" style="display: none;"></iframe>
				<input type="hidden" name="dzxxb.cjxh" id="cjxh" value="${dzxxb.cjxh }"/><!--每一宗的主表cjxh  -->
				<input type="hidden" name="dzxxb.cjzt" id="cjzt" value="${dzxxb.cjzt }"/><!--每一宗的主表cjzt  -->
				<table border="0" cellpadding="0" cellspacing="0" class="edittable"  style="width: 98%">
					<tr>
					    <td style="text-align:right;width: 80px;text-align: center;" rowspan="5" class="list0"><strong>基<br/>本<br/>信<br/>息</strong></td>
						<td style="text-align:right;width:240px" class="td0">业务类型：</td>
						<td class="trs" style="text-align:left;width:220px">
						   <input type="hidden" name="dzxxb.ywlx" id="ywlx" value="${dzxxb.ywlx }"/>
					      <input type="text" id="ywyy" value="${dzxxb.ywyy }"/>
						</td>
					    <td style="text-align:right;width:220px" class="td0">档案编号：</td>
						<td class="trs" style="text-align:left;">&nbsp;<input type="text" id="dabh" name="dzxxb.dabh" value="${dzxxb.dabh}"   maxlength="45"/></td>
					</tr>
					<tr>
						<td style="text-align:right;" class="td0">姓名：</td>
						<td class="trs" style="text-align:left;">&nbsp;<input type="text" id="xm" name="dzxxb.xm" value="${dzxxb.xm}"  maxlength="45"/></td>
					    <td style="text-align:right;" class="td0">性别：</td>
						<td class="trs" style="text-align:left;">&nbsp;<input type="radio" value="0" name="dzxxb.xb"  checked="<s:if test="#dzxxb.xb==0 ">checked</s:if>" />男 <input type="radio" value="0" name="dzxxb.xb" checked="<s:if test="#dzxxb.xb==1 ">checked</s:if>"/>女</td>
					</tr>
				    <tr>
						<td style="text-align:right;" class="td0">准驾车型：</td>
						<td class="trs" style="text-align:left;" colspan="3">&nbsp;
						<input type="text" name="dzxxb.zjcx" id="zjcx" value="${dzxxb.zjcx }"/></td>
					</tr>
					<tr>
						<td style="text-align:right;" class="td0">身份证明号码：</td>
						<td class="trs" style="text-align:left;">&nbsp;<input type="text" id="sfzmhm" value="${dzxxb.sfzmhm}" name="dzxxb.sfzmhm"  maxlength="45"/></td>
						<td style="text-align:right;" class="td0">身份证明名称：</td>
						<td class="trs" style="text-align:left;">&nbsp;<input type="text" id="sfzmmc" value="${dzxxb.sfzmmc}" name="dzxxb.sfzmmc"  maxlength="45"/></td>
					</tr>
					<tr>
						<td style="text-align:right;" class="td0">联系电话：</td>
						<td class="trs" style="text-align:left;">&nbsp;<input type="text" id="lxdh" value="${dzxxb.lxdh}" name="dzxxb.lxdh"  maxlength="11" onkeyup="value=value.replace(/\D/g,'')"/></td>
						<td style="text-align:right;" class="td0">联系地址：</td>
						<td class="trs" style="text-align:left;">&nbsp;<input type="text" id="lxdz" value="${dzxxb.lxdz}" name="dzxxb.lxdz"  style="width: 200px"/></td>
					</tr>
					<tr style="height: 10px;background-color:#aeeff4;" >
					 <td colspan="5"></td>
					</tr>
					<tr>
					    <td style="width: 80px;text-align: center;"  class="list0">
				              <strong>影<br/>像<br/>文<br/>件<br/>展<br/>示<br/>区</strong>
				        </td>
						<td colspan="4" style="width: 90%">
						   <s:if test="#request.photos.size > 0">				
						      <table style="width: 100%">
						         <tr>
						          <td style="400px">
							      <s:iterator id="pto" value="#request.photos" status="st">
						               <div style="width:410px;height:630px;border:1px solid <s:if test="#pto.zt=='JT' || #pto.zt=='CT' || #pto.zt==0 ">red</s:if><s:else>#666</s:else>;">
							              <s:if test="#pto.xh!=null && #pto.xh!=''">
							                    <s:if test="#pto.zt=='JT' || #pto.zt=='CT'">
							                         <img id="idImage${pto.xh}"  class="izImage"  style="height:500px;width:423px;" src="<%=request.getContextPath()%>/images/cp.gif" />
							                    </s:if>
							                    <s:else>
							                         <img id="idImage${pto.xh}"  class="izImage"  style="height:500px;width:423px;" src="http://100.100.21.61/cmp_new/view_pic.asp?efid=${pto.picStr}"/>
							                    </s:else>
							                    <div id="idViewer${pto.xh}" class="izViewer">
								                     <div style="height: 100%; border-bottom-width: 1px;"></div>
								                     <div style="width: 100%; border-right-width: 1px;"></div>
								                </div>
							                    <div style="height: 100px;width: 100%;overflow: hidden;">
								                 <s:if test="#pto.zt=='JT' || #pto.zt=='CT' || #pto.zt==0 ">
									                 <s:if test="#pto.zt=='CT' || #pto.zt=='JT'">
									                      <p style="color: red;font-weight: bold;margin-top: 3px">暂未上传待补审图片</p>
									                      <input type="hidden" name="noUploadPic" value="1"/>
									                 </s:if>
									                 <s:if test="#request.pto.historyId.size>0"><!--后3次审核次数 -->
									                    <p style="float:left;margin-left:10px;margin-top: 3px">历次审核退办最后3次：
									                        <s:iterator id="idObj" value="#request.pto.historyId" status="stc">						                        
									                           <input type="button" value="${stc.count}" onclick="checkImgUrl('<s:property value="idObj"/>','${pto.xh}','${pto.picStr}')" />
									                        </s:iterator>
									                        <s:if test="#pto.zt=='JT' || #pto.zt=='CT'">&nbsp;&nbsp;<input type="button" value="获取待审图片" onclick="checkImgUrl(null,'${pto.xh}','${pto.picStr}')" class="jsrBtn2"></s:if>
									                    </p>
									                 </s:if>
									                 <s:if test="#pto.tbyy!=null && #pto.tbyy!=''"><!--补审时，退办原因不为空则显示退办原因 -->
										                 <s:if test="#pto.zt==0">
										                     &nbsp;&nbsp;
									                         <input type="button"  class="jsrbtn" id="shBtn${pto.xh }" name="shBtn${pto.xh}"  value="合格" onclick="openDialog(${pto.xh})" />
									                          <p id="ptHang${pto.xh}" style="text-align:left;margin-left:10px;color:red"><br>上一次退办原因：${pto.tbyy}<a href="javascript:void(0);" style="margin-left:10px" onclick="changeTbyyByxh('${pto.xh}')">修改</a>&nbsp;&nbsp;<a href="javascript:void(0);" onclick="autoAccumulationYY('${pto.xh}',null)">取消</a><br></p>
										                 </s:if>
                                                         <!--待补审木有图片时，不显示退办原因 -->	
                                                          <s:else>
										                      <br>
										                      <p id="ptHang${pto.xh}" style="text-align:left;margin-left:10px;color:red;"><br>上一次退办原因：${pto.tbyy}<br></p>
										                 </s:else>                                                          	                        
								                     </s:if> 
								                 </s:if>
								                 <s:else>
								                     <p style="color: green;font-weight: bold;margin-top: 20px;text-align: center;">审核成功</p>
								                 </s:else>
							                  </div>						                  
							                  <div style="margin-bottom: 10px;">					                       
							                                                           资料类型：<s:select theme="simple" list="#request.ywlxAMap"  name="zllx%{#pto.xh}" id="zllx%{#pto.xh}" cssStyle="width:250px;" value="#request.pto.zllx"  onchange="autoAccumulationYY('#request.pto.xh',true)" ></s:select>
						                      </div>	
						                      <input type="hidden" name="photoYY${pto.xh }" id="photoYY${pto.xh }" value="${pto.cjmac}"/>
								              <input type="hidden" name="photoYYTxt${pto.xh }" id="photoYYTxt${pto.xh }" value="${pto.tbyy }"/>
								              <input type="hidden" name="phRemark${pto.xh }" id="phRemark${pto.xh }" value="${pto.remark }"/>
							                      							                   
									        </s:if>
								            <s:else>
								                  <img id="noImage"  src="<%=request.getContextPath()%>/images/cp.gif" style="height:500px;width:100%"/>
								             </s:else>						              
							        	    <s:if test="#st.count%2==0">
				                               </td><tr><td>
						           		    </s:if>
						           		    <s:else>
						           		       </td><td>
						           		   </s:else>
							         </s:iterator>	
		                           </tr>
		                        </table>			
				           </s:if>	
						  <s:else>						 
						      <div style='margin-top:100px;width:46%'><font color='red'>暂时没有图片显示</font></div>
						  </s:else>
					 </td>					
			  </tr>	
	  		  <tr style="height: 10px;background-color:#aeeff4;" >
				<td colspan="5"></td>
			  </tr>
	  		  <s:if test="#request.noMatchList.size>0">	  		  
			    <tr>
				    <td style="width: 80px;text-align: center;">
			              <strong>其<br/>它<br/>文<br/>件<br/>展<br/>示<br/>区</strong>
			        </td>
					<td colspan="4" >
					      <s:iterator id="otherPto" value="#request.noMatchList" status="st">
				               <div style="overflow:none;display: block;width:46%;height:520px;border:1px solid #666;float:left;margin-left:10px;margin-top:10px;">
				                 <s:if test="#pto.xh!=null && #pto.xh!=''">
				                      <img id="idImage${otherPto.xh }" class="izImage" src="http://100.100.21.61/cmp_new/view_pic.asp?efid=${otherPto.picStr}" style="width:423px;height:500px;display: block;"/>
					                  <div id="idViewer${otherPto.xh }" class="izViewer">
					                     <div style="height: 100%; border-bottom-width: 1px;"></div>
					                     <div style="width: 100%; border-right-width: 1px;"></div>
					                  </div>             
				                 </s:if>
				                 <s:else>
				                    <img id="noImage%{#st.count}" class="izImage" src="<%=request.getContextPath()%>/images/cp.gif" style="height:500px;width:423px;"/>
				                 </s:else>							                 
				             </div>
			           		   <s:if test="#st.count%2==0">
	                              <br>
			           		   </s:if>	
					     </s:iterator>
					</td>					
			     </tr>
			  </s:if>
		      <tr>
		          <td style="width: 80px;text-align: center;" rowspan="4" >
				          <strong>退办原因</strong>
				          <input type="hidden" name="isAllYY" id="isAllYY" value=""/><!--勾选全部不合格时，存放退办原因 key-->
				   </td>
				   <td style="text-align:left;" colspan="6">
				   <textarea style="margin-left: 20px;" cols="60" rows="5" name="allTbyy" id="allTbyy" readonly="readonly"><s:if test="#request.photos.size > 0"><s:iterator id="tbVo" value="#request.photos"><s:if test="#tbVo.cjmac!=null && #tbVo.cjmac!=''">${tbVo.zllxMc}:${tbVo.tbyy}.&#xd;</s:if></s:iterator></s:if></textarea></td>
		       </tr>		
			  <tr>
				 <td colspan="5" height="50" align="center">
					<s:if test="#request.editType != '查看'">
	    				<input type="button" onclick="javascript:checkFormVidate();" value="保存" style="margin-left: 170px;cursor:pointer;width: 79px;height: 28px;border: none;background: url(<%=request.getContextPath()%>/images/an2.gif) no-repeat;color: #FFFFFF;font-weight: bold;">
	    				<input type="button" onclick="javascript:window.history.go(-1);" value="返 回" style="cursor:pointer;width: 79px;height: 28px;border: none;background: url(<%=request.getContextPath()%>/images/an2.gif) no-repeat;color: #FFFFFF;font-weight: bold;">
	    			</s:if>
	    			<s:else>
	    				<input type="button" onclick="javascript:window.location.href='<%=request.getContextPath()%>/jsrdzda/jsrdzda_initJsrCjList.action'" value="返 回" style="margin-left: 200px;cursor:pointer;width: 79px;height: 28px;border: none;background: url(<%=request.getContextPath()%>/images/an2.gif) no-repeat;color: #FFFFFF;font-weight: bold;">
	    			</s:else>
				</td>
				</tr>
			</table>
			</form>
		</div>
	</center>
	
	<div id="shPhotoDiv" style="display:none;overflow-y:scroll;overflow-x:scroll;width:850px;height: 200px;">
	    <input type="hidden" name="photoCjxh" id="photoCjxh"/>
	    <input type="hidden" name="isUpdateYY" id="isUpdateYY" value=""/><!-- 修改原因标志时有值 -->
		<table  width="95%" border="0" cellpadding="0" cellspacing="0" class="datalist">
			<tr>
			    <td style="text-align: right;">退办原因:</td>
			    <td style="text-align: left;">
			      <div style="width:320px;">
			        <s:iterator value="#request.tpTbyyMap" id="tbyyVar" status="stVo">
				      <p style="width: 150px;float: left;">
				        <input type="checkbox" name="sbztTbyy" onclick="checkBoxItem(this)" value="${tbyyVar.dmz }" title="${tbyyVar.dmms1}"/>${tbyyVar.dmms1}
				      </p>
				      <s:if test="#request.stVo.count%2==0">
				        <br/>
				      </s:if>
				    </s:iterator>
			    </div>
			  </td>
			</tr>
			<tr id="otherTr" style="display: none;">
				<td style="text-align: right;width: 100px">备注:</td>
				<td style="text-align: left;"><textarea cols="30" rows="3" name="remark" id="remark" ></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="button" value="确定" onclick="checkTbFun()" class="bnt" style="cursor:pointer;"/>
					<input type="button" value="取消" onclick="closeDiaLog()" class="bnt" style="cursor:pointer;"/>
				</td>
			</tr>
		</table>
	</div>
  </body>
</html>
<script type="text/javascript">
     //图片放大
    $('[id^=idImage]').each(function(j,m){//查找每一个id以idImage开始的元素 资料类型
	     var idImageArray = new Array();
	     idImageArray = $(this).attr("id").split("idImage");
	     var cjxh = idImageArray[1];  //图片序号
	     var position= $("#idImage"+cjxh).offset();
	     $("#idViewer"+cjxh).css("left",position.left);
	     $("#idViewer"+cjxh).css("top",position.top);
	     var iz = new ImageZoom("idImage"+cjxh,"idViewer"+cjxh,{scale: 0,mouse:true ,rangeWidth:0,rangeHeight:0});	  
         iz.reset({originPic: $("#idImage"+cjxh).src, zoomPic: $("#idImage"+cjxh).src });	
    });	  	        
</script>