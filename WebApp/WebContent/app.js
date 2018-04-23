var app = angular.module("CopeApp", ["ngMaterial","ngMessages","ngAnimate","ngSanitize","ngAria","ui.router","LocalStorageModule","ngLetterAvatar","angularFileUpload"]);
app.config(function($urlRouterProvider, localStorageServiceProvider){
	$urlRouterProvider.otherwise(function($injector){
		return "/home";
	});
	
	localStorageServiceProvider.setPrefix('CopeApp');
	localStorageServiceProvider.setDefaultToCookie(false);
});