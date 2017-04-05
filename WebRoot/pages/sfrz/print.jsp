<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
	<head>
		<title></title>
		<style>
.content {
	width: 640px;
	margin: 0 auto;
	
}

.table {
	width: 640px;
	text-align: center;
}
#dh {
	text-align: left;
}
#wx {
	text-align: left;
}
.table caption {
	font-weight: bold;
}

.table1 {
	width: 640px;
	height: 129px;
	text-align: center;
}

.table2 {
	width: 640px;
	height: 66px;
	text-align: center;
}

.table3 {
	width: 640px;
	height: 265px;
	text-align: center;
}

.table4 {
	width: 640px;
	height: 233px;
	text-align: center;
}

.tdTitle {
	font-weight: bold;
}


td {
	height: 30px;
}
</style>
	</head>
	<script type="text/javascript">
function printt(printPage) {
	var headstr = "<html><head><title></title></head><body>";
	var footstr = "</body>";
	var newstr = document.getElementById(printPage).innerHTML;
	var oldstr = document.body.innerHTML;
	//document.body.innerHTML = headstr + newstr + footstr;
	parent.window.print();
	//document.body.innerHTML = oldstr;
	window.close();
}
</script>
	<body onload="printt('print_div2')">
		<div id="print_div2" class="content">
			<table width="640" border="1px" cellpadding="0" cellspacing="0"
				bordercolor="#000000" class="table"
				style="border-collapse: collapse"  font-size: 13px; border-color="#">
				<caption style="font-size: 28px; font-weight: bold">
					机动车车主（驾驶人）及驾驶证信息核实采集表
					<br />
				</caption>
				<tr>
				  <td rowspan="3" class="tdTitle" style="width: 25px;">
						个人信息
				  </td>
					<td width="89" style="width: 89px;">
						姓名
				  </td>
					<td width="84" style="width: 90px;">
						${xm}
				  </td>
					<td width="45">
						性别
				  </td>
					<td width="57" style="width: 57px;">
						${xb}
				  </td>
					<td width="57">
						籍贯
				  </td>
					<td width="60" style="width: 60px;">
						${jg}
				  </td>
					<td width="40">
						民族
				  </td>
					<td width="84" style="width: 90px;">
						${mz}
				  </td>
					<td width="41">
						国籍
				  </td>
					<td width="45" style="width: 45px;">
						${gj}
				  </td>
				</tr>
				<tr>
					<td height="41" >
						证件类别					</td>
					<td>
						${sfzmmc}
					</td>
					<td>
						号码
					</td>
					<td colspan="8">
						${sfzmhm}
					</td>
				</tr><%--
				<tr>
					<td></td>
					<td>
						号码
					</td>
					<td colspan="8"></td>
				</tr>
			--%></table>
			<table border="1px" cellpadding="0" cellspacing="0"
				bordercolor="#000000" class="table1"
				style="border-collapse: collapse;  font-size: 13px; border-top: none;"
				border-color="black">
				<tr>
					<td rowspan="4" style="width: 25px;" class="tdTitle">
						联系方式
					</td>
					<td style="width: 89px;">
						通讯地址
					</td>
					<td colspan="5">
						${txdz}
					</td>
				</tr>
				<tr>
					<td>
						投递地址
					</td>
					<td colspan="5"></td>
				</tr>
				<tr>
					<td>
						电子邮箱
					</td>
					<td style="width: 150px;">
						${dzyx}
					</td>
					<td>
						固定电话
					</td>
					<td style="width: 150px;">
						${gddh}
					</td>
					<td>
						移动电话
					</td>
					<td style="width: 150px;" id="dh">
						${yddh}
					</td>
				</tr>
				<tr>
					<td>
						QQ
					</td>
					<td>
						${qq}
					</td>
					<td>
						微博
					</td>
					<td>
						${wb}
					</td>
					<td>
						微信
					</td>
					<td id="wx">
						${wx}
					</td>
				</tr>
			</table>

			<table border="1px" cellpadding="0" cellspacing="0"
				bordercolor="#000000" class="table2"
				style="border-collapse: collapse; font-size: 13px; border-top: none; "
				border-color="#000">
				<tr>
					<td width="25" rowspan="2" class="tdTitle" style="width: 25px;">
						驾驶证					</td>
					<td width="88">
						驾驶证号码					</td>
					<td width="138" style="width: 150px;">					</td>
					<td width="44">档案编号</td>
					<td width="143" style="width: 150px;">					</td>
					<td width="41" >准驾车型</td>
					<td width="145" style="width: 150px;"></td>
			  </tr>
				<tr>
					<td>
						有效起始日期
					</td>
					<td style="width: 150px;">
						
					</td>
					<td>初次领证日期</td>
					<td style="width: 150px;">
						
					</td>
					<td>有效日期</td>
					<td style="width: 150px;">
						
					</td>
				</tr>
			</table>

			<table width="640" border="1px" cellpadding="0" cellspacing="0"
				bordercolor="#000000" class="table3"
				style="border-collapse: collapse; border-top: none; font-size: 13px;"
				border-color="#000">
				<tr>
					<td width="25" rowspan="8" class="tdTitle" style="width: 25px;">
						机动车
					</td>
					<td width="91" style="width: 89px;">
						机动车号牌					</td>
				  <td width="51" style="width: 50px;">&nbsp;				  </td>
					<td width="65">
						车辆类型					</td>
					<td width="56" style="width: 55;"></td>
					<td width="85" style="width: 83px;">
						车辆使用信息					</td>
					<td colspan="6">
						<input type="checkbox">
						本人使用
						<input type="checkbox">
						营运
						<input type="checkbox">
						非营运
						<input type="checkbox">
						其他
					</td>
				</tr>
				<tr>
					<td>
						所有人
					</td>
					<td colspan="3"></td>
					<td>
						注册日期
					</td>
					<td width="51" style="width: 50px;"></td>
					<td width="65">
						发证日期					</td>
					<td width="51" style="width: 50px;"></td>
					<td width="83">
						核定载人数					</td>
					<td width="83" style="width: 50px;"></td>
				</tr>
				<tr>
					<td>
						机动车号牌
					</td>
					<td>&nbsp;
						
				  </td>
					<td>
						车辆类型
					</td>
					<td></td>
					<td>
						车辆使用信息
					</td>
					<td colspan="6">
						<input type="checkbox">
						本人使用
						<input type="checkbox">
						营运
						<input type="checkbox">
						非营运
						<input type="checkbox">
						其他
					</td>
				</tr>
				<tr>
					<td>
						所有人
					</td>
					<td colspan="3"></td>
					<td>
						注册日期
					</td>
					<td></td>
					<td>
						发证日期
					</td>
					<td></td>
					<td>
						核定载人数
					</td>
					<td></td>
				</tr>
				<tr>
					<td>
						机动车号牌
					</td>
					<td>&nbsp;
						
				  </td>
					<td>
						车辆类型
					</td>
					<td></td>
					<td>
						车辆使用信息
					</td>
					<td colspan="6">
						<input type="checkbox">
						本人使用
						<input type="checkbox">
						营运
						<input type="checkbox">
						非营运
						<input type="checkbox">
						其他
					</td>
				</tr>
				<tr>
					<td>
						所有人
					</td>
					<td colspan="3"></td>
					<td>
						注册日期
					</td>
					<td></td>
					<td>
						发证日期
					</td>
					<td></td>
					<td>
						核定载人数
					</td>
					<td></td>
				</tr>
				<tr>
					<td>
						机动车号牌
					</td>
					<td>&nbsp;
						
				  </td>
					<td>
						车辆类型
					</td>
					<td></td>
					<td>
						车辆使用信息
					</td>
					<td colspan="6">
						<input type="checkbox">
						本人使用
						<input type="checkbox">
						营运
						<input type="checkbox">
						非营运
						<input type="checkbox">
						其他
					</td>
				</tr>
				<tr>
					<td height="41">
						所有人					</td>
					<td colspan="3"></td>
					<td>
						注册日期
					</td>
					<td></td>
					<td>
						发证日期
					</td>
					<td></td>
					<td>
						核定载人数
					</td>
					<td></td>
				</tr>
		  </table>

			<table width="640" border="1px" cellpadding="0" cellspacing="0"
				bordercolor="#000000" class="table4"
				style="border-collapse: collapse; border-top: none;"
				border-color="#000">
				<tr>
					<td width="25" rowspan="5" class="tdTitle" style="width: 25px;">
						说明					</td>
					<td width="400" rowspan="5"
						style="width: 400px; text-align: left; border-bottom: none; line-height: 20px; vertical-align: text-top; font-size: 13px">
					  <p>一、此信息采集表的内容由深圳市公安局交通警察局车辆管理所保管并严格保密，不对外公布。 <br />
					  二、机动车车主（驾驶人）及驾驶证持证人受理后有一个动态密码,请妥善保管，并及时更改。若遗忘， 请用绑定的手机号码找回。 <br />
					    三、采集表信息还需采集本人到窗口录入电子签名。 <br />
					    四、证件类别：居民身份证、护照、港澳台通行证、回乡证、居住证、暂住证。 <br />
					    五、填表时带“*”的为必填项，其他项若无可不填写。 <br />
	                  </p>
					</td>


					<td width="162" style="border-bottom: none; text-align: left">
					  <p>申请人对申请材料真实有效性负责的 </p>
					</td>
				</tr>

				<tr>
					<td style="border-bottom: none; text-align: left;" rowspan="">
						申请人电子签字：
						<img src="C:\\printzj4.jpg" width="150px;" height="40px">
					</td>
				</tr>
				<tr>
					<td valign="bottom" style="border-top: none;">
						<s:date name="#request.date" format="yyyy年MM月dd日"/>
					</td>
				</tr>
				<tr>
					<td style="border-bottom: none; text-align: left;">
						<p>
							申请人签名：
						</p>
					</td>
				</tr>
				<tr>
					<td style="border-top: none; vertical-align: bottom">
						<s:date name="#request.date" format="yyyy年MM月dd日"/>
					</td>
				</tr>

		  </table>
		</div>
	</body>
</html>
