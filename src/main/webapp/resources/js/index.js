$(document).ready(function() {
	$('.popup-with-zoom-anim').magnificPopup({
		type : 'inline',
		fixedContentPos : false,
		fixedBgPos : true,
		overflowY : 'auto',
		closeBtnInside : true,
		preloader : false,
		midClick : true,
		removalDelay : 300,
		mainClass : 'my-mfp-zoom-in'
	});

	// 返回顶部
	$.goup({
		trigger : 300,
		bottomOffset : 150,
		locationOffset : 50,
		titleAsText : true
	});
});

$(function() {
	var pull = $('#pull');
	menu = $('nav ul');
	menuHeight = menu.height();

	$(pull).on('click', function(e) {
		e.preventDefault();
		menu.slideToggle();
	});

	initSlider();
	initBanner();
	autoComplete();

	$(window).resize(function() {
		var w = $(window).width();
		if (w > 320 && menu.is(':hidden')) {
			menu.removeAttr('style');
		}
	});

	initSearch();

});

function initSlider() {

	var picList = new Array();

	picList[0] = "<div class=\"slide\" onmouseover=\"this.style.cursor='pointer'\" onclick=\"document.location='car/Renault DeZir';\"> <img src=\"resources/image/index/renault_dezir.jpg\"> <div class=\"slide_content\"> <div class=\"slide_content_wrap\"> <h4 class=\"title\">这里有最酷的跑车</h4> <p class=\"description\"> 速度与激情 与对手追逐 </p> </div> </div> </div>";
	picList[1] = "<div class=\"slide\"> <img src=\"resources/image/index/london.jpg\"> <div class=\"slide_content\"> <div class=\"slide_content_wrap\"> <h4 class=\"title\">在世界比赛争胜</h4> <p class=\"description\">致命一击 把对手撞翻</p> </div> </div> </div>";
	picList[2] = "<div class=\"slide\"> <img src=\"resources/image/index/alps.jpg\"> <div class=\"slide_content\"> <div class=\"slide_content_wrap\"> <h4 class=\"title\">无尽的天界 一路向前</h4> <p class=\"description\">加速 超越所有障碍</p> </div> </div> </div>";
	picList[3] = "<div class=\"slide\" onmouseover=\"this.style.cursor='pointer'\" onclick=\"document.location='car/Chevrolet Camaro GS';\"> <img src=\"resources/image/index/camaro.jpg\"> <div class=\"slide_content\"> <div class=\"slide_content_wrap\"> <h4 class=\"title\">坐上你最喜欢的跑车</h4> <p class=\"description\">这里 漂移只是基本技巧</p> </div> </div> </div>";
	picList[4] = "<div class=\"slide\" onmouseover=\"this.style.cursor='pointer'\" onclick=\"document.location='car/Dodge Dart GT';\"> <img src=\"resources/image/index/dodge_dart_gt.jpg\"> <div class=\"slide_content\"> <div class=\"slide_content_wrap\"> <h4 class=\"title\">氮气 氮气 氮气</h4> <p class=\"description\">重要的事情说三遍</p> </div> </div> </div>";
	picList[5] = "<div class=\"slide\"> <img src=\"resources/image/index/dodge_dart_gt_2.jpg\"> <div class=\"slide_content\"> <div class=\"slide_content_wrap\"> <h4 class=\"title\">比赛开始 准备好了吗</h4> <p class=\"description\">引擎正在响起...</p> </div> </div> </div>";
	picList[6] = "<div class=\"slide\"> <img src=\"resources/image/index/tokyo.jpg\"> <div class=\"slide_content\"> <div class=\"slide_content_wrap\"> <h4 class=\"title\">谁是你的对手</h4> <p class=\"description\">赛道上只有我才能战胜自己</p> </div> </div> </div>";
	picList[7] = "<div class=\"slide\" onmouseover=\"this.style.cursor='pointer'\" onclick=\"document.location='car/Tesla Model S';\"> <img src=\"resources/image/index/tesla.jpg\"> <div class=\"slide_content\"> <div class=\"slide_content_wrap\"> <h4 class=\"title\">玩命飙车</h4> <p class=\"description\">一旦出发 使命必达</p> </div> </div> </div>";
	picList[8] = "<div class=\'slide\'> <img src=\'resources/image/index/dubai.jpg\'> <div class=\'slide_content\'> <div class=\'slide_content_wrap\'> <h4 class=\'title\'>前方的十字路口</h4> <p class=\'description\'>终点并不遥远...</p> </div> </div> </div>";
	picList[9] = "<div class=\"slide\" onmouseover=\"this.style.cursor='pointer'\" onclick=\"document.location='car/Mercedes-Benz Biome';\"> <img src=\"resources/image/index/benz_biome.jpg\"> <div class=\"slide_content\"> <div class=\"slide_content_wrap\"> <h4 class=\"title\">硕大的引擎</h4> <p class=\"description\">谁来征服...</p> </div> </div> </div>";

	var len = picList.length;
	var rnd = randomArray(picList.length);

	var a = rnd[2];
	var b = rnd[7];
	var c = rnd[5];

	console.log("a=" + a + ",b=" + b + ",c=" + c);

	$(".slider_container").append(picList[a]);
	$(".slider_container").append(picList[b]);
	$(".slider_container").append(picList[c]);

}

