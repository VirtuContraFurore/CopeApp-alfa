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
			name: '',  
			password: '',
			remember: false,
			image: ''
	}
	$scope.getUser = function() {
		return $scope.user;
	}
	$scope.setUser = function(set) {
		$scope.user=set;
	}
	if (localStorageService.get("credentials") != null) {
		$scope.setUser(localStorageService.get("credentials"));
	} else {
		$scope.setUser({
				name: 'Username', //giusto per mostrare l'immagine profilo
				password: 'password',
				remember: false,
				image: ''
		})
	}
	
	//gestione logout
	$scope.logout = function() {
		$timeout(function() {
			$scope.goto("home")
			$scope.setLoggedin(false);
		}, 250);
	}
	
	//gestione login
	$scope.loggedin = true; //set to false to show Login Page
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
			userImage: $scope.user.image,
			userWallpaper: 'default',
			shape: "round"
	}
	if($scope.user.image == '') {
		$scope.customs.userImage = $scope.user.name;
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