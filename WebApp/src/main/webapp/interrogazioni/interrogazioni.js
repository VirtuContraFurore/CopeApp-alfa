app.config(function($stateProvider){
	$stateProvider.state("interrogazioni", {
		url:"/interrogazioni",
		templateUrl:"interrogazioni/interrogazioni.html"
	})
});
app.controller("InterrogazioniCtrl", InterrogazioniCtrl);
function InterrogazioniCtrl($scope){
	
	
	
}