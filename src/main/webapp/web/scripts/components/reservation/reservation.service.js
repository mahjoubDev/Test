'use strict';

angular.module('jhipsterApp')
    .factory('Reservation', function ($resource) {
        return $resource('http://localhost:8080/proxym/reservation/:id/:referenceResource/:action',
            {}, {
                'findAll': {
                    method: 'GET',
                    isArray: true,
                    params: {
                        action: 'getList'

                    }
                },
                'findByResource': {
                    method: 'GET',
                    isArray: true,
                    params: {
                        referenceResource: '@referenceResource',
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
                        id: '@id',
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
                        id: '@id',
                        action: 'delete'
                    }
                },
                'changeLevel': {method: 'PUT'}
            });
    });
