app.service('surveyService', SurveyService);

function SurveyService($q, $http) {
	//TODO gestire lastSurveyNumber e numberToRetrieve
	this.getSurveys = function(userId, isActive, isMine, lastSurvey) {
		var req = {
				method: 'POST',
				url: 'http://localhost:8080/CopeApp/rest/surveyList',
				headers: {
					'Content-Type': "application/json"
				},
				data: {
					active: isActive,
					mine: isMine,
					keyword: "",
					idUtente: userId,
					lastSurveyNumber: lastSurvey,
					numberToRetrieve: 20
				}
		}
		return $http(req);
		
	}
	this.uploadSurvey = function(survey) {
		var req = {
				method: 'POST',
				url: 'http://localhost:8080/CopeApp/rest/surveycreate',
				headers: {
					'Content-Type': "application/json"
				},
				data: survey
		}
		return $http(req);
	}
}