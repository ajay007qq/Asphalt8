<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<title>排名</title>
<%
	String path = request.getContextPath();
%>
<!-- Bootstrap -->
<link
	href="http://cdn.bootcss.com/twitter-bootstrap/2.0.4/css/bootstrap.min.css"
	rel="stylesheet" media="screen">
<link
	href="http://cdn.bootcss.com/twitter-bootstrap/2.0.4/css/bootstrap-responsive.min.css"
	rel="stylesheet" media="screen">
<link href="resources/css/rank-styles.css" rel="stylesheet"
	media="screen">
<link href="resources/css/index-style.css" rel="stylesheet"
	media="screen">
<!--[if lte IE 8]><script language="javascript" type="text/javascript" src="vendors/flot/excanvas.min.js"></script><![endif]-->
<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
</head>
<body style="height: 100%; padding-bottom: 0;">
	<div class="header_bg">
		<div class="wrap">
			<div class="header">
				<div class="logo">
					<a href="index.html"> <label
						style="font-size: 2em; color: white">Asphalt8 狂野飙车8</label>
					</a>
				</div>
				<div class="logo">
					<div class="search">
						<form action="search" method="get">
							<input name="keyword" id="keyword" type="text" placeholder="赛车">
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
						<li class="last"><a href="rank">排名</a></li>
						<div class="clear"></div>
					</ul>
				</div>
				<div class="clear"></div>
				<div class="top-nav">
					<nav class="clearfix">
						<ul>
							<li><a href="index.html">首页</a></li>
							<li><a href="search">赛车</a></li>
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

	<div class="container-fluid">
		<div class="row-fluid">
			<!--/span-->
			<div class="span9" id="content">
				<div class="row-fluid">
					<!-- block -->
					<div class="block">
						<div class="navbar navbar-inner block-header">
							<div class="muted pull-left">加速度 Top 10</div>
						</div>
						<div class="block-content collapse in">
							<div class="span12">
								<table id="ac" class="table table-hover">
								</table>
							</div>
						</div>
					</div>
					<!-- /block -->
				</div>
			</div>
		</div>
	</div>

	<div class="container-fluid">
		<div class="row-fluid">
			<!--/span-->
			<div class="span9" id="content">
				<div class="row-fluid">
					<!-- block -->
					<div class="block">
						<div class="navbar navbar-inner block-header">
							<div class="muted pull-left">速度 Top 10</div>
						</div>
						<div class="block-content collapse in">
							<div class="span12">
								<table id="speed" class="table table-hover">
								</table>
							</div>
						</div>
					</div>
					<!-- /block -->
				</div>
			</div>
		</div>
	</div>
	
	<div class="container-fluid">
		<div class="row-fluid">
			<!--/span-->
			<div class="span9" id="content">
				<div class="row-fluid">
					<!-- block -->
					<div class="block">
						<div class="navbar navbar-inner block-header">
							<div class="muted pull-left">氮气 Top 10</div>
						</div>
						<div class="block-content collapse in">
							<div class="span12">
								<table id="nitro" class="table table-hover">
								</table>
							</div>
						</div>
					</div>
					<!-- /block -->
				</div>
			</div>
		</div>
	</div>
	
	<div class="container-fluid">
		<div class="row-fluid">
			<!--/span-->
			<div class="span9" id="content">
				<div class="row-fluid">
					<!-- block -->
					<div class="block">
						<div class="navbar navbar-inner block-header">
							<div class="muted pull-left">操控性 Top 10</div>
						</div>
						<div class="block-content collapse in">
							<div class="span12">
								<table id="handle" class="table table-hover">
								</table>
							</div>
						</div>
					</div>
					<!-- /block -->
				</div>
			</div>
		</div>
	</div>
	
		<div class="container-fluid">
		<div class="row-fluid">
			<!--/span-->
			<div class="span9" id="content">
				<div class="row-fluid">
					<!-- block -->
					<div class="block">
						<div class="navbar navbar-inner block-header">
							<div class="muted pull-left">最大加氮速度 Top 10</div>
						</div>
						<div class="block-content collapse in">
							<div class="span12">
								<table id="nitrospeed" class="table table-hover">
								</table>
							</div>
						</div>
					</div>
					<!-- /block -->
				</div>
			</div>
		</div>
	</div>
	
	<div class="clear"></div>
	<!-- start footer  -->
	<div class="footer_bg" style="margin-bottom: -200px;">
		<div class="wrap">
			<div class="footer">
				<div class="copy">
					<p>Copyright &copy; 2016.Company All rights reserved.</p>
				</div>
				<div class="clear"></div>
			</div>
		</div>
	</div>
	<!--/.fluid-container-->
	<script src="http://cdn.bootcss.com/jquery/1.9.1/jquery.min.js"></script>
	<script
		src="http://cdn.bootcss.com/twitter-bootstrap/2.0.4/bootstrap.min.js"></script>
	<script
		src="http://cdn.bootcss.com/twitter-bootstrap/2.0.4/bootstrap.min.js"></script>
	<script src="<%=path%>/resources/js/jquery.goup.min.js" type="text/javascript"></script>
	<script>
		$(document)
		.ready(
				function() {
						
					// 返回顶部
					$.goup({
						trigger : 300,
						bottomOffset : 150,
						locationOffset : 50,
						titleAsText : true
					});				
					
					var topAccelerationJsonStr = '${topAccelerationJsonStr}';
					var topNitroSpeedJsonStr = '${topNitroSpeedJsonStr}';
					var topNitroJsonStr = '${topNitroJsonStr}';
					var topHandlingJsonStr = '${topHandlingJsonStr}';
					var topSpeedJsonStr = '${topSpeedJsonStr}';
					
					var topAccelerationJson = JSON.parse(topAccelerationJsonStr);
					var topNitroSpeedJson = JSON.parse(topNitroSpeedJsonStr);
					var topNitroJson = JSON.parse(topNitroJsonStr);
					var topHandlingJson = JSON.parse(topHandlingJsonStr);
					var topSpeedJsonStr = JSON.parse(topSpeedJsonStr);
					
					var tb = "<tbody></tbody>";
					
					$.each(topAccelerationJson,function(i,value){
						var idx = i+1;
						var cn = value.carName;
						var scn = cn.replace(/\s/g,"");
						var icon = "<a href=\'./car/" + cn +"\'><img src=\""+ "./resources/image/" + value.carId +"/" + scn + "-icon.jpg\"" + "alt=\"Corsa Racer\"></a>";
						if(i==0){
							var th =  "<thead><tr><th>" + idx + "</th><th><a href=\'./car/" + cn +"\'>"+ cn+"</a></th><th>" + value.maxAcceleration+" 秒</th><th>" + icon+ "</th></thead>";
							$("#ac").append(th).append(tb);
						}else{
							var tr = "<tr> <td>" + idx + "</td><td><a href=\'./car/" + cn +"\'>"+ cn+"</a></td><td>" + value.maxAcceleration+" 秒</td><td>" + icon + "</td></tr>";
							$("#ac").append(tr);
						} 
					});
					
					$.each(topSpeedJsonStr,function(i,value){
						var idx = i+1;
						var cn = value.carName;
						var scn = cn.replace(/\s/g,"");
						var icon = "<a href=\'./car/" + cn +"\'><img src=\""+ "./resources/image/" + value.carId +"/" + scn + "-icon.jpg\"" + "alt=\"Corsa Racer\"></a>";
						if(i==0){
							var th =  "<thead><tr><th>" + idx + "</th><th><a href=\'./car/" + cn +"\'>"+ cn+"</a></th><th>" + value.maxSpeed+" 公里</th><th>" + icon +  "</th></thead>";
							$("#speed").append(th).append(tb);
						}else{
							var tr = "<tr> <td>" + idx + "</td><td><a href=\'./car/" + cn +"\'>"+ cn+"</a></td><td>"  + value.maxSpeed+" 公里</td><td>" + icon + "</td></tr>";
							$("#speed").append(tr);
						} 
					});
					
					$.each(topNitroJson,function(i,value){
						var idx = i+1;
						var cn = value.carName;
						var scn = cn.replace(/\s/g,"");
						var icon = "<a href=\'./car/" + cn +"\'><img src=\""+ "./resources/image/" + value.carId +"/" + scn + "-icon.jpg\"" + "alt=\"Corsa Racer\"></a>";
						if(i==0){
							var th =  "<thead><tr><th>" + idx + "</th><th><a href=\'./car/" + cn +"\'>"+ cn+"</a></th><th>" + value.maxNitro+" </th><th>" + icon +"</th></thead>";
							$("#nitro").append(th).append(tb);
						}else{
							var tr = "<tr> <td>" + idx + "</td><td><a href=\'./car/" + cn +"\'>"+ cn+"</a></td><td>" + value.maxNitro+" </td><td>" + icon + "</td></tr>";
							$("#nitro").append(tr);
						} 
					});
					
					$.each(topNitroSpeedJson,function(i,value){
						var idx = i+1;
						var cn = value.carName;
						var scn = cn.replace(/\s/g,"");
						var icon = "<a href=\'./car/" + cn +"\'><img src=\""+ "./resources/image/" + value.carId +"/" + scn + "-icon.jpg\"" + "alt=\"Corsa Racer\"></a>";
						if(i==0){
							var th =  "<thead><tr><th>" + idx + "</th><th><a href=\'./car/" + cn +"\'>"+ cn+"</a></th><th>" + value.maxNitroSpeed+" 公里</th><th>" + icon +"</th></thead>";
							$("#nitrospeed").append(th).append(tb);
						}else{
							var tr = "<tr> <td>" + idx + "</td><td><a href=\'./car/" + cn +"\'>"+ cn+"</a></td><td>" + value.maxNitroSpeed+" 公里</td><td>" + icon + "</td></tr>";
							$("#nitrospeed").append(tr);
						} 
					});
					
					$.each(topHandlingJson,function(i,value){
						var idx = i+1;
						var cn = value.carName;
						var scn = cn.replace(/\s/g,"");
						var icon = "<a href=\'./car/" + cn +"\'><img src=\""+ "./resources/image/" + value.carId +"/" + scn + "-icon.jpg\"" + "alt=\"Corsa Racer\"></a>";
						if(i==0){
							var th =  "<thead><tr><th>" + idx + "</th><th><a href=\'./car/" + cn +"\'>"+ cn+"</a></th><th>" + value.maxHandling+"</th><th>" + icon +"</th></thead>";
							$("#handle").append(th).append(tb);
						}else{
							var tr = "<tr> <td>" + idx + "</td><td><a href=\'./car/" + cn +"\'>"+ cn+"</a></td><td>" + value.maxHandling+"</td><td>" + icon + "</td></tr>";
							$("#handle").append(tr);
						} 
					});
				});
		</script>
</body>
</html>