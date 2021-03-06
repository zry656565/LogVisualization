<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title>Log Visualization</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width">

        <link rel="stylesheet" href="css/bootstrap.min.css">
        <style>
            body {
                padding-top: 50px;
                padding-bottom: 20px;
            }
        </style>
        <link rel="stylesheet" href="css/bootstrap-theme.min.css">
        <link rel="stylesheet" href="css/main.css">
        <link href="css/bootstrap2_3_1.css" rel="stylesheet">
        <script type="text/javascript" src="js/vendor/bootstrap-modal.js"></script>
         <script type="text/javascript" src="js/vendor/bootstrap-transition.js"></script>
        <script src="js/vendor/modernizr-2.6.2-respond-1.1.0.min.js"></script>
        <script src="js/vendor/jquery-1.10.1.min.js"></script>
        <script src="js/vendor/bootstrap.min.js"></script>
        <script src="js/graphic/Chart.js"></script>
        <script src="js/main.js"></script>
    </head>
    <body>
    <div class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Log Visualization</a>
        </div>
        <div class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="#">Home</a></li>
            <li><a href="#about">About</a></li>
            <li><a href="#contact">Contact</a></li>
          </ul>
        </div>
      </div>
    </div>



    <div class="container">
      <h1>Log Visualization</h1>
      <p>The topic of the project is to visualize the data and predict the future of the data user provided.</p>
    </div>


    <div class="container">
        <!-- Example row of columns -->
      <div class="row">
        <div class="col-lg-7">
          <h2>Visualization</h2>
          <canvas id="canvas" width="620px" height="400px"></canvas>
        </div>
        <div class="col-lg-5">
          <h2>Import Data</h2>
          <table class="table">
            <tr>
              <td>NAME</td>
              <td>Source URL</td>
              <td>STATE</td>
            </tr>
            <tr>
              <td>World Cup 98</td>
              <td>http://www.abc.com</td>
              <td>100%</td>
            </tr>
            <tr>
              <td>NASA</td>
              <td>http://www.123.com</td>
              <td>fail</td>
            </tr>
            <tr>
              <td>balabala</td>
              <td>http://www.123.com</td>
              <td>fail</td>
            </tr>
          </table>
          <a class="btn btn-default" href="#">View details &raquo;</a>  &nbsp;
          <a class="btn btn-default" href="#">Import New Source</a>
        </div>
      </div>

      <div class="row">
          <div class="col-lg-7">
              <h2>Console</h2>
              <input type="text" class="col-sm-11" placeholder="Please input your instruction."/>
              <br/><br/>
              <div class="col-sm-11" style="border:1px solid grey;">
              <p>
                  $&nbsp; [Happy shell]<br/>
                  $&nbsp; Hello, visitor.<br/>
                  $&nbsp; Welcome to our log visualization beta.<br/>
                  $&nbsp; You can type some code to see the picture you want.<br/>
                  $&nbsp; Any question, try "-help"<br/>
                  $&nbsp; Just enjoy it.<br/>
                  $&nbsp; _<br/><br/><br/>
              </p>
              </div>
          </div>
          <div class="col-lg-5">
              <h2>Prediction</h2>
              <ul>
                  <li>Linear</li>
                  <li>Quadratic Curve</li>
                  <li>Custom</li>
              </ul>
          </div>
      </div>

      <hr>

      <footer>
        <p>&copy; SJTU Company 2013</p>
        <p>If you have any question, please send us <a href="mailto:SunWeicheng0001@gmail.com">SunWeicheng0001@gmail.com</a></p>
      </footer>
    </div>
    </body>
</html>
