app.controller("LoginCtrl", LoginCtrl);

function LoginCtrl($scope, localStorageService, UserService){
	
	$scope.login = function() {
		var p = UserService.login($scope.getUser().mail, $scope.getUser().password);
		p.then(function onSuccess(user) {
			$scope.setUser(user);
			if ($scope.user.remember) {
				localStorageService.set("credentials", $scope.getUser());
			} else {
				localStorageService.set("credentials", null);
			}
			$scope.setLoggedin(true);
		}, 
		function onFailure(error) {
			alert(error);
		});
		//chiamata di controllo al server
		
	}
	
	$('#loginBackground').css('background-image', 'url(http://source.unsplash.com/'+$scope.screenSize+'/?'+$scope.backgroundTag+')');
	$('#loginBackground').css({'-webkit-filter': 'blur('+$scope.backgroundBlur+'px)', '-moz-filter': 'blur('+$scope.backgroundBlur+'px)', '-o-filter': 'blur('+$scope.backgroundBlur+'px)', '-ms-filter': 'blur('+$scope.backgroundBlur+'px)', 'filter': 'blur('+$scope.backgroundBlur+'px)'})
}