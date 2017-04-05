	
	var downX,downY,upX,upY;
	
    
	function getX(obj){
		var parObj=obj; 
		var left=obj.offsetLeft; 
		while(parObj=parObj.offsetParent){
			left+=parObj.offsetLeft; 
		}
		return left; 
	}
	 
	function getY(obj){ 
		var parObj=obj; 
		var top=obj.offsetTop; 
		while(parObj = parObj.offsetParent){
			top+=parObj.offsetTop; 
		} 
		return top; 
	} 
	
	var isDown="false";
	
	function getDownXY(eve,obj){
		
		var isContinue=getDownOrUpXYClick(eve,obj);
		
		if(isContinue!="false"){
			
			isDown="true";
			
			var top,left,oDiv; 
			oDiv=obj; 
			top=getY(oDiv); 
			left=getX(oDiv);
			downX=eve.clientX-left+document.documentElement.scrollLeft;
			downY=eve.clientY-top+document.documentElement.scrollTop;
			
			$(obj).mousemove(function(e){
				creatArea_move(e,document.getElementById("smallImage"));
			});
			
			
		}
		
		var e=eve || window.event;
		if(e && e.stopPropagation()){
			e.stopPropagation();
		}else{
			e.cancelBubble=true;
		}
		
	}
	
	function getUpXY(eve,obj){
		var isContinue=getDownOrUpXYClick(eve,obj);
		
		if(isContinue!="false"){
			
			isDown="false";
			
			var top,left,oDiv; 
			oDiv=obj; 
			top=getY(oDiv); 
			left=getX(oDiv); 
			upX=eve.clientX-left+document.documentElement.scrollLeft;
			upY=eve.clientY-top+document.documentElement.scrollTop;
			
			$("#tt").remove();
			$(obj).unbind("mousemove");
			creatArea(downX,downY,upX,upY);
			removeLittlePic(obj);
			scoleTo();
		}
		var e=eve || window.event;
		if(e && e.stopPropagation()){
			e.stopPropagation();
		}else{
			e.cancelBubble=true;
		}
	}
	
	function getUpXY_(eve,obj){
		
		if(isDown=="true"){
			
			isDown="false";
			
			setTimeout(function(){
				var top,left; 
				top=getY(obj); 
				left=getX(obj);
				//获取鼠标释放时的坐标位置
				upX=eve.clientX-left+document.documentElement.scrollLeft;
				upY=eve.clientY-top+document.documentElement.scrollTop;
				$("#tt").remove();
				removeLittlePic(obj);
				scoleTo();
			},10);
		}
		

	}
	
	function creatArea(downX_,downY_,upX_,upY_){
		var widthStr=upX_-downX_;
		var heightStr=upY_-downY_;
		
		$("#t").remove();
		
		var myDiv=$("<div id='t'></div>");
		var w=Math.abs(widthStr);
		var h=Math.abs(heightStr);
		//alert(w+"-"+h);
		myDiv.css("width",w);
		myDiv.css("height",h);
		myDiv.css({"background-color":"blue"});
		myDiv.css("position","absolute");
		myDiv.css("border","1px solid #0F9"); 
		myDiv.css("filter","alpha(opacity:30)");
		myDiv.css("opacity","0.3");
		
		
		if(downX_<upX_){
			myDiv.css("left",downX_+"px");
		}else{
			myDiv.css("left",upX_+"px");
		}
		
		if(downY_<upY_){
			myDiv.css("top",downY_+"px");
		}else{
			myDiv.css("top",upY_+"px");
		}
		
		$("#smallImage").append(myDiv);
		
		
	};
	
	function creatArea_move(event,obj){
		
		var top,left,oDiv;
		oDiv=obj;
		top=getY(oDiv);
		left=getX(oDiv); 
		var upX_=event.clientX-left+document.documentElement.scrollLeft;
		var upY_=event.clientY-top+document.documentElement.scrollTop;
		
		var widthStr=upX_-downX;
		var heightStr=upY_-downY;
		
		$("#tt").remove();
		
		var myDiv=$("<div id='tt'></div>");
		var w=Math.abs(widthStr);
		var h=Math.abs(heightStr);
		//alert(w+"-"+h);
		myDiv.css("width",w);
		myDiv.css("height",h);
		myDiv.css({"background-color":"blue"});
		myDiv.css("position","absolute");
		myDiv.css("border","1px solid #0F9"); 
		myDiv.css("filter","alpha(opacity:30)");
		myDiv.css("opacity","0.3");
		
		if(downX<upX_){
			myDiv.css("left",downX+"px");
		}else{
			myDiv.css("left",upX_+"px");
		}
		
		if(downY<upY_){
			myDiv.css("top",downY+"px");
		}else{
			myDiv.css("top",upY_+"px");
		}
		
		$("#smallImage").append(myDiv);
		
	};
	
	function removeLittlePic(obj){
		$(obj).unbind("mousemove");
		$("#tt").remove();
		$("#t").remove();
	}
	
	function scoleTo(){
		
		
		var bigImage=document.getElementById("bigImage");
		
		var oldWidth=bigImage.getAttribute("oldWidth");
		var oldHeight=bigImage.getAttribute("oldHeight");
		
		var bs=document.getElementById("idScale");
		if(bs.value!="" && bs.value!="0" ){
			
			document.getElementById("scoleDiv").style.display="block";
			
			bigImage.setAttribute("width",oldWidth*bs.value);
			bigImage.setAttribute("height",oldHeight*bs.value);
			
			
			var bigImageWidth=bigImage.width;
			var bigImageHeight=bigImage.height;
			
			var smallImage=document.getElementById("smallImage");
			var smallImageWidth=smallImage.style.width;
			var smallImageHeight=smallImage.style.height;
			document.getElementById("realSmallImage").style.display="none";
			
			if(smallImageWidth.indexOf("px")>0 || smallImageWidth.indexOf("PX")>0 ){
				smallImageWidth=smallImageWidth.substr(0,smallImageWidth.length-2);
			}
			
			if(smallImageHeight.indexOf("px")>0 || smallImageHeight.indexOf("PX")>0 ){
				smallImageHeight=smallImageHeight.substr(0,smallImageHeight.length-2);
			}
			
			
			var bl_width=bigImageWidth/smallImageWidth;
			var bl_height=bigImageHeight/smallImageHeight;
			var scoleDiv=document.getElementById("scoleDiv");
			
			scoleDiv.style.overflow="scroll";
			
			var big_x=0;
			var big_y=0;
			
			//downX,downY,upX,upY;
			if(downX<upX){
				if(downY<upY){
					big_x=downX*bl_width;
					big_y=downY*bl_height;
				}else{
					big_x=downX*bl_width;
					big_y=upY*bl_height;
				}
			}else{
				if(downY<upY){
					big_x=upX*bl_width;
					big_y=downY*bl_height;
				}else{
					big_x=upX*bl_width;
					big_y=upY*bl_height;
				}
			}
			
			scoleDiv.scrollLeft=big_x;
			scoleDiv.scrollTop=big_y;
			
			
		}
			
		
	}
	
	//小图右键单击事件
	function getDownOrUpXYClick(eve,obj){
		if(eve.button==2){
			$(obj).unbind("mousemove");
			$("#tt").remove();
			$("#t").remove();
			
			document.getElementById("scoleDiv").style.overflow="hidden";
			document.getElementById("scoleDiv").style.display="none";
			document.getElementById("realSmallImage").style.display="block";
			return "false";
		}
	}
	
	//大图右键单击事件
	function imageRightClick(eve,obj){
		if(eve.button==2){
			hideBigImage_();
			var e=eve || window.event;
			if(e && e.stopPropagation()){
				e.stopPropagation();
			}else{
				e.cancelBubble=true;
			}
			return "false";
		}
		var e=eve || window.event;
		if(e && e.stopPropagation()){
			e.stopPropagation();
		}else{
			e.cancelBubble=true;
		}
	}
	
	//大图左键单击事件(防止事件冒泡)
	function imageLeftClick(event){
		var e=event || window.event;
		if(e && e.stopPropagation()){
			e.stopPropagation();
		}else{
			e.cancelBubble=true;
		}
	}
	
	//重置大图
	function hideBigImage_(){
		var smallImage=document.getElementById("smallImage");
		$(smallImage).unbind("mousemove");
		$("#tt").remove();
		$("#t").remove();
		document.getElementById("scoleDiv").style.overflow = "hidden";
		document.getElementById("scoleDiv").style.display = "none";
		document.getElementById("realSmallImage").style.display="block";
	}
	
	var lastX=0;
	var lastY=0;
	
	//大图移动
	function md(eve){
		
		var isContinue=imageRightClick(eve);
		if(isContinue!="false"){
			
			$("#scoleDiv").css("cursor","move");
		
			$("#scoleDiv").mousemove(function(event){
				scoleTo_(event);
				var e=event || window.event;
				if(e && e.stopPropagation()){
					e.stopPropagation();
				}else{
					e.cancelBubble=true;
				}
			});
			
			var e=eve || window.event;
			if(e && e.stopPropagation()){
				e.stopPropagation();
			}else{
				e.cancelBubble=true;
			}
		}
		
	}
	
	//大图移动
	function mu(eve){
		
		$("#scoleDiv").css("cursor","pointer");
		
		lastX=0;
	 	lastY=0;
		$("#scoleDiv").unbind("mousemove");
		
		var e=eve || window.event;
		if(e && e.stopPropagation()){
			e.stopPropagation();
		}else{
			e.cancelBubble=true;
		}
	}
	
	
	function scoleTo_(event){
		
		var scoleDiv=document.getElementById("scoleDiv");
		scoleDiv.style.display="block";
		
		//大图
		var bigImage=document.getElementById("bigImage");
		var bigImageWidth=bigImage.width;
		var bigImageHeight=bigImage.height;
		
		//小图
		var smallImage=document.getElementById("scoleDiv");
		var smallImageWidth=smallImage.style.width;
		var smallImageHeight=smallImage.style.height;
		
		if(smallImageWidth.indexOf("px")>0){
			smallImageWidth=smallImageWidth.substr(0,smallImageWidth.length-2);
		}
		if(smallImageHeight.indexOf("px")>0){
			smallImageHeight=smallImageHeight.substr(0,smallImageHeight.length-2);
		}
		
		//放大缩小比例
		var bl_width=bigImageWidth/smallImageWidth;
		var bl_height=bigImageHeight/smallImageHeight;
		
		scoleDiv.style.overflow="scroll";
		
		var top=getY(scoleDiv); 
		var left=getX(scoleDiv);
		var downX=event.clientX-left+document.documentElement.scrollLeft;
		var downY=event.clientY-top+document.documentElement.scrollTop;
		
		if(lastX!=0 && lastY!=0 ){
			if(lastX<downX){
				var s=downX-lastX;
				var t=scoleDiv.scrollLeft;
				t=t+s*bl_width;
				scoleDiv.scrollLeft=t;
			}else{
				var s=lastX-downX;
				var t=scoleDiv.scrollLeft;
				t=t-s*bl_width;
				scoleDiv.scrollLeft=t;
			}
			
			if(lastY<downY){
				var s=downY-lastY;
				var t=scoleDiv.scrollTop;
				t=t+s*bl_height;
				scoleDiv.scrollTop=t;
			}else{
				var s=lastY-downY;
				var t=scoleDiv.scrollTop;
				t=t-s*bl_height;
				scoleDiv.scrollTop=t;
			}
			
		}
		
		lastX=downX;
		lastY=downY;
		
		var e=event || window.event;
		if(e && e.stopPropagation()){
			e.stopPropagation();
		}else{
			e.cancelBubble=true;
		}
		
	}
	
	
	
	
	