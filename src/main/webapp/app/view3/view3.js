'use strict';

angular.module('myApp.view3', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view3', {
                    templateUrl: 'app/view3/view3.html',
                    controller: 'View3Ctrl'
                });
            }])

        .controller('View3Ctrl', function ($http, $scope) {
            $scope.searchText;
            $scope.Option;
            $scope.Country;
            $scope.name;
            $scope.vat;
            $scope.getInfo = function () {
                console.log("Searchtext " + $scope.searchText)
                console.log("Option " + $scope.Option)
                console.log("Country " + $scope.Country)
                var first = $scope.searchText;
                if ($scope.Option === "vat") {
                    first = $scope.searchText.substring(0,4);
                    first += "%20" + $scope.searchText.substring(4);
                    console.log(first);
                    }
                    $http.get('http://cvrapi.dk/api?' + $scope.Option + '=' + first + '&country=' + $scope.Country)
            .success(function (data, status, headers, config) {
                $scope.name = data.name;
        console.log(data.name);
                $scope.vat = data.vat;
              console.log(data);
            })
            .error(function (data, status, headers, config) {
              console.log("Error " + data);
             });
//                $http({
//                    url: 'http://cvrapi.dk/api?' + $scope.Option + '=' + first + '&country=' + $scope.Country,
//                    skipAuthorization: true,
//                    method: 'GET',
//                    succes: function (data) {
//                        console.log("Succes " + data);
//                    },
//                    error: function (data) {
//                        console.log("Error " + data);
//                    }
//                });
            };
        });