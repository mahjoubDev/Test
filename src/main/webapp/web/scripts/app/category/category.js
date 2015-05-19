'use strict';

angular.module('jhipsterApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('category', {
                parent: 'site',
                url: '/category',
                data: {
                    roles: [],
                    pageTitle: 'category.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/category/category.html',
                        controller: 'CategoryController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('category');
                        return $translate.refresh();
                    }]
                }
            });
    });
