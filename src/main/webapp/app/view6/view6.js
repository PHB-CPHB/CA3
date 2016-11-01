'use strict';

angular.module('myApp.view6', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view6', {
                    templateUrl: 'app/view6/view6.html',
                    controller: 'View6Ctrl'
                });
            }])
        .controller("View6Ctrl", ["$http", "$scope", function ($http, $scope) {

                $scope.newUser = {
                    userName: '',
                    passwordHash: ''
                };

                $scope.createUser = function () {
                    
                    $http.post('api/demoall/user', $scope.newUser)
                            .success(function (data, status, headers, config) {
                                console.log("Works");
                            })
                            .error(function (data, status, headers, config) {

                            });
                }
            }])