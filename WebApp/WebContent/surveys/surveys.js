app.config(function($stateProvider){
	$stateProvider.state("surveys", {
		url:"/surveys",
		templateUrl:"surveys/surveys.html"
	})
});
app.controller("SurveysCtrl", SurveysCtrl);

function SurveysCtrl($scope, surveyService) {
	
	surveyService.getSurveys($scope.user.userId, true).then(function(data) {
		$scope.activeSurveys = data.surveyMini;
	});
	
}