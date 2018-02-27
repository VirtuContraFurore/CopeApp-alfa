var app= angular.module("CopeApp", ["ngMaterial","ngMessages","ngAnimate","ngSanitize","ngAria","ui.router","LocalStorageModule"]);
app.config(function($urlRouterProvider, localStorageServiceProvider){
	$urlRouterProvider.otherwise(function($injector){
		return "/";
	});
	
	localStorageServiceProvider.setPrefix('CopeApp');
	localStorageServiceProvider.setDefaultToCookie(false);
});