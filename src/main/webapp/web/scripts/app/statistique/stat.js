'use strict';

angular.module('jhipsterApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('statistique', {
                parent: 'site',
                url: '/statistique',
                data: {
                    roles: [],
                    pageTitle: 'statistique.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/statistique/stat.html',
                        controller: 'StatController'
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
