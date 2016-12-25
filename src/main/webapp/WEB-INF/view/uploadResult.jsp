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
<link
	href="http://cdn.bootcss.com/twitter-bootstrap/2.0.4/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="http://cdn.bootcss.com/twitter-bootstrap/2.0.4/css/bootstrap-responsive.min.css"
	rel="stylesheet">
<link href="http://cdn.bootcss.com/jqueryui/1.12.0-rc.2/jquery-ui.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="../resources/css/default.css">
<link href="../resources/css/index-style.css" rel="stylesheet"
	type="text/css" media="all" />	
<link href="../resources/css/fileinput.css" media="all" rel="stylesheet"
	type="text/css" />
<script src="http://cdn.bootcss.com/jquery/1.9.1/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/jqueryui/1.12.0-rc.2/jquery-ui.js"></script>
<script src="../resources/js/echo.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Asphalt8</title>
</head>
<body>

	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<div class="navbar">
					<div class="navbar-inner">
						<div class="container-fluid">
							<a data-target=".navbar-responsive-collapse"
								data-toggle="collapse" class="btn btn-navbar"><span
								class="icon-bar"></span><span class="icon-bar"></span><span
								class="icon-bar"></span></a> <a href="#" class="brand">Asphalt8</a>
							<div class="nav-collapse collapse navbar-responsive-collapse">
								<ul class="nav">
									<li><a href="../index.html">主页</a></li>
									<li class="active"><a href="upload.html">图片管理</a></li>
									<li><a href="editCar.html">新增赛车</a></li>
									<li class="dropdown"><a data-toggle="dropdown"
										class="dropdown-toggle" href="#">下拉菜单<strong class="caret"></strong></a>
										<ul class="dropdown-menu">
											<li><a href="#">下拉导航1</a></li>
											<li><a href="#">下拉导航2</a></li>
											<li><a href="#">其他</a></li>
											<li class="divider"></li>
											<li class="nav-header">标签</li>
											<li><a href="#">链接1</a></li>
											<li><a href="#">链接2</a></li>
										</ul></li>
								</ul>
								<ul class="nav pull-right">
									<li><a href="#">右边链接</a></li>
									<li class="divider-vertical"></li>
									<li class="dropdown"><a data-toggle="dropdown"
										class="dropdown-toggle" href="#">下拉菜单<strong class="caret"></strong></a>
										<ul class="dropdown-menu">
											<li><a href="#">下拉导航1</a></li>
											<li><a href="#">下拉导航2</a></li>
											<li><a href="#">其他</a></li>
											<li class="divider"></li>
											<li><a href="#">链接3</a></li>
										</ul></li>
								</ul>
							</div>
						</div>
					</div>
				</div>
				
				
				<a class="btn" href="upload.html">继续上传 >></a>
				<hr>
					<p id="uploadStatus" style="margin-bottom: 20px;"></p>
					<div id="alertSuccess" class="alert alert-success" style="display: none;font-size: medium;">
					<p class="lead text-warning" style="word-wrap: break-word;"></p>
					</div>
						
		<div class="tool_tip" style="margin-bottom: 100px">
			<ul class="tt-wrapper">
			</ul>
		</div>
		
					<div class="row-fluid">
					<div class="span4">
					</div>
					</div>
				
		
		<br>		
		<hr>
		<a class="btn" href="upload.html">继续上传 >></a>

			</div>
		</div>
	</div>
<script src="../resources/js/uploadResult.js"></script>
<script>
$(document)
.ready(
		function() {
			
			var str = '${uploadResult}';
			showResult(str);			
			
		});
</script>
</body>
</html>