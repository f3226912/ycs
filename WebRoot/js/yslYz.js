// 国产/进口选择事件
function selChange(obj){
	if(pdGcJk(obj.value)){
		document.getElementById(obj.id + "TdJgpzh").style.display = "none";
		document.getElementById(obj.id + "TdJgpzhValue").style.display = "none";
		document.getElementById(obj.id + "TdHgzbh").style.display = "block";
		document.getElementById(obj.id + "TdHgzbhValue").style.display = "block";
		
		if(obj.id == "ptdo.gcjk"){
			document.getElementById("ptdo_jkpzhm").value = "";
		}
		else{
			document.getElementById("jkpzhmCf").value = "";
		}
	}
	else{
		document.getElementById(obj.id + "TdJgpzh").style.display = "block";
		document.getElementById(obj.id + "TdJgpzhValue").style.display = "block";
		document.getElementById(obj.id + "TdHgzbh").style.display = "none";
		document.getElementById(obj.id + "TdHgzbhValue").style.display = "none";
		
		if(obj.id == "ptdo.gcjk"){
			document.getElementById("ptdo_hgzbh").value = "";
		}
		else{
			document.getElementById("hgzbhCf").value = "";
		}
	}
}

// 判断 用户选择的是国产还是进口
function pdGcJk(obj){
	// 根据数据字典已有记录，判断用户选择的是国产还是进口。今后如果有增加种类，直接修改此方法即可。
	// 国产返回 true
	if(obj == 'A' || obj == 'C' || obj == 'D' || obj == 'F' || obj == 'H'){
		return true;
	}
	else{
		return false;
	}
}

// 选择验车方式
function ycfs(){
	art.dialog({ 
		width:300,
		height:100,
		title: '请选择验车类型',
		content: '<input type="radio" value="OUTSMYCYSL" id="smycType" name="smycType" checked="checked" /> 上门验车 &nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="OUT" id="smycType" name="smycType" /> 现场验车',
		lock: true,
		fixed: true,
		ok: function () {
			document.getElementById("ptdo_outIn").value = document.getElementById("smycType").value;
			user_form.submit();
			return true;
		},
		cancel: false
	});
}

// 登记参数项
function djcsClick(){
	//非空验证
	var sfzmhm = document.getElementById("ptdo_sfzmhm");
	if(checknotnull(sfzmhm,'请填写身份证明号码') != "true"){
		return false;
	}
	if(containSpecial(sfzmhm,"身份证明号码中，包含特殊字符，请先删除!") == "false"){
		return false;
	}
	
	var zzz = document.getElementById("ptdo_zzz");
	if(checknotnull(zzz,'请填写居住/暂住证号') != "true"){
		return false;
	}
	
	if(containSpecial(zzz,"居住/暂住证号中，包含特殊字符，请先删除!") == "false"){
		return false;
	}
	
	var syr = document.getElementById("ptdo_syr");
	if(checknotnull(syr,'请填写姓名/名称') != "true"){
		return false;
	}
	if(containSpecial(syr,"姓名/名称中，包含特殊字符，请先删除!") == "false"){
		return false;
	}
	
	var lxdh = document.getElementById("ptdo_lxdh");
	if(checknotnull(lxdh,'请填写移动电话') != "true"){
		return false;
	}
	if(containSpecial(lxdh,"移动电话中，包含特殊字符，请先删除!") == "false"){
		return false;
	}
	
	var fpdm = document.getElementById("ptdo_fpdm");
	if(checknotnull(fpdm,'请填写发票代码') != "true"){
		return false;
	}
	
	if(containSpecial(fpdm,"发票代码中，包含特殊字符，请先删除!") == "false"){
		return false;
	}
	
	var fphm = document.getElementById("ptdo_fphm");
	if(checknotnull(fphm,'请填写发票号码') != "true"){
		return false;
	}
	if(containSpecial(fphm,"发票号码中，包含特殊字符，请先删除!") == "false"){
		return false;
	}
	
	var gzszmbh = document.getElementById("ptdo_gzszmbh");
	if(checknotnull(gzszmbh,'请填写购置税证明编号') != "true"){
		return false;
	}
	if(containSpecial(gzszmbh,"购置税证明编号中，包含特殊字符，请先删除!") == "false"){
		return false;
	}
	
	// 如果电子邮箱有填写值，需要判断电子邮箱格式
	var dzyx = document.getElementById("ptdo_dzyx");
	if(dzyx.value != "" && dzyx.value != " " && dzyx.value != null
			&& dzyx.value != undefined){
		if(checkemail(dzyx.value) == "false"){
			prompt(dzyx,"填写正确的邮箱。");
			return false;
		}else{
			removePrompt(dzyx);
		}
	}else{
		removePrompt(dzyx);
	}
	
	var zsxxdz = document.getElementById("ptdo_zsxxdz");
	if(checknotnull(zsxxdz,'请填写住所地址') != "true"){
		return false;
	}
	
	var yxqz = document.getElementById("ptdo_yxqz");
	if(checknotnull(yxqz,'请填检验日期') != "true"){
		return false;
	}
	if(containSpecial(yxqz,"检验日期中，包含特殊字符，请先删除!") == "false"){
		return false;
	}
	
	// 跳转到登记参数重复页面
	//ycTable('tabCc2');
}

