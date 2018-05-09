app.controller("IndexCtrl", IndexCtrl);

function IndexCtrl($scope, $state, $moment, $mdToast, localStorageService, $mdSidenav, $timeout, UserService){
	
	//funzione globale cambio stato
	$scope.goto = function(state){
		$state.go(state);
	}
	
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
	
	$scope.serverErrorCallback = function(reason) {
		console.error(reason.data.debuggingDescription);
		console.error(reason.data.stackTrace);
	}
	$scope.serverErrorCallbackToast = function(reason) {
		$scope.showSimpleToast(reason.data.descrptionForUser, "bottom right", 2500);
		console.error(reason.data.debuggingDescription);
		console.error(reason.data.stackTrace);
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
	$scope.showActionToast = function(msg, pos, timeout, action, callback) {
	    var toast = $mdToast.simple()
	      .textContent(msg)
	      .action('OK')
	      .highlightAction(true)
	      .highlightClass('md-primary')// Accent is used by default, this just demonstrates the usage.
	      .position(pos)
	      .hideDelay(timeout);
	    $mdToast.show(toast).then(callback);
	  };
	
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
	$scope.user = {"userId":1,"mail":"cerammerda@gioli.it","firstname":"Luca","lastname":"Ceragioli","username":"Cerammerda","classe":"5","sezione":"F","password":"VincioGay","roles":[{"roleId":1,"role":"studente","description":"Studente"},{"roleId":2,"role":"prof","description":"Professore"},{"roleId":3,"role":"moderatore","description":"Moderatore"},{"roleId":4,"role":"admin","description":"Amministratore"},{"roleId":5,"role":"redattore","description":"Redattore"},{"roleId":6,"role":"rappresentante","description":"Rappresentante"}],"imageUrl":"cerammerda@gioli.it","wallpaper":"default","firstEntry":false};
	$scope.getUser = function() {return $scope.user}
	$scope.setUser = function(set) {$scope.user = set}
	
	//variabili globali
	$scope.screenSize = window.innerWidth+"x"+window.innerHeight;
	$scope.backgroundTag = "girl";
	$scope.backgroundBlur = 15;
	
	$scope.checkRoles = function(buttonRole) {
		if ($scope.user != null) {
			for (var i = 0; i < $scope.getUser().roles.length; i++) {
				if ($scope.getUser().roles[i].role == buttonRole) {
					return true;
				}
			}
		}
		return false
	}

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
		pageIcon: "description",
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
		pageIcon: "show_chart",
		itemType: "page"
	}, {
		displayName: "Negozio",
		routerStatus: "market",
		pageIcon: "shopping_cart",
		itemType: "page"
	}, {
		displayName: "Orario",
		routerStatus: "orario",
		pageIcon: "date_range",
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
		displayName: "Feedback",
		routerStatus: "feedback",
		pageIcon: "feedback",
		itemType: "page"
	}, {
		displayName: "Log out",
		routerStatus: "logout()",
		pageIcon: "exit_to_app",
		itemType: "function"
	}];
}