'use strict';

angular.module('jhipsterApp')
    .controller('NavbarController', function ($scope, $location, $state,  Users) {
        $scope.$state = $state;
        $scope.account = Users.getCurrentUser () ;
        $('[data-toggle=dropdown]').dropdown()


    });
