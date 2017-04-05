var html="";
var trzblxhtml = "";
$(document).ready(function(){
	//禁用回车自动提交
	$("input[type='text']").each(function(){
		$(this).keypress(function(e){
			var key = window.event?e.keyCode:e.which;
			if(key.toString() == '13'){
				return false;
			}
		});
	});
	
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
	
	$("#btnsplx").click(function(e){
		$("#dsrsfzmmc").hide();
		$("#dbrsfzmmc").hide();
		$("#hpzl").hide();
		$("#ddlOrgCom").hide();
		$("#dbrddlOrgCom").hide();
		e.stopPropagation();
		//设置弹出层位置
	    var offset = $(e.target).offset();
	    //设置弹出层位置在点击的下面
	    $("#dangport").css({top:offset.top + $(e.target).height()+5 + "px", left:103});
	    $("#dangport").show();
	    //加载业务类型和业务原因
	    var ywlxhtml = $("#ywlxdiv").html();
	    if(ywlxhtml == null || ywlxhtml == ''){
	    	var list = getYwlx('', '', 'JDCYWSL', 'VEH_YWLX');
	   		var data = createYwlx(list);
	    	$("#ywlxdiv").html(data.join(''));
	    }
	 });
	
	$("#ywlxhtml").click(function(e){
		$("#dsrsfzmmc").hide();
		$("#dbrsfzmmc").hide();
		$("#hpzl").hide();
		$("#ddlOrgCom").hide();
		$("#dbrddlOrgCom").hide();
		e.stopPropagation();
		//设置弹出层位置
	    var offset = $(e.target).offset();
	    //设置弹出层位置在点击的下面
	    $("#dangport").css({top:offset.top + $(e.target).height()+5 + "px", left:103});
	    $("#dangport").show();
	    //加载业务类型和业务原因
	    var ywlxhtml = $("#ywlxdiv").html();
	    if(ywlxhtml == null || ywlxhtml == ''){
	    	var list = getYwlx('', '', 'JDCYWSL', 'VEH_YWLX');
	   		var data = createYwlx(list);
	    	$("#ywlxdiv").html(data.join(''));
	    }
	 });
	
	$("input[name='dbZjxxb.bllx']").change(function(){
		if($(this).val() == 2){
			$("#dbrsfzmtd").show();
			$("#tddbrzjgsfzm").show();
			$("#sfz_b2").removeAttr("disabled");
			$("#zzjgz2").removeAttr("disabled");
		}else{
			$("#dbrsfzmtd").hide();
			$("#tddbrzjgsfzm").hide();
			$("#sfz_b2").attr("disabled", "disabled");
			$("#zzjgz2").attr("disabled", "disabled");

			cleardbrxx();
		}
		$("#slgDrvXxcjbsfzmhmdbr").val("");
		$("#slgDrvXxcjbxmdbr").val("");
	});
	
});


function changedsrsfzmmc(){
	//cleardsrxx();
	cleardbrxx();
	
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
		document.getElementById("dsrsfzmhm").disabled = true;
		document.getElementById("dsrxm").disabled = true;
		document.getElementById("dsrzzjgdm").disabled = false;
		document.getElementById("dsrdwmc").disabled = false;
	}else{
		$("#dsrrtablesfz").show();
		$("#dsrtablezzjgz").hide();
		document.getElementById("dsrsfzmhm").disabled = false;
		document.getElementById("dsrxm").disabled = false;
		document.getElementById("dsrzzjgdm").disabled = true;
		document.getElementById("dsrdwmc").disabled = true;
	}
	
}

function changedbrsfzmmc(){
	cleardbrxx();
	var sfzmmc = $("#dbrsfzmmc").find("option:selected").val();
	if(sfzmmc == 'B'){
		$("#dbrtablesfz").hide();
		$("#dbrtablezzjgz").show();
		document.getElementById("dbrsfzmhm").disabled = true;
		document.getElementById("dbrxm").disabled = true;
		document.getElementById("dbrzzjgdm").disabled = false;
		document.getElementById("dbrdwmc").disabled = false;
	}else{
		$("#dbrtablesfz").show();
		$("#dbrtablezzjgz").hide();
		document.getElementById("dbrsfzmhm").disabled = false;
		document.getElementById("dbrxm").disabled = false;
		document.getElementById("dbrzzjgdm").disabled = true;
		document.getElementById("dbrdwmc").disabled = true;
	}
}


function clearform(val, dsrreset, dbrreset, bllx){
	if(val == "1"){
		$("input[type=radio][name='dbZjxxb.bllx'][value='1']").attr("checked",true);
		//代办人身份验证和机构验证框隐藏
		$("#dbrsfzmtd").hide();
		$("#tddbrzjgsfzm").hide();
		
		//隐藏域清空
		$("#chfile1").val("0");
		$("#chfile2").val("0");
		$("#chfile01").val("0");
		$("#chfile02").val("0");
		
		$("#hphm").val("B");
		$("#hpzl").val("02");
		$("#lsh").val("");
		$("#lshval").val("");
		$("#hphm").removeAttr("readonly");
		$("#hpzl").removeAttr("disabled");
		
		$("#zblx").val("");
		$("#zbh").val("");
		$("#tyblsh").val("");
		$("#cllx").val("");
		$("#syxz").val("");
		$("#zjxxblsh").val("");
		$("#jhzbh").val("");
		$("#sffq").val("");
		$("#zczxhfhljzyqchzx").val("");
		$("#zbyzflag").val("0");
		$("#hdfs").val("");
		$("#gzh").val("");
		$("#clsbdhval").val("");
		$("#vehflowywlx").val("");
		$("#vehflowywyy").val("");
		$("#alterinfo").val("");
		
		clearywlxandyy();
		clearDjAndJs();
		$("#dydjli").hide();
		document.getElementById("img01").src=path+'/images/cp.gif';
		document.getElementById("img02").src=path+'/images/cp.gif';
		document.getElementById("ReadResult2").innerHTML="等待抓拍...";
		//document.getElementById("file01").outerHTML = document.getElementById("file01").outerHTML;
		//document.getElementById("file02").outerHTML = document.getElementById("file02").outerHTML;
	}
	if(dsrreset == 1){
			
	}else{
		$("#dsrsfzmmc").val('A');
		cleardsrxx();
		//身份证和机构隐藏和显示
		$("#dsrrtablesfz").show();
		$("#dsrtablezzjgz").hide();
	}
	if(dbrreset == 1){
		if(bllx == 2){
			$("input[type=radio][name='dbZjxxb.bllx'][value='2']").attr("checked",true);
			$("#dbrsfzmtd").show();
			$("#tddbrzjgsfzm").show();
		}else{
			cleardbrxx();
			$("#dbrsfzmmc").val('A');
			$("#dbrtablesfz").show();
		    $("#dbrtablezzjgz").hide();
		    $("#sfz_b2").attr("disabled", "disabled");
			$("#zzjgz2").attr("disabled", "disabled");
		}
	}else{
		cleardbrxx();
		$("#dbrsfzmmc").val('A');
		$("#dbrtablesfz").show();
		$("#dbrtablezzjgz").hide();
		$("#sfz_b2").attr("disabled", "disabled");
		$("#zzjgz2").attr("disabled", "disabled");
	}
	$("#xczpdate").val("");
	$("#zjzpdate").val("");
	//清空读取证件后readonly
	$("#dsrzzjgdm").removeAttr("readonly");
	$("#dsrdwmc").removeAttr("readonly");
	$("#dbrzzjgdm").removeAttr("readonly");
	$("#dbrdwmc").removeAttr("readonly");
	
	$("#dsrsfzmhm").removeAttr("readonly");
	$("#dsrxm").removeAttr("readonly");
	$("#dbrsfzmhm").removeAttr("readonly");
	$("#dbrxm").removeAttr("readonly");
}

function clearform2(){
	$("#dsrsfzmmc").val('A');
	$("#dbrsfzmmc").val('A');
	$("#lsh").val("");
	$("#lshval").val("");
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
	
	$("#hphm").val("B");
	$("#hpzl").val("02");
	$("#hphm").removeAttr("readonly");
	$("#hpzl").removeAttr("disabled");
	
	$("#zblx").val("");
	$("#zbh").val("");
	$("#tyblsh").val("");
	$("#cllx").val("");
	$("#syxz").val("");
	$("#zjxxblsh").val("");
	$("#jhzbh").val("");
	$("#sffq").val("");
	$("#zczxhfhljzyqchzx").val("");
	$("#zbyzflag").val("0");
	$("#hdfs").val("");
	$("#gzh").val("");
	$("#clsbdhval").val("");
	$("#vehflowywlx").val("");
	$("#vehflowywyy").val("");
	$("#alterinfo").val("");
	
	clearDjAndJs();
	document.getElementById("img01").src=path+'/images/cp.gif';
	document.getElementById("img02").src=path+'/images/cp.gif';
	document.getElementById("ReadResult2").innerHTML="等待抓拍...";
	//document.getElementById("file01").outerHTML = document.getElementById("file01").outerHTML;
	//document.getElementById("file02").outerHTML = document.getElementById("file02").outerHTML;
	cleardsrxx();
	cleardbrxx();
	$("#xczpdate").val();
	$("#zjzpdate").val();
}

function clearlsh(){
	$("#lsh").val("");
	$("#lshval").val("");
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
	
	$("#dsrLxdh").val("");
	$("#dsrLxdz").val("");
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
	
	$("#dsrzzjgdm").removeAttr("readonly");
	$("#dsrdwmc").removeAttr("readonly");
	
	$("#dsrsfzmhm").removeAttr("readonly");
	$("#dsrxm").removeAttr("readonly");
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
	
	$("#dbrzzjgdm").removeAttr("readonly");
	$("#dbrdwmc").removeAttr("readonly");
	
	$("#dbrsfzmhm").removeAttr("readonly");
	$("#dbrxm").removeAttr("readonly");
	document.getElementById("sfzxpimgid2").src=path+'/images/cp.gif';
	document.getElementById("file2").outerHTML = document.getElementById("file2").outerHTML;
	document.getElementById("ReadResult1").innerHTML="等待读取...";
	document.getElementById("ReadResultj2").innerHTML="等待读取...";
}

/**
 * 清空登记信息和技术参数、抵押登记
 */
function clearDjAndJs(){
	$("#tableid1 input").val("");
	$("#tableid1 select").val("");
	$("#tableid2 input").val("");
	$("#tableid2 select").val("");
	$("#tableid3 select").val("");
	$("input[name='dydjYwsbspb.ywlx']").each(function(){
		$(this).attr("checked", false);
	});
	$("input[name='dydjYwsbspb.sqlx']").each(function(){
		$(this).attr("checked", false);
	});
	$("input[name='bllx']").each(function(){
		$(this).attr("checked", false);
	});
}

//清空车辆限购信息
function clearclxginfo(){
	$("#zblx").val("");
	$("#zbh").val("");
	$("#cllx").val("");
	$("#jhzbh").val("");
	$("#sffq").val("");
	$("#syxz").val("");
	$("#zczxhfhljzyqchzx").val("");
	$("#hdfs").val("");
	$("#gzh").val("");
	$("#clsbdhval").val("");
	$("#tyblsh").val("");
	$("#zjxxblsh").val("");
	$("#zbyzflag").val("");
	$("#vehflowywlx").val("");
	$("#vehflowywyy").val("");
}


