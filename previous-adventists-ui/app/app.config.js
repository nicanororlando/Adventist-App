"use strict";

angular.module("app").config([
  "$locationProvider",
  "$routeProvider",
  function config($locationProvider, $routeProvider) {
    $locationProvider.html5Mode({
      enabled: true,
      requireBase: false,
    });
    $routeProvider
      // .when("/", {
      //   template: "<h5 class='text-center'>Hola gato</h5>",
      // })
      .when("/", {
        template: "<beliefs></beliefs>",
      })
      .when("/:beliefId", {
        template: "<complete-belief></complete-belief>",
      })
      .otherwise("/");
  },
]);
