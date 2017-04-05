$(document).ready(function(){
	initbnt();
	var ddlOrgCom = Cookies.get("ddlOrgCom");
	var dbrddlOrgCom = Cookies.get("dbrddlOrgCom");
	
	if(null != ddlOrgCom && "" != ddlOrgCom){
		$("#ddlOrgCom").val(ddlOrgCom);
	}
	if(null != dbrddlOrgCom && "" != dbrddlOrgCom){
		$("#dbrddlOrgCom").val(dbrddlOrgCom);
	}
	
	$("#ddlOrgCom").change(function(){
		Cookies.set("ddlOrgCom",$(this).val());
	});
	
	$("#dbrddlOrgCom").change(function(){
		Cookies.set("dbrddlOrgCom",$(this).val());
	});
	
	
	
});

function initform(){
	initbnt();
	clearform("1");
}

function closebnt(val){
	$("#" + val).attr("disabled", "disabled");
}

function openbnt(val){
	$("#" + val).removeAttr("disabled");
}

function initbnt(){
	closebnt("zp");
	closebnt("zp3");
	closebnt("zp4");
	closebnt("zp5");
	closebnt("zp6");
	closebnt("zp7");
	closebnt("zp8");
	closebnt("bcbnt");
	closebnt("dybnt");
}
function changedsrsfzmmc(){
	cleardsrxx();
	cleardbrxx();
	//clearzjpt();
	initbnt();
	//隐藏域清空
	$("#chfile1").val("0");
	$("#chfile2").val("0");
	$("#chfile01").val("0");
	$("#chfile02").val("0");
	$("#xczpdate").val();
	$("#zjzpdate").val();
	document.getElementById("img01").src=path+'/images/cp.gif';
	document.getElementById("img02").src=path+'/images/cp.gif';
	document.getElementById("ReadResult2").innerHTML="等待抓拍...";
	var sfzmmc = $("#dsrsfzmmc").find("option:selected").val();
	if(sfzmmc == 'B'){
		$("#dsrrtablesfz").hide();
		$("#dsrtablezzjgz").show();
		//document.getElementById("dsrsfzmhm").disabled = true;
		//document.getElementById("dsrxm").disabled = true;
		//document.getElementById("dsrzzjgdm").disabled = false;
		//document.getElementById("dsrdwmc").disabled = false;
	}else{
		$("#dsrrtablesfz").show();
		$("#dsrtablezzjgz").hide();
		//document.getElementById("dsrsfzmhm").disabled = false;
		//document.getElementById("dsrxm").disabled = false;
		//document.getElementById("dsrzzjgdm").disabled = true;
		//document.getElementById("dsrdwmc").disabled = true;
	}
	//$("#zj").hide();
	//$("#zja").hide();
	var dsrsfzmmc=$("#dsrsfzmmc").val();
		if(dsrsfzmmc=='A'||dsrsfzmmc=='B'){
			document.getElementById("sfrzCjxxb.sfzmmc").value=$("#dsrsfzmmc").val();
			$("#xm").val("");
			$("input[type=radio][name='sfrzCjxxb.xb'][value='1']").attr("checked",true);
			$("#sfzmhm").val("");
			$("#djzsdz").val("");
			$("#txdz").val("");
			$("#jzzhm").val("");
			$("#jzzyxq").val("");
			$("#jzzdz").val("");
			$("#yddh").val("");
		}
}

function changedbrsfzmmc(){
	cleardbrxx();
	var sfzmmc = $("#dbrsfzmmc").find("option:selected").val();
	if(sfzmmc == 'B'){
		$("#dbrtablesfz").hide();
		$("#dbrtablezzjgz").show();
		//document.getElementById("dbrsfzmhm").disabled = true;
		//document.getElementById("dbrxm").disabled = true;
		//document.getElementById("dbrzzjgdm").disabled = false;
		//document.getElementById("dbrdwmc").disabled = false;
	}else{
		$("#dbrtablesfz").show();
		$("#dbrtablezzjgz").hide();
		//document.getElementById("dbrsfzmhm").disabled = false;
		//document.getElementById("dbrxm").disabled = false;
		//document.getElementById("dbrzzjgdm").disabled = true;
		//document.getElementById("dbrdwmc").disabled = true;
	}
}


