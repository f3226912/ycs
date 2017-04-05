<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
  <head>
    <title>机 动 车 驾 驶 证 申 请 表</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/js/jquery-1.8.2.js"></script>
	<style type="text/css">
	*{ margin:0 auto; font-size:12px;}
	.main{ width:800px; text-align:center;}
	.bt{ width:446px; height:40px; font-size:30px; font-weight:bold; text-align:center; margin-top:0px;}
	.table1{ width:546px; height:38px; float:right;}
	.content{ width:800px; clear:both;  margin-top:-1px;}
	td{ border-right:solid 1px #000; border-top:solid 1px #000; text-align:center; vertical-align:middle; }
	p{ text-align:left; line-height:18px; padding:0 10px 0 20px;}
	.tdl{ text-align:left; }
	.text1{border:none; border:0;}
	.fonts{font-size:13px; font-weight:bold;}
	.font12{font-size:12px; font-weight:bold;}
	</style>
	<script type="text/javascript">
		function printReport() {
			//$("#printBtn").hide();
			window.print();
			//$("#printBtn").show();
			return false;
		}
	</script>
</head>

<body onload="printReport();">
<div class="bt">机 动 车 驾 驶 证 申 请 表</div>
<div class="main">
	<div class="table1"> 
	    <table width="469" height="38" border="1" cellpadding="0" cellspacing="0">
		  <tr>
			<td class="font12" width="98">受理岗签字签章</td>
			<td width="121">&nbsp;</td>
			<td class="font12"  width="68">档案编号</td>
			<td width="172">&nbsp;${slgDrvXxcjb.dabh }</td>
		  </tr>
	  </table>
	</div>
	
	<div class="content">
	<table width="721"   border="1" cellpadding="0" cellspacing="0"  style="border-left:solid 2px #000; border-bottom:solid 2px #000;border-top:solid 1px #000;">
	  <tr>
		<td width="716">
		   <table  height="230" class="fonts"  border="0" cellpadding="0" cellspacing="0">
			  <tr>
				<td  width="22" class="fonts" rowspan="6">申请人信息</td>
				<td  width="72">姓名</td>
				<td colspan="7" height="28">&nbsp;${slgDrvXxcjb.xm }</td>
				<td  colspan="2">性别</td>
				<td colspan="2">&nbsp;${slgDrvXxcjb.xb }</td>
				<td  colspan="3">出生日期</td>
				<td colspan="6">&nbsp;<s:date name="#request.slgDrvXxcjb.csrq" format="yyyy年MM月dd日"/></td>
				<td  width="32">国籍</td>
				<td width="136">&nbsp;${slgDrvXxcjb.gj }</td>
			  </tr>
			  <tr>
				<td  rowspan="2" width="72">身份证明名称</td>
				<td width="32" rowspan="2">&nbsp;居民身份证</td>
				<td  width="28">号码</td>
				<td width="21" height="19">&nbsp;${sfzmhmstr[0]}</td>
				<td width="21" height="19">&nbsp;${sfzmhmstr[1]}</td>
				<td width="21" height="19">&nbsp;${sfzmhmstr[2]}</td>
				<td width="21" height="19">&nbsp;${sfzmhmstr[3]}</td>
				<td width="21" height="19">&nbsp;${sfzmhmstr[4]}</td>
				<td width="21" height="19">&nbsp;${sfzmhmstr[5]}</td>
				<td width="21" height="19">&nbsp;${sfzmhmstr[6]}</td>
				<td width="21" height="19">&nbsp;${sfzmhmstr[7]}</td>
				<td width="21" height="19">&nbsp;${sfzmhmstr[8]}</td>
				<td width="21" height="19">&nbsp;${sfzmhmstr[9]}</td>
				<td width="21" height="19">&nbsp;${sfzmhmstr[10]}</td>
				<td width="21" height="19">&nbsp;${sfzmhmstr[11]}</td>
				<td width="21" height="19">&nbsp;${sfzmhmstr[12]}</td>
				<td width="21" height="19">&nbsp;${sfzmhmstr[13]}</td>
				<td width="21" height="19">&nbsp;${sfzmhmstr[14]}</td>
				<td width="21" height="19">&nbsp;${sfzmhmstr[15]}</td>
				<td width="21" height="19">&nbsp;${sfzmhmstr[16]}</td>
				<td width="21" height="19">&nbsp;${sfzmhmstr[17]}</td>
				<td  colspan="2" rowspan="5">照片</td>
			  </tr>
			  <tr>
				<td >号码</td>
				<td width="21" height="21">&nbsp;</td>
				<td width="21" height="21">&nbsp;</td>
				<td width="21" height="21">&nbsp;</td>
				<td width="21" height="21">&nbsp;</td>
				<td width="21" height="21">&nbsp;</td>
				<td width="21" height="21">&nbsp;</td>
				<td width="21" height="21">&nbsp;</td>
				<td width="21" height="21">&nbsp;</td>
				<td width="21" height="21">&nbsp;</td>
				<td width="21" height="21">&nbsp;</td>
				<td width="21" height="21">&nbsp;</td>
				<td width="21" height="21">&nbsp;</td>
				<td width="21" height="21">&nbsp;</td>
				<td width="21" height="21">&nbsp;</td>
				<td width="21" height="21">&nbsp;</td>
				<td width="21" height="21">&nbsp;</td>
				<td width="21" height="21">&nbsp;</td>
				<td width="21" height="21">&nbsp;</td>
			  </tr>
			  <tr>
				<td  width="72" height="22">邮寄地址</td>
				<td colspan="20">&nbsp;${slgDrvXxcjb.lxzsxxdz }</td>
			  </tr>
			  <tr>
				<td  width="72" height="25">固定电话</td>
				<td colspan="7">&nbsp;${slgDrvXxcjb.lxdh }</td>
				<td  colspan="4">电子信箱</td>
				<td colspan="9">&nbsp;${slgDrvXxcjb.dzyx }</td>
			  </tr>
			  <tr>
				<td  width="72" height="20">移动电话</td>
				<td colspan="7">&nbsp;${slgDrvXxcjb.sjhm }</td>
				<td  colspan="4">邮政编码</td>
				<td colspan="9">&nbsp;</td>
			  </tr>
		  </table>

		</td>
	  </tr>
	  
	  <tr>
		<td><table  height="360" border="0"  cellpadding="0" cellspacing="0">
          <tr>
            <td  width="22" class="fonts" rowspan="15">申请业务种类</td>
            <td class="tdl" colspan="2">□<font class="fonts">初次申领</font></td>
            <td  width="51" class="fonts" rowspan="4">申请的准驾车型代号</td>
            <td colspan="2" rowspan="4">&nbsp;</td>
            <td  colspan="2" class="font12" rowspan="2">属于持军警驾驶证、境外驾驶证申领的，还应填写所持驾驶证种类：</td>
            </tr>
          <tr>
            <td class="tdl" colspan="2">□<font class="fonts">增加准驾车型</font></td>
            </tr>
          <tr>
            <td class="tdl" colspan="2">□<font class="fonts">持军警驾驶证申领</font></td>
            <td colspan="2" style="text-align:center;">□<font class="fonts">军队驾驶证</font>  □<font class="fonts">武警驾驶证</font></td>
            </tr>
          <tr>
            <td class="tdl" colspan="2">□<font class="fonts">持境外驾驶证申领</font></td>
            <td colspan="2" style="text-align:center;">□<font class="fonts">香港驾驶证  □<font class="fonts">澳门驾驶证</font>  □<font class="fonts">台湾驾驶证</font>  □<font class="fonts">外国驾驶证</font></td>
            </tr>
          <tr>
            <td class="tdl" colspan="5">□<font class="fonts">证件损毁换证</font>   □<font class="fonts">转入换证</font>  □<font class="fonts">有效期满换证</font></td>
            <td align="left" class="fonts" colspan="2">换证时无法提交居住、暂住证明原因：</td>
            </tr>
          <tr>
            <td class="tdl" colspan="5">□<font class="fonts">达到规定年龄换证</font>  □<font class="fonts">自愿降低准驾车型换证</font></td>
            <td  width="91" class="fonts" rowspan="2">申请的准驾车型代号</td>
            <td width="342" rowspan="2">&nbsp;</td>
          </tr>
          <tr>
            <td class="tdl" colspan="5">□<font class="fonts">因身体条件变化降低准驾车型换证</font></td>
            </tr>
          <tr>
            <td height="24" colspan="2" class="tdl">□<font class="fonts">信息变化换证</font></td>
            <td >变更事项</td>
            <td colspan="2">&nbsp;</td>
            <td class="fonts" >变更内容</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td class="tdl" height="20" colspan="2">□<font class="fonts">信息备案</font></td>
            <td >从业单位</td>
            <td colspan="4">&nbsp;</td>
            </tr>
          <tr>
            <td height="19" colspan="3" class="tdl">□<font class="fonts">补证</font></td>
            <td  width="21" class="fonts" rowspan="6">原
              因</td>
            <td width="85" style="text-align:left;">□丢失  □其他</td>
            <td  class="fonts" colspan="2">补证时无法提交居住、暂住证明原因：</td>
            </tr>
          <tr>
            <td class="tdl" colspan="3">□<font class="fonts">注销</font></td>
            <td colspan="3" style="text-align:left;">□本人申请   □死亡   □身体条件不适合   □丧失民事行为能力   □其他</td>
            </tr>
          <tr>
            <td class="tdl" colspan="3">□<font class="fonts">注销最高准驾车型</font></td>
            <td colspan="3" rowspan="2" style="text-align:left;">□发生交通事故造成人员死亡，承担同等以上责任<br />
              □连续三个记分周期不参加审验  □记满12分<br />
              □延长的实习期内再次记6分以上但未达到12分</td>
            </tr>
          <tr>
            <td height="20" colspan="3" class="tdl">□<font class="fonts">注销实习准驾车型</font></td>
            </tr>
          <tr>
            <td class="tdl" width="98">□<font class="fonts">恢复驾驶资格</font></td>
            <td  width="43" class="fonts">准驾车型代号</td>
            <td>&nbsp;</td>
            <td style="text-align:left;" colspan="3">□超过有效期一年以上未换证被注销未满两年 <br />
			 □未按规定提交体检证明被注销未满两年 </td>
            </tr>
          <tr>
            <td  class="tdl" colspan="3">□<font class="fonts">延期换证</font>  □<font class="fonts">延期审验</font><br />
              □<font class="fonts">延期提交身体条件证明</font></td>
            <td colspan="3" style="text-align:left;">□服兵役        □出国（境）       □其他</td>
            </tr>
        </table></td>
	  </tr>
	  
	  <tr>
		<td>
		<table class="fonts"  height="90" border="0" cellpadding="0" cellspacing="0">
		  <tr>
			<td width="103" height="25" >申请方式</td>
			<td  colspan="6">□本人申请             □监护人申请 □委托
			  <input class="text1" style="border-bottom:1px solid #333333;" name="Input" type="text" /> 
			  代理申请</td>
			</tr>
		  <tr>
			<td   rowspan="2">委托代理人<br />
			  监护人信息</td>
			<td width="123" height="20" >代理人/监护人姓名</td>
			<td width="42">&nbsp;</td>
			<td  width="85">身份证明名称</td>
			<td width="71">&nbsp;</td>
			<td  width="103">身份证明号码</td>
			<td width="236">&nbsp;</td>
		  </tr>
		  <tr>
			<td >联系地址</td>
			<td colspan="3">&nbsp;</td>
			<td >联系电话</td>
			<td>&nbsp;</td>
		  </tr>
		</table>

		</td>
	  </tr>
	  
	  <tr>
		<td>
		<table height="270"  border="0" cellpadding="0" cellspacing="0">
		  <tr>
			<td  width="22" class="fonts" rowspan="3">申告的义务和内容</td>
			<td width="520" rowspan="3"> <p style="font-weight:bold;">机动车驾驶证申请人应当如实申告是否具有下列不准申请的情形：</p>
			  <p> 一、器质性心脏病、癫痫病、美尼尔氏症、眩晕症、癔病、震颤麻痹、精神病、痴呆以及影响肢体活动的神经系统疾病等妨碍安全驾驶疾病；</p>
			  <p> 二、三年内有吸食、注射毒品行为或者解除强制隔离戒毒措施未满三年，或者长期服用依赖性 精神药品成瘾尚未戒除；</p>
			  <p> 三、提供虚假申请材料，以欺骗等不正当手段申领机动车驾驶证；</p>
			  <p> 四、造成交通事故后逃逸构成犯罪；</p>
			  <p> 五、饮酒后或者醉酒驾驶机动车发生重大交通事故构成犯罪；</p>
			  <p> 六、醉酒驾驶机动车或者饮酒后驾驶营运机动车依法被吊销机动车驾驶证未满五年；</p>
			  <p>七、醉酒驾驶营运机动车依法被吊销机动车驾驶证未满十年；</p>
			  <p> 八、因其他情形依法被吊销机动车驾驶证未满二年</p>
			  <p>九、驾驶许可依法被撤销未满三年</p>
			  <p>十、法律和行政法规规定的其他不准申请的情形。</p>
			  <p style=" font-weight:bold;">上述内容本人已认真阅读，本人不具有所列的不准申请的情形。</p></td>
			<td  width="174" class="fonts" >申请人及代理人对申请材料的真实有效性负责。</td>
		  </tr>
		  <tr>
			<td  ><p class="fonts" style="padding:0;">申请人签字：
			  </p>
			  <br /> 
			  <br />
			  <br />
			  <p style="text-align:right">&nbsp;&nbsp;&nbsp;&nbsp;年 &nbsp;&nbsp;  月 &nbsp;&nbsp;  日</p></td>
		  </tr>
		  <tr>
			<td  ><p class="fonts" style="padding:0;">代理人/监护人签字: </p>
			  <br /> 
			  <br />
			  <br />
			  <br />
			  <br />
			  <p style="text-align:right">&nbsp;&nbsp;&nbsp;&nbsp;年   &nbsp;&nbsp;   月  &nbsp;&nbsp;    日</p></td>
		  </tr>
		</table>
		</td>
	  </tr>
	</table>

	</div>

</div>

</body>
</html>
