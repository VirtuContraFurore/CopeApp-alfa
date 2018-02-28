app.controller("LoginCtrl", LoginCtrl);

function LoginCtrl($scope, localStorageService){

	if (localStorageService.get("credentials") != null) {
		$scope.setUser(localStorageService.get("credentials"));
	} else {
		$scope.setUser({
				name: '',
				password: '',
				remember: false
		})
	}
	
	$scope.login = function() {
		//chiamata di controllo al server
		if ($scope.user.remember) {
			localStorageService.set("credentials", $scope.getUser());
		} else {
			localStorageService.set("credentials", null);
		}
		$scope.setLoggedin(true);
		$scope.setFirstEntry(true);
	}
	
	$('#loginBackground').css('background-image', 'url(http://source.unsplash.com/'+$scope.screenSize+'/?'+$scope.backgroundTag+')');
	$('#loginBackground').css({'-webkit-filter': 'blur('+$scope.backgroundBlur+'px)', '-moz-filter': 'blur('+$scope.backgroundBlur+'px)', '-o-filter': 'blur('+$scope.backgroundBlur+'px)', '-ms-filter': 'blur('+$scope.backgroundBlur+'px)', 'filter': 'blur('+$scope.backgroundBlur+'px)'})
}