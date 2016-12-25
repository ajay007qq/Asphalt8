<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
	String path = request.getContextPath();
%>
<!-- For-Mobile-Apps -->
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="keywords" content="Asphalt8,狂野飙车" />
	<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!-- //For-Mobile-Apps -->

<!-- Custom-Stylesheet-Links -->
	<!-- Bootstrap-Core-CSS --> <link rel="stylesheet" href="<%=path%>/resources/css/bootstrap.min.css" type="text/css" media="all"/>
	<!-- Index-Page-Styling --> <link rel="stylesheet" href="<%=path%>/resources/css/style.css" type="text/css" media="all" />
	<!-- Owl-Carousel-CSS --> <link rel="stylesheet" href="<%=path%>/resources/css/owl.carousel.css" type="text/css" media="all"/>
	<!-- Popup-Box-CSS --> <link rel="stylesheet" href="<%=path%>/resources/css/popuo-box.css" type="text/css" media="all"/>
<!-- //Custom-Stylesheet-Links -->

<!-- Web-Fonts -->
	<link href='http://fonts.useso.com/css?family=Open+Sans:400,300,600,700' rel='stylesheet' type='text/css'>
	<link href='http://fonts.useso.com/css?family=Racing+Sans+One' rel='stylesheet' type='text/css'>
	<link href='http://fonts.useso.com/css?family=Raleway:400,300,500,600,700,800,900' rel='stylesheet' type='text/css'>
