app.controller("IndexCtrl", IndexCtrl);

function IndexCtrl($scope,$state, localStorageService){
	
	//funzione globale cambio stato
	$scope.goto= function(state){
		$state.go(state);
	}
	
	//gestione user loggato
	$scope.user={
			name: 'Pierfilippo',  //giusto per mostrare l'immagine profilo
			password: '',
			remember: false,
	}
	$scope.getUser = function() {
		return $scope.user;
	}
	$scope.setUser = function(set) {
		$scope.user=set;
	}
	
	//gestione login
	$scope.loggedin = true;
	$scope.setLoggedin = function(set) {
		$scope.loggedin = set;
	}
	$scope.getLoggedin = function() {
		return $scope.loggedin;
	}
	//gestione first entry
	$scope.firstEntry = false;
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
	$scope.customs = {
			userImage: "https://ui-avatars.com/api/?length=1&rounded=true&name="+$scope.user.name,
			userWallpaper: 'default'
	}
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