function randomArray(num) {
	var count = num;
	var original = new Array;// 原始数组
	// 给原始数组original赋值
	for (var i = 0; i < count; i++) {
		original[i] = i;
	}
	original.sort(function() {
		return 0.5 - Math.random();
	});
	return original;
}

function initBanner() {
	var num = $("div.span1_of_3").length;
	var n = num - 3;

	for (var i = 0; i < n; i++) {
		var a = Math.floor(Math.random() * (num - i));
		$("div.span1_of_3")[a].remove();
	}

	var adList = new Array();

	adList[0] = "<div class='span1_of_3'> <a href='car/Audi R8 e-tron'><img src='resources/image/index/Audi_R8.jpg' alt=''></a> <a class='popup-with-zoom-anim' href='#small-dialog'><h3>Audi R8</h3></a> </div>";
	adList[1] = "<div class='span1_of_3'> <a href='car/Benz SL 65 AMG'><img src='resources/image/index/Benz_SL_65_AMG.jpg' alt='' /></a> <a class='popup-with-zoom-anim' href='#small-dialog'><h3>Benz SL 65 AMG</h3></a> </div>";
	adList[2] = "<div class='span1_of_3'> <a href='car/Citroen DS Survolt'><img src='resources/image/index/ds_survolt.jpg' alt='' /></a> <a class='popup-with-zoom-anim' href='#small-dialog'><h3>ds survolt</h3></a> </div>";
	adList[3] = "<div class='span1_of_3'> <a href='car/Renault CLIO RS'><img src='resources/image/index/renault_clio_rs.jpg' alt='' /></a> <a class='popup-with-zoom-anim' href='#small-dialog'><h3>Renault CLIO RS</h3></a> </div>";
	adList[4] = "<div class='span1_of_3'> <a href='car/Cadillac ATS'><img src='resources/image/index/cadillac_ats.jpg' alt='' /></a> <a class='popup-with-zoom-anim' href='#small-dialog'><h3>Cadillac ATS</h3></a> </div>";
	adList[5] = "<div class='span1_of_3'> <a href='car/Audi TTS Coupe'><img src='resources/image/index/audi_tts.jpg' alt='' /></a> <a class='popup-with-zoom-anim' href='#small-dialog'><h3>Audi TTS</h3></a> </div>";

	var rdm = randomArray(adList.length);

	var h = rdm[5];
	var t = rdm[1];
	var k = rdm[3];

	console.log("h=" + h + ",t=" + t + ",k=" + k);

	$(".span_of_3").append(adList[h]);
	$(".span_of_3").append(adList[t]);
	$(".span_of_3").append(adList[k]);

	var clr = "<div class='clear'></div>";
	$(".span_of_3").append(clr);

}

function autoComplete() {
	// Autocomplete for search

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

function initSearch() {

	$(":submit").on('click', function() {

		console.log("keyword: " + $("#keyword").val());

		var word = $("#keyword").val();

		if ($.trim(word) == '') {
			location.href = 'search?category=D';
			return false;
		} else {
			return true;
		}

	});

}
