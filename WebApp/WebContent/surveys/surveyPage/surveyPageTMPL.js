app.config(function($stateProvider){
	$stateProvider.state("surveyPage", {
		url:"/surveys/surveyPage",
		templateUrl:"surveys/surveyPage/surveyPageTMPL.html"
	})
});
app.controller("SurveysPageCtrl", SurveysPageCtrl);

function SurveysPageCtrl($scope, surveyId) {

	$scope.answers = [];
	$scope.question;
	$scope.maxAnswers;
	$scope.insertUser;  //inserire campi mancanti per completare survey details
	surveyService.getSurveyById(surveyId).then(	//prende il survey con l'id passato e usa le answers
		function(value) {
			$scope.answers = value.data.answers;
			$scope.question = value.data.question;
			$scope.maxAnswers = value.data.answersNumber;
		},
		function(reason) {
			$scope.roles = null;
//			$scope.showSimpleToast(loginResponse.data.description,
//					"bottom right", 2000);
//			console.error("errore n: " + loginResponse.data.httpStatus
//					+ "; StackTrace del server: //Da aggiungere");
		})
	
	
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
		return answers.indexOf(item) > -1;
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
			$scope.showActionToast("Procedere con l'invio dei voti?", "bottom right", 7500, "OK", function(response) {
				if ( response == 'ok' ) {
					surveyService.uploadSurvey($scope.user, $scope.votes, surveyId).then(function() {}, $scope.serverErrorCallback)
				}
			});
		}
	}


	}
  };
