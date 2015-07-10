(function (angular) {
	"use strict";

	var UserAgentCtrl = function ($scope, UserAgent, ngTableParams) {
		$scope.itemsByPage = 1;
		$scope.items = [];

		UserAgent.query(function (response) {
			var data = response ? response : [];
			$scope.items = data;
			$scope.tableParams = new ngTableParams({
				page: 1,
				count: 5,
				sorting: {
					name: 'asc'
				}
			},{
				total: data.length,
				getData: function($defer, params) {

					params.total(data.length);
					$defer.resolve(data.slice((params.page() - 1) * params.count(), params.page() * params.count()));

				}
			});
		});


    	$scope.updateItem = function(item){

			item.$update(function(){
				item.$edit = false;
			});

		};

		$scope.addItem = function (item) {

			new UserAgent(
				{
					id: item.id,
					name: item.name,
					version: item.version,
					operatingSystem: item.operatingSystem
				}).$save(function(item){
					$scope.items.push(item);
					$scope.tableParams.reload();

				});

		};

		$scope.removeItem = function (item) {
			item.$remove(function () {
				$scope.items.splice($scope.items.indexOf(item), 1);

			});

		};


	};

	UserAgentCtrl.$inject = ['$scope', 'UserAgent', 'ngTableParams'];
	angular.module("myApp.UserAgentCtrl").controller("UserAgentCtrl", UserAgentCtrl);
}(angular));