function clearform(val){
	if(val == "1"){
		$("#sfzmmc").val('A');
		$("#dsrsfzmmc").val('A');
		$("#dbrsfzmmc").val('A');
		$("input[type=radio][name='sfrzCjxxb.rzjs'][value='1']").attr("checked",true);
		$("input[type=radio][name='sfrzCjxxb.xb'][value='1']").attr("checked",true);
		$("input[type=radio][name='sfrzCjxxb.sfsh'][value='1']").attr("checked",true);
		$("#dwtd").hide();
		$("#dbtd").hide();
		//隐藏域清空
		$("#chfile1").val("0");
		$("#chfile2").val("0");
		$("#chfile01").val("0");
		$("#chfile02").val("0");
		$("#synFlag").val("");
		
		$('#fingerOne').val("");
		$('#fingerThree').val("");
		$('#fingerTwo').val("");
		$('#fingerFour').val("");
		
		
		//clearywlxandyy();
		document.getElementById("img01").src=path+'/images/cp.gif';
		document.getElementById("img02").src=path+'/images/cp.gif';
		document.getElementById("img05").src=path+'/images/cp.gif';
		document.getElementById("ReadResult2").innerHTML="等待抓拍...";
//		document.getElementById("file01").outerHTML = document.getElementById("file01").outerHTML;
//		document.getElementById("file02").outerHTML = document.getElementById("file02").outerHTML;
//		document.getElementById("file02").outerHTML = document.getElementById("file05").outerHTML;
		//clearzjpt();
	}
	cleardsrxx();
	cleardbrxx();
	Button3_onclick();
	$("#sfzmhm").val("");
	$("#xm").val("");
	$("#gj").val("156");
	$("#mz").val("");
	$("#jg").val("");
	$("#yddh").val("");
	$("#txdz").val("");
	$("#dzyx").val("");
	$("#gddh").val("");
	$("#wx").val("");
	$("#wb").val("");
	$("#qq").val("");
	
	$("#xphz").val("");
	$("#sfzmhmyxq").val("");
	$("#djzsdz").val("");
	$("#jzzhm").val("");
	$("#jzzyxq").val("");
	$("#jzzdz").val("");
	
	$("#xczpdate").val("");
	$("#zjzpdate").val("");
	$("#zjzpdate1").val("");
	$("#zjzpdate2").val("");
	$("#zjzpdate3").val("");
	$("#zjzpdate4").val("");
	$("#zjzpdate5").val("");
	$("#zjzpdate6").val("");
	$("#zjzpdate7").val("");
	$("#rzly").val("B");
	$("#chkdy").val("0");
	$("#chkpic").val("0");
	//$("#xphztdid").html("");
	$("#datableid").html("");
	$("#datableid2").html("");
	$("#datableid3").html("");
	$("#xztableid").html("");
	var inittr = "<tr class='tr1'><th style='width: 10%;' align='center'>序号</th><th style='width: 40%;' align='center'>身份证号码</th><th style='width: 20%;' align='center'>姓名</th><th style='width: 15%;' align='center'>操作</th></tr>";
	$("#xztableid").append(inittr);
}

