var app = angular.module("searchApplication", []);
app.controller("control", function($scope,$http,$window)
		{
	         $scope.search=function()
	      {
		     var data=
		        { 
				    firstName:$scope.firstName,
				    lastName:$scope.lastName,
				    patientId:$scope.patientId,
				    dateOfBirth:$scope.dateOfBirth
		        };
		     $scope.patientList = new Array;
		     $http({
			    method : "GET",
			    headers: {
				'Access-Control-Allow-Origin': '*',
				'Content-Type': 'application/json',
			     },
			    url : "webapi/search",
			    params:{firstName: data.firstName, lastName: data.lastName, patientId:data.patientId, dateOfBirth:data.dateOfBirth}
		     })
		     .then(function(response)
				  {
			           $scope.patientList = response.data;
			           if($scope.patientList.length != 0)
			           {
				           $window.alert("Success");
                       }
			           else
			           {
				           $window.alert("No data Found!");
			           }
				  })
			  .catch(function(response,status)
				   {
					   $scope.message=response;
				   });
	       } 
    });
