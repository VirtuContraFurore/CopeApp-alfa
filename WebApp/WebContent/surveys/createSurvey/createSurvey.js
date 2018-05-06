app.config(function($stateProvider) {
	$stateProvider.state("createSurvey", {
		url : "/createSurvey",
		templateUrl : "surveys/createSurvey/createSurvey.html"
	})
});
app.controller("CreateSurveyCtrl", CreateSurveyCtrl);

function CreateSurveyCtrl($scope, $moment, surveyService, commonsService) {

	$scope.question;
	$scope.expireDate = $moment(new Date).add(1, 'days').toDate();
	$scope.minDate = $moment(new Date).add(1, 'days').toDate();
	$scope.maxDate = $moment(new Date).add(9, 'months').toDate();

	//TODO aggiungere errori vari
	//TODO giochicchiare con le impostazioni per la versione desktop https://www.tinymce.com/docs/configure/
	//TODO cambiare colori al tutto usando questo tool http://skin.tinymce.com/?_ga=2.96845919.1922290377.1525292558-662263511.1524315199
	$scope.tinymceOptions = {
		theme : 'modern',
		mobile : {
			theme : 'mobile',
			plugins : [ 'lists', 'autolink' ],
			toolbar : [ 'undo', 'redo', 'bold', 'italic', 'underline', 'link',
					'image', 'bullist', 'numlist', 'fontsizeselect',
					'styleselect' ],
			branding : false
		},
		branding : false
	}

	
	$scope.isChecked = function(selected) {
		return selected.length === $scope.roles.length;
	};
	$scope.isIndeterminate = function(selected) {
		return (selected.length !== 0 && selected.length !== $scope.roles.length);
	};
	$scope.toggleAll = function(selected) {
		if (selected.length === $scope.roles.length) {
			selected.length = 0;
		} else if (selected.length === 0 || selected.length > 0) {
			selected.length = 0;
			selected.push(...$scope.roles);
		}
	};
	$scope.toggle = function(item, list) {
		var idx = list.indexOf(item);
		if (idx > -1) {
			list.splice(idx, 1);
		} else {
			list.push(item);
		}
	};

	$scope.exists = function(item, list) {
		return list.indexOf(item) > -1;
	};
	
	
	$scope.roles = [];
	commonsService.getAllRoles().then(
			function(value) {
				$scope.roles = value.data.roles;
			},
			function(reason) {
				$scope.roles = null;
				$scope.showSimpleToast(loginResponse.data.description,
						"bottom right", 2000);
				console.error("errore n: " + loginResponse.data.httpStatus
						+ "; StackTrace del server: //Da aggiungere");
			})
	$scope.selectedVoters = [];
	$scope.selectedViewers = [];
	
	$scope.answerNumber = 1;
	$scope.maxNumberOfAnswers = 5;
}