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
	var pattern = Trianglify({
		width: window.innerWidth+10,
		height: window.innerHeight+10,
		variance: 1
	});
	var a= pattern.png();
	$('#loginBackground').css('background-image', 'url(' + a + ')');
}