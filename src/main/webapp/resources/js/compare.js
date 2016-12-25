var arguments = [ "氮气强度", "最大氮气强度", "速度", "最大速度", "氮气推进速度", "最大氮气推进速度" ];

var randomColorFactor = function() {
	return Math.round(Math.random() * 255);
};

var randomColor = function() {
	return 'rgba(' + randomColorFactor() + ',' + randomColorFactor() + ','
			+ randomColorFactor() + ',.7)';
};

var barChartData = {
	labels : arguments,
	datasets : [
			{
				label : "${car1.carName}",
				backgroundColor : "rgba(220,20,60,0.5)",
				data : [ "${carFeature1.baseNitro}", "${carFeature1.maxNitro}",
						"${carFeature1.baseSpeed}", "${carFeature1.maxSpeed}",
						"${carFeature1.baseNitroSpeed}",
						"${carFeature1.maxNitroSpeed}" ]
			},
			{
				label : "${car2.carName}",
				backgroundColor : "rgba(255,0,255,0.5)",
				data : [ "${carFeature2.baseNitro}", "${carFeature2.maxNitro}",
						"${carFeature2.baseSpeed}", "${carFeature2.maxSpeed}",
						"${carFeature2.baseNitroSpeed}",
						"${carFeature2.maxNitroSpeed}" ]
			} ]

};

window.onload = function() {

	$("#alertSuccess").hide(200).show(1000);

	var ctx = document.getElementById("canvas").getContext("2d");
	window.myBar = new Chart(ctx, {
		type : 'bar',
		data : barChartData,
		options : {
			elements : {
				rectangle : {
					borderWidth : 1,
					borderColor : 'rgb(0, 255, 0)',
					borderSkipped : 'bottom'
				}
			},
			responsive : true,
			legend : {
				position : 'top',
			}
		}
	});

};