'use strict';

angular.module('jhipsterApp')
    .controller('ResourceController', function ($scope, $http, Category, Resource,  $window) {
        $scope.showModalUpdate = false;
        $scope.showModalAdd = false;
        $scope.resourceInfo = {};
        $scope.itemsByPage = 3;
        $scope.toggle = '!toggle';
        $scope.addResourceError = false ;
        $scope.category = {} ;
        $scope.typeDates = ['HOUR','DAY','WEEK'] ;
        $("#typeDateAdd").chosen() ;
        $("#categoryAdd").chosen() ;
        $scope.checkForUpdate = false ;
        $scope.showModalDelete = false;

        /**
         *
         */
        Category.findAll({}, function (response) {
            console.log('categorie ' + JSON.stringify(response));
            $scope.categories = response;
        });

        /**
         *
         */
        Resource.findAll({},function (response){
            console.log('resource '+JSON.stringify(response));
            $scope.resources = response;
        });

        /**
         *
         * @param reference
         * @returns {*}
         */
        $scope.findCategory = function (reference){
            var categoryList = $scope.categories ;
            if(angular.isDefined(categoryList)){
            for (var i = 0; i < categoryList.length; i++) {
                if (categoryList[i].reference === reference) {
                    return categoryList[i].nameCategorie;
                }
            }
            }
        };

        /**
         *
         */
        $scope.add = function (resourceInfo) {
            console.log('call method add resource');
            if (angular.isDefined(resourceInfo.reference) && angular.isDefined(resourceInfo.name)
            && angular.isDefined(resourceInfo.description) && angular.isDefined(resourceInfo.referenceCategory)
            && angular.isDefined(resourceInfo.typeDate) && angular.isDefined(resourceInfo.dureeMax)) {
                var promise = Resource.add({}, resourceInfo).$promise;
                promise.then(function (data) {
                        console.log('resource has been added successfully')
                        $scope.resources = Resource.findAll();                    // $route.reload();
                        $scope.resourceInfo = {};
                        $scope.showModalSucess = !$scope.showModalSucess;
                        $window.location.reload();

                    }, function (error) {
                        $scope.addResourceError = true;
                        $scope.error = error;
                        $scope.showModalError = !$scope.showModalError;
                        console.log("there is an error " + error);
                    }
                );
                $scope.checkForUpdate = false;
            }else {
                $scope.checkForUpdate = true;
            }
        };

        /**
         * Delete the selected category using it's reference
         *
         * @param category  categgory object selected by the user.
         */
        $scope.delete = function () {
            console.log('call method delete catgory');
            Resource.delete({reference: $scope.selectedResource.reference});
            $scope.showModalDelete = !$scope.showModalDelete;
            $scope.resources = Resource.findAll () ;
            $window.location.reload();
            console.log("resource deleted successfully") ;

        };

        /**
         * Update the selected category using it's reference and categoiry info to hold
         * new content
         *
         * @param category  category object selected by the user.
         */
        $scope.update = function () {
            console.log('call method update resource');
            if (angular.isDefined($scope.selectedResource)) {
                var promise = Resource.update({reference: $scope.selectedResource.reference},$scope.selectedResource).$promise;

                promise.then(function (data) {
                    console.log("resource updated successfully");
                    $scope.resourceInfo = Resource.findAll();
                    $window.location.reload();

                }, function (error) {
                    console.log("there is an error in update resource   " + JSON.stringify(error));
                });

                $scope.showModalUpdate = !$scope.showModalUpdate;
                $scope.showModalSucess = !$scope.showModalSucess;
            }
            else {
                console.log('the resource  is not selected there is an erro e cannot update !!!')
            }

        };

        /**
         *
         */
        $scope.toggleModalAdd = function () {
            $scope.showModalAdd = !$scope.showModalA;
            scope: $scope;
        };

        /**
         *
         * @param category
         */
        $scope.toggleModalUpdate = function (resource) {
            $scope.selectedResource = resource;
            $scope.showModalUpdate = !$scope.showModalUpdate;
        };

        /**
         *
         * @param category
         */
        $scope.toggleModalDelete = function (resource) {
            $scope.selectedResource = resource;
            $scope.showModalDelete = !$scope.showModalDelete;
        };

        $scope.cancel = function () {
            $scope.showModalUpdate = false;
        };


        $scope.initialize = function () {
            $scope.reservationInfo = {};
        };

        /**
         *
         */
        $scope.initialize = function () {
            $scope.resourceInfo = {};
            $scope.checkForUpdate = false;
        };

    });


angular.module('jhipsterApp').directive('modal2', function () {
    return {
        template: '<div class="modal fade">' +
        '<div class="modal-dialog">' +
        '<div class="modal-content">' +
        '<div class="modal-header">' +
        '<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>' +
        '<h4 class="modal-title">{{ title }}</h4>' +
        '</div>' +
        '<div class="modal-body" ng-transclude></div>' +
        '</div>' +
        '</div>' +
        '</div>',
        restrict: 'E',
        transclude: true,
        replace: true,
        scope: true,
        link: function postLink(scope, element, attrs) {
            scope.title = attrs.title;

            scope.$watch(attrs.visible, function (value) {
                if (value == true)
                    $(element).modal('show');
                else
                    $(element).modal('hide');
            });

            $(element).on('shown.bs.modal', function () {
                scope.$apply(function () {
                    scope.$parent[attrs.visible] = true;
                });
            });

            $(element).on('hidden.bs.modal', function () {
                scope.$apply(function () {
                    scope.$parent[attrs.visible] = false;
                });
            });
        }
    };
});

