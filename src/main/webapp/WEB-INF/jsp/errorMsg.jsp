<!DOCTYPE html>
<html lang="en">
<head>
  <title>Upload Application Tool</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="/resources/bootstrap/3.3.5/css/bootstrap.min.css">
  <link rel="stylesheet" href="/resources/font-awesome/4.6.3/css/font-awesome.min.css">
  <script src="/resources/jquery/1.12.4/jquery-1.12.4.min.js"></script>
  <script src="/resources/bootstrap/3.3.5/js/bootstrap.min.js"></script>
  
  <style type="text/css">
  	.header {
 		margin-top: 30px;
  		background-image: linear-gradient(90deg, #46B8DA, #269abc);
  		border-radius: 6px;
  		text-align: center;
  		color: white;
  		padding: 2px;
	}
	.rowAP {
		padding-top: 10px;
		padding-bottom: 10px;
		
	}
  </style>
  
</head>
<body class="container">
<div class="col-sm-12">
  <div class="header" id="header"><h1>Download file ERROR</h1></div>
  
	<div class="row rowAP">
	        <div class="col-sm-12 " >
	  			<a href="/" class="btn btn-info btn-lg">
	       					<span class="fa fa-arrow-left"></span> BACK
	       		</a>
	  		</div>
  	</div>
  	
       	<div class="row">
	        <div class="col-sm-12 " >
	        	  <label for="fileName">Result:</label>
	  			<input type="text" class="form-control" id="fileName" value="ERROR" readonly="readonly"/>
	  		</div>
  		</div>
       	<div class="row">
	        <div class="col-sm-12 " >
	        	  <label for="fileName">Message:</label>
	  			<input type="text" class="form-control" id="fileName" value="<%= request.getParameter("message") %>" readonly="readonly"/>
	  		</div>
  		</div>
</div>

</body>
</html>