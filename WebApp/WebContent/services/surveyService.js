app.service('surveyService', SurveyService);

function SurveyService($q, $http) {
	
	this.getSurveys = function(userId, active) {

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
			data.surveyMini[0].closeSurveyDate.setTime(1524562828000);
			data.surveyMini[1].closeSurveyDate.setTime(1547284040000);
			resolve(data);
		});
		
	}
	
}