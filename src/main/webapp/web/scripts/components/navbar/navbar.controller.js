'use strict';

angular.module('jhipsterApp')
    .controller('NavbarController', function ($scope, $location, $state, Auth, Principal,Users) {
        $scope.isAuthenticated = Principal.isAuthenticated;
        $scope.$state = $state;
        $scope.account = Users.getCurrentUser () ;
        $('[data-toggle=dropdown]').dropdown()

        /**
         * check if the current user is admin.
         */
        $scope.isAdmin = function () {
            var roles = $scope.account.roles;
            if (angular.isDefined(roles)) {
                for (var i = 0; i < roles.length; i++) {
                    if ( (roles[i].indexOf("Admins") > -1) || (roles[i].indexOf("admin") > -1) ) {
                        return true;
                    }
                }
                return false;
            }


        };

        $scope.logout = function () {
            Auth.logout();
            $state.go('reservation');
        };
    });
