app.controller("IndexCtrl", IndexCtrl);

function IndexCtrl($scope,$state, localStorageService){
	
	//funzione globale cambio stato
	$scope.goto= function(state){
		$state.go(state);
	}
	
	//gestione user loggato
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
	
	//gestione login
	$scope.loggedin = false;
	$scope.setLoggedin = function(set) {
		$scope.loggedin = set;
	}
	$scope.getLoggedin = function() {
		return $scope.loggedin;
	}
	//gestione first entry
	$scope.firstEntry = true;
	$scope.setFirstEntry = function(set) {
		$scope.firstEntry = set;
	}
	$scope.getFirstEntry = function() {
		return $scope.firstEntry;
	}
	
	
	//variabili globali
	$scope.screenSize = "1080x1920";
	$scope.backgroundTag = "landscape";
	$scope.backgroundBlur = 15;
	$scope.pages = [{
		displayName: "Home",
		routerStatus: "home",
		pageIcon: "home"
	}, {
		displayName: "News",
		routerStatus: "news",
		pageIcon: "whatshot"
	}, {
		displayName: "Settings",
		routerStatus: "settings",
		pageIcon: "settings"
	}];
}