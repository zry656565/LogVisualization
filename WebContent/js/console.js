/* console.js 
 * Auther: Zou
 * Create Date: 2013/11/29
 */

function printIntoConsole(console, string){
	var date = new Date();
	var dateStr = date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
	console.append("[Console-" +dateStr+ "]$ " + string + "<br/>");
	//scroll to the bottom of console
	console.scrollTop(50000);
}

//if number < 10, add a zero in front of it.
function Standardize(number){
	if (number<10 && number>=0) {
		return '0' + number;
	}
	else if (number>10) {
		return number.toString();
	}
	else {
		return '00';
	}
}