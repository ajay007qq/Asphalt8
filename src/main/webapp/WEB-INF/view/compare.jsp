<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
	String path = request.getContextPath();
%>
<link
	href="http://cdn.bootcss.com/twitter-bootstrap/2.0.4/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="http://www.bootcss.com/p/layoutit/css/bootstrap-combined.min.css"
	rel="stylesheet">
<link
	href="http://cdn.bootcss.com/twitter-bootstrap/2.0.4/css/bootstrap-responsive.min.css"
	rel="stylesheet">
<link href="http://cdn.bootcss.com/jqueryui/1.12.0-rc.2/jquery-ui.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="<%=path%>/resources/css/default.css">
<link href="<%=path%>/resources/css/index-style.css" rel="stylesheet"
	type="text/css" media="all" />
<link href="<%=path%>/resources/css/fileinput.css" media="all"
	rel="stylesheet" type="text/css" />
<script src="http://cdn.bootcss.com/jquery/1.9.1/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/jqueryui/1.12.0-rc.2/jquery-ui.js"></script>
<script src="<%=path%>/resources/js/Chart.bundle.js"></script>
<title>Asphalt8</title>

<style>
canvas {
	-moz-user-select: none;
	-webkit-user-select: none;
	-ms-user-select: none;
}
</style>

<script>
		
        var arguments = ["氮气", "最大氮气", "速度", "最大速度",  "氮气速度", "最大氮气速度"];

        var barChartData = {
            labels: arguments,
            datasets: [{
                label: "${car1.carName}",
                backgroundColor: "rgba(220,20,60,0.5)",
                data: ["${carFeature1.baseNitro}", "${carFeature1.maxNitro}","${carFeature1.baseSpeed}", "${carFeature1.maxSpeed}", "${carFeature1.baseNitroSpeed}","${carFeature1.maxNitroSpeed}"]
            }, {
                label: "${car2.carName}",
                backgroundColor: "rgba(255,0,255,0.5)",
                data: ["${carFeature2.baseNitro}", "${carFeature2.maxNitro}","${carFeature2.baseSpeed}", "${carFeature2.maxSpeed}", "${carFeature2.baseNitroSpeed}","${carFeature2.maxNitroSpeed}"]
            }]

        };

        window.onload = function() {
        	
    		$("#alertSuccess").hide(200).show(1000);
        	
            var ctx = document.getElementById("canvas").getContext("2d");
            window.myBar = new Chart(ctx, {
                type: 'bar',
                data: barChartData,
                options: {
                    // Elements options apply to all of the options unless overridden in a dataset
                    // In this case, we are setting the border of each bar to be 2px wide and green
                    elements: {
                        rectangle: {
                            borderWidth: 1,
                            borderColor: 'rgb(0, 255, 0)',
                            borderSkipped: 'bottom'
                        }
                    },
                    responsive: true,
                    legend: {
                        position: 'top',
                    }
                }
            });

        };
    </script>

