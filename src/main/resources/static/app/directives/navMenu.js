/**
 * Created by ban on 17. 6. 2015.
 */
(function(angular) {

	var menuController = function($scope){
		$scope.menus = [
			{
				title: "Home",
				action: "home",
				menus: [
					{
						title: "Submenu 1a",
						action: "stuff"
					},
					{
						title: "Submenu 1b",
						action: "moreStuff",
						menus: [
							{
								title: "Submenu 1b 1",
								action: "stuff"
							},
							{
								title: "Submenu 1b 2",
								action: "moreStuff"
							}
						]
					}
				]
			},
			{
				title: "Citation Management",
				action: "citation-management",
				menus: [
					{
						title: "Submenu 2a",
						action: "awesomeStuff"
					},
					{
						title: "Submenu 2b",
						action: "moreAwesomeStuff"
					}
				]
			},
			{
				title: "Search Setting",
				action: "search-setting"
			}
		];
	};
	var navMenu = function() {
		return {
			restrict: 'E',
			templateUrl: '../../directives/menu.html',
			controller: menuController
		};
	};


	angular.module("myApp.navMenuDirective").directive("navBarMenu", navMenu);
}(angular));
