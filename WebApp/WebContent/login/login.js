app.controller("LoginCtrl", LoginCtrl);

function LoginCtrl($scope, $state){
	
	$scope.user={
			name: '',
			password: ''	
	}

	$('#loginBackground').css('background-image', 'url(http://source.unsplash.com/'+$scope.screenSize+'/?'+$scope.backgroundTag+')');
}