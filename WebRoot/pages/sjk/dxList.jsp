<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<title>短信发送/查询</title>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/content.css" />
		<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/jquery-1.8.2.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/public.js"></script>
		<style type="text/css">
			.ui-autocomplete-loading {
				background: white url('<%=request.getContextPath()%>/jquery/development-bundle/demos/autocomplete/images/ui-anim_basic_16x16.gif') right center no-repeat;
			}
			#susernameid { width: 12em; }
			.bnt2 {
				width: 76px;
				height: 27px;
				background: url('<%=request.getContextPath()%>/images/an3.gif') no-repeat;
				border: none;
				font-weight: bold;
			}
		</style>
		<script type="text/javascript">
			$(document).ready(function() {
				
			});
			
			function fileSubmitFun(){
				var uploadExcel = document.getElementById("uploadExcel");
				var dxrn = document.getElementById("dxrn");
				if(checknotnull(uploadExcel,"请选择上传文件") == "false"){
					return false;
				}
				
				// 得到后缀名
				var hzm = uploadExcel.value.substring(uploadExcel.value.lastIndexOf(".")+1);
				
				if(hzm != "xls"){
					alert("请选择97-03版本的excle文件")
					uploadExcel.style.borderColor = '#FF0000';
					uploadExcel.focus();
					return false;
				}
				uploadExcel.style.borderColor = '';
				
				
				if(checknotnull(dxrn,"填写短信内容") == "false"){
					return false;
				}
				
				myForm.submit();
			}
			
			
			
		</script>
		<style> 
			html{ 
				overflow:scroll;
				scrollbar-base-color:#c7e5ff;
				scrollbar-track-color:#FFFFFF;
			} 
		</style>
	</head>

	<body style="background:#c7e5ff;">
		<div class="content1" style="width:100%;">
			<div class="roundedBox" id="type1" style="width:95%;">
				<div class="right" style="width:90%;">
					
					<form action="<%=request.getContextPath()%>/sjk/sjk_getExcelInfo.action" method="post" id="myForm" name="myForm" enctype="multipart/form-data"  target="abc">
						<iframe id="abc" name="abc" width="0" height="0" style="display: none;"></iframe>
						<table class="table1" width="100%" border="0"
						cellpadding="0" cellspacing="0">
							<tr>
								<th class="th1" height="32" colspan="4">
									短信发送
								</th>
							</tr>
							<tr>
								<td class="tds" style="text-align: right; width: 15%;">
									文件&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<input type="file" id="uploadExcel" name="uploadExcel">
								</td>
								<td class="tds" style="text-align: right; width: 15%;">
									内容&nbsp;
								</td>
								<td class="tdl" style="text-align: left;">
									<textarea id="dxrn" name="dxrn" rows="5" cols="30"></textarea>
								</td>
							</tr>
							<tr>
								<td class="tds" colspan="4">
									<div align="center">
										<input class="bnt" type="button" onclick="fileSubmitFun()" id="frombutid" value="发  送" style="cursor:pointer;" />
										<!-- <input class="bnt" type="reset" value="重  置" style="cursor:pointer;" /> -->
									</div>
								</td>
							</tr>
							<tr>
								<td class="tds" height="30" colspan="4">
									<span id="fileSpan" style="color: red;font-weight: normal;">${returnMsg}</span>
								</td>
							</tr>
						</table>
					</form>
				</div>

				<div class="corner topLeft"></div>
				<div class="corner topRight"></div>
				<div class="corner bottomLeft"></div>
				<div class="corner bottomRight"></div>
			</div>
		</div>

	</body>
</html>
