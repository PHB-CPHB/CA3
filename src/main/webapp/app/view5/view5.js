'use strict';

angular.module('myApp.view5', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view5', {
                    templateUrl: 'app/view5/view5.html',
                    controller: 'adminController'
                });
            }])

        .controller('adminController', ['$scope', '$http', function ($scope, $http) {
                $scope.allUsers;
                $scope.error;
                $http({
                    method: 'GET',
                    url: 'api/admin/users'
                })
                        .success(function (response) {
                            $scope.allUsers = response;
                        }, function (error) {
                            $scope.error = error;
                        });

            }])