'use strict';

angular.module('jhipsterApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('resource', {
                parent: 'site',
                url: '/resource',
                data: {
                    roles: [],
                    pageTitle: 'resource.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/resource/resource.html',
                        controller: 'ResourceController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('resource');
                        return $translate.refresh();
                    }]
                }
            });
    });