// 登记参数重复
function djcsCfClick(){
	// 判断身份证名名称是否一样
	var sfzmmcCf = document.getElementById("sfzmmcCf");
	var sfzmmc = document.getElementById("ptdo_sfzmmc").value;
	
	if(sfzmmc != sfzmmcCf.value.trim()){
		alert();
		prompt(sfzmmcCf,"身份证明名称不一样，请选择正确。");
		return false;
	}
	else{
		removePrompt(sfzmmcCf);
	}
	
	// 身份证名号码 是否一样
	var sfzmhmCf = document.getElementById("sfzmhmCf");
	var sfzmhm = document.getElementById("ptdo_sfzmhm").value;
	
	if(sfzmhm != sfzmhmCf.value.trim()){
		prompt(sfzmhmCf,"身份证号码不一样，请填写正确。");
		return false;
	}
	else{
		removePrompt(sfzmhmCf);
	}
	
	// 暂住证 是否一样
	var zzzCf = document.getElementById("zzzCf"); 
	var zzz = document.getElementById("ptdo_zzz").value; 
	
	if(zzz != zzzCf.value.trim()){
		prompt(zzzCf,"居住/暂住证号不一样，请填写正确。");
		return false;
	}
	else{
		removePrompt(zzzCf);
	}
	
	// 姓名是否一样
	var syrCf = document.getElementById("syrCf");
	var syr = document.getElementById("ptdo_syr").value;
	
	if(syr != syrCf.value.trim()){
		prompt(syrCf,"姓名/名称不一样，请填写正确。");
		return false;
	}
	else{
		removePrompt(syrCf);
	}
	
	// 移动电话 是否一样
	var lxdhCf = document.getElementById("lxdhCf");
	var lxdh = document.getElementById("ptdo_lxdh").value;
	
	if(lxdh != lxdhCf.value.trim()){
		prompt(lxdhCf,"移动电话不一样，请填写正确。");
		return false;
	}
	else{
		removePrompt(lxdhCf);
	}
	
	// 发票代码 是否一样
	var netFphCf = document.getElementById("fpdmCf");
	var netFph = document.getElementById("ptdo_fpdm").value;
	
	if(netFph != netFphCf.value.trim()){
		prompt(netFphCf,"发票代码不一样，请填写正确。");
		return false;
	}
	else{
		removePrompt(netFphCf);
	}
	
	// 发票号码 是否一样
	var nszmCf = document.getElementById("fphmCf");
	var nszm = document.getElementById("ptdo_fphm").value;
	
	if(nszm != nszmCf.value.trim()){
		prompt(nszmCf,"发票号码不一样，请填写正确。");
		return false;
	}
	else{
		removePrompt(nszmCf);
	}
	
	// 购置税证明编号 是否一样
	var nszmbhCf = document.getElementById("gzszmbhCf");
	var nszmbh = document.getElementById("ptdo_gzszmbh").value;
	
	if(nszmbh != nszmbhCf.value.trim()){
		prompt(nszmbhCf,"购置税证明编号不一样，请填写正确。");
		return false;
	}
	else{
		removePrompt(nszmbhCf);
	}
	
	// 电子邮箱 是否一样
	var dzyxCf = document.getElementById("dzyxCf");
	var dzyx = document.getElementById("ptdo_dzyx").value;
	
	if(dzyx != dzyxCf.value.trim()){
		prompt(dzyxCf,"电子邮箱不一样，请填写正确。");
		return false;
	}
	else{
		removePrompt(dzyxCf);
	}
	
	// 住所所在区域 是否一样
	var yzbm7Cf = document.getElementById("yzbm7Cf");
	var yzbm7 = document.getElementById("yzbm7").value;
	
	if(yzbm7 != yzbm7Cf.value.trim()){
		prompt(yzbm7Cf,"住所所在区域不一样，请填写正确。");
		return false;
	}
	else{
		removePrompt(yzbm7Cf);
	}
	
	// 住所地址 是否一样
	var zsxxdzCf = document.getElementById("zsxxdzCf");
	var zsxxdz = document.getElementById("ptdo_zsxxdz").value;
	
	if(zsxxdz != zsxxdzCf.value.trim()){
		prompt(zsxxdzCf,"住所地址不一样，请选择正确。");
		return false;
	}
	else{
		removePrompt(zsxxdzCf);
	}
	
	// 邮寄所在区域 是否一样
	var yzbm6Cf = document.getElementById("yzbm6Cf");
	var yzbm6 = document.getElementById("yzbm6").value;
	
	if(yzbm6 != yzbm6Cf.value.trim()){
		prompt(yzbm6Cf,"邮寄所在区域不一样，请选择正确。");
		return false;
	}
	else{
		removePrompt(yzbm6Cf);
	}
	
	// 邮寄地址 是否一样
	var zzxxdzCf = document.getElementById("zzxxdzCf");
	var zzxxdz = document.getElementById("ptdo_zzxxdz").value;
	
	if(zzxxdz != zzxxdzCf.value.trim()){
		prompt(zzxxdzCf,"邮寄地址不一样，请选择正确。");
		return false;
	}
	else{
		removePrompt(zzxxdzCf);
	}
	
	
	// 使用性质 是否一样
	var syxzCf = document.getElementById("syxzCf");
	var syxz = document.getElementById("ptdo_syxz").value;
	
	if(syxz != syxzCf.value.trim()){
		prompt(syxzCf,"使用性质不一样，请选择正确。");
		return false;
	}
	else{
		removePrompt(syxzCf);
	}
	
	// 所有权 是否一样
	var syqCf = document.getElementById("syqCf");
	var syq = document.getElementById("ptdo_syq").value;
	
	if(syq != syqCf.value.trim()){
		prompt(syqCf,"所有权不一样，请选择正确。");
		return false;
	}
	else{
		removePrompt(syqCf);
	}
	
	// 获得方式 是否一样
	var hdfsCf = document.getElementById("hdfsCf");
	var hdfs = document.getElementById("ptdo_hdfs").value;
	
	if(hdfs != hdfsCf.value.trim()){
		prompt(hdfsCf,"获得方式不一样，请选择正确。");
		return false;
	}
	else{
		removePrompt(hdfsCf);
	}
	
	// 行政区划 是否一样
	var xzqhCf = document.getElementById("xzqhCf");
	var xzqh = document.getElementById("ptdo_zsxzqh").value;
	
	if(xzqh != xzqhCf.value.trim()){
		prompt(xzqhCf,"行政区划不一样，请选择正确。");
		return false;
	}
	else{
		removePrompt(xzqhCf);
	}
	
	// 号牌种类 是否一样
	var hpzlCf = document.getElementById("hpzlCf");
	var hpzl = document.getElementById("ptdo_hpzl").value;
	
	if(hpzl != hpzlCf.value.trim()){
		prompt(hpzlCf,"号牌种类不一样，请选择正确。");
		return false;
	}
	else{
		removePrompt(hpzlCf);
	}
	
	// 保险生效日期 是否一样
	var zzrqCf = document.getElementById("sxrqCf");
	var zzrq = document.getElementById("ptdo_sxrq").value;
	
	if(zzrq != zzrqCf.value.trim()){
		prompt(zzrqCf,"保险生效日期不一样，请选择正确。");
		return false;
	}
	else{
		removePrompt(zzrqCf);
	}
	
	// 保险终止日期 是否一样
	var zzrqCf = document.getElementById("zzrqCf");
	var zzrq = document.getElementById("ptdo_zzrq").value;
	
	if(zzrq != zzrqCf.value.trim()){
		prompt(zzrqCf,"保险终止日期不一样，请选择正确。");
		return false;
	}
	else{
		removePrompt(zzrqCf);
	}
	
	
	// 检验日期 是否一样
	var yxqzCf = document.getElementById("yxqzCf");
	var yxqz = document.getElementById("ptdo_yxqz").value;
	
	if(yxqz != yxqzCf.value.trim()){
		prompt(yxqzCf,"检验日期不一样，请填写正确。");
		return false;
	}
	else{
		removePrompt(yxqzCf);
	}
	
	// 承检单位 是否一样 
	var cjdwCf = document.getElementById("cjdwCf");
	var cjdw = document.getElementById("ptdo_cjdw").value;
	
	if(cjdw != cjdwCf.value.trim()){
		prompt(cjdwCf,"承检单位不一样，请选择正确。");
		return false;
	}
	else{
		removePrompt(cjdwCf);
	}
	
	// 承检单位 是否一样 
	var tempBz2Cf = document.getElementById("tempBz2Cf");
	var tempBz2 = document.getElementById("ptdo_tempBz2").value;
	
	if(tempBz2 != tempBz2Cf.value.trim()){
		prompt(tempBz2Cf,"常停车场所不一样，请选择正确。");
		return false;
	}
	else{
		removePrompt(tempBz2Cf);
	}
	
	// 跳转到技术参数项页面
	ycTable('tabCc3');
}

