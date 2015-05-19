'use strict';

angular.module('jhipsterApp')
    .factory('Users', function ($resource) {
        return $resource('http://localhost:9080/gestion-reservations-resources/proxym/user/account',
            {}, {
                'getCurrentUser': {
                    method: 'GET' ,
                    params: {},
                    isArray: false

                }
            });
    });
