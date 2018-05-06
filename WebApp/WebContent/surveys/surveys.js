app.config(function($stateProvider){
	$stateProvider.state("surveys", {
		url:"/surveys",
		templateUrl:"surveys/surveys.html"
	})
});
app.controller("SurveysCtrl", SurveysCtrl);

function SurveysCtrl($scope, $sce, $moment, surveyService) {
	
	//TODO mettere bottone modifica
	surveyService.getSurveys($scope.user.userId, true).then(function(response) {
		$scope.activeSurveys = response.surveyMini;
	});
	surveyService.getSurveys($scope.user.userId, false).then(function(data) {
		$scope.finishedSurveys = data.surveyMini;
	});
	
	$scope.modifySurvey = function(surveyId) {
		urveyService.getSurveyById(surveyId).then(function(response) {
			//gestire risposta del server
		});
	}
	
	$scope.calculateExpireDate = function(date) {
		var remainingTime = $moment(date).fromNow();
		return remainingTime;
	}
	
	$scope.init = function() {
		console.log("controller survey creato "+$scope.user);
	}
	$scope.init();
}