// 技术参数项
function jscsClick(){
	//非空验证
	var clsbdh = document.getElementById("ptdo_clsbdh");
	if(checknotnull(clsbdh,'请填写车辆识别代号') != "true"){
		return false;
	}
	if(containSpecial(clsbdh,"车辆识别代号中，包含特殊字符，请先删除!") == "false"){
		return false;
	}
	if(!checkVIN_JYW(clsbdh.value)){
		prompt(clsbdh,"车辆识别代号不合法，请填写正确。");
		return false;
	}
	else{
		removePrompt(clsbdh);
	}
	
	var clxh = document.getElementById("ptdo_clxh");
	if(checknotnull(clxh,'请填写车辆型号') != "true"){
		return false;
	}
	if(containSpecial(clxh,"车辆型号中，包含特殊字符，请先删除!") == "false"){
		return false;
	}
	
	var clpp1 = document.getElementById("ptdo_clpp1");
	if(containSpecial(clpp1,"中文品牌中，包含特殊字符，请先删除!") == "false"){
		return false;
	}
	
	var clpp2 = document.getElementById("ptdo_clpp2");
	if(containSpecial(clpp2,"英文品牌中，包含特殊字符，请先删除!") == "false"){
		return false;
	}
	
	var fdjh = document.getElementById("ptdo_fdjh");
	if(checknotnull(fdjh,'请填写发动机号') != "true"){
		return false;
	}
	if(containSpecial(fdjh,"发动机号中，包含特殊字符，请先删除!") == "false"){
		return false;
	}
	
	var zzcmc = document.getElementById("ptdo_zzcmc");
		
	if(containSpecial(zzcmc,"制造厂名称中，包含特殊字符，请先删除!") == "false"){
		return false;
	}
	
	
	var hgzbh = document.getElementById("ptdo_hgzbh");
	var jkpzhm = document.getElementById("ptdo_jkpzhm");
	var tdJgpzh = document.getElementById("ptdo_gcjkTdJgpzh");
	
	if(tdJgpzh.style.display == "none"){
		// 清空进口凭证号的值
		jkpzhm.value = "";
		if(checknotnull(hgzbh,'请填写合格证编号') != "true"){
			return false;
		}
		if(containSpecial(hgzbh,"合格证编号中，包含特殊字符，请先删除!") == "false"){
			return false;
		}
	}
	else{
		// 清空合格证编号的值
		hgzbh.value = "";
		if(checknotnull(jkpzhm,'请填写进口凭证号') != "true"){
			return false;
		}
		if(containSpecial(jkpzhm,"进口凭证号中，包含特殊字符，请先删除!") == "false"){
			return false;
		}
	}
	
	var zzg = document.getElementById("ptdo_zzg");
	// 如果是国产车
	if(pdGcJk(document.getElementById("ptdo_gcjk").value)){
		if(zzg.value != "156"){
			prompt(zzg,"国产车的制造国必须是中国");
			return false;
		}
		removePrompt(zzg);
		// 制造国为中国的合格证编号必须为15位
		if(hgzbh.value.length != 15){
			prompt(hgzbh,"制造国为中国的合格证编号必须为15位");
			return false;
		}
	}
	else{
		if(zzg.value == "156"){
			prompt(zzg,"进口车的制造国不能是中国");
			return false;
		}
		removePrompt(zzg);
		
		if(clxh.value.length != 11){
			prompt(clxh,"进口车的车辆型号必须是11位");
			return false;
		}
		removePrompt(clxh);
		
		if(clsbdh.value.substring(0,11) != clxh.value){
			prompt(clxh,"进口车的车辆识别代码的前11位必须和车辆型号相同");
			return false;
		}
		removePrompt(clsbdh);
	}
	
	
	
	var fdjxh = document.getElementById("ptdo_fdjxh");
	if(checknotnull(fdjxh,'请填写发动机型号') != "true"){
		return false;
	}
	if(containSpecial(fdjxh,"发动机型号中，包含特殊字符，请先删除!") == "false"){
		return false;
	}
	
	var pl = document.getElementById("ptdo_pl");
	if(containSpecial(pl,"排量中，包含特殊字符，请先删除!") == "false"){
		return false;
	}
	if(checkNumber3(pl,"排量只能是数字") == "false"){
		return false;
	}
	
	var gl = document.getElementById("ptdo_gl");
	if(containSpecial(gl,"功率中，包含特殊字符，请先删除!") == "false"){
		return false;
	}
	if(checkNumber3(gl,"功率，只能是数字") == "false"){
		return false;
	}
	
	var cwkc = document.getElementById("ptdo_cwkc");
	if(containSpecial(cwkc,"外廓-长中，包含特殊字符，请先删除!") == "false"){
		return false;
	}
	if(checkNumber3(cwkc,"外廓-长，只能是数字") == "false"){
		return false;
	}
	
	var cwkk = document.getElementById("ptdo_cwkk");
	if(containSpecial(cwkk,"外廓-宽中，包含特殊字符，请先删除!") == "false"){
		return false;
	}
	if(checkNumber3(cwkk,"外廓-宽，只能是数字") == "false"){
		return false;
	}
	
	var cwkg = document.getElementById("ptdo_cwkg");
	if(containSpecial(cwkg,"外廓-高中，包含特殊字符，请先删除!") == "false"){
		return false;
	}
	if(checkNumber3(cwkg,"外廓-高，只能是数字") == "false"){
		return false;
	}
	
	var hxnbcd = document.getElementById("ptdo_hxnbcd");
	if(containSpecial(hxnbcd,"货箱内部尺寸-长中，包含特殊字符，请先删除!") == "false"){
		return false;
	}
	if(checkNumber3(hxnbcd,"货箱内部尺寸-长，只能是数字") == "false"){
		return false;
	}
	
	var hxnbkd = document.getElementById("ptdo_hxnbkd");
	if(containSpecial(hxnbkd,"货箱内部尺寸-宽中，包含特殊字符，请先删除!") == "false"){
		return false;
	}
	if(checkNumber3(hxnbkd,"货箱内部尺寸-宽，只能是数字") == "false"){
		return false;
	}
	
	var hxnbgd = document.getElementById("ptdo_hxnbgd");
	if(containSpecial(hxnbgd,"货箱内部尺寸-高中，包含特殊字符，请先删除!") == "false"){
		return false;
	}
	if(checkNumber3(hxnbgd,"货箱内部尺寸-高，只能是数字") == "false"){
		return false;
	}
	
	var hbdbqk = document.getElementById("ptdo_hbdbqk");
	if(containSpecial(hbdbqk,"环保达标情况中，包含特殊字符，请先删除!") == "false"){
		return false;
	}
	
	var gbthps = document.getElementById("ptdo_gbthps");
	if(containSpecial(gbthps,"钢板弹簧片数中，包含特殊字符，请先删除!") == "false"){
		return false;
	}
	if(checkNumber3(gbthps,"钢板弹簧片数，只能是数字") == "false"){
		return false;
	}
	
	var zs = document.getElementById("ptdo_zs");
	if(containSpecial(zs,"轴数中，包含特殊字符，请先删除!") == "false"){
		return false;
	}
	if(checkNumber3(zs,"轴数，只能是数字") == "false"){
		return false;
	}
	
	var zj = document.getElementById("ptdo_zj");
	if(containSpecial(zj,"轴距中，包含特殊字符，请先删除!") == "false"){
		return false;
	}
	if(checkNumber3(zj,"轴距，只能是数字") == "false"){
		return false;
	}
	
	var lts = document.getElementById("ptdo_lts");
	if(containSpecial(lts,"轮胎数中，包含特殊字符，请先删除!") == "false"){
		return false;
	}
	if(checkNumber3(lts,"轮胎数，只能是数字") == "false"){
		return false;
	}
	
	var ltgg = document.getElementById("ptdo_ltgg");
	if(containSpecial(ltgg,"轮胎规格中，包含特殊字符，请先删除!") == "false"){
		return false;
	}
	if(checkNumber3(ltgg,"轮胎规格，只能是数字") == "false"){
		return false;
	}
	
	var qlj = document.getElementById("ptdo_qlj");
	if(containSpecial(qlj,"前轮距中，包含特殊字符，请先删除!") == "false"){
		return false;
	}
	if(checkNumber3(qlj,"前轮距，只能是数字") == "false"){
		return false;
	}
	
	var hlj = document.getElementById("ptdo_hlj");
	if(containSpecial(hlj,"后轮距中，包含特殊字符，请先删除!") == "false"){
		return false;
	}
	if(checkNumber3(hlj,"后轮距，只能是数字") == "false"){
		return false;
	}
	
	var zzl = document.getElementById("ptdo_zzl");
	if(containSpecial(zzl,"总质量中，包含特殊字符，请先删除!") == "false"){
		return false;
	}
	if(checkNumber3(zzl,"总质量，只能是数字") == "false"){
		return false;
	}
	
	var zbzl = document.getElementById("ptdo_zbzl");
	if(containSpecial(zbzl,"整备质量中，包含特殊字符，请先删除!") == "false"){
		return false;
	}
	if(checkNumber3(zbzl,"整备质量，只能是数字") == "false"){
		return false;
	}
	
	var hdzk = document.getElementById("ptdo_hdzk");
	if(containSpecial(hdzk,"核定载客中，包含特殊字符，请先删除!") == "false"){
		return false;
	}
	if(checkNumber3(hdzk,"核定载客，只能是数字") == "false"){
		return false;
	}
	
	var zqyzl = document.getElementById("ptdo_zqyzl");
	if(containSpecial(zqyzl,"准牵引总质量中，包含特殊字符，请先删除!") == "false"){
		return false;
	}
	if(checkNumber3(zqyzl,"准牵引总质量，只能是数字") == "false"){
		return false;
	}
	
	var hdzzl = document.getElementById("ptdo_hdzzl");
	if(containSpecial(hdzzl,"核定载质量中，包含特殊字符，请先删除!") == "false"){
		return false;
	}
	if(checkNumber3(hdzzl,"核定载质量，只能是数字") == "false"){
		return false;
	}
	
	var qpzk = document.getElementById("ptdo_qpzk");
	if(containSpecial(qpzk,"前排载客中，包含特殊字符，请先删除!") == "false"){
		return false;
	}
	if(checkNumber3(qpzk,"前排载客，只能是数字") == "false"){
		return false;
	}
	
	var hpzk = document.getElementById("ptdo_hpzk");
	if(containSpecial(hpzk,"后排载客中，包含特殊字符，请先删除!") == "false"){
		return false;
	}
	
	
	// 跳转到技术参数重复页面
	//ycTable('tabCc4');
}

