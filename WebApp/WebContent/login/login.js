app.controller("LoginCtrl", LoginCtrl);

function LoginCtrl($scope, localStorageService){
	
	$scope.login = function() {
		//chiamata di controllo al server
		if ($scope.user.remember) {
			localStorageService.set("credentials", $scope.getUser());
		} else {
			localStorageService.set("credentials", null);
		}
		$scope.setLoggedin(true);
	}
	
	$('#loginBackground').css('background-image', 'url(http://source.unsplash.com/'+$scope.screenSize+'/?'+$scope.backgroundTag+')');
	$('#loginBackground').css({'-webkit-filter': 'blur('+$scope.backgroundBlur+'px)', '-moz-filter': 'blur('+$scope.backgroundBlur+'px)', '-o-filter': 'blur('+$scope.backgroundBlur+'px)', '-ms-filter': 'blur('+$scope.backgroundBlur+'px)', 'filter': 'blur('+$scope.backgroundBlur+'px)'})
}