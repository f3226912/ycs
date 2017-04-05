//记录鼠标是否按下
 var isClick=false;
 //按下鼠标时候的坐标
 var  defaultX;
 var  defaultY;
 //移动的时候的坐标
 var mouseX;
 var mouseY;
 //移动层距离上边和左边的距离
 var DivTop;
 var DivLeft;
 
 $(function(){     
	//按下鼠标
	 $("#moveDang").mousedown(function(e){
	         isClick=true; 
	         defaultX=e.pageX;
	            defaultY=e.pageY;
	         DivTop=$("#dangport").css("top");
	         DivLeft=$("#dangport").css("left");
	         DivTop=parseFloat(String(DivTop).substring(0,String(DivTop).indexOf("px")));
	         DivLeft=parseFloat(String(DivLeft).substring(0,String(DivLeft).indexOf("px")));
	         }); //moveDiv click fun
	 //移动鼠标
	 $("#moveDang").mousemove(function(e){
         // alert("mover");
          mouseX=e.pageX;
          mouseY=e.pageY;
          if(isClick &&mouseX>0 &&mouseY>0)
          { 
           var newTop=parseFloat(mouseY-defaultY);
           var newLeft=parseFloat(mouseX-defaultX);
           $("#dangport").css({"top":newTop+DivTop});
           $("#dangport").css({"left":newLeft+DivLeft});
         } //if end       
         });//mousemove fun end
	 //松开鼠标
	 $("#moveDang").mouseup(function(e){
	          isClick=false; 
	  }); //moveDiv click fun
	 
 });//$end