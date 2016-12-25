$("#carName").autocomplete({
	source : function(request, response) {

		$.ajax({
			dataType : "json",
			type : "Get",
			url : "queryCarNameByKeyWord",
			data : {
				word : $("#carName").val()
			},
			success : function(data) {
				response(data);
			}
		});

	},
	minLength : 2
});

$("#uploadFile").fileinput({
	'allowedFileExtensions' : [ 'jpg', 'png', 'gif', 'm4v', 'mp4' ],
});

$("form").on("click", ":submit", function() {
	var name = $("#carName").val();

	if ($.trim(name) == '') {
		goTop();
		$("#alertInfo").html("请输入赛车名称!");
		$("#alertInfo").hide(100).show(600);
		return false;
	}

	return true;
});

function showTip() {
	layer.load(2);
};

function goTop() {
	var speed = 200; // 滑动的速度
	$('body,html').animate({
		scrollTop : 0
	}, speed);
};
