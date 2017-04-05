<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>警员指纹采集</title>
	<style type="text/css">
			html{
			  scrollbar-face-color:#7ED1FE;
			  scrollbar-highlight-color:#BCBCBC;
			  scrollbar-3dlight-color:BCBCBC;
			  scrollbar-darkshadow-color:BCBCBC;
			  scrollbar-arrow-color:#000000;
			  scrollbar-track-color:#E7E7E7;
			}
			body{margin-left: 0px;margin-top: 0px;margin-right: 0px;margin-bottom: 0px;}
			.td_class{font-size:12px; color:#000000; line-height:20px;}
			html,body {
		       
		    }
    </style>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/myCss.css"/>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/jquery/js/ztreecss/zTreeStyle/zTreeStyle.css" type="text/css"/>
	<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/jquery-1.8.2.js"></script>
	<script type="text/javascript">
		function checkBaocun(){
			var finger1=$('#fingerOne').val();
			$('#finger1').val(finger1);

			var finger2=$('#fingerTwo').val();
			$('#finger2').val(finger2);

			var finger3=$('#fingerThree').val();
			$('#finger3').val(finger3);
			
			var finger4=$('#fingerFour').val();
			$('#finger4').val(finger4);
			
			if(finger1=="" && finger2==""){
				alert("还未进行指纹采集！");
				return false;
			}else{
				document.getElementById("user_form").submit();
			}
			
		}
		//1标示为第一次采集指纹
		var flag=1;
		//循环定时器标示
		var k;
        var finger400="";
        var finger512="";
        var ss=setInterval("AFIS1.DisFingers();",1000);
		//窗口加载时加载
        function check(){
					//硬件初始化的判断
					var v=AFIS1.Open;
					if(v==0){
						alert("不能发现指纹采集仪");
						return false;
					}else if(v==2){
						alert("不能发现软件狗");
						return false;
					}else if(v==3){
						alert("打开BM文件失败");
						return false;
					}else if(v==4){
						alert("比对程序初始化失败");
						return false;
					}else if(v==5){
						alert("初始化出现异常错误");
						return false;
					}
        }
		
		//清空数据
        function btn_ClearFace(){
				//清空数据
          		 AFIS1.ClearFace;
        }
		//传入四个参数
		function runFlag512(fingerOne,fingerThree,fingerTwo,fingerFour){
		  //打开硬件
		  var v=AFIS1.Open;
            if(v==0){
                alert("不能发现指纹采集仪");
                return false;
			}else if(v==2){
                alert("不能发现软件狗");
                return false;
            }else if(v==3){
                alert("打开BM文件失败");
                return false;
            }else if(v==4){
                alert("比对程序初始化失败");
                return false;
            }else if(v==5){
                alert("初始化出现异常错误");
                return false;
            }
            //设置参数初始化，然后调用方法
		   AFIS1.SETRVflags(4,3000);
		  //返回指示数
          var succ=AFIS1.Success;
		  //指纹采集失败时
          if(succ==1){
            finger400=AFIS1.FingerCodes;
            finger512=AFIS1.FingerCodes512;
			return false;
		  //指纹采集成功
          } else if(succ==2){
		  		//首次加载页面是第一次flag=1
		  		if(flag==1){
					finger512=AFIS1.FingerCodes512;
					finger400=AFIS1.FingerCodes;
					fingerOne.value=finger512;
					fingerThree.value=finger400;
					
					if(fingerOne.value.length!=1536 || fingerThree.value.length!=1200){
					  alert("指纹编码长度不正确，采集失败，请重新进行采集");
					  return false;
					}
				//第二次flag=2
				}else if(flag==2){
					finger512=AFIS1.FingerCodes512;
					finger400=AFIS1.FingerCodes;
					fingerTwo.value=finger512;
					fingerFour.value=finger400;	
					if(fingerTwo.value.length!=1536 || fingerFour.value.length!=1200){
					  alert("指纹编码长度不正确，采集失败，请重新进行采集");
					  return false;
					}
				}
		//指纹采集失败
		}else if(succ==0){
              alert("采集失败，请重新进行采集.....");
			  window.location.reload();
			  return false;
			  
        }
   }
   		//第一次采集显示与隐藏
        function divFinger1_show(){
			 document.getElementById("div1_1").style.display="none";//隐藏
			 document.getElementById("div1_2").style.display="block";//显示
			 document.getElementById("div_cj").style.display="none";//隐藏
			 document.getElementById("div2_1").style.display="none";//显示

		 
        }
        function setFinger400(){
<%--            if(finger400==""){--%>
<%--                ///alert("还没有400的数据，请点设置512");--%>
<%--                return false;--%>
<%--            }--%>
<%--            AFIS1.SETFINGERCODES(finger400,3);--%>
<%--            --%>
            if(finger512==""){
                ///alert("还没有512的数据，请点设置512");
                return false;
            }
            //AFIS1.SETFINGERCODES(finger400,3);
	    	AFIS1.SETFINGERCODES512(finger512,3);
        }
		
		//指纹验证
       function checkFinger(){
            AFIS1.SETRVflags(2,3000);
            var success = AFIS1.success;
			if(flag==1){
				if(success==1){
					alert("左手指纹验证成功");
					flag=flag+1;
					window.clearInterval(k);
					AFIS1.Stop;
					AFIS1.Colse;
					//表单提交
					fingerTwice();
				}else if(success==8){
					alert("指纹验证失败，重新采集");
					btn_ClearFace();
					window.location.reload();
					return false;
				}else if(success==0){
				   alert("请按手指!!!!!!!");
					return false;
				}else{
				   alert("AFIS1.success="+success);
				   return false;
				}
			}else if(flag==2){
				if(success==1){
					alert("右手指纹验证成功");
					window.
					AFIS1.Stop;
					AFIS1.Colse;
					window.clearInterval(k);
					window.opener.document.getElementById("fingerOne").value = document.getElementById("fingerOne").value;
					window.opener.document.getElementById("fingerTwo").value = document.getElementById("fingerTwo").value;
					window.opener.document.getElementById("fingerThree").value = document.getElementById("fingerThree").value;
					window.opener.document.getElementById("fingerFour").value = document.getElementById("fingerFour").value;

					//表单提交
					alert("指纹采集成功!");
					window.close();
					//myform.submit();
				}else if(success==8){
					alert("指纹验证失败，重新采集");
					btn_ClearFace();
					window.location.reload();
					return false;
				}else if(success==0){
				   alert("请按手指!!!!!!!");
					return false;
				}else{
				   alert("AFIS1.success="+success);
				   return false;
				}
			}
        }
		
		function fingerTwice(){
			document.getElementById("div1_2").style.display="none";//显示
			document.getElementById("div_cj").style.display="block";//显示
         	document.getElementById("div1_1").style.display="none";//隐藏
			document.getElementById("div2_1").style.display="block";//显示
			document.getElementById("div2_2").style.display="block";//显示
		}
	</script>
<style type="text/css">
	.ztree li span.button.addz {margin-left:2px; margin-right: -1px; background-position:-144px 0; vertical-align:top; *vertical-align:middle}
	div.zTreeDemoBackground {width:250px;height:250px;text-align:center;}
	
	ul.ztree {margin-top: 10px;border: 1px solid #617775;background: #f0f6e4;width:300px;height:400px;overflow-y:auto;overflow-x:auto;}
</style>
</head>
  
<body onLoad="check();" leftmargin="0" topmargin="0"	marginwidth="0" marginheight="0">
  <center>
  <br/>
	<table width="90%">
		<tr>
			<td colspan="4" class="table_t">警员指纹采集</td>
		</tr>
		<br/>
		<table style="width: 90%;" border="0" cellpadding="0" cellspacing="0"  >
			  <tr>
			      <td class="datalist">
			         <div>
						<table border="0" cellpadding="1" 	 cellspacing="1" width="100%" height="100%" style="border-right:#C7C7C7 1px solid; border-bottom:#C7C7C7 1px solid; border-left:#C7C7C7 1px solid; border-top:#C7C7C7 1px solid">
		<tr>
		<td width="60%" align="center">&nbsp;
			<object classid="clsid:862C377C-6F48-49B9-829C-D10F48555F9D" id="AFIS1">
				<param name="Visible" value="0">
				<param name="AutoScroll" value="0">
				<param name="AutoSize" value="0">
				<param name="AxBorderStyle" value="1">
				<param name="Caption" value="AFIS">
				<param name="Color" value="16777215">
				<param name="Font" value="MS Sans Serif">
				<param name="KeyPreview" value="0">
				<param name="PixelsPerInch" value="96">
				<param name="PrintScale" value="1">
				<param name="Scaled" value="-1">
				<param name="DropTarget" value="0">
				<param name="HelpFile" value>
				<param name="DoubleBuffered" value="0">
				<param name="Enabled" value="-1">
				<param name="Cursor" value="0">
				<param name="HelpType" value="1">
				<param name="HelpKeyword" value>
				<param name="Success" value="0">
				<param name="FingerCodes" value="0">
			</object>
		</td>
	  <td align="left">&nbsp;&nbsp;&nbsp;&nbsp;
	    <form name="myform" action="finger2.html" method="post" target="abc">
	    <iframe id="abc" name="abc" width="0" height="0" style="display: none;"></iframe>
			<table style="width: 95%;height: 78%";>
				<tr>
				    <td align="center">
					<div id="div1_2" style="display:none;">
						<font color="#cc4545" size="2">请将相同手指重新放置进行验证！</font>
					</div>
					</td>
				</tr>
				<tr>
					<td align="center">
						<input type="hidden" name="finger.fingerOne" id="fingerOne"></input>
						<input type="hidden" name="finger.fingerThree" id="fingerThree"></input>
						<input type="hidden" name="finger.fingerTwo" id="fingerTwo"></input>
						<input type="hidden" name="finger.fingerFour" id="fingerFour"></input>
						<div id="div_cj">
							<input type="button"  onclick="
								clearInterval(ss),
								runFlag512(fingerOne,fingerThree,fingerTwo,fingerFour),
								setFinger400(),
								divFinger1_show(),
								setInterval('AFIS1.DisFingers();',1000),
								k=setInterval('checkFinger();',1000);" style="border:0px;
								background: url('<%=basePath%>/images/caiji.gif');width:80px;height:27px;"/>
						</div>
					</td>
				</tr> 
				<tr>
					<td align="center" >
						<div id="div1_1"><font color="#cc4545" size="2">第一次建议使用左手食指采集！</font></div>
						<div id="div2_1" style="display:none;"><font color="#cc4545" size="2">第二次建议使用右手食指采集！</font></div>
						<div id="div2_2" style="display:none">
							<input type="button" style="border:0px;
								background: url('<%=basePath%>/images/ccai.gif');width:80px;height:27px;" onclick="window.location.reload();" />
						</div>
						<div id="div3_1"><font color="#cc4545" size="2">指纹采集时请把手指放在指纹仪上5秒,待有提示后方可松开。</font></div>
					</td>
				</tr>
				</table>
			</form>
	  	</td>
		</tr>
	</table>
					 </div>
			      </td>
			      <td style="width: 50%";>
			<form action="<%=request.getContextPath()%>/jyzwcj/jyzwcj_zwcjInsert.action" method="post" id="user_form" name="user_form" target="abc">
				<table border="0" cellpadding="0" cellspacing="0" class="edittable"  style="width: 95%;height: 91%";>
					<s:iterator id="user" value="#request.jyxxList" status="st">
					<br/>
					<tr>
						<td>警号</td>
						<td><input type="text" name="jh" value="${user[0]}" readonly="readonly" style="border: 0px;"/></td>
					</tr>
					<tr>
						<td>姓名</td>
						<td><input type="text" name="xm" value="${user[1]}" readonly="readonly" style="border: 0px;"/></td>
					</tr>
					<tr style="display: none">
						<td>部门编号</td>
						<td><input type="text" name="glbm" value="${user[2]}" readonly="readonly" style="border: 0px;"/></td>
					</tr>
					<tr>
						<td>部门名称</td>
						<td><input type="text" name="bmbh" value="${user[3]}" readonly="readonly" style="border: 0px;"/></td>
					</tr>
					<tr style="display: none">
						<td>指纹1</td>
						<td><input type="text"  name="fingerOne" id="finger1" readonly="readonly" style="border: 0px;"/></td>
					</tr>
					<tr style="display: none">
						<td>指纹2</td>
						<td><input type="text" name="fingerTwo" id="finger3" readonly="readonly" style="border: 0px;"/></td>
					</tr>
					<tr style="display: none">
						<td>指纹3</td>
						<td><input type="text" name="fingerThree" id="finger2" readonly="readonly" style="border: 0px;"/></td>
					</tr>
					<tr style="display: none">
						<td>指纹4</td>
						<td><input type="text" name="fingerFour" id="finger4" readonly="readonly" style="border: 0px;"//></td>
					</tr>
					<tr>
						<td>状态</td>
						<s:if test="#request.user[4]!=1">
							<td><input type="text" name="zt" value="未采集" readonly="readonly" style="border: 0px;"/></td>
						</s:if>
						<s:if test="#request.user[4]==1">
							<td><input type="text" name="zt" value="已采集" readonly="readonly" style="border: 0px;color: green;"/></td>
						</s:if>
					</tr>
					<tr>
						<td>采集人编号</td>
						<td><input type="text" name="czrbh" value="${user[5]}" readonly="readonly" style="border: 0px;"/></td>
					</tr>
					<tr>
						<td>采集人姓名</td>
						<td><input type="text" name="czrxm" value="${user[6]}" readonly="readonly" style="border: 0px;"/></td>
					</tr>
					<tr>
						<td>采集人部门</td>
						<td><input type="text" name="czrbm" value="${user[7]}" readonly="readonly" style="border: 0px;"/></td>
					</tr>
					<tr style="display: none">
						<td>科级部门</td>
						<td><input type="text" name="kjbm" value="" style="border: 0px;"/></td>
					</tr>
					<tr>
						<td>采集时间</td>
						<td><input type="text" name="czsj" value="${user[8]}" style="border: 0px;" ></td>
					</tr>
					<tr style="display: none">
						<td>采集IP</td>
						<td><input type="text" name="czip" value="" style="border: 0px;"/></td>
					</tr>
					<tr style="display: none">
						<td>采集MAC</td>
						<td><input type="text" name="czmac" value="" style="border: 0px;"/></td>
					</tr>
					</s:iterator>
				</table>
			</form>
			      </td>
			  </tr>
		</table>

	</table><br/>
	<div align="center">
		<input type="button" onclick="javascript:checkBaocun();" value="提 交" style="cursor:pointer;width: 79px;height: 28px;border: none;background: url(<%=request.getContextPath()%>/images/an2.gif) no-repeat;color: #FFFFFF;font-weight: bold;">&nbsp;&nbsp;&nbsp;
		<input type="button" onclick="javascript:window.history.go(-1);" value="返 回" style="cursor:pointer;width: 79px;height: 28px;border: none;background: url(<%=request.getContextPath()%>/images/an2.gif) no-repeat;color: #FFFFFF;font-weight: bold;">
	</div>
	</center>
  </body>
</html>