// 技术参数重复
function jscsCfClick(){
	
	// 车辆识别代号 是否一样
	var clsbdhCf = document.getElementById("clsbdhCf");
	var clsbdh = document.getElementById("ptdo_clsbdh").value;
	
	if(clsbdh != clsbdhCf.value.trim()){
		prompt(clsbdhCf,"车辆识别代号不一样，请填写正确。");
		return false;
	}
	else{
		removePrompt(clsbdhCf);
	}
	
	// 车辆型号 是否一样
	var clxhCf = document.getElementById("clxhCf");
	var clxh = document.getElementById("ptdo_clxh").value;
	
	if(clxh != clxhCf.value.trim()){
		prompt(clxhCf,"车辆型号不一样，请填写正确。");
		return false;
	}
	else{
		removePrompt(clxhCf);
	}
	
	// 中文品牌 是否一样 
	var clpp1Cf = document.getElementById("clpp1Cf");
	var clpp1 = document.getElementById("ptdo_clpp1").value;
	
	if(clpp1 != clpp1Cf.value.trim()){
		prompt(clpp1Cf,"中文品牌不一样，请填写正确。");
		return false;
	}
	else{
		removePrompt(clpp1Cf);
	}
	
	// 英文品牌 是否一样
	var clpp2Cf = document.getElementById("clpp2Cf");
	var clpp2 = document.getElementById("ptdo_clpp2").value;
	
	if(clpp2 != clpp2Cf.value.trim()){
		prompt(clpp2Cf,"英文品牌不一样，请填写正确。");
		return false;
	}
	else{
		removePrompt(clpp2Cf);
	}
	
	// 车辆类型 是否一样
	var cllxCf = document.getElementById("cllxCf");
	var cllx = document.getElementById("ptdo_cllx").value;
	
	if(cllx != cllxCf.value.trim()){
		prompt(cllxCf,"车辆类型不一样，请选择正确。");
		return false;
	}
	else{
		removePrompt(cllxCf);
	}
	
	// 制造国 是否一样
	var zzgCf = document.getElementById("zzgCf");
	var zzg = document.getElementById("ptdo_zzg").value;
	
	if(zzg != zzgCf.value.trim()){
		prompt(zzgCf,"制造国不一样，请选择正确。");
		return false;
	}
	else{
		removePrompt(zzgCf);
	}
	
	// 发动机型号 是否一样
	var fdjxhCf = document.getElementById("fdjxhCf");
	var fdjxh = document.getElementById("ptdo_fdjxh").value;
	
	if(fdjxh != fdjxhCf.value.trim()){
		prompt(fdjxhCf,"发动机型号不一样，请填写正确。");
		return false;
	}
	else{
		removePrompt(fdjxhCf);
	}
	
	// 制造厂名称 是否一样
	var zzcmcCf = document.getElementById("zzcmcCf");
	var zzcmc = document.getElementById("ptdo_zzcmc").value;
	
	if(zzcmc != zzcmcCf.value.trim()){
		prompt(zzcmcCf,"制造厂名称不一样，请填写正确。");
		return false;
	}
	else{
		removePrompt(zzcmcCf);
	}
	
	// 发合格证日期 是否一样
	var fhgzrqCf = document.getElementById("fhgzrqCf");
	var fhgzrq = document.getElementById("ptdo_fhgzrq").value;
	
	if(fhgzrq != fhgzrqCf.value.trim()){
		prompt(fhgzrqCf,"发合格证日期不一样，请填写正确。");
		return false;
	}
	else{
		removePrompt(fhgzrqCf);
	}
	
	// 车身颜色 是否一样
	var csysCf = document.getElementById("csysCf");
	var csys = document.getElementById("ptdo_csys").value;
	
	if(csys != csysCf.value.trim()){
		prompt(csysCf,"车身颜色不一样，请选择正确。");
		return false;
	}
	else{
		removePrompt(csysCf);
	}
	
	// 出厂日期 是否一样
	var ccrqCf = document.getElementById("ccrqCf");
	var ccrq = document.getElementById("ptdo_ccrq").value;
	
	if(ccrq != ccrqCf.value.trim()){
		prompt(ccrqCf,"出厂日期不一样，请填写正确。");
		return false;
	}
	else{
		removePrompt(ccrqCf);
	}
	
	// 国产/进口 是否一样
	var gcjkCf = document.getElementById("gcjkCf");
	var gcjk = document.getElementById("ptdo_gcjk").value;
	
	if(gcjk != gcjkCf.value.trim()){
		prompt(gcjkCf,"国产/进口不一样，请选择正确。");
		return false;
	}
	else{
		removePrompt(gcjkCf);
	}
	
	// 根据用户选择 国产/进口  判断合格证或者进口凭证号 是否一样
	var hgzbhCf = document.getElementById("hgzbhCf");
	var hgzbh = document.getElementById("ptdo_hgzbh").value;
	var jkpzhmCf = document.getElementById("jkpzhmCf");
	var jkpzhm = document.getElementById("ptdo_jkpzhm").value;
	var gcjkCfTdJgpzh = document.getElementById("gcjkCfTdJgpzh")
	
	if(gcjkCfTdJgpzh.style.display == "none"){
		if(hgzbh != hgzbhCf.value.trim()){
			prompt(hgzbhCf,"合格证编号不一样，请填写正确。");
			return false;
		}
		else{
			removePrompt(hgzbhCf);
		}
	}
	else{
		if(jkpzhm != jkpzhmCf.value.trim()){
			prompt(jkpzhmCf,"进口凭证号不一样，请填写正确。");
			return false;
		}
		else{
			removePrompt(jkpzhmCf);
		}
	}
		
	// 发动机号 是否一样
	var fdjhCf = document.getElementById("fdjhCf");
	var fdjh = document.getElementById("ptdo_fdjh").value;
	
	if(fdjh != fdjhCf.value.trim()){
		prompt(fdjhCf,"发动机号不一样，请填写正确。");
		return false;
	}
	else{
		removePrompt(fdjhCf);
	}
	
	// 排量 是否一样
	var plCf = document.getElementById("plCf");
	var pl = document.getElementById("ptdo_pl").value;
	
	if(pl != plCf.value.trim()){
		prompt(plCf,"排量不一样，请填写正确。");
		return false;
	}
	else{
		removePrompt(plCf);
	}
	
	// 功率是否一样
	var glCf = document.getElementById("glCf");
	var gl = document.getElementById("ptdo_gl").value;
	
	if(gl != glCf.value.trim()){
		prompt(glCf,"功率不一样，请填写正确。");
		return false;
	}
	else{
		removePrompt(glCf);
	}
	
	// 燃料种类 是否一样
	var rlzlCf = document.getElementById("rlzlCf");
	var rlzl = document.getElementById("ptdo_rlzl").value;
	
	if(rlzl != rlzlCf.value.trim()){
		prompt(rlzlCf,"燃料种类不一样，请选择正确。");
		return false;
	}
	else{
		removePrompt(rlzlCf);
	}
	
	// 外廓 长 是否一样
	var cwkcCf = document.getElementById("cwkcCf");
	var cwkc = document.getElementById("ptdo_cwkc").value;
	
	if(cwkc != cwkcCf.value.trim()){
		prompt(cwkcCf,"外廓长不一样，请填写正确。");
		return false;
	}
	else{
		removePrompt(cwkcCf);
	}
	
	// 外廓 宽 是否一样
	var cwkkCf = document.getElementById("cwkkCf");
	var cwkk = document.getElementById("ptdo_cwkk").value;
	
	if(cwkk != cwkkCf.value.trim()){
		prompt(cwkkCf,"外廓宽不一样，请填写正确。");
		return false;
	}
	else{
		removePrompt(cwkkCf);
	}
	
	// 外廓 高 是否一样
	var cwkgCf = document.getElementById("cwkgCf");
	var cwkg = document.getElementById("ptdo_cwkg").value;
	
	if(cwkg != cwkgCf.value.trim()){
		prompt(cwkgCf,"外廓高不一样，请填写正确。");
		return false;
	}
	else{
		removePrompt(cwkgCf);
	}
	
	// 转向形式 是否一样
	var zxxsCf = document.getElementById("zxxsCf");
	var zxxs = document.getElementById("ptdo_zxxs").value;
	
	if(zxxs != zxxsCf.value.trim()){
		prompt(zxxsCf,"转向形式不一样，请选择正确。");
		return false;
	}
	else{
		removePrompt(zxxsCf);
	}
	
	// 货箱内部尺寸 长 是否一样
	var hxnbcdCf = document.getElementById("hxnbcdCf");
	var hxnbcd = document.getElementById("ptdo_hxnbcd").value;
	
	if(hxnbcd != hxnbcdCf.value.trim()){
		prompt(hxnbcdCf,"货箱内部尺寸长不一样，请填写正确。");
		return false;
	}
	else{
		removePrompt(hxnbcdCf);
	}
	
	// 货箱内部尺寸 宽 是否一样
	var hxnbkdCf = document.getElementById("hxnbkdCf");
	var hxnbkd = document.getElementById("ptdo_hxnbkd").value;
	
	if(hxnbkd != hxnbkdCf.value.trim()){
		prompt(hxnbkdCf,"货箱内部尺寸宽不一样，请填写正确。");
		return false;
	}
	else{
		removePrompt(hxnbkdCf);
	}
	
	// 货箱内部尺寸 高 是否一样
	var hxnbgdCf = document.getElementById("hxnbgdCf");
	var hxnbgd = document.getElementById("ptdo_hxnbgd").value;
	
	if(hxnbgd != hxnbgdCf.value.trim()){
		prompt(hxnbgdCf,"货箱内部尺寸高不一样，请填写正确。");
		return false;
	}
	else{
		removePrompt(hxnbgdCf);
	}
	
	// 环保达标情况 是否一样
	var hbdbqkCf = document.getElementById("hbdbqkCf");
	var hbdbqk = document.getElementById("ptdo_hbdbqk").value;
	
	if(hbdbqk != hbdbqkCf.value.trim()){
		prompt(hbdbqkCf,"环保达标情况不一样，请填写正确。");
		return false;
	}
	else{
		removePrompt(hbdbqkCf);
	}
	
	// 钢板弹簧片数 是否一样
	var gbthpsCf = document.getElementById("gbthpsCf");
	var gbthps = document.getElementById("ptdo_gbthps").value;
	
	if(gbthps != gbthpsCf.value.trim()){
		prompt(gbthpsCf,"钢板弹簧片数不一样，请填写正确。");
		return false;
	}
	else{
		removePrompt(gbthpsCf);
	}
	
	// 轴数 是否一样
	var zsCf = document.getElementById("zsCf");
	var zs = document.getElementById("ptdo_zs").value;
	
	if(zs != zsCf.value.trim()){
		prompt(zsCf,"轴数不一样，请填写正确。");
		return false;
	}
	else{
		removePrompt(zsCf);
	}
	
	// 轴距 是否一样
	var zjCf = document.getElementById("zjCf");
	var zj = document.getElementById("ptdo_zj").value;
	
	if(zj != zjCf.value.trim()){
		prompt(zjCf,"轴距不一样，请填写正确。");
		return false;
	}
	else{
		removePrompt(zjCf);
	}
	
	// 轮胎数 是否一样
	var ltsCf = document.getElementById("ltsCf");
	var lts = document.getElementById("ptdo_lts").value;
	
	if(lts != ltsCf.value.trim()){
		prompt(ltsCf,"轮胎数不一样，请填写正确。");
		return false;
	}
	else{
		removePrompt(ltsCf);
	}
	
	// 轮胎规格 是否一样
	var ltggCf = document.getElementById("ltggCf");
	var ltgg = document.getElementById("ptdo_ltgg").value;
	
	if(ltgg != ltggCf.value.trim()){
		prompt(ltggCf,"轮胎规格不一样，请填写正确。");
		return false;
	}
	else{
		removePrompt(ltggCf);
	}
	
	// 前轮距 是否一样
	var qljCf = document.getElementById("qljCf");
	var qlj = document.getElementById("ptdo_qlj").value;
	
	if(qlj != qljCf.value.trim()){
		prompt(qljCf,"前轮距不一样，请填写正确。");
		return false;
	}
	else{
		removePrompt(qljCf);
	}
	
	// 后轮距 是否一样
	var hljCf = document.getElementById("hljCf");
	var hlj = document.getElementById("ptdo_hlj").value;
	
	if(hlj != hljCf.value.trim()){
		prompt(hljCf,"后轮距不一样，请填写正确。");
		return false;
	}
	else{
		removePrompt(hljCf);
	}
	
	// 总质量 是否一样
	var zzlCf = document.getElementById("zzlCf");
	var zzl = document.getElementById("ptdo_zzl").value;
	
	if(zzl != zzlCf.value.trim()){
		prompt(zzlCf,"总质量不一样，请填写正确。");
		return false;
	}
	else{
		removePrompt(zzlCf);
	}
	
	// 整备质量 是否一样
	var zbzlCf = document.getElementById("zbzlCf");
	var zbzl = document.getElementById("ptdo_zbzl").value;
	
	if(zbzl != zbzlCf.value.trim()){
		prompt(zbzlCf,"整备质量不一样，请填写正确。");
		return false;
	}
	else{
		removePrompt(zbzlCf);
	}
	
	// 核定载客 是否一样
	var hdzkCf = document.getElementById("hdzkCf");
	var hdzk = document.getElementById("ptdo_hdzk").value;
	
	if(hdzk != hdzkCf.value.trim()){
		prompt(hdzkCf,"核定载客不一样，请填写正确。");
		return false;
	}
	else{
		removePrompt(hdzkCf);
	}
	
	// 准牵引总质量 是否一样
	var zqyzlCf = document.getElementById("zqyzlCf");
	var zqyzl = document.getElementById("ptdo_zqyzl").value;
	
	if(zqyzl != zqyzlCf.value.trim()){
		prompt(zqyzlCf,"准牵引总质量不一样，请填写正确。");
		return false;
	}
	else{
		removePrompt(zqyzlCf);
	}
	
	// 核定载质量 是否一样
	var hdzzlCf = document.getElementById("hdzzlCf");
	var hdzzl = document.getElementById("ptdo_hdzzl").value;
	
	if(hdzzl != hdzzlCf.value.trim()){
		prompt(hdzzlCf,"核定载质量不一样，请填写正确。");
		return false;
	}
	else{
		removePrompt(hdzzlCf);
	}
	
	// 驾驶室 前排载客 是否一样
	var qpzkCf = document.getElementById("qpzkCf");
	var qpzk = document.getElementById("ptdo_qpzk").value;
	
	if(qpzk != qpzkCf.value.trim()){
		prompt(qpzkCf,"驾驶室前排载客不一样，请填写正确。");
		return false;
	}
	else{
		removePrompt(qpzkCf);
	}
	
	// 驾驶室 后排载客 是否一样
	var hpzkCf = document.getElementById("hpzkCf");
	var hpzk = document.getElementById("ptdo_hpzk").value;
	
	if(hpzk != hpzkCf.value.trim()){
		prompt(hpzkCf,"驾驶室后排载客不一样，请填写正确。");
		return false;
	}
	else{
		removePrompt(hpzkCf);
	}
	
	// 跳转到发票信息项页面
	ycTable('tabCc5');
}

