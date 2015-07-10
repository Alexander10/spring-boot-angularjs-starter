(function (angular) {
	var GoogleScholarCtrl = function ($scope, GoogleScholarService) {

		GoogleScholarService.query(function (response) {
			$scope.items = response ? response : [];
		});

		$scope.addItem = function (description) {
			console.log(description);

			new Item({
				description: description,
				checked: false
			}).$save(function (item) {
					$scope.items.push(item);
				});
			$scope.newItem = "";
		};


		$scope.updateItem = function (item) {
			item.$update();
		};

		$scope.deleteItem = function (item) {
			item.$remove(function () {
				$scope.items.splice($scope.items.indexOf(item), 1);
			});
		};

		$scope.searchInGoogleScholar = function (description) {
			GoogleScholarService.search({queryString: description});
		};
	};

	GoogleScholarCtrl.$inject = ['$scope', 'GoogleScholarService'];
	angular.module("myApp.GoogleScholarCtrl").controller("GoogleScholarCtrl", GoogleScholarCtrl);
}(angular));