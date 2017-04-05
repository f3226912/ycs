<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<title>用户类型性质配置编辑</title>

<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/jquery-1.8.2.js"></script>
<LINK media=screen href="<%=request.getContextPath()%>/js/gjgggl/jQuery.files/jquery-ui-1.7.1.custom.css" type=text/css rel=stylesheet>
<LINK media=screen href="<%=request.getContextPath()%>/js/gjgggl/jQuery.files/jquery.gzoom.css" type=text/css rel=stylesheet>
<SCRIPT src="<%=request.getContextPath()%>/js/gjgggl/jQuery.files/ui.core.min.js" type=text/javascript></SCRIPT>
<SCRIPT src="<%=request.getContextPath()%>/js/gjgggl/jQuery.files/ui.slider.min.js" type=text/javascript></SCRIPT>
<SCRIPT src="<%=request.getContextPath()%>/js/gjgggl/jQuery.files/jquery.mousewheel.js" type=text/javascript></SCRIPT>
<SCRIPT src="<%=request.getContextPath()%>/js/gjgggl/jQuery.files/jquery.gzoom.js" type=text/javascript></SCRIPT>
<SCRIPT type=text/javascript>
     $(function() {
        $(".zoom_no_lbox").gzoom({
            sW: 470,
            sH: 250,
            lW: 1410,
            lH: 750,
            lightbox: false
        });
      });
