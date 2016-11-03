'use strict';

var app = angular.module('myApp.view3', ['ngRoute']);

app.config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/view3', {
            templateUrl: 'app/view3/view3.html',
            controller: 'View3Ctrl'
        });
    }]);

app.controller('View3Ctrl', ['$http', '$scope', 'companyInfoFactory', function ($http, $scope, companyInfoFactory) {
        $scope.Country = {
            availableOptions: [
                {id: '1', name: 'DK', value: 'dk'},
                {id: '2', name: 'NO', value: 'no'}
            ],
            selectedOption: {id: '1', name: 'DK', value: 'dk'}
        };

        $scope.Option = {
            availableOptions: [
                {id: '1', name: 'Normal', value: 'search'},
                {id: '2', name: 'VAT Number', value: 'vat'},
                {id: '3', name: 'Firm Name', value: 'name'},
                {id: '4', name: 'Production Unit', value: 'produ'},
                {id: '5', name: 'Phone Number', value: 'phone'}
            ],
            selectedOption: {id: '1', name: 'Normal', value: 'search'}
        };
        $scope.searchText;
        $scope.name;
        $scope.vat;
        $scope.info;

        $scope.getInfo = function () {
            companyInfoFactory.getInfo($scope.searchText, $scope.Country, $scope.Option).then(function (response) {
                $scope.dataReady = true;
                $scope.info = response.data;
            });
        };
    }]);


app.factory('companyInfoFactory', ['$http', function ($http) {
        var getInfo = function (searchText, Country, Option) {
            var first = searchText;
            if (Option.selectedOption.value === "vat") {
                first = searchText.substring(0, 4);
                first += "%20" + searchText.substring(4);
            }
            return $http({
                url: 'http://cvrapi.dk/api?' + Option.selectedOption.value + '=' + first + '&country=' + Country.selectedOption.value,
                skipAuthorization: true,
                method: 'GET'
            })
                    .success(function (data, status, headers, config) {
                        console.log(data);
                        if (data.error) {
                            alert("Error: " + data.message);
                        }
                        return data;

                    })
                    .error(function (data, status, headers, config) {
                        return console.log("Error " + data);
                    });
        };
        return {
            getInfo: getInfo
        };
    }]);

