(function (angular) {
	'user strict';

	angular.module("myApp.GoogleScholarCtrl", []);
	angular.module("myApp.GoogleScholarService", []);

	angular.module("myApp.UserAgentCtrl",[]);
	angular.module("myApp.UserAgentService",[]);

	angular.module("myApp.navMenuDirective", []);
	angular.module("myApp.searchButtonDirective", []);

	angular.module("myApp", [
		'ngResource',
		'ui.router',
		'ngTable',

		'myApp.GoogleScholarCtrl',
		'myApp.GoogleScholarService',

		'myApp.UserAgentCtrl',
		'myApp.UserAgentService',

		'myApp.navMenuDirective',
		'myApp.searchButtonDirective'
	]).run(
		// It's very handy to add references to $state and $stateParams to the $rootScope
		// so that you can access them from any scope within your applications.For example,
		// <li ng-class="{ active: $state.includes('contacts.list') }"> will set the <li>
		// to active whenever 'contacts.list' or one of its decendents is active.
		['$rootScope', '$state', '$stateParams', function($rootScope, $state, $stateParams){
			$rootScope.$state = $state;
			$rootScope.$stateParams = $stateParams;
		}]
	).config(function($stateProvider, $urlRouterProvider) {

		$urlRouterProvider.otherwise('/home');

		$stateProvider
			.state('home', {
				url: '/home',
				templateUrl: '../views/partial/home-partial.html'
			})
			.state('citation-management', {
				url: '/citation-management',
				templateUrl: '../views/partial/citation-management-partial.html'
			})
			.state('search-setting', {
				url: '/search-setting',
				templateUrl: '../views/partial/search-settings-partial.html'
			});
	});
}(angular));