app.service('surveyService', SurveyService);

function SurveyService($q, $http) {
	//TODO gestire lastSurveyNumber e numberToRetrieve
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
}