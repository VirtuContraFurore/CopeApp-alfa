app.controller("IndexCtrl", IndexCtrl);

function IndexCtrl($scope, $state, $mdToast, localStorageService, $mdSidenav, $timeout, UserService){
	
	//funzione globale cambio stato
	$scope.goto = function(state){
		$state.go(state);
	}
	
	//funzione mostra toast
	$scope.showSimpleToast = function(msg, pos, timeout) {
		$mdToast.show(
		      $mdToast.simple()
		        .textContent(msg)
		        .position(pos)
		        .hideDelay(timeout)
		    );
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
	
	//gestione logout
	$scope.logout = function() {
		$timeout(function() {
			$scope.goto("home")
			$scope.setLoggedIn(false);
		}, 250);
	}
	
	//gestione login
//	$scope.loggedIn = false; decommentare per attivare la login
	$scope.loggedIn = false; //togliere per attivare la login
	$scope.getLoggedIn = function() {return $scope.loggedIn}
	$scope.setLoggedIn = function(set) {$scope.loggedIn = set}
	
	//gestione user
//	$scope.user;  decommentare per attivare la login
	UserService.login("cerammerda@gioli.it", "vinciogay").then(function(user) {$scope.user = user}); //togliere per attivare la login
	$scope.getUser = function() {return $scope.user}
	$scope.setUser = function(set) {$scope.user = set}
	
	//variabili globali
	$scope.screenSize = window.innerWidth+"x"+window.innerHeight;
	$scope.backgroundTag = "school";
	$scope.backgroundBlur = 15;
	
	//pagine di menu
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
		displayName: "Appunti",
		routerStatus: "appunti",
		pageIcon: "inbox",
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