/**
 * 查询业务类型
 * @param {Object} dmz
 * @param {Object} dmms2
 * @param {Object} dmlb
 * @param {Object} veh_drv
 * @return {TypeName} 
 */
function getYwlx(dmz, dmms2, dmlb, veh_drv){
	var list ;
	$.ajax({
		cache:false,
		async:false,
		url:path+'/drv_ajax/ywlxajax.action',
		type:'post',
		data:{"dmz":dmz, "dmms2":dmms2, "dmlb":dmlb, "veh_drv":veh_drv, "flag": "0"},
		dataType:'json',
		error: function(){
			alert("请求异常，请稍后再试!");
		},
		success: function(result){
			list = result;
		}
   	 });
	return list;	
}

function createYwlx(list){
	var data = new Array();
	var ywyy = new Array();
	if(list != null && list.length > 0){
		data.push("<table width='650' id='tabywlx' class='tabywlx' cellpadding='0' cellspacing='0' style='text-align:left;'>");
		var index = 0;
		var ywyyindex = 0;
		var cha = 1;
		var inputtype = "checkbox";
		//判断最后一条数据是否有原因（子）
		var tempobj = list[list.length -1];
		var lastlist = getYwlx('',tempobj.dmz, 'JDCYWSL', 'VEH_YWYY');
		if(lastlist != null){
			cha = 2;
		}
		for(var i = 0; i < list.length; i++){
			ischeck = "";
			var obj = list[i];
			var list2 = getYwlx('',obj.dmz, 'JDCYWSL', 'VEH_YWYY');
			if(list2 != null){
				ywyy.push("<tr class='tdywyy"+ywyyindex+"'>");
				if(obj.bz == "1"){
					ywyy.push("<td colspan='4' class='tdright'><input type='radio' name='ywlxradio' onclick='ywlxradioclick(this);' value='"+obj.dmms1+"'/><input type='hidden' name='ywyy"+ywyyindex+"' id='ywyy"+ywyyindex+"' value='"+obj.dmz+"' title='"+obj.dmms1+"'/><font color='blue'>"+obj.dmms1+"</font>(");
				}else{
					ywyy.push("<td colspan='4' class='tdright'><input type='radio' name='ywlxradio' onclick='ywlxradioclick(this);' value='"+obj.dmms1+"'/><input type='hidden' name='ywyy"+ywyyindex+"' id='ywyy"+ywyyindex+"' value='"+obj.dmz+"' title='"+obj.dmms1+"'/>"+obj.dmms1+"(");
				}
				if(obj.dmz == 'D' || obj.dmz == 'EE'){
					inputtype = "radio";
				}else{
					inputtype = "checkbox";
				}
				for(var j = 0; j < list2.length; j++){
					var temp = list2[j];
					if(temp.bz == "1"){
						ywyy.push("<input type='"+inputtype+"' name='chkywyy' onclick='checkonclick(this);' disabled='disabled' value='"+obj.dmz+":"+temp.dmz+"' title='"+temp.dmms1+"'/><font color='blue'>"+temp.dmms1+"</font>&nbsp;&nbsp;");
					}else{
						ywyy.push("<input type='"+inputtype+"' name='chkywyy' onclick='checkonclick(this);' disabled='disabled' value='"+obj.dmz+":"+temp.dmz+"' title='"+temp.dmms1+"'/>"+temp.dmms1+"&nbsp;&nbsp;");
					}
				}
				ywyy.push(")</td>");
				ywyy.push("</tr>");
				ywyyindex++;
			}else{
				var cols = 0;
				if((index+1)%4 == 1){
					data.push("<tr>");
				}
				//判断没有业务原因的业务类型是否是最后一条，是最后一条要计算跨列
				if(i == list.length-cha){
					var num = (index+1)%4;
					if( num == 1){
						cols = 4;
					}else if(num == 2){
						cols = 3;
					}else if(num = 3){
						cols = 2;
					}
				}
				if(cols != 0){
					if(obj.bz == "1"){
						data.push("<td colspan='"+cols+"' class='tdright'><input type='radio' name='chkywlx' onclick='checkboxonclick();' value='"+obj.dmz+"' title='"+obj.dmms1+"'/><font color='blue'>"+obj.dmms1+"</font></td>");
					}else{
						data.push("<td colspan='"+cols+"' class='tdright'><input type='radio' name='chkywlx' onclick='checkboxonclick();' value='"+obj.dmz+"' title='"+obj.dmms1+"'/>"+obj.dmms1+"</td>");
					}
				}else{
					if(obj.bz == "1"){
						data.push("<td class='tdright'><input type='radio' name='chkywlx' onclick='checkboxonclick();' value='"+obj.dmz+"' title='"+obj.dmms1+"'/><font color='blue'>"+obj.dmms1+"</font></td>");
					}else{
						data.push("<td class='tdright'><input type='radio' name='chkywlx' onclick='checkboxonclick();' value='"+obj.dmz+"' title='"+obj.dmms1+"'/>"+obj.dmms1+"</td>");
					}
					
				}
				
				if((index+1)%4 == 0){
					data.push("</tr>");
				}else{
					if(i == list.length-1){
						data.push("</tr>");
					}
				}
				index++;
			}
		}
		var trhtml = ywyy.join('');
		data.push(trhtml);
		data.push("</table>");
		$("#ywyysize").val(ywyyindex);
	}
	return data;
}

function getDtpzByYwlx(ywlx){
	//根据传过来的业务类型去字典展示页面动态图拍摄区域(临时号牌)
	var result = "";
	$.ajax({
		cache:false,
		async:false,
		url:path+'/veh_ajax/vehAjax_getDtpzByYwlx.action',
		type:'post',
		data:{"ywlx":ywlx},
		dataType:'html',
		success: function(data){
			var cv =$.parseJSON($.trim(data));
			$("#filecount").val(cv.length);
			var message = data;
			if(message.indexOf('异常信息') == -1){
				  //result +="<table>";
				  //result +="<tr>";
				  result +="<div class=\"box\">";
				  //result +="<div class=\"content\">";
				  result +="<ul>";
				  $.each($.parseJSON($.trim(message)),function(i,value){
					  var index = value.id;
					  var src = value.url;
					  //result +="<td>";
					  result +="<li>";
					  result +="<table class=\"edittable\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\">";
					  result +="<input type=\"hidden\" name=\"gpyid"+i+"\" id=\"gpyid"+i+"\" value=\""+index+"\"/>";
					  result +="<input type=\"hidden\" name=\"gpytitle"+i+"\" id=\"gpytitle"+i+"\" value=\""+value.title+"\"/>";
					  result +="<input type=\"hidden\" name=\"chfile"+i+"\" id=\"chfile"+i+"\" value=\"\"/>";
					  result +="<tr>";
					  result +="<td style=\"width: 25%\" height=\"36\" align=\"center\">"+value.title+"</td>";
					  result +="</tr>";
					  result +="<tr>";
					  result +="<td style=\"width: 25%\" align=\"center\">";
					  result +="<a id=\"gpyimg"+i+"a\" href=\"#\" title=\"高拍仪图片\"><img src=\"../../ycszhyw/images/cp.gif\" id=\"gpyimg"+i+"\" width=\"210\" height=\"175\" border=\"0\"></a>";
					  result +="</td>";
					  result +="</tr>";
					  result +="<tr>";
					  result +="<td style=\"width: 25%\" width=\"102\" align=\"center\">";
					  result +="<input type=\"file\" name=\"gpyfile"+i+"\" id=\"gpyfile"+i+"\" class=\"disabled\" title=\"请选择高拍仪图片路径:"+value.url+"\" style=\"width:210px;\" />";
					  result +="</td>";
					  result +="</tr>";
					  result +="<tr>";
					  result +="<td style=\"width: 25%\" width=\"102\" align=\"center\">";
					  result +="<input type=\"button\" style=\"cursor:pointer;\" name=\"zp\" id=\"zp\" value=\"拍照\" class=\"bnt\" onclick=\"getGpyMessage("+i+",'"+src+"');\">";
					  result +="</td>";
					  result +="</tr>";
					  result +="</table>";
					  result +="</li>";
					  //result +="</td>";
				  });
				  result +="</ul>";
				  //result +="</div>";
				  result +="</div>";
				  //result +="</tr>";
				  //result +="</table>";
			}else{
				exception(message);
			}
		}
	});
	$("#pztd").html(result);
	if($("#filecount").val()>0){
		$("#psdiv").show();
	}
}

/**
 * 单选按钮事件
 * @memberOf {TypeName} 
 */
function checkboxonclick(){
	$("#psdiv").hide();
	var ywlx = "";
	var ywlxhtml = "";
	var size = $("#ywyysize").val();
	$("input[name=chkywyy]").each(function(){
		$(this).attr("checked", false);
	});
	$("input[name='ywlxradio']").each(function(){
		$(this).attr("checked", false);
	});
	var size = $("#ywyysize").val();
	for(var i = 0; i < size; i++){
		$(".tdywyy"+i+" input[name=chkywyy]").each(function(){
			$(this).attr("disabled", true);
		});
	}
	$("input[name=chkywlx][checked]").each(function(){
		ywlx += $(this).val()+",";
		ywlxhtml += $(this).attr("title")+",";
	});
	for(var i = 0; i < size; i++){
		if($(".tdywyy"+i+" input[name=chkywyy][checked]").length > 0){
			var ywlxval = $("#ywyy"+i).attr("title");
			var ywlxvalue = $("#ywyy"+i).val();
			ywlx += ywlxvalue+",";
			ywlxhtml += ywlxval+"(";
			$(".tdywyy"+i+" input[name=chkywyy][checked]").each(function(){
				ywlxhtml += $(this).attr("title")+",";
			});
			ywlxhtml = ywlxhtml.substring(0,ywlxhtml.length-1);
			ywlxhtml += "),";
		}
		
	}
	if(ywlx.length > 1){
		ywlx = ywlx.substring(0,ywlx.length-1);
	}
	if(ywlxhtml.length > 1){
		ywlxhtml = ywlxhtml.substring(0,ywlxhtml.length-1);
	}
	getDtpzByYwlx(ywlx);
	//业务类型，临时号牌
	if(ywlxhtml=="临时号牌"){
		$("#hpzl").attr('value','22'); //临时行驶车
		$("#ptdoclsbdh").attr("disabled", false);
		$("#ptdoclsbdh").attr("style", "");
	}else if(ywlxhtml=="转入业务"){
		$("#ptdoclsbdh").attr("disabled", false);
		$("#ptdoclsbdh").attr("style", "");
	}else{
		$("#ptdoclsbdh").attr("disabled", true);
		$("#ptdoclsbdh").attr("style", "background-color: #F1F1F1");
	}
	$("#ywlxhtml").val(ywlxhtml);
	$("#ywlx").val(ywlx);
	$("#ywyy").val("");
	//清空信息
	//clearform2();
	//clearlsh();
}

/**
 * 复选框事件
 */
