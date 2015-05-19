'use strict';

angular.module('jhipsterApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('reservation', {
                parent: 'site',
                url: '/reservation',
                data: {
                    roles: [],
                    pageTitle: 'reservation.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/reservation/reservation.html',
                        controller: 'ReservationController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('reservation');
                        return $translate.refresh();
                    }]
                }
            });
    });