// 发票信息项
/* 
function fpxxClick(){
	//非空验证
	var netFph = document.getElementById("ptdo_netFph");
	if(checknotnull(netFph,'请填写发票交易号') != "true"){
		return false;
	}
	
	if(containSpecial(netFph,"发票交易号中，包含特殊字符，请先删除!") == "false"){
		return false;
	}
	
	var nszm = document.getElementById("ptdo_nszm");
	if(checknotnull(nszm,'请填写纳税证明') != "true"){
		return false;
	}
	if(containSpecial(nszm,"纳税证明中，包含特殊字符，请先删除!") == "false"){
		return false;
	}
	
	var nszmbh = document.getElementById("ptdo_nszmbh");
	if(checknotnull(nszmbh,'请填写纳税证明编号') != "true"){
		return false;
	}
	if(containSpecial(nszmbh,"纳税证明编号中，包含特殊字符，请先删除!") == "false"){
		return false;
	}
	
	var xsdw = document.getElementById("ptdo_xsdw");
	if(containSpecial(xsdw,"销售单位中，包含特殊字符，请先删除!") == "false"){
		return false;
	}
	
	// 跳转到发票信息重复页面
	ycTable('tabCc6');
}

// 发票信息重复项
function fpxxCfClick(){
	// 发票交易号 是否一样
	var netFphCf = document.getElementById("netFphCf");
	var netFph = document.getElementById("ptdo_netFph").value;
	
	if(netFph != netFphCf.value.trim()){
		prompt(netFphCf,"发票交易号不一样，请填写正确。");
		return false;
	}
	else{
		removePrompt(netFphCf);
	}
	
	// 纳税证明 是否一样
	var nszmCf = document.getElementById("nszmCf");
	var nszm = document.getElementById("ptdo_nszm").value;
	
	if(nszm != nszmCf.value.trim()){
		prompt(nszmCf,"纳税证明不一样，请填写正确。");
		return false;
	}
	else{
		removePrompt(nszmCf);
	}
	
	// 纳税证明编号 是否一样
	var nszmbhCf = document.getElementById("nszmbhCf");
	var nszmbh = document.getElementById("ptdo_nszmbh").value;
	
	if(nszmbh != nszmbhCf.value.trim()){
		prompt(nszmbhCf,"纳税证明编号不一样，请填写正确。");
		return false;
	}
	else{
		removePrompt(nszmbhCf);
	}
	
	// 销售单位 是否一样
	var xsdwCf = document.getElementById("xsdwCf");
	var xsdw = document.getElementById("ptdo_xsdw").value;
	
	if(xsdw != xsdwCf.value.trim()){
		prompt(xsdwCf,"销售单位不一样，请填写正确。");
		return false;
	}
	else{
		removePrompt(xsdwCf);
	}
	
	// 跳转到代理人页面
	ycTable('tabCc7');
}*/