function clearform2(){
	$("#dsrsfzmmc").val('A');
	$("#dbrsfzmmc").val('A');
	$("input[type=radio][name='dbZjxxb.bllx'][value='1']").attr("checked",true);
	//代办人身份验证和机构验证框隐藏
	$("#dbrsfzmtd").hide();
	$("#tddbrzjgsfzm").hide();
	//身份证和机构隐藏和显示
	$("#dsrrtablesfz").show();
	$("#dsrtablezzjgz").hide();
	$("#dbrtablesfz").show();
	$("#dbrtablezzjgz").hide();
	//隐藏域清空
	$("#chfile1").val("0");
	$("#chfile2").val("0");
	$("#chfile01").val("0");
	$("#chfile02").val("0");
	
	document.getElementById("img01").src=path+'/images/cp.gif';
	document.getElementById("img02").src=path+'/images/cp.gif';
	document.getElementById("ReadResult2").innerHTML="等待抓拍...";
	//document.getElementById("file01").outerHTML = document.getElementById("file01").outerHTML;
	//document.getElementById("file02").outerHTML = document.getElementById("file02").outerHTML;
	cleardsrxx();
	cleardbrxx();
	clearzjpt();
	$("#xczpdate").val("");
	$("#zjzpdate").val("");
	//$("#zj").hide();
	//$("#zja").hide();
}

/**
 * 清空当事人信息
 */
function cleardsrxx(){
	//身份证信息
	$("#dsrSfzCardname1").val("");
	$("#dsrSfzCardsex1").val("");
	$("#dsrSfzCardno1").val("");
	$("#dsrSfzCardaddress1").val("");
	$("#dsrsfzmhm").val("");
	$("#dsrxm").val("");
	//机构证信息
	$("#dsrZzjgZh1").val("");
	$("#dsrZzjgFrdb1").val("");
	$("#dsrZzjgYyzz1").val("");
	$("#dsrZzjgDwmc1").val("");
	$("#dsrZzjgNjrq1").val("");
	$("#dsrZzjgNjyxq1").val("");
	$("#dsrZzjgDz1").val("");
	$("#dsrzzjgdm").val("");
	$("#dsrdwmc").val("");
	document.getElementById("sfzxpimgid").src=path+'/images/cp.gif';
	document.getElementById("file1").outerHTML = document.getElementById("file1").outerHTML;
	document.getElementById("ReadResult").innerHTML="等待读取...";
	document.getElementById("ReadResultj").innerHTML="等待读取...";
}

/**
 * 清空代办人信息
 */
function cleardbrxx(){
	//身份证信息
	$("#dbrSfzCardname1").val("");
	$("#dbrSfzCardsex1").val("");
	$("#dbrSfzCardno1").val("");
	$("#dbrSfzCardaddress1").val("");
	$("#dbrsfzmhm").val("");
	$("#dbrxm").val("");
	//机构证信息
	$("#dbrZzjgZh1").val("");
	$("#dbrZzjgFrdb1").val("");
	$("#dbrZzjgYyzz1").val("");
	$("#dbrZzjgDwmc1").val("");
	$("#dbrZzjgNjrq1").val("");
	$("#dbrZzjgNjyxq1").val("");
	$("#dbrZzjgDz1").val("");
	$("#dbrzzjgdm").val("");
	$("#dbrdwmc").val("");
	document.getElementById("sfzxpimgid2").src=path+'/images/cp.gif';
	document.getElementById("file2").outerHTML = document.getElementById("file2").outerHTML;
	document.getElementById("ReadResult1").innerHTML="等待读取...";
	document.getElementById("ReadResultj2").innerHTML="等待读取...";
}

/**
 * 清空图片信息
 */
function clearzjpt(){
	for(var i = 3;i < 10; i++){
		document.getElementById("img0"+i).src=path+'/images/cp.gif';
		document.getElementById("img0"+i+"a").src=path+'/images/cp.gif';
		$("#file0"+i).val("");
		document.getElementById("file0"+i).outerHTML = document.getElementById("file0"+i).outerHTML;
		var n = i - 2;
		$("#zjzpdate"+n).val("");
	}
}


//切换表格
function settab(obj,n){
	var tds = $("#test2_li_now_ li");
	for(var i=0;i < tds.length; i++){ 
		tds[i].className=i==n?"now":"";
		$("#tableid" + i).hide();
	}
	$("#tableid" + n).show();
}

