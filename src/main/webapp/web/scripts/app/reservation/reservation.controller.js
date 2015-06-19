'use strict';

angular.module('jhipsterApp')
    .controller('ReservationController', function ($scope, $http, Category, Resource, Reservation, Users) {
        $scope.categories = Category.findAll();
        $scope.resources = Resource.findAll();
        $scope.resourcesByCategory = [];
        $scope.resourcesByCategoryForAdd = [];
        $scope.reservations = Reservation.findAll();
        $scope.showResourceRelatedToCtagory = false;
        $scope.eventsResourceRelatedToResource = false;
        $scope.toggle = '!toggle';
        $scope.events = [];
        $scope.eventSources = [];
        $scope.event = {};
        $scope.account = {};
        $scope.reservationInfo = {};
        $scope.categoryForResourceAdd = {} ;
        $scope.addReservationError = false ;
        $scope.error = {} ;
        Users.getCurrentUser(function (response) {
            $scope.account = response;

        });
        $('#resourceReservation').chosen() ;
        $('#categoryReservation').chosen() ;
        $scope.checkForUpdate = false ;
        $scope.showModalError = false;
        $scope.showModalDelete = false;




        /**
         *
         */
        $scope.add = function (reservationInfo) {
            console.log('call add reservation')

            reservationInfo.loginUser = $scope.account.login ;
            if (angular.isDefined(reservationInfo.reference) && angular.isDefined(reservationInfo.dateStart)
                && angular.isDefined(reservationInfo.description) && angular.isDefined($scope.categoryForResourceAdd)
                && angular.isDefined(reservationInfo.dateEnd) && angular.isDefined(reservationInfo.referenceResource)) {
                var promise = Reservation.add({}, reservationInfo).$promise;
                promise.then(function (data) {

                        console.log('reservation has been added successfully')
                        $scope.reservations = Reservation.findAll();
                        $scope.reservationInfo = {};
                        $scope.addReservationError = false;
                        $scope.error = {};
                        $scope.showModalSucess = !$scope.showModalSucess;


                    }, function (error) {
                        $scope.addReservationError = true;
                        $scope.showModalError = true;
                        $scope.error = error;
                        console.log("there is an error " + JSON.stringify(error));
                    }
                );
                $scope.checkForUpdate = false;
            }
            else {
                $scope.checkForUpdate = true;
            }
        };

        /**
         *
         * @param reference
         */
        $scope.delete = function (reservationInfo) {

            console.log("call delete rservation web services");
            var promise = Reservation.delete({id: reservationInfo.id}).$promise;
            promise.then(function (data) {
                console.log("the reservation has been deleted succesfully");
                $scope.getReservationsByReferenceResources(reservationInfo.referenceResource);
                $scope.showModalDelete = !$scope.showModalDelete;
                $scope.categoryForResourceAdd = {};
                $scope.resourcesByCategoryForAdd = [];

            }, function (error) {
                $scope.error = error ;
                console.log("there is an error " + JSON.stringify(error));
                chouf
            });


        };

        /**
         *
         * @param reference
         */
        $scope.update = function (reservationInfo) {
            console.log(JSON.stringify(reservationInfo)) ;
            if (angular.isDefined(reservationInfo)) {
                var promise = Reservation.update({id: reservationInfo.id}, reservationInfo).$promise;
                promise.then(function (data) {
                    console.log("the reservation has been updatedd succesfully");
                    $scope.reservations = Reservation.findAll();
                    $scope.reservationInfo = {};
                    $scope.getReservationsByReferenceResources(reservationInfo.referenceResource);
                    $scope.showModalUpdate = !$scope.showModalUpdate;
                    $scope.addReservationError = false ;
                    $scope.error = {};
                    $scope.showModalSucess = !$scope.showModalSucess;

                }, function (error) {
                    $scope.addReservationError = true;
                    $scope.error = error ;
                    $scope.showModalUpdate = !$scope.showModalUpdate;
                    console.log("there is an error " + JSON.stringify(error));
                });

            }

        };


        /**
         *
         */
        $scope.toggleModalAdd = function () {
            $scope.showModalAdd = !$scope.showModalAdd;
        };
        /**
         *
         */
        $scope.toggleModalUpdate = function () {
            $scope.showModalUpdate = !$scope.showModalUpdate;
        };
        /**
         *
         */
        $scope.toggleModalDelete = function (reservationInfo) {
            $scope.selectedReservationInfo = reservationInfo;
            $scope.showModalDelete = !$scope.showModalDelete;
            $scope.showModalUpdate = !$scope.showModalUpdate;
        };


        /**
         *
         * @param referenceCategory
         */
        $scope.getResourceByReferenceCategory = function (referenceCategory) {
            var resources = [];
            for (var i = 0; i < $scope.resources.length; i++) {
                if (referenceCategory === $scope.resources[i].referenceCategory) {

                    resources.push($scope.resources[i]);
                }
            }
            if (resources.length !== 0) {
                $scope.resourcesByCategory = resources;
                $scope.showResourceRelatedToCtagory = true;
            }
            else {
                $scope.resourcesByCategory = [];
                $scope.showResourceRelatedToCtagory = false;
            }

            console.log("the cat is "+$scope.resourcesByCategory.length) ;


        };


        /**
         *
         */
        var getResourceByReference = function (refrence) {

            for (var i = 0; i < $scope.resources.length; i++) {
                if ($scope.resources[i].reference === refrence) {

                    return $scope.resources[i];
                }
            }

        };

        /**
         *
         * @param referenceResource
         */
        $scope.getReservationsByReferenceResources = function (referenceResource) {
            console.log('call method get reservation by reference resource');
            $scope.events = [];
            //  console.log('the sue '+Principal.isInRole('users')) ;
            var promise = Reservation.findByResource({referenceResource: referenceResource}).$promise;
            promise.then(function (data) {
                    $scope.reservationForResource = data;
                    $scope.eventsResourceRelatedToResource = true;
                    if (data.length !== 0) {
                        var reservs = data;
                        for (var i = 0; i < reservs.length; i++) {
                            var res = reservs[i];
                            var dateStart = new Date(res.dateStart);
                            var dateEnd = new Date(res.dateEnd);
                            event = {
                                //format("Y-m-d h:i:s"),
                                id: res.id,
                                text: getResourceByReference(res.referenceResource).name,
                                start_date: new Date(dateStart.getFullYear(), dateStart.getMonth(), dateStart.getDate(), dateStart.getHours(),
                                    dateStart.getMinutes(), dateStart.getSeconds()),
                                end_date: new Date(dateEnd.getFullYear(), dateEnd.getMonth(), dateEnd.getDate(), dateEnd.getHours(),
                                    dateEnd.getMinutes(), dateEnd.getSeconds()),
                                login: res.loginUser,
                                resource: getResourceByReference(res.referenceResource),
                                refResource : res.referenceResource,
                                reference: res.reference ,
                                description :res.description

                            }
                            $scope.events.push(event);

                        }
                    }


                }, function (error) {
                    console.log("there is an error " + error);
                }
            );

        }


        /**
         * check if the current user is admin.
         */
       var isAdmin = function () {
            var roles = $scope.account.roles;
            if (angular.isDefined(roles)) {
                for (var i = 0; i < roles.length; i++) {
                    if ( (roles[i].indexOf("Admins") > -1) || (roles[i].indexOf("admin") > -1) ) {
                        return true;
                    }
                }
                return false;
            }


        };

        /**
         * check if the current user is the creator of the event.
         */
        var isUserOwner = function () {
            var userName = $scope.account.login;
            var eventUser = $scope.reservationInfo.loginUser;
            if (angular.isDefined(userName) && angular.isDefined(eventUser)) {
                if (eventUser.indexOf(userName) > -1) {
                    return true;
                }
                else {
                    return false;
                }
            }
            else {
                return false;
            }
        };
        /**
         *
         * @returns {*}
         */
        $scope.testForRoles = function () {
            return !(isAdmin() || isUserOwner () ) ;
        }

        $scope.setCatForResource =function () {
            console.log("the cat is "+$scope.categoryForResourceAdd) ;

        }

        /**
         * watching the selected value for categori before
         * getting the list of resources related to this one.
         *
         */
        $scope.$watch(
            "categoryForResourceAdd",
            function(newValue, oldValue ) {
                var resources = [];
                for (var i = 0; i < $scope.resources.length; i++) {
                    if (newValue === $scope.resources[i].referenceCategory) {
                        resources.push($scope.resources[i]);
                    }
                }
                if (resources.length !== 0) {
                    $scope.resourcesByCategoryForAdd = resources;
                }
                else {
                    $scope.resourcesByCategoryForAdd = [];
                }
            }
        );

        /**
         *
         */
        $scope.initialize = function () {
            console.log("call initialize")
            $scope.reservationInfo = {};
        };

        //======================================================================================================
        //-----------------------------------    Calendar  ------------------------------------------------------
        //=======================================================================================================


    });


