$(document).ready(function(){
	$('#console_input').keyup(function(){
		if(event.keyCode == 13){
			Transmission.sendCommand($('#console_input').val(), dealwith_console);
		}
	});
	
	$("#linear_pre").click(function() {
		var output = [];
		var length = lineChartData.labels.length;
		for (var i=0; i<length; i++){
			output.push({x:i, y:lineChartData.datasets[0].data[i]*1});
		}
		Tool.nextLabel(lineChartData.labels[length-1],select_step,dealwith_label);
		Prediction.Predict(JSON.stringify(output),dealwith_prediction);
	});
	
	var dealwith_label = function(data) {
		console.log(data);
		predict_label = data;
	}
	
	var dealwith_prediction = function(data) {
		document.getElementById("canvas").height = 320;
		document.getElementById("canvas").width = 620;
		lineChartData.labels.push(predict_label);
		lineChartData.datasets[0].data.push(data);
		new Chart(document.getElementById("canvas").getContext("2d")).Line(lineChartData);
		printIntoConsole($('#console_output'), "Newly Predicted Value:" + data);
	};
	
	var dealwith_Graphic = function(data){
		var obj = JSON.parse(data);
		var array = obj.value;
		select_step = obj.step + obj.unit;
		var key = [];
		var val = [];
		for (var i=0; i<array.length; i++) {
			if (i%2==0) 
				key.push(array[i]);
			else
				val.push(array[i]);
		}
		change_lineCharData(key,val);
	};
	
	var dealwith_console = function(data){
		if (data != null && typeof data == 'object')
			alert(dwr.util.toDescriptiveString(data, 2));
		else{ //dwr.util.setValue('console_output', dwr.util.toDescriptiveString(data, 1));
			printIntoConsole($('#console_output'), data);
			dealwith_Graphic(data);
		}
	};
});