</SCRIPT>
<style> 
	html{ 
		overflow:scroll;
		scrollbar-base-color:#c7e5ff;
		scrollbar-track-color:#FFFFFF;
	}
	
	
	.datalist {
		border: 1px solid #0058a3;
		font-family: Arial, Helvetica, sans-serif;
		border-collapse: collapse;
		/*background-color: #eaf5ff;*/
		font-size: 14px;
	}
	
	.datalist tr {
		height: 32px;
	}
	
	.datalist tr.altrow {
		/*background-color: #c7e5ff;*/
		height: 32px;
	}
	
	.datalist td {
		border: 1px solid #3c7eba;
		text-align: center;
		padding-top: 2px;
		padding-bottom: 2px;
		padding-left: 5px;
		overflow: hidden;
		font-size: 12px;
	}
	
	.datalist th {
		border: 1px solid #3c7eba;
		height: 32px;
	}
	
	.tds1 {
		background: #eaf5ff;
		font-weight: bold;
		font-size: 14px;
	}
	
	.tds2 {
		font-weight: bold;
		font-size: 14px;
	}
	
	.bnt {
		width: 76px;
		height: 27px;
		background: url(<%=request.getContextPath()%>/images/an2.gif) no-repeat;
		border: none;
		font-weight: bold;
	}
	
	.error{
		width:160px;
		color: #941305;
		font-size: 12px;
	}
	.span{ width: 100px; border: 1px solid #eaf5ff; }
	ul{ margin-left: -20px;}
	ul li{ border:0px solid red; float:left; width:210px; height: 20px;  list-style-type: none; text-align: left; }
</style>


<script type="text/javascript">   
	$(document).ready(function(){
		var Sfqx = ${request.Sfqx};
		if(Sfqx ==1){
			$('[name=ids1]:checkbox').attr('checked',true);
			$('[name=ids2]:checkbox').attr('checked',true);
		}
		
		//判断各分类是否都选中（全选则选中对应的全选框）
		var qxCount=$('[name=qx]').length;
		var qxCheckedCount = $('[name=qx]:checkbox:checked').length;
		if(qxCount==qxCheckedCount){
			$("#Sfqx").val("1");
		}
	});

	$(function(){
		$(".dis").attr("disabled","disabled");
		
		//是否全选
		$("#Sfqx").change(function(){
			var id = $("#Sfqx").val();
			if(id==1){//全选
				$('[name=ids1]:checkbox').attr('checked',true);
				$('[name=ids2]:checkbox').attr('checked',true);
				$('[name=qx]:checkbox').attr('checked',true);
			}else{
				$('[name=ids1]:checkbox').attr('checked',false);
				$('[name=ids2]:checkbox').attr('checked',false);
				$('[name=qx]:checkbox').attr('checked',false);
				
				//更改全选下拉框值
				$("#Sfqx option[text='否']").attr('selected',true);
			}
		});
		
		//返回
		$("#cancelButton").click(function(){
			window.close();
		});	
	});
	
	//全部全选
	function qxClick(bs){
		if($("#"+bs).is(":checked")){
			$("."+bs).attr('checked',true);
			var qxCount=$('[name=qx]').length;
			var qxCheckedCount = $('[name=qx]:checkbox:checked').length;
			if(qxCount==qxCheckedCount){
				$("#Sfqx").val("1");
			}
		}else{
			$("."+bs).attr('checked',false);
			$("#Sfqx").val("0");
		}
	}
	
	//提交修改
	function update(id){
		var zt = $("#zt").val();
		var Sfqx = $("#Sfqx").val();
		var SyxzCount = $('[name=ids1]:checkbox:checked').length;
		var Syxz ="";	
		$('[name=ids1]:checkbox:checked').each(function(i){
			  Syxz += $(this).val();
			  if(i+1<SyxzCount){
			  	Syxz +=",";
			  }			  
		});
		var SyxzCount = $('[name=ids2]:checkbox:checked').length;
		var Cllx="";
		$('[name=ids2]:checkbox:checked').each(function(i){
			  Cllx += $(this).val();
			  if(i+1<SyxzCount){
			  	Cllx +=",";
			  }
		});
		$.ajax({
			cache:false,
			async:false,
			type:"POST",
			url:"<%=request.getContextPath()%>/yanche/yczd_updateYhlxxz.action",
			data:{loginId:id,zt:zt,Sfqx:Sfqx,Syxz:Syxz,Cllx:Cllx},
			dataType: 'text',
			success:function(data){
				if(data==0){
					alert("修改成功!");
					window.close();
					window.returnValue="success";
				}else{
					alert("系统繁忙，修改失败,请稍后在试!");
					return;
				}
			}
		});
	}
				
</script>
</head>
	<body>
		  <div style="width:100%;" >
		  <form action="<%=request.getContextPath()%>/yanche/yczd_updateSubmit.action" id="sub_form" method="post">
		    <table class="datalist" width="100%" border="0" cellpadding="0" cellspacing="0">
				  <tr>
				    <td class="tds1" style="text-align: right;" width="80px">用户名：</td>
				    <td class="tds2" style="text-align: left;width:300px;">
				    	<input type="text" id="dmz" name="dmz" value="${UserObj[1]}" disabled="disabled"/>
				    </td>
				    <td class="tds1" style="text-align: right;" width="80px">状态：</td>
				    <td class="tds2" style="text-align: left;width:240px;">
				    	<s:if test='#session.userbean.name=="jst"'>
				  		<s:select theme="simple"  id="zt" name="zt" list="#{'1':'正常','0':'隐藏'}" value="#request.yanche.zt"></s:select>
				  		</s:if>
				  		<s:else>
				  		<s:select theme="simple" class="dis" id="zt" name="zt" list="#{'1':'正常','0':'隐藏'}" value="#request.yanche.zt"></s:select>
				  		</s:else>
				    </td>
				  </tr>
				  <tr>
				  	<td class="tds1" style="text-align: right;">所属部门：</td>
				  	<td class="tds2" style="text-align: left;" width="80px;">
				  		<input type="text" id="dmsm" value="${UserObj[3]}" disabled="disabled">
				  	</td>
				  	<td class="tds1" style="text-align: right;" width="80px">职位：</td>
				    <td class="tds2" style="text-align: left;width:240px;">
						<input type="text" id="dmsm" value="${UserObj[2]}" disabled="disabled">
				    </td>
				  </tr>
				  <tr>
				  	<td class="tds1" style="text-align: right;" width="80px">是否全选：</td>
				    <td class="tds2" colspan="3" style="text-align: left;width:240px;">
						<s:select theme="simple" id="Sfqx" name="Sfqx" list="#{'0':'否','1':'是'}" value="#request.UserObj[6]"></s:select>
				    </td>
				  </tr>
				  <tr>
				  	<td class="tds1" style="text-align: right;" width="80px">使用性质：</td>
				    <td class="tds2" colspan="3" style="text-align: left;">
				    	<table style="margin-left: -10px;">
				    		<td class="tds1" style="text-align: center" width="80px">
				    			全选<input type="checkbox" name="qx" id="syxz" onclick="qxClick('syxz')">
				    		</td>
				    		<td class="tds2" colspan="3" style="text-align: left;">
				    			<ul>
				    			<s:iterator id="obj" value="#request.syxzList">
				    				<li>
				    				<input type="checkbox" name="ids1" class="syxz"
				    						<s:iterator id="cx" value="#request.Syxz">
					    						<s:if test="#obj[0]==#cx">
					    							checked="checked" 
					    						</s:if>
				    						</s:iterator>
				    						value="<s:property value="#obj[0]"/>"/><s:property value="#obj[1]"/>
				    				</li>
								</s:iterator>
								</ul>
				    		</td>
				    	</table>
				    	
				    </td>
				  </tr>
				  <tr>
				  	<td class="tds1" style="text-align: right;" width="80px">车辆类型：</td>
				    <td class="tds2" colspan="3" style="text-align: left;">
				    	<table style="margin-left: -10px;">
				    		<s:iterator id="bs" value="#request.cllxBsList">
				    		<tr>
				    			<td class="tds1" style="text-align: center;" width="80px">
				    				<span style=" color: red; font-size: 16px;">${bs}</span>&nbsp开头</br>
				    				全选<input type="checkbox" name="qx" id="${bs}" value="${bs}" onclick="qxClick('${bs}')">
				    			</td>
				    			<td class="tds2" colspan="3" style="text-align: left;">
				    				<ul>
					    			<s:iterator id="pvp" value="#request.cllxMap.get(#bs)">
					    				<li>
					    				<input type="checkbox" name="ids2" class="${bs}"
					    						<s:iterator id="lx" value="#request.Cllx">
						    						<s:if test="#pvp[0]==#lx">
						    							checked="checked" 
						    						</s:if>
					    						</s:iterator>
					    						value="<s:property value="#pvp[0]"/>"/><s:property value="#pvp[0]"/>&nbsp&nbsp<s:property value="#pvp[1]"/>
					    				</li>
									</s:iterator>
									</ul>
				    			</td>
				    		</tr>
				    		</s:iterator>
				    	</table>
				    </td>
				  </tr>
				  <tr>
				  	<td colspan="4">
				  	<input type="button" id="click" class="bnt" style="width: 80px;" onclick="update('${UserObj[0]}')"  value="确定" />
				  	<input type="button" id="cancelButton" class="bnt" style="width: 80px;"  value="返回" /></td>
				  </tr>
			</table>
		</form>
		   </div>

</body>
</html>