</head>
<body>

	<div class="header_bg">
		<div class="wrap">
			<div class="header">
				<div class="logo">
					<a href="<%=path%>/index.html"><label
							style="font-size: 2em; color: white">Asphalt8 狂野飙车8</label></a>
				</div>
				<div class="cssmenu">
					<ul>
						<li class="active"><a href="<%=path%>/index.html">首页</a></li>
						<li><a href="portfolio.html">赛道</a></li>
						<li><a href="<%=path%>/search">赛车</a>
							<ul>
								<li class="has-sub"><a href="<%=path%>/search?category=D">D 级</a></li>
								<li class="has-sub"><a href="<%=path%>/search?category=C">C 级</a></li>
								<li class="has-sub"><a href="<%=path%>/search?category=B">B 级</a></li>
								<li class="has-sub"><a href="<%=path%>/search?category=A">A 级</a></li>
								<li class="has-sub"><a href="<%=path%>/search?category=S">S 级</a></li>
							</ul></li>
						<li><a href="staff.html">比赛</a></li>
						<li><a href="blog.html">社区</a></li>
						<li class="last"><a href="contact.html">排名</a></li>
						<div class="clear"></div>
					</ul>
				</div>
				<div class="clear"></div>
				<div class="top-nav">
					<nav class="clearfix">
						<ul>
							<li><a href="index.html">Home</a></li>
							<li><a href="portfolio.html">Portfolio</a></li>
							<li><a href="service.html">Services</a></li>
							<li><a href="staff.html">Staff</a></li>
							<li class="has-sub"><a href="index.html">Pages</a></li>
							<li><a href="feature.html">Features</a></li>
							<li><a href="blog.html">Blog</a></li>
							<li class="last"><a href="contact.html">Contact</a></li>
						</ul>
						<a href="#" id="pull">Menu</a>
					</nav>
				</div>
				<div class="clear"></div>
			</div>
		</div>
	</div>

	<div id="alertSuccess" class="alert alert-success"
		style="padding-left: 10%; margin-bottom: 50px;">
		<h1 style="font-size: 2em; color: white">性能比较</h1>
	</div>

	<div id="container" style="width: 80%; margin: 50px auto;">
		<canvas id="canvas"></canvas>
	</div>

	<div class="row-fluid">
		<div class="span12">
			<div class="row-fluid">
				<div class="span6">
					<a href="../../car/${car1.carName}"> <img alt="140x140"
						src="<%=path%>/resources/image/${car1.carId}/${car1.shortCarName}-1.jpg"
						class="img-rounded" style="width: 80%; margin-left: 20%" />
					</a>
				</div>
				<div class="span6">
					<a href="../../car/${car2.carName}"> <img alt="140x140"
						src="<%=path%>/resources/image/${car2.carId}/${car2.shortCarName}-1.jpg"
						class="img-rounded" style="width: 80%" />
					</a>
				</div>
			</div>


			<div class="btm_grid_bg" style="width: 100%">
				<div
					style="width: 80%; margin: 50px auto; padding-top: 50px; padding-bottom: 50px;">
					<table class="table table-hover table-striped">
						<thead>
							<tr style="color: white; font-size: medium;">
								<th width="33%">性能</th>
								<th width="33%">${car1.carName}</th>
								<th>${car2.carName}</th>
							</tr>
						</thead>
						<tbody>
							<tr class="success">
								<td>赛车级别</td>
								<td>${car1.carCategory}</td>
								<td>${car2.carCategory}</td>
							</tr>
							<tr class="error">
								<td>价格</td>
								<td>${car1.price}</td>
								<td>${car2.price}</td>
							</tr>
							<tr class="warning">
								<td>性能分</td>
								<td>${carFeature1.totalBaseRank}</td>
								<td>${carFeature2.totalBaseRank}</td>
							</tr>
							<tr class="info">
								<td>最大性能分</td>
								<td>${carFeature1.totalMaxRank}</td>
								<td>${carFeature2.totalMaxRank}</td>
							</tr>
							<tr class="success">
								<td>速度(km/h)</td>
								<td>${carFeature1.baseSpeed}</td>
								<td>${carFeature2.baseSpeed}</td>
							</tr>
							<tr class="error">
								<td>最大速度(km/h)</td>
								<td>${carFeature1.maxSpeed}</td>
								<td>${carFeature2.maxSpeed}</td>
							</tr>
							<tr class="warning">
								<td>氮气强度(km/h)</td>
								<td>${carFeature1.baseNitro}</td>
								<td>${carFeature2.baseNitro}</td>
							</tr>
							<tr class="info">
								<td>最大氮气强度(km/h)</td>
								<td>${carFeature1.maxNitro}</td>
								<td>${carFeature2.maxNitro}</td>
							</tr>
							<tr class="success">
								<td>氮气推进速度(km/h)</td>
								<td>${carFeature1.baseNitroSpeed}</td>
								<td>${carFeature2.baseNitroSpeed}</td>
							</tr>
							<tr class="error">
								<td>最大氮气推进速度(km/h)</td>
								<td>${carFeature1.maxNitroSpeed}</td>
								<td>${carFeature2.maxNitroSpeed}</td>
							</tr>
							<tr class="warning">
								<td>加速时间(秒)</td>
								<td>${carFeature1.baseAcceleration}</td>
								<td>${carFeature2.baseAcceleration}</td>
							</tr>
							<tr class="info">
								<td>最快加速时间(秒)</td>
								<td>${carFeature1.maxAcceleration}</td>
								<td>${carFeature2.maxAcceleration}</td>
							</tr>
							<tr class="success">
								<td>操控性(Gs)</td>
								<td>${carFeature1.baseHandling}</td>
								<td>${carFeature2.baseHandling}</td>
							</tr>
							<tr class="error">
								<td>最佳操控性(Gs)</td>
								<td>${carFeature1.maxHandling}</td>
								<td>${carFeature2.maxHandling}</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>

	<!-- start footer  -->
	<div class="footer_bg" style="margin-top: -50px">
		<div class="wrap">
			<div class="footer">
				<div class="copy">
					<p>Copyright &copy; 2016.Company All rights reserved.</p>
				</div>
				<div class="clear"></div>
			</div>
		</div>
	</div>

</body>
</html>