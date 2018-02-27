app.controller("MainCtrl", MainCtrl);

function MainCtrl($scope, $mdSidenav){
	
	$scope.sidenavToggle = function() {
		$mdSidenav("leftSidenav").toggle();
	}
	
}