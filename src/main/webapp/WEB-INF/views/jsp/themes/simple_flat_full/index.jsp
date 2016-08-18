<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ page isELIgnored ="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<!--[if lt IE 7 ]><html class="ie ie6" lang="en"> <![endif]-->
<!--[if IE 7 ]><html class="ie ie7" lang="en"> <![endif]-->
<!--[if IE 8 ]><html class="ie ie8" lang="en"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!--><!--<![endif]-->
<html lang="en">
	<head>
		<!-- Your Basic Site Informations -->
		<title>满意吧</title>
		<meta http-equiv="content-type" content="text/html;charset=utf-8"/>
		<meta name="description" content="Healthy -Awesome Health & Medical HTML Template">
		<meta name="author" content="2code.info">
		
		<!-- Mobile Specific Meta -->
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		
		<!-- Font -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/themes/simple_flat_full/fonts/font-awesome/css/font-awesome.css">

		<!-- Bootstrap -->
		<link href="${pageContext.request.contextPath}/assets/themes/simple_flat_full/css/bootstrap.css" rel="stylesheet">
		
		<!-- Style CSS -->
		<link href="${pageContext.request.contextPath}/assets/themes/simple_flat_full/css/style.css" rel="stylesheet" type="text/css">
		<link href="${pageContext.request.contextPath}/assets/themes/simple_flat_full/css/responsive.css" rel="stylesheet" type="text/css">
		<link href="${pageContext.request.contextPath}/assets/themes/simple_flat_full/css/animate.min.css" rel="stylesheet" type="text/css">
		
		<!-- Custom Colors -->
		<!-- <link rel="stylesheet" href="css/colors/green/color.css"> -->
		<!-- <link rel="stylesheet" href="css/colors/orange/color.css"> -->
		<!-- <link rel="stylesheet" href="css/colors/pink/color.css"> -->
		<!-- <link rel="stylesheet" href="css/colors/purple/color.css"> -->
		<!-- <link rel="stylesheet" href="css/colors/yellow/color.css"> -->
		
		<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
		<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
		<!--[if lt IE 9]>
		  <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->
		
		<!-- Favicons -->
		<link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/images/favicon.ico">
		<link rel="apple-touch-icon" href="${pageContext.request.contextPath}/assets/themes/simple_flat_full/images/apple-touch-icon.png">
		<link rel="apple-touch-icon" sizes="72x72" href="${pageContext.request.contextPath}/assets/themes/simple_flat_full/images/apple-touch-72x72.png">
		<link rel="apple-touch-icon" sizes="114x114" href="images/apple-touch-114x114.png">
	
	</head>
	<body>
		<div id="healthy_home_page" class="com_content view-featured healthy_home_page_section mulpurose-secion">
			<header id="header">
				<div id="header-top">
					<div class="container">
						<div class="row">
							<div class="col-md-7 col-sm-6">
								<div class="header-info">
									<span><i class="fa fa-tablet"></i>4008 000 000</span>
									<span class="fa-left"><i class="fa fa-envelope"></i>info@myb.com</span>
								</div>
							</div>
							<!-- Header Info End -->
							
							<div class="col-md-5 col-sm-6">
								<div class="pull-left">
									<div class="top-social-bar">
										<div class="social">
											<a href="#" class="link-rss" title="rss"><i class="fa fa-rss"></i></a>
											<a href="https://twitter.com/etrybiz" class="link-twitter" title="新浪微博" target="blank" ><i class="fa fa-twitter"></i></a>
											<a href="https://plus.google.com/u/1/100820321413378945561/posts" class="link-google-plus" title="微信" target="blank"><i class="fa fa-google-plus"></i></a>
											<a href="http://www.pinterest.com/ronycmt/" class="link-pinterest" title="腾讯微博"  target="blank"><i class="fa fa-pinterest"></i></a>
										</div>
										<!--Top Social Bar End -->
										
										<div class="header-search">
											<form class="search-form" role="search">
												<div class="input-group">
													<div class="header-sr-text">
														<input type="text" class="form-control" placeholder="Search...">
													</div>
													<div class="input-group-btn">
														<button type="submit" class="btn btn-default" title="Search">
															<span><i class="fa fa-search"></i>
																<span class="sr-only">搜索...</span>
															</span>
														</button>
													</div>
												</div>
											</form>
										</div><!-- Header Search End-->
									</div><!--Top Social Bar End -->
								</div><!-- Pull Left End -->
								
								<div class="pull-right">
									<div class="language-swcher">
										<a href="">登录</a> | <a href="">注册</a>
									</div><!-- Language Swcher End -->
								</div><!-- Pull Right End -->
							</div><!-- 5Col End-->
							
						</div>
					</div>
				</div>
				<!-- Header Top Section End -->
			</header>
			<!-- Header Seciotn End -->
			<div class="stickem-container">
				<!-- #navigation -->
				<nav id="navigation" class="stickem">
					
					<!-- .navigation-wrap -->
					<div class="navigation-wrap">
				
						<!-- .container -->
						<div class="container">
						
							<div class="logo">
								<a href="index.html"><img src="${pageContext.request.contextPath}/assets/images/logo2.png"></a> <!-- site logo -->
							</div>
							
							<ul class="menu">
								<li class="current"><a href="#">首页</a>
								</li>
								<li>
									<a href="#">方案</a>
								</li>
								<li>
									<a href="#">产品</a>
								</li>
								<li>
									<a href="#">文档</a>
								</li>
								<li>
									<a href="#">关于</a>
									<ul>
										<li><a href="#">媒体报道</a></li>
										<li><a href="#">联系我们</a></li>
									</ul>
								</li>								
							</ul>
							
						</div>
						<!-- .container end -->
					
					</div>
					<!-- .navigation-wrap end -->
						
				</nav><!--Navbar Header Shadwo End -->
				
				<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
					
					<!-- Wrapper for slides -->
					<div class="slideshow carousel-inner">
						<div class="item active">
							<img src="${pageContext.request.contextPath}/assets/images/slide1.jpg" class="img-responsive" alt="First slide">
							<!-- Static Header -->
							<div class="header-text">
								<div class="container">
									<div class="row">
										<div class="col-md-12">
											<div class="slider-header-text">
												<h2>
													<span>专业 & 易用的满意度调查平台，有效提高客户关怀和管理。</span>
												</h3>
												<p>简单易用让您的调查更加轻松；专业数据分析师为您提供高效的满意度分析手段；支持多门店实时分析；</p>
											</div>
										</div>
									</div>
								</div>
							</div><!-- /header-text -->
						</div>
						<!-- Slider Item-1 End-->
						
						<div class="item">
							<img src="${pageContext.request.contextPath}/assets/images/slide2.jpg" class="img-responsive" alt="Second slide">
							<!-- Static Header -->
							<div class="header-text">
								<div class="container">
									<div class="row">
										<div class="col-md-12">
											<div class="slider-header-text">
												<h2>
													<span>专业 & 易用的满意度调查平台，有效提高客户关怀和管理。</span>
												</h2>
												<p>简单易用让您的调查更加轻松；专业数据分析师为您提供高效的满意度分析手段；支持多门店实时分析；</p>
											</div>
										</div>
									</div>
								</div>
							</div><!-- /header-text -->
						</div>
						<!-- Slider Item-2 End-->
						
						
					</div>
					<!-- Controls -->
					<a class="right carousel-control" href="#carousel-example-generic" data-slide="prev">
						<i class="fa fa-angle-left"></i>
					</a>
					<a class="right carousel-control" href="#carousel-example-generic" data-slide="next">
						<i class="fa fa-angle-right"></i>
					</a>
				</div>
				<!-- Home Slider Part End-->
		
				<div id="features">
					<div class="container">
						<div class="row">
							<div class="col-md-12">
								<div class="features features-main triggerAnimation animated" data-animate="fadeInUp">
									<div class="header">
										<h2>专业 & 易用的满意度调查平台，有效提高客户关怀和管理。</h2>
										<p>简单易用让您的调查更加轻松；专业数据分析师为您提供高效的满意度分析手段；支持多门店实时分析；</p>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- Features End -->
				<div class="clearfix"></div>
			
				<div id="services">
					<div class="container">
						<div class="row triggerAnimation animated" data-animate="fadeInUp">
							<div class="sections services-main">
								
								<div class="col-md-4 col-xs-6">
									<div class="item">
										<h4>发现感知</h4>
										<div class="item-section">
											<div class="pull-left">
												<i class="fa fa-eye"></i>
											</div>
											<div class="pull-right">
												<p>简单易用让您的调查更加轻松；专业数据分析师为您提供高效的满意度分析手段；支持多门店实时分析；</p>
											</div>
										</div>
									</div>
								</div>
								
								<div class="col-md-4 col-xs-6">
									<div class="item">
										<h4>改变关怀</h4>
										<div class="item-section">
											<div class="pull-left">
												<i class="fa fa-heart"></i>
											</div>
											<div class="pull-right">
												<p>简单易用让您的调查更加轻松；专业数据分析师为您提供高效的满意度分析手段；支持多门店实时分析；</p>
											</div>
										</div>
									</div>
								</div>
								
								<div class="col-md-4 col-xs-6">
									<div class="item">
										<h4>成就满意</h4>
										<div class="item-section">
											<div class="pull-left">
												<i class="fa fa-flag-o"></i>
											</div>
											<div class="pull-right">
												<p>简单易用让您的调查更加轻松；专业数据分析师为您提供高效的满意度分析手段；支持多门店实时分析；</p>
											</div>
										</div>
									</div>
								</div>
								
							</div>
						</div>
					</div>
				</div>
				
				<!-- Services End -->
				<div class="clearfix"></div>
				
				<div class="sections appointment-main">
					<div class="container">
						<div class="row triggerAnimation animated" data-animate="fadeInUp">
							<div class="col-md-12">
								<div class="app-bg">
									<div class="pull-left">
										<h3>满意吧满意度调查平台.</h3>
										<p>简单易用让您的调查更加轻松；专业数据分析师为您提供高效的满意度分析手段；支持多门店实时分析；简单易用让您的调查更加轻松；专业数据分析师为您提供高效的满意度分析手段；支持多门店实时分析；
										</p>
									</div>
									<div class="pull-right">
										<div class="btn-section">
											<a class="btn btn-primary" href="＃" role="button" target="blank">查看详情</a>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="clearfix"></div>
				<!-- Appointment End -->
			
				<div id="latestblog">
					<div class="container">
						<div class="sectionsbd latestblog-main">
							<div class="row triggerAnimation animated" data-animate="fadeInDown">
								<div class="col-md-12">
									<div class="header">
										<h3>产品</h3>
										<span><a class="view" href="＃" target="blank">查看更多 <i class="fa fa-angle-double-right"></i></a></span>
									</div>
								</div>
							</div> <!-- Bolg header end -->
							
							<div class="row triggerAnimation animated" data-animate="fadeInUp">
								<div class="col-md-4 col-sm-6 col-xs-6">
									<div class="item">
										<div class="item-coments">
											<div class="artst-pic pull-left">
												<img src="http://placehold.it/265x155" alt="" class="img-responsive" />
												
											</div>
											<div class="pull-right">
												<div class="counter-tab">
													<div class="counter_like"><i class="fa fa-heart"></i> <br>2256</div>
													<div class="counter_comnt"><i class="fa fa-comment"></i><br> 359</div>
												</div>
											</div>
										</div>
										<div class="sub-header">
											<h5><a href="#">餐饮行业</a></h5>
											<p>简单易用让您的调查更加轻松；专业数据分析师为您提供高效的满意度分析手段；支持多门店实时分析；</p>
											<div class="btn-section">
												<a class="btn btn-primary" href="#" role="button" target="blank">创建问卷</a>
											</div>
										</div>
									</div><!-- Item End-->
								</div>
								<!-- Latestblog Item-1 End -->
								
								<div class="col-md-4 col-sm-6 col-xs-6">
									<div class="item">
										<div class="item-coments">
											<div class="artst-pic pull-left">
												<img src="http://placehold.it/265x155" alt="" class="img-responsive" />
											</div>
											<div class="pull-right">
												<div class="counter-tab">
													<div class="counter_like"><i class="fa fa-heart"></i> <br>2256</div>
													<div class="counter_comnt"><i class="fa fa-comment"></i><br> 359</div>
												</div>
											</div>
										</div>
										<div class="sub-header">
											<div class="art-title">
												<a href="#"><h5>银行业</h5></a>
											</div>
											<p>简单易用让您的调查更加轻松；专业数据分析师为您提供高效的满意度分析手段；支持多门店实时分析；</p>
											<div class="btn-section">
												<a class="btn btn-primary" href="#" role="button" target="blank">创建问卷</a>
											</div>
										</div>
									</div>
								</div> <!-- Latestblog Item-2 End -->
								
								<div class="col-md-4">
									<div class="item">
										<div class="item-coments">
											<div class="artst-pic pull-left">
												<img src="http://placehold.it/265x155" alt="" class="img-responsive" />
												
											</div>
											<div class="pull-right">
												<div class="counter-tab">
													<div class="counter_like"><i class="fa fa-heart"></i> <br>2256</div>
													<div class="counter_comnt"><i class="fa fa-comment"></i><br> 359</div>
												</div>
											</div>
										</div>
										<div class="sub-header">
											<a href="#" target="blank"><h5>汽车服务业</h5></a>
											<p>现场医疗管理专家及健康福利策划专家的专业团队，整合了北京著名三甲医院的医疗资源，为客户 ...</p>
											<div class="btn-section">
												<a class="btn btn-primary" href="#" role="button" target="blank">创建问卷</a>
											</div>
										</div>
									</div>
								</div> <!-- Latestblog Item-3 End -->
							</div> <!-- blog post section end -->
						</div>
					</div>
				</div> <!-- Blog End -->
				<div class="clearfix"></div>
				
				<!-- #Our Doctor End -->
			
				<div id="testimonials-newsletter">
					<div class="container">
						<div class="row">
							<div class="col-md-6 col-sm-6 col-xs-6">
								<div class="sectionsbd testimonials-main triggerAnimation animated" data-animate="fadeInLeft">
									<div class="header">
										<h3>人才招聘</h3>
										<a class="view" href="#" target="blank">详细<i class="fa fa-angle-double-right"></i></a>
									</div>
									<!-- Testimonials Header End -->
									
									<div class="carousel slide" data-ride="carousel" id="quote-carousel">
													
										<!-- Carousel Slides / Quotes -->
										<div class="carousel-inner">
										
										  <!-- Quote 1 -->
										  <div class="item active">
											<blockquote>
												<div class="row">
													<div class="col-md-2 col-sm-2">
													  <img class="img-circle" src="http://placehold.it/100x100" style="width: 68px;height: 68px;" alt="">
													</div>
													<div class="col-md-10 col-sm-10">
													  <p>制定并实施营销计划方案，对方案进行全方位的跟踪，指导相关效果分析和数据统计!</p>
													</div>
												</div>
											</blockquote>
											<div class="author">
												<strong>需求</strong><br>
												<small>职位</small>
											</div>
										  </div>
										  <!-- Quote 2 -->
										  <div class="item">
											<blockquote>
												<div class="row">
													<div class="col-md-2 col-sm-2">
													  <img class="img-circle" src="http://placehold.it/100x100" style="width: 68px;height: 68px;" alt="">
													</div>
													<div class="col-md-10 col-sm-10">
													  <p>制定并实施营销计划方案，对方案进行全方位的跟踪，指导相关效果分析和数据统计!</p>
													</div>
												</div>
											</blockquote>
											<div class="author">
												<strong>需求</strong><br>
												<small>职位</small>
											</div>
										  </div>
										  <!-- Quote 3 -->
										  <div class="item">
											<blockquote>
												<div class="row">
													<div class="col-md-2 col-sm-2">
													  <img class="img-circle" src="http://placehold.it/100x100" style="width: 68px;height: 68px;" alt="">
													</div>
													<div class="col-md-10 col-sm-10">
													  <p>制定并实施营销计划方案，对方案进行全方位的跟踪，指导相关效果分析和数据统计!</p>
													</div>
												</div>
											</blockquote>
											<div class="author">
												<strong>需求</strong><br>
												<small>职位</small>
											</div>
										  </div>
										</div>
					
										<!-- Carousel Buttons Next/Prev -->
										<a data-slide="prev" href="#quote-carousel" class="left carousel-control"><i class="fa fa-angle-left"></i></a>
										<a data-slide="next" href="#quote-carousel" class="right carousel-control"><i class="fa fa-angle-right"></i></a>
									</div>
									
								</div>							
							</div>				
							<!-- Testimonials End -->
				
							<div class="col-md-6 col-sm-6 col-xs-6">
								<div class="sectionsbd newsletter-main triggerAnimation animated" data-animate="fadeInRight">
									<div class="header">
										<h3>联系我们</h3>
									</div>
									<!-- Newsletter Header End -->
									<form role="form">
										<div class="form-group">
											<input type="email" class="form-control" id="exampleInputEmail1" placeholder="输入您的电子邮箱 ...">
										</div>
										<button type="submit" class="btn btn-default">提交</button>
									</form>
									<p>非常感谢您对我们公司的关注，我们回尽快的跟您取得联系，祝您生活愉快 ...</p>
								</div>
								<!-- Newsletter End -->
							</div>
						</div>
					</div>
				</div>
				<!-- Testimonials Newsletter End -->
				<div class="clearfix"></div>
				<!-- Content Section End -->	
			
				<!-- Footer Start -->
				
				<div id="footer">
					<!-- .container -->
					<div class="container">
						
						<!-- .row -->
						<div class="row">
							<!-- .widget1 -->
							<div class="col-md-3 col-sm-3 col-xs-6 widget">
								
								<div class="triggerAnimation animated" data-animate="fadeInLeft">
									<div class="header">
										<h4>联系我们</h4>
									</div>
										
									<address>
										<p>满意吧</p><br>
										<p class="text-default">北京朝阳门.</p>
										<p class="text-default">Ph. : +01088888888</p>
										<p class="text-default">Email : service@myb.com</p>
									</address>
								</div>
							</div>
							<!-- .widget1 end -->
								
							<!-- .widget2 -->
							<div class="col-md-3 col-sm-3 col-xs-6 widget">
								<div class="triggerAnimation animated" data-animate="fadeInUp">
									<div class="header">
										<h4>新闻中心</h4>
									</div>
									<ul class="footer_nav_bar">
										<li> <!-- 1 -->
											<a href="#" title="Home" target="blank">- 集团动态</a>
										</li>
										<li> <!-- 2 -->
											<a href="#" title="About" target="blank">- 业务动态</a>
										</li>
										<li> <!-- 3 -->
											<a href="#" title="Medical Services" target="blank">- 行业资讯</a>
										</li> 
										<li> <!-- 4 -->
											<a href="#" title="Doctors" target="blank">- 媒体焦点</a>
										</li> 
										<li> <!-- 5 -->
											<a href="#" title="Blog" target="blank">- 通知公告</a>
										</li>
										<li> <!-- 6 -->
											<a href="#" title="Contact Us" target="blank">- 视频中心  </a>
										</li>
									</ul>
								</div>
							</div>
							<!-- .widget2 end -->
								
							<!-- .widget3 -->
							<div class="col-md-3 col-sm-3 col-xs-6 widget">
							
								<div class="triggerAnimation animated" data-animate="fadeInDown">
									<div class="header">
										<h4>友情链接</h4>
									</div>
									<ul class="footer_quick_link">
										<li> <!-- 1 -->
											<a href="#" title="Home" target="blank">- 东直门医院</a>
										</li>
										<li> <!-- 2 -->
											<a href="#" title="Conditions" target="blank">- 协和医院</a>
										</li>
										<li> <!-- 3 -->
											<a href="#" title="Medical Services" target="blank">- 同仁医院</a>
										</li> 
										<li> <!-- 4 -->
											<a href="#" title="For Patients" target="blank">- 宣武医院</a>
										</li> 
										<li> <!-- 5 -->
											<a href="#" title="Medical Records" target="blank">- 安贞医院</a>
										</li>
										<li> <!-- 6 -->
											 <a href="#" title="Plan Finder" target="blank">- 北医三院</a>
										</li>
									</ul>
								</div>
							</div>
							<!-- .widget3 end -->
							
							<!-- .widget4 -->
							<div class="col-md-3 col-sm-3 col-xs-6 widget">
								<div class="triggerAnimation animated" data-animate="fadeInRight">
									<div class="header">
										<h4>用户反馈</h4>
									</div>
									<ul class="etrybiz_tweets_widget">
										<li>查看.....</li>
									</ul>
								</div>
							</div><!-- .widget4 end -->
						</div><!-- .row end -->
					</div><!-- .container end -->
				</div><!-- #bottom end -->
				
				<!-- #footer -->
				<div id="footer-bottom">
					
					<!-- .container -->
					<div class="container">
						<div class="row">
							<div class="col-md-12 col-sm-11">
								<div class="pull-left"><p>&copy; 2016 myb . All Rights Reserved.</p></div>
							
								<div class="pull-right">
									<p class="text-default">Created by myb.com</p>
								</div>
							</div>
						</div>
					</div><!-- container end -->
				</div><!-- #footer Bottom End -->
			</div>	
        </div>
           
		<div class="back_top">
			<a id="back-to-top" href="#" class="btn btn-primary btn-lg back-to-top" role="button" title="Click to return on the top page" data-toggle="tooltip" data-placement="left"><i class="fa fa-angle-up"></i></a>
		</div>
		
		<!-- Back To Top End-->
		
		<!-- Main Js -->
		<script src="${pageContext.request.contextPath}/assets/themes/simple_flat_full/js/jquery.min.js"></script>
		<script src="${pageContext.request.contextPath}/assets/themes/simple_flat_full/js/bootstrap.min.js"></script>
		
		<!-- Java Script -->
		<script type="text/javascript" src="${pageContext.request.contextPath}/assets/themes/simple_flat_full/js/jquery-easing.js"></script>
		<script src="${pageContext.request.contextPath}/assets/themes/simple_flat_full/js/scripts.js"></script>
		<script src="${pageContext.request.contextPath}/assets/themes/simple_flat_full/js/waypoints.min.js"></script>
		<script src="${pageContext.request.contextPath}/assets/themes/simple_flat_full/js/custom.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/assets/themes/simple_flat_full/js/jquery.mobilemenu.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/assets/themes/simple_flat_full/js/jquery.stickem.js"></script>
		
 
	</body>
</html>