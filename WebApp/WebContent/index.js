app.controller("IndexCtrl", IndexCtrl);

function IndexCtrl($scope, $state, localStorageService, $mdSidenav, $timeout){
	
	//funzione globale cambio stato
	$scope.goto= function(state){
		$state.go(state);
	}
	
	//call string function
	$scope.callFunction = function(func) {
		eval("$scope."+func);
	}
	
	//sidenav toggle
	$scope.sidenavToggle = function() {
		$timeout(function() {
			$mdSidenav("leftSidenav").toggle();
		}, 250);
	}
	
	//gestione user loggato
	$scope.user={
			name: 'Lucio',  //giusto per mostrare l'immagine profilo
			password: '',
			remember: false,
	}
	$scope.getUser = function() {
		return $scope.user;
	}
	$scope.setUser = function(set) {
		$scope.user=set;
	}
	
	//gestione logout
	$scope.logout = function() {
		$timeout(function() {
			$scope.goto("home")
			$scope.setLoggedin(false);
		}, 250);
	}
	
	//gestione login
	$scope.loggedin = false; //set to false to show Login Page
	$scope.setLoggedin = function(set) {
		$scope.loggedin = set;
	}
	$scope.getLoggedin = function() {
		return $scope.loggedin;
	}
	
	//gestione first entry
	$scope.firstEntry = false;  //set to true to show firstLogin Page
	$scope.setFirstEntry = function(set) {
		$scope.firstEntry = set;
	}
	$scope.getFirstEntry = function() {
		return $scope.firstEntry;
	}
	
	//variabili globali
	$scope.screenSize = window.innerWidth+"x"+window.innerHeight;
	$scope.backgroundTag = "architecture";
	$scope.backgroundBlur = 10;
	$scope.customs = {
			userImage: "https://ui-avatars.com/api/?length=1&rounded=true&name=",
			userWallpaper: 'default'
	}
	$scope.items = [{
		displayName: "Home",
		routerStatus: "home",
		pageIcon: "home",
		itemType: "page"
	}, {
		displayName: "News",
		routerStatus: "news",
		pageIcon: "whatshot",
		itemType: "page"
	}, {
		displayName: "divbar01",
		routerStatus: "-",
		pageIcon: "-",
		itemType: "divbar"
	}, {
		displayName: "Settings",
		routerStatus: "settings",
		pageIcon: "settings",
		itemType: "page"
	}, {
		displayName: "divbar02",
		routerStatus: "-",
		pageIcon: "-",
		itemType: "divbar"
	}, {
		displayName: "Log out",
		routerStatus: "logout()",
		pageIcon: "exit_to_app",
		itemType: "function"
	}];
}