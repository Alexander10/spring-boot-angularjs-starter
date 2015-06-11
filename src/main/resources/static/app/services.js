(function(angular) {
  var ItemFactory = function($resource) {
    return $resource('/items/:id', {
      id: '@id'
    }, {
      update: {
        method: "PUT"
      },
      remove: {
        method: "DELETE"
      }
    });
  };
  
  ItemFactory.$inject = ['$resource'];
  angular.module("myApp.services").factory("Item", ItemFactory);

  var CustomActionHandler = function($resource){
    return $resource('/items/search', {


    });
  };

  CustomActionHandler.$inject = ['$resource'];
  angular.module("myApp.services").factory("CustomActionHandler", ItemFactory);
}(angular));