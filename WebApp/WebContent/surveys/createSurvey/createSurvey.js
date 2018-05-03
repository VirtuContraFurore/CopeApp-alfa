app.config(function($stateProvider){
	$stateProvider.state("createSurvey", {
		url:"/createSurvey",
		templateUrl:"surveys/createSurvey/createSurvey.html"
	})
});
app.controller("CreateSurveyCtrl", CreateSurveyCtrl);

function CreateSurveyCtrl($scope, $moment, surveyService) {
	
	$scope.setExtraButtonById(2);
	
	$scope.question;
	$scope.expireDate = $moment(new Date).add(1, 'days').toDate();
	$scope.minDate = $moment(new Date).add(1, 'days').toDate();
	$scope.maxDate = $moment(new Date).add(9, 'months').toDate();
	
	//TODO giochicciare con le impostazioni per la versione desktop https://www.tinymce.com/docs/configure/
	//TODO cambiare colori al tutto usando questo tool http://skin.tinymce.com/?_ga=2.96845919.1922290377.1525292558-662263511.1524315199
	$scope.tinymceOptions = {
		theme: 'modern',
		mobile: {
			theme: 'mobile',
			plugins: ['lists', 'autolink' ],
		    toolbar: ['undo', 'redo', 'bold', 'italic', 'underline', 'link', 'image', 'bullist', 'numlist', 'fontsizeselect', 'styleselect'],
			branding: false
		},
		branding: false
	}
	
}