$(function () {

	$(".nav_li").not(":first-child").hover(
		function(){
			$(this).addClass("nav_li_hover");
			$(this).parent().parent().next(".subnavbg").show();			
	},
		function(){
			$(this).removeClass("nav_li_hover");
			$(this).parent().parent().next(".subnavbg").hide();	
		}
	);
	$(".h-r-nav").hover(
		function(){
			$(this).addClass("h-r-nav-hover");			
	},
		function(){
			$(this).removeClass("h-r-nav-hover");
		}
	);
	
	//菜单
	//$(".menu .tit").click(
	//	function(){$(this).next(".submenu").slideToggle(300)}
	//); 	
	
});



