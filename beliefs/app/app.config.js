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
      .otherwise({
        // template: "<h3>a donde vas pa</h3>"
        template: "<study-bible></study-bible>",
      });
  },
]);
