app.service('surveyService', SurveyService);

function SurveyService($q, $http) {
	
	this.getSurveys = function(userId, active) {

		if (active) {
			return $q(function(resolve, reject) {
				var data = {
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
					}
				data.surveyMini[0].closeSurveyDate.setTime(1525562828000);
				data.surveyMini[1].closeSurveyDate.setTime(1526562828000);
				resolve(data);
			});
		} else {
			return $q(function(resolve, reject) {
				var data = {
						surveyMini: [{
							surveyId: 1,
							question: "<h1>Question 3</h1>",
							closeSurveyDate: new Date(),
							voters: 1208
						}, {
							surveyId: 2,
							question: "<h1>Question 4</h1>",
							closeSurveyDate: new Date(),
							voters: 11202
						}, {
							surveyId: 2,
							question: "<h1>Question 4</h1>",
							closeSurveyDate: new Date(),
							voters: 11202
						}, {
							surveyId: 2,
							question: "<h1>Question 4</h1>",
							closeSurveyDate: new Date(),
							voters: 11202
						}, {
							surveyId: 2,
							question: "<h1>Question 4</h1>",
							closeSurveyDate: new Date(),
							voters: 11202
						}]
					}
				data.surveyMini[0].closeSurveyDate.setTime(1522075305000);
				data.surveyMini[1].closeSurveyDate.setTime(1518446505000);
				resolve(data);
			});
		}
		
	}
	
	this.getSurveyById = function(sufveyId) {
		
		return $q(function(resolve, reject) {
			var data;
			resolve(data);
		});
		
	}
	
}