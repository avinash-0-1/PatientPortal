var app = angular.module("patientPortal", []);

app.controller("patientController", function($scope, $http, $window, $filter) {
	$scope.firstName,
	$scope.lastName,
	$scope.dateOfBirth = new Date("mm/dd/yy"),
	$scope.gender,
	$scope.phoneNo,
	$scope.emailId,
	$scope.addressLine="",
	$scope.street="",
	$scope.city="",
	$scope.state="",
	$scope.country="",
	$scope.resData
	
	$scope.addPatient = function() {
		console.log('inside add fuction')
		console.log($scope.dateOfBirth);
		$scope.postData = {
			'firstName' : $scope.firstName,
			'lastName' : $scope.lastName,
			'dateOfBirth' : $scope.dateOfBirth,
			'gender' : $scope.gender,
			'phoneNo' : $scope.phoneNo,
			'emailId' : $scope.emailId,
			'addressLine' :$scope.addressLine,
			'street' :$scope.street,
			'city' : $scope.city,
			'state' :$scope.state,
			'country' : $scope.country
		};
		
		console.log($scope.postData);
		$http({
			   method : "POST",
			   url : "webapi/registerpatient",
			   data : angular.toJson($scope.postData)
			}).then(function(response){
				console.log("inside success function");
				if(response.data=="Successfully registerd the patient Details!"){
					$scope.firstName = '',
					$scope.lastName = '',
					$scope.dateOfBirth = '',
					$scope.gender = '',
					$scope.phoneNo = '',
					$scope.emailId = '',
					$scope.addressLine = '',
					$scope.street = '',
					$scope.city = '',
					$scope.state = '',
					$scope.country = ''
				}
				$window.alert(response.data);
				
			},function(response){
				console.log(response.status);
		});
	}
});