app.config(function($stateProvider){
	$stateProvider.state("home", {
		url:"/",
		templateUrl:"home/home.html"
	})
});
app.controller("HomeCtrl", HomeCtrl);
function HomeCtrl($scope){

}