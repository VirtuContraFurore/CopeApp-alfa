var app = angular.module("CopeApp", ["ngMaterial","ngMessages","ngAnimate","ngSanitize","ngAria","ui.router","LocalStorageModule","ngLetterAvatar","angularFileUpload", "angular-momentjs","ui.tinymce"]);
app.config(function($urlRouterProvider, localStorageServiceProvider, $mdDateLocaleProvider){
	$urlRouterProvider.otherwise(function($injector){
		return "/home";
	});
	
	localStorageServiceProvider.setPrefix('CopeApp');
	localStorageServiceProvider.setDefaultToCookie(false);
	$mdDateLocaleProvider.formatDate = function(date) {
		return moment(date).format('DD/MM/YYYY');
	}
});
app.filter('trustAsHtml',['$sce', function($sce) {
	  return function(text) {
	    return $sce.trustAsHtml(text);
	  };
	}]);