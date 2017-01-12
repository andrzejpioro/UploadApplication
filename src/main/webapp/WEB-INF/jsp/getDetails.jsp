<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
  <div class="header" id="header"><h1>Upload file application</h1></div>
  
	<div class="row rowAP">
	        <div class="col-sm-12 " >
	  			<a href="/" class="btn btn-info btn-lg">
	       					<span class="fa fa-arrow-left"></span> BACK
	       		</a>
	  		</div>
  	</div>
  		<div class="row">
	        <div class="col-sm-12 " >
	        	  <label for="fileName">File name:</label>
	  			<input type="text" class="form-control" id="fileName" value="${FILE_NAME}" readonly="readonly"/>
	  		</div>
  		</div>
       	<div class="row">
	        <div class="col-sm-12 " >
	        	  <label for="fileStatus">Status:</label>
	  			<input type="text" class="form-control" id="fileStatus" value="${FILE_STATUS}" readonly="readonly"/>
	  		</div>
  		</div>

  		<div class="row">
  			<div class="col-sm-12">
	          <label for="fileSize">File size 	[B]:</label>
	  		  <input type="text" class="form-control" id="fileSize" value="${FILE_SIZE}" readonly="readonly"/>
  			</div>
        </div>
        <div class="row">
  			<div class="col-sm-12">
	          <label for="fileUploaded">File uploaded:</label>
	  		  <input type="text" class="form-control" id="fileUploaded" value="${FILE_UPLOAD_STOP}" readonly="readonly"/>
  			</div>
        </div>
        <div class="row">
  			<div class="col-sm-12">
	          <label for="fileExp">File expiration:</label>
	  		  <input type="text" class="form-control" id="fileExp" value="${FILE_EXPR}" readonly="readonly"/>
  			</div>
        </div>
        <div class="row">
	        <div class="col-sm-12">
	        	  <label for="directLink">Download link:</label>
	  			<input type="text" class="form-control" id="directLink" value="${FILE_URL}" readonly="readonly"/>
	  		</div>
	  	</div>
	  	 <div class="row">
	        <div class="col-sm-12">
	        	<label for="directLink">Downloads:</label>
	        	<div  class="table-responsive">
	        	  <table class="table table-striped">
	        	  	<thead>
				      <tr>
				        <th>Source IP</th>
				        <th>SessionID</th>
				        <th>Download Start</th>
				        <th>Download Stop</th>
				      </tr>
				    </thead>
				    <tbody>
						<c:forEach var="downloadedFile" items="${FILE_DOWNLOADS}">
							<tr>
								<td>${downloadedFile.sourceIp}</td>
								<td>${downloadedFile.sessionId}</td>
								<td>${downloadedFile.downloadStart}</td>
								<td>${downloadedFile.downloadStop}</td>
							</tr>
						</c:forEach>    
				    </tbody>
	        	  </table>
	        	</div>
	  		</div>
	  	</div>
	  	
	  		
	  	
	  	<hr/>
	  	<c:if test = "${FILE_STATUS == 'ACTIVE'}">
	  		<div class="row">
	  		<div class="col-sm-12">
				        <a href="${FILE_URL}" class="btn btn-info btn-lg">
	       					<span class="fa fa-download"></span> Press here to download your file!
	       				</a>
	  		</div>
	  	</div>
	  	</c:if>
	  	
  
</div>

</body>
</html>