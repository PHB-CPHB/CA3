'use strict';

angular.module('myApp.view4', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view4', {
                    templateUrl: 'app/view4/view4.html',
                    controller: 'exchangeRateData'
                });
            }])
        .controller("exchangeRateData", ['$http', '$scope', function ($http, $scope) {

                $scope.data = {};
                $scope.getData = function () {
                    $http.get('http://localhost:8084/seedMaven/api/currency/dailyrates')
                            .success(function (data, status, headers, config) {
                                console.log(data);
                                $scope.data = data;
                            })
                            .error(function (data, status, headers, config) {

                            });
                };
                $scope.getData();
            }])