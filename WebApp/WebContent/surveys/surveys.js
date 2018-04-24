app.config(function($stateProvider){
	$stateProvider.state("surveys", {
		url:"/surveys",
		templateUrl:"surveys/surveys.html"
	})
});
app.controller("SurveysCtrl", SurveysCtrl);

function SurveysCtrl($scope, $sce, $moment, surveyService) {
	
	
	$scope.$watch("user", function() {
		surveyService.getSurveys($scope.user.userId, true).then(function(data) {
			$scope.activeSurveys = data.surveyMini;
		});
	})
	$scope.parseQuestion = function(question) {
		return $sce.trustAsHtml(question)
	}
	$scope.calculateExpireDate = function(date) {
		var remainingTime = $moment(date).fromNow();
		return remainingTime;
	}
}