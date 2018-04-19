app.controller("SidenavHeaderCtrl",SHCtrl);

function SHCtrl($scope){
	
	$("#backgroundContainer").ready(
		function() {
			var sidenavWidth = $("#leftSidenav").width();
			var sidenavHeight = $("#leftSidenav").height();
			if($scope.getUser().wallpaper == 'default'){
					var pattern = Trianglify({
							width: sidenavWidth,
							height: sidenavHeight/4,
							variance: 1,
							x_colors: 'YlGnBu'
					});
					$scope.userWallpaper = pattern.png();
			} else {
				$scope.userWallpaper = $scope.getUser().wallpaper;
			}
			$("#backgroundContainer").css("background-image", "url("+$scope.userWallpaper+")");
		}
	);
	
}
