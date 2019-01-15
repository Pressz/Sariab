$.get(Hi.home() + "/BridgeController.php?Id=questionsoverview.sql&" + Hi.loginprotocol() , function(data, status){ 
    if (JSON.stringify(data).charAt(0) == "{")
        data = JSON.parse("[" + JSON.stringify(data) + "]");

    data.forEach(obj => {		
		$('.content').append($("<div>").html(
			$("<input type=\"button\" href=\"#\" value=\"مشاهده‌ی نمودار\" />").on("click", 
				function(){
						Hi.modal($('<div style="direction:ltr;" class="pie"></div>').CanvasJSChart({
							title: {
								text: obj['Title']
							},
							data: [{
									type: "pie",
									// startAngle: 45,
									// showInLegend: "true",
									// legendText: "{label}",
									// indexLabel: "{label} ({y})",
									// yValueFormatString:"#,##0.#"%"",
									dataPoints: [
										{ label: obj['Choice1'], y:  parseInt(obj['Choice1Count']) },
										{ label: obj['Choice2'], y:  parseInt(obj['Choice2Count']) },
										{ label: obj['Choice3'], y:  parseInt(obj['Choice3Count']) },
										{ label: obj['Choice4'], y:  parseInt(obj['Choice4Count']) }
									]
							}]
						}));
					}
				)
			)
			.append('<input type=\"button\" onclick="Hi.load(\'questions_edit\', ' + obj['Id'] + ')" href=\"#\" value=\"ویرایش سوال\" />')
			.append(obj['Title'])
		);

    });
});