$(function() {

	initEcho();

});

function initEcho() {
	echo.init({
		offset : 0,
		throttle : 0
	});
}

function showResult(str) {

	console.log(str);
	var path = getContextPath();
	var result = JSON.parse(str); // convert json str to json object

	var images = result.imageUrlList;
	var video = result.videoUrl;
	var carName = result.carName;
	var error = result.error;

	if (images.length > 0) {
		$
				.each(
						images,
						function(i, value) {

							var pic = "<li><a href='#'><img class='lazy' src='../resources/img/blank.gif' data-echo='"
									+ ".."
									+ images[i]
									+ "' alt='' title='"
									+ carName
									+ "' /><span>"
									+ carName
									+ "</span></a></li>";

							$(".tt-wrapper:last").append(pic);

							if (i > 0 && i % 5 == 4) {
								var clear = "<div class='clear'></div>";
								$(".tt-wrapper:last").append(clear);

								var row = "<ul class='tt-wrapper'></ul>";
								$(".tool_tip").append(row);
							}

						});

	} else {
		$(".tool_tip").remove();
	}

	if (video) {
		var videoDiv = "<video src='../" + video
				+ "' controls='controls' width='100%' height='100%'></video>";
		$(".span4").append(videoDiv);
	}

	if (error) {
		$("#uploadStatus").html(carName + "文件上传失败");
		$("#alertSuccess p").html(error);
		$("#alertSuccess").fadeIn(1000);
		$(".tool_tip").remove();
	} else {
		$("#uploadStatus").html(carName + "文件上传成功");
		$(".tool_tip").fadeIn(1000);
	}

}

function getContextPath() {
	var localObj = window.location;
	var contextPath = localObj.pathname.split("/")[1];
	console.log("contextPath: " + contextPath);

	if (contextPath != "asphalt8") {
		contextPath = ""; // 生产环境中项目被发布成默认工程，url中没项目名称
	}

	var basePath = localObj.protocol + "//" + localObj.host + "/" + contextPath;

	console.log("localObj:  " + localObj);
	console.log("contextPath: " + contextPath);
	console.log("basePath: " + basePath);

	return basePath;
}