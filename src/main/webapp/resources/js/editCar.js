$("#vendor").autocomplete({
	source : function(request, response) {

		$.ajax({
			dataType : "json",
			type : "Get",
			url : "queryVendorsByKeyWord",
			data : {
				word : $("#vendor").val()
			},
			success : function(data) {
				response(data);
			}
		});

	},
	minLength : 2
});

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

function queryCarData() {

	$.ajax({
		dataType : "json",
		type : "Get",
		url : "queryCarData",
		data : {
			name : $("#carName").val()
		},
		success : function(data) {

			$("#alertSuccess").hide(interval);
			$("#alertInfo").hide(interval);

			console.log(data);
			if (data) {
				setValues(data);
			} else {
				resetValues();
			}
		},
		error : function() {

			$("#alertSuccess").hide(interval);
			$("#alertInfo").hide(interval);

			resetValues();
		}
	});
}

function resetValues() {
	$(":input:not(#carName)").val("");

	$("#raceCount").val(Math.floor(Math.random() * 99999));
	$("#broughtCount").val(Math.floor(Math.random() * 9999));
	$("#likeCount").val(Math.floor(Math.random() * 9999));
	$("#awardCount").val(Math.floor(Math.random() * 9999));

	$("#baseProRank").val(0);

}

function setValues(carInfo) {
	$("#price").val(carInfo.car.price);
	$("#carCategory").val(carInfo.car.carCategory);
	$("#vendor").val(carInfo.car.vendor);

	$("#baseUpgradeRank").val(carInfo.carFeature.baseUpgradeRank);
	$("#baseProRank").val(carInfo.carFeature.baseProRank);
	$("#maxUpgradeRank").val(carInfo.carFeature.maxUpgradeRank);
	$("#maxProRank").val(carInfo.carFeature.maxProRank);
	$("#baseAcceleration").val(carInfo.carFeature.baseAcceleration);
	$("#maxAcceleration").val(carInfo.carFeature.maxAcceleration);
	$("#baseSpeed").val(carInfo.carFeature.baseSpeed);
	$("#maxSpeed").val(carInfo.carFeature.maxSpeed);
	$("#baseHandling").val(carInfo.carFeature.baseHandling);
	$("#maxHandling").val(carInfo.carFeature.maxHandling);
	$("#baseNitro").val(carInfo.carFeature.baseNitro);
	$("#maxNitro").val(carInfo.carFeature.maxNitro);

	$("#description").val(carInfo.carIntroduction.description);
	$("#appearances1").val(carInfo.carIntroduction.appearances1);
	$("#appearances2").val(carInfo.carIntroduction.appearances2);
	$("#appearances3").val(carInfo.carIntroduction.appearances3);
	$("#design1").val(carInfo.carIntroduction.design1);
	$("#design2").val(carInfo.carIntroduction.design2);
	$("#design3").val(carInfo.carIntroduction.design3);

	$("#raceCount").val(carInfo.carStat.raceCount);
	$("#broughtCount").val(carInfo.carStat.broughtCount);
	$("#likeCount").val(carInfo.carStat.likeCount);
	$("#awardCount").val(carInfo.carStat.awardCount);

}

var interval = 500;

function showTip() {
	layer.msg('正在保存...', {
		icon : 16,
		time : interval
	});
};

function closeTip() {
	setTimeout(function() {
		layer.closeAll();
	}, interval);
}

function save() {

	if (validate()) {

		showTip();

		$.ajax({
			url : 'saveCar',
			type : 'POST',
			data : $('form').serialize(),
			dataType : 'json',
			success : function(data) {

				closeTip();
				var tip;

				if (data > 0) {
					tip = "<h3>" + $("#carName").val() + "保存成功!</h3>";
				} else {
					tip = "<h3>" + $("#carName").val() + "保存失败!</h3>";
				}
				$("#alertSuccess").html(tip);
				$("#alertSuccess").delay(interval).hide(interval)
						.show(interval);
			},
			error : function() {
				closeTip();
				var tip = "<h3>" + $("#carName") + "保存失败!</h3>";
				$("#alertSuccess").html(tip);
				$("#alertSuccess").delay(interval).hide(interval)
						.show(interval);
			}
		});

	}
}

