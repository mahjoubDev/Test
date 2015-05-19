'use strict';

angular.module('jhipsterApp')
    .factory('Resource', function ($resource) {
        return $resource('http://localhost:9080/gestion-reservations-resources/proxym/resource/:reference/:action',
            {}, {
                'findAll': {
                    method: 'GET',
                    isArray: true,
                    params: {
                        action: 'getList'

                    }
                },
                'add': {
                    method: 'POST',
                    params: {
                        action: 'add'
                    },
                    headers: {
                        'Content-Type': 'application/json'
                    }
                },
                'update': {
                    method: 'POST',
                    isArray: true,
                    params: {
                        reference: '@reference',
                        action: 'update'
                    },
                    headers: {
                        'Content-Type': 'application/json'
                    }

                },
                'delete': {
                    method: 'POST',
                    isArray: true,
                    params: {
                        reference: '@reference',
                        action: 'delete'
                    }
                },
                'changeLevel': {method: 'PUT'}
            });
    });
