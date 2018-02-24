app.controller("MainHeaderCtrl", MainHeaderCtrl);
function MainHeaderCtrl($scope,$state){
	$scope.title="Cope App";
	$scope.goto= function(state){
		$state.go(state);
	}
}