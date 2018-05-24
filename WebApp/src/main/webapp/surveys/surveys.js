app.config(function($stateProvider) {
	$stateProvider.state("surveys", {
		url : "/surveys",
		templateUrl : "surveys/surveys.html"
	})
});
app.controller("SurveysCtrl", SurveysCtrl);

function SurveysCtrl($scope, $sce, $moment, surveyService, $mdDialog) {

	// TODO mettere bottone modifica
	$scope.activeSurveys = [];
	$scope.refreshActive = function() {
		surveyService.getSurveys($scope.user, true, false,
				$scope.activeSurveys.length).then(function(response) {
			for (var a = 0; a < response.data.surveyMini.length; a++) {
				$scope.activeSurveys.push(response.data.surveyMini[a]);
			}
		}, $scope.serverErrorCallback);
	}
	$scope.finishedSurveys = [];
	$scope.refreshFinished = function() {
		surveyService.getSurveys($scope.user, false, false,
				$scope.finishedSurveys.length).then(function(response) {
			for (var a = 0; a < response.data.surveyMini.length; a++) {
				$scope.finishedSurveys.push(response.data.surveyMini[a]);
			}
		}, $scope.serverErrorCallback);
	}
	$scope.mySurveys = [];
	$scope.refreshMine = function() {	//TODO: vengono triplicati i sondaggi propri, per qualche ragione
		surveyService.getSurveys($scope.user, false, true,
				$scope.mySurveys.length).then(function(response) {
			for (var a = 0; a < response.data.surveyMini.length; a++) {
				$scope.mySurveys.push(response.data.surveyMini[a]);
			}
		}, $scope.serverErrorCallback);
	}
	$scope.$watch("user", function() {
		$scope.refreshActive();
		$scope.refreshFinished();
		$scope.refreshMine();
	});
	$scope.modifySurvey = function(surveyId) {
		surveyService.getSurveyById(surveyId).then(function(response) {
			// gestire risposta del server
		});
	}

	$scope.calculateExpireDate = function(date) {
		var remainingTime = $moment(date).fromNow();
		if (remainingTime.startsWith("tra")) {
			remainingTime = "Scade " + remainingTime;
		} else {
			remainingTime = "Scaduto " + remainingTime;
		}
		return remainingTime;
	}
	$scope.fullscreen = true;
	
	$scope.showSurveyDetails = function(ev, id, index) {
		$mdDialog.show({
			locals : {
				user: $scope.user,
				surveyId : id,
				serverErrorCallback: $scope.serverErrorCallback,
				serverErrorCallbackToast: $scope.serverErrorCallbackToast,
				showSimpleToast: $scope.showSimpleToast,
				showActionToast: $scope.showActionToast
			}, // passa il campo id ad alias surveyId al controller del dialog
			controller : SurveyPageCtrl,
			templateUrl : 'surveys/surveyPage/surveyPageTMPL.html',
			parent : angular.element(document.body),
			targetEvent : ev,
			clickOutsideToClose : true,
			fullscreen : $scope.fullscreen
		}).then(function(votes) {
			if(votes != null) {
				$scope.activeSurveys[index].voters++;
			}
		}, function() {

		});
	}
}
