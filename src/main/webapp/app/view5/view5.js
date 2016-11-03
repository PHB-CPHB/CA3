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
                $scope.getAllUsers = function () {
                    $http({
                        method: 'GET',
                        url: 'api/admin/users'
                    })
                            .success(function (response) {
                                $scope.allUsers = response;
                            }, function (error) {
                                $scope.error = error;
                            });
                }
                $scope.deleteUser = function (username) {
                    $http({
                        method: 'DELETE',
                        url: 'api/admin/user/' + username
                    })
                            .success(function (response) {
                                $scope.getAllUsers();
                            }, function (error) {
                                $scope.error = error;
                            });
                }
                
                $scope.getAllUsers();

            }])