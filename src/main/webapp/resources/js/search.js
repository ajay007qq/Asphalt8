$(function() {
	var pull = $('#pull');
	menu = $('nav ul');
	menuHeight = menu.height();

	$(pull).on('click', function(e) {
		e.preventDefault();
		menu.slideToggle();
	});

	initBtn();
	autoComplete();

	initEcho();
	showCars();

	$(window).resize(function() {
		var w = $(window).width();
		if (w > 320 && menu.is(':hidden')) {
			menu.removeAttr('style');
		}
	});
});

function loading() {
	layer.load(2);
};

function closeLoading() {
	setTimeout(function() {
		layer.closeAll('loading');
	}, 200);
};

function initEcho() {
	echo.init({
		offset : 0,
		throttle : 0
	});
	echo.render();
}

function initBtn() {
	$(".button-circle").click(function() {

		initEcho();
		disableBtn();

		$(".span_of_4").remove();

		$("#nocar").hide();
		var cat = $(this).find("i").html();

		loading();

		$.get("searchCar", {
			category : cat
		}, function(data) {

			closeLoading();

			if (data.length > 0) {
				var subdiv = "<div id='carsubdiv' class='span_of_4'></div>";
				$("#cardiv").append(subdiv);
				append(data);
				initEcho();
			} else {
				$("#nocar").delay(200).fadeIn(100);
			}

			enableBtn();

		});

		initEcho();

	});

	function disableBtn() {
		$(".button-circle").attr("disabled", "disabled");
	}

	function enableBtn() {
		$(".button-circle").removeAttr("disabled");
	}

	$('#form').on('submit', function() {
		$("#nocar").hide();
		$(".span_of_4").remove();
		loading();

		$.get("searchCar", {
			keyword : $("#keyword").val()
		}, function(data) {

			closeLoading();

			if (data.length > 0) {
				var subdiv = "<div id='carsubdiv' class='span_of_4'></div>";
				$("#cardiv").append(subdiv);
				append(data);
				initEcho();
			} else {
				$("#nocar").delay(200).fadeIn(100);
			}

			initEcho();
		});

		initEcho();

		return false;

	});

}

function autoComplete() {
	$("#keyword").autocomplete({
		source : function(request, response) {

			$.ajax({
				dataType : "json",
				type : "Get",
				url : "manage/queryCarNameByKeyWord",
				data : {
					word : $("#keyword").val()
				},
				success : function(data) {
					response(data);
				}
			});
		},
		minLength : 2
	});
}

function showCars() {

	var key = $("#keyword").val();
	var cat;
	if (key == "") {
		key = getUrlParameter("keyword");
		cat = getUrlParameter("category");
	}

	if (typeof (key) == "undefined") {
		key = "";
	}

	if (typeof (cat) == "undefined") {
		cat = "";
	}

	$("#nocar").hide();

	$.ajax({
		dataType : "json",
		type : "Get",
		url : "searchCar",
		data : {
			keyword : key,
			category : cat
		},
		success : function(data) {

			if (data.length > 0) {
				append(data);
			} else {
				$("#nocar").delay(200).fadeIn(100);
			}
		}
	});

}

function append(obj) {

	var path = getContextPath();

	$
			.each(
					obj,
					function(i, value) {
						var carName = obj[i].carName;
						var id = obj[i].carId;
						var imageName = obj[i].fileName;

						var image = "resources/image/" + id + "/" + imageName;

						var carDiv = "<div class='span1_of_4'><a href='car/"
								+ carName
								+ "'><img class='lazy' src='resources/img/blank.gif' data-echo='"
								+ image
								+ "' /></a><a class='popup-with-zoom-anim' href='#small-dialog'><h3>"
								+ carName + "</h3></a></div>";

						$(".span_of_4:last").append(carDiv);
						if (i > 0 && i % 4 == 3) {
							var clear = "<div class='clear'></div>";
							$(".span_of_4:last").append(clear);

							var rowDiv = "<div class='span_of_4'></div>";
							$("#cardiv").append(rowDiv);
						}

					});

}

function getUrlParameter(sParam) {
	var sPageURL = decodeURIComponent(window.location.search.substring(1)), sURLVariables = sPageURL
			.split('&'), sParameterName, i;

	for (i = 0; i < sURLVariables.length; i++) {
		sParameterName = sURLVariables[i].split('=');

		if (sParameterName[0] === sParam) {
			return sParameterName[1] === undefined ? true : sParameterName[1];
		}
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

	return contextPath;
}