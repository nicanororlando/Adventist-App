"use strict";

angular.module("completeBelief").component("completeBelief", {
  templateUrl: "../pages/completeBelief/belief.template.html",
  controller: [
    "$routeParams",
    "$http",
    function CompleteBeliefsController($routeParams, $http) {
      let url = "http://localhost:8080/api/beliefs/" + $routeParams.beliefSlug;

      $http.get(url).then(
        (res) => {
          this.belief = res.data;
        },
        (res) => {
          this.belief = [{ name: "Error!" + res.status }];
        }
      );
    },
  ],
});
