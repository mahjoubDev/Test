'use strict';

angular.module('jhipsterApp')
    .controller('LanguageController', function ($scope, $translate, Language) {
        $scope.changeLanguage = function (languageKey) {
            console.log('translation '+languageKey) ;
            $translate.use(languageKey);
        };

        Language.getAll().then(function (languages) {
            $scope.languages = languages;
        });
    });
