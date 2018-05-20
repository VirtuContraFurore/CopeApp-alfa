app.controller("SurveyPageCtrl", SurveyPageCtrl);

function SurveyPageCtrl($scope, $sce, $mdDialog, surveyService, user, surveyId, serverErrorCallback, serverErrorCallbackToast) {

	$scope.user = user;
	$scope.surveyId = surveyId;
	$scope.serverErrorCallback = serverErrorCallback;
	$scope.serverErrorCallbackToast = serverErrorCallbackToast;
	$scope.answers = [];
	$scope.question;
	$scope.maxAnswers;
	$scope.insertUser;  //inserire campi mancanti per completare survey details
	
	surveyService.getSurveyById($scope.user, $scope.surveyId).then(	//prende il survey con l'id passato e usa le answers
		function(value) {
			$scope.answers = value.data.answers;
			$scope.question = value.data.question;
			$scope.maxAnswers = value.data.answersNumber;
		}, $scope.serverErrorCallbackToast);
	
	$scope.votes = [];

	$scope.toggle = function(item) {
		if($scope.votes.length + 1 < maxAnswers) {
			var idx = $scope.votes.indexOf(item);
			if (idx > -1) {
				$scope.votes.splice(idx, 1);
			} else {
				$scope.votes.push(item);
			}
		} else {
			$scope.votes.splice(0,1);  //se si vota più del limite il primo voto viene rimosso
		}
	}; 
	
	$scope.exists = function(item) {
		return $scope.votes.indexOf(item) > -1;
	};
	
	$scope.hide = function() {
		$mdDialog.hide();
	};
	$scope.cancel = function() {
		$mdDialog.cancel();
	};
	$scope.vote = function(votes) {
		$mdDialog.hide(votes);
	};
	
	$scope.checkVoteForm = function () {
		var error = null;
		if($scope.answers.length > $scope.maxAnswers) {error = "troppi voti espressi"}  //ridondante
	}
	
	$scope.sendVotes = function() {
		if($scope.checkVoteForm() !== null){
			$scope.showSimpleToast($scope.checkVoteForm(), "bottom right", 2500);
		} else {
			$scope.showActionToast("Non sara' piu' possibile modificare il voto, continuare?", "bottom right", 7500, "OK", function(response) {
				if ( response == 'ok' ) {
					surveyService.sendVotes($scope.user, $scope.votes, $scope.surveyId).then(function() {
						//TODO chiudere il dialog
					}, $scope.serverErrorCallbackToast)
				}
			});
		}
	}
  };
