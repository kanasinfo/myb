$(function(){
var speed=2500;
var liWidth=104;
var move=104;
var obj=$("#js_scrollBox ul");
var allLi=obj.html();
var liLen=$("#js_scrollBox ul li").length;
var yd=liWidth*liLen;
obj.width(liWidth*liLen*3+"px");
obj.append(allLi);
obj.prepend(allLi);
obj.css({"margin-left":-yd+"px"});

function oneMove(style){
	if(style=="left"){
		var moveWidth=move;
		}else{
			var moveWidth=-move;
			}
	var leftPos=obj.css("margin-left").replace('px', '');
	leftPos=parseInt(leftPos);
	obj.stop(true,false).animate(
	{marginLeft:leftPos+moveWidth},
	500,
	function(){
		var now=parseInt(obj.css("margin-left").replace('px', ''));
		if(now>=0){
		obj.css("marginLeft",-yd+"px");
		}
		if(now<=-2*yd){
		obj.css("marginLeft",-yd+"px");
		}
}
	)
	
	}
$("#js_nextImg").click(function(){
	if(!obj.is(":animated")){oneMove();}
	
	});
$("#js_prevImg").click(function(){
	if(!obj.is(":animated")){oneMove("left");}
	});


var liTimer=setInterval(oneMove,speed);
$("#js_allBtm").mouseenter(function(){
	clearInterval(liTimer);
	});
$("#js_allBtm").mouseleave(function(){
liTimer=setInterval(oneMove,speed);
	});
});