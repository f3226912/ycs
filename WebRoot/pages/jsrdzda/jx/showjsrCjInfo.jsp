<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
  <head>
    <title>驾驶人信息采集审核界面</title>
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
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.PrintArea.js"></script>
    <script	src="<%=request.getContextPath()%>/imageZoom/CJL.0.1.min.js"	type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/imageZoom/test.js" type="text/javascript"></script>

   <style type="text/css">
    .izImage,.izViewer {background: #fff;}		
	.izImage {width: 580px;	height: 365px;cursor: pointer;}		
	.izViewer {width: 423px;height: 500px;position: absolute;left:590px;display: none;}			
	.izViewer div {position: absolute;border: 0 dashed #999;top: 0;	left: 0;z-index: 999;width: 100%;height:100%;}		
   </style>
	<script type="text/javascript">
	$(document).ready(function() {		
	    $("input,select").not(".bnt").each(function(){
				$(this).attr("disabled", "disabled");
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
	 //打印图
	 function printImage(picStr){
		 var uri='<%=request.getContextPath()%>' +"/pages/jsrdzda/dzdaInfo/showImage.jsp?picStr="+picStr;
		 window.showModalDialog(uri,picStr,"dialogWidth:800px;dialogHeight:960px;help:no;status:no;scroll:no;location:no;resizable:yes");
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
			<form action="<%=request.getContextPath()%>/user/user_jxFirstCheck.action" method="post" id="jsrdzform" name="jsrdzform" target="abc">
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
						<td class="trs" style="text-align:left;" colspan="3">&nbsp;<input type="text" name="dzxxb.zjcx" id="zjcx" value="${dzxxb.zjcx }"/></td>
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
						              <div style="overflow:none;display: block;width:400px;height:590px;border:1px solid <s:if test="#pto.xh!=null && #pto.xh!=''">#666</s:if><s:else>red</s:else>;float:left;margin-left:10px;margin-top:10px;">
						                 <s:if test="#pto.xh!=null && #pto.xh!=''">
						                      <img id="idImage${pto.xh }" class="izImage" src="http://100.100.21.61/cmp_new/view_pic.asp?efid=${pto.picStr}" style="width:400px;height:500px;display: block;"/>
							                  <div id="idViewer${pto.xh }" class="izViewer">
							                     <div style="height: 100%; border-bottom-width: 1px;"></div>
							                     <div style="width: 100%; border-right-width: 1px;"></div>
							                  </div>					                  			
	                                          <s:if test="#pto.tbyy!=null && #pto.tbyy!=''"><!--补审时，退办原因不为空则显示退办原因 -->
					                              <div id="divMove${pto.xh }" style="height: 45px;width100%;text-align: left;">
					                              <p style="float:left;margin-left:10px;color:red" id="ptHang${pto.xh}">退办原因：${pto.tbyy}
						                             <s:if test="#request.editType!='show'">
						                               <a href="javascript:void(0);" style="margin-left:10px" onclick="changeTbyyByxh('${pto.xh}')">修改</a>&nbsp;&nbsp;
						                               <a href="javascript:void(0);" onclick="autoAccumulationYY('${pto.xh}',null)">取消</a><br>
						                             </s:if>
					                             </p>
									              </div>
					                          </s:if> 
						                    <input type="hidden" name="photoYY${pto.xh }" id="photoYY${pto.xh }" value="${pto.cjmac}"/>
							                <input type="hidden" name="photoYYTxt${pto.xh }" id="photoYYTxt${pto.xh }" value="${pto.tbyy }"/>
						                 <div style="margin-bottom: 10px;">	
						                                                       资料类型：<s:select theme="simple" list="#request.ywlxAMap"  name="zllx%{#pto.xh}" id="zllx%{#pto.xh}" cssStyle="width:250px;" value="#request.pto.zllx"  onchange="autoAccumulationYY('#request.pto.xh',true)" ></s:select>
						                  </div>  
						                   <s:if test="#request.temp!=null && #request.temp=='shShow'">
				                              <input type="button" id="printBtn${pto.xh}"  value="打印" class="bnt" onclick="printImage('${pto.picStr}')"/>
				                          </s:if>              
						                 </s:if>
						                 <s:else>
						                    <img id="noImage%{#st.count}" class="izImage" src="<%=request.getContextPath()%>/images/cp.gif" style="width:400px;height:500px;display: block;"/>
						                    <div id="idViewer${pto.xh }" class="izViewer">
							                     <div style="height: 100%; border-bottom-width: 1px;"></div>
							                     <div style="width: 100%; border-right-width: 1px;"></div>
							                </div>
						                   <center>
						                    <div style="margin-top: 75px;margin-bottom: 10px;">					                       
									                                   资料类型：<s:select theme="simple" list="#request.ywlxAMap" cssStyle="width:250px;" value="#request.pto.cjr" ></s:select>
								            </div>	</center>
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
						    <div style='margin-top:100px;width:46%'><font color='red'>暂时没有图片显示</font><input type="hidden" id="noPic" name="noPic" value="0"/>  </div>
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
				               <div  style="overflow:none;display: block;width:46%;height:520px;border:1px solid #666;float:left;margin-left:10px;margin-top:10px;">
				                 <s:if test="#pto.xh!=null && #pto.xh!=''">
				                     <div id="printScope${otherPto.xh }">
				                        <img id="idImage${otherPto.xh }" class="izImage" src="http://100.100.21.61/cmp_new/view_pic.asp?efid=${otherPto.picStr}" style="width:423px;height:500px;display: block;"/>
				                     </div>
				                      
					                  <div id="idViewer${otherPto.xh }" class="izViewer">
					                     <div style="height: 100%; border-bottom-width: 1px;"></div>
					                     <div style="width: 100%; border-right-width: 1px;"></div>
					                  </div>             
				                 </s:if>
				                 <s:else>
				                    <img id="noImage%{#st.count}" class="izImage" src="<%=request.getContextPath()%>/images/cp.gif" style="height:500px;width:423px;"/>
				                 </s:else>	
				                 <s:if test="#request.temp!=null && #request.temp=='shShow' && #pto.xh!=null && #pto.xh!=''">
				                     <input type="button" id='printBtn${otherPto.xh }'  value="打印" class="bnt" onclick="printImage('${otherPto.picStr}')"/>
				                 </s:if>
				                 						                 
				             </div>
			           		   <s:if test="#st.count%2==0">
	                              <br>
			           		   </s:if>	
					     </s:iterator>
					</td>					
			     </tr>
			  </s:if>
			        <tr> 
			           <td style="width: 80px;text-align: center;" >
					       <strong>是否合格</strong>
					   </td>
					   <td style="text-align:left;" colspan="6"><input type="checkbox" name="isAllOk" id="isAllOk" value="allTrue"  style="margin-left: 20px;"/> 全部不合格</td>
			        </tr>	
			         <tr>
			           <td style="width: 80px;text-align: center;" rowspan="4" >
					          <strong>退办原因</strong>
					          <input type="hidden" name="isAllYY" id="isAllYY" value=""/><!-- 勾选全部不合格时，存放退办原因 -->
					   </td>
					   <td style="text-align:left;" colspan="6">
					   <textarea style="margin-left: 20px;" cols="60" rows="5" name="allTbyy" id="allTbyy"><s:if test="#request.photos.size > 0"><s:iterator id="tbVo" value="#request.photos"><s:if test="#tbVo.cjmac!=null && #tbVo.cjmac!=''">${tbVo.zllxMc}:${tbVo.tbyy}.&#xd;</s:if></s:iterator></s:if></textarea></td>
			        </tr>		
					<tr>
						<td colspan="5" height="50" align="center">
						<s:if test="#request.temp!=null && #request.temp!=''">
						    <input type="button" onclick="javascript:window.location.href='<%=request.getContextPath()%>/jsrdzda/jsrdzda_addCurrentLockList.action?btnType=back'" value="返 回" class="bnt">
						</s:if>
						<s:else>
						   <input type="button" onclick="javascript:window.location.href='<%=request.getContextPath()%>/jsrdzda/jsrdzda_initQueryInfo.action?temp='" value="返 回" class="bnt">
						</s:else>			    			
						</td>
					</tr>
				</table>
			</form>
		</div>
	</center>
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