
function getEsvehflowandvehicle(){
	var imgurl = path+'/artDialog4.1.6/skins/icons/loading2.gif';
	chuli = art.dialog({
	    content: '<div style=\"text-align:center; text-indent:-999em; overflow:hidden; background:url('+imgurl+') no-repeat center center;\"><span>loading..</span></div><br/>数据处理中,请稍候.....',
	    title: '数据处理中',
		lock: true,
	    opacity: 0.87
	});
	var lsh = $("#lsh").val();
	if(lsh == null || lsh ==""){
		closechuli();
		alert("请输入流水号");
		return false;
	}
	var pageforward = $("#pageforward").val();
	if(pageforward == 'zchf'){
		var vehflow = getEsvehflow(lsh);
		if(vehflow == "error"){
			closechuli();
			return false;
		}
		if(vehflow == "-1" || vehflow == null || vehflow == "null"){
			closechuli();
			alert("该流水号不存在!");
			return false;
		}
		//判断业务类型是否正确
		if(vehflow[2] != "B" && vehflow[2] != "G" && vehflow[2] != 'D'){
			closechuli();
			alert("此流水号非转出/注销业务，不能做受理!");
			return false;
		}
	
		var vehicle = getEehicle(vehflow[6], vehflow[5]);
		if(vehicle == "error"){
			closechuli();
			return false;
		}
		
		if(vehicle == "-1" || vehicle == null || vehicle == "null"){
			closechuli();
			alert("该流水号的车辆信息不存在!");
			return false;
		}
		
		//设置业务类型
		if(vehflow[18] == null || vehflow[18] == ""){
			$("#ywlxtext").val(vehflow[2]);
		}else{
			$("#ywlxtext").val(vehflow[18]);
		}
		$("#ywlx").val('U');
		$("#ywlxtext").val('转出/注销恢复');
		$("#vehflowywlx").val(vehflow[2]);
		$("#vehflowywyy").val(vehflow[3]);
		
		$("#hphm").val(vehflow[6]);
		$("#hpzl").val(vehflow[5]);
		$("#hphm").attr("readonly", "readonly");
		$("#hpzl").attr("disabled", "disabled");
		//流水信息
		$("#syr").val(vehflow[4]);
		$("#clpp1").val(vehflow[7]);
		$("#clxh").val(vehflow[8]);
		$("#clsbdh").val(vehflow[17]);
		$("#lshval").val(vehflow[0]);
		//机动车信息
		$("#vehi_clsbdh").val(vehicle[4]);
		$("#vehi_sfzmhm").val(vehicle[2]);
		$("#vehi_czmc").val(vehicle[3]);
		$("#vehi_cllx").val(vehicle[5]);
		$("#vehi_cp").val(vehicle[6]);
	}else if(pageforward == 'tbyw'){
		var vehflow = getDbjgzjxx(lsh);
		if(vehflow == "error"){
			closechuli();
			return false;
		}
		if(vehflow == "-1" || vehflow == null || vehflow == "null"){
			closechuli();
			alert("该流水号不存在!");
			return false;
		}
		
		//验证指标信息是否存在
		if(vehflow.zblx == null || vehflow.zblx == "" || vehflow.zbh == null || vehflow.zbh == ""){
			closechuli();
			alert("该流水不含指标信息，不属于流水指标退办数据!");
			return false;
		}
		
		//判断业务类型是否正确
		if((vehflow.ywlx != "A" && vehflow.ywyy != "A:A") 
		  && (vehflow.ywlx != "B" && vehflow.ywyy != "B:B") 
		  && (vehflow.ywlx != "D" && vehflow.ywyy != "D:I" && vehflow.ywyy != "D:J") 
		  && vehflow.ywlx != "I"){
			closechuli();
			alert("此流水号非退办业务，不能做受理!");
			return false;
		}
		
		//判断是否是本部门
		var issamedept = getIsSamedept(userdept, vehflow.lrbmdm);
		if(issamedept == "error"){
			closechuli();
			return false;
		}
		if(issamedept != '1'){
			closechuli();
			alert("本部门才有权限退办本部门的业务数据!");
			return false;
		}
		
		//验证流水状态是否是退办
		var flowinfo = getEsvehflow(lsh);
		if(flowinfo == "error"){
			closechuli();
			return false;
		}
		if(flowinfo == "-1" || flowinfo == null || flowinfo == "null"){
			closechuli();
			alert("该流水号不存在!");
			return false;
		}
		if(flowinfo[15] != "Q"){
			closechuli();
			alert("该流水在六合一系统没有做退办，请先在六合一系统对该流水进行退办操作!");
			return false;
		}
	
		var vehicle = getEehicle(vehflow.hphm, vehflow.hpzl);
		if(vehicle == "error"){
			closechuli();
			return false;
		}
		
//		if(vehicle == "-1" || vehicle == null || vehicle == "null"){
//			closechuli();
//			alert("该流水号的车辆信息不存在!");
//			return false;
//		}
		
		//设置业务类型
		$("#ywlx").val('TB');
		$("#ywlxtext").val('退办');
		$("#vehflowywlx").val(vehflow.ywlx);
		$("#vehflowywyy").val(vehflow.ywyy);
		
		$("#hphm").val(vehflow.hphm);
		$("#hpzl").val(vehflow.hpzl);
		$("#hphm").attr("readonly", "readonly");
		$("#hpzl").attr("disabled", "disabled");
		$("#lshval").val(vehflow.lsh);
		//证件受理信息
		$("#zjxxb_lsh").val(vehflow.lsh);
		$("#zjxxb_dsrsfzmhm").val(vehflow.dsrsfzmhm);
		$("#zjxxb_dsrxm").val(vehflow.dsrxm);
		$("#zjxxb_dsrlxdh").val(vehflow.dsrLxdh);
		$("#zjxxb_dsrlxdz").val(vehflow.dsrLxdz);
		$("#zjxxb_zblx").val(vehflow.zblx);
		$("#zjxxb_zbh").val(vehflow.zbh);
		$("#zjxxb_tyblsh").val(vehflow.tyblsh);
		$("#zjxxb_clsbdh").val(vehflow.clsbdh);
		//机动车信息
		$("#vehi_clsbdh").val(vehicle[4]);
		$("#vehi_sfzmhm").val(vehicle[2]);
		$("#vehi_czmc").val(vehicle[3]);
		$("#vehi_cllx").val(vehicle[5]);
		$("#vehi_cp").val(vehicle[6]);
		
		
	}else{
		closechuli();
		alert("此业务不能在此受理");
		return false;
	}
	
	
	closechuli();
}

