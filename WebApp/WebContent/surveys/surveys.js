app.config(function($stateProvider){
	$stateProvider.state("surveys", {
		url:"/surveys",
		templateUrl:"surveys/surveys.html"
	})
});
app.controller("SurveysCtrl", SurveysCtrl);

function SurveysCtrl($scope, $sce, $moment, surveyService) {
	
	//TODO mettere bottone modifica
	$scope.activeSurveys = [];
	surveyService.getSurveys($scope.user, true, false, $scope.activeSurveys.length).then(function(response) {
		for (var a = 0; a < data.surveyMini.length; a++) {
			$scope.activeSurveys.push(data.surveyMini[a]);
		}
	}, $scope.serverErrorCallback);
	$scope.finishedSurveys = [];
	surveyService.getSurveys($scope.user, false, false, $scope.finishedSurveys.length).then(function(data) {
		for (var a = 0; a < data.surveyMini.length; a++) {
			$scope.finishedSurveys.push(data.surveyMini[a]);
		}
	}, $scope.serverErrorCallback);
	$scope.mySurveys = [];
	surveyService.getSurveys($scope.user, false, true, $scope.mySurveys.length).then(function(data) {
		for (var a = 0; a < data.surveyMini.length; a++) {
			$scope.mySurveys.push(data.surveyMini[a]);
		}
	}, $scope.serverErrorCallback);
	
	$scope.modifySurvey = function(surveyId) {
		urveyService.getSurveyById(surveyId).then(function(response) {
			//gestire risposta del server
		});
	}
	
	$scope.calculateExpireDate = function(date) {
		var remainingTime = $moment(date).fromNow();
		if (remainingTime.startsWith("tra")) {
			remainingTime = "Scade "+remainingTime;
		} else {
			remainingTime = "Scaduto "+remainingTime;
		}
		return remainingTime;
	}
}