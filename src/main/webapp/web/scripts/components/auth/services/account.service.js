'use strict';

angular.module('jhipsterApp')
    .factory('Account', function Account($resource) {
        return $resource('http://localhost:9080/gestion-reservations-resources/proxym/user/account', {}, {
            'get': { method: 'GET', params: {}, isArray: false,
                interceptor: {
                    response: function(response) {
                        // expose response
                        return response;
                    }
                }
            }
        });
    });
