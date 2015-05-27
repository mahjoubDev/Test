'use strict';

angular.module('jhipsterApp')
    .factory('Users', function ($resource) {
        return $resource('http://localhost:8080/proxym/user/account',
            {}, {
                'getCurrentUser': {
                    method: 'GET' ,
                    params: {},
                    isArray: false

                }
            });
    });
