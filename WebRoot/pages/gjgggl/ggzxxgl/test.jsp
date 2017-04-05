<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<title>广告证打印</title>

		<script type="text/javascript"
			src="<%=request.getContextPath()%>/jquery/js/jquery-1.8.2.js">
</script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/gjgggl/jquery.jPrintArea.js">
</script>
		<script type="text/javascript">

$(function() {

	$("#test").click(function() {
		addBusCertifyInfo();
	});

	function addBusCertifyInfo() {

		$.ajax({
			url : "<%=request.getContextPath()%>/gjgg/ggz_getBazPic.action",
			type : 'post',
			async : true,
			dataType : 'text',
			error : function() {
				alert("请求异常,请重试!");
			},
			success : function(data) {
				var result = $.trim(data);
				var index = result.indexOf("-");
				if (index == 0) {
					alert(result.substring(1, result.length));
				} else {
					alert("ok");
				}
			}
		});
	}

})

var mac = "";

function query() {
	var lshStr = $.trim(document.getElementById("lshStr").value);
	if (typeof (lshStr) != "undefined" && lshStr != null && lshStr != "") {
		document.getElementById("querySubmit").submit();
	}
}

function showPrintAreaToShow() {
	var myPrintArea = document.getElementById("myPrintArea");
	var showPicArea = document.getElementById("showPicArea");
	var isShow = showPicArea.getAttribute("isShow");
	if (isShow == "false") {
		myPrintArea.style.display = "block";
		showPicArea.setAttribute("isShow", "true");
		showPicArea.value = "隐藏图片";
	} else {
		myPrintArea.style.display = "none";
		showPicArea.setAttribute("isShow", "false");
		showPicArea.value = "预览图片";
	}
}

function printPic() {

	if (confirm("确定打印广告证?")) {
		document.getElementById("myPrintArea").style.display = "block";

		var headstr = "<html><head><title></title></head><body>";
		var footstr = "</body>";
		//要打印的数据
		var newstr = document.getElementById("myPrintArea").innerHTML;
		//旧的网页数据
		var oldstr = document.body.innerHTML;
		document.body.innerHTML = headstr + newstr + footstr;
		parent.window.print();

		document.body.innerHTML = oldstr;
		document.getElementById("myPrintArea").style.display = "none";

		window.close();

	}
}

//获取mac地址
function getMac() {
	mac = "";
	try {
		var locator = new ActiveXObject("WbemScripting.SWbemLocator");
		var service = locator.ConnectServer(".");
		var properties = service.ExecQuery("Select * from Win32_NetworkAdapterConfiguration Where IPEnabled =True");
		var e = new Enumerator(properties);
		{
			var p = e.item();
			mac = p.MACAddress;
		}
	} catch (e) {
		mac = '0.0.0.0.0';
	}
	mac = $.trim(mac);
}

//初始化mac地址
getMac();
</script>
	
	</head>

	<body>
		<input type="hidden" id="lsh"  />
		<input id="test" type="button" style="width: 80px;"
			value="test" />

	</body>

</html>