function validate() {

	$("#alertSuccess").hide(interval);
	$("#alertInfo").hide(interval);

	if ($.trim($("#carName").val()) == '') {
		$("#alertInfo").html("<h3>请输入赛车名称</h3>");
		$("#alertInfo").show(interval);
		$("#carName").focus();
		return false;
	} else if ($.trim($("#vendor").val()) == '') {
		$("#alertInfo").html("<h3>请输入生产商</h3>");
		$("#alertInfo").show(interval);
		$("#vendor").focus();
		return false;
	} else if ($.trim($("#price").val()) == '') {
		$("#alertInfo").html("<h3>请输入价格</h3>");
		$("#alertInfo").show(interval);
		$("#price").focus();
		return false;
	}

	var re = /^[0-9]+[0-9]*]*$/;

	if (!re.test($("#price").val())) {
		$("#alertInfo").html("<h3>价格请输入有效数字</h3>");
		$("#alertInfo").show(interval);
		$("#price").focus();
		return false;
	}
	;

	if (!$("#baseUpgradeRank").val() == ''
			&& !re.test($("#baseUpgradeRank").val())) {
		$("#alertInfo").html("<h3>基础性能分请输入有效数字</h3>");
		$("#alertInfo").show(interval);
		$("#baseUpgradeRank").focus();
		return false;
	}
	;

	if (!$("#baseProRank").val() == '' && !re.test($("#baseProRank").val())) {
		$("#alertInfo").html("<h3>基础专业分请输入有效数字</h3>");
		$("#alertInfo").show(interval);
		$("#baseProRank").focus();
		return false;
	}
	;

	if (!$("#maxUpgradeRank").val() == ''
			&& !re.test($("#maxUpgradeRank").val())) {
		$("#alertInfo").html("<h3>最大性能分请输入有效数字</h3>");
		$("#alertInfo").show(interval);
		$("#maxUpgradeRank").focus();
		return false;
	}
	;

	if (!$("#maxProRank").val() == '' && !re.test($("#maxProRank").val())) {
		$("#alertInfo").html("<h3>最大专业分请输入有效数字</h3>");
		$("#alertInfo").show(interval);
		$("#maxProRank").focus();
		return false;
	}
	;

	var reg = /^[0-9]+(.[0-9]+)?$/;

	if (!$("#baseAcceleration").val() == ''
			&& !reg.test($("#baseAcceleration").val())) {
		$("#alertInfo").html("<h3>基础加速时间请输入有效数字</h3>");
		$("#alertInfo").show(interval);
		$("#baseAcceleration").focus();
		return false;
	}

	if (!$("#maxAcceleration").val() == ''
			&& !reg.test($("#maxAcceleration").val())) {
		$("#alertInfo").html("<h3>最快加速时间请输入有效数字</h3>");
		$("#alertInfo").show(interval);
		$("#maxAcceleration").focus();
		return false;
	}

	if (!$("#baseSpeed").val() == '' && !reg.test($("#baseSpeed").val())) {
		$("#alertInfo").html("<h3>基础速度请输入有效数字</h3>");
		$("#alertInfo").show(interval);
		$("#baseSpeed").focus();
		return false;
	}

	if (!$("#maxSpeed").val() == '' && !reg.test($("#maxSpeed").val())) {
		$("#alertInfo").html("<h3>最大速度请输入有效数字</h3>");
		$("#alertInfo").show(interval);
		$("#maxSpeed").focus();
	}

	if (!$("#baseHandling").val() == '' && !reg.test($("#baseHandling").val())) {
		$("#alertInfo").html("<h3>基础操控性请输入有效数字</h3>");
		$("#alertInfo").show(interval);
		$("#baseHandling").focus();
	}

	if (!$("#maxHandling").val() == '' && !reg.test($("#maxHandling").val())) {
		$("#alertInfo").html("<h3>基础操控性请输入有效数字</h3>");
		$("#alertInfo").show(interval);
		$("#maxHandling").focus();
	}

	if (!$("#baseNitro").val() == '' && !reg.test($("#baseNitro").val())) {
		$("#alertInfo").html("<h3>基础氮气强度请输入有效数字</h3>");
		$("#alertInfo").show(interval);
		$("#baseNitro").focus();
	}

	if (!$("#maxNitro").val() == '' && !reg.test($("#maxNitro").val())) {
		$("#alertInfo").html("<h3>最大氮气强度请输入有效数字</h3>");
		$("#alertInfo").show(interval);
		$("#maxNitro").focus();
	}

	if (!$("#raceCount").val() == '' && !re.test($("#raceCount").val())) {
		$("#alertInfo").html("<h3>最大专业分请输入有效数字</h3>");
		$("#alertInfo").show(interval);
		$("#raceCount").focus();
		return false;
	}
	;

	if (!$("#broughtCount").val() == '' && !re.test($("#broughtCount").val())) {
		$("#alertInfo").html("<h3>最大专业分请输入有效数字</h3>");
		$("#alertInfo").show(interval);
		$("#broughtCount").focus();
		return false;
	}
	;

	if (!$("#likeCount").val() == '' && !re.test($("#likeCount").val())) {
		$("#alertInfo").html("<h3>最大专业分请输入有效数字</h3>");
		$("#alertInfo").show(interval);
		$("#likeCount").focus();
		return false;
	}
	;

	if (!$("#awardCount").val() == '' && !re.test($("#awardCount").val())) {
		$("#alertInfo").html("<h3>最大专业分请输入有效数字</h3>");
		$("#alertInfo").show(interval);
		$("#awardCount").focus();
		return false;
	}
	;

	return true;
}
