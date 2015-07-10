(function(angular){
	"use strict";

	var UserAgentFactory = function($resource){
		return $resource('/user-agent/:id', {
			id: '@id'
		}, {
			update: {
				method: "PUT",
				isArray: false,
				cache: false
			},
			remove: {
				method: "DELETE",
				isArray: true,
				cache: false
			},
			search: {
				method: "GET",
				isArray: true,
				cache: true
			}

		});

	};

	UserAgentFactory.$inject = ['$resource'];
	angular.module('myApp.UserAgentService').factory('UserAgent', UserAgentFactory);

}(angular));