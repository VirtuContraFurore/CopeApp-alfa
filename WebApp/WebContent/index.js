app.controller("IndexCtrl", IndexCtrl);

function IndexCtrl($scope,$state, localStorageService){
	
	
	$scope.goto= function(state){
		$state.go(state);
	}
	$scope.user={
			name: '',
			password: '',
			remember: false
	}
	$scope.getUser = function() {
		return $scope.user;
	}
	$scope.setUser = function(set) {
		$scope.user=set;
	}
	
	$scope.loggedin = false;
	
	$scope.setLoggedin = function(set) {
		$scope.loggedin = set;
	}
	
	$scope.screenSize = "1920x1080";
	$scope.backgroundTag = "white";
	$scope.backgroundBlur = 10;
	
}