function subforms(){
	//身份证明号码
	var sfzmhm = checknotnull(document.getElementById("sfzmhm"),'请输入身份证明号码或者组织机构代码证号');
	if (sfzmhm != "true") {
		return false;
	}
	
	//姓名
	var xm = checknotnull(document.getElementById("xm"),'请输入姓名或者单位名称');
	if (xm != "true") {
		return false;
	}
	
	//移动电话
	var yddh = document.getElementById("yddh");
	var ccid=$("#ccid").val();
	var yddh_result = checknotnull(yddh,'请输入移动电话');
	if (yddh_result != "true") {
		return false;
	}else{
		clearallspace(yddh);
	}
	var xy1=/^[a-zA-Z0-9]{8}-[a-zA-Z0-9]{1}$/;
	if($("#sfzmmc").val()=='B'||$("#dsrsfzmmc").val()=='B'){
		var hm1=$("#dsrZzjgZh1").val();
		var mc1=$("#sfzmhm").val();
		if(!hm1.match(xy1)){
			alert("当事人组织机构代码证格式不正确");
			 return false;
		}
		if(!mc1.match(xy1)){
			alert("组织机构代码证格式不正确");
			return false;
		}
	}
	//dsrsfzmmc
	var xy=/^(\d{18}$|^\d{17}(\d|X|x))$/;
	if($("#dsrsfzmmc").val()=='A'||$("#dsrsfzmmc").val()=='M'){
		var hm=$("#dsrSfzCardno1").val();
		if(!hm.match(xy)){
			alert("当事人身份证明号码格式不正确");
			 return false;
		}
	}
	if($("#sfzmmc").val()=='A'||$("#sfzmmc").val()=='M'){
		var mc=$("#sfzmhm").val();
		if(!mc.match(xy)){
			alert("当事人身份证明号码格式不正确");
			return false;
		}
	}
	var mobileTest = /^0?1[345789]\d{9}$/;
	if(!yddh.value.match(mobileTest)){
	    alert("移动电话格式不正确!");
	    yddh.focus();
	    return false;
    }
	//通信地址
	var txdz = checknotnull(document.getElementById("txdz"),'请输入通信地址');
	if (txdz != "true") {
		return false;
	}
	
	//登记住所地址
	var djzsdz = checknotnull(document.getElementById("djzsdz"),'请输入登记住所地址');
	if (djzsdz != "true") {
		return false;
	}
	
	//相片回执
	//var xphz = checknotnull(document.getElementById("xphz"),"请输入身份证明号码后，点击'查询档案'按钮选择一个相片回执!");
	//if (xphz != "true") {
	//	return false;
	//}
	//var chkpic = $("#chkpic").val();
	//if(chkpic == '0'){
	//	alert('请点击调取相片按钮查询是否该相片回执有相片!');
	//	return false;
	//}
	
	var jsrz=$("input[type=radio][name='sfrzCjxxb.rzjs'][checked]").val();
	
	/*//居住证信息验证
	var sfsh = $("input[type=radio][name='sfrzCjxxb.sfsh'][checked]").val();
	if("2" == sfsh){
		//居住证号码
		var jzzhm = checknotnull(document.getElementById("jzzhm"),"非深户请输入居住证号码!");
		if (jzzhm != "true") {
			return false;
		}
		//居住证有效期
		var jzzyxq = checknotnull(document.getElementById("jzzyxq"),"非深户请选择居住证有效期!");
		if (jzzyxq != "true") {
			return false;
		}
		//居住证地址
		var jzzdz = checknotnull(document.getElementById("jzzdz"),"非深户请输入居住证地址!");
		if (jzzdz != "true") {
			return false;
		}
	}*/
	
	//验证身份证图片路劲
	var sfzmmc = $("#dsrsfzmmc").val();
	var idno = $("#dsrSfzCardno1").val();
	var zhno = $("#dsrZzjgZh1").val();
	if(sfzmmc == 'A' && idno != null && idno != ''){
		var file1 = $("#file1").val();
		if(file1 != "C:\\jjywslg_sfz1\\zp.bmp"){
			alert("当事人身份证图片路径读取失败!请读取后点击浏览选择C:\\jjywslg_sfz1\\zp.bmp");
			//settab(this,0);
			return false;
		}
	}else if(sfzmmc == 'B' && zhno != null && zhno != ''){
		
	}
	//else{
	//	alert("请读取当事人身份证件信息!");
		//settab(this,0);
	//	return false;
	//}
	
	
	//验证高拍仪和摄像头拍摄
	var file01 = $("#file01").val();
	var file02 = $("#file02").val();
	var chfile01 = $("#chfile01").val();
	var chfile02 = $("#chfile02").val();
	if(chfile01 == "1" && chfile02 == "1"){
		if(file01 != "C:\\printtx.jpg" || file02 != "C:\\printzj.jpg"){
			alert("高拍仪和摄像头拍摄图片路径读取失败!请抓拍图片后点击浏览摄像头图片选择C:\\printtx.jpg文件,证件正面图片（身份证、居住证、驾驶证、行驶证）图片选择C:\\printzj.jpg文件!");
			//settab(this,0);
			return false;
		}
	}else{
		alert("请点击'抓拍图片'按钮拍摄当事人照片和证件照片!");
		//settab(this,0);
		return false;
	}
	
	var fso = new ActiveXObject("Scripting.FileSystemObject");
	var f1 = fso.GetFile("C:\\printtx.jpg");
	var f2 = fso.GetFile("C:\\printzj.jpg");
	var f3, f4, f5, f6, f7, f8, f9;
	
	var d01 = $("#xczpdate").val();
	var d02 = $("#zjzpdate").val();
	var d03 = $("#zjzpdate1").val();
	var d04 = $("#zjzpdate2").val();
	var d05 = $("#zjzpdate3").val();
	var d06 = $("#zjzpdate4").val();
	var d07 = $("#zjzpdate5").val();
	var d08 = $("#zjzpdate6").val();
	var d09 = $("#zjzpdate7").val();
	var finger1=$('#fingerOne').val();
	var finger2=$('#fingerThree').val();
	var finger3=$('#fingerTwo').val();
	var finger4=$('#fingerFour').val();
	var tranFlag=$('#loginUser').val();
	if(d03 != null && d03 != ""){
		f3 = fso.GetFile("C:\\printzj1.jpg");
	}
	if(d04 != null && d04 != ""){
		f4 = fso.GetFile("C:\\printzj2.jpg");
	}
	if(d05 != null && d05 != ""){
		f5 = fso.GetFile("C:\\printzj3.jpg");
	}
	if(d06 != null && d06 != ""){
		f6 = fso.GetFile("C:\\printzj4.jpg");
	}
	if(d07 != null && d07 != ""){
		f7 = fso.GetFile("C:\\printzj5.jpg");
	}
	if(d08 != null && d08 != ""){
		f8 = fso.GetFile("C:\\printzj6.jpg");
	}
	if(d09 != null && d09 != ""){
		f9 = fso.GetFile("C:\\printzj7.jpg");
	}
	var f1d, f2d, f3d, f4d, f5d, f6d, f7d, f8d, f9d;
	f1d = f1.DateLastModified;
	f2d = f2.DateLastModified;
	if(f3 != null){
		f3d = f3.DateLastModified;
	}
	if(f4 != null){
		f4d = f4.DateLastModified;
	}
	if(f5 != null){
		f5d = f5.DateLastModified;
	}
	if(f6 != null){
		f6d = f6.DateLastModified;
	}
	if(f7 != null){
		f7d = f7.DateLastModified;
	}
	if(f8 != null){
		f8d = f8.DateLastModified;
	}
	if(f9 != null){
		f9d = f9.DateLastModified;
	}
	var d1 = new Date(f1d);
	var d2 = new Date(f2d);
	var d3, d4, d5, d6, d7, d8, d9;
	if(f3d != null){
		d3 = new Date(f3d);
	}
	if(f4d != null){
		d4 = new Date(f4d);
	}
	if(f5d != null){
		d5 = new Date(f5d);
	}
	if(f6d != null){
		d6 = new Date(f6d);
	}
	if(f7d != null){
		d7 = new Date(f7d);
	}
	if(f8d != null){
		d8 = new Date(f8d);
	}
	if(f9d != null){
		d9 = new Date(f9d);
	}
	
	if(d1.getTime() != d01){
		alert('现场照片存在异常!');
		return false;
	}else if(d2.getTime() != d02){
		alert('证件正面图片（身份证、居住证、驾驶证、行驶证）存在异常!');
		return false;
	}else{
		/*if(d3 != null && d03 != null && d03 != ""){
			if(d3.getTime() != d03){
				alert('驾驶证照片存在异常!');
				return false;
			}
			if(!checktime(d3)){
				alert('驾驶证照片存在异常!');
				return false;
			}
		}
		if(d4 != null && d04 != null && d04 != ""){
			if(d4.getTime() != d04){
				alert('行驶证照片存在异常!');
				return false;
			}
			if(!checktime(d4)){
				alert('行驶证照片存在异常!');
				return false;
			}
		}
		if(d7 != null && d07 != null && d07 != ""){
			if(d7.getTime() != d07){
				alert('身份证件反面照片存在异常!');
				return false;
			}
			if(!checktime(d7)){
				alert('身份证件反面照片存在异常!');
				return false;
			}
		}
		if(d8 != null && d08 != null && d08 != ""){
			if(d8.getTime() != d08){
				alert('居住证正面照片存在异常!');
				return false;
			}
			if(!checktime(d8)){
				alert('居住证正面照片存在异常!');
				return false;
			}
		}
		if(d9 != null && d09 != null && d09 != ""){
			if(d9.getTime() != d09){
				alert('居住证反面照片存在异常!');
				return false;
			}
			if(!checktime(d9)){
				alert('居住证反面照片存在异常!');
				return false;
			}
		}*/
		if(d5 != null && d05 != null && d05 != ""){
			if(d5.getTime() != d05){
				alert('面签照片存在异常!');
				return false;
			}
			if(!checktime(d5)){
				alert('面签照片存在异常!');
				return false;
			}
		}
		if(d6 != null && d06 != null && d06 != ""){
			if(d6.getTime() != d06){
				alert('手写签名图片存在异常!');
				return false;
			}
			if(!checktime(d6)){
				alert('手写签名图片存在异常!');
				return false;
			}
		}else{
			alert('请用签名版签名并保存图像!');
			return false;
		}
		if('5'==jsrz){
			if(finger1==""||finger2==""||finger3==""||finger4==""){
				alert("指纹采集失败或丢失，请重新采集指纹");
				document.getElementById("bc").disabled=false;
				return false;
			}		
		}	
		if(checktime(d1) && checktime(d2)){
			var chkdy = $("#chkdy").val();
			if(chkdy == '0'){
				alert('必须要打印申请表给申请人签字并拍照后才能保存,请点击打印按钮!');
				return false;
			}
			if(d5 != null && d05 != null && d05 != ""){
				
			}else{
				alert('请拍摄面签照片!');
				return false;
			}
			if((d03 != null && d03 != "") || (d04 != null && d04 != "") || (d05 != null && d05 != "") || (d06 != null && d06 != "")){
				if(confirm("证件信息较多，上传速度较慢,请耐心等候!")){
					var imgurl = path+'/artDialog4.1.6/skins/icons/loading2.gif';
					chuli = art.dialog({
					    content: '<div style=\"text-align:center; text-indent:-999em; overflow:hidden; background:url('+imgurl+') no-repeat center center;\"><span>loading..</span></div><br/>数据处理中,请稍候.....',
					    title: '数据处理中',
						lock: true,
					    opacity: 0.87
					});
					sfrz_form.submit();
				}
			}else{
				var imgurl = path+'/artDialog4.1.6/skins/icons/loading2.gif';
				chuli = art.dialog({
				    content: '<div style=\"text-align:center; text-indent:-999em; overflow:hidden; background:url('+imgurl+') no-repeat center center;\"><span>loading..</span></div><br/>数据处理中,请稍候.....',
				    title: '数据处理中',
					lock: true,
				    opacity: 0.87
				});
				sfrz_form.submit();
			}
			
		}else{
			alert('拍摄照片存在异常!');
			return false;
		}
	}
}

