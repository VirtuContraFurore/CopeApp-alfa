app.config(function($stateProvider){
	$stateProvider.state("accountSettings", {
		url:"/accountSettings",
		templateUrl:"accountSettings/accountSettings.html"
	})
});
app.controller("AccountSettingsCtrl", AccountSettingsCtrl);

function AccountSettingsCtrl($scope) {
	
	
	
}