// 代理人信息
function dlrxxCheck(){
	
	//非空验证
	var netXm = document.getElementById("ptdo_netXm");
	if(checknotnull(netXm,'请填写代理人姓名') != "true"){
		return false;
	}
	if(containSpecial(netXm,"代理人姓名中，包含特殊字符，请先删除!") == "false"){
		return false;
	}
	
	var netLxdh = document.getElementById("ptdo_netLxdh");
	if(checknotnull(netLxdh,'请填写联系电话') != "true"){
		return false;
	}
	if(containSpecial(netLxdh,"联系电话中，包含特殊字符，请先删除!") == "false"){
		return false;
	}
	
	var netYjdz = document.getElementById("ptdo_netYjdz");
	if(checknotnull(netYjdz,'请填写邮寄地址') != "true"){
		return false;
	}
	if(containSpecial(netYjdz,"邮寄地址中，包含特殊字符，请先删除!") == "false"){
		return false;
	}
	
	var netDzxx = document.getElementById("ptdo_netDzxx");
	if(netDzxx.value != "" && netDzxx.value.trim() != "" && netDzxx.value != null
			&& netDzxx.value != undefined){
		if(checkemail(netDzxx.value) == "false"){
			prompt(netDzxx,"填写正确的邮箱。");
			return false;
		}else{
			removePrompt(netDzxx);
		}
	}
	else{
		removePrompt(netDzxx);
	}
	
	var netJbrxm = document.getElementById("ptdo_netJbrxm");
	if(checknotnull(netJbrxm,'请填写经办人姓名') != "true"){
		return false;
	}
	if(containSpecial(netJbrxm,"经办人姓名中，包含特殊字符，请先删除!") == "false"){
		return false;
	}
	
	var netJbrlxdh = document.getElementById("ptdo_netJbrlxdh");
	if(checknotnull(netJbrlxdh,'请填写经办人联系电话') != "true"){
		return false;
	}
	if(containSpecial(netJbrlxdh,"经办人联系电话中，包含特殊字符，请先删除!") == "false"){
		return false;
	}
	
	// 跳转到代理人信息重复页面
	//ycTable('tabCc6');
}

