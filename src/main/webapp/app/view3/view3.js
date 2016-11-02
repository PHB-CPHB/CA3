'use strict';

var app = angular.module('myApp.view3', ['ngRoute']);

app.config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/view3', {
            templateUrl: 'app/view3/view3.html',
            controller: 'View3Ctrl'
        });
    }]);

app.controller('View3Ctrl', ['$http', '$scope', '$mdDialog', function ($http, $scope, $mdDialog) {
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
            var first = $scope.searchText;
            if ($scope.Option.selectedOption.value === "vat") {
                first = $scope.searchText.substring(0, 4);
                first += "%20" + $scope.searchText.substring(4);
            }
            $http.get('http://cvrapi.dk/api?' + $scope.Option.selectedOption.value + '=' + first + '&country=' + $scope.Country.selectedOption.value, {
                headers: {
                    'User-Agent': 'CVR API-CA3 CPH-Business Exercise-Phillip-cph-pb115@cphbusiness.dk'
                },
                skipAuthorization: true
            })
                    .success(function (data, status, headers, config) {
                        if (data.error) {
                            alert("Error: " + data.message);
                        }
                        $scope.dataReady = true;
                        $scope.info = data;
                        console.log(data);

                    })
                    .error(function (data, status, headers, config) {
                        console.log("Error " + data);
                    });
        };
        $scope.getSpecifikCompany = function (id) {
            for (var i = 0; i < $scope.info.productionunits.length(); i++) {
                if ($scope.info.productionunits.name === id) {
                        $mdDialog.show({
                            controller: DialogController,
                            templateUrl: 'dialog1.tmpl.html',
                            parent: angular.element(document.body),
                            targetEvent: ev,
                            clickOutsideToClose: true,
                            fullscreen: $scope.customFullscreen // Only for -xs, -sm breakpoints.
                        })
                                .then(function (answer) {
                                    $scope.status = 'You said the information was "' + answer + '".';
                                }, function () {
                                    $scope.status = 'You cancelled the dialog.';
                                });
                } else {

                }
            }
        };
//    $scope.info;
//    $scope.getInfo = function(){ 
//        companyInfoFactory.getInfo($scope.searchText, $scope.Country, $scope.Option).then(function(data){
//          $scope.info = data;  
//        });
//        
//    };
    }]);

app.directive('modalDirective', function () {
    return {
        templateUrl: 'modal.html'
    };
});

app.factory('companyInfoFactory', ['$http', function ($http) {
        var getInfo = function (searchText, Country, Option) {
            var first = searchText;
            console.log(Country.selectedOption.value);
            console.log(Option.selectedOption.value);
            if (Option.selectedOption.value === "vat") {
                first = searchText.substring(0, 4);
                first += "%20" + searchText.substring(4);
            }
            return $http.get('http://cvrapi.dk/api?' + Option.selectedOption.value + '=' + first + '&country=' + Country.selectedOption.value)
                    .success(function (data, status, headers, config) {
                        if (data.error) {
                            alert("Error: " + data.message);
                        }
                        return data;

                    })
                    .error(function (data, status, headers, config) {
                        return console.log("Error " + data);
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
        return {
            getInfo: getInfo
        };
    }]);

app.directive("vat", function () {
    return {
        templateUrl: "directive/oneFirm.html"
    };
});