<!-- //Web-Fonts -->
<title>${car.carName}</title>
</head>
<body>

	<!-- Header -->
	<div class="header">

		<!-- Navbar -->
		<nav class="navbar navbar-default">
			<div class="container-fluid">

				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="#">Asphalt8</a>
				</div>

				<div id="navbar" class="navbar-collapse collapse">
					<ul class="nav navbar-nav navbar-right">
						<li class="hover-effect"><a href="../index.html">首页</a></li>
						<li class="hover-effect"><a href="#about">关于</a></li>
						<li class="hover-effect"><a href="#features">性能参数</a></li>
						<li class="hover-effect"><a href="#skills">升级指数</a></li>
						<li class="hover-effect"><a href="#team">同级别赛车</a></li>	
					</ul>
				</div>

			</div>
		</nav>
		<!-- //Navbar -->

		<!-- Slider -->
		<div class="slider">
			<ul class="rslides" id="slider">
			
			<c:forEach items="${carImages}" var="carImage" varStatus="status">
				<li>
					<img src="<%=path%>/resources/image/${carImage.carId}/${carImage.fileName}" alt="Corsa Racer">
				</li>
			</c:forEach>

			</ul>
		</div>
		<!-- //Slider -->

	</div>
	<!-- //Header -->

	<!-- About -->
	<div class="about" id="about">
		<h1>${carIntroduction.carName}</h1>
		<div class="heading-underline"></div>

		<h3>${carIntroduction.description}</h3>

		<c:if test="${carVideo.fileName !=null}">		
		<div class="container" style="text-align:center;margin:50px auto"> 
			<video controls poster="<%=path%>/resources/image/${carFeature.carId}/${carFeature.shortCarName}-3.jpg">
			   <source src="<%=path%>/resources/video/${carVideo.fileName}" type="video/mp4">
			</video>
		</div>
		</c:if>

		<div class="container" style="margin-top: 60px">

			<div class="about-info">
				<div class="col-md-6 about-info-grid">
					<div class="about-info-list1">
						<h5>外观</h5>
						<ul>
							<li><span class="glyphicon glyphicon-arrow-right" aria-hidden="true"></span> ${carIntroduction.appearances1}</li>	
							<li><span class="glyphicon glyphicon-arrow-right" aria-hidden="true"></span> ${carIntroduction.appearances2}</li>
							<li><span class="glyphicon glyphicon-arrow-right" aria-hidden="true"></span> ${carIntroduction.appearances3}</li>
						</ul>
					</div>
					<div class="about-info-image">
						<img src="<%=path%>/resources/image/${carFeature.carId}/${carFeature.shortCarName}-1.jpg" alt="Corsa Racer">
					</div>
					<div class="clearfix"></div>
				</div>

				<div class="col-md-6 about-info-grid">
					<div class="about-info-list2">
						<h5>特点</h5>
						<ul>
							<li><span class="glyphicon glyphicon-arrow-right" aria-hidden="true"></span> ${carIntroduction.design1}</li>
							<li><span class="glyphicon glyphicon-arrow-right" aria-hidden="true"></span> ${carIntroduction.design2}</li>
							<li><span class="glyphicon glyphicon-arrow-right" aria-hidden="true"></span> ${carIntroduction.design3}</li>
						</ul>
					</div>
					<div class="about-info-image">
						<img src="<%=path%>/resources/image/${carFeature.carId}/${carFeature.shortCarName}-2.jpg" alt="Corsa Racer">
					</div>
					<div class="clearfix"></div>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>

	</div>
	<!-- //About -->

	<!-- Features -->
	<div class="features" id="features">
		<div class="container">

			<h2>性能参数</h2>
			<div class="heading-underline"></div>

			<div class="feature-grid">
				<div class="col-md-4 col-sm-4 feature1">
					<div class="row features-item sans-shadow text-center">
						<div class="features-icon">
							<img src="<%=path%>/resources/image/common/graphics.png" alt="Corsa Racer">
						</div>
						<div class="features-info">
							<h4>${carFeature.baseAcceleration} >> ${carFeature.maxAcceleration} </h4>
							<p>加速(秒)</p>
						</div>
					</div>
				</div>
				<div class="col-md-4 col-sm-4 feature1">
					<div class="row features-item sans-shadow text-center">
						<div class="features-icon">
							<img src="<%=path%>/resources/image/common/world.png" alt="Corsa Racer">
						</div>
						<div class="features-info">
							<h4>${carFeature.baseSpeed} >> ${carFeature.maxSpeed} </h4>
							<p>最大速度(km/h)</p>
						</div>
					</div>
				</div>
				<div class="col-md-4 col-sm-4 feature1">
					<div class="row features-item sans-shadow text-center">
						<div class="features-icon">
							<img src="<%=path%>/resources/image/common/engine.png" alt="Corsa Racer">
						</div>
						<div class="features-info">
							<h4>${carFeature.baseHandling} >> ${carFeature.maxHandling}</h4>
							<p>操控性(Gs)</p>
						</div>
					</div>
				</div>
				<div class="col-md-4 col-sm-4 feature1">
					<div class="row features-item sans-shadow text-center">
						<div class="features-icon">
							<img src="<%=path%>/resources/image/common/car.png" alt="Corsa Racer">
						</div> 
						<div class="features-info">
							<h4>${carFeature.baseNitro} >> ${carFeature.maxNitro}</h4>
							<p>氮气推进(km/h)</p>
						</div>
					</div>
				</div>
				<div class="col-md-4 col-sm-4 feature1">
					<div class="row features-item sans-shadow text-center">
						<div class="features-icon">
							<img src="<%=path%>/resources/image/common/track.png" alt="Corsa Racer">
						</div>
						<div class="features-info">
							<h4>${carFeature.baseUpgradeRank} / ${carFeature.maxUpgradeRank} </h4>
							<p>升级排名</p>
						</div>
					</div>
				</div>
				<div class="col-md-4 col-sm-4 feature1">
					<div class="row features-item sans-shadow text-center">
						<div class="features-icon">
							<img src="<%=path%>/resources/image/common/multiplayer.png" alt="Corsa Racer">
						</div>
						<div class="features-info">
							<h4>${carFeature.baseProRank} / ${carFeature.maxProRank}</h4>
							<p>专业排名</p>
						</div>
					</div>
				</div>
				<div class="clearfix"></div>
			</div>

		</div>
	</div>
	<!-- //Features -->

	<!-- Real -->
	<div class="real">

		<div class="container">
			<div class="col-md-4 col-sm-4 real-grid">
				<a href="../search">
				<img src="<%=path%>/resources/image/common/cars.png" alt="Corsa Racer">
				<h3>赛车</h3>
				<p>More than 130 real, licensed perfectly designed and handling cars to choose from.</p>
				</a>
			</div>
			<div class="col-md-4 col-sm-4 real-grid">
				<img src="<%=path%>/resources/image/common/tracks.png" alt="Corsa Racer">
				<h3>赛事</h3>
				<p>Drive through mofre than 30 licensed race tracks, accurate street circuits, dirt trials.</p>
			</div>
			<div class="col-md-4 col-sm-4 real-grid">
				<img src="<%=path%>/resources/image/common/players.png" alt="Corsa Racer">
				<h3>车手</h3>
				<p>Connect and compete against real people and players in the multiplayer races.</p>
			</div>
			<div class="clearfix"></div>
		</div>

	</div>
	<!-- //Real -->
	
			<!-- New -->
	<div class="new">
		<h3>改装</h3>
		<div class="heading-underline"></div>

		<div class="container">
			<div class="col-md-6 col-sm-6 new-grid">
				<img src="<%=path%>/resources/image/common/new-1.jpg" alt="Corsa Racer">
			</div>
			<div class="col-md-6 col-sm-6 new-grid">
				<h3>专业套件</h3>
				<ul>
					<li><h4>轮胎</h4></li>
					<li><h4>悬挂</h4></li>
					<li><h4>传动装置</h4></li>
					<li><h4>排气装置</h4></li>
				</ul>
			</div>
			<div class="clearfix"></div>
		</div>

	</div>
	<!-- //New -->

	<!-- Formats -->
	<div class="formats">

		<h3>${car.carCategory}级赛车</h3>
		<div class="heading-underline"></div>
		<p>Choose what you want to play from more than 10 different racing formats.</p>

		<!-- Owl-Carousel -->
		<div id="owl-demo" class="owl-carousel text-center">
		
			<c:forEach items="${carImages}" var="carImage" varStatus="status">
			<div class="item g1 popup-with-zoom-anim" href="#small-dialog">
				<img class="lazyOwl" data-src="<%=path%>/resources/image/${carImage.carId}/${carImage.fileName}" alt="Corsa Racer">
				<div class="caption">
					<h4>${carImage.carName}</h4>
					<span>Neque porro quisquam est qui dolorem </span>
				</div>
			</div>
			</c:forEach>
		
		</div>
		<!-- //Owl-Carousel -->

		<!-- Magnific-Popup -->
		<div class="caption-popup">
			<div id="small-dialog" class="mfp-hide innercontent">
				<h3>${carFeature.carName}</h3>
				<hr>
				<img class="img-responsive" src="<%=path%>/resources/image/${carFeature.carId}/${carFeature.shortCarName}-1.jpg" title="postname" />
				<p>elit. Etiam sit amet nunc nec magna accumsan ultricies eget a leo. Praesent nec libero aliquet, malesuada nibh et, tincidunt arcu. Aenean porta faucibus nisl. Fusce ultrices nec purus eget consequat. Phasellus pharetra dignissim lacus id rhoncus. In malesuada et mi non mollis. </p>
			</div>
		</div>
		<!-- //Magnific-Popup -->

	</div>
	<!-- //Formats -->

	<!-- Progressive-Effects -->
	<div class="progressive-effects" id="skills">
		<div class="container">

			<!-- Skills -->
			<div id="about-us" class="parallax">
				<h3>性能分${carFeature.totalBaseRank}</h3>
				<div class="heading-underline"></div>

				<div class="row">
					<div class="our-skills wow fadeInDown" data-wow-duration="1000ms" data-wow-delay="300ms" style="width: 60%;margin-left: 20%">
						<div class="single-skill wow fadeInDown" data-wow-duration="1000ms" data-wow-delay="300ms">
							<p class="lead">加速</p>
							<div class="progress">
								<div class="progress-bar progress-bar-primary six-sec-ease-in-out" role="progressbar" aria-valuetransitiongoal="${carFeature.accelerationRate}">
								${carFeature.accelerationRate}%
								</div>
							</div>
						</div>
						<div class="single-skill wow fadeInDown" data-wow-duration="1000ms" data-wow-delay="400ms">
							<p class="lead">最大速度</p>
							<div class="progress">
								<div class="progress-bar progress-bar-primary six-sec-ease-in-out" role="progressbar" aria-valuetransitiongoal="${carFeature.speedRate}">
								${carFeature.speedRate}%
								</div>
							</div>
						</div>
						<div class="single-skill wow fadeInDown" data-wow-duration="1000ms" data-wow-delay="500ms">
							<p class="lead">操控性</p>
							<div class="progress">
								<div class="progress-bar progress-bar-primary six-sec-ease-in-out" role="progressbar" aria-valuetransitiongoal="${carFeature.handlingRate}">
								${carFeature.handlingRate}%
								</div>
							</div>
						</div>
						<div class="single-skill wow fadeInDown" data-wow-duration="1000ms" data-wow-delay="600ms">
							<p class="lead">氮气推进</p>
							<div class="progress">
								<div class="progress-bar progress-bar-primary six-sec-ease-in-out" role="progressbar" aria-valuetransitiongoal="${carFeature.nitroRate}">
								${carFeature.nitroRate}%
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- //Skills -->

			<!-- Stats -->
			<div class="stats">

				<h3>${carFeature.carName}</h3>
				<div class="heading-underline"></div>

				<div class="stats-info">
					<div class="col-md-3 col-sm-3 stats-grid">
						<div class="stats-img">
							<img src="<%=path%>/resources/image/common/s2.png" alt="Corsa Racer">
						</div>
						<div class='numscroller numscroller-big-bottom' data-slno='1' data-min='0' data-max='${carStat.raceCount}' data-delay='.5' data-increment="1">${carStat.raceCount}</div>
						<p>参加比赛</p>
					</div>
					<div class="col-md-3 col-sm-3 stats-grid">
						<div class="stats-img">
							<img src="<%=path%>/resources/image/common/s3.png" alt="Corsa Racer">
						</div>
						<div class='numscroller numscroller-big-bottom' data-slno='1' data-min='0' data-max='${carStat.broughtCount}' data-delay='.8' data-increment="1">${carStat.broughtCount}</div>
						<p>购买记录</p>
					</div>
					<div class="col-md-3 col-sm-3 stats-grid">
						<div class="stats-img">
							<img src="<%=path%>/resources/image/common/s4.png" alt="Corsa Racer">
						</div>
						<div class='numscroller numscroller-big-bottom' data-slno='1' data-min='0' data-max='${carStat.likeCount}' data-delay='.5' data-increment="1">${carStat.likeCount}</div>
						<p>点赞</p>
					</div>
					<div class="col-md-3 col-sm-3 stats-grid">
						<div class="stats-img">
							<img src="<%=path%>/resources/image/common/s5.png" alt="Corsa Racer">
						</div>
						<div class='numscroller numscroller-big-bottom' data-slno='1' data-min='0' data-max='${carStat.awardCount}' data-delay='.8' data-increment="1">${carStat.awardCount}</div>
						<p>胜利</p>
					</div>
					<div class="clearfix"></div>
				</div>

			</div>
			<!-- //Stats -->

		</div>
	</div>
	<!-- Progressive-Effects -->

	<!-- Team -->
	<div class="team" id="team">
		<h3>同级别赛车</h3>
		<div class="heading-underline"></div>

		<div class="container">
			<div class="grid">
			<c:forEach items="${carsForAd}" var="adCar" varStatus="status">
					<figure>
					<img src="<%=path%>/resources/image/${adCar.carId}/${adCar.shortCarName}-1.jpg" alt="Corsa Racer"/>
					<figcaption>
						<h5><a href="${adCar.carName}" style="color: white;">${adCar.carName}</a></h5>
						<ul class="social">
							<li><a href="#" class="facebook" title="收藏"></a></li>
							<li><a href="#" class="twitter" title="购买"></a></li>
							<li><a href="../compare/${car.carName}/${adCar.carName}" class="googleplus" title="比较"></a></li>
						</ul>
					</figcaption>
				</figure>
			</c:forEach>	
			</div>
		</div>
	</div>
	<!-- //Team -->

		<!-- Footer -->
	<div class="footer">
		<div class="container">

			<div class="copyright">
				<p>Copyright &copy; 2016. All rights reserved.</p>
			</div>

		</div>
	</div>
	<!-- //Footer -->
	
