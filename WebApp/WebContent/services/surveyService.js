app.service('surveyService', SurveyService);

function SurveyService($q, $http) {
	
	this.getSurveys = function(userId, active) {

		return $q(function(resolve, reject) {
			resolve({
				surveyMini: [{
					surveyId: 1,
					question: "<h1>Question 1</h1>",
					closeSurveyDate: new Date(),
					voters: 1208
				}, {
					surveyId: 2,
					question: "<h1>Question 2</h1>",
					closeSurveyDate: new Date(),
					voters: 11202
				}]
			});
		});
		
	}
	
}