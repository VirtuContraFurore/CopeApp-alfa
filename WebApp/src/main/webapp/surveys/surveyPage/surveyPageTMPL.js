app.controller("SurveyPageCtrl", SurveyPageCtrl);

function SurveyPageCtrl($scope, $sce, $mdDialog, surveyService, user, surveyId, serverErrorCallback, serverErrorCallbackToast, showSimpleToast, showActionToast) {

	$scope.user = user;
	$scope.surveyId = surveyId;
	$scope.serverErrorCallback = serverErrorCallback;
	$scope.serverErrorCallbackToast = serverErrorCallbackToast;
	$scope.showSimpleToast = showSimpleToast;
	$scope.showActionToast = showActionToast;
	$scope.answers = [];
	$scope.question;
	$scope.maxAnswers;
	$scope.answerLeft;
	$scope.insertUser;  //inserire campi mancanti per completare survey details
	$scope.isLoading = true;
	
	$scope.data = [];
	$scope.labels = [];
//	$scope.winnerAnswer = [];
	
	
	surveyService.getSurveyById($scope.user, $scope.surveyId).then(	//prende il survey con l'id passato e usa le answers
		function(value) {
			$scope.isLoading = false;
			$scope.answers = value.data.surveyDTO.answers;
			$scope.question = value.data.surveyDTO.question;
			$scope.maxAnswers = value.data.surveyDTO.answersNumber;
			$scope.answerLeft = $scope.maxAnswers;
			$scope.isVoted = value.data.hasVoted;  //TODO creare due pagine diverse per voto e info
			if ($scope.isVoted) {
//				var winnerAnswerId = [];
//				winnerAnswerId.push(0);
//				var maxVotesumber = $scope.answers[0].votesNumber;
				$scope.data.push($scope.answers[0].votesNumber);
				$scope.labels.push($scope.answers[0].answerContent.answerText);
				for (var i = 1; i < $scope.answers.length; i++) {
//					if ($scope.answers[i].votesNumber > maxVotesumber) {
//						maxVotesumber = $scope.answers[i].votesNumber;
//						winnerAnswerId.length = 0;
//						winnerAnswerId.push(i);
//					} else if ($scope.answers[i].votesNumber == maxVotesumber) {
//						maxVotesumber = $scope.answers[i].votesNumber;
//						winnerAnswerId.push(i);
//					}
					$scope.data.push($scope.answers[i].votesNumber);
					$scope.labels.push($scope.answers[i].answerContent.answerText);
				}
//				for (var j = 0; j < winnerAnswerId.length; j++) {
//					$scope.winnerAnswer.push($scope.answers[winnerAnswerId[j]])
//				}
			}
		}, $scope.serverErrorCallbackToast);
	
	$scope.votes = [];

	$scope.toggle = function(item) {
		if($scope.votes.length + 1 <= $scope.maxAnswers || $scope.votes.indexOf(item) != -1) {
			var idx = $scope.votes.indexOf(item);
			if (idx > -1) {
				$scope.votes.splice(idx, 1);
				$scope.answerLeft += 1;
			} else {
				$scope.votes.push(item);
				$scope.answerLeft -= 1;
			}
			
		} else {
			$scope.votes.splice(0,1);
			$scope.votes.push(item);
		}
		if ($scope.votes.length >= 1 && $scope.votes.length <= $scope.maxAnswers) {
			$scope.canVote = false;
		} else {
			$scope.canVote = true;
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
		if($scope.votes.length > $scope.maxAnswers) {
			return error = "troppi voti espressi"
		} else {
			return null
		}
	}
	
	$scope.sendVotes = function() {
		if($scope.checkVoteForm() != null){
			$scope.showSimpleToast($scope.checkVoteForm(), "bottom right", 2500);
		} else {
			$scope.showActionToast("Non sara' piu' possibile modificare il voto, continuare?", "bottom right", 7500, "OK", function(response) {
				if ( response == 'ok' ) {
					surveyService.sendVotes($scope.user, $scope.votes, $scope.surveyId).then(function() {
						$scope.vote($scope.votes);
						$scope.showSimpleToast("Hai votato!", "bottom right", 2500);
					}, $scope.serverErrorCallbackToast)
				}
			});
		}
	}
  };
