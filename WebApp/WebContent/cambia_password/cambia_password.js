app.config(function($stateProvider){
	$stateProvider.state("cambia_password", {
		url:"/cambia_password",
		templateUrl:"cambia_password/cambia_password.html"
	})
});
app.controller("PasswordCtrl", PasswordCtrl);
function PasswordCtrl($scope){
	
}