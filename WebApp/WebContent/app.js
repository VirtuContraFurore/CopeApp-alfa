var app= angular.module("CopeApp", ["ngMaterial","ngMessages","ngAnimate","ngSanitize","ngAria","ui.router"]);
app.config(function($urlRouterProvider){
	$urlRouterProvider.otherwise(function($injector){
		return "/";
	});
});