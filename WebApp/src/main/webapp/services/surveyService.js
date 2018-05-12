app.service('surveyService', SurveyService);

function SurveyService($q, $http) {
	//TODO chiedere al server se mostrare i risultati
	this.getSurveys = function(user, isActive, isMine, lastSurvey) {
		var req = {
				method: 'POST',
				url: 'http://localhost:8080/CopeApp/rest/surveyList',
				headers: {
					'Content-Type': "application/json",
					'Authorization': btoa(user.mail+":"+user.password)
				},
				data: {
					active: isActive,
					mine: isMine,
					keyword: "",
					idUtente: user.userId,
					lastSurveyNumber: lastSurvey,
					numberToRetrieve: 20
				}
		}
		return $http(req);
	}
	
	this.getSurveyById = function(id) {
		var req = {
				method: 'POST',
				url: 'http://localhost:8080/CopeApp/rest/surveybyid',
				headers: {
					'Content-Type': "application/json",
					'Authorization': btoa(user.mail+":"+user.password)
				},
				data: {
					surveyId: id
				}
		}
		return $http(req);
	}
	
	
	this.uploadSurvey = function(user, survey) {
		var req = {
				method: 'POST',
				url: 'http://localhost:8080/CopeApp/rest/surveycreate',
				headers: {
					'Content-Type': "application/json",
					'Authorization': btoa(user.mail+":"+user.password)
				},
				data: survey
		}
		return $http(req);
	}
	
	this.sendVotes = function(user, votes, id) {
		var req = {
				method: 'POST',
				url: 'http://localhost:8080/CopeApp/rest/surveyvote',
				headers: {
					'Content-Type': "application/json",
					'Authorization': btoa(user.mail+":"+user.password)
				},
				data: {
					surveyId: id,
					answers: votes
				}
		}
		return $http(req);
	}
}