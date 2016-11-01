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

            $scope.getInfo = function () {
                $http({
                    url: 'http://cvrapi.dk/api' + $scope.Option + '=' + $scope.searchText + '&country=' + $scope.Country,
                    skipAuthorization: true,
                    method: 'GET'
                })
            }
        });