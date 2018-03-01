app.controller("SidenavHeaderCtrl",SHCtrl);

function SHCtrl($scope){
	var sidenavWidth = document.getElementById('leftSidenav').offsetWidth;
	var sidenavHeight = document.getElementById('leftSidenav').offsetHeight;
	$scope.username = $scope.getUser().name;
	if($scope.customs.userWallpaper=='default'){
			var pattern = Trianglify({
					width: sidenavWidth,
					height: sidenavHeight/4,
					variance: 1,
					x_colors: 'YlGnBu'
			});
			$scope.userWallpaper = pattern.png();
	} else {
		$scope.userWallpaper = $scope.customs.userWallpaper;
	}
	$scope.userImage = $scope.customs.userImage;
	//$("img[name='profilePicture']").css("left", sidenavWidth/2-32); riattivare se si vuole immagine profilo al centro orizontalmente
	$("img[name='profilePicture']").css("top", sidenavHeight/10-32);
	$("h1[name='username']").css("top", sidenavHeight/10+32);
	
}