// 代理人信息重复
function dlrxxCfCheck(){
	
	// 代理人姓名 是否一样
	var netXmCf = document.getElementById("netXmCf");
	var netXm = document.getElementById("ptdo_netXm").value;
	
	if(netXm != netXmCf.value.trim()){
		prompt(netXmCf,"代理人姓名不一样，请填写正确。");
		return false;
	}
	else{
		removePrompt(netXmCf);
	}
	
	// 联系电话 是否一样
	var netLxdhCf = document.getElementById("netLxdhCf");
	var netLxdh = document.getElementById("ptdo_netLxdh").value;
	
	if(netLxdh != netLxdhCf.value.trim()){
		prompt(netLxdhCf,"联系电话不一样，请填写正确。");
		return false;
	}
	else{
		removePrompt(netLxdhCf);
	}
	
	// 邮政编码 是否一样
	var netYzbmCf = document.getElementById("netYzbmCf");
	var netYzbm = document.getElementById("ptdo_netYzbm").value;
	
	if(netYzbm != netYzbmCf.value.trim()){
		prompt(netYzbmCf,"邮政编码不一样，请选择正确。");
		return false;
	}
	else{
		removePrompt(netYzbmCf);
	}
	
	// 邮寄地址 是否一样
	var netYjdzCf = document.getElementById("netYjdzCf");
	var netYjdz = document.getElementById("ptdo_netYjdz").value;
	
	if(netYjdz != netYjdzCf.value.trim()){
		prompt(netYjdzCf,"邮寄地址不一样，请填写正确。");
		return false;
	}
	else{
		removePrompt(netYjdzCf);
	}
	
	// 电子邮箱 是否一样
	var netDzxxCf = document.getElementById("netDzxxCf");
	var netDzxx = document.getElementById("ptdo_netDzxx").value;
	
	if(netDzxx != netDzxxCf.value.trim()){
		prompt(netDzxxCf,"电子邮箱不一样，请填写正确。");
		return false;
	}
	else{
		removePrompt(netDzxxCf);
	}
	
	// 经办人姓名 是否一样
	var netJbrxmCf = document.getElementById("netJbrxmCf");
	var netJbrxm = document.getElementById("ptdo_netJbrxm").value;
	
	if(netJbrxm != netJbrxmCf.value.trim()){
		prompt(netJbrxmCf,"经办人姓名不一样，请填写正确。");
		return false;
	}
	else{
		removePrompt(netJbrxmCf);
	}
	
	// 经办人联系电话 是否一样
	var netJbrlxdhCf = document.getElementById("netJbrlxdhCf");
	var netJbrlxdh = document.getElementById("ptdo_netJbrlxdh").value;
	
	if(netJbrlxdh != netJbrlxdhCf.value.trim()){
		prompt(netJbrlxdhCf,"经办人联系电话不一样，请填写正确。");
		return false;
	}
	else{
		removePrompt(netJbrlxdhCf);
	}
	
	// 跳转到邮寄信息页面
	ycTable('tabCc7');
	
}

