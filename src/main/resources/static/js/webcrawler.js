var webcrawlerModule = angular.module('webcrawler', []);

webcrawlerModule.factory('searchClient', function ($http) {
    return function (searchQuery) {
        $http.get("http://localhost:8080/search?q=" + searchQuery).success(function (data) {
            $scope.result = data;
        });
    }
});

webcrawlerModule.controller('search', ['$scope', 'searchClient', function ($scope, searchClient) {
    $scope.greeting = {id: 'hello', content: 'Hello World', test: 'test'};

    var self = this;
    self.submitQuery = function () {
        console.log('Submit query');
        searchClient(self.searchInput);
    }
}]);
