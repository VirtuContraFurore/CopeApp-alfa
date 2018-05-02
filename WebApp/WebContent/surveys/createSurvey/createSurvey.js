app.config(function($stateProvider){
	$stateProvider.state("createSurvey", {
		url:"/createSurvey",
		templateUrl:"surveys/createSurvey/createSurvey.html"
	})
});
app.controller("CreateSurveyCtrl", CreateSurveyCtrl);

function CreateSurveyCtrl($scope, surveyService) {
	
	$scope.setExtraButtonById(2);
	
	$scope.tinymceModel;
	
	//TODO giochicciare con le impostazioni https://www.tinymce.com/docs/configure/
	$scope.tinymceOptions = {
		theme: 'modern',
		mobile: {
			theme: 'mobile'
		}
	}
	
}