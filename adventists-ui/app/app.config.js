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
      .when("/beliefs", {
        template: "<beliefs></beliefs>",
      })
      .when("/beliefs/:beliefSlug", {
        template: "<complete-belief></complete-belief>",
      })
      .when("/study-the-bible", {
        template: "<study-bible></study-bible>",
      })
      .when("/about-us", {
        template: "<about-us></about-us>",
      })
      .otherwise({
        template: "<beliefs></beliefs>",
      });
    // .otherwise({
    //   template: "<study-bible></study-bible>",
    // });
  },
]);