function checkonclick(obj){
	var ywlx = "";
	var ywyy = "";
	var ywlxhtml = "";
	var ywyyhtml = "";
	var size = $("#ywyysize").val();
	$("input[name=chkywlx]").each(function(){
		$(this).attr("checked", false);
	});
	var classval = obj.parentNode.parentNode.className;
	var k = classval.substring(classval.length -1, classval.length);
	for(var i = 0; i < size; i++){
		if(i != k){
			$(".tdywyy"+i+" input[name=chkywyy]").each(function(){
				$(this).attr("checked", false);
			});
		}
	}
	$("input[name=chkywyy][checked]").each(function(){
		ywyy += $(this).val()+",";
	});
	for(var i = 0; i < size; i++){
		if(i != k){
			
		}else{
			if($(".tdywyy"+i+" input[name=chkywyy][checked]").length > 0){
				var ywlxval = $("#ywyy"+i).attr("title");
				var ywlxvalue = $("#ywyy"+i).val();
				ywlx += ywlxvalue+",";
				ywlxhtml += ywlxval+"(";
				$(".tdywyy"+i+" input[name=chkywyy][checked]").each(function(){
					ywlxhtml += $(this).attr("title")+",";
				});
				ywlxhtml = ywlxhtml.substring(0,ywlxhtml.length-1);
				ywlxhtml += "),";
			}
		}
	}
	if(ywlx.length > 1){
		ywlx = ywlx.substring(0,ywlx.length-1);
	}
	if(ywyy.length > 1){
		ywyy = ywyy.substring(0,ywyy.length-1);
	}
	if(ywlxhtml.length > 1){
		ywlxhtml = ywlxhtml.substring(0,ywlxhtml.length-1);
	}
	$("#ywlxhtml").val(ywlxhtml);
	$("#ywlx").val(ywlx);
	$("#ywyy").val(ywyy);
	getDtpzByYwlx(ywlx);
	//clearform2();
	//clearlsh();
}

/**
 * 有业务原因的单选按钮事件
 */
function ywlxradioclick(obj){
	$("input[name=chkywlx]").each(function(){
		$(this).attr("checked", false);
	});
	var size = $("#ywyysize").val();
	var classval = obj.parentNode.parentNode.className;
	var k = classval.substring(classval.length -1, classval.length);
	for(var i = 0; i < size; i++){
		if(i != k){
			$(".tdywyy"+i+" input[name=chkywyy]").each(function(){
				$(this).attr("disabled", true);
				$(this).attr("checked", false);
			});
			
		}else{
			$(".tdywyy"+i+" input[name=chkywyy]").each(function(){
				$(this).attr("disabled", false);
			});
		}
	}
	$("#ywlxhtml").val("");
	$("#ywlx").val("");
	$("#ywyy").val("");
	//clearlsh();
}

//清空业务类型和业务原因
function clearYwlxAndYwyy(){
	$("input[name=chkywlx]").each(function(){
		$(this).attr("checked", false);
	});
	$("input[name=chkywyy]").each(function(){
		$(this).attr("checked", false);
	});
	$("input[name='ywlxradio']").each(function(){
		$(this).attr("checked", false);
	});
	var size = $("#ywyysize").val();
	for(var i = 0; i < size; i++){
		$(".tdywyy"+i+" input[name=chkywyy]").each(function(){
			$(this).attr("disabled", true);
		});
	}
}

/**
 * 设置业务类型和业务原因
 */
function setYwlxAndYwyy(ywlx, ywyy){
	if(ywlx != null && ywlx != ""){
		$("input[name=chkywlx]").each(function(){
			if($(this).val() == ywlx){
				$(this).attr("checked", true);
			}
		});
		var ywyys = ywyy.split(',');
		var size = $("#ywyysize").val();
		if(ywyys != null && ywyys.length > 0){
			for(var k = 0; k < ywyys.length; k++){
				var ywyyval = ywlx+":"+ywyys[k];
				for(var i = 0; i < size; i++){
					$(".tdywyy"+i+" input[name=chkywyy]").each(function(){
						if($(this).val() == ywyyval){
							$(".tdywyy"+i+" input[name=chkywyy]").each(function(){
								$(this).attr("disabled", false);
							});
							$(this).attr("checked", true);
							$(".tdywyy"+i+" input[name=ywlxradio]").each(function(){
								$(this).attr("checked", true);
							});
						}
					});
				}
			}
		}
		var ywlxhtml="";
		$("input[name=chkywlx][checked]").each(function(){
			ywlxhtml += $(this).attr("title");
		});
		if(ywlxhtml == null || ywlxhtml == ""){
			$("input[name=ywlxradio][checked]").each(function(){
				ywlxhtml += $(this).val();
			});
		}
		ywlxhtml += "(";
		for(var i = 0; i < size; i++){
			if($(".tdywyy"+i+" input[name=chkywyy][checked]").length > 0){
				$(".tdywyy"+i+" input[name=chkywyy][checked]").each(function(){
					ywlxhtml += $(this).attr("title")+",";
				});
				ywlxhtml = ywlxhtml.substring(0,ywlxhtml.length-1);
				ywlxhtml += "),";
			}
		}
		if(ywlxhtml.length > 1){
			ywlxhtml = ywlxhtml.substring(0,ywlxhtml.length-1);
		}
		if(ywlxhtml.length == 1){
			ywlxhtml = "";
		}
		$("#ywlx").val(ywlx);
		ywyy = "";
		for(var j = 0; j < ywyys.length; j++){
			ywyy += ywlx+":"+ywyys[j];
			if(j != ywyys.length-1){
				ywyy += ",";
			}
		}
		$("#ywyy").val(ywyy);
		$("#ywlxhtml").val(ywlxhtml);
	}
	
}

/**
 * 清空业务类型和业务原因
 */
function clearywlxandyy(){
	$("#ywlxhtml").val("");
	$("#ywlxtext").val("");
	$("#ywlx").val("");
	$("#ywyy").val("");
	$("input[name=chkywlx]").each(function(){
		$(this).attr("checked", false);
	});
	$("input[name=chkywyy]").each(function(){
		$(this).attr("checked", false);
		$(this).attr("disabled", "disabled");
	});
	$("input[name='ywlxradio']").each(function(){
		$(this).attr("checked", false);
	});
}

function closeDiv(obj){
	$("#"+obj).hide();
	$("#dsrsfzmmc").show();
	$("#dbrsfzmmc").show();
	$("#hpzl").show();
	$("#ddlOrgCom").show();
	$("#dbrddlOrgCom").show();
}

//切换表格
function settab(obj,n){
	var tds = $("#test2_li_now_ li");
	if(n != 0){
		closeDiv('dangport');
	}
	for(var i=0;i < tds.length; i++){ 
		if(i != tds.length - 1){
			tds[i].className=i==n?"now":"";
			$("#tableid" + i).hide();
		}
//		tds[i].className=i==n?"now":"";
//		$("#tableid" + i).hide();
		
	}
	$("#tableid" + n).show();
}

