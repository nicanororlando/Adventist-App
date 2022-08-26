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
      .when("/", {
        template: "<beliefs></beliefs>",
      })
      .otherwise("/");
  },
]);
