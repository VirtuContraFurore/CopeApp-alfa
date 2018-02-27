app.controller("LoginCtrl", LoginCtrl);

function LoginCtrl($scope, $state){
	
	$scope.user={
			name: '',
			password: ''	
	}

	$('#loginBackground').css('background-image', 'url(http://source.unsplash.com/'+$scope.screenSize+'/?'+$scope.backgroundTag+')');
	$('#loginBackground').css({'-webkit-filter': 'blur('+$scope.backgroundBlur+'px)', '-moz-filter': 'blur('+$scope.backgroundBlur+'px)', '-o-filter': 'blur('+$scope.backgroundBlur+'px)', '-ms-filter': 'blur('+$scope.backgroundBlur+'px)', 'filter': 'blur('+$scope.backgroundBlur+'px)'})
}