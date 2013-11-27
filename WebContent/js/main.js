window.onload = initialize_line;

function initialize_line(){
var lineChartData = {
    labels : ["January","February","March","April","May","June","July"],
    datasets : [
        {
            fillColor : "rgba(220,220,220,0.5)",
            strokeColor : "rgba(220,220,220,1)",
            pointColor : "rgba(220,220,220,1)",
            pointStrokeColor : "#fff",
            data : [65,59,90,81,56,55,40]
        },
        {
            fillColor : "rgba(151,187,205,0.5)",
            strokeColor : "rgba(151,187,205,1)",
            pointColor : "rgba(151,187,205,1)",
            pointStrokeColor : "#fff",
            data : [28,48,40,19,96,27,100]
        }
    ]

}
var myLine = new Chart(document.getElementById("canvas").getContext("2d")).Line(lineChartData);
}
$(document).ready(function(){
    $('.col-lg-5 input').iCheck({
        radioClass: 'iradio_flat-grey'
    });
});
$(function () {
    $('#para-tab a:first').tab('show');//初始化显示哪个tab
})