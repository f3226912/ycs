<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title>车管档案信息复核界面</title>
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
	<script type="text/javascript">
	var chuli;
	$(document).ready(function() {	
		   $("#isAllOk").click(function(){
	  	         var checkZt= $("#isAllOk").attr("checked");
	  	         if(checkZt == undefined){   	         
	  	             $("#allTbyy").val("");
	  	             $("#isAllYY").val("");
	  	             $("#isAllRemark").val("");
	  	         }
	  	    });
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
        }else{//表示勾选全部不合格时，显示div
                openDlog = art.dialog({
 				 width:'45%',
 			    content: $('#shPhotoDiv').html(),
 			    title: '图片审核结果',
 				lock: true,
 			    opacity: 0.1			
 	         });
      	      $(".aui_content input[name='sbztTbyy']").each(function(){
  	             $(this).attr("checked",false);
  	          });
        }
      }
    
    //关闭对话框
    function closeDiaLog(){
    	if(openDlog!=undefined){
            openDlog.close();
        }   
    }
    //判断选择的退办原因后操作其他的方式
    function checkTbFun(){
        var itemNum= 0;
        var itemsTxt="";
        var itemsVal="";
        var flag=false;
        var photoXh = $("#photoCjxh").val();
        $("#photoYY"+photoXh).val("");
        $("#photoYYTxt"+photoXh).val("");
        $("#phRemark"+photoXh).val("");
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
	    	var tbyyTxt = itemsTxt.substring(0,itemsTxt.length-1);//每一个的退办原因text 
	    	var tbyy = itemsVal.substring(0,itemsVal.length-1);   //每一个的退办原因value
            if(photoXh!=''){
               $("#isAllOk").attr("checked",false);//防止都勾选后，又选择其他的退办原因；然后取消掉勾选
               $("#isAllYY").val("");  //总体退办原因为空
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
		         $("#photoYY"+photoXh).val(tbyy);      
		         $("#photoYYTxt"+photoXh).val(tbyyTxt);      
		         var phMark =$("#phRemark"+photoXh).val();
		         if(phMark!=''){
		             phMark=":"+phMark;
		         }
		         $("#divMove"+photoXh).html("<p style='float:left;margin-left:10px;color:red' id='ptHang"+photoXh+"'>退办原因："+tbyyTxt+phMark+"<a href='javascript:void(0);' style='margin-left:10px' onclick='changeTbyyByxh("+photoXh+")'>修改</a>&nbsp;&nbsp;<a href='javascript:void(0);' onclick='autoAccumulationYY("+photoXh+",null)'>取消</a><br></p>");
		         autoAccumulationYY(photoXh,true);//改变按钮文字
		         closeDiaLog();
           }else{//勾选所有不合格的原因
        	    var remark =$("#remark").val(); 
        	    if(remark!=''){
            	    $("#isAllRemark").val(remark);
        	    	remark=":"+remark;
		         }       
                $("#isAllYY").val(tbyy);
                $("#allTbyy").val(tbyyTxt+remark);
                closeDiaLog();
                checkFormVidate();
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
    
    //根据当前的某一个退办原因，再次修改
    function changeTbyyByxh(photoXh) {
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
     
     //验证表单信息，并提交
     function checkFormVidate(){
       var isAllOk = $("#isAllOk").attr("checked");
       if(isAllOk!="checked"){
	       	var noMacthLxs  =$('[id^=noMacthLx]');
		    if(noMacthLxs.length>0){
			   //判断当前的资料类型是否有重复
			    var content ="现有资料类型：";
			    var selArry  = new Array();
		        $('[id^=noMacthLx]').each(function(j,m){
		            var obj =$(this);
		            var txt= obj.find("option:selected").text();//获取当前选中值的text
	                   if(txt!=""){}{
	                  	 content += "\n\n“"+txt+"”\n";   
	                   }			               
		       });
		       alert(content+"未上传,请重新采集!");
		       return false;
		    }
       }         
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
	    var isAllOk = $("#isAllOk").attr("checked");
	    var allValue= $("#isAllYY").val();//勾选全部不合格时，存放的退办原因
	    if(isAllOk && allValue==''){
	    	 $("#photoCjxh").val("");//要去掉之前的cjxh
	    	 openDialog('');
	    }else{
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
     
     //进入打印一维码
     function checkPrint(yzbm){ 
	    var uri='<%=request.getContextPath()%>'+"/jsrdzda/jsrdzda_initPrint.action?yzbm="+yzbm;  
             window.open(uri ,"info",'width='+(window.screen.availWidth-10)+',height='+(window.screen.availHeight-30)+ ',top=0,left=0,toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=yes'); 
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
			<form action="<%=request.getContextPath()%>/jsrdzda/jsrdzda_jxFirstCheck.action?type=cg" method="post" id="jsrdzform" name="jsrdzform" target="abc">
				<iframe id="abc" name="abc" width="0" height="0" style="display: none;"></iframe>
				<input type="hidden" name="dzxxb.cjxh" id="cjxh" value="${dzxxb.cjxh }"/><!--每一宗的主表cjxh  -->
				<table border="0" cellpadding="0" cellspacing="0" class="edittable"  style="width: 98%">
					<tr>
					    <td style="text-align:right;width: 80px;text-align: center;" rowspan="5"><strong>基<br/>本<br/>信<br/>息</strong></td>
						<td style="text-align:right;width:220px">业务类型：</td>
						<td class="trs" style="text-align:left;width:240px">
						  <input type="hidden" name="dzxxb.ywlx" id="ywlx" value="${dzxxb.ywlx }"/>
					      <input type="text" id="ywyy" value="${dzxxb.ywyy }"/>
						</td>
					    <td style="text-align:right;width:220px">档案编号：</td>
						<td class="trs" style="text-align:left;">&nbsp;<input type="text" id="dabh" name="dzxxb.dabh" value="${dzxxb.dabh}"   maxlength="45"/></td>
					</tr>
					<tr>
						<td style="text-align:right;">姓名：</td>
						<td class="trs" style="text-align:left;">&nbsp;<input type="text" id="xm" name="dzxxb.xm" value="${dzxxb.xm}"  maxlength="45"/></td>
					    <td style="text-align:right;">性别：</td>
						<td class="trs" style="text-align:left;">&nbsp;
						           <input type="radio" value="0" name="dzxxb.xb" <s:if test="dzxxb.xb ==0">checked="checked"</s:if>/>男 
						           <input type="radio" value="0" name="dzxxb.xb" <s:if test="dzxxb.xb ==1">checked="checked"</s:if>/>女
						</td>
					</tr>
				    <tr>
						<td style="text-align:right;">准驾车型：</td>
						<td class="trs" style="text-align:left;" colspan="3">&nbsp;
						<input type="text" name="dzxxb.zjcx" id="zjcx" value="${dzxxb.zjcx }"/></td>
					</tr>
					<tr>
						<td style="text-align:right;">身份证明号码：</td>
						<td class="trs" style="text-align:left;">&nbsp;<input type="text" id="sfzmhm" value="${dzxxb.sfzmhm}" name="dzxxb.sfzmhm"  maxlength="45"/></td>
						<td style="text-align:right;">身份证明名称：</td>
						<td class="trs" style="text-align:left;">&nbsp;<input type="text" id="sfzmmc" value="${dzxxb.sfzmmc}" name="dzxxb.sfzmmc"  maxlength="45"/></td>
					</tr>
					<tr>
						<td style="text-align:right;">联系电话：</td>
						<td class="trs" style="text-align:left;">&nbsp;<input type="text" id="lxdh" value="${dzxxb.lxdh}" name="dzxxb.lxdh"  maxlength="11" onkeyup="value=value.replace(/\D/g,'')"/></td>
						<td style="text-align:right;">联系地址：</td>
						<td class="trs" style="text-align:left;">&nbsp;<input type="text" id="lxdz" value="${dzxxb.lxdz}" name="dzxxb.lxdz"  style="width: 200px"/></td>
					</tr>
					<tr style="height: 10px;background-color:#aeeff4;" >
					 <td colspan="5"></td>
					</tr>
					<tr>
					    <td style="width: 80px;text-align: center;">
				            <strong>影<br/>像<br/>文<br/>件<br/>展<br/>示<br/>区</strong>
				        </td>
						<td colspan="4">
					    <s:if test="#request.photos.size > 0">						 
						 <table style="width: 100%">
						  <tr>
						    <td style="400px">
							      <s:iterator id="pto" value="#request.photos" status="st">
						               <div style="overflow:hidden;width:410px;height:600px;border:1px solid #666t;margin-left:10px;margin-top:10px;">
						                   <s:if test="#pto.xh!=null && #pto.xh!=''">
								                  <img id="idImage${pto.xh}"  class="izImage" src="http://100.100.21.61/cmp_new/view_pic.asp?efid=${pto.picStr}" style="height:500px;width:410px;"/>
								                  <div id="idViewer${pto.xh}" class="izViewer">
								                     <div style="height: 100%; border-bottom-width: 1px;"></div>
								                     <div style="width: 100%; border-right-width: 1px;"></div>
								                  </div>		
							                      <input type="button" class="jsrbtn" id="shBtn${pto.xh }" name="shBtn${pto.xh}" <s:if test="#pto.zt==1 || #pto.zt=='B'">value="不合格"</s:if><s:elseif test="#pto.zt=='CT'">value="合格"</s:elseif> onclick="openDialog(${pto.xh })"/>
							                      <div id="divMove${pto.xh }" style="height: 45px">
										              <s:if test="#pto.tbyy!=null && #pto.tbyy!=''"><!--补审时，退办原因不为空则显示退办原因 -->
							                              <p id="ptHang${pto.xh}" style="float:left;margin-left:10px;color:red">退办原因：${pto.tbyy}<a href="javascript:void(0);" style="margin-left:10px" onclick="changeTbyyByxh('${pto.xh}')">修改</a>&nbsp;&nbsp;<a href="javascript:void(0);" onclick="autoAccumulationYY('${pto.xh}',null)">取消</a><br></p>
							                          </s:if> 
										          </div>
								                  <div style="margin-bottom: 10px;">					                       
										                 ${pto.zllx } 资料类型：<s:select theme="simple" list="#request.ywlxAMap"  name="zllx%{#pto.xh}" id="zllx%{#pto.xh}" cssStyle="width:250px;" value="#request.pto.zllx"  onchange="autoAccumulationYY('#request.pto.xh',true)" ></s:select>
									              </div>
									               <input type="hidden" name="photoYY${pto.xh }" id="photoYY${pto.xh }" value="${pto.cjmac}"/>
									               <input type="hidden" name="photoYYTxt${pto.xh }" id="photoYYTxt${pto.xh }" value="${pto.tbyy }"/>
									               <input type="hidden" name="phRemark${pto.xh }" id="phRemark${pto.xh }" value="${pto.remark }"/>
							               </s:if>							               
							                <s:else><!-- 资料不全的类型 -->
							                    <img id="noImage%{#st.count}" class="izImage" src="<%=request.getContextPath()%>/images/cp.gif" style="width:400px;height:500px;display: block;"/>
							                    <div id="idViewer${pto.xh }" class="izViewer">
								                     <div style="height: 100%; border-bottom-width: 1px;"></div>
								                     <div style="width: 100%; border-right-width: 1px;"></div>
								                  </div>
							                    <div style="margin-top: 75px;margin-bottom: 10px;text-align: center;">					                       
										                                   资料类型：<s:select theme="simple" id ="noMacthLx%{#st.count}" name="noMacthLx%{#st.count}"  list="#request.ywlxAMap" cssStyle="width:250px;" value="#request.pto.cjr" ></s:select>
									            </div>	
								            </s:else>
						             </div>
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
			       <tr style="height: 10px;background-color:#aeeff4;" >
					    <td colspan="5"></td>
				   </tr>
			    </s:if>
			        <tr>
			           <td style="width: 80px;text-align: center;" >
					       <strong>是否合格</strong>
					   </td>
					   <td style="text-align:left;" colspan="6">
					     <input type="checkbox" name="isAllOk" id="isAllOk" value="allTrue"  style="margin-left: 20px;"/> 全部不合格
					   </td>
			        </tr>	
			         <tr>
			           <td style="width: 80px;text-align: center;" rowspan="4" >
					          <strong>退办原因</strong>
					          <input type="hidden" name="isAllYY" id="isAllYY" value=""/><!-- 勾选全部不合格时，存放退办原因 -->
					          <input type="hidden" name="isAllRemark" id="isAllRemark"/><!-- 勾选全部不合格时,有其他原因时，存放备注信息 -->
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
			    				<input type="button" onclick="javascript:window.location.href='<%=request.getContextPath()%>/jsrdzda/jsrdzda_initCgCheckJsrList.action'" value="返 回" style="margin-left: 200px;cursor:pointer;width: 79px;height: 28px;border: none;background: url(<%=request.getContextPath()%>/images/an2.gif) no-repeat;color: #FFFFFF;font-weight: bold;">
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