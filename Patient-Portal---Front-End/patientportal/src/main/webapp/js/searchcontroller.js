var app = angular.module("searchApplication", ['pascalprecht.translate']);
app.controller("searchController", function($scope,$http,$window,$translate){
	
	var lang = $window.navigator.language;
	if(lang === "en-US"){
		$translate.use('en');
	}
	else if(lang === "fr"){
		$translate.use('fr');
	}
	else if(lang === "es"){
		$translate.use('es')
	}
	
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
			url : "webapi/patient/search",
			params:{firstName: data.firstName, lastName: data.lastName, patientId:data.patientId, dateOfBirth:data.dateOfBirth}
		})
		.then(function(response){
			$scope.patientList = response.data;
			if($scope.patientList.length == 0)
			{
				$scope.firstName='',
				$scope.lastName='',
				$scope.patientId='',
				$scope.dateOfBirth=''
				$window.alert("No data Found!");
				$scope.patientList=null;
			}
		})
		.catch(function(response,status){
			$window.alert(response.data);
		});
	} 
	$scope.resetFields=function(){
			$scope.firstName = ""
			$scope.lastName = ""
			$scope.patientId = 0
			$scope.dateOfBirth = ""
	}
});
app.config(['$translateProvider', function($translateProvider){
	$translateProvider.translations('en',{
		'title':'Search Patient',
		'patient_id':'Patient ID',
		'first_name':'First Name',
		'last_name':'Last Name',
		'date_of_birth':'Date Of Birth',
		'gender':'Gender',
		'phone_no':'Phone',
		'email_id':'Email-ID',
		'placeholder_patientId':'Enter Patient Id',
		'placeholder_firstName':'Enter First Name',
		'placeholder_lastName':'Enter Last Name',
		'address_line':'Line',
		'address_street':'Street',
		'address_city':'City',
		'address_state':'State',
		'address_country':'Country',
		'register_button':'Register',
		'search_button':'Search',
		'reset_button':'Reset',
		'cancel_button':'Cancel'
	});
	$translateProvider.translations('fr',{
		'title':'S\'inscrire au patient',
		'first_name':'Prénom',
		'last_name':'Nom de famille',
		'date_of_birth':'Date de naissance',
		'gender':'Sexe',
		'phone_no':'Téléphone',
		'email_id':'Email-ID',
		'placeholder_patientId':'entrer lidentifiant du patient',
		'placeholder_firstName':'entrez votre prénom',
		'placeholder_lastName':'Entrer le nom de famille',
		'address_line':'Ligne d\'',
		'address_street':'Rue',
		'address_city':'Ville',
		'address_state':'Etat',
		'address_country':'Pays',
		'search_button':'chercher',
		'register_button':'s\'inscrire',
		'clear_button':'nettoyer',
		'cancel_button':'Annuler',
		'male':'Mâle',
		'female':'Femelle',
		'transgender':'Transgenres'
	});
	$translateProvider.translations('es',{
		'title':'Registra un paciente',
		'first_name':'Nombre',
		'last_name':'Apellido',
		'date_of_birth':'Fecha de nacimiento',
		'gender':'Género',
		'phone_no':'Teléfono',
		'email_id':'Identificación de correo',
		'placeholder_patientId':'Ingrese Id paciente',
		'placeholder_firstName':'Ingresa el nombre',
		'placeholder_lastName':'Introduzca el apellido',
		'address_line':'Línea de',
		'address_street':'Calle',
		'address_city':'Ciudad',
		'address_state':'Estado',
		'address_country':'País',
		'search_button':'buscar',
		'register_button':'Registro',
		'clear_button':'limpiar',
		'cancel_button':'Cancelar',
		'male':'Masculino',
		'female':'Hembra',
		'transgender':'Transgénero'
	});
	$translateProvider.preferredLanguage('en');
}]);
