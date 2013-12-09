window.onload = initialize_line;

function initialize_line(){
lineChartData = {
    labels : ["Welcome","To","Our","Log","Visualization","Demo",""],
    datasets : [
        {
            fillColor : "rgba(151,187,205,0.5)",
            strokeColor : "rgba(151,187,205,1)",
            pointColor : "rgba(151,187,205,1)",
            pointStrokeColor : "#fff",
            data : [28,48,40,19,96,27,100]
        }
    ]
};
var myLine = new Chart(document.getElementById("canvas").getContext("2d")).Line(lineChartData);
}

function change_lineCharData(labels,datas){
	document.getElementById("canvas").height = 320;
	document.getElementById("canvas").width = 620;
    lineChartData.labels = labels;
    lineChartData.datasets[0].data = datas;
    new Chart(document.getElementById("canvas").getContext("2d")).Line(lineChartData);
    /*var datas_length = datas.length;
    if(origin_length >= datas_length)
    {
        for(var i =0; i < origin_length; i++)
        {
            lineChartData.datasets[i] = datas[i];
        }
    }
    else
    {
        for(var i =0; i < origin_length; i++)
        {
            lineChartData.datasets[i] = datas[i];
        }
        for(var i = origin_length; i<datas_length; i++)
        {
            var rgb1 = Math.random()*256;
            var rgb2 = Math.random()*256;
            var rgb3 = Math.random()*256;

            var fillcolor = "rgba("+rgb1 + ", "+rgb2 +", "+rgb3+", 0.5)";
            var strokecolor = "rgba("+rgb1 + ", "+rgb2 +", "+rgb3+", 1)";
            lineChartData.datasets.push(
                {
                    fillColor: fillcolor,
                    strokeColor: strokecolor,
                    pointColor: strokecolor,
                    pointStrokeColor : "#fff",
                    data : datas[i]
                }
            )
        }
    }*/
}
$(document).ready(function(){
    $('.col-lg-5 input').iCheck({
        radioClass: 'iradio_flat-grey'
    });
});
$(function () {
    $('#para-tab a:first').tab('show');//初始化显示哪个tab
})

function login()
{
    var username = Document.getElementById("username").value;
    var password = Document.getElementById("password").value;
    //login()
}