<!-- Custom-JavaScript-File-Links -->

	<!-- Supportive-JavaScript --> <script type="text/javascript" src="<%=path%>/resources/js/jquery.min.js"></script>
	<!-- Necessary-JS-File-For-Bootstrap --> <script type="text/javascript" src="<%=path%>/resources/js/bootstrap.min.js"></script>

	<!-- Banner-Slider-JavaScript -->
	<script src="<%=path%>/resources/js/responsiveslides.min.js"></script>
	<script>
		$(function () {
			$("#slider").responsiveSlides({
				auto: true,
				nav: true,
				speed: 800,
				namespace: "callbacks",
				pager: true,
			});
		});
	</script>
	<!-- //Banner-Slider-JavaScript -->

	<!-- Owl-Carousel-JavaScript -->
	<script src="<%=path%>/resources/js/owl.carousel.js"></script>
	<script>
		$(document).ready(function() {
			$("#owl-demo").owlCarousel ({
				items : 4,
				lazyLoad : true,
				autoPlay : true,
				pagination : false,
			});
		});
	</script>
	<!-- //Owl-Carousel-JavaScript -->

	<!-- Magnific-Popup-Display-JavaScript -->
	<script src="<%=path%>/resources/js/jquery.magnific-popup.js" type="text/javascript"></script>
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
	});
	</script>
	<!-- //Magnific-Popup-Display-JavaScript -->

	<!-- Progressive-Effects-Animation-JavaScript -->
	<script type="text/javascript" src="<%=path%>/resources/js/jquery.inview.min.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/wow.min.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/mousescroll.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/main.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/numscroller-1.0.js"></script>
	<!-- //Progressive-Effects-Animation-JavaScript -->

	<!-- Slide-To-Top JavaScript (No-Need-To-Change) -->
	<script type="text/javascript">
		$(document).ready(function() {
			var defaults = {
				containerID: 'toTop', // fading element id
				containerHoverID: 'toTopHover', // fading element hover id
				scrollSpeed: 100,
				easingType: 'linear'
			};
			$().UItoTop({ easingType: 'easeOutQuart' });
		});
	</script>
	<a href="#" id="toTop" style="display: block;"> <span id="toTopHover" style="opacity: 0;"> </span></a>
	<!-- //Slide-To-Top JavaScript -->

	<!-- Smooth-Scrolling-JavaScript -->
	<script type="text/javascript" src="<%=path%>/resources/js/move-top.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/easing.js"></script>
	<script type="text/javascript">
			jQuery(document).ready(function($) {
				$(".scroll, .navbar li a, .footer li a").click(function(event){
					$('html,body').animate({scrollTop:$(this.hash).offset().top},1000);
				});
			});
	</script>
	<!-- //Smooth-Scrolling-JavaScript -->

<!-- //Custom-JavaScript-File-Links -->

</body>
</html>