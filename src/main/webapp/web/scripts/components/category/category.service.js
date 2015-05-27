'use strict';

angular.module('jhipsterApp')
    .factory('Category', function ($resource) {
        return $resource('http://localhost:8080/proxym/category/:referenceCategory/:action',
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
                    referenceCategory: '@referenceCategory',
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
                    referenceCategory: '@referenceCategory',
                    action: 'delete'
                }
            },
            'changeLevel': {method: 'PUT'}
        });
    });
