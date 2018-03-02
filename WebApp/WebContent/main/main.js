app.controller("MainCtrl", MainCtrl);

function MainCtrl($scope, $mdSidenav, $timeout){
	
	$scope.sidenavToggle = function() {
		$timeout(function() {
			$mdSidenav("leftSidenav").toggle();
		}, 250);
	}
	
}