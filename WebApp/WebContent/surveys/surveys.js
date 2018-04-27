app.config(function($stateProvider){
	$stateProvider.state("surveys", {
		url:"/surveys",
		templateUrl:"surveys/surveys.html"
	})
});
app.controller("SurveysCtrl", SurveysCtrl);

function SurveysCtrl($scope, $sce, $moment, surveyService) {
	
	$scope.setExtraButtonById(1);
	
	$scope.$watch("user.userId", function() {
		surveyService.getSurveys($scope.user.userId, true).then(function(response) {
			$scope.activeSurveys = response.surveyMini;
		});
	})
	
	$scope.modifySurvey = function(surveyId) {
		urveyService.getSurveyById(surveyId).then(function(response) {
			//gestire risposta del server
		});
	}
	
	$scope.$watch("user.userId", function() {
		surveyService.getSurveys($scope.user.userId, false).then(function(data) {
			$scope.finishedSurveys = data.surveyMini;
		});
	})
	$scope.calculateExpireDate = function(date) {
		var remainingTime = $moment(date).fromNow();
		return remainingTime;
	}
}