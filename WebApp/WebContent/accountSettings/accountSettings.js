app.config(function($stateProvider){
	$stateProvider.state("accountSettings", {
		url:"/accountSettings",
		templateUrl:"accountSettings/accountSettings.html"
	})
});
app.controller("AccountSettingsCtrl", AccountSettingsCtrl);

function AccountSettingsCtrl($scope) {
	
	$scope.oldPassword = "";
	$scope.newPassword = "";
	$scope.newPasswordRepeated = "";
	
	$scope.$watch("oldPassword", function() {
		$scope.accountSettingsForm.oldPassword.$setValidity("match", $scope.oldPassword == $scope.user.password);
		$scope.accountSettingsForm.newPassword.$setValidity("equal", $scope.newPassword == $scope.newPasswordRepeated);
		$scope.accountSettingsForm.newPasswordRepeated.$setValidity("equal", $scope.newPassword == $scope.newPasswordRepeated);
	})
	$scope.$watch("newPassword", function() {
		$scope.accountSettingsForm.oldPassword.$setValidity("match", $scope.oldPassword == $scope.user.password);
		$scope.accountSettingsForm.newPassword.$setValidity("equal", $scope.newPassword == $scope.newPasswordRepeated);
		$scope.accountSettingsForm.newPasswordRepeated.$setValidity("equal", $scope.newPassword == $scope.newPasswordRepeated);
	})
	$scope.$watch("newPasswordRepeated", function() {
		$scope.accountSettingsForm.oldPassword.$setValidity("match", $scope.oldPassword == $scope.user.password);
		$scope.accountSettingsForm.newPassword.$setValidity("equal", $scope.newPassword == $scope.newPasswordRepeated);
		$scope.accountSettingsForm.newPasswordRepeated.$setValidity("equal", $scope.newPassword == $scope.newPasswordRepeated);
	})
}