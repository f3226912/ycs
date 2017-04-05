<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<title>指纹采集</title>
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
		        background-color: #EEF2FB;
		    }
    </style>
<head>
    <script language="javascript">
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
					alert(v);
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
                alert("初始化出现异常错误5");
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
        
        function tianjia(){
        	window.location.href="<%=request.getContextPath()%>/jyzwcj/jyzwcj_tianjiaFinger.action;
        }
		
		function fingerTwice(){
			document.getElementById("div1_2").style.display="none";//显示
			document.getElementById("div_cj").style.display="block";//显示
         	document.getElementById("div1_1").style.display="none";//隐藏
			document.getElementById("div2_1").style.display="block";//显示
			document.getElementById("div2_2").style.display="block";//显示
		}
    </script>
</head>
<body onLoad="check();" leftmargin="0" topmargin="0"	marginwidth="0" marginheight="0">
	<center>
	<table border="0" cellpadding="1" 	 cellspacing="1" width="100%"  style="border-right:#C7C7C7 1px solid; border-bottom:#C7C7C7 1px solid; border-left:#C7C7C7 1px solid; border-top:#C7C7C7 1px solid">
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
	    <form name="myform" action="finger2.html" method="post">
				<table>
					<tr>
				    <td align="center">
					<div id="div1_2" style="display:none;">
						<font color="#cc4545" size="3">请将相同手指重新放置进行验证！</font>
					</div></td>
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
						<div>
							<input type="button" onclick="tianjia()" value="添 加">
						</div>
					</td>
				</tr> 
				<tr>
					<td align="center">
						<div id="div1_1"><font color="#cc4545" size="3">第一次建议使用左手食指采集</font></div>
						<div id="div2_1" style="display:none;"><font color="#cc4545" size="3">第二次建议使用右手食指采集</font></div>
						<div id="div2_2" style="display:none">
							<input type="button" style="border:0px;
								background: url('<%=basePath%>/images/ccai.gif');width:80px;height:27px;" onclick="window.location.reload();" />
						</div>
						<div id="div3_1"><font color="#cc4545" size="3">指纹采集时请把手指放在指纹仪上5秒,待有提示后方可松开.</font></div>
					</td>
				</tr>
				</table>
			</form>
	  </td>
		</tr>
	</table>
</center>
</body>
</html>