/**
 * 获取车辆档案信息
 * @param {Object} hphm
 * @param {Object} hpzl
 * @return {TypeName} 
 */
function getEehicle(hphm, hpzl){
	var obj = "";
	$.ajax({
			cache:false,
			async:false,
			url:path+"/veh_ajax/vehAjax_getEsvehicle.action",
			type:'post',
			data:{"hphm":hphm, "hpzl":hpzl},
			dataType:'json',
			error:function(XmlHttpRequest,textStatus, errorThrown){
				exception(XmlHttpRequest.responseText);
				obj = "error";
			},
			success: function(result){
				var message = result+"";
				if(message.indexOf('异常信息') == -1){
					obj = result;
				}else{
					obj = 'error';
					exception(message);
				}
			}
	 });
	return obj;
}

//获取统一版流水号信息
function getEsvehflow(lsh){
	var obj = "";
	$.ajax({
		cache:false,
		async:false,
		url:path+'/veh_ajax/vehAjax_getEsvehflow.action',
		type:'post',
		data:{"lsh":lsh},
		dataType:'json',
		error:function(XmlHttpRequest,textStatus, errorThrown){
			exception(XmlHttpRequest.responseText);
			obj = "error";
		},
		success: function(result){
			var message = result+"";
			if(message.indexOf('异常信息') == -1){
				obj = result;
			}else{
				obj = 'error';
				exception(message);
			}
		}
	});
	return obj;
}

//查询机动车受理信息
function getDbjgzjxx(tyblsh){
	var obj = "";
	$.ajax({
		cache:false,
		async:false,
		url:path+'/veh_ajax/vehAjax_getDbjgzjxxInfo.action',
		type:'post',
		data:{"tyblsh":tyblsh},
		dataType:'json',
		error:function(XmlHttpRequest,textStatus, errorThrown){
			exception(XmlHttpRequest.responseText);
			obj = "error";
		},
		success: function(result){
			var message = result+"";
			if(message.indexOf('异常信息') == -1){
				obj = result;
			}else{
				obj = 'error';
				exception(message);
			}
		}
	});
	return obj;
}