angular.module('jhipsterApp').directive('dhxScheduler', function () {
    return {
        restrict: 'A',
        scope: false,
        transclude: true,
        template: '<div class="dhx_cal_navline"' +
        ' ng-transclude></div><div class="dhx_cal_header"></div><div class="dhx_cal_data"></div>',


        link: function ($scope, $element, $attrs, $controller, Principal) {
            var isAuthenticated = Principal.isAuthenticated
            if (!$scope.scheduler)
                $scope.scheduler = {};
            $scope.scheduler.mode = $scope.scheduler.mode || "week";
            $scope.scheduler.date = $scope.scheduler.date || new Date();

            scheduler.attachEvent("onDblClick", function (id, e) {
                console.log('double clik ');
                var ev = scheduler.getEvent(id);
                    $scope.event = ev;
                    $scope.reservationInfo.dateStart = ev.start_date.format("Y-m-d H:i:s");
                    $scope.reservationInfo.dateEnd = ev.end_date.format("Y-m-d H:i:s");
                    $scope.reservationInfo.reference = ev.reference;
                    $scope.reservationInfo.id = ev.id;
                    $scope.reservationInfo.referenceResource = ev.refResource;
                    $scope.reservationInfo.loginUser = ev.login;
                    $scope.reservationInfo.description = ev.description;
                    $scope.$apply();
                    $scope.$apply(" showModalUpdate = !showModalUpdate");
                return false;
            });

            scheduler.attachEvent("onClick", function (id, e){
                return false;
            });

            var dragged_event;
            scheduler.attachEvent("onBeforeDrag", function (id, mode, e){
                return false;
            });

            scheduler.attachEvent("onDragEnd", function(){
                var event_obj = dragged_event;
                $scope.$apply(" showModalUpdate = !showModalUpdate");

            });


            scheduler.showLightbox = function (id) {
                var ev = scheduler.getEvent(id);
            };


            //watch data collection, reload on changes
            $scope.$watch($attrs.data, function (collection) {
                scheduler.clearAll();
                scheduler.parse(collection, "json");
            }, true);

            //mode or date
            $scope.$watch(function () {
                return $scope.scheduler.mode + $scope.scheduler.date.toString();
            }, function (nv, ov) {
                var mode = scheduler.getState();
                if (nv.date != mode.date || nv.mode != mode.mode)
                    scheduler.setCurrentView($scope.scheduler.date, $scope.scheduler.mode);
            }, true);

            //size of scheduler
            $scope.$watch(function () {
                return $element[0].offsetWidth + "." + $element[0].offsetHeight;
            }, function () {
                scheduler.setCurrentView();
            });

            //styling for dhtmlx scheduler
            $element.addClass("dhx_cal_container");
            //init scheduler
            scheduler.config.dblclick_create = false;
            scheduler.config.drag_create = false;
            scheduler.config.drag_move = true;
            scheduler.config.readonly = false;
            scheduler.config.touch = true;
            scheduler.config.drag_out = false ;
            scheduler.config.drag_in = false ;
            scheduler.config.hour_date = "%H:%i:%s";

            scheduler.templates.event_class = function (start, end, event) {
                if (event.type == 'manager') return "manager_event";
                return "employee_event";
            };


            //init scheduler
            scheduler.init($element[0], $scope.scheduler.mode, $scope.scheduler.date);


        }

    }


});

