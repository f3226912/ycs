<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="com.ycszh.common.PageTag" prefix="pt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>机 动 车 驾 驶 证 申 请 表</title>
<style type="text/css">
*{ margin:0 auto;font-size:11px;}
.main{ width:645px;}
.bt{ width:645px; height:20px; font-size:20px; font-weight:bold; text-align:center; margin-top:0px;}
.table1{ width:300px; height:20px; float:right;}
.content{ width:645px; clear:both;  margin-top:-1px;}
td{ border-right:solid 1px #000; border-top:solid 1px #000; text-align:center; vertical-align:middle; }
p{ text-align:left; line-height:20px; padding:0 20px 0 20px;}
.tdl{ text-align:left; font-weight:bold;}
.tdc{font-weight:bold;}
.text1{border:none; border:0;}

</style>
<script type="text/javascript">
	setTimeout(function(){
		window.print();
		window.close();
		return false;
	},100);
	
</script>
</head>
  
  <body>
<div class="bt">机 动 车 驾 驶 证 申 请 表</div>
<div class="main">
	<div class="table1"> 
	    <table width="377" height="25" border="1" cellpadding="0" cellspacing="0">
		  <tr>
			<td class="tdc" width="100">受理岗签字签章</td>
			<td width="100">&nbsp;</td>
			<td class="tdc" width="77">档案编号</td>
			<td width="100">&nbsp;${ezDrvLiceChanApp.ydabh}</td>
		  </tr>
	  </table>
	</div>
	<div class="content">
	<table width="645px" height="350px" border="1" cellpadding="0" cellspacing="0"  style="border-left:solid 2px #000; border-bottom:solid 2px #000;border-top:solid 1px #000;">
	  <tr>
		<td>
		   <table width="645" height="100" border="0" cellpadding="0" cellspacing="0">
			  <tr>
				<td class="tdc" width="28" rowspan="6">申请人信息</td>
				<td class="tdc" width="39">姓名</td>
				<td colspan="7" height="20">&nbsp;${ezDrvLiceChanApp.xm}</td>
				<td class="tdc" colspan="2">性别</td>
				<td colspan="2">&nbsp;${ezDrvLiceChanApp.xb}</td>
				<td class="tdc" colspan="3">出生日期</td>
				<td colspan="6">&nbsp;<s:date name="#request.ezDrvLiceChanApp.csrq" format="yyyy-MM-dd"/></td>
				<td class="tdc" width="60">国籍</td>
				<td width="111">&nbsp;${ezDrvLiceChanApp.gj}</td>
			  </tr>
			  <tr>
				<td class="tdc" rowspan="2" width="60">身份证明名称</td>
				<td width="165" height="20">&nbsp;${ezDrvLiceChanApp.sfzmmc}</td>
				<td class="tdc" width="61">号码</td>
				<td width="27" height="20">&nbsp;${s1}</td>
				<td width="33" height="20">&nbsp;${s2}</td>
				<td width="32" height="20">&nbsp;${s3}</td>
				<td width="31" height="20">&nbsp;${s4}</td>
				<td width="27" height="20">&nbsp;${s5}</td>
				<td width="28" height="20">&nbsp;${s6}</td>
				<td width="27" height="20">&nbsp;${s7}</td>
				<td width="27" height="20">&nbsp;${s8}</td>
				<td width="30" height="20">&nbsp;${s9}</td>
				<td width="29" height="20">&nbsp;${s10}</td>
				<td width="27" height="20">&nbsp;${s11}</td>
				<td width="27" height="20">&nbsp;${s12}</td>
				<td width="27" height="20">&nbsp;${s13}</td>
				<td width="29" height="20">&nbsp;${s14}</td>
				<td width="27" height="20">&nbsp;${s15}</td>
				<td width="27" height="20">&nbsp;${s16}</td>
				<td width="27" height="20">&nbsp;${s17}</td>
				<td width="39" height="20">&nbsp;${s18}</td>
				<td class="tdc" colspan="2" rowspan="5">照片</td>
			  </tr>
			  <tr>
				<td height="20">&nbsp;</td>
				<td class="tdc">号码</td>
				<td width="27" height="20">&nbsp;</td>
				<td width="33" height="20">&nbsp;</td>
				<td width="32" height="20">&nbsp;</td>
				<td width="31" height="20">&nbsp;</td>
				<td width="27" height="20">&nbsp;</td>
				<td width="28" height="20">&nbsp;</td>
				<td width="27" height="20">&nbsp;</td>
				<td width="27" height="20">&nbsp;</td>
				<td width="30" height="20">&nbsp;</td>
				<td width="29" height="20">&nbsp;</td>
				<td width="27" height="20">&nbsp;</td>
				<td width="27" height="20">&nbsp;</td>
				<td width="27" height="20">&nbsp;</td>
				<td width="29" height="20">&nbsp;</td>
				<td width="27" height="20">&nbsp;</td>
				<td width="27" height="20">&nbsp;</td>
				<td width="27" height="20">&nbsp;</td>
				<td width="39" height="20">&nbsp;</td>
			  </tr>
			  <tr>
				<td class="tdc" width="39" height="20">邮寄地址</td>
				<td colspan="20">&nbsp;${ezDrvLiceChanApp.sjrdz}</td>
			  </tr>
			  <tr>
				<td class="tdc" width="39" height="20">固定电话</td>
				<td colspan="7">&nbsp;${ezDrvLiceChanApp.gddh}</td>
				<td class="tdc" colspan="4">电子信箱</td>
				<td colspan="9">&nbsp;${ezDrvLiceChanApp.dzyx}</td>
			  </tr>
			  <tr>
				<td class="tdc" width="39" height="20">移动电话</td>
				<td colspan="7">&nbsp;${ezDrvLiceChanApp.yddh}</td>
				<td class="tdc" colspan="4">邮政编码</td>
				<td colspan="9">&nbsp;${ezDrvLiceChanApp.yzbm}</td>
			  </tr>
		  </table>

		</td>
	  </tr>
	  
	  <tr height="350">
		<td><table width="645" height="420" border="0"  cellpadding="0" cellspacing="0">
          <tr>
            <td class="tdc" width="27" rowspan="15">申请业务种类</td>
            <td class="tdl" colspan="2"><input type="checkbox" >初次申领</td>
            <td class="tdc" width="91" rowspan="4">申请的准驾车型代号</td>
            <td colspan="2" rowspan="4">&nbsp;${ezDrvLiceChanApp.zjcx}</td>
            <td class="tdc" colspan="2" rowspan="2">属于持军警驾驶证、境外驾驶证申领的，还应填写所持驾驶证种类：</td>
            </tr>
          <tr>
            <td class="tdl" colspan="2"><input type="checkbox" >增加准驾车型</td>
            </tr>
          <tr>
            <td class="tdl" colspan="2"><input type="checkbox" >持军警驾驶证申领</td>
            <td colspan="2" style="text-align:left;"><input type="checkbox" >军队驾驶证  <input type="checkbox" >武警驾驶证</td>
            </tr>
          <tr>
            <td class="tdl" colspan="2"><input type="checkbox" >持境外驾驶证申领</td>
            <td colspan="2" style="text-align:left;">
            	<input type="checkbox" >香港驾驶证  
            	<input type="checkbox" >澳门驾驶证  
            	<input type="checkbox" >台湾驾驶证  
            	<input type="checkbox" >外国驾驶证
            </td>
            </tr>
          <tr>
            <td class="tdl" colspan="5">
            	<input type="checkbox" >证件损毁换证  
            	<input type="checkbox" >转入换证 
            	 <input type="checkbox" >有效期满换证
            </td>
            <td class="tdc" colspan="2">换证时无法提交居住、暂住证明原因：</td>
            </tr>
          <tr>
            <td class="tdl" colspan="5">
            	<input type="checkbox" >达到规定年龄换证 
            	<input type="checkbox" >自愿降低准驾车型换证
            </td>
            <td class="tdc" width="172" rowspan="2">申请的准驾车型代号</td>
            <td width="365" rowspan="2">&nbsp;</td>
          </tr>
          <tr>
            <td class="tdl" colspan="5">
            	<input type="checkbox" >因身体条件变化降低准驾车型换证
            </td>
            </tr>
          <tr>
            <td height="15" colspan="2" class="tdl">
            	<input type="checkbox" >信息变化换证
            </td>
            <td class="tdc">变更事项</td>
            <td colspan="2">&nbsp;</td>
            <td class="tdc">变更内容</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td class="tdl" height="15" colspan="2">
            	<input type="checkbox" >信息备案
            </td>
            <td class="tdc">从业单位</td>
            <td colspan="4">&nbsp;</td>
            </tr>
          <tr>
            <td class="tdl" colspan="3"><input type="checkbox" >补证</td>
            <td class="tdc" width="28" rowspan="6">原因</td>
            <td width="107" style="text-align:left;"><input type="checkbox" >丢失  <input type="checkbox" >其他</td>
            <td class="tdc" colspan="2">补证时无法提交居住、暂住证明原因：</td>
            </tr>
          <tr>
            <td class="tdl" colspan="3"><input type="checkbox" >注销</td>
            <td colspan="3" style="text-align:left;">
            	<input type="checkbox" >本人申请   
            	<input type="checkbox" >死亡  
                <input type="checkbox" >身体条件不适合  
                <input type="checkbox" >丧失民事行为能力  
                <input type="checkbox" >其他
           </td>
            </tr>
          <tr>
            <td class="tdl" colspan="3"><input type="checkbox" >注销最高准驾车型</td>
            <td colspan="3" rowspan="2" style="text-align:left;"><input type="checkbox" >发生交通事故造成人员死亡，承担同等以上责任<br />
              <input type="checkbox" >连续三个记分周期不参加审验  <input type="checkbox" >记满12分<br />
              <input type="checkbox" >延长的实习期内再次记6分以上但未达到12分</td>
            </tr>
          <tr>
            <td class="tdl" colspan="3"><input type="checkbox" >注销实习准驾车型</td>
            </tr>
          <tr>
            <td class="tdl" width="122"><input type="checkbox" >恢复驾驶资格</td>
            <td class="tdc" width="73">准驾车型代号</td>
            <td>&nbsp;</td>
            <td style="text-align:left;" colspan="3"><input type="checkbox" >超过有效期一年以上未换证被注销未满两年 <br />
			 <input type="checkbox" >未按规定提交体检证明被注销未满两年 </td>
            </tr>
          <tr height="30">
            <td  class="tdl" colspan="3">
            	<input type="checkbox" >延期换证  
            	<input type="checkbox" >延期审验<br />
                <input type="checkbox" >延期提交身体条件证明
            </td>
            <td colspan="3" style="text-align:left;">
            	<input type="checkbox" >服兵役        
            	<input type="checkbox" >出国（境）       
            	<input type="checkbox" >其他
           	</td>
            </tr>
        </table></td>
	  </tr>
	  
	  <tr>
		<td>
		<table width="645px" height="70px" border="0" cellpadding="0" cellspacing="0">
		  <tr>
			<td class="tdc" width="174">申请方式</td>
			<td class="tdc" colspan="6">
				<s:if test="#request.ezDrvLiceChanApp.sqfs=='本人申请'">
					<input type="checkbox" checked="checked">本人申请  
					<input type="checkbox" >监护人申请 
					<input type="checkbox">委托他人申请
					<input class="text1" style="border-bottom:1px solid #333333;" name="Input" value=${ezDrvLiceChanApp.xm } type="text" /> 代理申请
				</s:if>
				<s:elseif test="#request.ezDrvLiceChanApp.sqfs=='监护人申请 '">
					<input type="checkbox">本人申请  
					<input type="checkbox"  checked="checked">监护人申请 
					<input type="checkbox">委托他人申请
					<input class="text1" style="border-bottom:1px solid #333333;" name="Input" value=${ezDzwt.wtXm } type="text" /> 代理申请
				</s:elseif>
				<s:elseif test="#request.ezDrvLiceChanApp.sqfs=='委托他人申请' ">
					<input type="checkbox">本人申请  
					<input type="checkbox" >监护人申请 
					<input type="checkbox" checked="checked">委托他人申请
					<input class="text1" style="border-bottom:1px solid #333333;" name="Input" value=${ezDzwt.wtXm } type="text" /> 代理申请
				</s:elseif>
				<s:else>
					<input type="checkbox">本人申请  
					<input type="checkbox" >监护人申请 
					<input type="checkbox">委托他人申请
					<input class="text1" style="border-bottom:1px solid #333333;" name="Input" type="text" /> 代理申请
				</s:else>
			  </td>
			</tr>
		  	<s:if test="#request.ezDrvLiceChanApp.sqfs=='本人申请'">
			    <tr>
					<td  class="tdc" rowspan="2">委托代理人<br />
					  监护人信息</td>
					<td class="tdc" width="152">代理人/监护人姓名</td>
					<td width="107">&nbsp;</td>
					<td class="tdc" width="121">身份证明名称</td>
					<td width="121">&nbsp;</td>
					<td class="tdc" width="117">身份证明号  码</td>
					<td width="193">&nbsp;</td>
				  </tr>
				  <tr>
					<td class="tdc">联系地址</td>
					<td colspan="3">&nbsp;</td>
					<td class="tdc">联系电话</td>
					<td>&nbsp;</td>
				  </tr>
			</s:if>
			<s:else>
				<tr>
					<td  class="tdc" rowspan="2">委托代理人<br />
					  监护人信息</td>
					<td class="tdc" width="152">代理人/监护人姓名</td>
					<td width="107">&nbsp;${ezDrvLiceChanApp.dlrxm }</td>
					<td class="tdc" width="121">身份证明名称</td>
					<td width="121">&nbsp;${ezDrvLiceChanApp.dlrsfzmmc }</td>
					<td class="tdc" width="117">身份证明号 码</td>
					<td width="193">&nbsp;${ezDrvLiceChanApp.dlrsfzmhm }</td>
			  </tr>
			  <tr>
				<td class="tdc">联系地址</td>
				<td colspan="3">&nbsp;${ezDrvLiceChanApp.dlrlxdz }</td>
				<td class="tdc">联系电话</td>
				<td>&nbsp;${ezDrvLiceChanApp.dlrlxdh }</td>
			  </tr>
			</s:else>
		 
		</table>

		</td>
	  </tr>
	  
	  <tr>
		<td>
		<table width="645px" height="250px" border="0" cellpadding="0" cellspacing="0">
		  <tr>
			<td class="tdc" width="27" rowspan="3">申告的义务和内容</td>
			<td width="764" rowspan="3"> <p style="font-weight:bold;">机动车驾驶证申请人应当如实申告是否具有下列不准申请的情形：</p>
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
			<td class="tdc" width="194" height="30">申请人及代理人对申请材料的真实有效性负责。</td>
		  </tr>
		  <tr>
			<td class="tdc" height="60"><p style="padding:0;">申请人签字：
			  </p>
			  <br /> 
			   <s:if test="#request.ezDrvLiceChanApp.sqfs!='本人申请">
			  	<font style="font-family:楷体_GB2312;font-size: 16px; ">${ezDrvLiceChanApp.dlrxm}</font>
			  </s:if>
			  <s:else>
			  	 <font style="font-family:楷体_GB2312;font-size: 16px; ">${ezDrvLiceChanApp.xm}</font>
			  </s:else>
			  <br />
			  <br />
			  <p>&nbsp;&nbsp;&nbsp;&nbsp;<s:date name="#request.date" format="yyyy年MM月dd日"/></p></td>
		  </tr>
		  <tr>
			<td class="tdc" height="60"><p style="padding:0;">代理人/监护人签字: </p>
			  <br /> 
			  <s:if test="#request.ezDrvLiceChanApp.sqfs!='本人申请">
			  	<font style="font-family:楷体_GB2312;font-size: 16px; ">${ezDrvLiceChanApp.dlrxm}</font>
			  	<br />
			 	 <br />
			  	<s:date name="#request.date" format="yyyy年MM月dd日"/>
			  </s:if>
			   <s:else>
			     <br />
			 	 <br />
			 	 <p>&nbsp;&nbsp;&nbsp;&nbsp;年   月   日</p>
			  </s:else>
			  </td>
		  </tr>
		</table>
		</td>
	  </tr>
	</table>

	</div>

</div>

</body>
</html>
