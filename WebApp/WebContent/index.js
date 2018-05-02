app.controller("IndexCtrl", IndexCtrl);

function IndexCtrl($scope, $state, $moment, $mdToast, localStorageService, $mdSidenav, $timeout, UserService){
	
	//funzione globale cambio stato
	$scope.goto = function(state){
		$scope.currentButton = null;
		$state.go(state);
	}
	
	//configurazione extra button
	$scope.possibleButtons = [{
		id: 0,
		name: "homeQuickLogout",
		linkedFunction: "logout()",
		buttonIcon: "exit_to_app",
		roleCondition: ""
	}, {
		id: 1,
		name: "surveyModificateButton",
		linkedFunction: "goto('createSurvey')",
		buttonIcon: "edit",
		roleCondition: "redattore"
	}, {
		id: 2,
		name: "backToSurvey",
		linkedFunction: "goto('surveys')",
		buttonIcon: "arrow_back",
		roleCondition: ""
	}]

	//set header extra button by ID
	$scope.setExtraButtonById = function(id) {
		for (var i = 0; i < $scope.possibleButtons.length; i++) {
			if (id == $scope.possibleButtons[i].id) {
				$scope.currentButton = $scope.possibleButtons[i];
				break;
			} else if (i == $scope.possibleButtons.length - 1) {
				$scope.currentButton = null;
				break;
			}
		}
	}
	$scope.setExtraButtonByName = function(name) {
		for (var i = 0; i < $scope.possibleButtons.length; i++) {
			if (name == $scope.possibleButtons[i].name) {
				$scope.currentButton = $scope.possibleButtons[i];
				break;
			} else if (i == $scope.possibleButtons.length - 1) {
				$scope.currentButton = null;
				break;
			}
		}
	}
	$scope.resetExtraButton = function() {
		$scope.currentButton = null;
	}
	
	$scope.currentButton = null;
	
	//configurazione moment
	$moment.updateLocale('en', {
	    relativeTime : {
	        future: "tra %s",
	        past:   "%s fa",
	        s  : 'pochi secondi',
	        ss : '%d secondi',
	        m:  "un minuto",
	        mm: "%d minuti",
	        h:  "un' ora",
	        hh: "%d ore",
	        d:  "un giorno",
	        dd: "%d giorni",
	        M:  "un mese",
	        MM: "%d mesi",
	        y:  "un anno",
	        yy: "%d anni"
	    }
	});
	
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
			$scope.goto("home");
			$scope.setLoggedIn(false);
		}, 250);
	}
	
	//gestione login
//	$scope.loggedIn = false; //mettere per attivare la login
	$scope.loggedIn = true; //togliere per attivare la login
	$scope.getLoggedIn = function() {return $scope.loggedIn}
	$scope.setLoggedIn = function(set) {$scope.loggedIn = set}
	
	//gestione user
//	$scope.user;  //mettere per attivare la login
	UserService.login("Cerammerda", "VincioGay").then(function(response) {
		$scope.user = response.data.user;
	}); //togliere per attivare la login
	$scope.getUser = function() {return $scope.user}
	$scope.setUser = function(set) {$scope.user = set}
	
	//variabili globali
	$scope.screenSize = window.innerWidth+"x"+window.innerHeight;
	$scope.backgroundTag = "girl";
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
		displayName: "Annuario",
		routerStatus: "annuario",
		pageIcon: "recent_actors",
		itemType: "page"
	}, {
		displayName: "Sondaggi",
		routerStatus: "surveys",
		pageIcon: "data_usage",
		itemType: "page"
	}, {
		displayName: "Negozio",
		routerStatus: "market",
		pageIcon: "shopping_cart",
		itemType: "page"
	}, {
		displayName: "divbar01",
		routerStatus: "-",
		pageIcon: "-",
		itemType: "divbar"
	}, {
		displayName: "Impostazioni",
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