function subforms(){
	//1、验证指标
	//1.1、验证是否需要输入指标类型和指标号
//	var isshuruzb = checkIsneedzb(hphmval, hpzlval, ywlx, ywyy, dsrsfzmhmblack, '');
//	if(isshuruzb == "error"){
//		return false;
//	}
//	var arrs = isshuruzb.split("+");
//	if(arrs[0] == '-1'){
//		alert("验证指标失败");
//		return false;
//	}
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
//		//$("#drv_form").submit();
//	}
//	
//	return false;
	
	var lsh = $("#lsh").val();
	var lshval = $("#lshval").val();
	var ywlx = document.getElementById("ywlxhtml").value;
	var clsbdh = $("#ptdoclsbdh").val();//车辆识别代号
	if(ywlx =="临时号牌" || ywlx =="转入业务"){
		var hpzl = $("#hpzl").val();
			if(hpzl!="22" && ywlx !="转入业务"){
			 alert("你选择的是临时号牌，号牌种类必须是：临时行驶车!");
		     return false;
		 }
		if(clsbdh == null || clsbdh == ""){
			 alert("车辆识别代码不能为空!");
			 return false;
		}
		if(clsbdh.length < 8){
			 alert("车辆识别代码必须为8位!");
			 return false;
		}
		var clsbdhTest =/^[A-Z0-9]+$/;
		    if(!clsbdh.match(clsbdhTest)){
		          alert("请正确填写车辆识别代码!");
		          return false;
		    }
	}
	var dydj = false;
	if(lsh != null && lsh != ""){
		if(lsh != lshval){
			alert("流水号已改变，请重新点击‘查询’按钮!");
			return false;
		}
		var first = lsh.substring(0,1);
		if(first == "D"){
			dydj = true;
		}
	}
	if(dydj){
		if(lshval == null || lshval == ''){
			alert("请点击‘查询’按钮调取抵押登记信息!");
			return false;
		}
		dydjsubmit();
	}else{
		//业务类型
		var ywlx = checknotnull2(document.getElementById("ywlxhtml"),'请选择业务类型',0);
		if (ywlx != "true") {
			return false;
		}
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
			var dsrdwmc = checknotnull2(document.getElementById("dsrdwmc"),'请输入当事人组织机构证单位法人',0);
			if (dsrdwmc != "true") {
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
//				else{
//					if(dsrsfzmhmval.length > 17){
//						alert("当事人身份证明号码必须小于18位!");
//						$("#slgDrvXxcjbsfzmhm").focus();
//						return false;
//					}
//				}
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
		
		//验证动态高拍仪
		var filecount = $("#filecount").val();
		if(filecount !=0){
			for(var i=0;i<filecount;i++){
			     if($("#chfile"+i).val()!="1"){
			    	 alert("请点击'拍照'按钮拍摄当事人证件照片!");
			    	 settab(this,0);
			    	 return false;
			     }
		    }
		}
		
		var dsrsfzmhm = $("#dsrsfzmhm").val();
		var dsrxm = $("#dsrxm").val();
		var dsrzjjgzhm = $("#dsrzzjgdm").val();
		var dsrdwfr = $("#dsrdwmc").val();
		var dbrsfzmhm = $("#dbrsfzmhm").val();
		var dbrxm = $("#dbrxm").val();
		var ywlx = $("#ywlx").val();
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
		var yuyue = getIsyuyue(lsh, hphmval, hpzlval, dsrsfzmhmblack, ywlx, ywyy, '1', '3', '', '',clsbdh);
		//yuyue = '1+成功';
		if(yuyue == "error"){
			return false;
		}
		//1 不弹出信息，能继续(预约正常)
		var yuyuearr = yuyue.split("+");
		if($.trim(yuyuearr[0]) == "0"){//0 弹出信息，不能继续
			alert(yuyuearr[1]);
			return false;
		}else if($.trim(yuyuearr[0]) == "2"){//2 弹出信息，可以继续
			alert(yuyuearr[1]);
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
							var lshval = $("#lshval").val();
							if(lshval == null || lshval == ""){
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
							}else{
								if(confirm("此次以流水号方式受理，是否确定受理?")){
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
						}
					}else{
						alert("告知用户该车由于10宗以上违法未处理已被锁定，请到机动训练大队接受专门调查和处理!");
						return false;
					}
				}else{
					var lshval = $("#lshval").val();
					if(lshval == null || lshval == ""){
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
					}else{
						if(confirm("此次以流水号方式受理，是否确定受理?")){
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
				}
				
			}else{
				alert('拍摄照片存在异常!');
				return false;
			}
		}
	}
	
}

//抵押登记受理
function dydjsubmit(){
	//业务类型
	var ywlx = checknotnull2(document.getElementById("ywlxhtml"),'请选择业务类型',0);
	if (ywlx != "true") {
		return false;
	}
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
	
	var lshval = $("#lshval").val();
	var dyrsfz = $("#dyrSfzCardno").val();
	var first = lshval.substring(0,1);
	if(first == "D"){
		if(dyrsfz == null || dyrsfz == ""){
			//当事人验证身份证明号码
			if(sfzmmc == 'B'){
				var dsrzzjgdm = checknotnull2(document.getElementById("dsrzzjgdm"),'请输入当事人组织机构证号码',0);
				if (dsrzzjgdm != "true") {
					return false;
				}
				var dsrdwmc = checknotnull2(document.getElementById("dsrdwmc"),'请输入当事人组织机构证单位法人',0);
				if (dsrdwmc != "true") {
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
					}else{
						if(dsrsfzmhmval.length > 17){
							alert("当事人身份证明号码必须小于18位!");
							$("#slgDrvXxcjbsfzmhm").focus();
							return false;
						}
					}
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
		}
	}
	
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
	
	//审批验证
	var dsrsfzmhm = $("#dsrsfzmhm").val();
	var dsrxm = $("#dsrxm").val();
	var dsrzjjgzhm = $("#dsrzzjgdm").val();
	var dsrdwfr = $("#dsrdwmc").val();
	var dbrsfzmhm = $("#dbrsfzmhm").val();
	var dbrxm = $("#dbrxm").val();
	var ywlx = $("#ywlx").val();
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
	
	var lshval = $("#lshval").val();
	var dyrsfz = $("#dyrSfzCardno").val();
	var first = lshval.substring(0,1);
	
	//强制预约验证
	var yuyue = getIsyuyue(lsh, hphmval, hpzlval, dsrsfzmhmblack, ywlx, ywyy, '1', '3', '', '','');
	if(yuyue == "error"){
		return false;
	}
	//1 不弹出信息，能继续(预约正常)
	var yuyuearr = yuyue.split("+");
	if($.trim(yuyuearr[0]) == "0"){//0 弹出信息，不能继续
		alert(yuyuearr[1]);
		return false;
	}else if($.trim(yuyuearr[0]) == "2"){//2 弹出信息，可以继续
		alert(yuyuearr[1]);
	}
	
	//验证验车
//	if(lsh != null && lsh != ''){
//		var yanche = checkYanche(lsh);
//		if(yanche == "error" || yanche == ''){
//			return false;
//		}
//		if(yanche == '0'){
//			alert("不能受理，登录异常");
//			return false;
//		}else if(yanche == '2'){
//			alert("机动车管理科只能受理审验科验车的流水号数据");
//			return false;
//		}else if(yanche == '3'){
//			alert("只能受理本部门验车的流水号数据,请核对验车部门");
//			return false;
//		}
//	}
	
	if(first == "D"){
		//如果dyrsfz为空说明是车主自行办理，需要读取证件信息
		if(dyrsfz == null || dyrsfz == ""){
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
		}
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
	var bz = $("#bz").val();
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
//			if(ismyself == '2')
//			{
//				//验证是否备案
//				var isbeian = yansfbeian(dbrsfzmhm);
//				if(isbeian == '3'){
//					return false;
//				}else if(isbeian == '1'){
//					//没有备案
//					var isposp = yanpeioshenp(dsrsfzmhm, dbrsfzmhm, hphmval, hpzlval);
//					if(isposp == '3'){
//						return false;
//					}else if(isposp == '1'){
//						alert("你好，现录入的代办人未在车管部门备案，请尽快备齐资料申请备案!现为车管业务代办机构及人员备案工作期间，对尚没有备案的代办人，暂允许数据正常录入，当前录入信息有效!");
//					}
//				}
//			}
			
			//var isten = isWfwclTen(hphmval, hpzlval, '');
			var isten = "0";
			if(isten == "error"){
				return false;
			}
			if(isten != "0"){
				if(isten == '2'){
					if(confirm('该车由于10宗以上违法未处理已被锁定，请认真核实当事人信息后再确定保存。')){
						var lshval = $("#lshval").val();
						if(lshval == null || lshval == ""){
							if(confirm("此次以车牌号方式受理，是否确定受理?")){
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
						}else{
							if(confirm("此次以流水号方式受理，是否确定受理?")){
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
				var lshval = $("#lshval").val();
				if(lshval == null || lshval == ""){
					if(confirm("此次以车牌号方式受理，是否确定受理?")){
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
				}else{
					if(confirm("此次以流水号方式受理，是否确定受理?")){
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

function spyanzheng(sfzmhm, xm, splx, splx2, yxsj){
	var istrue;
	$.ajax({
		cache:false,
		async:false,
		url: path+'/slgSpxx/slgSpxx_selSlgSpxxByCondition.action',
		type: 'post',
		data: {"sfzmhm": sfzmhm, "xm":xm, "splx": splx, "splx2":splx2, "yxsj":yxsj},
		dataType: 'json',
		success: function(result){
			var message = result+"";
			if(message.indexOf('异常信息') == -1){
				istrue = result;
			}else{
				exception(message);
				istrue = "3";
			}
		}
	});
	return istrue;
}

function getDengjiAndJishu(){
	var lsh = $("#lsh").val();
	if(lsh == null || lsh ==""){
		alert("请输入流水号");
		return false;
	}
	var imgurl = path+'/artDialog4.1.6/skins/icons/loading2.gif';
	chuli = art.dialog({
	    content: '<div style=\"text-align:center; text-indent:-999em; overflow:hidden; background:url('+imgurl+') no-repeat center center;\"><span>loading..</span></div><br/>数据处理中,请稍候.....',
	    title: '数据处理中',
		lock: true,
	    opacity: 0.87
	});
	clearYwlxAndYwyy();
	
	//如果流水号是以“D”开头，则是抵押登记业务，验证该流水号是否是本科级部门受理信息
	var first = lsh.substring(0,1);
	if(first == "D"){
		cleardsrxx();
		cleardbrxx();
		$.ajax({
			cache:false,
			async:false,
			url:path+"/dydj/yhsl_getDydjYwsbspByLsh.action",
			type:'post',
			data:{"lsh":lsh},
			dataType:'json',
			error:function(XmlHttpRequest,textStatus, errorThrown){
				closechuli();
				exception(XmlHttpRequest.responseText);
				return false;
			},
			success: function(result){
				var orgId = getOrgId();
				if(orgId == "error"){
					closechuli();
					return false;
				}
				if(result != null && result != ""){
					if(result.ycsDeptId == $.trim(orgId)){
						//判断有没有被受理过
						var cgYhdm = result.cgYhdm;
						if(cgYhdm != null && cgYhdm){
							alert("此流水号在"+result.cgYhsj+"时间,被"+result.cgYhxm+"已受理!");
							closechuli();
							return false;
						}
						
						//设置业务类型
						var ywlx = "EE";
						var ywyy = result.ywlx;
				    	setYwlxAndYwyy(ywlx, ywyy);
				    	getDtpzByYwlx(ywlx);
				    	$("#lshval").val(result.lsh);
				    	$("#hphm").attr("readonly", "readonly");
						$("#hpzl").attr("disabled", "disabled");
						$("#hphm").val(result.hphm);
						$("#hpzl").val(result.hpzl);
						$("input[name='dydjYwsbspb.ywlx'][value='"+result.ywlx+"']").attr("checked", true);
						$("input[name='dydjYwsbspb.sqlx'][value='"+result.sqlx+"']").attr("checked", true);
						$("#zhth").val(result.zhth);
						$("#dyhth").val(result.dyhth);
						$("#hphmdydj").val(result.hphm);
						$("#hpzldydj").val(result.hpzl);
						$("#clsbdh").val(result.clsbdh);
						$("#djzsbh").val(result.djzsbh);
						$("#ycsDeptId").val(result.ycsDeptId);
						if(result.sbzt == '6'){
							$("input[name='bllx'][value='0']").attr("checked", true);
						}else{
							$("input[name='bllx'][value='1']").attr("checked", true);
						}
						$("#qjQjrxm").val(result.qjQjrxm);
						$("#qjLxdh").val(result.qjLxdh);
						$("#qjTddz").val(result.qjTddz);
						$("#qjYzbm").val(result.qjYzbm);
						$("#yjSjrxm").val(result.yjSjrxm);
						$("#yjLxdh").val(result.yjLxdh);
						$("#yjTddz").val(result.yjTddz);
						$("#yjYzbm").val(result.yjYzbm);
						$("#yhZzjgZh").val(result.yhZzjgZh);
						$("#yhZzjgFrdb").val(result.yhZzjgFrdb);
						$("#yhZzjgYyzz").val(result.yhZzjgYyzz);
						$("#yhZzjgDwmc").val(result.yhZzjgDwmc);
						$("#yhZzjgNjrq").val(result.yhZzjgNjrq);
						$("#yhZzjgNjyxq").val(result.yhZzjgNjyxq);
						$("#yhZzjgDz").val(result.yhZzjgDz);
						$("#yhSfzCardname").val(result.yhSfzCardname);
						if(result.yhSfzCardphotoId != null && result.yhSfzCardphotoId != ""){
							$("#sfzxpimgid1").attr("src", path+"/dydj/yhsl_showImage.action?id="+result.yhSfzCardphotoId);
						}else{
							$("#sfzxpimgid1").attr("src", path+"/images/cp.gif");
						}
						$("#yhSfzCardsex").val(result.yhSfzCardsex);
						$("#yhSfzCardno").val(result.yhSfzCardno);
						$("#yhSfzCardaddress").val(result.yhSfzCardaddress);
						$("#dyrZzjgZh").val(result.dyrZzjgZh);
						$("#dyrZzjgFrdb").val(result.dyrZzjgFrdb);
						$("#dyrZzjgYyzz").val(result.dyrZzjgYyzz);
						$("#dyrZzjgDwmc").val(result.dyrZzjgDwmc);
						$("#dyrZzjgNjrq").val(result.dyrZzjgNjrq);
						$("#dyrZzjgNjyxq").val(result.dyrZzjgNjyxq);
						$("#dyrZzjgDz").val(result.dyrZzjgDz);
						$("#dyrSfzCardname").val(result.dyrSfzCardname);
						if(result.dyrSfzCardphotoId != null && result.dyrSfzCardphotoId != ""){
							$("#dydjsfzxpimgid").attr("src", path+"/dydj/yhsl_showImage.action?id="+result.dyrSfzCardphotoId);
						}else{
							$("#dydjsfzxpimgid").attr("src", path+"/images/cp.gif");
						}
						$("#dyrSfzCardsex").val(result.dyrSfzCardsex);
						$("#dyrSfzCardno").val(result.dyrSfzCardno);
						$("#dyrSfzCardaddress").val(result.dyrSfzCardaddress);
						
						$("#dsrsfzmhm").val(result.dyrSfzCardno);
						$("#dsrxm").val(result.dyrSfzCardname);
				    	$("#dydjli").show();
				    	closechuli();
					}else{
						closechuli();
						alert("您不能受理其他部门的信息!");
					}
				}else{
					closechuli();
					alert("该流水号不存在");
				}
				
			}
		});
	}else if(first == "L"){//如果流水号是L就调用临牌接口取值
		$("#dydjli").hide();
		lsh = lsh.substring(1,lsh.length);
		$.ajax({
			cache:false,
			async:false,
			url:path+'/veh_ajax/vehAjax_getTemporaryLicenseByLsh.action',
			type:'post',
			data:{"lsh":lsh},
			dataType:'json',
			success: function(result){
				var message = result+"";
				if(message.indexOf('异常信息') == -1){
					if(result.idNumber==null){
					  closechuli();
					  alert("调取临牌预约数据异常!");
					  return false;
				}else{
					getDtpzByYwlx('O');
					$("#dsrsfzmhm").val(result.idNumber);
					$("#dsrxm").val(result.name);
					$("#dsrzzjgdm").val(result.idNumber);
					$("#dsrdwmc").val(result.name);
					$("#dsrLxdh").val(result.phoneNumber);
					$("#dsrLxdz").val(result.address);
					$("#lshval").val("L"+result.bookNumber);
					
					$("#ptdosfzmmc").val('A');
					$("#ptdosfzmhm").val(result.idNumber);
					$("#ptdosyr").val(result.name);
					$("#ptdolxdh").val(result.phoneNumber);
					$("#ptdoclxh").val(result.vehicleType);
					$("#ptdofdjh").val(result.engineNumber);
					$("#ptdohdzk").val(result.passengerNumber);
					$("#ptdoclpp1").val(result.chineseBrand);
					$("#ywlx").val("O");
					$("#ywlxhtml").val("临时号牌");
					$("#hpzl").attr('value','22');
					$("#ptdoclsbdh").val(result.chassisNumber);
					$("#ptdoclsbdh").attr("disabled", false);
					$("#ptdoclsbdh").attr("style", "");
					$("#ptdofdjh").attr("disabled", false);
					$("#ptdoclxh").attr("disabled", false);
					$("#ptdohdzk").attr("disabled", false);
					$("#ptdoclpp1").attr("disabled", false);
					
				    closechuli();
				}
				}else{
					closechuli();
					exception(message);
					return false;
				}
			}
	  });
	}else{
		$("#dydjli").hide();
		//根据流水号查询in表中是否有记录
		$.ajax({
			cache:false,
			async:false,
			url:path+"/veh_ajax/vehAjax_getVehicleTempMidInByLsh.action?lsh="+lsh,
			type:'post',
			data:{"lsh":lsh},
			dataType:'json',
			success: function(result){
				var message = result+"";
				if(message.indexOf('异常信息') == -1){
					var type = "";
					if(result == null){
						type = "out";
					}else{
						type = "in";
					}
					$.ajax({
						cache:false,
						async:false,
						url:path+'/veh_ajax/vehAjax_getVehicleByLshAjax.action',
						type:'post',
						data:{"lsh":lsh, "type": type},
						dataType:'json',
						success: function(result){
							var message = result+"";
							if(message.indexOf('异常信息') == -1){
								var ptdo = result;
								if(ptdo == null || ptdo == ""){
					    			alert("该流水号不存在!");
					    			closechuli();
					    			return false;
					    		}
								var ywlx = ptdo.ywlx;
						    	var ywyy = ptdo.ywyy;
						    	var bz = ptdo.bz;
						    	var ishandle = ptdo.localIshandle;
						    	var ptdolsh = ptdo.lsh;
						    	getDtpzByYwlx(ywlx);
						    	if(ptdolsh == null || ptdolsh == ""){
					    			alert("该流水号不存在!");
					    			closechuli();
					    			return false;
					    		}
						    	//赋值
								if(type == 'out'){
									if(bz != 'D:P' && bz != 'D:A' && bz != 'D:J'){
						    			alert("该业务类型不能通过此方式受理!");
						    			closechuli();
					    				return false;
						    		}
								}else if(type == 'in'){
									if(ishandle == 1){
							    		var localOpertime = ptdo.localOpertime;
							    		var localName = ptdo.localName;
							    		alert("此流水号在"+localOpertime+"时间,被"+localName+"已受理!");
							    		closechuli();
										return false;
							    	}
							    	if(ywlx == "A" && ywyy == "A"){
							    		alert("该流水号是新车登记业务，不能在本模块操作!");
							    		closechuli();
										return false;
							    	}
							    	if(ywlx == "B" && (ywyy == "B" || ywyy == "C") && (bz == null || bz == "") ){
							    		alert("该流水号是转移登记业务，不能在本模块操作!");
							    		closechuli();
										return false;
							    	}
								}
								
								//设置业务类型
								if(ywlx == "B" && bz != null && bz != ""){
						    		ywyy = "";
						    		var ywlxs = bz.split(':');
						    		ywlx = ywlxs[0];
						    		ywyy = ywlxs[1];
						    	}
						    	setYwlxAndYwyy(ywlx, ywyy);
						    	$("#hphm").attr("readonly", "readonly");
								$("#hpzl").attr("disabled", "disabled");
								$("#hphm").val(ptdo.hphm);
								$("#hpzl").val(ptdo.hpzl);
								$("#bz").val(ptdo.bz);
								$("#lshval").val(ptdolsh);
								//登记参数
								$("#ptdosfzmmc").val(ptdo.sfzmmc);
								$("#ptdosfzmhm").val(ptdo.sfzmhm);
								$("#ptdozzz").val(ptdo.zzz);
								$("#ptdosyr").val(ptdo.syr);
								$("#ptdolxdh").val(ptdo.lxdh);
								$("#ptdodzyx").val(ptdo.dzyx);
								$("#yzbm7").val(ptdo.zsxzqh);
								$("#ptdozsxxdz").val(ptdo.zsxxdz);
								$("#yzbm6").val(ptdo.yzbm1);
								$("#ptdozzxxdz").val(ptdo.zzxxdz);
								$("#ptdoyzbm1").val(ptdo.yzbm1);
								$("#ptdosyxz").val(ptdo.syxz);
								$("#ptdosyq").val(ptdo.syq);
								$("#ptdohdfs").val(ptdo.hdfs);
								$("#ptdozsxzqh").val(ptdo.zsxzqh);
								$("#ptdohpzl").val(ptdo.hpzl);
								if(ptdo.sxrq != null){
									var sxrq = ptdo.sxrq.substring(0, 10);
									$("#ptdosxrq").val(sxrq);
								}
								if(ptdo.zzrq != null){
									var zzrq = ptdo.zzrq.substring(0, 10);
									$("#ptdozzrq").val(zzrq);
								}
								if(ptdo.yxqz != null){
									var yxqz = ptdo.yxqz.substring(0, 10);
									$("#ptdoyxqz").val(yxqz);
								}
								$("#ptdocjdw").val(ptdo.cjdw);
								$("#ptdotempBz2").val(ptdo.tempBz2);
								//技术参数
								$("#ptdoclsbdh").val(ptdo.clsbdh);
								$("#ptdoclxh").val(ptdo.clxh);
								$("#ptdoclpp1").val(ptdo.clpp1);
								$("#ptdoclpp2").val(ptdo.clpp2);
								$("#ptdocllx").val(ptdo.cllx);
								$("#ptdozzcmc").val(ptdo.zzcmc);
								$("#ptdofdjh").val(ptdo.fdjh);
								if(ptdo.ccrq != null){
									var ccrq = ptdo.ccrq.substring(0, 10);
									$("#ptdoccrq").val(ccrq);
								}
								if(ptdo.fhgzrq != null){
									var fhgzrq = ptdo.fhgzrq.substring(0, 10);
									$("#ptdofhgzrq").val(fhgzrq);
								}
								if(ptdo.csys != null){
									var csys = ptdo.csys;
									var csysarr = csys.split('');
									if(csysarr.length >= 1){
										$("#ptdocsys1").val(csysarr[0]);
									}
									if(csysarr.length >= 2){
										$("#ptdocsys2").val(csysarr[1]);
									}
									if(csysarr.length >= 3){
										$("#ptdocsys3").val(csysarr[2]);
									}
								}
								$("#ptdohgzbh").val(ptdo.hgzbh);
								$("#ptdojkpzhm").val(ptdo.jkpzhm);
								$("#ptdogcjk").val(ptdo.gcjk);
								$("#ptdozzg").val(ptdo.zzg);
								
								$("#ptdocfdjxh").val(ptdo.fdjxh);
								$("#ptdogl").val(ptdo.gl);
								$("#ptdopl").val(ptdo.pl);
								$("#ptdorlzl").val(ptdo.rlzl);
								$("#ptdocwkc").val(ptdo.cwkc);
								$("#ptdocwkk").val(ptdo.cwkk);
								$("#ptdocwkg").val(ptdo.cwkg);
								$("#ptdozxxs").val(ptdo.zxxs);
								$("#ptdohxnbcd").val(ptdo.hxnbcd);
								$("#ptdohxnbkd").val(ptdo.hxnbkd);
								$("#ptdohxnbgd").val(ptdo.hxnbgd);
								$("#ptdohbdbqk").val(ptdo.hbdbqk);
								$("#ptdogbthps").val(ptdo.gbthps);
								$("#ptdozs").val(ptdo.zs);
								$("#ptdozj").val(ptdo.zj);
								$("#ptdolts").val(ptdo.lts);
								
								$("#ptdoltgg").val(ptdo.ltgg);
								$("#ptdoqlj").val(ptdo.qlj);
								$("#ptdohlj").val(ptdo.hlj);
								$("#ptdozzl").val(ptdo.zzl);
								$("#ptdozbzl").val(ptdo.zbzl);
								$("#ptdohdzk").val(ptdo.hdzk);
								$("#ptdozqyzl").val(ptdo.zqyzl);
								$("#ptdohdzzl").val(ptdo.hdzzl);
								$("#ptdoqpzk").val(ptdo.qpzk);
								$("#ptdohpzk").val(ptdo.hpzk);
								closechuli();
							}else{
								closechuli();
								exception(message);
								return false;
							}
						}
					});
				}else{
					closechuli();
					exception(message);
					return false;
				}
			}
		});
	}
}

/**
 * 根据流水号查询
 */
function getVehicleMin(lsh){
	var vehicle;
	$.ajax({
		cache:false,
		async:false,
		url:path+'/veh_ajax/getVehicleByLsh.action',
		type:'post',
		data:{"lsh":lsh},
		dataType:'html',
		success: function(result){
			var message = result+"";
			if(message.indexOf('异常信息') == -1){
				vehicle = result;
			}else{
				exception(message);
				vehicle = "3";
			}
		}
	});
	return vehicle
}

/**
 * 业务类型验证
 * @param {Object} ywlx
 */
function checkYwlx(ywlx){
	var mustlsh;
	$.ajax({
		cache:false,
		async:false,
		url:path+'/veh_ajax/vehAjax_getYwlxMustLsh.action',
		type:'post',
		data:{"ywlx":ywlx},
		dataType:'json',
		success: function(result){
			var message = result+"";
			if(message.indexOf('异常信息') == -1){
				mustlsh = result;
			}else{
				exception(message);
				mustlsh = "3";
			}
		}
	});
	return mustlsh;
}

/**
 * 验证是否配偶审批
 * @return {TypeName} 
 */
function yanpeioshenp(czsfzhm, dbsfzhm, hphm, hpzl){
	var result = "";
	$.ajax({
		cache:false,
		async:false,
		url:path+'/veh_ajax/vehAjax_getPoDbspList.action',
		type:'post',
		data:{"czsfzhm":czsfzhm, "dbsfzhm": dbsfzhm, "hphm": hphm, "hpzl": hpzl},
		dataType:'json',
		success: function(data){
			var message = data+"";
			if(message.indexOf('异常信息') == -1){
				result = data;
			}else{
				exception(message);
				result = "3";
			}
		}
	});
	return result;
}

/**
 * 验证是否备案
 */
function yansfbeian(sfzmhm){
	var result = "";
	$.ajax({
		cache:false,
		async:false,
		url:path+'/veh_ajax/vehAjax_selDbjgBdy.action',
		type:'post',
		data:{"sfzmhm":sfzmhm},
		dataType:'html',
		success: function(data){
			var message = data+"";
			if(message.indexOf('异常信息') == -1){
				result = data;
			}else{
				exception(message);
				result = "3";
			}
		}
	});
	return result;
}

//查找用户部门科级ID
function getOrgId(){
	var orgId = "";
	$.ajax({
		cache:false,
		async:false,
		url:path+'/dydj/yhsl_getDeptOrgId.action',
		type:'post',
		data:'',
		dataType:'html',
		success: function(data){
			var message = data+"";
			if(message.indexOf('异常信息') == -1){
				orgId = data;
			}else{
				exception(message);
				orgId = "error";
			}
		}
	});
	return orgId;
}

/**
 * 黑名单验证
 * @param {Object} sfzmhm
 * @param {Object} xm
 * @return {TypeName} 
 */
function getIsBlack(ywlx, ywzl, hphm, hpzl, dsrsfzmhm, dbrsfzmhm){
	var isblack = "";
	$.ajax({
		cache:false,
		async:false,
		url:path+'/veh_ajax/vehAjax_getIsBlackByFun.action',
		type:'post',
		data:{"ywlx": ywlx, "ywzl":ywzl, "hphm": hphm, "hpzl": hpzl, "dsrsfzmhm":dsrsfzmhm, "dbrsfzmhm": dbrsfzmhm},
		dataType:'html',
		success: function(data){
			var message = data+"";
			if(message.indexOf('异常信息') == -1){
				isblack = data;
			}else{
				exception(message);
				isblack = "error";
			}
		}
	});
	return isblack;
}

function getIsyuyue(lsh, hphm, hpzl, dsrsfzmhm, ywlx, ywyy, type, dqgw, imei, bz,clsbdh){
	var isyuyue;
	$.ajax({
		cache:false,
		async:false,
		url:path+'/veh_ajax/vehAjax_getIsYuyue.action',
		type:'post',
		data:{"lsh":lsh, "hphm": hphm, "hpzl": hpzl, "dsrsfzmhm":dsrsfzmhm, "ywlx": ywlx, "ywyy":ywyy, "type":type, "dqgw":dqgw, "imei":imei, "bz": bz, "clsbdh":clsbdh},
		dataType:'html',
		success: function(data){
			var message = data+"";
			if(message.indexOf('异常信息') == -1){
				isyuyue = data;
			}else{
				exception(message);
				isyuyue = "error";
			}
		}
	});
	return isyuyue;
}

//是否违法未处理超过10宗并且记分超过12分
function isWfwclTen(hphm, hpzl, jszh){
	var islock;
	$.ajax({
		cache:false,
		async:false,
		url:path+'/drv/getIsChaoTen.action',
		type:'post',
		data:{"hphm":hphm, "hpzl":hpzl, "jszh":jszh},
		dataType:'json',
		success: function(result){
			var message = result+"";
			if(message.indexOf('异常信息') == -1){
				islock = result;
			}else{
				islock = 'error';
				exception(message);
			}
		}
	});
	return islock;
}

/**
 * 验车验证
 * @return {TypeName} 
 */
function checkYanche(lsh){
	var yanche = "";
	$.ajax({
		cache:false,
		async:false,
		url:path+'/veh_ajax/vehAjax_getIsyanche.action',
		type:'post',
		data:{"lsh":lsh},
		dataType:'json',
		error:function(XmlHttpRequest,textStatus, errorThrown){
			exception(XmlHttpRequest.responseText);
			yanche = "error";
		},
		success: function(result){
			var message = result+"";
			if(message.indexOf('异常信息') == -1){
				yanche = result;
			}else{
				yanche = 'error';
				exception(message);
			}
		}
	});
	return yanche
}


function ywlxchang(){
	alert("fff");
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

/**
 * 验证是否需要指标
 * @param {Object} lsh
 * @return {TypeName} 
 */
function checkIsneedzb(hphm, hpzl, ywlx, ywyy, sfzmhm, xm){
	var needzb = "";
	var sfzmmc = $("#dsrsfzmmc").val();
	$.ajax({
		cache:false,
		async:false,
		url:path+'/veh_ajax/vehAjax_getIsneedzb.action',
		type:'post',
		data:{"hphm":hphm, "hpzl":hpzl, "ywlx":ywlx, "ywyy":ywyy, "sfzmhm":sfzmhm, "xm":xm, "sfzmmc": sfzmmc},
		dataType:'html',
		success: function(data){
			var message = data+"";
			if(message.indexOf('异常信息') == -1){
				needzb = data;
			}else{
				exception(message);
				needzb = "error";
			}
		}
	});
	return needzb
}

//指标验证
function checkZbyanz(zblx, zbh, lsh, hphm, hpzl, dsrsfzmhm, xm, ywlx, ywyy, gzh, bz){
	var result = "";
	var clsbdh = $("#clsbdhval").val();
	$.ajax({
		cache:false,
		async:false,
		url:path+'/veh_ajax/vehAjax_getIsyanzzb.action',
		type:'post',
		data:{"dqgw":'3',"zblx":zblx, "zbh":zbh, "lsh":lsh, "hphm":hphm, "hpzl":hpzl, "dsrsfzmhm":dsrsfzmhm, "xm": xm, "ywlx": ywlx, "ywyy": ywyy, "gzh": gzh, "bz":bz, "clsbdh":clsbdh},
		dataType:'html',
		success: function(data){
			var message = data+"";
			if(message.indexOf('异常信息') == -1){
				result = data;
			}else{
				exception(message);
				result = "error";
			}
		}
	});
	return result
}

/**
 * 获取车辆限购数据字典——指标类型
 * @return {TypeName} 
 */
function getClxgsjzdList(){
	var list = "";
	$.ajax({
		cache:false,
		async:false,
		url:path+'/veh_ajax/vehAjax_getClxgsjzdList.action',
		type:'post',
		data:{"zblx":"", "zbh":""},
		dataType:'json',
		error:function(XmlHttpRequest,textStatus, errorThrown){
			exception(XmlHttpRequest.responseText);
			list = "error";
		},
		success: function(result){
			var message = result+"";
			if(message.indexOf('异常信息') == -1){
				list = result;
			}else{
				list = 'error';
				exception(message);
			}
		}
	});
	return list;
}
/**
 * 获取车辆类型
 * @return {TypeName} 
 */
function getCllxList(){
	var list = "";
	$.ajax({
		cache:false,
		async:false,
		url:path+'/veh_ajax/vehAjax_getCllxList.action',
		type:'post',
		dataType:'json',
		error:function(XmlHttpRequest,textStatus, errorThrown){
			exception(XmlHttpRequest.responseText);
			list = "error";
		},
		success: function(result){
			var message = result+"";
			if(message.indexOf('异常信息') == -1){
				list = result;
			}else{
				list = 'error';
				exception(message);
			}
		}
	});
	return list;
}


/**
 * 新加查询车辆类型（'K3%' OR 'K4%'条件）
 */
function getZrCllxList(){
	var list = "";
	$.ajax({
		cache:false,
		async:false,
		url:path+'/veh_ajax/vehAjax_getZrCllxList.action',
		type:'post',
		dataType:'json',
		error:function(XmlHttpRequest,textStatus, errorThrown){
			exception(XmlHttpRequest.responseText);
			list = "error";
		},
		success: function(result){
			var message = result+"";
			if(message.indexOf('异常信息') == -1){
				list = result;
			}else{
				list = 'error';
				exception(message);
			}
		}
	});
	return list;
}


/**
 * 获取使用性质
 * @return {TypeName} 
 */
function getClsyxzList(xtlb, dmlb){
	var list = "";
	$.ajax({
		cache:false,
		async:false,
		url:path+'/veh_ajax/vehAjax_getClsyxzList.action',
		type:'post',
		data:{"xtlb":xtlb, "dmlb":dmlb},
		dataType:'json',
		error:function(XmlHttpRequest,textStatus, errorThrown){
			exception(XmlHttpRequest.responseText);
			list = "error";
		},
		success: function(result){
			var message = result+"";
			if(message.indexOf('异常信息') == -1){
				list = result;
			}else{
				list = 'error';
				exception(message);
			}
		}
	});
	return list;
}

/**
 * 获取限购车辆类型
 * @return {TypeName} 
 */
function getClxglxList(){
	var list = "";
	$.ajax({
		cache:false,
		async:false,
		url:path+'/veh_ajax/vehAjax_getClxglxList.action',
		type:'post',
		dataType:'json',
		error:function(XmlHttpRequest,textStatus, errorThrown){
			exception(XmlHttpRequest.responseText);
			list = "error";
		},
		success: function(result){
			var message = result+"";
			if(message.indexOf('异常信息') == -1){
				list = result;
			}else{
				list = 'error';
				exception(message);
			}
		}
	});
	return list;
}

/**
 * 获取车辆限购数据字典
 * @return {TypeName} 
 */
function getClxgsjzd2List(xtlb, dmlb){
	var list = "";
	$.ajax({
		cache:false,
		async:false,
		url:path+'/veh_ajax/vehAjax_getClxgsjzd2List.action',
		type:'post',
		data:{"xtlb":xtlb, "dmlb":dmlb},
		dataType:'json',
		error:function(XmlHttpRequest,textStatus, errorThrown){
			exception(XmlHttpRequest.responseText);
			list = "error";
		},
		success: function(result){
			var message = result+"";
			if(message.indexOf('异常信息') == -1){
				list = result;
			}else{
				list = 'error';
				exception(message);
			}
		}
	});
	return list;
}

//获取证件信息表流水号
function getZjxxblsh(tyblsh){
	var obj = "";
	$.ajax({
		cache:false,
		async:false,
		url:path+'/veh_ajax/vehAjax_getZjxxblsh.action',
		type:'post',
		data:{"tyblsh":tyblsh},
		dataType:'html',
		error:function(XmlHttpRequest,textStatus, errorThrown){
			exception(XmlHttpRequest.responseText);
			obj = "error";
		},
		success: function(result){
			var message = result+"";
			if(message.indexOf('异常信息') == -1){
				obj = result.substring(0, 13);
			}else{
				obj = 'error';
				exception(message);
			}
		}
	});
	return obj;
}

//获取统一版流水号信息
function getTyblshinfo(tyblsh){
	var obj = "";
	$.ajax({
		cache:false,
		async:false,
		url:path+'/veh_ajax/vehAjax_getTyblshinfo.action',
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

function cllxchange(){
	var cllxval = $("#div_cllx").val();
 	var xgcxList = getClxglxList();
 	if(xgcxList == 'error'){
 		return false;
 	}
 	var count = 0;
 	for(var i = 0; i < xgcxList.length; i++){
 		var obj = xgcxList[i];
 		if(cllxval == $.trim(obj[0])){
 			count++;
 		}
 	}
 	if(count == 0){
 		$("#trzblx").hide();
		$("#trzbh").hide();
 	}else{
 		$("#trzblx").show();
		$("#trzbh").show();
 	}
}


/**
 * 指标查询
 * @param {Object} cllx 是否选择车辆类型
 * @param {Object} sffq 是否选择夫妻非夫妻
 * @param {Object} syxz 是否选择使用性质
 * @param {Object} httyblsh 是否回填流水号
 */
function alertdiv(cllx, sffq, syxz, httyblsh, zczxhfhljzyqchzx, sfyxgc, syxzsfsczqx, hdfs ){
	var divhtml = "";
	var flag = true;
	if(divhtml == null || divhtml == ""){
		divhtml += "<div><input type='hidden' id='zbtype' name='zbtype' value='0'/>";
		divhtml += "<table class='edittable' style='width: 600px;'>";
		if($.trim(sffq) != '0'){
			divhtml += "<tr><td style='text-align:right;'>是否夫妻：</td>";
			divhtml += "<td colspan='3'>&nbsp;&nbsp;<input type='radio' name='div_sffq' value='0' onclick='changIsfuradio("+sfyxgc+");'/>&nbsp;&nbsp;非夫妻<input type='radio' name='div_sffq' value='1' onclick='changIsfuradio("+sfyxgc+");'/>夫妻<span style='color: blue'>(温馨提示:新车主名下有车或有指标的必须输入指标)</span></td></tr>";
			divhtml += "<tr id='trjhzbh' style='display:none;'><td style='text-align:right;'><div id='jhzbhtext'>结婚证编号：</div></td><td colspan='3'>&nbsp;&nbsp;<input type='text' style='width: 150px;' name='div_jhzbh' id='div_jhzbh' value=''/></td></tr>";
		}
		if($.trim(cllx) != '0'){
			//var zrYwlx = document.getElementById("ywlxhtml").value;
			//if($.trim(zrYwlx) == "转入业务"){
			//	options = getZrCllxList();
			//}else{
				options = getCllxList();
			//}
			divhtml += "<tr><td style='text-align:right;'>车辆类型：</td><td colspan='3'>&nbsp;&nbsp;<select id='div_cllx' name='div_cllx' style='width: 150px;' onchange='cllxchange();'>";
			divhtml += "<option value=''>===请选择===</option>";
			for(var i = 0; i < options.length; i++){
				var obj = options[i];
				divhtml += "<option value='"+obj[0]+"'>"+obj[1]+"</option>";
			}
			divhtml += "</select></td></tr>";
		}
		if($.trim(syxz) != '0'){
			options = getClsyxzList('00', '1003');
			divhtml += "<tr><td style='text-align:right;'>变更后使用性质：</td><td colspan='3'>&nbsp;&nbsp;<select id='div_syxz' name='div_syxz' style='width: 150px;'>";
			divhtml += "<option value=''>===请选择===</option>";
			for(var i = 0; i < options.length; i++){
				var obj = options[i];
				divhtml += "<option value='"+obj[0]+"'>"+obj[1]+"</option>";
			}
			divhtml += "</select></td></tr>";
		}
		if($.trim(hdfs) != '0'){
			options = getClsyxzList('01', '0001');
			divhtml += "<tr><td style='text-align:right;'>获得方式：</td><td colspan='3'>&nbsp;&nbsp;<select id='div_hdfs' name='div_hdfs' style='width: 150px;'>";
			divhtml += "<option value=''>===请选择===</option>";
			for(var i = 0; i < options.length; i++){
				var obj = options[i];
				divhtml += "<option value='"+obj[0]+"'>"+obj[1]+"</option>";
			}
			divhtml += "</select></td></tr>";
		}
		
		if($.trim(zczxhfhljzyqchzx) != '0'){
			divhtml += "<tr><td style='text-align:right;'><input type='checkbox' name='div_zczxhfhljzyqchzx' id='div_zczxhfhljzyqchzx' onclick='zcljhfclick();' value='0'/></td><td colspan='3'>&nbsp;&nbsp;转出注销恢复后立即转移、迁出或注销<span style='color: blue;'>(勾选之后无需进行指标验证)</span></td></tr>";
		}
		divhtml += "<tr id='trzblx'><td style='text-align:right;'>指标类型：</td><td>&nbsp;&nbsp;<select id='div_zblx' name='div_zblx' style='width: 150px;' onchange='zblxchange();'>";
		divhtml += "<option value=''>===请选择===</option>";
		var options = getClxgsjzdList();
		for(var i = 0; i < options.length; i++){
			var obj = options[i];
			divhtml += "<option value='"+obj[0]+"'>"+obj[1]+"</option>";
		}
		divhtml += "</select></td></tr>";
		divhtml += "<tr id='trzbh'>" 
		divhtml += "<td style='text-align:right;'>指标号：</td><td>&nbsp;&nbsp;<input id='div_zbh' name='div_zbh' class='easyui-combogrid' style='width:148px;'/ ></td></tr>";
		divhtml += "</table>";
		divhtml += "</div>";
	}
	var reason = art.dialog({
		width:'50%',
		height:100,
		title: '指标验证',
		content: divhtml,
		okVal: '验证',
		ok: function(){
			//验证指标结果
		 	var zblx = $("#div_zblx").val();
		 	var zbh = $("#div_zbh").val();
		 	//共同所有人姓名变更D:A 如果是夫妻，新车主名下有限购车型（k31-k49）或者不是夫妻，则必须验证指标
		 	if($.trim(sffq) != '0'){
		 		var isfqval = $("input[name='div_sffq']:checked").val(); 
		 		if(isfqval == null || isfqval == ''){
		 			alert("请选择是否是夫妻");
		 			return false;
		 		}
		 		if(isfqval == '1'){
		 			var jhzbhval = $("#div_jhzbh").val();
		 			if(jhzbhval == null || jhzbhval == ''){
		 				alert("请填写结婚证编号");
		 				return false;
		 			}
		 			if(sfyxgc == '0'){
		 				flag = false;  //是夫妻且新车主名下没有限购车型
		 			}
		 		}
		 	}
		 	//变更使用性质D:I  如果变更前使用性质是出租、抢险等+车辆类型是k31-k49，则必须验证指标
		 	if($.trim(syxz) != '0'){
		 		var syxzval = $("#div_syxz").val();
		 		if(syxzval == null || syxzval == ''){
		 			alert("请选择变更后使用性质");
		 			return false;
		 		}
		 		if(syxzsfsczqx == '0'){
		 			flag = false;
		 		}
		 	}
		 	//获得方式
		 	if($.trim(hdfs) != '0'){
		 		var hdfsval = $("#div_hdfs").val();
		 		if(hdfsval == null || hdfsval == ''){
		 			alert("请选择获得方式");
		 			return false;
		 		}
		 	}
		 	//转入业务I 如果转入车辆类型是k31-k49，则必须验证指标
		 	if($.trim(cllx) != '0'){
		 		var cllxval = $("#div_cllx").val();
		 		if(cllxval == null || cllxval == ''){
		 			alert("请选择车辆类型");
		 			return false;
		 		}
		 		var xgcxList = getClxglxList();
		 		if(xgcxList == 'error'){
		 			return false;
		 		}
		 		var count = 0;
		 		for(var i = 0; i < xgcxList.length; i++){
		 			var obj = xgcxList[i];
		 			if(cllxval == $.trim(obj[0])){
		 				count++;
		 			}
		 		}
		 		if(count == 0){
		 			flag = false;
		 		}else{
		 			flag = true; //20150202修改，年前转入业务所有都不需要验证指标
		 		}
		 	}
		 	if($.trim(zczxhfhljzyqchzx) != '0'){
		 		var chck = $("#div_zczxhfhljzyqchzx").attr("checked");
		 		if(chck == 'checked'){
		 			flag = false;
		 		}
		 	}
		 	
		 	$("#zblx").val($("#div_zblx").val());
			$("#zbh").val($("#div_zbh").val());
			if(cllx != '0'){
				$("#cllx").val($("#div_cllx").val());
			}
			if(sffq != '0'){
				$("#jhzbh").val($("#div_jhzbh").val());
				$("#sffq").val($("input[name='div_sffq']:checked").val());
			}
			if(syxz != '0'){
				$("#syxz").val($("#div_syxz").val());
			}
			if($.trim(zczxhfhljzyqchzx) != '0'){
				var tempval = $("input[name='div_zczxhfhljzyqchzx']:checked").val();
				if(tempval == 0){
					$("#zczxhfhljzyqchzx").val("0");
				}else{
					$("#zczxhfhljzyqchzx").val("1");
				}
				
			}else{
				$("#zczxhfhljzyqchzx").val("");
			}
			if($.trim(hdfs) != '0'){
				$("#hdfs").val($("#div_hdfs").val());
			}
			$("#gzh").val($("#div_gzh").val());
			$("#clsbdhval").val($("#div_clsbdh").val());
		 	
		 	if(flag){
		 		$("#zbyzflag").val("1");
		 		var alertinfo = $("#alterinfo").val();
		 		if(zblx == null || zblx == ''){
		 			alert("请选择指标类型\n"+alertinfo);
		 			return false;
		 		}
		 		if(zbh == null || zbh == ''){
		 			alert("请输入指标号\n"+alertinfo);
		 			return false;
		 		}
		 		var zjxxblshval = $("#zjxxblsh").val();
		 		var hphm = $("#hphm").val();
		 		var hpzl = $("#hpzl").val();
		 		var dsrsfzmhm = $("#dsrsfzmhm").val();
				var dsrzjjgzhm = $("#dsrzzjgdm").val();
				var dsrxm = $("#dsrxm").val();
				var dsrdwfr = $("#dsrdwmc").val();
				var dsrsfzmhmblack = "";
				var dsrxmblack = "";
				if(dsrsfzmhm != null &&  dsrsfzmhm != ""){
					dsrsfzmhmblack = dsrsfzmhm;
				}else{
					dsrsfzmhmblack = dsrzjjgzhm;
				}
				if(dsrxm != null &&  dsrxm != ""){
					dsrxmblack = dsrxm;
				}else{
					dsrxmblack = dsrdwfr;
				}
		 		var ywlx = $("#ywlx").val();
		 		var ywyy = $("#ywyy").val();
		 		var gzh = $("#div_gzh").val();
		 		var bz = "";
		 		//dsrsfzmhmblack = $("#div_sfzmhm").val();
		 		//alert(hphm+","+hpzl+","+ywlx+","+ywyy+","+dsrsfzmhmblack);
		 		var result = checkZbyanz(zblx, zbh, zjxxblshval, hphm, hpzl, dsrsfzmhmblack, dsrxmblack, ywlx, ywyy, gzh, bz);
				if(result == "error"){
					return false;
				}
				var code = result.substring(0, 6);
				var msg = result.substring(6, result.length);
				if($.trim(code) == "[0000]"){
					$("#zbyzflag").val('1');
					alertdivlsh(httyblsh);
				}else{
					alert(msg);
					return false;
				}
		 	}else{
		 		alertdivlsh(httyblsh);
		 	}
		 	
		},
		cancelVal: '关闭',
		cancel: true,
		lock: true,
	    opacity: 0.4
	});
}

function alertdivlsh(httyblsh){
	var lsh = "";
	if(httyblsh == '1'){
		lsh = $("#lsh").val();
	}
	var title = "统一版流水号";
	var ywlx = $("ywlx").val();
	if(ywlx == 'U'){
		title = "统一版转出注销恢复流水号";
	}
	var divhtml = "<div>";
	divhtml += "<table class='edittable' style='width: 600px;'>";
	divhtml += "<tr><td style='text-align:right;'>"+title+"：</td><td>&nbsp;&nbsp;<input type='text' name='div_tyblsh' id='div_tyblsh' value='"+lsh+"'/></td></tr>";
	divhtml += "</table>";
	divhtml += "</div>";
	var reason2 = art.dialog({
		width:'50%',
		height:100,
		title: '流水号',
		content: divhtml,
		okVal: '保存',
		ok: function(){
			//调取六合一接口验证统一版流水号业务类型、姓名是否相同
			var tyblsh = $("#div_tyblsh").val();
			if(tyblsh == null || tyblsh == ""){
				alert("请输入统一版流水号");
				return false;
			}
			if(tyblsh.length != 13){
				alert("统一版流水号长度不正确");
				return false;
			}
//			var result = getTyblshinfo(tyblsh);
//			if(result == "error"){
//				return false;
//			}
//			if(result == ",,,,,,,,,,,,,,,,,,,,,"){
//				alert("此流水号不存在!");
//				return false;
//			}
//			var page_ywlx = $("#ywlx").val();
//			var page_ywyy = $("#ywyy").val();
//			if(page_ywyy == null || page_ywyy == ""){
//				page_ywyy = page_ywlx;
//			}
//			var tyb_ywyy = $.trim(result[6])+":"+$.trim(result[21]);
//			//page_ywyy = "A:A";
//			if(page_ywyy != tyb_ywyy){
//				alert("统一版流水号业务类型不正确!");
//				return false;
//			}
//			var page_dsrSfzCardname1 = $("#dsrSfzCardname1").val();
//			//page_dsrSfzCardname1 = "卓冬梅";
//			if(page_dsrSfzCardname1 != $.trim(result[7])){
//				alert("统一版信息里所有人与窗口当事人姓名不同!");
//				return false;
//			}
			$("#tyblsh").val($("#div_tyblsh").val());
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
			
		},
		cancelVal: '关闭',
		cancel: true,
		lock: true,
	    opacity: 0.4
	});
}

function changIsfuradio(sfyxgc){
	var isfq = $("input[name='div_sffq']:checked").val();
	if(isfq == 1){
		$("#trjhzbh").show();
		if(sfyxgc == '1'){
			$("#trzblx").show();
			$("#trzbh").show();
		}else{
			$("#trzblx").hide();
			$("#trzbh").hide();
		}
	}else{
		$("#trjhzbh").hide();
		$("#trzblx").show();
		$("#trzbh").show();
	}
}

function ClickRow(rowIndex, rowData){
//    $("#tbZbh").val(rowData.zbh);
//    $("#xm_txt").val(rowData.xm);
//    $("#sfzmhm_txt").val(rowData.sfzmhm);
//    $("#sltGzh").val(rowData.gzh);
//    $("#clsbdm_txt").val(rowData.clsbdh);
//    $("#xcfphm_txt").val(rowData.fph);
    $("#div_zbh").val(rowData.zbh);
    var val = $("#div_zbh").val();
    if(val.indexOf('<span style=color:red;>') != -1){
    	$("#div_zbh").combogrid("setValue", '');
    }
    
 }

function LoadSuccessClsbdh(){
	var total=$("#div_zbh").combogrid("grid").datagrid('getData').total;
	if(total==0){
		alert("根据当前信息没有查询到指标!");
	   $("#div_zbh").combogrid("grid").datagrid('mergeCells', { index: 0, field: 'id', rowspan:1,colspan:5 })
	}

}

function clearzbh(){
	//$("#div_zbh").val("");
	var zbtype = $("#zbtype").val();
	if(zbtype == '1'){
		try{
			var val = $("#div_zbh").combogrid("getValue");
			if(val != null && val != ""){
				$("#div_zbh").combogrid("setValue", "");
			}
		}catch(e){
			return false;
		}
		
	}
}

function selzblist(){
	$("#div_zbh").removeAttr("readonly");
	var gzh = "";
	var sfzmhm = "";
	var clsbdh = "";
	var alertinfo = "";
	var zblx = $("#div_zblx").val();
	var zbh = $("#div_zbh").val();
	if(zblx == "BAZB"){
		gzh = $("#div_gzh").val();
		//sfzmhm = $("#div_sfzmhm").val();
		alertinfo = "请填写公证号";
		if(gzh == null || gzh == ''){
			alert(alertinfo);
			return false;
		}
		$('#div_zbh').combogrid({
			panelWidth:340,
			idField:'zbh',
			textField:'zbh',
			url:path+'/veh_ajax/vehAjax_getZblist.action',
			loadMsg:"加载中,请等待...",
			sortName:"zbh",
			sortOrder:"asc",
			rowStyler:function(rowIndex,rowData){
			  if(rowIndex%2==0){
			  	return "background-color:#D8E7F8";
			  }else{
			  
			  }
			},
			queryParams:{"zblx": zblx, "gzh":gzh, "sfzmhm":sfzmhm, "clsbdh":clsbdh},
			columns:[[
			    {field:'id',title:'ID',width:30,align:"center"},
			    {field:'gzh',title:'公证号',width:200,align:"center"},
				{field:'zbh',title:'指标号',width:100,align:"center"},
				{field:'zbgqrq',title:'指标过期日期',width:120,align:"center"},
				{field:'xm',title:'姓名或机构名称',width:100,align:"center"},
				{field:'sfzmhm',title:'身份证明号码',width:120,align:"center"},
			    {field:'ppxh',title:'品牌型号',width:60,align:"center"},
				{field:'clsbdh',title:'车架号',width:100,align:"center"},
				{field:'fph',title:'发票号',width:120,align:"center"}
			]],
		    onClickRow:ClickRow,
		    onLoadSuccess: LoadSuccessClsbdh
		    
		});
	}
	if(zblx == "ESCLZB"){
		clsbdh = $("#div_clsbdh").val();
		alertinfo = "请填写车辆识别代号";
		if(clsbdh == null || clsbdh == ''){
			alert(alertinfo);
			return false;
		}
		$('#div_zbh').combogrid({
			panelWidth:340,
			idField:'zbh',
			textField:'zbh',
			url:path+'/veh_ajax/vehAjax_getZblist.action',
			loadMsg:"加载中,请等待...",
			sortName:"zbh",
			sortOrder:"asc",
			rowStyler:function(rowIndex,rowData){
			  if(rowIndex%2==0){
			  	return "background-color:#D8E7F8";
			  }else{
			  
			  }
			},
			queryParams:{"zblx": zblx, "gzh":gzh, "sfzmhm":sfzmhm, "clsbdh":clsbdh},
			columns:[[
			    {field:'id',title:'ID',width:30,align:"center"},
			    {field:'zbh',title:'指标号',width:120,align:"center"},
				{field:'hphm',title:'号牌号码',width:120,align:"center"},
				{field:'clsbdh',title:'车辆识别代号',width:100,align:"center"},
				{field:'xm',title:'姓名',width:80,align:"center"},
				{field:'zbgqrq',title:'指标过期日期',width:120,align:"center"},
				{field:'sfzmhm',title:'身份证明号码',width:100,align:"center"}
			]],
		    onClickRow:ClickRow,
		    onLoadSuccess: LoadSuccessClsbdh
		    
		});
	}
	
}

//trzblx  trzblxhtml
function zblxchange(){
	if(html == ""){
		html = $("#trzbh").html();
	}
	if(trzblxhtml == ""){
		trzblxhtml = $("#trzblx").html();
	}
	var nowhtml = "";
	var zblx = $("#div_zblx").val();
	var dsrsfzmmc = $("#dsrsfzmmc").val();
	if(zblx == 'DBZB'){
//		alert("该指标类型数据程序开发中，请稍后再试!");
//		$("#div_zblx").val("");
//		return false;
		
		if(dsrsfzmmc != 'B'){
			alert("该指标类型只能使用组织机构证!");
			$("#div_zblx").val("");
			return false;
		}
	}
	
	if(zblx == 'BAZB'){
		$("#zbtype").val("1");
		var dsrxm = "";
		if(dsrsfzmmc == "B"){
			dsrxm = $("#dsrzzjgdm").val();
		}else{
			dsrxm = $("#dsrsfzmhm").val();
		}
		//nowhtml = trzblxhtml+"<td style='text-align:right; display: none;'>身份证明号码：</td><td style='display: none;'>&nbsp;&nbsp;<input id='div_sfzmhm' name='div_sfzmhm' value='"+dsrxm+"' style='width:148px;' onkeyup='clearzbh()' onblur='clearzbh();'/>&nbsp;&nbsp;</td>";
		nowhtml = trzblxhtml;
		$("#trzblx").html(nowhtml);
		nowhtml = "<td style='text-align:right;'>公证号：</td><td>&nbsp;&nbsp;<input id='div_gzh' name='div_gzh' style='width:148px;' onkeyup='clearzbh()' onblur='clearzbh();'/>&nbsp;&nbsp;<input type='button' value='查询' class='btn2' onclick='selzblist();'/></td>"+html;
		$("#trzbh").html(nowhtml);
		$("#div_zblx").val(zblx);
		$("#div_zbh").attr("readonly", "readonly");
	}else if(zblx == 'ESCLZB'){
		$("#zbtype").val("1");
		$("#trzblx").html(trzblxhtml);
		var clsbdhval = $("#ptdoclsbdh").val();
		if(clsbdhval == null || clsbdhval == ''){
			clsbdhval = $("#clsbdh").val();
		}
		nowhtml = "<td style='text-align:right;'>车辆识别代号：</td><td>&nbsp;&nbsp;<input id='div_clsbdh' name='div_clsbdh' style='width:148px;' value='"+clsbdhval+"' onkeyup='clearzbh()' onblur='clearzbh();' />&nbsp;&nbsp;<input type='button' value='查询' class='btn2' onclick='selzblist();'/></td>"+html;
		$("#trzbh").html(nowhtml);
		$("#div_zblx").val(zblx);
		$("#div_zbh").attr("readonly", "readonly");
	}else{
		$("#zbtype").val("0");
		$("#trzbh").html(html);
		$("#trzblx").html(trzblxhtml);
		$("#div_zblx").val(zblx);
		$("#div_zbh").removeAttr("readonly");
	}
	
}

function zcljhfclick(){
	var result = $("#div_zczxhfhljzyqchzx").attr("checked");
	if(result == 'checked'){
		$("#div_zblx").attr("disabled", "disabled");
		$("#div_zbh").attr("disabled", "disabled");
		$("#div_zblx").val("");
		$("#div_zbh").val("");
		
		$("#div_clsbdh").val("");
		$("#div_gzh").val("");
		$("#div_sfzmhm").val("");
		
		$("#div_clsbdh").attr("disabled", "disabled");
		$("#div_gzh").attr("disabled", "disabled");
		$("#div_sfzmhm").attr("disabled", "disabled");
	}else{
		$("#div_zblx").removeAttr("disabled");
		$("#div_zbh").removeAttr("disabled");
		$("#div_clsbdh").removeAttr("disabled");
		$("#div_gzh").removeAttr("disabled");
		$("#div_sfzmhm").removeAttr("disabled");
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