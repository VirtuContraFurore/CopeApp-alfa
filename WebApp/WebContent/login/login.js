app.config(function($stateProvider){
	$stateProvider.state("login", {
		url:"/",
		templateUrl:"login/login.html"
	})
});
app.controller("LoginCtrl", LoginCtrl);
function LoginCtrl($scope, $state){
	$scope.user={
			name: '',
			password: ''	
	}
	$scope.goto= function(state){
		$state.go(state);
	}
}