// 返回按钮事件
function fhClick(obj){
	ycTable("tabCc"+obj);
}

// 隐藏所有层，显示参数1 。
function ycTable(divId){
	
	/*for(var i = 1; i <= 7; i++){
		document.getElementById("tabCc"+i).style.display = "none";
		document.getElementById("tabCc"+i+"Th").className = ""
	}*/
	document.getElementById("tabCc1").style.display = "none";
	document.getElementById("tabCc1Th").className = ""
	document.getElementById("tabCc3").style.display = "none";
	document.getElementById("tabCc3Th").className = ""
	document.getElementById("tabCc5").style.display = "none";
	document.getElementById("tabCc5Th").className = ""
	document.getElementById("tabCc7").style.display = "none";
	document.getElementById("tabCc7Th").className = ""
		
	document.getElementById(divId).style.display = "block";
	document.getElementById(divId+"Th").className = "thColor"
}

// 隐藏所有层，显示参数1 。
function ycTable2(divId){
	
	/*for(var i = 1; i <= 7; i++){
		document.getElementById("tabCc"+i).style.display = "none";
		document.getElementById("tabCc"+i+"Th").className = ""
	}*/
	document.getElementById("tabCc1").style.display = "none";
	document.getElementById("tabCc1Th").className = ""
	document.getElementById("tabCc3").style.display = "none";
	document.getElementById("tabCc3Th").className = ""
		
	document.getElementById(divId).style.display = "block";
	document.getElementById(divId+"Th").className = "thColor"
}

//判断车辆识别代号是否合法
function checkVIN_JYW(strClsbdh){ 
    var strLetter;
    var arrayValue = new Array(1,2,3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5, 7, 9, 2, 3, 4, 5, 6, 7, 8, 9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
    var arrayIndex = new Array(8, 7, 6, 5, 4, 3, 2, 10, 0, 9, 8, 7, 6, 5, 4, 3, 2);
    var iSum;
    var SingleLetter;
    var checkValue;
    var strCheckValue;
    strLetter = "ABCDEFGHJKLMNPRSTUVWXYZ0123456789";
    iSum = 0;
    SingleLetter = "";
    for (var i = 0; i <= 16 ; i++)
    {
        iSum = iSum + arrayValue[strLetter.indexOf(strClsbdh.substring(i, i+1))] * arrayIndex[i];
    }
    checkValue = iSum%11;
    strCheckValue = checkValue;
    if(checkValue == 10)
    {
        strCheckValue = "X";
    }
    if( strClsbdh.substring( 8, 9) != strCheckValue)
    { return  false;}
    else
    {
        return true;
    }
}