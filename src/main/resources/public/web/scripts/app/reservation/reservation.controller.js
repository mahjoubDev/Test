'use strict';

angular.module('jhipsterApp')
    .controller('ReservationController', function ($scope, $http, Category, Resource, Reservation, Users, Principal) {
        $scope.categories = Category.findAll();
        $scope.resources = Resource.findAll();
        $scope.resourcesByCategory = [];
        $scope.reservations = Reservation.findAll();
        $scope.showResourceRelatedToCtagory = false;
        $scope.eventsResourceRelatedToResource = false;
        $scope.toggle = '!toggle';
        $scope.events = [];
        $scope.eventSources = [];
        $scope.event = {};
        $scope.account = {};
        $scope.reservationInfo = {};
        Users.getCurrentUser(function (response) {
            $scope.account = response;

        });


        /**
         *
         */
        $scope.add = function (reservationInfo) {

            var promise = Reservation.add({}, reservationInfo).$promise;
            promise.then(function (data) {

                    console.log('reservation has been added successfully')
                    $scope.reservations = Reservation.findAll();
                    $scope.showModalAdd = !$scope.showModalAdd;
                    $scope.reservationInfo = {};


                }, function (error) {
                    console.log("there is an error " + JSON.stringify(error));
                }
            );
        };

        /**
         *
         * @param reference
         */
        $scope.delete = function (reservationInfo) {

            console.log("call delete rservation web services");
            var promise = Reservation.delete({referenceReservation: reservationInfo.reference}).$promise;
            promise.then(function (data) {
                console.log("the reservation has been deleted succesfully");
                $scope.getReservationsByReferenceResources(reservationInfo.referenceResource);
                $scope.showModalDelete = !$scope.showModalDelete;

            }, function (error) {
                console.log("there is an error " + JSON.stringify(error));
                chouf
            });


        };

        /**
         *
         * @param reference
         */
        $scope.update = function (reservationInfo) {

            if (angular.isDefined(reservationInfo)) {
                var promise = Reservation.update({referenceReservation: reservationInfo.reference}, reservationInfo).$promise;
                promise.then(function (data) {
                    console.log("the reservation has been updatedd succesfully");
                    $scope.reservations = Reservation.findAll();
                    $scope.reservationInfo = {};
                    $scope.getReservationsByReferenceResources(reservationInfo.referenceResource);
                    $scope.showModalUpdate = !$scope.showModalUpdate;

                }, function (error) {
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
        $scope.toggleModalDelete = function () {
            $scope.showModalUpdate = !$scope.showModalUpdate;
            $scope.showModalDelete = !$scope.showModalDelete;
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


        };


        /**
         *
         * @param referenceResource
         */
        $scope.getReservationsByReferenceResources = function (referenceResource) {
            console.log('call method get reservation by reference resource');
            $scope.eventsResourceRelatedToResource = false;
            $scope.events = [];
            //  console.log('the sue '+Principal.isInRole('users')) ;
            var promise = Reservation.findByResource({referenceResource: referenceResource}).$promise;
            promise.then(function (data) {
                    $scope.reservationForResource = data;
                    if (data.length !== 0) {
                        for (var i = 0; i < $scope.reservations.length; i++) {
                            var res = $scope.reservations[i];
                            var dateStart = new Date(res.dateStart);
                            var dateEnd = new Date(res.dateEnd);
                            event = {
                                //format("Y-m-d h:i:s"),
                                id: res.id,
                                text: res.description,
                                start_date: new Date(dateStart.getFullYear(), dateStart.getMonth(), dateStart.getDate(), dateStart.getHours(),
                                    dateStart.getMinutes(), dateStart.getSeconds()),
                                end_date: new Date(dateEnd.getFullYear(), dateEnd.getMonth(), dateEnd.getDate(), dateEnd.getHours(),
                                    dateEnd.getMinutes(), dateEnd.getSeconds()),
                                login: res.loginUser,
                                resource: res.referenceResource,
                                reference: res.reference

                            }
                            $scope.events.push(event);

                        }
                        $scope.eventsResourceRelatedToResource = true;
                    }


                }, function (error) {
                    console.log("there is an error " + error);
                }
            );

        }

        /**
         * check if the curent user is admin.
         */
        $scope.isAdmin = function () {
            var roles = $scope.account.roles;
            if (angular.isDefined(roles)) {
                for (var i = 0; i < roles.length; i++) {
                    if (roles[i].indexOf("Admins") > -1) {
                        return true;
                    }
                }
                return false;
            }


        };

        /**
         * check if the curent user is the creator of the event.
         */
        $scope.isUserOwner = function () {
            var userName = $scope.account.lastName;
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


        //======================================================================================================
        //-----------------------------------    Calendar  ------------------------------------------------------
        //=======================================================================================================

        /*    $scope.events = [
         {
         id: 1,
         text: "Reservation sale reunion",
         start_date: new Date(2015, 4, 11, 9, 0, 0),
         end_date: new Date(2015, 4, 12, 11, 0, 0),
         login: "mahjoub",
         resource: "resource num 1",
         reference: "0000001"
         },
         {
         id: 2, text: "Reservation tablette",
         start_date: new Date(2015, 4, 22),
         end_date: new Date(2015, 4, 24),
         login: "nesrine",
         resource: "resource num 1",
         reference: "0000002"
         },

         {
         id: 3, text: "Reservation pc",
         start_date: new Date(2015, 4, 30),
         end_date: new Date(2015, 5, 4),
         login: "achref",
         resource: "resource num 1",
         reference: "0000003"
         }
         ];*/

        $scope.scheduler = {date: new Date()};


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
            $scope.scheduler.mode = $scope.scheduler.mode || "month";
            $scope.scheduler.date = $scope.scheduler.date || new Date();

            scheduler.attachEvent("onDblClick", function (id, e) {
                console.log('double clik ') ;
                var ev = scheduler.getEvent(id);
                if (!isAuthenticated) {
                    $scope.event = ev;
                    $scope.reservationInfo.dateStart = ev.start_date.format("Y-m-d h:i:s");
                    $scope.reservationInfo.dateEnd = ev.end_date.format("Y-m-d h:i:s");
                    $scope.reservationInfo.reference = ev.reference;
                    $scope.reservationInfo.id = ev.id;
                    $scope.reservationInfo.referenceResource = ev.resource;
                    $scope.reservationInfo.loginUser = ev.login;
                    $scope.reservationInfo.description = ev.text;
                    $scope.$apply();
                    $scope.$apply(" showModalUpdate = !showModalUpdate");
                }

                return true;
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
            scheduler.config.dblclick_create = true;
            scheduler.config.drag_create = false;
            scheduler.config.drag_move = true;
            scheduler.config.readonly = false;
            scheduler.config.touch = true;
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