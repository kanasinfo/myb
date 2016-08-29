$(function(){
   $(window).scroll(function () {
     if($(window).scrollTop() >= 30)//距离顶部多少高度显示按钮
     {
     $('#goTopBtn').slideDown(200);
     }else
     {
     $('#goTopBtn').slideUp(200);
     }
   }); 
     
   $('#btn-gototop').click(function(){
    $('body,html').animate({scrollTop:0},500)
   });
   
   //按钮定位
   var win_width= $(window).width();      //窗口宽度
 
 })