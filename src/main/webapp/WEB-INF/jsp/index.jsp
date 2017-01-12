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
  		color: #fff;
  		padding: 2px;
	}
	.rowAP {
		padding-top: 10px;
		padding-bottom: 10px;
		
	}
  </style>  
</head>
<body>

<div class="container">
  <div class="header" id="header"><h1>Upload file application</h1></div>
  
  <form role="form" method="POST" enctype="multipart/form-data" action="/upload">
  <div class="row">
  	<div class="col-sm-12 " >
      <label for="file">File:</label>
      <input type="file" class="form-control" id="file" name="file" required="required">
    </div>
  </div>
  <div class="row">
  	<div class="col-sm-12 " >
      <label for="email">Email:</label>
      <input type="text" class="form-control" id="email" name="email" required="required">
    </div>
  </div> 
  <div class="row">
	  <div class="col-sm-12 ">
	      <label for="fileName">File will be available for:</label>
	      <select class="form-control" name="validity">
			  <option value="1m">1 min</option>
			  <option value="1h">1 h</option>
			  <option value="1d">1 day</option>
			  <option value="7d">7 days</option>
			  <option value="30d"">30 days</option>
			  <option value="0">Unlimited Time</option>
			</select>
	    </div>	
  </div>
     
  <hr/>
  <div class="row">
    <div class="col-sm-12">
   		<input type="submit"  class="btn btn-info btn-lg" value="Press here to upload the file!"/>
   	</div>
  </div>
  </form>
</div>

</body>
</html>