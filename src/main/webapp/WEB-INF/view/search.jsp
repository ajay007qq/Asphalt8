<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Asphalt8</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%
	String path = request.getContextPath();
%>
<meta name="keywords" content="Asphalt8,狂野飙车" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link href="resources/css/index-style.css" rel="stylesheet"
	type="text/css" media="all" />
<link rel="shortcut icon" type="image/ico" href="resources/img/asphalt8.ico"> 	
<!--start slider -->
<link rel="stylesheet" href="resources/css/fwslider.css" media="all">
<link href="http://cdn.bootcss.com/jqueryui/1.12.0-rc.2/jquery-ui.css"
	rel="stylesheet">
<link href="http://www.bootcss.com/p/buttons/css/buttons.css"
	rel="stylesheet">	
<script src="http://cdn.bootcss.com/jquery/1.9.1/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/jqueryui/1.12.0-rc.2/jquery-ui.js"></script>
<script src="resources/js/css3-mediaqueries.js"></script>
<script src="resources/js/fwslider.js"></script>
<script src="resources/js/echo.min.js"></script>
<script src="resources/js/layer/layer.js"></script>		
<script type="text/javascript" src="resources/js/jquery-hover-effect.js"></script>
<script type="text/javascript">
//Image Hover
jQuery(document).ready(function(){
jQuery(function() {
	jQuery('ul.da-thumbs > li').hoverdir();
});
});
</script>
<!-- Add fancyBox main JS and CSS files -->
<script src="resources/js/jquery.magnific-popup.js"
	type="text/javascript"></script>
<script src="resources/js/jquery.goup.min.js" type="text/javascript"></script>	
<link href="resources/css/magnific-popup.css" rel="stylesheet"
	type="text/css">
<script>
			$(document).ready(function() {
				$('.popup-with-zoom-anim').magnificPopup({
					type: 'inline',
					fixedContentPos: false,
					fixedBgPos: true,
					overflowY: 'auto',
					closeBtnInside: true,
					preloader: false,
					midClick: true,
					removalDelay: 300,
					mainClass: 'my-mfp-zoom-in'
			});
				 //返回顶部	
		         $.goup({
		                trigger: 300,
		                bottomOffset: 150,
		                locationOffset: 50,
		                titleAsText: true
		            });	
				
		});
		</script>
<!--nav-->
<script src="resources/js/search.js" type="text/javascript"></script>	
</head>
<body>
	<div class="header_bg">
		<div class="wrap">
			<div class="header">
				<div class="logo">
					<a href="index.html">
						<label style="font-size: 2em; color: white">Asphalt8 狂野飙车8</label>
					</a>
				</div>
				<div class="logo">
					<div class="search">
		    		<form id="form">
		    			<input name="keyword" id="keyword" type="text" placeholder="赛车" value="${param.keyword}">
		    			<input type="submit" value="">
		    		</form>
					</div>
				</div>
				<div class="cssmenu">
					<ul>
						<li class="active"><a href="index.html">首页</a></li>
						<li><a href="#">赛道</a></li>
						<li><a href="search">赛车</a>
							<ul>
								<li class="has-sub"><a href="search?category=D">D 级</a></li>
								<li class="has-sub"><a href="search?category=C">C 级</a></li>
								<li class="has-sub"><a href="search?category=B">B 级</a></li>
								<li class="has-sub"><a href="search?category=A">A 级</a></li>
								<li class="has-sub"><a href="search?category=S">S 级</a></li>
							</ul></li>
						<li><a href="#">比赛</a></li>
						<li><a href="#">社区</a></li>
						<li class="last"><a href="#">排名</a></li>
						<div class="clear"></div>
					</ul>
				</div>
				<div class="clear"></div>
				<div class="top-nav">
					<nav class="clearfix">
						<ul>
							<li><a href="index.html">首页</a></li>
							<li><a href="#">赛车</a></li>
							<li><a href="#">赛道</a></li>
							<li><a href="#">比赛</a></li>
							<li><a href="#">排名</a></li>
							<li><a href="#">社区</a></li>
						</ul>
						<a href="#" id="pull">登录</a>
					</nav>
				</div>
				<div class="clear"></div>
			</div>
		</div>
	</div>

	<!-- start top_grid -->
	<div class="top_grid_bg">
		<div class="wrap">
			<div id="catbtn" style="text-align: right;padding: 2% 8%;" >
			<button class="button button-action button-circle">
			<i class="fa">D</i></button>
			<button class="button button-primary button-circle" style="margin-left: 20px;">
			<i class="fa">C</i></button>
			<button class="button button-highlight button-circle" style="margin-left: 20px;">
			<i class="fa">B</i></button>
			<button class="button button-caution button-circle" style="margin-left: 20px;">
			<i class="fa">A</i></button>
			<button class="button button-royal button-circle" style="margin-left: 20px;">
			<i class="fa">S</i></button>
			</div>
		</div>
		<!-- start mid_grid -->
		<div class="btm_grid_bg">
			<div class="wrap">
				<div id="cardiv" class="btm_grid" style="padding: 3% 2%;overflow:hidden">

					<div id="nocar" style="margin-top: 50px;text-align: center;">
						<h1 style="font-size: 2em; color: white">没有搜索结果</h1>
					</div>

					<div id="carsubdiv" class="span_of_4">						
					</div>
				</div>
			</div>
		</div>
		<!-- start magnific-->
		<div id="small-dialog" class="mfp-hide">
			<div class="pop_up">
				<h2>Aenean fringilla elementum</h2>
				<p>There are many variations of passages of Lorem Ipsum
					available, but the majority have suffered alteration in some form,
					by injected humour, or randomised words which don't look even
					slightly believable. If you are going to use a passage of Lorem
					Ipsum.</p>
			</div>
		</div>
		<div class="clear"></div>
		<!-- start footer  -->
		<div class="footer_bg">
			<div class="wrap">
				<div class="footer">
					<div class="copy">
						<p>Copyright &copy; 2016.Company All rights reserved.</p>
					</div>
					<div class="clear"></div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>