//判断是否是本部门
function getIsSamedept(userdept, datadept){
	var obj = "";
	$.ajax({
		cache:false,
		async:false,
		url:path+'/veh_ajax/vehAjax_getIsSamedept.action',
		type:'post',
		data:{"dqyhbmid":userdept, "databmid":datadept},
		dataType:'json',
		error:function(XmlHttpRequest,textStatus, errorThrown){
			exception(XmlHttpRequest.responseText);
			obj = "error";
		},
		success: function(result){
			var message = result+"";
			if(message.indexOf('异常信息') == -1){
				obj = result;
			}else{
				obj = 'error';
				exception(message);
			}
		}
	});
	return obj;
}

function subzchfforms(){
//	//1、验证指标
//	//1.1、验证是否需要输入指标类型和指标号
//	var isshuruzb = checkIsneedzb(hphmval, hpzlval, ywlx, ywyy, dsrsfzmhmblack, '');
//	if(isshuruzb == "error"){
//		return false;
//	}
//	var arrs = isshuruzb.split("+");
//	if(arrs[0] == '-1'){
//		alert("验证指标失败");
//		return false;
//	}
//	$("#alterinfo").val(arrs[11]);
//	$("#zbyzflag").val(arrs[7]);
//	if(arrs[0] == "1" && arrs[1] == "1"){
//		var zjxxblsh = getZjxxblsh();
//		if(zjxxblsh == "error"){
//			alert("流水号生成失败,请重新保存");
//			return false;
//		}else{
//			$("#zjxxblsh").val(zjxxblsh);
//			alertdiv(arrs[2], arrs[3], arrs[4], arrs[5], arrs[6], arrs[8], arrs[9], arrs[10]);
//			return false;
//		}
//	}else if(arrs[0] == "1" && arrs[1] == "0"){
//		alertdivlsh(arrs[5]);
//	}else{
//		var imgurl = path+'/artDialog4.1.6/skins/icons/loading2.gif';
//		chuli = art.dialog({
//		    content: '<div style=\"text-align:center; text-indent:-999em; overflow:hidden; background:url('+imgurl+') no-repeat center center;\"><span>loading..</span></div><br/>数据处理中,请稍候.....',
//		    title: '数据处理中',
//			lock: true,
//		    opacity: 0.87
//		});
//		$("#hpzl").removeAttr("disabled");
//		$("#drv_form").attr("action", path+"/veh/veh_editSlgVehXxcj.action");
//		$("#drv_form").attr("target", "abc");
//		$("#drv_form").submit();
//	}
//	return false;
	
	var lsh = $("#lsh").val();
	var lshval = $("#lshval").val();
	if(lsh != null && lsh != "" && lshval != null && lshval != ""){
		if(lsh != lshval){
			alert("流水号已改变，请重新点击‘查询’按钮!");
			return false;
		}
	}
	
	//业务类型
	var ywlx = checknotnull2(document.getElementById("ywlxtext"),'请用流水号调取基础信息',0);
	if (ywlx != "true") {
		return false;
	}
	var vehywyyval = $("#vehflowywyy").val();
	if(vehywyyval != 'A:A'){
		//号牌号码
		var hphm = checknotnull2(document.getElementById("hphm"),'请填写号牌号码',0);
		if (hphm != "true") {
			return false;
		}
		var hphmval = $("#hphm").val();
		if(hphmval == "B" || hphmval.length < 4){
			alert("请填写正确的号牌号码");
			return false;
		}
		//号牌种类
		var hpzlval = $("#hpzl").val();
		var hpzl = checknotnull2(document.getElementById("hpzl"),'请选择号牌种类',0);
		if (hpzl != "true") {
			return false;
		}
	}
	
	//验证身份证图片路劲
	var sfzmmc = $("#dsrsfzmmc").val();
	var idno = $("#dsrSfzCardno1").val();
	if(sfzmmc == 'A' && idno != null && idno != ''){
		var file1 = $("#file1").val();
		if(file1 != "C:\\ycszh_sfz1\\zp.bmp"){
			alert("当事人身份证图片路径读取失败!请读取后点击浏览选择C:\\ycszh_sfz1\\zp.bmp");
			settab(this,0);
			return false;
		}
	}
	var ismyself = $("input:radio[name='dbZjxxb.bllx'][checked]").val();
	var dbrsfzmmc = $("#dbrsfzmmc").val();
	var idno2 = '';
	if(ismyself == '2'){
		//验证代办人身份证读取
		idno2 = $("#dbrSfzCardno1").val();
		if(dbrsfzmmc == 'A' && idno2 != null && idno2 != ''){
			var file2 = $("#file2").val();
			if(file2 != "C:\\ycszh_sfz2\\zp.bmp"){
				alert("代办人身份证图片路径读取失败!请读取后点击浏览选择C:\\ycszh_sfz2\\zp.bmp");
				settab(this,0);
				return false;
			}
		}
	}
	
	//当事人验证身份证明号码
	if(sfzmmc == 'B'){
		var dsrzzjgdm = checknotnull2(document.getElementById("dsrzzjgdm"),'请输入当事人组织机构证号码',0);
		if (dsrzzjgdm != "true") {
			return false;
		}
		var dsrzzjgdm = checknotnull2(document.getElementById("dsrzzjgdm"),'请输入当事人组织机构证单位法人',0);
		if (dsrzzjgdm != "true") {
			return false;
		}
	}else{
		var dsrsfzmhm = checknotnull2(document.getElementById("dsrsfzmhm"),'请输入当事人身份证明号码',0);
		if (dsrsfzmhm != "true") {
			return false;
		}else{
			var dsrsfzmhmval = $("#dsrsfzmhm").val();
			if(sfzmmc == 'A' || sfzmmc == 'M'){
				if(dsrsfzmhmval.length != 18){
					alert("当事人身份证明号码必须为18位!");
					$("#slgDrvXxcjbsfzmhm").focus();
					return false;
				}
			}
//			else{
//				if(dsrsfzmhmval.length > 17){
//					alert("当事人身份证明号码必须小于18位!");
//					$("#slgDrvXxcjbsfzmhm").focus();
//					return false;
//				}
//			}
		}
		//验证姓名
		var dsrxm = checknotnull2(document.getElementById("dsrxm"),'请输入当事人姓名',0);
		if (dsrxm != "true") {
			return false;
		}
	}
	
	if(ismyself == '2'){
		//组织机构证验证
		if(dbrsfzmmc == 'B'){
			var dbrzzjgdm = checknotnull2(document.getElementById("dbrzzjgdm"),'请输入代办人组织机构证号码',0);
			if (dbrzzjgdm != "true") {
				return false;
			}
			var dbrdwmc = checknotnull2(document.getElementById("dbrdwmc"),'请输入代办人组织机构证单位法人',0);
			if (dbrdwmc != "true") {
				return false;
			}
		}else{
			//当事人验证身份证明号码
			var dbrsfzmhm = checknotnull2(document.getElementById("dbrsfzmhm"),'请输入代办人身份证明号码',0);
			if (dbrsfzmhm != "true") {
				return false;
			}else{
				var dbrsfzmhmval = $("#dbrsfzmhm").val();
				if(dbrsfzmmc == 'A' || dbrsfzmmc == 'M'){
					if(dbrsfzmhmval.length != 18){
						alert("代办人身份证明号码必须为18位!");
						$("#slgDrvXxcjbsfzmhm").focus();
						return false;
					}
				}else{
					if(dbrsfzmhmval.length > 17){
						alert("代办人身份证明号码必须小于18位!");
						$("#slgDrvXxcjbsfzmhm").focus();
						return false;
					}
				}
			}
			//验证姓名
			var dbrxm = checknotnull2(document.getElementById("dbrxm"),'请输入代办人姓名',0);
			if (dbrxm != "true") {
				return false;
			}
		}
	}
	
	//验证手机号码
	var dsrLxdh = checknotnull2(document.getElementById("dsrLxdh"),'请输入当事人手机号码',0);
	if (dsrLxdh != "true") {
		return false;
	}else{
		var mobileTest = /^0?1[3578]\d{9}$/;
	    if(!$("#dsrLxdh").val().match(mobileTest)){
	          alert("请正确填写手机号!");
	          var obj = document.getElementById("dsrLxdh");
	          obj.style.borderColor = '#FF0000';
			  obj.focus();
	          return false;
	    }
		
		var sjhm = $("#dsrLxdh").val();
		var resjhm = checkmobile(sjhm);
		if (resjhm != "true") {
			var obj = document.getElementById("dsrLxdh");
	        obj.style.borderColor = '#FF0000';
			obj.focus();
			return false;
		}
	}
	//验证当事人联系地址
	var dsrLxdz = checknotnull2(document.getElementById("dsrLxdz"),'请输入当事人联系地址',0);
	if (dsrLxdz != "true") {
		return false;
	}
	
	//退办原因
	var ywlx = $("#ywlx").val();
	if(ywlx == 'TB'){
		var tbyyval = checknotnull2(document.getElementById("tbyy"),'请输入退办原因',0);
		if (tbyyval != "true") {
			return false;
		}
	}
	
	
	//判断当事人是否与机动车信息当事人是否相同
	var dsrsfzmmc = $("#dsrsfzmmc").val();
	var dsrxm = "";
	if(dsrsfzmmc != "B"){
		dsrxm = $("#dsrsfzmhm").val();
	}else{
		dsrxm = $("#dsrzzjgdm").val();
	}
//	if($.trim($("#vehi_sfzmhm").val()) != dsrxm){
//		closechuli();
//		alert("流水号当事人与窗口受理当事人不同!");
//		return false;
//	}
	
	//验证高拍仪和摄像头拍摄
	var file01 = $("#file01").val();
	var file02 = $("#file02").val();
	var chfile01 = $("#chfile01").val();
	var chfile02 = $("#chfile02").val();
	if(chfile01 == "1" && chfile01 == "1"){
		if(file01 != "C:\\printtx.jpg" || file02 != "C:\\printzj.jpg"){
			alert("高拍仪和摄像头拍摄图片路径读取失败!请抓拍图片后点击浏览摄像头图片选择C:\\printtx.jpg文件,高拍仪图片选择C:\\printzj.jpg文件!");
			settab(this,0);
			return false;
		}
	}else{
		alert("请点击'抓拍图片'按钮拍摄当事人照片和证件照片!");
		settab(this,0);
		return false;
	}
	
	var dsrsfzmhm = $("#dsrsfzmhm").val();
	var dsrxm = $("#dsrxm").val();
	var dsrzjjgzhm = $("#dsrzzjgdm").val();
	var dsrdwfr = $("#dsrdwmc").val();
	var dbrsfzmhm = $("#dbrsfzmhm").val();
	var dbrxm = $("#dbrxm").val();
	var bz = $("#bz").val();
	var ywyy = $("#ywyy").val();
	
	var dsrsfzmhmblack = "";
	var sfzmhmblack = "";
	var xmblack = "";
	if(dsrsfzmhm != null &&  dsrsfzmhm != ""){
		dsrsfzmhmblack = dsrsfzmhm;
	}else{
		dsrsfzmhmblack = dsrzjjgzhm;
	}
	if(dbrsfzmhm != null && dbrsfzmhm != ""){
		sfzmhmblack = dbrsfzmhm;
	}else{
		sfzmhmblack = dbrzzjgdm;
	}
	
	if(dbrxm != null && dbrxm != ""){
		xmblack = dbrxm;
	}else{
		xmblack = dbrdwfr;
	}
	
	
	//强制预约验证
	var yuyue = getIsyuyue(lsh, hphmval, hpzlval, dsrsfzmhmblack, ywlx, ywyy, '1', '3', '', '');
	//yuyue = "1+成功";
	if(yuyue == "error"){
		return false;
	}
	var yuyuearr = yuyue.split("+");
	if($.trim(yuyuearr[0]) != "1"){
		alert(yuyuearr[1]);
		return false;
	}
	
	//验证验车
//		if(lsh != null && lsh != ''){
//			var yanche = checkYanche(lsh);
//			if(yanche == "error" || yanche == ''){
//				return false;
//			}
//			if(yanche == '0'){
//				alert("不能受理，登录异常");
//				return false;
//			}else if(yanche == '2'){
//				alert("机动车管理科只能受理审验科验车的流水号数据");
//				return false;
//			}else if(yanche == '3'){
//				alert("只能受理本部门验车的流水号数据,请核对验车部门");
//				return false;
//			}
//		}
	
	//审批验证
	if(ismyself == '2'){   //代办人
		if((sfzmmc == 'A' || sfzmmc == 'M') && (idno == null ||idno == '') ){
			var istrue = spyanzheng(dsrsfzmhm, dsrxm, 'veh', 0, 2);
			if(istrue == 0){
				alert("当事人二代居民身份证或临时居民身份证未读取，请先进行审批!");
				return false;
			}
		}
//		if(sfzmmc == 'B'){
//			var istrue = spyanzheng(dsrzjjgzhm, dsrdwfr, 'veh', 0, 2);
//			if(istrue == 0){
//				alert("当事人组织机构证未读取，请先进行审批!");
//				return false;
//			}
//		}
		var dbrzzjgdm = $("#dbrzzjgdm").val();
		var dbrdwfr = $("#dbrdwmc").val();
		
		//验证代办人黑名单
		var isblack = getIsBlack("1", "2", hphmval, hpzlval, dsrsfzmhmblack, sfzmhmblack);
		if(isblack == "error"){
			return false;
		}
		var remesg = isblack.split("+");
		if($.trim(remesg[0]) != "0"){
			alert(remesg[1]);
			return false;
		}
		
		if((dbrsfzmmc == 'A' || dbrsfzmmc == 'M') && (idno2 == null ||idno2 == '')){
			var istrue = spyanzheng(dbrsfzmhm, dbrxm, 'veh', 1, 1);
			if(istrue == 0){
				alert("代办人二代居民身份证或临时居民身份证未读取，请先进行审批!");
				return false;
			}
		}
//		if(dbrsfzmmc == 'B'){
//			var istrue = spyanzheng(dbrzzjgdm, dbrdwfr, 'veh', 1, 1);
//			if(istrue == 0){
//				alert("代办人组织机构证未读取，请先进行审批!");
//				return false;
//			}
//		}
		
	}else{ //当事人
		if((sfzmmc == 'A' || sfzmmc == 'M') && (idno == null ||idno == '') ){
			var istrue = spyanzheng(dsrsfzmhm, dsrxm, 'veh', 0, 2);
			if(istrue == 0){
				alert("当事人二代居民身份证或临时居民身份证未读取，请先进行审批!");
				return false;
			}
		}
//		if(sfzmmc == 'B'){
//			var istrue = spyanzheng(dsrzjjgzhm, dsrdwfr, 'veh', 0, 2);
//			if(istrue == 0){
//				alert("当事人组织机构证未读取，请先进行审批!");
//				return false;
//			}
//		}
	}
	
	//业务类型验证
	var mustlsh = checkYwlx(ywlx);
	if(mustlsh == '3'){
		return false;
	}else{
		if(mustlsh == '1'){
			var lsh = $("#lsh").val();
			if(lsh == null || lsh == ""){
				alert("该办理的业务类型需通过流水号形式处理!");
				return false;
			}
		}
	}
	
	//验证业务类型是否为新车登记和转移登记
	if(ywlx == "A" && ywyy == "A"){
		alert("该流水是新车登记业务，不能在本模块操作!");
		return false;
	}
	if(ywlx == "B" && (ywyy == "B" || ywyy == "C") && (bz == null || bz == "") ){
		alert("该流水是转移登记业务，不能在本模块操作!");
		return false;
	}
	
	var fso = new ActiveXObject("Scripting.FileSystemObject");
	var f1 = fso.GetFile("C:\\printtx.jpg");
	var f2 = fso.GetFile("C:\\printzj.jpg");
	var f1d, f2d;
	f1d = f1.DateLastModified;
	f2d = f2.DateLastModified;
	var d1 = new Date(f1d);
	var d2 = new Date(f2d);
	var d01 = $("#xczpdate").val();
	var d02 = $("#zjzpdate").val();
	if(d1.getTime() != d01){
		alert('现场照片存在异常!');
		return false;
	}else if(d2.getTime() != d02){
		alert('高拍仪照片存在异常!');
		return false;
	}else{
		if(checktime(d1) && checktime(d2)){
//				if(ismyself == '2')
//				{
//					//验证是否备案
//					var isbeian = yansfbeian(dbrsfzmhm);
//					if(isbeian == '3'){
//						return false;
//					}else if(isbeian == '1'){
//						//没有备案
//						var isposp = yanpeioshenp(dsrsfzmhm, dbrsfzmhm, hphmval, hpzlval);
//						if(isposp == '3'){
//							return false;
//						}else if(isposp == '1'){
//							alert("你好，现录入的代办人未在车管部门备案，请尽快备齐资料申请备案!现为车管业务代办机构及人员备案工作期间，对尚没有备案的代办人，暂允许数据正常录入，当前录入信息有效!");
//						}
//					}
//				}
			
			//var isten = isWfwclTen(hphmval, hpzlval, '');
			var isten = "0";
			if(isten == "error"){
				return false;
			}
			if(isten != "0"){
				if(isten == '2'){
					if(confirm('该车由于10宗以上违法未处理已被锁定，请认真核实当事人信息后再确定保存。')){
						if(confirm("此次以车牌号方式受理，是否确定受理?")){
							//1、验证指标
							//1.1、验证是否需要输入指标类型和指标号
							var isshuruzb = checkIsneedzb(hphmval, hpzlval, ywlx, ywyy, dsrsfzmhmblack, '');
							if(isshuruzb == "error"){
								return false;
							}
							var arrs = isshuruzb.split("+");
							if(arrs[0] == '-1'){
								alert("验证指标失败");
								return false;
							}
							$("#alterinfo").val(arrs[11]);
							$("#zbyzflag").val(arrs[7]);
							alert(isshuruzb+" "+arrs[7]);
							if(arrs[0] == "1" && arrs[1] == "1"){
								var zjxxblsh = getZjxxblsh();
								if(zjxxblsh == "error"){
									alert("流水号生成失败,请重新保存");
									return false;
								}else{
									$("#zjxxblsh").val(zjxxblsh);
									alertdiv(arrs[2], arrs[3], arrs[4], arrs[5], arrs[6], arrs[8], arrs[9], arrs[10]);
									return false;
								}
							}else if(arrs[0] == "1" && arrs[1] == "0"){
								alertdivlsh(arrs[5]);
							}else{
								//清空车辆限购信息
								clearclxginfo();
								var imgurl = path+'/artDialog4.1.6/skins/icons/loading2.gif';
								chuli = art.dialog({
								    content: '<div style=\"text-align:center; text-indent:-999em; overflow:hidden; background:url('+imgurl+') no-repeat center center;\"><span>loading..</span></div><br/>数据处理中,请稍候.....',
								    title: '数据处理中',
									lock: true,
								    opacity: 0.87
								});
								$("#hpzl").removeAttr("disabled");
								$("#drv_form").attr("action", path+"/veh/veh_editSlgVehXxcj.action");
								$("#drv_form").attr("target", "abc");
								$("#drv_form").submit();
							}
						}
					}
				}else{
					alert("告知用户该车由于10宗以上违法未处理已被锁定，请到机动训练大队接受专门调查和处理!");
					return false;
				}
			}else{
				if(confirm("此次以车牌号方式受理，是否确定受理?")){
					//1、验证指标
					//1.1、验证是否需要输入指标类型和指标号
					var isshuruzb = checkIsneedzb(hphmval, hpzlval, ywlx, ywyy, dsrsfzmhmblack, '');
					if(isshuruzb == "error"){
						return false;
					}
					var arrs = isshuruzb.split("+");
					if(arrs[0] == '-1'){
						alert("验证指标失败");
						return false;
					}
					$("#zbyzflag").val(arrs[7]);
					$("#alterinfo").val(arrs[11]);
					if(arrs[0] == "1" && arrs[1] == "1"){
						var zjxxblsh = getZjxxblsh();
						if(zjxxblsh == "error"){
							alert("流水号生成失败,请重新保存");
							return false;
						}else{
							$("#zjxxblsh").val(zjxxblsh);
							alertdiv(arrs[2], arrs[3], arrs[4], arrs[5], arrs[6], arrs[8], arrs[9], arrs[10]);
							return false;
						}
					}else if(arrs[0] == "1" && arrs[1] == "0"){
						alertdivlsh(arrs[5]);
					}else{
						//清空车辆限购信息
						clearclxginfo();
						var imgurl = path+'/artDialog4.1.6/skins/icons/loading2.gif';
						chuli = art.dialog({
						    content: '<div style=\"text-align:center; text-indent:-999em; overflow:hidden; background:url('+imgurl+') no-repeat center center;\"><span>loading..</span></div><br/>数据处理中,请稍候.....',
						    title: '数据处理中',
							lock: true,
						    opacity: 0.87
						});
						$("#hpzl").removeAttr("disabled");
						$("#drv_form").attr("action", path+"/veh/veh_editSlgVehXxcj.action");
						$("#drv_form").attr("target", "abc");
						$("#drv_form").submit();
					}
				}
			}
			
		}else{
			alert('拍摄照片存在异常!');
			return false;
		}
	}
	
}
