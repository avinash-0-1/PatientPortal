var app = angular.module("registerApplication", ['pascalprecht.translate']);

app.controller("registerController", function($scope, $http, $window, $translate) {
	
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
	
	$scope.addPatient = function() {
		console.log('Adding patient')
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
		
		console.log('Sending data to server: '+$scope.postData);
		$http({
			   method : "POST",
			   url : "webapi/patient/register",
			   data : angular.toJson($scope.postData)
			}).then(function(response){
				console.log("hit the server successfully");
				if(response.data){
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
				console.log('Error occured in making request with status: '+response.status);
		});
	}
});
app.config(['$translateProvider', function($translateProvider){
	$translateProvider.translations('en',{
		'title':'Register a Patient',
		'mandatory':'Fields are mandatory',
		'first_name':'First Name',
		'last_name':'Last Name',
		'date_of_birth':'Date Of Birth',
		'gender':'Gender',
		'phone_no':'Phone',
		'email_id':'Email-ID',
		'placeholder_phone':'e.g.-9122345678',
		'placeholder_email':'e.g.-abc@xyz.com',
		'address':'Address',
		'address_line':'Line',
		'address_street':'Street',
		'address_city':'City',
		'address_state':'State',
		'address_country':'Country',
		'register_button':'Register',
		'clear_button':'Clear',
		'cancel_button':'Cancel',
		'name_title':'Enter characters only',
		'male':'Male',
		'female':'Female',
		'transgender':'Transgender'
	});
	$translateProvider.translations('fr',{
		'title':'S\'inscrire au patient',
		'mandatory':'Les champs sont obligatoires',
		'first_name':'Prénom',
		'last_name':'Nom de famille',
		'date_of_birth':'Date de naissance',
		'gender':'Sexe',
		'phone_no':'Téléphone',
		'email_id':'Email-ID',
		'placeholder_phone':'e.g.-9122345678',
		'placeholder_email':'e.g.-abc@xyz.com',
		'address':'adresse',
		'address_line':'Ligne d\'',
		'address_street':'Rue',
		'address_city':'Ville',
		'address_state':'Etat',
		'address_country':'Pays',
		'register_button':'s\'inscrire',
		'clear_button':'nettoyer',
		'cancel_button':'Annuler',
		'name_title':'Entrer seulement les caractères',
		'male':'Mâle',
		'female':'Femelle',
		'transgender':'Transgenres'
	});
	$translateProvider.translations('es',{
		'title':'Registra un paciente',
		'mandatory':'Los campos son obligatorios',
		'first_name':'Nombre',
		'last_name':'Apellido',
		'date_of_birth':'Fecha de nacimiento',
		'gender':'Género',
		'phone_no':'Teléfono',
		'email_id':'Identificación de correo',
		'placeholder_phone':'e.g.-9122345678',
		'placeholder_email':'e.g.-abc@xyz.com',
		'address':'dirección',
		'address_line':'Línea de',
		'address_street':'Calle',
		'address_city':'Ciudad',
		'address_state':'Estado',
		'address_country':'País',
		'register_button':'Registro',
		'clear_button':'limpiar',
		'cancel_button':'Cancelar',
		'name_title':'Ingresa solo caracteres',
		'male':'Masculino',
		'female':'Hembra',
		'transgender':'Transgénero'
	});
	$translateProvider.preferredLanguage('en');
}]);