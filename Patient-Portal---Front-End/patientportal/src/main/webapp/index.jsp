<!DOCTYPE html>
<html>
<head>
<meta charset = "ISO-8859-1">
<title>Patient Portal</title>
<link rel = "stylesheet" href = "css/index.css" type = "text/css">
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.7/angular.js"></script>
	<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script
	src = "https://cdnjs.cloudflare.com/ajax/libs/angular-translate/2.17.0/angular-translate.js"></script>
<script src="js/searchcontroller.js"></script>
<!-- <script type="text/javascript">
 function login(){
	 var user = $("#username").value
	 var pass = $("#password").value
	 if(user==="Admin" && pass==="Cerner"){
		 console.log("valid");
	 }
 }
</script>-->
<body data-ng-app="searchApplication" data-ng-controller="searchController">
	<div class = "titlebar">
		<img height = "50px" class = "titleImage" src = "image/title.JPG">
	</div>
	<div class = "menu">
		<a href = "register.html"><input class = "menubutton" id = "registerLink" type = "button" value = "{{'register_button'|translate}}"/></a>
		<a href ="search.html"><input class = "menubutton" id = "searchLink" type = "button" value = "{{'search_button'|translate}}"/></a>
	</div>
	<div>
		<img src = "image/background.jpg">
	</div>
	<!-- <div class = "loginblock" id="loginblk">
		<div class="logininput">	
			<form>
				<input id="username" type = "text" style = "width:500px; height:50px" placeholder = "Username" pattern = "[a-zA-Z]+"><br/>
				<input id="password" type = "password" style = "width:500px; height:50px" placeholder = "Password"><br/>
				<input type = "button" style = "width:500px; height:50px" value = "Login" onclick="login()"> 
			</form>
		</div>
	</div>-->
</body>
</html>