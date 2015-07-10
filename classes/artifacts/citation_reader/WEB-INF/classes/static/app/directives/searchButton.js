(function(angular){

	'use strict';

	var searchButton = function() {
		return {
			require: '^GoogleScholarService',
			restrict: 'E',
			templateUrl: '../../directives/searchButton.html',
			controller: function($scope, GoogleScholarService) {
				$scope.placeholder = "dsfsd";

				$scope.searchData = function(){
					console.log($scope.content);
					GoogleScholarService.search({queryString: $scope.content});;
					$scope.content= "";
				}
			}
		};
	};

	angular.module("myApp.searchButtonDirective").directive("searchButton", searchButton);
}(angular));