'use strict';

angular.module('jhipsterApp')
    .factory('Reservation', function ($resource) {
        return $resource('http://localhost:9080/gestion-reservations-resources/proxym/reservation/:referenceReservation/:referenceResource/:action',
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
                        referenceReservation: '@referenceReservation',
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
                        referenceReservation: '@referenceReservation',
                        action: 'delete'
                    }
                },
                'changeLevel': {method: 'PUT'}
            });
    });
