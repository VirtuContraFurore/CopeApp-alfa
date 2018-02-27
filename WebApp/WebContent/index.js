app.controller("IndexCtrl", IndexCtrl);

function IndexCtrl($scope,$state){
	
	$scope.goto= function(state){
		$state.go(state);
	}
	
	$scope.loggedin = false;
	
	$scope.screenSize = "1920x1080";
	$scope.backgroundTag = "landscape";
	$scope.backgroundBlur = 15;
}