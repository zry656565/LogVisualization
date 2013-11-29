$(document).ready(function(){
	$('#console_input').keyup(function(){
		if(event.keyCode == 13){
			Transmission.sendCommand($('#console_input').val(), dealwith_console);
		}
	});
	
	var dealwith_console = function(data){
		if (data != null && typeof data == 'object')
			alert(dwr.util.toDescriptiveString(data, 2));
		else //dwr.util.setValue('console_output', dwr.util.toDescriptiveString(data, 1));
			printIntoConsole($('#console_output'), data);
	};
});