function dysqb(){
	var fso = new ActiveXObject("Scripting.FileSystemObject");
	var f6;
	
	var d06 = $("#zjzpdate4").val();
	
	if(d06 != null && d06 != ""){
		f6 = fso.GetFile("C:\\printzj4.jpg");
	}
	var f6d;
	if(f6 != null){
		f6d = f6.DateLastModified;
	}
	var d6;
	if(f6d != null){
		d6 = new Date(f6d);
	}
	if(d6 != null && d06 != null && d06 != ""){
		if(d6.getTime() != d06){
			alert('手写签名图片存在异常!');
			return false;
		}
		if(!checktime(d6)){
			alert('手写签名图片存在异常!');
			return false;
		}
	}else{
		alert('请用签名版签名并保存图像后在打印申请表!');
		return false;
	}
	
	var sfzmhm = $("#sfzmhm").val();
	var sfzmmc = $("#sfzmmc").val();
	var xb = $("input:radio[name='sfrzCjxxb.xb'][checked]").val();
	var xm = $("#xm").val();
	var gj = $("#gj").val();
	var mz = $("#mz").val();
	var jg = $("#jg").val();
	var yddh = $("#yddh").val();
	var txdz = $("#txdz").val();
	var dzyx = $("#dzyx").val();
	var gddh = $("#gddh").val();
	var wx = $("#wx").val();
	var wb = $("#wb").val();
	var qq = $("#qq").val();
	
	
	sfzmhm = encodeURI(sfzmhm);
	sfzmmc = encodeURI(sfzmmc);
	xm = encodeURI(xm);
	gj = encodeURI(gj);
	mz = encodeURI(mz);
	jg = encodeURI(jg);
	yddh = encodeURI(yddh);
	txdz = encodeURI(txdz);
	dzyx = encodeURI(dzyx);
	gddh = encodeURI(gddh);
	wx = encodeURI(wx);
	wb = encodeURI(wb);
	qq = encodeURI(qq);
	
	window.open("sfrz_initPrint.action?sfzmhm=" + sfzmhm + "&sfzmmc=" + sfzmmc + "&xb=" + xb + "&xm=" + xm + "&gj=" + gj + "&mz=" + mz + "&jg=" + jg + "&yddh=" + yddh + "&txdz=" + txdz + "&dzyx=" + dzyx + "&gddh=" + gddh + "&wx=" + wx + "&wb=" + wb + "&qq=" + qq);
	$("#chkdy").val("1");
	openbnt("zp5");
}

function checktime(date){
	var d = new Date();
	var year = d.getFullYear();
	var month = d.getMonth() + 1;
	var day = d.getDate();
	var hour = d.getHours();
	var nowtime = year + '-' + month + '-' + day + ' ' + hour;
	var year2 = date.getFullYear();
	var month2 = date.getMonth() + 1;
	var day2 = date.getDate();
	var hour2 = date.getHours();
	var nowtime2 = year2 + '-' + month2 + '-' + day2 + ' ' + hour2;
	if(nowtime == nowtime2){
		return true;
	}else{
		return false;
	}
}

//页面重新加载
function reonload(){
	window.location.reload();
}

function closechuli(){
	chuli.close();
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