app.controller("IndexCtrl", IndexCtrl);
function IndexCtrl($scope,$state){;
	$scope.goto= function(state){
		$state.go(state);
	}
	$scope.loggedin=false;
}