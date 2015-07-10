(function (angular) {

	var GoogleScholar = function ($resource) {

		var resourceErrorHandler = function (response) {
			console.log("error");
		};

		return $resource('/items/:methodName/:queryString', {
			queryString: '@queryString'
		}, {
			update: {
				method: "PUT"
			},
			remove: {
				method: "DELETE"
			},
			search: {
				method: "GET",
				params: {methodName: "searchData"},
				interceptor: {responseError: resourceErrorHandler},
				isArray: true,
				cache: true
			}

		});
	};

	GoogleScholar.$inject = ['$resource'];
	angular.module("myApp.GoogleScholarService").factory("GoogleScholarService", GoogleScholar);

}(angular));