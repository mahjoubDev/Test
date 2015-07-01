'use strict';

angular.module('jhipsterApp')
    .controller('CategoryController', function ($scope, $http, Category,$window) {
        $scope.showModalUpdate = false;
        $scope.showModalAdd = false;
        $scope.categorieInfo = {};
        $scope.itemsByPage = 5;
        $scope.toggle = '!toggle';
        $scope.addCategoryError = false;
        $scope.showModalDelete = false;
        $scope.checkForUpdate = false ;
        /**
         *
         */
        Category.findAll({}, function (response) {
            console.log('categorie ' + JSON.stringify(response));
            $scope.categories = response;
        });

        $scope.deleteItem = function (item) {
            var msgbox = $dialog.messageBox('Delete Item', 'Are you sure?', [{
                label: 'Yes, I\'m sure',
                result: 'yes'
            }, {label: 'Nope', result: 'no'}]);
            msgbox.open().then(function (result) {
                if (result === 'yes') {
                    //code to delete here
                    console.log("deleting item " + item.name);
                }
            });
        };

        /**
         *
         */
        $scope.add = function (categorieInfo) {
            console.log('call method add category   ' + JSON.stringify(categorieInfo));

            if (angular.isDefined(categorieInfo.nameCategorie) &&angular.isDefined(categorieInfo.reference)) {
            var promise = Category.add({}, categorieInfo).$promise;
            promise.then(function (data) {
                    console.log('category has been added successfully')
                    $scope.categories = Category.findAll();
                    $scope.categorieInfo = {};
                    $scope.showModalSucess = !$scope.showModalSucess;
                    $window.location.reload();

                }, function (error) {
                    $scope.addCategoryError = true;
                    $scope.error = error;
                    $scope.showModalError = !$scope.showModalError;
                    console.log("there is an error " + error);
                }
            );
                $scope.checkForUpdate = false;
        }
            else {
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
            var promise= Category.delete({referenceCategory: $scope.selectedCategory.reference});
            $scope.showModalDelete = !$scope.showModalDelete;
            $scope.categories = Category.findAll();
            $window.location.reload();


        };

        /**
         * Update the selected category using it's reference and categoiry info to hold
         * new content
         *
         * @param category  category object selected by the user.
         */
        $scope.update = function (category) {
            console.log('call method update category');
            if (angular.isDefined($scope.selectedCategory)) {
                var promise = Category.update({referenceCategory: $scope.selectedCategory.reference},
                    {
                        "nameCategorie": $scope.selectedCategory.nameCategorie,
                        "reference": $scope.selectedCategory.reference
                    }).$promise;

                promise.then(function (data) {
                    console.log("category updated successfully")
                    $scope.categories = Category.findAll();
                    $window.location.reload();
                }, function (error) {
                    console.log("there is an error in update  " + error);
                });

                $scope.showModalUpdate = !$scope.showModalUpdate;
                $scope.showModalSucess = !$scope.showModalSucess;
            }
            else {
                console.log('the category is not selected there is an erro e cannot update !!!')
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
        $scope.toggleModalUpdate = function (category) {
            $scope.selectedCategory = category;
            $scope.showModalUpdate = !$scope.showModalUpdate;
        };

        /**
         *
         * @param category
         */
        $scope.toggleModalDelete = function (category) {
            $scope.selectedCategory = category;
            $scope.showModalDelete = !$scope.showModalDelete;
        };

        $scope.cancel = function () {
            $scope.showModalUpdate = false;
        };

        /**
         *
         */
        $scope.initialize = function () {
            $scope.categorieInfo = {};
            $scope.checkForUpdate = false;
        };


    });


angular.module('jhipsterApp').directive('modal', function () {
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