angular.module('jhipsterApp').directive('dhxTemplate', ['$filter', function ($filter) {
    scheduler.aFilter = $filter;

    return {
        restrict: 'AE',
        terminal: true,

        link: function ($scope, $element, $attrs, $controller) {
            $element[0].style.display = 'none';

            var template = $element[0].innerHTML;
            template = template.replace(/[\r\n]/g, "").replace(/"/g, "\\\"").replace(/\{\{event\.([^\}]+)\}\}/g, function (match, prop) {
                if (prop.indexOf("|") != -1) {
                    var parts = prop.split("|");
                    return "\"+scheduler.aFilter('" + (parts[1]).trim() + "')(event." + (parts[0]).trim() + ")+\"";
                }
                return '"+event.' + prop + '+"';
            });
            var templateFunc = Function('sd', 'ed', 'event', 'return "' + template + '"');
            scheduler.templates[$attrs.dhxTemplate] = templateFunc;
        }
    };
}]);

angular.module('jhipsterApp').directive('jqdatepicker', function () {
    return {
        restrict: 'A',
        require: 'ngModel',
        link: function (scope, element, attrs, ngModelCtrl) {
            var updateModel = function (dateText) {
                scope.$apply(function () {
                    ngModelCtrl.$setViewValue(dateText);
                });
            };
            var options = {
                dateFormat: "yy-mm-dd 00:00:00",
                onSelect: function (dateText) {
                    updateModel(dateText);
                }
            };
            element.datepicker(options);
        }
    };
});